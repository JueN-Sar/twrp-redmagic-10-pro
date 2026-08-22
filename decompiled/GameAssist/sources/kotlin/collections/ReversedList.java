package kotlin.collections;

import java.util.List;
import kotlin.Metadata;

@Metadata
/* loaded from: classes2.dex */
final class ReversedList<T> extends AbstractMutableList<T> {

    /* renamed from: c, reason: collision with root package name */
    private final List f18354c;

    @Override // java.util.AbstractList, java.util.List
    public void add(int i2, Object obj) {
        int v;
        List list = this.f18354c;
        v = CollectionsKt__ReversedViewsKt.v(this, i2);
        list.add(v, obj);
    }

    @Override // kotlin.collections.AbstractMutableList
    public int b() {
        return this.f18354c.size();
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        this.f18354c.clear();
    }

    @Override // kotlin.collections.AbstractMutableList
    public Object d(int i2) {
        int u;
        List list = this.f18354c;
        u = CollectionsKt__ReversedViewsKt.u(this, i2);
        return list.remove(u);
    }

    @Override // java.util.AbstractList, java.util.List
    public Object get(int i2) {
        int u;
        List list = this.f18354c;
        u = CollectionsKt__ReversedViewsKt.u(this, i2);
        return list.get(u);
    }

    @Override // java.util.AbstractList, java.util.List
    public Object set(int i2, Object obj) {
        int u;
        List list = this.f18354c;
        u = CollectionsKt__ReversedViewsKt.u(this, i2);
        return list.set(u, obj);
    }
}
