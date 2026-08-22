package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;
import kotlinx.coroutines.internal.Symbol;

@Metadata
/* loaded from: classes2.dex */
public final class StateFlowKt {

    /* renamed from: a, reason: collision with root package name */
    private static final Symbol f19278a = new Symbol("NONE");

    /* renamed from: b, reason: collision with root package name */
    private static final Symbol f19279b = new Symbol("PENDING");

    public static final MutableStateFlow a(Object obj) {
        if (obj == null) {
            obj = NullSurrogateKt.f19324a;
        }
        return new StateFlowImpl(obj);
    }

    public static final Flow d(StateFlow stateFlow, CoroutineContext coroutineContext, int i2, BufferOverflow bufferOverflow) {
        return (((i2 < 0 || i2 >= 2) && i2 != -2) || bufferOverflow != BufferOverflow.DROP_OLDEST) ? SharedFlowKt.c(stateFlow, coroutineContext, i2, bufferOverflow) : stateFlow;
    }
}
