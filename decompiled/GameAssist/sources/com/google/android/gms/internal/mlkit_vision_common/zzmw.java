package com.google.android.gms.internal.mlkit_vision_common;

/* loaded from: classes.dex */
public final class zzmw {

    /* renamed from: a, reason: collision with root package name */
    private static zzmw f12644a;

    private zzmw() {
    }

    public static synchronized zzmw a() {
        zzmw zzmwVar;
        synchronized (zzmw.class) {
            try {
                if (f12644a == null) {
                    f12644a = new zzmw();
                }
                zzmwVar = f12644a;
            } catch (Throwable th) {
                throw th;
            }
        }
        return zzmwVar;
    }

    public static final boolean b() {
        return zzmv.a("mlkit-dev-profiling");
    }
}
