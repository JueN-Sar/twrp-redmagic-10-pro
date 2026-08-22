package com.google.android.gms.internal.mlkit_vision_common;

/* loaded from: classes.dex */
public final class zzms {

    /* renamed from: a, reason: collision with root package name */
    private static zzmr f12635a;

    public static synchronized zzmj a(zzme zzmeVar) {
        zzmj zzmjVar;
        synchronized (zzms.class) {
            try {
                if (f12635a == null) {
                    f12635a = new zzmr(null);
                }
                zzmjVar = (zzmj) f12635a.b(zzmeVar);
            } catch (Throwable th) {
                throw th;
            }
        }
        return zzmjVar;
    }

    public static synchronized zzmj b(String str) {
        zzmj a2;
        synchronized (zzms.class) {
            a2 = a(zzme.d("vision-common").c());
        }
        return a2;
    }
}
