package kotlinx.coroutines.flow.internal;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.Flow;

@Metadata
/* loaded from: classes2.dex */
public final class ChannelLimitedFlowMerge<T> extends ChannelFlow<T> {

    /* renamed from: j, reason: collision with root package name */
    private final Iterable f19306j;

    public ChannelLimitedFlowMerge(Iterable iterable, CoroutineContext coroutineContext, int i2, BufferOverflow bufferOverflow) {
        super(coroutineContext, i2, bufferOverflow);
        this.f19306j = iterable;
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    protected Object f(ProducerScope producerScope, Continuation continuation) {
        SendingCollector sendingCollector = new SendingCollector(producerScope);
        Iterator<T> it = this.f19306j.iterator();
        while (it.hasNext()) {
            BuildersKt__Builders_commonKt.b(producerScope, null, null, new ChannelLimitedFlowMerge$collectTo$2$1((Flow) it.next(), sendingCollector, null), 3, null);
        }
        return Unit.f18288a;
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    protected ChannelFlow g(CoroutineContext coroutineContext, int i2, BufferOverflow bufferOverflow) {
        return new ChannelLimitedFlowMerge(this.f19306j, coroutineContext, i2, bufferOverflow);
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    public ReceiveChannel l(CoroutineScope coroutineScope) {
        return ProduceKt.b(coroutineScope, this.f19291c, this.f19292h, j());
    }
}
