package androidx.core.os;

import android.os.Trace;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;

@Deprecated
/* loaded from: classes.dex */
public final class TraceCompat {

    @RequiresApi
    static class Api29Impl {
        @DoNotInline
        static void a(String str, int i2) {
            Trace.beginAsyncSection(str, i2);
        }

        @DoNotInline
        static void b(String str, int i2) {
            Trace.endAsyncSection(str, i2);
        }

        @DoNotInline
        static boolean c() {
            return Trace.isEnabled();
        }

        @DoNotInline
        static void d(String str, long j2) {
            Trace.setCounter(str, j2);
        }
    }

    public static void a(String str) {
        Trace.beginSection(str);
    }

    public static void b() {
        Trace.endSection();
    }
}
