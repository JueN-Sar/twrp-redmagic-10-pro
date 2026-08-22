package androidx.core.view;

import android.view.WindowInsetsAnimationController;
import androidx.annotation.RequiresApi;

/* loaded from: classes.dex */
public final class WindowInsetsAnimationControllerCompat {

    /* renamed from: a, reason: collision with root package name */
    private final Impl f3437a;

    private static class Impl {
        Impl() {
        }
    }

    @RequiresApi
    private static class Impl30 extends Impl {

        /* renamed from: a, reason: collision with root package name */
        private final WindowInsetsAnimationController f3438a;

        Impl30(WindowInsetsAnimationController windowInsetsAnimationController) {
            this.f3438a = windowInsetsAnimationController;
        }
    }

    WindowInsetsAnimationControllerCompat(WindowInsetsAnimationController windowInsetsAnimationController) {
        this.f3437a = new Impl30(windowInsetsAnimationController);
    }
}
