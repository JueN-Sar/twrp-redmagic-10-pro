package com.zte.mifavor.androidx.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.animation.PathInterpolator;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.zte.mifavor.widget.Util;
import com.zte.mifavor.widget.Utils;

/* loaded from: classes2.dex */
public class ViewPager extends androidx.viewpager.widget.ViewPager {
    private static final String TAG = "Z#View-SpringVP";
    private int EDGE_DRAG_MAX_DISTANCE;
    private int mActivePointerId;
    private float mDensity;
    private DisplayMetrics mDm;
    private float mInitialMotionXY;
    private int mInitialTouchX;
    private int mInitialTouchY;
    private final float mInvalidValue;
    private boolean mIsDispalyMotion;
    private boolean mIsUseSpring;
    private int mScrollPointerId;
    private float mStartDragX;
    private int mTouchSlop;
    PathInterpolator pathInterpolator;

    @Nullable
    private ValueAnimator valueSpringAnim;

    public ViewPager(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mInvalidValue = -1.0f;
        this.EDGE_DRAG_MAX_DISTANCE = 150;
        this.mInitialMotionXY = 0.0f;
        this.mStartDragX = 0.0f;
        this.mIsUseSpring = true;
        this.mIsDispalyMotion = true;
        this.valueSpringAnim = null;
        this.pathInterpolator = new PathInterpolator(0.33f, 0.0f, 0.0f, 1.0f);
        Log.d(TAG, "ViewPager in, context = " + context);
        boolean booleanValue = Util.d(context).booleanValue();
        this.mIsDispalyMotion = booleanValue;
        this.mIsUseSpring = this.mIsUseSpring && booleanValue;
        setOverScrollMode(2);
        this.mDm = getResources().getDisplayMetrics();
        this.mDensity = getResources().getDisplayMetrics().density;
        W();
        Log.d(TAG, "ViewPager out. mIsDispalyMotion = " + this.mIsDispalyMotion + ", mIsUseSpring = " + this.mIsUseSpring);
    }

    private void U(MotionEvent motionEvent, int i2) {
        if (this.mStartDragX == 0.0f) {
            this.mStartDragX = motionEvent.getRawX();
        }
        float rawX = (motionEvent.getRawX() - this.mStartDragX) * ((this.EDGE_DRAG_MAX_DISTANCE * this.mDensity) / this.mDm.widthPixels);
        Log.d(TAG, "+++++++++++++++++++++drag To Left Or Right. desX = " + rawX + ", isDragToRight = " + i2 + ", mStartDragX = " + this.mStartDragX);
        if ((i2 <= 0 || rawX <= 0.0f) && ((i2 >= 0 || rawX >= 0.0f) && i2 != 0)) {
            return;
        }
        setTranslationX(rawX);
    }

    private float V(MotionEvent motionEvent) {
        int findPointerIndex = motionEvent.findPointerIndex(this.mActivePointerId);
        if (findPointerIndex < 0) {
            return -1.0f;
        }
        return motionEvent.getX(findPointerIndex);
    }

    private void W() {
        this.mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }

    private boolean X(float f2) {
        return getCurrentItem() == 0 && ((double) (f2 - this.mInitialMotionXY)) > 0.0d;
    }

    private boolean Y(float f2) {
        return getCurrentItem() == getAdapter().e() - 1 && ((double) (this.mInitialMotionXY - f2)) > 0.0d;
    }

