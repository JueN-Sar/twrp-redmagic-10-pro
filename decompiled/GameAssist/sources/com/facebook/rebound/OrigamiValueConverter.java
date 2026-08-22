package com.facebook.rebound;

/* loaded from: classes.dex */
public class OrigamiValueConverter {
    public static double a(double d2) {
        if (d2 == 0.0d) {
            return 0.0d;
        }
        return 25.0d + ((d2 - 8.0d) * 3.0d);
    }

    public static double b(double d2) {
        if (d2 == 0.0d) {
            return 0.0d;
        }
        return 8.0d + ((d2 - 25.0d) / 3.0d);
    }

    public static double c(double d2) {
        if (d2 == 0.0d) {
            return 0.0d;
        }
        return 30.0d + ((d2 - 194.0d) / 3.62d);
    }

    public static double d(double d2) {
        if (d2 == 0.0d) {
            return 0.0d;
        }
        return 194.0d + ((d2 - 30.0d) * 3.62d);
    }
}
