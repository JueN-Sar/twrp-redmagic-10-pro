package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.Metrics;
import androidx.constraintlayout.core.SolverVariable;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.constraintlayout.core.widgets.analyzer.DependencyGraph;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: classes.dex */
public class ConstraintWidgetContainer extends WidgetContainer {
    private int Y0;
    public Metrics b1;
    int d1;
    int e1;
    int f1;
    int g1;
    BasicMeasure W0 = new BasicMeasure(this);
    public DependencyGraph X0 = new DependencyGraph(this);
    protected BasicMeasure.Measurer Z0 = null;
    private boolean a1 = false;
    protected LinearSystem c1 = new LinearSystem();
    public int h1 = 0;
    public int i1 = 0;
    ChainHead[] j1 = new ChainHead[4];
    ChainHead[] k1 = new ChainHead[4];
    public boolean l1 = false;
    public boolean m1 = false;
    public boolean n1 = false;
    public int o1 = 0;
    public int p1 = 0;
    private int q1 = 257;
    public boolean r1 = false;
    private boolean s1 = false;
    private boolean t1 = false;
    int u1 = 0;
    private WeakReference v1 = null;
    private WeakReference w1 = null;
    private WeakReference x1 = null;
    private WeakReference y1 = null;
    HashSet z1 = new HashSet();
    public BasicMeasure.Measure A1 = new BasicMeasure.Measure();

    private void D1(ConstraintWidget constraintWidget) {
        int i2 = this.h1 + 1;
        ChainHead[] chainHeadArr = this.k1;
        if (i2 >= chainHeadArr.length) {
            this.k1 = (ChainHead[]) Arrays.copyOf(chainHeadArr, chainHeadArr.length * 2);
        }
        this.k1[this.h1] = new ChainHead(constraintWidget, 0, W1());
        this.h1++;
    }

    private void G1(ConstraintAnchor constraintAnchor, SolverVariable solverVariable) {
        this.c1.h(solverVariable, this.c1.q(constraintAnchor), 0, 5);
    }

    private void H1(ConstraintAnchor constraintAnchor, SolverVariable solverVariable) {
        this.c1.h(this.c1.q(constraintAnchor), solverVariable, 0, 5);
    }

    private void I1(ConstraintWidget constraintWidget) {
        int i2 = this.i1 + 1;
        ChainHead[] chainHeadArr = this.j1;
        if (i2 >= chainHeadArr.length) {
            this.j1 = (ChainHead[]) Arrays.copyOf(chainHeadArr, chainHeadArr.length * 2);
        }
        this.j1[this.i1] = new ChainHead(constraintWidget, 1, W1());
        this.i1++;
    }

    public static boolean Z1(int i2, ConstraintWidget constraintWidget, BasicMeasure.Measurer measurer, BasicMeasure.Measure measure, int i3) {
        int i4;
        int i5;
        if (measurer == null) {
            return false;
        }
        if (constraintWidget.X() == 8 || (constraintWidget instanceof Guideline) || (constraintWidget instanceof Barrier)) {
            measure.f2019e = 0;
            measure.f2020f = 0;
            return false;
        }
        measure.f2015a = constraintWidget.C();
        measure.f2016b = constraintWidget.V();
        measure.f2017c = constraintWidget.Y();
        measure.f2018d = constraintWidget.z();
        measure.f2023i = false;
        measure.f2024j = i3;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = measure.f2015a;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        boolean z = dimensionBehaviour == dimensionBehaviour2;
        boolean z2 = measure.f2016b == dimensionBehaviour2;
        boolean z3 = z && constraintWidget.f0 > 0.0f;
        boolean z4 = z2 && constraintWidget.f0 > 0.0f;
        if (z && constraintWidget.c0(0) && constraintWidget.w == 0 && !z3) {
            measure.f2015a = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            if (z2 && constraintWidget.x == 0) {
                measure.f2015a = ConstraintWidget.DimensionBehaviour.FIXED;
            }
            z = false;
        }
        if (z2 && constraintWidget.c0(1) && constraintWidget.x == 0 && !z4) {
            measure.f2016b = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            if (z && constraintWidget.w == 0) {
                measure.f2016b = ConstraintWidget.DimensionBehaviour.FIXED;
            }
            z2 = false;
        }
        if (constraintWidget.p0()) {
            measure.f2015a = ConstraintWidget.DimensionBehaviour.FIXED;
            z = false;
        }
        if (constraintWidget.q0()) {
            measure.f2016b = ConstraintWidget.DimensionBehaviour.FIXED;
            z2 = false;
        }
        if (z3) {
            if (constraintWidget.y[0] == 4) {
                measure.f2015a = ConstraintWidget.DimensionBehaviour.FIXED;
            } else if (!z2) {
                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = measure.f2016b;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = ConstraintWidget.DimensionBehaviour.FIXED;
                if (dimensionBehaviour3 == dimensionBehaviour4) {
                    i5 = measure.f2018d;
                } else {
                    measure.f2015a = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    measurer.b(constraintWidget, measure);
                    i5 = measure.f2020f;
                }
                measure.f2015a = dimensionBehaviour4;
                measure.f2017c = (int) (constraintWidget.x() * i5);
            }
        }
        if (z4) {
            if (constraintWidget.y[1] == 4) {
                measure.f2016b = ConstraintWidget.DimensionBehaviour.FIXED;
            } else if (!z) {
                ConstraintWidget.DimensionBehaviour dimensionBehaviour5 = measure.f2015a;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour6 = ConstraintWidget.DimensionBehaviour.FIXED;
                if (dimensionBehaviour5 == dimensionBehaviour6) {
                    i4 = measure.f2017c;
                } else {
                    measure.f2016b = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    measurer.b(constraintWidget, measure);
                    i4 = measure.f2019e;
                }
                measure.f2016b = dimensionBehaviour6;
                if (constraintWidget.y() == -1) {
                    measure.f2018d = (int) (i4 / constraintWidget.x());
                } else {
                    measure.f2018d = (int) (constraintWidget.x() * i4);
                }
            }
        }
        measurer.b(constraintWidget, measure);
        constraintWidget.p1(measure.f2019e);
        constraintWidget.Q0(measure.f2020f);
        constraintWidget.P0(measure.f2022h);
        constraintWidget.F0(measure.f2021g);
        measure.f2024j = BasicMeasure.Measure.f2012k;
        return measure.f2023i;
    }

