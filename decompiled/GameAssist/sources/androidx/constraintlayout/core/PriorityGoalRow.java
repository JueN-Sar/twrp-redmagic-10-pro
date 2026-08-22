package androidx.constraintlayout.core;

import androidx.constraintlayout.core.ArrayRow;
import java.util.Arrays;
import java.util.Comparator;

/* loaded from: classes.dex */
public class PriorityGoalRow extends ArrayRow {

    /* renamed from: g, reason: collision with root package name */
    private int f1513g;

    /* renamed from: h, reason: collision with root package name */
    private SolverVariable[] f1514h;

    /* renamed from: i, reason: collision with root package name */
    private SolverVariable[] f1515i;

    /* renamed from: j, reason: collision with root package name */
    private int f1516j;

    /* renamed from: k, reason: collision with root package name */
    GoalVariableAccessor f1517k;

    /* renamed from: l, reason: collision with root package name */
    Cache f1518l;

    class GoalVariableAccessor {

        /* renamed from: a, reason: collision with root package name */
        SolverVariable f1520a;

        /* renamed from: b, reason: collision with root package name */
        PriorityGoalRow f1521b;

        GoalVariableAccessor(PriorityGoalRow priorityGoalRow) {
            this.f1521b = priorityGoalRow;
        }

        public boolean a(SolverVariable solverVariable, float f2) {
            boolean z = true;
            if (!this.f1520a.f1523c) {
                for (int i2 = 0; i2 < 9; i2++) {
                    float f3 = solverVariable.f1531o[i2];
                    if (f3 != 0.0f) {
                        float f4 = f3 * f2;
                        if (Math.abs(f4) < 1.0E-4f) {
                            f4 = 0.0f;
                        }
                        this.f1520a.f1531o[i2] = f4;
                    } else {
                        this.f1520a.f1531o[i2] = 0.0f;
                    }
                }
                return true;
            }
            for (int i3 = 0; i3 < 9; i3++) {
                float[] fArr = this.f1520a.f1531o;
                float f5 = fArr[i3] + (solverVariable.f1531o[i3] * f2);
                fArr[i3] = f5;
                if (Math.abs(f5) < 1.0E-4f) {
                    this.f1520a.f1531o[i3] = 0.0f;
                } else {
                    z = false;
                }
            }
            if (z) {
                PriorityGoalRow.this.G(this.f1520a);
            }
            return false;
        }

        public void b(SolverVariable solverVariable) {
            this.f1520a = solverVariable;
        }

        public final boolean c() {
            for (int i2 = 8; i2 >= 0; i2--) {
                float f2 = this.f1520a.f1531o[i2];
                if (f2 > 0.0f) {
                    return false;
                }
                if (f2 < 0.0f) {
                    return true;
                }
            }
            return false;
        }

        public final boolean d(SolverVariable solverVariable) {
            for (int i2 = 8; i2 >= 0; i2--) {
                float f2 = solverVariable.f1531o[i2];
                float f3 = this.f1520a.f1531o[i2];
                if (f3 != f2) {
                    return f3 < f2;
                }
            }
            return false;
        }

        public void e() {
            Arrays.fill(this.f1520a.f1531o, 0.0f);
        }

        public String toString() {
            String str = "[ ";
            if (this.f1520a != null) {
                for (int i2 = 0; i2 < 9; i2++) {
                    str = str + this.f1520a.f1531o[i2] + " ";
                }
            }
            return str + "] " + this.f1520a;
        }
    }

    public PriorityGoalRow(Cache cache) {
        super(cache);
        this.f1513g = 128;
        this.f1514h = new SolverVariable[128];
        this.f1515i = new SolverVariable[128];
        this.f1516j = 0;
        this.f1517k = new GoalVariableAccessor(this);
        this.f1518l = cache;
    }

