package androidx.appcompat.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import androidx.annotation.RestrictTo;
import androidx.appcompat.R;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.view.ViewCompat;
import androidx.core.widget.ImageViewCompat;

@RestrictTo
/* loaded from: classes.dex */
public class AppCompatImageHelper {

    /* renamed from: a, reason: collision with root package name */
    private final ImageView f770a;

    /* renamed from: b, reason: collision with root package name */
    private TintInfo f771b;

    /* renamed from: c, reason: collision with root package name */
    private TintInfo f772c;

    /* renamed from: d, reason: collision with root package name */
    private TintInfo f773d;

    /* renamed from: e, reason: collision with root package name */
    private int f774e = 0;

    public AppCompatImageHelper(ImageView imageView) {
        this.f770a = imageView;
    }

    private boolean a(Drawable drawable) {
        if (this.f773d == null) {
            this.f773d = new TintInfo();
        }
        TintInfo tintInfo = this.f773d;
        tintInfo.a();
        ColorStateList a2 = ImageViewCompat.a(this.f770a);
        if (a2 != null) {
            tintInfo.f1018d = true;
            tintInfo.f1015a = a2;
        }
        PorterDuff.Mode b2 = ImageViewCompat.b(this.f770a);
        if (b2 != null) {
            tintInfo.f1017c = true;
            tintInfo.f1016b = b2;
        }
        if (!tintInfo.f1018d && !tintInfo.f1017c) {
            return false;
        }
        AppCompatDrawableManager.i(drawable, tintInfo, this.f770a.getDrawableState());
        return true;
    }

    private boolean l() {
        return this.f771b != null;
    }

    void b() {
        if (this.f770a.getDrawable() != null) {
            this.f770a.getDrawable().setLevel(this.f774e);
        }
    }

    void c() {
        Drawable drawable = this.f770a.getDrawable();
        if (drawable != null) {
            DrawableUtils.b(drawable);
        }
        if (drawable != null) {
            if (l() && a(drawable)) {
                return;
            }
            TintInfo tintInfo = this.f772c;
            if (tintInfo != null) {
                AppCompatDrawableManager.i(drawable, tintInfo, this.f770a.getDrawableState());
                return;
            }
            TintInfo tintInfo2 = this.f771b;
            if (tintInfo2 != null) {
                AppCompatDrawableManager.i(drawable, tintInfo2, this.f770a.getDrawableState());
            }
        }
    }

    ColorStateList d() {
        TintInfo tintInfo = this.f772c;
        if (tintInfo != null) {
            return tintInfo.f1015a;
        }
        return null;
    }

    PorterDuff.Mode e() {
        TintInfo tintInfo = this.f772c;
        if (tintInfo != null) {
            return tintInfo.f1016b;
        }
        return null;
    }

    boolean f() {
        return !(this.f770a.getBackground() instanceof RippleDrawable);
    }

    public void g(AttributeSet attributeSet, int i2) {
        int n2;
        TintTypedArray v = TintTypedArray.v(this.f770a.getContext(), attributeSet, R.styleable.AppCompatImageView, i2, 0);
        ImageView imageView = this.f770a;
        ViewCompat.g0(imageView, imageView.getContext(), R.styleable.AppCompatImageView, attributeSet, v.r(), i2, 0);
        try {
            Drawable drawable = this.f770a.getDrawable();
            if (drawable == null && (n2 = v.n(R.styleable.AppCompatImageView_srcCompat, -1)) != -1 && (drawable = AppCompatResources.b(this.f770a.getContext(), n2)) != null) {
                this.f770a.setImageDrawable(drawable);
            }
            if (drawable != null) {
                DrawableUtils.b(drawable);
            }
            if (v.s(R.styleable.AppCompatImageView_tint)) {
                ImageViewCompat.c(this.f770a, v.c(R.styleable.AppCompatImageView_tint));
            }
            if (v.s(R.styleable.AppCompatImageView_tintMode)) {
                ImageViewCompat.d(this.f770a, DrawableUtils.d(v.k(R.styleable.AppCompatImageView_tintMode, -1), null));
            }
            v.x();
        } catch (Throwable th) {
            v.x();
            throw th;
        }
    }

    void h(Drawable drawable) {
        this.f774e = drawable.getLevel();
    }

    public void i(int i2) {
        if (i2 != 0) {
            Drawable b2 = AppCompatResources.b(this.f770a.getContext(), i2);
            if (b2 != null) {
                DrawableUtils.b(b2);
            }
            this.f770a.setImageDrawable(b2);
        } else {
            this.f770a.setImageDrawable(null);
        }
        c();
    }

    void j(ColorStateList colorStateList) {
        if (this.f772c == null) {
            this.f772c = new TintInfo();
        }
        TintInfo tintInfo = this.f772c;
        tintInfo.f1015a = colorStateList;
        tintInfo.f1018d = true;
        c();
    }

    void k(PorterDuff.Mode mode) {
        if (this.f772c == null) {
            this.f772c = new TintInfo();
        }
        TintInfo tintInfo = this.f772c;
        tintInfo.f1016b = mode;
        tintInfo.f1017c = true;
        c();
    }
}
