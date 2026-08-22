package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.core.widgets.Guideline;
import androidx.constraintlayout.core.widgets.HelperWidget;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: classes.dex */
public class DependencyGraph {

    /* renamed from: a, reason: collision with root package name */
    private ConstraintWidgetContainer f2027a;

    /* renamed from: d, reason: collision with root package name */
    private ConstraintWidgetContainer f2030d;

    /* renamed from: b, reason: collision with root package name */
    private boolean f2028b = true;

    /* renamed from: c, reason: collision with root package name */
    private boolean f2029c = true;

    /* renamed from: e, reason: collision with root package name */
    private ArrayList f2031e = new ArrayList();

    /* renamed from: f, reason: collision with root package name */
    private ArrayList f2032f = new ArrayList();

    /* renamed from: g, reason: collision with root package name */
    private BasicMeasure.Measurer f2033g = null;

    /* renamed from: h, reason: collision with root package name */
    private BasicMeasure.Measure f2034h = new BasicMeasure.Measure();

    /* renamed from: i, reason: collision with root package name */
    ArrayList f2035i = new ArrayList();

    public DependencyGraph(ConstraintWidgetContainer constraintWidgetContainer) {
        this.f2027a = constraintWidgetContainer;
        this.f2030d = constraintWidgetContainer;
    }

    private void a(DependencyNode dependencyNode, int i2, int i3, DependencyNode dependencyNode2, ArrayList arrayList, RunGroup runGroup) {
        WidgetRun widgetRun = dependencyNode.f2039d;
        if (widgetRun.f2081c == null) {
            ConstraintWidgetContainer constraintWidgetContainer = this.f2027a;
            if (widgetRun == constraintWidgetContainer.f1973e || widgetRun == constraintWidgetContainer.f1974f) {
                return;
            }
            if (runGroup == null) {
                runGroup = new RunGroup(widgetRun, i3);
                arrayList.add(runGroup);
            }
            widgetRun.f2081c = runGroup;
            runGroup.a(widgetRun);
            for (Dependency dependency : widgetRun.f2086h.f2046k) {
                if (dependency instanceof DependencyNode) {
                    a((DependencyNode) dependency, i2, 0, dependencyNode2, arrayList, runGroup);
                }
            }
            for (Dependency dependency2 : widgetRun.f2087i.f2046k) {
                if (dependency2 instanceof DependencyNode) {
                    a((DependencyNode) dependency2, i2, 1, dependencyNode2, arrayList, runGroup);
                }
            }
            if (i2 == 1 && (widgetRun instanceof VerticalWidgetRun)) {
                for (Dependency dependency3 : ((VerticalWidgetRun) widgetRun).f2062k.f2046k) {
                    if (dependency3 instanceof DependencyNode) {
                        a((DependencyNode) dependency3, i2, 2, dependencyNode2, arrayList, runGroup);
                    }
                }
            }
            for (DependencyNode dependencyNode3 : widgetRun.f2086h.f2047l) {
                if (dependencyNode3 == dependencyNode2) {
                    runGroup.f2056b = true;
                }
                a(dependencyNode3, i2, 0, dependencyNode2, arrayList, runGroup);
            }
            for (DependencyNode dependencyNode4 : widgetRun.f2087i.f2047l) {
                if (dependencyNode4 == dependencyNode2) {
                    runGroup.f2056b = true;
                }
                a(dependencyNode4, i2, 1, dependencyNode2, arrayList, runGroup);
            }
            if (i2 == 1 && (widgetRun instanceof VerticalWidgetRun)) {
                Iterator it = ((VerticalWidgetRun) widgetRun).f2062k.f2047l.iterator();
                while (it.hasNext()) {
                    a((DependencyNode) it.next(), i2, 2, dependencyNode2, arrayList, runGroup);
                }
            }
        }
    }

