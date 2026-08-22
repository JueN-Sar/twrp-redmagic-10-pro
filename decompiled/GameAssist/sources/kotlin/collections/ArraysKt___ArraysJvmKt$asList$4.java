package kotlin.collections;

import java.util.RandomAccess;
import kotlin.Metadata;

@Metadata
/* loaded from: classes2.dex */
public final class ArraysKt___ArraysJvmKt$asList$4 extends AbstractList<Long> implements RandomAccess {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ long[] f18317c;

    @Override // kotlin.collections.AbstractCollection
    public int b() {
        return this.f18317c.length;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof Long) {
            return d(((Number) obj).longValue());
        }
        return false;
    }

    public boolean d(long j2) {
        boolean v;
        v = ArraysKt___ArraysKt.v(this.f18317c, j2);
        return v;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    /* renamed from: f, reason: merged with bridge method [inline-methods] */
    public Long get(int i2) {
        return Long.valueOf(this.f18317c[i2]);
    }

    public int g(long j2) {
        return ArraysKt___ArraysKt.G(this.f18317c, j2);
    }

    public int h(long j2) {
        int P;
        P = ArraysKt___ArraysKt.P(this.f18317c, j2);
        return P;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof Long) {
            return g(((Number) obj).longValue());
        }
        return -1;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return this.f18317c.length == 0;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof Long) {
            return h(((Number) obj).longValue());
        }
        return -1;
    }
}
