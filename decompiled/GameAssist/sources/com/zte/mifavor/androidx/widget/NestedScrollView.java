package com.zte.mifavor.androidx.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.dynamicanimation.animation.DynamicAnimation;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.AppBarLayoutSpringBehavior;
import com.zte.extres.R;
import com.zte.mifavor.utils.SpringAnimationCommon;
import com.zte.mifavor.utils.overscroll.IOverScrollDecor;
import com.zte.mifavor.utils.overscroll.OverScrollDecoratorHelper;
import com.zte.mifavor.widget.Util;

/* loaded from: classes2.dex */
public class NestedScrollView extends androidx.core.widget.NestedScrollView {
    public static final boolean DEBUG = false;
    private static final String TAG = "Z#View-SpringNSV";

    @Nullable
    private IOverScrollDecor iOverScrollDecor;
    private boolean isNeedDoFling;
    private boolean isScrolledToBottom;
    private boolean isScrolledToTop;
    private boolean isUpAction;
    private int mInterruptDirection;
    private boolean mIsDispalyMotion;
    private boolean mIsUseSpring;

    @NonNull
    private OverScrollDecoratorHelper mOverScrollDecoratorHelper;

    @NonNull
    private SpringAnimationCommon mSpringAnimationCommon;
    private int mTotalyDy;

    public NestedScrollView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private boolean getAnimIsRunning() {
        boolean z;
        AppBarLayoutSpringBehavior appBarLayoutSpringBehavior;
        View rootView = getRootView();
        if (rootView != null) {
            View findViewById = rootView.findViewById(R.id.base_sink_app_bar_layout);
            if ((findViewById instanceof AppBarLayout) && (appBarLayoutSpringBehavior = (AppBarLayoutSpringBehavior) ((CoordinatorLayout.LayoutParams) ((AppBarLayout) findViewById).getLayoutParams()).f()) != null) {
                z = appBarLayoutSpringBehavior.S0();
                Log.w(TAG, "get Anim Is Running = " + z);
                return z;
            }
        }
        z = false;
        Log.w(TAG, "get Anim Is Running = " + z);
        return z;
    }

    private boolean getIsFirstDownScroll() {
        AppBarLayoutSpringBehavior appBarLayoutSpringBehavior;
        View rootView = getRootView();
        if (rootView != null) {
            View findViewById = rootView.findViewById(R.id.base_sink_app_bar_layout);
            if ((findViewById instanceof AppBarLayout) && (appBarLayoutSpringBehavior = (AppBarLayoutSpringBehavior) ((CoordinatorLayout.LayoutParams) ((AppBarLayout) findViewById).getLayoutParams()).f()) != null) {
                return appBarLayoutSpringBehavior.V0();
            }
        }
        return false;
    }

    private void setApplayoutSpringDistance(int i2) {
        AppBarLayoutSpringBehavior appBarLayoutSpringBehavior;
        View rootView = getRootView();
        if (rootView != null) {
            View findViewById = rootView.findViewById(R.id.base_sink_app_bar_layout);
            Log.e(TAG, "set App layout Spring Distance in, distance=" + i2 + ", appBarLayout=" + findViewById + ", view=" + rootView);
            if (!(findViewById instanceof AppBarLayout) || (appBarLayoutSpringBehavior = (AppBarLayoutSpringBehavior) ((CoordinatorLayout.LayoutParams) ((AppBarLayout) findViewById).getLayoutParams()).f()) == null) {
                return;
            }
            appBarLayoutSpringBehavior.R0(i2);
        }
    }

    public boolean a0() {
        if (!c0()) {
            return true;
        }
        SpringAnimationCommon springAnimationCommon = this.mSpringAnimationCommon;
        if (springAnimationCommon != null) {
            return springAnimationCommon.t();
        }
        return false;
    }

    public boolean b0() {
        SpringAnimationCommon springAnimationCommon = this.mSpringAnimationCommon;
        if (springAnimationCommon != null) {
            return springAnimationCommon.v();
        }
        return false;
    }

