package cn.nubia.componentsdk.until;

import android.util.Log;

/* loaded from: classes.dex */
public class PayLog {

    /* renamed from: a, reason: collision with root package name */
    public static boolean f6070a = false;

    public static void a(String str, String str2) {
        if (f6070a) {
            Log.d(str, str2);
        }
    }

    public static void b(String str, String str2) {
        if (f6070a) {
            Log.e(str, str2);
        }
    }

    public static void c(String str, String str2) {
        if (f6070a) {
            Log.i(str, str2);
        }
    }
}
