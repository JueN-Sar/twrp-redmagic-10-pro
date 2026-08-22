package com.google.android.material.internal;

import android.R;
import android.content.Context;
import android.view.Window;
import androidx.annotation.RestrictTo;
import androidx.core.view.WindowCompat;
import com.google.android.material.color.MaterialColors;

@RestrictTo
/* loaded from: classes.dex */
public class EdgeToEdgeUtils {
    public static void a(Window window, boolean z, Integer num, Integer num2) {
        boolean z2 = num == null || num.intValue() == 0;
        boolean z3 = num2 == null || num2.intValue() == 0;
        if (z2 || z3) {
            int b2 = MaterialColors.b(window.getContext(), R.attr.colorBackground, -16777216);
            if (z2) {
                num = Integer.valueOf(b2);
            }
            if (z3) {
                num2 = Integer.valueOf(b2);
            }
        }
        WindowCompat.b(window, !z);
        int c2 = c(window.getContext(), z);
        int b3 = b(window.getContext(), z);
        window.setStatusBarColor(c2);
        window.setNavigationBarColor(b3);
        f(window, d(c2, MaterialColors.i(num.intValue())));
        e(window, d(b3, MaterialColors.i(num2.intValue())));
    }

    private static int b(Context context, boolean z) {
        if (z) {
            return 0;
        }
        return MaterialColors.b(context, R.attr.navigationBarColor, -16777216);
    }

    private static int c(Context context, boolean z) {
        if (z) {
            return 0;
        }
        return MaterialColors.b(context, R.attr.statusBarColor, -16777216);
    }

    private static boolean d(int i2, boolean z) {
        return MaterialColors.i(i2) || (i2 == 0 && z);
    }

    public static void e(Window window, boolean z) {
        WindowCompat.a(window, window.getDecorView()).c(z);
    }

    public static void f(Window window, boolean z) {
        WindowCompat.a(window, window.getDecorView()).d(z);
    }
}