    private boolean b(ConstraintWidgetContainer constraintWidgetContainer) {
        int i2;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour;
        int i3;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour3;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour4;
        Iterator it = constraintWidgetContainer.V0.iterator();
        while (it.hasNext()) {
            ConstraintWidget constraintWidget = (ConstraintWidget) it.next();
            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = constraintWidget.b0;
            ConstraintWidget.DimensionBehaviour dimensionBehaviour5 = dimensionBehaviourArr[0];
            ConstraintWidget.DimensionBehaviour dimensionBehaviour6 = dimensionBehaviourArr[1];
            if (constraintWidget.X() == 8) {
                constraintWidget.f1969a = true;
            } else {
                if (constraintWidget.B < 1.0f && dimensionBehaviour5 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    constraintWidget.w = 2;
                }
                if (constraintWidget.E < 1.0f && dimensionBehaviour6 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    constraintWidget.x = 2;
                }
                if (constraintWidget.x() > 0.0f) {
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour7 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                    if (dimensionBehaviour5 == dimensionBehaviour7 && (dimensionBehaviour6 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || dimensionBehaviour6 == ConstraintWidget.DimensionBehaviour.FIXED)) {
                        constraintWidget.w = 3;
                    } else if (dimensionBehaviour6 == dimensionBehaviour7 && (dimensionBehaviour5 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || dimensionBehaviour5 == ConstraintWidget.DimensionBehaviour.FIXED)) {
                        constraintWidget.x = 3;
                    } else if (dimensionBehaviour5 == dimensionBehaviour7 && dimensionBehaviour6 == dimensionBehaviour7) {
                        if (constraintWidget.w == 0) {
                            constraintWidget.w = 3;
                        }
                        if (constraintWidget.x == 0) {
                            constraintWidget.x = 3;
                        }
                    }
                }
                ConstraintWidget.DimensionBehaviour dimensionBehaviour8 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (dimensionBehaviour5 == dimensionBehaviour8 && constraintWidget.w == 1 && (constraintWidget.Q.f1965f == null || constraintWidget.S.f1965f == null)) {
                    dimensionBehaviour5 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                }
                ConstraintWidget.DimensionBehaviour dimensionBehaviour9 = dimensionBehaviour5;
                if (dimensionBehaviour6 == dimensionBehaviour8 && constraintWidget.x == 1 && (constraintWidget.R.f1965f == null || constraintWidget.T.f1965f == null)) {
                    dimensionBehaviour6 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                }
                ConstraintWidget.DimensionBehaviour dimensionBehaviour10 = dimensionBehaviour6;
                HorizontalWidgetRun horizontalWidgetRun = constraintWidget.f1973e;
                horizontalWidgetRun.f2082d = dimensionBehaviour9;
                int i4 = constraintWidget.w;
                horizontalWidgetRun.f2079a = i4;
                VerticalWidgetRun verticalWidgetRun = constraintWidget.f1974f;
                verticalWidgetRun.f2082d = dimensionBehaviour10;
                int i5 = constraintWidget.x;
                verticalWidgetRun.f2079a = i5;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour11 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
                if ((dimensionBehaviour9 == dimensionBehaviour11 || dimensionBehaviour9 == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviour9 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) && (dimensionBehaviour10 == dimensionBehaviour11 || dimensionBehaviour10 == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviour10 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT)) {
                    int Y = constraintWidget.Y();
                    if (dimensionBehaviour9 == dimensionBehaviour11) {
                        i2 = (constraintWidgetContainer.Y() - constraintWidget.Q.f1966g) - constraintWidget.S.f1966g;
                        dimensionBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
                    } else {
                        i2 = Y;
                        dimensionBehaviour = dimensionBehaviour9;
                    }
                    int z = constraintWidget.z();
                    if (dimensionBehaviour10 == dimensionBehaviour11) {
                        i3 = (constraintWidgetContainer.z() - constraintWidget.R.f1966g) - constraintWidget.T.f1966g;
                        dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.FIXED;
                    } else {
                        i3 = z;
                        dimensionBehaviour2 = dimensionBehaviour10;
                    }
                    l(constraintWidget, dimensionBehaviour, i2, dimensionBehaviour2, i3);
                    constraintWidget.f1973e.f2083e.d(constraintWidget.Y());
                    constraintWidget.f1974f.f2083e.d(constraintWidget.z());
                    constraintWidget.f1969a = true;
                } else {
                    if (dimensionBehaviour9 == dimensionBehaviour8 && (dimensionBehaviour10 == (dimensionBehaviour4 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) || dimensionBehaviour10 == ConstraintWidget.DimensionBehaviour.FIXED)) {
                        if (i4 == 3) {
                            if (dimensionBehaviour10 == dimensionBehaviour4) {
                                l(constraintWidget, dimensionBehaviour4, 0, dimensionBehaviour4, 0);
                            }
                            int z2 = constraintWidget.z();
                            int i6 = (int) ((z2 * constraintWidget.f0) + 0.5f);
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour12 = ConstraintWidget.DimensionBehaviour.FIXED;
                            l(constraintWidget, dimensionBehaviour12, i6, dimensionBehaviour12, z2);
                            constraintWidget.f1973e.f2083e.d(constraintWidget.Y());
                            constraintWidget.f1974f.f2083e.d(constraintWidget.z());
                            constraintWidget.f1969a = true;
                        } else if (i4 == 1) {
                            l(constraintWidget, dimensionBehaviour4, 0, dimensionBehaviour10, 0);
                            constraintWidget.f1973e.f2083e.f2048m = constraintWidget.Y();
                        } else if (i4 == 2) {
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour13 = constraintWidgetContainer.b0[0];
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour14 = ConstraintWidget.DimensionBehaviour.FIXED;
                            if (dimensionBehaviour13 == dimensionBehaviour14 || dimensionBehaviour13 == dimensionBehaviour11) {
                                l(constraintWidget, dimensionBehaviour14, (int) ((constraintWidget.B * constraintWidgetContainer.Y()) + 0.5f), dimensionBehaviour10, constraintWidget.z());
                                constraintWidget.f1973e.f2083e.d(constraintWidget.Y());
                                constraintWidget.f1974f.f2083e.d(constraintWidget.z());
                                constraintWidget.f1969a = true;
                            }
                        } else {
                            ConstraintAnchor[] constraintAnchorArr = constraintWidget.Y;
                            if (constraintAnchorArr[0].f1965f == null || constraintAnchorArr[1].f1965f == null) {
                                l(constraintWidget, dimensionBehaviour4, 0, dimensionBehaviour10, 0);
                                constraintWidget.f1973e.f2083e.d(constraintWidget.Y());
                                constraintWidget.f1974f.f2083e.d(constraintWidget.z());
                                constraintWidget.f1969a = true;
                            }
                        }
                    }
                    if (dimensionBehaviour10 == dimensionBehaviour8 && (dimensionBehaviour9 == (dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) || dimensionBehaviour9 == ConstraintWidget.DimensionBehaviour.FIXED)) {
                        if (i5 == 3) {
                            if (dimensionBehaviour9 == dimensionBehaviour3) {
                                l(constraintWidget, dimensionBehaviour3, 0, dimensionBehaviour3, 0);
                            }
                            int Y2 = constraintWidget.Y();
                            float f2 = constraintWidget.f0;
                            if (constraintWidget.y() == -1) {
                                f2 = 1.0f / f2;
                            }
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour15 = ConstraintWidget.DimensionBehaviour.FIXED;
                            l(constraintWidget, dimensionBehaviour15, Y2, dimensionBehaviour15, (int) ((Y2 * f2) + 0.5f));
                            constraintWidget.f1973e.f2083e.d(constraintWidget.Y());
                            constraintWidget.f1974f.f2083e.d(constraintWidget.z());
                            constraintWidget.f1969a = true;
                        } else if (i5 == 1) {
                            l(constraintWidget, dimensionBehaviour9, 0, dimensionBehaviour3, 0);
                            constraintWidget.f1974f.f2083e.f2048m = constraintWidget.z();
                        } else if (i5 == 2) {
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour16 = constraintWidgetContainer.b0[1];
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour17 = ConstraintWidget.DimensionBehaviour.FIXED;
                            if (dimensionBehaviour16 == dimensionBehaviour17 || dimensionBehaviour16 == dimensionBehaviour11) {
                                l(constraintWidget, dimensionBehaviour9, constraintWidget.Y(), dimensionBehaviour17, (int) ((constraintWidget.E * constraintWidgetContainer.z()) + 0.5f));
                                constraintWidget.f1973e.f2083e.d(constraintWidget.Y());
                                constraintWidget.f1974f.f2083e.d(constraintWidget.z());
                                constraintWidget.f1969a = true;
                            }
                        } else {
                            ConstraintAnchor[] constraintAnchorArr2 = constraintWidget.Y;
                            if (constraintAnchorArr2[2].f1965f == null || constraintAnchorArr2[3].f1965f == null) {
                                l(constraintWidget, dimensionBehaviour3, 0, dimensionBehaviour10, 0);
                                constraintWidget.f1973e.f2083e.d(constraintWidget.Y());
                                constraintWidget.f1974f.f2083e.d(constraintWidget.z());
                                constraintWidget.f1969a = true;
                            }
                        }
                    }
                    if (dimensionBehaviour9 == dimensionBehaviour8 && dimensionBehaviour10 == dimensionBehaviour8) {
                        if (i4 == 1 || i5 == 1) {
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour18 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                            l(constraintWidget, dimensionBehaviour18, 0, dimensionBehaviour18, 0);
                            constraintWidget.f1973e.f2083e.f2048m = constraintWidget.Y();
                            constraintWidget.f1974f.f2083e.f2048m = constraintWidget.z();
                        } else if (i5 == 2 && i4 == 2) {
                            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr2 = constraintWidgetContainer.b0;
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour19 = dimensionBehaviourArr2[0];
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour20 = ConstraintWidget.DimensionBehaviour.FIXED;
                            if (dimensionBehaviour19 == dimensionBehaviour20 && dimensionBehaviourArr2[1] == dimensionBehaviour20) {
                                l(constraintWidget, dimensionBehaviour20, (int) ((constraintWidget.B * constraintWidgetContainer.Y()) + 0.5f), dimensionBehaviour20, (int) ((constraintWidget.E * constraintWidgetContainer.z()) + 0.5f));
                                constraintWidget.f1973e.f2083e.d(constraintWidget.Y());
                                constraintWidget.f1974f.f2083e.d(constraintWidget.z());
                                constraintWidget.f1969a = true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private int e(ConstraintWidgetContainer constraintWidgetContainer, int i2) {
        int size = this.f2035i.size();
        long j2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            j2 = Math.max(j2, ((RunGroup) this.f2035i.get(i3)).b(constraintWidgetContainer, i2));
        }
        return (int) j2;
    }

    private void i(WidgetRun widgetRun, int i2, ArrayList arrayList) {
        for (Dependency dependency : widgetRun.f2086h.f2046k) {
            if (dependency instanceof DependencyNode) {
                a((DependencyNode) dependency, i2, 0, widgetRun.f2087i, arrayList, null);
            } else if (dependency instanceof WidgetRun) {
                a(((WidgetRun) dependency).f2086h, i2, 0, widgetRun.f2087i, arrayList, null);
            }
        }
        for (Dependency dependency2 : widgetRun.f2087i.f2046k) {
            if (dependency2 instanceof DependencyNode) {
                a((DependencyNode) dependency2, i2, 1, widgetRun.f2086h, arrayList, null);
            } else if (dependency2 instanceof WidgetRun) {
                a(((WidgetRun) dependency2).f2087i, i2, 1, widgetRun.f2086h, arrayList, null);
            }
        }
        if (i2 == 1) {
            for (Dependency dependency3 : ((VerticalWidgetRun) widgetRun).f2062k.f2046k) {
                if (dependency3 instanceof DependencyNode) {
                    a((DependencyNode) dependency3, i2, 2, null, arrayList, null);
                }
            }
        }
    }

    private void l(ConstraintWidget constraintWidget, ConstraintWidget.DimensionBehaviour dimensionBehaviour, int i2, ConstraintWidget.DimensionBehaviour dimensionBehaviour2, int i3) {
        BasicMeasure.Measure measure = this.f2034h;
        measure.f2015a = dimensionBehaviour;
        measure.f2016b = dimensionBehaviour2;
        measure.f2017c = i2;
        measure.f2018d = i3;
        this.f2033g.b(constraintWidget, measure);
        constraintWidget.p1(this.f2034h.f2019e);
        constraintWidget.Q0(this.f2034h.f2020f);
        constraintWidget.P0(this.f2034h.f2022h);
        constraintWidget.F0(this.f2034h.f2021g);
    }

    public void c() {
        d(this.f2031e);
        this.f2035i.clear();
        RunGroup.f2054h = 0;
        i(this.f2027a.f1973e, 0, this.f2035i);
        i(this.f2027a.f1974f, 1, this.f2035i);
        this.f2028b = false;
    }

    public void d(ArrayList arrayList) {
        arrayList.clear();
        this.f2030d.f1973e.f();
        this.f2030d.f1974f.f();
        arrayList.add(this.f2030d.f1973e);
        arrayList.add(this.f2030d.f1974f);
        Iterator it = this.f2030d.V0.iterator();
        HashSet hashSet = null;
        while (it.hasNext()) {
            ConstraintWidget constraintWidget = (ConstraintWidget) it.next();
            if (constraintWidget instanceof Guideline) {
                arrayList.add(new GuidelineReference(constraintWidget));
            } else {
                if (constraintWidget.k0()) {
                    if (constraintWidget.f1971c == null) {
                        constraintWidget.f1971c = new ChainRun(constraintWidget, 0);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(constraintWidget.f1971c);
                } else {
                    arrayList.add(constraintWidget.f1973e);
                }
                if (constraintWidget.m0()) {
                    if (constraintWidget.f1972d == null) {
                        constraintWidget.f1972d = new ChainRun(constraintWidget, 1);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(constraintWidget.f1972d);
                } else {
                    arrayList.add(constraintWidget.f1974f);
                }
                if (constraintWidget instanceof HelperWidget) {
                    arrayList.add(new HelperReferences(constraintWidget));
                }
            }
        }
        if (hashSet != null) {
            arrayList.addAll(hashSet);
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            ((WidgetRun) it2.next()).f();
        }
        Iterator it3 = arrayList.iterator();
        while (it3.hasNext()) {
            WidgetRun widgetRun = (WidgetRun) it3.next();
            if (widgetRun.f2080b != this.f2030d) {
                widgetRun.d();
            }
        }
    }

    public boolean f(boolean z) {
        boolean z2;
        boolean z3 = true;
        boolean z4 = z & true;
        if (this.f2028b || this.f2029c) {
            Iterator it = this.f2027a.V0.iterator();
            while (it.hasNext()) {
                ConstraintWidget constraintWidget = (ConstraintWidget) it.next();
                constraintWidget.p();
                constraintWidget.f1969a = false;
                constraintWidget.f1973e.r();
                constraintWidget.f1974f.q();
            }
            this.f2027a.p();
            ConstraintWidgetContainer constraintWidgetContainer = this.f2027a;
            constraintWidgetContainer.f1969a = false;
            constraintWidgetContainer.f1973e.r();
            this.f2027a.f1974f.q();
            this.f2029c = false;
        }
        if (b(this.f2030d)) {
            return false;
        }
        this.f2027a.r1(0);
        this.f2027a.s1(0);
        ConstraintWidget.DimensionBehaviour w = this.f2027a.w(0);
        ConstraintWidget.DimensionBehaviour w2 = this.f2027a.w(1);
        if (this.f2028b) {
            c();
        }
        int Z = this.f2027a.Z();
        int a0 = this.f2027a.a0();
        this.f2027a.f1973e.f2086h.d(Z);
        this.f2027a.f1974f.f2086h.d(a0);
        m();
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (w == dimensionBehaviour || w2 == dimensionBehaviour) {
            if (z4) {
                Iterator it2 = this.f2031e.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    if (!((WidgetRun) it2.next()).m()) {
                        z4 = false;
                        break;
                    }
                }
            }
            if (z4 && w == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                this.f2027a.U0(ConstraintWidget.DimensionBehaviour.FIXED);
                ConstraintWidgetContainer constraintWidgetContainer2 = this.f2027a;
                constraintWidgetContainer2.p1(e(constraintWidgetContainer2, 0));
                ConstraintWidgetContainer constraintWidgetContainer3 = this.f2027a;
                constraintWidgetContainer3.f1973e.f2083e.d(constraintWidgetContainer3.Y());
            }
            if (z4 && w2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                this.f2027a.l1(ConstraintWidget.DimensionBehaviour.FIXED);
                ConstraintWidgetContainer constraintWidgetContainer4 = this.f2027a;
                constraintWidgetContainer4.Q0(e(constraintWidgetContainer4, 1));
                ConstraintWidgetContainer constraintWidgetContainer5 = this.f2027a;
                constraintWidgetContainer5.f1974f.f2083e.d(constraintWidgetContainer5.z());
            }
        }
        ConstraintWidgetContainer constraintWidgetContainer6 = this.f2027a;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = constraintWidgetContainer6.b0[0];
        ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.FIXED;
        if (dimensionBehaviour2 == dimensionBehaviour3 || dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
            int Y = constraintWidgetContainer6.Y() + Z;
            this.f2027a.f1973e.f2087i.d(Y);
            this.f2027a.f1973e.f2083e.d(Y - Z);
            m();
            ConstraintWidgetContainer constraintWidgetContainer7 = this.f2027a;
            ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = constraintWidgetContainer7.b0[1];
            if (dimensionBehaviour4 == dimensionBehaviour3 || dimensionBehaviour4 == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                int z5 = constraintWidgetContainer7.z() + a0;
                this.f2027a.f1974f.f2087i.d(z5);
                this.f2027a.f1974f.f2083e.d(z5 - a0);
            }
            m();
            z2 = true;
        } else {
            z2 = false;
        }
        Iterator it3 = this.f2031e.iterator();
        while (it3.hasNext()) {
            WidgetRun widgetRun = (WidgetRun) it3.next();
            if (widgetRun.f2080b != this.f2027a || widgetRun.f2085g) {
                widgetRun.e();
            }
        }
        Iterator it4 = this.f2031e.iterator();
        while (it4.hasNext()) {
            WidgetRun widgetRun2 = (WidgetRun) it4.next();
            if (z2 || widgetRun2.f2080b != this.f2027a) {
                if (!widgetRun2.f2086h.f2045j || ((!widgetRun2.f2087i.f2045j && !(widgetRun2 instanceof GuidelineReference)) || (!widgetRun2.f2083e.f2045j && !(widgetRun2 instanceof ChainRun) && !(widgetRun2 instanceof GuidelineReference)))) {
                    z3 = false;
                    break;
                }
            }
        }
        this.f2027a.U0(w);
        this.f2027a.l1(w2);
        return z3;
    }

    public boolean g(boolean z) {
        if (this.f2028b) {
            Iterator it = this.f2027a.V0.iterator();
            while (it.hasNext()) {
                ConstraintWidget constraintWidget = (ConstraintWidget) it.next();
                constraintWidget.p();
                constraintWidget.f1969a = false;
                HorizontalWidgetRun horizontalWidgetRun = constraintWidget.f1973e;
                horizontalWidgetRun.f2083e.f2045j = false;
                horizontalWidgetRun.f2085g = false;
                horizontalWidgetRun.r();
                VerticalWidgetRun verticalWidgetRun = constraintWidget.f1974f;
                verticalWidgetRun.f2083e.f2045j = false;
                verticalWidgetRun.f2085g = false;
                verticalWidgetRun.q();
            }
            this.f2027a.p();
            ConstraintWidgetContainer constraintWidgetContainer = this.f2027a;
            constraintWidgetContainer.f1969a = false;
            HorizontalWidgetRun horizontalWidgetRun2 = constraintWidgetContainer.f1973e;
            horizontalWidgetRun2.f2083e.f2045j = false;
            horizontalWidgetRun2.f2085g = false;
            horizontalWidgetRun2.r();
            VerticalWidgetRun verticalWidgetRun2 = this.f2027a.f1974f;
            verticalWidgetRun2.f2083e.f2045j = false;
            verticalWidgetRun2.f2085g = false;
            verticalWidgetRun2.q();
            c();
        }
        if (b(this.f2030d)) {
            return false;
        }
        this.f2027a.r1(0);
        this.f2027a.s1(0);
        this.f2027a.f1973e.f2086h.d(0);
        this.f2027a.f1974f.f2086h.d(0);
        return true;
    }

    public boolean h(boolean z, int i2) {
        boolean z2;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour;
        boolean z3 = true;
        boolean z4 = z & true;
        ConstraintWidget.DimensionBehaviour w = this.f2027a.w(0);
        ConstraintWidget.DimensionBehaviour w2 = this.f2027a.w(1);
        int Z = this.f2027a.Z();
        int a0 = this.f2027a.a0();
        if (z4 && (w == (dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) || w2 == dimensionBehaviour)) {
            Iterator it = this.f2031e.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                WidgetRun widgetRun = (WidgetRun) it.next();
                if (widgetRun.f2084f == i2 && !widgetRun.m()) {
                    z4 = false;
                    break;
                }
            }
            if (i2 == 0) {
                if (z4 && w == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    this.f2027a.U0(ConstraintWidget.DimensionBehaviour.FIXED);
                    ConstraintWidgetContainer constraintWidgetContainer = this.f2027a;
                    constraintWidgetContainer.p1(e(constraintWidgetContainer, 0));
                    ConstraintWidgetContainer constraintWidgetContainer2 = this.f2027a;
                    constraintWidgetContainer2.f1973e.f2083e.d(constraintWidgetContainer2.Y());
                }
            } else if (z4 && w2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                this.f2027a.l1(ConstraintWidget.DimensionBehaviour.FIXED);
                ConstraintWidgetContainer constraintWidgetContainer3 = this.f2027a;
                constraintWidgetContainer3.Q0(e(constraintWidgetContainer3, 1));
                ConstraintWidgetContainer constraintWidgetContainer4 = this.f2027a;
                constraintWidgetContainer4.f1974f.f2083e.d(constraintWidgetContainer4.z());
            }
        }
        if (i2 == 0) {
            ConstraintWidgetContainer constraintWidgetContainer5 = this.f2027a;
            ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = constraintWidgetContainer5.b0[0];
            if (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                int Y = constraintWidgetContainer5.Y() + Z;
                this.f2027a.f1973e.f2087i.d(Y);
                this.f2027a.f1973e.f2083e.d(Y - Z);
                z2 = true;
            }
            z2 = false;
        } else {
            ConstraintWidgetContainer constraintWidgetContainer6 = this.f2027a;
            ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = constraintWidgetContainer6.b0[1];
            if (dimensionBehaviour3 == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviour3 == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                int z5 = constraintWidgetContainer6.z() + a0;
                this.f2027a.f1974f.f2087i.d(z5);
                this.f2027a.f1974f.f2083e.d(z5 - a0);
                z2 = true;
            }
            z2 = false;
        }
        m();
        Iterator it2 = this.f2031e.iterator();
        while (it2.hasNext()) {
            WidgetRun widgetRun2 = (WidgetRun) it2.next();
            if (widgetRun2.f2084f == i2 && (widgetRun2.f2080b != this.f2027a || widgetRun2.f2085g)) {
                widgetRun2.e();
            }
        }
        Iterator it3 = this.f2031e.iterator();
        while (it3.hasNext()) {
            WidgetRun widgetRun3 = (WidgetRun) it3.next();
            if (widgetRun3.f2084f == i2 && (z2 || widgetRun3.f2080b != this.f2027a)) {
                if (!widgetRun3.f2086h.f2045j || !widgetRun3.f2087i.f2045j || (!(widgetRun3 instanceof ChainRun) && !widgetRun3.f2083e.f2045j)) {
                    z3 = false;
                    break;
                }
            }
        }
        this.f2027a.U0(w);
        this.f2027a.l1(w2);
        return z3;
    }

    public void j() {
        this.f2028b = true;
    }

    public void k() {
        this.f2029c = true;
    }

    public void m() {
        DimensionDependency dimensionDependency;
        Iterator it = this.f2027a.V0.iterator();
        while (it.hasNext()) {
            ConstraintWidget constraintWidget = (ConstraintWidget) it.next();
            if (!constraintWidget.f1969a) {
                ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = constraintWidget.b0;
                boolean z = false;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = dimensionBehaviourArr[0];
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = dimensionBehaviourArr[1];
                int i2 = constraintWidget.w;
                int i3 = constraintWidget.x;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                boolean z2 = dimensionBehaviour == dimensionBehaviour3 || (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && i2 == 1);
                if (dimensionBehaviour2 == dimensionBehaviour3 || (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && i3 == 1)) {
                    z = true;
                }
                DimensionDependency dimensionDependency2 = constraintWidget.f1973e.f2083e;
                boolean z3 = dimensionDependency2.f2045j;
                DimensionDependency dimensionDependency3 = constraintWidget.f1974f.f2083e;
                boolean z4 = dimensionDependency3.f2045j;
                if (z3 && z4) {
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = ConstraintWidget.DimensionBehaviour.FIXED;
                    l(constraintWidget, dimensionBehaviour4, dimensionDependency2.f2042g, dimensionBehaviour4, dimensionDependency3.f2042g);
                    constraintWidget.f1969a = true;
                } else if (z3 && z) {
                    l(constraintWidget, ConstraintWidget.DimensionBehaviour.FIXED, dimensionDependency2.f2042g, dimensionBehaviour3, dimensionDependency3.f2042g);
                    if (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        constraintWidget.f1974f.f2083e.f2048m = constraintWidget.z();
                    } else {
                        constraintWidget.f1974f.f2083e.d(constraintWidget.z());
                        constraintWidget.f1969a = true;
                    }
                } else if (z4 && z2) {
                    l(constraintWidget, dimensionBehaviour3, dimensionDependency2.f2042g, ConstraintWidget.DimensionBehaviour.FIXED, dimensionDependency3.f2042g);
                    if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        constraintWidget.f1973e.f2083e.f2048m = constraintWidget.Y();
                    } else {
                        constraintWidget.f1973e.f2083e.d(constraintWidget.Y());
                        constraintWidget.f1969a = true;
                    }
                }
                if (constraintWidget.f1969a && (dimensionDependency = constraintWidget.f1974f.f2063l) != null) {
                    dimensionDependency.d(constraintWidget.r());
                }
            }
        }
    }

    public void n(BasicMeasure.Measurer measurer) {
        this.f2033g = measurer;
    }
}
