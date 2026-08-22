package com.google.android.material.color.utilities;

import androidx.annotation.RestrictTo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;

@RestrictTo
/* loaded from: classes.dex */
public final class DynamicColor {

    /* renamed from: a, reason: collision with root package name */
    public final String f14314a;

    /* renamed from: b, reason: collision with root package name */
    public final Function f14315b;

    /* renamed from: c, reason: collision with root package name */
    public final Function f14316c;

    /* renamed from: d, reason: collision with root package name */
    public final boolean f14317d;

    /* renamed from: e, reason: collision with root package name */
    public final Function f14318e;

    /* renamed from: f, reason: collision with root package name */
    public final Function f14319f;

    /* renamed from: g, reason: collision with root package name */
    public final ContrastCurve f14320g;

    /* renamed from: h, reason: collision with root package name */
    public final Function f14321h;

    /* renamed from: i, reason: collision with root package name */
    public final Function f14322i;

    /* renamed from: j, reason: collision with root package name */
    private final HashMap f14323j;

    public DynamicColor(String str, Function function, Function function2, boolean z, Function function3, Function function4, ContrastCurve contrastCurve, Function function5) {
        this.f14323j = new HashMap();
        this.f14314a = str;
        this.f14315b = function;
        this.f14316c = function2;
        this.f14317d = z;
        this.f14318e = function3;
        this.f14319f = function4;
        this.f14320g = contrastCurve;
        this.f14321h = function5;
        this.f14322i = null;
    }

    public static double a(double d2) {
        if (!h(d2) || g(d2)) {
            return d2;
        }
        return 49.0d;
    }

    public static double b(double d2, double d3) {
        double d4 = Contrast.d(d2, d3);
        double b2 = Contrast.b(d2, d3);
        double e2 = Contrast.e(d4, d2);
        double e3 = Contrast.e(b2, d2);
        if (h(d2)) {
            return (e2 >= d3 || e2 >= e3 || ((Math.abs(e2 - e3) > 0.1d ? 1 : (Math.abs(e2 - e3) == 0.1d ? 0 : -1)) < 0 && (e2 > d3 ? 1 : (e2 == d3 ? 0 : -1)) < 0 && (e3 > d3 ? 1 : (e3 == d3 ? 0 : -1)) < 0)) ? d4 : b2;
        }
        return (e3 >= d3 || e3 >= e2) ? b2 : d4;
    }

    public static DynamicColor c(String str, Function function, Function function2) {
        return new DynamicColor(str, function, function2, false, null, null, null, null);
    }

    public static boolean g(double d2) {
        return Math.round(d2) <= 49;
    }

    public static boolean h(double d2) {
        return Math.round(d2) < 60;
    }

    public int d(DynamicScheme dynamicScheme) {
        int h2 = e(dynamicScheme).h();
        Function function = this.f14322i;
        if (function == null) {
            return h2;
        }
        return (MathUtils.b(0, 255, (int) Math.round(((Double) function.apply(dynamicScheme)).doubleValue() * 255.0d)) << 24) | (16777215 & h2);
    }

    public Hct e(DynamicScheme dynamicScheme) {
        Hct hct = (Hct) this.f14323j.get(dynamicScheme);
        if (hct != null) {
            return hct;
        }
        Hct e2 = ((TonalPalette) this.f14315b.apply(dynamicScheme)).e(f(dynamicScheme));
        if (this.f14323j.size() > 4) {
            this.f14323j.clear();
        }
        this.f14323j.put(dynamicScheme, e2);
        return e2;
    }

