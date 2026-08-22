package okio;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/* loaded from: classes2.dex */
public final class InflaterSource implements Source {

    /* renamed from: c, reason: collision with root package name */
    private final BufferedSource f19601c;

    /* renamed from: h, reason: collision with root package name */
    private final Inflater f19602h;

    /* renamed from: i, reason: collision with root package name */
    private int f19603i;

    /* renamed from: j, reason: collision with root package name */
    private boolean f19604j;

    private void c() {
        int i2 = this.f19603i;
        if (i2 == 0) {
            return;
        }
        int remaining = i2 - this.f19602h.getRemaining();
        this.f19603i -= remaining;
        this.f19601c.skip(remaining);
    }

    public final boolean a() {
        if (!this.f19602h.needsInput()) {
            return false;
        }
        c();
        if (this.f19602h.getRemaining() != 0) {
            throw new IllegalStateException("?");
        }
        if (this.f19601c.V()) {
            return true;
        }
        Segment segment = this.f19601c.b().f19571c;
        int i2 = segment.f19642c;
        int i3 = segment.f19641b;
        int i4 = i2 - i3;
        this.f19603i = i4;
        this.f19602h.setInput(segment.f19640a, i3, i4);
        return false;
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.f19604j) {
            return;
        }
        this.f19602h.end();
        this.f19604j = true;
        this.f19601c.close();
    }

    @Override // okio.Source
    public long d0(Buffer buffer, long j2) {
        boolean a2;
        if (j2 < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j2);
        }
        if (this.f19604j) {
            throw new IllegalStateException("closed");
        }
        if (j2 == 0) {
            return 0L;
        }
        do {
            a2 = a();
            try {
                Segment W = buffer.W(1);
                int inflate = this.f19602h.inflate(W.f19640a, W.f19642c, (int) Math.min(j2, 8192 - W.f19642c));
                if (inflate > 0) {
                    W.f19642c += inflate;
                    long j3 = inflate;
                    buffer.f19572h += j3;
                    return j3;
                }
                if (!this.f19602h.finished() && !this.f19602h.needsDictionary()) {
                }
                c();
                if (W.f19641b != W.f19642c) {
                    return -1L;
                }
                buffer.f19571c = W.b();
                SegmentPool.a(W);
                return -1L;
            } catch (DataFormatException e2) {
                throw new IOException(e2);
            }
        } while (!a2);
        throw new EOFException("source exhausted prematurely");
    }
}
