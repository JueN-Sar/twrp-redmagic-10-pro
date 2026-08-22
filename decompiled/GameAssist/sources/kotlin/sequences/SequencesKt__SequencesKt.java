package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata
/* loaded from: classes2.dex */
public class SequencesKt__SequencesKt extends SequencesKt__SequencesJVMKt {
    public static Sequence c(final Iterator it) {
        Intrinsics.e(it, "<this>");
        return d(new Sequence<Object>() { // from class: kotlin.sequences.SequencesKt__SequencesKt$asSequence$$inlined$Sequence$1
            @Override // kotlin.sequences.Sequence
            public Iterator iterator() {
                return it;
            }
        });
    }

    public static final Sequence d(Sequence sequence) {
        Intrinsics.e(sequence, "<this>");
        return sequence instanceof ConstrainedOnceSequence ? sequence : new ConstrainedOnceSequence(sequence);
    }

    public static Sequence e(final Object obj, Function1 nextFunction) {
        Intrinsics.e(nextFunction, "nextFunction");
        return obj == null ? EmptySequence.f18684a : new GeneratorSequence(new Function0<Object>() { // from class: kotlin.sequences.SequencesKt__SequencesKt$generateSequence$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object a() {
                return obj;
            }
        }, nextFunction);
    }
}
