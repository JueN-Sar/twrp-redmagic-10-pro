package kotlin.collections;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes2.dex */
public final class IndexedValue<T> {

    /* renamed from: a, reason: collision with root package name */
    private final int f18344a;

    /* renamed from: b, reason: collision with root package name */
    private final Object f18345b;

    public IndexedValue(int i2, Object obj) {
        this.f18344a = i2;
        this.f18345b = obj;
    }

    public final int a() {
        return this.f18344a;
    }

    public final Object b() {
        return this.f18345b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IndexedValue)) {
            return false;
        }
        IndexedValue indexedValue = (IndexedValue) obj;
        return this.f18344a == indexedValue.f18344a && Intrinsics.a(this.f18345b, indexedValue.f18345b);
    }

    public int hashCode() {
        int hashCode = Integer.hashCode(this.f18344a) * 31;
        Object obj = this.f18345b;
        return hashCode + (obj == null ? 0 : obj.hashCode());
    }

    public String toString() {
        return "IndexedValue(index=" + this.f18344a + ", value=" + this.f18345b + ')';
    }
}
