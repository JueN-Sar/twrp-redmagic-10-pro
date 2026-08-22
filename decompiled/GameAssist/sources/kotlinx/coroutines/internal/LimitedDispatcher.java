package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.DefaultExecutorKt;
import kotlinx.coroutines.Delay;
import kotlinx.coroutines.DisposableHandle;

@Metadata
/* loaded from: classes2.dex */
public final class LimitedDispatcher extends CoroutineDispatcher implements Runnable, Delay {

    /* renamed from: i, reason: collision with root package name */
    private final CoroutineDispatcher f19360i;

    /* renamed from: j, reason: collision with root package name */
    private final int f19361j;

    /* renamed from: k, reason: collision with root package name */
    private final /* synthetic */ Delay f19362k;

    /* renamed from: l, reason: collision with root package name */
    private final LockFreeTaskQueue f19363l;

    /* renamed from: m, reason: collision with root package name */
    private final Object f19364m;
    private volatile int runningWorkers;

    /* JADX WARN: Multi-variable type inference failed */
    public LimitedDispatcher(CoroutineDispatcher coroutineDispatcher, int i2) {
        this.f19360i = coroutineDispatcher;
        this.f19361j = i2;
        Delay delay = coroutineDispatcher instanceof Delay ? (Delay) coroutineDispatcher : null;
        this.f19362k = delay == null ? DefaultExecutorKt.a() : delay;
        this.f19363l = new LockFreeTaskQueue(false);
        this.f19364m = new Object();
    }

    private final boolean n0(Runnable runnable) {
        this.f19363l.a(runnable);
        return this.runningWorkers >= this.f19361j;
    }

    private final boolean o0() {
        synchronized (this.f19364m) {
            if (this.runningWorkers >= this.f19361j) {
                return false;
            }
            this.runningWorkers++;
            return true;
        }
    }

    @Override // kotlinx.coroutines.Delay
    public DisposableHandle B(long j2, Runnable runnable, CoroutineContext coroutineContext) {
        return this.f19362k.B(j2, runnable, coroutineContext);
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public void j0(CoroutineContext coroutineContext, Runnable runnable) {
        if (n0(runnable) || !o0()) {
            return;
        }
        this.f19360i.j0(this, this);
    }

    @Override // kotlinx.coroutines.Delay
    public void k(long j2, CancellableContinuation cancellableContinuation) {
        this.f19362k.k(j2, cancellableContinuation);
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public void k0(CoroutineContext coroutineContext, Runnable runnable) {
        if (n0(runnable) || !o0()) {
            return;
        }
        this.f19360i.k0(this, this);
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x002a, code lost:
    
        r1 = r4.f19364m;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x002c, code lost:
    
        monitor-enter(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x002d, code lost:
    
        r4.runningWorkers--;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0039, code lost:
    
        if (r4.f19363l.c() != 0) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x003d, code lost:
    
        r4.runningWorkers++;
        r2 = kotlin.Unit.f18288a;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x003b, code lost:
    
        monitor-exit(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x003c, code lost:
    
        return;
     */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void run() {
        /*
            r4 = this;
            r0 = 0
        L1:
            r1 = r0
        L2:
            kotlinx.coroutines.internal.LockFreeTaskQueue r2 = r4.f19363l
            java.lang.Object r2 = r2.d()
            java.lang.Runnable r2 = (java.lang.Runnable) r2
            if (r2 == 0) goto L2a
            r2.run()     // Catch: java.lang.Throwable -> L10
            goto L16
        L10:
            r2 = move-exception
            kotlin.coroutines.EmptyCoroutineContext r3 = kotlin.coroutines.EmptyCoroutineContext.INSTANCE
            kotlinx.coroutines.CoroutineExceptionHandlerKt.a(r3, r2)
        L16:
            int r1 = r1 + 1
            r2 = 16
            if (r1 < r2) goto L2
            kotlinx.coroutines.CoroutineDispatcher r2 = r4.f19360i
            boolean r2 = r2.l0(r4)
            if (r2 == 0) goto L2
            kotlinx.coroutines.CoroutineDispatcher r0 = r4.f19360i
            r0.j0(r4, r4)
            return
        L2a:
            java.lang.Object r1 = r4.f19364m
            monitor-enter(r1)
            int r2 = r4.runningWorkers     // Catch: java.lang.Throwable -> L47
            int r2 = r2 + (-1)
            r4.runningWorkers = r2     // Catch: java.lang.Throwable -> L47
            kotlinx.coroutines.internal.LockFreeTaskQueue r2 = r4.f19363l     // Catch: java.lang.Throwable -> L47
            int r2 = r2.c()     // Catch: java.lang.Throwable -> L47
            if (r2 != 0) goto L3d
            monitor-exit(r1)
            return
        L3d:
            int r2 = r4.runningWorkers     // Catch: java.lang.Throwable -> L47
            int r2 = r2 + 1
            r4.runningWorkers = r2     // Catch: java.lang.Throwable -> L47
            kotlin.Unit r2 = kotlin.Unit.f18288a     // Catch: java.lang.Throwable -> L47
            monitor-exit(r1)
            goto L1
        L47:
            r4 = move-exception
            monitor-exit(r1)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.internal.LimitedDispatcher.run():void");
    }
}
