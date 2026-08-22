package com.google.android.material.color.utilities;

import androidx.annotation.RestrictTo;

@RestrictTo
/* loaded from: classes.dex */
public final class ViewingConditions {

    /* renamed from: k, reason: collision with root package name */
    public static final ViewingConditions f14391k = a(50.0d);

    /* renamed from: a, reason: collision with root package name */
    private final double f14392a;

    /* renamed from: b, reason: collision with root package name */
    private final double f14393b;

    /* renamed from: c, reason: collision with root package name */
    private final double f14394c;

    /* renamed from: d, reason: collision with root package name */
    private final double f14395d;

    /* renamed from: e, reason: collision with root package name */
    private final double f14396e;

    /* renamed from: f, reason: collision with root package name */
    private final double f14397f;

    /* renamed from: g, reason: collision with root package name */
    private final double[] f14398g;

    /* renamed from: h, reason: collision with root package name */
    private final double f14399h;

    /* renamed from: i, reason: collision with root package name */
    private final double f14400i;

    /* renamed from: j, reason: collision with root package name */
    private final double f14401j;

    private ViewingConditions(double d2, double d3, double d4, double d5, double d6, double d7, double[] dArr, double d8, double d9, double d10) {
        this.f14397f = d2;
        this.f14392a = d3;
        this.f14393b = d4;
        this.f14394c = d5;
        this.f14395d = d6;
        this.f14396e = d7;
        this.f14398g = dArr;
        this.f14399h = d8;
        this.f14400i = d9;
        this.f14401j = d10;
    }

    public static ViewingConditions a(double d2) {
        return l(ColorUtils.n(), (ColorUtils.p(50.0d) * 63.66197723675813d) / 100.0d, d2, 2.0d, false);
    }

    public static ViewingConditions l(double[] dArr, double d2, double d3, double d4, boolean z) {
        double max = Math.max(0.1d, d3);
        double[][] dArr2 = Cam16.f14294k;
        double d5 = dArr[0];
        double[] dArr3 = dArr2[0];
        double d6 = dArr3[0] * d5;
        double d7 = dArr[1];
        double d8 = d6 + (dArr3[1] * d7);
        double d9 = dArr[2];
        double d10 = d8 + (dArr3[2] * d9);
        double[] dArr4 = dArr2[1];
        double d11 = (dArr4[0] * d5) + (dArr4[1] * d7) + (dArr4[2] * d9);
        double[] dArr5 = dArr2[2];
        double d12 = (d5 * dArr5[0]) + (d7 * dArr5[1]) + (d9 * dArr5[2]);
        double d13 = (d4 / 10.0d) + 0.8d;
        double c2 = d13 >= 0.9d ? MathUtils.c(0.59d, 0.69d, (d13 - 0.9d) * 10.0d) : MathUtils.c(0.525d, 0.59d, (d13 - 0.8d) * 10.0d);
        double a2 = MathUtils.a(0.0d, 1.0d, z ? 1.0d : (1.0d - (Math.exp(((-d2) - 42.0d) / 92.0d) * 0.2777777777777778d)) * d13);
        double[] dArr6 = {(((100.0d / d10) * a2) + 1.0d) - a2, (((100.0d / d11) * a2) + 1.0d) - a2, (((100.0d / d12) * a2) + 1.0d) - a2};
        double d14 = 5.0d * d2;
        double d15 = 1.0d / (d14 + 1.0d);
        double d16 = d15 * d15 * d15 * d15;
        double d17 = 1.0d - d16;
        double cbrt = (d16 * d2) + (0.1d * d17 * d17 * Math.cbrt(d14));
        double p2 = ColorUtils.p(max) / dArr[1];
        double sqrt = Math.sqrt(p2) + 1.48d;
        double pow = 0.725d / Math.pow(p2, 0.2d);
        double[] dArr7 = {Math.pow(((dArr6[0] * cbrt) * d10) / 100.0d, 0.42d), Math.pow(((dArr6[1] * cbrt) * d11) / 100.0d, 0.42d), Math.pow(((dArr6[2] * cbrt) * d12) / 100.0d, 0.42d)};
        double d18 = dArr7[0];
        double d19 = (d18 * 400.0d) / (d18 + 27.13d);
        double d20 = dArr7[1];
        double d21 = (d20 * 400.0d) / (d20 + 27.13d);
        double d22 = dArr7[2];
        double[] dArr8 = {d19, d21, (400.0d * d22) / (d22 + 27.13d)};
        return new ViewingConditions(p2, ((dArr8[0] * 2.0d) + dArr8[1] + (dArr8[2] * 0.05d)) * pow, pow, pow, c2, d13, dArr6, cbrt, Math.pow(cbrt, 0.25d), sqrt);
    }

    public double b() {
        return this.f14392a;
    }

    double c() {
        return this.f14395d;
    }

    double d() {
        return this.f14399h;
    }

    public double e() {
        return this.f14400i;
    }

    public double f() {
        return this.f14397f;
    }

    public double g() {
        return this.f14393b;
    }

    double h() {
        return this.f14396e;
    }

    double i() {
        return this.f14394c;
    }

    public double[] j() {
        return this.f14398g;
    }

    double k() {
        return this.f14401j;
    }
}
