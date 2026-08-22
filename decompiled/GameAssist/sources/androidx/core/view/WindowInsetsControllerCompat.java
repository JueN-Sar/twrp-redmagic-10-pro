package androidx.core.view;

import android.view.View;
import android.view.Window;
import android.view.WindowInsetsAnimationControlListener;
import android.view.WindowInsetsAnimationController;
import android.view.WindowInsetsController;
import androidx.annotation.RequiresApi;
import androidx.collection.SimpleArrayMap;
import com.zte.shared.wrapper.WindowManagerWrapper;

/* loaded from: classes.dex */
public final class WindowInsetsControllerCompat {

    /* renamed from: a, reason: collision with root package name */
    private final Impl f3463a;

    private static class Impl {
        Impl() {
        }

        void a(int i2) {
        }

        public boolean b() {
            return false;
        }

        public void c(boolean z) {
        }

        public void d(boolean z) {
        }

        void e(int i2) {
        }
    }

    @RequiresApi
    private static class Impl20 extends Impl {

        /* renamed from: a, reason: collision with root package name */
        protected final Window f3464a;

        /* renamed from: b, reason: collision with root package name */
        private final SoftwareKeyboardControllerCompat f3465b;

        private void f(int i2) {
            if (i2 == 1) {
                g(4);
            } else if (i2 == 2) {
                g(2);
            } else {
                if (i2 != 8) {
                    return;
                }
                this.f3465b.a();
            }
        }

