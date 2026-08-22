package androidx.constraintlayout.core;

import java.util.Arrays;
import java.util.HashSet;

/* loaded from: classes.dex */
public class SolverVariable implements Comparable<SolverVariable> {
    private static int x = 1;

    /* renamed from: c, reason: collision with root package name */
    public boolean f1523c;

    /* renamed from: h, reason: collision with root package name */
    private String f1524h;

    /* renamed from: l, reason: collision with root package name */
    public float f1528l;

    /* renamed from: p, reason: collision with root package name */
    Type f1532p;

    /* renamed from: i, reason: collision with root package name */
    public int f1525i = -1;

    /* renamed from: j, reason: collision with root package name */
    int f1526j = -1;

    /* renamed from: k, reason: collision with root package name */
    public int f1527k = 0;

    /* renamed from: m, reason: collision with root package name */
    public boolean f1529m = false;

    /* renamed from: n, reason: collision with root package name */
    float[] f1530n = new float[9];

    /* renamed from: o, reason: collision with root package name */
    float[] f1531o = new float[9];

    /* renamed from: q, reason: collision with root package name */
    ArrayRow[] f1533q = new ArrayRow[16];

    /* renamed from: r, reason: collision with root package name */
    int f1534r = 0;

    /* renamed from: s, reason: collision with root package name */
    public int f1535s = 0;
    boolean t = false;
    int u = -1;
    float v = 0.0f;
    HashSet w = null;

    public enum Type {
        UNRESTRICTED,
        CONSTANT,
        SLACK,
        ERROR,
        UNKNOWN
    }

    public SolverVariable(Type type, String str) {
        this.f1532p = type;
    }

    static void e() {
        x++;
    }

    public final void c(ArrayRow arrayRow) {
        int i2 = 0;
        while (true) {
            int i3 = this.f1534r;
            if (i2 >= i3) {
                ArrayRow[] arrayRowArr = this.f1533q;
                if (i3 >= arrayRowArr.length) {
                    this.f1533q = (ArrayRow[]) Arrays.copyOf(arrayRowArr, arrayRowArr.length * 2);
                }
                ArrayRow[] arrayRowArr2 = this.f1533q;
                int i4 = this.f1534r;
                arrayRowArr2[i4] = arrayRow;
                this.f1534r = i4 + 1;
                return;
            }
            if (this.f1533q[i2] == arrayRow) {
                return;
            } else {
                i2++;
            }
        }
    }

    @Override // java.lang.Comparable
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public int compareTo(SolverVariable solverVariable) {
        return this.f1525i - solverVariable.f1525i;
    }

    public final void f(ArrayRow arrayRow) {
        int i2 = this.f1534r;
        int i3 = 0;
        while (i3 < i2) {
            if (this.f1533q[i3] == arrayRow) {
                while (i3 < i2 - 1) {
                    ArrayRow[] arrayRowArr = this.f1533q;
                    int i4 = i3 + 1;
                    arrayRowArr[i3] = arrayRowArr[i4];
                    i3 = i4;
                }
                this.f1534r--;
                return;
            }
            i3++;
        }
    }

    public void h() {
        this.f1524h = null;
        this.f1532p = Type.UNKNOWN;
        this.f1527k = 0;
        this.f1525i = -1;
        this.f1526j = -1;
        this.f1528l = 0.0f;
        this.f1529m = false;
        this.t = false;
        this.u = -1;
        this.v = 0.0f;
        int i2 = this.f1534r;
        for (int i3 = 0; i3 < i2; i3++) {
            this.f1533q[i3] = null;
        }
        this.f1534r = 0;
        this.f1535s = 0;
        this.f1523c = false;
        Arrays.fill(this.f1531o, 0.0f);
    }

    public void j(LinearSystem linearSystem, float f2) {
        this.f1528l = f2;
        this.f1529m = true;
        this.t = false;
        this.u = -1;
        this.v = 0.0f;
        int i2 = this.f1534r;
        this.f1526j = -1;
        for (int i3 = 0; i3 < i2; i3++) {
            this.f1533q[i3].A(linearSystem, this, false);
        }
        this.f1534r = 0;
    }

    public void l(Type type, String str) {
        this.f1532p = type;
    }

    public final void n(LinearSystem linearSystem, ArrayRow arrayRow) {
        int i2 = this.f1534r;
        for (int i3 = 0; i3 < i2; i3++) {
            this.f1533q[i3].B(linearSystem, arrayRow, false);
        }
        this.f1534r = 0;
    }

    public String toString() {
        if (this.f1524h != null) {
            return "" + this.f1524h;
        }
        return "" + this.f1525i;
    }
}
