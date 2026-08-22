package androidx.constraintlayout.motion.widget;

import android.view.View;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintSet;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.util.Arrays;
import java.util.LinkedHashMap;

/* loaded from: classes.dex */
class MotionPaths implements Comparable<MotionPaths> {
    static String[] z = {"position", "x", "y", "width", "height", "pathRotate"};

    /* renamed from: c, reason: collision with root package name */
    Easing f2279c;

    /* renamed from: i, reason: collision with root package name */
    float f2281i;

    /* renamed from: j, reason: collision with root package name */
    float f2282j;

    /* renamed from: k, reason: collision with root package name */
    float f2283k;

    /* renamed from: l, reason: collision with root package name */
    float f2284l;

    /* renamed from: m, reason: collision with root package name */
    float f2285m;

    /* renamed from: n, reason: collision with root package name */
    float f2286n;

    /* renamed from: q, reason: collision with root package name */
    int f2289q;

    /* renamed from: r, reason: collision with root package name */
    int f2290r;

    /* renamed from: s, reason: collision with root package name */
    float f2291s;
    MotionController t;
    LinkedHashMap u;
    int v;
    int w;
    double[] x;
    double[] y;

    /* renamed from: h, reason: collision with root package name */
    int f2280h = 0;

    /* renamed from: o, reason: collision with root package name */
    float f2287o = Float.NaN;

    /* renamed from: p, reason: collision with root package name */
    float f2288p = Float.NaN;

    MotionPaths() {
        int i2 = Key.f2122f;
        this.f2289q = i2;
        this.f2290r = i2;
        this.f2291s = Float.NaN;
        this.t = null;
        this.u = new LinkedHashMap();
        this.v = 0;
        this.x = new double[18];
        this.y = new double[18];
    }

    private boolean e(float f2, float f3) {
        return (Float.isNaN(f2) || Float.isNaN(f3)) ? Float.isNaN(f2) != Float.isNaN(f3) : Math.abs(f2 - f3) > 1.0E-6f;
    }

    public void A(MotionController motionController, MotionPaths motionPaths) {
        double d2 = ((this.f2283k + (this.f2285m / 2.0f)) - motionPaths.f2283k) - (motionPaths.f2285m / 2.0f);
        double d3 = ((this.f2284l + (this.f2286n / 2.0f)) - motionPaths.f2284l) - (motionPaths.f2286n / 2.0f);
        this.t = motionController;
        this.f2283k = (float) Math.hypot(d3, d2);
        if (Float.isNaN(this.f2291s)) {
            this.f2284l = (float) (Math.atan2(d3, d2) + 1.5707963267948966d);
        } else {
            this.f2284l = (float) Math.toRadians(this.f2291s);
        }
    }

    public void c(ConstraintSet.Constraint constraint) {
        this.f2279c = Easing.c(constraint.f2490d.f2530d);
        ConstraintSet.Motion motion = constraint.f2490d;
        this.f2289q = motion.f2531e;
        this.f2290r = motion.f2528b;
        this.f2287o = motion.f2535i;
        this.f2280h = motion.f2532f;
        this.w = motion.f2529c;
        this.f2288p = constraint.f2489c.f2545e;
        this.f2291s = constraint.f2491e.D;
        for (String str : constraint.f2493g.keySet()) {
            ConstraintAttribute constraintAttribute = (ConstraintAttribute) constraint.f2493g.get(str);
            if (constraintAttribute != null && constraintAttribute.g()) {
                this.u.put(str, constraintAttribute);
            }
        }
    }

    @Override // java.lang.Comparable
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public int compareTo(MotionPaths motionPaths) {
        return Float.compare(this.f2282j, motionPaths.f2282j);
    }

    void f(MotionPaths motionPaths, boolean[] zArr, String[] strArr, boolean z2) {
        boolean e2 = e(this.f2283k, motionPaths.f2283k);
        boolean e3 = e(this.f2284l, motionPaths.f2284l);
        zArr[0] = zArr[0] | e(this.f2282j, motionPaths.f2282j);
        boolean z3 = e2 | e3 | z2;
        zArr[1] = zArr[1] | z3;
        zArr[2] = z3 | zArr[2];
        zArr[3] = zArr[3] | e(this.f2285m, motionPaths.f2285m);
        zArr[4] = e(this.f2286n, motionPaths.f2286n) | zArr[4];
    }

