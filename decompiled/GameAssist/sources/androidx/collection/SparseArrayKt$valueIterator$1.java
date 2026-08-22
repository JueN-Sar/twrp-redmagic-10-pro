package androidx.collection;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata
/* loaded from: classes.dex */
public final class SparseArrayKt$valueIterator$1 implements Iterator<Object>, KMappedMarker {

    /* renamed from: c, reason: collision with root package name */
    private int f1411c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ SparseArrayCompat f1412h;

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f1411c < this.f1412h.j();
    }

    @Override // java.util.Iterator
    public Object next() {
        SparseArrayCompat sparseArrayCompat = this.f1412h;
        int i2 = this.f1411c;
        this.f1411c = i2 + 1;
        return sparseArrayCompat.k(i2);
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
