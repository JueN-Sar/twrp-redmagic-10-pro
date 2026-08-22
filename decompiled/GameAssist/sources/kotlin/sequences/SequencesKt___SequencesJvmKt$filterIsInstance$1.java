package kotlin.sequences;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata
/* loaded from: classes2.dex */
final class SequencesKt___SequencesJvmKt$filterIsInstance$1 extends Lambda implements Function1<Object, Boolean> {
    final /* synthetic */ Class<Object> $klass;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SequencesKt___SequencesJvmKt$filterIsInstance$1(Class<Object> cls) {
        super(1);
        this.$klass = cls;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public final Boolean c(Object obj) {
        return Boolean.valueOf(this.$klass.isInstance(obj));
    }
}
