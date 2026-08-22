package com.google.android.material.color.utilities;

import androidx.annotation.RestrictTo;
import java.util.HashMap;
import java.util.Map;

@RestrictTo
/* loaded from: classes.dex */
public final class TonalPalette {

    /* renamed from: a, reason: collision with root package name */
    Map f14380a = new HashMap();

    /* renamed from: b, reason: collision with root package name */
    Hct f14381b;

    /* renamed from: c, reason: collision with root package name */
    double f14382c;

    /* renamed from: d, reason: collision with root package name */
    double f14383d;

    private TonalPalette(double d2, double d3, Hct hct) {
        this.f14382c = d2;
        this.f14383d = d3;
        this.f14381b = hct;
    }

    private static Hct a(double d2, double d3) {
        Hct a2 = Hct.a(d2, d3, 50.0d);
        Hct hct = a2;
        double abs = Math.abs(a2.c() - d3);
        for (double d4 = 1.0d; d4 < 50.0d && Math.round(d3) != Math.round(hct.c()); d4 += 1.0d) {
            Hct a3 = Hct.a(d2, d3, 50.0d + d4);
            double abs2 = Math.abs(a3.c() - d3);
            if (abs2 < abs) {
                hct = a3;
                abs = abs2;
            }
            Hct a4 = Hct.a(d2, d3, 50.0d - d4);
            double abs3 = Math.abs(a4.c() - d3);
            if (abs3 < abs) {
                hct = a4;
                abs = abs3;
            }
        }
        return hct;
    }

    public static TonalPalette b(Hct hct) {
        return new TonalPalette(hct.d(), hct.c(), hct);
    }

    public static TonalPalette c(double d2, double d3) {
        return new TonalPalette(d2, d3, a(d2, d3));
    }

    public double d() {
        return this.f14383d;
    }

    public Hct e(double d2) {
        return Hct.a(this.f14382c, this.f14383d, d2);
    }

    public double f() {
        return this.f14382c;
    }
}
