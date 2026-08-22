package androidx.appcompat.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.R;
import androidx.core.view.ViewCompat;

/* loaded from: classes.dex */
class AppCompatBackgroundHelper {

    /* renamed from: a, reason: collision with root package name */
    private final View f704a;

    /* renamed from: d, reason: collision with root package name */
    private TintInfo f707d;

    /* renamed from: e, reason: collision with root package name */
    private TintInfo f708e;

    /* renamed from: f, reason: collision with root package name */
    private TintInfo f709f;

    /* renamed from: c, reason: collision with root package name */
    private int f706c = -1;

    /* renamed from: b, reason: collision with root package name */
    private final AppCompatDrawableManager f705b = AppCompatDrawableManager.b();

    AppCompatBackgroundHelper(View view) {
        this.f704a = view;
    }

    private boolean a(Drawable drawable) {
        if (this.f709f == null) {
            this.f709f = new TintInfo();
        }
        TintInfo tintInfo = this.f709f;
        tintInfo.a();
        ColorStateList o2 = ViewCompat.o(this.f704a);
        if (o2 != null) {
            tintInfo.f1018d = true;
            tintInfo.f1015a = o2;
        }
        PorterDuff.Mode p2 = ViewCompat.p(this.f704a);
        if (p2 != null) {
            tintInfo.f1017c = true;
            tintInfo.f1016b = p2;
        }
        if (!tintInfo.f1018d && !tintInfo.f1017c) {
            return false;
        }
        AppCompatDrawableManager.i(drawable, tintInfo, this.f704a.getDrawableState());
        return true;
    }

    private boolean k() {
        return this.f707d != null;
    }

    void b() {
        Drawable background = this.f704a.getBackground();
        if (background != null) {
            if (k() && a(background)) {
                return;
            }
            TintInfo tintInfo = this.f708e;
            if (tintInfo != null) {
                AppCompatDrawableManager.i(background, tintInfo, this.f704a.getDrawableState());
                return;
            }
            TintInfo tintInfo2 = this.f707d;
            if (tintInfo2 != null) {
                AppCompatDrawableManager.i(background, tintInfo2, this.f704a.getDrawableState());
            }
        }
    }

    ColorStateList c() {
        TintInfo tintInfo = this.f708e;
        if (tintInfo != null) {
            return tintInfo.f1015a;
        }
        return null;
    }

    PorterDuff.Mode d() {
        TintInfo tintInfo = this.f708e;
        if (tintInfo != null) {
            return tintInfo.f1016b;
        }
        return null;
    }

    void e(AttributeSet attributeSet, int i2) {
        TintTypedArray v = TintTypedArray.v(this.f704a.getContext(), attributeSet, R.styleable.ViewBackgroundHelper, i2, 0);
        View view = this.f704a;
        ViewCompat.g0(view, view.getContext(), R.styleable.ViewBackgroundHelper, attributeSet, v.r(), i2, 0);
        try {
            if (v.s(R.styleable.ViewBackgroundHelper_android_background)) {
                this.f706c = v.n(R.styleable.ViewBackgroundHelper_android_background, -1);
                ColorStateList f2 = this.f705b.f(this.f704a.getContext(), this.f706c);
                if (f2 != null) {
                    h(f2);
                }
            }
            if (v.s(R.styleable.ViewBackgroundHelper_backgroundTint)) {
                ViewCompat.n0(this.f704a, v.c(R.styleable.ViewBackgroundHelper_backgroundTint));
            }
            if (v.s(R.styleable.ViewBackgroundHelper_backgroundTintMode)) {
                ViewCompat.o0(this.f704a, DrawableUtils.d(v.k(R.styleable.ViewBackgroundHelper_backgroundTintMode, -1), null));
            }
            v.x();
        } catch (Throwable th) {
            v.x();
            throw th;
        }
    }

    void f(Drawable drawable) {
        this.f706c = -1;
        h(null);
        b();
    }

    void g(int i2) {
        this.f706c = i2;
        AppCompatDrawableManager appCompatDrawableManager = this.f705b;
        h(appCompatDrawableManager != null ? appCompatDrawableManager.f(this.f704a.getContext(), i2) : null);
        b();
    }

    void h(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.f707d == null) {
                this.f707d = new TintInfo();
            }
            TintInfo tintInfo = this.f707d;
            tintInfo.f1015a = colorStateList;
            tintInfo.f1018d = true;
        } else {
            this.f707d = null;
        }
        b();
    }

    void i(ColorStateList colorStateList) {
        if (this.f708e == null) {
            this.f708e = new TintInfo();
        }
        TintInfo tintInfo = this.f708e;
        tintInfo.f1015a = colorStateList;
        tintInfo.f1018d = true;
        b();
    }

    void j(PorterDuff.Mode mode) {
        if (this.f708e == null) {
            this.f708e = new TintInfo();
        }
        TintInfo tintInfo = this.f708e;
        tintInfo.f1016b = mode;
        tintInfo.f1017c = true;
        b();
    }
}
