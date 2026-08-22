package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata
@DebugMetadata(c = "kotlinx.coroutines.flow.CancellableFlowImpl$collect$2", f = "Context.kt", l = {275}, m = "emit")
/* loaded from: classes2.dex */
final class CancellableFlowImpl$collect$2$emit$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ CancellableFlowImpl$collect$2<T> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    CancellableFlowImpl$collect$2$emit$1(CancellableFlowImpl$collect$2<? super T> cancellableFlowImpl$collect$2, Continuation<? super CancellableFlowImpl$collect$2$emit$1> continuation) {
        super(continuation);
        this.this$0 = cancellableFlowImpl$collect$2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object v(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.k(null, this);
    }
}
