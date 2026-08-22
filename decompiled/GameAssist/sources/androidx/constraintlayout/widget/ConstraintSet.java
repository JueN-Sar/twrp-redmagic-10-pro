package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.HelperWidget;
import androidx.constraintlayout.motion.widget.Debug;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;
import androidx.constraintlayout.widget.R;
import com.google.mlkit.common.MlKitException;
import com.zte.distbus.basetransfer.Status;
import com.zte.mifavor.widget.VolumeView;
import com.zte.shared.wrapper.VirtualHandleWrapper;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
public class ConstraintSet {

    /* renamed from: i, reason: collision with root package name */
    private static final int[] f2476i = {0, 4, 8};

    /* renamed from: j, reason: collision with root package name */
    private static SparseIntArray f2477j = new SparseIntArray();

    /* renamed from: k, reason: collision with root package name */
    private static SparseIntArray f2478k = new SparseIntArray();

    /* renamed from: a, reason: collision with root package name */
    private boolean f2479a;

    /* renamed from: b, reason: collision with root package name */
    public String f2480b;

    /* renamed from: c, reason: collision with root package name */
    public String f2481c = "";

    /* renamed from: d, reason: collision with root package name */
    private String[] f2482d = new String[0];

    /* renamed from: e, reason: collision with root package name */
    public int f2483e = 0;

    /* renamed from: f, reason: collision with root package name */
    private HashMap f2484f = new HashMap();

    /* renamed from: g, reason: collision with root package name */
    private boolean f2485g = true;

    /* renamed from: h, reason: collision with root package name */
    private HashMap f2486h = new HashMap();

    public static class Constraint {

        /* renamed from: a, reason: collision with root package name */
        int f2487a;

        /* renamed from: b, reason: collision with root package name */
        String f2488b;

        /* renamed from: c, reason: collision with root package name */
        public final PropertySet f2489c = new PropertySet();

        /* renamed from: d, reason: collision with root package name */
        public final Motion f2490d = new Motion();

        /* renamed from: e, reason: collision with root package name */
        public final Layout f2491e = new Layout();

        /* renamed from: f, reason: collision with root package name */
        public final Transform f2492f = new Transform();

        /* renamed from: g, reason: collision with root package name */
        public HashMap f2493g = new HashMap();

        /* renamed from: h, reason: collision with root package name */
        Delta f2494h;

        static class Delta {

            /* renamed from: a, reason: collision with root package name */
            int[] f2495a = new int[10];

            /* renamed from: b, reason: collision with root package name */
            int[] f2496b = new int[10];

            /* renamed from: c, reason: collision with root package name */
            int f2497c = 0;

            /* renamed from: d, reason: collision with root package name */
            int[] f2498d = new int[10];

            /* renamed from: e, reason: collision with root package name */
            float[] f2499e = new float[10];

            /* renamed from: f, reason: collision with root package name */
            int f2500f = 0;

            /* renamed from: g, reason: collision with root package name */
            int[] f2501g = new int[5];

            /* renamed from: h, reason: collision with root package name */
            String[] f2502h = new String[5];

            /* renamed from: i, reason: collision with root package name */
            int f2503i = 0;

            /* renamed from: j, reason: collision with root package name */
            int[] f2504j = new int[4];

            /* renamed from: k, reason: collision with root package name */
            boolean[] f2505k = new boolean[4];

            /* renamed from: l, reason: collision with root package name */
            int f2506l = 0;

            Delta() {
            }

            void a(int i2, float f2) {
                int i3 = this.f2500f;
                int[] iArr = this.f2498d;
                if (i3 >= iArr.length) {
                    this.f2498d = Arrays.copyOf(iArr, iArr.length * 2);
                    float[] fArr = this.f2499e;
                    this.f2499e = Arrays.copyOf(fArr, fArr.length * 2);
                }
                int[] iArr2 = this.f2498d;
                int i4 = this.f2500f;
                iArr2[i4] = i2;
                float[] fArr2 = this.f2499e;
                this.f2500f = i4 + 1;
                fArr2[i4] = f2;
            }

            void b(int i2, int i3) {
                int i4 = this.f2497c;
                int[] iArr = this.f2495a;
                if (i4 >= iArr.length) {
                    this.f2495a = Arrays.copyOf(iArr, iArr.length * 2);
                    int[] iArr2 = this.f2496b;
                    this.f2496b = Arrays.copyOf(iArr2, iArr2.length * 2);
                }
                int[] iArr3 = this.f2495a;
                int i5 = this.f2497c;
                iArr3[i5] = i2;
                int[] iArr4 = this.f2496b;
                this.f2497c = i5 + 1;
                iArr4[i5] = i3;
            }

            void c(int i2, String str) {
                int i3 = this.f2503i;
                int[] iArr = this.f2501g;
                if (i3 >= iArr.length) {
                    this.f2501g = Arrays.copyOf(iArr, iArr.length * 2);
                    String[] strArr = this.f2502h;
                    this.f2502h = (String[]) Arrays.copyOf(strArr, strArr.length * 2);
                }
                int[] iArr2 = this.f2501g;
                int i4 = this.f2503i;
                iArr2[i4] = i2;
                String[] strArr2 = this.f2502h;
                this.f2503i = i4 + 1;
                strArr2[i4] = str;
            }

            void d(int i2, boolean z) {
                int i3 = this.f2506l;
                int[] iArr = this.f2504j;
                if (i3 >= iArr.length) {
                    this.f2504j = Arrays.copyOf(iArr, iArr.length * 2);
                    boolean[] zArr = this.f2505k;
                    this.f2505k = Arrays.copyOf(zArr, zArr.length * 2);
                }
                int[] iArr2 = this.f2504j;
                int i4 = this.f2506l;
                iArr2[i4] = i2;
                boolean[] zArr2 = this.f2505k;
                this.f2506l = i4 + 1;
                zArr2[i4] = z;
            }

