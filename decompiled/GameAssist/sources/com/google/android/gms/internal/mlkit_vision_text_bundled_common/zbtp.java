package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import java.util.Collections;
import java.util.Map;

/* loaded from: classes.dex */
public final class zbtp {

    /* renamed from: b, reason: collision with root package name */
    private static volatile zbtp f12962b;

    /* renamed from: c, reason: collision with root package name */
    static final zbtp f12963c = new zbtp(true);

    /* renamed from: a, reason: collision with root package name */
    private final Map f12964a = Collections.emptyMap();

    zbtp(boolean z) {
    }

    public static zbtp a() {
        int i2 = zbvu.f13023d;
        return f12963c;
    }

    public static zbtp b() {
        zbtp zbtpVar = f12962b;
        if (zbtpVar != null) {
            return zbtpVar;
        }
        synchronized (zbtp.class) {
            try {
                zbtp zbtpVar2 = f12962b;
                if (zbtpVar2 != null) {
                    return zbtpVar2;
                }
                int i2 = zbvu.f13023d;
                zbtp b2 = zbtx.b(zbtp.class);
                f12962b = b2;
                return b2;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final zbud c(zbvm zbvmVar, int i2) {
        return (zbud) this.f12964a.get(new zbto(zbvmVar, i2));
    }
}
