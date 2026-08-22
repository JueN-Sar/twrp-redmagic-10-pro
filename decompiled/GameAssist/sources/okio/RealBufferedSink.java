package okio;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
final class RealBufferedSink implements BufferedSink {

    /* renamed from: c, reason: collision with root package name */
    public final Buffer f19632c;

    /* renamed from: h, reason: collision with root package name */
    public final Sink f19633h;

    /* renamed from: i, reason: collision with root package name */
    boolean f19634i;

    @Override // okio.BufferedSink
    public BufferedSink U(int i2) {
        if (this.f19634i) {
            throw new IllegalStateException("closed");
        }
        this.f19632c.U(i2);
        return r();
    }

    @Override // okio.BufferedSink
    public Buffer b() {
        return this.f19632c;
    }

    @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.f19634i) {
            return;
        }
        try {
            Buffer buffer = this.f19632c;
            long j2 = buffer.f19572h;
            if (j2 > 0) {
                this.f19633h.w(buffer, j2);
            }
            th = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            this.f19633h.close();
        } catch (Throwable th2) {
            if (th == null) {
                th = th2;
            }
        }
        this.f19634i = true;
        if (th != null) {
            Util.e(th);
        }
    }

    @Override // okio.BufferedSink, okio.Sink, java.io.Flushable
    public void flush() {
        if (this.f19634i) {
            throw new IllegalStateException("closed");
        }
        Buffer buffer = this.f19632c;
        long j2 = buffer.f19572h;
        if (j2 > 0) {
            this.f19633h.w(buffer, j2);
        }
        this.f19633h.flush();
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return !this.f19634i;
    }

    @Override // okio.Sink
    public Timeout n() {
        return this.f19633h.n();
    }

    @Override // okio.BufferedSink
    public BufferedSink r() {
        if (this.f19634i) {
            throw new IllegalStateException("closed");
        }
        long e2 = this.f19632c.e();
        if (e2 > 0) {
            this.f19633h.w(this.f19632c, e2);
        }
        return this;
    }

    public String toString() {
        return "buffer(" + this.f19633h + ")";
    }

    @Override // okio.BufferedSink
    public BufferedSink v(String str) {
        if (this.f19634i) {
            throw new IllegalStateException("closed");
        }
        this.f19632c.v(str);
        return r();
    }

    @Override // okio.Sink
    public void w(Buffer buffer, long j2) {
        if (this.f19634i) {
            throw new IllegalStateException("closed");
        }
        this.f19632c.w(buffer, j2);
        r();
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) {
        if (this.f19634i) {
            throw new IllegalStateException("closed");
        }
        int write = this.f19632c.write(byteBuffer);
        r();
        return write;
    }

    @Override // okio.BufferedSink
    public BufferedSink writeByte(int i2) {
        if (this.f19634i) {
            throw new IllegalStateException("closed");
        }
        this.f19632c.writeByte(i2);
        return r();
    }

    @Override // okio.BufferedSink
    public BufferedSink y(String str, int i2, int i3) {
        if (this.f19634i) {
            throw new IllegalStateException("closed");
        }
        this.f19632c.y(str, i2, i3);
        return r();
    }

    /* renamed from: okio.RealBufferedSink$1, reason: invalid class name */
    class AnonymousClass1 extends OutputStream {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ RealBufferedSink f19635c;

        @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.f19635c.close();
        }

        @Override // java.io.OutputStream, java.io.Flushable
        public void flush() {
            RealBufferedSink realBufferedSink = this.f19635c;
            if (realBufferedSink.f19634i) {
                return;
            }
            realBufferedSink.flush();
        }

        public String toString() {
            return this.f19635c + ".outputStream()";
        }

        @Override // java.io.OutputStream
        public void write(int i2) {
            RealBufferedSink realBufferedSink = this.f19635c;
            if (realBufferedSink.f19634i) {
                throw new IOException("closed");
            }
            realBufferedSink.f19632c.writeByte((byte) i2);
            this.f19635c.r();
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i2, int i3) {
            RealBufferedSink realBufferedSink = this.f19635c;
            if (!realBufferedSink.f19634i) {
                realBufferedSink.f19632c.Y(bArr, i2, i3);
                this.f19635c.r();
                return;
            }
            throw new IOException("closed");
        }
    }
}
