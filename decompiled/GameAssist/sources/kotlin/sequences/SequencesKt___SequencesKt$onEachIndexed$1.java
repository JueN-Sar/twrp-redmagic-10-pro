package kotlin.sequences;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

@Metadata
/* loaded from: classes2.dex */
final class SequencesKt___SequencesKt$onEachIndexed$1 extends Lambda implements Function2<Integer, Object, Object> {
    final /* synthetic */ Function2<Integer, Object, Unit> $action;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    SequencesKt___SequencesKt$onEachIndexed$1(Function2<? super Integer, Object, Unit> function2) {
        super(2);
        this.$action = function2;
    }

    public final Object d(int i2, Object obj) {
        this.$action.y(Integer.valueOf(i2), obj);
        return obj;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object y(Object obj, Object obj2) {
        return d(((Number) obj).intValue(), obj2);
    }
}
