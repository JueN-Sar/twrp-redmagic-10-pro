package androidx.core.view;

import android.view.View;
import android.view.Window;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

/* loaded from: classes.dex */
public final class WindowCompat {

    static class Api16Impl {
    }

    @RequiresApi
    static class Api28Impl {
        @DoNotInline
        static <T> T a(Window window, int i2) {
            return (T) window.requireViewById(i2);
        }
    }

    @RequiresApi
    static class Api30Impl {
        @DoNotInline
        static void a(@NonNull Window window, boolean z) {
            window.setDecorFitsSystemWindows(z);
        }
    }

    public static WindowInsetsControllerCompat a(Window window, View view) {
        return new WindowInsetsControllerCompat(window, view);
    }

    public static void b(Window window, boolean z) {
        Api30Impl.a(window, z);
    }
}
