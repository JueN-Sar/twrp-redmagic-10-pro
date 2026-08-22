package androidx.constraintlayout.core.motion.utils;

import androidx.constraintlayout.core.motion.CustomAttribute;
import androidx.constraintlayout.core.motion.CustomVariable;
import androidx.constraintlayout.core.motion.utils.KeyFrameArray;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.Arrays;

/* loaded from: classes.dex */
public abstract class SplineSet {

    /* renamed from: a, reason: collision with root package name */
    protected CurveFit f1827a;

    /* renamed from: b, reason: collision with root package name */
    protected int[] f1828b = new int[10];

    /* renamed from: c, reason: collision with root package name */
    protected float[] f1829c = new float[10];

    /* renamed from: d, reason: collision with root package name */
    private int f1830d;

    /* renamed from: e, reason: collision with root package name */
    private String f1831e;

    private static class CoreSpline extends SplineSet {
    }

    public static class CustomSet extends SplineSet {

        /* renamed from: f, reason: collision with root package name */
        KeyFrameArray.CustomArray f1832f;

        /* renamed from: g, reason: collision with root package name */
        float[] f1833g;

        @Override // androidx.constraintlayout.core.motion.utils.SplineSet
        public void c(int i2, float f2) {
            throw new RuntimeException("don't call for custom attribute call setPoint(pos, ConstraintAttribute)");
        }

        @Override // androidx.constraintlayout.core.motion.utils.SplineSet
        public void e(int i2) {
            int b2 = this.f1832f.b();
            int b3 = this.f1832f.c(0).b();
            double[] dArr = new double[b2];
            this.f1833g = new float[b3];
            double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, b2, b3);
            for (int i3 = 0; i3 < b2; i3++) {
                int a2 = this.f1832f.a(i3);
                CustomAttribute c2 = this.f1832f.c(i3);
                dArr[i3] = a2 * 0.01d;
                c2.a(this.f1833g);
                int i4 = 0;
                while (true) {
                    if (i4 < this.f1833g.length) {
                        dArr2[i3][i4] = r6[i4];
                        i4++;
                    }
                }
            }
            this.f1827a = CurveFit.a(i2, dArr, dArr2);
        }
    }

    public static class CustomSpline extends SplineSet {

        /* renamed from: f, reason: collision with root package name */
        KeyFrameArray.CustomVar f1834f;

        /* renamed from: g, reason: collision with root package name */
        float[] f1835g;

        @Override // androidx.constraintlayout.core.motion.utils.SplineSet
        public void c(int i2, float f2) {
            throw new RuntimeException("don't call for custom attribute call setPoint(pos, ConstraintAttribute)");
        }

        @Override // androidx.constraintlayout.core.motion.utils.SplineSet
        public void e(int i2) {
            int b2 = this.f1834f.b();
            int c2 = this.f1834f.c(0).c();
            double[] dArr = new double[b2];
            this.f1835g = new float[c2];
            double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, b2, c2);
            for (int i3 = 0; i3 < b2; i3++) {
                int a2 = this.f1834f.a(i3);
                CustomVariable c3 = this.f1834f.c(i3);
                dArr[i3] = a2 * 0.01d;
                c3.b(this.f1835g);
                int i4 = 0;
                while (true) {
                    if (i4 < this.f1835g.length) {
                        dArr2[i3][i4] = r6[i4];
                        i4++;
                    }
                }
            }
            this.f1827a = CurveFit.a(i2, dArr, dArr2);
        }
    }

    private static class Sort {
        static void a(int[] iArr, float[] fArr, int i2, int i3) {
            int[] iArr2 = new int[iArr.length + 10];
            iArr2[0] = i3;
            iArr2[1] = i2;
            int i4 = 2;
            while (i4 > 0) {
                int i5 = iArr2[i4 - 1];
                int i6 = i4 - 2;
                int i7 = iArr2[i6];
                if (i5 < i7) {
                    int b2 = b(iArr, fArr, i5, i7);
                    iArr2[i6] = b2 - 1;
                    iArr2[i4 - 1] = i5;
                    int i8 = i4 + 1;
                    iArr2[i4] = i7;
                    i4 += 2;
                    iArr2[i8] = b2 + 1;
                } else {
                    i4 = i6;
                }
            }
        }

        private static int b(int[] iArr, float[] fArr, int i2, int i3) {
            int i4 = iArr[i3];
            int i5 = i2;
            while (i2 < i3) {
                if (iArr[i2] <= i4) {
                    c(iArr, fArr, i5, i2);
                    i5++;
                }
                i2++;
            }
            c(iArr, fArr, i5, i3);
            return i5;
        }

        private static void c(int[] iArr, float[] fArr, int i2, int i3) {
            int i4 = iArr[i2];
            iArr[i2] = iArr[i3];
            iArr[i3] = i4;
            float f2 = fArr[i2];
            fArr[i2] = fArr[i3];
            fArr[i3] = f2;
        }
    }

    public float a(float f2) {
        return (float) this.f1827a.c(f2, 0);
    }

    public float b(float f2) {
        return (float) this.f1827a.f(f2, 0);
    }

    public void c(int i2, float f2) {
        int[] iArr = this.f1828b;
        if (iArr.length < this.f1830d + 1) {
            this.f1828b = Arrays.copyOf(iArr, iArr.length * 2);
            float[] fArr = this.f1829c;
            this.f1829c = Arrays.copyOf(fArr, fArr.length * 2);
        }
        int[] iArr2 = this.f1828b;
        int i3 = this.f1830d;
        iArr2[i3] = i2;
        this.f1829c[i3] = f2;
        this.f1830d = i3 + 1;
    }

    public void d(String str) {
        this.f1831e = str;
    }

    public void e(int i2) {
        int i3;
        int i4 = this.f1830d;
        if (i4 == 0) {
            return;
        }
        Sort.a(this.f1828b, this.f1829c, 0, i4 - 1);
        int i5 = 1;
        for (int i6 = 1; i6 < this.f1830d; i6++) {
            int[] iArr = this.f1828b;
            if (iArr[i6 - 1] != iArr[i6]) {
                i5++;
            }
        }
        double[] dArr = new double[i5];
        double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, i5, 1);
        int i7 = 0;
        for (0; i3 < this.f1830d; i3 + 1) {
            if (i3 > 0) {
                int[] iArr2 = this.f1828b;
                i3 = iArr2[i3] == iArr2[i3 - 1] ? i3 + 1 : 0;
            }
            dArr[i7] = this.f1828b[i3] * 0.01d;
            dArr2[i7][0] = this.f1829c[i3];
            i7++;
        }
        this.f1827a = CurveFit.a(i2, dArr, dArr2);
    }

    public String toString() {
        String str = this.f1831e;
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        for (int i2 = 0; i2 < this.f1830d; i2++) {
            str = str + "[" + this.f1828b[i2] + " , " + decimalFormat.format(this.f1829c[i2]) + "] ";
        }
        return str;
    }
}
