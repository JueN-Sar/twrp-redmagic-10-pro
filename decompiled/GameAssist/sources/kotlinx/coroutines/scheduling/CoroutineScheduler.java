package kotlinx.coroutines.scheduling;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.ranges.RangesKt___RangesKt;
import kotlinx.coroutines.AbstractTimeSource;
import kotlinx.coroutines.AbstractTimeSourceKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.internal.ResizableAtomicArray;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata
/* loaded from: classes2.dex */
public final class CoroutineScheduler implements Executor, Closeable {

    @NotNull
    private volatile /* synthetic */ int _isTerminated;

    /* renamed from: c, reason: collision with root package name */
    public final int f19428c;

    @NotNull
    volatile /* synthetic */ long controlState;

    /* renamed from: h, reason: collision with root package name */
    public final int f19429h;

    /* renamed from: i, reason: collision with root package name */
    public final long f19430i;

    /* renamed from: j, reason: collision with root package name */
    public final String f19431j;

    /* renamed from: k, reason: collision with root package name */
    public final GlobalQueue f19432k;

    /* renamed from: l, reason: collision with root package name */
    public final GlobalQueue f19433l;

    /* renamed from: m, reason: collision with root package name */
    public final ResizableAtomicArray f19434m;

    @NotNull
    private volatile /* synthetic */ long parkedWorkersStack;

    /* renamed from: n, reason: collision with root package name */
    public static final Companion f19423n = new Companion(null);

    /* renamed from: r, reason: collision with root package name */
    public static final Symbol f19427r = new Symbol("NOT_IN_STACK");

    /* renamed from: o, reason: collision with root package name */
    private static final /* synthetic */ AtomicLongFieldUpdater f19424o = AtomicLongFieldUpdater.newUpdater(CoroutineScheduler.class, "parkedWorkersStack");

    /* renamed from: p, reason: collision with root package name */
    static final /* synthetic */ AtomicLongFieldUpdater f19425p = AtomicLongFieldUpdater.newUpdater(CoroutineScheduler.class, "controlState");

    /* renamed from: q, reason: collision with root package name */
    private static final /* synthetic */ AtomicIntegerFieldUpdater f19426q = AtomicIntegerFieldUpdater.newUpdater(CoroutineScheduler.class, "_isTerminated");

    @Metadata
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata
    public /* synthetic */ class WhenMappings {

        /* renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f19435a;

        static {
            int[] iArr = new int[WorkerState.values().length];
            iArr[WorkerState.PARKING.ordinal()] = 1;
            iArr[WorkerState.BLOCKING.ordinal()] = 2;
            iArr[WorkerState.CPU_ACQUIRED.ordinal()] = 3;
            iArr[WorkerState.DORMANT.ordinal()] = 4;
            iArr[WorkerState.TERMINATED.ordinal()] = 5;
            f19435a = iArr;
        }
    }

    @Metadata
    public enum WorkerState {
        CPU_ACQUIRED,
        BLOCKING,
        PARKING,
        DORMANT,
        TERMINATED
    }

    public CoroutineScheduler(int i2, int i3, long j2, String str) {
        this.f19428c = i2;
        this.f19429h = i3;
        this.f19430i = j2;
        this.f19431j = str;
        if (i2 < 1) {
            throw new IllegalArgumentException(("Core pool size " + i2 + " should be at least 1").toString());
        }
        if (i3 < i2) {
            throw new IllegalArgumentException(("Max pool size " + i3 + " should be greater than or equals to core pool size " + i2).toString());
        }
        if (i3 > 2097150) {
            throw new IllegalArgumentException(("Max pool size " + i3 + " should not exceed maximal supported number of threads 2097150").toString());
        }
        if (j2 <= 0) {
            throw new IllegalArgumentException(("Idle worker keep alive time " + j2 + " must be positive").toString());
        }
        this.f19432k = new GlobalQueue();
        this.f19433l = new GlobalQueue();
        this.parkedWorkersStack = 0L;
        this.f19434m = new ResizableAtomicArray(i2 + 1);
        this.controlState = i2 << 42;
        this._isTerminated = 0;
    }

    private final void C(boolean z) {
        long addAndGet = f19425p.addAndGet(this, 2097152L);
        if (z || L() || G(addAndGet)) {
            return;
        }
        L();
    }

    private final Task F(Worker worker, Task task, boolean z) {
        if (worker == null || worker.f19438h == WorkerState.TERMINATED) {
            return task;
        }
        if (task.f19461h.a0() == 0 && worker.f19438h == WorkerState.BLOCKING) {
            return task;
        }
        worker.f19442l = true;
        return worker.f19437c.a(task, z);
    }

    private final boolean G(long j2) {
        int a2;
        a2 = RangesKt___RangesKt.a(((int) (2097151 & j2)) - ((int) ((j2 & 4398044413952L) >> 21)), 0);
        if (a2 < this.f19428c) {
            int c2 = c();
            if (c2 == 1 && this.f19428c > 1) {
                c();
            }
            if (c2 > 0) {
                return true;
            }
        }
        return false;
    }

    static /* synthetic */ boolean I(CoroutineScheduler coroutineScheduler, long j2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            j2 = coroutineScheduler.controlState;
        }
        return coroutineScheduler.G(j2);
    }

