package okio;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
final class RealBufferedSource implements BufferedSource {

    /* renamed from: c, reason: collision with root package name */
    public final Buffer f19636c = new Buffer();

    /* renamed from: h, reason: collision with root package name */
    public final Source f19637h;

    /* renamed from: i, reason: collision with root package name */
    boolean f19638i;

    RealBufferedSource(Source source) {
        if (source == null) {
            throw new NullPointerException("source == null");
        }
        this.f19637h = source;
    }

    @Override // okio.BufferedSource
    public short J() {
        O(2L);
        return this.f19636c.J();
    }

    @Override // okio.BufferedSource
    public void O(long j2) {
        if (!z(j2)) {
            throw new EOFException();
        }
    }

    @Override // okio.BufferedSource
    public long Q(byte b2) {
        return a(b2, 0L, Long.MAX_VALUE);
    }

    @Override // okio.BufferedSource
    public boolean V() {
        if (this.f19638i) {
            throw new IllegalStateException("closed");
        }
        return this.f19636c.V() && this.f19637h.d0(this.f19636c, 8192L) == -1;
    }

    public long a(byte b2, long j2, long j3) {
        if (this.f19638i) {
            throw new IllegalStateException("closed");
        }
        if (j2 < 0 || j3 < j2) {
            throw new IllegalArgumentException(String.format("fromIndex=%s toIndex=%s", Long.valueOf(j2), Long.valueOf(j3)));
        }
        while (j2 < j3) {
            long k2 = this.f19636c.k(b2, j2, j3);
            if (k2 == -1) {
                Buffer buffer = this.f19636c;
                long j4 = buffer.f19572h;
                if (j4 >= j3 || this.f19637h.d0(buffer, 8192L) == -1) {
                    break;
                }
                j2 = Math.max(j2, j4);
            } else {
                return k2;
            }
        }
        return -1L;
    }

    @Override // okio.BufferedSource, okio.BufferedSink
    public Buffer b() {
        return this.f19636c;
    }

    @Override // okio.BufferedSource
    public int b0() {
        O(4L);
        return this.f19636c.b0();
    }

