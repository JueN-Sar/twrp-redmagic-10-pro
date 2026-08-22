package okio;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Inflater;

/* loaded from: classes2.dex */
public final class GzipSource implements Source {

    /* renamed from: c, reason: collision with root package name */
    private int f19592c;

    /* renamed from: h, reason: collision with root package name */
    private final BufferedSource f19593h;

    /* renamed from: i, reason: collision with root package name */
    private final Inflater f19594i;

    /* renamed from: j, reason: collision with root package name */
    private final InflaterSource f19595j;

    /* renamed from: k, reason: collision with root package name */
    private final CRC32 f19596k;

    private void a(String str, int i2, int i3) {
        if (i3 != i2) {
            throw new IOException(String.format("%s: actual 0x%08x != expected 0x%08x", str, Integer.valueOf(i3), Integer.valueOf(i2)));
        }
    }

    private void c() {
        this.f19593h.O(10L);
        byte j2 = this.f19593h.b().j(3L);
        boolean z = ((j2 >> 1) & 1) == 1;
        if (z) {
            e(this.f19593h.b(), 0L, 10L);
        }
        a("ID1ID2", 8075, this.f19593h.readShort());
        this.f19593h.skip(8L);
        if (((j2 >> 2) & 1) == 1) {
            this.f19593h.O(2L);
            if (z) {
                e(this.f19593h.b(), 0L, 2L);
            }
            long J = this.f19593h.b().J() & 65535;
            this.f19593h.O(J);
            if (z) {
                e(this.f19593h.b(), 0L, J);
            }
            this.f19593h.skip(J);
        }
        if (((j2 >> 3) & 1) == 1) {
            long Q = this.f19593h.Q((byte) 0);
            if (Q == -1) {
                throw new EOFException();
            }
            if (z) {
                e(this.f19593h.b(), 0L, Q + 1);
            }
            this.f19593h.skip(Q + 1);
        }
        if (((j2 >> 4) & 1) == 1) {
            long Q2 = this.f19593h.Q((byte) 0);
            if (Q2 == -1) {
                throw new EOFException();
            }
            if (z) {
                e(this.f19593h.b(), 0L, Q2 + 1);
            }
            this.f19593h.skip(Q2 + 1);
        }
        if (z) {
            a("FHCRC", this.f19593h.J(), (short) this.f19596k.getValue());
            this.f19596k.reset();
        }
    }

    private void d() {
        a("CRC", this.f19593h.b0(), (int) this.f19596k.getValue());
        a("ISIZE", this.f19593h.b0(), (int) this.f19594i.getBytesWritten());
    }

    private void e(Buffer buffer, long j2, long j3) {
        Segment segment = buffer.f19571c;
        while (true) {
            int i2 = segment.f19642c;
            int i3 = segment.f19641b;
            if (j2 < i2 - i3) {
                break;
            }
            j2 -= i2 - i3;
            segment = segment.f19645f;
        }
        while (j3 > 0) {
            int min = (int) Math.min(segment.f19642c - r6, j3);
            this.f19596k.update(segment.f19640a, (int) (segment.f19641b + j2), min);
            j3 -= min;
            segment = segment.f19645f;
            j2 = 0;
        }
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f19595j.close();
    }

    @Override // okio.Source
    public long d0(Buffer buffer, long j2) {
        if (j2 < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j2);
        }
        if (j2 == 0) {
            return 0L;
        }
        if (this.f19592c == 0) {
            c();
            this.f19592c = 1;
        }
        if (this.f19592c == 1) {
            long j3 = buffer.f19572h;
            long d0 = this.f19595j.d0(buffer, j2);
            if (d0 != -1) {
                e(buffer, j3, d0);
                return d0;
            }
            this.f19592c = 2;
        }
        if (this.f19592c == 2) {
            d();
            this.f19592c = 3;
            if (!this.f19593h.V()) {
                throw new IOException("gzip finished without exhausting source");
            }
        }
        return -1L;
    }
}
