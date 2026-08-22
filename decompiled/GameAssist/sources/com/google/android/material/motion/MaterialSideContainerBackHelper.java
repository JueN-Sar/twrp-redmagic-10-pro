package com.google.android.material.motion;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.BackEventCompat;
import androidx.annotation.GravityInt;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;

@RestrictTo
/* loaded from: classes.dex */
public class MaterialSideContainerBackHelper extends MaterialBackAnimationHelper<View> {

    /* renamed from: g, reason: collision with root package name */
    private final float f14830g;

    /* renamed from: h, reason: collision with root package name */
    private final float f14831h;

    /* renamed from: i, reason: collision with root package name */
    private final float f14832i;

    public MaterialSideContainerBackHelper(View view) {
        super(view);
        Resources resources = view.getResources();
        this.f14830g = resources.getDimension(R.dimen.m3_back_progress_side_container_max_scale_x_distance_shrink);
        this.f14831h = resources.getDimension(R.dimen.m3_back_progress_side_container_max_scale_x_distance_grow);
        this.f14832i = resources.getDimension(R.dimen.m3_back_progress_side_container_max_scale_y_distance);
    }

    private boolean g(int i2, int i3) {
        return (GravityCompat.b(i2, ViewCompat.v(this.f14808b)) & i3) == i3;
    }

    private int i(boolean z) {
        ViewGroup.LayoutParams layoutParams = this.f14808b.getLayoutParams();
        if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            return 0;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        return z ? marginLayoutParams.leftMargin : marginLayoutParams.rightMargin;
    }

    public void f() {
        if (super.b() == null) {
            return;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ObjectAnimator.ofFloat(this.f14808b, (Property<View, Float>) View.SCALE_X, 1.0f), ObjectAnimator.ofFloat(this.f14808b, (Property<View, Float>) View.SCALE_Y, 1.0f));
        View view = this.f14808b;
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                animatorSet.playTogether(ObjectAnimator.ofFloat(viewGroup.getChildAt(i2), (Property<View, Float>) View.SCALE_Y, 1.0f));
            }
        }
        animatorSet.setDuration(this.f14811e);
        animatorSet.start();
    }

    public void h(BackEventCompat backEventCompat, final int i2, Animator.AnimatorListener animatorListener, ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        final boolean z = backEventCompat.b() == 0;
        boolean g2 = g(i2, 3);
        float width = (this.f14808b.getWidth() * this.f14808b.getScaleX()) + i(g2);
        View view = this.f14808b;
        Property property = View.TRANSLATION_X;
        if (g2) {
            width = -width;
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, (Property<View, Float>) property, width);
        if (animatorUpdateListener != null) {
            ofFloat.addUpdateListener(animatorUpdateListener);
        }
        ofFloat.setInterpolator(new FastOutSlowInInterpolator());
        ofFloat.setDuration(AnimationUtils.c(this.f14809c, this.f14810d, backEventCompat.a()));
        ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.motion.MaterialSideContainerBackHelper.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                MaterialSideContainerBackHelper.this.f14808b.setTranslationX(0.0f);
                MaterialSideContainerBackHelper.this.updateBackProgress(0.0f, z, i2);
            }
        });
        if (animatorListener != null) {
            ofFloat.addListener(animatorListener);
        }
        ofFloat.start();
    }

    public void j(BackEventCompat backEventCompat) {
        super.d(backEventCompat);
    }

    public void k(BackEventCompat backEventCompat, int i2) {
        if (super.e(backEventCompat) == null) {
            return;
        }
        updateBackProgress(backEventCompat.a(), backEventCompat.b() == 0, i2);
    }

    @VisibleForTesting
    public void updateBackProgress(float f2, boolean z, @GravityInt int i2) {
        float a2 = a(f2);
        boolean g2 = g(i2, 3);
        boolean z2 = z == g2;
        int width = this.f14808b.getWidth();
        int height = this.f14808b.getHeight();
        float f3 = width;
        if (f3 > 0.0f) {
            float f4 = height;
            if (f4 <= 0.0f) {
                return;
            }
            float f5 = this.f14830g / f3;
            float f6 = this.f14831h / f3;
            float f7 = this.f14832i / f4;
            View view = this.f14808b;
            if (g2) {
                f3 = 0.0f;
            }
            view.setPivotX(f3);
            if (!z2) {
                f6 = -f5;
            }
            float a3 = AnimationUtils.a(0.0f, f6, a2);
            float f8 = a3 + 1.0f;
            this.f14808b.setScaleX(f8);
            float a4 = 1.0f - AnimationUtils.a(0.0f, f7, a2);
            this.f14808b.setScaleY(a4);
            View view2 = this.f14808b;
            if (view2 instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view2;
                for (int i3 = 0; i3 < viewGroup.getChildCount(); i3++) {
                    View childAt = viewGroup.getChildAt(i3);
                    childAt.setPivotX(g2 ? (width - childAt.getRight()) + childAt.getWidth() : -childAt.getLeft());
                    childAt.setPivotY(-childAt.getTop());
                    float f9 = z2 ? 1.0f - a3 : 1.0f;
                    float f10 = a4 != 0.0f ? (f8 / a4) * f9 : 1.0f;
                    childAt.setScaleX(f9);
                    childAt.setScaleY(f10);
                }
            }
        }
    }
}
