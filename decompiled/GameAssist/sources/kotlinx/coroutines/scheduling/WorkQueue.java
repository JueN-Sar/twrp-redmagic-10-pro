package kotlinx.coroutines.scheduling;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata
/* loaded from: classes2.dex */
public final class WorkQueue {

    /* renamed from: b, reason: collision with root package name */
    private static final /* synthetic */ AtomicReferenceFieldUpdater f19472b = AtomicReferenceFieldUpdater.newUpdater(WorkQueue.class, Object.class, "lastScheduledTask");

    /* renamed from: c, reason: collision with root package name */
    private static final /* synthetic */ AtomicIntegerFieldUpdater f19473c = AtomicIntegerFieldUpdater.newUpdater(WorkQueue.class, "producerIndex");

    /* renamed from: d, reason: collision with root package name */
    private static final /* synthetic */ AtomicIntegerFieldUpdater f19474d = AtomicIntegerFieldUpdater.newUpdater(WorkQueue.class, "consumerIndex");

    /* renamed from: e, reason: collision with root package name */
    private static final /* synthetic */ AtomicIntegerFieldUpdater f19475e = AtomicIntegerFieldUpdater.newUpdater(WorkQueue.class, "blockingTasksInBuffer");

    /* renamed from: a, reason: collision with root package name */
    private final AtomicReferenceArray f19476a = new AtomicReferenceArray(128);

    @NotNull
    private volatile /* synthetic */ Object lastScheduledTask = null;

    @NotNull
    private volatile /* synthetic */ int producerIndex = 0;

    @NotNull
    private volatile /* synthetic */ int consumerIndex = 0;

    @NotNull
    private volatile /* synthetic */ int blockingTasksInBuffer = 0;

    public static /* synthetic */ Task b(WorkQueue workQueue, Task task, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        return workQueue.a(task, z);
    }

    private final Task c(Task task) {
        if (task.f19461h.a0() == 1) {
            f19475e.incrementAndGet(this);
        }
        if (e() == 127) {
            return task;
        }
        int i2 = this.producerIndex & 127;
        while (this.f19476a.get(i2) != null) {
            Thread.yield();
        }
        this.f19476a.lazySet(i2, task);
        f19473c.incrementAndGet(this);
        return null;
    }

    private final void d(Task task) {
        if (task == null || task.f19461h.a0() != 1) {
            return;
        }
        f19475e.decrementAndGet(this);
    }

    private final Task i() {
        Task task;
        while (true) {
            int i2 = this.consumerIndex;
            if (i2 - this.producerIndex == 0) {
                return null;
            }
            int i3 = i2 & 127;
            if (f19474d.compareAndSet(this, i2, i2 + 1) && (task = (Task) this.f19476a.getAndSet(i3, null)) != null) {
                d(task);
                return task;
            }
        }
    }

    private final boolean j(GlobalQueue globalQueue) {
        Task i2 = i();
        if (i2 == null) {
            return false;
        }
        globalQueue.a(i2);
        return true;
    }

    private final long m(WorkQueue workQueue, boolean z) {
        Task task;
        do {
            task = (Task) workQueue.lastScheduledTask;
            if (task == null) {
                return -2L;
            }
            if (z && task.f19461h.a0() != 1) {
                return -2L;
            }
            long a2 = TasksKt.f19468e.a() - task.f19460c;
            long j2 = TasksKt.f19464a;
            if (a2 < j2) {
                return j2 - a2;
            }
        } while (!f19472b.compareAndSet(workQueue, task, null));
        b(this, task, false, 2, null);
        return -1L;
    }

    public final Task a(Task task, boolean z) {
        if (z) {
            return c(task);
        }
        Task task2 = (Task) f19472b.getAndSet(this, task);
        if (task2 == null) {
            return null;
        }
        return c(task2);
    }

    public final int e() {
        return this.producerIndex - this.consumerIndex;
    }

    public final int f() {
        Object obj = this.lastScheduledTask;
        int e2 = e();
        return obj != null ? e2 + 1 : e2;
    }

    public final void g(GlobalQueue globalQueue) {
        Task task = (Task) f19472b.getAndSet(this, null);
        if (task != null) {
            globalQueue.a(task);
        }
        while (j(globalQueue)) {
        }
    }

    public final Task h() {
        Task task = (Task) f19472b.getAndSet(this, null);
        return task == null ? i() : task;
    }

    public final long k(WorkQueue workQueue) {
        int i2 = workQueue.producerIndex;
        AtomicReferenceArray atomicReferenceArray = workQueue.f19476a;
        for (int i3 = workQueue.consumerIndex; i3 != i2; i3++) {
            int i4 = i3 & 127;
            if (workQueue.blockingTasksInBuffer == 0) {
                break;
            }
            Task task = (Task) atomicReferenceArray.get(i4);
            if (task != null && task.f19461h.a0() == 1 && atomicReferenceArray.compareAndSet(i4, task, null)) {
                f19475e.decrementAndGet(workQueue);
                b(this, task, false, 2, null);
                return -1L;
            }
        }
        return m(workQueue, true);
    }

    public final long l(WorkQueue workQueue) {
        Task i2 = workQueue.i();
        if (i2 == null) {
            return m(workQueue, false);
        }
        b(this, i2, false, 2, null);
        return -1L;
    }
}
