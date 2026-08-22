package androidx.constraintlayout.core.motion.utils;

import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.Arrays;

/* loaded from: classes.dex */
public class StepCurve extends Easing {

    /* renamed from: d, reason: collision with root package name */
    MonotonicCurveFit f1847d;

    StepCurve(String str) {
        this.f1764a = str;
        double[] dArr = new double[str.length() / 2];
        int indexOf = str.indexOf(40) + 1;
        int indexOf2 = str.indexOf(44, indexOf);
        int i2 = 0;
        while (indexOf2 != -1) {
            dArr[i2] = Double.parseDouble(str.substring(indexOf, indexOf2).trim());
            indexOf = indexOf2 + 1;
            indexOf2 = str.indexOf(44, indexOf);
            i2++;
        }
        dArr[i2] = Double.parseDouble(str.substring(indexOf, str.indexOf(41, indexOf)).trim());
        this.f1847d = d(Arrays.copyOf(dArr, i2 + 1));
    }

    private static MonotonicCurveFit d(double[] dArr) {
        int length = (dArr.length * 3) - 2;
        int length2 = dArr.length - 1;
        double d2 = 1.0d / length2;
        double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, length, 1);
        double[] dArr3 = new double[length];
        for (int i2 = 0; i2 < dArr.length; i2++) {
            double d3 = dArr[i2];
            int i3 = i2 + length2;
            dArr2[i3][0] = d3;
            double d4 = i2 * d2;
            dArr3[i3] = d4;
            if (i2 > 0) {
                int i4 = (length2 * 2) + i2;
                dArr2[i4][0] = d3 + 1.0d;
                dArr3[i4] = d4 + 1.0d;
                int i5 = i2 - 1;
                dArr2[i5][0] = (d3 - 1.0d) - d2;
                dArr3[i5] = (d4 - 1.0d) - d2;
            }
        }
        MonotonicCurveFit monotonicCurveFit = new MonotonicCurveFit(dArr3, dArr2);
        PrintStream printStream = System.out;
        printStream.println(" 0 " + monotonicCurveFit.c(0.0d, 0));
        printStream.println(" 1 " + monotonicCurveFit.c(1.0d, 0));
        return monotonicCurveFit;
    }

    @Override // androidx.constraintlayout.core.motion.utils.Easing
    public double a(double d2) {
        return this.f1847d.c(d2, 0);
    }

    @Override // androidx.constraintlayout.core.motion.utils.Easing
    public double b(double d2) {
        return this.f1847d.f(d2, 0);
    }
}
