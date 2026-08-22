package kotlin.collections;

import java.util.RandomAccess;
import kotlin.Metadata;

@Metadata
/* loaded from: classes2.dex */
public final class ArraysKt___ArraysJvmKt$asList$7 extends AbstractList<Boolean> implements RandomAccess {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ boolean[] f18320c;

    @Override // kotlin.collections.AbstractCollection
    public int b() {
        return this.f18320c.length;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof Boolean) {
            return d(((Boolean) obj).booleanValue());
        }
        return false;
    }

    public boolean d(boolean z) {
        return ArraysKt___ArraysKt.y(this.f18320c, z);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    /* renamed from: f, reason: merged with bridge method [inline-methods] */
    public Boolean get(int i2) {
        return Boolean.valueOf(this.f18320c[i2]);
    }

    public int g(boolean z) {
        return ArraysKt___ArraysKt.J(this.f18320c, z);
    }

    public int h(boolean z) {
        return ArraysKt___ArraysKt.R(this.f18320c, z);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof Boolean) {
            return g(((Boolean) obj).booleanValue());
        }
        return -1;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return this.f18320c.length == 0;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof Boolean) {
            return h(((Boolean) obj).booleanValue());
        }
        return -1;
    }
}