    public boolean c0() {
        SpringAnimationCommon springAnimationCommon = this.mSpringAnimationCommon;
        if (springAnimationCommon != null) {
            return springAnimationCommon.w();
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.mSpringAnimationCommon == null) {
            return super.dispatchTouchEvent(motionEvent);
        }
        int action = motionEvent.getAction();
        getTranslationY();
        if (action == 0) {
            if (this.mSpringAnimationCommon.p().f()) {
                this.mSpringAnimationCommon.p().t();
                this.mSpringAnimationCommon.p().b();
            }
            this.mSpringAnimationCommon.f();
            this.mTotalyDy = 0;
        }
        return super.dispatchTouchEvent(motionEvent);
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

    @Override // androidx.core.widget.NestedScrollView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        boolean onInterceptTouchEvent = super.onInterceptTouchEvent(motionEvent);
        if (action == 0) {
            this.isNeedDoFling = a0();
            this.isUpAction = false;
        }
        return onInterceptTouchEvent;
    }

    @Override // androidx.core.widget.NestedScrollView, android.view.View
    protected void onOverScrolled(int i2, int i3, boolean z, boolean z2) {
        super.onOverScrolled(i2, i3, z, z2);
        if (i3 == 0) {
            this.isScrolledToTop = z2;
            this.isScrolledToBottom = false;
        } else {
            this.isScrolledToTop = false;
            this.isScrolledToBottom = z2;
        }
        int k2 = this.mSpringAnimationCommon.k();
        boolean animIsRunning = getAnimIsRunning();
        boolean isFirstDownScroll = getIsFirstDownScroll();
        int i4 = this.mTotalyDy - (i3 / 20);
        this.mTotalyDy = i4;
        this.mSpringAnimationCommon.C(i4);
        View childAt = getChildAt(0);
        boolean z3 = this.isScrolledToBottom;
        if (!z3 && this.isScrolledToTop && this.isNeedDoFling && this.isUpAction && k2 == 3 && !isFirstDownScroll) {
            int l2 = (int) this.mSpringAnimationCommon.l(childAt, 1004);
            if (l2 <= 33.0f || animIsRunning) {
                return;
            }
            setApplayoutSpringDistance(l2);
            return;
        }
        if (((!z3 || this.isScrolledToTop) && (z3 || !this.isScrolledToTop)) || k2 != 2 || childAt == null || animIsRunning) {
            return;
        }
        this.mSpringAnimationCommon.x(childAt, 1003);
    }

    @Override // androidx.core.widget.NestedScrollView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        boolean onTouchEvent = super.onTouchEvent(motionEvent);
        if (1 != action && 3 != action) {
            return onTouchEvent;
        }
        this.isUpAction = true;
        return onTouchEvent;
    }

    public void setDampingRatio(String str) {
        try {
            this.mSpringAnimationCommon.y(Float.parseFloat(str));
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
        }
    }

    public void setDragAmplitude(String str) {
        try {
            this.mSpringAnimationCommon.z(Integer.valueOf(str).intValue());
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
        }
    }

    public void setDuration(String str) {
        try {
            this.mSpringAnimationCommon.A(Integer.parseInt(str));
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
        }
    }

    public void setInterruptSlideDirection(int i2) {
        this.mInterruptDirection = i2;
    }

    public void setSlipAmplitude(String str) {
        try {
            this.mSpringAnimationCommon.D(Float.parseFloat(str));
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
        }
    }

    public void setStiffness(String str) {
        try {
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
    }

    @Override // androidx.core.widget.NestedScrollView
    public void v(int i2) {
        SpringAnimationCommon springAnimationCommon;
        canScrollVertically(-1);
        canScrollVertically(1);
        if (getIsBeingDragged()) {
            return;
        }
        if (this.mIsUseSpring && (springAnimationCommon = this.mSpringAnimationCommon) != null) {
            springAnimationCommon.h(i2);
        }
        super.v(i2);
    }

    public NestedScrollView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        boolean z = false;
        this.mTotalyDy = 0;
        this.mIsUseSpring = true;
        this.isScrolledToTop = true;
        this.isScrolledToBottom = false;
        this.mIsDispalyMotion = true;
        this.iOverScrollDecor = null;
        this.mInterruptDirection = 1;
        this.isNeedDoFling = false;
        this.isUpAction = false;
        Log.d(TAG, "NestedScrollView in, context = " + context);
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
        OverScrollDecoratorHelper overScrollDecoratorHelper = new OverScrollDecoratorHelper(this);
        this.mOverScrollDecoratorHelper = overScrollDecoratorHelper;
        this.iOverScrollDecor = overScrollDecoratorHelper.b();
    }
}
