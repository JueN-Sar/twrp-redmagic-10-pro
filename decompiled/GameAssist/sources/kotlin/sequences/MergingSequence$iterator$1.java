package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: Add missing generic type declarations: [V] */
@Metadata
/* loaded from: classes2.dex */
public final class MergingSequence$iterator$1<V> implements Iterator<V>, KMappedMarker {

    /* renamed from: c, reason: collision with root package name */
    private final Iterator f18709c;

    /* renamed from: h, reason: collision with root package name */
    private final Iterator f18710h;

    /* renamed from: i, reason: collision with root package name */
    final /* synthetic */ MergingSequence f18711i;

    MergingSequence$iterator$1(MergingSequence mergingSequence) {
        Sequence sequence;
        Sequence sequence2;
        this.f18711i = mergingSequence;
        sequence = mergingSequence.f18706a;
        this.f18709c = sequence.iterator();
        sequence2 = mergingSequence.f18707b;
        this.f18710h = sequence2.iterator();
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f18709c.hasNext() && this.f18710h.hasNext();
    }

    @Override // java.util.Iterator
    public Object next() {
        Function2 function2;
        function2 = this.f18711i.f18708c;
        return function2.y(this.f18709c.next(), this.f18710h.next());
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
