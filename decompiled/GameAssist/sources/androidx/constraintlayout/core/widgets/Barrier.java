package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.SolverVariable;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import java.util.HashMap;

/* loaded from: classes.dex */
public class Barrier extends HelperWidget {
    private int X0 = 0;
    private boolean Y0 = true;
    private int Z0 = 0;
    boolean a1 = false;

    public int A1() {
        return this.X0;
    }

    public int B1() {
        return this.Z0;
    }

    public int C1() {
        int i2 = this.X0;
        if (i2 == 0 || i2 == 1) {
            return 0;
        }
        return (i2 == 2 || i2 == 3) ? 1 : -1;
    }

    protected void D1() {
        for (int i2 = 0; i2 < this.W0; i2++) {
            ConstraintWidget constraintWidget = this.V0[i2];
            if (this.Y0 || constraintWidget.h()) {
                int i3 = this.X0;
                if (i3 == 0 || i3 == 1) {
                    constraintWidget.X0(0, true);
                } else if (i3 == 2 || i3 == 3) {
                    constraintWidget.X0(1, true);
                }
            }
        }
    }

    public void E1(boolean z) {
        this.Y0 = z;
    }

    public void F1(int i2) {
        this.X0 = i2;
    }

    public void G1(int i2) {
        this.Z0 = i2;
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public void g(LinearSystem linearSystem, boolean z) {
        ConstraintAnchor[] constraintAnchorArr;
        boolean z2;
        int i2;
        int i3;
        int i4;
        ConstraintAnchor[] constraintAnchorArr2 = this.Y;
        constraintAnchorArr2[0] = this.Q;
        constraintAnchorArr2[2] = this.R;
        constraintAnchorArr2[1] = this.S;
        constraintAnchorArr2[3] = this.T;
        int i5 = 0;
        while (true) {
            constraintAnchorArr = this.Y;
            if (i5 >= constraintAnchorArr.length) {
                break;
            }
            ConstraintAnchor constraintAnchor = constraintAnchorArr[i5];
            constraintAnchor.f1968i = linearSystem.q(constraintAnchor);
            i5++;
        }
        int i6 = this.X0;
        if (i6 < 0 || i6 >= 4) {
            return;
        }
        ConstraintAnchor constraintAnchor2 = constraintAnchorArr[i6];
        if (!this.a1) {
            y1();
        }
        if (this.a1) {
            this.a1 = false;
            int i7 = this.X0;
            if (i7 == 0 || i7 == 1) {
                linearSystem.f(this.Q.f1968i, this.h0);
                linearSystem.f(this.S.f1968i, this.h0);
                return;
            } else {
                if (i7 == 2 || i7 == 3) {
                    linearSystem.f(this.R.f1968i, this.i0);
                    linearSystem.f(this.T.f1968i, this.i0);
                    return;
                }
                return;
            }
        }
        for (int i8 = 0; i8 < this.W0; i8++) {
            ConstraintWidget constraintWidget = this.V0[i8];
            if ((this.Y0 || constraintWidget.h()) && ((((i3 = this.X0) == 0 || i3 == 1) && constraintWidget.C() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.Q.f1965f != null && constraintWidget.S.f1965f != null) || (((i4 = this.X0) == 2 || i4 == 3) && constraintWidget.V() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.R.f1965f != null && constraintWidget.T.f1965f != null))) {
                z2 = true;
                break;
            }
        }
        z2 = false;
        boolean z3 = this.Q.l() || this.S.l();
        boolean z4 = this.R.l() || this.T.l();
        int i9 = !(!z2 && (((i2 = this.X0) == 0 && z3) || ((i2 == 2 && z4) || ((i2 == 1 && z3) || (i2 == 3 && z4))))) ? 4 : 5;
        for (int i10 = 0; i10 < this.W0; i10++) {
            ConstraintWidget constraintWidget2 = this.V0[i10];
            if (this.Y0 || constraintWidget2.h()) {
                SolverVariable q2 = linearSystem.q(constraintWidget2.Y[this.X0]);
                ConstraintAnchor[] constraintAnchorArr3 = constraintWidget2.Y;
                int i11 = this.X0;
                ConstraintAnchor constraintAnchor3 = constraintAnchorArr3[i11];
                constraintAnchor3.f1968i = q2;
                ConstraintAnchor constraintAnchor4 = constraintAnchor3.f1965f;
                int i12 = (constraintAnchor4 == null || constraintAnchor4.f1963d != this) ? 0 : constraintAnchor3.f1966g;
                if (i11 == 0 || i11 == 2) {
                    linearSystem.i(constraintAnchor2.f1968i, q2, this.Z0 - i12, z2);
                } else {
                    linearSystem.g(constraintAnchor2.f1968i, q2, this.Z0 + i12, z2);
                }
                linearSystem.e(constraintAnchor2.f1968i, q2, this.Z0 + i12, i9);
            }
        }
        int i13 = this.X0;
        if (i13 == 0) {
            linearSystem.e(this.S.f1968i, this.Q.f1968i, 0, 8);
            linearSystem.e(this.Q.f1968i, this.c0.S.f1968i, 0, 4);
            linearSystem.e(this.Q.f1968i, this.c0.Q.f1968i, 0, 0);
            return;
        }
        if (i13 == 1) {
            linearSystem.e(this.Q.f1968i, this.S.f1968i, 0, 8);
            linearSystem.e(this.Q.f1968i, this.c0.Q.f1968i, 0, 4);
            linearSystem.e(this.Q.f1968i, this.c0.S.f1968i, 0, 0);
        } else if (i13 == 2) {
            linearSystem.e(this.T.f1968i, this.R.f1968i, 0, 8);
            linearSystem.e(this.R.f1968i, this.c0.T.f1968i, 0, 4);
            linearSystem.e(this.R.f1968i, this.c0.R.f1968i, 0, 0);
        } else if (i13 == 3) {
            linearSystem.e(this.R.f1968i, this.T.f1968i, 0, 8);
            linearSystem.e(this.R.f1968i, this.c0.R.f1968i, 0, 4);
            linearSystem.e(this.R.f1968i, this.c0.T.f1968i, 0, 0);
        }
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public boolean h() {
        return true;
    }

    @Override // androidx.constraintlayout.core.widgets.HelperWidget, androidx.constraintlayout.core.widgets.ConstraintWidget
    public void n(ConstraintWidget constraintWidget, HashMap hashMap) {
        super.n(constraintWidget, hashMap);
        Barrier barrier = (Barrier) constraintWidget;
        this.X0 = barrier.X0;
        this.Y0 = barrier.Y0;
        this.Z0 = barrier.Z0;
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public boolean p0() {
        return this.a1;
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public boolean q0() {
        return this.a1;
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public String toString() {
        String str = "[Barrier] " + v() + " {";
        for (int i2 = 0; i2 < this.W0; i2++) {
            ConstraintWidget constraintWidget = this.V0[i2];
            if (i2 > 0) {
                str = str + ", ";
            }
            str = str + constraintWidget.v();
        }
        return str + "}";
    }

    public boolean y1() {
        int i2;
        int i3;
        int i4;
        boolean z = true;
        int i5 = 0;
        while (true) {
            i2 = this.W0;
            if (i5 >= i2) {
                break;
            }
            ConstraintWidget constraintWidget = this.V0[i5];
            if ((this.Y0 || constraintWidget.h()) && ((((i3 = this.X0) == 0 || i3 == 1) && !constraintWidget.p0()) || (((i4 = this.X0) == 2 || i4 == 3) && !constraintWidget.q0()))) {
                z = false;
            }
            i5++;
        }
        if (!z || i2 <= 0) {
            return false;
        }
        int i6 = 0;
        boolean z2 = false;
        for (int i7 = 0; i7 < this.W0; i7++) {
            ConstraintWidget constraintWidget2 = this.V0[i7];
            if (this.Y0 || constraintWidget2.h()) {
                if (!z2) {
                    int i8 = this.X0;
                    if (i8 == 0) {
                        i6 = constraintWidget2.q(ConstraintAnchor.Type.LEFT).e();
                    } else if (i8 == 1) {
                        i6 = constraintWidget2.q(ConstraintAnchor.Type.RIGHT).e();
                    } else if (i8 == 2) {
                        i6 = constraintWidget2.q(ConstraintAnchor.Type.TOP).e();
                    } else if (i8 == 3) {
                        i6 = constraintWidget2.q(ConstraintAnchor.Type.BOTTOM).e();
                    }
                    z2 = true;
                }
                int i9 = this.X0;
                if (i9 == 0) {
                    i6 = Math.min(i6, constraintWidget2.q(ConstraintAnchor.Type.LEFT).e());
                } else if (i9 == 1) {
                    i6 = Math.max(i6, constraintWidget2.q(ConstraintAnchor.Type.RIGHT).e());
                } else if (i9 == 2) {
                    i6 = Math.min(i6, constraintWidget2.q(ConstraintAnchor.Type.TOP).e());
                } else if (i9 == 3) {
                    i6 = Math.max(i6, constraintWidget2.q(ConstraintAnchor.Type.BOTTOM).e());
                }
            }
        }
        int i10 = i6 + this.Z0;
        int i11 = this.X0;
        if (i11 == 0 || i11 == 1) {
            K0(i10, i10);
        } else {
            N0(i10, i10);
        }
        this.a1 = true;
        return true;
    }

    public boolean z1() {
        return this.Y0;
    }
}
