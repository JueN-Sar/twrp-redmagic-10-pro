package com.google.android.material.internal;

import android.os.Build;
import androidx.annotation.RestrictTo;
import java.util.Locale;

@RestrictTo
/* loaded from: classes.dex */
public class ManufacturerUtils {
    private static String a() {
        String str = Build.MANUFACTURER;
        return str != null ? str.toLowerCase(Locale.ENGLISH) : "";
    }

    public static boolean b() {
        return c() || e();
    }

    public static boolean c() {
        return a().equals("lge");
    }

    public static boolean d() {
        return a().equals("meizu");
    }

    public static boolean e() {
        return a().equals("samsung");
    }
}
