package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: Add missing generic type declarations: [T] */
@Metadata
/* loaded from: classes2.dex */
public final class DropSequence$iterator$1<T> implements Iterator<T>, KMappedMarker {

    /* renamed from: c, reason: collision with root package name */
    private final Iterator f18676c;

    /* renamed from: h, reason: collision with root package name */
    private int f18677h;

    DropSequence$iterator$1(DropSequence dropSequence) {
        Sequence sequence;
        int i2;
        sequence = dropSequence.f18674a;
        this.f18676c = sequence.iterator();
        i2 = dropSequence.f18675b;
        this.f18677h = i2;
    }

    private final void b() {
        while (this.f18677h > 0 && this.f18676c.hasNext()) {
            this.f18676c.next();
            this.f18677h--;
        }
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        b();
        return this.f18676c.hasNext();
    }

    @Override // java.util.Iterator
    public Object next() {
        b();
        return this.f18676c.next();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
