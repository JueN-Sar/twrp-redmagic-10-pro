package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.EmptyIterator;

@Metadata
/* loaded from: classes2.dex */
final class EmptySequence implements Sequence, DropTakeSequence {

    /* renamed from: a, reason: collision with root package name */
    public static final EmptySequence f18684a = new EmptySequence();

    private EmptySequence() {
    }

    @Override // kotlin.sequences.Sequence
    public Iterator iterator() {
        return EmptyIterator.f18343c;
    }
}
