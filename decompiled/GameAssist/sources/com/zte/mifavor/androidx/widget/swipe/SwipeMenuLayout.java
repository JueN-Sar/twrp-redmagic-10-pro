package com.zte.mifavor.androidx.widget.swipe;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.PathInterpolator;
import android.widget.FrameLayout;
import android.widget.OverScroller;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.zte.extres.R;
import com.zte.mifavor.androidx.widget.RecyclerView;
import com.zte.mifavor.utils.Utils;
import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public class SwipeMenuLayout extends FrameLayout {
    private static final boolean DEBUG = false;
    public static final int DEFAULT_SCROLLER_DURATION = 200;
    private static final String TAG = "Z#SwipeMenuLayout";
    private float mClosePercent;
    private int mContentViewId;
    private int mDownX;
    private int mDownY;
    private boolean mDragging;

    @Nullable
    private ScheduledThreadPoolExecutor mExecutor;
    private Handler mHandler;
    private boolean mIsCardDelete;
    private Boolean mIsDisableHorizontalSwipe;
    private Boolean mIsDisableSwipe;
    private boolean mIsNeedDeleted;
    private int mLastX;
    private int mLastY;
    private final Object mLocker;
    private int mMenuViewId;
    private int mMenuWidth;
    private boolean mOnlyMenu;
    private float mOpenPercent;
    private int mPreValve;
    private int mPreViewValve;
    private final int mScaledMaximumFlingVelocity;
    private final int mScaledMinimumFlingVelocity;
    private final int mScaledTouchSlop;
    private final OverScroller mScroller;
    private boolean mSlidingPause;
    private String mStringID;

    @Nullable
    private SwipeContentView mSwipeContentView;
    private int mSwipeContentViewPreX;

    @Nullable
    private Horizontal mSwipeCurrentHorizontal;

    @Nullable
    private SwipeMenuView mSwipeMenuView;

    @Nullable
    private VelocityTracker mVelocityTracker;
    private int mWidth;
    PathInterpolator pathInterpolator;

    @Nullable
    private ValueAnimator valueTranslationAnim;

    @Nullable
    private ValueAnimator valueViewTranslationAnim;

    class SlidingPauseTask extends TimerTask {
        SlidingPauseTask() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            if (SwipeMenuLayout.this.mSwipeContentView != null) {
                int scrollX = SwipeMenuLayout.this.getScrollX();
                if (Math.abs(scrollX - SwipeMenuLayout.this.mSwipeContentViewPreX) < 24) {
                    SwipeMenuLayout.this.setSlidingPause(true);
                } else {
                    SwipeMenuLayout.this.mSwipeContentViewPreX = scrollX;
                    SwipeMenuLayout.this.setSlidingPause(false);
                }
            }
        }
    }

    public SwipeMenuLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void k() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.clear();
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    private void m() {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = this.mExecutor;
        if (scheduledThreadPoolExecutor != null && !scheduledThreadPoolExecutor.isShutdown()) {
            this.mExecutor.shutdownNow();
            this.mExecutor = null;
        }
        this.mSlidingPause = false;
    }

    private boolean o(float f2) {
        return f2 < ((float) (this.mWidth - this.mMenuWidth));
    }

    private boolean q(int i2, int i3) {
        if (!this.mOnlyMenu) {
            return false;
        }
        if (this.mSwipeContentView == null) {
            Log.e(TAG, this.mStringID + "judge Delete Unique Item. mSwipeContentView is null.");
            return false;
        }
        int scrollX = getScrollX();
        if (this.mIsCardDelete) {
            scrollX = this.mSwipeContentView.getScrollX();
        }
        boolean slidingPause = getSlidingPause();
        int i4 = this.mWidth;
        float f2 = i4 * this.mOpenPercent;
        float f3 = i4 * (1.0f - this.mClosePercent);
        if (i2 > 0 && scrollX > f2 && i3 < 400 && slidingPause && !this.mIsNeedDeleted) {
            this.mIsNeedDeleted = true;
            h(false);
            return true;
        }
        if (i2 < 0 && this.mIsNeedDeleted) {
            float f4 = scrollX;
            if (f2 < f4 && f4 < f3 && i3 > 125) {
                this.mIsNeedDeleted = false;
                j(false);
                return true;
            }
        }
        return false;
    }

    private void r(int i2, boolean z) {
        if (this.mSwipeCurrentHorizontal == null) {
            Log.e(TAG, this.mStringID + "judgeOpenClose mSwipeCurrentHorizontal is null.");
            return;
        }
        int scrollX = getScrollX();
        if (this.mIsCardDelete) {
            scrollX = this.mSwipeContentView.getScrollX();
        }
        this.mSwipeCurrentHorizontal.d();
        float f2 = this.mWidth * this.mOpenPercent;
        if (i2 <= 0) {
            i(false);
            return;
        }
        if (this.mOnlyMenu && scrollX > f2 && this.mIsNeedDeleted) {
            new Handler().postDelayed(new Runnable() { // from class: com.zte.mifavor.androidx.widget.swipe.SwipeMenuLayout.1
                @Override // java.lang.Runnable
                public void run() {
                    if (SwipeMenuLayout.this.getParent() instanceof RecyclerView) {
                        ((RecyclerView) SwipeMenuLayout.this.getParent()).O1();
                    }
                }
            }, 190L);
        } else if (scrollX < this.mSwipeCurrentHorizontal.b() / 2) {
            i(false);
        } else {
            j(false);
        }
    }

    private void s() {
        if (this.mOnlyMenu) {
            ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = this.mExecutor;
            if (scheduledThreadPoolExecutor != null && !scheduledThreadPoolExecutor.isShutdown()) {
                this.mExecutor.shutdownNow();
                this.mExecutor = null;
            }
            ScheduledThreadPoolExecutor scheduledThreadPoolExecutor2 = new ScheduledThreadPoolExecutor(1);
            this.mExecutor = scheduledThreadPoolExecutor2;
            scheduledThreadPoolExecutor2.scheduleAtFixedRate(new SlidingPauseTask(), 1L, 1L, TimeUnit.MILLISECONDS);
        }
    }

    private void t(int i2, int i3) {
        ValueAnimator valueAnimator = this.valueTranslationAnim;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.valueTranslationAnim.cancel();
            i2 = this.mPreValve;
        }
        ValueAnimator ofInt = ValueAnimator.ofInt(i2, i3);
        this.valueTranslationAnim = ofInt;
        if (ofInt != null) {
            ofInt.setDuration(200L);
            this.valueTranslationAnim.setInterpolator(new LinearInterpolator());
            this.valueTranslationAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.zte.mifavor.androidx.widget.swipe.SwipeMenuLayout.2
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                    int intValue = ((Integer) valueAnimator2.getAnimatedValue()).intValue();
                    SwipeMenuLayout.this.mPreValve = intValue;
                    if (!SwipeMenuLayout.this.mIsCardDelete) {
                        SwipeMenuLayout.this.scrollTo(intValue, 0);
                    } else if (SwipeMenuLayout.this.mSwipeContentView != null) {
                        SwipeMenuLayout.this.mSwipeContentView.scrollTo(intValue, 0);
                    }
                    SwipeMenuLayout.this.invalidate();
                }
            });
            this.valueTranslationAnim.addListener(new Animator.AnimatorListener() { // from class: com.zte.mifavor.androidx.widget.swipe.SwipeMenuLayout.3
                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animator) {
                    Log.i(SwipeMenuLayout.TAG, SwipeMenuLayout.this.mStringID + "+++++++++++ onAnimationCancel ");
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    Log.i(SwipeMenuLayout.TAG, SwipeMenuLayout.this.mStringID + "+++++++++++ onAnimationEnd ");
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationRepeat(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                    Log.i(SwipeMenuLayout.TAG, SwipeMenuLayout.this.mStringID + "+++++++++++ onAnimationStart ");
                }
            });
            this.valueTranslationAnim.start();
        }
    }

    private void u(final ViewGroup viewGroup, int i2, int i3) {
        if (!this.mIsCardDelete) {
            Log.i(TAG, this.mStringID + "+++++++++++ Start value View-Translation Anim out. mIsCardDelete=" + this.mIsCardDelete);
            return;
        }
        ValueAnimator valueAnimator = this.valueViewTranslationAnim;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.valueViewTranslationAnim.cancel();
            i2 = this.mPreViewValve;
        }
        ValueAnimator ofInt = ValueAnimator.ofInt(i2, i3);
        this.valueViewTranslationAnim = ofInt;
        if (ofInt != null) {
            ofInt.setDuration(200L);
            this.valueViewTranslationAnim.setInterpolator(new LinearInterpolator());
            this.valueViewTranslationAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.zte.mifavor.androidx.widget.swipe.SwipeMenuLayout.4
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                    int intValue = ((Integer) valueAnimator2.getAnimatedValue()).intValue();
                    ViewGroup viewGroup2 = viewGroup;
                    if (viewGroup2 != null) {
                        viewGroup2.scrollTo(intValue, 0);
                    }
                    SwipeMenuLayout.this.invalidate();
                    SwipeMenuLayout.this.mPreViewValve = intValue;
                }
            });
            this.valueViewTranslationAnim.addListener(new Animator.AnimatorListener() { // from class: com.zte.mifavor.androidx.widget.swipe.SwipeMenuLayout.5
                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    Log.i(SwipeMenuLayout.TAG, SwipeMenuLayout.this.mStringID + "+++++++++++ onAnimationEnd. View-Translation ");
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationRepeat(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                    Log.i(SwipeMenuLayout.TAG, SwipeMenuLayout.this.mStringID + "+++++++++++ onAnimationStart View-Translation");
                }
            });
            this.valueViewTranslationAnim.start();
        }
    }

    @Override // android.view.View
    public void computeScroll() {
        SwipeContentView swipeContentView;
        if (!this.mIsCardDelete) {
            OverScroller overScroller = this.mScroller;
            if (overScroller == null || !overScroller.computeScrollOffset()) {
                return;
            }
            scrollTo(Math.abs(this.mScroller.getCurrX()), 0);
            invalidate();
            return;
        }
        OverScroller overScroller2 = this.mScroller;
        if (overScroller2 == null || !overScroller2.computeScrollOffset() || (swipeContentView = this.mSwipeContentView) == null) {
            return;
        }
        swipeContentView.scrollTo(Math.abs(this.mScroller.getCurrX()), 0);
        invalidate();
    }

    public boolean getItemDisableHorizontalSwipe() {
        Log.w(TAG, this.mStringID + "getItemDisableHorizontalSwipe out. mIsDisableHorizontalSwipe=" + this.mIsDisableHorizontalSwipe);
        return this.mIsDisableHorizontalSwipe.booleanValue();
    }

    public int getItemMenuCount() {
        Horizontal horizontal = this.mSwipeCurrentHorizontal;
        if (horizontal != null) {
            return horizontal.a();
        }
        return 0;
    }

    public boolean getSlidingPause() {
        boolean z;
        synchronized (this.mLocker) {
            z = this.mSlidingPause;
        }
        return z;
    }

    public void h(boolean z) {
        if (this.mOnlyMenu) {
            int scrollX = getScrollX();
            if (this.mIsCardDelete) {
                scrollX = this.mSwipeContentView.getScrollX();
            }
            int i2 = scrollX;
            if (z && this.mScroller != null) {
                this.mScroller.startScroll(i2, 0, this.mWidth - Math.abs(i2), 0, 200);
                return;
            }
            t(i2, this.mWidth);
            if (this.mIsCardDelete) {
                int scrollX2 = this.mSwipeMenuView.getScrollX();
                SwipeMenuView swipeMenuView = this.mSwipeMenuView;
                u(swipeMenuView, scrollX2, this.mWidth - (swipeMenuView.getITEM_WIDTH() / 2));
            }
        }
    }

    public void i(boolean z) {
        OverScroller overScroller;
        int scrollX = getScrollX();
        if (this.mIsCardDelete) {
            scrollX = this.mSwipeContentView.getScrollX();
        }
        int i2 = scrollX;
        if (!z || (overScroller = this.mScroller) == null) {
            t(i2, 0);
        } else {
            overScroller.startScroll(i2, 0, -i2, 0, 200);
        }
        if (this.mIsCardDelete) {
            u(this.mSwipeMenuView, this.mSwipeMenuView.getScrollX(), 0);
        }
    }

    public void j(boolean z) {
        int scrollX = getScrollX();
        if (this.mIsCardDelete) {
            scrollX = this.mSwipeContentView.getScrollX();
        }
        int i2 = scrollX;
        if (!z || this.mScroller == null) {
            t(i2, this.mMenuWidth);
        } else {
            this.mScroller.startScroll(i2, 0, -(this.mMenuWidth - Math.abs(i2)), 0, 200);
        }
        if (this.mIsCardDelete) {
            u(this.mSwipeMenuView, this.mSwipeMenuView.getScrollX(), this.mMenuWidth);
        }
    }

    public void l() {
        if (!this.mIsCardDelete) {
            scrollTo(0, 0);
            return;
        }
        SwipeContentView swipeContentView = this.mSwipeContentView;
        if (swipeContentView != null) {
            swipeContentView.scrollTo(0, 0);
        }
    }

    public boolean n() {
        Horizontal horizontal = this.mSwipeCurrentHorizontal;
        return horizontal != null && horizontal.e();
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        Log.d(TAG, this.mStringID + "onFinishInflate: in.");
        super.onFinishInflate();
        int i2 = this.mMenuViewId;
        if (i2 != 0) {
            View findViewById = findViewById(i2);
            this.mSwipeCurrentHorizontal = new Horizontal(findViewById);
            if (findViewById instanceof SwipeMenuView) {
                SwipeMenuView swipeMenuView = (SwipeMenuView) findViewById;
                this.mSwipeMenuView = swipeMenuView;
                this.mIsCardDelete = swipeMenuView.getIsCardDelete();
            }
        }
        int i3 = this.mContentViewId;
        if (i3 != 0) {
            View findViewById2 = findViewById(i3);
            if (findViewById2 instanceof SwipeContentView) {
                this.mSwipeContentView = (SwipeContentView) findViewById2;
            }
        }
        Log.d(TAG, this.mStringID + "onFinishInflate out. mIsCardDelete=" + this.mIsCardDelete + ", mSwipeMenuView=" + this.mSwipeMenuView + ", mSwipeContentView=" + this.mSwipeContentView);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        boolean onInterceptTouchEvent = super.onInterceptTouchEvent(motionEvent);
        if (this.mIsDisableSwipe.booleanValue() || this.mIsDisableHorizontalSwipe.booleanValue()) {
            m();
            return onInterceptTouchEvent;
        }
        if (action == 0) {
            int x = (int) motionEvent.getX();
            this.mLastX = x;
            this.mDownX = x;
            this.mDownY = (int) motionEvent.getY();
            this.mLastX = (int) motionEvent.getX();
            this.mLastY = (int) motionEvent.getY();
            this.mDragging = false;
            this.mIsNeedDeleted = false;
            s();
            return false;
        }
        if (action == 1) {
            m();
            if (!p() || !o(motionEvent.getX())) {
                return false;
            }
            i(false);
            return true;
        }
        if (action == 2) {
            int x2 = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            int i2 = x2 - this.mDownX;
            return Math.abs(i2) > this.mScaledTouchSlop && Math.abs(i2) > Math.abs(y - this.mDownY);
        }
        if (action != 3) {
            return onInterceptTouchEvent;
        }
        if (!this.mScroller.isFinished()) {
            this.mScroller.abortAnimation();
        }
        m();
        return false;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int i6;
        int i7;
        int i8;
        this.mIsCardDelete = this.mSwipeMenuView.getIsCardDelete();
        SwipeContentView swipeContentView = this.mSwipeContentView;
        if (swipeContentView != null) {
            int measuredWidthAndState = swipeContentView.getMeasuredWidthAndState();
            int measuredHeightAndState = this.mSwipeContentView.getMeasuredHeightAndState();
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mSwipeContentView.getLayoutParams();
            i6 = getPaddingLeft();
            i7 = getPaddingTop() + layoutParams.topMargin;
            this.mSwipeContentView.layout(i6, i7, measuredWidthAndState + i6, measuredHeightAndState + i7);
        } else {
            i6 = 0;
            i7 = 0;
        }
        Horizontal horizontal = this.mSwipeCurrentHorizontal;
        if (horizontal != null) {
            View c2 = horizontal.c();
            int measuredWidthAndState2 = c2.getMeasuredWidthAndState();
            int measuredHeightAndState2 = c2.getMeasuredHeightAndState();
            if (this.mIsCardDelete) {
                i8 = measuredWidthAndState2 + i6 + Utils.c(getContext(), 64.0f);
            } else {
                i6 = getMeasuredWidthAndState();
                i8 = measuredWidthAndState2 + i6;
            }
            c2.layout(i6, i7, i8, measuredHeightAndState2 + i7);
        }
        Horizontal horizontal2 = this.mSwipeCurrentHorizontal;
        if (horizontal2 != null && this.mSwipeContentView != null) {
            this.mMenuWidth = horizontal2.a() * this.mSwipeCurrentHorizontal.b();
            this.mOnlyMenu = n();
        }
        this.mWidth = getWidth();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int i2;
        int action = motionEvent.getAction();
        boolean onTouchEvent = super.onTouchEvent(motionEvent);
        if (!this.mIsDisableSwipe.booleanValue() && !this.mIsDisableHorizontalSwipe.booleanValue()) {
            if (this.mVelocityTracker == null) {
                this.mVelocityTracker = VelocityTracker.obtain();
            }
            VelocityTracker velocityTracker = this.mVelocityTracker;
            if (velocityTracker != null) {
                velocityTracker.addMovement(motionEvent);
            }
            if (action != 0) {
                if (action == 1) {
                    m();
                    VelocityTracker velocityTracker2 = this.mVelocityTracker;
                    if (velocityTracker2 != null) {
                        velocityTracker2.computeCurrentVelocity(1000, this.mScaledMaximumFlingVelocity);
                        i2 = (int) this.mVelocityTracker.getXVelocity();
                    } else {
                        i2 = 0;
                    }
                    r((int) (this.mDownX - motionEvent.getX()), Math.abs(i2) > this.mScaledMinimumFlingVelocity * 4);
                    k();
                    this.mDragging = false;
                    this.mIsNeedDeleted = false;
                } else if (action == 2) {
                    int x = (int) motionEvent.getX();
                    int x2 = (int) (this.mLastX - motionEvent.getX());
                    int y = (int) (this.mLastY - motionEvent.getY());
                    if (!this.mDragging && Math.abs(x2) > this.mScaledTouchSlop && Math.abs(x2) > Math.abs(y)) {
                        this.mDragging = true;
                    }
                    int scrollX = getScrollX();
                    if (this.mDragging && ((x2 > 0 || p()) && scrollX >= 0)) {
                        if (x2 < 0 && Math.abs(x2) > Math.abs(scrollX)) {
                            x2 = -Math.abs(scrollX);
                        }
                        motionEvent.getX();
                        boolean z = this.mOnlyMenu;
                        if (z || (!z && scrollX < this.mMenuWidth * 1.2f)) {
                            if (this.mIsCardDelete) {
                                this.mSwipeContentView.scrollBy(x2, 0);
                                this.mSwipeMenuView.scrollBy(x2, 0);
                            } else {
                                scrollBy(x2, 0);
                            }
                        }
                    }
                    this.mLastX = (int) motionEvent.getX();
                    this.mLastY = (int) motionEvent.getY();
                    if (q(x2, x)) {
                        this.mDragging = false;
                        return false;
                    }
                } else if (action == 3) {
                    m();
                    r((int) (this.mDownX - motionEvent.getX()), false);
                    k();
                    this.mDragging = false;
                    this.mIsNeedDeleted = false;
                }
            } else {
                this.mLastX = (int) motionEvent.getX();
                this.mLastY = (int) motionEvent.getY();
                if (!this.mScroller.isFinished()) {
                    this.mScroller.abortAnimation();
                }
                this.mDragging = false;
            }
        }
        return onTouchEvent;
    }

    public boolean p() {
        if (!this.mIsCardDelete) {
            return getScrollX() > 0;
        }
        int scrollX = getScrollX();
        int scrollX2 = this.mSwipeMenuView.getScrollX();
        int scrollX3 = this.mSwipeContentView.getScrollX();
        Log.d(TAG, "isMenuOpen, scrollx=" + scrollX + ", scrollx1=" + scrollX2 + ", scrollx2=" + scrollX3);
        return scrollX > 0 || scrollX2 > 0 || scrollX3 > 0;
    }

    @Override // android.view.View
    public void scrollBy(int i2, int i3) {
        super.scrollBy(i2, i3);
    }

    public void setDisableSwipeDelete(Boolean bool) {
        this.mIsDisableSwipe = bool;
        Log.d(TAG, this.mStringID + "setDisableSwipeDelete out. mIsDisableSwipe=" + this.mIsDisableSwipe);
    }

    public void setItemDisableHorizontalSwipe(Boolean bool) {
        this.mIsDisableHorizontalSwipe = bool;
        Log.w(TAG, this.mStringID + "setItemDisableHorizontalSwipe out. mIsDisableHorizontalSwipe=" + this.mIsDisableHorizontalSwipe);
    }

    public void setSlidingPause(boolean z) {
        synchronized (this.mLocker) {
            this.mSlidingPause = z;
        }
    }

    public SwipeMenuLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mContentViewId = 0;
        this.mMenuViewId = 0;
        this.mDragging = false;
        this.mIsNeedDeleted = false;
        this.mOpenPercent = 0.45f;
        this.mClosePercent = 0.06f;
        this.mExecutor = null;
        this.mSwipeContentViewPreX = 0;
        this.mSlidingPause = false;
        this.mLocker = new Object();
        Boolean bool = Boolean.FALSE;
        this.mIsDisableSwipe = bool;
        this.mIsDisableHorizontalSwipe = bool;
        this.mOnlyMenu = false;
        this.valueTranslationAnim = null;
        this.valueViewTranslationAnim = null;
        this.mHandler = new Handler();
        this.mMenuWidth = 1;
        this.mStringID = "";
        this.mIsCardDelete = false;
        this.pathInterpolator = new PathInterpolator(0.33f, 0.0f, 0.0f, 1.0f);
        this.mPreValve = 0;
        this.mPreViewValve = 0;
        setClickable(true);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SwipeMenuLayout);
        this.mContentViewId = obtainStyledAttributes.getResourceId(R.styleable.SwipeMenuLayout_contentViewId, this.mContentViewId);
        this.mMenuViewId = obtainStyledAttributes.getResourceId(R.styleable.SwipeMenuLayout_menuViewId, this.mMenuViewId);
        obtainStyledAttributes.recycle();
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        this.mScaledTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mScaledMinimumFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        this.mScaledMaximumFlingVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        this.mScroller = new OverScroller(getContext(), this.pathInterpolator);
        String frameLayout = toString();
        if (frameLayout != null) {
            int indexOf = frameLayout.indexOf("{");
            this.mStringID = "[" + frameLayout.substring(indexOf + 1, indexOf + 7) + "] ";
        }
    }
}
