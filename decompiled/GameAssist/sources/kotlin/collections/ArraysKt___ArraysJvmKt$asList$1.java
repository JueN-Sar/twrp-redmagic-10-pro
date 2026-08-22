package kotlin.collections;

import java.util.RandomAccess;
import kotlin.Metadata;

@Metadata
/* loaded from: classes2.dex */
public final class ArraysKt___ArraysJvmKt$asList$1 extends AbstractList<Byte> implements RandomAccess {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ byte[] f18314c;

    @Override // kotlin.collections.AbstractCollection
    public int b() {
        return this.f18314c.length;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof Byte) {
            return d(((Number) obj).byteValue());
        }
        return false;
    }

    public boolean d(byte b2) {
        boolean s2;
        s2 = ArraysKt___ArraysKt.s(this.f18314c, b2);
        return s2;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    /* renamed from: f, reason: merged with bridge method [inline-methods] */
    public Byte get(int i2) {
        return Byte.valueOf(this.f18314c[i2]);
    }

    public int g(byte b2) {
        return ArraysKt___ArraysKt.D(this.f18314c, b2);
    }

    public int h(byte b2) {
        int M;
        M = ArraysKt___ArraysKt.M(this.f18314c, b2);
        return M;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof Byte) {
            return g(((Number) obj).byteValue());
        }
        return -1;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return this.f18314c.length == 0;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof Byte) {
            return h(((Number) obj).byteValue());
        }
        return -1;
    }
}
