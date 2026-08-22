package com.zte.mifavor.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.PathInterpolator;
import androidx.dynamicanimation.animation.DynamicAnimation;

/* loaded from: classes2.dex */
public class ClickScaleAnimHelperListener implements View.OnTouchListener {

    /* renamed from: c, reason: collision with root package name */
    private Context f17407c;

    /* renamed from: h, reason: collision with root package name */
    private View f17408h;

    /* renamed from: i, reason: collision with root package name */
    private int f17409i;

    /* renamed from: j, reason: collision with root package name */
    private boolean f17410j;

    /* renamed from: k, reason: collision with root package name */
    private float f17411k;

    /* renamed from: l, reason: collision with root package name */
    private float f17412l;

    /* renamed from: m, reason: collision with root package name */
    private float f17413m;

    /* renamed from: n, reason: collision with root package name */
    private ValueAnimator f17414n;

    /* renamed from: o, reason: collision with root package name */
    private ValueAnimator f17415o;

    /* renamed from: p, reason: collision with root package name */
    private PathInterpolator f17416p;

    /* renamed from: q, reason: collision with root package name */
    private Boolean f17417q;

    /* renamed from: com.zte.mifavor.utils.ClickScaleAnimHelperListener$5, reason: invalid class name */
    class AnonymousClass5 implements DynamicAnimation.OnAnimationUpdateListener {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ ClickScaleAnimHelperListener f17420c;

        @Override // androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationUpdateListener
        public void a(DynamicAnimation dynamicAnimation, float f2, float f3) {
            Log.d("ClickScaleAnimHelperLis", this.f17420c.f17409i + ", SmallToBig onAnimationUpdate value=" + f2);
            this.f17420c.f17408h.setScaleY(f2);
        }
    }

    private void f(boolean z) {
        if (!z) {
            if (this.f17414n != null && this.f17417q.booleanValue()) {
                this.f17414n.cancel();
                this.f17414n = null;
                i();
                ValueAnimator valueAnimator = this.f17415o;
                if (valueAnimator != null) {
                    valueAnimator.start();
                }
            }
            this.f17417q = Boolean.FALSE;
            return;
        }
        ValueAnimator valueAnimator2 = this.f17414n;
        if (valueAnimator2 == null || !valueAnimator2.isRunning()) {
            ValueAnimator valueAnimator3 = this.f17415o;
            if (valueAnimator3 == null || !valueAnimator3.isRunning()) {
                this.f17417q = Boolean.TRUE;
                h();
                ValueAnimator valueAnimator4 = this.f17414n;
                if (valueAnimator4 != null) {
                    valueAnimator4.start();
                }
                ValueAnimator valueAnimator5 = this.f17415o;
                if (valueAnimator5 != null) {
                    valueAnimator5.cancel();
                    this.f17415o = null;
                }
            }
        }
    }

    private static float g(View view) {
        return view.getScaleX();
    }

    private void h() {
        int measuredWidth = this.f17408h.getMeasuredWidth();
        int measuredHeight = this.f17408h.getMeasuredHeight();
        if (!this.f17410j || measuredWidth == 0 || measuredHeight == 0) {
            this.f17412l = this.f17413m;
        } else {
            int f2 = Utils.f(this.f17407c, measuredWidth);
            int f3 = Utils.f(this.f17407c, measuredHeight);
            this.f17412l = Math.min(Math.max(((float) ((Math.sqrt((f2 * f2) + (f3 * f3)) / 44.0d) + 87.88d)) / 100.0f, 0.9f), 0.97f);
        }
        this.f17411k = g(this.f17408h);
        if (this.f17414n == null) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            this.f17414n = ofFloat;
            ofFloat.setInterpolator(this.f17416p);
            this.f17414n.setDuration(200L);
            this.f17414n.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.zte.mifavor.utils.ClickScaleAnimHelperListener.1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    ClickScaleAnimHelperListener.j(ClickScaleAnimHelperListener.this.f17408h, (1.0f - ((1.0f - ClickScaleAnimHelperListener.this.f17412l) * ((Float) valueAnimator.getAnimatedValue()).floatValue())) * ClickScaleAnimHelperListener.this.f17411k);
                }
            });
            this.f17414n.addListener(new AnimatorListenerAdapter(this) { // from class: com.zte.mifavor.utils.ClickScaleAnimHelperListener.2
                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator, boolean z) {
                    super.onAnimationEnd(animator, z);
                }
            });
        }
    }

    private void i() {
        if (this.f17415o == null) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            this.f17415o = ofFloat;
            ofFloat.setInterpolator(this.f17416p);
            this.f17415o.setDuration(400L);
            this.f17412l = g(this.f17408h);
            this.f17415o.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.zte.mifavor.utils.ClickScaleAnimHelperListener.3
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    ClickScaleAnimHelperListener.j(ClickScaleAnimHelperListener.this.f17408h, (ClickScaleAnimHelperListener.this.f17412l + ((1.0f - ClickScaleAnimHelperListener.this.f17412l) * ((Float) valueAnimator.getAnimatedValue()).floatValue())) * ClickScaleAnimHelperListener.this.f17411k);
                }
            });
            this.f17415o.addListener(new AnimatorListenerAdapter(this) { // from class: com.zte.mifavor.utils.ClickScaleAnimHelperListener.4
                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator, boolean z) {
                    super.onAnimationEnd(animator, z);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void j(View view, float f2) {
        if (view != null) {
            view.setScaleX(f2);
            view.setScaleY(f2);
        }
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            f(true);
        } else if (action == 1 || action == 3) {
            f(false);
        }
        return false;
    }
}
