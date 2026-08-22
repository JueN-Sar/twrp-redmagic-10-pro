package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.Metrics;
import androidx.constraintlayout.core.widgets.Barrier;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.core.widgets.Guideline;
import androidx.constraintlayout.core.widgets.Helper;
import androidx.constraintlayout.core.widgets.Optimizer;
import androidx.constraintlayout.core.widgets.VirtualLayout;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class BasicMeasure {

    /* renamed from: a, reason: collision with root package name */
    private final ArrayList f2009a = new ArrayList();

    /* renamed from: b, reason: collision with root package name */
    private Measure f2010b = new Measure();

    /* renamed from: c, reason: collision with root package name */
    private ConstraintWidgetContainer f2011c;

    public static class Measure {

        /* renamed from: k, reason: collision with root package name */
        public static int f2012k = 0;

        /* renamed from: l, reason: collision with root package name */
        public static int f2013l = 1;

        /* renamed from: m, reason: collision with root package name */
        public static int f2014m = 2;

        /* renamed from: a, reason: collision with root package name */
        public ConstraintWidget.DimensionBehaviour f2015a;

        /* renamed from: b, reason: collision with root package name */
        public ConstraintWidget.DimensionBehaviour f2016b;

        /* renamed from: c, reason: collision with root package name */
        public int f2017c;

        /* renamed from: d, reason: collision with root package name */
        public int f2018d;

        /* renamed from: e, reason: collision with root package name */
        public int f2019e;

        /* renamed from: f, reason: collision with root package name */
        public int f2020f;

        /* renamed from: g, reason: collision with root package name */
        public int f2021g;

        /* renamed from: h, reason: collision with root package name */
        public boolean f2022h;

        /* renamed from: i, reason: collision with root package name */
        public boolean f2023i;

        /* renamed from: j, reason: collision with root package name */
        public int f2024j;
    }

    public interface Measurer {
        void a();

        void b(ConstraintWidget constraintWidget, Measure measure);
    }

    public BasicMeasure(ConstraintWidgetContainer constraintWidgetContainer) {
        this.f2011c = constraintWidgetContainer;
    }

    private boolean a(Measurer measurer, ConstraintWidget constraintWidget, int i2) {
        this.f2010b.f2015a = constraintWidget.C();
        this.f2010b.f2016b = constraintWidget.V();
        this.f2010b.f2017c = constraintWidget.Y();
        this.f2010b.f2018d = constraintWidget.z();
        Measure measure = this.f2010b;
        measure.f2023i = false;
        measure.f2024j = i2;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = measure.f2015a;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        boolean z = dimensionBehaviour == dimensionBehaviour2;
        boolean z2 = measure.f2016b == dimensionBehaviour2;
        boolean z3 = z && constraintWidget.f0 > 0.0f;
        boolean z4 = z2 && constraintWidget.f0 > 0.0f;
        if (z3 && constraintWidget.y[0] == 4) {
            measure.f2015a = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        if (z4 && constraintWidget.y[1] == 4) {
            measure.f2016b = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        measurer.b(constraintWidget, measure);
        constraintWidget.p1(this.f2010b.f2019e);
        constraintWidget.Q0(this.f2010b.f2020f);
        constraintWidget.P0(this.f2010b.f2022h);
        constraintWidget.F0(this.f2010b.f2021g);
        Measure measure2 = this.f2010b;
        measure2.f2024j = Measure.f2012k;
        return measure2.f2023i;
    }

    private void b(ConstraintWidgetContainer constraintWidgetContainer) {
        HorizontalWidgetRun horizontalWidgetRun;
        VerticalWidgetRun verticalWidgetRun;
        int size = constraintWidgetContainer.V0.size();
        boolean a2 = constraintWidgetContainer.a2(64);
        Measurer P1 = constraintWidgetContainer.P1();
        for (int i2 = 0; i2 < size; i2++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) constraintWidgetContainer.V0.get(i2);
            if (!(constraintWidget instanceof Guideline) && !(constraintWidget instanceof Barrier) && !constraintWidget.n0() && (!a2 || (horizontalWidgetRun = constraintWidget.f1973e) == null || (verticalWidgetRun = constraintWidget.f1974f) == null || !horizontalWidgetRun.f2083e.f2045j || !verticalWidgetRun.f2083e.f2045j)) {
                ConstraintWidget.DimensionBehaviour w = constraintWidget.w(0);
                ConstraintWidget.DimensionBehaviour w2 = constraintWidget.w(1);
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                boolean z = w == dimensionBehaviour && constraintWidget.w != 1 && w2 == dimensionBehaviour && constraintWidget.x != 1;
                if (!z && constraintWidgetContainer.a2(1) && !(constraintWidget instanceof VirtualLayout)) {
                    if (w == dimensionBehaviour && constraintWidget.w == 0 && w2 != dimensionBehaviour && !constraintWidget.k0()) {
                        z = true;
                    }
                    boolean z2 = (w2 != dimensionBehaviour || constraintWidget.x != 0 || w == dimensionBehaviour || constraintWidget.k0()) ? z : true;
                    if ((w != dimensionBehaviour && w2 != dimensionBehaviour) || constraintWidget.f0 <= 0.0f) {
                        z = z2;
                    }
                }
                if (!z) {
                    a(P1, constraintWidget, Measure.f2012k);
                    Metrics metrics = constraintWidgetContainer.b1;
                    if (metrics != null) {
                        metrics.f1494c++;
                    }
                }
            }
        }
        P1.a();
    }

    private void c(ConstraintWidgetContainer constraintWidgetContainer, String str, int i2, int i3, int i4) {
        long nanoTime = constraintWidgetContainer.b1 != null ? System.nanoTime() : 0L;
        int K = constraintWidgetContainer.K();
        int J = constraintWidgetContainer.J();
        constraintWidgetContainer.f1(0);
        constraintWidgetContainer.e1(0);
        constraintWidgetContainer.p1(i3);
        constraintWidgetContainer.Q0(i4);
        constraintWidgetContainer.f1(K);
        constraintWidgetContainer.e1(J);
        this.f2011c.e2(i2);
        this.f2011c.y1();
        if (constraintWidgetContainer.b1 != null) {
            long nanoTime2 = System.nanoTime();
            Metrics metrics = constraintWidgetContainer.b1;
            metrics.R++;
            metrics.f1493b += nanoTime2 - nanoTime;
        }
    }

    public long d(ConstraintWidgetContainer constraintWidgetContainer, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
        boolean z;
        int i11;
        long j2;
        int i12;
        boolean z2;
        boolean z3;
        int i13;
        int i14;
        int i15;
        boolean z4;
        Metrics metrics;
        BasicMeasure basicMeasure = this;
        Measurer P1 = constraintWidgetContainer.P1();
        int size = constraintWidgetContainer.V0.size();
        int Y = constraintWidgetContainer.Y();
        int z5 = constraintWidgetContainer.z();
        boolean b2 = Optimizer.b(i2, 128);
        boolean z6 = b2 || Optimizer.b(i2, 64);
        if (z6) {
            for (int i16 = 0; i16 < size; i16++) {
                ConstraintWidget constraintWidget = (ConstraintWidget) constraintWidgetContainer.V0.get(i16);
                ConstraintWidget.DimensionBehaviour C = constraintWidget.C();
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                boolean z7 = (C == dimensionBehaviour) && (constraintWidget.V() == dimensionBehaviour) && constraintWidget.x() > 0.0f;
                if ((constraintWidget.k0() && z7) || ((constraintWidget.m0() && z7) || (constraintWidget instanceof VirtualLayout) || constraintWidget.k0() || constraintWidget.m0())) {
                    z6 = false;
                    break;
                }
            }
        }
        if (z6 && (metrics = LinearSystem.x) != null) {
            metrics.f1496e++;
        }
        boolean z8 = z6 & ((i5 == 1073741824 && i7 == 1073741824) || b2);
        int i17 = 2;
        if (z8) {
            int min = Math.min(constraintWidgetContainer.I(), i6);
            int min2 = Math.min(constraintWidgetContainer.H(), i8);
            if (i5 == 1073741824 && constraintWidgetContainer.Y() != min) {
                constraintWidgetContainer.p1(min);
                constraintWidgetContainer.T1();
            }
            if (i7 == 1073741824 && constraintWidgetContainer.z() != min2) {
                constraintWidgetContainer.Q0(min2);
                constraintWidgetContainer.T1();
            }
            if (i5 == 1073741824 && i7 == 1073741824) {
                z = constraintWidgetContainer.L1(b2);
                i11 = 2;
            } else {
                boolean M1 = constraintWidgetContainer.M1(b2);
                if (i5 == 1073741824) {
                    M1 &= constraintWidgetContainer.N1(b2, 0);
                    i11 = 1;
                } else {
                    i11 = 0;
                }
                if (i7 == 1073741824) {
                    z = constraintWidgetContainer.N1(b2, 1) & M1;
                    i11++;
                } else {
                    z = M1;
                }
            }
            if (z) {
                constraintWidgetContainer.u1(i5 == 1073741824, i7 == 1073741824);
            }
        } else {
            z = false;
            i11 = 0;
        }
        if (!z || i11 != 2) {
            int Q1 = constraintWidgetContainer.Q1();
            if (size > 0) {
                b(constraintWidgetContainer);
            }
            r3 = constraintWidgetContainer.b1 != null ? System.nanoTime() : 0L;
            e(constraintWidgetContainer);
            int size2 = basicMeasure.f2009a.size();
            if (size > 0) {
                c(constraintWidgetContainer, "First pass", 0, Y, z5);
            }
            if (size2 > 0) {
                ConstraintWidget.DimensionBehaviour C2 = constraintWidgetContainer.C();
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                boolean z9 = C2 == dimensionBehaviour2;
                boolean z10 = constraintWidgetContainer.V() == dimensionBehaviour2;
                int max = Math.max(constraintWidgetContainer.Y(), basicMeasure.f2011c.K());
                int max2 = Math.max(constraintWidgetContainer.z(), basicMeasure.f2011c.J());
                int i18 = 0;
                boolean z11 = false;
                while (i18 < size2) {
                    ConstraintWidget constraintWidget2 = (ConstraintWidget) basicMeasure.f2009a.get(i18);
                    long j3 = r3;
                    if (constraintWidget2 instanceof VirtualLayout) {
                        int Y2 = constraintWidget2.Y();
                        int z12 = constraintWidget2.z();
                        i13 = Q1;
                        boolean a2 = z11 | basicMeasure.a(P1, constraintWidget2, Measure.f2013l);
                        Metrics metrics2 = constraintWidgetContainer.b1;
                        i14 = Y;
                        i15 = z5;
                        if (metrics2 != null) {
                            metrics2.f1495d++;
                        }
                        int Y3 = constraintWidget2.Y();
                        int z13 = constraintWidget2.z();
                        if (Y3 != Y2) {
                            constraintWidget2.p1(Y3);
                            if (z9 && constraintWidget2.O() > max) {
                                max = Math.max(max, constraintWidget2.O() + constraintWidget2.q(ConstraintAnchor.Type.RIGHT).f());
                            }
                            z4 = true;
                        } else {
                            z4 = a2;
                        }
                        if (z13 != z12) {
                            constraintWidget2.Q0(z13);
                            if (z10 && constraintWidget2.t() > max2) {
                                max2 = Math.max(max2, constraintWidget2.t() + constraintWidget2.q(ConstraintAnchor.Type.BOTTOM).f());
                            }
                            z4 = true;
                        }
                        z11 = z4 | ((VirtualLayout) constraintWidget2).K1();
                    } else {
                        i14 = Y;
                        i15 = z5;
                        i13 = Q1;
                    }
                    i18++;
                    Q1 = i13;
                    r3 = j3;
                    Y = i14;
                    z5 = i15;
                    i17 = 2;
                }
                j2 = r3;
                int i19 = Y;
                int i20 = z5;
                int i21 = Q1;
                int i22 = i17;
                int i23 = 0;
                while (i23 < i22) {
                    int i24 = 0;
                    while (i24 < size2) {
                        ConstraintWidget constraintWidget3 = (ConstraintWidget) basicMeasure.f2009a.get(i24);
                        if (((constraintWidget3 instanceof Helper) && !(constraintWidget3 instanceof VirtualLayout)) || (constraintWidget3 instanceof Guideline) || constraintWidget3.X() == 8 || ((z8 && constraintWidget3.f1973e.f2083e.f2045j && constraintWidget3.f1974f.f2083e.f2045j) || (constraintWidget3 instanceof VirtualLayout))) {
                            z2 = z8;
                        } else {
                            int Y4 = constraintWidget3.Y();
                            int z14 = constraintWidget3.z();
                            int r2 = constraintWidget3.r();
                            int i25 = Measure.f2013l;
                            if (i23 == 1) {
                                i25 = Measure.f2014m;
                            }
                            boolean a3 = z11 | basicMeasure.a(P1, constraintWidget3, i25);
                            Metrics metrics3 = constraintWidgetContainer.b1;
                            z2 = z8;
                            if (metrics3 != null) {
                                metrics3.f1495d++;
                            }
                            int Y5 = constraintWidget3.Y();
                            int z15 = constraintWidget3.z();
                            if (Y5 != Y4) {
                                constraintWidget3.p1(Y5);
                                if (z9 && constraintWidget3.O() > max) {
                                    max = Math.max(max, constraintWidget3.O() + constraintWidget3.q(ConstraintAnchor.Type.RIGHT).f());
                                }
                                z3 = true;
                            } else {
                                z3 = a3;
                            }
                            if (z15 != z14) {
                                constraintWidget3.Q0(z15);
                                if (z10 && constraintWidget3.t() > max2) {
                                    max2 = Math.max(max2, constraintWidget3.t() + constraintWidget3.q(ConstraintAnchor.Type.BOTTOM).f());
                                }
                                z3 = true;
                            }
                            z11 = (!constraintWidget3.b0() || r2 == constraintWidget3.r()) ? z3 : true;
                        }
                        i24++;
                        basicMeasure = this;
                        z8 = z2;
                    }
                    boolean z16 = z8;
                    if (!z11) {
                        break;
                    }
                    i23++;
                    c(constraintWidgetContainer, "intermediate pass", i23, i19, i20);
                    i22 = 2;
                    z11 = false;
                    basicMeasure = this;
                    z8 = z16;
                }
                i12 = i21;
            } else {
                j2 = r3;
                i12 = Q1;
            }
            constraintWidgetContainer.d2(i12);
            r3 = j2;
        }
        return constraintWidgetContainer.b1 != null ? System.nanoTime() - r3 : r3;
    }

    public void e(ConstraintWidgetContainer constraintWidgetContainer) {
        this.f2009a.clear();
        int size = constraintWidgetContainer.V0.size();
        for (int i2 = 0; i2 < size; i2++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) constraintWidgetContainer.V0.get(i2);
            ConstraintWidget.DimensionBehaviour C = constraintWidget.C();
            ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
            if (C == dimensionBehaviour || constraintWidget.V() == dimensionBehaviour) {
                this.f2009a.add(constraintWidget);
            }
        }
        constraintWidgetContainer.T1();
    }
}