    public long c(ByteString byteString, long j2) {
        if (this.f19638i) {
            throw new IllegalStateException("closed");
        }
        while (true) {
            long p2 = this.f19636c.p(byteString, j2);
            if (p2 != -1) {
                return p2;
            }
            Buffer buffer = this.f19636c;
            long j3 = buffer.f19572h;
            if (this.f19637h.d0(buffer, 8192L) == -1) {
                return -1L;
            }
            j2 = Math.max(j2, (j3 - byteString.r()) + 1);
        }
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.f19638i) {
            return;
        }
        this.f19638i = true;
        this.f19637h.close();
        this.f19636c.a();
    }

    public long d(ByteString byteString, long j2) {
        if (this.f19638i) {
            throw new IllegalStateException("closed");
        }
        while (true) {
            long s2 = this.f19636c.s(byteString, j2);
            if (s2 != -1) {
                return s2;
            }
            Buffer buffer = this.f19636c;
            long j3 = buffer.f19572h;
            if (this.f19637h.d0(buffer, 8192L) == -1) {
                return -1L;
            }
            j2 = Math.max(j2, j3);
        }
    }

    @Override // okio.Source
    public long d0(Buffer buffer, long j2) {
        if (buffer == null) {
            throw new IllegalArgumentException("sink == null");
        }
        if (j2 < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j2);
        }
        if (this.f19638i) {
            throw new IllegalStateException("closed");
        }
        Buffer buffer2 = this.f19636c;
        if (buffer2.f19572h == 0 && this.f19637h.d0(buffer2, 8192L) == -1) {
            return -1L;
        }
        return this.f19636c.d0(buffer, Math.min(j2, this.f19636c.f19572h));
    }

    @Override // okio.BufferedSource
    public InputStream g0() {
        return new InputStream() { // from class: okio.RealBufferedSource.1
            @Override // java.io.InputStream
            public int available() {
                RealBufferedSource realBufferedSource = RealBufferedSource.this;
                if (realBufferedSource.f19638i) {
                    throw new IOException("closed");
                }
                return (int) Math.min(realBufferedSource.f19636c.f19572h, 2147483647L);
            }

            @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                RealBufferedSource.this.close();
            }

            @Override // java.io.InputStream
            public int read() {
                RealBufferedSource realBufferedSource = RealBufferedSource.this;
                if (realBufferedSource.f19638i) {
                    throw new IOException("closed");
                }
                Buffer buffer = realBufferedSource.f19636c;
                if (buffer.f19572h == 0 && realBufferedSource.f19637h.d0(buffer, 8192L) == -1) {
                    return -1;
                }
                return RealBufferedSource.this.f19636c.readByte() & 255;
            }

            public String toString() {
                return RealBufferedSource.this + ".inputStream()";
            }

            @Override // java.io.InputStream
            public int read(byte[] bArr, int i2, int i3) {
                if (!RealBufferedSource.this.f19638i) {
                    Util.b(bArr.length, i2, i3);
                    RealBufferedSource realBufferedSource = RealBufferedSource.this;
                    Buffer buffer = realBufferedSource.f19636c;
                    if (buffer.f19572h == 0 && realBufferedSource.f19637h.d0(buffer, 8192L) == -1) {
                        return -1;
                    }
                    return RealBufferedSource.this.f19636c.read(bArr, i2, i3);
                }
                throw new IOException("closed");
            }
        };
    }

    @Override // okio.BufferedSource
    public int i0(Options options) {
        if (this.f19638i) {
            throw new IllegalStateException("closed");
        }
        do {
            int P = this.f19636c.P(options, true);
            if (P == -1) {
                return -1;
            }
            if (P != -2) {
                this.f19636c.skip(options.f19611c[P].r());
                return P;
            }
        } while (this.f19637h.d0(this.f19636c, 8192L) != -1);
        return -1;
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return !this.f19638i;
    }

    @Override // okio.BufferedSource
    public long m(ByteString byteString) {
        return c(byteString, 0L);
    }

    @Override // okio.BufferedSource
    public BufferedSource peek() {
        return Okio.a(new PeekSource(this));
    }

    @Override // okio.BufferedSource
    public long q(ByteString byteString) {
        return d(byteString, 0L);
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) {
        Buffer buffer = this.f19636c;
        if (buffer.f19572h == 0 && this.f19637h.d0(buffer, 8192L) == -1) {
            return -1;
        }
        return this.f19636c.read(byteBuffer);
    }

    @Override // okio.BufferedSource
    public byte readByte() {
        O(1L);
        return this.f19636c.readByte();
    }

    @Override // okio.BufferedSource
    public short readShort() {
        O(2L);
        return this.f19636c.readShort();
    }

    @Override // okio.BufferedSource
    public void skip(long j2) {
        if (this.f19638i) {
            throw new IllegalStateException("closed");
        }
        while (j2 > 0) {
            Buffer buffer = this.f19636c;
            if (buffer.f19572h == 0 && this.f19637h.d0(buffer, 8192L) == -1) {
                throw new EOFException();
            }
            long min = Math.min(j2, this.f19636c.size());
            this.f19636c.skip(min);
            j2 -= min;
        }
    }

    public String toString() {
        return "buffer(" + this.f19637h + ")";
    }

    @Override // okio.BufferedSource
    public boolean z(long j2) {
        Buffer buffer;
        if (j2 < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j2);
        }
        if (this.f19638i) {
            throw new IllegalStateException("closed");
        }
        do {
            buffer = this.f19636c;
            if (buffer.f19572h >= j2) {
                return true;
            }
        } while (this.f19637h.d0(buffer, 8192L) != -1);
        return false;
    }
}
