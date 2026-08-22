package com.google.android.material.internal;

import android.animation.TimeInterpolator;
import androidx.annotation.RestrictTo;

@RestrictTo
/* loaded from: classes.dex */
public class ReversableAnimatedValueInterpolator implements TimeInterpolator {

    /* renamed from: a, reason: collision with root package name */
    private final TimeInterpolator f14755a;

    public ReversableAnimatedValueInterpolator(TimeInterpolator timeInterpolator) {
        this.f14755a = timeInterpolator;
    }

    public static TimeInterpolator a(boolean z, TimeInterpolator timeInterpolator) {
        return z ? timeInterpolator : new ReversableAnimatedValueInterpolator(timeInterpolator);
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f2) {
        return 1.0f - this.f14755a.getInterpolation(f2);
    }
}
