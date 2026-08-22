package com.zte.mifavor.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringUtil;
import com.google.android.gms.common.api.Api;
import com.zte.extres.R;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class MenuScrollerView extends HorizontalScrollView {
    private static final int ATTACHMENT_FRICTION = 20;
    private static final int ATTACHMENT_TENSION = 120;
    private static final boolean DEBUG = false;
    private static final float DISPLACEMENT_FROM_FING_THRESHOLD = 10.0f;
    private static final double DISPLACEMENT_FROM_REST_THRESHOLD = 0.005d;
    private static final int EDGE_REBOUND_X = 130;
    private static final int MAIN_FRICTION = 20;
    private static final int MAIN_TENSION = 50;
    private static final String TAG = "Scroll#MenuScrollerView";
    public int MAX_SPRING_ID;
    private int mActivePointerId;

    @Nullable
    protected LinearLayout mChild;
    private Context mContext;
    private int mControlIndex;

    @Nullable
    private View mFirstItem;
    private float mFlingVelocity;
    private boolean mHadInitSpringChain;
    private boolean mIsEffective;
    private boolean mIsFlingToEdge;
    private boolean mIsLeftFling;
    private boolean mIsMoveToLeftEdge;
    private boolean mIsMoveToRightEdge;
    private boolean mIsRightFling;
    private final boolean mIsRtl;
    private boolean mIsUpdateSpringSystem;
    private int mItemCount;
    private int mItemGapPx;
    private boolean mItemHasMagin;
    private int mItemHeight;
    private int mItemWidth;
    private boolean mLastDown;
    private float mLastDownX;
    private float mLastDownXlat;

    @Nullable
    private View mLastItem;

    @Nullable
    public MenuItemViews mLastSelected;
    private int mMaxControlSpringID;
    private float mMaxMoveDistance;
    private int mMaxmumVelocity;
    private int mMinnumVelocity;
    private int mSelectedPosition;

    @Nullable
    private SpringChain mSpringChain;
    private List<View> mSpringChainViewList;
    private String mStringID;

    @Nullable
    private VelocityTracker mVelocityTracker;

    /* renamed from: com.zte.mifavor.widget.MenuScrollerView$1, reason: invalid class name */
    class AnonymousClass1 extends SimpleSpringListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ View f17685a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ MenuScrollerView f17686b;

        @Override // com.facebook.rebound.SimpleSpringListener, com.facebook.rebound.SpringListener
        public void a(Spring spring) {
            String obj = spring.toString();
            if (obj != null) {
                this.f17686b.mStringID = "[" + obj.substring(obj.length() - 4) + "] ";
            }
            float d2 = (float) spring.d();
            this.f17685a.setTranslationX(d2);
            if (this.f17686b.mIsFlingToEdge && this.f17686b.mSpringChain != null && this.f17686b.m(this.f17685a)) {
                Log.d(MenuScrollerView.TAG, "do Edge Animation touch edge, rebound item(" + this.f17686b.mSpringChainViewList.indexOf(this.f17685a) + ").value=" + d2);
                this.f17686b.mSpringChain.f17769a = true;
                if (this.f17686b.mSpringChain.f() != null) {
                    double d3 = this.f17686b.mSpringChain.f().d();
                    Log.d(MenuScrollerView.TAG, this.f17686b.mStringID + "onSpringUpdate. set End Value 0. currentValue = " + d3);
                    ValueAnimator ofFloat = ValueAnimator.ofFloat((float) d3, 0.0f);
                    if (ofFloat != null) {
                        ofFloat.setDuration(50L);
                        ofFloat.setInterpolator(new LinearInterpolator());
                        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.zte.mifavor.widget.MenuScrollerView.1.1
                            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                Spring f2;
                                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                                Log.d(MenuScrollerView.TAG, "+++++++++++ Update value Animator. value = " + floatValue);
                                if (AnonymousClass1.this.f17686b.mSpringChain == null || (f2 = AnonymousClass1.this.f17686b.mSpringChain.f()) == null) {
                                    return;
                                }
                                f2.n(floatValue);
                            }
                        });
                        Log.d(MenuScrollerView.TAG, "+++++++++++ Start valueAnimator. from = " + d3 + ", to = 0");
                        ofFloat.start();
                    }
                }
                this.f17686b.mIsRightFling = false;
                this.f17686b.mIsLeftFling = false;
                this.f17686b.mIsFlingToEdge = false;
            }
        }

        @Override // com.facebook.rebound.SimpleSpringListener, com.facebook.rebound.SpringListener
        public void b(Spring spring) {
        }

        @Override // com.facebook.rebound.SimpleSpringListener, com.facebook.rebound.SpringListener
        public void c(Spring spring) {
        }

        @Override // com.facebook.rebound.SimpleSpringListener, com.facebook.rebound.SpringListener
        public void d(Spring spring) {
        }
    }

    public interface MenuItemViews {
        void a(boolean z);
    }

    public MenuScrollerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void k() {
        SpringChain springChain;
        SpringChain springChain2;
        if (!this.mIsEffective) {
            Log.w(TAG, "do Edge Animation. do nothing.");
            return;
        }
        try {
            int size = this.mSpringChainViewList.size();
            if (size <= 3) {
                return;
            }
            VelocityTracker velocityTracker = this.mVelocityTracker;
            int xVelocity = velocityTracker != null ? (int) velocityTracker.getXVelocity() : 0;
            this.mIsFlingToEdge = true;
            Log.d(TAG, "do Edge Animation in. mFlingVelocity=" + this.mFlingVelocity + ", mMaxmumVelocity=" + this.mMaxmumVelocity + ", mMaxMoveDistance=" + this.mMaxMoveDistance + ", mFlingDistanceRange=" + (Math.abs(this.mFlingVelocity / this.mMaxmumVelocity) * this.mMaxMoveDistance * 1.5f) + ", xVelocity=" + xVelocity);
            if (this.mIsLeftFling && (springChain2 = this.mSpringChain) != null) {
                springChain2.f17769a = false;
                int i2 = size - 1;
                springChain2.h(i2);
                this.mControlIndex = i2;
                int i3 = i2 - this.mMaxControlSpringID;
                Log.w(TAG, "do Edge Animation left. startIdx=" + i3 + ", size = " + size);
                for (int i4 = i3; i4 < size; i4++) {
                    ((Spring) this.mSpringChain.e().get(i4)).n(-(r5 / ((float) Math.sqrt((i4 - i3) + 1))));
                }
            }
            if (this.mIsRightFling && (springChain = this.mSpringChain) != null) {
                springChain.h(0);
                this.mSpringChain.f17769a = false;
                this.mControlIndex = 0;
                int i5 = this.mMaxControlSpringID;
                Log.w(TAG, "do Edge Animation right. startIdx=" + i5 + ", size = " + size);
                for (int i6 = i5; i6 >= 0; i6--) {
                    ((Spring) this.mSpringChain.e().get(i6)).n(r5 / ((float) Math.sqrt((i5 - i6) + 1)));
                }
            }
            Log.d(TAG, "do Edge Animation out. IsLeftFling=" + this.mIsLeftFling + ", IsRightFling = " + this.mIsRightFling);
            this.mIsLeftFling = false;
            this.mIsRightFling = false;
        } catch (Exception e2) {
            Log.e(TAG, "do Edge Animation error, e =", e2);
        }
    }

    private int l(float f2) {
        int i2 = Api.BaseClientBuilder.API_PRIORITY_OTHER;
        int i3 = -1;
        for (int i4 = 0; i4 < this.mSpringChainViewList.size(); i4++) {
            this.mSpringChainViewList.get(i4).getLocationOnScreen(new int[]{0, 0});
            int abs = (int) Math.abs(f2 - (r5[0] + (r4.getWidth() / 2)));
            if (abs < i2) {
                i3 = i4;
                i2 = abs;
            }
        }
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean m(View view) {
        boolean z;
        View view2;
        View view3;
        try {
            int indexOf = this.mSpringChainViewList.indexOf(view);
            if (this.mControlIndex != 0 || indexOf != this.mMaxControlSpringID || Math.abs(view.getTranslationX()) <= 130.0f || (view3 = this.mFirstItem) == null || view3.getTranslationX() == 0.0f) {
                z = false;
            } else {
                Log.d(TAG, this.mStringID + "Left had Do Edge Animation = true");
                z = true;
            }
            if (this.mControlIndex != this.mSpringChainViewList.size() - 1 || indexOf != (this.mSpringChainViewList.size() - 1) - this.mMaxControlSpringID || Math.abs(view.getTranslationX()) <= 130.0f || (view2 = this.mLastItem) == null || view2.getTranslationX() == 0.0f) {
                return z;
            }
            Log.d(TAG, this.mStringID + "Right had Do Edge Animation = true");
            return true;
        } catch (Exception e2) {
            Log.e(TAG, "had Do Edge Animation, error = ", e2);
            return false;
        }
    }

    private boolean o() {
        int left = getLeft();
        View view = this.mFirstItem;
        if (view != null) {
            left = view.getLeft();
        }
        return getScrollX() == 0 || getScrollX() < left;
    }

    private boolean p() {
        int right = getRight();
        View view = this.mLastItem;
        if (view != null) {
            right = view.getRight();
        }
        return getWidth() + getScrollX() >= right;
    }

    @Override // android.widget.HorizontalScrollView
    public void fling(int i2) {
        Spring spring;
        int i3;
        SpringChain springChain = this.mSpringChain;
        if (springChain != null) {
            i3 = springChain.g();
            if (i3 <= 3 && Math.abs(i2) < 6000) {
                super.fling(i2);
                Log.w(TAG, "+++ fling out. velocityX=" + i2 + ", maxId=" + i3);
                return;
            }
            spring = this.mSpringChain.f();
        } else {
            spring = null;
            i3 = 0;
        }
        float c2 = spring != null ? (float) spring.c() : 0.0f;
        Log.d(TAG, "+++ fling. velocityX=" + i2 + ", displacementFromRest=" + c2 + ", maxId=" + i3);
        if (c2 > DISPLACEMENT_FROM_FING_THRESHOLD) {
            i2 = i2 > 0 ? this.mMinnumVelocity : -this.mMinnumVelocity;
        }
        Log.d(TAG, "+++ fling. velocityX=" + i2 + ", mMinnumVelocity=" + this.mMinnumVelocity + ", displacementFromRest=" + c2 + ", springControl=" + this.mControlIndex);
        super.fling(i2);
        this.mIsLeftFling = false;
        this.mIsRightFling = false;
        if (i2 > 0) {
            this.mIsLeftFling = true;
            this.mFlingVelocity = i2;
        } else if (i2 < 0) {
            this.mIsRightFling = true;
            this.mFlingVelocity = i2;
        }
    }

    protected boolean j(float f2) {
        boolean z;
        if (!this.mIsEffective) {
            return false;
        }
        this.mIsMoveToLeftEdge = o();
        boolean p2 = p();
        this.mIsMoveToRightEdge = p2;
        if ((!p2 || f2 >= 0.0f) && ((!(z = this.mIsMoveToLeftEdge) || f2 <= 0.0f) && !(z && p2))) {
            return false;
        }
        if (!this.mHadInitSpringChain) {
            int size = f2 > 0.0f ? 0 : this.mSpringChainViewList.size() - 1;
            SpringChain springChain = this.mSpringChain;
            if (springChain != null) {
                SpringChain h2 = springChain.h(size);
                if (h2 != null && h2.f() != null) {
                    h2.f().l(this.mLastDownXlat);
                }
                this.mSpringChain.f17769a = false;
            }
            this.mHadInitSpringChain = true;
            this.mControlIndex = size;
        }
        return true;
    }

    public void n(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            Log.d(TAG, "handleRowTouch ACTION_DOWN");
            this.mActivePointerId = motionEvent.getPointerId(0);
            this.mLastDownXlat = view.getTranslationX();
            this.mLastDownX = motionEvent.getRawX();
            this.mIsRightFling = false;
            this.mIsLeftFling = false;
            this.mLastDown = true;
            this.mIsUpdateSpringSystem = false;
            VelocityTracker velocityTracker = this.mVelocityTracker;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
            if (this.mVelocityTracker != null) {
                VelocityTracker obtain = VelocityTracker.obtain();
                this.mVelocityTracker = obtain;
                obtain.addMovement(motionEvent);
                return;
            }
            return;
        }
        float f2 = 0.0f;
        if (action != 1) {
            if (action == 2) {
                int findPointerIndex = motionEvent.findPointerIndex(this.mActivePointerId);
                if (findPointerIndex == -1 || !this.mLastDown) {
                    return;
                }
                view.getLocationOnScreen(new int[]{0, 0});
                float x = ((motionEvent.getX(findPointerIndex) + r8[0]) - this.mLastDownX) + this.mLastDownXlat;
                if (j(x)) {
                    SpringChain springChain = this.mSpringChain;
                    r7 = springChain != null ? springChain.f() : null;
                    if (r7 == null) {
                        return;
                    }
                    float abs = Math.abs(x);
                    float f3 = this.mMaxMoveDistance;
                    if (abs <= f3) {
                        r7.n(x / (Math.abs(l(this.mLastDownX) - this.mControlIndex) + 1));
                        this.mIsUpdateSpringSystem = true;
                        VelocityTracker velocityTracker2 = this.mVelocityTracker;
                        if (velocityTracker2 != null) {
                            velocityTracker2.addMovement(motionEvent);
                            return;
                        }
                        return;
                    }
                    if (this.mIsUpdateSpringSystem) {
                        return;
                    }
                    float a2 = (float) SpringUtil.a(x > 0.0f ? f3 : -f3, 0.0d, 1.0d, 1.0d, 1.1d);
                    Log.w(TAG, "handleRowTouch ACTION_MOVE spring = " + r7 + ", mappedValue = " + a2);
                    r7.n((double) a2);
                    this.mIsUpdateSpringSystem = true;
                    return;
                }
                return;
            }
            if (action != 3) {
                return;
            }
        }
        this.mIsMoveToLeftEdge = false;
        this.mIsMoveToRightEdge = false;
        this.mLastDown = false;
        this.mIsUpdateSpringSystem = false;
        this.mHadInitSpringChain = false;
        VelocityTracker velocityTracker3 = this.mVelocityTracker;
        if (velocityTracker3 != null) {
            velocityTracker3.addMovement(motionEvent);
            this.mVelocityTracker.computeCurrentVelocity(1000);
            f2 = this.mVelocityTracker.getXVelocity();
        }
        SpringChain springChain2 = this.mSpringChain;
        if (springChain2 != null) {
            r7 = springChain2.f();
            this.mSpringChain.f17769a = true;
        }
        Log.d(TAG, "handleRowTouch ACTION_UP isLoose is true. spring = " + r7 + ", XVelocity = " + f2);
        if (r7 != null) {
            r7.n(0.0d);
        }
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if ((motionEvent.getAction() & 255) == 0) {
            onTouchEvent(motionEvent);
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        setMeasuredDimension(i2, i3);
        super.onMeasure(i2, i3);
    }

    @Override // android.view.View
    protected void onScrollChanged(int i2, int i3, int i4, int i5) {
        super.onScrollChanged(i2, i3, i4, i5);
        View childAt = getChildAt(0);
        int scrollX = getScrollX();
        if (childAt != null && getWidth() + scrollX >= childAt.getMeasuredWidth() && this.mIsLeftFling) {
            Log.d(TAG, "onScrollChanged left. call do Edge Animation.");
            k();
        } else {
            if (scrollX > 0 || !this.mIsRightFling) {
                return;
            }
            Log.d(TAG, "onScrollChanged right. call do Edge Animation.");
            k();
        }
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        motionEvent.getAction();
        if (this.mChild != null) {
            n(this, motionEvent);
        }
        return super.onTouchEvent(motionEvent);
    }

    public void q(int i2) {
        Log.d(TAG, "set Position scroll to  position = " + i2 + ", mItemWidth = " + this.mItemWidth + ", mItemHeight = " + this.mItemHeight);
        smoothScrollTo(i2 * (this.mItemWidth + (this.mItemGapPx / 2)), 0);
    }

    @Override // android.view.View
    public void scrollBy(int i2, int i3) {
        super.scrollBy(i2, i3);
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    public void scrollTo(int i2, int i3) {
        super.scrollTo(i2, i3);
    }

    public void setControlSpringID(int i2) {
        this.mMaxControlSpringID = 6;
        this.MAX_SPRING_ID = i2;
        int i3 = i2 / 2;
        if (i3 < 6) {
            this.mMaxControlSpringID = i3;
        }
        SpringChain springChain = this.mSpringChain;
        if (springChain != null) {
            springChain.i(i2);
        } else {
            Log.d(TAG, "setControlSpringID error. mSpringChain is null.");
        }
        Log.d(TAG, "set Control Spring ID,  MAX_SPRING_ID = " + this.MAX_SPRING_ID + ", mMaxControlSpringID = " + this.mMaxControlSpringID + ", mIsEffective = " + this.mIsEffective);
    }

    public void setHasMargin(boolean z) {
        this.mItemHasMagin = z;
        Log.d(TAG, "setHasMargin mItemHasMagin = " + this.mItemHasMagin);
    }

    public void setItemCount(int i2) {
        this.mItemCount = i2;
        Log.d(TAG, "setItemCount mItemCount = " + this.mItemCount);
    }

    public void setItemGap(int i2) {
        this.mItemGapPx = i2;
        Log.d(TAG, "setItemGap mItemGapPx = " + this.mItemGapPx);
    }

    public void setSelectItem(MenuItemViews menuItemViews) {
        MenuItemViews menuItemViews2 = this.mLastSelected;
        if (menuItemViews2 != null) {
            menuItemViews2.a(false);
        }
        this.mLastSelected = menuItemViews;
        if (menuItemViews != null) {
            menuItemViews.a(true);
        }
    }

    public void setSelectPosition(int i2) {
        this.mSelectedPosition = i2;
        Log.d(TAG, "set Select Position and smoothScrollTo, mSelectedPosition = " + this.mSelectedPosition);
        q(this.mSelectedPosition);
    }

    public void setSpringToEffective(boolean z) {
        this.mIsEffective = z;
        Log.d(TAG, "set Spring To Effective  mIsEffective = " + this.mIsEffective);
    }

    public MenuScrollerView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mSelectedPosition = 0;
        this.mIsLeftFling = false;
        this.mIsRightFling = false;
        this.mIsFlingToEdge = false;
        this.mIsMoveToLeftEdge = false;
        this.mIsMoveToRightEdge = false;
        this.mVelocityTracker = null;
        this.mIsUpdateSpringSystem = false;
        this.mSpringChainViewList = new ArrayList();
        this.mSpringChain = null;
        this.mControlIndex = -1;
        this.mMaxControlSpringID = 6;
        this.MAX_SPRING_ID = 10;
        this.mIsEffective = true;
        this.mItemHasMagin = true;
        this.mItemCount = 1000;
        this.mStringID = "";
        this.mContext = context;
        this.mItemGapPx = getResources().getDimensionPixelSize(R.dimen.sensorui_recycler_item_gap_in_horizontal);
        this.mIsRtl = false;
        this.mMaxMoveDistance = ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getWidth() * 0.1f;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.mMaxmumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        this.mMinnumVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        setHorizontalFadingEdgeEnabled(false);
    }
}
