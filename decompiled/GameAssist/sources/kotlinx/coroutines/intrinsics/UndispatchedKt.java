package kotlinx.coroutines.intrinsics;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.CompletedExceptionally;
import kotlinx.coroutines.JobSupportKt;
import kotlinx.coroutines.TimeoutCancellationException;
import kotlinx.coroutines.internal.ScopeCoroutine;
import kotlinx.coroutines.internal.ThreadContextKt;

@Metadata
/* loaded from: classes2.dex */
public final class UndispatchedKt {
    public static final void a(Function2 function2, Object obj, Continuation continuation) {
        Object d2;
        Continuation a2 = DebugProbesKt.a(continuation);
        try {
            CoroutineContext context = continuation.getContext();
            Object c2 = ThreadContextKt.c(context, null);
            try {
                Object y = ((Function2) TypeIntrinsics.a(function2, 2)).y(obj, a2);
                d2 = IntrinsicsKt__IntrinsicsKt.d();
                if (y != d2) {
                    a2.g(Result.b(y));
                }
            } finally {
                ThreadContextKt.a(context, c2);
            }
        } catch (Throwable th) {
            Result.Companion companion = Result.Companion;
            a2.g(Result.b(ResultKt.a(th)));
        }
    }

    public static final void b(Function1 function1, Continuation continuation) {
        Object d2;
        Continuation a2 = DebugProbesKt.a(continuation);
        try {
            Object c2 = ((Function1) TypeIntrinsics.a(function1, 1)).c(a2);
            d2 = IntrinsicsKt__IntrinsicsKt.d();
            if (c2 != d2) {
                a2.g(Result.b(c2));
            }
        } catch (Throwable th) {
            Result.Companion companion = Result.Companion;
            a2.g(Result.b(ResultKt.a(th)));
        }
    }

    public static final void c(Function2 function2, Object obj, Continuation continuation) {
        Object d2;
        Continuation a2 = DebugProbesKt.a(continuation);
        try {
            Object y = ((Function2) TypeIntrinsics.a(function2, 2)).y(obj, a2);
            d2 = IntrinsicsKt__IntrinsicsKt.d();
            if (y != d2) {
                a2.g(Result.b(y));
            }
        } catch (Throwable th) {
            Result.Companion companion = Result.Companion;
            a2.g(Result.b(ResultKt.a(th)));
        }
    }

    public static final Object d(ScopeCoroutine scopeCoroutine, Object obj, Function2 function2) {
        Object completedExceptionally;
        Object d2;
        Object d3;
        Object d4;
        try {
            completedExceptionally = ((Function2) TypeIntrinsics.a(function2, 2)).y(obj, scopeCoroutine);
        } catch (Throwable th) {
            completedExceptionally = new CompletedExceptionally(th, false, 2, null);
        }
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        if (completedExceptionally == d2) {
            d4 = IntrinsicsKt__IntrinsicsKt.d();
            return d4;
        }
        Object D0 = scopeCoroutine.D0(completedExceptionally);
        if (D0 == JobSupportKt.f18912b) {
            d3 = IntrinsicsKt__IntrinsicsKt.d();
            return d3;
        }
        if (D0 instanceof CompletedExceptionally) {
            throw ((CompletedExceptionally) D0).f18845a;
        }
        return JobSupportKt.h(D0);
    }

    public static final Object e(ScopeCoroutine scopeCoroutine, Object obj, Function2 function2) {
        Object completedExceptionally;
        Object d2;
        Object d3;
        Object d4;
        try {
            completedExceptionally = ((Function2) TypeIntrinsics.a(function2, 2)).y(obj, scopeCoroutine);
        } catch (Throwable th) {
            completedExceptionally = new CompletedExceptionally(th, false, 2, null);
        }
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        if (completedExceptionally == d2) {
            d4 = IntrinsicsKt__IntrinsicsKt.d();
            return d4;
        }
        Object D0 = scopeCoroutine.D0(completedExceptionally);
        if (D0 == JobSupportKt.f18912b) {
            d3 = IntrinsicsKt__IntrinsicsKt.d();
            return d3;
        }
        if (D0 instanceof CompletedExceptionally) {
            Throwable th2 = ((CompletedExceptionally) D0).f18845a;
            if (!(th2 instanceof TimeoutCancellationException)) {
                throw th2;
            }
            if (((TimeoutCancellationException) th2).coroutine != scopeCoroutine) {
                throw th2;
            }
            if (completedExceptionally instanceof CompletedExceptionally) {
                throw ((CompletedExceptionally) completedExceptionally).f18845a;
            }
        } else {
            completedExceptionally = JobSupportKt.h(D0);
        }
        return completedExceptionally;
    }
}
