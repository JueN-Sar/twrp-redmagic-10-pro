package cn.nubia.projection.util;

import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public class PLog {
    public static void a(String str) {
        GaLog.a("ProjectionUI", str);
    }

    public static void b(String str) {
        GaLog.b("ProjectionUI", str);
    }

    public static void c(String str, Exception exc) {
        GaLog.c("ProjectionUI", str, exc);
    }

    public static void d(String str, Throwable th) {
        GaLog.d("ProjectionUI", str, th);
    }

    public static void e(String str) {
        GaLog.e("ProjectionUI", str);
    }
}
