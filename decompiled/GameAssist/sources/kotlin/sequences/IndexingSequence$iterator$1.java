package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: Add missing generic type declarations: [T] */
@Metadata
/* loaded from: classes2.dex */
public final class IndexingSequence$iterator$1<T> implements Iterator<IndexedValue<? extends T>>, KMappedMarker {

    /* renamed from: c, reason: collision with root package name */
    private final Iterator f18704c;

    /* renamed from: h, reason: collision with root package name */
    private int f18705h;

    IndexingSequence$iterator$1(IndexingSequence indexingSequence) {
        Sequence sequence;
        sequence = indexingSequence.f18703a;
        this.f18704c = sequence.iterator();
    }

    @Override // java.util.Iterator
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public IndexedValue next() {
        int i2 = this.f18705h;
        this.f18705h = i2 + 1;
        if (i2 < 0) {
            CollectionsKt__CollectionsKt.m();
        }
        return new IndexedValue(i2, this.f18704c.next());
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f18704c.hasNext();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
