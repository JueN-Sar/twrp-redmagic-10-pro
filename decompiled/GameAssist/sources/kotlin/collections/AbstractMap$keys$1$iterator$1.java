package kotlin.collections;

import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: Add missing generic type declarations: [K] */
@Metadata
/* loaded from: classes2.dex */
public final class AbstractMap$keys$1$iterator$1<K> implements Iterator<K>, KMappedMarker {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Iterator f18303c;

    AbstractMap$keys$1$iterator$1(Iterator it) {
        this.f18303c = it;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f18303c.hasNext();
    }

    @Override // java.util.Iterator
    public Object next() {
        return ((Map.Entry) this.f18303c.next()).getKey();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
