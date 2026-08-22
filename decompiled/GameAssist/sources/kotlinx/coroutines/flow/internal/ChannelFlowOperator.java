package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata
/* loaded from: classes2.dex */
public abstract class ChannelFlowOperator<S, T> extends ChannelFlow<T> {

    /* renamed from: j, reason: collision with root package name */
    protected final Flow f19300j;

    public ChannelFlowOperator(Flow flow, CoroutineContext coroutineContext, int i2, BufferOverflow bufferOverflow) {
        super(coroutineContext, i2, bufferOverflow);
        this.f19300j = flow;
    }

    static /* synthetic */ Object m(ChannelFlowOperator channelFlowOperator, FlowCollector flowCollector, Continuation continuation) {
        Object d2;
        Object d3;
        Object d4;
        if (channelFlowOperator.f19292h == -3) {
            CoroutineContext context = continuation.getContext();
            CoroutineContext R = context.R(channelFlowOperator.f19291c);
            if (Intrinsics.a(R, context)) {
                Object p2 = channelFlowOperator.p(flowCollector, continuation);
                d4 = IntrinsicsKt__IntrinsicsKt.d();
                return p2 == d4 ? p2 : Unit.f18288a;
            }
            ContinuationInterceptor.Key key = ContinuationInterceptor.f18409d;
            if (Intrinsics.a(R.c(key), context.c(key))) {
                Object o2 = channelFlowOperator.o(flowCollector, R, continuation);
                d3 = IntrinsicsKt__IntrinsicsKt.d();
                return o2 == d3 ? o2 : Unit.f18288a;
            }
        }
        Object a2 = super.a(flowCollector, continuation);
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        return a2 == d2 ? a2 : Unit.f18288a;
    }

    static /* synthetic */ Object n(ChannelFlowOperator channelFlowOperator, ProducerScope producerScope, Continuation continuation) {
        Object d2;
        Object p2 = channelFlowOperator.p(new SendingCollector(producerScope), continuation);
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        return p2 == d2 ? p2 : Unit.f18288a;
    }

    private final Object o(FlowCollector flowCollector, CoroutineContext coroutineContext, Continuation continuation) {
        FlowCollector d2;
        Object d3;
        d2 = ChannelFlowKt.d(flowCollector, continuation.getContext());
        Object c2 = ChannelFlowKt.c(coroutineContext, d2, null, new ChannelFlowOperator$collectWithContextUndispatched$2(this, null), continuation, 4, null);
        d3 = IntrinsicsKt__IntrinsicsKt.d();
        return c2 == d3 ? c2 : Unit.f18288a;
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow, kotlinx.coroutines.flow.Flow
    public Object a(FlowCollector flowCollector, Continuation continuation) {
        return m(this, flowCollector, continuation);
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    protected Object f(ProducerScope producerScope, Continuation continuation) {
        return n(this, producerScope, continuation);
    }

    protected abstract Object p(FlowCollector flowCollector, Continuation continuation);

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    public String toString() {
        return this.f19300j + " -> " + super.toString();
    }
}
