package com.google.android.gms.internal.mlkit_common;

/* loaded from: classes.dex */
final class zzag {

    /* renamed from: a, reason: collision with root package name */
    private final Object f11417a;

    /* renamed from: b, reason: collision with root package name */
    private final Object f11418b;

    /* renamed from: c, reason: collision with root package name */
    private final Object f11419c;

    zzag(Object obj, Object obj2, Object obj3) {
        this.f11417a = obj;
        this.f11418b = obj2;
        this.f11419c = obj3;
    }

    final IllegalArgumentException a() {
        Object obj = this.f11419c;
        Object obj2 = this.f11418b;
        Object obj3 = this.f11417a;
        return new IllegalArgumentException("Multiple entries with same key: " + String.valueOf(obj3) + "=" + String.valueOf(obj2) + " and " + String.valueOf(obj3) + "=" + String.valueOf(obj));
    }
}
