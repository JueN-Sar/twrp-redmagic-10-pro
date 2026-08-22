package okio;

import java.io.Closeable;

/* loaded from: classes2.dex */
public interface Source extends Closeable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    long d0(Buffer buffer, long j2);
}
