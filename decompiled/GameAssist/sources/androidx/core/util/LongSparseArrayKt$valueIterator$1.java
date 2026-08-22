package androidx.core.util;

import android.util.LongSparseArray;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata
/* loaded from: classes.dex */
public final class LongSparseArrayKt$valueIterator$1 implements Iterator<Object>, KMappedMarker {

    /* renamed from: c, reason: collision with root package name */
    private int f3265c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ LongSparseArray f3266h;

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f3265c < this.f3266h.size();
    }

    @Override // java.util.Iterator
    public Object next() {
        LongSparseArray longSparseArray = this.f3266h;
        int i2 = this.f3265c;
        this.f3265c = i2 + 1;
        return longSparseArray.valueAt(i2);
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
