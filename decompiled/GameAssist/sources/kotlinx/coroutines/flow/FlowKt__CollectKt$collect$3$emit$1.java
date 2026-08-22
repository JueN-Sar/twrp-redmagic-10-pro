package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

@Metadata
/* loaded from: classes2.dex */
public final class FlowKt__CollectKt$collect$3$emit$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ FlowKt__CollectKt$collect$3 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowKt__CollectKt$collect$3$emit$1(FlowKt__CollectKt$collect$3 flowKt__CollectKt$collect$3, Continuation<? super FlowKt__CollectKt$collect$3$emit$1> continuation) {
        super(continuation);
        this.this$0 = flowKt__CollectKt$collect$3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object v(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.k(null, this);
    }
}
