package kotlin.collections;

import java.util.RandomAccess;
import kotlin.Metadata;

@Metadata
/* loaded from: classes2.dex */
public final class ArraysKt___ArraysJvmKt$asList$2 extends AbstractList<Short> implements RandomAccess {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ short[] f18315c;

    @Override // kotlin.collections.AbstractCollection
    public int b() {
        return this.f18315c.length;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof Short) {
            return d(((Number) obj).shortValue());
        }
        return false;
    }

    public boolean d(short s2) {
        boolean x;
        x = ArraysKt___ArraysKt.x(this.f18315c, s2);
        return x;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    /* renamed from: f, reason: merged with bridge method [inline-methods] */
    public Short get(int i2) {
        return Short.valueOf(this.f18315c[i2]);
    }

    public int g(short s2) {
        return ArraysKt___ArraysKt.I(this.f18315c, s2);
    }

    public int h(short s2) {
        int Q;
        Q = ArraysKt___ArraysKt.Q(this.f18315c, s2);
        return Q;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof Short) {
            return g(((Number) obj).shortValue());
        }
        return -1;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return this.f18315c.length == 0;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof Short) {
            return h(((Number) obj).shortValue());
        }
        return -1;
    }
}