    private final boolean L() {
        Worker p2;
        do {
            p2 = p();
            if (p2 == null) {
                return false;
            }
        } while (!Worker.f19436n.compareAndSet(p2, -1, 0));
        LockSupport.unpark(p2);
        return true;
    }

    private final boolean a(Task task) {
        return task.f19461h.a0() == 1 ? this.f19433l.a(task) : this.f19432k.a(task);
    }

    private final int c() {
        int a2;
        synchronized (this.f19434m) {
            if (isTerminated()) {
                return -1;
            }
            long j2 = this.controlState;
            int i2 = (int) (j2 & 2097151);
            a2 = RangesKt___RangesKt.a(i2 - ((int) ((j2 & 4398044413952L) >> 21)), 0);
            if (a2 >= this.f19428c) {
                return 0;
            }
            if (i2 >= this.f19429h) {
                return 0;
            }
            int i3 = ((int) (this.controlState & 2097151)) + 1;
            if (i3 <= 0 || this.f19434m.b(i3) != null) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            Worker worker = new Worker(this, i3);
            this.f19434m.c(i3, worker);
            if (i3 != ((int) (2097151 & f19425p.incrementAndGet(this)))) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            worker.start();
            return a2 + 1;
        }
    }

    private final Worker h() {
        Thread currentThread = Thread.currentThread();
        Worker worker = currentThread instanceof Worker ? (Worker) currentThread : null;
        if (worker == null || !Intrinsics.a(CoroutineScheduler.this, this)) {
            return null;
        }
        return worker;
    }

    public static /* synthetic */ void j(CoroutineScheduler coroutineScheduler, Runnable runnable, TaskContext taskContext, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            taskContext = TasksKt.f19469f;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        coroutineScheduler.i(runnable, taskContext, z);
    }

    private final int k(Worker worker) {
        Object h2 = worker.h();
        while (h2 != f19427r) {
            if (h2 == null) {
                return 0;
            }
            Worker worker2 = (Worker) h2;
            int g2 = worker2.g();
            if (g2 != 0) {
                return g2;
            }
            h2 = worker2.h();
        }
        return -1;
    }

    private final Worker p() {
        while (true) {
            long j2 = this.parkedWorkersStack;
            Worker worker = (Worker) this.f19434m.b((int) (2097151 & j2));
            if (worker == null) {
                return null;
            }
            long j3 = (2097152 + j2) & (-2097152);
            int k2 = k(worker);
            if (k2 >= 0 && f19424o.compareAndSet(this, j2, k2 | j3)) {
                worker.p(f19427r);
                return worker;
            }
        }
    }

    public final void A(Task task) {
        try {
            task.run();
        } catch (Throwable th) {
            try {
                Thread currentThread = Thread.currentThread();
                currentThread.getUncaughtExceptionHandler().uncaughtException(currentThread, th);
                AbstractTimeSource a2 = AbstractTimeSourceKt.a();
                if (a2 == null) {
                }
            } finally {
                AbstractTimeSource a3 = AbstractTimeSourceKt.a();
                if (a3 != null) {
                    a3.e();
                }
            }
        }
    }

    public final void B(long j2) {
        int i2;
        Task task;
        if (f19426q.compareAndSet(this, 0, 1)) {
            Worker h2 = h();
            synchronized (this.f19434m) {
                i2 = (int) (this.controlState & 2097151);
            }
            if (1 <= i2) {
                int i3 = 1;
                while (true) {
                    Object b2 = this.f19434m.b(i3);
                    Intrinsics.b(b2);
                    Worker worker = (Worker) b2;
                    if (worker != h2) {
                        while (worker.isAlive()) {
                            LockSupport.unpark(worker);
                            worker.join(j2);
                        }
                        worker.f19437c.g(this.f19433l);
                    }
                    if (i3 == i2) {
                        break;
                    } else {
                        i3++;
                    }
                }
            }
            this.f19433l.b();
            this.f19432k.b();
            while (true) {
                if (h2 != null) {
                    task = h2.f(true);
                    if (task != null) {
                        continue;
                        A(task);
                    }
                }
                task = (Task) this.f19432k.d();
                if (task == null && (task = (Task) this.f19433l.d()) == null) {
                    break;
                }
                A(task);
            }
            if (h2 != null) {
                h2.s(WorkerState.TERMINATED);
            }
            this.parkedWorkersStack = 0L;
            this.controlState = 0L;
        }
    }

