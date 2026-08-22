package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class DropSequence<T> implements Sequence<T>, DropTakeSequence<T> {

    /* renamed from: a, reason: collision with root package name */
    private final Sequence f18674a;

    /* renamed from: b, reason: collision with root package name */
    private final int f18675b;

    @Override // kotlin.sequences.Sequence
    public Iterator iterator() {
        return new DropSequence$iterator$1(this);
    }
}
