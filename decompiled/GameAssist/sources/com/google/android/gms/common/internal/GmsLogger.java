package com.google.android.gms.common.internal;

import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
/* loaded from: classes.dex */
public final class GmsLogger {

    /* renamed from: a, reason: collision with root package name */
    private final String f11006a;

    /* renamed from: b, reason: collision with root package name */
    private final String f11007b;

    public GmsLogger(String str, String str2) {
        Preconditions.j(str, "log tag cannot be null");
        Preconditions.c(str.length() <= 23, "tag \"%s\" is longer than the %d character maximum", str, 23);
        this.f11006a = str;
        this.f11007b = (str2 == null || str2.length() <= 0) ? null : str2;
    }

    private final String i(String str) {
        String str2 = this.f11007b;
        return str2 == null ? str : str2.concat(str);
    }

    public boolean a(int i2) {
        return Log.isLoggable(this.f11006a, i2);
    }

    public void b(String str, String str2) {
        if (a(3)) {
            Log.d(str, i(str2));
        }
    }

    public void c(String str, String str2) {
        if (a(6)) {
            Log.e(str, i(str2));
        }
    }

    public void d(String str, String str2, Throwable th) {
        if (a(6)) {
            Log.e(str, i(str2), th);
        }
    }

    public void e(String str, String str2) {
        if (a(4)) {
            Log.i(str, i(str2));
        }
    }

    public void f(String str, String str2) {
        if (a(2)) {
            Log.v(str, i(str2));
        }
    }

    public void g(String str, String str2) {
        if (a(5)) {
            Log.w(str, i(str2));
        }
    }

    public void h(String str, String str2, Throwable th) {
        if (a(5)) {
            Log.w(str, i(str2), th);
        }
    }
}
