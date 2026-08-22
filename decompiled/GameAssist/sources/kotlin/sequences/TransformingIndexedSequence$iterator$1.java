package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: Add missing generic type declarations: [R] */
@Metadata
/* loaded from: classes2.dex */
public final class TransformingIndexedSequence$iterator$1<R> implements Iterator<R>, KMappedMarker {

    /* renamed from: c, reason: collision with root package name */
    private final Iterator f18749c;

    /* renamed from: h, reason: collision with root package name */
    private int f18750h;

    /* renamed from: i, reason: collision with root package name */
    final /* synthetic */ TransformingIndexedSequence f18751i;

    TransformingIndexedSequence$iterator$1(TransformingIndexedSequence transformingIndexedSequence) {
        Sequence sequence;
        this.f18751i = transformingIndexedSequence;
        sequence = transformingIndexedSequence.f18747a;
        this.f18749c = sequence.iterator();
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f18749c.hasNext();
    }

    @Override // java.util.Iterator
    public Object next() {
        Function2 function2;
        function2 = this.f18751i.f18748b;
        int i2 = this.f18750h;
        this.f18750h = i2 + 1;
        if (i2 < 0) {
            CollectionsKt__CollectionsKt.m();
        }
        return function2.y(Integer.valueOf(i2), this.f18749c.next());
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
