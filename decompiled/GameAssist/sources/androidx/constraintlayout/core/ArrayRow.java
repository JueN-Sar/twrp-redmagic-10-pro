package androidx.constraintlayout.core;

import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.SolverVariable;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class ArrayRow implements LinearSystem.Row {

    /* renamed from: e, reason: collision with root package name */
    public ArrayRowVariables f1467e;

    /* renamed from: a, reason: collision with root package name */
    SolverVariable f1463a = null;

    /* renamed from: b, reason: collision with root package name */
    float f1464b = 0.0f;

    /* renamed from: c, reason: collision with root package name */
    boolean f1465c = false;

    /* renamed from: d, reason: collision with root package name */
    ArrayList f1466d = new ArrayList();

    /* renamed from: f, reason: collision with root package name */
    boolean f1468f = false;

    public interface ArrayRowVariables {
        boolean a(SolverVariable solverVariable);

        SolverVariable b(int i2);

        void c(SolverVariable solverVariable, float f2, boolean z);

        void clear();

        void d();

        float e(SolverVariable solverVariable, boolean z);

        int f();

        float g(ArrayRow arrayRow, boolean z);

        void h(SolverVariable solverVariable, float f2);

        float i(int i2);

        float j(SolverVariable solverVariable);

        void k(float f2);
    }

    public ArrayRow() {
    }

    private boolean u(SolverVariable solverVariable, LinearSystem linearSystem) {
        return solverVariable.f1535s <= 1;
    }

    private SolverVariable w(boolean[] zArr, SolverVariable solverVariable) {
        SolverVariable.Type type;
        int f2 = this.f1467e.f();
        SolverVariable solverVariable2 = null;
        float f3 = 0.0f;
        for (int i2 = 0; i2 < f2; i2++) {
            float i3 = this.f1467e.i(i2);
            if (i3 < 0.0f) {
                SolverVariable b2 = this.f1467e.b(i2);
                if ((zArr == null || !zArr[b2.f1525i]) && b2 != solverVariable && (((type = b2.f1532p) == SolverVariable.Type.SLACK || type == SolverVariable.Type.ERROR) && i3 < f3)) {
                    f3 = i3;
                    solverVariable2 = b2;
                }
            }
        }
        return solverVariable2;
    }

    public void A(LinearSystem linearSystem, SolverVariable solverVariable, boolean z) {
        if (solverVariable == null || !solverVariable.f1529m) {
            return;
        }
        this.f1464b += solverVariable.f1528l * this.f1467e.j(solverVariable);
        this.f1467e.e(solverVariable, z);
        if (z) {
            solverVariable.f(this);
        }
        if (LinearSystem.u && this.f1467e.f() == 0) {
            this.f1468f = true;
            linearSystem.f1475b = true;
        }
    }

    public void B(LinearSystem linearSystem, ArrayRow arrayRow, boolean z) {
        this.f1464b += arrayRow.f1464b * this.f1467e.g(arrayRow, z);
        if (z) {
            arrayRow.f1463a.f(this);
        }
        if (LinearSystem.u && this.f1463a != null && this.f1467e.f() == 0) {
            this.f1468f = true;
            linearSystem.f1475b = true;
        }
    }

    public void C(LinearSystem linearSystem, SolverVariable solverVariable, boolean z) {
        if (solverVariable == null || !solverVariable.t) {
            return;
        }
        float j2 = this.f1467e.j(solverVariable);
        this.f1464b += solverVariable.v * j2;
        this.f1467e.e(solverVariable, z);
        if (z) {
            solverVariable.f(this);
        }
        this.f1467e.c(linearSystem.f1488o.f1472d[solverVariable.u], j2, z);
        if (LinearSystem.u && this.f1467e.f() == 0) {
            this.f1468f = true;
            linearSystem.f1475b = true;
        }
    }

    public void D(LinearSystem linearSystem) {
        if (linearSystem.f1481h.length == 0) {
            return;
        }
        boolean z = false;
        while (!z) {
            int f2 = this.f1467e.f();
            for (int i2 = 0; i2 < f2; i2++) {
                SolverVariable b2 = this.f1467e.b(i2);
                if (b2.f1526j != -1 || b2.f1529m || b2.t) {
                    this.f1466d.add(b2);
                }
            }
            int size = this.f1466d.size();
            if (size > 0) {
                for (int i3 = 0; i3 < size; i3++) {
                    SolverVariable solverVariable = (SolverVariable) this.f1466d.get(i3);
                    if (solverVariable.f1529m) {
                        A(linearSystem, solverVariable, true);
                    } else if (solverVariable.t) {
                        C(linearSystem, solverVariable, true);
                    } else {
                        B(linearSystem, linearSystem.f1481h[solverVariable.f1526j], true);
                    }
                }
                this.f1466d.clear();
            } else {
                z = true;
            }
        }
        if (LinearSystem.u && this.f1463a != null && this.f1467e.f() == 0) {
            this.f1468f = true;
            linearSystem.f1475b = true;
        }
    }

    @Override // androidx.constraintlayout.core.LinearSystem.Row
    public void a(LinearSystem.Row row) {
        if (row instanceof ArrayRow) {
            ArrayRow arrayRow = (ArrayRow) row;
            this.f1463a = null;
            this.f1467e.clear();
            for (int i2 = 0; i2 < arrayRow.f1467e.f(); i2++) {
                this.f1467e.c(arrayRow.f1467e.b(i2), arrayRow.f1467e.i(i2), true);
            }
        }
    }

    @Override // androidx.constraintlayout.core.LinearSystem.Row
    public SolverVariable b(LinearSystem linearSystem, boolean[] zArr) {
        return w(zArr, null);
    }

    @Override // androidx.constraintlayout.core.LinearSystem.Row
    public void c(SolverVariable solverVariable) {
        int i2 = solverVariable.f1527k;
        float f2 = 1.0f;
        if (i2 != 1) {
            if (i2 == 2) {
                f2 = 1000.0f;
            } else if (i2 == 3) {
                f2 = 1000000.0f;
            } else if (i2 == 4) {
                f2 = 1.0E9f;
            } else if (i2 == 5) {
                f2 = 1.0E12f;
            }
        }
        this.f1467e.h(solverVariable, f2);
    }

    @Override // androidx.constraintlayout.core.LinearSystem.Row
    public void clear() {
        this.f1467e.clear();
        this.f1463a = null;
        this.f1464b = 0.0f;
    }

    public ArrayRow d(LinearSystem linearSystem, int i2) {
        this.f1467e.h(linearSystem.o(i2, "ep"), 1.0f);
        this.f1467e.h(linearSystem.o(i2, "em"), -1.0f);
        return this;
    }

    ArrayRow e(SolverVariable solverVariable, int i2) {
        this.f1467e.h(solverVariable, i2);
        return this;
    }

    boolean f(LinearSystem linearSystem) {
        boolean z;
        SolverVariable g2 = g(linearSystem);
        if (g2 == null) {
            z = true;
        } else {
            x(g2);
            z = false;
        }
        if (this.f1467e.f() == 0) {
            this.f1468f = true;
        }
        return z;
    }

    SolverVariable g(LinearSystem linearSystem) {
        boolean u;
        boolean u2;
        int f2 = this.f1467e.f();
        SolverVariable solverVariable = null;
        float f3 = 0.0f;
        float f4 = 0.0f;
        boolean z = false;
        boolean z2 = false;
        SolverVariable solverVariable2 = null;
        for (int i2 = 0; i2 < f2; i2++) {
            float i3 = this.f1467e.i(i2);
            SolverVariable b2 = this.f1467e.b(i2);
            if (b2.f1532p == SolverVariable.Type.UNRESTRICTED) {
                if (solverVariable == null) {
                    u2 = u(b2, linearSystem);
                } else if (f3 > i3) {
                    u2 = u(b2, linearSystem);
                } else if (!z && u(b2, linearSystem)) {
                    f3 = i3;
                    solverVariable = b2;
                    z = true;
                }
                z = u2;
                f3 = i3;
                solverVariable = b2;
            } else if (solverVariable == null && i3 < 0.0f) {
                if (solverVariable2 == null) {
                    u = u(b2, linearSystem);
                } else if (f4 > i3) {
                    u = u(b2, linearSystem);
                } else if (!z2 && u(b2, linearSystem)) {
                    f4 = i3;
                    solverVariable2 = b2;
                    z2 = true;
                }
                z2 = u;
                f4 = i3;
                solverVariable2 = b2;
            }
        }
        return solverVariable != null ? solverVariable : solverVariable2;
    }

    @Override // androidx.constraintlayout.core.LinearSystem.Row
    public SolverVariable getKey() {
        return this.f1463a;
    }

    ArrayRow h(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, float f2, SolverVariable solverVariable3, SolverVariable solverVariable4, int i3) {
        if (solverVariable2 == solverVariable3) {
            this.f1467e.h(solverVariable, 1.0f);
            this.f1467e.h(solverVariable4, 1.0f);
            this.f1467e.h(solverVariable2, -2.0f);
            return this;
        }
        if (f2 == 0.5f) {
            this.f1467e.h(solverVariable, 1.0f);
            this.f1467e.h(solverVariable2, -1.0f);
            this.f1467e.h(solverVariable3, -1.0f);
            this.f1467e.h(solverVariable4, 1.0f);
            if (i2 > 0 || i3 > 0) {
                this.f1464b = (-i2) + i3;
            }
        } else if (f2 <= 0.0f) {
            this.f1467e.h(solverVariable, -1.0f);
            this.f1467e.h(solverVariable2, 1.0f);
            this.f1464b = i2;
        } else if (f2 >= 1.0f) {
            this.f1467e.h(solverVariable4, -1.0f);
            this.f1467e.h(solverVariable3, 1.0f);
            this.f1464b = -i3;
        } else {
            float f3 = 1.0f - f2;
            this.f1467e.h(solverVariable, f3 * 1.0f);
            this.f1467e.h(solverVariable2, f3 * (-1.0f));
            this.f1467e.h(solverVariable3, (-1.0f) * f2);
            this.f1467e.h(solverVariable4, 1.0f * f2);
            if (i2 > 0 || i3 > 0) {
                this.f1464b = ((-i2) * f3) + (i3 * f2);
            }
        }
        return this;
    }

    ArrayRow i(SolverVariable solverVariable, int i2) {
        this.f1463a = solverVariable;
        float f2 = i2;
        solverVariable.f1528l = f2;
        this.f1464b = f2;
        this.f1468f = true;
        return this;
    }

    @Override // androidx.constraintlayout.core.LinearSystem.Row
    public boolean isEmpty() {
        return this.f1463a == null && this.f1464b == 0.0f && this.f1467e.f() == 0;
    }

    ArrayRow j(SolverVariable solverVariable, SolverVariable solverVariable2, float f2) {
        this.f1467e.h(solverVariable, -1.0f);
        this.f1467e.h(solverVariable2, f2);
        return this;
    }

    public ArrayRow k(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f2) {
        this.f1467e.h(solverVariable, -1.0f);
        this.f1467e.h(solverVariable2, 1.0f);
        this.f1467e.h(solverVariable3, f2);
        this.f1467e.h(solverVariable4, -f2);
        return this;
    }

    public ArrayRow l(float f2, float f3, float f4, SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4) {
        this.f1464b = 0.0f;
        if (f3 == 0.0f || f2 == f4) {
            this.f1467e.h(solverVariable, 1.0f);
            this.f1467e.h(solverVariable2, -1.0f);
            this.f1467e.h(solverVariable4, 1.0f);
            this.f1467e.h(solverVariable3, -1.0f);
        } else if (f2 == 0.0f) {
            this.f1467e.h(solverVariable, 1.0f);
            this.f1467e.h(solverVariable2, -1.0f);
        } else if (f4 == 0.0f) {
            this.f1467e.h(solverVariable3, 1.0f);
            this.f1467e.h(solverVariable4, -1.0f);
        } else {
            float f5 = (f2 / f3) / (f4 / f3);
            this.f1467e.h(solverVariable, 1.0f);
            this.f1467e.h(solverVariable2, -1.0f);
            this.f1467e.h(solverVariable4, f5);
            this.f1467e.h(solverVariable3, -f5);
        }
        return this;
    }

    public ArrayRow m(SolverVariable solverVariable, int i2) {
        if (i2 < 0) {
            this.f1464b = i2 * (-1);
            this.f1467e.h(solverVariable, 1.0f);
        } else {
            this.f1464b = i2;
            this.f1467e.h(solverVariable, -1.0f);
        }
        return this;
    }

    public ArrayRow n(SolverVariable solverVariable, SolverVariable solverVariable2, int i2) {
        boolean z;
        if (i2 != 0) {
            if (i2 < 0) {
                i2 *= -1;
                z = true;
            } else {
                z = false;
            }
            this.f1464b = i2;
            if (z) {
                this.f1467e.h(solverVariable, 1.0f);
                this.f1467e.h(solverVariable2, -1.0f);
                return this;
            }
        }
        this.f1467e.h(solverVariable, -1.0f);
        this.f1467e.h(solverVariable2, 1.0f);
        return this;
    }

    public ArrayRow o(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, int i2) {
        boolean z;
        if (i2 != 0) {
            if (i2 < 0) {
                i2 *= -1;
                z = true;
            } else {
                z = false;
            }
            this.f1464b = i2;
            if (z) {
                this.f1467e.h(solverVariable, 1.0f);
                this.f1467e.h(solverVariable2, -1.0f);
                this.f1467e.h(solverVariable3, -1.0f);
                return this;
            }
        }
        this.f1467e.h(solverVariable, -1.0f);
        this.f1467e.h(solverVariable2, 1.0f);
        this.f1467e.h(solverVariable3, 1.0f);
        return this;
    }

    public ArrayRow p(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, int i2) {
        boolean z;
        if (i2 != 0) {
            if (i2 < 0) {
                i2 *= -1;
                z = true;
            } else {
                z = false;
            }
            this.f1464b = i2;
            if (z) {
                this.f1467e.h(solverVariable, 1.0f);
                this.f1467e.h(solverVariable2, -1.0f);
                this.f1467e.h(solverVariable3, 1.0f);
                return this;
            }
        }
        this.f1467e.h(solverVariable, -1.0f);
        this.f1467e.h(solverVariable2, 1.0f);
        this.f1467e.h(solverVariable3, -1.0f);
        return this;
    }

    public ArrayRow q(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f2) {
        this.f1467e.h(solverVariable3, 0.5f);
        this.f1467e.h(solverVariable4, 0.5f);
        this.f1467e.h(solverVariable, -0.5f);
        this.f1467e.h(solverVariable2, -0.5f);
        this.f1464b = -f2;
        return this;
    }

    void r() {
        float f2 = this.f1464b;
        if (f2 < 0.0f) {
            this.f1464b = f2 * (-1.0f);
            this.f1467e.d();
        }
    }

    boolean s() {
        SolverVariable solverVariable = this.f1463a;
        return solverVariable != null && (solverVariable.f1532p == SolverVariable.Type.UNRESTRICTED || this.f1464b >= 0.0f);
    }

    boolean t(SolverVariable solverVariable) {
        return this.f1467e.a(solverVariable);
    }

    public String toString() {
        return z();
    }

    public SolverVariable v(SolverVariable solverVariable) {
        return w(null, solverVariable);
    }

    void x(SolverVariable solverVariable) {
        SolverVariable solverVariable2 = this.f1463a;
        if (solverVariable2 != null) {
            this.f1467e.h(solverVariable2, -1.0f);
            this.f1463a.f1526j = -1;
            this.f1463a = null;
        }
        float e2 = this.f1467e.e(solverVariable, true) * (-1.0f);
        this.f1463a = solverVariable;
        if (e2 == 1.0f) {
            return;
        }
        this.f1464b /= e2;
        this.f1467e.k(e2);
    }

    public void y() {
        this.f1463a = null;
        this.f1467e.clear();
        this.f1464b = 0.0f;
        this.f1468f = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00d0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    java.lang.String z() {
        /*
            Method dump skipped, instructions count: 256
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.ArrayRow.z():java.lang.String");
    }

    public ArrayRow(Cache cache) {
        this.f1467e = new ArrayLinkedVariables(this, cache);
    }
}
