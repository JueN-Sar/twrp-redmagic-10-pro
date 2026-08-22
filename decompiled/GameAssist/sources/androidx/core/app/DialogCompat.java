package androidx.core.app;

import android.app.Dialog;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;

/* loaded from: classes.dex */
public class DialogCompat {

    @RequiresApi
    static class Api28Impl {
        @DoNotInline
        static <T> T a(Dialog dialog, int i2) {
            return (T) dialog.requireViewById(i2);
        }
    }
}
