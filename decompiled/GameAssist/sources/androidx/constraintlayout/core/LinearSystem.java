package androidx.constraintlayout.core;

import androidx.constraintlayout.core.SolverVariable;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import java.util.Arrays;
import java.util.HashMap;

/* loaded from: classes.dex */
public class LinearSystem {

    /* renamed from: s, reason: collision with root package name */
    public static boolean f1473s = false;
    public static boolean t = true;
    public static boolean u = true;
    public static boolean v = true;
    public static boolean w = false;
    public static Metrics x;
    public static long y;
    public static long z;

    /* renamed from: e, reason: collision with root package name */
    private Row f1478e;

    /* renamed from: o, reason: collision with root package name */
    final Cache f1488o;

    /* renamed from: r, reason: collision with root package name */
    private Row f1491r;

    /* renamed from: a, reason: collision with root package name */
    private int f1474a = 1000;

    /* renamed from: b, reason: collision with root package name */
    public boolean f1475b = false;

    /* renamed from: c, reason: collision with root package name */
    int f1476c = 0;

    /* renamed from: d, reason: collision with root package name */
    private HashMap f1477d = null;

    /* renamed from: f, reason: collision with root package name */
    private int f1479f = 32;

    /* renamed from: g, reason: collision with root package name */
    private int f1480g = 32;

    /* renamed from: i, reason: collision with root package name */
    public boolean f1482i = false;

    /* renamed from: j, reason: collision with root package name */
    public boolean f1483j = false;

    /* renamed from: k, reason: collision with root package name */
    private boolean[] f1484k = new boolean[32];

    /* renamed from: l, reason: collision with root package name */
    int f1485l = 1;

    /* renamed from: m, reason: collision with root package name */
    int f1486m = 0;

    /* renamed from: n, reason: collision with root package name */
    private int f1487n = 32;

    /* renamed from: p, reason: collision with root package name */
    private SolverVariable[] f1489p = new SolverVariable[1000];

    /* renamed from: q, reason: collision with root package name */
    private int f1490q = 0;

    /* renamed from: h, reason: collision with root package name */
    ArrayRow[] f1481h = new ArrayRow[32];

    interface Row {
        void a(Row row);

        SolverVariable b(LinearSystem linearSystem, boolean[] zArr);

        void c(SolverVariable solverVariable);

        void clear();

        SolverVariable getKey();

        boolean isEmpty();
    }

    static class ValuesRow extends ArrayRow {
        ValuesRow(Cache cache) {
            this.f1467e = new SolverVariableValues(this, cache);
        }
    }

    public LinearSystem() {
        F();
        Cache cache = new Cache();
        this.f1488o = cache;
        this.f1478e = new PriorityGoalRow(cache);
        if (w) {
            this.f1491r = new ValuesRow(cache);
        } else {
            this.f1491r = new ArrayRow(cache);
        }
    }

    private void B() {
        int i2 = this.f1479f * 2;
        this.f1479f = i2;
        this.f1481h = (ArrayRow[]) Arrays.copyOf(this.f1481h, i2);
        Cache cache = this.f1488o;
        cache.f1472d = (SolverVariable[]) Arrays.copyOf(cache.f1472d, this.f1479f);
        int i3 = this.f1479f;
        this.f1484k = new boolean[i3];
        this.f1480g = i3;
        this.f1487n = i3;
        Metrics metrics = x;
        if (metrics != null) {
            metrics.f1499h++;
            metrics.t = Math.max(metrics.t, i3);
            Metrics metrics2 = x;
            metrics2.E = metrics2.t;
        }
    }

