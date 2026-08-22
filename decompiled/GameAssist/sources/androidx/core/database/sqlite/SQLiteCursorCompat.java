package androidx.core.database.sqlite;

import android.database.sqlite.SQLiteCursor;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;

/* loaded from: classes.dex */
public final class SQLiteCursorCompat {

    @RequiresApi
    static class Api28Impl {
        @DoNotInline
        static void a(SQLiteCursor sQLiteCursor, boolean z) {
            sQLiteCursor.setFillWindowForwardOnly(z);
        }
    }
}
