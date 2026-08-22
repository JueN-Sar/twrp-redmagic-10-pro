package kotlinx.coroutines;

import kotlin.Metadata;
import kotlinx.coroutines.internal.ArrayQueue;

@Metadata
/* loaded from: classes2.dex */
public abstract class EventLoop extends CoroutineDispatcher {

    /* renamed from: i, reason: collision with root package name */
    private long f18876i;

    /* renamed from: j, reason: collision with root package name */
    private boolean f18877j;

    /* renamed from: k, reason: collision with root package name */
    private ArrayQueue f18878k;

    private final long o0(boolean z) {
        return z ? 4294967296L : 1L;
    }

    public static /* synthetic */ void s0(EventLoop eventLoop, boolean z, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: incrementUseCount");
        }
        if ((i2 & 1) != 0) {
            z = false;
        }
        eventLoop.r0(z);
    }

    public final void n0(boolean z) {
        long o0 = this.f18876i - o0(z);
        this.f18876i = o0;
        if (o0 <= 0 && this.f18877j) {
            shutdown();
        }
    }

    public final void p0(DispatchedTask dispatchedTask) {
        ArrayQueue arrayQueue = this.f18878k;
        if (arrayQueue == null) {
            arrayQueue = new ArrayQueue();
            this.f18878k = arrayQueue;
        }
        arrayQueue.a(dispatchedTask);
    }

    protected long q0() {
        ArrayQueue arrayQueue = this.f18878k;
        return (arrayQueue == null || arrayQueue.c()) ? Long.MAX_VALUE : 0L;
    }

    public final void r0(boolean z) {
        this.f18876i += o0(z);
        if (z) {
            return;
        }
        this.f18877j = true;
    }

    public void shutdown() {
    }

    public final boolean t0() {
        return this.f18876i >= o0(true);
    }

    public final boolean u0() {
        ArrayQueue arrayQueue = this.f18878k;
        if (arrayQueue != null) {
            return arrayQueue.c();
        }
        return true;
    }

    public final boolean v0() {
        DispatchedTask dispatchedTask;
        ArrayQueue arrayQueue = this.f18878k;
        if (arrayQueue == null || (dispatchedTask = (DispatchedTask) arrayQueue.d()) == null) {
            return false;
        }
        dispatchedTask.run();
        return true;
    }
}
