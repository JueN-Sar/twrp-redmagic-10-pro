package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import java.util.Map;

/* loaded from: classes.dex */
final class zbut implements Map.Entry {

    /* renamed from: c, reason: collision with root package name */
    private final Map.Entry f12986c;

    public final zbuv a() {
        return (zbuv) this.f12986c.getValue();
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.f12986c.getKey();
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        if (((zbuv) this.f12986c.getValue()) == null) {
            return null;
        }
        throw null;
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        if (obj instanceof zbvm) {
            return ((zbuv) this.f12986c.getValue()).c((zbvm) obj);
        }
        throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
    }
}
