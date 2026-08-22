package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* loaded from: classes.dex */
final class zbvu {

    /* renamed from: c, reason: collision with root package name */
    private static final zbvu f13022c = new zbvu();

    /* renamed from: d, reason: collision with root package name */
    public static final /* synthetic */ int f13023d = 0;

    /* renamed from: b, reason: collision with root package name */
    private final ConcurrentMap f13025b = new ConcurrentHashMap();

    /* renamed from: a, reason: collision with root package name */
    private final zbvy f13024a = new zbvd();

    private zbvu() {
    }

    public static zbvu a() {
        return f13022c;
    }

    public final zbvx b(Class cls) {
        zbuo.c(cls, "messageType");
        zbvx zbvxVar = (zbvx) this.f13025b.get(cls);
        if (zbvxVar == null) {
            zbvxVar = this.f13024a.a(cls);
            zbuo.c(cls, "messageType");
            zbvx zbvxVar2 = (zbvx) this.f13025b.putIfAbsent(cls, zbvxVar);
            if (zbvxVar2 != null) {
                return zbvxVar2;
            }
        }
        return zbvxVar;
    }
}
