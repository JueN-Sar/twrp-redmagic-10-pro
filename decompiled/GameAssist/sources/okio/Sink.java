package okio;

import java.io.Closeable;
import java.io.Flushable;

/* loaded from: classes2.dex */
public interface Sink extends Closeable, Flushable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    void flush();

    Timeout n();

    void w(Buffer buffer, long j2);
}
