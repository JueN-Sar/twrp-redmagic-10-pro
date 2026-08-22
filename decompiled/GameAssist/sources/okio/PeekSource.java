package okio;

/* loaded from: classes2.dex */
final class PeekSource implements Source {

    /* renamed from: c, reason: collision with root package name */
    private final BufferedSource f19613c;

    /* renamed from: h, reason: collision with root package name */
    private final Buffer f19614h;

    /* renamed from: i, reason: collision with root package name */
    private Segment f19615i;

    /* renamed from: j, reason: collision with root package name */
    private int f19616j;

    /* renamed from: k, reason: collision with root package name */
    private boolean f19617k;

    /* renamed from: l, reason: collision with root package name */
    private long f19618l;

    PeekSource(BufferedSource bufferedSource) {
        this.f19613c = bufferedSource;
        Buffer b2 = bufferedSource.b();
        this.f19614h = b2;
        Segment segment = b2.f19571c;
        this.f19615i = segment;
        this.f19616j = segment != null ? segment.f19641b : -1;
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f19617k = true;
    }

    @Override // okio.Source
    public long d0(Buffer buffer, long j2) {
        Segment segment;
        Segment segment2;
        if (j2 < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j2);
        }
        if (this.f19617k) {
            throw new IllegalStateException("closed");
        }
        Segment segment3 = this.f19615i;
        if (segment3 != null && (segment3 != (segment2 = this.f19614h.f19571c) || this.f19616j != segment2.f19641b)) {
            throw new IllegalStateException("Peek source is invalid because upstream source was used");
        }
        if (j2 == 0) {
            return 0L;
        }
        if (!this.f19613c.z(this.f19618l + 1)) {
            return -1L;
        }
        if (this.f19615i == null && (segment = this.f19614h.f19571c) != null) {
            this.f19615i = segment;
            this.f19616j = segment.f19641b;
        }
        long min = Math.min(j2, this.f19614h.f19572h - this.f19618l);
        this.f19614h.h(buffer, this.f19618l, min);
        this.f19618l += min;
        return min;
    }
}
