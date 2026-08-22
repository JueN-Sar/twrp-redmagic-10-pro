package androidx.constraintlayout.core.motion.utils;

import java.lang.reflect.Array;
import java.text.DecimalFormat;

/* loaded from: classes.dex */
public abstract class TimeCycleSplineSet {

    /* renamed from: k, reason: collision with root package name */
    protected static float f1871k = 6.2831855f;

    /* renamed from: a, reason: collision with root package name */
    protected CurveFit f1872a;

    /* renamed from: e, reason: collision with root package name */
    protected int f1876e;

    /* renamed from: f, reason: collision with root package name */
    protected String f1877f;

    /* renamed from: i, reason: collision with root package name */
    protected long f1880i;

    /* renamed from: b, reason: collision with root package name */
    protected int f1873b = 0;

    /* renamed from: c, reason: collision with root package name */
    protected int[] f1874c = new int[10];

    /* renamed from: d, reason: collision with root package name */
    protected float[][] f1875d = (float[][]) Array.newInstance((Class<?>) Float.TYPE, 10, 3);

    /* renamed from: g, reason: collision with root package name */
    protected float[] f1878g = new float[3];

    /* renamed from: h, reason: collision with root package name */
    protected boolean f1879h = false;

    /* renamed from: j, reason: collision with root package name */
    protected float f1881j = Float.NaN;

    public static class CustomSet extends TimeCycleSplineSet {
    }

    public static class CustomVarSet extends TimeCycleSplineSet {
    }

    protected static class Sort {
        static void a(int[] iArr, float[][] fArr, int i2, int i3) {
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

        private static int b(int[] iArr, float[][] fArr, int i2, int i3) {
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

        private static void c(int[] iArr, float[][] fArr, int i2, int i3) {
            int i4 = iArr[i2];
            iArr[i2] = iArr[i3];
            iArr[i3] = i4;
            float[] fArr2 = fArr[i2];
            fArr[i2] = fArr[i3];
            fArr[i3] = fArr2;
        }
    }

    protected float a(float f2) {
        switch (this.f1873b) {
            case 1:
                return Math.signum(f2 * f1871k);
            case 2:
                return 1.0f - Math.abs(f2);
            case 3:
                return (((f2 * 2.0f) + 1.0f) % 2.0f) - 1.0f;
            case 4:
                return 1.0f - (((f2 * 2.0f) + 1.0f) % 2.0f);
            case 5:
                return (float) Math.cos(f2 * f1871k);
            case 6:
                float abs = 1.0f - Math.abs(((f2 * 4.0f) % 4.0f) - 2.0f);
                return 1.0f - (abs * abs);
            default:
                return (float) Math.sin(f2 * f1871k);
        }
    }

    public void b(int i2, float f2, float f3, int i3, float f4) {
        int[] iArr = this.f1874c;
        int i4 = this.f1876e;
        iArr[i4] = i2;
        float[] fArr = this.f1875d[i4];
        fArr[0] = f2;
        fArr[1] = f3;
        fArr[2] = f4;
        this.f1873b = Math.max(this.f1873b, i3);
        this.f1876e++;
    }

    protected void c(long j2) {
        this.f1880i = j2;
    }

    public void d(String str) {
        this.f1877f = str;
    }

    public void e(int i2) {
        int i3;
        int i4 = this.f1876e;
        if (i4 == 0) {
            System.err.println("Error no points added to " + this.f1877f);
            return;
        }
        Sort.a(this.f1874c, this.f1875d, 0, i4 - 1);
        int i5 = 1;
        int i6 = 0;
        while (true) {
            int[] iArr = this.f1874c;
            if (i5 >= iArr.length) {
                break;
            }
            if (iArr[i5] != iArr[i5 - 1]) {
                i6++;
            }
            i5++;
        }
        if (i6 == 0) {
            i6 = 1;
        }
        double[] dArr = new double[i6];
        double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, i6, 3);
        int i7 = 0;
        for (0; i3 < this.f1876e; i3 + 1) {
            if (i3 > 0) {
                int[] iArr2 = this.f1874c;
                i3 = iArr2[i3] == iArr2[i3 - 1] ? i3 + 1 : 0;
            }
            dArr[i7] = this.f1874c[i3] * 0.01d;
            double[] dArr3 = dArr2[i7];
            float[] fArr = this.f1875d[i3];
            dArr3[0] = fArr[0];
            dArr3[1] = fArr[1];
            dArr3[2] = fArr[2];
            i7++;
        }
        this.f1872a = CurveFit.a(i2, dArr, dArr2);
    }

    public String toString() {
        String str = this.f1877f;
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        for (int i2 = 0; i2 < this.f1876e; i2++) {
            str = str + "[" + this.f1874c[i2] + " , " + decimalFormat.format(this.f1875d[i2]) + "] ";
        }
        return str;
    }
}
