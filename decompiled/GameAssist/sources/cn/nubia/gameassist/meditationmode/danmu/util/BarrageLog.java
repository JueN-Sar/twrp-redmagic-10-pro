package cn.nubia.gameassist.meditationmode.danmu.util;

import android.os.SystemProperties;
import android.os.Trace;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public class BarrageLog {

    /* renamed from: a, reason: collision with root package name */
    public static boolean f6710a = SystemProperties.getBoolean("debug.persist.sys.gameassist_debug", false);

    public static void a(String str) {
        if (f6710a) {
            Trace.beginSection(str);
        }
    }

    public static void b(String str, String str2) {
        GaLog.a("BLog", g(str, str2));
    }

    public static void c(String str, String str2) {
        GaLog.b("BLog", g(str, str2));
    }

    public static void d(String str, String str2, Exception exc) {
        GaLog.c("BLog", g(str, str2), exc);
    }

    public static void e() {
        if (f6710a) {
            Trace.endSection();
        }
    }

    public static void f(String str, String str2) {
        GaLog.e("BLog", g(str, str2));
    }

    private static String g(String str, String str2) {
        return "[" + str + "]" + str2;
    }
}
