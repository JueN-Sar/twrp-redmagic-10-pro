package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata
/* loaded from: classes2.dex */
public final class ChannelFlowTransformLatest<T, R> extends ChannelFlowOperator<T, R> {

    /* renamed from: k, reason: collision with root package name */
    private final Function3 f19301k;

    public /* synthetic */ ChannelFlowTransformLatest(Function3 function3, Flow flow, CoroutineContext coroutineContext, int i2, BufferOverflow bufferOverflow, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(function3, flow, (i3 & 4) != 0 ? EmptyCoroutineContext.INSTANCE : coroutineContext, (i3 & 8) != 0 ? -2 : i2, (i3 & 16) != 0 ? BufferOverflow.SUSPEND : bufferOverflow);
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    protected ChannelFlow g(CoroutineContext coroutineContext, int i2, BufferOverflow bufferOverflow) {
        return new ChannelFlowTransformLatest(this.f19301k, this.f19300j, coroutineContext, i2, bufferOverflow);
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlowOperator
    protected Object p(FlowCollector flowCollector, Continuation continuation) {
        Object d2;
        Object a2 = CoroutineScopeKt.a(new ChannelFlowTransformLatest$flowCollect$3(this, flowCollector, null), continuation);
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        return a2 == d2 ? a2 : Unit.f18288a;
    }

    public ChannelFlowTransformLatest(Function3 function3, Flow flow, CoroutineContext coroutineContext, int i2, BufferOverflow bufferOverflow) {
        super(flow, coroutineContext, i2, bufferOverflow);
        this.f19301k = function3;
    }
}
