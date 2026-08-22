package androidx.appcompat.widget;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.SeekBar;
import androidx.appcompat.R;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;

/* loaded from: classes.dex */
class AppCompatSeekBarHelper extends AppCompatProgressBarHelper {

    /* renamed from: d, reason: collision with root package name */
    private final SeekBar f797d;

    /* renamed from: e, reason: collision with root package name */
    private Drawable f798e;

    /* renamed from: f, reason: collision with root package name */
    private ColorStateList f799f;

    /* renamed from: g, reason: collision with root package name */
    private PorterDuff.Mode f800g;

    /* renamed from: h, reason: collision with root package name */
    private boolean f801h;

    /* renamed from: i, reason: collision with root package name */
    private boolean f802i;

    AppCompatSeekBarHelper(SeekBar seekBar) {
        super(seekBar);
        this.f799f = null;
        this.f800g = null;
        this.f801h = false;
        this.f802i = false;
        this.f797d = seekBar;
    }

    private void e() {
        Drawable drawable = this.f798e;
        if (drawable != null) {
            if (this.f801h || this.f802i) {
                Drawable r2 = DrawableCompat.r(drawable.mutate());
                this.f798e = r2;
                if (this.f801h) {
                    DrawableCompat.o(r2, this.f799f);
                }
                if (this.f802i) {
                    DrawableCompat.p(this.f798e, this.f800g);
                }
                if (this.f798e.isStateful()) {
                    this.f798e.setState(this.f797d.getDrawableState());
                }
            }
        }
    }

    @Override // androidx.appcompat.widget.AppCompatProgressBarHelper
    void c(AttributeSet attributeSet, int i2) {
        super.c(attributeSet, i2);
        TintTypedArray v = TintTypedArray.v(this.f797d.getContext(), attributeSet, R.styleable.AppCompatSeekBar, i2, 0);
        SeekBar seekBar = this.f797d;
        ViewCompat.g0(seekBar, seekBar.getContext(), R.styleable.AppCompatSeekBar, attributeSet, v.r(), i2, 0);
        Drawable h2 = v.h(R.styleable.AppCompatSeekBar_android_thumb);
        if (h2 != null) {
            this.f797d.setThumb(h2);
        }
        i(v.g(R.styleable.AppCompatSeekBar_tickMark));
        if (v.s(R.styleable.AppCompatSeekBar_tickMarkTintMode)) {
            this.f800g = DrawableUtils.d(v.k(R.styleable.AppCompatSeekBar_tickMarkTintMode, -1), this.f800g);
            this.f802i = true;
        }
        if (v.s(R.styleable.AppCompatSeekBar_tickMarkTint)) {
            this.f799f = v.c(R.styleable.AppCompatSeekBar_tickMarkTint);
            this.f801h = true;
        }
        v.x();
        e();
    }

    void f(Canvas canvas) {
        if (this.f798e != null) {
            int max = this.f797d.getMax();
            if (max > 1) {
                int intrinsicWidth = this.f798e.getIntrinsicWidth();
                int intrinsicHeight = this.f798e.getIntrinsicHeight();
                int i2 = intrinsicWidth >= 0 ? intrinsicWidth / 2 : 1;
                int i3 = intrinsicHeight >= 0 ? intrinsicHeight / 2 : 1;
                this.f798e.setBounds(-i2, -i3, i2, i3);
                float width = ((this.f797d.getWidth() - this.f797d.getPaddingLeft()) - this.f797d.getPaddingRight()) / max;
                int save = canvas.save();
                canvas.translate(this.f797d.getPaddingLeft(), this.f797d.getHeight() / 2);
                for (int i4 = 0; i4 <= max; i4++) {
                    this.f798e.draw(canvas);
                    canvas.translate(width, 0.0f);
                }
                canvas.restoreToCount(save);
            }
        }
    }

    void g() {
        Drawable drawable = this.f798e;
        if (drawable != null && drawable.isStateful() && drawable.setState(this.f797d.getDrawableState())) {
            this.f797d.invalidateDrawable(drawable);
        }
    }

    void h() {
        Drawable drawable = this.f798e;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    void i(Drawable drawable) {
        Drawable drawable2 = this.f798e;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.f798e = drawable;
        if (drawable != null) {
            drawable.setCallback(this.f797d);
            DrawableCompat.m(drawable, this.f797d.getLayoutDirection());
            if (drawable.isStateful()) {
                drawable.setState(this.f797d.getDrawableState());
            }
            e();
        }
        this.f797d.invalidate();
    }
}
