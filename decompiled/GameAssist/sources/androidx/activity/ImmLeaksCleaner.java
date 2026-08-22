package androidx.activity;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import java.lang.reflect.Field;

@RequiresApi
/* loaded from: classes.dex */
final class ImmLeaksCleaner implements LifecycleEventObserver {

    /* renamed from: h, reason: collision with root package name */
    private static int f50h;

    /* renamed from: i, reason: collision with root package name */
    private static Field f51i;

    /* renamed from: j, reason: collision with root package name */
    private static Field f52j;

    /* renamed from: k, reason: collision with root package name */
    private static Field f53k;

    /* renamed from: c, reason: collision with root package name */
    private Activity f54c;

    private static void b() {
        try {
            f50h = 2;
            Field declaredField = InputMethodManager.class.getDeclaredField("mServedView");
            f52j = declaredField;
            declaredField.setAccessible(true);
            Field declaredField2 = InputMethodManager.class.getDeclaredField("mNextServedView");
            f53k = declaredField2;
            declaredField2.setAccessible(true);
            Field declaredField3 = InputMethodManager.class.getDeclaredField("mH");
            f51i = declaredField3;
            declaredField3.setAccessible(true);
            f50h = 1;
        } catch (NoSuchFieldException unused) {
        }
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public void c(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        if (event != Lifecycle.Event.ON_DESTROY) {
            return;
        }
        if (f50h == 0) {
            b();
        }
        if (f50h == 1) {
            InputMethodManager inputMethodManager = (InputMethodManager) this.f54c.getSystemService("input_method");
            try {
                Object obj = f51i.get(inputMethodManager);
                if (obj == null) {
                    return;
                }
                synchronized (obj) {
                    try {
                        try {
                            try {
                                View view = (View) f52j.get(inputMethodManager);
                                if (view == null) {
                                    return;
                                }
                                if (view.isAttachedToWindow()) {
                                    return;
                                }
                                try {
                                    f53k.set(inputMethodManager, null);
                                    inputMethodManager.isActive();
                                } catch (IllegalAccessException unused) {
                                }
                            } catch (ClassCastException unused2) {
                            }
                        } catch (IllegalAccessException unused3) {
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            } catch (IllegalAccessException unused4) {
            }
        }
    }
}
