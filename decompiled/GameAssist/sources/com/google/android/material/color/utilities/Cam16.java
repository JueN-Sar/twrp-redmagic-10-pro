package com.google.android.material.color.utilities;

import androidx.annotation.RestrictTo;

@RestrictTo
/* loaded from: classes.dex */
public final class Cam16 {

    /* renamed from: k, reason: collision with root package name */
    static final double[][] f14294k = {new double[]{0.401288d, 0.650173d, -0.051461d}, new double[]{-0.250268d, 1.204414d, 0.045854d}, new double[]{-0.002079d, 0.048952d, 0.953127d}};

    /* renamed from: l, reason: collision with root package name */
    static final double[][] f14295l = {new double[]{1.8620678d, -1.0112547d, 0.14918678d}, new double[]{0.38752654d, 0.62144744d, -0.00897398d}, new double[]{-0.0158415d, -0.03412294d, 1.0499644d}};

    /* renamed from: a, reason: collision with root package name */
    private final double f14296a;

    /* renamed from: b, reason: collision with root package name */
    private final double f14297b;

    /* renamed from: c, reason: collision with root package name */
    private final double f14298c;

    /* renamed from: d, reason: collision with root package name */
    private final double f14299d;

    /* renamed from: e, reason: collision with root package name */
    private final double f14300e;

    /* renamed from: f, reason: collision with root package name */
    private final double f14301f;

    /* renamed from: g, reason: collision with root package name */
    private final double f14302g;

    /* renamed from: h, reason: collision with root package name */
    private final double f14303h;

    /* renamed from: i, reason: collision with root package name */
    private final double f14304i;

    /* renamed from: j, reason: collision with root package name */
    private final double[] f14305j = {0.0d, 0.0d, 0.0d};

    private Cam16(double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9, double d10) {
        this.f14296a = d2;
        this.f14297b = d3;
        this.f14298c = d4;
        this.f14299d = d5;
        this.f14300e = d6;
        this.f14301f = d7;
        this.f14302g = d8;
        this.f14303h = d9;
        this.f14304i = d10;
    }

    public static Cam16 a(int i2) {
        return b(i2, ViewingConditions.f14391k);
    }

    static Cam16 b(int i2, ViewingConditions viewingConditions) {
        double j2 = ColorUtils.j((16711680 & i2) >> 16);
        double j3 = ColorUtils.j((65280 & i2) >> 8);
        double j4 = ColorUtils.j(i2 & 255);
        return c((0.41233895d * j2) + (0.35762064d * j3) + (0.18051042d * j4), (0.2126d * j2) + (0.7152d * j3) + (0.0722d * j4), (j2 * 0.01932141d) + (j3 * 0.11916382d) + (j4 * 0.95034478d), viewingConditions);
    }

    static Cam16 c(double d2, double d3, double d4, ViewingConditions viewingConditions) {
        double[][] dArr = f14294k;
        double[] dArr2 = dArr[0];
        double d5 = (dArr2[0] * d2) + (dArr2[1] * d3) + (dArr2[2] * d4);
        double[] dArr3 = dArr[1];
        double d6 = (dArr3[0] * d2) + (dArr3[1] * d3) + (dArr3[2] * d4);
        double[] dArr4 = dArr[2];
        double d7 = (dArr4[0] * d2) + (dArr4[1] * d3) + (dArr4[2] * d4);
        double d8 = viewingConditions.j()[0] * d5;
        double d9 = viewingConditions.j()[1] * d6;
        double d10 = viewingConditions.j()[2] * d7;
        double pow = Math.pow((viewingConditions.d() * Math.abs(d8)) / 100.0d, 0.42d);
        double pow2 = Math.pow((viewingConditions.d() * Math.abs(d9)) / 100.0d, 0.42d);
        double pow3 = Math.pow((viewingConditions.d() * Math.abs(d10)) / 100.0d, 0.42d);
        double signum = ((Math.signum(d8) * 400.0d) * pow) / (pow + 27.13d);
        double signum2 = ((Math.signum(d9) * 400.0d) * pow2) / (pow2 + 27.13d);
        double signum3 = ((Math.signum(d10) * 400.0d) * pow3) / (pow3 + 27.13d);
        double d11 = (((signum * 11.0d) + ((-12.0d) * signum2)) + signum3) / 11.0d;
        double d12 = ((signum + signum2) - (signum3 * 2.0d)) / 9.0d;
        double d13 = signum2 * 20.0d;
        double d14 = (((signum * 20.0d) + d13) + (21.0d * signum3)) / 20.0d;
        double d15 = (((signum * 40.0d) + d13) + signum3) / 20.0d;
        double degrees = Math.toDegrees(Math.atan2(d12, d11));
        if (degrees < 0.0d) {
            degrees += 360.0d;
        } else if (degrees >= 360.0d) {
            degrees -= 360.0d;
        }
        double d16 = degrees;
        double radians = Math.toRadians(d16);
        double pow4 = Math.pow((d15 * viewingConditions.g()) / viewingConditions.b(), viewingConditions.c() * viewingConditions.k()) * 100.0d;
        double d17 = pow4 / 100.0d;
        double c2 = (4.0d / viewingConditions.c()) * Math.sqrt(d17) * (viewingConditions.b() + 4.0d) * viewingConditions.e();
        double pow5 = Math.pow(1.64d - Math.pow(0.29d, viewingConditions.f()), 0.73d) * Math.pow(((((((Math.cos(Math.toRadians(d16 < 20.14d ? d16 + 360.0d : d16) + 2.0d) + 3.8d) * 0.25d) * 3846.153846153846d) * viewingConditions.h()) * viewingConditions.i()) * Math.hypot(d11, d12)) / (d14 + 0.305d), 0.9d);
        double sqrt = Math.sqrt(d17) * pow5;
        double e2 = sqrt * viewingConditions.e();
        double log1p = Math.log1p(e2 * 0.0228d) * 43.859649122807014d;
        return new Cam16(d16, sqrt, pow4, c2, e2, Math.sqrt((pow5 * viewingConditions.c()) / (viewingConditions.b() + 4.0d)) * 50.0d, (1.7000000000000002d * pow4) / ((0.007d * pow4) + 1.0d), log1p * Math.cos(radians), log1p * Math.sin(radians));
    }

