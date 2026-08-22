package okio;

import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public class Timeout {

    /* renamed from: d, reason: collision with root package name */
    public static final Timeout f19649d = new Timeout() { // from class: okio.Timeout.1
        @Override // okio.Timeout
        public Timeout c(long j2) {
            return this;
        }

        @Override // okio.Timeout
        public void f() {
        }

        @Override // okio.Timeout
        public Timeout g(long j2, TimeUnit timeUnit) {
            return this;
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private boolean f19650a;

    /* renamed from: b, reason: collision with root package name */
    private long f19651b;

    /* renamed from: c, reason: collision with root package name */
    private long f19652c;

    static long e(long j2, long j3) {
        return j2 == 0 ? j3 : (j3 != 0 && j2 >= j3) ? j3 : j2;
    }

    public Timeout a() {
        this.f19650a = false;
        return this;
    }

    public long b() {
        if (this.f19650a) {
            return this.f19651b;
        }
        throw new IllegalStateException("No deadline");
    }

    public Timeout c(long j2) {
        this.f19650a = true;
        this.f19651b = j2;
        return this;
    }

    public boolean d() {
        return this.f19650a;
    }

    public void f() {
        if (Thread.interrupted()) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException("interrupted");
        }
        if (this.f19650a && this.f19651b - System.nanoTime() <= 0) {
            throw new InterruptedIOException("deadline reached");
        }
    }

    public Timeout g(long j2, TimeUnit timeUnit) {
        if (j2 >= 0) {
            if (timeUnit == null) {
                throw new IllegalArgumentException("unit == null");
            }
            this.f19652c = timeUnit.toNanos(j2);
            return this;
        }
        throw new IllegalArgumentException("timeout < 0: " + j2);
    }

    public long h() {
        return this.f19652c;
    }

    public final void i(Object obj) {
        try {
            boolean d2 = d();
            long h2 = h();
            long j2 = 0;
            if (!d2 && h2 == 0) {
                obj.wait();
                return;
            }
            long nanoTime = System.nanoTime();
            if (d2 && h2 != 0) {
                h2 = Math.min(h2, b() - nanoTime);
            } else if (d2) {
                h2 = b() - nanoTime;
            }
            if (h2 > 0) {
                long j3 = h2 / 1000000;
                obj.wait(j3, (int) (h2 - (1000000 * j3)));
                j2 = System.nanoTime() - nanoTime;
            }
            if (j2 >= h2) {
                throw new InterruptedIOException("timeout");
            }
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException("interrupted");
        }
    }
}
