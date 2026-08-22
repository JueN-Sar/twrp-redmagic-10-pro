package okio;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public class AsyncTimeout extends Timeout {

    /* renamed from: h, reason: collision with root package name */
    private static final long f19558h;

    /* renamed from: i, reason: collision with root package name */
    private static final long f19559i;

    /* renamed from: j, reason: collision with root package name */
    static AsyncTimeout f19560j;

    /* renamed from: e, reason: collision with root package name */
    private boolean f19561e;

    /* renamed from: f, reason: collision with root package name */
    private AsyncTimeout f19562f;

    /* renamed from: g, reason: collision with root package name */
    private long f19563g;

    /* renamed from: okio.AsyncTimeout$1, reason: invalid class name */
    class AnonymousClass1 implements Sink {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ Sink f19564c;

        /* renamed from: h, reason: collision with root package name */
        final /* synthetic */ AsyncTimeout f19565h;

        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.f19565h.l();
            try {
                try {
                    this.f19564c.close();
                    this.f19565h.n(true);
                } catch (IOException e2) {
                    throw this.f19565h.m(e2);
                }
            } catch (Throwable th) {
                this.f19565h.n(false);
                throw th;
            }
        }

        @Override // okio.Sink, java.io.Flushable
        public void flush() {
            this.f19565h.l();
            try {
                try {
                    this.f19564c.flush();
                    this.f19565h.n(true);
                } catch (IOException e2) {
                    throw this.f19565h.m(e2);
                }
            } catch (Throwable th) {
                this.f19565h.n(false);
                throw th;
            }
        }

        @Override // okio.Sink
        public Timeout n() {
            return this.f19565h;
        }

        public String toString() {
            return "AsyncTimeout.sink(" + this.f19564c + ")";
        }

        @Override // okio.Sink
        public void w(Buffer buffer, long j2) {
            Util.b(buffer.f19572h, 0L, j2);
            while (true) {
                long j3 = 0;
                if (j2 <= 0) {
                    return;
                }
                Segment segment = buffer.f19571c;
                while (true) {
                    if (j3 >= 65536) {
                        break;
                    }
                    j3 += segment.f19642c - segment.f19641b;
                    if (j3 >= j2) {
                        j3 = j2;
                        break;
                    }
                    segment = segment.f19645f;
                }
                this.f19565h.l();
                try {
                    try {
                        this.f19564c.w(buffer, j3);
                        j2 -= j3;
                        this.f19565h.n(true);
                    } catch (IOException e2) {
                        throw this.f19565h.m(e2);
                    }
                } catch (Throwable th) {
                    this.f19565h.n(false);
                    throw th;
                }
            }
        }
    }

    /* renamed from: okio.AsyncTimeout$2, reason: invalid class name */
    class AnonymousClass2 implements Source {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ Source f19566c;

        /* renamed from: h, reason: collision with root package name */
        final /* synthetic */ AsyncTimeout f19567h;

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.f19567h.l();
            try {
                try {
                    this.f19566c.close();
                    this.f19567h.n(true);
                } catch (IOException e2) {
                    throw this.f19567h.m(e2);
                }
            } catch (Throwable th) {
                this.f19567h.n(false);
                throw th;
            }
        }

        @Override // okio.Source
        public long d0(Buffer buffer, long j2) {
            this.f19567h.l();
            try {
                try {
                    long d0 = this.f19566c.d0(buffer, j2);
                    this.f19567h.n(true);
                    return d0;
                } catch (IOException e2) {
                    throw this.f19567h.m(e2);
                }
            } catch (Throwable th) {
                this.f19567h.n(false);
                throw th;
            }
        }

        public String toString() {
            return "AsyncTimeout.source(" + this.f19566c + ")";
        }
    }

    private static final class Watchdog extends Thread {
        Watchdog() {
            super("Okio Watchdog");
            setDaemon(true);
        }

        /* JADX WARN: Code restructure failed: missing block: B:19:0x0017, code lost:
        
            r0.s();
         */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void run() {
            /*
                r2 = this;
            L0:
                java.lang.Class<okio.AsyncTimeout> r2 = okio.AsyncTimeout.class
                monitor-enter(r2)     // Catch: java.lang.InterruptedException -> L0
                okio.AsyncTimeout r0 = okio.AsyncTimeout.j()     // Catch: java.lang.Throwable -> Lb
                if (r0 != 0) goto Ld
                monitor-exit(r2)     // Catch: java.lang.Throwable -> Lb
                goto L0
            Lb:
                r0 = move-exception
                goto L1b
            Ld:
                okio.AsyncTimeout r1 = okio.AsyncTimeout.f19560j     // Catch: java.lang.Throwable -> Lb
                if (r0 != r1) goto L16
                r0 = 0
                okio.AsyncTimeout.f19560j = r0     // Catch: java.lang.Throwable -> Lb
                monitor-exit(r2)     // Catch: java.lang.Throwable -> Lb
                return
            L16:
                monitor-exit(r2)     // Catch: java.lang.Throwable -> Lb
                r0.s()     // Catch: java.lang.InterruptedException -> L0
                goto L0
            L1b:
                monitor-exit(r2)     // Catch: java.lang.Throwable -> Lb
                throw r0     // Catch: java.lang.InterruptedException -> L0
            */
            throw new UnsupportedOperationException("Method not decompiled: okio.AsyncTimeout.Watchdog.run():void");
        }
    }

    static {
        long millis = TimeUnit.SECONDS.toMillis(60L);
        f19558h = millis;
        f19559i = TimeUnit.MILLISECONDS.toNanos(millis);
    }

    static AsyncTimeout j() {
        AsyncTimeout asyncTimeout = f19560j.f19562f;
        if (asyncTimeout == null) {
            long nanoTime = System.nanoTime();
            AsyncTimeout.class.wait(f19558h);
            if (f19560j.f19562f != null || System.nanoTime() - nanoTime < f19559i) {
                return null;
            }
            return f19560j;
        }
        long q2 = asyncTimeout.q(System.nanoTime());
        if (q2 > 0) {
            long j2 = q2 / 1000000;
            AsyncTimeout.class.wait(j2, (int) (q2 - (1000000 * j2)));
            return null;
        }
        f19560j.f19562f = asyncTimeout.f19562f;
        asyncTimeout.f19562f = null;
        return asyncTimeout;
    }

    private static synchronized boolean k(AsyncTimeout asyncTimeout) {
        synchronized (AsyncTimeout.class) {
            AsyncTimeout asyncTimeout2 = f19560j;
            while (asyncTimeout2 != null) {
                AsyncTimeout asyncTimeout3 = asyncTimeout2.f19562f;
                if (asyncTimeout3 == asyncTimeout) {
                    asyncTimeout2.f19562f = asyncTimeout.f19562f;
                    asyncTimeout.f19562f = null;
                    return false;
                }
                asyncTimeout2 = asyncTimeout3;
            }
            return true;
        }
    }

    private long q(long j2) {
        return this.f19563g - j2;
    }

    private static synchronized void r(AsyncTimeout asyncTimeout, long j2, boolean z) {
        synchronized (AsyncTimeout.class) {
            try {
                if (f19560j == null) {
                    f19560j = new AsyncTimeout();
                    new Watchdog().start();
                }
                long nanoTime = System.nanoTime();
                if (j2 != 0 && z) {
                    asyncTimeout.f19563g = Math.min(j2, asyncTimeout.b() - nanoTime) + nanoTime;
                } else if (j2 != 0) {
                    asyncTimeout.f19563g = j2 + nanoTime;
                } else {
                    if (!z) {
                        throw new AssertionError();
                    }
                    asyncTimeout.f19563g = asyncTimeout.b();
                }
                long q2 = asyncTimeout.q(nanoTime);
                AsyncTimeout asyncTimeout2 = f19560j;
                while (true) {
                    AsyncTimeout asyncTimeout3 = asyncTimeout2.f19562f;
                    if (asyncTimeout3 == null || q2 < asyncTimeout3.q(nanoTime)) {
                        break;
                    } else {
                        asyncTimeout2 = asyncTimeout2.f19562f;
                    }
                }
                asyncTimeout.f19562f = asyncTimeout2.f19562f;
                asyncTimeout2.f19562f = asyncTimeout;
                if (asyncTimeout2 == f19560j) {
                    AsyncTimeout.class.notify();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void l() {
        if (this.f19561e) {
            throw new IllegalStateException("Unbalanced enter/exit");
        }
        long h2 = h();
        boolean d2 = d();
        if (h2 != 0 || d2) {
            this.f19561e = true;
            r(this, h2, d2);
        }
    }

    final IOException m(IOException iOException) {
        return !o() ? iOException : p(iOException);
    }

    final void n(boolean z) {
        if (o() && z) {
            throw p(null);
        }
    }

    public final boolean o() {
        if (!this.f19561e) {
            return false;
        }
        this.f19561e = false;
        return k(this);
    }

    protected IOException p(IOException iOException) {
        InterruptedIOException interruptedIOException = new InterruptedIOException("timeout");
        if (iOException != null) {
            interruptedIOException.initCause(iOException);
        }
        return interruptedIOException;
    }

    protected void s() {
    }
}