    public final void E() {
        if (L() || I(this, 0L, 1, null)) {
            return;
        }
        L();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        B(10000L);
    }

    public final Task e(Runnable runnable, TaskContext taskContext) {
        long a2 = TasksKt.f19468e.a();
        if (!(runnable instanceof Task)) {
            return new TaskImpl(runnable, a2, taskContext);
        }
        Task task = (Task) runnable;
        task.f19460c = a2;
        task.f19461h = taskContext;
        return task;
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        j(this, runnable, null, false, 6, null);
    }

    public final void i(Runnable runnable, TaskContext taskContext, boolean z) {
        AbstractTimeSource a2 = AbstractTimeSourceKt.a();
        if (a2 != null) {
            a2.d();
        }
        Task e2 = e(runnable, taskContext);
        Worker h2 = h();
        Task F = F(h2, e2, z);
        if (F != null && !a(F)) {
            throw new RejectedExecutionException(this.f19431j + " was terminated");
        }
        boolean z2 = z && h2 != null;
        if (e2.f19461h.a0() != 0) {
            C(z2);
        } else {
            if (z2) {
                return;
            }
            E();
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [boolean, int] */
    public final boolean isTerminated() {
        return this._isTerminated;
    }

    public final boolean s(Worker worker) {
        long j2;
        int g2;
        if (worker.h() != f19427r) {
            return false;
        }
        do {
            j2 = this.parkedWorkersStack;
            g2 = worker.g();
            worker.p(this.f19434m.b((int) (2097151 & j2)));
        } while (!f19424o.compareAndSet(this, j2, ((2097152 + j2) & (-2097152)) | g2));
        return true;
    }

    public final void t(Worker worker, int i2, int i3) {
        while (true) {
            long j2 = this.parkedWorkersStack;
            int i4 = (int) (2097151 & j2);
            long j3 = (2097152 + j2) & (-2097152);
            if (i4 == i2) {
                i4 = i3 == 0 ? k(worker) : i3;
            }
            if (i4 >= 0 && f19424o.compareAndSet(this, j2, j3 | i4)) {
                return;
            }
        }
    }

    public String toString() {
        ArrayList arrayList = new ArrayList();
        int a2 = this.f19434m.a();
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 1; i7 < a2; i7++) {
            Worker worker = (Worker) this.f19434m.b(i7);
            if (worker != null) {
                int f2 = worker.f19437c.f();
                int i8 = WhenMappings.f19435a[worker.f19438h.ordinal()];
                if (i8 == 1) {
                    i4++;
                } else if (i8 == 2) {
                    i3++;
                    StringBuilder sb = new StringBuilder();
                    sb.append(f2);
                    sb.append('b');
                    arrayList.add(sb.toString());
                } else if (i8 == 3) {
                    i2++;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(f2);
                    sb2.append('c');
                    arrayList.add(sb2.toString());
                } else if (i8 == 4) {
                    i5++;
                    if (f2 > 0) {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(f2);
                        sb3.append('d');
                        arrayList.add(sb3.toString());
                    }
                } else if (i8 == 5) {
                    i6++;
                }
            }
        }
        long j2 = this.controlState;
        return this.f19431j + '@' + DebugStringsKt.b(this) + "[Pool Size {core = " + this.f19428c + ", max = " + this.f19429h + "}, Worker States {CPU = " + i2 + ", blocking = " + i3 + ", parked = " + i4 + ", dormant = " + i5 + ", terminated = " + i6 + "}, running workers queues = " + arrayList + ", global CPU queue size = " + this.f19432k.c() + ", global blocking queue size = " + this.f19433l.c() + ", Control State {created workers= " + ((int) (2097151 & j2)) + ", blocking tasks = " + ((int) ((4398044413952L & j2) >> 21)) + ", CPUs acquired = " + (this.f19428c - ((int) ((9223367638808264704L & j2) >> 42))) + "}]";
    }

    @Metadata
    public final class Worker extends Thread {

        /* renamed from: n, reason: collision with root package name */
        static final /* synthetic */ AtomicIntegerFieldUpdater f19436n = AtomicIntegerFieldUpdater.newUpdater(Worker.class, "workerCtl");

        /* renamed from: c, reason: collision with root package name */
        public final WorkQueue f19437c;

        /* renamed from: h, reason: collision with root package name */
        public WorkerState f19438h;

        /* renamed from: i, reason: collision with root package name */
        private long f19439i;
        private volatile int indexInArray;

        /* renamed from: j, reason: collision with root package name */
        private long f19440j;

        /* renamed from: k, reason: collision with root package name */
        private int f19441k;

        /* renamed from: l, reason: collision with root package name */
        public boolean f19442l;

        @Nullable
        private volatile Object nextParkedWorker;

        @NotNull
        volatile /* synthetic */ int workerCtl;

        private Worker() {
            setDaemon(true);
            this.f19437c = new WorkQueue();
            this.f19438h = WorkerState.DORMANT;
            this.workerCtl = 0;
            this.nextParkedWorker = CoroutineScheduler.f19427r;
            this.f19441k = Random.Default.h();
        }

        private final void b(int i2) {
            if (i2 == 0) {
                return;
            }
            CoroutineScheduler.f19425p.addAndGet(CoroutineScheduler.this, -2097152L);
            if (this.f19438h != WorkerState.TERMINATED) {
                this.f19438h = WorkerState.DORMANT;
            }
        }

        private final void c(int i2) {
            if (i2 != 0 && s(WorkerState.BLOCKING)) {
                CoroutineScheduler.this.E();
            }
        }

        private final void d(Task task) {
            int a0 = task.f19461h.a0();
            i(a0);
            c(a0);
            CoroutineScheduler.this.A(task);
            b(a0);
        }

        private final Task e(boolean z) {
            Task m2;
            Task m3;
            if (z) {
                boolean z2 = k(CoroutineScheduler.this.f19428c * 2) == 0;
                if (z2 && (m3 = m()) != null) {
                    return m3;
                }
                Task h2 = this.f19437c.h();
                if (h2 != null) {
                    return h2;
                }
                if (!z2 && (m2 = m()) != null) {
                    return m2;
                }
            } else {
                Task m4 = m();
                if (m4 != null) {
                    return m4;
                }
            }
            return t(false);
        }

        private final void i(int i2) {
            this.f19439i = 0L;
            if (this.f19438h == WorkerState.PARKING) {
                this.f19438h = WorkerState.BLOCKING;
            }
        }

        private final boolean j() {
            return this.nextParkedWorker != CoroutineScheduler.f19427r;
        }

        private final void l() {
            if (this.f19439i == 0) {
                this.f19439i = System.nanoTime() + CoroutineScheduler.this.f19430i;
            }
            LockSupport.parkNanos(CoroutineScheduler.this.f19430i);
            if (System.nanoTime() - this.f19439i >= 0) {
                this.f19439i = 0L;
                u();
            }
        }

        private final Task m() {
            if (k(2) == 0) {
                Task task = (Task) CoroutineScheduler.this.f19432k.d();
                return task != null ? task : (Task) CoroutineScheduler.this.f19433l.d();
            }
            Task task2 = (Task) CoroutineScheduler.this.f19433l.d();
            return task2 != null ? task2 : (Task) CoroutineScheduler.this.f19432k.d();
        }

        private final void n() {
            loop0: while (true) {
                boolean z = false;
                while (!CoroutineScheduler.this.isTerminated() && this.f19438h != WorkerState.TERMINATED) {
                    Task f2 = f(this.f19442l);
                    if (f2 != null) {
                        this.f19440j = 0L;
                        d(f2);
                    } else {
                        this.f19442l = false;
                        if (this.f19440j == 0) {
                            r();
                        } else if (z) {
                            s(WorkerState.PARKING);
                            Thread.interrupted();
                            LockSupport.parkNanos(this.f19440j);
                            this.f19440j = 0L;
                        } else {
                            z = true;
                        }
                    }
                }
            }
            s(WorkerState.TERMINATED);
        }

        private final boolean q() {
            long j2;
            if (this.f19438h == WorkerState.CPU_ACQUIRED) {
                return true;
            }
            CoroutineScheduler coroutineScheduler = CoroutineScheduler.this;
            do {
                j2 = coroutineScheduler.controlState;
                if (((int) ((9223367638808264704L & j2) >> 42)) == 0) {
                    return false;
                }
            } while (!CoroutineScheduler.f19425p.compareAndSet(coroutineScheduler, j2, j2 - 4398046511104L));
            this.f19438h = WorkerState.CPU_ACQUIRED;
            return true;
        }

        private final void r() {
            if (!j()) {
                CoroutineScheduler.this.s(this);
                return;
            }
            this.workerCtl = -1;
            while (j() && this.workerCtl == -1 && !CoroutineScheduler.this.isTerminated() && this.f19438h != WorkerState.TERMINATED) {
                s(WorkerState.PARKING);
                Thread.interrupted();
                l();
            }
        }

        private final Task t(boolean z) {
            int i2 = (int) (CoroutineScheduler.this.controlState & 2097151);
            if (i2 < 2) {
                return null;
            }
            int k2 = k(i2);
            CoroutineScheduler coroutineScheduler = CoroutineScheduler.this;
            long j2 = Long.MAX_VALUE;
            for (int i3 = 0; i3 < i2; i3++) {
                k2++;
                if (k2 > i2) {
                    k2 = 1;
                }
                Worker worker = (Worker) coroutineScheduler.f19434m.b(k2);
                if (worker != null && worker != this) {
                    long k3 = z ? this.f19437c.k(worker.f19437c) : this.f19437c.l(worker.f19437c);
                    if (k3 == -1) {
                        return this.f19437c.h();
                    }
                    if (k3 > 0) {
                        j2 = Math.min(j2, k3);
                    }
                }
            }
            if (j2 == Long.MAX_VALUE) {
                j2 = 0;
            }
            this.f19440j = j2;
            return null;
        }

        private final void u() {
            CoroutineScheduler coroutineScheduler = CoroutineScheduler.this;
            synchronized (coroutineScheduler.f19434m) {
                try {
                    if (coroutineScheduler.isTerminated()) {
                        return;
                    }
                    if (((int) (coroutineScheduler.controlState & 2097151)) <= coroutineScheduler.f19428c) {
                        return;
                    }
                    if (f19436n.compareAndSet(this, -1, 1)) {
                        int i2 = this.indexInArray;
                        o(0);
                        coroutineScheduler.t(this, i2, 0);
                        int andDecrement = (int) (CoroutineScheduler.f19425p.getAndDecrement(coroutineScheduler) & 2097151);
                        if (andDecrement != i2) {
                            Object b2 = coroutineScheduler.f19434m.b(andDecrement);
                            Intrinsics.b(b2);
                            Worker worker = (Worker) b2;
                            coroutineScheduler.f19434m.c(i2, worker);
                            worker.o(i2);
                            coroutineScheduler.t(worker, andDecrement, i2);
                        }
                        coroutineScheduler.f19434m.c(andDecrement, null);
                        Unit unit = Unit.f18288a;
                        this.f19438h = WorkerState.TERMINATED;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public final Task f(boolean z) {
            Task task;
            if (q()) {
                return e(z);
            }
            if (z) {
                task = this.f19437c.h();
                if (task == null) {
                    task = (Task) CoroutineScheduler.this.f19433l.d();
                }
            } else {
                task = (Task) CoroutineScheduler.this.f19433l.d();
            }
            return task == null ? t(true) : task;
        }

        public final int g() {
            return this.indexInArray;
        }

        public final Object h() {
            return this.nextParkedWorker;
        }

        public final int k(int i2) {
            int i3 = this.f19441k;
            int i4 = i3 ^ (i3 << 13);
            int i5 = i4 ^ (i4 >> 17);
            int i6 = i5 ^ (i5 << 5);
            this.f19441k = i6;
            int i7 = i2 - 1;
            return (i7 & i2) == 0 ? i7 & i6 : (Integer.MAX_VALUE & i6) % i2;
        }

        public final void o(int i2) {
            StringBuilder sb = new StringBuilder();
            sb.append(CoroutineScheduler.this.f19431j);
            sb.append("-worker-");
            sb.append(i2 == 0 ? "TERMINATED" : String.valueOf(i2));
            setName(sb.toString());
            this.indexInArray = i2;
        }

        public final void p(Object obj) {
            this.nextParkedWorker = obj;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            n();
        }

        public final boolean s(WorkerState workerState) {
            WorkerState workerState2 = this.f19438h;
            boolean z = workerState2 == WorkerState.CPU_ACQUIRED;
            if (z) {
                CoroutineScheduler.f19425p.addAndGet(CoroutineScheduler.this, 4398046511104L);
            }
            if (workerState2 != workerState) {
                this.f19438h = workerState;
            }
            return z;
        }

        public Worker(CoroutineScheduler coroutineScheduler, int i2) {
            this();
            o(i2);
        }
    }
}
