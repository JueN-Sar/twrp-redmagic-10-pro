package cn.nubia.multisubscreen.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

/* loaded from: classes.dex */
public class ZoomButton extends Button {
    private final int DOWN_SCALE_ANIM_DURATION;
    private final float MAX_SCALE_VALUE;
    private final float MIN_SCALE_VALUE;
    private final int UP_SCALE_ANIM_DURATION;
    ValueAnimator mPressAnimator;
    private float mScale;

    public ZoomButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mScale = 1.0f;
        this.MIN_SCALE_VALUE = 0.9f;
        this.MAX_SCALE_VALUE = 1.0f;
        this.DOWN_SCALE_ANIM_DURATION = 200;
        this.UP_SCALE_ANIM_DURATION = 150;
        setFocusable(true);
        setSingleLine();
        setEllipsize(TextUtils.TruncateAt.MARQUEE);
        setMarqueeRepeatLimit(-1);
    }

    private void b(final float f2, int i2) {
        ValueAnimator valueAnimator = this.mPressAnimator;
        if (valueAnimator != null) {
            valueAnimator.end();
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(this.mScale, f2);
        this.mPressAnimator = ofFloat;
        ofFloat.setDuration(i2);
        this.mPressAnimator.start();
        this.mPressAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: cn.nubia.multisubscreen.view.ZoomButton.2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                ZoomButton.this.setScale(((Float) valueAnimator2.getAnimatedValue()).floatValue());
            }
        });
        this.mPressAnimator.addListener(new AnimatorListenerAdapter() { // from class: cn.nubia.multisubscreen.view.ZoomButton.3
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                super.onAnimationCancel(animator);
                ZoomButton.this.setScale(1.0f);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                ZoomButton.this.setScale(f2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setScale(float f2) {
        this.mScale = f2;
        setScaleX(f2);
        setScaleY(f2);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        new Handler().postDelayed(new Runnable() { // from class: cn.nubia.multisubscreen.view.ZoomButton.1
            @Override // java.lang.Runnable
            public void run() {
                ZoomButton.this.onWindowFocusChanged(true);
            }
        }, 500L);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onFocusChanged(boolean z, int i2, Rect rect) {
        super.onFocusChanged(z, i2, rect);
        if (z) {
            b(1.1f, 200);
        } else {
            b(1.0f, 150);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isClickable() || !isEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            b(0.9f, 200);
        } else if (action == 1 || action == 3) {
            b(1.0f, 150);
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public void setAlpha(float f2) {
        super.setAlpha(f2);
        if (0.0f == f2) {
            setClickable(false);
        } else {
            setClickable(true);
        }
    }
}
