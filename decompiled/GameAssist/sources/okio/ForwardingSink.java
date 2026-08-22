package okio;

/* loaded from: classes2.dex */
public abstract class ForwardingSink implements Sink {

    /* renamed from: c, reason: collision with root package name */
    private final Sink f19584c;

    @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f19584c.close();
    }

    @Override // okio.Sink, java.io.Flushable
    public void flush() {
        this.f19584c.flush();
    }

    @Override // okio.Sink
    public Timeout n() {
        return this.f19584c.n();
    }

    public String toString() {
        return getClass().getSimpleName() + "(" + this.f19584c.toString() + ")";
    }

    @Override // okio.Sink
    public void w(Buffer buffer, long j2) {
        this.f19584c.w(buffer, j2);
    }
}
