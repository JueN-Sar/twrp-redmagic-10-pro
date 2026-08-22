package kotlin.collections.unsigned;

import java.util.RandomAccess;
import kotlin.Metadata;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt___ArraysKt;

@Metadata
/* loaded from: classes2.dex */
public final class UArraysKt___UArraysJvmKt$asList$4 extends AbstractList<UShort> implements RandomAccess {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ short[] f18382c;

    @Override // kotlin.collections.AbstractCollection
    public int b() {
        return UShortArray.j(this.f18382c);
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof UShort) {
            return d(((UShort) obj).j());
        }
        return false;
    }

    public boolean d(short s2) {
        return UShortArray.d(this.f18382c, s2);
    }

    public short f(int i2) {
        return UShortArray.h(this.f18382c, i2);
    }

    public int g(short s2) {
        return ArraysKt___ArraysKt.I(this.f18382c, s2);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public /* bridge */ /* synthetic */ Object get(int i2) {
        return UShort.c(f(i2));
    }

    public int h(short s2) {
        int Q;
        Q = ArraysKt___ArraysKt.Q(this.f18382c, s2);
        return Q;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof UShort) {
            return g(((UShort) obj).j());
        }
        return -1;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return UShortArray.l(this.f18382c);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof UShort) {
            return h(((UShort) obj).j());
        }
        return -1;
    }
}
