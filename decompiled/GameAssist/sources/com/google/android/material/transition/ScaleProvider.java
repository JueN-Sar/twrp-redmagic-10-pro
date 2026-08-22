package com.google.android.material.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes.dex */
public final class ScaleProvider implements VisibilityAnimatorProvider {

    /* renamed from: a, reason: collision with root package name */
    private float f15625a;

    /* renamed from: b, reason: collision with root package name */
    private float f15626b;

    /* renamed from: c, reason: collision with root package name */
    private float f15627c;

    /* renamed from: d, reason: collision with root package name */
    private float f15628d;

    /* renamed from: e, reason: collision with root package name */
    private boolean f15629e;

    /* renamed from: f, reason: collision with root package name */
    private boolean f15630f;

    private static Animator c(final View view, float f2, float f3) {
        final float scaleX = view.getScaleX();
        final float scaleY = view.getScaleY();
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, PropertyValuesHolder.ofFloat((Property<?, Float>) View.SCALE_X, scaleX * f2, scaleX * f3), PropertyValuesHolder.ofFloat((Property<?, Float>) View.SCALE_Y, f2 * scaleY, f3 * scaleY));
        ofPropertyValuesHolder.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.transition.ScaleProvider.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                view.setScaleX(scaleX);
                view.setScaleY(scaleY);
            }
        });
        return ofPropertyValuesHolder;
    }

    @Override // com.google.android.material.transition.VisibilityAnimatorProvider
    public Animator a(ViewGroup viewGroup, View view) {
        if (this.f15630f) {
            return this.f15629e ? c(view, this.f15625a, this.f15626b) : c(view, this.f15628d, this.f15627c);
        }
        return null;
    }

    @Override // com.google.android.material.transition.VisibilityAnimatorProvider
    public Animator b(ViewGroup viewGroup, View view) {
        return this.f15629e ? c(view, this.f15627c, this.f15628d) : c(view, this.f15626b, this.f15625a);
    }
}