    void h(double[] dArr, int[] iArr) {
        float[] fArr = {this.f2282j, this.f2283k, this.f2284l, this.f2285m, this.f2286n, this.f2287o};
        int i2 = 0;
        for (int i3 : iArr) {
            if (i3 < 6) {
                dArr[i2] = fArr[r1];
                i2++;
            }
        }
    }

    void j(double d2, int[] iArr, double[] dArr, float[] fArr, int i2) {
        float f2 = this.f2283k;
        float f3 = this.f2284l;
        float f4 = this.f2285m;
        float f5 = this.f2286n;
        for (int i3 = 0; i3 < iArr.length; i3++) {
            float f6 = (float) dArr[i3];
            int i4 = iArr[i3];
            if (i4 == 1) {
                f2 = f6;
            } else if (i4 == 2) {
                f3 = f6;
            } else if (i4 == 3) {
                f4 = f6;
            } else if (i4 == 4) {
                f5 = f6;
            }
        }
        MotionController motionController = this.t;
        if (motionController != null) {
            float[] fArr2 = new float[2];
            motionController.i(d2, fArr2, new float[2]);
            float f7 = fArr2[0];
            float f8 = fArr2[1];
            double d3 = f2;
            double d4 = f3;
            float sin = (float) ((f7 + (Math.sin(d4) * d3)) - (f4 / 2.0f));
            f3 = (float) ((f8 - (d3 * Math.cos(d4))) - (f5 / 2.0f));
            f2 = sin;
        }
        fArr[i2] = f2 + (f4 / 2.0f) + 0.0f;
        fArr[i2 + 1] = f3 + (f5 / 2.0f) + 0.0f;
    }

