package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
final class zbto {

    /* renamed from: a, reason: collision with root package name */
    private final Object f12960a;

    /* renamed from: b, reason: collision with root package name */
    private final int f12961b;

    zbto(Object obj, int i2) {
        this.f12960a = obj;
        this.f12961b = i2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zbto)) {
            return false;
        }
        zbto zbtoVar = (zbto) obj;
        return this.f12960a == zbtoVar.f12960a && this.f12961b == zbtoVar.f12961b;
    }

    public final int hashCode() {
        return (System.identityHashCode(this.f12960a) * 65535) + this.f12961b;
    }
}
