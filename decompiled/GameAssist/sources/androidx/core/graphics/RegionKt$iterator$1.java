package androidx.core.graphics;

import android.graphics.Rect;
import android.graphics.RegionIterator;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata
/* loaded from: classes.dex */
public final class RegionKt$iterator$1 implements Iterator<Rect>, KMappedMarker {

    /* renamed from: c, reason: collision with root package name */
    private final RegionIterator f2933c;

    /* renamed from: h, reason: collision with root package name */
    private final Rect f2934h;

    /* renamed from: i, reason: collision with root package name */
    private boolean f2935i;

    @Override // java.util.Iterator
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public Rect next() {
        if (!this.f2935i) {
            throw new IndexOutOfBoundsException();
        }
        Rect rect = new Rect(this.f2934h);
        this.f2935i = this.f2933c.next(this.f2934h);
        return rect;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f2935i;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
