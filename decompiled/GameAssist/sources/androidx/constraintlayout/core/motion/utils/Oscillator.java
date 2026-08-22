package androidx.constraintlayout.core.motion.utils;

import java.util.Arrays;

/* loaded from: classes.dex */
public class Oscillator {

    /* renamed from: c, reason: collision with root package name */
    double[] f1819c;

    /* renamed from: d, reason: collision with root package name */
    String f1820d;

    /* renamed from: e, reason: collision with root package name */
    MonotonicCurveFit f1821e;

    /* renamed from: f, reason: collision with root package name */
    int f1822f;

    /* renamed from: a, reason: collision with root package name */
    float[] f1817a = new float[0];

    /* renamed from: b, reason: collision with root package name */
    double[] f1818b = new double[0];

    /* renamed from: g, reason: collision with root package name */
    double f1823g = 6.283185307179586d;

    /* renamed from: h, reason: collision with root package name */
    private boolean f1824h = false;

    public void a(double d2, float f2) {
        int length = this.f1817a.length + 1;
        int binarySearch = Arrays.binarySearch(this.f1818b, d2);
        if (binarySearch < 0) {
            binarySearch = (-binarySearch) - 1;
        }
        this.f1818b = Arrays.copyOf(this.f1818b, length);
        this.f1817a = Arrays.copyOf(this.f1817a, length);
        this.f1819c = new double[length];
        double[] dArr = this.f1818b;
        System.arraycopy(dArr, binarySearch, dArr, binarySearch + 1, (length - binarySearch) - 1);
        this.f1818b[binarySearch] = d2;
        this.f1817a[binarySearch] = f2;
        this.f1824h = false;
    }

    double b(double d2) {
        if (d2 <= 0.0d) {
            return 0.0d;
        }
        if (d2 >= 1.0d) {
            return 1.0d;
        }
        int binarySearch = Arrays.binarySearch(this.f1818b, d2);
        if (binarySearch < 0) {
            binarySearch = (-binarySearch) - 1;
        }
        float[] fArr = this.f1817a;
        float f2 = fArr[binarySearch];
        int i2 = binarySearch - 1;
        float f3 = fArr[i2];
        double d3 = f2 - f3;
        double[] dArr = this.f1818b;
        double d4 = dArr[binarySearch];
        double d5 = dArr[i2];
        double d6 = d3 / (d4 - d5);
        return (d2 * d6) + (f3 - (d6 * d5));
    }

    double c(double d2) {
        if (d2 <= 0.0d) {
            return 0.0d;
        }
        if (d2 >= 1.0d) {
            return 1.0d;
        }
        int binarySearch = Arrays.binarySearch(this.f1818b, d2);
        if (binarySearch < 0) {
            binarySearch = (-binarySearch) - 1;
        }
        float[] fArr = this.f1817a;
        float f2 = fArr[binarySearch];
        int i2 = binarySearch - 1;
        float f3 = fArr[i2];
        double d3 = f2 - f3;
        double[] dArr = this.f1818b;
        double d4 = dArr[binarySearch];
        double d5 = dArr[i2];
        double d6 = d3 / (d4 - d5);
        return this.f1819c[i2] + ((f3 - (d6 * d5)) * (d2 - d5)) + ((d6 * ((d2 * d2) - (d5 * d5))) / 2.0d);
    }

    public double d(double d2, double d3, double d4) {
        double d5;
        double signum;
        double c2 = d3 + c(d2);
        double b2 = b(d2) + d4;
        switch (this.f1822f) {
            case 1:
                return 0.0d;
            case 2:
                d5 = b2 * 4.0d;
                signum = Math.signum((((c2 * 4.0d) + 3.0d) % 4.0d) - 2.0d);
                break;
            case 3:
                return b2 * 2.0d;
            case 4:
                return (-b2) * 2.0d;
            case 5:
                double d6 = this.f1823g;
                return (-d6) * b2 * Math.sin(d6 * c2);
            case 6:
                d5 = b2 * 4.0d;
                signum = (((c2 * 4.0d) + 2.0d) % 4.0d) - 2.0d;
                break;
            case 7:
                return this.f1821e.f(c2 % 1.0d, 0);
            default:
                double d7 = this.f1823g;
                d5 = b2 * d7;
                signum = Math.cos(d7 * c2);
                break;
        }
        return d5 * signum;
    }

    public double e(double d2, double d3) {
        double c2 = c(d2) + d3;
        switch (this.f1822f) {
            case 1:
                return Math.signum(0.5d - (c2 % 1.0d));
            case 2:
                return 1.0d - Math.abs((((c2 * 4.0d) + 1.0d) % 4.0d) - 2.0d);
            case 3:
                return (((c2 * 2.0d) + 1.0d) % 2.0d) - 1.0d;
            case 4:
                return 1.0d - (((c2 * 2.0d) + 1.0d) % 2.0d);
            case 5:
                return Math.cos(this.f1823g * (d3 + c2));
            case 6:
                double abs = 1.0d - Math.abs(((c2 * 4.0d) % 4.0d) - 2.0d);
                return 1.0d - (abs * abs);
            case 7:
                return this.f1821e.c(c2 % 1.0d, 0);
            default:
                return Math.sin(this.f1823g * c2);
        }
    }

    public void f() {
        double d2 = 0.0d;
        int i2 = 0;
        while (true) {
            if (i2 >= this.f1817a.length) {
                break;
            }
            d2 += r6[i2];
            i2++;
        }
        double d3 = 0.0d;
        int i3 = 1;
        while (true) {
            float[] fArr = this.f1817a;
            if (i3 >= fArr.length) {
                break;
            }
            int i4 = i3 - 1;
            float f2 = (fArr[i4] + fArr[i3]) / 2.0f;
            double[] dArr = this.f1818b;
            d3 += (dArr[i3] - dArr[i4]) * f2;
            i3++;
        }
        int i5 = 0;
        while (true) {
            float[] fArr2 = this.f1817a;
            if (i5 >= fArr2.length) {
                break;
            }
            fArr2[i5] = fArr2[i5] * ((float) (d2 / d3));
            i5++;
        }
        this.f1819c[0] = 0.0d;
        int i6 = 1;
        while (true) {
            float[] fArr3 = this.f1817a;
            if (i6 >= fArr3.length) {
                this.f1824h = true;
                return;
            }
            int i7 = i6 - 1;
            float f3 = (fArr3[i7] + fArr3[i6]) / 2.0f;
            double[] dArr2 = this.f1818b;
            double d4 = dArr2[i6] - dArr2[i7];
            double[] dArr3 = this.f1819c;
            dArr3[i6] = dArr3[i7] + (d4 * f3);
            i6++;
        }
    }

    public void g(int i2, String str) {
        this.f1822f = i2;
        this.f1820d = str;
        if (str != null) {
            this.f1821e = MonotonicCurveFit.i(str);
        }
    }

    public String toString() {
        return "pos =" + Arrays.toString(this.f1818b) + " period=" + Arrays.toString(this.f1817a);
    }
}
