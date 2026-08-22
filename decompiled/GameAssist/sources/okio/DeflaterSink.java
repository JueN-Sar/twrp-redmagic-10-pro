package okio;

import java.util.zip.Deflater;

/* loaded from: classes2.dex */
public final class DeflaterSink implements Sink {

    /* renamed from: c, reason: collision with root package name */
    private final BufferedSink f19581c;

    /* renamed from: h, reason: collision with root package name */
    private final Deflater f19582h;

    /* renamed from: i, reason: collision with root package name */
    private boolean f19583i;

    private void a(boolean z) {
        Segment W;
        int deflate;
        Buffer b2 = this.f19581c.b();
        while (true) {
            W = b2.W(1);
            if (z) {
                Deflater deflater = this.f19582h;
                byte[] bArr = W.f19640a;
                int i2 = W.f19642c;
                deflate = deflater.deflate(bArr, i2, 8192 - i2, 2);
            } else {
                Deflater deflater2 = this.f19582h;
                byte[] bArr2 = W.f19640a;
                int i3 = W.f19642c;
                deflate = deflater2.deflate(bArr2, i3, 8192 - i3);
            }
            if (deflate > 0) {
                W.f19642c += deflate;
                b2.f19572h += deflate;
                this.f19581c.r();
            } else if (this.f19582h.needsInput()) {
                break;
            }
        }
        if (W.f19641b == W.f19642c) {
            b2.f19571c = W.b();
            SegmentPool.a(W);
        }
    }

    void c() {
        this.f19582h.finish();
        a(false);
    }

    @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.f19583i) {
            return;
        }
        try {
            c();
            th = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            this.f19582h.end();
        } catch (Throwable th2) {
            if (th == null) {
                th = th2;
            }
        }
        try {
            this.f19581c.close();
        } catch (Throwable th3) {
            if (th == null) {
                th = th3;
            }
        }
        this.f19583i = true;
        if (th != null) {
            Util.e(th);
        }
    }

    @Override // okio.Sink, java.io.Flushable
    public void flush() {
        a(true);
        this.f19581c.flush();
    }

    @Override // okio.Sink
    public Timeout n() {
        return this.f19581c.n();
    }

    public String toString() {
        return "DeflaterSink(" + this.f19581c + ")";
    }

    @Override // okio.Sink
    public void w(Buffer buffer, long j2) {
        Util.b(buffer.f19572h, 0L, j2);
        while (j2 > 0) {
            Segment segment = buffer.f19571c;
            int min = (int) Math.min(j2, segment.f19642c - segment.f19641b);
            this.f19582h.setInput(segment.f19640a, segment.f19641b, min);
            a(false);
            long j3 = min;
            buffer.f19572h -= j3;
            int i2 = segment.f19641b + min;
            segment.f19641b = i2;
            if (i2 == segment.f19642c) {
                buffer.f19571c = segment.b();
                SegmentPool.a(segment);
            }
            j2 -= j3;
        }
    }
}
