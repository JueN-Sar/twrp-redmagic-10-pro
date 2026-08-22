package androidx.activity;

import android.view.View;
import android.view.Window;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@RequiresApi
@Metadata
/* loaded from: classes.dex */
final class EdgeToEdgeApi26 implements EdgeToEdgeImpl {
    @DoNotInline
    public void a(@NotNull SystemBarStyle statusBarStyle, @NotNull SystemBarStyle navigationBarStyle, @NotNull Window window, @NotNull View view, boolean z, boolean z2) {
        Intrinsics.e(statusBarStyle, "statusBarStyle");
        Intrinsics.e(navigationBarStyle, "navigationBarStyle");
        Intrinsics.e(window, "window");
        Intrinsics.e(view, "view");
        WindowCompat.b(window, false);
        window.setStatusBarColor(statusBarStyle.c(z));
        window.setNavigationBarColor(navigationBarStyle.c(z2));
        WindowInsetsControllerCompat windowInsetsControllerCompat = new WindowInsetsControllerCompat(window, view);
        windowInsetsControllerCompat.d(!z);
        windowInsetsControllerCompat.c(!z2);
    }
}
