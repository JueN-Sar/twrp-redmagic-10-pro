package androidx.constraintlayout.core;

import androidx.constraintlayout.core.ArrayRow;
import java.util.Arrays;

/* loaded from: classes.dex */
public class ArrayLinkedVariables implements ArrayRow.ArrayRowVariables {

    /* renamed from: l, reason: collision with root package name */
    private static float f1451l = 0.001f;

    /* renamed from: b, reason: collision with root package name */
    private final ArrayRow f1453b;

    /* renamed from: c, reason: collision with root package name */
    protected final Cache f1454c;

    /* renamed from: a, reason: collision with root package name */
    int f1452a = 0;

    /* renamed from: d, reason: collision with root package name */
    private int f1455d = 8;

    /* renamed from: e, reason: collision with root package name */
    private SolverVariable f1456e = null;

    /* renamed from: f, reason: collision with root package name */
    private int[] f1457f = new int[8];

    /* renamed from: g, reason: collision with root package name */
    private int[] f1458g = new int[8];

    /* renamed from: h, reason: collision with root package name */
    private float[] f1459h = new float[8];

    /* renamed from: i, reason: collision with root package name */
    private int f1460i = -1;

    /* renamed from: j, reason: collision with root package name */
    private int f1461j = -1;

    /* renamed from: k, reason: collision with root package name */
    private boolean f1462k = false;

