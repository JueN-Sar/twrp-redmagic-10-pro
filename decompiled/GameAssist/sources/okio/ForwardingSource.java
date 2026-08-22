package okio;

/* loaded from: classes2.dex */
public abstract class ForwardingSource implements Source {

    /* renamed from: c, reason: collision with root package name */
    private final Source f19585c;

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f19585c.close();
    }

    @Override // okio.Source
    public long d0(Buffer buffer, long j2) {
        return this.f19585c.d0(buffer, j2);
    }

    public String toString() {
        return getClass().getSimpleName() + "(" + this.f19585c.toString() + ")";
    }
}
