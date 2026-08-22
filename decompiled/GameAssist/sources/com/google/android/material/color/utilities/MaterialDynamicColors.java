package com.google.android.material.color.utilities;

import androidx.annotation.RestrictTo;
import java.util.function.Function;

@RestrictTo
/* loaded from: classes.dex */
public final class MaterialDynamicColors {
    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Double A1(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.f14327d ? 30.0d : 90.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Double A2(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.f14327d ? 80.0d : 40.0d);
    }

    static double A3(Hct hct, DynamicScheme dynamicScheme) {
        Hct f2 = hct.f(V3(dynamicScheme));
        return (!DynamicColor.h(hct.e()) || DynamicColor.g(f2.e())) ? DynamicColor.a(f2.e()) : DynamicColor.a(hct.e());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ToneDeltaPair B1(DynamicScheme dynamicScheme) {
        return new ToneDeltaPair(f1(), e1(), 15.0d, TonePolarity.NEARER, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ToneDeltaPair B2(DynamicScheme dynamicScheme) {
        return new ToneDeltaPair(E3(), D3(), 15.0d, TonePolarity.NEARER, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Double D1(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.f14327d ? 20.0d : 95.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Double D2(DynamicScheme dynamicScheme) {
        double d2 = dynamicScheme.f14327d ? 30.0d : 90.0d;
        if (m1(dynamicScheme)) {
            return Double.valueOf(dynamicScheme.f14327d ? 30.0d : 85.0d);
        }
        if (l1(dynamicScheme)) {
            return Double.valueOf(A3(dynamicScheme.f14330g.e(g1(dynamicScheme.f14330g.f(), dynamicScheme.f14330g.d(), d2, !dynamicScheme.f14327d)), dynamicScheme));
        }
        return Double.valueOf(d2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ DynamicColor E1(DynamicScheme dynamicScheme) {
        return k1();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ToneDeltaPair E2(DynamicScheme dynamicScheme) {
        return new ToneDeltaPair(E3(), D3(), 15.0d, TonePolarity.NEARER, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Double G1(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.f14327d ? 40.0d : 80.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Double G2(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.f14327d ? 6.0d : 98.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ DynamicColor H1(DynamicScheme dynamicScheme) {
        return k1();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Double I2(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.f14327d ? 24.0d : 98.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Double J1(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.f14327d ? 90.0d : 20.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Double K2(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.f14327d ? 12.0d : 94.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Double L1(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.f14327d ? 90.0d : 10.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ DynamicColor M1(DynamicScheme dynamicScheme) {
        return a1();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Double M2(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.f14327d ? 17.0d : 92.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Double O1(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.f14327d ? 20.0d : 100.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Double O2(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.f14327d ? 22.0d : 90.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ DynamicColor P1(DynamicScheme dynamicScheme) {
        return e1();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Double Q2(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.f14327d ? 10.0d : 96.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Double R1(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.f14327d ? 90.0d : 10.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ DynamicColor S1(DynamicScheme dynamicScheme) {
        return f1();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Double S2(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.f14327d ? 4.0d : 100.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Double U1(DynamicScheme dynamicScheme) {
        if (m1(dynamicScheme)) {
            return Double.valueOf(dynamicScheme.f14327d ? 10.0d : 90.0d);
        }
        return Double.valueOf(dynamicScheme.f14327d ? 20.0d : 100.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Double U2(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.f14327d ? 6.0d : 87.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ DynamicColor V1(DynamicScheme dynamicScheme) {
        return B3();
    }

    private static ViewingConditions V3(DynamicScheme dynamicScheme) {
        return ViewingConditions.a(dynamicScheme.f14327d ? 30.0d : 80.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Double W2(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.f14327d ? 30.0d : 90.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Double X1(DynamicScheme dynamicScheme) {
        if (l1(dynamicScheme)) {
            return Double.valueOf(DynamicColor.b(((Double) C3().f14316c.apply(dynamicScheme)).doubleValue(), 4.5d));
        }
        if (m1(dynamicScheme)) {
            return Double.valueOf(dynamicScheme.f14327d ? 0.0d : 100.0d);
        }
        return Double.valueOf(dynamicScheme.f14327d ? 90.0d : 10.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ DynamicColor Y1(DynamicScheme dynamicScheme) {
        return C3();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Double Y2(DynamicScheme dynamicScheme) {
        if (m1(dynamicScheme)) {
            return Double.valueOf(dynamicScheme.f14327d ? 90.0d : 25.0d);
        }
        return Double.valueOf(dynamicScheme.f14327d ? 80.0d : 40.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ToneDeltaPair Z2(DynamicScheme dynamicScheme) {
        return new ToneDeltaPair(P3(), O3(), 15.0d, TonePolarity.NEARER, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Double a2(DynamicScheme dynamicScheme) {
        if (m1(dynamicScheme)) {
            return Double.valueOf(dynamicScheme.f14327d ? 10.0d : 100.0d);
        }
        return Double.valueOf(dynamicScheme.f14327d ? 20.0d : 100.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ DynamicColor b2(DynamicScheme dynamicScheme) {
        return D3();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Double b3(DynamicScheme dynamicScheme) {
        if (m1(dynamicScheme)) {
            return Double.valueOf(dynamicScheme.f14327d ? 60.0d : 49.0d);
        }
        if (l1(dynamicScheme)) {
            return Double.valueOf(DislikeAnalyzer.a(dynamicScheme.f14331h.e(A3(dynamicScheme.f14331h.e(dynamicScheme.f14325b.e()), dynamicScheme))).e());
        }
        return Double.valueOf(dynamicScheme.f14327d ? 30.0d : 90.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ToneDeltaPair c3(DynamicScheme dynamicScheme) {
        return new ToneDeltaPair(P3(), O3(), 15.0d, TonePolarity.NEARER, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Double d2(DynamicScheme dynamicScheme) {
        if (l1(dynamicScheme)) {
            return Double.valueOf(DynamicColor.b(((Double) E3().f14316c.apply(dynamicScheme)).doubleValue(), 4.5d));
        }
        return Double.valueOf(dynamicScheme.f14327d ? 90.0d : 10.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ DynamicColor e2(DynamicScheme dynamicScheme) {
        return E3();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Double e3(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.f14327d ? 10.0d : 90.0d);
    }

    static double g1(double d2, double d3, double d4, boolean z) {
        Hct a2 = Hct.a(d2, d3, d4);
        if (a2.c() >= d3) {
            return d4;
        }
        Hct hct = a2;
        double c2 = a2.c();
        double d5 = d4;
        while (hct.c() < d3) {
            double d6 = d5 + (z ? -1.0d : 1.0d);
            Hct a3 = Hct.a(d2, d3, d6);
            if (c2 > a3.c() || Math.abs(a3.c() - d3) < 0.4d) {
                return d6;
            }
            if (Math.abs(a3.c() - d3) < Math.abs(hct.c() - d3)) {
                hct = a3;
            }
            c2 = Math.max(c2, a3.c());
            d5 = d6;
        }
        return d5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Double g2(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.f14327d ? 90.0d : 10.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Double g3(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.f14327d ? 10.0d : 90.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Double i2(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.f14327d ? 80.0d : 30.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Double i3(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.f14327d ? 10.0d : 90.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Double k2(DynamicScheme dynamicScheme) {
        if (m1(dynamicScheme)) {
            return Double.valueOf(dynamicScheme.f14327d ? 10.0d : 90.0d);
        }
        return Double.valueOf(dynamicScheme.f14327d ? 20.0d : 100.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Double k3(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.f14327d ? 30.0d : 80.0d);
    }

    private static boolean l1(DynamicScheme dynamicScheme) {
        Variant variant = dynamicScheme.f14326c;
        return variant == Variant.FIDELITY || variant == Variant.CONTENT;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ DynamicColor l2(DynamicScheme dynamicScheme) {
        return O3();
    }

    private static boolean m1(DynamicScheme dynamicScheme) {
        return dynamicScheme.f14326c == Variant.MONOCHROME;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Double m3(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.f14327d ? 10.0d : 90.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Double n2(DynamicScheme dynamicScheme) {
        if (m1(dynamicScheme)) {
            return Double.valueOf(dynamicScheme.f14327d ? 0.0d : 100.0d);
        }
        if (l1(dynamicScheme)) {
            return Double.valueOf(DynamicColor.b(((Double) P3().f14316c.apply(dynamicScheme)).doubleValue(), 4.5d));
        }
        return Double.valueOf(dynamicScheme.f14327d ? 90.0d : 10.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Double o1(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.f14327d ? 6.0d : 98.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ DynamicColor o2(DynamicScheme dynamicScheme) {
        return P3();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Double q1(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.f14327d ? 30.0d : 90.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Double q2(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.f14327d ? 60.0d : 50.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Double s1(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.f14327d ? 100.0d : 0.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Double s2(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.f14327d ? 30.0d : 80.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Double t1(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.f14327d ? 0.2d : 0.12d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Double u2(DynamicScheme dynamicScheme) {
        if (m1(dynamicScheme)) {
            return Double.valueOf(dynamicScheme.f14327d ? 100.0d : 0.0d);
        }
        return Double.valueOf(dynamicScheme.f14327d ? 80.0d : 40.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Double v1(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.f14327d ? 80.0d : 30.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ToneDeltaPair v2(DynamicScheme dynamicScheme) {
        return new ToneDeltaPair(C3(), B3(), 15.0d, TonePolarity.NEARER, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Double x1(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.f14327d ? 80.0d : 40.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Double x2(DynamicScheme dynamicScheme) {
        if (l1(dynamicScheme)) {
            return Double.valueOf(A3(dynamicScheme.f14325b, dynamicScheme));
        }
        if (m1(dynamicScheme)) {
            return Double.valueOf(dynamicScheme.f14327d ? 85.0d : 25.0d);
        }
        return Double.valueOf(dynamicScheme.f14327d ? 30.0d : 90.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ToneDeltaPair y1(DynamicScheme dynamicScheme) {
        return new ToneDeltaPair(f1(), e1(), 15.0d, TonePolarity.NEARER, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ToneDeltaPair y2(DynamicScheme dynamicScheme) {
        return new ToneDeltaPair(C3(), B3(), 15.0d, TonePolarity.NEARER, false);
    }

    public DynamicColor B3() {
        return new DynamicColor("primary", new Function() { // from class: com.google.android.material.color.utilities.a1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                TonalPalette tonalPalette;
                tonalPalette = ((DynamicScheme) obj).f14329f;
                return tonalPalette;
            }
        }, new Function() { // from class: com.google.android.material.color.utilities.b
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Double u2;
                u2 = MaterialDynamicColors.u2((DynamicScheme) obj);
                return u2;
            }
        }, true, new b1(this), null, new ContrastCurve(3.0d, 4.5d, 7.0d, 11.0d), new Function() { // from class: com.google.android.material.color.utilities.c
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                ToneDeltaPair v2;
                v2 = MaterialDynamicColors.this.v2((DynamicScheme) obj);
                return v2;
            }
        });
    }

    public DynamicColor C3() {
        return new DynamicColor("primary_container", new Function() { // from class: com.google.android.material.color.utilities.v
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                TonalPalette tonalPalette;
                tonalPalette = ((DynamicScheme) obj).f14329f;
                return tonalPalette;
            }
        }, new Function() { // from class: com.google.android.material.color.utilities.w
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Double x2;
                x2 = MaterialDynamicColors.x2((DynamicScheme) obj);
                return x2;
            }
        }, true, new b1(this), null, new ContrastCurve(1.0d, 1.0d, 3.0d, 7.0d), new Function() { // from class: com.google.android.material.color.utilities.x
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                ToneDeltaPair y2;
                y2 = MaterialDynamicColors.this.y2((DynamicScheme) obj);
                return y2;
            }
        });
    }

    public DynamicColor D3() {
        return new DynamicColor("secondary", new Function() { // from class: com.google.android.material.color.utilities.o0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                TonalPalette tonalPalette;
                tonalPalette = ((DynamicScheme) obj).f14330g;
                return tonalPalette;
            }
        }, new Function() { // from class: com.google.android.material.color.utilities.p0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Double A2;
                A2 = MaterialDynamicColors.A2((DynamicScheme) obj);
                return A2;
            }
        }, true, new b1(this), null, new ContrastCurve(3.0d, 4.5d, 7.0d, 11.0d), new Function() { // from class: com.google.android.material.color.utilities.q0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                ToneDeltaPair B2;
                B2 = MaterialDynamicColors.this.B2((DynamicScheme) obj);
                return B2;
            }
        });
    }

    public DynamicColor E3() {
        return new DynamicColor("secondary_container", new Function() { // from class: com.google.android.material.color.utilities.o
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                TonalPalette tonalPalette;
                tonalPalette = ((DynamicScheme) obj).f14330g;
                return tonalPalette;
            }
        }, new Function() { // from class: com.google.android.material.color.utilities.p
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Double D2;
                D2 = MaterialDynamicColors.D2((DynamicScheme) obj);
                return D2;
            }
        }, true, new b1(this), null, new ContrastCurve(1.0d, 1.0d, 3.0d, 7.0d), new Function() { // from class: com.google.android.material.color.utilities.r
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                ToneDeltaPair E2;
                E2 = MaterialDynamicColors.this.E2((DynamicScheme) obj);
                return E2;
            }
        });
    }

    public DynamicColor F3() {
        return new DynamicColor("surface", new Function() { // from class: com.google.android.material.color.utilities.a
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                TonalPalette tonalPalette;
                tonalPalette = ((DynamicScheme) obj).f14332i;
                return tonalPalette;
            }
        }, new Function() { // from class: com.google.android.material.color.utilities.q
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Double G2;
                G2 = MaterialDynamicColors.G2((DynamicScheme) obj);
                return G2;
            }
        }, true, null, null, null, null);
    }

    public DynamicColor G3() {
        return new DynamicColor("surface_bright", new Function() { // from class: com.google.android.material.color.utilities.I
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                TonalPalette tonalPalette;
                tonalPalette = ((DynamicScheme) obj).f14332i;
                return tonalPalette;
            }
        }, new Function() { // from class: com.google.android.material.color.utilities.J
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Double I2;
                I2 = MaterialDynamicColors.I2((DynamicScheme) obj);
                return I2;
            }
        }, true, null, null, null, null);
    }

    public DynamicColor H3() {
        return new DynamicColor("surface_container", new Function() { // from class: com.google.android.material.color.utilities.L0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                TonalPalette tonalPalette;
                tonalPalette = ((DynamicScheme) obj).f14332i;
                return tonalPalette;
            }
        }, new Function() { // from class: com.google.android.material.color.utilities.M0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Double K2;
                K2 = MaterialDynamicColors.K2((DynamicScheme) obj);
                return K2;
            }
        }, true, null, null, null, null);
    }

    public DynamicColor I3() {
        return new DynamicColor("surface_container_high", new Function() { // from class: com.google.android.material.color.utilities.A
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                TonalPalette tonalPalette;
                tonalPalette = ((DynamicScheme) obj).f14332i;
                return tonalPalette;
            }
        }, new Function() { // from class: com.google.android.material.color.utilities.C
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Double M2;
                M2 = MaterialDynamicColors.M2((DynamicScheme) obj);
                return M2;
            }
        }, true, null, null, null, null);
    }

    public DynamicColor J3() {
        return new DynamicColor("surface_container_highest", new Function() { // from class: com.google.android.material.color.utilities.f0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                TonalPalette tonalPalette;
                tonalPalette = ((DynamicScheme) obj).f14332i;
                return tonalPalette;
            }
        }, new Function() { // from class: com.google.android.material.color.utilities.g0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Double O2;
                O2 = MaterialDynamicColors.O2((DynamicScheme) obj);
                return O2;
            }
        }, true, null, null, null, null);
    }

    public DynamicColor K3() {
        return new DynamicColor("surface_container_low", new Function() { // from class: com.google.android.material.color.utilities.m
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                TonalPalette tonalPalette;
                tonalPalette = ((DynamicScheme) obj).f14332i;
                return tonalPalette;
            }
        }, new Function() { // from class: com.google.android.material.color.utilities.n
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Double Q2;
                Q2 = MaterialDynamicColors.Q2((DynamicScheme) obj);
                return Q2;
            }
        }, true, null, null, null, null);
    }

    public DynamicColor L3() {
        return new DynamicColor("surface_container_lowest", new Function() { // from class: com.google.android.material.color.utilities.r0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                TonalPalette tonalPalette;
                tonalPalette = ((DynamicScheme) obj).f14332i;
                return tonalPalette;
            }
        }, new Function() { // from class: com.google.android.material.color.utilities.s0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Double S2;
                S2 = MaterialDynamicColors.S2((DynamicScheme) obj);
                return S2;
            }
        }, true, null, null, null, null);
    }

    public DynamicColor M3() {
        return new DynamicColor("surface_dim", new Function() { // from class: com.google.android.material.color.utilities.g
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                TonalPalette tonalPalette;
                tonalPalette = ((DynamicScheme) obj).f14332i;
                return tonalPalette;
            }
        }, new Function() { // from class: com.google.android.material.color.utilities.h
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Double U2;
                U2 = MaterialDynamicColors.U2((DynamicScheme) obj);
                return U2;
            }
        }, true, null, null, null, null);
    }

    public DynamicColor N3() {
        return new DynamicColor("surface_variant", new Function() { // from class: com.google.android.material.color.utilities.b0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                TonalPalette tonalPalette;
                tonalPalette = ((DynamicScheme) obj).f14333j;
                return tonalPalette;
            }
        }, new Function() { // from class: com.google.android.material.color.utilities.c0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Double W2;
                W2 = MaterialDynamicColors.W2((DynamicScheme) obj);
                return W2;
            }
        }, true, null, null, null, null);
    }

    public DynamicColor O3() {
        return new DynamicColor("tertiary", new Function() { // from class: com.google.android.material.color.utilities.d
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                TonalPalette tonalPalette;
                tonalPalette = ((DynamicScheme) obj).f14331h;
                return tonalPalette;
            }
        }, new Function() { // from class: com.google.android.material.color.utilities.e
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Double Y2;
                Y2 = MaterialDynamicColors.Y2((DynamicScheme) obj);
                return Y2;
            }
        }, true, new b1(this), null, new ContrastCurve(3.0d, 4.5d, 7.0d, 11.0d), new Function() { // from class: com.google.android.material.color.utilities.f
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                ToneDeltaPair Z2;
                Z2 = MaterialDynamicColors.this.Z2((DynamicScheme) obj);
                return Z2;
            }
        });
    }

    public DynamicColor P3() {
        return new DynamicColor("tertiary_container", new Function() { // from class: com.google.android.material.color.utilities.h0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                TonalPalette tonalPalette;
                tonalPalette = ((DynamicScheme) obj).f14331h;
                return tonalPalette;
            }
        }, new Function() { // from class: com.google.android.material.color.utilities.i0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Double b3;
                b3 = MaterialDynamicColors.b3((DynamicScheme) obj);
                return b3;
            }
        }, true, new b1(this), null, new ContrastCurve(1.0d, 1.0d, 3.0d, 7.0d), new Function() { // from class: com.google.android.material.color.utilities.k0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                ToneDeltaPair c3;
                c3 = MaterialDynamicColors.this.c3((DynamicScheme) obj);
                return c3;
            }
        });
    }

    public DynamicColor Q3() {
        return DynamicColor.c("text_hint_inverse", new Function() { // from class: com.google.android.material.color.utilities.d0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                TonalPalette tonalPalette;
                tonalPalette = ((DynamicScheme) obj).f14332i;
                return tonalPalette;
            }
        }, new Function() { // from class: com.google.android.material.color.utilities.e0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Double e3;
                e3 = MaterialDynamicColors.e3((DynamicScheme) obj);
                return e3;
            }
        });
    }

    public DynamicColor R3() {
        return DynamicColor.c("text_primary_inverse", new Function() { // from class: com.google.android.material.color.utilities.J0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                TonalPalette tonalPalette;
                tonalPalette = ((DynamicScheme) obj).f14332i;
                return tonalPalette;
            }
        }, new Function() { // from class: com.google.android.material.color.utilities.K0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Double g3;
                g3 = MaterialDynamicColors.g3((DynamicScheme) obj);
                return g3;
            }
        });
    }

    public DynamicColor S3() {
        return DynamicColor.c("text_primary_inverse_disable_only", new Function() { // from class: com.google.android.material.color.utilities.V
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                TonalPalette tonalPalette;
                tonalPalette = ((DynamicScheme) obj).f14332i;
                return tonalPalette;
            }
        }, new Function() { // from class: com.google.android.material.color.utilities.W
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Double i3;
                i3 = MaterialDynamicColors.i3((DynamicScheme) obj);
                return i3;
            }
        });
    }

    public DynamicColor T3() {
        return DynamicColor.c("text_secondary_and_tertiary_inverse", new Function() { // from class: com.google.android.material.color.utilities.V0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                TonalPalette tonalPalette;
                tonalPalette = ((DynamicScheme) obj).f14333j;
                return tonalPalette;
            }
        }, new Function() { // from class: com.google.android.material.color.utilities.W0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Double k3;
                k3 = MaterialDynamicColors.k3((DynamicScheme) obj);
                return k3;
            }
        });
    }

    public DynamicColor U3() {
        return DynamicColor.c("text_secondary_and_tertiary_inverse_disabled", new Function() { // from class: com.google.android.material.color.utilities.B
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                TonalPalette tonalPalette;
                tonalPalette = ((DynamicScheme) obj).f14332i;
                return tonalPalette;
            }
        }, new Function() { // from class: com.google.android.material.color.utilities.M
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Double m3;
                m3 = MaterialDynamicColors.m3((DynamicScheme) obj);
                return m3;
            }
        });
    }

    public DynamicColor a1() {
        return new DynamicColor("background", new Function() { // from class: com.google.android.material.color.utilities.y
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                TonalPalette tonalPalette;
                tonalPalette = ((DynamicScheme) obj).f14332i;
                return tonalPalette;
            }
        }, new Function() { // from class: com.google.android.material.color.utilities.z
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Double o1;
                o1 = MaterialDynamicColors.o1((DynamicScheme) obj);
                return o1;
            }
        }, true, null, null, null, null);
    }

    public DynamicColor b1() {
        return DynamicColor.c("control_activated", new Function() { // from class: com.google.android.material.color.utilities.k
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                TonalPalette tonalPalette;
                tonalPalette = ((DynamicScheme) obj).f14329f;
                return tonalPalette;
            }
        }, new Function() { // from class: com.google.android.material.color.utilities.l
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Double q1;
                q1 = MaterialDynamicColors.q1((DynamicScheme) obj);
                return q1;
            }
        });
    }

    public DynamicColor c1() {
        return new DynamicColor("control_highlight", new Function() { // from class: com.google.android.material.color.utilities.s
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                TonalPalette tonalPalette;
                tonalPalette = ((DynamicScheme) obj).f14332i;
                return tonalPalette;
            }
        }, new Function() { // from class: com.google.android.material.color.utilities.t
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Double s1;
                s1 = MaterialDynamicColors.s1((DynamicScheme) obj);
                return s1;
            }
        }, false, null, null, null, null, new Function() { // from class: com.google.android.material.color.utilities.u
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Double t1;
                t1 = MaterialDynamicColors.t1((DynamicScheme) obj);
                return t1;
            }
        });
    }

    public DynamicColor d1() {
        return DynamicColor.c("control_normal", new Function() { // from class: com.google.android.material.color.utilities.i
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                TonalPalette tonalPalette;
                tonalPalette = ((DynamicScheme) obj).f14333j;
                return tonalPalette;
            }
        }, new Function() { // from class: com.google.android.material.color.utilities.j
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Double v1;
                v1 = MaterialDynamicColors.v1((DynamicScheme) obj);
                return v1;
            }
        });
    }

    public DynamicColor e1() {
        return new DynamicColor("error", new Function() { // from class: com.google.android.material.color.utilities.G0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                TonalPalette tonalPalette;
                tonalPalette = ((DynamicScheme) obj).f14334k;
                return tonalPalette;
            }
        }, new Function() { // from class: com.google.android.material.color.utilities.H0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Double x1;
                x1 = MaterialDynamicColors.x1((DynamicScheme) obj);
                return x1;
            }
        }, true, new b1(this), null, new ContrastCurve(3.0d, 4.5d, 7.0d, 11.0d), new Function() { // from class: com.google.android.material.color.utilities.I0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                ToneDeltaPair y1;
                y1 = MaterialDynamicColors.this.y1((DynamicScheme) obj);
                return y1;
            }
        });
    }

    public DynamicColor f1() {
        return new DynamicColor("error_container", new Function() { // from class: com.google.android.material.color.utilities.X0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                TonalPalette tonalPalette;
                tonalPalette = ((DynamicScheme) obj).f14334k;
                return tonalPalette;
            }
        }, new Function() { // from class: com.google.android.material.color.utilities.Y0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Double A1;
                A1 = MaterialDynamicColors.A1((DynamicScheme) obj);
                return A1;
            }
        }, true, new b1(this), null, new ContrastCurve(1.0d, 1.0d, 3.0d, 7.0d), new Function() { // from class: com.google.android.material.color.utilities.Z0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                ToneDeltaPair B1;
                B1 = MaterialDynamicColors.this.B1((DynamicScheme) obj);
                return B1;
            }
        });
    }

    public DynamicColor h1(DynamicScheme dynamicScheme) {
        return dynamicScheme.f14327d ? G3() : M3();
    }

    public DynamicColor i1() {
        return new DynamicColor("inverse_on_surface", new Function() { // from class: com.google.android.material.color.utilities.w0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                TonalPalette tonalPalette;
                tonalPalette = ((DynamicScheme) obj).f14332i;
                return tonalPalette;
            }
        }, new Function() { // from class: com.google.android.material.color.utilities.x0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Double D1;
                D1 = MaterialDynamicColors.D1((DynamicScheme) obj);
                return D1;
            }
        }, false, new Function() { // from class: com.google.android.material.color.utilities.y0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                DynamicColor E1;
                E1 = MaterialDynamicColors.this.E1((DynamicScheme) obj);
                return E1;
            }
        }, null, new ContrastCurve(4.5d, 7.0d, 11.0d, 21.0d), null);
    }

    public DynamicColor j1() {
        return new DynamicColor("inverse_primary", new Function() { // from class: com.google.android.material.color.utilities.O
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                TonalPalette tonalPalette;
                tonalPalette = ((DynamicScheme) obj).f14329f;
                return tonalPalette;
            }
        }, new Function() { // from class: com.google.android.material.color.utilities.P
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Double G1;
                G1 = MaterialDynamicColors.G1((DynamicScheme) obj);
                return G1;
            }
        }, false, new Function() { // from class: com.google.android.material.color.utilities.Q
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                DynamicColor H1;
                H1 = MaterialDynamicColors.this.H1((DynamicScheme) obj);
                return H1;
            }
        }, null, new ContrastCurve(3.0d, 4.5d, 7.0d, 11.0d), null);
    }

    public DynamicColor k1() {
        return new DynamicColor("inverse_surface", new Function() { // from class: com.google.android.material.color.utilities.P0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                TonalPalette tonalPalette;
                tonalPalette = ((DynamicScheme) obj).f14332i;
                return tonalPalette;
            }
        }, new Function() { // from class: com.google.android.material.color.utilities.R0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Double J1;
                J1 = MaterialDynamicColors.J1((DynamicScheme) obj);
                return J1;
            }
        }, false, null, null, null, null);
    }

    public DynamicColor n3() {
        return new DynamicColor("on_background", new Function() { // from class: com.google.android.material.color.utilities.D
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                TonalPalette tonalPalette;
                tonalPalette = ((DynamicScheme) obj).f14332i;
                return tonalPalette;
            }
        }, new Function() { // from class: com.google.android.material.color.utilities.E
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Double L1;
                L1 = MaterialDynamicColors.L1((DynamicScheme) obj);
                return L1;
            }
        }, false, new Function() { // from class: com.google.android.material.color.utilities.F
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                DynamicColor M1;
                M1 = MaterialDynamicColors.this.M1((DynamicScheme) obj);
                return M1;
            }
        }, null, new ContrastCurve(3.0d, 3.0d, 4.5d, 7.0d), null);
    }

    public DynamicColor o3() {
        return new DynamicColor("on_error", new Function() { // from class: com.google.android.material.color.utilities.S
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                TonalPalette tonalPalette;
                tonalPalette = ((DynamicScheme) obj).f14334k;
                return tonalPalette;
            }
        }, new Function() { // from class: com.google.android.material.color.utilities.T
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Double O1;
                O1 = MaterialDynamicColors.O1((DynamicScheme) obj);
                return O1;
            }
        }, false, new Function() { // from class: com.google.android.material.color.utilities.U
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                DynamicColor P1;
                P1 = MaterialDynamicColors.this.P1((DynamicScheme) obj);
                return P1;
            }
        }, null, new ContrastCurve(4.5d, 7.0d, 11.0d, 21.0d), null);
    }

    public DynamicColor p3() {
        return new DynamicColor("on_error_container", new Function() { // from class: com.google.android.material.color.utilities.S0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                TonalPalette tonalPalette;
                tonalPalette = ((DynamicScheme) obj).f14334k;
                return tonalPalette;
            }
        }, new Function() { // from class: com.google.android.material.color.utilities.T0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Double R1;
                R1 = MaterialDynamicColors.R1((DynamicScheme) obj);
                return R1;
            }
        }, false, new Function() { // from class: com.google.android.material.color.utilities.U0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                DynamicColor S1;
                S1 = MaterialDynamicColors.this.S1((DynamicScheme) obj);
                return S1;
            }
        }, null, new ContrastCurve(4.5d, 7.0d, 11.0d, 21.0d), null);
    }

    public DynamicColor q3() {
        return new DynamicColor("on_primary", new Function() { // from class: com.google.android.material.color.utilities.K
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                TonalPalette tonalPalette;
                tonalPalette = ((DynamicScheme) obj).f14329f;
                return tonalPalette;
            }
        }, new Function() { // from class: com.google.android.material.color.utilities.L
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Double U1;
                U1 = MaterialDynamicColors.U1((DynamicScheme) obj);
                return U1;
            }
        }, false, new Function() { // from class: com.google.android.material.color.utilities.N
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                DynamicColor V1;
                V1 = MaterialDynamicColors.this.V1((DynamicScheme) obj);
                return V1;
            }
        }, null, new ContrastCurve(4.5d, 7.0d, 11.0d, 21.0d), null);
    }

    public DynamicColor r3() {
        return new DynamicColor("on_primary_container", new Function() { // from class: com.google.android.material.color.utilities.X
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                TonalPalette tonalPalette;
                tonalPalette = ((DynamicScheme) obj).f14329f;
                return tonalPalette;
            }
        }, new Function() { // from class: com.google.android.material.color.utilities.Z
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Double X1;
                X1 = MaterialDynamicColors.this.X1((DynamicScheme) obj);
                return X1;
            }
        }, false, new Function() { // from class: com.google.android.material.color.utilities.a0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                DynamicColor Y1;
                Y1 = MaterialDynamicColors.this.Y1((DynamicScheme) obj);
                return Y1;
            }
        }, null, new ContrastCurve(4.5d, 7.0d, 11.0d, 21.0d), null);
    }

    public DynamicColor s3() {
        return new DynamicColor("on_secondary", new Function() { // from class: com.google.android.material.color.utilities.l0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                TonalPalette tonalPalette;
                tonalPalette = ((DynamicScheme) obj).f14330g;
                return tonalPalette;
            }
        }, new Function() { // from class: com.google.android.material.color.utilities.m0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Double a2;
                a2 = MaterialDynamicColors.a2((DynamicScheme) obj);
                return a2;
            }
        }, false, new Function() { // from class: com.google.android.material.color.utilities.n0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                DynamicColor b2;
                b2 = MaterialDynamicColors.this.b2((DynamicScheme) obj);
                return b2;
            }
        }, null, new ContrastCurve(4.5d, 7.0d, 11.0d, 21.0d), null);
    }

    public DynamicColor t3() {
        return new DynamicColor("on_secondary_container", new Function() { // from class: com.google.android.material.color.utilities.C0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                TonalPalette tonalPalette;
                tonalPalette = ((DynamicScheme) obj).f14330g;
                return tonalPalette;
            }
        }, new Function() { // from class: com.google.android.material.color.utilities.D0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Double d2;
                d2 = MaterialDynamicColors.this.d2((DynamicScheme) obj);
                return d2;
            }
        }, false, new Function() { // from class: com.google.android.material.color.utilities.E0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                DynamicColor e2;
                e2 = MaterialDynamicColors.this.e2((DynamicScheme) obj);
                return e2;
            }
        }, null, new ContrastCurve(4.5d, 7.0d, 11.0d, 21.0d), null);
    }

    public DynamicColor u3() {
        return new DynamicColor("on_surface", new Function() { // from class: com.google.android.material.color.utilities.F0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                TonalPalette tonalPalette;
                tonalPalette = ((DynamicScheme) obj).f14332i;
                return tonalPalette;
            }
        }, new Function() { // from class: com.google.android.material.color.utilities.Q0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Double g2;
                g2 = MaterialDynamicColors.g2((DynamicScheme) obj);
                return g2;
            }
        }, false, new b1(this), null, new ContrastCurve(4.5d, 7.0d, 11.0d, 21.0d), null);
    }

    public DynamicColor v3() {
        return new DynamicColor("on_surface_variant", new Function() { // from class: com.google.android.material.color.utilities.N0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                TonalPalette tonalPalette;
                tonalPalette = ((DynamicScheme) obj).f14333j;
                return tonalPalette;
            }
        }, new Function() { // from class: com.google.android.material.color.utilities.O0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Double i2;
                i2 = MaterialDynamicColors.i2((DynamicScheme) obj);
                return i2;
            }
        }, false, new b1(this), null, new ContrastCurve(3.0d, 4.5d, 7.0d, 11.0d), null);
    }

    public DynamicColor w3() {
        return new DynamicColor("on_tertiary", new Function() { // from class: com.google.android.material.color.utilities.Y
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                TonalPalette tonalPalette;
                tonalPalette = ((DynamicScheme) obj).f14331h;
                return tonalPalette;
            }
        }, new Function() { // from class: com.google.android.material.color.utilities.j0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Double k2;
                k2 = MaterialDynamicColors.k2((DynamicScheme) obj);
                return k2;
            }
        }, false, new Function() { // from class: com.google.android.material.color.utilities.u0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                DynamicColor l2;
                l2 = MaterialDynamicColors.this.l2((DynamicScheme) obj);
                return l2;
            }
        }, null, new ContrastCurve(4.5d, 7.0d, 11.0d, 21.0d), null);
    }

    public DynamicColor x3() {
        return new DynamicColor("on_tertiary_container", new Function() { // from class: com.google.android.material.color.utilities.z0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                TonalPalette tonalPalette;
                tonalPalette = ((DynamicScheme) obj).f14331h;
                return tonalPalette;
            }
        }, new Function() { // from class: com.google.android.material.color.utilities.A0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Double n2;
                n2 = MaterialDynamicColors.this.n2((DynamicScheme) obj);
                return n2;
            }
        }, false, new Function() { // from class: com.google.android.material.color.utilities.B0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                DynamicColor o2;
                o2 = MaterialDynamicColors.this.o2((DynamicScheme) obj);
                return o2;
            }
        }, null, new ContrastCurve(4.5d, 7.0d, 11.0d, 21.0d), null);
    }

    public DynamicColor y3() {
        return new DynamicColor("outline", new Function() { // from class: com.google.android.material.color.utilities.t0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                TonalPalette tonalPalette;
                tonalPalette = ((DynamicScheme) obj).f14333j;
                return tonalPalette;
            }
        }, new Function() { // from class: com.google.android.material.color.utilities.v0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Double q2;
                q2 = MaterialDynamicColors.q2((DynamicScheme) obj);
                return q2;
            }
        }, false, new b1(this), null, new ContrastCurve(1.5d, 3.0d, 4.5d, 7.0d), null);
    }

    public DynamicColor z3() {
        return new DynamicColor("outline_variant", new Function() { // from class: com.google.android.material.color.utilities.G
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                TonalPalette tonalPalette;
                tonalPalette = ((DynamicScheme) obj).f14333j;
                return tonalPalette;
            }
        }, new Function() { // from class: com.google.android.material.color.utilities.H
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Double s2;
                s2 = MaterialDynamicColors.s2((DynamicScheme) obj);
                return s2;
            }
        }, false, new b1(this), null, new ContrastCurve(1.0d, 1.0d, 3.0d, 7.0d), null);
    }
}
