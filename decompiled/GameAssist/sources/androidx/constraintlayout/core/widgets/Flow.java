package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes.dex */
public class Flow extends VirtualLayout {
    private ConstraintWidget[] H1;
    private int k1 = -1;
    private int l1 = -1;
    private int m1 = -1;
    private int n1 = -1;
    private int o1 = -1;
    private int p1 = -1;
    private float q1 = 0.5f;
    private float r1 = 0.5f;
    private float s1 = 0.5f;
    private float t1 = 0.5f;
    private float u1 = 0.5f;
    private float v1 = 0.5f;
    private int w1 = 0;
    private int x1 = 0;
    private int y1 = 2;
    private int z1 = 2;
    private int A1 = 0;
    private int B1 = -1;
    private int C1 = 0;
    private ArrayList D1 = new ArrayList();
    private ConstraintWidget[] E1 = null;
    private ConstraintWidget[] F1 = null;
    private int[] G1 = null;
    private int I1 = 0;

    private class WidgetsList {

        /* renamed from: a, reason: collision with root package name */
        private int f1989a;

        /* renamed from: d, reason: collision with root package name */
        private ConstraintAnchor f1992d;

        /* renamed from: e, reason: collision with root package name */
        private ConstraintAnchor f1993e;

        /* renamed from: f, reason: collision with root package name */
        private ConstraintAnchor f1994f;

        /* renamed from: g, reason: collision with root package name */
        private ConstraintAnchor f1995g;

        /* renamed from: h, reason: collision with root package name */
        private int f1996h;

        /* renamed from: i, reason: collision with root package name */
        private int f1997i;

        /* renamed from: j, reason: collision with root package name */
        private int f1998j;

        /* renamed from: k, reason: collision with root package name */
        private int f1999k;

        /* renamed from: q, reason: collision with root package name */
        private int f2005q;

        /* renamed from: b, reason: collision with root package name */
        private ConstraintWidget f1990b = null;

        /* renamed from: c, reason: collision with root package name */
        int f1991c = 0;

        /* renamed from: l, reason: collision with root package name */
        private int f2000l = 0;

        /* renamed from: m, reason: collision with root package name */
        private int f2001m = 0;

        /* renamed from: n, reason: collision with root package name */
        private int f2002n = 0;

        /* renamed from: o, reason: collision with root package name */
        private int f2003o = 0;

        /* renamed from: p, reason: collision with root package name */
        private int f2004p = 0;

        WidgetsList(int i2, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, ConstraintAnchor constraintAnchor3, ConstraintAnchor constraintAnchor4, int i3) {
            this.f1996h = 0;
            this.f1997i = 0;
            this.f1998j = 0;
            this.f1999k = 0;
            this.f2005q = 0;
            this.f1989a = i2;
            this.f1992d = constraintAnchor;
            this.f1993e = constraintAnchor2;
            this.f1994f = constraintAnchor3;
            this.f1995g = constraintAnchor4;
            this.f1996h = Flow.this.E1();
            this.f1997i = Flow.this.G1();
            this.f1998j = Flow.this.F1();
            this.f1999k = Flow.this.D1();
            this.f2005q = i3;
        }

        private void h() {
            this.f2000l = 0;
            this.f2001m = 0;
            this.f1990b = null;
            this.f1991c = 0;
            int i2 = this.f2003o;
            for (int i3 = 0; i3 < i2 && this.f2002n + i3 < Flow.this.I1; i3++) {
                ConstraintWidget constraintWidget = Flow.this.H1[this.f2002n + i3];
                if (this.f1989a == 0) {
                    int Y = constraintWidget.Y();
                    int i4 = Flow.this.w1;
                    if (constraintWidget.X() == 8) {
                        i4 = 0;
                    }
                    this.f2000l += Y + i4;
                    int p2 = Flow.this.p2(constraintWidget, this.f2005q);
                    if (this.f1990b == null || this.f1991c < p2) {
                        this.f1990b = constraintWidget;
                        this.f1991c = p2;
                        this.f2001m = p2;
                    }
                } else {
                    int q2 = Flow.this.q2(constraintWidget, this.f2005q);
                    int p22 = Flow.this.p2(constraintWidget, this.f2005q);
                    int i5 = Flow.this.x1;
                    if (constraintWidget.X() == 8) {
                        i5 = 0;
                    }
                    this.f2001m += p22 + i5;
                    if (this.f1990b == null || this.f1991c < q2) {
                        this.f1990b = constraintWidget;
                        this.f1991c = q2;
                        this.f2000l = q2;
                    }
                }
            }
        }

        public void b(ConstraintWidget constraintWidget) {
            if (this.f1989a == 0) {
                int q2 = Flow.this.q2(constraintWidget, this.f2005q);
                if (constraintWidget.C() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    this.f2004p++;
                    q2 = 0;
                }
                this.f2000l += q2 + (constraintWidget.X() != 8 ? Flow.this.w1 : 0);
                int p2 = Flow.this.p2(constraintWidget, this.f2005q);
                if (this.f1990b == null || this.f1991c < p2) {
                    this.f1990b = constraintWidget;
                    this.f1991c = p2;
                    this.f2001m = p2;
                }
            } else {
                int q22 = Flow.this.q2(constraintWidget, this.f2005q);
                int p22 = Flow.this.p2(constraintWidget, this.f2005q);
                if (constraintWidget.V() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    this.f2004p++;
                    p22 = 0;
                }
                this.f2001m += p22 + (constraintWidget.X() != 8 ? Flow.this.x1 : 0);
                if (this.f1990b == null || this.f1991c < q22) {
                    this.f1990b = constraintWidget;
                    this.f1991c = q22;
                    this.f2000l = q22;
                }
            }
            this.f2003o++;
        }

