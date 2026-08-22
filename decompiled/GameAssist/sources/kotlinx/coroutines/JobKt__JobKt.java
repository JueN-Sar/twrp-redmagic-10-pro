package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata
/* loaded from: classes2.dex */
public final /* synthetic */ class JobKt__JobKt {
    public static final CompletableJob a(Job job) {
        return new JobImpl(job);
    }

    public static /* synthetic */ CompletableJob b(Job job, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            job = null;
        }
        return JobKt.a(job);
    }

    public static final void c(CoroutineContext coroutineContext, CancellationException cancellationException) {
        Job job = (Job) coroutineContext.c(Job.f18898f);
        if (job != null) {
            job.a(cancellationException);
        }
    }

    public static /* synthetic */ void d(CoroutineContext coroutineContext, CancellationException cancellationException, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            cancellationException = null;
        }
        JobKt.c(coroutineContext, cancellationException);
    }

    public static final DisposableHandle e(Job job, DisposableHandle disposableHandle) {
        return job.A(new DisposeOnCompletion(disposableHandle));
    }

    public static final void f(CoroutineContext coroutineContext) {
        Job job = (Job) coroutineContext.c(Job.f18898f);
        if (job != null) {
            JobKt.h(job);
        }
    }

    public static final void g(Job job) {
        if (!job.isActive()) {
            throw job.s();
        }
    }

    public static final Job h(CoroutineContext coroutineContext) {
        Job job = (Job) coroutineContext.c(Job.f18898f);
        if (job != null) {
            return job;
        }
        throw new IllegalStateException(("Current context doesn't contain Job in it: " + coroutineContext).toString());
    }
}
