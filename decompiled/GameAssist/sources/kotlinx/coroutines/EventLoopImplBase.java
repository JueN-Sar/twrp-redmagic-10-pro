package kotlinx.coroutines;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import kotlinx.coroutines.Delay;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.ThreadSafeHeap;
import kotlinx.coroutines.internal.ThreadSafeHeapNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata
/* loaded from: classes2.dex */
public abstract class EventLoopImplBase extends EventLoopImplPlatform implements Delay {

    /* renamed from: l, reason: collision with root package name */
    private static final /* synthetic */ AtomicReferenceFieldUpdater f18879l = AtomicReferenceFieldUpdater.newUpdater(EventLoopImplBase.class, Object.class, "_queue");

    /* renamed from: m, reason: collision with root package name */
    private static final /* synthetic */ AtomicReferenceFieldUpdater f18880m = AtomicReferenceFieldUpdater.newUpdater(EventLoopImplBase.class, Object.class, "_delayed");

    @NotNull
    private volatile /* synthetic */ Object _queue = null;

    @NotNull
    private volatile /* synthetic */ Object _delayed = null;

    @NotNull
    private volatile /* synthetic */ int _isCompleted = 0;

    @Metadata
    private final class DelayedResumeTask extends DelayedTask {

        /* renamed from: i, reason: collision with root package name */
        private final CancellableContinuation f18881i;

        public DelayedResumeTask(long j2, CancellableContinuation cancellableContinuation) {
            super(j2);
            this.f18881i = cancellableContinuation;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f18881i.H(EventLoopImplBase.this, Unit.f18288a);
        }

        @Override // kotlinx.coroutines.EventLoopImplBase.DelayedTask
        public String toString() {
            return super.toString() + this.f18881i;
        }
    }

    @Metadata
    private static final class DelayedRunnableTask extends DelayedTask {

        /* renamed from: i, reason: collision with root package name */
        private final Runnable f18883i;

        public DelayedRunnableTask(long j2, Runnable runnable) {
            super(j2);
            this.f18883i = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f18883i.run();
        }

        @Override // kotlinx.coroutines.EventLoopImplBase.DelayedTask
        public String toString() {
            return super.toString() + this.f18883i;
        }
    }

    @Metadata
    public static abstract class DelayedTask implements Runnable, Comparable<DelayedTask>, DisposableHandle, ThreadSafeHeapNode {

        @Nullable
        private volatile Object _heap;

        /* renamed from: c, reason: collision with root package name */
        public long f18884c;

        /* renamed from: h, reason: collision with root package name */
        private int f18885h = -1;

        public DelayedTask(long j2) {
            this.f18884c = j2;
        }

        @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
        public void c(ThreadSafeHeap threadSafeHeap) {
            Symbol symbol;
            Object obj = this._heap;
            symbol = EventLoop_commonKt.f18887a;
            if (obj == symbol) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            this._heap = threadSafeHeap;
        }

        @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
        public ThreadSafeHeap d() {
            Object obj = this._heap;
            if (obj instanceof ThreadSafeHeap) {
                return (ThreadSafeHeap) obj;
            }
            return null;
        }

        @Override // kotlinx.coroutines.DisposableHandle
        public final synchronized void dispose() {
            Symbol symbol;
            Symbol symbol2;
            try {
                Object obj = this._heap;
                symbol = EventLoop_commonKt.f18887a;
                if (obj == symbol) {
                    return;
                }
                DelayedTaskQueue delayedTaskQueue = obj instanceof DelayedTaskQueue ? (DelayedTaskQueue) obj : null;
                if (delayedTaskQueue != null) {
                    delayedTaskQueue.g(this);
                }
                symbol2 = EventLoop_commonKt.f18887a;
                this._heap = symbol2;
            } catch (Throwable th) {
                throw th;
            }
        }

