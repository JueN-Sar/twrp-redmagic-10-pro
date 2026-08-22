package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.internal.ChannelFlowOperatorImpl;
import kotlinx.coroutines.internal.Symbol;

@Metadata
/* loaded from: classes2.dex */
public final class SharedFlowKt {

    /* renamed from: a, reason: collision with root package name */
    public static final Symbol f19266a = new Symbol("NO_VALUE");

    public static final Flow c(SharedFlow sharedFlow, CoroutineContext coroutineContext, int i2, BufferOverflow bufferOverflow) {
        return ((i2 == 0 || i2 == -3) && bufferOverflow == BufferOverflow.SUSPEND) ? sharedFlow : new ChannelFlowOperatorImpl(sharedFlow, coroutineContext, i2, bufferOverflow);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object d(Object[] objArr, long j2) {
        return objArr[((int) j2) & (objArr.length - 1)];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void e(Object[] objArr, long j2, Object obj) {
        objArr[((int) j2) & (objArr.length - 1)] = obj;
    }
}
