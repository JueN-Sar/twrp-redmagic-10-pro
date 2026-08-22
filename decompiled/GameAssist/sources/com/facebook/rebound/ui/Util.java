package com.facebook.rebound.ui;

import android.content.res.Resources;
import android.util.TypedValue;
import android.widget.FrameLayout;

/* loaded from: classes.dex */
public abstract class Util {
    public static final FrameLayout.LayoutParams a(int i2, int i3) {
        return new FrameLayout.LayoutParams(i2, i3);
    }

    public static final FrameLayout.LayoutParams b() {
        return a(-1, -1);
    }

    public static final FrameLayout.LayoutParams c() {
        return a(-1, -2);
    }

    public static final int d(float f2, Resources resources) {
        return (int) TypedValue.applyDimension(1, f2, resources.getDisplayMetrics());
    }
}
