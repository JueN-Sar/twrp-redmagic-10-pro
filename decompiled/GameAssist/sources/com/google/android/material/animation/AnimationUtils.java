package com.google.android.material.animation;

import android.animation.TimeInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import androidx.annotation.RestrictTo;
import androidx.interpolator.view.animation.FastOutLinearInInterpolator;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;

@RestrictTo
/* loaded from: classes.dex */
public class AnimationUtils {

    /* renamed from: a, reason: collision with root package name */
    public static final TimeInterpolator f13814a = new LinearInterpolator();

    /* renamed from: b, reason: collision with root package name */
    public static final TimeInterpolator f13815b = new FastOutSlowInInterpolator();

    /* renamed from: c, reason: collision with root package name */
    public static final TimeInterpolator f13816c = new FastOutLinearInInterpolator();

    /* renamed from: d, reason: collision with root package name */
    public static final TimeInterpolator f13817d = new LinearOutSlowInInterpolator();

    /* renamed from: e, reason: collision with root package name */
    public static final TimeInterpolator f13818e = new DecelerateInterpolator();

    public static float a(float f2, float f3, float f4) {
        return f2 + (f4 * (f3 - f2));
    }

    public static float b(float f2, float f3, float f4, float f5, float f6) {
        return f6 <= f4 ? f2 : f6 >= f5 ? f3 : a(f2, f3, (f6 - f4) / (f5 - f4));
    }

    public static int c(int i2, int i3, float f2) {
        return i2 + Math.round(f2 * (i3 - i2));
    }
}
