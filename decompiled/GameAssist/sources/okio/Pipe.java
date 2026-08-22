package okio;

import java.io.IOException;

/* loaded from: classes2.dex */
public final class Pipe {

    /* renamed from: a, reason: collision with root package name */
    final long f19619a;

    /* renamed from: b, reason: collision with root package name */
    final Buffer f19620b;

    /* renamed from: c, reason: collision with root package name */
    boolean f19621c;

    /* renamed from: d, reason: collision with root package name */
    boolean f19622d;

    /* renamed from: e, reason: collision with root package name */
    private Sink f19623e;

    final class PipeSink implements Sink {

        /* renamed from: c, reason: collision with root package name */
        final PushableTimeout f19624c;

        /* renamed from: h, reason: collision with root package name */
        final /* synthetic */ Pipe f19625h;

        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            Sink sink;
            synchronized (this.f19625h.f19620b) {
                try {
                    Pipe pipe = this.f19625h;
                    if (pipe.f19621c) {
                        return;
                    }
                    if (pipe.f19623e != null) {
                        sink = this.f19625h.f19623e;
                    } else {
                        Pipe pipe2 = this.f19625h;
                        if (pipe2.f19622d && pipe2.f19620b.size() > 0) {
                            throw new IOException("source is closed");
                        }
                        Pipe pipe3 = this.f19625h;
                        pipe3.f19621c = true;
                        pipe3.f19620b.notifyAll();
                        sink = null;
                    }
                    if (sink != null) {
                        this.f19624c.k(sink.n());
                        try {
                            sink.close();
                        } finally {
                            this.f19624c.j();
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @Override // okio.Sink, java.io.Flushable
        public void flush() {
            Sink sink;
            synchronized (this.f19625h.f19620b) {
                try {
                    Pipe pipe = this.f19625h;
                    if (pipe.f19621c) {
                        throw new IllegalStateException("closed");
                    }
                    if (pipe.f19623e != null) {
                        sink = this.f19625h.f19623e;
                    } else {
                        Pipe pipe2 = this.f19625h;
                        if (pipe2.f19622d && pipe2.f19620b.size() > 0) {
                            throw new IOException("source is closed");
                        }
                        sink = null;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (sink != null) {
                this.f19624c.k(sink.n());
                try {
                    sink.flush();
                } finally {
                    this.f19624c.j();
                }
            }
        }

        @Override // okio.Sink
        public Timeout n() {
            return this.f19624c;
        }

        @Override // okio.Sink
        public void w(Buffer buffer, long j2) {
            Sink sink;
            synchronized (this.f19625h.f19620b) {
                try {
                    if (!this.f19625h.f19621c) {
                        while (true) {
                            if (j2 <= 0) {
                                sink = null;
                                break;
                            }
                            if (this.f19625h.f19623e != null) {
                                sink = this.f19625h.f19623e;
                                break;
                            }
                            Pipe pipe = this.f19625h;
                            if (pipe.f19622d) {
                                throw new IOException("source is closed");
                            }
                            long size = pipe.f19619a - pipe.f19620b.size();
                            if (size == 0) {
                                this.f19624c.i(this.f19625h.f19620b);
                            } else {
                                long min = Math.min(size, j2);
                                this.f19625h.f19620b.w(buffer, min);
                                j2 -= min;
                                this.f19625h.f19620b.notifyAll();
                            }
                        }
                    } else {
                        throw new IllegalStateException("closed");
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (sink != null) {
                this.f19624c.k(sink.n());
                try {
                    sink.w(buffer, j2);
                } finally {
                    this.f19624c.j();
                }
            }
        }
    }

    final class PipeSource implements Source {

        /* renamed from: c, reason: collision with root package name */
        final Timeout f19626c;

        /* renamed from: h, reason: collision with root package name */
        final /* synthetic */ Pipe f19627h;

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            synchronized (this.f19627h.f19620b) {
                Pipe pipe = this.f19627h;
                pipe.f19622d = true;
                pipe.f19620b.notifyAll();
            }
        }

        @Override // okio.Source
        public long d0(Buffer buffer, long j2) {
            synchronized (this.f19627h.f19620b) {
                try {
                    if (this.f19627h.f19622d) {
                        throw new IllegalStateException("closed");
                    }
                    while (this.f19627h.f19620b.size() == 0) {
                        Pipe pipe = this.f19627h;
                        if (pipe.f19621c) {
                            return -1L;
                        }
                        this.f19626c.i(pipe.f19620b);
                    }
                    long d0 = this.f19627h.f19620b.d0(buffer, j2);
                    this.f19627h.f19620b.notifyAll();
                    return d0;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }
}
