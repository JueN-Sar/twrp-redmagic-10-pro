package com.zte.shared.wrapper;

import android.os.SystemProperties;

/* loaded from: classes2.dex */
public class SystemPropertiesWrapper {
    public static void addChangeCallback(Runnable runnable) {
        SystemProperties.addChangeCallback(runnable);
    }

    public static String get(String str) {
        return SystemProperties.get(str);
    }

    public static boolean getBoolean(String str, boolean z) {
        return SystemProperties.getBoolean(str, z);
    }

    public static long getLong(String str, long j2) {
        return SystemProperties.getLong(str, j2);
    }

    public static void removeChangeCallback(Runnable runnable) {
        SystemProperties.removeChangeCallback(runnable);
    }

    public static void set(String str, String str2) {
        SystemProperties.set(str, str2);
    }

    public static String get(String str, String str2) {
        return SystemProperties.get(str, str2);
    }

    public static int getBoolean(String str, int i2) {
        return SystemProperties.getInt(str, i2);
    }
}
