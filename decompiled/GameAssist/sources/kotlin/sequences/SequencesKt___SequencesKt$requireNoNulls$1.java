package kotlin.sequences;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata
/* loaded from: classes2.dex */
final class SequencesKt___SequencesKt$requireNoNulls$1 extends Lambda implements Function1<Object, Object> {
    final /* synthetic */ Sequence<Object> $this_requireNoNulls;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SequencesKt___SequencesKt$requireNoNulls$1(Sequence<Object> sequence) {
        super(1);
        this.$this_requireNoNulls = sequence;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object c(Object obj) {
        if (obj != null) {
            return obj;
        }
        throw new IllegalArgumentException("null element found in " + this.$this_requireNoNulls + '.');
    }
}
