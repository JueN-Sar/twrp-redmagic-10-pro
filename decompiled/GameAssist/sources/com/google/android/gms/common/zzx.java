package com.google.android.gms.common;

import com.google.errorprone.annotations.CheckReturnValue;

@CheckReturnValue
/* loaded from: classes.dex */
class zzx {

    /* renamed from: e, reason: collision with root package name */
    private static final zzx f11327e = new zzx(true, 3, 1, null, null);

    /* renamed from: a, reason: collision with root package name */
    final boolean f11328a;

    /* renamed from: b, reason: collision with root package name */
    final String f11329b;

    /* renamed from: c, reason: collision with root package name */
    final Throwable f11330c;

    /* renamed from: d, reason: collision with root package name */
    final int f11331d;

    private zzx(boolean z, int i2, int i3, String str, Throwable th) {
        this.f11328a = z;
        this.f11331d = i2;
        this.f11329b = str;
        this.f11330c = th;
    }

    static zzx a() {
        return f11327e;
    }

    static zzx b(String str, Throwable th) {
        return new zzx(false, 1, 5, str, th);
    }
}
