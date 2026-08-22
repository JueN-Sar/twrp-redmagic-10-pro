package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes2.dex */
public final class TransformingSequence<T, R> implements Sequence<R> {

    /* renamed from: a, reason: collision with root package name */
    private final Sequence f18752a;

    /* renamed from: b, reason: collision with root package name */
    private final Function1 f18753b;

    public TransformingSequence(Sequence sequence, Function1 transformer) {
        Intrinsics.e(sequence, "sequence");
        Intrinsics.e(transformer, "transformer");
        this.f18752a = sequence;
        this.f18753b = transformer;
    }

    @Override // kotlin.sequences.Sequence
    public Iterator iterator() {
        return new TransformingSequence$iterator$1(this);
    }
}
