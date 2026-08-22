package kotlin.collections;

import java.util.RandomAccess;
import kotlin.Metadata;

@Metadata
/* loaded from: classes2.dex */
public final class ArraysKt___ArraysJvmKt$asList$3 extends AbstractList<Integer> implements RandomAccess {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ int[] f18316c;

    @Override // kotlin.collections.AbstractCollection
    public int b() {
        return this.f18316c.length;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof Integer) {
            return d(((Number) obj).intValue());
        }
        return false;
    }

    public boolean d(int i2) {
        boolean u;
        u = ArraysKt___ArraysKt.u(this.f18316c, i2);
        return u;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    /* renamed from: f, reason: merged with bridge method [inline-methods] */
    public Integer get(int i2) {
        return Integer.valueOf(this.f18316c[i2]);
    }

    public int g(int i2) {
        return ArraysKt___ArraysKt.F(this.f18316c, i2);
    }

    public int h(int i2) {
        int O;
        O = ArraysKt___ArraysKt.O(this.f18316c, i2);
        return O;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof Integer) {
            return g(((Number) obj).intValue());
        }
        return -1;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return this.f18316c.length == 0;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof Integer) {
            return h(((Number) obj).intValue());
        }
        return -1;
    }
}
