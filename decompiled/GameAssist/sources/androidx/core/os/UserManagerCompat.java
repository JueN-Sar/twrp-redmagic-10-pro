package androidx.core.os;

import android.content.Context;
import android.os.UserManager;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;

/* loaded from: classes.dex */
public class UserManagerCompat {

    @RequiresApi
    static class Api24Impl {
        @DoNotInline
        static boolean a(Context context) {
            return ((UserManager) context.getSystemService(UserManager.class)).isUserUnlocked();
        }
    }
}
