package androidx.core.view;

import android.view.Menu;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;

/* loaded from: classes.dex */
public final class MenuCompat {

    @RequiresApi
    static class Api28Impl {
        @DoNotInline
        static void a(Menu menu, boolean z) {
            menu.setGroupDividerEnabled(z);
        }
    }
}
