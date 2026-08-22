package com.google.android.material.color.utilities;

import androidx.annotation.RestrictTo;

@RestrictTo
/* loaded from: classes.dex */
public class ColorUtils {

    /* renamed from: a, reason: collision with root package name */
    static final double[][] f14306a = {new double[]{0.41233895d, 0.35762064d, 0.18051042d}, new double[]{0.2126d, 0.7152d, 0.0722d}, new double[]{0.01932141d, 0.11916382d, 0.95034478d}};

    /* renamed from: b, reason: collision with root package name */
    static final double[][] f14307b = {new double[]{3.2413774792388685d, -1.5376652402851851d, -0.49885366846268053d}, new double[]{-0.9691452513005321d, 1.8758853451067872d, 0.04156585616912061d}, new double[]{0.05562093689691305d, -0.20395524564742123d, 1.0571799111220335d}};

    /* renamed from: c, reason: collision with root package name */
    static final double[] f14308c = {95.047d, 100.0d, 108.883d};

    public static int a(double[] dArr) {
        return c(e(dArr[0]), e(dArr[1]), e(dArr[2]));
    }

    public static int b(double d2) {
        int e2 = e(p(d2));
        return c(e2, e2, e2);
    }

    public static int c(int i2, int i3, int i4) {
        return ((i2 & 255) << 16) | (-16777216) | ((i3 & 255) << 8) | (i4 & 255);
    }

    public static int d(int i2) {
        return i2 & 255;
    }

    public static int e(double d2) {
        double d3 = d2 / 100.0d;
        return MathUtils.b(0, 255, (int) Math.round((d3 <= 0.0031308d ? d3 * 12.92d : (Math.pow(d3, 0.4166666666666667d) * 1.055d) - 0.055d) * 255.0d));
    }

    public static int f(int i2) {
        return (i2 >> 8) & 255;
    }

    static double g(double d2) {
        return d2 > 0.008856451679035631d ? Math.pow(d2, 0.3333333333333333d) : ((d2 * 903.2962962962963d) + 16.0d) / 116.0d;
    }

    public static double[] h(int i2) {
        double j2 = j(m(i2));
        double j3 = j(f(i2));
        double j4 = j(d(i2));
        double[][] dArr = f14306a;
        double[] dArr2 = dArr[0];
        double d2 = (dArr2[0] * j2) + (dArr2[1] * j3) + (dArr2[2] * j4);
        double[] dArr3 = dArr[1];
        double d3 = (dArr3[0] * j2) + (dArr3[1] * j3) + (dArr3[2] * j4);
        double[] dArr4 = dArr[2];
        double d4 = (dArr4[0] * j2) + (dArr4[1] * j3) + (dArr4[2] * j4);
        double[] dArr5 = f14308c;
        double d5 = d2 / dArr5[0];
        double d6 = d3 / dArr5[1];
        double d7 = d4 / dArr5[2];
        double g2 = g(d5);
        double g3 = g(d6);
        return new double[]{(116.0d * g3) - 16.0d, (g2 - g3) * 500.0d, (g3 - g(d7)) * 200.0d};
    }

    static double i(double d2) {
        double d3 = d2 * d2 * d2;
        return d3 > 0.008856451679035631d ? d3 : ((d2 * 116.0d) - 16.0d) / 903.2962962962963d;
    }

    public static double j(int i2) {
        double d2 = i2 / 255.0d;
        return (d2 <= 0.040449936d ? d2 / 12.92d : Math.pow((d2 + 0.055d) / 1.055d, 2.4d)) * 100.0d;
    }

    public static double k(int i2) {
        return (g(o(i2)[1] / 100.0d) * 116.0d) - 16.0d;
    }

    public static double l(double d2) {
        return (g(d2 / 100.0d) * 116.0d) - 16.0d;
    }

    public static int m(int i2) {
        return (i2 >> 16) & 255;
    }

    public static double[] n() {
        return f14308c;
    }

    public static double[] o(int i2) {
        return MathUtils.d(new double[]{j(m(i2)), j(f(i2)), j(d(i2))}, f14306a);
    }

    public static double p(double d2) {
        return i((d2 + 16.0d) / 116.0d) * 100.0d;
    }
}
