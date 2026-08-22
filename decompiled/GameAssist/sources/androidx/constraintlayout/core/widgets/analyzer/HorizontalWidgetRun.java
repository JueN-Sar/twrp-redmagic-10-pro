package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.Helper;
import androidx.constraintlayout.core.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.core.widgets.analyzer.WidgetRun;

/* loaded from: classes.dex */
public class HorizontalWidgetRun extends WidgetRun {

    /* renamed from: k, reason: collision with root package name */
    private static int[] f2052k = new int[2];

    /* renamed from: androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f2053a;

        static {
            int[] iArr = new int[WidgetRun.RunType.values().length];
            f2053a = iArr;
            try {
                iArr[WidgetRun.RunType.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2053a[WidgetRun.RunType.END.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2053a[WidgetRun.RunType.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public HorizontalWidgetRun(ConstraintWidget constraintWidget) {
        super(constraintWidget);
        this.f2086h.f2040e = DependencyNode.Type.LEFT;
        this.f2087i.f2040e = DependencyNode.Type.RIGHT;
        this.f2084f = 0;
    }

    private void q(int[] iArr, int i2, int i3, int i4, int i5, float f2, int i6) {
        int i7 = i3 - i2;
        int i8 = i5 - i4;
        if (i6 != -1) {
            if (i6 == 0) {
                iArr[0] = (int) ((i8 * f2) + 0.5f);
                iArr[1] = i8;
                return;
            } else {
                if (i6 != 1) {
                    return;
                }
                iArr[0] = i7;
                iArr[1] = (int) ((i7 * f2) + 0.5f);
                return;
            }
        }
        int i9 = (int) ((i8 * f2) + 0.5f);
        int i10 = (int) ((i7 / f2) + 0.5f);
        if (i9 <= i7) {
            iArr[0] = i9;
            iArr[1] = i8;
        } else if (i10 <= i8) {
            iArr[0] = i7;
            iArr[1] = i10;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:111:0x02ba, code lost:
    
        if (r14 != 1) goto L135;
     */
    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun, androidx.constraintlayout.core.widgets.analyzer.Dependency
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(androidx.constraintlayout.core.widgets.analyzer.Dependency r17) {
        /*
            Method dump skipped, instructions count: 1088
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun.a(androidx.constraintlayout.core.widgets.analyzer.Dependency):void");
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void d() {
        ConstraintWidget M;
        ConstraintWidget M2;
        ConstraintWidget constraintWidget = this.f2080b;
        if (constraintWidget.f1969a) {
            this.f2083e.d(constraintWidget.Y());
        }
        if (this.f2083e.f2045j) {
            ConstraintWidget.DimensionBehaviour dimensionBehaviour = this.f2082d;
            ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
            if (dimensionBehaviour == dimensionBehaviour2 && (M = this.f2080b.M()) != null && (M.C() == ConstraintWidget.DimensionBehaviour.FIXED || M.C() == dimensionBehaviour2)) {
                b(this.f2086h, M.f1973e.f2086h, this.f2080b.Q.f());
                b(this.f2087i, M.f1973e.f2087i, -this.f2080b.S.f());
                return;
            }
        } else {
            ConstraintWidget.DimensionBehaviour C = this.f2080b.C();
            this.f2082d = C;
            if (C != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
                if (C == dimensionBehaviour3 && (M2 = this.f2080b.M()) != null && (M2.C() == ConstraintWidget.DimensionBehaviour.FIXED || M2.C() == dimensionBehaviour3)) {
                    int Y = (M2.Y() - this.f2080b.Q.f()) - this.f2080b.S.f();
                    b(this.f2086h, M2.f1973e.f2086h, this.f2080b.Q.f());
                    b(this.f2087i, M2.f1973e.f2087i, -this.f2080b.S.f());
                    this.f2083e.d(Y);
                    return;
                }
                if (this.f2082d == ConstraintWidget.DimensionBehaviour.FIXED) {
                    this.f2083e.d(this.f2080b.Y());
                }
            }
        }
        DimensionDependency dimensionDependency = this.f2083e;
        if (dimensionDependency.f2045j) {
            ConstraintWidget constraintWidget2 = this.f2080b;
            if (constraintWidget2.f1969a) {
                ConstraintAnchor[] constraintAnchorArr = constraintWidget2.Y;
                ConstraintAnchor constraintAnchor = constraintAnchorArr[0];
                ConstraintAnchor constraintAnchor2 = constraintAnchor.f1965f;
                if (constraintAnchor2 != null && constraintAnchorArr[1].f1965f != null) {
                    if (constraintWidget2.k0()) {
                        this.f2086h.f2041f = this.f2080b.Y[0].f();
                        this.f2087i.f2041f = -this.f2080b.Y[1].f();
                        return;
                    }
                    DependencyNode h2 = h(this.f2080b.Y[0]);
                    if (h2 != null) {
                        b(this.f2086h, h2, this.f2080b.Y[0].f());
                    }
                    DependencyNode h3 = h(this.f2080b.Y[1]);
                    if (h3 != null) {
                        b(this.f2087i, h3, -this.f2080b.Y[1].f());
                    }
                    this.f2086h.f2037b = true;
                    this.f2087i.f2037b = true;
                    return;
                }
                if (constraintAnchor2 != null) {
                    DependencyNode h4 = h(constraintAnchor);
                    if (h4 != null) {
                        b(this.f2086h, h4, this.f2080b.Y[0].f());
                        b(this.f2087i, this.f2086h, this.f2083e.f2042g);
                        return;
                    }
                    return;
                }
                ConstraintAnchor constraintAnchor3 = constraintAnchorArr[1];
                if (constraintAnchor3.f1965f != null) {
                    DependencyNode h5 = h(constraintAnchor3);
                    if (h5 != null) {
                        b(this.f2087i, h5, -this.f2080b.Y[1].f());
                        b(this.f2086h, this.f2087i, -this.f2083e.f2042g);
                        return;
                    }
                    return;
                }
                if ((constraintWidget2 instanceof Helper) || constraintWidget2.M() == null || this.f2080b.q(ConstraintAnchor.Type.CENTER).f1965f != null) {
                    return;
                }
                b(this.f2086h, this.f2080b.M().f1973e.f2086h, this.f2080b.Z());
                b(this.f2087i, this.f2086h, this.f2083e.f2042g);
                return;
            }
        }
        if (this.f2082d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            ConstraintWidget constraintWidget3 = this.f2080b;
            int i2 = constraintWidget3.w;
            if (i2 == 2) {
                ConstraintWidget M3 = constraintWidget3.M();
                if (M3 != null) {
                    DimensionDependency dimensionDependency2 = M3.f1974f.f2083e;
                    this.f2083e.f2047l.add(dimensionDependency2);
                    dimensionDependency2.f2046k.add(this.f2083e);
                    DimensionDependency dimensionDependency3 = this.f2083e;
                    dimensionDependency3.f2037b = true;
                    dimensionDependency3.f2046k.add(this.f2086h);
                    this.f2083e.f2046k.add(this.f2087i);
                }
            } else if (i2 == 3) {
                if (constraintWidget3.x == 3) {
                    this.f2086h.f2036a = this;
                    this.f2087i.f2036a = this;
                    VerticalWidgetRun verticalWidgetRun = constraintWidget3.f1974f;
                    verticalWidgetRun.f2086h.f2036a = this;
                    verticalWidgetRun.f2087i.f2036a = this;
                    dimensionDependency.f2036a = this;
                    if (constraintWidget3.m0()) {
                        this.f2083e.f2047l.add(this.f2080b.f1974f.f2083e);
                        this.f2080b.f1974f.f2083e.f2046k.add(this.f2083e);
                        VerticalWidgetRun verticalWidgetRun2 = this.f2080b.f1974f;
                        verticalWidgetRun2.f2083e.f2036a = this;
                        this.f2083e.f2047l.add(verticalWidgetRun2.f2086h);
                        this.f2083e.f2047l.add(this.f2080b.f1974f.f2087i);
                        this.f2080b.f1974f.f2086h.f2046k.add(this.f2083e);
                        this.f2080b.f1974f.f2087i.f2046k.add(this.f2083e);
                    } else if (this.f2080b.k0()) {
                        this.f2080b.f1974f.f2083e.f2047l.add(this.f2083e);
                        this.f2083e.f2046k.add(this.f2080b.f1974f.f2083e);
                    } else {
                        this.f2080b.f1974f.f2083e.f2047l.add(this.f2083e);
                    }
                } else {
                    DimensionDependency dimensionDependency4 = constraintWidget3.f1974f.f2083e;
                    dimensionDependency.f2047l.add(dimensionDependency4);
                    dimensionDependency4.f2046k.add(this.f2083e);
                    this.f2080b.f1974f.f2086h.f2046k.add(this.f2083e);
                    this.f2080b.f1974f.f2087i.f2046k.add(this.f2083e);
                    DimensionDependency dimensionDependency5 = this.f2083e;
                    dimensionDependency5.f2037b = true;
                    dimensionDependency5.f2046k.add(this.f2086h);
                    this.f2083e.f2046k.add(this.f2087i);
                    this.f2086h.f2047l.add(this.f2083e);
                    this.f2087i.f2047l.add(this.f2083e);
                }
            }
        }
        ConstraintWidget constraintWidget4 = this.f2080b;
        ConstraintAnchor[] constraintAnchorArr2 = constraintWidget4.Y;
        ConstraintAnchor constraintAnchor4 = constraintAnchorArr2[0];
        ConstraintAnchor constraintAnchor5 = constraintAnchor4.f1965f;
        if (constraintAnchor5 != null && constraintAnchorArr2[1].f1965f != null) {
            if (constraintWidget4.k0()) {
                this.f2086h.f2041f = this.f2080b.Y[0].f();
                this.f2087i.f2041f = -this.f2080b.Y[1].f();
                return;
            }
            DependencyNode h6 = h(this.f2080b.Y[0]);
            DependencyNode h7 = h(this.f2080b.Y[1]);
            if (h6 != null) {
                h6.b(this);
            }
            if (h7 != null) {
                h7.b(this);
            }
            this.f2088j = WidgetRun.RunType.CENTER;
            return;
        }
        if (constraintAnchor5 != null) {
            DependencyNode h8 = h(constraintAnchor4);
            if (h8 != null) {
                b(this.f2086h, h8, this.f2080b.Y[0].f());
                c(this.f2087i, this.f2086h, 1, this.f2083e);
                return;
            }
            return;
        }
        ConstraintAnchor constraintAnchor6 = constraintAnchorArr2[1];
        if (constraintAnchor6.f1965f != null) {
            DependencyNode h9 = h(constraintAnchor6);
            if (h9 != null) {
                b(this.f2087i, h9, -this.f2080b.Y[1].f());
                c(this.f2086h, this.f2087i, -1, this.f2083e);
                return;
            }
            return;
        }
        if ((constraintWidget4 instanceof Helper) || constraintWidget4.M() == null) {
            return;
        }
        b(this.f2086h, this.f2080b.M().f1973e.f2086h, this.f2080b.Z());
        c(this.f2087i, this.f2086h, 1, this.f2083e);
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void e() {
        DependencyNode dependencyNode = this.f2086h;
        if (dependencyNode.f2045j) {
            this.f2080b.r1(dependencyNode.f2042g);
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void f() {
        this.f2081c = null;
        this.f2086h.c();
        this.f2087i.c();
        this.f2083e.c();
        this.f2085g = false;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    boolean m() {
        return this.f2082d != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || this.f2080b.w == 0;
    }

    void r() {
        this.f2085g = false;
        this.f2086h.c();
        this.f2086h.f2045j = false;
        this.f2087i.c();
        this.f2087i.f2045j = false;
        this.f2083e.f2045j = false;
    }

    public String toString() {
        return "HorizontalRun " + this.f2080b.v();
    }
}
