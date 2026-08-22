package kotlin.collections;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata
/* loaded from: classes2.dex */
public final class IndexingIterator<T> implements Iterator<IndexedValue<? extends T>>, KMappedMarker {

    /* renamed from: c, reason: collision with root package name */
    private final Iterator f18347c;

    /* renamed from: h, reason: collision with root package name */
    private int f18348h;

    public IndexingIterator(Iterator iterator) {
        Intrinsics.e(iterator, "iterator");
        this.f18347c = iterator;
    }

    @Override // java.util.Iterator
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public final IndexedValue next() {
        int i2 = this.f18348h;
        this.f18348h = i2 + 1;
        if (i2 < 0) {
            CollectionsKt__CollectionsKt.m();
        }
        return new IndexedValue(i2, this.f18347c.next());
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f18347c.hasNext();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
