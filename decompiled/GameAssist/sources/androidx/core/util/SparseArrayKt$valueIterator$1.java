package androidx.core.util;

import android.util.SparseArray;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata
/* loaded from: classes.dex */
public final class SparseArrayKt$valueIterator$1 implements Iterator<Object>, KMappedMarker {

    /* renamed from: c, reason: collision with root package name */
    private int f3290c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ SparseArray f3291h;

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f3290c < this.f3291h.size();
    }

    @Override // java.util.Iterator
    public Object next() {
        SparseArray sparseArray = this.f3291h;
        int i2 = this.f3290c;
        this.f3290c = i2 + 1;
        return sparseArray.valueAt(i2);
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
