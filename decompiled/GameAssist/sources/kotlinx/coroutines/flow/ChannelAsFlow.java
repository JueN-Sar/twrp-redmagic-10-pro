package kotlinx.coroutines.flow;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.internal.ChannelFlow;
import kotlinx.coroutines.flow.internal.SendingCollector;
import org.jetbrains.annotations.NotNull;

@Metadata
/* loaded from: classes2.dex */
final class ChannelAsFlow<T> extends ChannelFlow<T> {

    /* renamed from: l, reason: collision with root package name */
    private static final /* synthetic */ AtomicIntegerFieldUpdater f19090l = AtomicIntegerFieldUpdater.newUpdater(ChannelAsFlow.class, "consumed");

    @NotNull
    private volatile /* synthetic */ int consumed;

    /* renamed from: j, reason: collision with root package name */
    private final ReceiveChannel f19091j;

    /* renamed from: k, reason: collision with root package name */
    private final boolean f19092k;

    public ChannelAsFlow(ReceiveChannel receiveChannel, boolean z, CoroutineContext coroutineContext, int i2, BufferOverflow bufferOverflow) {
        super(coroutineContext, i2, bufferOverflow);
        this.f19091j = receiveChannel;
        this.f19092k = z;
        this.consumed = 0;
    }

    private final void m() {
        if (this.f19092k && f19090l.getAndSet(this, 1) != 0) {
            throw new IllegalStateException("ReceiveChannel.consumeAsFlow can be collected just once".toString());
        }
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow, kotlinx.coroutines.flow.Flow
    public Object a(FlowCollector flowCollector, Continuation continuation) {
        Object d2;
        Object c2;
        Object d3;
        if (this.f19292h != -3) {
            Object a2 = super.a(flowCollector, continuation);
            d2 = IntrinsicsKt__IntrinsicsKt.d();
            return a2 == d2 ? a2 : Unit.f18288a;
        }
        m();
        c2 = FlowKt__ChannelsKt.c(flowCollector, this.f19091j, this.f19092k, continuation);
        d3 = IntrinsicsKt__IntrinsicsKt.d();
        return c2 == d3 ? c2 : Unit.f18288a;
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    protected String c() {
        return "channel=" + this.f19091j;
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    protected Object f(ProducerScope producerScope, Continuation continuation) {
        Object c2;
        Object d2;
        c2 = FlowKt__ChannelsKt.c(new SendingCollector(producerScope), this.f19091j, this.f19092k, continuation);
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        return c2 == d2 ? c2 : Unit.f18288a;
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    protected ChannelFlow g(CoroutineContext coroutineContext, int i2, BufferOverflow bufferOverflow) {
        return new ChannelAsFlow(this.f19091j, this.f19092k, coroutineContext, i2, bufferOverflow);
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    public ReceiveChannel l(CoroutineScope coroutineScope) {
        m();
        return this.f19292h == -3 ? this.f19091j : super.l(coroutineScope);
    }
}
