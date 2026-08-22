package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.IndexedValue;

@Metadata
/* loaded from: classes2.dex */
public final class IndexingSequence<T> implements Sequence<IndexedValue<? extends T>> {

    /* renamed from: a, reason: collision with root package name */
    private final Sequence f18703a;

    @Override // kotlin.sequences.Sequence
    public Iterator iterator() {
        return new IndexingSequence$iterator$1(this);
    }
}
