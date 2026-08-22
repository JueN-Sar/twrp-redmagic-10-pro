package kotlin.sequences;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes2.dex */
public final class ConstrainedOnceSequence<T> implements Sequence<T> {

    /* renamed from: a, reason: collision with root package name */
    private final AtomicReference f18668a;

    public ConstrainedOnceSequence(Sequence sequence) {
        Intrinsics.e(sequence, "sequence");
        this.f18668a = new AtomicReference(sequence);
    }

    @Override // kotlin.sequences.Sequence
    public Iterator iterator() {
        Sequence sequence = (Sequence) this.f18668a.getAndSet(null);
        if (sequence != null) {
            return sequence.iterator();
        }
        throw new IllegalStateException("This sequence can be consumed only once.");
    }
}
