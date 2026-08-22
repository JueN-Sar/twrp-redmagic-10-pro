package androidx.collection.internal;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes.dex */
public final class LruHashMap<K, V> {

    /* renamed from: a, reason: collision with root package name */
    private final LinkedHashMap f1416a;

    public LruHashMap(int i2, float f2) {
        this.f1416a = new LinkedHashMap(i2, f2, true);
    }

    public final Object a(Object key) {
        Intrinsics.e(key, "key");
        return this.f1416a.get(key);
    }

    public final Set b() {
        Set<Map.Entry<K, V>> entrySet = this.f1416a.entrySet();
        Intrinsics.d(entrySet, "map.entries");
        return entrySet;
    }

    public final boolean c() {
        return this.f1416a.isEmpty();
    }

    public final Object d(Object key, Object value) {
        Intrinsics.e(key, "key");
        Intrinsics.e(value, "value");
        return this.f1416a.put(key, value);
    }

    public final Object e(Object key) {
        Intrinsics.e(key, "key");
        return this.f1416a.remove(key);
    }
}
