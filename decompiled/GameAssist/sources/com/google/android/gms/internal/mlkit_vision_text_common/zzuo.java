package com.google.android.gms.internal.mlkit_vision_text_common;

/* loaded from: classes.dex */
public final class zzuo {

    /* renamed from: a, reason: collision with root package name */
    private static zzuo f13591a;

    private zzuo() {
    }

    public static synchronized zzuo a() {
        zzuo zzuoVar;
        synchronized (zzuo.class) {
            try {
                if (f13591a == null) {
                    f13591a = new zzuo();
                }
                zzuoVar = f13591a;
            } catch (Throwable th) {
                throw th;
            }
        }
        return zzuoVar;
    }
}
