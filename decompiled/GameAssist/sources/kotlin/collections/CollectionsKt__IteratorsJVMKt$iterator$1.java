package kotlin.collections;

import java.util.Enumeration;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata
/* loaded from: classes2.dex */
public final class CollectionsKt__IteratorsJVMKt$iterator$1 implements Iterator<Object>, KMappedMarker {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Enumeration f18341c;

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f18341c.hasMoreElements();
    }

    @Override // java.util.Iterator
    public Object next() {
        return this.f18341c.nextElement();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