    private int E(Row row, boolean z2) {
        Metrics metrics = x;
        if (metrics != null) {
            metrics.f1503l++;
        }
        for (int i2 = 0; i2 < this.f1485l; i2++) {
            this.f1484k[i2] = false;
        }
        boolean z3 = false;
        int i3 = 0;
        while (!z3) {
            Metrics metrics2 = x;
            if (metrics2 != null) {
                metrics2.f1504m++;
            }
            i3++;
            if (i3 >= this.f1485l * 2) {
                return i3;
            }
            if (row.getKey() != null) {
                this.f1484k[row.getKey().f1525i] = true;
            }
            SolverVariable b2 = row.b(this, this.f1484k);
            if (b2 != null) {
                boolean[] zArr = this.f1484k;
                int i4 = b2.f1525i;
                if (zArr[i4]) {
                    return i3;
                }
                zArr[i4] = true;
            }
            if (b2 != null) {
                float f2 = Float.MAX_VALUE;
                int i5 = -1;
                for (int i6 = 0; i6 < this.f1486m; i6++) {
                    ArrayRow arrayRow = this.f1481h[i6];
                    if (arrayRow.f1463a.f1532p != SolverVariable.Type.UNRESTRICTED && !arrayRow.f1468f && arrayRow.t(b2)) {
                        float j2 = arrayRow.f1467e.j(b2);
                        if (j2 < 0.0f) {
                            float f3 = (-arrayRow.f1464b) / j2;
                            if (f3 < f2) {
                                i5 = i6;
                                f2 = f3;
                            }
                        }
                    }
                }
                if (i5 > -1) {
                    ArrayRow arrayRow2 = this.f1481h[i5];
                    arrayRow2.f1463a.f1526j = -1;
                    Metrics metrics3 = x;
                    if (metrics3 != null) {
                        metrics3.f1505n++;
                    }
                    arrayRow2.x(b2);
                    SolverVariable solverVariable = arrayRow2.f1463a;
                    solverVariable.f1526j = i5;
                    solverVariable.n(this, arrayRow2);
                }
            } else {
                z3 = true;
            }
        }
        return i3;
    }

    private void F() {
        int i2 = 0;
        if (w) {
            while (i2 < this.f1486m) {
                ArrayRow arrayRow = this.f1481h[i2];
                if (arrayRow != null) {
                    this.f1488o.f1469a.release(arrayRow);
                }
                this.f1481h[i2] = null;
                i2++;
            }
            return;
        }
        while (i2 < this.f1486m) {
            ArrayRow arrayRow2 = this.f1481h[i2];
            if (arrayRow2 != null) {
                this.f1488o.f1470b.release(arrayRow2);
            }
            this.f1481h[i2] = null;
            i2++;
        }
    }

    private SolverVariable a(SolverVariable.Type type, String str) {
        SolverVariable solverVariable = (SolverVariable) this.f1488o.f1471c.acquire();
        if (solverVariable == null) {
            solverVariable = new SolverVariable(type, str);
            solverVariable.l(type, str);
        } else {
            solverVariable.h();
            solverVariable.l(type, str);
        }
        int i2 = this.f1490q;
        int i3 = this.f1474a;
        if (i2 >= i3) {
            int i4 = i3 * 2;
            this.f1474a = i4;
            this.f1489p = (SolverVariable[]) Arrays.copyOf(this.f1489p, i4);
        }
        SolverVariable[] solverVariableArr = this.f1489p;
        int i5 = this.f1490q;
        this.f1490q = i5 + 1;
        solverVariableArr[i5] = solverVariable;
        return solverVariable;
    }

    private void l(ArrayRow arrayRow) {
        int i2;
        if (u && arrayRow.f1468f) {
            arrayRow.f1463a.j(this, arrayRow.f1464b);
        } else {
            ArrayRow[] arrayRowArr = this.f1481h;
            int i3 = this.f1486m;
            arrayRowArr[i3] = arrayRow;
            SolverVariable solverVariable = arrayRow.f1463a;
            solverVariable.f1526j = i3;
            this.f1486m = i3 + 1;
            solverVariable.n(this, arrayRow);
        }
        if (u && this.f1475b) {
            int i4 = 0;
            while (i4 < this.f1486m) {
                if (this.f1481h[i4] == null) {
                    System.out.println("WTF");
                }
                ArrayRow arrayRow2 = this.f1481h[i4];
                if (arrayRow2 != null && arrayRow2.f1468f) {
                    arrayRow2.f1463a.j(this, arrayRow2.f1464b);
                    if (w) {
                        this.f1488o.f1469a.release(arrayRow2);
                    } else {
                        this.f1488o.f1470b.release(arrayRow2);
                    }
                    this.f1481h[i4] = null;
                    int i5 = i4 + 1;
                    int i6 = i5;
                    while (true) {
                        i2 = this.f1486m;
                        if (i5 >= i2) {
                            break;
                        }
                        ArrayRow[] arrayRowArr2 = this.f1481h;
                        int i7 = i5 - 1;
                        ArrayRow arrayRow3 = arrayRowArr2[i5];
                        arrayRowArr2[i7] = arrayRow3;
                        SolverVariable solverVariable2 = arrayRow3.f1463a;
                        if (solverVariable2.f1526j == i5) {
                            solverVariable2.f1526j = i7;
                        }
                        i6 = i5;
                        i5++;
                    }
                    if (i6 < i2) {
                        this.f1481h[i6] = null;
                    }
                    this.f1486m = i2 - 1;
                    i4--;
                }
                i4++;
            }
            this.f1475b = false;
        }
    }

