package kotlin.collections;

import java.util.RandomAccess;
import kotlin.Metadata;

@Metadata
/* loaded from: classes2.dex */
public final class ArraysKt___ArraysJvmKt$asList$8 extends AbstractList<Character> implements RandomAccess {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ char[] f18321c;

    @Override // kotlin.collections.AbstractCollection
    public int b() {
        return this.f18321c.length;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof Character) {
            return d(((Character) obj).charValue());
        }
        return false;
    }

    public boolean d(char c2) {
        return ArraysKt___ArraysKt.t(this.f18321c, c2);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    /* renamed from: f, reason: merged with bridge method [inline-methods] */
    public Character get(int i2) {
        return Character.valueOf(this.f18321c[i2]);
    }

    public int g(char c2) {
        return ArraysKt___ArraysKt.E(this.f18321c, c2);
    }

    public int h(char c2) {
        return ArraysKt___ArraysKt.N(this.f18321c, c2);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof Character) {
            return g(((Character) obj).charValue());
        }
        return -1;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return this.f18321c.length == 0;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof Character) {
            return h(((Character) obj).charValue());
        }
        return -1;
    }
}
