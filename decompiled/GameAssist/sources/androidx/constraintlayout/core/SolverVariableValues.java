package androidx.constraintlayout.core;

import androidx.constraintlayout.core.ArrayRow;
import java.util.Arrays;

/* loaded from: classes.dex */
public class SolverVariableValues implements ArrayRow.ArrayRowVariables {

    /* renamed from: n, reason: collision with root package name */
    private static float f1536n = 0.001f;

    /* renamed from: a, reason: collision with root package name */
    private final int f1537a = -1;

    /* renamed from: b, reason: collision with root package name */
    private int f1538b = 16;

    /* renamed from: c, reason: collision with root package name */
    private int f1539c = 16;

    /* renamed from: d, reason: collision with root package name */
    int[] f1540d = new int[16];

    /* renamed from: e, reason: collision with root package name */
    int[] f1541e = new int[16];

    /* renamed from: f, reason: collision with root package name */
    int[] f1542f = new int[16];

    /* renamed from: g, reason: collision with root package name */
    float[] f1543g = new float[16];

    /* renamed from: h, reason: collision with root package name */
    int[] f1544h = new int[16];

    /* renamed from: i, reason: collision with root package name */
    int[] f1545i = new int[16];

    /* renamed from: j, reason: collision with root package name */
    int f1546j = 0;

    /* renamed from: k, reason: collision with root package name */
    int f1547k = -1;

    /* renamed from: l, reason: collision with root package name */
    private final ArrayRow f1548l;

    /* renamed from: m, reason: collision with root package name */
    protected final Cache f1549m;

    SolverVariableValues(ArrayRow arrayRow, Cache cache) {
        this.f1548l = arrayRow;
        this.f1549m = cache;
        clear();
    }

    private void l(SolverVariable solverVariable, int i2) {
        int[] iArr;
        int i3 = solverVariable.f1525i % this.f1539c;
        int[] iArr2 = this.f1540d;
        int i4 = iArr2[i3];
        if (i4 == -1) {
            iArr2[i3] = i2;
        } else {
            while (true) {
                iArr = this.f1541e;
                int i5 = iArr[i4];
                if (i5 == -1) {
                    break;
                } else {
                    i4 = i5;
                }
            }
            iArr[i4] = i2;
        }
        this.f1541e[i2] = -1;
    }

    private void m(int i2, SolverVariable solverVariable, float f2) {
        this.f1542f[i2] = solverVariable.f1525i;
        this.f1543g[i2] = f2;
        this.f1544h[i2] = -1;
        this.f1545i[i2] = -1;
        solverVariable.c(this.f1548l);
        solverVariable.f1535s++;
        this.f1546j++;
    }

    private int n() {
        for (int i2 = 0; i2 < this.f1538b; i2++) {
            if (this.f1542f[i2] == -1) {
                return i2;
            }
        }
        return -1;
    }

    private void o() {
        int i2 = this.f1538b * 2;
        this.f1542f = Arrays.copyOf(this.f1542f, i2);
        this.f1543g = Arrays.copyOf(this.f1543g, i2);
        this.f1544h = Arrays.copyOf(this.f1544h, i2);
        this.f1545i = Arrays.copyOf(this.f1545i, i2);
        this.f1541e = Arrays.copyOf(this.f1541e, i2);
        for (int i3 = this.f1538b; i3 < i2; i3++) {
            this.f1542f[i3] = -1;
            this.f1541e[i3] = -1;
        }
        this.f1538b = i2;
    }

    private void q(int i2, SolverVariable solverVariable, float f2) {
        int n2 = n();
        m(n2, solverVariable, f2);
        if (i2 != -1) {
            this.f1544h[n2] = i2;
            int[] iArr = this.f1545i;
            iArr[n2] = iArr[i2];
            iArr[i2] = n2;
        } else {
            this.f1544h[n2] = -1;
            if (this.f1546j > 0) {
                this.f1545i[n2] = this.f1547k;
                this.f1547k = n2;
            } else {
                this.f1545i[n2] = -1;
            }
        }
        int i3 = this.f1545i[n2];
        if (i3 != -1) {
            this.f1544h[i3] = n2;
        }
        l(solverVariable, n2);
    }

