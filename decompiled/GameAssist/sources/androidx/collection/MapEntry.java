package androidx.collection;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata
/* loaded from: classes.dex */
final class MapEntry<K, V> implements Map.Entry<K, V>, KMappedMarker {

    /* renamed from: c, reason: collision with root package name */
    private final Object f1314c;

    /* renamed from: h, reason: collision with root package name */
    private final Object f1315h;

    public MapEntry(Object obj, Object obj2) {
        this.f1314c = obj;
        this.f1315h = obj2;
    }

    @Override // java.util.Map.Entry
    public Object getKey() {
        return this.f1314c;
    }

    @Override // java.util.Map.Entry
    public Object getValue() {
        return this.f1315h;
    }

    @Override // java.util.Map.Entry
    public Object setValue(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
