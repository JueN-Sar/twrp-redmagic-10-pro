package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.intrinsics.UndispatchedKt;

@Metadata
/* loaded from: classes2.dex */
public final class FlowCoroutineKt {
    public static final Object a(Function2 function2, Continuation continuation) {
        Object d2;
        FlowCoroutine flowCoroutine = new FlowCoroutine(continuation.getContext(), continuation);
        Object d3 = UndispatchedKt.d(flowCoroutine, flowCoroutine, function2);
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        if (d3 == d2) {
            DebugProbesKt.c(continuation);
        }
        return d3;
    }
}
