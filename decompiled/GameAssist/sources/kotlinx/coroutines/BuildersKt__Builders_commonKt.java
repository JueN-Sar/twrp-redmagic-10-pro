package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.ScopeCoroutine;
import kotlinx.coroutines.internal.ThreadContextKt;
import kotlinx.coroutines.intrinsics.CancellableKt;
import kotlinx.coroutines.intrinsics.UndispatchedKt;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata
/* loaded from: classes2.dex */
public final /* synthetic */ class BuildersKt__Builders_commonKt {
    public static final Job a(CoroutineScope coroutineScope, CoroutineContext coroutineContext, CoroutineStart coroutineStart, Function2 function2) {
        CoroutineContext e2 = CoroutineContextKt.e(coroutineScope, coroutineContext);
        StandaloneCoroutine lazyStandaloneCoroutine = coroutineStart.e() ? new LazyStandaloneCoroutine(e2, function2) : new StandaloneCoroutine(e2, true);
        lazyStandaloneCoroutine.f1(coroutineStart, lazyStandaloneCoroutine, function2);
        return lazyStandaloneCoroutine;
    }

    public static /* synthetic */ Job b(CoroutineScope coroutineScope, CoroutineContext coroutineContext, CoroutineStart coroutineStart, Function2 function2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        if ((i2 & 2) != 0) {
            coroutineStart = CoroutineStart.DEFAULT;
        }
        return BuildersKt.a(coroutineScope, coroutineContext, coroutineStart, function2);
    }

    public static final Object c(CoroutineContext coroutineContext, Function2 function2, Continuation continuation) {
        Object h1;
        Object d2;
        CoroutineContext context = continuation.getContext();
        CoroutineContext d3 = CoroutineContextKt.d(context, coroutineContext);
        JobKt.g(d3);
        if (d3 == context) {
            ScopeCoroutine scopeCoroutine = new ScopeCoroutine(d3, continuation);
            h1 = UndispatchedKt.d(scopeCoroutine, scopeCoroutine, function2);
        } else {
            ContinuationInterceptor.Key key = ContinuationInterceptor.f18409d;
            if (Intrinsics.a(d3.c(key), context.c(key))) {
                UndispatchedCoroutine undispatchedCoroutine = new UndispatchedCoroutine(d3, continuation);
                Object c2 = ThreadContextKt.c(d3, null);
                try {
                    Object d4 = UndispatchedKt.d(undispatchedCoroutine, undispatchedCoroutine, function2);
                    ThreadContextKt.a(d3, c2);
                    h1 = d4;
                } catch (Throwable th) {
                    ThreadContextKt.a(d3, c2);
                    throw th;
                }
            } else {
                DispatchedCoroutine dispatchedCoroutine = new DispatchedCoroutine(d3, continuation);
                CancellableKt.e(function2, dispatchedCoroutine, dispatchedCoroutine, null, 4, null);
                h1 = dispatchedCoroutine.h1();
            }
        }
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        if (h1 == d2) {
            DebugProbesKt.c(continuation);
        }
        return h1;
    }
}
