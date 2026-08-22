package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.SolverVariable;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import java.util.HashMap;

/* loaded from: classes.dex */
public class Guideline extends ConstraintWidget {
    protected float V0 = -1.0f;
    protected int W0 = -1;
    protected int X0 = -1;
    protected boolean Y0 = true;
    private ConstraintAnchor Z0 = this.R;
    private int a1 = 0;
    private int b1 = 0;
    private boolean c1;

    /* renamed from: androidx.constraintlayout.core.widgets.Guideline$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f2007a;

        static {
            int[] iArr = new int[ConstraintAnchor.Type.values().length];
            f2007a = iArr;
            try {
                iArr[ConstraintAnchor.Type.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2007a[ConstraintAnchor.Type.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2007a[ConstraintAnchor.Type.TOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2007a[ConstraintAnchor.Type.BOTTOM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f2007a[ConstraintAnchor.Type.BASELINE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f2007a[ConstraintAnchor.Type.CENTER.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f2007a[ConstraintAnchor.Type.CENTER_X.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f2007a[ConstraintAnchor.Type.CENTER_Y.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f2007a[ConstraintAnchor.Type.NONE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    public Guideline() {
        this.Z.clear();
        this.Z.add(this.Z0);
        int length = this.Y.length;
        for (int i2 = 0; i2 < length; i2++) {
            this.Y[i2] = this.Z0;
        }
    }

    public float A1() {
        return this.V0;
    }

    public void B1(int i2) {
        this.Z0.t(i2);
        this.c1 = true;
    }

    public void C1(int i2) {
        if (i2 > -1) {
            this.V0 = -1.0f;
            this.W0 = i2;
            this.X0 = -1;
        }
    }

    public void D1(int i2) {
        if (i2 > -1) {
            this.V0 = -1.0f;
            this.W0 = -1;
            this.X0 = i2;
        }
    }

    public void E1(float f2) {
        if (f2 > -1.0f) {
            this.V0 = f2;
            this.W0 = -1;
            this.X0 = -1;
        }
    }

    public void F1(int i2) {
        if (this.a1 == i2) {
            return;
        }
        this.a1 = i2;
        this.Z.clear();
        if (this.a1 == 1) {
            this.Z0 = this.Q;
        } else {
            this.Z0 = this.R;
        }
        this.Z.add(this.Z0);
        int length = this.Y.length;
        for (int i3 = 0; i3 < length; i3++) {
            this.Y[i3] = this.Z0;
        }
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public void g(LinearSystem linearSystem, boolean z) {
        ConstraintWidgetContainer constraintWidgetContainer = (ConstraintWidgetContainer) M();
        if (constraintWidgetContainer == null) {
            return;
        }
        ConstraintAnchor q2 = constraintWidgetContainer.q(ConstraintAnchor.Type.LEFT);
        ConstraintAnchor q3 = constraintWidgetContainer.q(ConstraintAnchor.Type.RIGHT);
        ConstraintWidget constraintWidget = this.c0;
        boolean z2 = constraintWidget != null && constraintWidget.b0[0] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (this.a1 == 0) {
            q2 = constraintWidgetContainer.q(ConstraintAnchor.Type.TOP);
            q3 = constraintWidgetContainer.q(ConstraintAnchor.Type.BOTTOM);
            ConstraintWidget constraintWidget2 = this.c0;
            z2 = constraintWidget2 != null && constraintWidget2.b0[1] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        }
        if (this.c1 && this.Z0.n()) {
            SolverVariable q4 = linearSystem.q(this.Z0);
            linearSystem.f(q4, this.Z0.e());
            if (this.W0 != -1) {
                if (z2) {
                    linearSystem.h(linearSystem.q(q3), q4, 0, 5);
                }
            } else if (this.X0 != -1 && z2) {
                SolverVariable q5 = linearSystem.q(q3);
                linearSystem.h(q4, linearSystem.q(q2), 0, 5);
                linearSystem.h(q5, q4, 0, 5);
            }
            this.c1 = false;
            return;
        }
        if (this.W0 != -1) {
            SolverVariable q6 = linearSystem.q(this.Z0);
            linearSystem.e(q6, linearSystem.q(q2), this.W0, 8);
            if (z2) {
                linearSystem.h(linearSystem.q(q3), q6, 0, 5);
                return;
            }
            return;
        }
        if (this.X0 == -1) {
            if (this.V0 != -1.0f) {
                linearSystem.d(LinearSystem.s(linearSystem, linearSystem.q(this.Z0), linearSystem.q(q3), this.V0));
                return;
            }
            return;
        }
        SolverVariable q7 = linearSystem.q(this.Z0);
        SolverVariable q8 = linearSystem.q(q3);
        linearSystem.e(q7, q8, -this.X0, 8);
        if (z2) {
            linearSystem.h(q7, linearSystem.q(q2), 0, 5);
            linearSystem.h(q8, q7, 0, 5);
        }
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public boolean h() {
        return true;
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public void n(ConstraintWidget constraintWidget, HashMap hashMap) {
        super.n(constraintWidget, hashMap);
        Guideline guideline = (Guideline) constraintWidget;
        this.V0 = guideline.V0;
        this.W0 = guideline.W0;
        this.X0 = guideline.X0;
        this.Y0 = guideline.Y0;
        F1(guideline.a1);
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public boolean p0() {
        return this.c1;
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public ConstraintAnchor q(ConstraintAnchor.Type type) {
        int i2 = AnonymousClass1.f2007a[type.ordinal()];
        if (i2 == 1 || i2 == 2) {
            if (this.a1 == 1) {
                return this.Z0;
            }
            return null;
        }
        if ((i2 == 3 || i2 == 4) && this.a1 == 0) {
            return this.Z0;
        }
        return null;
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public boolean q0() {
        return this.c1;
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public void v1(LinearSystem linearSystem, boolean z) {
        if (M() == null) {
            return;
        }
        int A = linearSystem.A(this.Z0);
        if (this.a1 == 1) {
            r1(A);
            s1(0);
            Q0(M().z());
            p1(0);
            return;
        }
        r1(0);
        s1(A);
        p1(M().Y());
        Q0(0);
    }

    public ConstraintAnchor w1() {
        return this.Z0;
    }

    public int x1() {
        return this.a1;
    }

    public int y1() {
        return this.W0;
    }

    public int z1() {
        return this.X0;
    }
}
