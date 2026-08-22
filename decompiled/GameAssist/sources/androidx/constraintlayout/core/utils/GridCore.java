package androidx.constraintlayout.core.utils;

import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.utils.GridCore;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.core.widgets.VirtualLayout;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;

/* loaded from: classes.dex */
public class GridCore extends VirtualLayout {
    Set A1;
    private int[][] B1;
    private int C1;
    private int[][] D1;
    private int E1;
    ConstraintWidgetContainer k1;
    private ConstraintWidget[] l1;
    private boolean m1;
    private int n1;
    private int o1;
    private int p1;
    private int q1;
    private float r1;
    private float s1;
    private String t1;
    private String u1;
    private String v1;
    private String w1;
    private int x1;
    private int y1;
    private boolean[][] z1;

    private void V1() {
        p2();
        o2();
        W1();
    }

    private void W1() {
        int[][] iArr;
        int i2;
        for (int i3 = 0; i3 < this.W0; i3++) {
            if (!this.A1.contains(this.V0[i3].f1983o)) {
                int c2 = c2();
                int d2 = d2(c2);
                int b2 = b2(c2);
                if (c2 == -1) {
                    return;
                }
                if (i2() && (iArr = this.D1) != null && (i2 = this.E1) < iArr.length) {
                    int[] iArr2 = iArr[i2];
                    if (iArr2[0] == c2) {
                        this.z1[d2][b2] = true;
                        if (h2(d2, b2, iArr2[1], iArr2[2])) {
                            ConstraintWidget constraintWidget = this.V0[i3];
                            int[] iArr3 = this.D1[this.E1];
                            Z1(constraintWidget, d2, b2, iArr3[1], iArr3[2]);
                            this.E1++;
                        }
                    }
                }
                Z1(this.V0[i3], d2, b2, 1, 1);
            }
        }
    }

    private void X1(ConstraintWidget constraintWidget) {
        constraintWidget.W0(-1.0f);
        constraintWidget.Q.q();
        constraintWidget.S.q();
    }

    private void Y1(ConstraintWidget constraintWidget) {
        constraintWidget.n1(-1.0f);
        constraintWidget.R.q();
        constraintWidget.T.q();
        constraintWidget.U.q();
    }

    private void Z1(ConstraintWidget constraintWidget, int i2, int i3, int i4, int i5) {
        constraintWidget.Q.a(this.l1[i3].Q, 0);
        constraintWidget.R.a(this.l1[i2].R, 0);
        constraintWidget.S.a(this.l1[(i3 + i5) - 1].S, 0);
        constraintWidget.T.a(this.l1[(i2 + i4) - 1].T, 0);
    }

    private void a2() {
        int max = Math.max(this.n1, this.p1);
        ConstraintWidget[] constraintWidgetArr = this.l1;
        int i2 = 0;
        if (constraintWidgetArr == null) {
            this.l1 = new ConstraintWidget[max];
            while (true) {
                ConstraintWidget[] constraintWidgetArr2 = this.l1;
                if (i2 >= constraintWidgetArr2.length) {
                    return;
                }
                constraintWidgetArr2[i2] = l2();
                i2++;
            }
        } else {
            if (max == constraintWidgetArr.length) {
                return;
            }
            ConstraintWidget[] constraintWidgetArr3 = new ConstraintWidget[max];
            while (i2 < max) {
                ConstraintWidget[] constraintWidgetArr4 = this.l1;
                if (i2 < constraintWidgetArr4.length) {
                    constraintWidgetArr3[i2] = constraintWidgetArr4[i2];
                } else {
                    constraintWidgetArr3[i2] = l2();
                }
                i2++;
            }
            while (true) {
                ConstraintWidget[] constraintWidgetArr5 = this.l1;
                if (max >= constraintWidgetArr5.length) {
                    this.l1 = constraintWidgetArr3;
                    return;
                } else {
                    this.k1.z1(constraintWidgetArr5[max]);
                    max++;
                }
            }
        }
    }

    private int b2(int i2) {
        return this.x1 == 1 ? i2 / this.n1 : i2 % this.p1;
    }

    private int c2() {
        boolean z = false;
        int i2 = 0;
        while (!z) {
            i2 = this.y1;
            if (i2 >= this.n1 * this.p1) {
                return -1;
            }
            int d2 = d2(i2);
            int b2 = b2(this.y1);
            boolean[] zArr = this.z1[d2];
            if (zArr[b2]) {
                zArr[b2] = false;
                z = true;
            }
            this.y1++;
        }
        return i2;
    }

    private int d2(int i2) {
        return this.x1 == 1 ? i2 % this.n1 : i2 / this.p1;
    }

    private void e2(int[][] iArr) {
        for (int[] iArr2 : iArr) {
            if (!h2(d2(iArr2[0]), b2(iArr2[0]), iArr2[1], iArr2[2])) {
                return;
            }
        }
    }

