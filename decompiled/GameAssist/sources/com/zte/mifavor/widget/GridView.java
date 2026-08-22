package com.zte.mifavor.widget;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.dynamicanimation.animation.DynamicAnimation;
import com.zte.mifavor.utils.SpringAnimationCommon;
import com.zte.mifavor.utils.overscroll.IOverScrollDecor;
import com.zte.mifavor.utils.overscroll.OverScrollDecoratorHelper;

/* loaded from: classes2.dex */
public class GridView extends android.widget.GridView {
    private static final String TAG = "Z#View-SpringGV";

    @Nullable
    private IOverScrollDecor iOverScrollDecor;

    @NonNull
    private GestureDetector mGestureDetector;
    private int mInterruptDirection;
    private boolean mIsDispalyMotion;
    private boolean mIsUseSpring;

    @NonNull
    private OverScrollDecoratorHelper mOverScrollDecoratorHelper;

    @NonNull
    private SpringAnimationCommon mSpringAnimationCommon;
    private int mTotalyDy;

    private class TouchGesture extends GestureDetector.SimpleOnGestureListener {
        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
            Log.d(GridView.TAG, "onFling+++++++++++++++++++++, velocityX = " + f2 + ", velocityY = " + f3 + ", canScrollUp = " + GridView.this.canScrollVertically(-1) + ", canScrollDown = " + GridView.this.canScrollVertically(1) + ", IsBeingDragged = " + GridView.this.getIsBeingDragged());
            if (!GridView.this.getIsBeingDragged()) {
                if (GridView.this.mIsUseSpring && GridView.this.mSpringAnimationCommon != null) {
                    GridView.this.mSpringAnimationCommon.h((int) (-f3));
                }
                return true;
            }
            Log.w(GridView.TAG, "fling+++++++++++++++++++++, ignore fling, velocityY = " + f3);
            return false;
        }

