package kotlinx.coroutines.scheduling;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import org.jetbrains.annotations.NotNull;

@Metadata
/* loaded from: classes2.dex */
final class LimitingDispatcher extends ExecutorCoroutineDispatcher implements TaskContext, Executor {

    /* renamed from: o, reason: collision with root package name */
    private static final /* synthetic */ AtomicIntegerFieldUpdater f19448o = AtomicIntegerFieldUpdater.newUpdater(LimitingDispatcher.class, "inFlightTasks");

    @NotNull
    private volatile /* synthetic */ int inFlightTasks;

    /* renamed from: j, reason: collision with root package name */
    private final ExperimentalCoroutineDispatcher f19449j;

    /* renamed from: k, reason: collision with root package name */
    private final int f19450k;

    /* renamed from: l, reason: collision with root package name */
    private final String f19451l;

    /* renamed from: m, reason: collision with root package name */
    private final int f19452m;

    /* renamed from: n, reason: collision with root package name */
    private final ConcurrentLinkedQueue f19453n;

    private final void n0(Runnable runnable, boolean z) {
        do {
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f19448o;
            if (atomicIntegerFieldUpdater.incrementAndGet(this) <= this.f19450k) {
                this.f19449j.n0(runnable, this, z);
                return;
            }
            this.f19453n.add(runnable);
            if (atomicIntegerFieldUpdater.decrementAndGet(this) >= this.f19450k) {
                return;
            } else {
                runnable = (Runnable) this.f19453n.poll();
            }
        } while (runnable != null);
    }

    @Override // kotlinx.coroutines.scheduling.TaskContext
    public void W() {
        Runnable runnable = (Runnable) this.f19453n.poll();
        if (runnable != null) {
            this.f19449j.n0(runnable, this, true);
            return;
        }
        f19448o.decrementAndGet(this);
        Runnable runnable2 = (Runnable) this.f19453n.poll();
        if (runnable2 == null) {
            return;
        }
        n0(runnable2, true);
    }

    @Override // kotlinx.coroutines.scheduling.TaskContext
    public int a0() {
        return this.f19452m;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        throw new IllegalStateException("Close cannot be invoked on LimitingBlockingDispatcher".toString());
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        n0(runnable, false);
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public void j0(CoroutineContext coroutineContext, Runnable runnable) {
        n0(runnable, false);
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public void k0(CoroutineContext coroutineContext, Runnable runnable) {
        n0(runnable, true);
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public String toString() {
        String str = this.f19451l;
        if (str != null) {
            return str;
        }
        return super.toString() + "[dispatcher = " + this.f19449j + ']';
    }
}
