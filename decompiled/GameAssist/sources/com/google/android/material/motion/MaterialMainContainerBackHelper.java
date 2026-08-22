package com.google.android.material.motion;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.Property;
import android.view.RoundedCorner;
import android.view.View;
import android.view.WindowInsets;
import androidx.activity.BackEventCompat;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.internal.ClippableRoundedCornerLayout;
import com.google.android.material.internal.ViewUtils;

@RestrictTo
/* loaded from: classes.dex */
public class MaterialMainContainerBackHelper extends MaterialBackAnimationHelper<View> {

    /* renamed from: g, reason: collision with root package name */
    private final float f14822g;

    /* renamed from: h, reason: collision with root package name */
    private final float f14823h;

    /* renamed from: i, reason: collision with root package name */
    private float f14824i;

    /* renamed from: j, reason: collision with root package name */
    private Rect f14825j;

    /* renamed from: k, reason: collision with root package name */
    private Rect f14826k;

    /* renamed from: l, reason: collision with root package name */
    private Integer f14827l;

    public MaterialMainContainerBackHelper(View view) {
        super(view);
        Resources resources = view.getResources();
        this.f14822g = resources.getDimension(R.dimen.m3_back_progress_main_container_min_edge_gap);
        this.f14823h = resources.getDimension(R.dimen.m3_back_progress_main_container_max_translation_y);
    }

    private ValueAnimator h(final ClippableRoundedCornerLayout clippableRoundedCornerLayout) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(clippableRoundedCornerLayout.getCornerRadius(), k());
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.motion.b
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                MaterialMainContainerBackHelper.q(ClippableRoundedCornerLayout.this, valueAnimator);
            }
        });
        return ofFloat;
    }

    private AnimatorSet i(final View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ObjectAnimator.ofFloat(this.f14808b, (Property<View, Float>) View.SCALE_X, 1.0f), ObjectAnimator.ofFloat(this.f14808b, (Property<View, Float>) View.SCALE_Y, 1.0f), ObjectAnimator.ofFloat(this.f14808b, (Property<View, Float>) View.TRANSLATION_X, 0.0f), ObjectAnimator.ofFloat(this.f14808b, (Property<View, Float>) View.TRANSLATION_Y, 0.0f));
        animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.motion.MaterialMainContainerBackHelper.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                View view2 = view;
                if (view2 != null) {
                    view2.setVisibility(0);
                }
            }
        });
        return animatorSet;
    }

    private int n() {
        WindowInsets rootWindowInsets = this.f14808b.getRootWindowInsets();
        if (rootWindowInsets != null) {
            return Math.max(Math.max(o(rootWindowInsets, 0), o(rootWindowInsets, 1)), Math.max(o(rootWindowInsets, 3), o(rootWindowInsets, 2)));
        }
        return 0;
    }

    private int o(WindowInsets windowInsets, int i2) {
        RoundedCorner roundedCorner = windowInsets.getRoundedCorner(i2);
        if (roundedCorner != null) {
            return roundedCorner.getRadius();
        }
        return 0;
    }

    private boolean p() {
        int[] iArr = new int[2];
        this.f14808b.getLocationOnScreen(iArr);
        return iArr[1] == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void q(ClippableRoundedCornerLayout clippableRoundedCornerLayout, ValueAnimator valueAnimator) {
        clippableRoundedCornerLayout.e(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }

    private void r() {
        this.f14824i = 0.0f;
        this.f14825j = null;
        this.f14826k = null;
    }

    public void g(View view) {
        if (super.b() == null) {
            return;
        }
        AnimatorSet i2 = i(view);
        View view2 = this.f14808b;
        if (view2 instanceof ClippableRoundedCornerLayout) {
            i2.playTogether(h((ClippableRoundedCornerLayout) view2));
        }
        i2.setDuration(this.f14811e);
        i2.start();
        r();
    }

    public void j(long j2, View view) {
        AnimatorSet i2 = i(view);
        i2.setDuration(j2);
        i2.start();
        r();
    }

    public int k() {
        if (this.f14827l == null) {
            this.f14827l = Integer.valueOf(p() ? n() : 0);
        }
        return this.f14827l.intValue();
    }

    public Rect l() {
        return this.f14826k;
    }

    public Rect m() {
        return this.f14825j;
    }

    public void s(BackEventCompat backEventCompat, View view) {
        super.d(backEventCompat);
        startBackProgress(backEventCompat.c(), view);
    }

    @VisibleForTesting
    public void startBackProgress(float f2, @Nullable View view) {
        this.f14825j = ViewUtils.d(this.f14808b);
        if (view != null) {
            this.f14826k = ViewUtils.c(this.f14808b, view);
        }
        this.f14824i = f2;
    }

    public void t(BackEventCompat backEventCompat, View view, float f2) {
        if (super.e(backEventCompat) == null) {
            return;
        }
        if (view != null && view.getVisibility() != 4) {
            view.setVisibility(4);
        }
        updateBackProgress(backEventCompat.a(), backEventCompat.b() == 0, backEventCompat.c(), f2);
    }

    @VisibleForTesting
    public void updateBackProgress(float f2, boolean z, float f3, float f4) {
        float a2 = a(f2);
        float width = this.f14808b.getWidth();
        float height = this.f14808b.getHeight();
        if (width <= 0.0f || height <= 0.0f) {
            return;
        }
        float a3 = AnimationUtils.a(1.0f, 0.9f, a2);
        float a4 = AnimationUtils.a(0.0f, Math.max(0.0f, ((width - (0.9f * width)) / 2.0f) - this.f14822g), a2) * (z ? 1 : -1);
        float min = Math.min(Math.max(0.0f, ((height - (a3 * height)) / 2.0f) - this.f14822g), this.f14823h);
        float f5 = f3 - this.f14824i;
        float a5 = AnimationUtils.a(0.0f, min, Math.abs(f5) / height) * Math.signum(f5);
        this.f14808b.setScaleX(a3);
        this.f14808b.setScaleY(a3);
        this.f14808b.setTranslationX(a4);
        this.f14808b.setTranslationY(a5);
        View view = this.f14808b;
        if (view instanceof ClippableRoundedCornerLayout) {
            ((ClippableRoundedCornerLayout) view).e(AnimationUtils.a(k(), f4, a2));
        }
    }
}