        private void i(int i2) {
            if (i2 == 1) {
                j(4);
                k(1024);
            } else if (i2 == 2) {
                j(2);
            } else {
                if (i2 != 8) {
                    return;
                }
                this.f3465b.b();
            }
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        void a(int i2) {
            for (int i3 = 1; i3 <= 256; i3 <<= 1) {
                if ((i2 & i3) != 0) {
                    f(i3);
                }
            }
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        void e(int i2) {
            for (int i3 = 1; i3 <= 256; i3 <<= 1) {
                if ((i2 & i3) != 0) {
                    i(i3);
                }
            }
        }

        protected void g(int i2) {
            View decorView = this.f3464a.getDecorView();
            decorView.setSystemUiVisibility(i2 | decorView.getSystemUiVisibility());
        }

        protected void h(int i2) {
            this.f3464a.addFlags(i2);
        }

        protected void j(int i2) {
            View decorView = this.f3464a.getDecorView();
            decorView.setSystemUiVisibility((~i2) & decorView.getSystemUiVisibility());
        }

        protected void k(int i2) {
            this.f3464a.clearFlags(i2);
        }
    }

    @RequiresApi
    private static class Impl23 extends Impl20 {
        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public boolean b() {
            return (this.f3464a.getDecorView().getSystemUiVisibility() & 8192) != 0;
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public void d(boolean z) {
            if (!z) {
                j(8192);
                return;
            }
            k(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_OPT_OUT_EDGE_TO_EDGE);
            h(Integer.MIN_VALUE);
            g(8192);
        }
    }

    @RequiresApi
    private static class Impl26 extends Impl23 {
        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public void c(boolean z) {
            if (!z) {
                j(16);
                return;
            }
            k(134217728);
            h(Integer.MIN_VALUE);
            g(16);
        }
    }

    public interface OnControllableInsetsChangedListener {
    }

    private WindowInsetsControllerCompat(WindowInsetsController windowInsetsController) {
        this.f3463a = new Impl30(windowInsetsController, this, new SoftwareKeyboardControllerCompat(windowInsetsController));
    }

    public static WindowInsetsControllerCompat f(WindowInsetsController windowInsetsController) {
        return new WindowInsetsControllerCompat(windowInsetsController);
    }

    public void a(int i2) {
        this.f3463a.a(i2);
    }

    public boolean b() {
        return this.f3463a.b();
    }

    public void c(boolean z) {
        this.f3463a.c(z);
    }

    public void d(boolean z) {
        this.f3463a.d(z);
    }

    public void e(int i2) {
        this.f3463a.e(i2);
    }

    @RequiresApi
    private static class Impl30 extends Impl {

        /* renamed from: a, reason: collision with root package name */
        final WindowInsetsControllerCompat f3466a;

        /* renamed from: b, reason: collision with root package name */
        final WindowInsetsController f3467b;

        /* renamed from: c, reason: collision with root package name */
        final SoftwareKeyboardControllerCompat f3468c;

        /* renamed from: d, reason: collision with root package name */
        private final SimpleArrayMap f3469d;

        /* renamed from: e, reason: collision with root package name */
        protected Window f3470e;

        /* renamed from: androidx.core.view.WindowInsetsControllerCompat$Impl30$1, reason: invalid class name */
        class AnonymousClass1 implements WindowInsetsAnimationControlListener {

            /* renamed from: a, reason: collision with root package name */
            private WindowInsetsAnimationControllerCompat f3471a;

            /* renamed from: b, reason: collision with root package name */
            final /* synthetic */ WindowInsetsAnimationControlListenerCompat f3472b;

            @Override // android.view.WindowInsetsAnimationControlListener
            public void onCancelled(WindowInsetsAnimationController windowInsetsAnimationController) {
                this.f3472b.a(windowInsetsAnimationController == null ? null : this.f3471a);
            }

            @Override // android.view.WindowInsetsAnimationControlListener
            public void onFinished(WindowInsetsAnimationController windowInsetsAnimationController) {
                this.f3472b.c(this.f3471a);
            }

            @Override // android.view.WindowInsetsAnimationControlListener
            public void onReady(WindowInsetsAnimationController windowInsetsAnimationController, int i2) {
                WindowInsetsAnimationControllerCompat windowInsetsAnimationControllerCompat = new WindowInsetsAnimationControllerCompat(windowInsetsAnimationController);
                this.f3471a = windowInsetsAnimationControllerCompat;
                this.f3472b.b(windowInsetsAnimationControllerCompat, i2);
            }
        }

        Impl30(Window window, WindowInsetsControllerCompat windowInsetsControllerCompat, SoftwareKeyboardControllerCompat softwareKeyboardControllerCompat) {
            this(window.getInsetsController(), windowInsetsControllerCompat, softwareKeyboardControllerCompat);
            this.f3470e = window;
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        void a(int i2) {
            if ((i2 & 8) != 0) {
                this.f3468c.a();
            }
            this.f3467b.hide(i2 & (-9));
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public boolean b() {
            this.f3467b.setSystemBarsAppearance(0, 0);
            return (this.f3467b.getSystemBarsAppearance() & 8) != 0;
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public void c(boolean z) {
            if (z) {
                if (this.f3470e != null) {
                    f(16);
                }
                this.f3467b.setSystemBarsAppearance(16, 16);
            } else {
                if (this.f3470e != null) {
                    g(16);
                }
                this.f3467b.setSystemBarsAppearance(0, 16);
            }
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public void d(boolean z) {
            if (z) {
                if (this.f3470e != null) {
                    f(8192);
                }
                this.f3467b.setSystemBarsAppearance(8, 8);
            } else {
                if (this.f3470e != null) {
                    g(8192);
                }
                this.f3467b.setSystemBarsAppearance(0, 8);
            }
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        void e(int i2) {
            if ((i2 & 8) != 0) {
                this.f3468c.b();
            }
            this.f3467b.show(i2 & (-9));
        }

        protected void f(int i2) {
            View decorView = this.f3470e.getDecorView();
            decorView.setSystemUiVisibility(i2 | decorView.getSystemUiVisibility());
        }

        protected void g(int i2) {
            View decorView = this.f3470e.getDecorView();
            decorView.setSystemUiVisibility((~i2) & decorView.getSystemUiVisibility());
        }

        Impl30(WindowInsetsController windowInsetsController, WindowInsetsControllerCompat windowInsetsControllerCompat, SoftwareKeyboardControllerCompat softwareKeyboardControllerCompat) {
            this.f3469d = new SimpleArrayMap();
            this.f3467b = windowInsetsController;
            this.f3466a = windowInsetsControllerCompat;
            this.f3468c = softwareKeyboardControllerCompat;
        }
    }

    public WindowInsetsControllerCompat(Window window, View view) {
        this.f3463a = new Impl30(window, this, new SoftwareKeyboardControllerCompat(view));
    }
}
