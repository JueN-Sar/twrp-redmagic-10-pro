package kotlin.sequences;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata
/* loaded from: classes2.dex */
final class SequencesKt___SequencesKt$onEach$1 extends Lambda implements Function1<Object, Object> {
    final /* synthetic */ Function1<Object, Unit> $action;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SequencesKt___SequencesKt$onEach$1(Function1<Object, Unit> function1) {
        super(1);
        this.$action = function1;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object c(Object obj) {
        this.$action.c(obj);
        return obj;
    }
}
