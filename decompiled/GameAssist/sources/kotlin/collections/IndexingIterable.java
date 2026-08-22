package kotlin.collections;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata
/* loaded from: classes2.dex */
public final class IndexingIterable<T> implements Iterable<IndexedValue<? extends T>>, KMappedMarker {

    /* renamed from: c, reason: collision with root package name */
    private final Function0 f18346c;

    @Override // java.lang.Iterable
    public Iterator iterator() {
        return new IndexingIterator((Iterator) this.f18346c.a());
    }
}
