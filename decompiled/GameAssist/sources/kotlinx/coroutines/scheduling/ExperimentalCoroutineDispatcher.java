package kotlinx.coroutines.scheduling;

import java.util.concurrent.RejectedExecutionException;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.DefaultExecutor;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;

@Metadata
@PublishedApi
/* loaded from: classes2.dex */
public class ExperimentalCoroutineDispatcher extends ExecutorCoroutineDispatcher {

    /* renamed from: j, reason: collision with root package name */
    private CoroutineScheduler f19447j;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f19447j.close();
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public void j0(CoroutineContext coroutineContext, Runnable runnable) {
        try {
            CoroutineScheduler.j(this.f19447j, runnable, null, false, 6, null);
        } catch (RejectedExecutionException unused) {
            DefaultExecutor.f18861n.j0(coroutineContext, runnable);
        }
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public void k0(CoroutineContext coroutineContext, Runnable runnable) {
        try {
            CoroutineScheduler.j(this.f19447j, runnable, null, true, 2, null);
        } catch (RejectedExecutionException unused) {
            DefaultExecutor.f18861n.k0(coroutineContext, runnable);
        }
    }

    public final void n0(Runnable runnable, TaskContext taskContext, boolean z) {
        try {
            this.f19447j.i(runnable, taskContext, z);
        } catch (RejectedExecutionException unused) {
            DefaultExecutor.f18861n.C0(this.f19447j.e(runnable, taskContext));
        }
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public String toString() {
        return super.toString() + "[scheduler = " + this.f19447j + ']';
    }
}