            void e(Constraint constraint) {
                for (int i2 = 0; i2 < this.f2497c; i2++) {
                    ConstraintSet.N(constraint, this.f2495a[i2], this.f2496b[i2]);
                }
                for (int i3 = 0; i3 < this.f2500f; i3++) {
                    ConstraintSet.M(constraint, this.f2498d[i3], this.f2499e[i3]);
                }
                for (int i4 = 0; i4 < this.f2503i; i4++) {
                    ConstraintSet.O(constraint, this.f2501g[i4], this.f2502h[i4]);
                }
                for (int i5 = 0; i5 < this.f2506l; i5++) {
                    ConstraintSet.P(constraint, this.f2504j[i5], this.f2505k[i5]);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void g(int i2, ConstraintLayout.LayoutParams layoutParams) {
            this.f2487a = i2;
            Layout layout = this.f2491e;
            layout.f2516j = layoutParams.f2432e;
            layout.f2517k = layoutParams.f2433f;
            layout.f2518l = layoutParams.f2434g;
            layout.f2519m = layoutParams.f2435h;
            layout.f2520n = layoutParams.f2436i;
            layout.f2521o = layoutParams.f2437j;
            layout.f2522p = layoutParams.f2438k;
            layout.f2523q = layoutParams.f2439l;
            layout.f2524r = layoutParams.f2440m;
            layout.f2525s = layoutParams.f2441n;
            layout.t = layoutParams.f2442o;
            layout.u = layoutParams.f2446s;
            layout.v = layoutParams.t;
            layout.w = layoutParams.u;
            layout.x = layoutParams.v;
            layout.y = layoutParams.G;
            layout.z = layoutParams.H;
            layout.A = layoutParams.I;
            layout.B = layoutParams.f2443p;
            layout.C = layoutParams.f2444q;
            layout.D = layoutParams.f2445r;
            layout.E = layoutParams.X;
            layout.F = layoutParams.Y;
            layout.G = layoutParams.Z;
            layout.f2514h = layoutParams.f2430c;
            layout.f2512f = layoutParams.f2428a;
            layout.f2513g = layoutParams.f2429b;
            layout.f2510d = ((ViewGroup.MarginLayoutParams) layoutParams).width;
            layout.f2511e = ((ViewGroup.MarginLayoutParams) layoutParams).height;
            layout.H = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
            layout.I = ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
            layout.J = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
            layout.K = ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
            layout.N = layoutParams.D;
            layout.V = layoutParams.M;
            layout.W = layoutParams.L;
            layout.Y = layoutParams.O;
            layout.X = layoutParams.N;
            layout.n0 = layoutParams.a0;
            layout.o0 = layoutParams.b0;
            layout.Z = layoutParams.P;
            layout.a0 = layoutParams.Q;
            layout.b0 = layoutParams.T;
            layout.c0 = layoutParams.U;
            layout.d0 = layoutParams.R;
            layout.e0 = layoutParams.S;
            layout.f0 = layoutParams.V;
            layout.g0 = layoutParams.W;
            layout.m0 = layoutParams.c0;
            layout.P = layoutParams.x;
            layout.R = layoutParams.z;
            layout.O = layoutParams.w;
            layout.Q = layoutParams.y;
            layout.T = layoutParams.A;
            layout.S = layoutParams.B;
            layout.U = layoutParams.C;
            layout.q0 = layoutParams.d0;
            layout.L = layoutParams.getMarginEnd();
            this.f2491e.M = layoutParams.getMarginStart();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void h(int i2, Constraints.LayoutParams layoutParams) {
            g(i2, layoutParams);
            this.f2489c.f2544d = layoutParams.x0;
            Transform transform = this.f2492f;
            transform.f2548b = layoutParams.A0;
            transform.f2549c = layoutParams.B0;
            transform.f2550d = layoutParams.C0;
            transform.f2551e = layoutParams.D0;
            transform.f2552f = layoutParams.E0;
            transform.f2553g = layoutParams.F0;
            transform.f2554h = layoutParams.G0;
            transform.f2556j = layoutParams.H0;
            transform.f2557k = layoutParams.I0;
            transform.f2558l = layoutParams.J0;
            transform.f2560n = layoutParams.z0;
            transform.f2559m = layoutParams.y0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void i(ConstraintHelper constraintHelper, int i2, Constraints.LayoutParams layoutParams) {
            h(i2, layoutParams);
            if (constraintHelper instanceof Barrier) {
                Layout layout = this.f2491e;
                layout.j0 = 1;
                Barrier barrier = (Barrier) constraintHelper;
                layout.h0 = barrier.getType();
                this.f2491e.k0 = barrier.getReferencedIds();
                this.f2491e.i0 = barrier.getMargin();
            }
        }

        public void d(Constraint constraint) {
            Delta delta = this.f2494h;
            if (delta != null) {
                delta.e(constraint);
            }
        }

        public void e(ConstraintLayout.LayoutParams layoutParams) {
            Layout layout = this.f2491e;
            layoutParams.f2432e = layout.f2516j;
            layoutParams.f2433f = layout.f2517k;
            layoutParams.f2434g = layout.f2518l;
            layoutParams.f2435h = layout.f2519m;
            layoutParams.f2436i = layout.f2520n;
            layoutParams.f2437j = layout.f2521o;
            layoutParams.f2438k = layout.f2522p;
            layoutParams.f2439l = layout.f2523q;
            layoutParams.f2440m = layout.f2524r;
            layoutParams.f2441n = layout.f2525s;
            layoutParams.f2442o = layout.t;
            layoutParams.f2446s = layout.u;
            layoutParams.t = layout.v;
            layoutParams.u = layout.w;
            layoutParams.v = layout.x;
            ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin = layout.H;
            ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin = layout.I;
            ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = layout.J;
            ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin = layout.K;
            layoutParams.A = layout.T;
            layoutParams.B = layout.S;
            layoutParams.x = layout.P;
            layoutParams.z = layout.R;
            layoutParams.G = layout.y;
            layoutParams.H = layout.z;
            layoutParams.f2443p = layout.B;
            layoutParams.f2444q = layout.C;
            layoutParams.f2445r = layout.D;
            layoutParams.I = layout.A;
            layoutParams.X = layout.E;
            layoutParams.Y = layout.F;
            layoutParams.M = layout.V;
            layoutParams.L = layout.W;
            layoutParams.O = layout.Y;
            layoutParams.N = layout.X;
            layoutParams.a0 = layout.n0;
            layoutParams.b0 = layout.o0;
            layoutParams.P = layout.Z;
            layoutParams.Q = layout.a0;
            layoutParams.T = layout.b0;
            layoutParams.U = layout.c0;
            layoutParams.R = layout.d0;
            layoutParams.S = layout.e0;
            layoutParams.V = layout.f0;
            layoutParams.W = layout.g0;
            layoutParams.Z = layout.G;
            layoutParams.f2430c = layout.f2514h;
            layoutParams.f2428a = layout.f2512f;
            layoutParams.f2429b = layout.f2513g;
            ((ViewGroup.MarginLayoutParams) layoutParams).width = layout.f2510d;
            ((ViewGroup.MarginLayoutParams) layoutParams).height = layout.f2511e;
            String str = layout.m0;
            if (str != null) {
                layoutParams.c0 = str;
            }
            layoutParams.d0 = layout.q0;
            layoutParams.setMarginStart(layout.M);
            layoutParams.setMarginEnd(this.f2491e.L);
            layoutParams.c();
        }

        /* renamed from: f, reason: merged with bridge method [inline-methods] */
        public Constraint clone() {
            Constraint constraint = new Constraint();
            constraint.f2491e.a(this.f2491e);
            constraint.f2490d.a(this.f2490d);
            constraint.f2489c.a(this.f2489c);
            constraint.f2492f.a(this.f2492f);
            constraint.f2487a = this.f2487a;
            constraint.f2494h = this.f2494h;
            return constraint;
        }
    }

    public static class Layout {
        private static SparseIntArray r0;

        /* renamed from: d, reason: collision with root package name */
        public int f2510d;

        /* renamed from: e, reason: collision with root package name */
        public int f2511e;
        public int[] k0;
        public String l0;
        public String m0;

        /* renamed from: a, reason: collision with root package name */
        public boolean f2507a = false;

        /* renamed from: b, reason: collision with root package name */
        public boolean f2508b = false;

        /* renamed from: c, reason: collision with root package name */
        public boolean f2509c = false;

        /* renamed from: f, reason: collision with root package name */
        public int f2512f = -1;

        /* renamed from: g, reason: collision with root package name */
        public int f2513g = -1;

        /* renamed from: h, reason: collision with root package name */
        public float f2514h = -1.0f;

        /* renamed from: i, reason: collision with root package name */
        public boolean f2515i = true;

        /* renamed from: j, reason: collision with root package name */
        public int f2516j = -1;

        /* renamed from: k, reason: collision with root package name */
        public int f2517k = -1;

        /* renamed from: l, reason: collision with root package name */
        public int f2518l = -1;

        /* renamed from: m, reason: collision with root package name */
        public int f2519m = -1;

        /* renamed from: n, reason: collision with root package name */
        public int f2520n = -1;

        /* renamed from: o, reason: collision with root package name */
        public int f2521o = -1;

        /* renamed from: p, reason: collision with root package name */
        public int f2522p = -1;

        /* renamed from: q, reason: collision with root package name */
        public int f2523q = -1;

        /* renamed from: r, reason: collision with root package name */
        public int f2524r = -1;

        /* renamed from: s, reason: collision with root package name */
        public int f2525s = -1;
        public int t = -1;
        public int u = -1;
        public int v = -1;
        public int w = -1;
        public int x = -1;
        public float y = 0.5f;
        public float z = 0.5f;
        public String A = null;
        public int B = -1;
        public int C = 0;
        public float D = 0.0f;
        public int E = -1;
        public int F = -1;
        public int G = -1;
        public int H = 0;
        public int I = 0;
        public int J = 0;
        public int K = 0;
        public int L = 0;
        public int M = 0;
        public int N = 0;
        public int O = Integer.MIN_VALUE;
        public int P = Integer.MIN_VALUE;
        public int Q = Integer.MIN_VALUE;
        public int R = Integer.MIN_VALUE;
        public int S = Integer.MIN_VALUE;
        public int T = Integer.MIN_VALUE;
        public int U = Integer.MIN_VALUE;
        public float V = -1.0f;
        public float W = -1.0f;
        public int X = 0;
        public int Y = 0;
        public int Z = 0;
        public int a0 = 0;
        public int b0 = 0;
        public int c0 = 0;
        public int d0 = 0;
        public int e0 = 0;
        public float f0 = 1.0f;
        public float g0 = 1.0f;
        public int h0 = -1;
        public int i0 = 0;
        public int j0 = -1;
        public boolean n0 = false;
        public boolean o0 = false;
        public boolean p0 = true;
        public int q0 = 0;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            r0 = sparseIntArray;
            sparseIntArray.append(R.styleable.Layout_layout_constraintLeft_toLeftOf, 24);
            r0.append(R.styleable.Layout_layout_constraintLeft_toRightOf, 25);
            r0.append(R.styleable.Layout_layout_constraintRight_toLeftOf, 28);
            r0.append(R.styleable.Layout_layout_constraintRight_toRightOf, 29);
            r0.append(R.styleable.Layout_layout_constraintTop_toTopOf, 35);
            r0.append(R.styleable.Layout_layout_constraintTop_toBottomOf, 34);
            r0.append(R.styleable.Layout_layout_constraintBottom_toTopOf, 4);
            r0.append(R.styleable.Layout_layout_constraintBottom_toBottomOf, 3);
            r0.append(R.styleable.Layout_layout_constraintBaseline_toBaselineOf, 1);
            r0.append(R.styleable.Layout_layout_editor_absoluteX, 6);
            r0.append(R.styleable.Layout_layout_editor_absoluteY, 7);
            r0.append(R.styleable.Layout_layout_constraintGuide_begin, 17);
            r0.append(R.styleable.Layout_layout_constraintGuide_end, 18);
            r0.append(R.styleable.Layout_layout_constraintGuide_percent, 19);
            r0.append(R.styleable.Layout_guidelineUseRtl, 90);
            r0.append(R.styleable.Layout_android_orientation, 26);
            r0.append(R.styleable.Layout_layout_constraintStart_toEndOf, 31);
            r0.append(R.styleable.Layout_layout_constraintStart_toStartOf, 32);
            r0.append(R.styleable.Layout_layout_constraintEnd_toStartOf, 10);
            r0.append(R.styleable.Layout_layout_constraintEnd_toEndOf, 9);
            r0.append(R.styleable.Layout_layout_goneMarginLeft, 13);
            r0.append(R.styleable.Layout_layout_goneMarginTop, 16);
            r0.append(R.styleable.Layout_layout_goneMarginRight, 14);
            r0.append(R.styleable.Layout_layout_goneMarginBottom, 11);
            r0.append(R.styleable.Layout_layout_goneMarginStart, 15);
            r0.append(R.styleable.Layout_layout_goneMarginEnd, 12);
            r0.append(R.styleable.Layout_layout_constraintVertical_weight, 38);
            r0.append(R.styleable.Layout_layout_constraintHorizontal_weight, 37);
            r0.append(R.styleable.Layout_layout_constraintHorizontal_chainStyle, 39);
            r0.append(R.styleable.Layout_layout_constraintVertical_chainStyle, 40);
            r0.append(R.styleable.Layout_layout_constraintHorizontal_bias, 20);
            r0.append(R.styleable.Layout_layout_constraintVertical_bias, 36);
            r0.append(R.styleable.Layout_layout_constraintDimensionRatio, 5);
            r0.append(R.styleable.Layout_layout_constraintLeft_creator, 91);
            r0.append(R.styleable.Layout_layout_constraintTop_creator, 91);
            r0.append(R.styleable.Layout_layout_constraintRight_creator, 91);
            r0.append(R.styleable.Layout_layout_constraintBottom_creator, 91);
            r0.append(R.styleable.Layout_layout_constraintBaseline_creator, 91);
            r0.append(R.styleable.Layout_android_layout_marginLeft, 23);
            r0.append(R.styleable.Layout_android_layout_marginRight, 27);
            r0.append(R.styleable.Layout_android_layout_marginStart, 30);
            r0.append(R.styleable.Layout_android_layout_marginEnd, 8);
            r0.append(R.styleable.Layout_android_layout_marginTop, 33);
            r0.append(R.styleable.Layout_android_layout_marginBottom, 2);
            r0.append(R.styleable.Layout_android_layout_width, 22);
            r0.append(R.styleable.Layout_android_layout_height, 21);
            r0.append(R.styleable.Layout_layout_constraintWidth, 41);
            r0.append(R.styleable.Layout_layout_constraintHeight, 42);
            r0.append(R.styleable.Layout_layout_constrainedWidth, 87);
            r0.append(R.styleable.Layout_layout_constrainedHeight, 88);
            r0.append(R.styleable.Layout_layout_wrapBehaviorInParent, 76);
            r0.append(R.styleable.Layout_layout_constraintCircle, 61);
            r0.append(R.styleable.Layout_layout_constraintCircleRadius, 62);
            r0.append(R.styleable.Layout_layout_constraintCircleAngle, 63);
            r0.append(R.styleable.Layout_layout_constraintWidth_percent, 69);
            r0.append(R.styleable.Layout_layout_constraintHeight_percent, 70);
            r0.append(R.styleable.Layout_chainUseRtl, 71);
            r0.append(R.styleable.Layout_barrierDirection, 72);
            r0.append(R.styleable.Layout_barrierMargin, 73);
            r0.append(R.styleable.Layout_constraint_referenced_ids, 74);
            r0.append(R.styleable.Layout_barrierAllowsGoneWidgets, 75);
            r0.append(R.styleable.Layout_layout_constraintWidth_max, 84);
            r0.append(R.styleable.Layout_layout_constraintWidth_min, 86);
            r0.append(R.styleable.Layout_layout_constraintWidth_max, 83);
            r0.append(R.styleable.Layout_layout_constraintHeight_min, 85);
            r0.append(R.styleable.Layout_layout_constraintWidth, 87);
            r0.append(R.styleable.Layout_layout_constraintHeight, 88);
            r0.append(R.styleable.ConstraintLayout_Layout_layout_constraintTag, 89);
            r0.append(R.styleable.Layout_guidelineUseRtl, 90);
        }

        public void a(Layout layout) {
            this.f2507a = layout.f2507a;
            this.f2510d = layout.f2510d;
            this.f2508b = layout.f2508b;
            this.f2511e = layout.f2511e;
            this.f2512f = layout.f2512f;
            this.f2513g = layout.f2513g;
            this.f2514h = layout.f2514h;
            this.f2515i = layout.f2515i;
            this.f2516j = layout.f2516j;
            this.f2517k = layout.f2517k;
            this.f2518l = layout.f2518l;
            this.f2519m = layout.f2519m;
            this.f2520n = layout.f2520n;
            this.f2521o = layout.f2521o;
            this.f2522p = layout.f2522p;
            this.f2523q = layout.f2523q;
            this.f2524r = layout.f2524r;
            this.f2525s = layout.f2525s;
            this.t = layout.t;
            this.u = layout.u;
            this.v = layout.v;
            this.w = layout.w;
            this.x = layout.x;
            this.y = layout.y;
            this.z = layout.z;
            this.A = layout.A;
            this.B = layout.B;
            this.C = layout.C;
            this.D = layout.D;
            this.E = layout.E;
            this.F = layout.F;
            this.G = layout.G;
            this.H = layout.H;
            this.I = layout.I;
            this.J = layout.J;
            this.K = layout.K;
            this.L = layout.L;
            this.M = layout.M;
            this.N = layout.N;
            this.O = layout.O;
            this.P = layout.P;
            this.Q = layout.Q;
            this.R = layout.R;
            this.S = layout.S;
            this.T = layout.T;
            this.U = layout.U;
            this.V = layout.V;
            this.W = layout.W;
            this.X = layout.X;
            this.Y = layout.Y;
            this.Z = layout.Z;
            this.a0 = layout.a0;
            this.b0 = layout.b0;
            this.c0 = layout.c0;
            this.d0 = layout.d0;
            this.e0 = layout.e0;
            this.f0 = layout.f0;
            this.g0 = layout.g0;
            this.h0 = layout.h0;
            this.i0 = layout.i0;
            this.j0 = layout.j0;
            this.m0 = layout.m0;
            int[] iArr = layout.k0;
            if (iArr == null || layout.l0 != null) {
                this.k0 = null;
            } else {
                this.k0 = Arrays.copyOf(iArr, iArr.length);
            }
            this.l0 = layout.l0;
            this.n0 = layout.n0;
            this.o0 = layout.o0;
            this.p0 = layout.p0;
            this.q0 = layout.q0;
        }

        void b(Context context, AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Layout);
            this.f2508b = true;
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                int i3 = r0.get(index);
                switch (i3) {
                    case 1:
                        this.f2524r = ConstraintSet.E(obtainStyledAttributes, index, this.f2524r);
                        break;
                    case 2:
                        this.K = obtainStyledAttributes.getDimensionPixelSize(index, this.K);
                        break;
                    case 3:
                        this.f2523q = ConstraintSet.E(obtainStyledAttributes, index, this.f2523q);
                        break;
                    case 4:
                        this.f2522p = ConstraintSet.E(obtainStyledAttributes, index, this.f2522p);
                        break;
                    case 5:
                        this.A = obtainStyledAttributes.getString(index);
                        break;
                    case 6:
                        this.E = obtainStyledAttributes.getDimensionPixelOffset(index, this.E);
                        break;
                    case 7:
                        this.F = obtainStyledAttributes.getDimensionPixelOffset(index, this.F);
                        break;
                    case 8:
                        this.L = obtainStyledAttributes.getDimensionPixelSize(index, this.L);
                        break;
                    case 9:
                        this.x = ConstraintSet.E(obtainStyledAttributes, index, this.x);
                        break;
                    case 10:
                        this.w = ConstraintSet.E(obtainStyledAttributes, index, this.w);
                        break;
                    case 11:
                        this.R = obtainStyledAttributes.getDimensionPixelSize(index, this.R);
                        break;
                    case 12:
                        this.S = obtainStyledAttributes.getDimensionPixelSize(index, this.S);
                        break;
                    case 13:
                        this.O = obtainStyledAttributes.getDimensionPixelSize(index, this.O);
                        break;
                    case 14:
                        this.Q = obtainStyledAttributes.getDimensionPixelSize(index, this.Q);
                        break;
                    case 15:
                        this.T = obtainStyledAttributes.getDimensionPixelSize(index, this.T);
                        break;
                    case 16:
                        this.P = obtainStyledAttributes.getDimensionPixelSize(index, this.P);
                        break;
                    case MlKitException.NETWORK_ISSUE /* 17 */:
                        this.f2512f = obtainStyledAttributes.getDimensionPixelOffset(index, this.f2512f);
                        break;
                    case MlKitException.UNSUPPORTED /* 18 */:
                        this.f2513g = obtainStyledAttributes.getDimensionPixelOffset(index, this.f2513g);
                        break;
                    case 19:
                        this.f2514h = obtainStyledAttributes.getFloat(index, this.f2514h);
                        break;
                    case 20:
                        this.y = obtainStyledAttributes.getFloat(index, this.y);
                        break;
                    case Status.ERROR_STREAM_REMOTE_FAILED /* 21 */:
                        this.f2511e = obtainStyledAttributes.getLayoutDimension(index, this.f2511e);
                        break;
                    case 22:
                        this.f2510d = obtainStyledAttributes.getLayoutDimension(index, this.f2510d);
                        break;
                    case 23:
                        this.H = obtainStyledAttributes.getDimensionPixelSize(index, this.H);
                        break;
                    case 24:
                        this.f2516j = ConstraintSet.E(obtainStyledAttributes, index, this.f2516j);
                        break;
                    case 25:
                        this.f2517k = ConstraintSet.E(obtainStyledAttributes, index, this.f2517k);
                        break;
                    case 26:
                        this.G = obtainStyledAttributes.getInt(index, this.G);
                        break;
                    case 27:
                        this.I = obtainStyledAttributes.getDimensionPixelSize(index, this.I);
                        break;
                    case 28:
                        this.f2518l = ConstraintSet.E(obtainStyledAttributes, index, this.f2518l);
                        break;
                    case 29:
                        this.f2519m = ConstraintSet.E(obtainStyledAttributes, index, this.f2519m);
                        break;
                    case 30:
                        this.M = obtainStyledAttributes.getDimensionPixelSize(index, this.M);
                        break;
                    case 31:
                        this.u = ConstraintSet.E(obtainStyledAttributes, index, this.u);
                        break;
                    case 32:
                        this.v = ConstraintSet.E(obtainStyledAttributes, index, this.v);
                        break;
                    case 33:
                        this.J = obtainStyledAttributes.getDimensionPixelSize(index, this.J);
                        break;
                    case 34:
                        this.f2521o = ConstraintSet.E(obtainStyledAttributes, index, this.f2521o);
                        break;
                    case 35:
                        this.f2520n = ConstraintSet.E(obtainStyledAttributes, index, this.f2520n);
                        break;
                    case 36:
                        this.z = obtainStyledAttributes.getFloat(index, this.z);
                        break;
                    case 37:
                        this.W = obtainStyledAttributes.getFloat(index, this.W);
                        break;
                    case 38:
                        this.V = obtainStyledAttributes.getFloat(index, this.V);
                        break;
                    case 39:
                        this.X = obtainStyledAttributes.getInt(index, this.X);
                        break;
                    case 40:
                        this.Y = obtainStyledAttributes.getInt(index, this.Y);
                        break;
                    case 41:
                        ConstraintSet.F(this, obtainStyledAttributes, index, 0);
                        break;
                    case 42:
                        ConstraintSet.F(this, obtainStyledAttributes, index, 1);
                        break;
                    default:
                        switch (i3) {
                            case 61:
                                this.B = ConstraintSet.E(obtainStyledAttributes, index, this.B);
                                break;
                            case 62:
                                this.C = obtainStyledAttributes.getDimensionPixelSize(index, this.C);
                                break;
                            case 63:
                                this.D = obtainStyledAttributes.getFloat(index, this.D);
                                break;
                            default:
                                switch (i3) {
                                    case 69:
                                        this.f0 = obtainStyledAttributes.getFloat(index, 1.0f);
                                        break;
                                    case 70:
                                        this.g0 = obtainStyledAttributes.getFloat(index, 1.0f);
                                        break;
                                    case 71:
                                        Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                                        break;
                                    case 72:
                                        this.h0 = obtainStyledAttributes.getInt(index, this.h0);
                                        break;
                                    case 73:
                                        this.i0 = obtainStyledAttributes.getDimensionPixelSize(index, this.i0);
                                        break;
                                    case 74:
                                        this.l0 = obtainStyledAttributes.getString(index);
                                        break;
                                    case 75:
                                        this.p0 = obtainStyledAttributes.getBoolean(index, this.p0);
                                        break;
                                    case 76:
                                        this.q0 = obtainStyledAttributes.getInt(index, this.q0);
                                        break;
                                    case 77:
                                        this.f2525s = ConstraintSet.E(obtainStyledAttributes, index, this.f2525s);
                                        break;
                                    case 78:
                                        this.t = ConstraintSet.E(obtainStyledAttributes, index, this.t);
                                        break;
                                    case 79:
                                        this.U = obtainStyledAttributes.getDimensionPixelSize(index, this.U);
                                        break;
                                    case VolumeView.MINI_VOLUME /* 80 */:
                                        this.N = obtainStyledAttributes.getDimensionPixelSize(index, this.N);
                                        break;
                                    case 81:
                                        this.Z = obtainStyledAttributes.getInt(index, this.Z);
                                        break;
                                    case 82:
                                        this.a0 = obtainStyledAttributes.getInt(index, this.a0);
                                        break;
                                    case 83:
                                        this.c0 = obtainStyledAttributes.getDimensionPixelSize(index, this.c0);
                                        break;
                                    case 84:
                                        this.b0 = obtainStyledAttributes.getDimensionPixelSize(index, this.b0);
                                        break;
                                    case 85:
                                        this.e0 = obtainStyledAttributes.getDimensionPixelSize(index, this.e0);
                                        break;
                                    case 86:
                                        this.d0 = obtainStyledAttributes.getDimensionPixelSize(index, this.d0);
                                        break;
                                    case 87:
                                        this.n0 = obtainStyledAttributes.getBoolean(index, this.n0);
                                        break;
                                    case 88:
                                        this.o0 = obtainStyledAttributes.getBoolean(index, this.o0);
                                        break;
                                    case 89:
                                        this.m0 = obtainStyledAttributes.getString(index);
                                        break;
                                    case 90:
                                        this.f2515i = obtainStyledAttributes.getBoolean(index, this.f2515i);
                                        break;
                                    case 91:
                                        Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(index) + "   " + r0.get(index));
                                        break;
                                    default:
                                        Log.w("ConstraintSet", "Unknown attribute 0x" + Integer.toHexString(index) + "   " + r0.get(index));
                                        break;
                                }
                        }
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    public static class Motion {

        /* renamed from: o, reason: collision with root package name */
        private static SparseIntArray f2526o;

        /* renamed from: a, reason: collision with root package name */
        public boolean f2527a = false;

        /* renamed from: b, reason: collision with root package name */
        public int f2528b = -1;

        /* renamed from: c, reason: collision with root package name */
        public int f2529c = 0;

        /* renamed from: d, reason: collision with root package name */
        public String f2530d = null;

        /* renamed from: e, reason: collision with root package name */
        public int f2531e = -1;

        /* renamed from: f, reason: collision with root package name */
        public int f2532f = 0;

        /* renamed from: g, reason: collision with root package name */
        public float f2533g = Float.NaN;

        /* renamed from: h, reason: collision with root package name */
        public int f2534h = -1;

        /* renamed from: i, reason: collision with root package name */
        public float f2535i = Float.NaN;

        /* renamed from: j, reason: collision with root package name */
        public float f2536j = Float.NaN;

        /* renamed from: k, reason: collision with root package name */
        public int f2537k = -1;

        /* renamed from: l, reason: collision with root package name */
        public String f2538l = null;

        /* renamed from: m, reason: collision with root package name */
        public int f2539m = -3;

        /* renamed from: n, reason: collision with root package name */
        public int f2540n = -1;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f2526o = sparseIntArray;
            sparseIntArray.append(R.styleable.Motion_motionPathRotate, 1);
            f2526o.append(R.styleable.Motion_pathMotionArc, 2);
            f2526o.append(R.styleable.Motion_transitionEasing, 3);
            f2526o.append(R.styleable.Motion_drawPath, 4);
            f2526o.append(R.styleable.Motion_animateRelativeTo, 5);
            f2526o.append(R.styleable.Motion_animateCircleAngleTo, 6);
            f2526o.append(R.styleable.Motion_motionStagger, 7);
            f2526o.append(R.styleable.Motion_quantizeMotionSteps, 8);
            f2526o.append(R.styleable.Motion_quantizeMotionPhase, 9);
            f2526o.append(R.styleable.Motion_quantizeMotionInterpolator, 10);
        }

        public void a(Motion motion) {
            this.f2527a = motion.f2527a;
            this.f2528b = motion.f2528b;
            this.f2530d = motion.f2530d;
            this.f2531e = motion.f2531e;
            this.f2532f = motion.f2532f;
            this.f2535i = motion.f2535i;
            this.f2533g = motion.f2533g;
            this.f2534h = motion.f2534h;
        }

        void b(Context context, AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Motion);
            this.f2527a = true;
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                switch (f2526o.get(index)) {
                    case 1:
                        this.f2535i = obtainStyledAttributes.getFloat(index, this.f2535i);
                        break;
                    case 2:
                        this.f2531e = obtainStyledAttributes.getInt(index, this.f2531e);
                        break;
                    case 3:
                        if (obtainStyledAttributes.peekValue(index).type == 3) {
                            this.f2530d = obtainStyledAttributes.getString(index);
                            break;
                        } else {
                            this.f2530d = Easing.f1763c[obtainStyledAttributes.getInteger(index, 0)];
                            break;
                        }
                    case 4:
                        this.f2532f = obtainStyledAttributes.getInt(index, 0);
                        break;
                    case 5:
                        this.f2528b = ConstraintSet.E(obtainStyledAttributes, index, this.f2528b);
                        break;
                    case 6:
                        this.f2529c = obtainStyledAttributes.getInteger(index, this.f2529c);
                        break;
                    case 7:
                        this.f2533g = obtainStyledAttributes.getFloat(index, this.f2533g);
                        break;
                    case 8:
                        this.f2537k = obtainStyledAttributes.getInteger(index, this.f2537k);
                        break;
                    case 9:
                        this.f2536j = obtainStyledAttributes.getFloat(index, this.f2536j);
                        break;
                    case 10:
                        int i3 = obtainStyledAttributes.peekValue(index).type;
                        if (i3 == 1) {
                            int resourceId = obtainStyledAttributes.getResourceId(index, -1);
                            this.f2540n = resourceId;
                            if (resourceId != -1) {
                                this.f2539m = -2;
                                break;
                            } else {
                                break;
                            }
                        } else if (i3 == 3) {
                            String string = obtainStyledAttributes.getString(index);
                            this.f2538l = string;
                            if (string.indexOf("/") > 0) {
                                this.f2540n = obtainStyledAttributes.getResourceId(index, -1);
                                this.f2539m = -2;
                                break;
                            } else {
                                this.f2539m = -1;
                                break;
                            }
                        } else {
                            this.f2539m = obtainStyledAttributes.getInteger(index, this.f2540n);
                            break;
                        }
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    public static class PropertySet {

        /* renamed from: a, reason: collision with root package name */
        public boolean f2541a = false;

        /* renamed from: b, reason: collision with root package name */
        public int f2542b = 0;

        /* renamed from: c, reason: collision with root package name */
        public int f2543c = 0;

        /* renamed from: d, reason: collision with root package name */
        public float f2544d = 1.0f;

        /* renamed from: e, reason: collision with root package name */
        public float f2545e = Float.NaN;

        public void a(PropertySet propertySet) {
            this.f2541a = propertySet.f2541a;
            this.f2542b = propertySet.f2542b;
            this.f2544d = propertySet.f2544d;
            this.f2545e = propertySet.f2545e;
            this.f2543c = propertySet.f2543c;
        }

        void b(Context context, AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PropertySet);
            this.f2541a = true;
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                if (index == R.styleable.PropertySet_android_alpha) {
                    this.f2544d = obtainStyledAttributes.getFloat(index, this.f2544d);
                } else if (index == R.styleable.PropertySet_android_visibility) {
                    this.f2542b = obtainStyledAttributes.getInt(index, this.f2542b);
                    this.f2542b = ConstraintSet.f2476i[this.f2542b];
                } else if (index == R.styleable.PropertySet_visibilityMode) {
                    this.f2543c = obtainStyledAttributes.getInt(index, this.f2543c);
                } else if (index == R.styleable.PropertySet_motionProgress) {
                    this.f2545e = obtainStyledAttributes.getFloat(index, this.f2545e);
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    public static class Transform {

        /* renamed from: o, reason: collision with root package name */
        private static SparseIntArray f2546o;

        /* renamed from: a, reason: collision with root package name */
        public boolean f2547a = false;

        /* renamed from: b, reason: collision with root package name */
        public float f2548b = 0.0f;

        /* renamed from: c, reason: collision with root package name */
        public float f2549c = 0.0f;

        /* renamed from: d, reason: collision with root package name */
        public float f2550d = 0.0f;

        /* renamed from: e, reason: collision with root package name */
        public float f2551e = 1.0f;

        /* renamed from: f, reason: collision with root package name */
        public float f2552f = 1.0f;

        /* renamed from: g, reason: collision with root package name */
        public float f2553g = Float.NaN;

        /* renamed from: h, reason: collision with root package name */
        public float f2554h = Float.NaN;

        /* renamed from: i, reason: collision with root package name */
        public int f2555i = -1;

        /* renamed from: j, reason: collision with root package name */
        public float f2556j = 0.0f;

        /* renamed from: k, reason: collision with root package name */
        public float f2557k = 0.0f;

        /* renamed from: l, reason: collision with root package name */
        public float f2558l = 0.0f;

        /* renamed from: m, reason: collision with root package name */
        public boolean f2559m = false;

        /* renamed from: n, reason: collision with root package name */
        public float f2560n = 0.0f;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f2546o = sparseIntArray;
            sparseIntArray.append(R.styleable.Transform_android_rotation, 1);
            f2546o.append(R.styleable.Transform_android_rotationX, 2);
            f2546o.append(R.styleable.Transform_android_rotationY, 3);
            f2546o.append(R.styleable.Transform_android_scaleX, 4);
            f2546o.append(R.styleable.Transform_android_scaleY, 5);
            f2546o.append(R.styleable.Transform_android_transformPivotX, 6);
            f2546o.append(R.styleable.Transform_android_transformPivotY, 7);
            f2546o.append(R.styleable.Transform_android_translationX, 8);
            f2546o.append(R.styleable.Transform_android_translationY, 9);
            f2546o.append(R.styleable.Transform_android_translationZ, 10);
            f2546o.append(R.styleable.Transform_android_elevation, 11);
            f2546o.append(R.styleable.Transform_transformPivotTarget, 12);
        }

        public void a(Transform transform) {
            this.f2547a = transform.f2547a;
            this.f2548b = transform.f2548b;
            this.f2549c = transform.f2549c;
            this.f2550d = transform.f2550d;
            this.f2551e = transform.f2551e;
            this.f2552f = transform.f2552f;
            this.f2553g = transform.f2553g;
            this.f2554h = transform.f2554h;
            this.f2555i = transform.f2555i;
            this.f2556j = transform.f2556j;
            this.f2557k = transform.f2557k;
            this.f2558l = transform.f2558l;
            this.f2559m = transform.f2559m;
            this.f2560n = transform.f2560n;
        }

        void b(Context context, AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Transform);
            this.f2547a = true;
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                switch (f2546o.get(index)) {
                    case 1:
                        this.f2548b = obtainStyledAttributes.getFloat(index, this.f2548b);
                        break;
                    case 2:
                        this.f2549c = obtainStyledAttributes.getFloat(index, this.f2549c);
                        break;
                    case 3:
                        this.f2550d = obtainStyledAttributes.getFloat(index, this.f2550d);
                        break;
                    case 4:
                        this.f2551e = obtainStyledAttributes.getFloat(index, this.f2551e);
                        break;
                    case 5:
                        this.f2552f = obtainStyledAttributes.getFloat(index, this.f2552f);
                        break;
                    case 6:
                        this.f2553g = obtainStyledAttributes.getDimension(index, this.f2553g);
                        break;
                    case 7:
                        this.f2554h = obtainStyledAttributes.getDimension(index, this.f2554h);
                        break;
                    case 8:
                        this.f2556j = obtainStyledAttributes.getDimension(index, this.f2556j);
                        break;
                    case 9:
                        this.f2557k = obtainStyledAttributes.getDimension(index, this.f2557k);
                        break;
                    case 10:
                        this.f2558l = obtainStyledAttributes.getDimension(index, this.f2558l);
                        break;
                    case 11:
                        this.f2559m = true;
                        this.f2560n = obtainStyledAttributes.getDimension(index, this.f2560n);
                        break;
                    case 12:
                        this.f2555i = ConstraintSet.E(obtainStyledAttributes, index, this.f2555i);
                        break;
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    class WriteJsonEngine {
    }

    class WriteXmlEngine {
    }

    static {
        f2477j.append(R.styleable.Constraint_layout_constraintLeft_toLeftOf, 25);
        f2477j.append(R.styleable.Constraint_layout_constraintLeft_toRightOf, 26);
        f2477j.append(R.styleable.Constraint_layout_constraintRight_toLeftOf, 29);
        f2477j.append(R.styleable.Constraint_layout_constraintRight_toRightOf, 30);
        f2477j.append(R.styleable.Constraint_layout_constraintTop_toTopOf, 36);
        f2477j.append(R.styleable.Constraint_layout_constraintTop_toBottomOf, 35);
        f2477j.append(R.styleable.Constraint_layout_constraintBottom_toTopOf, 4);
        f2477j.append(R.styleable.Constraint_layout_constraintBottom_toBottomOf, 3);
        f2477j.append(R.styleable.Constraint_layout_constraintBaseline_toBaselineOf, 1);
        f2477j.append(R.styleable.Constraint_layout_constraintBaseline_toTopOf, 91);
        f2477j.append(R.styleable.Constraint_layout_constraintBaseline_toBottomOf, 92);
        f2477j.append(R.styleable.Constraint_layout_editor_absoluteX, 6);
        f2477j.append(R.styleable.Constraint_layout_editor_absoluteY, 7);
        f2477j.append(R.styleable.Constraint_layout_constraintGuide_begin, 17);
        f2477j.append(R.styleable.Constraint_layout_constraintGuide_end, 18);
        f2477j.append(R.styleable.Constraint_layout_constraintGuide_percent, 19);
        f2477j.append(R.styleable.Constraint_guidelineUseRtl, 99);
        f2477j.append(R.styleable.Constraint_android_orientation, 27);
        f2477j.append(R.styleable.Constraint_layout_constraintStart_toEndOf, 32);
        f2477j.append(R.styleable.Constraint_layout_constraintStart_toStartOf, 33);
        f2477j.append(R.styleable.Constraint_layout_constraintEnd_toStartOf, 10);
        f2477j.append(R.styleable.Constraint_layout_constraintEnd_toEndOf, 9);
        f2477j.append(R.styleable.Constraint_layout_goneMarginLeft, 13);
        f2477j.append(R.styleable.Constraint_layout_goneMarginTop, 16);
        f2477j.append(R.styleable.Constraint_layout_goneMarginRight, 14);
        f2477j.append(R.styleable.Constraint_layout_goneMarginBottom, 11);
        f2477j.append(R.styleable.Constraint_layout_goneMarginStart, 15);
        f2477j.append(R.styleable.Constraint_layout_goneMarginEnd, 12);
        f2477j.append(R.styleable.Constraint_layout_constraintVertical_weight, 40);
        f2477j.append(R.styleable.Constraint_layout_constraintHorizontal_weight, 39);
        f2477j.append(R.styleable.Constraint_layout_constraintHorizontal_chainStyle, 41);
        f2477j.append(R.styleable.Constraint_layout_constraintVertical_chainStyle, 42);
        f2477j.append(R.styleable.Constraint_layout_constraintHorizontal_bias, 20);
        f2477j.append(R.styleable.Constraint_layout_constraintVertical_bias, 37);
        f2477j.append(R.styleable.Constraint_layout_constraintDimensionRatio, 5);
        f2477j.append(R.styleable.Constraint_layout_constraintLeft_creator, 87);
        f2477j.append(R.styleable.Constraint_layout_constraintTop_creator, 87);
        f2477j.append(R.styleable.Constraint_layout_constraintRight_creator, 87);
        f2477j.append(R.styleable.Constraint_layout_constraintBottom_creator, 87);
        f2477j.append(R.styleable.Constraint_layout_constraintBaseline_creator, 87);
        f2477j.append(R.styleable.Constraint_android_layout_marginLeft, 24);
        f2477j.append(R.styleable.Constraint_android_layout_marginRight, 28);
        f2477j.append(R.styleable.Constraint_android_layout_marginStart, 31);
        f2477j.append(R.styleable.Constraint_android_layout_marginEnd, 8);
        f2477j.append(R.styleable.Constraint_android_layout_marginTop, 34);
        f2477j.append(R.styleable.Constraint_android_layout_marginBottom, 2);
        f2477j.append(R.styleable.Constraint_android_layout_width, 23);
        f2477j.append(R.styleable.Constraint_android_layout_height, 21);
        f2477j.append(R.styleable.Constraint_layout_constraintWidth, 95);
        f2477j.append(R.styleable.Constraint_layout_constraintHeight, 96);
        f2477j.append(R.styleable.Constraint_android_visibility, 22);
        f2477j.append(R.styleable.Constraint_android_alpha, 43);
        f2477j.append(R.styleable.Constraint_android_elevation, 44);
        f2477j.append(R.styleable.Constraint_android_rotationX, 45);
        f2477j.append(R.styleable.Constraint_android_rotationY, 46);
        f2477j.append(R.styleable.Constraint_android_rotation, 60);
        f2477j.append(R.styleable.Constraint_android_scaleX, 47);
        f2477j.append(R.styleable.Constraint_android_scaleY, 48);
        f2477j.append(R.styleable.Constraint_android_transformPivotX, 49);
        f2477j.append(R.styleable.Constraint_android_transformPivotY, 50);
        f2477j.append(R.styleable.Constraint_android_translationX, 51);
        f2477j.append(R.styleable.Constraint_android_translationY, 52);
        f2477j.append(R.styleable.Constraint_android_translationZ, 53);
        f2477j.append(R.styleable.Constraint_layout_constraintWidth_default, 54);
        f2477j.append(R.styleable.Constraint_layout_constraintHeight_default, 55);
        f2477j.append(R.styleable.Constraint_layout_constraintWidth_max, 56);
        f2477j.append(R.styleable.Constraint_layout_constraintHeight_max, 57);
        f2477j.append(R.styleable.Constraint_layout_constraintWidth_min, 58);
        f2477j.append(R.styleable.Constraint_layout_constraintHeight_min, 59);
        f2477j.append(R.styleable.Constraint_layout_constraintCircle, 61);
        f2477j.append(R.styleable.Constraint_layout_constraintCircleRadius, 62);
        f2477j.append(R.styleable.Constraint_layout_constraintCircleAngle, 63);
        f2477j.append(R.styleable.Constraint_animateRelativeTo, 64);
        f2477j.append(R.styleable.Constraint_transitionEasing, 65);
        f2477j.append(R.styleable.Constraint_drawPath, 66);
        f2477j.append(R.styleable.Constraint_transitionPathRotate, 67);
        f2477j.append(R.styleable.Constraint_motionStagger, 79);
        f2477j.append(R.styleable.Constraint_android_id, 38);
        f2477j.append(R.styleable.Constraint_motionProgress, 68);
        f2477j.append(R.styleable.Constraint_layout_constraintWidth_percent, 69);
        f2477j.append(R.styleable.Constraint_layout_constraintHeight_percent, 70);
        f2477j.append(R.styleable.Constraint_layout_wrapBehaviorInParent, 97);
        f2477j.append(R.styleable.Constraint_chainUseRtl, 71);
        f2477j.append(R.styleable.Constraint_barrierDirection, 72);
        f2477j.append(R.styleable.Constraint_barrierMargin, 73);
        f2477j.append(R.styleable.Constraint_constraint_referenced_ids, 74);
        f2477j.append(R.styleable.Constraint_barrierAllowsGoneWidgets, 75);
        f2477j.append(R.styleable.Constraint_pathMotionArc, 76);
        f2477j.append(R.styleable.Constraint_layout_constraintTag, 77);
        f2477j.append(R.styleable.Constraint_visibilityMode, 78);
        f2477j.append(R.styleable.Constraint_layout_constrainedWidth, 80);
        f2477j.append(R.styleable.Constraint_layout_constrainedHeight, 81);
        f2477j.append(R.styleable.Constraint_polarRelativeTo, 82);
        f2477j.append(R.styleable.Constraint_transformPivotTarget, 83);
        f2477j.append(R.styleable.Constraint_quantizeMotionSteps, 84);
        f2477j.append(R.styleable.Constraint_quantizeMotionPhase, 85);
        f2477j.append(R.styleable.Constraint_quantizeMotionInterpolator, 86);
        f2478k.append(R.styleable.ConstraintOverride_layout_editor_absoluteY, 6);
        f2478k.append(R.styleable.ConstraintOverride_layout_editor_absoluteY, 7);
        f2478k.append(R.styleable.ConstraintOverride_android_orientation, 27);
        f2478k.append(R.styleable.ConstraintOverride_layout_goneMarginLeft, 13);
        f2478k.append(R.styleable.ConstraintOverride_layout_goneMarginTop, 16);
        f2478k.append(R.styleable.ConstraintOverride_layout_goneMarginRight, 14);
        f2478k.append(R.styleable.ConstraintOverride_layout_goneMarginBottom, 11);
        f2478k.append(R.styleable.ConstraintOverride_layout_goneMarginStart, 15);
        f2478k.append(R.styleable.ConstraintOverride_layout_goneMarginEnd, 12);
        f2478k.append(R.styleable.ConstraintOverride_layout_constraintVertical_weight, 40);
        f2478k.append(R.styleable.ConstraintOverride_layout_constraintHorizontal_weight, 39);
        f2478k.append(R.styleable.ConstraintOverride_layout_constraintHorizontal_chainStyle, 41);
        f2478k.append(R.styleable.ConstraintOverride_layout_constraintVertical_chainStyle, 42);
        f2478k.append(R.styleable.ConstraintOverride_layout_constraintHorizontal_bias, 20);
        f2478k.append(R.styleable.ConstraintOverride_layout_constraintVertical_bias, 37);
        f2478k.append(R.styleable.ConstraintOverride_layout_constraintDimensionRatio, 5);
        f2478k.append(R.styleable.ConstraintOverride_layout_constraintLeft_creator, 87);
        f2478k.append(R.styleable.ConstraintOverride_layout_constraintTop_creator, 87);
        f2478k.append(R.styleable.ConstraintOverride_layout_constraintRight_creator, 87);
        f2478k.append(R.styleable.ConstraintOverride_layout_constraintBottom_creator, 87);
        f2478k.append(R.styleable.ConstraintOverride_layout_constraintBaseline_creator, 87);
        f2478k.append(R.styleable.ConstraintOverride_android_layout_marginLeft, 24);
        f2478k.append(R.styleable.ConstraintOverride_android_layout_marginRight, 28);
        f2478k.append(R.styleable.ConstraintOverride_android_layout_marginStart, 31);
        f2478k.append(R.styleable.ConstraintOverride_android_layout_marginEnd, 8);
        f2478k.append(R.styleable.ConstraintOverride_android_layout_marginTop, 34);
        f2478k.append(R.styleable.ConstraintOverride_android_layout_marginBottom, 2);
        f2478k.append(R.styleable.ConstraintOverride_android_layout_width, 23);
        f2478k.append(R.styleable.ConstraintOverride_android_layout_height, 21);
        f2478k.append(R.styleable.ConstraintOverride_layout_constraintWidth, 95);
        f2478k.append(R.styleable.ConstraintOverride_layout_constraintHeight, 96);
        f2478k.append(R.styleable.ConstraintOverride_android_visibility, 22);
        f2478k.append(R.styleable.ConstraintOverride_android_alpha, 43);
        f2478k.append(R.styleable.ConstraintOverride_android_elevation, 44);
        f2478k.append(R.styleable.ConstraintOverride_android_rotationX, 45);
        f2478k.append(R.styleable.ConstraintOverride_android_rotationY, 46);
        f2478k.append(R.styleable.ConstraintOverride_android_rotation, 60);
        f2478k.append(R.styleable.ConstraintOverride_android_scaleX, 47);
        f2478k.append(R.styleable.ConstraintOverride_android_scaleY, 48);
        f2478k.append(R.styleable.ConstraintOverride_android_transformPivotX, 49);
        f2478k.append(R.styleable.ConstraintOverride_android_transformPivotY, 50);
        f2478k.append(R.styleable.ConstraintOverride_android_translationX, 51);
        f2478k.append(R.styleable.ConstraintOverride_android_translationY, 52);
        f2478k.append(R.styleable.ConstraintOverride_android_translationZ, 53);
        f2478k.append(R.styleable.ConstraintOverride_layout_constraintWidth_default, 54);
        f2478k.append(R.styleable.ConstraintOverride_layout_constraintHeight_default, 55);
        f2478k.append(R.styleable.ConstraintOverride_layout_constraintWidth_max, 56);
        f2478k.append(R.styleable.ConstraintOverride_layout_constraintHeight_max, 57);
        f2478k.append(R.styleable.ConstraintOverride_layout_constraintWidth_min, 58);
        f2478k.append(R.styleable.ConstraintOverride_layout_constraintHeight_min, 59);
        f2478k.append(R.styleable.ConstraintOverride_layout_constraintCircleRadius, 62);
        f2478k.append(R.styleable.ConstraintOverride_layout_constraintCircleAngle, 63);
        f2478k.append(R.styleable.ConstraintOverride_animateRelativeTo, 64);
        f2478k.append(R.styleable.ConstraintOverride_transitionEasing, 65);
        f2478k.append(R.styleable.ConstraintOverride_drawPath, 66);
        f2478k.append(R.styleable.ConstraintOverride_transitionPathRotate, 67);
        f2478k.append(R.styleable.ConstraintOverride_motionStagger, 79);
        f2478k.append(R.styleable.ConstraintOverride_android_id, 38);
        f2478k.append(R.styleable.ConstraintOverride_motionTarget, 98);
        f2478k.append(R.styleable.ConstraintOverride_motionProgress, 68);
        f2478k.append(R.styleable.ConstraintOverride_layout_constraintWidth_percent, 69);
        f2478k.append(R.styleable.ConstraintOverride_layout_constraintHeight_percent, 70);
        f2478k.append(R.styleable.ConstraintOverride_chainUseRtl, 71);
        f2478k.append(R.styleable.ConstraintOverride_barrierDirection, 72);
        f2478k.append(R.styleable.ConstraintOverride_barrierMargin, 73);
        f2478k.append(R.styleable.ConstraintOverride_constraint_referenced_ids, 74);
        f2478k.append(R.styleable.ConstraintOverride_barrierAllowsGoneWidgets, 75);
        f2478k.append(R.styleable.ConstraintOverride_pathMotionArc, 76);
        f2478k.append(R.styleable.ConstraintOverride_layout_constraintTag, 77);
        f2478k.append(R.styleable.ConstraintOverride_visibilityMode, 78);
        f2478k.append(R.styleable.ConstraintOverride_layout_constrainedWidth, 80);
        f2478k.append(R.styleable.ConstraintOverride_layout_constrainedHeight, 81);
        f2478k.append(R.styleable.ConstraintOverride_polarRelativeTo, 82);
        f2478k.append(R.styleable.ConstraintOverride_transformPivotTarget, 83);
        f2478k.append(R.styleable.ConstraintOverride_quantizeMotionSteps, 84);
        f2478k.append(R.styleable.ConstraintOverride_quantizeMotionPhase, 85);
        f2478k.append(R.styleable.ConstraintOverride_quantizeMotionInterpolator, 86);
        f2478k.append(R.styleable.ConstraintOverride_layout_wrapBehaviorInParent, 97);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int E(TypedArray typedArray, int i2, int i3) {
        int resourceId = typedArray.getResourceId(i2, i3);
        return resourceId == -1 ? typedArray.getInt(i2, -1) : resourceId;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0041  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static void F(java.lang.Object r4, android.content.res.TypedArray r5, int r6, int r7) {
        /*
            if (r4 != 0) goto L3
            return
        L3:
            android.util.TypedValue r0 = r5.peekValue(r6)
            int r0 = r0.type
            r1 = 3
            if (r0 == r1) goto L71
            r1 = 5
            r2 = 0
            if (r0 == r1) goto L2a
            int r5 = r5.getInt(r6, r2)
            r6 = -4
            r0 = -2
            if (r5 == r6) goto L26
            r6 = -3
            if (r5 == r6) goto L20
            if (r5 == r0) goto L22
            r6 = -1
            if (r5 == r6) goto L22
        L20:
            r5 = r2
            goto L2f
        L22:
            r3 = r2
            r2 = r5
            r5 = r3
            goto L2f
        L26:
            r2 = 1
            r5 = r2
            r2 = r0
            goto L2f
        L2a:
            int r5 = r5.getDimensionPixelSize(r6, r2)
            goto L22
        L2f:
            boolean r6 = r4 instanceof androidx.constraintlayout.widget.ConstraintLayout.LayoutParams
            if (r6 == 0) goto L41
            androidx.constraintlayout.widget.ConstraintLayout$LayoutParams r4 = (androidx.constraintlayout.widget.ConstraintLayout.LayoutParams) r4
            if (r7 != 0) goto L3c
            r4.width = r2
            r4.a0 = r5
            goto L70
        L3c:
            r4.height = r2
            r4.b0 = r5
            goto L70
        L41:
            boolean r6 = r4 instanceof androidx.constraintlayout.widget.ConstraintSet.Layout
            if (r6 == 0) goto L53
            androidx.constraintlayout.widget.ConstraintSet$Layout r4 = (androidx.constraintlayout.widget.ConstraintSet.Layout) r4
            if (r7 != 0) goto L4e
            r4.f2510d = r2
            r4.n0 = r5
            goto L70
        L4e:
            r4.f2511e = r2
            r4.o0 = r5
            goto L70
        L53:
            boolean r6 = r4 instanceof androidx.constraintlayout.widget.ConstraintSet.Constraint.Delta
            if (r6 == 0) goto L70
            androidx.constraintlayout.widget.ConstraintSet$Constraint$Delta r4 = (androidx.constraintlayout.widget.ConstraintSet.Constraint.Delta) r4
            if (r7 != 0) goto L66
            r6 = 23
            r4.b(r6, r2)
            r6 = 80
            r4.d(r6, r5)
            goto L70
        L66:
            r6 = 21
            r4.b(r6, r2)
            r6 = 81
            r4.d(r6, r5)
        L70:
            return
        L71:
            java.lang.String r5 = r5.getString(r6)
            G(r4, r5, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintSet.F(java.lang.Object, android.content.res.TypedArray, int, int):void");
    }

    static void G(Object obj, String str, int i2) {
        if (str == null) {
            return;
        }
        int indexOf = str.indexOf(61);
        int length = str.length();
        if (indexOf <= 0 || indexOf >= length - 1) {
            return;
        }
        String substring = str.substring(0, indexOf);
        String substring2 = str.substring(indexOf + 1);
        if (substring2.length() > 0) {
            String trim = substring.trim();
            String trim2 = substring2.trim();
            if ("ratio".equalsIgnoreCase(trim)) {
                if (obj instanceof ConstraintLayout.LayoutParams) {
                    ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) obj;
                    if (i2 == 0) {
                        ((ViewGroup.MarginLayoutParams) layoutParams).width = 0;
                    } else {
                        ((ViewGroup.MarginLayoutParams) layoutParams).height = 0;
                    }
                    H(layoutParams, trim2);
                    return;
                }
                if (obj instanceof Layout) {
                    ((Layout) obj).A = trim2;
                    return;
                } else {
                    if (obj instanceof Constraint.Delta) {
                        ((Constraint.Delta) obj).c(5, trim2);
                        return;
                    }
                    return;
                }
            }
            try {
                if ("weight".equalsIgnoreCase(trim)) {
                    float parseFloat = Float.parseFloat(trim2);
                    if (obj instanceof ConstraintLayout.LayoutParams) {
                        ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) obj;
                        if (i2 == 0) {
                            ((ViewGroup.MarginLayoutParams) layoutParams2).width = 0;
                            layoutParams2.L = parseFloat;
                        } else {
                            ((ViewGroup.MarginLayoutParams) layoutParams2).height = 0;
                            layoutParams2.M = parseFloat;
                        }
                    } else if (obj instanceof Layout) {
                        Layout layout = (Layout) obj;
                        if (i2 == 0) {
                            layout.f2510d = 0;
                            layout.W = parseFloat;
                        } else {
                            layout.f2511e = 0;
                            layout.V = parseFloat;
                        }
                    } else if (obj instanceof Constraint.Delta) {
                        Constraint.Delta delta = (Constraint.Delta) obj;
                        if (i2 == 0) {
                            delta.b(23, 0);
                            delta.a(39, parseFloat);
                        } else {
                            delta.b(21, 0);
                            delta.a(40, parseFloat);
                        }
                    }
                } else {
                    if (!"parent".equalsIgnoreCase(trim)) {
                        return;
                    }
                    float max = Math.max(0.0f, Math.min(1.0f, Float.parseFloat(trim2)));
                    if (obj instanceof ConstraintLayout.LayoutParams) {
                        ConstraintLayout.LayoutParams layoutParams3 = (ConstraintLayout.LayoutParams) obj;
                        if (i2 == 0) {
                            ((ViewGroup.MarginLayoutParams) layoutParams3).width = 0;
                            layoutParams3.V = max;
                            layoutParams3.P = 2;
                        } else {
                            ((ViewGroup.MarginLayoutParams) layoutParams3).height = 0;
                            layoutParams3.W = max;
                            layoutParams3.Q = 2;
                        }
                    } else if (obj instanceof Layout) {
                        Layout layout2 = (Layout) obj;
                        if (i2 == 0) {
                            layout2.f2510d = 0;
                            layout2.f0 = max;
                            layout2.Z = 2;
                        } else {
                            layout2.f2511e = 0;
                            layout2.g0 = max;
                            layout2.a0 = 2;
                        }
                    } else if (obj instanceof Constraint.Delta) {
                        Constraint.Delta delta2 = (Constraint.Delta) obj;
                        if (i2 == 0) {
                            delta2.b(23, 0);
                            delta2.b(54, 2);
                        } else {
                            delta2.b(21, 0);
                            delta2.b(55, 2);
                        }
                    }
                }
            } catch (NumberFormatException unused) {
            }
        }
    }

    static void H(ConstraintLayout.LayoutParams layoutParams, String str) {
        float f2 = Float.NaN;
        int i2 = -1;
        if (str != null) {
            int length = str.length();
            int indexOf = str.indexOf(44);
            int i3 = 0;
            if (indexOf > 0 && indexOf < length - 1) {
                String substring = str.substring(0, indexOf);
                if (substring.equalsIgnoreCase("W")) {
                    i2 = 0;
                } else if (substring.equalsIgnoreCase("H")) {
                    i2 = 1;
                }
                i3 = indexOf + 1;
            }
            int indexOf2 = str.indexOf(58);
            try {
                if (indexOf2 < 0 || indexOf2 >= length - 1) {
                    String substring2 = str.substring(i3);
                    if (substring2.length() > 0) {
                        f2 = Float.parseFloat(substring2);
                    }
                } else {
                    String substring3 = str.substring(i3, indexOf2);
                    String substring4 = str.substring(indexOf2 + 1);
                    if (substring3.length() > 0 && substring4.length() > 0) {
                        float parseFloat = Float.parseFloat(substring3);
                        float parseFloat2 = Float.parseFloat(substring4);
                        if (parseFloat > 0.0f && parseFloat2 > 0.0f) {
                            f2 = i2 == 1 ? Math.abs(parseFloat2 / parseFloat) : Math.abs(parseFloat / parseFloat2);
                        }
                    }
                }
            } catch (NumberFormatException unused) {
            }
        }
        layoutParams.I = str;
        layoutParams.J = f2;
        layoutParams.K = i2;
    }

    private void I(Constraint constraint, TypedArray typedArray, boolean z) {
        if (z) {
            J(constraint, typedArray);
            return;
        }
        int indexCount = typedArray.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = typedArray.getIndex(i2);
            if (index != R.styleable.Constraint_android_id && R.styleable.Constraint_android_layout_marginStart != index && R.styleable.Constraint_android_layout_marginEnd != index) {
                constraint.f2490d.f2527a = true;
                constraint.f2491e.f2508b = true;
                constraint.f2489c.f2541a = true;
                constraint.f2492f.f2547a = true;
            }
            switch (f2477j.get(index)) {
                case 1:
                    Layout layout = constraint.f2491e;
                    layout.f2524r = E(typedArray, index, layout.f2524r);
                    break;
                case 2:
                    Layout layout2 = constraint.f2491e;
                    layout2.K = typedArray.getDimensionPixelSize(index, layout2.K);
                    break;
                case 3:
                    Layout layout3 = constraint.f2491e;
                    layout3.f2523q = E(typedArray, index, layout3.f2523q);
                    break;
                case 4:
                    Layout layout4 = constraint.f2491e;
                    layout4.f2522p = E(typedArray, index, layout4.f2522p);
                    break;
                case 5:
                    constraint.f2491e.A = typedArray.getString(index);
                    break;
                case 6:
                    Layout layout5 = constraint.f2491e;
                    layout5.E = typedArray.getDimensionPixelOffset(index, layout5.E);
                    break;
                case 7:
                    Layout layout6 = constraint.f2491e;
                    layout6.F = typedArray.getDimensionPixelOffset(index, layout6.F);
                    break;
                case 8:
                    Layout layout7 = constraint.f2491e;
                    layout7.L = typedArray.getDimensionPixelSize(index, layout7.L);
                    break;
                case 9:
                    Layout layout8 = constraint.f2491e;
                    layout8.x = E(typedArray, index, layout8.x);
                    break;
                case 10:
                    Layout layout9 = constraint.f2491e;
                    layout9.w = E(typedArray, index, layout9.w);
                    break;
                case 11:
                    Layout layout10 = constraint.f2491e;
                    layout10.R = typedArray.getDimensionPixelSize(index, layout10.R);
                    break;
                case 12:
                    Layout layout11 = constraint.f2491e;
                    layout11.S = typedArray.getDimensionPixelSize(index, layout11.S);
                    break;
                case 13:
                    Layout layout12 = constraint.f2491e;
                    layout12.O = typedArray.getDimensionPixelSize(index, layout12.O);
                    break;
                case 14:
                    Layout layout13 = constraint.f2491e;
                    layout13.Q = typedArray.getDimensionPixelSize(index, layout13.Q);
                    break;
                case 15:
                    Layout layout14 = constraint.f2491e;
                    layout14.T = typedArray.getDimensionPixelSize(index, layout14.T);
                    break;
                case 16:
                    Layout layout15 = constraint.f2491e;
                    layout15.P = typedArray.getDimensionPixelSize(index, layout15.P);
                    break;
                case MlKitException.NETWORK_ISSUE /* 17 */:
                    Layout layout16 = constraint.f2491e;
                    layout16.f2512f = typedArray.getDimensionPixelOffset(index, layout16.f2512f);
                    break;
                case MlKitException.UNSUPPORTED /* 18 */:
                    Layout layout17 = constraint.f2491e;
                    layout17.f2513g = typedArray.getDimensionPixelOffset(index, layout17.f2513g);
                    break;
                case 19:
                    Layout layout18 = constraint.f2491e;
                    layout18.f2514h = typedArray.getFloat(index, layout18.f2514h);
                    break;
                case 20:
                    Layout layout19 = constraint.f2491e;
                    layout19.y = typedArray.getFloat(index, layout19.y);
                    break;
                case Status.ERROR_STREAM_REMOTE_FAILED /* 21 */:
                    Layout layout20 = constraint.f2491e;
                    layout20.f2511e = typedArray.getLayoutDimension(index, layout20.f2511e);
                    break;
                case 22:
                    PropertySet propertySet = constraint.f2489c;
                    propertySet.f2542b = typedArray.getInt(index, propertySet.f2542b);
                    PropertySet propertySet2 = constraint.f2489c;
                    propertySet2.f2542b = f2476i[propertySet2.f2542b];
                    break;
                case 23:
                    Layout layout21 = constraint.f2491e;
                    layout21.f2510d = typedArray.getLayoutDimension(index, layout21.f2510d);
                    break;
                case 24:
                    Layout layout22 = constraint.f2491e;
                    layout22.H = typedArray.getDimensionPixelSize(index, layout22.H);
                    break;
                case 25:
                    Layout layout23 = constraint.f2491e;
                    layout23.f2516j = E(typedArray, index, layout23.f2516j);
                    break;
                case 26:
                    Layout layout24 = constraint.f2491e;
                    layout24.f2517k = E(typedArray, index, layout24.f2517k);
                    break;
                case 27:
                    Layout layout25 = constraint.f2491e;
                    layout25.G = typedArray.getInt(index, layout25.G);
                    break;
                case 28:
                    Layout layout26 = constraint.f2491e;
                    layout26.I = typedArray.getDimensionPixelSize(index, layout26.I);
                    break;
                case 29:
                    Layout layout27 = constraint.f2491e;
                    layout27.f2518l = E(typedArray, index, layout27.f2518l);
                    break;
                case 30:
                    Layout layout28 = constraint.f2491e;
                    layout28.f2519m = E(typedArray, index, layout28.f2519m);
                    break;
                case 31:
                    Layout layout29 = constraint.f2491e;
                    layout29.M = typedArray.getDimensionPixelSize(index, layout29.M);
                    break;
                case 32:
                    Layout layout30 = constraint.f2491e;
                    layout30.u = E(typedArray, index, layout30.u);
                    break;
                case 33:
                    Layout layout31 = constraint.f2491e;
                    layout31.v = E(typedArray, index, layout31.v);
                    break;
                case 34:
                    Layout layout32 = constraint.f2491e;
                    layout32.J = typedArray.getDimensionPixelSize(index, layout32.J);
                    break;
                case 35:
                    Layout layout33 = constraint.f2491e;
                    layout33.f2521o = E(typedArray, index, layout33.f2521o);
                    break;
                case 36:
                    Layout layout34 = constraint.f2491e;
                    layout34.f2520n = E(typedArray, index, layout34.f2520n);
                    break;
                case 37:
                    Layout layout35 = constraint.f2491e;
                    layout35.z = typedArray.getFloat(index, layout35.z);
                    break;
                case 38:
                    constraint.f2487a = typedArray.getResourceId(index, constraint.f2487a);
                    break;
                case 39:
                    Layout layout36 = constraint.f2491e;
                    layout36.W = typedArray.getFloat(index, layout36.W);
                    break;
                case 40:
                    Layout layout37 = constraint.f2491e;
                    layout37.V = typedArray.getFloat(index, layout37.V);
                    break;
                case 41:
                    Layout layout38 = constraint.f2491e;
                    layout38.X = typedArray.getInt(index, layout38.X);
                    break;
                case 42:
                    Layout layout39 = constraint.f2491e;
                    layout39.Y = typedArray.getInt(index, layout39.Y);
                    break;
                case 43:
                    PropertySet propertySet3 = constraint.f2489c;
                    propertySet3.f2544d = typedArray.getFloat(index, propertySet3.f2544d);
                    break;
                case 44:
                    Transform transform = constraint.f2492f;
                    transform.f2559m = true;
                    transform.f2560n = typedArray.getDimension(index, transform.f2560n);
                    break;
                case 45:
                    Transform transform2 = constraint.f2492f;
                    transform2.f2549c = typedArray.getFloat(index, transform2.f2549c);
                    break;
                case 46:
                    Transform transform3 = constraint.f2492f;
                    transform3.f2550d = typedArray.getFloat(index, transform3.f2550d);
                    break;
                case 47:
                    Transform transform4 = constraint.f2492f;
                    transform4.f2551e = typedArray.getFloat(index, transform4.f2551e);
                    break;
                case 48:
                    Transform transform5 = constraint.f2492f;
                    transform5.f2552f = typedArray.getFloat(index, transform5.f2552f);
                    break;
                case 49:
                    Transform transform6 = constraint.f2492f;
                    transform6.f2553g = typedArray.getDimension(index, transform6.f2553g);
                    break;
                case 50:
                    Transform transform7 = constraint.f2492f;
                    transform7.f2554h = typedArray.getDimension(index, transform7.f2554h);
                    break;
                case 51:
                    Transform transform8 = constraint.f2492f;
                    transform8.f2556j = typedArray.getDimension(index, transform8.f2556j);
                    break;
                case 52:
                    Transform transform9 = constraint.f2492f;
                    transform9.f2557k = typedArray.getDimension(index, transform9.f2557k);
                    break;
                case 53:
                    Transform transform10 = constraint.f2492f;
                    transform10.f2558l = typedArray.getDimension(index, transform10.f2558l);
                    break;
                case 54:
                    Layout layout40 = constraint.f2491e;
                    layout40.Z = typedArray.getInt(index, layout40.Z);
                    break;
                case 55:
                    Layout layout41 = constraint.f2491e;
                    layout41.a0 = typedArray.getInt(index, layout41.a0);
                    break;
                case 56:
                    Layout layout42 = constraint.f2491e;
                    layout42.b0 = typedArray.getDimensionPixelSize(index, layout42.b0);
                    break;
                case 57:
                    Layout layout43 = constraint.f2491e;
                    layout43.c0 = typedArray.getDimensionPixelSize(index, layout43.c0);
                    break;
                case 58:
                    Layout layout44 = constraint.f2491e;
                    layout44.d0 = typedArray.getDimensionPixelSize(index, layout44.d0);
                    break;
                case 59:
                    Layout layout45 = constraint.f2491e;
                    layout45.e0 = typedArray.getDimensionPixelSize(index, layout45.e0);
                    break;
                case 60:
                    Transform transform11 = constraint.f2492f;
                    transform11.f2548b = typedArray.getFloat(index, transform11.f2548b);
                    break;
                case 61:
                    Layout layout46 = constraint.f2491e;
                    layout46.B = E(typedArray, index, layout46.B);
                    break;
                case 62:
                    Layout layout47 = constraint.f2491e;
                    layout47.C = typedArray.getDimensionPixelSize(index, layout47.C);
                    break;
                case 63:
                    Layout layout48 = constraint.f2491e;
                    layout48.D = typedArray.getFloat(index, layout48.D);
                    break;
                case 64:
                    Motion motion = constraint.f2490d;
                    motion.f2528b = E(typedArray, index, motion.f2528b);
                    break;
                case 65:
                    if (typedArray.peekValue(index).type == 3) {
                        constraint.f2490d.f2530d = typedArray.getString(index);
                        break;
                    } else {
                        constraint.f2490d.f2530d = Easing.f1763c[typedArray.getInteger(index, 0)];
                        break;
                    }
                case 66:
                    constraint.f2490d.f2532f = typedArray.getInt(index, 0);
                    break;
                case 67:
                    Motion motion2 = constraint.f2490d;
                    motion2.f2535i = typedArray.getFloat(index, motion2.f2535i);
                    break;
                case 68:
                    PropertySet propertySet4 = constraint.f2489c;
                    propertySet4.f2545e = typedArray.getFloat(index, propertySet4.f2545e);
                    break;
                case 69:
                    constraint.f2491e.f0 = typedArray.getFloat(index, 1.0f);
                    break;
                case 70:
                    constraint.f2491e.g0 = typedArray.getFloat(index, 1.0f);
                    break;
                case 71:
                    Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                    break;
                case 72:
                    Layout layout49 = constraint.f2491e;
                    layout49.h0 = typedArray.getInt(index, layout49.h0);
                    break;
                case 73:
                    Layout layout50 = constraint.f2491e;
                    layout50.i0 = typedArray.getDimensionPixelSize(index, layout50.i0);
                    break;
                case 74:
                    constraint.f2491e.l0 = typedArray.getString(index);
                    break;
                case 75:
                    Layout layout51 = constraint.f2491e;
                    layout51.p0 = typedArray.getBoolean(index, layout51.p0);
                    break;
                case 76:
                    Motion motion3 = constraint.f2490d;
                    motion3.f2531e = typedArray.getInt(index, motion3.f2531e);
                    break;
                case 77:
                    constraint.f2491e.m0 = typedArray.getString(index);
                    break;
                case 78:
                    PropertySet propertySet5 = constraint.f2489c;
                    propertySet5.f2543c = typedArray.getInt(index, propertySet5.f2543c);
                    break;
                case 79:
                    Motion motion4 = constraint.f2490d;
                    motion4.f2533g = typedArray.getFloat(index, motion4.f2533g);
                    break;
                case VolumeView.MINI_VOLUME /* 80 */:
                    Layout layout52 = constraint.f2491e;
                    layout52.n0 = typedArray.getBoolean(index, layout52.n0);
                    break;
                case 81:
                    Layout layout53 = constraint.f2491e;
                    layout53.o0 = typedArray.getBoolean(index, layout53.o0);
                    break;
                case 82:
                    Motion motion5 = constraint.f2490d;
                    motion5.f2529c = typedArray.getInteger(index, motion5.f2529c);
                    break;
                case 83:
                    Transform transform12 = constraint.f2492f;
                    transform12.f2555i = E(typedArray, index, transform12.f2555i);
                    break;
                case 84:
                    Motion motion6 = constraint.f2490d;
                    motion6.f2537k = typedArray.getInteger(index, motion6.f2537k);
                    break;
                case 85:
                    Motion motion7 = constraint.f2490d;
                    motion7.f2536j = typedArray.getFloat(index, motion7.f2536j);
                    break;
                case 86:
                    int i3 = typedArray.peekValue(index).type;
                    if (i3 == 1) {
                        constraint.f2490d.f2540n = typedArray.getResourceId(index, -1);
                        Motion motion8 = constraint.f2490d;
                        if (motion8.f2540n != -1) {
                            motion8.f2539m = -2;
                            break;
                        } else {
                            break;
                        }
                    } else if (i3 == 3) {
                        constraint.f2490d.f2538l = typedArray.getString(index);
                        if (constraint.f2490d.f2538l.indexOf("/") > 0) {
                            constraint.f2490d.f2540n = typedArray.getResourceId(index, -1);
                            constraint.f2490d.f2539m = -2;
                            break;
                        } else {
                            constraint.f2490d.f2539m = -1;
                            break;
                        }
                    } else {
                        Motion motion9 = constraint.f2490d;
                        motion9.f2539m = typedArray.getInteger(index, motion9.f2540n);
                        break;
                    }
                case 87:
                    Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(index) + "   " + f2477j.get(index));
                    break;
                case 88:
                case 89:
                case 90:
                default:
                    Log.w("ConstraintSet", "Unknown attribute 0x" + Integer.toHexString(index) + "   " + f2477j.get(index));
                    break;
                case 91:
                    Layout layout54 = constraint.f2491e;
                    layout54.f2525s = E(typedArray, index, layout54.f2525s);
                    break;
                case 92:
                    Layout layout55 = constraint.f2491e;
                    layout55.t = E(typedArray, index, layout55.t);
                    break;
                case 93:
                    Layout layout56 = constraint.f2491e;
                    layout56.N = typedArray.getDimensionPixelSize(index, layout56.N);
                    break;
                case 94:
                    Layout layout57 = constraint.f2491e;
                    layout57.U = typedArray.getDimensionPixelSize(index, layout57.U);
                    break;
                case 95:
                    F(constraint.f2491e, typedArray, index, 0);
                    break;
                case 96:
                    F(constraint.f2491e, typedArray, index, 1);
                    break;
                case 97:
                    Layout layout58 = constraint.f2491e;
                    layout58.q0 = typedArray.getInt(index, layout58.q0);
                    break;
            }
        }
        Layout layout59 = constraint.f2491e;
        if (layout59.l0 != null) {
            layout59.k0 = null;
        }
    }

    private static void J(Constraint constraint, TypedArray typedArray) {
        int indexCount = typedArray.getIndexCount();
        Constraint.Delta delta = new Constraint.Delta();
        constraint.f2494h = delta;
        constraint.f2490d.f2527a = false;
        constraint.f2491e.f2508b = false;
        constraint.f2489c.f2541a = false;
        constraint.f2492f.f2547a = false;
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = typedArray.getIndex(i2);
            switch (f2478k.get(index)) {
                case 2:
                    delta.b(2, typedArray.getDimensionPixelSize(index, constraint.f2491e.K));
                    break;
                case 3:
                case 4:
                case 9:
                case 10:
                case 25:
                case 26:
                case 29:
                case 30:
                case 32:
                case 33:
                case 35:
                case 36:
                case 61:
                case 88:
                case 89:
                case 90:
                case 91:
                case 92:
                default:
                    Log.w("ConstraintSet", "Unknown attribute 0x" + Integer.toHexString(index) + "   " + f2477j.get(index));
                    break;
                case 5:
                    delta.c(5, typedArray.getString(index));
                    break;
                case 6:
                    delta.b(6, typedArray.getDimensionPixelOffset(index, constraint.f2491e.E));
                    break;
                case 7:
                    delta.b(7, typedArray.getDimensionPixelOffset(index, constraint.f2491e.F));
                    break;
                case 8:
                    delta.b(8, typedArray.getDimensionPixelSize(index, constraint.f2491e.L));
                    break;
                case 11:
                    delta.b(11, typedArray.getDimensionPixelSize(index, constraint.f2491e.R));
                    break;
                case 12:
                    delta.b(12, typedArray.getDimensionPixelSize(index, constraint.f2491e.S));
                    break;
                case 13:
                    delta.b(13, typedArray.getDimensionPixelSize(index, constraint.f2491e.O));
                    break;
                case 14:
                    delta.b(14, typedArray.getDimensionPixelSize(index, constraint.f2491e.Q));
                    break;
                case 15:
                    delta.b(15, typedArray.getDimensionPixelSize(index, constraint.f2491e.T));
                    break;
                case 16:
                    delta.b(16, typedArray.getDimensionPixelSize(index, constraint.f2491e.P));
                    break;
                case MlKitException.NETWORK_ISSUE /* 17 */:
                    delta.b(17, typedArray.getDimensionPixelOffset(index, constraint.f2491e.f2512f));
                    break;
                case MlKitException.UNSUPPORTED /* 18 */:
                    delta.b(18, typedArray.getDimensionPixelOffset(index, constraint.f2491e.f2513g));
                    break;
                case 19:
                    delta.a(19, typedArray.getFloat(index, constraint.f2491e.f2514h));
                    break;
                case 20:
                    delta.a(20, typedArray.getFloat(index, constraint.f2491e.y));
                    break;
                case Status.ERROR_STREAM_REMOTE_FAILED /* 21 */:
                    delta.b(21, typedArray.getLayoutDimension(index, constraint.f2491e.f2511e));
                    break;
                case 22:
                    delta.b(22, f2476i[typedArray.getInt(index, constraint.f2489c.f2542b)]);
                    break;
                case 23:
                    delta.b(23, typedArray.getLayoutDimension(index, constraint.f2491e.f2510d));
                    break;
                case 24:
                    delta.b(24, typedArray.getDimensionPixelSize(index, constraint.f2491e.H));
                    break;
                case 27:
                    delta.b(27, typedArray.getInt(index, constraint.f2491e.G));
                    break;
                case 28:
                    delta.b(28, typedArray.getDimensionPixelSize(index, constraint.f2491e.I));
                    break;
                case 31:
                    delta.b(31, typedArray.getDimensionPixelSize(index, constraint.f2491e.M));
                    break;
                case 34:
                    delta.b(34, typedArray.getDimensionPixelSize(index, constraint.f2491e.J));
                    break;
                case 37:
                    delta.a(37, typedArray.getFloat(index, constraint.f2491e.z));
                    break;
                case 38:
                    int resourceId = typedArray.getResourceId(index, constraint.f2487a);
                    constraint.f2487a = resourceId;
                    delta.b(38, resourceId);
                    break;
                case 39:
                    delta.a(39, typedArray.getFloat(index, constraint.f2491e.W));
                    break;
                case 40:
                    delta.a(40, typedArray.getFloat(index, constraint.f2491e.V));
                    break;
                case 41:
                    delta.b(41, typedArray.getInt(index, constraint.f2491e.X));
                    break;
                case 42:
                    delta.b(42, typedArray.getInt(index, constraint.f2491e.Y));
                    break;
                case 43:
                    delta.a(43, typedArray.getFloat(index, constraint.f2489c.f2544d));
                    break;
                case 44:
                    delta.d(44, true);
                    delta.a(44, typedArray.getDimension(index, constraint.f2492f.f2560n));
                    break;
                case 45:
                    delta.a(45, typedArray.getFloat(index, constraint.f2492f.f2549c));
                    break;
                case 46:
                    delta.a(46, typedArray.getFloat(index, constraint.f2492f.f2550d));
                    break;
                case 47:
                    delta.a(47, typedArray.getFloat(index, constraint.f2492f.f2551e));
                    break;
                case 48:
                    delta.a(48, typedArray.getFloat(index, constraint.f2492f.f2552f));
                    break;
                case 49:
                    delta.a(49, typedArray.getDimension(index, constraint.f2492f.f2553g));
                    break;
                case 50:
                    delta.a(50, typedArray.getDimension(index, constraint.f2492f.f2554h));
                    break;
                case 51:
                    delta.a(51, typedArray.getDimension(index, constraint.f2492f.f2556j));
                    break;
                case 52:
                    delta.a(52, typedArray.getDimension(index, constraint.f2492f.f2557k));
                    break;
                case 53:
                    delta.a(53, typedArray.getDimension(index, constraint.f2492f.f2558l));
                    break;
                case 54:
                    delta.b(54, typedArray.getInt(index, constraint.f2491e.Z));
                    break;
                case 55:
                    delta.b(55, typedArray.getInt(index, constraint.f2491e.a0));
                    break;
                case 56:
                    delta.b(56, typedArray.getDimensionPixelSize(index, constraint.f2491e.b0));
                    break;
                case 57:
                    delta.b(57, typedArray.getDimensionPixelSize(index, constraint.f2491e.c0));
                    break;
                case 58:
                    delta.b(58, typedArray.getDimensionPixelSize(index, constraint.f2491e.d0));
                    break;
                case 59:
                    delta.b(59, typedArray.getDimensionPixelSize(index, constraint.f2491e.e0));
                    break;
                case 60:
                    delta.a(60, typedArray.getFloat(index, constraint.f2492f.f2548b));
                    break;
                case 62:
                    delta.b(62, typedArray.getDimensionPixelSize(index, constraint.f2491e.C));
                    break;
                case 63:
                    delta.a(63, typedArray.getFloat(index, constraint.f2491e.D));
                    break;
                case 64:
                    delta.b(64, E(typedArray, index, constraint.f2490d.f2528b));
                    break;
                case 65:
                    if (typedArray.peekValue(index).type == 3) {
                        delta.c(65, typedArray.getString(index));
                        break;
                    } else {
                        delta.c(65, Easing.f1763c[typedArray.getInteger(index, 0)]);
                        break;
                    }
                case 66:
                    delta.b(66, typedArray.getInt(index, 0));
                    break;
                case 67:
                    delta.a(67, typedArray.getFloat(index, constraint.f2490d.f2535i));
                    break;
                case 68:
                    delta.a(68, typedArray.getFloat(index, constraint.f2489c.f2545e));
                    break;
                case 69:
                    delta.a(69, typedArray.getFloat(index, 1.0f));
                    break;
                case 70:
                    delta.a(70, typedArray.getFloat(index, 1.0f));
                    break;
                case 71:
                    Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                    break;
                case 72:
                    delta.b(72, typedArray.getInt(index, constraint.f2491e.h0));
                    break;
                case 73:
                    delta.b(73, typedArray.getDimensionPixelSize(index, constraint.f2491e.i0));
                    break;
                case 74:
                    delta.c(74, typedArray.getString(index));
                    break;
                case 75:
                    delta.d(75, typedArray.getBoolean(index, constraint.f2491e.p0));
                    break;
                case 76:
                    delta.b(76, typedArray.getInt(index, constraint.f2490d.f2531e));
                    break;
                case 77:
                    delta.c(77, typedArray.getString(index));
                    break;
                case 78:
                    delta.b(78, typedArray.getInt(index, constraint.f2489c.f2543c));
                    break;
                case 79:
                    delta.a(79, typedArray.getFloat(index, constraint.f2490d.f2533g));
                    break;
                case VolumeView.MINI_VOLUME /* 80 */:
                    delta.d(80, typedArray.getBoolean(index, constraint.f2491e.n0));
                    break;
                case 81:
                    delta.d(81, typedArray.getBoolean(index, constraint.f2491e.o0));
                    break;
                case 82:
                    delta.b(82, typedArray.getInteger(index, constraint.f2490d.f2529c));
                    break;
                case 83:
                    delta.b(83, E(typedArray, index, constraint.f2492f.f2555i));
                    break;
                case 84:
                    delta.b(84, typedArray.getInteger(index, constraint.f2490d.f2537k));
                    break;
                case 85:
                    delta.a(85, typedArray.getFloat(index, constraint.f2490d.f2536j));
                    break;
                case 86:
                    int i3 = typedArray.peekValue(index).type;
                    if (i3 == 1) {
                        constraint.f2490d.f2540n = typedArray.getResourceId(index, -1);
                        delta.b(89, constraint.f2490d.f2540n);
                        Motion motion = constraint.f2490d;
                        if (motion.f2540n != -1) {
                            motion.f2539m = -2;
                            delta.b(88, -2);
                            break;
                        } else {
                            break;
                        }
                    } else if (i3 == 3) {
                        constraint.f2490d.f2538l = typedArray.getString(index);
                        delta.c(90, constraint.f2490d.f2538l);
                        if (constraint.f2490d.f2538l.indexOf("/") > 0) {
                            constraint.f2490d.f2540n = typedArray.getResourceId(index, -1);
                            delta.b(89, constraint.f2490d.f2540n);
                            constraint.f2490d.f2539m = -2;
                            delta.b(88, -2);
                            break;
                        } else {
                            constraint.f2490d.f2539m = -1;
                            delta.b(88, -1);
                            break;
                        }
                    } else {
                        Motion motion2 = constraint.f2490d;
                        motion2.f2539m = typedArray.getInteger(index, motion2.f2540n);
                        delta.b(88, constraint.f2490d.f2539m);
                        break;
                    }
                case 87:
                    Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(index) + "   " + f2477j.get(index));
                    break;
                case 93:
                    delta.b(93, typedArray.getDimensionPixelSize(index, constraint.f2491e.N));
                    break;
                case 94:
                    delta.b(94, typedArray.getDimensionPixelSize(index, constraint.f2491e.U));
                    break;
                case 95:
                    F(delta, typedArray, index, 0);
                    break;
                case 96:
                    F(delta, typedArray, index, 1);
                    break;
                case 97:
                    delta.b(97, typedArray.getInt(index, constraint.f2491e.q0));
                    break;
                case 98:
                    if (MotionLayout.IS_IN_EDIT_MODE) {
                        int resourceId2 = typedArray.getResourceId(index, constraint.f2487a);
                        constraint.f2487a = resourceId2;
                        if (resourceId2 == -1) {
                            constraint.f2488b = typedArray.getString(index);
                            break;
                        } else {
                            break;
                        }
                    } else if (typedArray.peekValue(index).type == 3) {
                        constraint.f2488b = typedArray.getString(index);
                        break;
                    } else {
                        constraint.f2487a = typedArray.getResourceId(index, constraint.f2487a);
                        break;
                    }
                case 99:
                    delta.d(99, typedArray.getBoolean(index, constraint.f2491e.f2515i));
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void M(Constraint constraint, int i2, float f2) {
        if (i2 == 19) {
            constraint.f2491e.f2514h = f2;
        }
        if (i2 == 20) {
            constraint.f2491e.y = f2;
            return;
        }
        if (i2 == 37) {
            constraint.f2491e.z = f2;
            return;
        }
        if (i2 == 60) {
            constraint.f2492f.f2548b = f2;
            return;
        }
        if (i2 == 63) {
            constraint.f2491e.D = f2;
            return;
        }
        if (i2 == 79) {
            constraint.f2490d.f2533g = f2;
            return;
        }
        if (i2 == 85) {
            constraint.f2490d.f2536j = f2;
            return;
        }
        if (i2 != 87) {
            if (i2 == 39) {
                constraint.f2491e.W = f2;
                return;
            }
            if (i2 == 40) {
                constraint.f2491e.V = f2;
                return;
            }
            switch (i2) {
                case 43:
                    constraint.f2489c.f2544d = f2;
                    break;
                case 44:
                    Transform transform = constraint.f2492f;
                    transform.f2560n = f2;
                    transform.f2559m = true;
                    break;
                case 45:
                    constraint.f2492f.f2549c = f2;
                    break;
                case 46:
                    constraint.f2492f.f2550d = f2;
                    break;
                case 47:
                    constraint.f2492f.f2551e = f2;
                    break;
                case 48:
                    constraint.f2492f.f2552f = f2;
                    break;
                case 49:
                    constraint.f2492f.f2553g = f2;
                    break;
                case 50:
                    constraint.f2492f.f2554h = f2;
                    break;
                case 51:
                    constraint.f2492f.f2556j = f2;
                    break;
                case 52:
                    constraint.f2492f.f2557k = f2;
                    break;
                case 53:
                    constraint.f2492f.f2558l = f2;
                    break;
                default:
                    switch (i2) {
                        case 67:
                            constraint.f2490d.f2535i = f2;
                            break;
                        case 68:
                            constraint.f2489c.f2545e = f2;
                            break;
                        case 69:
                            constraint.f2491e.f0 = f2;
                            break;
                        case 70:
                            constraint.f2491e.g0 = f2;
                            break;
                        default:
                            Log.w("ConstraintSet", "Unknown attribute 0x");
                            break;
                    }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void N(Constraint constraint, int i2, int i3) {
        if (i2 == 6) {
            constraint.f2491e.E = i3;
        }
        if (i2 == 7) {
            constraint.f2491e.F = i3;
            return;
        }
        if (i2 == 8) {
            constraint.f2491e.L = i3;
            return;
        }
        if (i2 == 27) {
            constraint.f2491e.G = i3;
            return;
        }
        if (i2 == 28) {
            constraint.f2491e.I = i3;
            return;
        }
        if (i2 == 41) {
            constraint.f2491e.X = i3;
            return;
        }
        if (i2 == 42) {
            constraint.f2491e.Y = i3;
            return;
        }
        if (i2 == 61) {
            constraint.f2491e.B = i3;
            return;
        }
        if (i2 == 62) {
            constraint.f2491e.C = i3;
            return;
        }
        if (i2 == 72) {
            constraint.f2491e.h0 = i3;
            return;
        }
        if (i2 == 73) {
            constraint.f2491e.i0 = i3;
            return;
        }
        switch (i2) {
            case 2:
                constraint.f2491e.K = i3;
                break;
            case 11:
                constraint.f2491e.R = i3;
                break;
            case 12:
                constraint.f2491e.S = i3;
                break;
            case 13:
                constraint.f2491e.O = i3;
                break;
            case 14:
                constraint.f2491e.Q = i3;
                break;
            case 15:
                constraint.f2491e.T = i3;
                break;
            case 16:
                constraint.f2491e.P = i3;
                break;
            case MlKitException.NETWORK_ISSUE /* 17 */:
                constraint.f2491e.f2512f = i3;
                break;
            case MlKitException.UNSUPPORTED /* 18 */:
                constraint.f2491e.f2513g = i3;
                break;
            case 31:
                constraint.f2491e.M = i3;
                break;
            case 34:
                constraint.f2491e.J = i3;
                break;
            case 38:
                constraint.f2487a = i3;
                break;
            case 64:
                constraint.f2490d.f2528b = i3;
                break;
            case 66:
                constraint.f2490d.f2532f = i3;
                break;
            case 76:
                constraint.f2490d.f2531e = i3;
                break;
            case 78:
                constraint.f2489c.f2543c = i3;
                break;
            case 93:
                constraint.f2491e.N = i3;
                break;
            case 94:
                constraint.f2491e.U = i3;
                break;
            case 97:
                constraint.f2491e.q0 = i3;
                break;
            default:
                switch (i2) {
                    case Status.ERROR_STREAM_REMOTE_FAILED /* 21 */:
                        constraint.f2491e.f2511e = i3;
                        break;
                    case 22:
                        constraint.f2489c.f2542b = i3;
                        break;
                    case 23:
                        constraint.f2491e.f2510d = i3;
                        break;
                    case 24:
                        constraint.f2491e.H = i3;
                        break;
                    default:
                        switch (i2) {
                            case 54:
                                constraint.f2491e.Z = i3;
                                break;
                            case 55:
                                constraint.f2491e.a0 = i3;
                                break;
                            case 56:
                                constraint.f2491e.b0 = i3;
                                break;
                            case 57:
                                constraint.f2491e.c0 = i3;
                                break;
                            case 58:
                                constraint.f2491e.d0 = i3;
                                break;
                            case 59:
                                constraint.f2491e.e0 = i3;
                                break;
                            default:
                                switch (i2) {
                                    case 82:
                                        constraint.f2490d.f2529c = i3;
                                        break;
                                    case 83:
                                        constraint.f2492f.f2555i = i3;
                                        break;
                                    case 84:
                                        constraint.f2490d.f2537k = i3;
                                        break;
                                    default:
                                        switch (i2) {
                                            case 87:
                                                break;
                                            case 88:
                                                constraint.f2490d.f2539m = i3;
                                                break;
                                            case 89:
                                                constraint.f2490d.f2540n = i3;
                                                break;
                                            default:
                                                Log.w("ConstraintSet", "Unknown attribute 0x");
                                                break;
                                        }
                                }
                        }
                }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void O(Constraint constraint, int i2, String str) {
        if (i2 == 5) {
            constraint.f2491e.A = str;
            return;
        }
        if (i2 == 65) {
            constraint.f2490d.f2530d = str;
            return;
        }
        if (i2 == 74) {
            Layout layout = constraint.f2491e;
            layout.l0 = str;
            layout.k0 = null;
        } else if (i2 == 77) {
            constraint.f2491e.m0 = str;
        } else if (i2 != 87) {
            if (i2 != 90) {
                Log.w("ConstraintSet", "Unknown attribute 0x");
            } else {
                constraint.f2490d.f2538l = str;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void P(Constraint constraint, int i2, boolean z) {
        if (i2 == 44) {
            constraint.f2492f.f2559m = z;
            return;
        }
        if (i2 == 75) {
            constraint.f2491e.p0 = z;
            return;
        }
        if (i2 != 87) {
            if (i2 == 80) {
                constraint.f2491e.n0 = z;
            } else if (i2 != 81) {
                Log.w("ConstraintSet", "Unknown attribute 0x");
            } else {
                constraint.f2491e.o0 = z;
            }
        }
    }

    public static Constraint m(Context context, XmlPullParser xmlPullParser) {
        AttributeSet asAttributeSet = Xml.asAttributeSet(xmlPullParser);
        Constraint constraint = new Constraint();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(asAttributeSet, R.styleable.ConstraintOverride);
        J(constraint, obtainStyledAttributes);
        obtainStyledAttributes.recycle();
        return constraint;
    }

    private int[] s(View view, String str) {
        int i2;
        Object o2;
        String[] split = str.split(",");
        Context context = view.getContext();
        int[] iArr = new int[split.length];
        int i3 = 0;
        int i4 = 0;
        while (i3 < split.length) {
            String trim = split[i3].trim();
            try {
                i2 = R.id.class.getField(trim).getInt(null);
            } catch (Exception unused) {
                i2 = 0;
            }
            if (i2 == 0) {
                i2 = context.getResources().getIdentifier(trim, VirtualHandleWrapper.KEY_ID, context.getPackageName());
            }
            if (i2 == 0 && view.isInEditMode() && (view.getParent() instanceof ConstraintLayout) && (o2 = ((ConstraintLayout) view.getParent()).o(0, trim)) != null && (o2 instanceof Integer)) {
                i2 = ((Integer) o2).intValue();
            }
            iArr[i4] = i2;
            i3++;
            i4++;
        }
        return i4 != split.length ? Arrays.copyOf(iArr, i4) : iArr;
    }

    private Constraint t(Context context, AttributeSet attributeSet, boolean z) {
        Constraint constraint = new Constraint();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, z ? R.styleable.ConstraintOverride : R.styleable.Constraint);
        I(constraint, obtainStyledAttributes, z);
        obtainStyledAttributes.recycle();
        return constraint;
    }

    private Constraint u(int i2) {
        if (!this.f2486h.containsKey(Integer.valueOf(i2))) {
            this.f2486h.put(Integer.valueOf(i2), new Constraint());
        }
        return (Constraint) this.f2486h.get(Integer.valueOf(i2));
    }

    public int A(int i2) {
        return u(i2).f2489c.f2543c;
    }

    public int B(int i2) {
        return u(i2).f2491e.f2510d;
    }

    public void C(Context context, int i2) {
        XmlResourceParser xml = context.getResources().getXml(i2);
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                if (eventType == 2) {
                    String name = xml.getName();
                    Constraint t = t(context, Xml.asAttributeSet(xml), false);
                    if (name.equalsIgnoreCase("Guideline")) {
                        t.f2491e.f2507a = true;
                    }
                    this.f2486h.put(Integer.valueOf(t.f2487a), t);
                }
            }
        } catch (IOException e2) {
            Log.e("ConstraintSet", "Error parsing resource: " + i2, e2);
        } catch (XmlPullParserException e3) {
            Log.e("ConstraintSet", "Error parsing resource: " + i2, e3);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:82:0x01d3, code lost:
    
        continue;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void D(android.content.Context r12, org.xmlpull.v1.XmlPullParser r13) {
        /*
            Method dump skipped, instructions count: 566
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintSet.D(android.content.Context, org.xmlpull.v1.XmlPullParser):void");
    }

    public void K(ConstraintLayout constraintLayout) {
        int childCount = constraintLayout.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = constraintLayout.getChildAt(i2);
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) childAt.getLayoutParams();
            int id = childAt.getId();
            if (this.f2485g && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!this.f2486h.containsKey(Integer.valueOf(id))) {
                this.f2486h.put(Integer.valueOf(id), new Constraint());
            }
            Constraint constraint = (Constraint) this.f2486h.get(Integer.valueOf(id));
            if (constraint != null) {
                if (!constraint.f2491e.f2508b) {
                    constraint.g(id, layoutParams);
                    if (childAt instanceof ConstraintHelper) {
                        constraint.f2491e.k0 = ((ConstraintHelper) childAt).getReferencedIds();
                        if (childAt instanceof Barrier) {
                            Barrier barrier = (Barrier) childAt;
                            constraint.f2491e.p0 = barrier.getAllowsGoneWidget();
                            constraint.f2491e.h0 = barrier.getType();
                            constraint.f2491e.i0 = barrier.getMargin();
                        }
                    }
                    constraint.f2491e.f2508b = true;
                }
                PropertySet propertySet = constraint.f2489c;
                if (!propertySet.f2541a) {
                    propertySet.f2542b = childAt.getVisibility();
                    constraint.f2489c.f2544d = childAt.getAlpha();
                    constraint.f2489c.f2541a = true;
                }
                Transform transform = constraint.f2492f;
                if (!transform.f2547a) {
                    transform.f2547a = true;
                    transform.f2548b = childAt.getRotation();
                    constraint.f2492f.f2549c = childAt.getRotationX();
                    constraint.f2492f.f2550d = childAt.getRotationY();
                    constraint.f2492f.f2551e = childAt.getScaleX();
                    constraint.f2492f.f2552f = childAt.getScaleY();
                    float pivotX = childAt.getPivotX();
                    float pivotY = childAt.getPivotY();
                    if (pivotX != 0.0d || pivotY != 0.0d) {
                        Transform transform2 = constraint.f2492f;
                        transform2.f2553g = pivotX;
                        transform2.f2554h = pivotY;
                    }
                    constraint.f2492f.f2556j = childAt.getTranslationX();
                    constraint.f2492f.f2557k = childAt.getTranslationY();
                    constraint.f2492f.f2558l = childAt.getTranslationZ();
                    Transform transform3 = constraint.f2492f;
                    if (transform3.f2559m) {
                        transform3.f2560n = childAt.getElevation();
                    }
                }
            }
        }
    }

    public void L(ConstraintSet constraintSet) {
        for (Integer num : constraintSet.f2486h.keySet()) {
            num.intValue();
            Constraint constraint = (Constraint) constraintSet.f2486h.get(num);
            if (!this.f2486h.containsKey(num)) {
                this.f2486h.put(num, new Constraint());
            }
            Constraint constraint2 = (Constraint) this.f2486h.get(num);
            if (constraint2 != null) {
                Layout layout = constraint2.f2491e;
                if (!layout.f2508b) {
                    layout.a(constraint.f2491e);
                }
                PropertySet propertySet = constraint2.f2489c;
                if (!propertySet.f2541a) {
                    propertySet.a(constraint.f2489c);
                }
                Transform transform = constraint2.f2492f;
                if (!transform.f2547a) {
                    transform.a(constraint.f2492f);
                }
                Motion motion = constraint2.f2490d;
                if (!motion.f2527a) {
                    motion.a(constraint.f2490d);
                }
                for (String str : constraint.f2493g.keySet()) {
                    if (!constraint2.f2493g.containsKey(str)) {
                        constraint2.f2493g.put(str, (ConstraintAttribute) constraint.f2493g.get(str));
                    }
                }
            }
        }
    }

    public void Q(boolean z) {
        this.f2485g = z;
    }

    public void R(String str) {
        this.f2482d = str.split(",");
        int i2 = 0;
        while (true) {
            String[] strArr = this.f2482d;
            if (i2 >= strArr.length) {
                return;
            }
            strArr[i2] = strArr[i2].trim();
            i2++;
        }
    }

    public void S(boolean z) {
        this.f2479a = z;
    }

    public void g(ConstraintLayout constraintLayout) {
        Constraint constraint;
        int childCount = constraintLayout.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = constraintLayout.getChildAt(i2);
            int id = childAt.getId();
            if (!this.f2486h.containsKey(Integer.valueOf(id))) {
                Log.w("ConstraintSet", "id unknown " + Debug.d(childAt));
            } else {
                if (this.f2485g && id == -1) {
                    throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
                }
                if (this.f2486h.containsKey(Integer.valueOf(id)) && (constraint = (Constraint) this.f2486h.get(Integer.valueOf(id))) != null) {
                    ConstraintAttribute.j(childAt, constraint.f2493g);
                }
            }
        }
    }

    public void h(ConstraintSet constraintSet) {
        for (Constraint constraint : constraintSet.f2486h.values()) {
            if (constraint.f2494h != null) {
                if (constraint.f2488b == null) {
                    constraint.f2494h.e(v(constraint.f2487a));
                } else {
                    Iterator it = this.f2486h.keySet().iterator();
                    while (it.hasNext()) {
                        Constraint v = v(((Integer) it.next()).intValue());
                        String str = v.f2491e.m0;
                        if (str != null && constraint.f2488b.matches(str)) {
                            constraint.f2494h.e(v);
                            v.f2493g.putAll((HashMap) constraint.f2493g.clone());
                        }
                    }
                }
            }
        }
    }

    public void i(ConstraintLayout constraintLayout) {
        k(constraintLayout, true);
        constraintLayout.setConstraintSet(null);
        constraintLayout.requestLayout();
    }

    public void j(ConstraintHelper constraintHelper, ConstraintWidget constraintWidget, ConstraintLayout.LayoutParams layoutParams, SparseArray sparseArray) {
        Constraint constraint;
        int id = constraintHelper.getId();
        if (this.f2486h.containsKey(Integer.valueOf(id)) && (constraint = (Constraint) this.f2486h.get(Integer.valueOf(id))) != null && (constraintWidget instanceof HelperWidget)) {
            constraintHelper.p(constraint, (HelperWidget) constraintWidget, layoutParams, sparseArray);
        }
    }

    void k(ConstraintLayout constraintLayout, boolean z) {
        int childCount = constraintLayout.getChildCount();
        HashSet hashSet = new HashSet(this.f2486h.keySet());
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = constraintLayout.getChildAt(i2);
            int id = childAt.getId();
            if (!this.f2486h.containsKey(Integer.valueOf(id))) {
                Log.w("ConstraintSet", "id unknown " + Debug.d(childAt));
            } else {
                if (this.f2485g && id == -1) {
                    throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
                }
                if (id != -1) {
                    if (this.f2486h.containsKey(Integer.valueOf(id))) {
                        hashSet.remove(Integer.valueOf(id));
                        Constraint constraint = (Constraint) this.f2486h.get(Integer.valueOf(id));
                        if (constraint != null) {
                            if (childAt instanceof Barrier) {
                                constraint.f2491e.j0 = 1;
                                Barrier barrier = (Barrier) childAt;
                                barrier.setId(id);
                                barrier.setType(constraint.f2491e.h0);
                                barrier.setMargin(constraint.f2491e.i0);
                                barrier.setAllowsGoneWidget(constraint.f2491e.p0);
                                Layout layout = constraint.f2491e;
                                int[] iArr = layout.k0;
                                if (iArr != null) {
                                    barrier.setReferencedIds(iArr);
                                } else {
                                    String str = layout.l0;
                                    if (str != null) {
                                        layout.k0 = s(barrier, str);
                                        barrier.setReferencedIds(constraint.f2491e.k0);
                                    }
                                }
                            }
                            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) childAt.getLayoutParams();
                            layoutParams.c();
                            constraint.e(layoutParams);
                            if (z) {
                                ConstraintAttribute.j(childAt, constraint.f2493g);
                            }
                            childAt.setLayoutParams(layoutParams);
                            PropertySet propertySet = constraint.f2489c;
                            if (propertySet.f2543c == 0) {
                                childAt.setVisibility(propertySet.f2542b);
                            }
                            childAt.setAlpha(constraint.f2489c.f2544d);
                            childAt.setRotation(constraint.f2492f.f2548b);
                            childAt.setRotationX(constraint.f2492f.f2549c);
                            childAt.setRotationY(constraint.f2492f.f2550d);
                            childAt.setScaleX(constraint.f2492f.f2551e);
                            childAt.setScaleY(constraint.f2492f.f2552f);
                            Transform transform = constraint.f2492f;
                            if (transform.f2555i != -1) {
                                if (((View) childAt.getParent()).findViewById(constraint.f2492f.f2555i) != null) {
                                    float top = (r4.getTop() + r4.getBottom()) / 2.0f;
                                    float left = (r4.getLeft() + r4.getRight()) / 2.0f;
                                    if (childAt.getRight() - childAt.getLeft() > 0 && childAt.getBottom() - childAt.getTop() > 0) {
                                        childAt.setPivotX(left - childAt.getLeft());
                                        childAt.setPivotY(top - childAt.getTop());
                                    }
                                }
                            } else {
                                if (!Float.isNaN(transform.f2553g)) {
                                    childAt.setPivotX(constraint.f2492f.f2553g);
                                }
                                if (!Float.isNaN(constraint.f2492f.f2554h)) {
                                    childAt.setPivotY(constraint.f2492f.f2554h);
                                }
                            }
                            childAt.setTranslationX(constraint.f2492f.f2556j);
                            childAt.setTranslationY(constraint.f2492f.f2557k);
                            childAt.setTranslationZ(constraint.f2492f.f2558l);
                            Transform transform2 = constraint.f2492f;
                            if (transform2.f2559m) {
                                childAt.setElevation(transform2.f2560n);
                            }
                        }
                    } else {
                        Log.v("ConstraintSet", "WARNING NO CONSTRAINTS for view " + id);
                    }
                }
            }
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            Integer num = (Integer) it.next();
            Constraint constraint2 = (Constraint) this.f2486h.get(num);
            if (constraint2 != null) {
                if (constraint2.f2491e.j0 == 1) {
                    Barrier barrier2 = new Barrier(constraintLayout.getContext());
                    barrier2.setId(num.intValue());
                    Layout layout2 = constraint2.f2491e;
                    int[] iArr2 = layout2.k0;
                    if (iArr2 != null) {
                        barrier2.setReferencedIds(iArr2);
                    } else {
                        String str2 = layout2.l0;
                        if (str2 != null) {
                            layout2.k0 = s(barrier2, str2);
                            barrier2.setReferencedIds(constraint2.f2491e.k0);
                        }
                    }
                    barrier2.setType(constraint2.f2491e.h0);
                    barrier2.setMargin(constraint2.f2491e.i0);
                    ConstraintLayout.LayoutParams generateDefaultLayoutParams = constraintLayout.generateDefaultLayoutParams();
                    barrier2.w();
                    constraint2.e(generateDefaultLayoutParams);
                    constraintLayout.addView(barrier2, generateDefaultLayoutParams);
                }
                if (constraint2.f2491e.f2507a) {
                    View guideline = new Guideline(constraintLayout.getContext());
                    guideline.setId(num.intValue());
                    ConstraintLayout.LayoutParams generateDefaultLayoutParams2 = constraintLayout.generateDefaultLayoutParams();
                    constraint2.e(generateDefaultLayoutParams2);
                    constraintLayout.addView(guideline, generateDefaultLayoutParams2);
                }
            }
        }
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt2 = constraintLayout.getChildAt(i3);
            if (childAt2 instanceof ConstraintHelper) {
                ((ConstraintHelper) childAt2).j(constraintLayout);
            }
        }
    }

    public void l(int i2, ConstraintLayout.LayoutParams layoutParams) {
        Constraint constraint;
        if (!this.f2486h.containsKey(Integer.valueOf(i2)) || (constraint = (Constraint) this.f2486h.get(Integer.valueOf(i2))) == null) {
            return;
        }
        constraint.e(layoutParams);
    }

    public void n(Context context, int i2) {
        o((ConstraintLayout) LayoutInflater.from(context).inflate(i2, (ViewGroup) null));
    }

    public void o(ConstraintLayout constraintLayout) {
        int childCount = constraintLayout.getChildCount();
        this.f2486h.clear();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = constraintLayout.getChildAt(i2);
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) childAt.getLayoutParams();
            int id = childAt.getId();
            if (this.f2485g && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!this.f2486h.containsKey(Integer.valueOf(id))) {
                this.f2486h.put(Integer.valueOf(id), new Constraint());
            }
            Constraint constraint = (Constraint) this.f2486h.get(Integer.valueOf(id));
            if (constraint != null) {
                constraint.f2493g = ConstraintAttribute.b(this.f2484f, childAt);
                constraint.g(id, layoutParams);
                constraint.f2489c.f2542b = childAt.getVisibility();
                constraint.f2489c.f2544d = childAt.getAlpha();
                constraint.f2492f.f2548b = childAt.getRotation();
                constraint.f2492f.f2549c = childAt.getRotationX();
                constraint.f2492f.f2550d = childAt.getRotationY();
                constraint.f2492f.f2551e = childAt.getScaleX();
                constraint.f2492f.f2552f = childAt.getScaleY();
                float pivotX = childAt.getPivotX();
                float pivotY = childAt.getPivotY();
                if (pivotX != 0.0d || pivotY != 0.0d) {
                    Transform transform = constraint.f2492f;
                    transform.f2553g = pivotX;
                    transform.f2554h = pivotY;
                }
                constraint.f2492f.f2556j = childAt.getTranslationX();
                constraint.f2492f.f2557k = childAt.getTranslationY();
                constraint.f2492f.f2558l = childAt.getTranslationZ();
                Transform transform2 = constraint.f2492f;
                if (transform2.f2559m) {
                    transform2.f2560n = childAt.getElevation();
                }
                if (childAt instanceof Barrier) {
                    Barrier barrier = (Barrier) childAt;
                    constraint.f2491e.p0 = barrier.getAllowsGoneWidget();
                    constraint.f2491e.k0 = barrier.getReferencedIds();
                    constraint.f2491e.h0 = barrier.getType();
                    constraint.f2491e.i0 = barrier.getMargin();
                }
            }
        }
    }

    public void p(ConstraintSet constraintSet) {
        this.f2486h.clear();
        for (Integer num : constraintSet.f2486h.keySet()) {
            Constraint constraint = (Constraint) constraintSet.f2486h.get(num);
            if (constraint != null) {
                this.f2486h.put(num, constraint.clone());
            }
        }
    }

    public void q(Constraints constraints) {
        int childCount = constraints.getChildCount();
        this.f2486h.clear();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = constraints.getChildAt(i2);
            Constraints.LayoutParams layoutParams = (Constraints.LayoutParams) childAt.getLayoutParams();
            int id = childAt.getId();
            if (this.f2485g && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!this.f2486h.containsKey(Integer.valueOf(id))) {
                this.f2486h.put(Integer.valueOf(id), new Constraint());
            }
            Constraint constraint = (Constraint) this.f2486h.get(Integer.valueOf(id));
            if (constraint != null) {
                if (childAt instanceof ConstraintHelper) {
                    constraint.i((ConstraintHelper) childAt, id, layoutParams);
                }
                constraint.h(id, layoutParams);
            }
        }
    }

    public void r(int i2, int i3, int i4, float f2) {
        Layout layout = u(i2).f2491e;
        layout.B = i3;
        layout.C = i4;
        layout.D = f2;
    }

    public Constraint v(int i2) {
        if (this.f2486h.containsKey(Integer.valueOf(i2))) {
            return (Constraint) this.f2486h.get(Integer.valueOf(i2));
        }
        return null;
    }

    public int w(int i2) {
        return u(i2).f2491e.f2511e;
    }

    public int[] x() {
        Integer[] numArr = (Integer[]) this.f2486h.keySet().toArray(new Integer[0]);
        int length = numArr.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = numArr[i2].intValue();
        }
        return iArr;
    }

    public Constraint y(int i2) {
        return u(i2);
    }

    public int z(int i2) {
        return u(i2).f2489c.f2542b;
    }
}