    private void z(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.mActivePointerId) {
            this.mActivePointerId = motionEvent.getPointerId(actionIndex == 0 ? 1 : 0);
        }
    }

    public void T() {
        ValueAnimator valueAnimator = this.valueSpringAnim;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.valueSpringAnim.cancel();
        }
        this.valueSpringAnim = null;
        float translationX = getTranslationX();
        if (translationX == 0.0f) {
            Log.w(TAG, "animation Restored. ++++++ don't setStartVelocity and do nothing and clear.");
            return;
        }
        ValueAnimator ofInt = ValueAnimator.ofInt((int) translationX, 0);
        this.valueSpringAnim = ofInt;
        if (ofInt != null) {
            ofInt.setDuration(180L);
            this.valueSpringAnim.setInterpolator(this.pathInterpolator);
            this.valueSpringAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.zte.mifavor.androidx.widget.ViewPager.1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                    int intValue = ((Integer) valueAnimator2.getAnimatedValue()).intValue();
                    Log.i(ViewPager.TAG, "+++++++++++ Update value Spring Anim. value = " + intValue);
                    ViewPager.this.setTranslationX((float) intValue);
                }
            });
            Log.i(TAG, "+++++++++++ Start value Spring Anim. fromX = " + translationX + ", toX = 0.");
            this.valueSpringAnim.start();
        }
        this.mStartDragX = 0.0f;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        boolean dispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
        Log.d(TAG, "+++++++++++++++++++= dispatchTouchEvent out. action = " + action + ", ret = " + dispatchTouchEvent);
        return dispatchTouchEvent;
    }

    public boolean getUseSpring() {
        Log.d(TAG, "getUseSpring mIsUseSpring = " + this.mIsUseSpring);
        return this.mIsUseSpring;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (Utils.w()) {
            return false;
        }
        if (!this.mIsUseSpring) {
            Log.w(TAG, "onInterceptTouchEvent. mIsUseSpring = " + this.mIsUseSpring);
            return false;
        }
        boolean onInterceptTouchEvent = super.onInterceptTouchEvent(motionEvent);
        int action = motionEvent.getAction();
        Log.d(TAG, "++++++++onInterceptTouchEvent in, action=" + action + ", intercept = " + onInterceptTouchEvent);
        int actionIndex = motionEvent.getActionIndex();
        if (action == 0) {
            this.mActivePointerId = motionEvent.getPointerId(0);
            float V = V(motionEvent);
            if (Math.abs(V - (-1.0f)) < 0.001f) {
                return false;
            }
            this.mInitialMotionXY = V;
            Log.d(TAG, "onInterceptTouchEvent. mInitialMotionXY = " + this.mInitialMotionXY);
            this.mScrollPointerId = motionEvent.getPointerId(0);
            this.mInitialTouchX = (int) (motionEvent.getX() + 0.5f);
            this.mInitialTouchY = (int) (motionEvent.getY() + 0.5f);
            return onInterceptTouchEvent;
        }
        if (action != 2) {
            if (action == 5) {
                this.mScrollPointerId = motionEvent.getPointerId(actionIndex);
                this.mInitialTouchX = (int) (motionEvent.getX(actionIndex) + 0.5f);
                this.mInitialTouchY = (int) (motionEvent.getY(actionIndex) + 0.5f);
            }
            Log.d(TAG, "++++++++onInterceptTouchEvent out, intercept = " + onInterceptTouchEvent);
            return onInterceptTouchEvent;
        }
        int findPointerIndex = motionEvent.findPointerIndex(this.mScrollPointerId);
        if (findPointerIndex < 0) {
            Log.e(TAG, "Error processing scroll; pointer index for id " + this.mScrollPointerId + " not found. Did any MotionEvents get skipped?");
            return false;
        }
        int x = (int) (motionEvent.getX(findPointerIndex) + 0.5f);
        int y = (int) (motionEvent.getY(findPointerIndex) + 0.5f);
        int i2 = x - this.mInitialTouchX;
        int i3 = y - this.mInitialTouchY;
        boolean z = (Math.abs(i2) <= this.mTouchSlop || Math.abs(i2) <= Math.abs(i3)) ? Math.abs(i3) > this.mTouchSlop && Math.abs(i3) > Math.abs(i2) : false;
        if (((getParent() instanceof androidx.core.widget.NestedScrollView) || (getParent().getParent() instanceof androidx.core.widget.NestedScrollView)) && z) {
            Log.d(TAG, "++++++++onInterceptTouchEvent out, return true, action=" + action);
            return true;
        }
        Log.d(TAG, "++++++++onInterceptTouchEvent out, action=" + action + ", intercept = " + onInterceptTouchEvent + ", startScroll = " + z);
        return onInterceptTouchEvent;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        boolean onTouchEvent = super.onTouchEvent(motionEvent);
        if (Utils.w()) {
            return onTouchEvent;
        }
        if (!this.mIsUseSpring) {
            Log.w(TAG, "onTouchEvent abort. mIsUseSpring = " + this.mIsUseSpring);
            return onTouchEvent;
        }
        if (action == 0) {
            this.mActivePointerId = motionEvent.getPointerId(0);
            float V = V(motionEvent);
            if (Math.abs(V - (-1.0f)) < 0.001f) {
                Log.w(TAG, "onTouchEvent action down error.");
                return onTouchEvent;
            }
            this.mInitialMotionXY = V;
            Log.d(TAG, "onTouchEvent action down, mInitialMotionXY = " + this.mInitialMotionXY);
        } else if (action == 1) {
            Log.d(TAG, "+++++++++++++++++++ onTouchEvent action up.+++++++++++++++++++++++++++++ action = " + action);
            T();
        } else if (action == 2) {
            float V2 = V(motionEvent);
            boolean X = X(V(motionEvent));
            boolean Y = Y(V(motionEvent));
            Log.d(TAG, "onTouchEvent, action move, isScrollL = " + X + ", isScrollR = " + Y + ", touchXY = " + V2 + ", mInitialMotionXY = " + this.mInitialMotionXY);
            if (Y) {
                float f2 = this.mInitialMotionXY - V2;
                if (f2 > 0.0f) {
                    Log.d(TAG, "onTouchEvent, rightDistance = " + f2 + ", mInitialMotionXY = " + this.mInitialMotionXY);
                    U(motionEvent, -1);
                } else {
                    Log.w(TAG, "onTouchEvent move abort, rightDistance < 0. rightDistance = " + f2);
                }
            } else if (X) {
                float f3 = V2 - this.mInitialMotionXY;
                if (f3 > 0.0f) {
                    Log.d(TAG, "onTouchEvent, leftDistance = " + f3 + ", mInitialMotionXY = " + this.mInitialMotionXY);
                    U(motionEvent, 1);
                } else {
                    Log.w(TAG, "onTouchEvent move abort, topLeftDistance < 0. leftDistance = " + f3);
                }
            } else {
                this.mInitialMotionXY = V2;
                this.mStartDragX = motionEvent.getRawX();
                Log.w(TAG, "onTouchEvent, action move abort, please check isScrollTL and isScrollBR. mInitialMotionXY = " + this.mInitialMotionXY + ", mStartDragX = " + this.mStartDragX);
            }
        } else if (action == 5) {
            this.mActivePointerId = motionEvent.getPointerId(motionEvent.getActionIndex());
        } else if (action == 6) {
            z(motionEvent);
        }
        return onTouchEvent;
    }

    public void setUseSpring(boolean z) {
        this.mIsUseSpring = z && this.mIsDispalyMotion;
        Log.d(TAG, "setUseSpring mIsUseSpring = " + this.mIsUseSpring);
    }
}
