package kotlin.collections;

import java.util.List;
import kotlin.Metadata;

@Metadata
/* loaded from: classes2.dex */
class ReversedListReadOnly<T> extends AbstractList<T> {

    /* renamed from: c, reason: collision with root package name */
    private final List f18355c;

    @Override // kotlin.collections.AbstractCollection
    public int b() {
        return this.f18355c.size();
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public Object get(int i2) {
        int u;
        List list = this.f18355c;
        u = CollectionsKt__ReversedViewsKt.u(this, i2);
        return list.get(u);
    }
}
