package kotlin.collections.unsigned;

import java.util.RandomAccess;
import kotlin.Metadata;
import kotlin.ULong;
import kotlin.ULongArray;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt___ArraysKt;

@Metadata
/* loaded from: classes2.dex */
public final class UArraysKt___UArraysJvmKt$asList$2 extends AbstractList<ULong> implements RandomAccess {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ long[] f18380c;

    @Override // kotlin.collections.AbstractCollection
    public int b() {
        return ULongArray.j(this.f18380c);
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof ULong) {
            return d(((ULong) obj).j());
        }
        return false;
    }

    public boolean d(long j2) {
        return ULongArray.d(this.f18380c, j2);
    }

    public long f(int i2) {
        return ULongArray.h(this.f18380c, i2);
    }

    public int g(long j2) {
        return ArraysKt___ArraysKt.G(this.f18380c, j2);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public /* bridge */ /* synthetic */ Object get(int i2) {
        return ULong.c(f(i2));
    }

    public int h(long j2) {
        int P;
        P = ArraysKt___ArraysKt.P(this.f18380c, j2);
        return P;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof ULong) {
            return g(((ULong) obj).j());
        }
        return -1;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return ULongArray.l(this.f18380c);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof ULong) {
            return h(((ULong) obj).j());
        }
        return -1;
    }
}
