package kotlin.collections.unsigned;

import java.util.RandomAccess;
import kotlin.Metadata;
import kotlin.UInt;
import kotlin.UIntArray;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt___ArraysKt;

@Metadata
/* loaded from: classes2.dex */
public final class UArraysKt___UArraysJvmKt$asList$1 extends AbstractList<UInt> implements RandomAccess {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ int[] f18379c;

    @Override // kotlin.collections.AbstractCollection
    public int b() {
        return UIntArray.j(this.f18379c);
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof UInt) {
            return d(((UInt) obj).j());
        }
        return false;
    }

    public boolean d(int i2) {
        return UIntArray.d(this.f18379c, i2);
    }

    public int f(int i2) {
        return UIntArray.h(this.f18379c, i2);
    }

    public int g(int i2) {
        return ArraysKt___ArraysKt.F(this.f18379c, i2);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public /* bridge */ /* synthetic */ Object get(int i2) {
        return UInt.c(f(i2));
    }

    public int h(int i2) {
        int O;
        O = ArraysKt___ArraysKt.O(this.f18379c, i2);
        return O;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof UInt) {
            return g(((UInt) obj).j());
        }
        return -1;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return UIntArray.l(this.f18379c);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof UInt) {
            return h(((UInt) obj).j());
        }
        return -1;
    }
}
