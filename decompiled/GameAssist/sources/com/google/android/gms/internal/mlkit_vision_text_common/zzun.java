package com.google.android.gms.internal.mlkit_vision_text_common;

/* loaded from: classes.dex */
public final class zzun {

    /* renamed from: a, reason: collision with root package name */
    private static zzum f13590a;

    public static synchronized zzuc a(zztu zztuVar) {
        zzuc zzucVar;
        synchronized (zzun.class) {
            try {
                if (f13590a == null) {
                    f13590a = new zzum(null);
                }
                zzucVar = (zzuc) f13590a.b(zztuVar);
            } catch (Throwable th) {
                throw th;
            }
        }
        return zzucVar;
    }

    public static synchronized zzuc b(String str) {
        zzuc a2;
        synchronized (zzun.class) {
            a2 = a(zztu.d(str).c());
        }
        return a2;
    }
}
