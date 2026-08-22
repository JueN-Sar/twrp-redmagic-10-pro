package okio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes2.dex */
public final class Okio {

    /* renamed from: a, reason: collision with root package name */
    static final Logger f19605a = Logger.getLogger(Okio.class.getName());

    /* renamed from: okio.Okio$1, reason: invalid class name */
    final class AnonymousClass1 implements Sink {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ Timeout f19606c;

        /* renamed from: h, reason: collision with root package name */
        final /* synthetic */ OutputStream f19607h;

        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.f19607h.close();
        }

        @Override // okio.Sink, java.io.Flushable
        public void flush() {
            this.f19607h.flush();
        }

        @Override // okio.Sink
        public Timeout n() {
            return this.f19606c;
        }

        public String toString() {
            return "sink(" + this.f19607h + ")";
        }

        @Override // okio.Sink
        public void w(Buffer buffer, long j2) {
            Util.b(buffer.f19572h, 0L, j2);
            while (j2 > 0) {
                this.f19606c.f();
                Segment segment = buffer.f19571c;
                int min = (int) Math.min(j2, segment.f19642c - segment.f19641b);
                this.f19607h.write(segment.f19640a, segment.f19641b, min);
                int i2 = segment.f19641b + min;
                segment.f19641b = i2;
                long j3 = min;
                j2 -= j3;
                buffer.f19572h -= j3;
                if (i2 == segment.f19642c) {
                    buffer.f19571c = segment.b();
                    SegmentPool.a(segment);
                }
            }
        }
    }

    /* renamed from: okio.Okio$3, reason: invalid class name */
    final class AnonymousClass3 implements Sink {
        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // okio.Sink, java.io.Flushable
        public void flush() {
        }

        @Override // okio.Sink
        public Timeout n() {
            return Timeout.f19649d;
        }

        @Override // okio.Sink
        public void w(Buffer buffer, long j2) {
            buffer.skip(j2);
        }
    }

    /* renamed from: okio.Okio$4, reason: invalid class name */
    final class AnonymousClass4 extends AsyncTimeout {

        /* renamed from: k, reason: collision with root package name */
        final /* synthetic */ Socket f19610k;

        @Override // okio.AsyncTimeout
        protected IOException p(IOException iOException) {
            SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
            if (iOException != null) {
                socketTimeoutException.initCause(iOException);
            }
            return socketTimeoutException;
        }

        @Override // okio.AsyncTimeout
        protected void s() {
            try {
                this.f19610k.close();
            } catch (AssertionError e2) {
                if (!Okio.b(e2)) {
                    throw e2;
                }
                Okio.f19605a.log(Level.WARNING, "Failed to close timed out socket " + this.f19610k, (Throwable) e2);
            } catch (Exception e3) {
                Okio.f19605a.log(Level.WARNING, "Failed to close timed out socket " + this.f19610k, (Throwable) e3);
            }
        }
    }

    public static BufferedSource a(Source source) {
        return new RealBufferedSource(source);
    }

    static boolean b(AssertionError assertionError) {
        return (assertionError.getCause() == null || assertionError.getMessage() == null || !assertionError.getMessage().contains("getsockname failed")) ? false : true;
    }

    public static Source c(InputStream inputStream) {
        return d(inputStream, new Timeout());
    }

    private static Source d(final InputStream inputStream, final Timeout timeout) {
        if (inputStream == null) {
            throw new IllegalArgumentException("in == null");
        }
        if (timeout != null) {
            return new Source() { // from class: okio.Okio.2
                @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
                public void close() {
                    inputStream.close();
                }

                @Override // okio.Source
                public long d0(Buffer buffer, long j2) {
                    if (j2 < 0) {
                        throw new IllegalArgumentException("byteCount < 0: " + j2);
                    }
                    if (j2 == 0) {
                        return 0L;
                    }
                    try {
                        Timeout.this.f();
                        Segment W = buffer.W(1);
                        int read = inputStream.read(W.f19640a, W.f19642c, (int) Math.min(j2, 8192 - W.f19642c));
                        if (read != -1) {
                            W.f19642c += read;
                            long j3 = read;
                            buffer.f19572h += j3;
                            return j3;
                        }
                        if (W.f19641b != W.f19642c) {
                            return -1L;
                        }
                        buffer.f19571c = W.b();
                        SegmentPool.a(W);
                        return -1L;
                    } catch (AssertionError e2) {
                        if (Okio.b(e2)) {
                            throw new IOException(e2);
                        }
                        throw e2;
                    }
                }

                public String toString() {
                    return "source(" + inputStream + ")";
                }
            };
        }
        throw new IllegalArgumentException("timeout == null");
    }
}
