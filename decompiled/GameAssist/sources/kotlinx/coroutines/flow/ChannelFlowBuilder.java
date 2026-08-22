package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.flow.internal.ChannelFlow;

@Metadata
/* loaded from: classes2.dex */
class ChannelFlowBuilder<T> extends ChannelFlow<T> {

    /* renamed from: j, reason: collision with root package name */
    private final Function2 f19093j;

    public ChannelFlowBuilder(Function2 function2, CoroutineContext coroutineContext, int i2, BufferOverflow bufferOverflow) {
        super(coroutineContext, i2, bufferOverflow);
        this.f19093j = function2;
    }

    static /* synthetic */ Object m(ChannelFlowBuilder channelFlowBuilder, ProducerScope producerScope, Continuation continuation) {
        Object d2;
        Object y = channelFlowBuilder.f19093j.y(producerScope, continuation);
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        return y == d2 ? y : Unit.f18288a;
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    protected Object f(ProducerScope producerScope, Continuation continuation) {
        return m(this, producerScope, continuation);
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    protected ChannelFlow g(CoroutineContext coroutineContext, int i2, BufferOverflow bufferOverflow) {
        return new ChannelFlowBuilder(this.f19093j, coroutineContext, i2, bufferOverflow);
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    public String toString() {
        return "block[" + this.f19093j + "] -> " + super.toString();
    }
}
