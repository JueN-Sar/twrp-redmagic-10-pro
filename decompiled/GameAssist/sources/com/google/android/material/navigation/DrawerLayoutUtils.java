package com.google.android.material.navigation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.view.View;
import androidx.annotation.RestrictTo;
import androidx.core.graphics.ColorUtils;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.animation.AnimationUtils;

@RestrictTo
/* loaded from: classes.dex */
public class DrawerLayoutUtils {

    /* renamed from: a, reason: collision with root package name */
    private static final int f14838a = Color.alpha(-1728053248);

    public static Animator.AnimatorListener b(final DrawerLayout drawerLayout, final View view) {
        return new AnimatorListenerAdapter() { // from class: com.google.android.material.navigation.DrawerLayoutUtils.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                DrawerLayout.this.g(view, false);
                DrawerLayout.this.setScrimColor(-1728053248);
            }
        };
    }

    public static ValueAnimator.AnimatorUpdateListener c(final DrawerLayout drawerLayout) {
        return new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.navigation.a
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                DrawerLayoutUtils.d(DrawerLayout.this, valueAnimator);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void d(DrawerLayout drawerLayout, ValueAnimator valueAnimator) {
        drawerLayout.setScrimColor(ColorUtils.k(-1728053248, AnimationUtils.c(f14838a, 0, valueAnimator.getAnimatedFraction())));
    }
}
