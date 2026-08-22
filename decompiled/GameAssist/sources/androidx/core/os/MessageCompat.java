package androidx.core.os;

import android.os.Message;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;

/* loaded from: classes.dex */
public final class MessageCompat {

    @RequiresApi
    static class Api22Impl {
        @DoNotInline
        static boolean a(Message message) {
            return message.isAsynchronous();
        }

        @DoNotInline
        static void b(Message message, boolean z) {
            message.setAsynchronous(z);
        }
    }
}
