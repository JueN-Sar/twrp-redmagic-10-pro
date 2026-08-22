package androidx.constraintlayout.core.motion.utils;

import java.util.Arrays;

/* loaded from: classes.dex */
public class Easing {

    /* renamed from: b, reason: collision with root package name */
    static Easing f1762b = new Easing();

    /* renamed from: c, reason: collision with root package name */
    public static String[] f1763c = {"standard", "accelerate", "decelerate", "linear"};

    /* renamed from: a, reason: collision with root package name */
    String f1764a = "identity";

    static class CubicEasing extends Easing {

        /* renamed from: h, reason: collision with root package name */
        private static double f1765h = 0.01d;

        /* renamed from: i, reason: collision with root package name */
        private static double f1766i = 1.0E-4d;

        /* renamed from: d, reason: collision with root package name */
        double f1767d;

        /* renamed from: e, reason: collision with root package name */
        double f1768e;

        /* renamed from: f, reason: collision with root package name */
        double f1769f;

        /* renamed from: g, reason: collision with root package name */
        double f1770g;

        CubicEasing(String str) {
            this.f1764a = str;
            int indexOf = str.indexOf(40);
            int indexOf2 = str.indexOf(44, indexOf);
            this.f1767d = Double.parseDouble(str.substring(indexOf + 1, indexOf2).trim());
            int i2 = indexOf2 + 1;
            int indexOf3 = str.indexOf(44, i2);
            this.f1768e = Double.parseDouble(str.substring(i2, indexOf3).trim());
            int i3 = indexOf3 + 1;
            int indexOf4 = str.indexOf(44, i3);
            this.f1769f = Double.parseDouble(str.substring(i3, indexOf4).trim());
            int i4 = indexOf4 + 1;
            this.f1770g = Double.parseDouble(str.substring(i4, str.indexOf(41, i4)).trim());
        }

        private double d(double d2) {
            double d3 = 1.0d - d2;
            double d4 = 3.0d * d3;
            return (this.f1767d * d3 * d4 * d2) + (this.f1769f * d4 * d2 * d2) + (d2 * d2 * d2);
        }

        private double e(double d2) {
            double d3 = 1.0d - d2;
            double d4 = 3.0d * d3;
            return (this.f1768e * d3 * d4 * d2) + (this.f1770g * d4 * d2 * d2) + (d2 * d2 * d2);
        }

        @Override // androidx.constraintlayout.core.motion.utils.Easing
        public double a(double d2) {
            if (d2 <= 0.0d) {
                return 0.0d;
            }
            if (d2 >= 1.0d) {
                return 1.0d;
            }
            double d3 = 0.5d;
            double d4 = 0.5d;
            while (d3 > f1765h) {
                d3 *= 0.5d;
                d4 = d(d4) < d2 ? d4 + d3 : d4 - d3;
            }
            double d5 = d4 - d3;
            double d6 = d(d5);
            double d7 = d4 + d3;
            double d8 = d(d7);
            double e2 = e(d5);
            return (((e(d7) - e2) * (d2 - d6)) / (d8 - d6)) + e2;
        }

        @Override // androidx.constraintlayout.core.motion.utils.Easing
        public double b(double d2) {
            double d3 = 0.5d;
            double d4 = 0.5d;
            while (d3 > f1766i) {
                d3 *= 0.5d;
                d4 = d(d4) < d2 ? d4 + d3 : d4 - d3;
            }
            double d5 = d4 - d3;
            double d6 = d4 + d3;
            return (e(d6) - e(d5)) / (d(d6) - d(d5));
        }
    }

    public static Easing c(String str) {
        if (str == null) {
            return null;
        }
        if (str.startsWith("cubic")) {
            return new CubicEasing(str);
        }
        if (str.startsWith("spline")) {
            return new StepCurve(str);
        }
        if (str.startsWith("Schlick")) {
            return new Schlick(str);
        }
        switch (str) {
            case "accelerate":
                return new CubicEasing("cubic(0.4, 0.05, 0.8, 0.7)");
            case "decelerate":
                return new CubicEasing("cubic(0.0, 0.0, 0.2, 0.95)");
            case "anticipate":
                return new CubicEasing("cubic(0.36, 0, 0.66, -0.56)");
            case "linear":
                return new CubicEasing("cubic(1, 1, 0, 0)");
            case "overshoot":
                return new CubicEasing("cubic(0.34, 1.56, 0.64, 1)");
            case "standard":
                return new CubicEasing("cubic(0.4, 0.0, 0.2, 1)");
            default:
                System.err.println("transitionEasing syntax error syntax:transitionEasing=\"cubic(1.0,0.5,0.0,0.6)\" or " + Arrays.toString(f1763c));
                return f1762b;
        }
    }

    public double a(double d2) {
        return d2;
    }

    public double b(double d2) {
        return 1.0d;
    }

    public String toString() {
        return this.f1764a;
    }
}