    private void b2() {
        this.h1 = 0;
        this.i1 = 0;
    }

    void B1(ConstraintWidget constraintWidget, int i2) {
        if (i2 == 0) {
            D1(constraintWidget);
        } else if (i2 == 1) {
            I1(constraintWidget);
        }
    }

    public boolean C1(LinearSystem linearSystem) {
        boolean a2 = a2(64);
        g(linearSystem, a2);
        int size = this.V0.size();
        boolean z = false;
        for (int i2 = 0; i2 < size; i2++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.V0.get(i2);
            constraintWidget.X0(0, false);
            constraintWidget.X0(1, false);
            if (constraintWidget instanceof Barrier) {
                z = true;
            }
        }
        if (z) {
            for (int i3 = 0; i3 < size; i3++) {
                ConstraintWidget constraintWidget2 = (ConstraintWidget) this.V0.get(i3);
                if (constraintWidget2 instanceof Barrier) {
                    ((Barrier) constraintWidget2).D1();
                }
            }
        }
        this.z1.clear();
        for (int i4 = 0; i4 < size; i4++) {
            ConstraintWidget constraintWidget3 = (ConstraintWidget) this.V0.get(i4);
            if (constraintWidget3.f()) {
                if (constraintWidget3 instanceof VirtualLayout) {
                    this.z1.add(constraintWidget3);
                } else {
                    constraintWidget3.g(linearSystem, a2);
                }
            }
        }
        while (this.z1.size() > 0) {
            int size2 = this.z1.size();
            Iterator it = this.z1.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                VirtualLayout virtualLayout = (VirtualLayout) ((ConstraintWidget) it.next());
                if (virtualLayout.A1(this.z1)) {
                    virtualLayout.g(linearSystem, a2);
                    this.z1.remove(virtualLayout);
                    break;
                }
            }
            if (size2 == this.z1.size()) {
                Iterator it2 = this.z1.iterator();
                while (it2.hasNext()) {
                    ((ConstraintWidget) it2.next()).g(linearSystem, a2);
                }
                this.z1.clear();
            }
        }
        if (LinearSystem.f1473s) {
            HashSet hashSet = new HashSet();
            for (int i5 = 0; i5 < size; i5++) {
                ConstraintWidget constraintWidget4 = (ConstraintWidget) this.V0.get(i5);
                if (!constraintWidget4.f()) {
                    hashSet.add(constraintWidget4);
                }
            }
            e(this, linearSystem, hashSet, C() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT ? 0 : 1, false);
            Iterator it3 = hashSet.iterator();
            while (it3.hasNext()) {
                ConstraintWidget constraintWidget5 = (ConstraintWidget) it3.next();
                Optimizer.a(this, linearSystem, constraintWidget5);
                constraintWidget5.g(linearSystem, a2);
            }
        } else {
            for (int i6 = 0; i6 < size; i6++) {
                ConstraintWidget constraintWidget6 = (ConstraintWidget) this.V0.get(i6);
                if (constraintWidget6 instanceof ConstraintWidgetContainer) {
                    ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = constraintWidget6.b0;
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour = dimensionBehaviourArr[0];
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = dimensionBehaviourArr[1];
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    if (dimensionBehaviour == dimensionBehaviour3) {
                        constraintWidget6.U0(ConstraintWidget.DimensionBehaviour.FIXED);
                    }
                    if (dimensionBehaviour2 == dimensionBehaviour3) {
                        constraintWidget6.l1(ConstraintWidget.DimensionBehaviour.FIXED);
                    }
                    constraintWidget6.g(linearSystem, a2);
                    if (dimensionBehaviour == dimensionBehaviour3) {
                        constraintWidget6.U0(dimensionBehaviour);
                    }
                    if (dimensionBehaviour2 == dimensionBehaviour3) {
                        constraintWidget6.l1(dimensionBehaviour2);
                    }
                } else {
                    Optimizer.a(this, linearSystem, constraintWidget6);
                    if (!constraintWidget6.f()) {
                        constraintWidget6.g(linearSystem, a2);
                    }
                }
            }
        }
        if (this.h1 > 0) {
            Chain.b(this, linearSystem, null, 0);
        }
        if (this.i1 > 0) {
            Chain.b(this, linearSystem, null, 1);
        }
        return true;
    }

    public void E1(ConstraintAnchor constraintAnchor) {
        WeakReference weakReference = this.y1;
        if (weakReference == null || weakReference.get() == null || constraintAnchor.e() > ((ConstraintAnchor) this.y1.get()).e()) {
            this.y1 = new WeakReference(constraintAnchor);
        }
    }

    public void F1(ConstraintAnchor constraintAnchor) {
        WeakReference weakReference = this.w1;
        if (weakReference == null || weakReference.get() == null || constraintAnchor.e() > ((ConstraintAnchor) this.w1.get()).e()) {
            this.w1 = new WeakReference(constraintAnchor);
        }
    }

    void J1(ConstraintAnchor constraintAnchor) {
        WeakReference weakReference = this.x1;
        if (weakReference == null || weakReference.get() == null || constraintAnchor.e() > ((ConstraintAnchor) this.x1.get()).e()) {
            this.x1 = new WeakReference(constraintAnchor);
        }
    }

    void K1(ConstraintAnchor constraintAnchor) {
        WeakReference weakReference = this.v1;
        if (weakReference == null || weakReference.get() == null || constraintAnchor.e() > ((ConstraintAnchor) this.v1.get()).e()) {
            this.v1 = new WeakReference(constraintAnchor);
        }
    }

    public boolean L1(boolean z) {
        return this.X0.f(z);
    }

    public boolean M1(boolean z) {
        return this.X0.g(z);
    }

    public boolean N1(boolean z, int i2) {
        return this.X0.h(z, i2);
    }

    public void O1(Metrics metrics) {
        this.b1 = metrics;
        this.c1.v(metrics);
    }

    public BasicMeasure.Measurer P1() {
        return this.Z0;
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public void Q(StringBuilder sb) {
        sb.append(this.f1983o + ":{\n");
        sb.append("  actualWidth:" + this.d0);
        sb.append("\n");
        sb.append("  actualHeight:" + this.e0);
        sb.append("\n");
        Iterator it = x1().iterator();
        while (it.hasNext()) {
            ((ConstraintWidget) it.next()).Q(sb);
            sb.append(",\n");
        }
        sb.append("}");
    }

    public int Q1() {
        return this.q1;
    }

    public LinearSystem R1() {
        return this.c1;
    }

    public boolean S1() {
        return false;
    }

    public void T1() {
        this.X0.j();
    }

    public void U1() {
        this.X0.k();
    }

    public boolean V1() {
        return this.t1;
    }

    public boolean W1() {
        return this.a1;
    }

    public boolean X1() {
        return this.s1;
    }

    public long Y1(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
        this.d1 = i9;
        this.e1 = i10;
        return this.W0.d(this, i2, i9, i10, i3, i4, i5, i6, i7, i8);
    }

    public boolean a2(int i2) {
        return (this.q1 & i2) == i2;
    }

    public void c2(BasicMeasure.Measurer measurer) {
        this.Z0 = measurer;
        this.X0.n(measurer);
    }

    public void d2(int i2) {
        this.q1 = i2;
        LinearSystem.f1473s = a2(512);
    }

    public void e2(int i2) {
        this.Y0 = i2;
    }

    public void f2(boolean z) {
        this.a1 = z;
    }

    public boolean g2(LinearSystem linearSystem, boolean[] zArr) {
        zArr[2] = false;
        boolean a2 = a2(64);
        v1(linearSystem, a2);
        int size = this.V0.size();
        boolean z = false;
        for (int i2 = 0; i2 < size; i2++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.V0.get(i2);
            constraintWidget.v1(linearSystem, a2);
            if (constraintWidget.e0()) {
                z = true;
            }
        }
        return z;
    }

    public void h2() {
        this.W0.e(this);
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public void u1(boolean z, boolean z2) {
        super.u1(z, z2);
        int size = this.V0.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((ConstraintWidget) this.V0.get(i2)).u1(z, z2);
        }
    }

    @Override // androidx.constraintlayout.core.widgets.WidgetContainer, androidx.constraintlayout.core.widgets.ConstraintWidget
    public void v0() {
        this.c1.G();
        this.d1 = 0;
        this.f1 = 0;
        this.e1 = 0;
        this.g1 = 0;
        this.r1 = false;
        super.v0();
    }

    /* JADX WARN: Removed duplicated region for block: B:152:0x0319  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x031b  */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v4, types: [boolean] */
    /* JADX WARN: Type inference failed for: r6v6 */
    @Override // androidx.constraintlayout.core.widgets.WidgetContainer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void y1() {
        /*
            Method dump skipped, instructions count: 824
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.ConstraintWidgetContainer.y1():void");
    }
}