    void l(double d2, int[] iArr, double[] dArr, float[] fArr, double[] dArr2, float[] fArr2) {
        float f2;
        float f3;
        float f4 = this.f2283k;
        float f5 = this.f2284l;
        float f6 = this.f2285m;
        float f7 = this.f2286n;
        float f8 = 0.0f;
        float f9 = 0.0f;
        float f10 = 0.0f;
        float f11 = 0.0f;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            float f12 = (float) dArr[i2];
            float f13 = (float) dArr2[i2];
            int i3 = iArr[i2];
            if (i3 == 1) {
                f4 = f12;
                f8 = f13;
            } else if (i3 == 2) {
                f5 = f12;
                f10 = f13;
            } else if (i3 == 3) {
                f6 = f12;
                f9 = f13;
            } else if (i3 == 4) {
                f7 = f12;
                f11 = f13;
            }
        }
        float f14 = (f9 / 2.0f) + f8;
        float f15 = (f11 / 2.0f) + f10;
        MotionController motionController = this.t;
        if (motionController != null) {
            float[] fArr3 = new float[2];
            float[] fArr4 = new float[2];
            motionController.i(d2, fArr3, fArr4);
            float f16 = fArr3[0];
            float f17 = fArr3[1];
            float f18 = fArr4[0];
            float f19 = fArr4[1];
            double d3 = f4;
            double d4 = f5;
            f2 = f6;
            float sin = (float) ((f16 + (Math.sin(d4) * d3)) - (f6 / 2.0f));
            float cos = (float) ((f17 - (d3 * Math.cos(d4))) - (f7 / 2.0f));
            double d5 = f8;
            double d6 = f10;
            float sin2 = (float) (f18 + (Math.sin(d4) * d5) + (Math.cos(d4) * d6));
            f15 = (float) ((f19 - (d5 * Math.cos(d4))) + (Math.sin(d4) * d6));
            f4 = sin;
            f5 = cos;
            f14 = sin2;
            f3 = 2.0f;
        } else {
            f2 = f6;
            f3 = 2.0f;
        }
        fArr[0] = f4 + (f2 / f3) + 0.0f;
        fArr[1] = f5 + (f7 / f3) + 0.0f;
        fArr2[0] = f14;
        fArr2[1] = f15;
    }

    int n(String str, double[] dArr, int i2) {
        ConstraintAttribute constraintAttribute = (ConstraintAttribute) this.u.get(str);
        int i3 = 0;
        if (constraintAttribute == null) {
            return 0;
        }
        if (constraintAttribute.h() == 1) {
            dArr[i2] = constraintAttribute.e();
            return 1;
        }
        int h2 = constraintAttribute.h();
        constraintAttribute.f(new float[h2]);
        while (i3 < h2) {
            dArr[i2] = r1[i3];
            i3++;
            i2++;
        }
        return h2;
    }

    int o(String str) {
        ConstraintAttribute constraintAttribute = (ConstraintAttribute) this.u.get(str);
        if (constraintAttribute == null) {
            return 0;
        }
        return constraintAttribute.h();
    }

    void p(int[] iArr, double[] dArr, float[] fArr, int i2) {
        float f2 = this.f2283k;
        float f3 = this.f2284l;
        float f4 = this.f2285m;
        float f5 = this.f2286n;
        for (int i3 = 0; i3 < iArr.length; i3++) {
            float f6 = (float) dArr[i3];
            int i4 = iArr[i3];
            if (i4 == 1) {
                f2 = f6;
            } else if (i4 == 2) {
                f3 = f6;
            } else if (i4 == 3) {
                f4 = f6;
            } else if (i4 == 4) {
                f5 = f6;
            }
        }
        MotionController motionController = this.t;
        if (motionController != null) {
            float j2 = motionController.j();
            float k2 = this.t.k();
            double d2 = f2;
            double d3 = f3;
            float sin = (float) ((j2 + (Math.sin(d3) * d2)) - (f4 / 2.0f));
            f3 = (float) ((k2 - (d2 * Math.cos(d3))) - (f5 / 2.0f));
            f2 = sin;
        }
        float f7 = f4 + f2;
        float f8 = f5 + f3;
        Float.isNaN(Float.NaN);
        Float.isNaN(Float.NaN);
        fArr[i2] = f2 + 0.0f;
        fArr[i2 + 1] = f3 + 0.0f;
        fArr[i2 + 2] = f7 + 0.0f;
        fArr[i2 + 3] = f3 + 0.0f;
        fArr[i2 + 4] = f7 + 0.0f;
        fArr[i2 + 5] = f8 + 0.0f;
        fArr[i2 + 6] = f2 + 0.0f;
        fArr[i2 + 7] = f8 + 0.0f;
    }

    boolean r(String str) {
        return this.u.containsKey(str);
    }

    void s(KeyPosition keyPosition, MotionPaths motionPaths, MotionPaths motionPaths2) {
        float f2 = keyPosition.f2123a / 100.0f;
        this.f2281i = f2;
        this.f2280h = keyPosition.f2160j;
        float f3 = Float.isNaN(keyPosition.f2161k) ? f2 : keyPosition.f2161k;
        float f4 = Float.isNaN(keyPosition.f2162l) ? f2 : keyPosition.f2162l;
        float f5 = motionPaths2.f2285m;
        float f6 = motionPaths.f2285m;
        float f7 = f5 - f6;
        float f8 = motionPaths2.f2286n;
        float f9 = motionPaths.f2286n;
        float f10 = f8 - f9;
        this.f2282j = this.f2281i;
        float f11 = (f6 / 2.0f) + motionPaths.f2283k;
        float f12 = motionPaths.f2284l + (f9 / 2.0f);
        float f13 = motionPaths2.f2283k + (f5 / 2.0f);
        float f14 = motionPaths2.f2284l + (f8 / 2.0f);
        if (f11 > f13) {
            f11 = f13;
            f13 = f11;
        }
        if (f12 <= f14) {
            f12 = f14;
            f14 = f12;
        }
        float f15 = f13 - f11;
        float f16 = f12 - f14;
        float f17 = (f7 * f3) / 2.0f;
        this.f2283k = (int) ((r13 + (f15 * f2)) - f17);
        float f18 = (f10 * f4) / 2.0f;
        this.f2284l = (int) ((r1 + (f16 * f2)) - f18);
        this.f2285m = (int) (f6 + r9);
        this.f2286n = (int) (f9 + r12);
        float f19 = Float.isNaN(keyPosition.f2163m) ? f2 : keyPosition.f2163m;
        float f20 = Float.isNaN(keyPosition.f2166p) ? 0.0f : keyPosition.f2166p;
        if (!Float.isNaN(keyPosition.f2164n)) {
            f2 = keyPosition.f2164n;
        }
        float f21 = Float.isNaN(keyPosition.f2165o) ? 0.0f : keyPosition.f2165o;
        this.v = 0;
        this.f2283k = (int) (((motionPaths.f2283k + (f19 * f15)) + (f21 * f16)) - f17);
        this.f2284l = (int) (((motionPaths.f2284l + (f15 * f20)) + (f16 * f2)) - f18);
        this.f2279c = Easing.c(keyPosition.f2158h);
        this.f2289q = keyPosition.f2159i;
    }

    void t(KeyPosition keyPosition, MotionPaths motionPaths, MotionPaths motionPaths2) {
        float f2 = keyPosition.f2123a / 100.0f;
        this.f2281i = f2;
        this.f2280h = keyPosition.f2160j;
        float f3 = Float.isNaN(keyPosition.f2161k) ? f2 : keyPosition.f2161k;
        float f4 = Float.isNaN(keyPosition.f2162l) ? f2 : keyPosition.f2162l;
        float f5 = motionPaths2.f2285m;
        float f6 = motionPaths.f2285m;
        float f7 = motionPaths2.f2286n;
        float f8 = motionPaths.f2286n;
        this.f2282j = this.f2281i;
        float f9 = motionPaths.f2283k;
        float f10 = motionPaths.f2284l;
        float f11 = (motionPaths2.f2283k + (f5 / 2.0f)) - ((f6 / 2.0f) + f9);
        float f12 = (motionPaths2.f2284l + (f7 / 2.0f)) - (f10 + (f8 / 2.0f));
        float f13 = ((f5 - f6) * f3) / 2.0f;
        this.f2283k = (int) ((f9 + (f11 * f2)) - f13);
        float f14 = ((f7 - f8) * f4) / 2.0f;
        this.f2284l = (int) ((f10 + (f12 * f2)) - f14);
        this.f2285m = (int) (f6 + r9);
        this.f2286n = (int) (f8 + r12);
        float f15 = Float.isNaN(keyPosition.f2163m) ? f2 : keyPosition.f2163m;
        float f16 = Float.isNaN(keyPosition.f2166p) ? 0.0f : keyPosition.f2166p;
        if (!Float.isNaN(keyPosition.f2164n)) {
            f2 = keyPosition.f2164n;
        }
        float f17 = Float.isNaN(keyPosition.f2165o) ? 0.0f : keyPosition.f2165o;
        this.v = 0;
        this.f2283k = (int) (((motionPaths.f2283k + (f15 * f11)) + (f17 * f12)) - f13);
        this.f2284l = (int) (((motionPaths.f2284l + (f11 * f16)) + (f12 * f2)) - f14);
        this.f2279c = Easing.c(keyPosition.f2158h);
        this.f2289q = keyPosition.f2159i;
    }

    void u(KeyPosition keyPosition, MotionPaths motionPaths, MotionPaths motionPaths2) {
        float f2 = keyPosition.f2123a / 100.0f;
        this.f2281i = f2;
        this.f2280h = keyPosition.f2160j;
        float f3 = Float.isNaN(keyPosition.f2161k) ? f2 : keyPosition.f2161k;
        float f4 = Float.isNaN(keyPosition.f2162l) ? f2 : keyPosition.f2162l;
        float f5 = motionPaths2.f2285m - motionPaths.f2285m;
        float f6 = motionPaths2.f2286n - motionPaths.f2286n;
        this.f2282j = this.f2281i;
        if (!Float.isNaN(keyPosition.f2163m)) {
            f2 = keyPosition.f2163m;
        }
        float f7 = motionPaths.f2283k;
        float f8 = motionPaths.f2285m;
        float f9 = motionPaths.f2284l;
        float f10 = motionPaths.f2286n;
        float f11 = (motionPaths2.f2283k + (motionPaths2.f2285m / 2.0f)) - ((f8 / 2.0f) + f7);
        float f12 = (motionPaths2.f2284l + (motionPaths2.f2286n / 2.0f)) - ((f10 / 2.0f) + f9);
        float f13 = f11 * f2;
        float f14 = (f5 * f3) / 2.0f;
        this.f2283k = (int) ((f7 + f13) - f14);
        float f15 = f2 * f12;
        float f16 = (f6 * f4) / 2.0f;
        this.f2284l = (int) ((f9 + f15) - f16);
        this.f2285m = (int) (f8 + r7);
        this.f2286n = (int) (f10 + r8);
        float f17 = Float.isNaN(keyPosition.f2164n) ? 0.0f : keyPosition.f2164n;
        this.v = 1;
        float f18 = (int) ((motionPaths.f2283k + f13) - f14);
        float f19 = (int) ((motionPaths.f2284l + f15) - f16);
        this.f2283k = f18 + ((-f12) * f17);
        this.f2284l = f19 + (f11 * f17);
        this.f2290r = this.f2290r;
        this.f2279c = Easing.c(keyPosition.f2158h);
        this.f2289q = keyPosition.f2159i;
    }

    void v(int i2, int i3, KeyPosition keyPosition, MotionPaths motionPaths, MotionPaths motionPaths2) {
        float min;
        float f2;
        float f3 = keyPosition.f2123a / 100.0f;
        this.f2281i = f3;
        this.f2280h = keyPosition.f2160j;
        this.v = keyPosition.f2167q;
        float f4 = Float.isNaN(keyPosition.f2161k) ? f3 : keyPosition.f2161k;
        float f5 = Float.isNaN(keyPosition.f2162l) ? f3 : keyPosition.f2162l;
        float f6 = motionPaths2.f2285m;
        float f7 = motionPaths.f2285m;
        float f8 = motionPaths2.f2286n;
        float f9 = motionPaths.f2286n;
        this.f2282j = this.f2281i;
        this.f2285m = (int) (f7 + ((f6 - f7) * f4));
        this.f2286n = (int) (f9 + ((f8 - f9) * f5));
        if (keyPosition.f2167q != 2) {
            float f10 = Float.isNaN(keyPosition.f2163m) ? f3 : keyPosition.f2163m;
            float f11 = motionPaths2.f2283k;
            float f12 = motionPaths.f2283k;
            this.f2283k = (f10 * (f11 - f12)) + f12;
            if (!Float.isNaN(keyPosition.f2164n)) {
                f3 = keyPosition.f2164n;
            }
            float f13 = motionPaths2.f2284l;
            float f14 = motionPaths.f2284l;
            this.f2284l = (f3 * (f13 - f14)) + f14;
        } else {
            if (Float.isNaN(keyPosition.f2163m)) {
                float f15 = motionPaths2.f2283k;
                float f16 = motionPaths.f2283k;
                min = ((f15 - f16) * f3) + f16;
            } else {
                min = Math.min(f5, f4) * keyPosition.f2163m;
            }
            this.f2283k = min;
            if (Float.isNaN(keyPosition.f2164n)) {
                float f17 = motionPaths2.f2284l;
                float f18 = motionPaths.f2284l;
                f2 = (f3 * (f17 - f18)) + f18;
            } else {
                f2 = keyPosition.f2164n;
            }
            this.f2284l = f2;
        }
        this.f2290r = motionPaths.f2290r;
        this.f2279c = Easing.c(keyPosition.f2158h);
        this.f2289q = keyPosition.f2159i;
    }

    void w(int i2, int i3, KeyPosition keyPosition, MotionPaths motionPaths, MotionPaths motionPaths2) {
        float f2 = keyPosition.f2123a / 100.0f;
        this.f2281i = f2;
        this.f2280h = keyPosition.f2160j;
        float f3 = Float.isNaN(keyPosition.f2161k) ? f2 : keyPosition.f2161k;
        float f4 = Float.isNaN(keyPosition.f2162l) ? f2 : keyPosition.f2162l;
        float f5 = motionPaths2.f2285m;
        float f6 = motionPaths.f2285m;
        float f7 = motionPaths2.f2286n;
        float f8 = motionPaths.f2286n;
        this.f2282j = this.f2281i;
        float f9 = motionPaths.f2283k;
        float f10 = motionPaths.f2284l;
        float f11 = motionPaths2.f2283k + (f5 / 2.0f);
        float f12 = motionPaths2.f2284l + (f7 / 2.0f);
        float f13 = (f5 - f6) * f3;
        this.f2283k = (int) ((f9 + ((f11 - ((f6 / 2.0f) + f9)) * f2)) - (f13 / 2.0f));
        float f14 = (f7 - f8) * f4;
        this.f2284l = (int) ((f10 + ((f12 - (f10 + (f8 / 2.0f))) * f2)) - (f14 / 2.0f));
        this.f2285m = (int) (f6 + f13);
        this.f2286n = (int) (f8 + f14);
        this.v = 2;
        if (!Float.isNaN(keyPosition.f2163m)) {
            this.f2283k = (int) (keyPosition.f2163m * (i2 - ((int) this.f2285m)));
        }
        if (!Float.isNaN(keyPosition.f2164n)) {
            this.f2284l = (int) (keyPosition.f2164n * (i3 - ((int) this.f2286n)));
        }
        this.f2290r = this.f2290r;
        this.f2279c = Easing.c(keyPosition.f2158h);
        this.f2289q = keyPosition.f2159i;
    }

    void x(float f2, float f3, float f4, float f5) {
        this.f2283k = f2;
        this.f2284l = f3;
        this.f2285m = f4;
        this.f2286n = f5;
    }

    void y(float f2, float f3, float[] fArr, int[] iArr, double[] dArr, double[] dArr2) {
        float f4 = 0.0f;
        float f5 = 0.0f;
        float f6 = 0.0f;
        float f7 = 0.0f;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            float f8 = (float) dArr[i2];
            double d2 = dArr2[i2];
            int i3 = iArr[i2];
            if (i3 == 1) {
                f4 = f8;
            } else if (i3 == 2) {
                f6 = f8;
            } else if (i3 == 3) {
                f5 = f8;
            } else if (i3 == 4) {
                f7 = f8;
            }
        }
        float f9 = f4 - ((0.0f * f5) / 2.0f);
        float f10 = f6 - ((0.0f * f7) / 2.0f);
        fArr[0] = (f9 * (1.0f - f2)) + (((f5 * 1.0f) + f9) * f2) + 0.0f;
        fArr[1] = (f10 * (1.0f - f3)) + (((f7 * 1.0f) + f10) * f3) + 0.0f;
    }

    /* JADX WARN: Multi-variable type inference failed */
    void z(float f2, View view, int[] iArr, double[] dArr, double[] dArr2, double[] dArr3, boolean z2) {
        float f3;
        float f4;
        float f5 = this.f2283k;
        float f6 = this.f2284l;
        float f7 = this.f2285m;
        float f8 = this.f2286n;
        if (iArr.length != 0 && this.x.length <= iArr[iArr.length - 1]) {
            int i2 = iArr[iArr.length - 1] + 1;
            this.x = new double[i2];
            this.y = new double[i2];
        }
        Arrays.fill(this.x, Double.NaN);
        for (int i3 = 0; i3 < iArr.length; i3++) {
            double[] dArr4 = this.x;
            int i4 = iArr[i3];
            dArr4[i4] = dArr[i3];
            this.y[i4] = dArr2[i3];
        }
        float f9 = Float.NaN;
        int i5 = 0;
        float f10 = 0.0f;
        float f11 = 0.0f;
        float f12 = 0.0f;
        float f13 = 0.0f;
        while (true) {
            double[] dArr5 = this.x;
            if (i5 >= dArr5.length) {
                break;
            }
            if (Double.isNaN(dArr5[i5]) && (dArr3 == null || dArr3[i5] == 0.0d)) {
                f4 = f9;
            } else {
                double d2 = dArr3 != null ? dArr3[i5] : 0.0d;
                if (!Double.isNaN(this.x[i5])) {
                    d2 = this.x[i5] + d2;
                }
                f4 = f9;
                float f14 = (float) d2;
                float f15 = (float) this.y[i5];
                if (i5 == 1) {
                    f9 = f4;
                    f5 = f14;
                    f10 = f15;
                } else if (i5 == 2) {
                    f9 = f4;
                    f6 = f14;
                    f11 = f15;
                } else if (i5 == 3) {
                    f9 = f4;
                    f7 = f14;
                    f12 = f15;
                } else if (i5 == 4) {
                    f9 = f4;
                    f8 = f14;
                    f13 = f15;
                } else if (i5 == 5) {
                    f9 = f14;
                }
                i5++;
            }
            f9 = f4;
            i5++;
        }
        float f16 = f9;
        MotionController motionController = this.t;
        if (motionController != null) {
            float[] fArr = new float[2];
            float[] fArr2 = new float[2];
            motionController.i(f2, fArr, fArr2);
            float f17 = fArr[0];
            float f18 = fArr[1];
            float f19 = fArr2[0];
            float f20 = fArr2[1];
            double d3 = f5;
            double d4 = f6;
            float sin = (float) ((f17 + (Math.sin(d4) * d3)) - (f7 / 2.0f));
            f3 = f8;
            float cos = (float) ((f18 - (Math.cos(d4) * d3)) - (f8 / 2.0f));
            double d5 = f10;
            double d6 = f11;
            float sin2 = (float) (f19 + (Math.sin(d4) * d5) + (Math.cos(d4) * d3 * d6));
            float cos2 = (float) ((f20 - (d5 * Math.cos(d4))) + (d3 * Math.sin(d4) * d6));
            if (dArr2.length >= 2) {
                dArr2[0] = sin2;
                dArr2[1] = cos2;
            }
            if (!Float.isNaN(f16)) {
                view.setRotation((float) (f16 + Math.toDegrees(Math.atan2(cos2, sin2))));
            }
            f5 = sin;
            f6 = cos;
        } else {
            f3 = f8;
            if (!Float.isNaN(f16)) {
                view.setRotation(f16 + ((float) Math.toDegrees(Math.atan2(f11 + (f13 / 2.0f), f10 + (f12 / 2.0f)))) + 0.0f);
            }
        }
        if (view instanceof FloatLayout) {
            ((FloatLayout) view).a(f5, f6, f7 + f5, f6 + f3);
            return;
        }
        float f21 = f5 + 0.5f;
        int i6 = (int) f21;
        float f22 = f6 + 0.5f;
        int i7 = (int) f22;
        int i8 = (int) (f21 + f7);
        int i9 = (int) (f22 + f3);
        int i10 = i8 - i6;
        int i11 = i9 - i7;
        if (i10 != view.getMeasuredWidth() || i11 != view.getMeasuredHeight() || z2) {
            view.measure(View.MeasureSpec.makeMeasureSpec(i10, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME), View.MeasureSpec.makeMeasureSpec(i11, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME));
        }
        view.layout(i6, i7, i8, i9);
    }

    MotionPaths(int i2, int i3, KeyPosition keyPosition, MotionPaths motionPaths, MotionPaths motionPaths2) {
        int i4 = Key.f2122f;
        this.f2289q = i4;
        this.f2290r = i4;
        this.f2291s = Float.NaN;
        this.t = null;
        this.u = new LinkedHashMap();
        this.v = 0;
        this.x = new double[18];
        this.y = new double[18];
        if (motionPaths.f2290r != Key.f2122f) {
            v(i2, i3, keyPosition, motionPaths, motionPaths2);
            return;
        }
        int i5 = keyPosition.f2167q;
        if (i5 == 1) {
            u(keyPosition, motionPaths, motionPaths2);
            return;
        }
        if (i5 == 2) {
            w(i2, i3, keyPosition, motionPaths, motionPaths2);
        } else if (i5 != 3) {
            t(keyPosition, motionPaths, motionPaths2);
        } else {
            s(keyPosition, motionPaths, motionPaths2);
        }
    }
}
