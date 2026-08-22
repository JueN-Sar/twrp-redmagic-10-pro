package com.google.android.material.color.utilities;

import androidx.annotation.RestrictTo;

@RestrictTo
/* loaded from: classes.dex */
public final class ContrastCurve {

    /* renamed from: a, reason: collision with root package name */
    private final double f14309a;

    /* renamed from: b, reason: collision with root package name */
    private final double f14310b;

    /* renamed from: c, reason: collision with root package name */
    private final double f14311c;

    /* renamed from: d, reason: collision with root package name */
    private final double f14312d;

    public ContrastCurve(double d2, double d3, double d4, double d5) {
        this.f14309a = d2;
        this.f14310b = d3;
        this.f14311c = d4;
        this.f14312d = d5;
    }

    public double a(double d2) {
        return d2 <= -1.0d ? this.f14309a : d2 < 0.0d ? MathUtils.c(this.f14309a, this.f14310b, (d2 - (-1.0d)) / 1.0d) : d2 < 0.5d ? MathUtils.c(this.f14310b, this.f14311c, (d2 - 0.0d) / 0.5d) : d2 < 1.0d ? MathUtils.c(this.f14311c, this.f14312d, (d2 - 0.5d) / 0.5d) : this.f14312d;
    }
}
