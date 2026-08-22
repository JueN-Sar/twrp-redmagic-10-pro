package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.Barrier;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.core.widgets.Guideline;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class Direct {

    /* renamed from: a, reason: collision with root package name */
    private static BasicMeasure.Measure f2049a = new BasicMeasure.Measure();

    /* renamed from: b, reason: collision with root package name */
    private static int f2050b = 0;

    /* renamed from: c, reason: collision with root package name */
    private static int f2051c = 0;

    private static boolean a(int i2, ConstraintWidget constraintWidget) {
        ConstraintWidget.DimensionBehaviour dimensionBehaviour;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2;
        ConstraintWidget.DimensionBehaviour C = constraintWidget.C();
        ConstraintWidget.DimensionBehaviour V = constraintWidget.V();
        ConstraintWidgetContainer constraintWidgetContainer = constraintWidget.M() != null ? (ConstraintWidgetContainer) constraintWidget.M() : null;
        if (constraintWidgetContainer != null) {
            constraintWidgetContainer.C();
            ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        if (constraintWidgetContainer != null) {
            constraintWidgetContainer.V();
            ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        ConstraintWidget.DimensionBehaviour dimensionBehaviour5 = ConstraintWidget.DimensionBehaviour.FIXED;
        boolean z = C == dimensionBehaviour5 || constraintWidget.p0() || C == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || (C == (dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) && constraintWidget.w == 0 && constraintWidget.f0 == 0.0f && constraintWidget.c0(0)) || (C == dimensionBehaviour2 && constraintWidget.w == 1 && constraintWidget.f0(0, constraintWidget.Y()));
        boolean z2 = V == dimensionBehaviour5 || constraintWidget.q0() || V == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || (V == (dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) && constraintWidget.x == 0 && constraintWidget.f0 == 0.0f && constraintWidget.c0(1)) || (V == dimensionBehaviour && constraintWidget.x == 1 && constraintWidget.f0(1, constraintWidget.z()));
        if (constraintWidget.f0 <= 0.0f || !(z || z2)) {
            return z && z2;
        }
        return true;
    }

    private static void b(int i2, ConstraintWidget constraintWidget, BasicMeasure.Measurer measurer, boolean z) {
        ConstraintAnchor constraintAnchor;
        ConstraintAnchor constraintAnchor2;
        ConstraintAnchor constraintAnchor3;
        ConstraintAnchor constraintAnchor4;
        if (constraintWidget.i0()) {
            return;
        }
        boolean z2 = true;
        f2050b++;
        if (!(constraintWidget instanceof ConstraintWidgetContainer) && constraintWidget.o0()) {
            int i3 = i2 + 1;
            if (a(i3, constraintWidget)) {
                ConstraintWidgetContainer.Z1(i3, constraintWidget, measurer, new BasicMeasure.Measure(), BasicMeasure.Measure.f2012k);
            }
        }
        ConstraintAnchor q2 = constraintWidget.q(ConstraintAnchor.Type.LEFT);
        ConstraintAnchor q3 = constraintWidget.q(ConstraintAnchor.Type.RIGHT);
        int e2 = q2.e();
        int e3 = q3.e();
        if (q2.d() != null && q2.n()) {
            Iterator it = q2.d().iterator();
            while (it.hasNext()) {
                ConstraintAnchor constraintAnchor5 = (ConstraintAnchor) it.next();
                ConstraintWidget constraintWidget2 = constraintAnchor5.f1963d;
                int i4 = i2 + 1;
                boolean a2 = a(i4, constraintWidget2);
                if (constraintWidget2.o0() && a2) {
                    ConstraintWidgetContainer.Z1(i4, constraintWidget2, measurer, new BasicMeasure.Measure(), BasicMeasure.Measure.f2012k);
                }
                boolean z3 = ((constraintAnchor5 == constraintWidget2.Q && (constraintAnchor4 = constraintWidget2.S.f1965f) != null && constraintAnchor4.n()) || (constraintAnchor5 == constraintWidget2.S && (constraintAnchor3 = constraintWidget2.Q.f1965f) != null && constraintAnchor3.n())) ? z2 : false;
                ConstraintWidget.DimensionBehaviour C = constraintWidget2.C();
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (C != dimensionBehaviour || a2) {
                    if (!constraintWidget2.o0()) {
                        ConstraintAnchor constraintAnchor6 = constraintWidget2.Q;
                        if (constraintAnchor5 == constraintAnchor6 && constraintWidget2.S.f1965f == null) {
                            int f2 = constraintAnchor6.f() + e2;
                            constraintWidget2.K0(f2, constraintWidget2.Y() + f2);
                            b(i4, constraintWidget2, measurer, z);
                        } else {
                            ConstraintAnchor constraintAnchor7 = constraintWidget2.S;
                            if (constraintAnchor5 == constraintAnchor7 && constraintAnchor6.f1965f == null) {
                                int f3 = e2 - constraintAnchor7.f();
                                constraintWidget2.K0(f3 - constraintWidget2.Y(), f3);
                                b(i4, constraintWidget2, measurer, z);
                            } else if (z3 && !constraintWidget2.k0()) {
                                d(i4, measurer, constraintWidget2, z);
                            }
                        }
                    }
                } else if (constraintWidget2.C() == dimensionBehaviour && constraintWidget2.A >= 0 && constraintWidget2.z >= 0 && ((constraintWidget2.X() == 8 || (constraintWidget2.w == 0 && constraintWidget2.x() == 0.0f)) && !constraintWidget2.k0() && !constraintWidget2.n0() && z3 && !constraintWidget2.k0())) {
                    e(i4, constraintWidget, measurer, constraintWidget2, z);
                }
                z2 = true;
            }
        }
        if (constraintWidget instanceof Guideline) {
            return;
        }
        if (q3.d() != null && q3.n()) {
            Iterator it2 = q3.d().iterator();
            while (it2.hasNext()) {
                ConstraintAnchor constraintAnchor8 = (ConstraintAnchor) it2.next();
                ConstraintWidget constraintWidget3 = constraintAnchor8.f1963d;
                int i5 = i2 + 1;
                boolean a3 = a(i5, constraintWidget3);
                if (constraintWidget3.o0() && a3) {
                    ConstraintWidgetContainer.Z1(i5, constraintWidget3, measurer, new BasicMeasure.Measure(), BasicMeasure.Measure.f2012k);
                }
                boolean z4 = (constraintAnchor8 == constraintWidget3.Q && (constraintAnchor2 = constraintWidget3.S.f1965f) != null && constraintAnchor2.n()) || (constraintAnchor8 == constraintWidget3.S && (constraintAnchor = constraintWidget3.Q.f1965f) != null && constraintAnchor.n());
                ConstraintWidget.DimensionBehaviour C2 = constraintWidget3.C();
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (C2 != dimensionBehaviour2 || a3) {
                    if (!constraintWidget3.o0()) {
                        ConstraintAnchor constraintAnchor9 = constraintWidget3.Q;
                        if (constraintAnchor8 == constraintAnchor9 && constraintWidget3.S.f1965f == null) {
                            int f4 = constraintAnchor9.f() + e3;
                            constraintWidget3.K0(f4, constraintWidget3.Y() + f4);
                            b(i5, constraintWidget3, measurer, z);
                        } else {
                            ConstraintAnchor constraintAnchor10 = constraintWidget3.S;
                            if (constraintAnchor8 == constraintAnchor10 && constraintAnchor9.f1965f == null) {
                                int f5 = e3 - constraintAnchor10.f();
                                constraintWidget3.K0(f5 - constraintWidget3.Y(), f5);
                                b(i5, constraintWidget3, measurer, z);
                            } else if (z4 && !constraintWidget3.k0()) {
                                d(i5, measurer, constraintWidget3, z);
                            }
                        }
                    }
                } else if (constraintWidget3.C() == dimensionBehaviour2 && constraintWidget3.A >= 0 && constraintWidget3.z >= 0 && (constraintWidget3.X() == 8 || (constraintWidget3.w == 0 && constraintWidget3.x() == 0.0f))) {
                    if (!constraintWidget3.k0() && !constraintWidget3.n0() && z4 && !constraintWidget3.k0()) {
                        e(i5, constraintWidget, measurer, constraintWidget3, z);
                    }
                }
            }
        }
        constraintWidget.s0();
    }

    private static void c(int i2, Barrier barrier, BasicMeasure.Measurer measurer, int i3, boolean z) {
        if (barrier.y1()) {
            if (i3 == 0) {
                b(i2 + 1, barrier, measurer, z);
            } else {
                i(i2 + 1, barrier, measurer);
            }
        }
    }

    private static void d(int i2, BasicMeasure.Measurer measurer, ConstraintWidget constraintWidget, boolean z) {
        float A = constraintWidget.A();
        int e2 = constraintWidget.Q.f1965f.e();
        int e3 = constraintWidget.S.f1965f.e();
        int f2 = constraintWidget.Q.f() + e2;
        int f3 = e3 - constraintWidget.S.f();
        if (e2 == e3) {
            A = 0.5f;
        } else {
            e2 = f2;
            e3 = f3;
        }
        int Y = constraintWidget.Y();
        int i3 = (e3 - e2) - Y;
        if (e2 > e3) {
            i3 = (e2 - e3) - Y;
        }
        int i4 = ((int) (i3 > 0 ? (A * i3) + 0.5f : A * i3)) + e2;
        int i5 = i4 + Y;
        if (e2 > e3) {
            i5 = i4 - Y;
        }
        constraintWidget.K0(i4, i5);
        b(i2 + 1, constraintWidget, measurer, z);
    }

    private static void e(int i2, ConstraintWidget constraintWidget, BasicMeasure.Measurer measurer, ConstraintWidget constraintWidget2, boolean z) {
        float A = constraintWidget2.A();
        int e2 = constraintWidget2.Q.f1965f.e() + constraintWidget2.Q.f();
        int e3 = constraintWidget2.S.f1965f.e() - constraintWidget2.S.f();
        if (e3 >= e2) {
            int Y = constraintWidget2.Y();
            if (constraintWidget2.X() != 8) {
                int i3 = constraintWidget2.w;
                if (i3 == 2) {
                    Y = (int) (constraintWidget2.A() * 0.5f * (constraintWidget instanceof ConstraintWidgetContainer ? constraintWidget.Y() : constraintWidget.M().Y()));
                } else if (i3 == 0) {
                    Y = e3 - e2;
                }
                Y = Math.max(constraintWidget2.z, Y);
                int i4 = constraintWidget2.A;
                if (i4 > 0) {
                    Y = Math.min(i4, Y);
                }
            }
            int i5 = e2 + ((int) ((A * ((e3 - e2) - Y)) + 0.5f));
            constraintWidget2.K0(i5, Y + i5);
            b(i2 + 1, constraintWidget2, measurer, z);
        }
    }

    private static void f(int i2, BasicMeasure.Measurer measurer, ConstraintWidget constraintWidget) {
        float T = constraintWidget.T();
        int e2 = constraintWidget.R.f1965f.e();
        int e3 = constraintWidget.T.f1965f.e();
        int f2 = constraintWidget.R.f() + e2;
        int f3 = e3 - constraintWidget.T.f();
        if (e2 == e3) {
            T = 0.5f;
        } else {
            e2 = f2;
            e3 = f3;
        }
        int z = constraintWidget.z();
        int i3 = (e3 - e2) - z;
        if (e2 > e3) {
            i3 = (e2 - e3) - z;
        }
        int i4 = (int) (i3 > 0 ? (T * i3) + 0.5f : T * i3);
        int i5 = e2 + i4;
        int i6 = i5 + z;
        if (e2 > e3) {
            i5 = e2 - i4;
            i6 = i5 - z;
        }
        constraintWidget.N0(i5, i6);
        i(i2 + 1, constraintWidget, measurer);
    }

    private static void g(int i2, ConstraintWidget constraintWidget, BasicMeasure.Measurer measurer, ConstraintWidget constraintWidget2) {
        float T = constraintWidget2.T();
        int e2 = constraintWidget2.R.f1965f.e() + constraintWidget2.R.f();
        int e3 = constraintWidget2.T.f1965f.e() - constraintWidget2.T.f();
        if (e3 >= e2) {
            int z = constraintWidget2.z();
            if (constraintWidget2.X() != 8) {
                int i3 = constraintWidget2.x;
                if (i3 == 2) {
                    z = (int) (T * 0.5f * (constraintWidget instanceof ConstraintWidgetContainer ? constraintWidget.z() : constraintWidget.M().z()));
                } else if (i3 == 0) {
                    z = e3 - e2;
                }
                z = Math.max(constraintWidget2.C, z);
                int i4 = constraintWidget2.D;
                if (i4 > 0) {
                    z = Math.min(i4, z);
                }
            }
            int i5 = e2 + ((int) ((T * ((e3 - e2) - z)) + 0.5f));
            constraintWidget2.N0(i5, z + i5);
            i(i2 + 1, constraintWidget2, measurer);
        }
    }

    public static void h(ConstraintWidgetContainer constraintWidgetContainer, BasicMeasure.Measurer measurer) {
        ConstraintWidget.DimensionBehaviour C = constraintWidgetContainer.C();
        ConstraintWidget.DimensionBehaviour V = constraintWidgetContainer.V();
        f2050b = 0;
        f2051c = 0;
        constraintWidgetContainer.y0();
        ArrayList x1 = constraintWidgetContainer.x1();
        int size = x1.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((ConstraintWidget) x1.get(i2)).y0();
        }
        boolean W1 = constraintWidgetContainer.W1();
        if (C == ConstraintWidget.DimensionBehaviour.FIXED) {
            constraintWidgetContainer.K0(0, constraintWidgetContainer.Y());
        } else {
            constraintWidgetContainer.L0(0);
        }
        boolean z = false;
        boolean z2 = false;
        for (int i3 = 0; i3 < size; i3++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) x1.get(i3);
            if (constraintWidget instanceof Guideline) {
                Guideline guideline = (Guideline) constraintWidget;
                if (guideline.x1() == 1) {
                    if (guideline.y1() != -1) {
                        guideline.B1(guideline.y1());
                    } else if (guideline.z1() != -1 && constraintWidgetContainer.p0()) {
                        guideline.B1(constraintWidgetContainer.Y() - guideline.z1());
                    } else if (constraintWidgetContainer.p0()) {
                        guideline.B1((int) ((guideline.A1() * constraintWidgetContainer.Y()) + 0.5f));
                    }
                    z = true;
                }
            } else if ((constraintWidget instanceof Barrier) && ((Barrier) constraintWidget).C1() == 0) {
                z2 = true;
            }
        }
        if (z) {
            for (int i4 = 0; i4 < size; i4++) {
                ConstraintWidget constraintWidget2 = (ConstraintWidget) x1.get(i4);
                if (constraintWidget2 instanceof Guideline) {
                    Guideline guideline2 = (Guideline) constraintWidget2;
                    if (guideline2.x1() == 1) {
                        b(0, guideline2, measurer, W1);
                    }
                }
            }
        }
        b(0, constraintWidgetContainer, measurer, W1);
        if (z2) {
            for (int i5 = 0; i5 < size; i5++) {
                ConstraintWidget constraintWidget3 = (ConstraintWidget) x1.get(i5);
                if (constraintWidget3 instanceof Barrier) {
                    Barrier barrier = (Barrier) constraintWidget3;
                    if (barrier.C1() == 0) {
                        c(0, barrier, measurer, 0, W1);
                    }
                }
            }
        }
        if (V == ConstraintWidget.DimensionBehaviour.FIXED) {
            constraintWidgetContainer.N0(0, constraintWidgetContainer.z());
        } else {
            constraintWidgetContainer.M0(0);
        }
        boolean z3 = false;
        boolean z4 = false;
        for (int i6 = 0; i6 < size; i6++) {
            ConstraintWidget constraintWidget4 = (ConstraintWidget) x1.get(i6);
            if (constraintWidget4 instanceof Guideline) {
                Guideline guideline3 = (Guideline) constraintWidget4;
                if (guideline3.x1() == 0) {
                    if (guideline3.y1() != -1) {
                        guideline3.B1(guideline3.y1());
                    } else if (guideline3.z1() != -1 && constraintWidgetContainer.q0()) {
                        guideline3.B1(constraintWidgetContainer.z() - guideline3.z1());
                    } else if (constraintWidgetContainer.q0()) {
                        guideline3.B1((int) ((guideline3.A1() * constraintWidgetContainer.z()) + 0.5f));
                    }
                    z3 = true;
                }
            } else if ((constraintWidget4 instanceof Barrier) && ((Barrier) constraintWidget4).C1() == 1) {
                z4 = true;
            }
        }
        if (z3) {
            for (int i7 = 0; i7 < size; i7++) {
                ConstraintWidget constraintWidget5 = (ConstraintWidget) x1.get(i7);
                if (constraintWidget5 instanceof Guideline) {
                    Guideline guideline4 = (Guideline) constraintWidget5;
                    if (guideline4.x1() == 0) {
                        i(1, guideline4, measurer);
                    }
                }
            }
        }
        i(0, constraintWidgetContainer, measurer);
        if (z4) {
            for (int i8 = 0; i8 < size; i8++) {
                ConstraintWidget constraintWidget6 = (ConstraintWidget) x1.get(i8);
                if (constraintWidget6 instanceof Barrier) {
                    Barrier barrier2 = (Barrier) constraintWidget6;
                    if (barrier2.C1() == 1) {
                        c(0, barrier2, measurer, 1, W1);
                    }
                }
            }
        }
        for (int i9 = 0; i9 < size; i9++) {
            ConstraintWidget constraintWidget7 = (ConstraintWidget) x1.get(i9);
            if (constraintWidget7.o0() && a(0, constraintWidget7)) {
                ConstraintWidgetContainer.Z1(0, constraintWidget7, measurer, f2049a, BasicMeasure.Measure.f2012k);
                if (!(constraintWidget7 instanceof Guideline)) {
                    b(0, constraintWidget7, measurer, W1);
                    i(0, constraintWidget7, measurer);
                } else if (((Guideline) constraintWidget7).x1() == 0) {
                    i(0, constraintWidget7, measurer);
                } else {
                    b(0, constraintWidget7, measurer, W1);
                }
            }
        }
    }

    private static void i(int i2, ConstraintWidget constraintWidget, BasicMeasure.Measurer measurer) {
        ConstraintAnchor constraintAnchor;
        ConstraintAnchor constraintAnchor2;
        ConstraintAnchor constraintAnchor3;
        ConstraintAnchor constraintAnchor4;
        if (constraintWidget.r0()) {
            return;
        }
        f2051c++;
        if (!(constraintWidget instanceof ConstraintWidgetContainer) && constraintWidget.o0()) {
            int i3 = i2 + 1;
            if (a(i3, constraintWidget)) {
                ConstraintWidgetContainer.Z1(i3, constraintWidget, measurer, new BasicMeasure.Measure(), BasicMeasure.Measure.f2012k);
            }
        }
        ConstraintAnchor q2 = constraintWidget.q(ConstraintAnchor.Type.TOP);
        ConstraintAnchor q3 = constraintWidget.q(ConstraintAnchor.Type.BOTTOM);
        int e2 = q2.e();
        int e3 = q3.e();
        if (q2.d() != null && q2.n()) {
            Iterator it = q2.d().iterator();
            while (it.hasNext()) {
                ConstraintAnchor constraintAnchor5 = (ConstraintAnchor) it.next();
                ConstraintWidget constraintWidget2 = constraintAnchor5.f1963d;
                int i4 = i2 + 1;
                boolean a2 = a(i4, constraintWidget2);
                if (constraintWidget2.o0() && a2) {
                    ConstraintWidgetContainer.Z1(i4, constraintWidget2, measurer, new BasicMeasure.Measure(), BasicMeasure.Measure.f2012k);
                }
                boolean z = (constraintAnchor5 == constraintWidget2.R && (constraintAnchor4 = constraintWidget2.T.f1965f) != null && constraintAnchor4.n()) || (constraintAnchor5 == constraintWidget2.T && (constraintAnchor3 = constraintWidget2.R.f1965f) != null && constraintAnchor3.n());
                ConstraintWidget.DimensionBehaviour V = constraintWidget2.V();
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (V != dimensionBehaviour || a2) {
                    if (!constraintWidget2.o0()) {
                        ConstraintAnchor constraintAnchor6 = constraintWidget2.R;
                        if (constraintAnchor5 == constraintAnchor6 && constraintWidget2.T.f1965f == null) {
                            int f2 = constraintAnchor6.f() + e2;
                            constraintWidget2.N0(f2, constraintWidget2.z() + f2);
                            i(i4, constraintWidget2, measurer);
                        } else {
                            ConstraintAnchor constraintAnchor7 = constraintWidget2.T;
                            if (constraintAnchor5 == constraintAnchor7 && constraintAnchor6.f1965f == null) {
                                int f3 = e2 - constraintAnchor7.f();
                                constraintWidget2.N0(f3 - constraintWidget2.z(), f3);
                                i(i4, constraintWidget2, measurer);
                            } else if (z && !constraintWidget2.m0()) {
                                f(i4, measurer, constraintWidget2);
                            }
                        }
                    }
                } else if (constraintWidget2.V() == dimensionBehaviour && constraintWidget2.D >= 0 && constraintWidget2.C >= 0 && (constraintWidget2.X() == 8 || (constraintWidget2.x == 0 && constraintWidget2.x() == 0.0f))) {
                    if (!constraintWidget2.m0() && !constraintWidget2.n0() && z && !constraintWidget2.m0()) {
                        g(i4, constraintWidget, measurer, constraintWidget2);
                    }
                }
            }
        }
        if (constraintWidget instanceof Guideline) {
            return;
        }
        if (q3.d() != null && q3.n()) {
            Iterator it2 = q3.d().iterator();
            while (it2.hasNext()) {
                ConstraintAnchor constraintAnchor8 = (ConstraintAnchor) it2.next();
                ConstraintWidget constraintWidget3 = constraintAnchor8.f1963d;
                int i5 = i2 + 1;
                boolean a3 = a(i5, constraintWidget3);
                if (constraintWidget3.o0() && a3) {
                    ConstraintWidgetContainer.Z1(i5, constraintWidget3, measurer, new BasicMeasure.Measure(), BasicMeasure.Measure.f2012k);
                }
                boolean z2 = (constraintAnchor8 == constraintWidget3.R && (constraintAnchor2 = constraintWidget3.T.f1965f) != null && constraintAnchor2.n()) || (constraintAnchor8 == constraintWidget3.T && (constraintAnchor = constraintWidget3.R.f1965f) != null && constraintAnchor.n());
                ConstraintWidget.DimensionBehaviour V2 = constraintWidget3.V();
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (V2 != dimensionBehaviour2 || a3) {
                    if (!constraintWidget3.o0()) {
                        ConstraintAnchor constraintAnchor9 = constraintWidget3.R;
                        if (constraintAnchor8 == constraintAnchor9 && constraintWidget3.T.f1965f == null) {
                            int f4 = constraintAnchor9.f() + e3;
                            constraintWidget3.N0(f4, constraintWidget3.z() + f4);
                            i(i5, constraintWidget3, measurer);
                        } else {
                            ConstraintAnchor constraintAnchor10 = constraintWidget3.T;
                            if (constraintAnchor8 == constraintAnchor10 && constraintAnchor9.f1965f == null) {
                                int f5 = e3 - constraintAnchor10.f();
                                constraintWidget3.N0(f5 - constraintWidget3.z(), f5);
                                i(i5, constraintWidget3, measurer);
                            } else if (z2 && !constraintWidget3.m0()) {
                                f(i5, measurer, constraintWidget3);
                            }
                        }
                    }
                } else if (constraintWidget3.V() == dimensionBehaviour2 && constraintWidget3.D >= 0 && constraintWidget3.C >= 0 && (constraintWidget3.X() == 8 || (constraintWidget3.x == 0 && constraintWidget3.x() == 0.0f))) {
                    if (!constraintWidget3.m0() && !constraintWidget3.n0() && z2 && !constraintWidget3.m0()) {
                        g(i5, constraintWidget, measurer, constraintWidget3);
                    }
                }
            }
        }
        ConstraintAnchor q4 = constraintWidget.q(ConstraintAnchor.Type.BASELINE);
        if (q4.d() != null && q4.n()) {
            int e4 = q4.e();
            Iterator it3 = q4.d().iterator();
            while (it3.hasNext()) {
                ConstraintAnchor constraintAnchor11 = (ConstraintAnchor) it3.next();
                ConstraintWidget constraintWidget4 = constraintAnchor11.f1963d;
                int i6 = i2 + 1;
                boolean a4 = a(i6, constraintWidget4);
                if (constraintWidget4.o0() && a4) {
                    ConstraintWidgetContainer.Z1(i6, constraintWidget4, measurer, new BasicMeasure.Measure(), BasicMeasure.Measure.f2012k);
                }
                if (constraintWidget4.V() != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || a4) {
                    if (!constraintWidget4.o0() && constraintAnchor11 == constraintWidget4.U) {
                        constraintWidget4.J0(constraintAnchor11.f() + e4);
                        i(i6, constraintWidget4, measurer);
                    }
                }
            }
        }
        constraintWidget.t0();
    }
}
