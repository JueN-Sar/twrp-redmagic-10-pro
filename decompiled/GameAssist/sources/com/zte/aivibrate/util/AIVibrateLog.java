package com.zte.aivibrate.util;

import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public class AIVibrateLog {
    public static void a(String str) {
        GaLog.a("AIVibrate4D", str);
    }

    public static void b(String str, String str2) {
        GaLog.a(e(str), str2);
    }

    public static void c(String str) {
        GaLog.b("AIVibrate4D", str);
    }

    public static void d(String str, String str2) {
        GaLog.b(e(str), str2);
    }

    private static String e(String str) {
        return "AIVibrate4D" + str;
    }
}
