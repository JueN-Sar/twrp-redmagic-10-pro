package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;

@Metadata
/* loaded from: classes2.dex */
public final class TransformingIndexedSequence<T, R> implements Sequence<R> {

    /* renamed from: a, reason: collision with root package name */
    private final Sequence f18747a;

    /* renamed from: b, reason: collision with root package name */
    private final Function2 f18748b;

    @Override // kotlin.sequences.Sequence
    public Iterator iterator() {
        return new TransformingIndexedSequence$iterator$1(this);
    }
}
