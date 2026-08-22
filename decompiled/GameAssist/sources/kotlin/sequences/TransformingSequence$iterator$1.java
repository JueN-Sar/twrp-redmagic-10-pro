package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: Add missing generic type declarations: [R] */
@Metadata
/* loaded from: classes2.dex */
public final class TransformingSequence$iterator$1<R> implements Iterator<R>, KMappedMarker {

    /* renamed from: c, reason: collision with root package name */
    private final Iterator f18754c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ TransformingSequence f18755h;

    TransformingSequence$iterator$1(TransformingSequence transformingSequence) {
        Sequence sequence;
        this.f18755h = transformingSequence;
        sequence = transformingSequence.f18752a;
        this.f18754c = sequence.iterator();
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f18754c.hasNext();
    }

    @Override // java.util.Iterator
    public Object next() {
        Function1 function1;
        function1 = this.f18755h.f18753b;
        return function1.c(this.f18754c.next());
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
