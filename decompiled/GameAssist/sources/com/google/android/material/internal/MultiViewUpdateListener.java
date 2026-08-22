package com.google.android.material.internal;

import android.animation.ValueAnimator;
import android.view.View;
import androidx.annotation.RestrictTo;

@RestrictTo
/* loaded from: classes.dex */
public class MultiViewUpdateListener implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: c, reason: collision with root package name */
    private final Listener f14725c;

    /* renamed from: h, reason: collision with root package name */
    private final View[] f14726h;

    interface Listener {
        void a(ValueAnimator valueAnimator, View view);
    }

    public MultiViewUpdateListener(Listener listener, View... viewArr) {
        this.f14725c = listener;
        this.f14726h = viewArr;
    }

    public static MultiViewUpdateListener e(View... viewArr) {
        return new MultiViewUpdateListener(new Listener() { // from class: com.google.android.material.internal.d
            @Override // com.google.android.material.internal.MultiViewUpdateListener.Listener
            public final void a(ValueAnimator valueAnimator, View view) {
                MultiViewUpdateListener.g(valueAnimator, view);
            }
        }, viewArr);
    }

    public static MultiViewUpdateListener f(View... viewArr) {
        return new MultiViewUpdateListener(new Listener() { // from class: com.google.android.material.internal.c
            @Override // com.google.android.material.internal.MultiViewUpdateListener.Listener
            public final void a(ValueAnimator valueAnimator, View view) {
                MultiViewUpdateListener.h(valueAnimator, view);
            }
        }, viewArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void g(ValueAnimator valueAnimator, View view) {
        view.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void h(ValueAnimator valueAnimator, View view) {
        Float f2 = (Float) valueAnimator.getAnimatedValue();
        view.setScaleX(f2.floatValue());
        view.setScaleY(f2.floatValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void i(ValueAnimator valueAnimator, View view) {
        view.setTranslationX(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void j(ValueAnimator valueAnimator, View view) {
        view.setTranslationY(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }

    public static MultiViewUpdateListener k(View... viewArr) {
        return new MultiViewUpdateListener(new Listener() { // from class: com.google.android.material.internal.a
            @Override // com.google.android.material.internal.MultiViewUpdateListener.Listener
            public final void a(ValueAnimator valueAnimator, View view) {
                MultiViewUpdateListener.i(valueAnimator, view);
            }
        }, viewArr);
    }

    public static MultiViewUpdateListener l(View... viewArr) {
        return new MultiViewUpdateListener(new Listener() { // from class: com.google.android.material.internal.b
            @Override // com.google.android.material.internal.MultiViewUpdateListener.Listener
            public final void a(ValueAnimator valueAnimator, View view) {
                MultiViewUpdateListener.j(valueAnimator, view);
            }
        }, viewArr);
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        for (View view : this.f14726h) {
            this.f14725c.a(valueAnimator, view);
        }
    }
}