        private TouchGesture() {
        }
    }

    public GridView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.gridViewStyle);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.mSpringAnimationCommon == null) {
            Log.w(TAG, "====================== dispatchTouchEvent error. mSpringAnimationCommon is null. ");
            return super.dispatchTouchEvent(motionEvent);
        }
        float translationY = getTranslationY();
        if (motionEvent.getAction() != 0) {
            Log.d(TAG, "====================== dispatchTouchEvent other. ev = " + motionEvent.getAction() + ", translationY = " + translationY);
        } else {
            if (this.mSpringAnimationCommon.p().f()) {
                this.mSpringAnimationCommon.p().t();
                this.mSpringAnimationCommon.p().b();
                Log.i(TAG, "====================== dispatchTouchEvent. ev = " + motionEvent.getAction() + ", translationY = " + translationY + ", skip To End and cancel!");
            }
            this.mSpringAnimationCommon.f();
            this.mTotalyDy = 0;
        }
        boolean dispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
        Log.d(TAG, "+++++++++++++++++++= dispatchTouchEvent out.ev = " + motionEvent.getAction() + ", ret = " + dispatchTouchEvent);
        return dispatchTouchEvent;
    }

    public boolean e() {
        if (!g()) {
            Log.d(TAG, "isCollapsed don't support sink. return true.");
            return true;
        }
        SpringAnimationCommon springAnimationCommon = this.mSpringAnimationCommon;
        boolean t = springAnimationCommon != null ? springAnimationCommon.t() : false;
        Log.d(TAG, "isCollapsed out. isCollapsed = " + t);
        return t;
    }

    public boolean f() {
        SpringAnimationCommon springAnimationCommon = this.mSpringAnimationCommon;
        boolean v = springAnimationCommon != null ? springAnimationCommon.v() : false;
        Log.d(TAG, "isDisableSink out. isSupport = " + v);
        return v;
    }

    public boolean g() {
        SpringAnimationCommon springAnimationCommon = this.mSpringAnimationCommon;
        boolean w = springAnimationCommon != null ? springAnimationCommon.w() : false;
        Log.d(TAG, "isSupprtSink out. isSupport = " + w);
        return w;
    }

    public int getInterruptSlideDirection() {
        return this.mInterruptDirection;
    }

    public boolean getIsBeingDragged() {
        IOverScrollDecor iOverScrollDecor = this.iOverScrollDecor;
        if (iOverScrollDecor != null) {
            return iOverScrollDecor.getIsBeingDragged();
        }
        return false;
    }

    public boolean getUseSpring() {
        return this.mIsUseSpring;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.d(TAG, "onFinishInflate in. mIsUseSpring = " + this.mIsUseSpring);
        if (this.mIsUseSpring) {
            setOnScrollListener(new AbsListView.OnScrollListener() { // from class: com.zte.mifavor.widget.GridView.1

                /* renamed from: a, reason: collision with root package name */
                private SparseArray f17648a = new SparseArray(0);

                /* renamed from: b, reason: collision with root package name */
                private int f17649b = 0;

                /* renamed from: com.zte.mifavor.widget.GridView$1$ItemRecod */
                class ItemRecod {

                    /* renamed from: a, reason: collision with root package name */
                    int f17651a = 0;

                    /* renamed from: b, reason: collision with root package name */
                    int f17652b = 0;

                    ItemRecod(AnonymousClass1 anonymousClass1) {
                    }
                }

                private int a() {
                    int i2;
                    int i3 = 0;
                    int i4 = 0;
                    while (true) {
                        i2 = this.f17649b;
                        if (i3 >= i2) {
                            break;
                        }
                        ItemRecod itemRecod = (ItemRecod) this.f17648a.get(i3);
                        if (itemRecod != null) {
                            i4 += itemRecod.f17651a;
                        }
                        i3++;
                    }
                    ItemRecod itemRecod2 = (ItemRecod) this.f17648a.get(i2);
                    if (itemRecod2 == null) {
                        itemRecod2 = new ItemRecod(this);
                    }
                    return i4 - itemRecod2.f17652b;
                }

                @Override // android.widget.AbsListView.OnScrollListener
                public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
                    this.f17649b = i2;
                    View childAt = absListView.getChildAt(0);
                    if (childAt != null) {
                        ItemRecod itemRecod = (ItemRecod) this.f17648a.get(i2);
                        if (itemRecod == null) {
                            itemRecod = new ItemRecod(this);
                        }
                        itemRecod.f17651a = childAt.getHeight();
                        itemRecod.f17652b = childAt.getTop();
                        this.f17648a.append(i2, itemRecod);
                        if (itemRecod.f17651a != 0) {
                            GridView.this.mTotalyDy -= a() / childAt.getHeight();
                            GridView.this.mSpringAnimationCommon.C(GridView.this.mTotalyDy);
                        }
                    }
                }

                @Override // android.widget.AbsListView.OnScrollListener
                public void onScrollStateChanged(AbsListView absListView, int i2) {
                    Log.d(GridView.TAG, "onScrollStateChanged in, state = " + i2);
                    if (!GridView.this.mIsUseSpring || GridView.this.mSpringAnimationCommon == null) {
                        Log.w(GridView.TAG, "onScrollStateChanged don't use animation, mIsUseSpring = " + GridView.this.mIsUseSpring);
                        return;
                    }
                    if (i2 != 0 || !GridView.this.mSpringAnimationCommon.t()) {
                        Log.d(GridView.TAG, "onScrollStateChanged out, state = " + i2);
                        return;
                    }
                    Log.w(GridView.TAG, "onScrollStateChanged, state = " + i2 + ", canScrollUp = " + GridView.this.canScrollVertically(-1) + ", canScrollDown = " + GridView.this.canScrollVertically(1));
                    GridView.this.mSpringAnimationCommon.x(GridView.this.getChildAt(0), 1002);
                }
            });
        }
    }

    @Override // android.widget.AbsListView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        Log.d(TAG, "+++++++++++++++++++= onTouchEvent ev = " + motionEvent.getAction());
        GestureDetector gestureDetector = this.mGestureDetector;
        if (gestureDetector != null) {
            gestureDetector.onTouchEvent(motionEvent);
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setDampingRatio(String str) {
        try {
            Log.d(TAG, "setDampingRatio dampingRatio = " + str);
            this.mSpringAnimationCommon.y(Float.parseFloat(str));
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
        }
    }

    public void setDragAmplitude(String str) {
        try {
            Log.d(TAG, "setDragAmplitude dragAmplitude = " + str);
            this.mSpringAnimationCommon.z(Integer.valueOf(str).intValue());
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
        }
    }

    public void setInterruptSlideDirection(int i2) {
        this.mInterruptDirection = i2;
    }

    public void setSlipAmplitude(String str) {
        try {
            Log.d(TAG, "setSlipAmplitude slipAmplitude = " + str);
            this.mSpringAnimationCommon.D(Float.parseFloat(str));
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
        }
    }

    public void setStiffness(String str) {
        try {
            Log.d(TAG, "setStiffness stiffness = " + str);
            this.mSpringAnimationCommon.F(Float.parseFloat(str));
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
        }
    }

    public void setUseSpring(boolean z) {
        boolean z2 = z && this.mIsDispalyMotion;
        this.mIsUseSpring = z2;
        SpringAnimationCommon springAnimationCommon = this.mSpringAnimationCommon;
        if (springAnimationCommon != null) {
            springAnimationCommon.B(z2);
        }
        Log.d(TAG, "setUseSpring mIsUseSpring = " + this.mIsUseSpring);
    }

    public GridView(@NonNull Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    @Override // android.widget.AdapterView
    public void setAdapter(ListAdapter listAdapter) {
        super.setAdapter(listAdapter);
        OverScrollDecoratorHelper overScrollDecoratorHelper = new OverScrollDecoratorHelper(this);
        this.mOverScrollDecoratorHelper = overScrollDecoratorHelper;
        this.iOverScrollDecor = overScrollDecoratorHelper.b();
    }

    public GridView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        boolean z = false;
        this.mTotalyDy = 0;
        this.mIsUseSpring = true;
        this.mIsDispalyMotion = true;
        this.iOverScrollDecor = null;
        this.mInterruptDirection = 1;
        Log.d(TAG, "GridView in, context = " + context);
        SpringAnimationCommon springAnimationCommon = new SpringAnimationCommon();
        this.mSpringAnimationCommon = springAnimationCommon;
        springAnimationCommon.s(this, DynamicAnimation.f3652o, 0.0f);
        this.mSpringAnimationCommon.j(getContext());
        boolean booleanValue = Util.d(context).booleanValue();
        this.mIsDispalyMotion = booleanValue;
        if (this.mIsUseSpring && booleanValue) {
            z = true;
        }
        this.mIsUseSpring = z;
        this.mSpringAnimationCommon.B(z);
        this.mGestureDetector = new GestureDetector(getContext(), new TouchGesture());
        Log.d(TAG, "GridView out. mIsDispalyMotion = " + this.mIsDispalyMotion + ", mIsUseSpring = " + this.mIsUseSpring);
    }
}
