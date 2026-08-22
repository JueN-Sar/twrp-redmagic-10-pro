package androidx.collection;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableMap;

@Metadata
/* loaded from: classes.dex */
final class MutableMapEntry<K, V> implements Map.Entry<K, V>, KMutableMap.Entry {

    /* renamed from: c, reason: collision with root package name */
    private final Object[] f1331c;

    /* renamed from: h, reason: collision with root package name */
    private final Object[] f1332h;

    /* renamed from: i, reason: collision with root package name */
    private final int f1333i;

    public MutableMapEntry(Object[] keys, Object[] values, int i2) {
        Intrinsics.e(keys, "keys");
        Intrinsics.e(values, "values");
        this.f1331c = keys;
        this.f1332h = values;
        this.f1333i = i2;
    }

    @Override // java.util.Map.Entry
    public Object getKey() {
        return this.f1331c[this.f1333i];
    }

    @Override // java.util.Map.Entry
    public Object getValue() {
        return this.f1332h[this.f1333i];
    }

    @Override // java.util.Map.Entry
    public Object setValue(Object obj) {
        Object[] objArr = this.f1332h;
        int i2 = this.f1333i;
        Object obj2 = objArr[i2];
        objArr[i2] = obj;
        return obj2;
    }
}