        public void c() {
            this.f1991c = 0;
            this.f1990b = null;
            this.f2000l = 0;
            this.f2001m = 0;
            this.f2002n = 0;
            this.f2003o = 0;
            this.f2004p = 0;
        }

        public void d(boolean z, int i2, boolean z2) {
            ConstraintWidget constraintWidget;
            char c2;
            float f2;
            float f3;
            int i3 = this.f2003o;
            for (int i4 = 0; i4 < i3 && this.f2002n + i4 < Flow.this.I1; i4++) {
                ConstraintWidget constraintWidget2 = Flow.this.H1[this.f2002n + i4];
                if (constraintWidget2 != null) {
                    constraintWidget2.x0();
                }
            }
            if (i3 == 0 || this.f1990b == null) {
                return;
            }
            boolean z3 = z2 && i2 == 0;
            int i5 = -1;
            int i6 = -1;
            for (int i7 = 0; i7 < i3; i7++) {
                int i8 = z ? (i3 - 1) - i7 : i7;
                if (this.f2002n + i8 >= Flow.this.I1) {
                    break;
                }
                ConstraintWidget constraintWidget3 = Flow.this.H1[this.f2002n + i8];
                if (constraintWidget3 != null && constraintWidget3.X() == 0) {
                    if (i5 == -1) {
                        i5 = i7;
                    }
                    i6 = i7;
                }
            }
            ConstraintWidget constraintWidget4 = null;
            if (this.f1989a != 0) {
                ConstraintWidget constraintWidget5 = this.f1990b;
                constraintWidget5.S0(Flow.this.k1);
                int i9 = this.f1996h;
                if (i2 > 0) {
                    i9 += Flow.this.w1;
                }
                if (z) {
                    constraintWidget5.S.a(this.f1994f, i9);
                    if (z2) {
                        constraintWidget5.Q.a(this.f1992d, this.f1998j);
                    }
                    if (i2 > 0) {
                        this.f1994f.f1963d.Q.a(constraintWidget5.S, 0);
                    }
                } else {
                    constraintWidget5.Q.a(this.f1992d, i9);
                    if (z2) {
                        constraintWidget5.S.a(this.f1994f, this.f1998j);
                    }
                    if (i2 > 0) {
                        this.f1992d.f1963d.S.a(constraintWidget5.Q, 0);
                    }
                }
                for (int i10 = 0; i10 < i3 && this.f2002n + i10 < Flow.this.I1; i10++) {
                    ConstraintWidget constraintWidget6 = Flow.this.H1[this.f2002n + i10];
                    if (constraintWidget6 != null) {
                        if (i10 == 0) {
                            constraintWidget6.l(constraintWidget6.R, this.f1993e, this.f1997i);
                            int i11 = Flow.this.l1;
                            float f4 = Flow.this.r1;
                            if (this.f2002n == 0 && Flow.this.n1 != -1) {
                                i11 = Flow.this.n1;
                                f4 = Flow.this.t1;
                            } else if (z2 && Flow.this.p1 != -1) {
                                i11 = Flow.this.p1;
                                f4 = Flow.this.v1;
                            }
                            constraintWidget6.j1(i11);
                            constraintWidget6.i1(f4);
                        }
                        if (i10 == i3 - 1) {
                            constraintWidget6.l(constraintWidget6.T, this.f1995g, this.f1999k);
                        }
                        if (constraintWidget4 != null) {
                            constraintWidget6.R.a(constraintWidget4.T, Flow.this.x1);
                            if (i10 == i5) {
                                constraintWidget6.R.u(this.f1997i);
                            }
                            constraintWidget4.T.a(constraintWidget6.R, 0);
                            if (i10 == i6 + 1) {
                                constraintWidget4.T.u(this.f1999k);
                            }
                        }
                        if (constraintWidget6 != constraintWidget5) {
                            if (z) {
                                int i12 = Flow.this.y1;
                                if (i12 == 0) {
                                    constraintWidget6.S.a(constraintWidget5.S, 0);
                                } else if (i12 == 1) {
                                    constraintWidget6.Q.a(constraintWidget5.Q, 0);
                                } else if (i12 == 2) {
                                    constraintWidget6.Q.a(constraintWidget5.Q, 0);
                                    constraintWidget6.S.a(constraintWidget5.S, 0);
                                }
                            } else {
                                int i13 = Flow.this.y1;
                                if (i13 == 0) {
                                    constraintWidget6.Q.a(constraintWidget5.Q, 0);
                                } else if (i13 == 1) {
                                    constraintWidget6.S.a(constraintWidget5.S, 0);
                                } else if (i13 == 2) {
                                    if (z3) {
                                        constraintWidget6.Q.a(this.f1992d, this.f1996h);
                                        constraintWidget6.S.a(this.f1994f, this.f1998j);
                                    } else {
                                        constraintWidget6.Q.a(constraintWidget5.Q, 0);
                                        constraintWidget6.S.a(constraintWidget5.S, 0);
                                    }
                                }
                                constraintWidget4 = constraintWidget6;
                            }
                        }
                        constraintWidget4 = constraintWidget6;
                    }
                }
                return;
            }
            ConstraintWidget constraintWidget7 = this.f1990b;
            constraintWidget7.j1(Flow.this.l1);
            int i14 = this.f1997i;
            if (i2 > 0) {
                i14 += Flow.this.x1;
            }
            constraintWidget7.R.a(this.f1993e, i14);
            if (z2) {
                constraintWidget7.T.a(this.f1995g, this.f1999k);
            }
            if (i2 > 0) {
                this.f1993e.f1963d.T.a(constraintWidget7.R, 0);
            }
            char c3 = 3;
            if (Flow.this.z1 == 3 && !constraintWidget7.b0()) {
                for (int i15 = 0; i15 < i3; i15++) {
                    int i16 = z ? (i3 - 1) - i15 : i15;
                    if (this.f2002n + i16 >= Flow.this.I1) {
                        break;
                    }
                    constraintWidget = Flow.this.H1[this.f2002n + i16];
                    if (constraintWidget.b0()) {
                        break;
                    }
                }
            }
            constraintWidget = constraintWidget7;
            int i17 = 0;
            while (i17 < i3) {
                int i18 = z ? (i3 - 1) - i17 : i17;
                if (this.f2002n + i18 >= Flow.this.I1) {
                    return;
                }
                ConstraintWidget constraintWidget8 = Flow.this.H1[this.f2002n + i18];
                if (constraintWidget8 == null) {
                    constraintWidget8 = constraintWidget4;
                    c2 = c3;
                } else {
                    if (i17 == 0) {
                        constraintWidget8.l(constraintWidget8.Q, this.f1992d, this.f1996h);
                    }
                    if (i18 == 0) {
                        int i19 = Flow.this.k1;
                        float f5 = Flow.this.q1;
                        if (z) {
                            f5 = 1.0f - f5;
                        }
                        if (this.f2002n == 0 && Flow.this.m1 != -1) {
                            i19 = Flow.this.m1;
                            if (z) {
                                f3 = Flow.this.s1;
                                f2 = 1.0f - f3;
                                f5 = f2;
                            } else {
                                f2 = Flow.this.s1;
                                f5 = f2;
                            }
                        } else if (z2 && Flow.this.o1 != -1) {
                            i19 = Flow.this.o1;
                            if (z) {
                                f3 = Flow.this.u1;
                                f2 = 1.0f - f3;
                                f5 = f2;
                            } else {
                                f2 = Flow.this.u1;
                                f5 = f2;
                            }
                        }
                        constraintWidget8.S0(i19);
                        constraintWidget8.R0(f5);
                    }
                    if (i17 == i3 - 1) {
                        constraintWidget8.l(constraintWidget8.S, this.f1994f, this.f1998j);
                    }
                    if (constraintWidget4 != null) {
                        constraintWidget8.Q.a(constraintWidget4.S, Flow.this.w1);
                        if (i17 == i5) {
                            constraintWidget8.Q.u(this.f1996h);
                        }
                        constraintWidget4.S.a(constraintWidget8.Q, 0);
                        if (i17 == i6 + 1) {
                            constraintWidget4.S.u(this.f1998j);
                        }
                    }
                    if (constraintWidget8 != constraintWidget7) {
                        c2 = 3;
                        if (Flow.this.z1 == 3 && constraintWidget.b0() && constraintWidget8 != constraintWidget && constraintWidget8.b0()) {
                            constraintWidget8.U.a(constraintWidget.U, 0);
                        } else {
                            int i20 = Flow.this.z1;
                            if (i20 == 0) {
                                constraintWidget8.R.a(constraintWidget7.R, 0);
                            } else if (i20 == 1) {
                                constraintWidget8.T.a(constraintWidget7.T, 0);
                            } else if (z3) {
                                constraintWidget8.R.a(this.f1993e, this.f1997i);
                                constraintWidget8.T.a(this.f1995g, this.f1999k);
                            } else {
                                constraintWidget8.R.a(constraintWidget7.R, 0);
                                constraintWidget8.T.a(constraintWidget7.T, 0);
                            }
                        }
                    } else {
                        c2 = 3;
                    }
                }
                i17++;
                c3 = c2;
                constraintWidget4 = constraintWidget8;
            }
        }

