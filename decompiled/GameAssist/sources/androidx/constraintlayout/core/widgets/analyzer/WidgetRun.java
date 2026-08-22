package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;

/* loaded from: classes.dex */
public abstract class WidgetRun implements Dependency {

    /* renamed from: a, reason: collision with root package name */
    public int f2079a;

    /* renamed from: b, reason: collision with root package name */
    ConstraintWidget f2080b;

    /* renamed from: c, reason: collision with root package name */
    RunGroup f2081c;

    /* renamed from: d, reason: collision with root package name */
    protected ConstraintWidget.DimensionBehaviour f2082d;

    /* renamed from: e, reason: collision with root package name */
    DimensionDependency f2083e = new DimensionDependency(this);

    /* renamed from: f, reason: collision with root package name */
    public int f2084f = 0;

    /* renamed from: g, reason: collision with root package name */
    boolean f2085g = false;

    /* renamed from: h, reason: collision with root package name */
    public DependencyNode f2086h = new DependencyNode(this);

    /* renamed from: i, reason: collision with root package name */
    public DependencyNode f2087i = new DependencyNode(this);

    /* renamed from: j, reason: collision with root package name */
    protected RunType f2088j = RunType.NONE;

    /* renamed from: androidx.constraintlayout.core.widgets.analyzer.WidgetRun$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f2089a;

        static {
            int[] iArr = new int[ConstraintAnchor.Type.values().length];
            f2089a = iArr;
            try {
                iArr[ConstraintAnchor.Type.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2089a[ConstraintAnchor.Type.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2089a[ConstraintAnchor.Type.TOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2089a[ConstraintAnchor.Type.BASELINE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f2089a[ConstraintAnchor.Type.BOTTOM.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    enum RunType {
        NONE,
        START,
        END,
        CENTER
    }

    public WidgetRun(ConstraintWidget constraintWidget) {
        this.f2080b = constraintWidget;
    }

    private void l(int i2, int i3) {
        int i4 = this.f2079a;
        if (i4 == 0) {
            this.f2083e.d(g(i3, i2));
            return;
        }
        if (i4 == 1) {
            this.f2083e.d(Math.min(g(this.f2083e.f2048m, i2), i3));
            return;
        }
        if (i4 == 2) {
            ConstraintWidget M = this.f2080b.M();
            if (M != null) {
                if ((i2 == 0 ? M.f1973e : M.f1974f).f2083e.f2045j) {
                    this.f2083e.d(g((int) ((r9.f2042g * (i2 == 0 ? this.f2080b.B : this.f2080b.E)) + 0.5f), i2));
                    return;
                }
                return;
            }
            return;
        }
        if (i4 != 3) {
            return;
        }
        ConstraintWidget constraintWidget = this.f2080b;
        WidgetRun widgetRun = constraintWidget.f1973e;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = widgetRun.f2082d;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (dimensionBehaviour == dimensionBehaviour2 && widgetRun.f2079a == 3) {
            VerticalWidgetRun verticalWidgetRun = constraintWidget.f1974f;
            if (verticalWidgetRun.f2082d == dimensionBehaviour2 && verticalWidgetRun.f2079a == 3) {
                return;
            }
        }
        if (i2 == 0) {
            widgetRun = constraintWidget.f1974f;
        }
        if (widgetRun.f2083e.f2045j) {
            float x = constraintWidget.x();
            this.f2083e.d(i2 == 1 ? (int) ((widgetRun.f2083e.f2042g / x) + 0.5f) : (int) ((x * widgetRun.f2083e.f2042g) + 0.5f));
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.Dependency
    public void a(Dependency dependency) {
    }

    protected final void b(DependencyNode dependencyNode, DependencyNode dependencyNode2, int i2) {
        dependencyNode.f2047l.add(dependencyNode2);
        dependencyNode.f2041f = i2;
        dependencyNode2.f2046k.add(dependencyNode);
    }

    protected final void c(DependencyNode dependencyNode, DependencyNode dependencyNode2, int i2, DimensionDependency dimensionDependency) {
        dependencyNode.f2047l.add(dependencyNode2);
        dependencyNode.f2047l.add(this.f2083e);
        dependencyNode.f2043h = i2;
        dependencyNode.f2044i = dimensionDependency;
        dependencyNode2.f2046k.add(dependencyNode);
        dimensionDependency.f2046k.add(dependencyNode);
    }

    abstract void d();

    abstract void e();

    abstract void f();

    protected final int g(int i2, int i3) {
        int max;
        if (i3 == 0) {
            ConstraintWidget constraintWidget = this.f2080b;
            int i4 = constraintWidget.A;
            max = Math.max(constraintWidget.z, i2);
            if (i4 > 0) {
                max = Math.min(i4, i2);
            }
            if (max == i2) {
                return i2;
            }
        } else {
            ConstraintWidget constraintWidget2 = this.f2080b;
            int i5 = constraintWidget2.D;
            max = Math.max(constraintWidget2.C, i2);
            if (i5 > 0) {
                max = Math.min(i5, i2);
            }
            if (max == i2) {
                return i2;
            }
        }
        return max;
    }

    protected final DependencyNode h(ConstraintAnchor constraintAnchor) {
        ConstraintAnchor constraintAnchor2 = constraintAnchor.f1965f;
        if (constraintAnchor2 == null) {
            return null;
        }
        ConstraintWidget constraintWidget = constraintAnchor2.f1963d;
        int i2 = AnonymousClass1.f2089a[constraintAnchor2.f1964e.ordinal()];
        if (i2 == 1) {
            return constraintWidget.f1973e.f2086h;
        }
        if (i2 == 2) {
            return constraintWidget.f1973e.f2087i;
        }
        if (i2 == 3) {
            return constraintWidget.f1974f.f2086h;
        }
        if (i2 == 4) {
            return constraintWidget.f1974f.f2062k;
        }
        if (i2 != 5) {
            return null;
        }
        return constraintWidget.f1974f.f2087i;
    }

    protected final DependencyNode i(ConstraintAnchor constraintAnchor, int i2) {
        ConstraintAnchor constraintAnchor2 = constraintAnchor.f1965f;
        if (constraintAnchor2 == null) {
            return null;
        }
        ConstraintWidget constraintWidget = constraintAnchor2.f1963d;
        WidgetRun widgetRun = i2 == 0 ? constraintWidget.f1973e : constraintWidget.f1974f;
        int i3 = AnonymousClass1.f2089a[constraintAnchor2.f1964e.ordinal()];
        if (i3 != 1) {
            if (i3 != 2) {
                if (i3 != 3) {
                    if (i3 != 5) {
                        return null;
                    }
                }
            }
            return widgetRun.f2087i;
        }
        return widgetRun.f2086h;
    }

    public long j() {
        if (this.f2083e.f2045j) {
            return r2.f2042g;
        }
        return 0L;
    }

    public boolean k() {
        return this.f2085g;
    }

    abstract boolean m();

    protected void n(Dependency dependency, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i2) {
        DependencyNode h2 = h(constraintAnchor);
        DependencyNode h3 = h(constraintAnchor2);
        if (h2.f2045j && h3.f2045j) {
            int f2 = h2.f2042g + constraintAnchor.f();
            int f3 = h3.f2042g - constraintAnchor2.f();
            int i3 = f3 - f2;
            if (!this.f2083e.f2045j && this.f2082d == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                l(i2, i3);
            }
            DimensionDependency dimensionDependency = this.f2083e;
            if (dimensionDependency.f2045j) {
                if (dimensionDependency.f2042g == i3) {
                    this.f2086h.d(f2);
                    this.f2087i.d(f3);
                    return;
                }
                float A = i2 == 0 ? this.f2080b.A() : this.f2080b.T();
                if (h2 == h3) {
                    f2 = h2.f2042g;
                    f3 = h3.f2042g;
                    A = 0.5f;
                }
                this.f2086h.d((int) (f2 + 0.5f + (((f3 - f2) - this.f2083e.f2042g) * A)));
                this.f2087i.d(this.f2086h.f2042g + this.f2083e.f2042g);
            }
        }
    }

    protected void o(Dependency dependency) {
    }

    protected void p(Dependency dependency) {
    }
}
