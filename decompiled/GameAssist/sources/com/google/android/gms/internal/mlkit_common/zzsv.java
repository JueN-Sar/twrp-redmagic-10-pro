package com.google.android.gms.internal.mlkit_common;

/* loaded from: classes.dex */
public final class zzsv {

    /* renamed from: a, reason: collision with root package name */
    private static zzsv f11854a;

    private zzsv() {
    }

    public static synchronized zzsv a() {
        zzsv zzsvVar;
        synchronized (zzsv.class) {
            try {
                if (f11854a == null) {
                    f11854a = new zzsv();
                }
                zzsvVar = f11854a;
            } catch (Throwable th) {
                throw th;
            }
        }
        return zzsvVar;
    }

    public static void b() {
        zzsu.a();
    }
}
