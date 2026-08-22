package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.sync.SemaphoreKt;

@Metadata
/* loaded from: classes2.dex */
public final class ChannelFlowMerge<T> extends ChannelFlow<T> {

    /* renamed from: j, reason: collision with root package name */
    private final Flow f19294j;

    /* renamed from: k, reason: collision with root package name */
    private final int f19295k;

    public ChannelFlowMerge(Flow flow, int i2, CoroutineContext coroutineContext, int i3, BufferOverflow bufferOverflow) {
        super(coroutineContext, i3, bufferOverflow);
        this.f19294j = flow;
        this.f19295k = i2;
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    protected String c() {
        return "concurrency=" + this.f19295k;
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    protected Object f(ProducerScope producerScope, Continuation continuation) {
        Object d2;
        Object a2 = this.f19294j.a(new ChannelFlowMerge$collectTo$2((Job) continuation.getContext().c(Job.f18898f), SemaphoreKt.b(this.f19295k, 0, 2, null), producerScope, new SendingCollector(producerScope)), continuation);
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        return a2 == d2 ? a2 : Unit.f18288a;
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    protected ChannelFlow g(CoroutineContext coroutineContext, int i2, BufferOverflow bufferOverflow) {
        return new ChannelFlowMerge(this.f19294j, this.f19295k, coroutineContext, i2, bufferOverflow);
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    public ReceiveChannel l(CoroutineScope coroutineScope) {
        return ProduceKt.b(coroutineScope, this.f19291c, this.f19292h, j());
    }
}
