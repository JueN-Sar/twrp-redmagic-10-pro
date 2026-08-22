package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.Cache;
import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.state.WidgetFrame;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.analyzer.ChainRun;
import androidx.constraintlayout.core.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun;
import androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun;
import androidx.constraintlayout.core.widgets.analyzer.WidgetRun;
import com.google.android.gms.common.api.Api;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: classes.dex */
public class ConstraintWidget {
    public static float U0 = 0.5f;
    int A0;
    int B0;
    boolean C0;
    boolean D0;
    boolean E0;
    public boolean F;
    boolean F0;
    public boolean G;
    boolean G0;
    boolean H0;
    boolean I0;
    int J0;
    int K0;
    boolean L0;
    private boolean M;
    boolean M0;
    public float[] N0;
    protected ConstraintWidget[] O0;
    protected ConstraintWidget[] P0;
    ConstraintWidget Q0;
    ConstraintWidget R0;
    public int S0;
    public int T0;
    public ConstraintAnchor X;
    public ConstraintAnchor[] Y;
    protected ArrayList Z;
    private boolean[] a0;
    public DimensionBehaviour[] b0;

    /* renamed from: c, reason: collision with root package name */
    public ChainRun f1971c;
    public ConstraintWidget c0;

    /* renamed from: d, reason: collision with root package name */
    public ChainRun f1972d;
    int d0;
    int e0;
    public float f0;
    protected int g0;
    protected int h0;
    protected int i0;
    int j0;
    int k0;
    protected int l0;
    protected int m0;
    int n0;

    /* renamed from: o, reason: collision with root package name */
    public String f1983o;
    protected int o0;
    protected int p0;
    float q0;
    float r0;
    private Object s0;
    private int t0;
    private int u0;
    private boolean v0;
    private String w0;
    private String x0;
    int y0;
    int z0;

    /* renamed from: a, reason: collision with root package name */
    public boolean f1969a = false;

    /* renamed from: b, reason: collision with root package name */
    public WidgetRun[] f1970b = new WidgetRun[2];

    /* renamed from: e, reason: collision with root package name */
    public HorizontalWidgetRun f1973e = null;

    /* renamed from: f, reason: collision with root package name */
    public VerticalWidgetRun f1974f = null;

    /* renamed from: g, reason: collision with root package name */
    public boolean[] f1975g = {true, true};

    /* renamed from: h, reason: collision with root package name */
    boolean f1976h = false;

    /* renamed from: i, reason: collision with root package name */
    private boolean f1977i = true;

    /* renamed from: j, reason: collision with root package name */
    private boolean f1978j = false;

    /* renamed from: k, reason: collision with root package name */
    private boolean f1979k = true;

    /* renamed from: l, reason: collision with root package name */
    private int f1980l = -1;

    /* renamed from: m, reason: collision with root package name */
    private int f1981m = -1;

    /* renamed from: n, reason: collision with root package name */
    public WidgetFrame f1982n = new WidgetFrame(this);

    /* renamed from: p, reason: collision with root package name */
    private boolean f1984p = false;

    /* renamed from: q, reason: collision with root package name */
    private boolean f1985q = false;

    /* renamed from: r, reason: collision with root package name */
    private boolean f1986r = false;