        public int e() {
            return this.f1989a == 1 ? this.f2001m - Flow.this.x1 : this.f2001m;
        }

        public int f() {
            return this.f1989a == 0 ? this.f2000l - Flow.this.w1 : this.f2000l;
        }

        public void g(int i2) {
            int i3 = this.f2004p;
            if (i3 == 0) {
                return;
            }
            int i4 = this.f2003o;
            int i5 = i2 / i3;
            for (int i6 = 0; i6 < i4 && this.f2002n + i6 < Flow.this.I1; i6++) {
                ConstraintWidget constraintWidget = Flow.this.H1[this.f2002n + i6];
                if (this.f1989a == 0) {
                    if (constraintWidget != null && constraintWidget.C() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.w == 0) {
                        Flow.this.I1(constraintWidget, ConstraintWidget.DimensionBehaviour.FIXED, i5, constraintWidget.V(), constraintWidget.z());
                    }
                } else if (constraintWidget != null && constraintWidget.V() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.x == 0) {
                    Flow.this.I1(constraintWidget, constraintWidget.C(), constraintWidget.Y(), ConstraintWidget.DimensionBehaviour.FIXED, i5);
                }
            }
            h();
        }

        public void i(int i2) {
            this.f2002n = i2;
        }

        public void j(int i2, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, ConstraintAnchor constraintAnchor3, ConstraintAnchor constraintAnchor4, int i3, int i4, int i5, int i6, int i7) {
            this.f1989a = i2;
            this.f1992d = constraintAnchor;
            this.f1993e = constraintAnchor2;
            this.f1994f = constraintAnchor3;
            this.f1995g = constraintAnchor4;
            this.f1996h = i3;
            this.f1997i = i4;
            this.f1998j = i5;
            this.f1999k = i6;
            this.f2005q = i7;
        }
    }

