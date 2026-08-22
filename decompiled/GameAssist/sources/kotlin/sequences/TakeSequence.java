package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class TakeSequence<T> implements Sequence<T>, DropTakeSequence<T> {

    /* renamed from: a, reason: collision with root package name */
    private final Sequence f18737a;

    /* renamed from: b, reason: collision with root package name */
    private final int f18738b;

    @Override // kotlin.sequences.Sequence
    public Iterator iterator() {
        return new TakeSequence$iterator$1(this);
    }
}
