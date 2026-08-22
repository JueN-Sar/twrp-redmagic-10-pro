package androidx.fragment.app;

import android.util.Log;
import java.io.Writer;

/* loaded from: classes.dex */
final class LogWriter extends Writer {

    /* renamed from: c, reason: collision with root package name */
    private final String f4208c;

    /* renamed from: h, reason: collision with root package name */
    private StringBuilder f4209h = new StringBuilder(128);

    LogWriter(String str) {
        this.f4208c = str;
    }

    private void a() {
        if (this.f4209h.length() > 0) {
            Log.d(this.f4208c, this.f4209h.toString());
            StringBuilder sb = this.f4209h;
            sb.delete(0, sb.length());
        }
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        a();
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() {
        a();
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            char c2 = cArr[i2 + i4];
            if (c2 == '\n') {
                a();
            } else {
                this.f4209h.append(c2);
            }
        }
    }
}
