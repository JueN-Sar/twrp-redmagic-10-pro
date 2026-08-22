package kotlin.jvm.internal;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata
/* loaded from: classes2.dex */
final class ArrayIterator<T> implements Iterator<T>, KMappedMarker {

    /* renamed from: c, reason: collision with root package name */
    private final Object[] f18541c;

    /* renamed from: h, reason: collision with root package name */
    private int f18542h;

    public ArrayIterator(Object[] array) {
        Intrinsics.e(array, "array");
        this.f18541c = array;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f18542h < this.f18541c.length;
    }

    @Override // java.util.Iterator
    public Object next() {
        try {
            Object[] objArr = this.f18541c;
            int i2 = this.f18542h;
            this.f18542h = i2 + 1;
            return objArr[i2];
        } catch (ArrayIndexOutOfBoundsException e2) {
            this.f18542h--;
            throw new NoSuchElementException(e2.getMessage());
        }
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
