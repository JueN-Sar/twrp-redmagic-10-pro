package kotlin.collections;

import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: Add missing generic type declarations: [V] */
@Metadata
/* loaded from: classes2.dex */
public final class AbstractMap$values$1$iterator$1<V> implements Iterator<V>, KMappedMarker {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Iterator f18305c;

    AbstractMap$values$1$iterator$1(Iterator it) {
        this.f18305c = it;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f18305c.hasNext();
    }

    @Override // java.util.Iterator
    public Object next() {
        return ((Map.Entry) this.f18305c.next()).getValue();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
