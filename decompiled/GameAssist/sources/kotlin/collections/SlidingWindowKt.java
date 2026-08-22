package kotlin.collections;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt__SequenceBuilderKt;

@Metadata
/* loaded from: classes2.dex */
public final class SlidingWindowKt {
    public static final Iterator a(Iterator iterator, int i2, int i3, boolean z, boolean z2) {
        Iterator a2;
        Intrinsics.e(iterator, "iterator");
        if (!iterator.hasNext()) {
            return EmptyIterator.f18343c;
        }
        a2 = SequencesKt__SequenceBuilderKt.a(new SlidingWindowKt$windowedIterator$1(i2, i3, iterator, z2, z, null));
        return a2;
    }
}
