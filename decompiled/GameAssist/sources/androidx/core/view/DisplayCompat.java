package androidx.core.view;

import android.view.Display;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;

/* loaded from: classes.dex */
public final class DisplayCompat {

    @RequiresApi
    static class Api23Impl {
    }

    public static final class ModeCompat {

        @RequiresApi
        static class Api23Impl {
            @DoNotInline
            static int a(Display.Mode mode) {
                return mode.getPhysicalHeight();
            }

            @DoNotInline
            static int b(Display.Mode mode) {
                return mode.getPhysicalWidth();
            }
        }
    }
}
