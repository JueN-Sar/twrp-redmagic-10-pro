package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;

@Metadata
/* loaded from: classes2.dex */
public final class MergingSequence<T1, T2, V> implements Sequence<V> {

    /* renamed from: a, reason: collision with root package name */
    private final Sequence f18706a;

    /* renamed from: b, reason: collision with root package name */
    private final Sequence f18707b;

    /* renamed from: c, reason: collision with root package name */
    private final Function2 f18708c;

    @Override // kotlin.sequences.Sequence
    public Iterator iterator() {
        return new MergingSequence$iterator$1(this);
    }
}
