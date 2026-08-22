package com.google.android.material.tabs;

import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.google.android.material.animation.AnimationUtils;

/* loaded from: classes.dex */
class ElasticTabIndicatorInterpolator extends TabIndicatorInterpolator {
    ElasticTabIndicatorInterpolator() {
    }

    private static float e(float f2) {
        return (float) (1.0d - Math.cos((f2 * 3.141592653589793d) / 2.0d));
    }

    private static float f(float f2) {
        return (float) Math.sin((f2 * 3.141592653589793d) / 2.0d);
    }

    @Override // com.google.android.material.tabs.TabIndicatorInterpolator
    void d(TabLayout tabLayout, View view, View view2, float f2, Drawable drawable) {
        float f3;
        float e2;
        RectF a2 = TabIndicatorInterpolator.a(tabLayout, view);
        RectF a3 = TabIndicatorInterpolator.a(tabLayout, view2);
        if (a2.left < a3.left) {
            f3 = e(f2);
            e2 = f(f2);
        } else {
            f3 = f(f2);
            e2 = e(f2);
        }
        drawable.setBounds(AnimationUtils.c((int) a2.left, (int) a3.left, f3), drawable.getBounds().top, AnimationUtils.c((int) a2.right, (int) a3.right, e2), drawable.getBounds().bottom);
    }
}
