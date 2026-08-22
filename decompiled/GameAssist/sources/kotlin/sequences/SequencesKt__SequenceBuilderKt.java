package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata
/* loaded from: classes2.dex */
public class SequencesKt__SequenceBuilderKt {
    public static Iterator a(Function2 block) {
        Continuation b2;
        Intrinsics.e(block, "block");
        SequenceBuilderIterator sequenceBuilderIterator = new SequenceBuilderIterator();
        b2 = IntrinsicsKt__IntrinsicsJvmKt.b(block, sequenceBuilderIterator, sequenceBuilderIterator);
        sequenceBuilderIterator.i(b2);
        return sequenceBuilderIterator;
    }

    public static Sequence b(final Function2 block) {
        Intrinsics.e(block, "block");
        return new Sequence<Object>() { // from class: kotlin.sequences.SequencesKt__SequenceBuilderKt$sequence$$inlined$Sequence$1
            @Override // kotlin.sequences.Sequence
            public Iterator iterator() {
                Iterator a2;
                a2 = SequencesKt__SequenceBuilderKt.a(Function2.this);
                return a2;
            }
        };
    }
}
