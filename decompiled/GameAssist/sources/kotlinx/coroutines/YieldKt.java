package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.DispatchedContinuationKt;

@Metadata
/* loaded from: classes2.dex */
public final class YieldKt {
    public static final Object a(Continuation continuation) {
        Continuation c2;
        Object d2;
        Object d3;
        Object d4;
        CoroutineContext context = continuation.getContext();
        JobKt.g(context);
        c2 = IntrinsicsKt__IntrinsicsJvmKt.c(continuation);
        DispatchedContinuation dispatchedContinuation = c2 instanceof DispatchedContinuation ? (DispatchedContinuation) c2 : null;
        if (dispatchedContinuation == null) {
            d2 = Unit.f18288a;
        } else {
            if (dispatchedContinuation.f19350j.l0(context)) {
                dispatchedContinuation.l(context, Unit.f18288a);
            } else {
                YieldContext yieldContext = new YieldContext();
                CoroutineContext R = context.R(yieldContext);
                Unit unit = Unit.f18288a;
                dispatchedContinuation.l(R, unit);
                if (yieldContext.f18943h) {
                    d2 = DispatchedContinuationKt.d(dispatchedContinuation) ? IntrinsicsKt__IntrinsicsKt.d() : unit;
                }
            }
            d2 = IntrinsicsKt__IntrinsicsKt.d();
        }
        d3 = IntrinsicsKt__IntrinsicsKt.d();
        if (d2 == d3) {
            DebugProbesKt.c(continuation);
        }
        d4 = IntrinsicsKt__IntrinsicsKt.d();
        return d2 == d4 ? d2 : Unit.f18288a;
    }
}
