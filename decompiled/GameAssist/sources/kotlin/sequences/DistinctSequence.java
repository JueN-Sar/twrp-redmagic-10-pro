package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

@Metadata
/* loaded from: classes2.dex */
public final class DistinctSequence<T, K> implements Sequence<T> {

    /* renamed from: a, reason: collision with root package name */
    private final Sequence f18672a;

    /* renamed from: b, reason: collision with root package name */
    private final Function1 f18673b;

    @Override // kotlin.sequences.Sequence
    public Iterator iterator() {
        return new DistinctIterator(this.f18672a.iterator(), this.f18673b);
    }
}