    private void F(SolverVariable solverVariable) {
        int i2;
        int i3 = this.f1516j + 1;
        SolverVariable[] solverVariableArr = this.f1514h;
        if (i3 > solverVariableArr.length) {
            SolverVariable[] solverVariableArr2 = (SolverVariable[]) Arrays.copyOf(solverVariableArr, solverVariableArr.length * 2);
            this.f1514h = solverVariableArr2;
            this.f1515i = (SolverVariable[]) Arrays.copyOf(solverVariableArr2, solverVariableArr2.length * 2);
        }
        SolverVariable[] solverVariableArr3 = this.f1514h;
        int i4 = this.f1516j;
        solverVariableArr3[i4] = solverVariable;
        int i5 = i4 + 1;
        this.f1516j = i5;
        if (i5 > 1 && solverVariableArr3[i4].f1525i > solverVariable.f1525i) {
            int i6 = 0;
            while (true) {
                i2 = this.f1516j;
                if (i6 >= i2) {
                    break;
                }
                this.f1515i[i6] = this.f1514h[i6];
                i6++;
            }
            Arrays.sort(this.f1515i, 0, i2, new Comparator<SolverVariable>() { // from class: androidx.constraintlayout.core.PriorityGoalRow.1
                @Override // java.util.Comparator
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public int compare(SolverVariable solverVariable2, SolverVariable solverVariable3) {
                    return solverVariable2.f1525i - solverVariable3.f1525i;
                }
            });
            for (int i7 = 0; i7 < this.f1516j; i7++) {
                this.f1514h[i7] = this.f1515i[i7];
            }
        }
        solverVariable.f1523c = true;
        solverVariable.c(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void G(SolverVariable solverVariable) {
        int i2 = 0;
        while (i2 < this.f1516j) {
            if (this.f1514h[i2] == solverVariable) {
                while (true) {
                    int i3 = this.f1516j;
                    if (i2 >= i3 - 1) {
                        this.f1516j = i3 - 1;
                        solverVariable.f1523c = false;
                        return;
                    } else {
                        SolverVariable[] solverVariableArr = this.f1514h;
                        int i4 = i2 + 1;
                        solverVariableArr[i2] = solverVariableArr[i4];
                        i2 = i4;
                    }
                }
            } else {
                i2++;
            }
        }
    }

    @Override // androidx.constraintlayout.core.ArrayRow
    public void B(LinearSystem linearSystem, ArrayRow arrayRow, boolean z) {
        SolverVariable solverVariable = arrayRow.f1463a;
        if (solverVariable == null) {
            return;
        }
        ArrayRow.ArrayRowVariables arrayRowVariables = arrayRow.f1467e;
        int f2 = arrayRowVariables.f();
        for (int i2 = 0; i2 < f2; i2++) {
            SolverVariable b2 = arrayRowVariables.b(i2);
            float i3 = arrayRowVariables.i(i2);
            this.f1517k.b(b2);
            if (this.f1517k.a(solverVariable, i3)) {
                F(b2);
            }
            this.f1464b += arrayRow.f1464b * i3;
        }
        G(solverVariable);
    }

    @Override // androidx.constraintlayout.core.ArrayRow, androidx.constraintlayout.core.LinearSystem.Row
    public SolverVariable b(LinearSystem linearSystem, boolean[] zArr) {
        int i2 = -1;
        for (int i3 = 0; i3 < this.f1516j; i3++) {
            SolverVariable solverVariable = this.f1514h[i3];
            if (!zArr[solverVariable.f1525i]) {
                this.f1517k.b(solverVariable);
                if (i2 == -1) {
                    if (!this.f1517k.c()) {
                    }
                    i2 = i3;
                } else {
                    if (!this.f1517k.d(this.f1514h[i2])) {
                    }
                    i2 = i3;
                }
            }
        }
        if (i2 == -1) {
            return null;
        }
        return this.f1514h[i2];
    }

    @Override // androidx.constraintlayout.core.ArrayRow, androidx.constraintlayout.core.LinearSystem.Row
    public void c(SolverVariable solverVariable) {
        this.f1517k.b(solverVariable);
        this.f1517k.e();
        solverVariable.f1531o[solverVariable.f1527k] = 1.0f;
        F(solverVariable);
    }

    @Override // androidx.constraintlayout.core.ArrayRow, androidx.constraintlayout.core.LinearSystem.Row
    public void clear() {
        this.f1516j = 0;
        this.f1464b = 0.0f;
    }

    @Override // androidx.constraintlayout.core.ArrayRow, androidx.constraintlayout.core.LinearSystem.Row
    public boolean isEmpty() {
        return this.f1516j == 0;
    }

    @Override // androidx.constraintlayout.core.ArrayRow
    public String toString() {
        String str = " goal -> (" + this.f1464b + ") : ";
        for (int i2 = 0; i2 < this.f1516j; i2++) {
            this.f1517k.b(this.f1514h[i2]);
            str = str + this.f1517k + " ";
        }
        return str;
    }
}
