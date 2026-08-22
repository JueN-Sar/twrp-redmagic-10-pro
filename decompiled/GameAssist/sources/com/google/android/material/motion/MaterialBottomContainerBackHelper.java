package com.google.android.material.motion;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.BackEventCompat;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;

@RestrictTo
/* loaded from: classes.dex */
public class MaterialBottomContainerBackHelper extends MaterialBackAnimationHelper<View> {

    /* renamed from: g, reason: collision with root package name */
    private final float f14819g;

    /* renamed from: h, reason: collision with root package name */
    private final float f14820h;

    public MaterialBottomContainerBackHelper(View view) {
        super(view);
        Resources resources = view.getResources();
        this.f14819g = resources.getDimension(R.dimen.m3_back_progress_bottom_container_max_scale_x_distance);
        this.f14820h = resources.getDimension(R.dimen.m3_back_progress_bottom_container_max_scale_y_distance);
    }

    private Animator g() {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ObjectAnimator.ofFloat(this.f14808b, (Property<View, Float>) View.SCALE_X, 1.0f), ObjectAnimator.ofFloat(this.f14808b, (Property<View, Float>) View.SCALE_Y, 1.0f));
        View view = this.f14808b;
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                animatorSet.playTogether(ObjectAnimator.ofFloat(viewGroup.getChildAt(i2), (Property<View, Float>) View.SCALE_Y, 1.0f));
            }
        }
        animatorSet.setInterpolator(new FastOutSlowInInterpolator());
        return animatorSet;
    }

    public void f() {
        if (super.b() == null) {
            return;
        }
        Animator g2 = g();
        g2.setDuration(this.f14811e);
        g2.start();
    }

    public void h(BackEventCompat backEventCompat, Animator.AnimatorListener animatorListener) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.f14808b, (Property<View, Float>) View.TRANSLATION_Y, this.f14808b.getHeight() * this.f14808b.getScaleY());
        ofFloat.setInterpolator(new FastOutSlowInInterpolator());
        ofFloat.setDuration(AnimationUtils.c(this.f14809c, this.f14810d, backEventCompat.a()));
        ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.motion.MaterialBottomContainerBackHelper.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                MaterialBottomContainerBackHelper.this.f14808b.setTranslationY(0.0f);
                MaterialBottomContainerBackHelper.this.updateBackProgress(0.0f);
            }
        });
        if (animatorListener != null) {
            ofFloat.addListener(animatorListener);
        }
        ofFloat.start();
    }

    public void i(BackEventCompat backEventCompat, Animator.AnimatorListener animatorListener) {
        Animator g2 = g();
        g2.setDuration(AnimationUtils.c(this.f14809c, this.f14810d, backEventCompat.a()));
        if (animatorListener != null) {
            g2.addListener(animatorListener);
        }
        g2.start();
    }

    public void j(BackEventCompat backEventCompat) {
        super.d(backEventCompat);
    }

    public void k(BackEventCompat backEventCompat) {
        if (super.e(backEventCompat) == null) {
            return;
        }
        updateBackProgress(backEventCompat.a());
    }

    @VisibleForTesting
    public void updateBackProgress(float f2) {
        float a2 = a(f2);
        float width = this.f14808b.getWidth();
        float height = this.f14808b.getHeight();
        if (width <= 0.0f || height <= 0.0f) {
            return;
        }
        float f3 = this.f14819g / width;
        float f4 = this.f14820h / height;
        float a3 = 1.0f - AnimationUtils.a(0.0f, f3, a2);
        float a4 = 1.0f - AnimationUtils.a(0.0f, f4, a2);
        this.f14808b.setScaleX(a3);
        this.f14808b.setPivotY(height);
        this.f14808b.setScaleY(a4);
        View view = this.f14808b;
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                View childAt = viewGroup.getChildAt(i2);
                childAt.setPivotY(-childAt.getTop());
                childAt.setScaleY(a4 != 0.0f ? a3 / a4 : 1.0f);
            }
        }
    }
}
