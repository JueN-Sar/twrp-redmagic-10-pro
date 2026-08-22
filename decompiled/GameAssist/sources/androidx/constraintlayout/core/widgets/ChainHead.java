package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.widgets.ConstraintWidget;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class ChainHead {

    /* renamed from: a, reason: collision with root package name */
    protected ConstraintWidget f1941a;

    /* renamed from: b, reason: collision with root package name */
    protected ConstraintWidget f1942b;

    /* renamed from: c, reason: collision with root package name */
    protected ConstraintWidget f1943c;

    /* renamed from: d, reason: collision with root package name */
    protected ConstraintWidget f1944d;

    /* renamed from: e, reason: collision with root package name */
    protected ConstraintWidget f1945e;

    /* renamed from: f, reason: collision with root package name */
    protected ConstraintWidget f1946f;

    /* renamed from: g, reason: collision with root package name */
    protected ConstraintWidget f1947g;

    /* renamed from: h, reason: collision with root package name */
    protected ArrayList f1948h;

    /* renamed from: i, reason: collision with root package name */
    protected int f1949i;

    /* renamed from: j, reason: collision with root package name */
    protected int f1950j;

    /* renamed from: k, reason: collision with root package name */
    protected float f1951k = 0.0f;

    /* renamed from: l, reason: collision with root package name */
    int f1952l;

    /* renamed from: m, reason: collision with root package name */
    int f1953m;

    /* renamed from: n, reason: collision with root package name */
    int f1954n;

    /* renamed from: o, reason: collision with root package name */
    boolean f1955o;

    /* renamed from: p, reason: collision with root package name */
    private int f1956p;

    /* renamed from: q, reason: collision with root package name */
    private boolean f1957q;

    /* renamed from: r, reason: collision with root package name */
    protected boolean f1958r;

    /* renamed from: s, reason: collision with root package name */
    protected boolean f1959s;
    protected boolean t;
    protected boolean u;
    private boolean v;

    public ChainHead(ConstraintWidget constraintWidget, int i2, boolean z) {
        this.f1941a = constraintWidget;
        this.f1956p = i2;
        this.f1957q = z;
    }

    private void b() {
        int i2 = this.f1956p * 2;
        ConstraintWidget constraintWidget = this.f1941a;
        this.f1955o = true;
        ConstraintWidget constraintWidget2 = constraintWidget;
        boolean z = false;
        while (!z) {
            this.f1949i++;
            ConstraintWidget[] constraintWidgetArr = constraintWidget.P0;
            int i3 = this.f1956p;
            ConstraintWidget constraintWidget3 = null;
            constraintWidgetArr[i3] = null;
            constraintWidget.O0[i3] = null;
            if (constraintWidget.X() != 8) {
                this.f1952l++;
                ConstraintWidget.DimensionBehaviour w = constraintWidget.w(this.f1956p);
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (w != dimensionBehaviour) {
                    this.f1953m += constraintWidget.G(this.f1956p);
                }
                int f2 = this.f1953m + constraintWidget.Y[i2].f();
                this.f1953m = f2;
                int i4 = i2 + 1;
                this.f1953m = f2 + constraintWidget.Y[i4].f();
                int f3 = this.f1954n + constraintWidget.Y[i2].f();
                this.f1954n = f3;
                this.f1954n = f3 + constraintWidget.Y[i4].f();
                if (this.f1942b == null) {
                    this.f1942b = constraintWidget;
                }
                this.f1944d = constraintWidget;
                ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = constraintWidget.b0;
                int i5 = this.f1956p;
                if (dimensionBehaviourArr[i5] == dimensionBehaviour) {
                    int i6 = constraintWidget.y[i5];
                    if (i6 == 0 || i6 == 3 || i6 == 2) {
                        this.f1950j++;
                        float f4 = constraintWidget.N0[i5];
                        if (f4 > 0.0f) {
                            this.f1951k += f4;
                        }
                        if (c(constraintWidget, i5)) {
                            if (f4 < 0.0f) {
                                this.f1958r = true;
                            } else {
                                this.f1959s = true;
                            }
                            if (this.f1948h == null) {
                                this.f1948h = new ArrayList();
                            }
                            this.f1948h.add(constraintWidget);
                        }
                        if (this.f1946f == null) {
                            this.f1946f = constraintWidget;
                        }
                        ConstraintWidget constraintWidget4 = this.f1947g;
                        if (constraintWidget4 != null) {
                            constraintWidget4.O0[this.f1956p] = constraintWidget;
                        }
                        this.f1947g = constraintWidget;
                    }
                    if (this.f1956p == 0) {
                        if (constraintWidget.w != 0) {
                            this.f1955o = false;
                        } else if (constraintWidget.z != 0 || constraintWidget.A != 0) {
                            this.f1955o = false;
                        }
                    } else if (constraintWidget.x != 0) {
                        this.f1955o = false;
                    } else if (constraintWidget.C != 0 || constraintWidget.D != 0) {
                        this.f1955o = false;
                    }
                    if (constraintWidget.f0 != 0.0f) {
                        this.f1955o = false;
                        this.u = true;
                    }
                }
            }
            if (constraintWidget2 != constraintWidget) {
                constraintWidget2.P0[this.f1956p] = constraintWidget;
            }
            ConstraintAnchor constraintAnchor = constraintWidget.Y[i2 + 1].f1965f;
            if (constraintAnchor != null) {
                ConstraintWidget constraintWidget5 = constraintAnchor.f1963d;
                ConstraintAnchor constraintAnchor2 = constraintWidget5.Y[i2].f1965f;
                if (constraintAnchor2 != null && constraintAnchor2.f1963d == constraintWidget) {
                    constraintWidget3 = constraintWidget5;
                }
            }
            if (constraintWidget3 == null) {
                constraintWidget3 = constraintWidget;
                z = true;
            }
            constraintWidget2 = constraintWidget;
            constraintWidget = constraintWidget3;
        }
        ConstraintWidget constraintWidget6 = this.f1942b;
        if (constraintWidget6 != null) {
            this.f1953m -= constraintWidget6.Y[i2].f();
        }
        ConstraintWidget constraintWidget7 = this.f1944d;
        if (constraintWidget7 != null) {
            this.f1953m -= constraintWidget7.Y[i2 + 1].f();
        }
        this.f1943c = constraintWidget;
        if (this.f1956p == 0 && this.f1957q) {
            this.f1945e = constraintWidget;
        } else {
            this.f1945e = this.f1941a;
        }
        this.t = this.f1959s && this.f1958r;
    }

    private static boolean c(ConstraintWidget constraintWidget, int i2) {
        int i3;
        return constraintWidget.X() != 8 && constraintWidget.b0[i2] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && ((i3 = constraintWidget.y[i2]) == 0 || i3 == 3);
    }

    public void a() {
        if (!this.v) {
            b();
        }
        this.v = true;
    }
}