    private void f2(int[][] iArr) {
        if (i2()) {
            return;
        }
        for (int i2 = 0; i2 < iArr.length; i2++) {
            int d2 = d2(iArr[i2][0]);
            int b2 = b2(iArr[i2][0]);
            int[] iArr2 = iArr[i2];
            if (!h2(d2, b2, iArr2[1], iArr2[2])) {
                return;
            }
            ConstraintWidget constraintWidget = this.V0[i2];
            int[] iArr3 = iArr[i2];
            Z1(constraintWidget, d2, b2, iArr3[1], iArr3[2]);
            this.A1.add(this.V0[i2].f1983o);
        }
    }

    private void g2() {
        boolean[][] zArr = (boolean[][]) Array.newInstance((Class<?>) Boolean.TYPE, this.n1, this.p1);
        this.z1 = zArr;
        for (boolean[] zArr2 : zArr) {
            Arrays.fill(zArr2, true);
        }
        int i2 = this.W0;
        if (i2 > 0) {
            int[][] iArr = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, i2, 4);
            this.B1 = iArr;
            for (int[] iArr2 : iArr) {
                Arrays.fill(iArr2, -1);
            }
        }
    }

    private boolean h2(int i2, int i3, int i4, int i5) {
        for (int i6 = i2; i6 < i2 + i4; i6++) {
            for (int i7 = i3; i7 < i3 + i5; i7++) {
                boolean[][] zArr = this.z1;
                if (i6 < zArr.length && i7 < zArr[0].length) {
                    boolean[] zArr2 = zArr[i6];
                    if (zArr2[i7]) {
                        zArr2[i7] = false;
                    }
                }
                return false;
            }
        }
        return true;
    }

    private boolean i2() {
        return (this.C1 & 2) > 0;
    }

    private boolean j2() {
        return (this.C1 & 1) > 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int k2(String str, String str2) {
        return Integer.parseInt(str.split(":")[0]) - Integer.parseInt(str2.split(":")[0]);
    }

    private ConstraintWidget l2() {
        ConstraintWidget constraintWidget = new ConstraintWidget();
        ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = constraintWidget.b0;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        dimensionBehaviourArr[0] = dimensionBehaviour;
        dimensionBehaviourArr[1] = dimensionBehaviour;
        constraintWidget.f1983o = String.valueOf(constraintWidget.hashCode());
        return constraintWidget;
    }

    private int[][] m2(String str, boolean z) {
        try {
            String[] split = str.split(",");
            Arrays.sort(split, new Comparator() { // from class: a.a
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    int k2;
                    k2 = GridCore.k2((String) obj, (String) obj2);
                    return k2;
                }
            });
            int[][] iArr = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, split.length, 3);
            if (this.n1 != 1 && this.p1 != 1) {
                for (int i2 = 0; i2 < split.length; i2++) {
                    String[] split2 = split[i2].trim().split(":");
                    String[] split3 = split2[1].split("x");
                    iArr[i2][0] = Integer.parseInt(split2[0]);
                    if (j2()) {
                        iArr[i2][1] = Integer.parseInt(split3[1]);
                        iArr[i2][2] = Integer.parseInt(split3[0]);
                    } else {
                        iArr[i2][1] = Integer.parseInt(split3[0]);
                        iArr[i2][2] = Integer.parseInt(split3[1]);
                    }
                }
                return iArr;
            }
            int i3 = 0;
            int i4 = 0;
            for (int i5 = 0; i5 < split.length; i5++) {
                String[] split4 = split[i5].trim().split(":");
                iArr[i5][0] = Integer.parseInt(split4[0]);
                int[] iArr2 = iArr[i5];
                iArr2[1] = 1;
                iArr2[2] = 1;
                if (this.p1 == 1) {
                    iArr2[1] = Integer.parseInt(split4[1]);
                    i3 += iArr[i5][1];
                    if (z) {
                        i3--;
                    }
                }
                if (this.n1 == 1) {
                    iArr[i5][2] = Integer.parseInt(split4[1]);
                    i4 += iArr[i5][2];
                    if (z) {
                        i4--;
                    }
                }
            }
            if (i3 != 0 && !this.m1) {
                r2(this.n1 + i3);
            }
            if (i4 != 0 && !this.m1) {
                q2(this.p1 + i4);
            }
            this.m1 = true;
            return iArr;
        } catch (Exception unused) {
            return null;
        }
    }

    private float[] n2(int i2, String str) {
        if (str == null || str.trim().isEmpty()) {
            return null;
        }
        String[] split = str.split(",");
        float[] fArr = new float[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            if (i3 < split.length) {
                try {
                    fArr[i3] = Float.parseFloat(split[i3]);
                } catch (Exception e2) {
                    System.err.println("Error parsing `" + split[i3] + "`: " + e2.getMessage());
                    fArr[i3] = 1.0f;
                }
            } else {
                fArr[i3] = 1.0f;
            }
        }
        return fArr;
    }

    private void o2() {
        int i2;
        int max = Math.max(this.n1, this.p1);
        ConstraintWidget constraintWidget = this.l1[0];
        float[] n2 = n2(this.p1, this.u1);
        if (this.p1 == 1) {
            X1(constraintWidget);
            constraintWidget.Q.a(this.Q, 0);
            constraintWidget.S.a(this.S, 0);
            return;
        }
        int i3 = 0;
        while (true) {
            i2 = this.p1;
            if (i3 >= i2) {
                break;
            }
            ConstraintWidget constraintWidget2 = this.l1[i3];
            X1(constraintWidget2);
            if (n2 != null) {
                constraintWidget2.W0(n2[i3]);
            }
            if (i3 > 0) {
                constraintWidget2.Q.a(this.l1[i3 - 1].S, 0);
            } else {
                constraintWidget2.Q.a(this.Q, 0);
            }
            if (i3 < this.p1 - 1) {
                constraintWidget2.S.a(this.l1[i3 + 1].Q, 0);
            } else {
                constraintWidget2.S.a(this.S, 0);
            }
            if (i3 > 0) {
                constraintWidget2.Q.f1966g = (int) this.r1;
            }
            i3++;
        }
        while (i2 < max) {
            ConstraintWidget constraintWidget3 = this.l1[i2];
            X1(constraintWidget3);
            constraintWidget3.Q.a(this.Q, 0);
            constraintWidget3.S.a(this.S, 0);
            i2++;
        }
    }

    private void p2() {
        int i2;
        int max = Math.max(this.n1, this.p1);
        ConstraintWidget constraintWidget = this.l1[0];
        float[] n2 = n2(this.n1, this.t1);
        if (this.n1 == 1) {
            Y1(constraintWidget);
            constraintWidget.R.a(this.R, 0);
            constraintWidget.T.a(this.T, 0);
            return;
        }
        int i3 = 0;
        while (true) {
            i2 = this.n1;
            if (i3 >= i2) {
                break;
            }
            ConstraintWidget constraintWidget2 = this.l1[i3];
            Y1(constraintWidget2);
            if (n2 != null) {
                constraintWidget2.n1(n2[i3]);
            }
            if (i3 > 0) {
                constraintWidget2.R.a(this.l1[i3 - 1].T, 0);
            } else {
                constraintWidget2.R.a(this.R, 0);
            }
            if (i3 < this.n1 - 1) {
                constraintWidget2.T.a(this.l1[i3 + 1].R, 0);
            } else {
                constraintWidget2.T.a(this.T, 0);
            }
            if (i3 > 0) {
                constraintWidget2.R.f1966g = (int) this.s1;
            }
            i3++;
        }
        while (i2 < max) {
            ConstraintWidget constraintWidget3 = this.l1[i2];
            Y1(constraintWidget3);
            constraintWidget3.R.a(this.R, 0);
            constraintWidget3.T.a(this.T, 0);
            i2++;
        }
    }

    private void s2(boolean z) {
        int[][] m2;
        if (this.n1 < 1 || this.p1 < 1) {
            return;
        }
        if (z) {
            for (int i2 = 0; i2 < this.z1.length; i2++) {
                int i3 = 0;
                while (true) {
                    boolean[][] zArr = this.z1;
                    if (i3 < zArr[0].length) {
                        zArr[i2][i3] = true;
                        i3++;
                    }
                }
            }
            this.A1.clear();
        }
        this.y1 = 0;
        String str = this.w1;
        if (str != null && !str.trim().isEmpty() && (m2 = m2(this.w1, false)) != null) {
            e2(m2);
        }
        String str2 = this.v1;
        if (str2 != null && !str2.trim().isEmpty()) {
            this.D1 = m2(this.v1, true);
        }
        a2();
        int[][] iArr = this.D1;
        if (iArr != null) {
            f2(iArr);
        }
    }

    private void t2() {
        int i2;
        int i3 = this.o1;
        if (i3 != 0 && (i2 = this.q1) != 0) {
            this.n1 = i3;
            this.p1 = i2;
            return;
        }
        int i4 = this.q1;
        if (i4 > 0) {
            this.p1 = i4;
            this.n1 = ((this.W0 + i4) - 1) / i4;
        } else if (i3 > 0) {
            this.n1 = i3;
            this.p1 = ((this.W0 + i3) - 1) / i3;
        } else {
            int sqrt = (int) (Math.sqrt(this.W0) + 1.5d);
            this.n1 = sqrt;
            this.p1 = ((this.W0 + sqrt) - 1) / sqrt;
        }
    }

    @Override // androidx.constraintlayout.core.widgets.VirtualLayout
    public void H1(int i2, int i3, int i4, int i5) {
        super.H1(i2, i3, i4, i5);
        this.k1 = (ConstraintWidgetContainer) M();
        s2(false);
        this.k1.w1(this.l1);
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public void g(LinearSystem linearSystem, boolean z) {
        super.g(linearSystem, z);
        V1();
    }

    public void q2(int i2) {
        if (i2 <= 50 && this.q1 != i2) {
            this.q1 = i2;
            t2();
            g2();
        }
    }

    public void r2(int i2) {
        if (i2 <= 50 && this.o1 != i2) {
            this.o1 = i2;
            t2();
            g2();
        }
    }
}
