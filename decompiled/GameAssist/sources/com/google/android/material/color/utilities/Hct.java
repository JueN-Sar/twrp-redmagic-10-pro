package com.google.android.material.color.utilities;

import androidx.annotation.RestrictTo;

@RestrictTo
/* loaded from: classes.dex */
public final class Hct {

    /* renamed from: a, reason: collision with root package name */
    private double f14337a;

    /* renamed from: b, reason: collision with root package name */
    private double f14338b;

    /* renamed from: c, reason: collision with root package name */
    private double f14339c;

    /* renamed from: d, reason: collision with root package name */
    private int f14340d;

    private Hct(int i2) {
        g(i2);
    }

    public static Hct a(double d2, double d3, double d4) {
        return new Hct(HctSolver.q(d2, d3, d4));
    }

    public static Hct b(int i2) {
        return new Hct(i2);
    }

    private void g(int i2) {
        this.f14340d = i2;
        Cam16 a2 = Cam16.a(i2);
        this.f14337a = a2.e();
        this.f14338b = a2.d();
        this.f14339c = ColorUtils.k(i2);
    }

    public double c() {
        return this.f14338b;
    }

    public double d() {
        return this.f14337a;
    }

    public double e() {
        return this.f14339c;
    }

    public Hct f(ViewingConditions viewingConditions) {
        double[] g2 = Cam16.a(h()).g(viewingConditions, null);
        Cam16 c2 = Cam16.c(g2[0], g2[1], g2[2], ViewingConditions.f14391k);
        return a(c2.e(), c2.d(), ColorUtils.l(g2[1]));
    }

    public int h() {
        return this.f14340d;
    }
}
