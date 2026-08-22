package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.internal.FusibleFlow;

@Metadata
/* loaded from: classes2.dex */
final class ReadonlyStateFlow<T> implements StateFlow<T>, CancellableFlow<T>, FusibleFlow<T> {

    /* renamed from: c, reason: collision with root package name */
    private final Job f19250c;

    /* renamed from: h, reason: collision with root package name */
    private final /* synthetic */ StateFlow f19251h;

    public ReadonlyStateFlow(StateFlow stateFlow, Job job) {
        this.f19250c = job;
        this.f19251h = stateFlow;
    }

    @Override // kotlinx.coroutines.flow.SharedFlow, kotlinx.coroutines.flow.Flow
    public Object a(FlowCollector flowCollector, Continuation continuation) {
        return this.f19251h.a(flowCollector, continuation);
    }

    @Override // kotlinx.coroutines.flow.internal.FusibleFlow
    public Flow b(CoroutineContext coroutineContext, int i2, BufferOverflow bufferOverflow) {
        return StateFlowKt.d(this, coroutineContext, i2, bufferOverflow);
    }
}