    public double d() {
        return this.f14297b;
    }

    public double e() {
        return this.f14296a;
    }

    public double f() {
        return this.f14298c;
    }

    double[] g(ViewingConditions viewingConditions, double[] dArr) {
        double pow = Math.pow(((d() == 0.0d || f() == 0.0d) ? 0.0d : d() / Math.sqrt(f() / 100.0d)) / Math.pow(1.64d - Math.pow(0.29d, viewingConditions.f()), 0.73d), 1.1111111111111112d);
        double radians = Math.toRadians(e());
        double cos = (Math.cos(2.0d + radians) + 3.8d) * 0.25d;
        double b2 = viewingConditions.b() * Math.pow(f() / 100.0d, (1.0d / viewingConditions.c()) / viewingConditions.k());
        double h2 = cos * 3846.153846153846d * viewingConditions.h() * viewingConditions.i();
        double g2 = b2 / viewingConditions.g();
        double sin = Math.sin(radians);
        double cos2 = Math.cos(radians);
        double d2 = (((0.305d + g2) * 23.0d) * pow) / (((h2 * 23.0d) + ((11.0d * pow) * cos2)) + ((pow * 108.0d) * sin));
        double d3 = cos2 * d2;
        double d4 = d2 * sin;
        double d5 = g2 * 460.0d;
        double d6 = (((451.0d * d3) + d5) + (288.0d * d4)) / 1403.0d;
        double d7 = ((d5 - (891.0d * d3)) - (261.0d * d4)) / 1403.0d;
        double d8 = ((d5 - (d3 * 220.0d)) - (d4 * 6300.0d)) / 1403.0d;
        double signum = Math.signum(d6) * (100.0d / viewingConditions.d()) * Math.pow(Math.max(0.0d, (Math.abs(d6) * 27.13d) / (400.0d - Math.abs(d6))), 2.380952380952381d);
        double signum2 = Math.signum(d7) * (100.0d / viewingConditions.d()) * Math.pow(Math.max(0.0d, (Math.abs(d7) * 27.13d) / (400.0d - Math.abs(d7))), 2.380952380952381d);
        double signum3 = Math.signum(d8) * (100.0d / viewingConditions.d()) * Math.pow(Math.max(0.0d, (Math.abs(d8) * 27.13d) / (400.0d - Math.abs(d8))), 2.380952380952381d);
        double d9 = signum / viewingConditions.j()[0];
        double d10 = signum2 / viewingConditions.j()[1];
        double d11 = signum3 / viewingConditions.j()[2];
        double[][] dArr2 = f14295l;
        double[] dArr3 = dArr2[0];
        double d12 = (dArr3[0] * d9) + (dArr3[1] * d10) + (dArr3[2] * d11);
        double[] dArr4 = dArr2[1];
        double d13 = (dArr4[0] * d9) + (dArr4[1] * d10) + (dArr4[2] * d11);
        double[] dArr5 = dArr2[2];
        double d14 = (d9 * dArr5[0]) + (d10 * dArr5[1]) + (d11 * dArr5[2]);
        if (dArr == null) {
            return new double[]{d12, d13, d14};
        }
        dArr[0] = d12;
        dArr[1] = d13;
        dArr[2] = d14;
        return dArr;
    }
}
