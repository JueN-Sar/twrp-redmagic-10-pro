package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata
/* loaded from: classes2.dex */
public final class ChannelFlowOperatorImpl<T> extends ChannelFlowOperator<T, T> {
    public /* synthetic */ ChannelFlowOperatorImpl(Flow flow, CoroutineContext coroutineContext, int i2, BufferOverflow bufferOverflow, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(flow, (i3 & 2) != 0 ? EmptyCoroutineContext.INSTANCE : coroutineContext, (i3 & 4) != 0 ? -3 : i2, (i3 & 8) != 0 ? BufferOverflow.SUSPEND : bufferOverflow);
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    protected ChannelFlow g(CoroutineContext coroutineContext, int i2, BufferOverflow bufferOverflow) {
        return new ChannelFlowOperatorImpl(this.f19300j, coroutineContext, i2, bufferOverflow);
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlowOperator
    protected Object p(FlowCollector flowCollector, Continuation continuation) {
        Object d2;
        Object a2 = this.f19300j.a(flowCollector, continuation);
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        return a2 == d2 ? a2 : Unit.f18288a;
    }

    public ChannelFlowOperatorImpl(Flow flow, CoroutineContext coroutineContext, int i2, BufferOverflow bufferOverflow) {
        super(flow, coroutineContext, i2, bufferOverflow);
    }
}
