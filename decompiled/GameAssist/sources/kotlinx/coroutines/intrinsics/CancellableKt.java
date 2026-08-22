package kotlinx.coroutines.intrinsics;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.internal.DispatchedContinuationKt;

@Metadata
/* loaded from: classes2.dex */
public final class CancellableKt {
    private static final void a(Continuation continuation, Throwable th) {
        Result.Companion companion = Result.Companion;
        continuation.g(Result.b(ResultKt.a(th)));
        throw th;
    }

    public static final void b(Continuation continuation, Continuation continuation2) {
        Continuation c2;
        try {
            c2 = IntrinsicsKt__IntrinsicsJvmKt.c(continuation);
            Result.Companion companion = Result.Companion;
            DispatchedContinuationKt.c(c2, Result.b(Unit.f18288a), null, 2, null);
        } catch (Throwable th) {
            a(continuation2, th);
        }
    }

    public static final void c(Function1 function1, Continuation continuation) {
        Continuation a2;
        Continuation c2;
        try {
            a2 = IntrinsicsKt__IntrinsicsJvmKt.a(function1, continuation);
            c2 = IntrinsicsKt__IntrinsicsJvmKt.c(a2);
            Result.Companion companion = Result.Companion;
            DispatchedContinuationKt.c(c2, Result.b(Unit.f18288a), null, 2, null);
        } catch (Throwable th) {
            a(continuation, th);
        }
    }

    public static final void d(Function2 function2, Object obj, Continuation continuation, Function1 function1) {
        Continuation b2;
        Continuation c2;
        try {
            b2 = IntrinsicsKt__IntrinsicsJvmKt.b(function2, obj, continuation);
            c2 = IntrinsicsKt__IntrinsicsJvmKt.c(b2);
            Result.Companion companion = Result.Companion;
            DispatchedContinuationKt.b(c2, Result.b(Unit.f18288a), function1);
        } catch (Throwable th) {
            a(continuation, th);
        }
    }

    public static /* synthetic */ void e(Function2 function2, Object obj, Continuation continuation, Function1 function1, int i2, Object obj2) {
        if ((i2 & 4) != 0) {
            function1 = null;
        }
        d(function2, obj, continuation, function1);
    }
}
