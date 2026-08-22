package kotlinx.coroutines;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;

@Metadata
/* loaded from: classes2.dex */
public final class ExecutorCoroutineDispatcherImpl extends ExecutorCoroutineDispatcher implements Delay {

    /* renamed from: j, reason: collision with root package name */
    private final Executor f18890j;

    private final void n0(CoroutineContext coroutineContext, RejectedExecutionException rejectedExecutionException) {
        JobKt.c(coroutineContext, ExceptionsKt.a("The task was rejected", rejectedExecutionException));
    }

    private final ScheduledFuture p0(ScheduledExecutorService scheduledExecutorService, Runnable runnable, CoroutineContext coroutineContext, long j2) {
        try {
            return scheduledExecutorService.schedule(runnable, j2, TimeUnit.MILLISECONDS);
        } catch (RejectedExecutionException e2) {
            this.n0(coroutineContext, e2);
            return null;
        }
    }

    @Override // kotlinx.coroutines.Delay
    public DisposableHandle B(long j2, Runnable runnable, CoroutineContext coroutineContext) {
        Executor o0 = o0();
        ScheduledExecutorService scheduledExecutorService = o0 instanceof ScheduledExecutorService ? (ScheduledExecutorService) o0 : null;
        ScheduledFuture p0 = scheduledExecutorService != null ? p0(scheduledExecutorService, runnable, coroutineContext, j2) : null;
        return p0 != null ? new DisposableFutureHandle(p0) : DefaultExecutor.f18861n.B(j2, runnable, coroutineContext);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        Executor o0 = o0();
        ExecutorService executorService = o0 instanceof ExecutorService ? (ExecutorService) o0 : null;
        if (executorService != null) {
            executorService.shutdown();
        }
    }

    public boolean equals(Object obj) {
        return (obj instanceof ExecutorCoroutineDispatcherImpl) && ((ExecutorCoroutineDispatcherImpl) obj).o0() == o0();
    }

    public int hashCode() {
        return System.identityHashCode(o0());
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public void j0(CoroutineContext coroutineContext, Runnable runnable) {
        Runnable runnable2;
        try {
            Executor o0 = o0();
            AbstractTimeSource a2 = AbstractTimeSourceKt.a();
            if (a2 != null) {
                runnable2 = a2.h(runnable);
                if (runnable2 == null) {
                }
                o0.execute(runnable2);
            }
            runnable2 = runnable;
            o0.execute(runnable2);
        } catch (RejectedExecutionException e2) {
            AbstractTimeSource a3 = AbstractTimeSourceKt.a();
            if (a3 != null) {
                a3.e();
            }
            n0(coroutineContext, e2);
            Dispatchers.b().j0(coroutineContext, runnable);
        }
    }

    @Override // kotlinx.coroutines.Delay
    public void k(long j2, CancellableContinuation cancellableContinuation) {
        Executor o0 = o0();
        ScheduledExecutorService scheduledExecutorService = o0 instanceof ScheduledExecutorService ? (ScheduledExecutorService) o0 : null;
        ScheduledFuture p0 = scheduledExecutorService != null ? p0(scheduledExecutorService, new ResumeUndispatchedRunnable(this, cancellableContinuation), cancellableContinuation.getContext(), j2) : null;
        if (p0 != null) {
            JobKt.e(cancellableContinuation, p0);
        } else {
            DefaultExecutor.f18861n.k(j2, cancellableContinuation);
        }
    }

    public Executor o0() {
        return this.f18890j;
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public String toString() {
        return o0().toString();
    }
}
