package kotlin.sequences;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata
/* loaded from: classes2.dex */
final class SequencesKt__SequencesKt$generateSequence$1 extends Lambda implements Function1<Object, Object> {
    final /* synthetic */ Function0<Object> $nextFunction;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SequencesKt__SequencesKt$generateSequence$1(Function0<Object> function0) {
        super(1);
        this.$nextFunction = function0;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object c(Object it) {
        Intrinsics.e(it, "it");
        return this.$nextFunction.a();
    }
}