        @Override // java.lang.Comparable
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public int compareTo(DelayedTask delayedTask) {
            long j2 = this.f18884c - delayedTask.f18884c;
            if (j2 > 0) {
                return 1;
            }
            return j2 < 0 ? -1 : 0;
        }

        public final synchronized int f(long j2, DelayedTaskQueue delayedTaskQueue, EventLoopImplBase eventLoopImplBase) {
            Symbol symbol;
            Object obj = this._heap;
            symbol = EventLoop_commonKt.f18887a;
            if (obj == symbol) {
                return 2;
            }
            synchronized (delayedTaskQueue) {
                try {
                    DelayedTask delayedTask = (DelayedTask) delayedTaskQueue.b();
                    if (eventLoopImplBase.L()) {
                        return 1;
                    }
                    if (delayedTask == null) {
                        delayedTaskQueue.f18886b = j2;
                    } else {
                        long j3 = delayedTask.f18884c;
                        if (j3 - j2 < 0) {
                            j2 = j3;
                        }
                        if (j2 - delayedTaskQueue.f18886b > 0) {
                            delayedTaskQueue.f18886b = j2;
                        }
                    }
                    long j4 = this.f18884c;
                    long j5 = delayedTaskQueue.f18886b;
                    if (j4 - j5 < 0) {
                        this.f18884c = j5;
                    }
                    delayedTaskQueue.a(this);
                    return 0;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
        public int getIndex() {
            return this.f18885h;
        }

        public final boolean j(long j2) {
            return j2 - this.f18884c >= 0;
        }

        @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
        public void setIndex(int i2) {
            this.f18885h = i2;
        }

        public String toString() {
            return "Delayed[nanos=" + this.f18884c + ']';
        }
    }

    @Metadata
    public static final class DelayedTaskQueue extends ThreadSafeHeap<DelayedTask> {

        /* renamed from: b, reason: collision with root package name */
        public long f18886b;

        public DelayedTaskQueue(long j2) {
            this.f18886b = j2;
        }
    }

    private final void A0() {
        Symbol symbol;
        Symbol symbol2;
        while (true) {
            Object obj = this._queue;
            if (obj == null) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f18879l;
                symbol = EventLoop_commonKt.f18888b;
                if (atomicReferenceFieldUpdater.compareAndSet(this, null, symbol)) {
                    return;
                }
            } else {
                if (obj instanceof LockFreeTaskQueueCore) {
                    ((LockFreeTaskQueueCore) obj).d();
                    return;
                }
                symbol2 = EventLoop_commonKt.f18888b;
                if (obj == symbol2) {
                    return;
                }
                LockFreeTaskQueueCore lockFreeTaskQueueCore = new LockFreeTaskQueueCore(8, true);
                lockFreeTaskQueueCore.a((Runnable) obj);
                if (f18879l.compareAndSet(this, obj, lockFreeTaskQueueCore)) {
                    return;
                }
            }
        }
    }

    private final Runnable B0() {
        Symbol symbol;
        while (true) {
            Object obj = this._queue;
            if (obj == null) {
                return null;
            }
            if (obj instanceof LockFreeTaskQueueCore) {
                LockFreeTaskQueueCore lockFreeTaskQueueCore = (LockFreeTaskQueueCore) obj;
                Object j2 = lockFreeTaskQueueCore.j();
                if (j2 != LockFreeTaskQueueCore.f19387h) {
                    return (Runnable) j2;
                }
                f18879l.compareAndSet(this, obj, lockFreeTaskQueueCore.i());
            } else {
                symbol = EventLoop_commonKt.f18888b;
                if (obj == symbol) {
                    return null;
                }
                if (f18879l.compareAndSet(this, obj, null)) {
                    return (Runnable) obj;
                }
            }
        }
    }

    private final boolean D0(Runnable runnable) {
        Symbol symbol;
        while (true) {
            Object obj = this._queue;
            if (L()) {
                return false;
            }
            if (obj == null) {
                if (f18879l.compareAndSet(this, null, runnable)) {
                    return true;
                }
            } else if (obj instanceof LockFreeTaskQueueCore) {
                LockFreeTaskQueueCore lockFreeTaskQueueCore = (LockFreeTaskQueueCore) obj;
                int a2 = lockFreeTaskQueueCore.a(runnable);
                if (a2 == 0) {
                    return true;
                }
                if (a2 == 1) {
                    f18879l.compareAndSet(this, obj, lockFreeTaskQueueCore.i());
                } else if (a2 == 2) {
                    return false;
                }
            } else {
                symbol = EventLoop_commonKt.f18888b;
                if (obj == symbol) {
                    return false;
                }
                LockFreeTaskQueueCore lockFreeTaskQueueCore2 = new LockFreeTaskQueueCore(8, true);
                lockFreeTaskQueueCore2.a((Runnable) obj);
                lockFreeTaskQueueCore2.a(runnable);
                if (f18879l.compareAndSet(this, obj, lockFreeTaskQueueCore2)) {
                    return true;
                }
            }
        }
    }

    private final void G0() {
        DelayedTask delayedTask;
        AbstractTimeSource a2 = AbstractTimeSourceKt.a();
        long a3 = a2 != null ? a2.a() : System.nanoTime();
        while (true) {
            DelayedTaskQueue delayedTaskQueue = (DelayedTaskQueue) this._delayed;
            if (delayedTaskQueue == null || (delayedTask = (DelayedTask) delayedTaskQueue.i()) == null) {
                return;
            } else {
                x0(a3, delayedTask);
            }
        }
    }

    private final int J0(long j2, DelayedTask delayedTask) {
        if (L()) {
            return 1;
        }
        DelayedTaskQueue delayedTaskQueue = (DelayedTaskQueue) this._delayed;
        if (delayedTaskQueue == null) {
            f18880m.compareAndSet(this, null, new DelayedTaskQueue(j2));
            Object obj = this._delayed;
            Intrinsics.b(obj);
            delayedTaskQueue = (DelayedTaskQueue) obj;
        }
        return delayedTask.f(j2, delayedTaskQueue, this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v1, types: [boolean, int] */
    public final boolean L() {
        return this._isCompleted;
    }

    private final void L0(boolean z) {
        this._isCompleted = z ? 1 : 0;
    }

    private final boolean M0(DelayedTask delayedTask) {
        DelayedTaskQueue delayedTaskQueue = (DelayedTaskQueue) this._delayed;
        return (delayedTaskQueue != null ? (DelayedTask) delayedTaskQueue.e() : null) == delayedTask;
    }

    public DisposableHandle B(long j2, Runnable runnable, CoroutineContext coroutineContext) {
        return Delay.DefaultImpls.a(this, j2, runnable, coroutineContext);
    }

    public void C0(Runnable runnable) {
        if (D0(runnable)) {
            y0();
        } else {
            DefaultExecutor.f18861n.C0(runnable);
        }
    }

    protected boolean E0() {
        Symbol symbol;
        if (!u0()) {
            return false;
        }
        DelayedTaskQueue delayedTaskQueue = (DelayedTaskQueue) this._delayed;
        if (delayedTaskQueue != null && !delayedTaskQueue.d()) {
            return false;
        }
        Object obj = this._queue;
        if (obj != null) {
            if (obj instanceof LockFreeTaskQueueCore) {
                return ((LockFreeTaskQueueCore) obj).g();
            }
            symbol = EventLoop_commonKt.f18888b;
            if (obj != symbol) {
                return false;
            }
        }
        return true;
    }

    public long F0() {
        ThreadSafeHeapNode threadSafeHeapNode;
        if (v0()) {
            return 0L;
        }
        DelayedTaskQueue delayedTaskQueue = (DelayedTaskQueue) this._delayed;
        if (delayedTaskQueue != null && !delayedTaskQueue.d()) {
            AbstractTimeSource a2 = AbstractTimeSourceKt.a();
            long a3 = a2 != null ? a2.a() : System.nanoTime();
            do {
                synchronized (delayedTaskQueue) {
                    ThreadSafeHeapNode b2 = delayedTaskQueue.b();
                    threadSafeHeapNode = null;
                    if (b2 != null) {
                        DelayedTask delayedTask = (DelayedTask) b2;
                        if (delayedTask.j(a3) && D0(delayedTask)) {
                            threadSafeHeapNode = delayedTaskQueue.h(0);
                        }
                    }
                }
            } while (((DelayedTask) threadSafeHeapNode) != null);
        }
        Runnable B0 = B0();
        if (B0 == null) {
            return q0();
        }
        B0.run();
        return 0L;
    }

    protected final void H0() {
        this._queue = null;
        this._delayed = null;
    }

    public final void I0(long j2, DelayedTask delayedTask) {
        int J0 = J0(j2, delayedTask);
        if (J0 == 0) {
            if (M0(delayedTask)) {
                y0();
            }
        } else if (J0 == 1) {
            x0(j2, delayedTask);
        } else if (J0 != 2) {
            throw new IllegalStateException("unexpected result".toString());
        }
    }

    protected final DisposableHandle K0(long j2, Runnable runnable) {
        long d2 = EventLoop_commonKt.d(j2);
        if (d2 >= 4611686018427387903L) {
            return NonDisposableHandle.f18921c;
        }
        AbstractTimeSource a2 = AbstractTimeSourceKt.a();
        long a3 = a2 != null ? a2.a() : System.nanoTime();
        DelayedRunnableTask delayedRunnableTask = new DelayedRunnableTask(d2 + a3, runnable);
        I0(a3, delayedRunnableTask);
        return delayedRunnableTask;
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public final void j0(CoroutineContext coroutineContext, Runnable runnable) {
        C0(runnable);
    }

    @Override // kotlinx.coroutines.Delay
    public void k(long j2, CancellableContinuation cancellableContinuation) {
        long d2 = EventLoop_commonKt.d(j2);
        if (d2 < 4611686018427387903L) {
            AbstractTimeSource a2 = AbstractTimeSourceKt.a();
            long a3 = a2 != null ? a2.a() : System.nanoTime();
            DelayedResumeTask delayedResumeTask = new DelayedResumeTask(d2 + a3, cancellableContinuation);
            I0(a3, delayedResumeTask);
            CancellableContinuationKt.a(cancellableContinuation, delayedResumeTask);
        }
    }

    @Override // kotlinx.coroutines.EventLoop
    protected long q0() {
        DelayedTask delayedTask;
        long b2;
        Symbol symbol;
        if (super.q0() == 0) {
            return 0L;
        }
        Object obj = this._queue;
        if (obj != null) {
            if (!(obj instanceof LockFreeTaskQueueCore)) {
                symbol = EventLoop_commonKt.f18888b;
                return obj == symbol ? Long.MAX_VALUE : 0L;
            }
            if (!((LockFreeTaskQueueCore) obj).g()) {
                return 0L;
            }
        }
        DelayedTaskQueue delayedTaskQueue = (DelayedTaskQueue) this._delayed;
        if (delayedTaskQueue == null || (delayedTask = (DelayedTask) delayedTaskQueue.e()) == null) {
            return Long.MAX_VALUE;
        }
        long j2 = delayedTask.f18884c;
        AbstractTimeSource a2 = AbstractTimeSourceKt.a();
        b2 = RangesKt___RangesKt.b(j2 - (a2 != null ? a2.a() : System.nanoTime()), 0L);
        return b2;
    }

    @Override // kotlinx.coroutines.EventLoop
    public void shutdown() {
        ThreadLocalEventLoop.f18932a.b();
        L0(true);
        A0();
        while (F0() <= 0) {
        }
        G0();
    }
}
