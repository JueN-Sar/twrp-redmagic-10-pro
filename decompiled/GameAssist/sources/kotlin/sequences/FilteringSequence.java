package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes2.dex */
public final class FilteringSequence<T> implements Sequence<T> {

    /* renamed from: a, reason: collision with root package name */
    private final Sequence f18685a;

    /* renamed from: b, reason: collision with root package name */
    private final boolean f18686b;

    /* renamed from: c, reason: collision with root package name */
    private final Function1 f18687c;

    public FilteringSequence(Sequence sequence, boolean z, Function1 predicate) {
        Intrinsics.e(sequence, "sequence");
        Intrinsics.e(predicate, "predicate");
        this.f18685a = sequence;
        this.f18686b = z;
        this.f18687c = predicate;
    }

    @Override // kotlin.sequences.Sequence
    public Iterator iterator() {
        return new FilteringSequence$iterator$1(this);
    }
}
