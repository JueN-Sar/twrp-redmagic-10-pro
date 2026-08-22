package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import android.util.Log;

/* loaded from: classes.dex */
public final class zbcq {

    /* renamed from: b, reason: collision with root package name */
    public static final zbcq f12749b = new zbcq("VisionKit", 2);

    /* renamed from: a, reason: collision with root package name */
    private final String f12750a = "VisionKit";

    public zbcq(String str, int i2) {
    }

    private final boolean d(int i2) {
        return Log.isLoggable(this.f12750a, i2);
    }

    private static final String e(Object obj, String str, Object... objArr) {
        String str2;
        if (obj instanceof String) {
            str2 = (String) obj;
        } else {
            String name = obj.getClass().getName();
            if (obj instanceof Class) {
                name = ((Class) obj).getName();
            }
            String[] split = name.split("\\.");
            int length = split.length;
            str2 = length == 0 ? "" : split[length - 1];
        }
        return "[" + str2 + "] " + str;
    }

    public final void a(Throwable th, String str, Object... objArr) {
        if (d(6)) {
            Log.e(this.f12750a, "Error in result from JNI layer", th);
        }
    }

    public final void b(Object obj, String str, Object... objArr) {
        if (d(4)) {
            Log.i(this.f12750a, e(obj, str, objArr));
        }
    }

    public final void c(Object obj, String str, Object... objArr) {
        if (d(5)) {
            Log.w(this.f12750a, e(obj, str, objArr));
        }
    }
}