    ArrayLinkedVariables(ArrayRow arrayRow, Cache cache) {
        this.f1453b = arrayRow;
        this.f1454c = cache;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public boolean a(SolverVariable solverVariable) {
        int i2 = this.f1460i;
        if (i2 == -1) {
            return false;
        }
        for (int i3 = 0; i2 != -1 && i3 < this.f1452a; i3++) {
            if (this.f1457f[i2] == solverVariable.f1525i) {
                return true;
            }
            i2 = this.f1458g[i2];
        }
        return false;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public SolverVariable b(int i2) {
        int i3 = this.f1460i;
        for (int i4 = 0; i3 != -1 && i4 < this.f1452a; i4++) {
            if (i4 == i2) {
                return this.f1454c.f1472d[this.f1457f[i3]];
            }
            i3 = this.f1458g[i3];
        }
        return null;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public void c(SolverVariable solverVariable, float f2, boolean z) {
        float f3 = f1451l;
        if (f2 <= (-f3) || f2 >= f3) {
            int i2 = this.f1460i;
            if (i2 == -1) {
                this.f1460i = 0;
                this.f1459h[0] = f2;
                this.f1457f[0] = solverVariable.f1525i;
                this.f1458g[0] = -1;
                solverVariable.f1535s++;
                solverVariable.c(this.f1453b);
                this.f1452a++;
                if (this.f1462k) {
                    return;
                }
                int i3 = this.f1461j + 1;
                this.f1461j = i3;
                int[] iArr = this.f1457f;
                if (i3 >= iArr.length) {
                    this.f1462k = true;
                    this.f1461j = iArr.length - 1;
                    return;
                }
                return;
            }
            int i4 = -1;
            for (int i5 = 0; i2 != -1 && i5 < this.f1452a; i5++) {
                int i6 = this.f1457f[i2];
                int i7 = solverVariable.f1525i;
                if (i6 == i7) {
                    float[] fArr = this.f1459h;
                    float f4 = fArr[i2] + f2;
                    float f5 = f1451l;
                    if (f4 > (-f5) && f4 < f5) {
                        f4 = 0.0f;
                    }
                    fArr[i2] = f4;
                    if (f4 == 0.0f) {
                        if (i2 == this.f1460i) {
                            this.f1460i = this.f1458g[i2];
                        } else {
                            int[] iArr2 = this.f1458g;
                            iArr2[i4] = iArr2[i2];
                        }
                        if (z) {
                            solverVariable.f(this.f1453b);
                        }
                        if (this.f1462k) {
                            this.f1461j = i2;
                        }
                        solverVariable.f1535s--;
                        this.f1452a--;
                        return;
                    }
                    return;
                }
                if (i6 < i7) {
                    i4 = i2;
                }
                i2 = this.f1458g[i2];
            }
            int i8 = this.f1461j;
            int i9 = i8 + 1;
            if (this.f1462k) {
                int[] iArr3 = this.f1457f;
                if (iArr3[i8] != -1) {
                    i8 = iArr3.length;
                }
            } else {
                i8 = i9;
            }
            int[] iArr4 = this.f1457f;
            if (i8 >= iArr4.length && this.f1452a < iArr4.length) {
                int i10 = 0;
                while (true) {
                    int[] iArr5 = this.f1457f;
                    if (i10 >= iArr5.length) {
                        break;
                    }
                    if (iArr5[i10] == -1) {
                        i8 = i10;
                        break;
                    }
                    i10++;
                }
            }
            int[] iArr6 = this.f1457f;
            if (i8 >= iArr6.length) {
                i8 = iArr6.length;
                int i11 = this.f1455d * 2;
                this.f1455d = i11;
                this.f1462k = false;
                this.f1461j = i8 - 1;
                this.f1459h = Arrays.copyOf(this.f1459h, i11);
                this.f1457f = Arrays.copyOf(this.f1457f, this.f1455d);
                this.f1458g = Arrays.copyOf(this.f1458g, this.f1455d);
            }
            this.f1457f[i8] = solverVariable.f1525i;
            this.f1459h[i8] = f2;
            if (i4 != -1) {
                int[] iArr7 = this.f1458g;
                iArr7[i8] = iArr7[i4];
                iArr7[i4] = i8;
            } else {
                this.f1458g[i8] = this.f1460i;
                this.f1460i = i8;
            }
            solverVariable.f1535s++;
            solverVariable.c(this.f1453b);
            this.f1452a++;
            if (!this.f1462k) {
                this.f1461j++;
            }
            int i12 = this.f1461j;
            int[] iArr8 = this.f1457f;
            if (i12 >= iArr8.length) {
                this.f1462k = true;
                this.f1461j = iArr8.length - 1;
            }
        }
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public final void clear() {
        int i2 = this.f1460i;
        for (int i3 = 0; i2 != -1 && i3 < this.f1452a; i3++) {
            SolverVariable solverVariable = this.f1454c.f1472d[this.f1457f[i2]];
            if (solverVariable != null) {
                solverVariable.f(this.f1453b);
            }
            i2 = this.f1458g[i2];
        }
        this.f1460i = -1;
        this.f1461j = -1;
        this.f1462k = false;
        this.f1452a = 0;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public void d() {
        int i2 = this.f1460i;
        for (int i3 = 0; i2 != -1 && i3 < this.f1452a; i3++) {
            float[] fArr = this.f1459h;
            fArr[i2] = fArr[i2] * (-1.0f);
            i2 = this.f1458g[i2];
        }
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public final float e(SolverVariable solverVariable, boolean z) {
        if (this.f1456e == solverVariable) {
            this.f1456e = null;
        }
        int i2 = this.f1460i;
        if (i2 == -1) {
            return 0.0f;
        }
        int i3 = 0;
        int i4 = -1;
        while (i2 != -1 && i3 < this.f1452a) {
            if (this.f1457f[i2] == solverVariable.f1525i) {
                if (i2 == this.f1460i) {
                    this.f1460i = this.f1458g[i2];
                } else {
                    int[] iArr = this.f1458g;
                    iArr[i4] = iArr[i2];
                }
                if (z) {
                    solverVariable.f(this.f1453b);
                }
                solverVariable.f1535s--;
                this.f1452a--;
                this.f1457f[i2] = -1;
                if (this.f1462k) {
                    this.f1461j = i2;
                }
                return this.f1459h[i2];
            }
            i3++;
            i4 = i2;
            i2 = this.f1458g[i2];
        }
        return 0.0f;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public int f() {
        return this.f1452a;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public float g(ArrayRow arrayRow, boolean z) {
        float j2 = j(arrayRow.f1463a);
        e(arrayRow.f1463a, z);
        ArrayRow.ArrayRowVariables arrayRowVariables = arrayRow.f1467e;
        int f2 = arrayRowVariables.f();
        for (int i2 = 0; i2 < f2; i2++) {
            SolverVariable b2 = arrayRowVariables.b(i2);
            c(b2, arrayRowVariables.j(b2) * j2, z);
        }
        return j2;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public final void h(SolverVariable solverVariable, float f2) {
        if (f2 == 0.0f) {
            e(solverVariable, true);
            return;
        }
        int i2 = this.f1460i;
        if (i2 == -1) {
            this.f1460i = 0;
            this.f1459h[0] = f2;
            this.f1457f[0] = solverVariable.f1525i;
            this.f1458g[0] = -1;
            solverVariable.f1535s++;
            solverVariable.c(this.f1453b);
            this.f1452a++;
            if (this.f1462k) {
                return;
            }
            int i3 = this.f1461j + 1;
            this.f1461j = i3;
            int[] iArr = this.f1457f;
            if (i3 >= iArr.length) {
                this.f1462k = true;
                this.f1461j = iArr.length - 1;
                return;
            }
            return;
        }
        int i4 = -1;
        for (int i5 = 0; i2 != -1 && i5 < this.f1452a; i5++) {
            int i6 = this.f1457f[i2];
            int i7 = solverVariable.f1525i;
            if (i6 == i7) {
                this.f1459h[i2] = f2;
                return;
            }
            if (i6 < i7) {
                i4 = i2;
            }
            i2 = this.f1458g[i2];
        }
        int i8 = this.f1461j;
        int i9 = i8 + 1;
        if (this.f1462k) {
            int[] iArr2 = this.f1457f;
            if (iArr2[i8] != -1) {
                i8 = iArr2.length;
            }
        } else {
            i8 = i9;
        }
        int[] iArr3 = this.f1457f;
        if (i8 >= iArr3.length && this.f1452a < iArr3.length) {
            int i10 = 0;
            while (true) {
                int[] iArr4 = this.f1457f;
                if (i10 >= iArr4.length) {
                    break;
                }
                if (iArr4[i10] == -1) {
                    i8 = i10;
                    break;
                }
                i10++;
            }
        }
        int[] iArr5 = this.f1457f;
        if (i8 >= iArr5.length) {
            i8 = iArr5.length;
            int i11 = this.f1455d * 2;
            this.f1455d = i11;
            this.f1462k = false;
            this.f1461j = i8 - 1;
            this.f1459h = Arrays.copyOf(this.f1459h, i11);
            this.f1457f = Arrays.copyOf(this.f1457f, this.f1455d);
            this.f1458g = Arrays.copyOf(this.f1458g, this.f1455d);
        }
        this.f1457f[i8] = solverVariable.f1525i;
        this.f1459h[i8] = f2;
        if (i4 != -1) {
            int[] iArr6 = this.f1458g;
            iArr6[i8] = iArr6[i4];
            iArr6[i4] = i8;
        } else {
            this.f1458g[i8] = this.f1460i;
            this.f1460i = i8;
        }
        solverVariable.f1535s++;
        solverVariable.c(this.f1453b);
        int i12 = this.f1452a + 1;
        this.f1452a = i12;
        if (!this.f1462k) {
            this.f1461j++;
        }
        int[] iArr7 = this.f1457f;
        if (i12 >= iArr7.length) {
            this.f1462k = true;
        }
        if (this.f1461j >= iArr7.length) {
            this.f1462k = true;
            this.f1461j = iArr7.length - 1;
        }
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public float i(int i2) {
        int i3 = this.f1460i;
        for (int i4 = 0; i3 != -1 && i4 < this.f1452a; i4++) {
            if (i4 == i2) {
                return this.f1459h[i3];
            }
            i3 = this.f1458g[i3];
        }
        return 0.0f;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public final float j(SolverVariable solverVariable) {
        int i2 = this.f1460i;
        for (int i3 = 0; i2 != -1 && i3 < this.f1452a; i3++) {
            if (this.f1457f[i2] == solverVariable.f1525i) {
                return this.f1459h[i2];
            }
            i2 = this.f1458g[i2];
        }
        return 0.0f;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public void k(float f2) {
        int i2 = this.f1460i;
        for (int i3 = 0; i2 != -1 && i3 < this.f1452a; i3++) {
            float[] fArr = this.f1459h;
            fArr[i2] = fArr[i2] / f2;
            i2 = this.f1458g[i2];
        }
    }

    public String toString() {
        int i2 = this.f1460i;
        String str = "";
        for (int i3 = 0; i2 != -1 && i3 < this.f1452a; i3++) {
            str = ((str + " -> ") + this.f1459h[i2] + " : ") + this.f1454c.f1472d[this.f1457f[i2]];
            i2 = this.f1458g[i2];
        }
        return str;
    }
}
