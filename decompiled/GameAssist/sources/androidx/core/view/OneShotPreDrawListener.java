package androidx.core.view;

import android.view.View;
import android.view.ViewTreeObserver;

/* loaded from: classes.dex */
public final class OneShotPreDrawListener implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {

    /* renamed from: c, reason: collision with root package name */
    private final View f3356c;

    /* renamed from: h, reason: collision with root package name */
    private ViewTreeObserver f3357h;

    /* renamed from: i, reason: collision with root package name */
    private final Runnable f3358i;

    private OneShotPreDrawListener(View view, Runnable runnable) {
        this.f3356c = view;
        this.f3357h = view.getViewTreeObserver();
        this.f3358i = runnable;
    }

    public static OneShotPreDrawListener a(View view, Runnable runnable) {
        if (view == null) {
            throw new NullPointerException("view == null");
        }
        if (runnable == null) {
            throw new NullPointerException("runnable == null");
        }
        OneShotPreDrawListener oneShotPreDrawListener = new OneShotPreDrawListener(view, runnable);
        view.getViewTreeObserver().addOnPreDrawListener(oneShotPreDrawListener);
        view.addOnAttachStateChangeListener(oneShotPreDrawListener);
        return oneShotPreDrawListener;
    }

    public void b() {
        if (this.f3357h.isAlive()) {
            this.f3357h.removeOnPreDrawListener(this);
        } else {
            this.f3356c.getViewTreeObserver().removeOnPreDrawListener(this);
        }
        this.f3356c.removeOnAttachStateChangeListener(this);
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public boolean onPreDraw() {
        b();
        this.f3358i.run();
        return true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
        this.f3357h = view.getViewTreeObserver();
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        b();
    }
}
