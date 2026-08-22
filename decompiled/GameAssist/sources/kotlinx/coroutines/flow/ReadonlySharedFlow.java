package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.internal.FusibleFlow;

@Metadata
/* loaded from: classes2.dex */
final class ReadonlySharedFlow<T> implements SharedFlow<T>, CancellableFlow<T>, FusibleFlow<T> {

    /* renamed from: c, reason: collision with root package name */
    private final /* synthetic */ SharedFlow f19249c;

    @Override // kotlinx.coroutines.flow.SharedFlow, kotlinx.coroutines.flow.Flow
    public Object a(FlowCollector flowCollector, Continuation continuation) {
        return this.f19249c.a(flowCollector, continuation);
    }

    @Override // kotlinx.coroutines.flow.internal.FusibleFlow
    public Flow b(CoroutineContext coroutineContext, int i2, BufferOverflow bufferOverflow) {
        return SharedFlowKt.c(this, coroutineContext, i2, bufferOverflow);
    }
}
