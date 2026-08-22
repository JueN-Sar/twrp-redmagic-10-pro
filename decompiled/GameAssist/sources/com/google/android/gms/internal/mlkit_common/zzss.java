package com.google.android.gms.internal.mlkit_common;

/* loaded from: classes.dex */
public final class zzss {

    /* renamed from: a, reason: collision with root package name */
    private static zzsr f11851a;

    public static synchronized zzsh a(zzsb zzsbVar) {
        zzsh zzshVar;
        synchronized (zzss.class) {
            try {
                if (f11851a == null) {
                    f11851a = new zzsr(null);
                }
                zzshVar = (zzsh) f11851a.b(zzsbVar);
            } catch (Throwable th) {
                throw th;
            }
        }
        return zzshVar;
    }

    public static synchronized zzsh b(String str) {
        zzsh a2;
        synchronized (zzss.class) {
            a2 = a(zzsb.d("common").c());
        }
        return a2;
    }
}