    private void n() {
        for (int i2 = 0; i2 < this.f1486m; i2++) {
            ArrayRow arrayRow = this.f1481h[i2];
            arrayRow.f1463a.f1528l = arrayRow.f1464b;
        }
    }

    public static ArrayRow s(LinearSystem linearSystem, SolverVariable solverVariable, SolverVariable solverVariable2, float f2) {
        return linearSystem.r().j(solverVariable, solverVariable2, f2);
    }

    private int u(Row row) {
        for (int i2 = 0; i2 < this.f1486m; i2++) {
            ArrayRow arrayRow = this.f1481h[i2];
            if (arrayRow.f1463a.f1532p != SolverVariable.Type.UNRESTRICTED && arrayRow.f1464b < 0.0f) {
                boolean z2 = false;
                int i3 = 0;
                while (!z2) {
                    Metrics metrics = x;
                    if (metrics != null) {
                        metrics.f1506o++;
                    }
                    i3++;
                    float f2 = Float.MAX_VALUE;
                    int i4 = 0;
                    int i5 = -1;
                    int i6 = -1;
                    int i7 = 0;
                    while (true) {
                        if (i4 >= this.f1486m) {
                            break;
                        }
                        ArrayRow arrayRow2 = this.f1481h[i4];
                        if (arrayRow2.f1463a.f1532p != SolverVariable.Type.UNRESTRICTED && !arrayRow2.f1468f && arrayRow2.f1464b < 0.0f) {
                            int i8 = 9;
                            if (v) {
                                int f3 = arrayRow2.f1467e.f();
                                int i9 = 0;
                                while (i9 < f3) {
                                    SolverVariable b2 = arrayRow2.f1467e.b(i9);
                                    float j2 = arrayRow2.f1467e.j(b2);
                                    if (j2 > 0.0f) {
                                        int i10 = 0;
                                        while (i10 < i8) {
                                            float f4 = b2.f1530n[i10] / j2;
                                            if ((f4 < f2 && i10 == i7) || i10 > i7) {
                                                i7 = i10;
                                                i6 = b2.f1525i;
                                                i5 = i4;
                                                f2 = f4;
                                            }
                                            i10++;
                                            i8 = 9;
                                        }
                                    }
                                    i9++;
                                    i8 = 9;
                                }
                            } else {
                                for (int i11 = 1; i11 < this.f1485l; i11++) {
                                    SolverVariable solverVariable = this.f1488o.f1472d[i11];
                                    float j3 = arrayRow2.f1467e.j(solverVariable);
                                    if (j3 > 0.0f) {
                                        for (int i12 = 0; i12 < 9; i12++) {
                                            float f5 = solverVariable.f1530n[i12] / j3;
                                            if ((f5 < f2 && i12 == i7) || i12 > i7) {
                                                i7 = i12;
                                                i5 = i4;
                                                i6 = i11;
                                                f2 = f5;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        i4++;
                    }
                    if (i5 != -1) {
                        ArrayRow arrayRow3 = this.f1481h[i5];
                        arrayRow3.f1463a.f1526j = -1;
                        Metrics metrics2 = x;
                        if (metrics2 != null) {
                            metrics2.f1505n++;
                        }
                        arrayRow3.x(this.f1488o.f1472d[i6]);
                        SolverVariable solverVariable2 = arrayRow3.f1463a;
                        solverVariable2.f1526j = i5;
                        solverVariable2.n(this, arrayRow3);
                    } else {
                        z2 = true;
                    }
                    if (i3 > this.f1485l / 2) {
                        z2 = true;
                    }
                }
                return i3;
            }
        }
        return 0;
    }

    public static Metrics x() {
        return x;
    }

    public int A(Object obj) {
        SolverVariable i2 = ((ConstraintAnchor) obj).i();
        if (i2 != null) {
            return (int) (i2.f1528l + 0.5f);
        }
        return 0;
    }

    public void C() {
        Metrics metrics = x;
        if (metrics != null) {
            metrics.f1500i++;
        }
        if (this.f1478e.isEmpty()) {
            n();
            return;
        }
        if (!this.f1482i && !this.f1483j) {
            D(this.f1478e);
            return;
        }
        Metrics metrics2 = x;
        if (metrics2 != null) {
            metrics2.v++;
        }
        for (int i2 = 0; i2 < this.f1486m; i2++) {
            if (!this.f1481h[i2].f1468f) {
                D(this.f1478e);
                return;
            }
        }
        Metrics metrics3 = x;
        if (metrics3 != null) {
            metrics3.u++;
        }
        n();
    }

    void D(Row row) {
        Metrics metrics = x;
        if (metrics != null) {
            metrics.z++;
            metrics.A = Math.max(metrics.A, this.f1485l);
            Metrics metrics2 = x;
            metrics2.B = Math.max(metrics2.B, this.f1486m);
        }
        u(row);
        E(row, false);
        n();
    }

    public void G() {
        Cache cache;
        int i2 = 0;
        while (true) {
            cache = this.f1488o;
            SolverVariable[] solverVariableArr = cache.f1472d;
            if (i2 >= solverVariableArr.length) {
                break;
            }
            SolverVariable solverVariable = solverVariableArr[i2];
            if (solverVariable != null) {
                solverVariable.h();
            }
            i2++;
        }
        cache.f1471c.a(this.f1489p, this.f1490q);
        this.f1490q = 0;
        Arrays.fill(this.f1488o.f1472d, (Object) null);
        HashMap hashMap = this.f1477d;
        if (hashMap != null) {
            hashMap.clear();
        }
        this.f1476c = 0;
        this.f1478e.clear();
        this.f1485l = 1;
        for (int i3 = 0; i3 < this.f1486m; i3++) {
            ArrayRow arrayRow = this.f1481h[i3];
            if (arrayRow != null) {
                arrayRow.f1465c = false;
            }
        }
        F();
        this.f1486m = 0;
        if (w) {
            this.f1491r = new ValuesRow(this.f1488o);
        } else {
            this.f1491r = new ArrayRow(this.f1488o);
        }
    }

    public void b(ConstraintWidget constraintWidget, ConstraintWidget constraintWidget2, float f2, int i2) {
        ConstraintAnchor.Type type = ConstraintAnchor.Type.LEFT;
        SolverVariable q2 = q(constraintWidget.q(type));
        ConstraintAnchor.Type type2 = ConstraintAnchor.Type.TOP;
        SolverVariable q3 = q(constraintWidget.q(type2));
        ConstraintAnchor.Type type3 = ConstraintAnchor.Type.RIGHT;
        SolverVariable q4 = q(constraintWidget.q(type3));
        ConstraintAnchor.Type type4 = ConstraintAnchor.Type.BOTTOM;
        SolverVariable q5 = q(constraintWidget.q(type4));
        SolverVariable q6 = q(constraintWidget2.q(type));
        SolverVariable q7 = q(constraintWidget2.q(type2));
        SolverVariable q8 = q(constraintWidget2.q(type3));
        SolverVariable q9 = q(constraintWidget2.q(type4));
        ArrayRow r2 = r();
        double d2 = f2;
        double d3 = i2;
        r2.q(q3, q5, q7, q9, (float) (Math.sin(d2) * d3));
        d(r2);
        ArrayRow r3 = r();
        r3.q(q2, q4, q6, q8, (float) (Math.cos(d2) * d3));
        d(r3);
    }

    public void c(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, float f2, SolverVariable solverVariable3, SolverVariable solverVariable4, int i3, int i4) {
        ArrayRow r2 = r();
        r2.h(solverVariable, solverVariable2, i2, f2, solverVariable3, solverVariable4, i3);
        if (i4 != 8) {
            r2.d(this, i4);
        }
        d(r2);
    }

    public void d(ArrayRow arrayRow) {
        SolverVariable v2;
        if (arrayRow == null) {
            return;
        }
        Metrics metrics = x;
        if (metrics != null) {
            metrics.f1501j++;
            if (arrayRow.f1468f) {
                metrics.f1502k++;
            }
        }
        boolean z2 = true;
        if (this.f1486m + 1 >= this.f1487n || this.f1485l + 1 >= this.f1480g) {
            B();
        }
        if (!arrayRow.f1468f) {
            arrayRow.D(this);
            if (arrayRow.isEmpty()) {
                return;
            }
            arrayRow.r();
            if (arrayRow.f(this)) {
                SolverVariable p2 = p();
                arrayRow.f1463a = p2;
                int i2 = this.f1486m;
                l(arrayRow);
                if (this.f1486m == i2 + 1) {
                    this.f1491r.a(arrayRow);
                    E(this.f1491r, true);
                    if (p2.f1526j == -1) {
                        if (arrayRow.f1463a == p2 && (v2 = arrayRow.v(p2)) != null) {
                            Metrics metrics2 = x;
                            if (metrics2 != null) {
                                metrics2.f1505n++;
                            }
                            arrayRow.x(v2);
                        }
                        if (!arrayRow.f1468f) {
                            arrayRow.f1463a.n(this, arrayRow);
                        }
                        if (w) {
                            this.f1488o.f1469a.release(arrayRow);
                        } else {
                            this.f1488o.f1470b.release(arrayRow);
                        }
                        this.f1486m--;
                    }
                    if (arrayRow.s() || z2) {
                        return;
                    }
                }
            }
            z2 = false;
            if (arrayRow.s()) {
                return;
            } else {
                return;
            }
        }
        l(arrayRow);
    }

    public ArrayRow e(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, int i3) {
        Metrics metrics = x;
        if (metrics != null) {
            metrics.U++;
        }
        if (t && i3 == 8 && solverVariable2.f1529m && solverVariable.f1526j == -1) {
            solverVariable.j(this, solverVariable2.f1528l + i2);
            return null;
        }
        ArrayRow r2 = r();
        r2.n(solverVariable, solverVariable2, i2);
        if (i3 != 8) {
            r2.d(this, i3);
        }
        d(r2);
        return r2;
    }

    public void f(SolverVariable solverVariable, int i2) {
        Metrics metrics = x;
        if (metrics != null) {
            metrics.U++;
        }
        if (t && solverVariable.f1526j == -1) {
            float f2 = i2;
            solverVariable.j(this, f2);
            for (int i3 = 0; i3 < this.f1476c + 1; i3++) {
                SolverVariable solverVariable2 = this.f1488o.f1472d[i3];
                if (solverVariable2 != null && solverVariable2.t && solverVariable2.u == solverVariable.f1525i) {
                    solverVariable2.j(this, solverVariable2.v + f2);
                }
            }
            return;
        }
        int i4 = solverVariable.f1526j;
        if (i4 == -1) {
            ArrayRow r2 = r();
            r2.i(solverVariable, i2);
            d(r2);
            return;
        }
        ArrayRow arrayRow = this.f1481h[i4];
        if (arrayRow.f1468f) {
            arrayRow.f1464b = i2;
            return;
        }
        if (arrayRow.f1467e.f() == 0) {
            arrayRow.f1468f = true;
            arrayRow.f1464b = i2;
        } else {
            ArrayRow r3 = r();
            r3.m(solverVariable, i2);
            d(r3);
        }
    }

    public void g(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, boolean z2) {
        ArrayRow r2 = r();
        SolverVariable t2 = t();
        t2.f1527k = 0;
        r2.o(solverVariable, solverVariable2, t2, i2);
        d(r2);
    }

    public void h(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, int i3) {
        ArrayRow r2 = r();
        SolverVariable t2 = t();
        t2.f1527k = 0;
        r2.o(solverVariable, solverVariable2, t2, i2);
        if (i3 != 8) {
            m(r2, (int) (r2.f1467e.j(t2) * (-1.0f)), i3);
        }
        d(r2);
    }

    public void i(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, boolean z2) {
        ArrayRow r2 = r();
        SolverVariable t2 = t();
        t2.f1527k = 0;
        r2.p(solverVariable, solverVariable2, t2, i2);
        d(r2);
    }

    public void j(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, int i3) {
        ArrayRow r2 = r();
        SolverVariable t2 = t();
        t2.f1527k = 0;
        r2.p(solverVariable, solverVariable2, t2, i2);
        if (i3 != 8) {
            m(r2, (int) (r2.f1467e.j(t2) * (-1.0f)), i3);
        }
        d(r2);
    }

    public void k(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f2, int i2) {
        ArrayRow r2 = r();
        r2.k(solverVariable, solverVariable2, solverVariable3, solverVariable4, f2);
        if (i2 != 8) {
            r2.d(this, i2);
        }
        d(r2);
    }

    void m(ArrayRow arrayRow, int i2, int i3) {
        arrayRow.e(o(i3, null), i2);
    }

    public SolverVariable o(int i2, String str) {
        Metrics metrics = x;
        if (metrics != null) {
            metrics.f1508q++;
        }
        if (this.f1485l + 1 >= this.f1480g) {
            B();
        }
        SolverVariable a2 = a(SolverVariable.Type.ERROR, str);
        int i3 = this.f1476c + 1;
        this.f1476c = i3;
        this.f1485l++;
        a2.f1525i = i3;
        a2.f1527k = i2;
        this.f1488o.f1472d[i3] = a2;
        this.f1478e.c(a2);
        return a2;
    }

    public SolverVariable p() {
        Metrics metrics = x;
        if (metrics != null) {
            metrics.f1510s++;
        }
        if (this.f1485l + 1 >= this.f1480g) {
            B();
        }
        SolverVariable a2 = a(SolverVariable.Type.SLACK, null);
        int i2 = this.f1476c + 1;
        this.f1476c = i2;
        this.f1485l++;
        a2.f1525i = i2;
        this.f1488o.f1472d[i2] = a2;
        return a2;
    }

    public SolverVariable q(Object obj) {
        SolverVariable solverVariable = null;
        if (obj == null) {
            return null;
        }
        if (this.f1485l + 1 >= this.f1480g) {
            B();
        }
        if (obj instanceof ConstraintAnchor) {
            ConstraintAnchor constraintAnchor = (ConstraintAnchor) obj;
            solverVariable = constraintAnchor.i();
            if (solverVariable == null) {
                constraintAnchor.s(this.f1488o);
                solverVariable = constraintAnchor.i();
            }
            int i2 = solverVariable.f1525i;
            if (i2 == -1 || i2 > this.f1476c || this.f1488o.f1472d[i2] == null) {
                if (i2 != -1) {
                    solverVariable.h();
                }
                int i3 = this.f1476c + 1;
                this.f1476c = i3;
                this.f1485l++;
                solverVariable.f1525i = i3;
                solverVariable.f1532p = SolverVariable.Type.UNRESTRICTED;
                this.f1488o.f1472d[i3] = solverVariable;
            }
        }
        return solverVariable;
    }

    public ArrayRow r() {
        ArrayRow arrayRow;
        if (w) {
            arrayRow = (ArrayRow) this.f1488o.f1469a.acquire();
            if (arrayRow == null) {
                arrayRow = new ValuesRow(this.f1488o);
                z++;
            } else {
                arrayRow.y();
            }
        } else {
            arrayRow = (ArrayRow) this.f1488o.f1470b.acquire();
            if (arrayRow == null) {
                arrayRow = new ArrayRow(this.f1488o);
                y++;
            } else {
                arrayRow.y();
            }
        }
        SolverVariable.e();
        return arrayRow;
    }

    public SolverVariable t() {
        Metrics metrics = x;
        if (metrics != null) {
            metrics.f1509r++;
        }
        if (this.f1485l + 1 >= this.f1480g) {
            B();
        }
        SolverVariable a2 = a(SolverVariable.Type.SLACK, null);
        int i2 = this.f1476c + 1;
        this.f1476c = i2;
        this.f1485l++;
        a2.f1525i = i2;
        this.f1488o.f1472d[i2] = a2;
        return a2;
    }

    public void v(Metrics metrics) {
        x = metrics;
    }

    public Cache w() {
        return this.f1488o;
    }

    public int y() {
        return this.f1486m;
    }

    public int z() {
        return this.f1476c;
    }
}
