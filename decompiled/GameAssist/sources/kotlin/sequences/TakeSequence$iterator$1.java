package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: Add missing generic type declarations: [T] */
@Metadata
/* loaded from: classes2.dex */
public final class TakeSequence$iterator$1<T> implements Iterator<T>, KMappedMarker {

    /* renamed from: c, reason: collision with root package name */
    private int f18739c;

    /* renamed from: h, reason: collision with root package name */
    private final Iterator f18740h;

    TakeSequence$iterator$1(TakeSequence takeSequence) {
        int i2;
        Sequence sequence;
        i2 = takeSequence.f18738b;
        this.f18739c = i2;
        sequence = takeSequence.f18737a;
        this.f18740h = sequence.iterator();
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f18739c > 0 && this.f18740h.hasNext();
    }

    @Override // java.util.Iterator
    public Object next() {
        int i2 = this.f18739c;
        if (i2 == 0) {
            throw new NoSuchElementException();
        }
        this.f18739c = i2 - 1;
        return this.f18740h.next();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
