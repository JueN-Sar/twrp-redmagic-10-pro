package androidx.core.view;

import android.R;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.inputmethod.InputMethodManager;
import androidx.annotation.RequiresApi;
import androidx.core.view.SoftwareKeyboardControllerCompat;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
public final class SoftwareKeyboardControllerCompat {

    /* renamed from: a, reason: collision with root package name */
    private final Impl f3360a;

    private static class Impl {
        Impl() {
        }

        void a() {
        }

        void b() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi
    static class Impl20 extends Impl {

        /* renamed from: a, reason: collision with root package name */
        private final View f3361a;

        Impl20(View view) {
            this.f3361a = view;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void d(View view) {
            ((InputMethodManager) view.getContext().getSystemService("input_method")).showSoftInput(view, 0);
        }

        @Override // androidx.core.view.SoftwareKeyboardControllerCompat.Impl
        void a() {
            View view = this.f3361a;
            if (view != null) {
                ((InputMethodManager) view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(this.f3361a.getWindowToken(), 0);
            }
        }

        @Override // androidx.core.view.SoftwareKeyboardControllerCompat.Impl
        void b() {
            final View view = this.f3361a;
            if (view == null) {
                return;
            }
            if (view.isInEditMode() || view.onCheckIsTextEditor()) {
                view.requestFocus();
            } else {
                view = view.getRootView().findFocus();
            }
            if (view == null) {
                view = this.f3361a.getRootView().findViewById(R.id.content);
            }
            if (view == null || !view.hasWindowFocus()) {
                return;
            }
            view.post(new Runnable() { // from class: androidx.core.view.d
                @Override // java.lang.Runnable
                public final void run() {
                    SoftwareKeyboardControllerCompat.Impl20.d(view);
                }
            });
        }
    }

    public SoftwareKeyboardControllerCompat(View view) {
        this.f3360a = new Impl30(view);
    }

    public void a() {
        this.f3360a.a();
    }

    public void b() {
        this.f3360a.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi
    static class Impl30 extends Impl20 {

        /* renamed from: b, reason: collision with root package name */
        private View f3362b;

        /* renamed from: c, reason: collision with root package name */
        private WindowInsetsController f3363c;

        Impl30(View view) {
            super(view);
            this.f3362b = view;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void f(AtomicBoolean atomicBoolean, WindowInsetsController windowInsetsController, int i2) {
            atomicBoolean.set((i2 & 8) != 0);
        }

        @Override // androidx.core.view.SoftwareKeyboardControllerCompat.Impl20, androidx.core.view.SoftwareKeyboardControllerCompat.Impl
        void a() {
            View view;
            WindowInsetsController windowInsetsController = this.f3363c;
            if (windowInsetsController == null) {
                View view2 = this.f3362b;
                windowInsetsController = view2 != null ? view2.getWindowInsetsController() : null;
            }
            if (windowInsetsController == null) {
                super.a();
                return;
            }
            final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            WindowInsetsController.OnControllableInsetsChangedListener onControllableInsetsChangedListener = new WindowInsetsController.OnControllableInsetsChangedListener() { // from class: androidx.core.view.e
                @Override // android.view.WindowInsetsController.OnControllableInsetsChangedListener
                public final void onControllableInsetsChanged(WindowInsetsController windowInsetsController2, int i2) {
                    SoftwareKeyboardControllerCompat.Impl30.f(atomicBoolean, windowInsetsController2, i2);
                }
            };
            windowInsetsController.addOnControllableInsetsChangedListener(onControllableInsetsChangedListener);
            if (!atomicBoolean.get() && (view = this.f3362b) != null) {
                ((InputMethodManager) view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(this.f3362b.getWindowToken(), 0);
            }
            windowInsetsController.removeOnControllableInsetsChangedListener(onControllableInsetsChangedListener);
            windowInsetsController.hide(WindowInsets.Type.ime());
        }

        @Override // androidx.core.view.SoftwareKeyboardControllerCompat.Impl20, androidx.core.view.SoftwareKeyboardControllerCompat.Impl
        void b() {
            View view = this.f3362b;
            WindowInsetsController windowInsetsController = this.f3363c;
            if (windowInsetsController == null) {
                windowInsetsController = view != null ? view.getWindowInsetsController() : null;
            }
            if (windowInsetsController != null) {
                windowInsetsController.show(WindowInsets.Type.ime());
            } else {
                super.b();
            }
        }

        Impl30(WindowInsetsController windowInsetsController) {
            super(null);
            this.f3363c = windowInsetsController;
        }
    }

    SoftwareKeyboardControllerCompat(WindowInsetsController windowInsetsController) {
        this.f3360a = new Impl30(windowInsetsController);
    }
}
