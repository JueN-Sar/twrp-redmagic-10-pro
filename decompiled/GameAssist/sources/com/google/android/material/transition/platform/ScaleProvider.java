package com.google.android.material.transition.platform;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.RequiresApi;

@RequiresApi
/* loaded from: classes.dex */
public final class ScaleProvider implements VisibilityAnimatorProvider {

    /* renamed from: a, reason: collision with root package name */
    private float f15753a;

    /* renamed from: b, reason: collision with root package name */
    private float f15754b;

    /* renamed from: c, reason: collision with root package name */
    private float f15755c;

    /* renamed from: d, reason: collision with root package name */
    private float f15756d;

    /* renamed from: e, reason: collision with root package name */
    private boolean f15757e;

    /* renamed from: f, reason: collision with root package name */
    private boolean f15758f;

    private static Animator c(final View view, float f2, float f3) {
        final float scaleX = view.getScaleX();
        final float scaleY = view.getScaleY();
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, PropertyValuesHolder.ofFloat((Property<?, Float>) View.SCALE_X, scaleX * f2, scaleX * f3), PropertyValuesHolder.ofFloat((Property<?, Float>) View.SCALE_Y, f2 * scaleY, f3 * scaleY));
        ofPropertyValuesHolder.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.transition.platform.ScaleProvider.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                view.setScaleX(scaleX);
                view.setScaleY(scaleY);
            }
        });
        return ofPropertyValuesHolder;
    }

    @Override // com.google.android.material.transition.platform.VisibilityAnimatorProvider
    public Animator a(ViewGroup viewGroup, View view) {
        if (this.f15758f) {
            return this.f15757e ? c(view, this.f15753a, this.f15754b) : c(view, this.f15756d, this.f15755c);
        }
        return null;
    }

    @Override // com.google.android.material.transition.platform.VisibilityAnimatorProvider
    public Animator b(ViewGroup viewGroup, View view) {
        return this.f15757e ? c(view, this.f15755c, this.f15756d) : c(view, this.f15754b, this.f15753a);
    }
}
