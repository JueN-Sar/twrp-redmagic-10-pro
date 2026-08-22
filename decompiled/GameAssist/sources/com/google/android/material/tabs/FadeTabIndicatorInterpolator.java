package com.google.android.material.tabs;

import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.google.android.material.animation.AnimationUtils;

/* loaded from: classes.dex */
class FadeTabIndicatorInterpolator extends TabIndicatorInterpolator {
    FadeTabIndicatorInterpolator() {
    }

    @Override // com.google.android.material.tabs.TabIndicatorInterpolator
    void d(TabLayout tabLayout, View view, View view2, float f2, Drawable drawable) {
        if (f2 >= 0.5f) {
            view = view2;
        }
        RectF a2 = TabIndicatorInterpolator.a(tabLayout, view);
        float b2 = f2 < 0.5f ? AnimationUtils.b(1.0f, 0.0f, 0.0f, 0.5f, f2) : AnimationUtils.b(0.0f, 1.0f, 0.5f, 1.0f, f2);
        drawable.setBounds((int) a2.left, drawable.getBounds().top, (int) a2.right, drawable.getBounds().bottom);
        drawable.setAlpha((int) (b2 * 255.0f));
    }
}
