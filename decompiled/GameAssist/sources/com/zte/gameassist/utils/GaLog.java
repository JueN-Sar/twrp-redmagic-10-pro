package com.zte.gameassist.utils;

import android.os.Build;
import android.os.SystemProperties;
import android.provider.Settings;
import android.util.Log;
import com.zte.gameassist.common.SystemMgr;
import com.zte.shared.wrapper.ContextWrapper;

/* loaded from: classes2.dex */
public class GaLog {

    /* renamed from: a, reason: collision with root package name */
    public static String f17033a = "GameAssist";

    /* renamed from: b, reason: collision with root package name */
    public static boolean f17034b = i();

    /* renamed from: c, reason: collision with root package name */
    public static boolean f17035c;

    public static void a(String str, String str2) {
        Log.i(f17033a, h(str, str2));
    }

    public static void b(String str, String str2) {
        Log.e(f17033a, h(str, str2));
    }

    public static void c(String str, String str2, Exception exc) {
        Log.e(f17033a, h(str, str2), exc);
    }

    public static void d(String str, String str2, Throwable th) {
        Log.e(f17033a, h(str, str2), th);
    }

    public static void e(String str, String str2) {
        Log.i(f17033a, h(str, str2));
    }

    public static void f(String str, String str2, Throwable th) {
        Log.i(f17033a, h(str, str2), th);
    }

    public static void g(String str, String str2) {
        if (SystemMgr.F) {
            return;
        }
        Log.i(f17033a, h(str, str2));
    }

    private static String h(String str, String str2) {
        return "[" + str + "]" + str2;
    }

    public static boolean i() {
        boolean z = "userdebug".equals(Build.TYPE) || SystemProperties.getBoolean("debug.persist.sys.gameassist_debug", false);
        boolean z2 = Settings.Global.getInt(ContextWrapper.getContext().getContentResolver(), "debug_game_assist", 0) == 1;
        boolean z3 = z | z2;
        f17034b = z3;
        f17035c = z2;
        return z3;
    }

    public static void j(String str, String str2) {
        if (f17034b) {
            Log.i(f17033a, h(str, str2));
        }
    }

    public static void k(String str, String str2) {
        Log.w(f17033a, h(str, str2));
    }

    public static void l(String str, String str2, Throwable th) {
        Log.w(f17033a, h(str, str2), th);
    }
}
