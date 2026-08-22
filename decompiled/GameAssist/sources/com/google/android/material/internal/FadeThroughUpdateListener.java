package com.google.android.material.internal;

import android.animation.ValueAnimator;
import android.view.View;
import androidx.annotation.RestrictTo;

@RestrictTo
/* loaded from: classes.dex */
public class FadeThroughUpdateListener implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: c, reason: collision with root package name */
    private final View f14722c;

    /* renamed from: h, reason: collision with root package name */
    private final View f14723h;

    /* renamed from: i, reason: collision with root package name */
    private final float[] f14724i = new float[2];

    public FadeThroughUpdateListener(View view, View view2) {
        this.f14722c = view;
        this.f14723h = view2;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        FadeThroughUtils.a(((Float) valueAnimator.getAnimatedValue()).floatValue(), this.f14724i);
        View view = this.f14722c;
        if (view != null) {
            view.setAlpha(this.f14724i[0]);
        }
        View view2 = this.f14723h;
        if (view2 != null) {
            view2.setAlpha(this.f14724i[1]);
        }
    }
}
