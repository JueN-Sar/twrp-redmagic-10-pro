package com.zte.mifavor.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.dynamicanimation.animation.DynamicAnimation;
import com.zte.mifavor.utils.SpringAnimationCommon;
import com.zte.mifavor.utils.overscroll.IOverScrollDecor;
import com.zte.mifavor.utils.overscroll.OverScrollDecoratorHelper;

/* loaded from: classes2.dex */
public class ScrollView extends android.widget.ScrollView {
    private static final String TAG = "Z#View-SpringSV";

    @Nullable
    private IOverScrollDecor iOverScrollDecor;
    private boolean isScrolledToBottom;
    private boolean isScrolledToTop;
    private int mInterruptDirection;
    private boolean mIsDispalyMotion;
    private boolean mIsUseSpring;

    @NonNull
    private OverScrollDecoratorHelper mOverScrollDecoratorHelper;

    @NonNull
    private SpringAnimationCommon mSpringAnimationCommon;
    private int mTotalyDy;

    public ScrollView(@NonNull Context context) {
        this(context, null);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.mSpringAnimationCommon == null) {
            Log.w(TAG, "====================== dispatchTouchEvent error. mSpringAnimationCommon is null. ");
            return super.dispatchTouchEvent(motionEvent);
        }
        float translationY = getTranslationY();
        if (motionEvent.getAction() == 0) {
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

    @Override // android.widget.ScrollView
    public void fling(int i2) {
        SpringAnimationCommon springAnimationCommon;
        Log.d(TAG, "fling+++++++++++++++++++++, velocityY = " + i2 + ", canScrollUp = " + canScrollVertically(-1) + ", canScrollDown = " + canScrollVertically(1) + ", IsBeingDragged = " + getIsBeingDragged());
        if (getIsBeingDragged()) {
            Log.w(TAG, "fling+++++++++++++++++++++, ignore fling, velocityY = " + i2);
            return;
        }
        if (this.mIsUseSpring && (springAnimationCommon = this.mSpringAnimationCommon) != null) {
            springAnimationCommon.h(i2);
        }
        super.fling(i2);
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

    public boolean isCollapsed() {
        if (!isSupportSink()) {
            Log.d(TAG, "isCollapsed don't support sink. return true.");
            return true;
        }
        SpringAnimationCommon springAnimationCommon = this.mSpringAnimationCommon;
        boolean t = springAnimationCommon != null ? springAnimationCommon.t() : false;
        Log.d(TAG, "isCollapsed out. isCollapsed = " + t);
        return t;
    }

    public boolean isDisableSink() {
        SpringAnimationCommon springAnimationCommon = this.mSpringAnimationCommon;
        boolean v = springAnimationCommon != null ? springAnimationCommon.v() : false;
        Log.d(TAG, "isDisableSink out. isSupport = " + v);
        return v;
    }

    public boolean isExpanded() {
        if (!isSupportSink()) {
            Log.d(TAG, "isExpanded don't support sink. return true.");
            return true;
        }
        SpringAnimationCommon springAnimationCommon = this.mSpringAnimationCommon;
        boolean u = springAnimationCommon != null ? springAnimationCommon.u() : false;
        Log.d(TAG, "isExpanded out. isExpanded = " + u);
        return u;
    }

    public boolean isSupportSink() {
        SpringAnimationCommon springAnimationCommon = this.mSpringAnimationCommon;
        boolean w = springAnimationCommon != null ? springAnimationCommon.w() : false;
        Log.d(TAG, "isSupprtSink out. isSupport = " + w);
        return w;
    }

    @Override // android.widget.ScrollView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        boolean onInterceptTouchEvent = super.onInterceptTouchEvent(motionEvent);
        Log.d(TAG, "++++++++onInterceptTouchEvent out, return ret=" + onInterceptTouchEvent + ",action=" + action);
        return onInterceptTouchEvent;
    }

    @Override // android.widget.ScrollView, android.view.View
    protected void onOverScrolled(int i2, int i3, boolean z, boolean z2) {
        super.onOverScrolled(i2, i3, z, z2);
        if (i3 == 0) {
            this.isScrolledToTop = z2;
            this.isScrolledToBottom = false;
        } else {
            this.isScrolledToTop = false;
            this.isScrolledToBottom = z2;
        }
        int i4 = i3 / 20;
        Log.d(TAG, "onOverScrolled ++ scrollX=" + i2 + ", scrollY=" + i3 + ", dy=" + i4 + ", clampedX=" + z + ", clampedY=" + z2 + ", isScrolledToTop=" + this.isScrolledToTop + ", isScrolledToBottom=" + this.isScrolledToBottom);
        int i5 = this.mTotalyDy - i4;
        this.mTotalyDy = i5;
        this.mSpringAnimationCommon.C(i5);
        if ((this.isScrolledToBottom || this.isScrolledToTop) && this.mSpringAnimationCommon.t()) {
            View childAt = getChildAt(0);
            if (childAt != null) {
                this.mSpringAnimationCommon.x(childAt, 1003);
            } else {
                Log.w(TAG, "onOverScrolled============= childView is null.");
            }
        }
    }

    @Override // android.widget.ScrollView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        try {
            int pointerCount = motionEvent.getPointerCount();
            if (pointerCount > 1) {
                Log.w(TAG, "++++++++onTouchEvent Pointer Count is " + pointerCount);
                return true;
            }
            int action = motionEvent.getAction();
            boolean onTouchEvent = super.onTouchEvent(motionEvent);
            Log.d(TAG, "++++++++onTouchEvent out, return ret=" + onTouchEvent + ",action=" + action);
            return onTouchEvent;
        } catch (Exception e2) {
            Log.d(TAG, "onTouchEvent error, e = ", e2);
            return true;
        }
    }

    public void restoreTouchListener() {
        if (this.mOverScrollDecoratorHelper == null) {
            this.mOverScrollDecoratorHelper = new OverScrollDecoratorHelper(this);
        }
        OverScrollDecoratorHelper overScrollDecoratorHelper = this.mOverScrollDecoratorHelper;
        if (overScrollDecoratorHelper != null) {
            this.iOverScrollDecor = overScrollDecoratorHelper.b();
        }
        Log.d(TAG, "restore Touch Listener = " + this.iOverScrollDecor);
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

    public ScrollView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        boolean z = false;
        this.mTotalyDy = 0;
        this.mIsUseSpring = true;
        this.isScrolledToTop = true;
        this.isScrolledToBottom = false;
        this.mIsDispalyMotion = true;
        this.iOverScrollDecor = null;
        this.mInterruptDirection = 1;
        Log.d(TAG, "ScrollView in, context = " + context);
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
        Log.d(TAG, "ScrollView out. mIsDispalyMotion = " + this.mIsDispalyMotion + ", mIsUseSpring = " + this.mIsUseSpring);
    }
}
