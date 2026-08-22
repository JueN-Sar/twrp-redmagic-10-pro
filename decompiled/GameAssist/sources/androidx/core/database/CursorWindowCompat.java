package androidx.core.database;

import android.database.CursorWindow;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;

/* loaded from: classes.dex */
public final class CursorWindowCompat {

    @RequiresApi
    static class Api28Impl {
        @DoNotInline
        static CursorWindow a(String str, long j2) {
            return new CursorWindow(str, j2);
        }
    }
}