    public double f(DynamicScheme dynamicScheme) {
        double d2;
        double min;
        boolean z = dynamicScheme.f14328e < 0.0d;
        Function function = this.f14321h;
        if (function == null) {
            double doubleValue = ((Double) this.f14316c.apply(dynamicScheme)).doubleValue();
            Function function2 = this.f14318e;
            if (function2 == null) {
                return doubleValue;
            }
            double f2 = ((DynamicColor) function2.apply(dynamicScheme)).f(dynamicScheme);
            double a2 = this.f14320g.a(dynamicScheme.f14328e);
            if (Contrast.e(f2, doubleValue) < a2) {
                doubleValue = b(f2, a2);
            }
            if (z) {
                doubleValue = b(f2, a2);
            }
            double d3 = (!this.f14317d || 50.0d > doubleValue || doubleValue >= 60.0d) ? doubleValue : Contrast.e(49.0d, f2) >= a2 ? 49.0d : 60.0d;
            if (this.f14319f == null) {
                return d3;
            }
            double f3 = ((DynamicColor) this.f14318e.apply(dynamicScheme)).f(dynamicScheme);
            double f4 = ((DynamicColor) this.f14319f.apply(dynamicScheme)).f(dynamicScheme);
            double max = Math.max(f3, f4);
            double min2 = Math.min(f3, f4);
            if (Contrast.e(max, d3) >= a2 && Contrast.e(min2, d3) >= a2) {
                return d3;
            }
            double c2 = Contrast.c(max, a2);
            double a3 = Contrast.a(min2, a2);
            ArrayList arrayList = new ArrayList();
            if (c2 != -1.0d) {
                arrayList.add(Double.valueOf(c2));
            }
            if (a3 != -1.0d) {
                arrayList.add(Double.valueOf(a3));
            }
            if (h(f3) || h(f4)) {
                if (c2 == -1.0d) {
                    return 100.0d;
                }
                return c2;
            }
            if (arrayList.size() == 1) {
                return ((Double) arrayList.get(0)).doubleValue();
            }
            if (a3 == -1.0d) {
                return 0.0d;
            }
            return a3;
        }
        ToneDeltaPair toneDeltaPair = (ToneDeltaPair) function.apply(dynamicScheme);
        DynamicColor c3 = toneDeltaPair.c();
        DynamicColor d4 = toneDeltaPair.d();
        double a4 = toneDeltaPair.a();
        TonePolarity b2 = toneDeltaPair.b();
        boolean e2 = toneDeltaPair.e();
        double f5 = ((DynamicColor) this.f14318e.apply(dynamicScheme)).f(dynamicScheme);
        boolean z2 = b2 == TonePolarity.NEARER || (b2 == TonePolarity.LIGHTER && !dynamicScheme.f14327d) || (b2 == TonePolarity.DARKER && dynamicScheme.f14327d);
        DynamicColor dynamicColor = z2 ? c3 : d4;
        DynamicColor dynamicColor2 = z2 ? d4 : c3;
        boolean equals = this.f14314a.equals(dynamicColor.f14314a);
        double d5 = dynamicScheme.f14327d ? 1.0d : -1.0d;
        double a5 = dynamicColor.f14320g.a(dynamicScheme.f14328e);
        double a6 = dynamicColor2.f14320g.a(dynamicScheme.f14328e);
        double doubleValue2 = ((Double) dynamicColor.f14316c.apply(dynamicScheme)).doubleValue();
        if (Contrast.e(f5, doubleValue2) < a5) {
            doubleValue2 = b(f5, a5);
        }
        double doubleValue3 = ((Double) dynamicColor2.f14316c.apply(dynamicScheme)).doubleValue();
        if (Contrast.e(f5, doubleValue3) < a6) {
            doubleValue3 = b(f5, a6);
        }
        if (z) {
            doubleValue2 = b(f5, a5);
            doubleValue3 = b(f5, a6);
        }
        if ((doubleValue3 - doubleValue2) * d5 < a4) {
            double d6 = a4 * d5;
            doubleValue3 = MathUtils.a(0.0d, 100.0d, doubleValue2 + d6);
            if ((doubleValue3 - doubleValue2) * d5 < a4) {
                doubleValue2 = MathUtils.a(0.0d, 100.0d, doubleValue3 - d6);
            }
        }
        if (50.0d > doubleValue2 || doubleValue2 >= 60.0d) {
            if (50.0d > doubleValue3 || doubleValue3 >= 60.0d) {
                d2 = doubleValue3;
            } else if (!e2) {
                d2 = d5 > 0.0d ? 60.0d : 49.0d;
            } else if (d5 > 0.0d) {
                d2 = Math.max(doubleValue3, (a4 * d5) + 60.0d);
                doubleValue2 = 60.0d;
            } else {
                min = Math.min(doubleValue3, (a4 * d5) + 49.0d);
                d2 = min;
                doubleValue2 = 49.0d;
            }
        } else if (d5 > 0.0d) {
            d2 = Math.max(doubleValue3, (a4 * d5) + 60.0d);
            doubleValue2 = 60.0d;
        } else {
            min = Math.min(doubleValue3, (a4 * d5) + 49.0d);
            d2 = min;
            doubleValue2 = 49.0d;
        }
        return equals ? doubleValue2 : d2;
    }

    public DynamicColor(String str, Function function, Function function2, boolean z, Function function3, Function function4, ContrastCurve contrastCurve, Function function5, Function function6) {
        this.f14323j = new HashMap();
        this.f14314a = str;
        this.f14315b = function;
        this.f14316c = function2;
        this.f14317d = z;
        this.f14318e = function3;
        this.f14319f = function4;
        this.f14320g = contrastCurve;
        this.f14321h = function5;
        this.f14322i = function6;
    }
}
