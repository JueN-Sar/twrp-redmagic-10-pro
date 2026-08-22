package androidx.core.util;

import android.util.Log;
import androidx.annotation.RestrictTo;
import java.io.Writer;

@RestrictTo
@Deprecated
/* loaded from: classes.dex */
public class LogWriter extends Writer {

    /* renamed from: c, reason: collision with root package name */
    private final String f3261c;

    /* renamed from: h, reason: collision with root package name */
    private StringBuilder f3262h;

    private void a() {
        if (this.f3262h.length() > 0) {
            Log.d(this.f3261c, this.f3262h.toString());
            StringBuilder sb = this.f3262h;
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
                this.f3262h.append(c2);
            }
        }
    }
}
