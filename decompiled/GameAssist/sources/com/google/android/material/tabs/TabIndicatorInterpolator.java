package com.google.android.material.tabs;

import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.tabs.TabLayout;

/* loaded from: classes.dex */
class TabIndicatorInterpolator {
    TabIndicatorInterpolator() {
    }

    static RectF a(TabLayout tabLayout, View view) {
        return view == null ? new RectF() : (tabLayout.D() || !(view instanceof TabLayout.TabView)) ? new RectF(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()) : b((TabLayout.TabView) view, 24);
    }

    static RectF b(TabLayout.TabView tabView, int i2) {
        int contentWidth = tabView.getContentWidth();
        int contentHeight = tabView.getContentHeight();
        int h2 = (int) ViewUtils.h(tabView.getContext(), i2);
        if (contentWidth < h2) {
            contentWidth = h2;
        }
        int left = (tabView.getLeft() + tabView.getRight()) / 2;
        int top = (tabView.getTop() + tabView.getBottom()) / 2;
        int i3 = contentWidth / 2;
        return new RectF(left - i3, top - (contentHeight / 2), i3 + left, top + (left / 2));
    }

    void c(TabLayout tabLayout, View view, Drawable drawable) {
        RectF a2 = a(tabLayout, view);
        drawable.setBounds((int) a2.left, drawable.getBounds().top, (int) a2.right, drawable.getBounds().bottom);
    }

    void d(TabLayout tabLayout, View view, View view2, float f2, Drawable drawable) {
        RectF a2 = a(tabLayout, view);
        RectF a3 = a(tabLayout, view2);
        drawable.setBounds(AnimationUtils.c((int) a2.left, (int) a3.left, f2), drawable.getBounds().top, AnimationUtils.c((int) a2.right, (int) a3.right, f2), drawable.getBounds().bottom);
    }
}
