package com.google.android.material.color.utilities;

import androidx.annotation.RestrictTo;

@RestrictTo
/* loaded from: classes.dex */
public final class Contrast {
    public static double a(double d2, double d3) {
        if (d2 >= 0.0d && d2 <= 100.0d) {
            double p2 = ColorUtils.p(d2);
            double d4 = ((p2 + 5.0d) / d3) - 5.0d;
            if (d4 >= 0.0d && d4 <= 100.0d) {
                double f2 = f(p2, d4);
                double abs = Math.abs(f2 - d3);
                if (f2 < d3 && abs > 0.04d) {
                    return -1.0d;
                }
                double l2 = ColorUtils.l(d4) - 0.4d;
                if (l2 >= 0.0d && l2 <= 100.0d) {
                    return l2;
                }
            }
        }
        return -1.0d;
    }

    public static double b(double d2, double d3) {
        return Math.max(0.0d, a(d2, d3));
    }

    public static double c(double d2, double d3) {
        if (d2 >= 0.0d && d2 <= 100.0d) {
            double p2 = ColorUtils.p(d2);
            double d4 = ((p2 + 5.0d) * d3) - 5.0d;
            if (d4 >= 0.0d && d4 <= 100.0d) {
                double f2 = f(d4, p2);
                double abs = Math.abs(f2 - d3);
                if (f2 < d3 && abs > 0.04d) {
                    return -1.0d;
                }
                double l2 = ColorUtils.l(d4) + 0.4d;
                if (l2 >= 0.0d && l2 <= 100.0d) {
                    return l2;
                }
            }
        }
        return -1.0d;
    }

    public static double d(double d2, double d3) {
        double c2 = c(d2, d3);
        if (c2 < 0.0d) {
            return 100.0d;
        }
        return c2;
    }

    public static double e(double d2, double d3) {
        return f(ColorUtils.p(d2), ColorUtils.p(d3));
    }

    public static double f(double d2, double d3) {
        double max = Math.max(d2, d3);
        if (max != d3) {
            d2 = d3;
        }
        return (max + 5.0d) / (d2 + 5.0d);
    }
}
