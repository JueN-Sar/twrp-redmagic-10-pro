package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

@Metadata
/* loaded from: classes2.dex */
public final class FlatteningSequence<T, R, E> implements Sequence<E> {

    /* renamed from: a, reason: collision with root package name */
    private final Sequence f18692a;

    /* renamed from: b, reason: collision with root package name */
    private final Function1 f18693b;

    /* renamed from: c, reason: collision with root package name */
    private final Function1 f18694c;

    @Override // kotlin.sequences.Sequence
    public Iterator iterator() {
        return new FlatteningSequence$iterator$1(this);
    }
}