    /* renamed from: s, reason: collision with root package name */
    private boolean f1987s = false;
    public int t = -1;
    public int u = -1;
    private int v = 0;
    public int w = 0;
    public int x = 0;
    public int[] y = new int[2];
    public int z = 0;
    public int A = 0;
    public float B = 1.0f;
    public int C = 0;
    public int D = 0;
    public float E = 1.0f;
    int H = -1;
    float I = 1.0f;
    private int[] J = {Api.BaseClientBuilder.API_PRIORITY_OTHER, Api.BaseClientBuilder.API_PRIORITY_OTHER};
    public float K = Float.NaN;
    private boolean L = false;
    private boolean N = false;
    private int O = 0;
    private int P = 0;
    public ConstraintAnchor Q = new ConstraintAnchor(this, ConstraintAnchor.Type.LEFT);
    public ConstraintAnchor R = new ConstraintAnchor(this, ConstraintAnchor.Type.TOP);
    public ConstraintAnchor S = new ConstraintAnchor(this, ConstraintAnchor.Type.RIGHT);
    public ConstraintAnchor T = new ConstraintAnchor(this, ConstraintAnchor.Type.BOTTOM);
    public ConstraintAnchor U = new ConstraintAnchor(this, ConstraintAnchor.Type.BASELINE);
    ConstraintAnchor V = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_X);
    ConstraintAnchor W = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_Y);

    /* renamed from: androidx.constraintlayout.core.widgets.ConstraintWidget$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f1988a;

        static {
            int[] iArr = new int[ConstraintAnchor.Type.values().length];
            f1988a = iArr;
            try {
                iArr[ConstraintAnchor.Type.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1988a[ConstraintAnchor.Type.TOP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1988a[ConstraintAnchor.Type.RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1988a[ConstraintAnchor.Type.BOTTOM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1988a[ConstraintAnchor.Type.BASELINE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1988a[ConstraintAnchor.Type.CENTER.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1988a[ConstraintAnchor.Type.CENTER_X.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f1988a[ConstraintAnchor.Type.CENTER_Y.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f1988a[ConstraintAnchor.Type.NONE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    public enum DimensionBehaviour {
        FIXED,
        WRAP_CONTENT,
        MATCH_CONSTRAINT,
        MATCH_PARENT
    }

    public ConstraintWidget() {
        ConstraintAnchor constraintAnchor = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER);
        this.X = constraintAnchor;
        this.Y = new ConstraintAnchor[]{this.Q, this.S, this.R, this.T, this.U, constraintAnchor};
        this.Z = new ArrayList();
        this.a0 = new boolean[2];
        DimensionBehaviour dimensionBehaviour = DimensionBehaviour.FIXED;
        this.b0 = new DimensionBehaviour[]{dimensionBehaviour, dimensionBehaviour};
        this.c0 = null;
        this.d0 = 0;
        this.e0 = 0;
        this.f0 = 0.0f;
        this.g0 = -1;
        this.h0 = 0;
        this.i0 = 0;
        this.j0 = 0;
        this.k0 = 0;
        this.l0 = 0;
        this.m0 = 0;
        this.n0 = 0;
        float f2 = U0;
        this.q0 = f2;
        this.r0 = f2;
        this.t0 = 0;
        this.u0 = 0;
        this.v0 = false;
        this.w0 = null;
        this.x0 = null;
        this.I0 = false;
        this.J0 = 0;
        this.K0 = 0;
        this.N0 = new float[]{-1.0f, -1.0f};
        this.O0 = new ConstraintWidget[]{null, null};
        this.P0 = new ConstraintWidget[]{null, null};
        this.Q0 = null;
        this.R0 = null;
        this.S0 = -1;
        this.T0 = -1;
        d();
    }

    private void A0(StringBuilder sb, String str, float f2, float f3) {
        if (f2 == f3) {
            return;
        }
        sb.append(str);
        sb.append(" :   ");
        sb.append(f2);
        sb.append(",\n");
    }

    private void B0(StringBuilder sb, String str, int i2, int i3) {
        if (i2 == i3) {
            return;
        }
        sb.append(str);
        sb.append(" :   ");
        sb.append(i2);
        sb.append(",\n");
    }

    private void C0(StringBuilder sb, String str, String str2, String str3) {
        if (str3.equals(str2)) {
            return;
        }
        sb.append(str);
        sb.append(" :   ");
        sb.append(str2);
        sb.append(",\n");
    }

    private void D0(StringBuilder sb, String str, float f2, int i2) {
        if (f2 == 0.0f) {
            return;
        }
        sb.append(str);
        sb.append(" :  [");
        sb.append(f2);
        sb.append(",");
        sb.append(i2);
        sb.append("");
        sb.append("],\n");
    }

    private void R(StringBuilder sb, String str, int i2, int i3, int i4, int i5, int i6, int i7, float f2, DimensionBehaviour dimensionBehaviour, float f3) {
        sb.append(str);
        sb.append(" :  {\n");
        C0(sb, "      behavior", dimensionBehaviour.toString(), DimensionBehaviour.FIXED.toString());
        B0(sb, "      size", i2, 0);
        B0(sb, "      min", i3, 0);
        B0(sb, "      max", i4, Api.BaseClientBuilder.API_PRIORITY_OTHER);
        B0(sb, "      matchMin", i6, 0);
        B0(sb, "      matchDef", i7, 0);
        A0(sb, "      matchPercent", f2, 1.0f);
        sb.append("    },\n");
    }

    private void S(StringBuilder sb, String str, ConstraintAnchor constraintAnchor) {
        if (constraintAnchor.f1965f == null) {
            return;
        }
        sb.append("    ");
        sb.append(str);
        sb.append(" : [ '");
        sb.append(constraintAnchor.f1965f);
        sb.append("'");
        if (constraintAnchor.f1967h != Integer.MIN_VALUE || constraintAnchor.f1966g != 0) {
            sb.append(",");
            sb.append(constraintAnchor.f1966g);
            if (constraintAnchor.f1967h != Integer.MIN_VALUE) {
                sb.append(",");
                sb.append(constraintAnchor.f1967h);
                sb.append(",");
            }
        }
        sb.append(" ] ,\n");
    }

    private void d() {
        this.Z.add(this.Q);
        this.Z.add(this.R);
        this.Z.add(this.S);
        this.Z.add(this.T);
        this.Z.add(this.V);
        this.Z.add(this.W);
        this.Z.add(this.X);
        this.Z.add(this.U);
    }

    private boolean h0(int i2) {
        ConstraintAnchor constraintAnchor;
        ConstraintAnchor constraintAnchor2;
        int i3 = i2 * 2;
        ConstraintAnchor[] constraintAnchorArr = this.Y;
        ConstraintAnchor constraintAnchor3 = constraintAnchorArr[i3];
        ConstraintAnchor constraintAnchor4 = constraintAnchor3.f1965f;
        return (constraintAnchor4 == null || constraintAnchor4.f1965f == constraintAnchor3 || (constraintAnchor2 = (constraintAnchor = constraintAnchorArr[i3 + 1]).f1965f) == null || constraintAnchor2.f1965f != constraintAnchor) ? false : true;
    }

    /* JADX WARN: Removed duplicated region for block: B:107:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:156:0x03cf A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:160:0x03dc  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x042e  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0437  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x045d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:192:0x0477  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x04c4  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x04d6 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:240:0x0459  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x041e  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x04fa A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void i(androidx.constraintlayout.core.LinearSystem r33, boolean r34, boolean r35, boolean r36, boolean r37, androidx.constraintlayout.core.SolverVariable r38, androidx.constraintlayout.core.SolverVariable r39, androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour r40, boolean r41, androidx.constraintlayout.core.widgets.ConstraintAnchor r42, androidx.constraintlayout.core.widgets.ConstraintAnchor r43, int r44, int r45, int r46, int r47, float r48, boolean r49, boolean r50, boolean r51, boolean r52, boolean r53, int r54, int r55, int r56, int r57, float r58, boolean r59) {
        /*
            Method dump skipped, instructions count: 1392
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.ConstraintWidget.i(androidx.constraintlayout.core.LinearSystem, boolean, boolean, boolean, boolean, androidx.constraintlayout.core.SolverVariable, androidx.constraintlayout.core.SolverVariable, androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour, boolean, androidx.constraintlayout.core.widgets.ConstraintAnchor, androidx.constraintlayout.core.widgets.ConstraintAnchor, int, int, int, int, float, boolean, boolean, boolean, boolean, boolean, int, int, int, int, float, boolean):void");
    }

    public float A() {
        return this.q0;
    }

    public int B() {
        return this.J0;
    }

    public DimensionBehaviour C() {
        return this.b0[0];
    }

    public int D() {
        ConstraintAnchor constraintAnchor = this.Q;
        int i2 = constraintAnchor != null ? constraintAnchor.f1966g : 0;
        ConstraintAnchor constraintAnchor2 = this.S;
        return constraintAnchor2 != null ? i2 + constraintAnchor2.f1966g : i2;
    }

    public int E() {
        return this.O;
    }

    public void E0(boolean z) {
        this.v0 = z;
    }

    public int F() {
        return this.P;
    }

    public void F0(int i2) {
        this.n0 = i2;
        this.L = i2 > 0;
    }

    public int G(int i2) {
        if (i2 == 0) {
            return Y();
        }
        if (i2 == 1) {
            return z();
        }
        return 0;
    }

    public void G0(Object obj) {
        this.s0 = obj;
    }

    public int H() {
        return this.J[1];
    }

    public void H0(String str) {
        this.w0 = str;
    }

    public int I() {
        return this.J[0];
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:38:0x0086 -> B:31:0x0087). Please report as a decompilation issue!!! */
    public void I0(String str) {
        float f2;
        int i2 = 0;
        if (str == null || str.length() == 0) {
            this.f0 = 0.0f;
            return;
        }
        int length = str.length();
        int indexOf = str.indexOf(44);
        int i3 = -1;
        if (indexOf > 0 && indexOf < length - 1) {
            String substring = str.substring(0, indexOf);
            i3 = substring.equalsIgnoreCase("W") ? 0 : substring.equalsIgnoreCase("H") ? 1 : -1;
            r3 = indexOf + 1;
        }
        int indexOf2 = str.indexOf(58);
        if (indexOf2 < 0 || indexOf2 >= length - 1) {
            String substring2 = str.substring(r3);
            if (substring2.length() > 0) {
                f2 = Float.parseFloat(substring2);
            }
            f2 = i2;
        } else {
            String substring3 = str.substring(r3, indexOf2);
            String substring4 = str.substring(indexOf2 + 1);
            if (substring3.length() > 0 && substring4.length() > 0) {
                float parseFloat = Float.parseFloat(substring3);
                float parseFloat2 = Float.parseFloat(substring4);
                if (parseFloat > 0.0f && parseFloat2 > 0.0f) {
                    f2 = i3 == 1 ? Math.abs(parseFloat2 / parseFloat) : Math.abs(parseFloat / parseFloat2);
                }
            }
            f2 = i2;
        }
        i2 = (f2 > i2 ? 1 : (f2 == i2 ? 0 : -1));
        if (i2 > 0) {
            this.f0 = f2;
            this.g0 = i3;
        }
    }

    public int J() {
        return this.p0;
    }

    public void J0(int i2) {
        if (this.L) {
            int i3 = i2 - this.n0;
            int i4 = this.e0 + i3;
            this.i0 = i3;
            this.R.t(i3);
            this.T.t(i4);
            this.U.t(i2);
            this.f1985q = true;
        }
    }

    public int K() {
        return this.o0;
    }

    public void K0(int i2, int i3) {
        if (this.f1984p) {
            return;
        }
        this.Q.t(i2);
        this.S.t(i3);
        this.h0 = i2;
        this.d0 = i3 - i2;
        this.f1984p = true;
    }

    public ConstraintWidget L(int i2) {
        ConstraintAnchor constraintAnchor;
        ConstraintAnchor constraintAnchor2;
        if (i2 != 0) {
            if (i2 == 1 && (constraintAnchor2 = (constraintAnchor = this.T).f1965f) != null && constraintAnchor2.f1965f == constraintAnchor) {
                return constraintAnchor2.f1963d;
            }
            return null;
        }
        ConstraintAnchor constraintAnchor3 = this.S;
        ConstraintAnchor constraintAnchor4 = constraintAnchor3.f1965f;
        if (constraintAnchor4 == null || constraintAnchor4.f1965f != constraintAnchor3) {
            return null;
        }
        return constraintAnchor4.f1963d;
    }

    public void L0(int i2) {
        this.Q.t(i2);
        this.h0 = i2;
    }

    public ConstraintWidget M() {
        return this.c0;
    }

    public void M0(int i2) {
        this.R.t(i2);
        this.i0 = i2;
    }

    public ConstraintWidget N(int i2) {
        ConstraintAnchor constraintAnchor;
        ConstraintAnchor constraintAnchor2;
        if (i2 != 0) {
            if (i2 == 1 && (constraintAnchor2 = (constraintAnchor = this.R).f1965f) != null && constraintAnchor2.f1965f == constraintAnchor) {
                return constraintAnchor2.f1963d;
            }
            return null;
        }
        ConstraintAnchor constraintAnchor3 = this.Q;
        ConstraintAnchor constraintAnchor4 = constraintAnchor3.f1965f;
        if (constraintAnchor4 == null || constraintAnchor4.f1965f != constraintAnchor3) {
            return null;
        }
        return constraintAnchor4.f1963d;
    }

    public void N0(int i2, int i3) {
        if (this.f1985q) {
            return;
        }
        this.R.t(i2);
        this.T.t(i3);
        this.i0 = i2;
        this.e0 = i3 - i2;
        if (this.L) {
            this.U.t(i2 + this.n0);
        }
        this.f1985q = true;
    }

    public int O() {
        return Z() + this.d0;
    }

    public void O0(int i2, int i3, int i4, int i5) {
        int i6;
        int i7;
        int i8 = i4 - i2;
        int i9 = i5 - i3;
        this.h0 = i2;
        this.i0 = i3;
        if (this.u0 == 8) {
            this.d0 = 0;
            this.e0 = 0;
            return;
        }
        DimensionBehaviour[] dimensionBehaviourArr = this.b0;
        DimensionBehaviour dimensionBehaviour = dimensionBehaviourArr[0];
        DimensionBehaviour dimensionBehaviour2 = DimensionBehaviour.FIXED;
        if (dimensionBehaviour == dimensionBehaviour2 && i8 < (i7 = this.d0)) {
            i8 = i7;
        }
        if (dimensionBehaviourArr[1] == dimensionBehaviour2 && i9 < (i6 = this.e0)) {
            i9 = i6;
        }
        this.d0 = i8;
        this.e0 = i9;
        int i10 = this.p0;
        if (i9 < i10) {
            this.e0 = i10;
        }
        int i11 = this.o0;
        if (i8 < i11) {
            this.d0 = i11;
        }
        int i12 = this.A;
        if (i12 > 0 && dimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT) {
            this.d0 = Math.min(this.d0, i12);
        }
        int i13 = this.D;
        if (i13 > 0 && this.b0[1] == DimensionBehaviour.MATCH_CONSTRAINT) {
            this.e0 = Math.min(this.e0, i13);
        }
        int i14 = this.d0;
        if (i8 != i14) {
            this.f1980l = i14;
        }
        int i15 = this.e0;
        if (i9 != i15) {
            this.f1981m = i15;
        }
    }

    public WidgetRun P(int i2) {
        if (i2 == 0) {
            return this.f1973e;
        }
        if (i2 == 1) {
            return this.f1974f;
        }
        return null;
    }

    public void P0(boolean z) {
        this.L = z;
    }

    public void Q(StringBuilder sb) {
        sb.append("  " + this.f1983o + ":{\n");
        StringBuilder sb2 = new StringBuilder();
        sb2.append("    actualWidth:");
        sb2.append(this.d0);
        sb.append(sb2.toString());
        sb.append("\n");
        sb.append("    actualHeight:" + this.e0);
        sb.append("\n");
        sb.append("    actualLeft:" + this.h0);
        sb.append("\n");
        sb.append("    actualTop:" + this.i0);
        sb.append("\n");
        S(sb, "left", this.Q);
        S(sb, "top", this.R);
        S(sb, "right", this.S);
        S(sb, "bottom", this.T);
        S(sb, "baseline", this.U);
        S(sb, "centerX", this.V);
        S(sb, "centerY", this.W);
        R(sb, "    width", this.d0, this.o0, this.J[0], this.f1980l, this.z, this.w, this.B, this.b0[0], this.N0[0]);
        R(sb, "    height", this.e0, this.p0, this.J[1], this.f1981m, this.C, this.x, this.E, this.b0[1], this.N0[1]);
        D0(sb, "    dimensionRatio", this.f0, this.g0);
        A0(sb, "    horizontalBias", this.q0, U0);
        A0(sb, "    verticalBias", this.r0, U0);
        B0(sb, "    horizontalChainStyle", this.J0, 0);
        B0(sb, "    verticalChainStyle", this.K0, 0);
        sb.append("  }");
    }

    public void Q0(int i2) {
        this.e0 = i2;
        int i3 = this.p0;
        if (i2 < i3) {
            this.e0 = i3;
        }
    }

    public void R0(float f2) {
        this.q0 = f2;
    }

    public void S0(int i2) {
        this.J0 = i2;
    }

    public float T() {
        return this.r0;
    }

    public void T0(int i2, int i3) {
        this.h0 = i2;
        int i4 = i3 - i2;
        this.d0 = i4;
        int i5 = this.o0;
        if (i4 < i5) {
            this.d0 = i5;
        }
    }

    public int U() {
        return this.K0;
    }

    public void U0(DimensionBehaviour dimensionBehaviour) {
        this.b0[0] = dimensionBehaviour;
    }

    public DimensionBehaviour V() {
        return this.b0[1];
    }

    public void V0(int i2, int i3, int i4, float f2) {
        this.w = i2;
        this.z = i3;
        if (i4 == Integer.MAX_VALUE) {
            i4 = 0;
        }
        this.A = i4;
        this.B = f2;
        if (f2 <= 0.0f || f2 >= 1.0f || i2 != 0) {
            return;
        }
        this.w = 2;
    }

    public int W() {
        int i2 = this.Q != null ? this.R.f1966g : 0;
        return this.S != null ? i2 + this.T.f1966g : i2;
    }

    public void W0(float f2) {
        this.N0[0] = f2;
    }

    public int X() {
        return this.u0;
    }

    protected void X0(int i2, boolean z) {
        this.a0[i2] = z;
    }

    public int Y() {
        if (this.u0 == 8) {
            return 0;
        }
        return this.d0;
    }

    public void Y0(boolean z) {
        this.M = z;
    }

    public int Z() {
        ConstraintWidget constraintWidget = this.c0;
        return (constraintWidget == null || !(constraintWidget instanceof ConstraintWidgetContainer)) ? this.h0 : ((ConstraintWidgetContainer) constraintWidget).d1 + this.h0;
    }

    public void Z0(boolean z) {
        this.N = z;
    }

    public int a0() {
        ConstraintWidget constraintWidget = this.c0;
        return (constraintWidget == null || !(constraintWidget instanceof ConstraintWidgetContainer)) ? this.i0 : ((ConstraintWidgetContainer) constraintWidget).e1 + this.i0;
    }

    public void a1(int i2, int i3) {
        this.O = i2;
        this.P = i3;
        d1(false);
    }

    public boolean b0() {
        return this.L;
    }

    public void b1(int i2) {
        this.J[1] = i2;
    }

    public boolean c0(int i2) {
        if (i2 == 0) {
            return (this.Q.f1965f != null ? 1 : 0) + (this.S.f1965f != null ? 1 : 0) < 2;
        }
        return ((this.R.f1965f != null ? 1 : 0) + (this.T.f1965f != null ? 1 : 0)) + (this.U.f1965f != null ? 1 : 0) < 2;
    }

    public void c1(int i2) {
        this.J[0] = i2;
    }

    public boolean d0() {
        int size = this.Z.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (((ConstraintAnchor) this.Z.get(i2)).m()) {
                return true;
            }
        }
        return false;
    }

    public void d1(boolean z) {
        this.f1977i = z;
    }

    public void e(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, HashSet hashSet, int i2, boolean z) {
        if (z) {
            if (!hashSet.contains(this)) {
                return;
            }
            Optimizer.a(constraintWidgetContainer, linearSystem, this);
            hashSet.remove(this);
            g(linearSystem, constraintWidgetContainer.a2(64));
        }
        if (i2 == 0) {
            HashSet d2 = this.Q.d();
            if (d2 != null) {
                Iterator it = d2.iterator();
                while (it.hasNext()) {
                    ((ConstraintAnchor) it.next()).f1963d.e(constraintWidgetContainer, linearSystem, hashSet, i2, true);
                }
            }
            HashSet d3 = this.S.d();
            if (d3 != null) {
                Iterator it2 = d3.iterator();
                while (it2.hasNext()) {
                    ((ConstraintAnchor) it2.next()).f1963d.e(constraintWidgetContainer, linearSystem, hashSet, i2, true);
                }
                return;
            }
            return;
        }
        HashSet d4 = this.R.d();
        if (d4 != null) {
            Iterator it3 = d4.iterator();
            while (it3.hasNext()) {
                ((ConstraintAnchor) it3.next()).f1963d.e(constraintWidgetContainer, linearSystem, hashSet, i2, true);
            }
        }
        HashSet d5 = this.T.d();
        if (d5 != null) {
            Iterator it4 = d5.iterator();
            while (it4.hasNext()) {
                ((ConstraintAnchor) it4.next()).f1963d.e(constraintWidgetContainer, linearSystem, hashSet, i2, true);
            }
        }
        HashSet d6 = this.U.d();
        if (d6 != null) {
            Iterator it5 = d6.iterator();
            while (it5.hasNext()) {
                ((ConstraintAnchor) it5.next()).f1963d.e(constraintWidgetContainer, linearSystem, hashSet, i2, true);
            }
        }
    }

    public boolean e0() {
        return (this.f1980l == -1 && this.f1981m == -1) ? false : true;
    }

    public void e1(int i2) {
        if (i2 < 0) {
            this.p0 = 0;
        } else {
            this.p0 = i2;
        }
    }

    boolean f() {
        return (this instanceof VirtualLayout) || (this instanceof Guideline);
    }

    public boolean f0(int i2, int i3) {
        ConstraintAnchor constraintAnchor;
        ConstraintAnchor constraintAnchor2;
        if (i2 == 0) {
            ConstraintAnchor constraintAnchor3 = this.Q.f1965f;
            return constraintAnchor3 != null && constraintAnchor3.n() && (constraintAnchor2 = this.S.f1965f) != null && constraintAnchor2.n() && (this.S.f1965f.e() - this.S.f()) - (this.Q.f1965f.e() + this.Q.f()) >= i3;
        }
        ConstraintAnchor constraintAnchor4 = this.R.f1965f;
        return constraintAnchor4 != null && constraintAnchor4.n() && (constraintAnchor = this.T.f1965f) != null && constraintAnchor.n() && (this.T.f1965f.e() - this.T.f()) - (this.R.f1965f.e() + this.R.f()) >= i3;
        return false;
    }

    public void f1(int i2) {
        if (i2 < 0) {
            this.o0 = 0;
        } else {
            this.o0 = i2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:100:0x021b  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0223  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x022e  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x023c  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x02db  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x02ea  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x02ff  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0308  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0324  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0346  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x044d  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x04b6 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:196:0x04cd  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x04d3  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x04e1  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x0553  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x059f  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x05cd  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x05fa  */
    /* JADX WARN: Removed duplicated region for block: B:226:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:227:0x05ef  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x0556  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x04f9  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x0520  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x04e9  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x04db  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x0390  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x03ac  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x03ba  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x03e7  */
    /* JADX WARN: Removed duplicated region for block: B:258:0x03ea  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x03c3  */
    /* JADX WARN: Removed duplicated region for block: B:260:0x03b4  */
    /* JADX WARN: Removed duplicated region for block: B:262:0x030b  */
    /* JADX WARN: Removed duplicated region for block: B:266:0x02e5  */
    /* JADX WARN: Removed duplicated region for block: B:286:0x023e  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x0232  */
    /* JADX WARN: Removed duplicated region for block: B:290:0x0210  */
    /* JADX WARN: Removed duplicated region for block: B:292:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:302:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0128  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0195  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x019f  */
    /* JADX WARN: Type inference failed for: r11v15 */
    /* JADX WARN: Type inference failed for: r11v2 */
    /* JADX WARN: Type inference failed for: r11v3 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void g(androidx.constraintlayout.core.LinearSystem r54, boolean r55) {
        /*
            Method dump skipped, instructions count: 1547
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.ConstraintWidget.g(androidx.constraintlayout.core.LinearSystem, boolean):void");
    }

    public void g0(ConstraintAnchor.Type type, ConstraintWidget constraintWidget, ConstraintAnchor.Type type2, int i2, int i3) {
        q(type).b(constraintWidget.q(type2), i2, i3, true);
    }

    public void g1(int i2, int i3) {
        this.h0 = i2;
        this.i0 = i3;
    }

    public boolean h() {
        return this.u0 != 8;
    }

    public void h1(ConstraintWidget constraintWidget) {
        this.c0 = constraintWidget;
    }

    public boolean i0() {
        return this.f1986r;
    }

    public void i1(float f2) {
        this.r0 = f2;
    }

    public void j(ConstraintAnchor.Type type, ConstraintWidget constraintWidget, ConstraintAnchor.Type type2) {
        k(type, constraintWidget, type2, 0);
    }

    public boolean j0(int i2) {
        return this.a0[i2];
    }

    public void j1(int i2) {
        this.K0 = i2;
    }

    public void k(ConstraintAnchor.Type type, ConstraintWidget constraintWidget, ConstraintAnchor.Type type2, int i2) {
        ConstraintAnchor.Type type3;
        ConstraintAnchor.Type type4;
        boolean z;
        ConstraintAnchor.Type type5 = ConstraintAnchor.Type.CENTER;
        if (type == type5) {
            if (type2 != type5) {
                ConstraintAnchor.Type type6 = ConstraintAnchor.Type.LEFT;
                if (type2 == type6 || type2 == ConstraintAnchor.Type.RIGHT) {
                    k(type6, constraintWidget, type2, 0);
                    k(ConstraintAnchor.Type.RIGHT, constraintWidget, type2, 0);
                    q(type5).a(constraintWidget.q(type2), 0);
                    return;
                }
                ConstraintAnchor.Type type7 = ConstraintAnchor.Type.TOP;
                if (type2 == type7 || type2 == ConstraintAnchor.Type.BOTTOM) {
                    k(type7, constraintWidget, type2, 0);
                    k(ConstraintAnchor.Type.BOTTOM, constraintWidget, type2, 0);
                    q(type5).a(constraintWidget.q(type2), 0);
                    return;
                }
                return;
            }
            ConstraintAnchor.Type type8 = ConstraintAnchor.Type.LEFT;
            ConstraintAnchor q2 = q(type8);
            ConstraintAnchor.Type type9 = ConstraintAnchor.Type.RIGHT;
            ConstraintAnchor q3 = q(type9);
            ConstraintAnchor.Type type10 = ConstraintAnchor.Type.TOP;
            ConstraintAnchor q4 = q(type10);
            ConstraintAnchor.Type type11 = ConstraintAnchor.Type.BOTTOM;
            ConstraintAnchor q5 = q(type11);
            boolean z2 = true;
            if ((q2 == null || !q2.o()) && (q3 == null || !q3.o())) {
                k(type8, constraintWidget, type8, 0);
                k(type9, constraintWidget, type9, 0);
                z = true;
            } else {
                z = false;
            }
            if ((q4 == null || !q4.o()) && (q5 == null || !q5.o())) {
                k(type10, constraintWidget, type10, 0);
                k(type11, constraintWidget, type11, 0);
            } else {
                z2 = false;
            }
            if (z && z2) {
                q(type5).a(constraintWidget.q(type5), 0);
                return;
            }
            if (z) {
                ConstraintAnchor.Type type12 = ConstraintAnchor.Type.CENTER_X;
                q(type12).a(constraintWidget.q(type12), 0);
                return;
            } else {
                if (z2) {
                    ConstraintAnchor.Type type13 = ConstraintAnchor.Type.CENTER_Y;
                    q(type13).a(constraintWidget.q(type13), 0);
                    return;
                }
                return;
            }
        }
        ConstraintAnchor.Type type14 = ConstraintAnchor.Type.CENTER_X;
        if (type == type14 && (type2 == (type4 = ConstraintAnchor.Type.LEFT) || type2 == ConstraintAnchor.Type.RIGHT)) {
            ConstraintAnchor q6 = q(type4);
            ConstraintAnchor q7 = constraintWidget.q(type2);
            ConstraintAnchor q8 = q(ConstraintAnchor.Type.RIGHT);
            q6.a(q7, 0);
            q8.a(q7, 0);
            q(type14).a(q7, 0);
            return;
        }
        ConstraintAnchor.Type type15 = ConstraintAnchor.Type.CENTER_Y;
        if (type == type15 && (type2 == (type3 = ConstraintAnchor.Type.TOP) || type2 == ConstraintAnchor.Type.BOTTOM)) {
            ConstraintAnchor q9 = constraintWidget.q(type2);
            q(type3).a(q9, 0);
            q(ConstraintAnchor.Type.BOTTOM).a(q9, 0);
            q(type15).a(q9, 0);
            return;
        }
        if (type == type14 && type2 == type14) {
            ConstraintAnchor.Type type16 = ConstraintAnchor.Type.LEFT;
            q(type16).a(constraintWidget.q(type16), 0);
            ConstraintAnchor.Type type17 = ConstraintAnchor.Type.RIGHT;
            q(type17).a(constraintWidget.q(type17), 0);
            q(type14).a(constraintWidget.q(type2), 0);
            return;
        }
        if (type == type15 && type2 == type15) {
            ConstraintAnchor.Type type18 = ConstraintAnchor.Type.TOP;
            q(type18).a(constraintWidget.q(type18), 0);
            ConstraintAnchor.Type type19 = ConstraintAnchor.Type.BOTTOM;
            q(type19).a(constraintWidget.q(type19), 0);
            q(type15).a(constraintWidget.q(type2), 0);
            return;
        }
        ConstraintAnchor q10 = q(type);
        ConstraintAnchor q11 = constraintWidget.q(type2);
        if (q10.p(q11)) {
            ConstraintAnchor.Type type20 = ConstraintAnchor.Type.BASELINE;
            if (type == type20) {
                ConstraintAnchor q12 = q(ConstraintAnchor.Type.TOP);
                ConstraintAnchor q13 = q(ConstraintAnchor.Type.BOTTOM);
                if (q12 != null) {
                    q12.q();
                }
                if (q13 != null) {
                    q13.q();
                }
            } else if (type == ConstraintAnchor.Type.TOP || type == ConstraintAnchor.Type.BOTTOM) {
                ConstraintAnchor q14 = q(type20);
                if (q14 != null) {
                    q14.q();
                }
                ConstraintAnchor q15 = q(type5);
                if (q15.j() != q11) {
                    q15.q();
                }
                ConstraintAnchor g2 = q(type).g();
                ConstraintAnchor q16 = q(type15);
                if (q16.o()) {
                    g2.q();
                    q16.q();
                }
            } else if (type == ConstraintAnchor.Type.LEFT || type == ConstraintAnchor.Type.RIGHT) {
                ConstraintAnchor q17 = q(type5);
                if (q17.j() != q11) {
                    q17.q();
                }
                ConstraintAnchor g3 = q(type).g();
                ConstraintAnchor q18 = q(type14);
                if (q18.o()) {
                    g3.q();
                    q18.q();
                }
            }
            q10.a(q11, i2);
        }
    }

    public boolean k0() {
        ConstraintAnchor constraintAnchor = this.Q;
        ConstraintAnchor constraintAnchor2 = constraintAnchor.f1965f;
        if (constraintAnchor2 != null && constraintAnchor2.f1965f == constraintAnchor) {
            return true;
        }
        ConstraintAnchor constraintAnchor3 = this.S;
        ConstraintAnchor constraintAnchor4 = constraintAnchor3.f1965f;
        return constraintAnchor4 != null && constraintAnchor4.f1965f == constraintAnchor3;
    }

    public void k1(int i2, int i3) {
        this.i0 = i2;
        int i4 = i3 - i2;
        this.e0 = i4;
        int i5 = this.p0;
        if (i4 < i5) {
            this.e0 = i5;
        }
    }

    public void l(ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i2) {
        if (constraintAnchor.h() == this) {
            k(constraintAnchor.k(), constraintAnchor2.h(), constraintAnchor2.k(), i2);
        }
    }

    public boolean l0() {
        return this.M;
    }

    public void l1(DimensionBehaviour dimensionBehaviour) {
        this.b0[1] = dimensionBehaviour;
    }

    public void m(ConstraintWidget constraintWidget, float f2, int i2) {
        ConstraintAnchor.Type type = ConstraintAnchor.Type.CENTER;
        g0(type, constraintWidget, type, i2, 0);
        this.K = f2;
    }

    public boolean m0() {
        ConstraintAnchor constraintAnchor = this.R;
        ConstraintAnchor constraintAnchor2 = constraintAnchor.f1965f;
        if (constraintAnchor2 != null && constraintAnchor2.f1965f == constraintAnchor) {
            return true;
        }
        ConstraintAnchor constraintAnchor3 = this.T;
        ConstraintAnchor constraintAnchor4 = constraintAnchor3.f1965f;
        return constraintAnchor4 != null && constraintAnchor4.f1965f == constraintAnchor3;
    }

    public void m1(int i2, int i3, int i4, float f2) {
        this.x = i2;
        this.C = i3;
        if (i4 == Integer.MAX_VALUE) {
            i4 = 0;
        }
        this.D = i4;
        this.E = f2;
        if (f2 <= 0.0f || f2 >= 1.0f || i2 != 0) {
            return;
        }
        this.x = 2;
    }

    public void n(ConstraintWidget constraintWidget, HashMap hashMap) {
        this.t = constraintWidget.t;
        this.u = constraintWidget.u;
        this.w = constraintWidget.w;
        this.x = constraintWidget.x;
        int[] iArr = this.y;
        int[] iArr2 = constraintWidget.y;
        iArr[0] = iArr2[0];
        iArr[1] = iArr2[1];
        this.z = constraintWidget.z;
        this.A = constraintWidget.A;
        this.C = constraintWidget.C;
        this.D = constraintWidget.D;
        this.E = constraintWidget.E;
        this.F = constraintWidget.F;
        this.G = constraintWidget.G;
        this.H = constraintWidget.H;
        this.I = constraintWidget.I;
        int[] iArr3 = constraintWidget.J;
        this.J = Arrays.copyOf(iArr3, iArr3.length);
        this.K = constraintWidget.K;
        this.L = constraintWidget.L;
        this.M = constraintWidget.M;
        this.Q.q();
        this.R.q();
        this.S.q();
        this.T.q();
        this.U.q();
        this.V.q();
        this.W.q();
        this.X.q();
        this.b0 = (DimensionBehaviour[]) Arrays.copyOf(this.b0, 2);
        this.c0 = this.c0 == null ? null : (ConstraintWidget) hashMap.get(constraintWidget.c0);
        this.d0 = constraintWidget.d0;
        this.e0 = constraintWidget.e0;
        this.f0 = constraintWidget.f0;
        this.g0 = constraintWidget.g0;
        this.h0 = constraintWidget.h0;
        this.i0 = constraintWidget.i0;
        this.j0 = constraintWidget.j0;
        this.k0 = constraintWidget.k0;
        this.l0 = constraintWidget.l0;
        this.m0 = constraintWidget.m0;
        this.n0 = constraintWidget.n0;
        this.o0 = constraintWidget.o0;
        this.p0 = constraintWidget.p0;
        this.q0 = constraintWidget.q0;
        this.r0 = constraintWidget.r0;
        this.s0 = constraintWidget.s0;
        this.t0 = constraintWidget.t0;
        this.u0 = constraintWidget.u0;
        this.v0 = constraintWidget.v0;
        this.w0 = constraintWidget.w0;
        this.x0 = constraintWidget.x0;
        this.y0 = constraintWidget.y0;
        this.z0 = constraintWidget.z0;
        this.A0 = constraintWidget.A0;
        this.B0 = constraintWidget.B0;
        this.C0 = constraintWidget.C0;
        this.D0 = constraintWidget.D0;
        this.E0 = constraintWidget.E0;
        this.F0 = constraintWidget.F0;
        this.G0 = constraintWidget.G0;
        this.H0 = constraintWidget.H0;
        this.J0 = constraintWidget.J0;
        this.K0 = constraintWidget.K0;
        this.L0 = constraintWidget.L0;
        this.M0 = constraintWidget.M0;
        float[] fArr = this.N0;
        float[] fArr2 = constraintWidget.N0;
        fArr[0] = fArr2[0];
        fArr[1] = fArr2[1];
        ConstraintWidget[] constraintWidgetArr = this.O0;
        ConstraintWidget[] constraintWidgetArr2 = constraintWidget.O0;
        constraintWidgetArr[0] = constraintWidgetArr2[0];
        constraintWidgetArr[1] = constraintWidgetArr2[1];
        ConstraintWidget[] constraintWidgetArr3 = this.P0;
        ConstraintWidget[] constraintWidgetArr4 = constraintWidget.P0;
        constraintWidgetArr3[0] = constraintWidgetArr4[0];
        constraintWidgetArr3[1] = constraintWidgetArr4[1];
        ConstraintWidget constraintWidget2 = constraintWidget.Q0;
        this.Q0 = constraintWidget2 == null ? null : (ConstraintWidget) hashMap.get(constraintWidget2);
        ConstraintWidget constraintWidget3 = constraintWidget.R0;
        this.R0 = constraintWidget3 != null ? (ConstraintWidget) hashMap.get(constraintWidget3) : null;
    }

    public boolean n0() {
        return this.N;
    }

    public void n1(float f2) {
        this.N0[1] = f2;
    }

    public void o(LinearSystem linearSystem) {
        linearSystem.q(this.Q);
        linearSystem.q(this.R);
        linearSystem.q(this.S);
        linearSystem.q(this.T);
        if (this.n0 > 0) {
            linearSystem.q(this.U);
        }
    }

    public boolean o0() {
        return this.f1977i && this.u0 != 8;
    }

    public void o1(int i2) {
        this.u0 = i2;
    }

    public void p() {
        if (this.f1973e == null) {
            this.f1973e = new HorizontalWidgetRun(this);
        }
        if (this.f1974f == null) {
            this.f1974f = new VerticalWidgetRun(this);
        }
    }

    public boolean p0() {
        return this.f1984p || (this.Q.n() && this.S.n());
    }

    public void p1(int i2) {
        this.d0 = i2;
        int i3 = this.o0;
        if (i2 < i3) {
            this.d0 = i3;
        }
    }

    public ConstraintAnchor q(ConstraintAnchor.Type type) {
        switch (AnonymousClass1.f1988a[type.ordinal()]) {
            case 1:
                return this.Q;
            case 2:
                return this.R;
            case 3:
                return this.S;
            case 4:
                return this.T;
            case 5:
                return this.U;
            case 6:
                return this.X;
            case 7:
                return this.V;
            case 8:
                return this.W;
            case 9:
                return null;
            default:
                throw new AssertionError(type.name());
        }
    }

    public boolean q0() {
        return this.f1985q || (this.R.n() && this.T.n());
    }

    public void q1(int i2) {
        if (i2 < 0 || i2 > 3) {
            return;
        }
        this.v = i2;
    }

    public int r() {
        return this.n0;
    }

    public boolean r0() {
        return this.f1987s;
    }

    public void r1(int i2) {
        this.h0 = i2;
    }

    public float s(int i2) {
        if (i2 == 0) {
            return this.q0;
        }
        if (i2 == 1) {
            return this.r0;
        }
        return -1.0f;
    }

    public void s0() {
        this.f1986r = true;
    }

    public void s1(int i2) {
        this.i0 = i2;
    }

    public int t() {
        return a0() + this.e0;
    }

    public void t0() {
        this.f1987s = true;
    }

    public void t1(boolean z, boolean z2, boolean z3, boolean z4) {
        if (this.H == -1) {
            if (z3 && !z4) {
                this.H = 0;
            } else if (!z3 && z4) {
                this.H = 1;
                if (this.g0 == -1) {
                    this.I = 1.0f / this.I;
                }
            }
        }
        if (this.H == 0 && (!this.R.o() || !this.T.o())) {
            this.H = 1;
        } else if (this.H == 1 && (!this.Q.o() || !this.S.o())) {
            this.H = 0;
        }
        if (this.H == -1 && (!this.R.o() || !this.T.o() || !this.Q.o() || !this.S.o())) {
            if (this.R.o() && this.T.o()) {
                this.H = 0;
            } else if (this.Q.o() && this.S.o()) {
                this.I = 1.0f / this.I;
                this.H = 1;
            }
        }
        if (this.H == -1) {
            int i2 = this.z;
            if (i2 > 0 && this.C == 0) {
                this.H = 0;
            } else {
                if (i2 != 0 || this.C <= 0) {
                    return;
                }
                this.I = 1.0f / this.I;
                this.H = 1;
            }
        }
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        String str2 = "";
        if (this.x0 != null) {
            str = "type: " + this.x0 + " ";
        } else {
            str = "";
        }
        sb.append(str);
        if (this.w0 != null) {
            str2 = "id: " + this.w0 + " ";
        }
        sb.append(str2);
        sb.append("(");
        sb.append(this.h0);
        sb.append(", ");
        sb.append(this.i0);
        sb.append(") - (");
        sb.append(this.d0);
        sb.append(" x ");
        sb.append(this.e0);
        sb.append(")");
        return sb.toString();
    }

    public Object u() {
        return this.s0;
    }

    public boolean u0() {
        DimensionBehaviour[] dimensionBehaviourArr = this.b0;
        DimensionBehaviour dimensionBehaviour = dimensionBehaviourArr[0];
        DimensionBehaviour dimensionBehaviour2 = DimensionBehaviour.MATCH_CONSTRAINT;
        return dimensionBehaviour == dimensionBehaviour2 && dimensionBehaviourArr[1] == dimensionBehaviour2;
    }

    public void u1(boolean z, boolean z2) {
        int i2;
        int i3;
        boolean k2 = z & this.f1973e.k();
        boolean k3 = z2 & this.f1974f.k();
        HorizontalWidgetRun horizontalWidgetRun = this.f1973e;
        int i4 = horizontalWidgetRun.f2086h.f2042g;
        VerticalWidgetRun verticalWidgetRun = this.f1974f;
        int i5 = verticalWidgetRun.f2086h.f2042g;
        int i6 = horizontalWidgetRun.f2087i.f2042g;
        int i7 = verticalWidgetRun.f2087i.f2042g;
        int i8 = i7 - i5;
        if (i6 - i4 < 0 || i8 < 0 || i4 == Integer.MIN_VALUE || i4 == Integer.MAX_VALUE || i5 == Integer.MIN_VALUE || i5 == Integer.MAX_VALUE || i6 == Integer.MIN_VALUE || i6 == Integer.MAX_VALUE || i7 == Integer.MIN_VALUE || i7 == Integer.MAX_VALUE) {
            i6 = 0;
            i4 = 0;
            i7 = 0;
            i5 = 0;
        }
        int i9 = i6 - i4;
        int i10 = i7 - i5;
        if (k2) {
            this.h0 = i4;
        }
        if (k3) {
            this.i0 = i5;
        }
        if (this.u0 == 8) {
            this.d0 = 0;
            this.e0 = 0;
            return;
        }
        if (k2) {
            if (this.b0[0] == DimensionBehaviour.FIXED && i9 < (i3 = this.d0)) {
                i9 = i3;
            }
            this.d0 = i9;
            int i11 = this.o0;
            if (i9 < i11) {
                this.d0 = i11;
            }
        }
        if (k3) {
            if (this.b0[1] == DimensionBehaviour.FIXED && i10 < (i2 = this.e0)) {
                i10 = i2;
            }
            this.e0 = i10;
            int i12 = this.p0;
            if (i10 < i12) {
                this.e0 = i12;
            }
        }
    }

    public String v() {
        return this.w0;
    }

    public void v0() {
        this.Q.q();
        this.R.q();
        this.S.q();
        this.T.q();
        this.U.q();
        this.V.q();
        this.W.q();
        this.X.q();
        this.c0 = null;
        this.K = Float.NaN;
        this.d0 = 0;
        this.e0 = 0;
        this.f0 = 0.0f;
        this.g0 = -1;
        this.h0 = 0;
        this.i0 = 0;
        this.l0 = 0;
        this.m0 = 0;
        this.n0 = 0;
        this.o0 = 0;
        this.p0 = 0;
        float f2 = U0;
        this.q0 = f2;
        this.r0 = f2;
        DimensionBehaviour[] dimensionBehaviourArr = this.b0;
        DimensionBehaviour dimensionBehaviour = DimensionBehaviour.FIXED;
        dimensionBehaviourArr[0] = dimensionBehaviour;
        dimensionBehaviourArr[1] = dimensionBehaviour;
        this.s0 = null;
        this.t0 = 0;
        this.u0 = 0;
        this.x0 = null;
        this.G0 = false;
        this.H0 = false;
        this.J0 = 0;
        this.K0 = 0;
        this.L0 = false;
        this.M0 = false;
        float[] fArr = this.N0;
        fArr[0] = -1.0f;
        fArr[1] = -1.0f;
        this.t = -1;
        this.u = -1;
        int[] iArr = this.J;
        iArr[0] = Integer.MAX_VALUE;
        iArr[1] = Integer.MAX_VALUE;
        this.w = 0;
        this.x = 0;
        this.B = 1.0f;
        this.E = 1.0f;
        this.A = Api.BaseClientBuilder.API_PRIORITY_OTHER;
        this.D = Api.BaseClientBuilder.API_PRIORITY_OTHER;
        this.z = 0;
        this.C = 0;
        this.f1976h = false;
        this.H = -1;
        this.I = 1.0f;
        this.I0 = false;
        boolean[] zArr = this.f1975g;
        zArr[0] = true;
        zArr[1] = true;
        this.N = false;
        boolean[] zArr2 = this.a0;
        zArr2[0] = false;
        zArr2[1] = false;
        this.f1977i = true;
        int[] iArr2 = this.y;
        iArr2[0] = 0;
        iArr2[1] = 0;
        this.f1980l = -1;
        this.f1981m = -1;
    }

    public void v1(LinearSystem linearSystem, boolean z) {
        VerticalWidgetRun verticalWidgetRun;
        HorizontalWidgetRun horizontalWidgetRun;
        int A = linearSystem.A(this.Q);
        int A2 = linearSystem.A(this.R);
        int A3 = linearSystem.A(this.S);
        int A4 = linearSystem.A(this.T);
        if (z && (horizontalWidgetRun = this.f1973e) != null) {
            DependencyNode dependencyNode = horizontalWidgetRun.f2086h;
            if (dependencyNode.f2045j) {
                DependencyNode dependencyNode2 = horizontalWidgetRun.f2087i;
                if (dependencyNode2.f2045j) {
                    A = dependencyNode.f2042g;
                    A3 = dependencyNode2.f2042g;
                }
            }
        }
        if (z && (verticalWidgetRun = this.f1974f) != null) {
            DependencyNode dependencyNode3 = verticalWidgetRun.f2086h;
            if (dependencyNode3.f2045j) {
                DependencyNode dependencyNode4 = verticalWidgetRun.f2087i;
                if (dependencyNode4.f2045j) {
                    A2 = dependencyNode3.f2042g;
                    A4 = dependencyNode4.f2042g;
                }
            }
        }
        int i2 = A4 - A2;
        if (A3 - A < 0 || i2 < 0 || A == Integer.MIN_VALUE || A == Integer.MAX_VALUE || A2 == Integer.MIN_VALUE || A2 == Integer.MAX_VALUE || A3 == Integer.MIN_VALUE || A3 == Integer.MAX_VALUE || A4 == Integer.MIN_VALUE || A4 == Integer.MAX_VALUE) {
            A = 0;
            A4 = 0;
            A2 = 0;
            A3 = 0;
        }
        O0(A, A2, A3, A4);
    }

    public DimensionBehaviour w(int i2) {
        if (i2 == 0) {
            return C();
        }
        if (i2 == 1) {
            return V();
        }
        return null;
    }

    public void w0() {
        x0();
        i1(U0);
        R0(U0);
    }

    public float x() {
        return this.f0;
    }

    public void x0() {
        ConstraintWidget M = M();
        if (M != null && (M instanceof ConstraintWidgetContainer) && ((ConstraintWidgetContainer) M()).S1()) {
            return;
        }
        int size = this.Z.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((ConstraintAnchor) this.Z.get(i2)).q();
        }
    }

    public int y() {
        return this.g0;
    }

    public void y0() {
        this.f1984p = false;
        this.f1985q = false;
        this.f1986r = false;
        this.f1987s = false;
        int size = this.Z.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((ConstraintAnchor) this.Z.get(i2)).r();
        }
    }

    public int z() {
        if (this.u0 == 8) {
            return 0;
        }
        return this.e0;
    }

    public void z0(Cache cache) {
        this.Q.s(cache);
        this.R.s(cache);
        this.S.s(cache);
        this.T.s(cache);
        this.U.s(cache);
        this.X.s(cache);
        this.V.s(cache);
        this.W.s(cache);
    }
}