    private void o2(boolean z) {
        ConstraintWidget constraintWidget;
        float f2;
        int i2;
        if (this.G1 == null || this.F1 == null || this.E1 == null) {
            return;
        }
        for (int i3 = 0; i3 < this.I1; i3++) {
            this.H1[i3].x0();
        }
        int[] iArr = this.G1;
        int i4 = iArr[0];
        int i5 = iArr[1];
        float f3 = this.q1;
        ConstraintWidget constraintWidget2 = null;
        int i6 = 0;
        while (i6 < i4) {
            if (z) {
                i2 = (i4 - i6) - 1;
                f2 = 1.0f - this.q1;
            } else {
                f2 = f3;
                i2 = i6;
            }
            ConstraintWidget constraintWidget3 = this.F1[i2];
            if (constraintWidget3 != null && constraintWidget3.X() != 8) {
                if (i6 == 0) {
                    constraintWidget3.l(constraintWidget3.Q, this.Q, E1());
                    constraintWidget3.S0(this.k1);
                    constraintWidget3.R0(f2);
                }
                if (i6 == i4 - 1) {
                    constraintWidget3.l(constraintWidget3.S, this.S, F1());
                }
                if (i6 > 0 && constraintWidget2 != null) {
                    constraintWidget3.l(constraintWidget3.Q, constraintWidget2.S, this.w1);
                    constraintWidget2.l(constraintWidget2.S, constraintWidget3.Q, 0);
                }
                constraintWidget2 = constraintWidget3;
            }
            i6++;
            f3 = f2;
        }
        for (int i7 = 0; i7 < i5; i7++) {
            ConstraintWidget constraintWidget4 = this.E1[i7];
            if (constraintWidget4 != null && constraintWidget4.X() != 8) {
                if (i7 == 0) {
                    constraintWidget4.l(constraintWidget4.R, this.R, G1());
                    constraintWidget4.j1(this.l1);
                    constraintWidget4.i1(this.r1);
                }
                if (i7 == i5 - 1) {
                    constraintWidget4.l(constraintWidget4.T, this.T, D1());
                }
                if (i7 > 0 && constraintWidget2 != null) {
                    constraintWidget4.l(constraintWidget4.R, constraintWidget2.T, this.x1);
                    constraintWidget2.l(constraintWidget2.T, constraintWidget4.R, 0);
                }
                constraintWidget2 = constraintWidget4;
            }
        }
        for (int i8 = 0; i8 < i4; i8++) {
            for (int i9 = 0; i9 < i5; i9++) {
                int i10 = (i9 * i4) + i8;
                if (this.C1 == 1) {
                    i10 = (i8 * i5) + i9;
                }
                ConstraintWidget[] constraintWidgetArr = this.H1;
                if (i10 < constraintWidgetArr.length && (constraintWidget = constraintWidgetArr[i10]) != null && constraintWidget.X() != 8) {
                    ConstraintWidget constraintWidget5 = this.F1[i8];
                    ConstraintWidget constraintWidget6 = this.E1[i9];
                    if (constraintWidget != constraintWidget5) {
                        constraintWidget.l(constraintWidget.Q, constraintWidget5.Q, 0);
                        constraintWidget.l(constraintWidget.S, constraintWidget5.S, 0);
                    }
                    if (constraintWidget != constraintWidget6) {
                        constraintWidget.l(constraintWidget.R, constraintWidget6.R, 0);
                        constraintWidget.l(constraintWidget.T, constraintWidget6.T, 0);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int p2(ConstraintWidget constraintWidget, int i2) {
        if (constraintWidget == null) {
            return 0;
        }
        if (constraintWidget.V() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            int i3 = constraintWidget.x;
            if (i3 == 0) {
                return 0;
            }
            if (i3 == 2) {
                int i4 = (int) (constraintWidget.E * i2);
                if (i4 != constraintWidget.z()) {
                    constraintWidget.d1(true);
                    I1(constraintWidget, constraintWidget.C(), constraintWidget.Y(), ConstraintWidget.DimensionBehaviour.FIXED, i4);
                }
                return i4;
            }
            if (i3 == 1) {
                return constraintWidget.z();
            }
            if (i3 == 3) {
                return (int) ((constraintWidget.Y() * constraintWidget.f0) + 0.5f);
            }
        }
        return constraintWidget.z();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int q2(ConstraintWidget constraintWidget, int i2) {
        if (constraintWidget == null) {
            return 0;
        }
        if (constraintWidget.C() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            int i3 = constraintWidget.w;
            if (i3 == 0) {
                return 0;
            }
            if (i3 == 2) {
                int i4 = (int) (constraintWidget.B * i2);
                if (i4 != constraintWidget.Y()) {
                    constraintWidget.d1(true);
                    I1(constraintWidget, ConstraintWidget.DimensionBehaviour.FIXED, i4, constraintWidget.V(), constraintWidget.z());
                }
                return i4;
            }
            if (i3 == 1) {
                return constraintWidget.Y();
            }
            if (i3 == 3) {
                return (int) ((constraintWidget.z() * constraintWidget.f0) + 0.5f);
            }
        }
        return constraintWidget.Y();
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x005e  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:77:0x010d -> B:22:0x0059). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:78:0x010f -> B:22:0x0059). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:80:0x0115 -> B:22:0x0059). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:81:0x0117 -> B:22:0x0059). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void r2(androidx.constraintlayout.core.widgets.ConstraintWidget[] r11, int r12, int r13, int r14, int[] r15) {
        /*
            Method dump skipped, instructions count: 292
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.Flow.r2(androidx.constraintlayout.core.widgets.ConstraintWidget[], int, int, int, int[]):void");
    }

    private void s2(ConstraintWidget[] constraintWidgetArr, int i2, int i3, int i4, int[] iArr) {
        int i5;
        int i6;
        int i7;
        ConstraintAnchor constraintAnchor;
        int F1;
        ConstraintAnchor constraintAnchor2;
        int D1;
        int i8;
        if (i2 == 0) {
            return;
        }
        this.D1.clear();
        WidgetsList widgetsList = new WidgetsList(i3, this.Q, this.R, this.S, this.T, i4);
        this.D1.add(widgetsList);
        if (i3 == 0) {
            i5 = 0;
            int i9 = 0;
            int i10 = 0;
            while (i10 < i2) {
                ConstraintWidget constraintWidget = constraintWidgetArr[i10];
                int q2 = q2(constraintWidget, i4);
                if (constraintWidget.C() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    i5++;
                }
                int i11 = i5;
                boolean z = (i9 == i4 || (this.w1 + i9) + q2 > i4) && widgetsList.f1990b != null;
                if ((!z && i10 > 0 && (i8 = this.B1) > 0 && i10 % i8 == 0) || z) {
                    widgetsList = new WidgetsList(i3, this.Q, this.R, this.S, this.T, i4);
                    widgetsList.i(i10);
                    this.D1.add(widgetsList);
                } else if (i10 > 0) {
                    i9 += this.w1 + q2;
                    widgetsList.b(constraintWidget);
                    i10++;
                    i5 = i11;
                }
                i9 = q2;
                widgetsList.b(constraintWidget);
                i10++;
                i5 = i11;
            }
        } else {
            i5 = 0;
            int i12 = 0;
            int i13 = 0;
            while (i13 < i2) {
                ConstraintWidget constraintWidget2 = constraintWidgetArr[i13];
                int p2 = p2(constraintWidget2, i4);
                if (constraintWidget2.V() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    i5++;
                }
                int i14 = i5;
                boolean z2 = (i12 == i4 || (this.x1 + i12) + p2 > i4) && widgetsList.f1990b != null;
                if ((!z2 && i13 > 0 && (i6 = this.B1) > 0 && i13 % i6 == 0) || z2) {
                    widgetsList = new WidgetsList(i3, this.Q, this.R, this.S, this.T, i4);
                    widgetsList.i(i13);
                    this.D1.add(widgetsList);
                } else if (i13 > 0) {
                    i12 += this.x1 + p2;
                    widgetsList.b(constraintWidget2);
                    i13++;
                    i5 = i14;
                }
                i12 = p2;
                widgetsList.b(constraintWidget2);
                i13++;
                i5 = i14;
            }
        }
        int size = this.D1.size();
        ConstraintAnchor constraintAnchor3 = this.Q;
        ConstraintAnchor constraintAnchor4 = this.R;
        ConstraintAnchor constraintAnchor5 = this.S;
        ConstraintAnchor constraintAnchor6 = this.T;
        int E1 = E1();
        int G1 = G1();
        int F12 = F1();
        int D12 = D1();
        ConstraintWidget.DimensionBehaviour C = C();
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        boolean z3 = C == dimensionBehaviour || V() == dimensionBehaviour;
        if (i5 > 0 && z3) {
            for (int i15 = 0; i15 < size; i15++) {
                WidgetsList widgetsList2 = (WidgetsList) this.D1.get(i15);
                if (i3 == 0) {
                    widgetsList2.g(i4 - widgetsList2.f());
                } else {
                    widgetsList2.g(i4 - widgetsList2.e());
                }
            }
        }
        int i16 = G1;
        int i17 = F12;
        int i18 = 0;
        int i19 = 0;
        int i20 = 0;
        int i21 = E1;
        ConstraintAnchor constraintAnchor7 = constraintAnchor4;
        ConstraintAnchor constraintAnchor8 = constraintAnchor3;
        int i22 = D12;
        while (i20 < size) {
            WidgetsList widgetsList3 = (WidgetsList) this.D1.get(i20);
            if (i3 == 0) {
                if (i20 < size - 1) {
                    constraintAnchor2 = ((WidgetsList) this.D1.get(i20 + 1)).f1990b.R;
                    D1 = 0;
                } else {
                    constraintAnchor2 = this.T;
                    D1 = D1();
                }
                ConstraintAnchor constraintAnchor9 = widgetsList3.f1990b.T;
                ConstraintAnchor constraintAnchor10 = constraintAnchor8;
                ConstraintAnchor constraintAnchor11 = constraintAnchor8;
                int i23 = i18;
                ConstraintAnchor constraintAnchor12 = constraintAnchor7;
                int i24 = i19;
                ConstraintAnchor constraintAnchor13 = constraintAnchor5;
                ConstraintAnchor constraintAnchor14 = constraintAnchor5;
                i7 = i20;
                widgetsList3.j(i3, constraintAnchor10, constraintAnchor12, constraintAnchor13, constraintAnchor2, i21, i16, i17, D1, i4);
                int max = Math.max(i24, widgetsList3.f());
                i18 = i23 + widgetsList3.e();
                if (i7 > 0) {
                    i18 += this.x1;
                }
                constraintAnchor8 = constraintAnchor11;
                i19 = max;
                i16 = 0;
                constraintAnchor7 = constraintAnchor9;
                constraintAnchor = constraintAnchor14;
                int i25 = D1;
                constraintAnchor6 = constraintAnchor2;
                i22 = i25;
            } else {
                ConstraintAnchor constraintAnchor15 = constraintAnchor8;
                int i26 = i18;
                int i27 = i19;
                i7 = i20;
                if (i7 < size - 1) {
                    constraintAnchor = ((WidgetsList) this.D1.get(i7 + 1)).f1990b.Q;
                    F1 = 0;
                } else {
                    constraintAnchor = this.S;
                    F1 = F1();
                }
                ConstraintAnchor constraintAnchor16 = widgetsList3.f1990b.S;
                widgetsList3.j(i3, constraintAnchor15, constraintAnchor7, constraintAnchor, constraintAnchor6, i21, i16, F1, i22, i4);
                i19 = i27 + widgetsList3.f();
                int max2 = Math.max(i26, widgetsList3.e());
                if (i7 > 0) {
                    i19 += this.w1;
                }
                i18 = max2;
                i21 = 0;
                i17 = F1;
                constraintAnchor8 = constraintAnchor16;
            }
            i20 = i7 + 1;
            constraintAnchor5 = constraintAnchor;
        }
        iArr[0] = i19;
        iArr[1] = i18;
    }

    private void t2(ConstraintWidget[] constraintWidgetArr, int i2, int i3, int i4, int[] iArr) {
        int i5;
        int i6;
        int i7;
        ConstraintAnchor constraintAnchor;
        int F1;
        ConstraintAnchor constraintAnchor2;
        int D1;
        int i8;
        if (i2 == 0) {
            return;
        }
        this.D1.clear();
        WidgetsList widgetsList = new WidgetsList(i3, this.Q, this.R, this.S, this.T, i4);
        this.D1.add(widgetsList);
        if (i3 == 0) {
            int i9 = 0;
            i5 = 0;
            int i10 = 0;
            int i11 = 0;
            while (i11 < i2) {
                i9++;
                ConstraintWidget constraintWidget = constraintWidgetArr[i11];
                int q2 = q2(constraintWidget, i4);
                if (constraintWidget.C() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    i5++;
                }
                int i12 = i5;
                boolean z = (i10 == i4 || (this.w1 + i10) + q2 > i4) && widgetsList.f1990b != null;
                if ((z || i11 <= 0 || (i8 = this.B1) <= 0 || i9 <= i8) && !z) {
                    i10 = i11 > 0 ? i10 + this.w1 + q2 : q2;
                } else {
                    widgetsList = new WidgetsList(i3, this.Q, this.R, this.S, this.T, i4);
                    widgetsList.i(i11);
                    this.D1.add(widgetsList);
                    i10 = q2;
                    i9 = 1;
                }
                widgetsList.b(constraintWidget);
                i11++;
                i5 = i12;
            }
        } else {
            int i13 = 0;
            i5 = 0;
            int i14 = 0;
            int i15 = 0;
            while (i15 < i2) {
                i13++;
                ConstraintWidget constraintWidget2 = constraintWidgetArr[i15];
                int p2 = p2(constraintWidget2, i4);
                if (constraintWidget2.V() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    i5++;
                }
                int i16 = i5;
                boolean z2 = (i14 == i4 || (this.x1 + i14) + p2 > i4) && widgetsList.f1990b != null;
                if ((z2 || i15 <= 0 || (i6 = this.B1) <= 0 || i13 <= i6) && !z2) {
                    i14 = i15 > 0 ? i14 + this.x1 + p2 : p2;
                } else {
                    widgetsList = new WidgetsList(i3, this.Q, this.R, this.S, this.T, i4);
                    widgetsList.i(i15);
                    this.D1.add(widgetsList);
                    i14 = p2;
                    i13 = 1;
                }
                widgetsList.b(constraintWidget2);
                i15++;
                i5 = i16;
            }
        }
        int size = this.D1.size();
        ConstraintAnchor constraintAnchor3 = this.Q;
        ConstraintAnchor constraintAnchor4 = this.R;
        ConstraintAnchor constraintAnchor5 = this.S;
        ConstraintAnchor constraintAnchor6 = this.T;
        int E1 = E1();
        int G1 = G1();
        int F12 = F1();
        int D12 = D1();
        ConstraintWidget.DimensionBehaviour C = C();
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        boolean z3 = C == dimensionBehaviour || V() == dimensionBehaviour;
        if (i5 > 0 && z3) {
            for (int i17 = 0; i17 < size; i17++) {
                WidgetsList widgetsList2 = (WidgetsList) this.D1.get(i17);
                if (i3 == 0) {
                    widgetsList2.g(i4 - widgetsList2.f());
                } else {
                    widgetsList2.g(i4 - widgetsList2.e());
                }
            }
        }
        int i18 = G1;
        int i19 = F12;
        int i20 = 0;
        int i21 = 0;
        int i22 = 0;
        int i23 = E1;
        ConstraintAnchor constraintAnchor7 = constraintAnchor4;
        ConstraintAnchor constraintAnchor8 = constraintAnchor3;
        int i24 = D12;
        while (i22 < size) {
            WidgetsList widgetsList3 = (WidgetsList) this.D1.get(i22);
            if (i3 == 0) {
                if (i22 < size - 1) {
                    constraintAnchor2 = ((WidgetsList) this.D1.get(i22 + 1)).f1990b.R;
                    D1 = 0;
                } else {
                    constraintAnchor2 = this.T;
                    D1 = D1();
                }
                ConstraintAnchor constraintAnchor9 = widgetsList3.f1990b.T;
                ConstraintAnchor constraintAnchor10 = constraintAnchor8;
                ConstraintAnchor constraintAnchor11 = constraintAnchor8;
                int i25 = i20;
                ConstraintAnchor constraintAnchor12 = constraintAnchor7;
                int i26 = i21;
                ConstraintAnchor constraintAnchor13 = constraintAnchor5;
                ConstraintAnchor constraintAnchor14 = constraintAnchor5;
                i7 = i22;
                widgetsList3.j(i3, constraintAnchor10, constraintAnchor12, constraintAnchor13, constraintAnchor2, i23, i18, i19, D1, i4);
                int max = Math.max(i26, widgetsList3.f());
                i20 = i25 + widgetsList3.e();
                if (i7 > 0) {
                    i20 += this.x1;
                }
                constraintAnchor8 = constraintAnchor11;
                i21 = max;
                i18 = 0;
                constraintAnchor7 = constraintAnchor9;
                constraintAnchor = constraintAnchor14;
                int i27 = D1;
                constraintAnchor6 = constraintAnchor2;
                i24 = i27;
            } else {
                ConstraintAnchor constraintAnchor15 = constraintAnchor8;
                int i28 = i20;
                int i29 = i21;
                i7 = i22;
                if (i7 < size - 1) {
                    constraintAnchor = ((WidgetsList) this.D1.get(i7 + 1)).f1990b.Q;
                    F1 = 0;
                } else {
                    constraintAnchor = this.S;
                    F1 = F1();
                }
                ConstraintAnchor constraintAnchor16 = widgetsList3.f1990b.S;
                widgetsList3.j(i3, constraintAnchor15, constraintAnchor7, constraintAnchor, constraintAnchor6, i23, i18, F1, i24, i4);
                i21 = i29 + widgetsList3.f();
                int max2 = Math.max(i28, widgetsList3.e());
                if (i7 > 0) {
                    i21 += this.w1;
                }
                i20 = max2;
                i23 = 0;
                i19 = F1;
                constraintAnchor8 = constraintAnchor16;
            }
            i22 = i7 + 1;
            constraintAnchor5 = constraintAnchor;
        }
        iArr[0] = i21;
        iArr[1] = i20;
    }

    private void u2(ConstraintWidget[] constraintWidgetArr, int i2, int i3, int i4, int[] iArr) {
        WidgetsList widgetsList;
        if (i2 == 0) {
            return;
        }
        if (this.D1.size() == 0) {
            widgetsList = new WidgetsList(i3, this.Q, this.R, this.S, this.T, i4);
            this.D1.add(widgetsList);
        } else {
            WidgetsList widgetsList2 = (WidgetsList) this.D1.get(0);
            widgetsList2.c();
            widgetsList = widgetsList2;
            widgetsList.j(i3, this.Q, this.R, this.S, this.T, E1(), G1(), F1(), D1(), i4);
        }
        for (int i5 = 0; i5 < i2; i5++) {
            widgetsList.b(constraintWidgetArr[i5]);
        }
        iArr[0] = widgetsList.f();
        iArr[1] = widgetsList.e();
    }

    public void A2(float f2) {
        this.q1 = f2;
    }

    public void B2(int i2) {
        this.w1 = i2;
    }

    public void C2(int i2) {
        this.k1 = i2;
    }

    public void D2(float f2) {
        this.u1 = f2;
    }

    public void E2(int i2) {
        this.o1 = i2;
    }

    public void F2(float f2) {
        this.v1 = f2;
    }

    public void G2(int i2) {
        this.p1 = i2;
    }

    @Override // androidx.constraintlayout.core.widgets.VirtualLayout
    public void H1(int i2, int i3, int i4, int i5) {
        int i6;
        int i7;
        int[] iArr;
        boolean z;
        if (this.W0 > 0 && !J1()) {
            M1(0, 0);
            L1(false);
            return;
        }
        int E1 = E1();
        int F1 = F1();
        int G1 = G1();
        int D1 = D1();
        int[] iArr2 = new int[2];
        int i8 = (i3 - E1) - F1;
        int i9 = this.C1;
        if (i9 == 1) {
            i8 = (i5 - G1) - D1;
        }
        int i10 = i8;
        if (i9 == 0) {
            if (this.k1 == -1) {
                this.k1 = 0;
            }
            if (this.l1 == -1) {
                this.l1 = 0;
            }
        } else {
            if (this.k1 == -1) {
                this.k1 = 0;
            }
            if (this.l1 == -1) {
                this.l1 = 0;
            }
        }
        ConstraintWidget[] constraintWidgetArr = this.V0;
        int i11 = 0;
        int i12 = 0;
        while (true) {
            i6 = this.W0;
            if (i11 >= i6) {
                break;
            }
            if (this.V0[i11].X() == 8) {
                i12++;
            }
            i11++;
        }
        if (i12 > 0) {
            constraintWidgetArr = new ConstraintWidget[i6 - i12];
            int i13 = 0;
            for (int i14 = 0; i14 < this.W0; i14++) {
                ConstraintWidget constraintWidget = this.V0[i14];
                if (constraintWidget.X() != 8) {
                    constraintWidgetArr[i13] = constraintWidget;
                    i13++;
                }
            }
            i7 = i13;
        } else {
            i7 = i6;
        }
        this.H1 = constraintWidgetArr;
        this.I1 = i7;
        int i15 = this.A1;
        if (i15 == 0) {
            iArr = iArr2;
            z = true;
            u2(constraintWidgetArr, i7, this.C1, i10, iArr2);
        } else if (i15 == 1) {
            z = true;
            iArr = iArr2;
            s2(constraintWidgetArr, i7, this.C1, i10, iArr2);
        } else if (i15 == 2) {
            z = true;
            iArr = iArr2;
            r2(constraintWidgetArr, i7, this.C1, i10, iArr2);
        } else if (i15 != 3) {
            z = true;
            iArr = iArr2;
        } else {
            z = true;
            iArr = iArr2;
            t2(constraintWidgetArr, i7, this.C1, i10, iArr2);
        }
        int i16 = iArr[0] + E1 + F1;
        int i17 = iArr[z ? 1 : 0] + G1 + D1;
        if (i2 == 1073741824) {
            i16 = i3;
        } else if (i2 == Integer.MIN_VALUE) {
            i16 = Math.min(i16, i3);
        } else if (i2 != 0) {
            i16 = 0;
        }
        if (i4 == 1073741824) {
            i17 = i5;
        } else if (i4 == Integer.MIN_VALUE) {
            i17 = Math.min(i17, i5);
        } else if (i4 != 0) {
            i17 = 0;
        }
        M1(i16, i17);
        p1(i16);
        Q0(i17);
        if (this.W0 <= 0) {
            z = false;
        }
        L1(z);
    }

    public void H2(int i2) {
        this.B1 = i2;
    }

    public void I2(int i2) {
        this.C1 = i2;
    }

    public void J2(int i2) {
        this.z1 = i2;
    }

    public void K2(float f2) {
        this.r1 = f2;
    }

    public void L2(int i2) {
        this.x1 = i2;
    }

    public void M2(int i2) {
        this.l1 = i2;
    }

    public void N2(int i2) {
        this.A1 = i2;
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public void g(LinearSystem linearSystem, boolean z) {
        super.g(linearSystem, z);
        boolean z2 = M() != null && ((ConstraintWidgetContainer) M()).W1();
        int i2 = this.A1;
        if (i2 != 0) {
            if (i2 == 1) {
                int size = this.D1.size();
                int i3 = 0;
                while (i3 < size) {
                    ((WidgetsList) this.D1.get(i3)).d(z2, i3, i3 == size + (-1));
                    i3++;
                }
            } else if (i2 == 2) {
                o2(z2);
            } else if (i2 == 3) {
                int size2 = this.D1.size();
                int i4 = 0;
                while (i4 < size2) {
                    ((WidgetsList) this.D1.get(i4)).d(z2, i4, i4 == size2 + (-1));
                    i4++;
                }
            }
        } else if (this.D1.size() > 0) {
            ((WidgetsList) this.D1.get(0)).d(z2, 0, true);
        }
        L1(false);
    }

    @Override // androidx.constraintlayout.core.widgets.HelperWidget, androidx.constraintlayout.core.widgets.ConstraintWidget
    public void n(ConstraintWidget constraintWidget, HashMap hashMap) {
        super.n(constraintWidget, hashMap);
        Flow flow = (Flow) constraintWidget;
        this.k1 = flow.k1;
        this.l1 = flow.l1;
        this.m1 = flow.m1;
        this.n1 = flow.n1;
        this.o1 = flow.o1;
        this.p1 = flow.p1;
        this.q1 = flow.q1;
        this.r1 = flow.r1;
        this.s1 = flow.s1;
        this.t1 = flow.t1;
        this.u1 = flow.u1;
        this.v1 = flow.v1;
        this.w1 = flow.w1;
        this.x1 = flow.x1;
        this.y1 = flow.y1;
        this.z1 = flow.z1;
        this.A1 = flow.A1;
        this.B1 = flow.B1;
        this.C1 = flow.C1;
    }

    public void v2(float f2) {
        this.s1 = f2;
    }

    public void w2(int i2) {
        this.m1 = i2;
    }

    public void x2(float f2) {
        this.t1 = f2;
    }

    public void y2(int i2) {
        this.n1 = i2;
    }

    public void z2(int i2) {
        this.y1 = i2;
    }
}
