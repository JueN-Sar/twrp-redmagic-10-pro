package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import java.util.Map;

/* loaded from: classes.dex */
final class zbwb implements Map.Entry, Comparable {

    /* renamed from: c, reason: collision with root package name */
    private final Comparable f13035c;

    /* renamed from: h, reason: collision with root package name */
    private Object f13036h;

    /* renamed from: i, reason: collision with root package name */
    final /* synthetic */ zbwh f13037i;

    zbwb(zbwh zbwhVar, Comparable comparable, Object obj) {
        this.f13037i = zbwhVar;
        this.f13035c = comparable;
        this.f13036h = obj;
    }

    private static final boolean d(Object obj, Object obj2) {
        return obj == null ? obj2 == null : obj.equals(obj2);
    }

    public final Comparable c() {
        return this.f13035c;
    }

    @Override // java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return this.f13035c.compareTo(((zbwb) obj).f13035c);
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return d(this.f13035c, entry.getKey()) && d(this.f13036h, entry.getValue());
    }

    @Override // java.util.Map.Entry
    public final /* synthetic */ Object getKey() {
        return this.f13035c;
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.f13036h;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        Comparable comparable = this.f13035c;
        int hashCode = comparable == null ? 0 : comparable.hashCode();
        Object obj = this.f13036h;
        return hashCode ^ (obj != null ? obj.hashCode() : 0);
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        this.f13037i.o();
        Object obj2 = this.f13036h;
        this.f13036h = obj;
        return obj2;
    }

    public final String toString() {
        return String.valueOf(this.f13035c) + "=" + String.valueOf(this.f13036h);
    }
}
