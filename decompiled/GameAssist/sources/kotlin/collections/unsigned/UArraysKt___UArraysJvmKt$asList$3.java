package kotlin.collections.unsigned;

import java.util.RandomAccess;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt___ArraysKt;

@Metadata
/* loaded from: classes2.dex */
public final class UArraysKt___UArraysJvmKt$asList$3 extends AbstractList<UByte> implements RandomAccess {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ byte[] f18381c;

    @Override // kotlin.collections.AbstractCollection
    public int b() {
        return UByteArray.j(this.f18381c);
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof UByte) {
            return d(((UByte) obj).j());
        }
        return false;
    }

    public boolean d(byte b2) {
        return UByteArray.d(this.f18381c, b2);
    }

    public byte f(int i2) {
        return UByteArray.h(this.f18381c, i2);
    }

    public int g(byte b2) {
        return ArraysKt___ArraysKt.D(this.f18381c, b2);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public /* bridge */ /* synthetic */ Object get(int i2) {
        return UByte.c(f(i2));
    }

    public int h(byte b2) {
        int M;
        M = ArraysKt___ArraysKt.M(this.f18381c, b2);
        return M;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof UByte) {
            return g(((UByte) obj).j());
        }
        return -1;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return UByteArray.l(this.f18381c);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof UByte) {
            return h(((UByte) obj).j());
        }
        return -1;
    }
}
