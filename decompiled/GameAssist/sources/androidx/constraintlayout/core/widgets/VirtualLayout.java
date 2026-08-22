package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import java.util.HashSet;

/* loaded from: classes.dex */
public class VirtualLayout extends HelperWidget {
    private int X0 = 0;
    private int Y0 = 0;
    private int Z0 = 0;
    private int a1 = 0;
    private int b1 = 0;
    private int c1 = 0;
    private int d1 = 0;
    private int e1 = 0;
    private boolean f1 = false;
    private int g1 = 0;
    private int h1 = 0;
    protected BasicMeasure.Measure i1 = new BasicMeasure.Measure();
    BasicMeasure.Measurer j1 = null;

    public boolean A1(HashSet hashSet) {
        for (int i2 = 0; i2 < this.W0; i2++) {
            if (hashSet.contains(this.V0[i2])) {
                return true;
            }
        }
        return false;
    }

    public int B1() {
        return this.h1;
    }

    public int C1() {
        return this.g1;
    }

    public int D1() {
        return this.Y0;
    }

    public int E1() {
        return this.d1;
    }

    public int F1() {
        return this.e1;
    }

    public int G1() {
        return this.X0;
    }

    public void H1(int i2, int i3, int i4, int i5) {
    }

    protected void I1(ConstraintWidget constraintWidget, ConstraintWidget.DimensionBehaviour dimensionBehaviour, int i2, ConstraintWidget.DimensionBehaviour dimensionBehaviour2, int i3) {
        while (this.j1 == null && M() != null) {
            this.j1 = ((ConstraintWidgetContainer) M()).P1();
        }
        BasicMeasure.Measure measure = this.i1;
        measure.f2015a = dimensionBehaviour;
        measure.f2016b = dimensionBehaviour2;
        measure.f2017c = i2;
        measure.f2018d = i3;
        this.j1.b(constraintWidget, measure);
        constraintWidget.p1(this.i1.f2019e);
        constraintWidget.Q0(this.i1.f2020f);
        constraintWidget.P0(this.i1.f2022h);
        constraintWidget.F0(this.i1.f2021g);
    }

    protected boolean J1() {
        ConstraintWidget constraintWidget = this.c0;
        BasicMeasure.Measurer P1 = constraintWidget != null ? ((ConstraintWidgetContainer) constraintWidget).P1() : null;
        if (P1 == null) {
            return false;
        }
        for (int i2 = 0; i2 < this.W0; i2++) {
            ConstraintWidget constraintWidget2 = this.V0[i2];
            if (constraintWidget2 != null && !(constraintWidget2 instanceof Guideline)) {
                ConstraintWidget.DimensionBehaviour w = constraintWidget2.w(0);
                ConstraintWidget.DimensionBehaviour w2 = constraintWidget2.w(1);
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (w != dimensionBehaviour || constraintWidget2.w == 1 || w2 != dimensionBehaviour || constraintWidget2.x == 1) {
                    if (w == dimensionBehaviour) {
                        w = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    }
                    if (w2 == dimensionBehaviour) {
                        w2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    }
                    BasicMeasure.Measure measure = this.i1;
                    measure.f2015a = w;
                    measure.f2016b = w2;
                    measure.f2017c = constraintWidget2.Y();
                    this.i1.f2018d = constraintWidget2.z();
                    P1.b(constraintWidget2, this.i1);
                    constraintWidget2.p1(this.i1.f2019e);
                    constraintWidget2.Q0(this.i1.f2020f);
                    constraintWidget2.F0(this.i1.f2021g);
                }
            }
        }
        return true;
    }

    public boolean K1() {
        return this.f1;
    }

    protected void L1(boolean z) {
        this.f1 = z;
    }

    public void M1(int i2, int i3) {
        this.g1 = i2;
        this.h1 = i3;
    }

    public void N1(int i2) {
        this.Z0 = i2;
        this.X0 = i2;
        this.a1 = i2;
        this.Y0 = i2;
        this.b1 = i2;
        this.c1 = i2;
    }

    public void O1(int i2) {
        this.Y0 = i2;
    }

    public void P1(int i2) {
        this.c1 = i2;
    }

    public void Q1(int i2) {
        this.Z0 = i2;
        this.d1 = i2;
    }

    public void R1(int i2) {
        this.a1 = i2;
        this.e1 = i2;
    }

    public void S1(int i2) {
        this.b1 = i2;
        this.d1 = i2;
        this.e1 = i2;
    }

    public void T1(int i2) {
        this.X0 = i2;
    }

    @Override // androidx.constraintlayout.core.widgets.HelperWidget, androidx.constraintlayout.core.widgets.Helper
    public void c(ConstraintWidgetContainer constraintWidgetContainer) {
        z1();
    }

    public void y1(boolean z) {
        int i2 = this.b1;
        if (i2 > 0 || this.c1 > 0) {
            if (z) {
                this.d1 = this.c1;
                this.e1 = i2;
            } else {
                this.d1 = i2;
                this.e1 = this.c1;
            }
        }
    }

    public void z1() {
        for (int i2 = 0; i2 < this.W0; i2++) {
            ConstraintWidget constraintWidget = this.V0[i2];
            if (constraintWidget != null) {
                constraintWidget.Z0(true);
            }
        }
    }
}
