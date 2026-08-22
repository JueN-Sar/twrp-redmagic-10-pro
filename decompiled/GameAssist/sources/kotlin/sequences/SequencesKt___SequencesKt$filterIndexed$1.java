package kotlin.sequences;

import kotlin.Metadata;
import kotlin.collections.IndexedValue;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata
/* loaded from: classes2.dex */
final class SequencesKt___SequencesKt$filterIndexed$1 extends Lambda implements Function1<IndexedValue<Object>, Boolean> {
    final /* synthetic */ Function2<Integer, Object, Boolean> $predicate;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    SequencesKt___SequencesKt$filterIndexed$1(Function2<? super Integer, Object, Boolean> function2) {
        super(1);
        this.$predicate = function2;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public final Boolean c(IndexedValue it) {
        Intrinsics.e(it, "it");
        return (Boolean) this.$predicate.y(Integer.valueOf(it.a()), it.b());
    }
}
