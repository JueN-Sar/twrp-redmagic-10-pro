package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.Helper;
import androidx.constraintlayout.core.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.core.widgets.analyzer.WidgetRun;

/* loaded from: classes.dex */
public class VerticalWidgetRun extends WidgetRun {

    /* renamed from: k, reason: collision with root package name */
    public DependencyNode f2062k;

    /* renamed from: l, reason: collision with root package name */
    DimensionDependency f2063l;

    /* renamed from: androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f2064a;

        static {
            int[] iArr = new int[WidgetRun.RunType.values().length];
            f2064a = iArr;
            try {
                iArr[WidgetRun.RunType.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2064a[WidgetRun.RunType.END.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2064a[WidgetRun.RunType.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public VerticalWidgetRun(ConstraintWidget constraintWidget) {
        super(constraintWidget);
        DependencyNode dependencyNode = new DependencyNode(this);
        this.f2062k = dependencyNode;
        this.f2063l = null;
        this.f2086h.f2040e = DependencyNode.Type.TOP;
        this.f2087i.f2040e = DependencyNode.Type.BOTTOM;
        dependencyNode.f2040e = DependencyNode.Type.BASELINE;
        this.f2084f = 1;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun, androidx.constraintlayout.core.widgets.analyzer.Dependency
    public void a(Dependency dependency) {
        float f2;
        float x;
        float f3;
        int i2;
        int i3 = AnonymousClass1.f2064a[this.f2088j.ordinal()];
        if (i3 == 1) {
            p(dependency);
        } else if (i3 == 2) {
            o(dependency);
        } else if (i3 == 3) {
            ConstraintWidget constraintWidget = this.f2080b;
            n(dependency, constraintWidget.R, constraintWidget.T, 1);
            return;
        }
        DimensionDependency dimensionDependency = this.f2083e;
        if (dimensionDependency.f2038c && !dimensionDependency.f2045j && this.f2082d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            ConstraintWidget constraintWidget2 = this.f2080b;
            int i4 = constraintWidget2.x;
            if (i4 == 2) {
                ConstraintWidget M = constraintWidget2.M();
                if (M != null) {
                    if (M.f1974f.f2083e.f2045j) {
                        this.f2083e.d((int) ((r7.f2042g * this.f2080b.E) + 0.5f));
                    }
                }
            } else if (i4 == 3 && constraintWidget2.f1973e.f2083e.f2045j) {
                int y = constraintWidget2.y();
                if (y == -1) {
                    ConstraintWidget constraintWidget3 = this.f2080b;
                    f2 = constraintWidget3.f1973e.f2083e.f2042g;
                    x = constraintWidget3.x();
                } else if (y == 0) {
                    f3 = r7.f1973e.f2083e.f2042g * this.f2080b.x();
                    i2 = (int) (f3 + 0.5f);
                    this.f2083e.d(i2);
                } else if (y != 1) {
                    i2 = 0;
                    this.f2083e.d(i2);
                } else {
                    ConstraintWidget constraintWidget4 = this.f2080b;
                    f2 = constraintWidget4.f1973e.f2083e.f2042g;
                    x = constraintWidget4.x();
                }
                f3 = f2 / x;
                i2 = (int) (f3 + 0.5f);
                this.f2083e.d(i2);
            }
        }
        DependencyNode dependencyNode = this.f2086h;
        if (dependencyNode.f2038c) {
            DependencyNode dependencyNode2 = this.f2087i;
            if (dependencyNode2.f2038c) {
                if (dependencyNode.f2045j && dependencyNode2.f2045j && this.f2083e.f2045j) {
                    return;
                }
                if (!this.f2083e.f2045j && this.f2082d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    ConstraintWidget constraintWidget5 = this.f2080b;
                    if (constraintWidget5.w == 0 && !constraintWidget5.m0()) {
                        DependencyNode dependencyNode3 = (DependencyNode) this.f2086h.f2047l.get(0);
                        DependencyNode dependencyNode4 = (DependencyNode) this.f2087i.f2047l.get(0);
                        int i5 = dependencyNode3.f2042g;
                        DependencyNode dependencyNode5 = this.f2086h;
                        int i6 = i5 + dependencyNode5.f2041f;
                        int i7 = dependencyNode4.f2042g + this.f2087i.f2041f;
                        dependencyNode5.d(i6);
                        this.f2087i.d(i7);
                        this.f2083e.d(i7 - i6);
                        return;
                    }
                }
                if (!this.f2083e.f2045j && this.f2082d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && this.f2079a == 1 && this.f2086h.f2047l.size() > 0 && this.f2087i.f2047l.size() > 0) {
                    DependencyNode dependencyNode6 = (DependencyNode) this.f2086h.f2047l.get(0);
                    int i8 = (((DependencyNode) this.f2087i.f2047l.get(0)).f2042g + this.f2087i.f2041f) - (dependencyNode6.f2042g + this.f2086h.f2041f);
                    DimensionDependency dimensionDependency2 = this.f2083e;
                    int i9 = dimensionDependency2.f2048m;
                    if (i8 < i9) {
                        dimensionDependency2.d(i8);
                    } else {
                        dimensionDependency2.d(i9);
                    }
                }
                if (this.f2083e.f2045j && this.f2086h.f2047l.size() > 0 && this.f2087i.f2047l.size() > 0) {
                    DependencyNode dependencyNode7 = (DependencyNode) this.f2086h.f2047l.get(0);
                    DependencyNode dependencyNode8 = (DependencyNode) this.f2087i.f2047l.get(0);
                    int i10 = dependencyNode7.f2042g + this.f2086h.f2041f;
                    int i11 = dependencyNode8.f2042g + this.f2087i.f2041f;
                    float T = this.f2080b.T();
                    if (dependencyNode7 == dependencyNode8) {
                        i10 = dependencyNode7.f2042g;
                        i11 = dependencyNode8.f2042g;
                        T = 0.5f;
                    }
                    this.f2086h.d((int) (i10 + 0.5f + (((i11 - i10) - this.f2083e.f2042g) * T)));
                    this.f2087i.d(this.f2086h.f2042g + this.f2083e.f2042g);
                }
            }
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void d() {
        ConstraintWidget M;
        ConstraintWidget M2;
        ConstraintWidget constraintWidget = this.f2080b;
        if (constraintWidget.f1969a) {
            this.f2083e.d(constraintWidget.z());
        }
        if (!this.f2083e.f2045j) {
            this.f2082d = this.f2080b.V();
            if (this.f2080b.b0()) {
                this.f2063l = new BaselineDimensionDependency(this);
            }
            ConstraintWidget.DimensionBehaviour dimensionBehaviour = this.f2082d;
            if (dimensionBehaviour != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_PARENT && (M2 = this.f2080b.M()) != null && M2.V() == ConstraintWidget.DimensionBehaviour.FIXED) {
                    int z = (M2.z() - this.f2080b.R.f()) - this.f2080b.T.f();
                    b(this.f2086h, M2.f1974f.f2086h, this.f2080b.R.f());
                    b(this.f2087i, M2.f1974f.f2087i, -this.f2080b.T.f());
                    this.f2083e.d(z);
                    return;
                }
                if (this.f2082d == ConstraintWidget.DimensionBehaviour.FIXED) {
                    this.f2083e.d(this.f2080b.z());
                }
            }
        } else if (this.f2082d == ConstraintWidget.DimensionBehaviour.MATCH_PARENT && (M = this.f2080b.M()) != null && M.V() == ConstraintWidget.DimensionBehaviour.FIXED) {
            b(this.f2086h, M.f1974f.f2086h, this.f2080b.R.f());
            b(this.f2087i, M.f1974f.f2087i, -this.f2080b.T.f());
            return;
        }
        DimensionDependency dimensionDependency = this.f2083e;
        boolean z2 = dimensionDependency.f2045j;
        if (z2) {
            ConstraintWidget constraintWidget2 = this.f2080b;
            if (constraintWidget2.f1969a) {
                ConstraintAnchor[] constraintAnchorArr = constraintWidget2.Y;
                ConstraintAnchor constraintAnchor = constraintAnchorArr[2];
                ConstraintAnchor constraintAnchor2 = constraintAnchor.f1965f;
                if (constraintAnchor2 != null && constraintAnchorArr[3].f1965f != null) {
                    if (constraintWidget2.m0()) {
                        this.f2086h.f2041f = this.f2080b.Y[2].f();
                        this.f2087i.f2041f = -this.f2080b.Y[3].f();
                    } else {
                        DependencyNode h2 = h(this.f2080b.Y[2]);
                        if (h2 != null) {
                            b(this.f2086h, h2, this.f2080b.Y[2].f());
                        }
                        DependencyNode h3 = h(this.f2080b.Y[3]);
                        if (h3 != null) {
                            b(this.f2087i, h3, -this.f2080b.Y[3].f());
                        }
                        this.f2086h.f2037b = true;
                        this.f2087i.f2037b = true;
                    }
                    if (this.f2080b.b0()) {
                        b(this.f2062k, this.f2086h, this.f2080b.r());
                        return;
                    }
                    return;
                }
                if (constraintAnchor2 != null) {
                    DependencyNode h4 = h(constraintAnchor);
                    if (h4 != null) {
                        b(this.f2086h, h4, this.f2080b.Y[2].f());
                        b(this.f2087i, this.f2086h, this.f2083e.f2042g);
                        if (this.f2080b.b0()) {
                            b(this.f2062k, this.f2086h, this.f2080b.r());
                            return;
                        }
                        return;
                    }
                    return;
                }
                ConstraintAnchor constraintAnchor3 = constraintAnchorArr[3];
                if (constraintAnchor3.f1965f != null) {
                    DependencyNode h5 = h(constraintAnchor3);
                    if (h5 != null) {
                        b(this.f2087i, h5, -this.f2080b.Y[3].f());
                        b(this.f2086h, this.f2087i, -this.f2083e.f2042g);
                    }
                    if (this.f2080b.b0()) {
                        b(this.f2062k, this.f2086h, this.f2080b.r());
                        return;
                    }
                    return;
                }
                ConstraintAnchor constraintAnchor4 = constraintAnchorArr[4];
                if (constraintAnchor4.f1965f != null) {
                    DependencyNode h6 = h(constraintAnchor4);
                    if (h6 != null) {
                        b(this.f2062k, h6, 0);
                        b(this.f2086h, this.f2062k, -this.f2080b.r());
                        b(this.f2087i, this.f2086h, this.f2083e.f2042g);
                        return;
                    }
                    return;
                }
                if ((constraintWidget2 instanceof Helper) || constraintWidget2.M() == null || this.f2080b.q(ConstraintAnchor.Type.CENTER).f1965f != null) {
                    return;
                }
                b(this.f2086h, this.f2080b.M().f1974f.f2086h, this.f2080b.a0());
                b(this.f2087i, this.f2086h, this.f2083e.f2042g);
                if (this.f2080b.b0()) {
                    b(this.f2062k, this.f2086h, this.f2080b.r());
                    return;
                }
                return;
            }
        }
        if (z2 || this.f2082d != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            dimensionDependency.b(this);
        } else {
            ConstraintWidget constraintWidget3 = this.f2080b;
            int i2 = constraintWidget3.x;
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
            } else if (i2 == 3 && !constraintWidget3.m0()) {
                ConstraintWidget constraintWidget4 = this.f2080b;
                if (constraintWidget4.w != 3) {
                    DimensionDependency dimensionDependency4 = constraintWidget4.f1973e.f2083e;
                    this.f2083e.f2047l.add(dimensionDependency4);
                    dimensionDependency4.f2046k.add(this.f2083e);
                    DimensionDependency dimensionDependency5 = this.f2083e;
                    dimensionDependency5.f2037b = true;
                    dimensionDependency5.f2046k.add(this.f2086h);
                    this.f2083e.f2046k.add(this.f2087i);
                }
            }
        }
        ConstraintWidget constraintWidget5 = this.f2080b;
        ConstraintAnchor[] constraintAnchorArr2 = constraintWidget5.Y;
        ConstraintAnchor constraintAnchor5 = constraintAnchorArr2[2];
        ConstraintAnchor constraintAnchor6 = constraintAnchor5.f1965f;
        if (constraintAnchor6 != null && constraintAnchorArr2[3].f1965f != null) {
            if (constraintWidget5.m0()) {
                this.f2086h.f2041f = this.f2080b.Y[2].f();
                this.f2087i.f2041f = -this.f2080b.Y[3].f();
            } else {
                DependencyNode h7 = h(this.f2080b.Y[2]);
                DependencyNode h8 = h(this.f2080b.Y[3]);
                if (h7 != null) {
                    h7.b(this);
                }
                if (h8 != null) {
                    h8.b(this);
                }
                this.f2088j = WidgetRun.RunType.CENTER;
            }
            if (this.f2080b.b0()) {
                c(this.f2062k, this.f2086h, 1, this.f2063l);
            }
        } else if (constraintAnchor6 != null) {
            DependencyNode h9 = h(constraintAnchor5);
            if (h9 != null) {
                b(this.f2086h, h9, this.f2080b.Y[2].f());
                c(this.f2087i, this.f2086h, 1, this.f2083e);
                if (this.f2080b.b0()) {
                    c(this.f2062k, this.f2086h, 1, this.f2063l);
                }
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = this.f2082d;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (dimensionBehaviour2 == dimensionBehaviour3 && this.f2080b.x() > 0.0f) {
                    HorizontalWidgetRun horizontalWidgetRun = this.f2080b.f1973e;
                    if (horizontalWidgetRun.f2082d == dimensionBehaviour3) {
                        horizontalWidgetRun.f2083e.f2046k.add(this.f2083e);
                        this.f2083e.f2047l.add(this.f2080b.f1973e.f2083e);
                        this.f2083e.f2036a = this;
                    }
                }
            }
        } else {
            ConstraintAnchor constraintAnchor7 = constraintAnchorArr2[3];
            if (constraintAnchor7.f1965f != null) {
                DependencyNode h10 = h(constraintAnchor7);
                if (h10 != null) {
                    b(this.f2087i, h10, -this.f2080b.Y[3].f());
                    c(this.f2086h, this.f2087i, -1, this.f2083e);
                    if (this.f2080b.b0()) {
                        c(this.f2062k, this.f2086h, 1, this.f2063l);
                    }
                }
            } else {
                ConstraintAnchor constraintAnchor8 = constraintAnchorArr2[4];
                if (constraintAnchor8.f1965f != null) {
                    DependencyNode h11 = h(constraintAnchor8);
                    if (h11 != null) {
                        b(this.f2062k, h11, 0);
                        c(this.f2086h, this.f2062k, -1, this.f2063l);
                        c(this.f2087i, this.f2086h, 1, this.f2083e);
                    }
                } else if (!(constraintWidget5 instanceof Helper) && constraintWidget5.M() != null) {
                    b(this.f2086h, this.f2080b.M().f1974f.f2086h, this.f2080b.a0());
                    c(this.f2087i, this.f2086h, 1, this.f2083e);
                    if (this.f2080b.b0()) {
                        c(this.f2062k, this.f2086h, 1, this.f2063l);
                    }
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = this.f2082d;
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour5 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                    if (dimensionBehaviour4 == dimensionBehaviour5 && this.f2080b.x() > 0.0f) {
                        HorizontalWidgetRun horizontalWidgetRun2 = this.f2080b.f1973e;
                        if (horizontalWidgetRun2.f2082d == dimensionBehaviour5) {
                            horizontalWidgetRun2.f2083e.f2046k.add(this.f2083e);
                            this.f2083e.f2047l.add(this.f2080b.f1973e.f2083e);
                            this.f2083e.f2036a = this;
                        }
                    }
                }
            }
        }
        if (this.f2083e.f2047l.size() == 0) {
            this.f2083e.f2038c = true;
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void e() {
        DependencyNode dependencyNode = this.f2086h;
        if (dependencyNode.f2045j) {
            this.f2080b.s1(dependencyNode.f2042g);
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void f() {
        this.f2081c = null;
        this.f2086h.c();
        this.f2087i.c();
        this.f2062k.c();
        this.f2083e.c();
        this.f2085g = false;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    boolean m() {
        return this.f2082d != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || this.f2080b.x == 0;
    }

    void q() {
        this.f2085g = false;
        this.f2086h.c();
        this.f2086h.f2045j = false;
        this.f2087i.c();
        this.f2087i.f2045j = false;
        this.f2062k.c();
        this.f2062k.f2045j = false;
        this.f2083e.f2045j = false;
    }

    public String toString() {
        return "VerticalRun " + this.f2080b.v();
    }
}
