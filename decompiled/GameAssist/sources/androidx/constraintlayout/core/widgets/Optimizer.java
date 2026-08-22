package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.widgets.ConstraintWidget;

/* loaded from: classes.dex */
public class Optimizer {

    /* renamed from: a, reason: collision with root package name */
    static boolean[] f2008a = new boolean[3];

    static void a(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, ConstraintWidget constraintWidget) {
        constraintWidget.t = -1;
        constraintWidget.u = -1;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = constraintWidgetContainer.b0[0];
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (dimensionBehaviour != dimensionBehaviour2 && constraintWidget.b0[0] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
            int i2 = constraintWidget.Q.f1966g;
            int Y = constraintWidgetContainer.Y() - constraintWidget.S.f1966g;
            ConstraintAnchor constraintAnchor = constraintWidget.Q;
            constraintAnchor.f1968i = linearSystem.q(constraintAnchor);
            ConstraintAnchor constraintAnchor2 = constraintWidget.S;
            constraintAnchor2.f1968i = linearSystem.q(constraintAnchor2);
            linearSystem.f(constraintWidget.Q.f1968i, i2);
            linearSystem.f(constraintWidget.S.f1968i, Y);
            constraintWidget.t = 2;
            constraintWidget.T0(i2, Y);
        }
        if (constraintWidgetContainer.b0[1] == dimensionBehaviour2 || constraintWidget.b0[1] != ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
            return;
        }
        int i3 = constraintWidget.R.f1966g;
        int z = constraintWidgetContainer.z() - constraintWidget.T.f1966g;
        ConstraintAnchor constraintAnchor3 = constraintWidget.R;
        constraintAnchor3.f1968i = linearSystem.q(constraintAnchor3);
        ConstraintAnchor constraintAnchor4 = constraintWidget.T;
        constraintAnchor4.f1968i = linearSystem.q(constraintAnchor4);
        linearSystem.f(constraintWidget.R.f1968i, i3);
        linearSystem.f(constraintWidget.T.f1968i, z);
        if (constraintWidget.n0 > 0 || constraintWidget.X() == 8) {
            ConstraintAnchor constraintAnchor5 = constraintWidget.U;
            constraintAnchor5.f1968i = linearSystem.q(constraintAnchor5);
            linearSystem.f(constraintWidget.U.f1968i, constraintWidget.n0 + i3);
        }
        constraintWidget.u = 2;
        constraintWidget.k1(i3, z);
    }

    public static final boolean b(int i2, int i3) {
        return (i2 & i3) == i3;
    }
}
