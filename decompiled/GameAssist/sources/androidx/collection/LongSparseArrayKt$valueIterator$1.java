package androidx.collection;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata
/* loaded from: classes.dex */
public final class LongSparseArrayKt$valueIterator$1 implements Iterator<Object>, KMappedMarker {

    /* renamed from: c, reason: collision with root package name */
    private int f1300c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ LongSparseArray f1301h;

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f1300c < this.f1301h.n();
    }

    @Override // java.util.Iterator
    public Object next() {
        LongSparseArray longSparseArray = this.f1301h;
        int i2 = this.f1300c;
        this.f1300c = i2 + 1;
        return longSparseArray.o(i2);
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
