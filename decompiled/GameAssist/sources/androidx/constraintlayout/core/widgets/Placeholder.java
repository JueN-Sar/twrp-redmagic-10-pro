package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;

/* loaded from: classes.dex */
public class Placeholder extends VirtualLayout {
    @Override // androidx.constraintlayout.core.widgets.VirtualLayout
    public void H1(int i2, int i3, int i4, int i5) {
        int E1 = E1() + F1();
        int G1 = G1() + D1();
        if (this.W0 > 0) {
            E1 += this.V0[0].Y();
            G1 += this.V0[0].z();
        }
        int max = Math.max(K(), E1);
        int max2 = Math.max(J(), G1);
        if (i2 != 1073741824) {
            i3 = i2 == Integer.MIN_VALUE ? Math.min(max, i3) : i2 == 0 ? max : 0;
        }
        if (i4 != 1073741824) {
            i5 = i4 == Integer.MIN_VALUE ? Math.min(max2, i5) : i4 == 0 ? max2 : 0;
        }
        M1(i3, i5);
        p1(i3);
        Q0(i5);
        L1(this.W0 > 0);
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public void g(LinearSystem linearSystem, boolean z) {
        super.g(linearSystem, z);
        if (this.W0 > 0) {
            ConstraintWidget constraintWidget = this.V0[0];
            constraintWidget.w0();
            ConstraintAnchor.Type type = ConstraintAnchor.Type.LEFT;
            constraintWidget.j(type, this, type);
            ConstraintAnchor.Type type2 = ConstraintAnchor.Type.RIGHT;
            constraintWidget.j(type2, this, type2);
            ConstraintAnchor.Type type3 = ConstraintAnchor.Type.TOP;
            constraintWidget.j(type3, this, type3);
            ConstraintAnchor.Type type4 = ConstraintAnchor.Type.BOTTOM;
            constraintWidget.j(type4, this, type4);
        }
    }
}
