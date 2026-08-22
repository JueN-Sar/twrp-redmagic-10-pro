package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.internal.ThreadContextKt;

@Metadata
/* loaded from: classes2.dex */
public final class ChannelFlowKt {
    public static final Object b(CoroutineContext coroutineContext, Object obj, Object obj2, Function2 function2, Continuation continuation) {
        Object d2;
        Object c2 = ThreadContextKt.c(coroutineContext, obj2);
        try {
            Object y = ((Function2) TypeIntrinsics.a(function2, 2)).y(obj, new StackFrameContinuation(continuation, coroutineContext));
            ThreadContextKt.a(coroutineContext, c2);
            d2 = IntrinsicsKt__IntrinsicsKt.d();
            if (y == d2) {
                DebugProbesKt.c(continuation);
            }
            return y;
        } catch (Throwable th) {
            ThreadContextKt.a(coroutineContext, c2);
            throw th;
        }
    }

    public static /* synthetic */ Object c(CoroutineContext coroutineContext, Object obj, Object obj2, Function2 function2, Continuation continuation, int i2, Object obj3) {
        if ((i2 & 4) != 0) {
            obj2 = ThreadContextKt.b(coroutineContext);
        }
        return b(coroutineContext, obj, obj2, function2, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FlowCollector d(FlowCollector flowCollector, CoroutineContext coroutineContext) {
        return ((flowCollector instanceof SendingCollector) || (flowCollector instanceof NopCollector)) ? flowCollector : new UndispatchedContextCollector(flowCollector, coroutineContext);
    }
}
