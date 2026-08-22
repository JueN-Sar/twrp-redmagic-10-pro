package kotlin.collections;

import java.util.List;
import java.util.RandomAccess;
import kotlin.Metadata;

@Metadata
/* loaded from: classes2.dex */
public final class MovingSubList<E> extends AbstractList<E> implements RandomAccess {

    /* renamed from: c, reason: collision with root package name */
    private final List f18350c;

    /* renamed from: h, reason: collision with root package name */
    private int f18351h;

    /* renamed from: i, reason: collision with root package name */
    private int f18352i;

    @Override // kotlin.collections.AbstractCollection
    public int b() {
        return this.f18352i;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public Object get(int i2) {
        AbstractList.Companion.b(i2, this.f18352i);
        return this.f18350c.get(this.f18351h + i2);
    }
}