    private void r(SolverVariable solverVariable) {
        int[] iArr;
        int i2;
        int i3 = solverVariable.f1525i;
        int i4 = i3 % this.f1539c;
        int[] iArr2 = this.f1540d;
        int i5 = iArr2[i4];
        if (i5 == -1) {
            return;
        }
        if (this.f1542f[i5] == i3) {
            int[] iArr3 = this.f1541e;
            iArr2[i4] = iArr3[i5];
            iArr3[i5] = -1;
            return;
        }
        while (true) {
            iArr = this.f1541e;
            i2 = iArr[i5];
            if (i2 == -1 || this.f1542f[i2] == i3) {
                break;
            } else {
                i5 = i2;
            }
        }
        if (i2 == -1 || this.f1542f[i2] != i3) {
            return;
        }
        iArr[i5] = iArr[i2];
        iArr[i2] = -1;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public boolean a(SolverVariable solverVariable) {
        return p(solverVariable) != -1;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public SolverVariable b(int i2) {
        int i3 = this.f1546j;
        if (i3 == 0) {
            return null;
        }
        int i4 = this.f1547k;
        for (int i5 = 0; i5 < i3; i5++) {
            if (i5 == i2 && i4 != -1) {
                return this.f1549m.f1472d[this.f1542f[i4]];
            }
            i4 = this.f1545i[i4];
            if (i4 == -1) {
                break;
            }
        }
        return null;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public void c(SolverVariable solverVariable, float f2, boolean z) {
        float f3 = f1536n;
        if (f2 <= (-f3) || f2 >= f3) {
            int p2 = p(solverVariable);
            if (p2 == -1) {
                h(solverVariable, f2);
                return;
            }
            float[] fArr = this.f1543g;
            float f4 = fArr[p2] + f2;
            fArr[p2] = f4;
            float f5 = f1536n;
            if (f4 <= (-f5) || f4 >= f5) {
                return;
            }
            fArr[p2] = 0.0f;
            e(solverVariable, z);
        }
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public void clear() {
        int i2 = this.f1546j;
        for (int i3 = 0; i3 < i2; i3++) {
            SolverVariable b2 = b(i3);
            if (b2 != null) {
                b2.f(this.f1548l);
            }
        }
        for (int i4 = 0; i4 < this.f1538b; i4++) {
            this.f1542f[i4] = -1;
            this.f1541e[i4] = -1;
        }
        for (int i5 = 0; i5 < this.f1539c; i5++) {
            this.f1540d[i5] = -1;
        }
        this.f1546j = 0;
        this.f1547k = -1;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public void d() {
        int i2 = this.f1546j;
        int i3 = this.f1547k;
        for (int i4 = 0; i4 < i2; i4++) {
            float[] fArr = this.f1543g;
            fArr[i3] = fArr[i3] * (-1.0f);
            i3 = this.f1545i[i3];
            if (i3 == -1) {
                return;
            }
        }
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public float e(SolverVariable solverVariable, boolean z) {
        int p2 = p(solverVariable);
        if (p2 == -1) {
            return 0.0f;
        }
        r(solverVariable);
        float f2 = this.f1543g[p2];
        if (this.f1547k == p2) {
            this.f1547k = this.f1545i[p2];
        }
        this.f1542f[p2] = -1;
        int[] iArr = this.f1544h;
        int i2 = iArr[p2];
        if (i2 != -1) {
            int[] iArr2 = this.f1545i;
            iArr2[i2] = iArr2[p2];
        }
        int i3 = this.f1545i[p2];
        if (i3 != -1) {
            iArr[i3] = iArr[p2];
        }
        this.f1546j--;
        solverVariable.f1535s--;
        if (z) {
            solverVariable.f(this.f1548l);
        }
        return f2;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public int f() {
        return this.f1546j;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public float g(ArrayRow arrayRow, boolean z) {
        float j2 = j(arrayRow.f1463a);
        e(arrayRow.f1463a, z);
        SolverVariableValues solverVariableValues = (SolverVariableValues) arrayRow.f1467e;
        int f2 = solverVariableValues.f();
        int i2 = 0;
        int i3 = 0;
        while (i2 < f2) {
            int i4 = solverVariableValues.f1542f[i3];
            if (i4 != -1) {
                c(this.f1549m.f1472d[i4], solverVariableValues.f1543g[i3] * j2, z);
                i2++;
            }
            i3++;
        }
        return j2;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public void h(SolverVariable solverVariable, float f2) {
        float f3 = f1536n;
        if (f2 > (-f3) && f2 < f3) {
            e(solverVariable, true);
            return;
        }
        if (this.f1546j == 0) {
            m(0, solverVariable, f2);
            l(solverVariable, 0);
            this.f1547k = 0;
            return;
        }
        int p2 = p(solverVariable);
        if (p2 != -1) {
            this.f1543g[p2] = f2;
            return;
        }
        if (this.f1546j + 1 >= this.f1538b) {
            o();
        }
        int i2 = this.f1546j;
        int i3 = this.f1547k;
        int i4 = -1;
        for (int i5 = 0; i5 < i2; i5++) {
            int i6 = this.f1542f[i3];
            int i7 = solverVariable.f1525i;
            if (i6 == i7) {
                this.f1543g[i3] = f2;
                return;
            }
            if (i6 < i7) {
                i4 = i3;
            }
            i3 = this.f1545i[i3];
            if (i3 == -1) {
                break;
            }
        }
        q(i4, solverVariable, f2);
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public float i(int i2) {
        int i3 = this.f1546j;
        int i4 = this.f1547k;
        for (int i5 = 0; i5 < i3; i5++) {
            if (i5 == i2) {
                return this.f1543g[i4];
            }
            i4 = this.f1545i[i4];
            if (i4 == -1) {
                return 0.0f;
            }
        }
        return 0.0f;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public float j(SolverVariable solverVariable) {
        int p2 = p(solverVariable);
        if (p2 != -1) {
            return this.f1543g[p2];
        }
        return 0.0f;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public void k(float f2) {
        int i2 = this.f1546j;
        int i3 = this.f1547k;
        for (int i4 = 0; i4 < i2; i4++) {
            float[] fArr = this.f1543g;
            fArr[i3] = fArr[i3] / f2;
            i3 = this.f1545i[i3];
            if (i3 == -1) {
                return;
            }
        }
    }

    public int p(SolverVariable solverVariable) {
        if (this.f1546j != 0 && solverVariable != null) {
            int i2 = solverVariable.f1525i;
            int i3 = this.f1540d[i2 % this.f1539c];
            if (i3 == -1) {
                return -1;
            }
            if (this.f1542f[i3] == i2) {
                return i3;
            }
            do {
                i3 = this.f1541e[i3];
                if (i3 == -1) {
                    break;
                }
            } while (this.f1542f[i3] != i2);
            if (i3 != -1 && this.f1542f[i3] == i2) {
                return i3;
            }
        }
        return -1;
    }

    public String toString() {
        String str = hashCode() + " { ";
        int i2 = this.f1546j;
        for (int i3 = 0; i3 < i2; i3++) {
            SolverVariable b2 = b(i3);
            if (b2 != null) {
                String str2 = str + b2 + " = " + i(i3) + " ";
                int p2 = p(b2);
                String str3 = str2 + "[p: ";
                String str4 = (this.f1544h[p2] != -1 ? str3 + this.f1549m.f1472d[this.f1542f[this.f1544h[p2]]] : str3 + "none") + ", n: ";
                str = (this.f1545i[p2] != -1 ? str4 + this.f1549m.f1472d[this.f1542f[this.f1545i[p2]]] : str4 + "none") + "]";
            }
        }
        return str + " }";
    }
}
