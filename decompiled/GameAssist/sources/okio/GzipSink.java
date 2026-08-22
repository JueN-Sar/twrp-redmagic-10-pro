package okio;

import java.util.zip.CRC32;
import java.util.zip.Deflater;

/* loaded from: classes2.dex */
public final class GzipSink implements Sink {

    /* renamed from: c, reason: collision with root package name */
    private final BufferedSink f19587c;

    /* renamed from: h, reason: collision with root package name */
    private final Deflater f19588h;

    /* renamed from: i, reason: collision with root package name */
    private final DeflaterSink f19589i;

    /* renamed from: j, reason: collision with root package name */
    private boolean f19590j;

    /* renamed from: k, reason: collision with root package name */
    private final CRC32 f19591k;

    private void a(Buffer buffer, long j2) {
        Segment segment = buffer.f19571c;
        while (j2 > 0) {
            int min = (int) Math.min(j2, segment.f19642c - segment.f19641b);
            this.f19591k.update(segment.f19640a, segment.f19641b, min);
            j2 -= min;
            segment = segment.f19645f;
        }
    }

    private void c() {
        this.f19587c.U((int) this.f19591k.getValue());
        this.f19587c.U((int) this.f19588h.getBytesRead());
    }

    @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.f19590j) {
            return;
        }
        try {
            this.f19589i.c();
            c();
            th = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            this.f19588h.end();
        } catch (Throwable th2) {
            if (th == null) {
                th = th2;
            }
        }
        try {
            this.f19587c.close();
        } catch (Throwable th3) {
            if (th == null) {
                th = th3;
            }
        }
        this.f19590j = true;
        if (th != null) {
            Util.e(th);
        }
    }

    @Override // okio.Sink, java.io.Flushable
    public void flush() {
        this.f19589i.flush();
    }

    @Override // okio.Sink
    public Timeout n() {
        return this.f19587c.n();
    }

    @Override // okio.Sink
    public void w(Buffer buffer, long j2) {
        if (j2 < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j2);
        }
        if (j2 == 0) {
            return;
        }
        a(buffer, j2);
        this.f19589i.w(buffer, j2);
    }
}
