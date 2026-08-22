package kotlinx.coroutines;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.ranges.RangesKt___RangesKt;
import kotlinx.coroutines.EventLoopImplBase;
import org.jetbrains.annotations.Nullable;

@Metadata
/* loaded from: classes2.dex */
public final class DefaultExecutor extends EventLoopImplBase implements Runnable {

    @Nullable
    private static volatile Thread _thread;
    private static volatile int debugStatus;

    /* renamed from: n, reason: collision with root package name */
    public static final DefaultExecutor f18861n;

    /* renamed from: o, reason: collision with root package name */
    private static final long f18862o;

    static {
        Long l2;
        DefaultExecutor defaultExecutor = new DefaultExecutor();
        f18861n = defaultExecutor;
        EventLoop.s0(defaultExecutor, false, 1, null);
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        try {
            l2 = Long.getLong("kotlinx.coroutines.DefaultExecutor.keepAlive", 1000L);
        } catch (SecurityException unused) {
            l2 = 1000L;
        }
        f18862o = timeUnit.toNanos(l2.longValue());
    }

    private DefaultExecutor() {
    }

    private final synchronized void N0() {
        if (Q0()) {
            debugStatus = 3;
            H0();
            notifyAll();
        }
    }

    private final synchronized Thread O0() {
        Thread thread;
        thread = _thread;
        if (thread == null) {
            thread = new Thread(this, "kotlinx.coroutines.DefaultExecutor");
            _thread = thread;
            thread.setDaemon(true);
            thread.start();
        }
        return thread;
    }

    private final boolean P0() {
        return debugStatus == 4;
    }

    private final boolean Q0() {
        int i2 = debugStatus;
        return i2 == 2 || i2 == 3;
    }

    private final synchronized boolean R0() {
        if (Q0()) {
            return false;
        }
        debugStatus = 1;
        notifyAll();
        return true;
    }

    private final void S0() {
        throw new RejectedExecutionException("DefaultExecutor was shut down. This error indicates that Dispatchers.shutdown() was invoked prior to completion of exiting coroutines, leaving coroutines in incomplete state. Please refer to Dispatchers.shutdown documentation for more details");
    }

    @Override // kotlinx.coroutines.EventLoopImplBase, kotlinx.coroutines.Delay
    public DisposableHandle B(long j2, Runnable runnable, CoroutineContext coroutineContext) {
        return K0(j2, runnable);
    }

    @Override // kotlinx.coroutines.EventLoopImplBase
    public void C0(Runnable runnable) {
        if (P0()) {
            S0();
        }
        super.C0(runnable);
    }

    @Override // java.lang.Runnable
    public void run() {
        Unit unit;
        ThreadLocalEventLoop.f18932a.c(this);
        AbstractTimeSource a2 = AbstractTimeSourceKt.a();
        if (a2 != null) {
            a2.c();
        }
        try {
            if (!R0()) {
                _thread = null;
                N0();
                AbstractTimeSource a3 = AbstractTimeSourceKt.a();
                if (a3 != null) {
                    a3.g();
                }
                if (E0()) {
                    return;
                }
                w0();
                return;
            }
            long j2 = Long.MAX_VALUE;
            while (true) {
                Thread.interrupted();
                long F0 = F0();
                if (F0 == Long.MAX_VALUE) {
                    AbstractTimeSource a4 = AbstractTimeSourceKt.a();
                    long a5 = a4 != null ? a4.a() : System.nanoTime();
                    if (j2 == Long.MAX_VALUE) {
                        j2 = f18862o + a5;
                    }
                    long j3 = j2 - a5;
                    if (j3 <= 0) {
                        _thread = null;
                        N0();
                        AbstractTimeSource a6 = AbstractTimeSourceKt.a();
                        if (a6 != null) {
                            a6.g();
                        }
                        if (E0()) {
                            return;
                        }
                        w0();
                        return;
                    }
                    F0 = RangesKt___RangesKt.d(F0, j3);
                } else {
                    j2 = Long.MAX_VALUE;
                }
                if (F0 > 0) {
                    if (Q0()) {
                        _thread = null;
                        N0();
                        AbstractTimeSource a7 = AbstractTimeSourceKt.a();
                        if (a7 != null) {
                            a7.g();
                        }
                        if (E0()) {
                            return;
                        }
                        w0();
                        return;
                    }
                    AbstractTimeSource a8 = AbstractTimeSourceKt.a();
                    if (a8 != null) {
                        a8.b(this, F0);
                        unit = Unit.f18288a;
                    } else {
                        unit = null;
                    }
                    if (unit == null) {
                        LockSupport.parkNanos(this, F0);
                    }
                }
            }
        } catch (Throwable th) {
            _thread = null;
            N0();
            AbstractTimeSource a9 = AbstractTimeSourceKt.a();
            if (a9 != null) {
                a9.g();
            }
            if (!E0()) {
                w0();
            }
            throw th;
        }
    }

    @Override // kotlinx.coroutines.EventLoopImplBase, kotlinx.coroutines.EventLoop
    public void shutdown() {
        debugStatus = 4;
        super.shutdown();
    }

    @Override // kotlinx.coroutines.EventLoopImplPlatform
    protected Thread w0() {
        Thread thread = _thread;
        return thread == null ? O0() : thread;
    }

    @Override // kotlinx.coroutines.EventLoopImplPlatform
    protected void x0(long j2, EventLoopImplBase.DelayedTask delayedTask) {
        S0();
    }
}
