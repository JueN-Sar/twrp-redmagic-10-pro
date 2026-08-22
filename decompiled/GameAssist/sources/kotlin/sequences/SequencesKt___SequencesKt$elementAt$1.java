package kotlin.sequences;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata
/* loaded from: classes2.dex */
final class SequencesKt___SequencesKt$elementAt$1 extends Lambda implements Function1<Integer, Object> {
    final /* synthetic */ int $index;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SequencesKt___SequencesKt$elementAt$1(int i2) {
        super(1);
        this.$index = i2;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object c(Object obj) {
        return d(((Number) obj).intValue());
    }

    public final Object d(int i2) {
        throw new IndexOutOfBoundsException("Sequence doesn't contain element at index " + this.$index + '.');
    }
}
