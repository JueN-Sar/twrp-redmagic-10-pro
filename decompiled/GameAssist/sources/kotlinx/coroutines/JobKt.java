package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import java.util.concurrent.Future;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;

@Metadata
/* loaded from: classes2.dex */
public final class JobKt {
    public static final CompletableJob a(Job job) {
        return JobKt__JobKt.a(job);
    }

    public static final void c(CoroutineContext coroutineContext, CancellationException cancellationException) {
        JobKt__JobKt.c(coroutineContext, cancellationException);
    }

    public static final void e(CancellableContinuation cancellableContinuation, Future future) {
        JobKt__FutureKt.a(cancellableContinuation, future);
    }

    public static final DisposableHandle f(Job job, DisposableHandle disposableHandle) {
        return JobKt__JobKt.e(job, disposableHandle);
    }

    public static final void g(CoroutineContext coroutineContext) {
        JobKt__JobKt.f(coroutineContext);
    }

    public static final void h(Job job) {
        JobKt__JobKt.g(job);
    }

    public static final Job i(CoroutineContext coroutineContext) {
        return JobKt__JobKt.h(coroutineContext);
    }
}
