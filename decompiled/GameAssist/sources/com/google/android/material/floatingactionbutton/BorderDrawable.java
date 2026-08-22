package com.google.android.material.floatingactionbutton;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import androidx.annotation.RestrictTo;
import androidx.core.graphics.ColorUtils;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.ShapeAppearancePathProvider;

@RestrictTo
/* loaded from: classes.dex */
class BorderDrawable extends Drawable {

    /* renamed from: b, reason: collision with root package name */
    private final Paint f14590b;

    /* renamed from: h, reason: collision with root package name */
    float f14596h;

    /* renamed from: i, reason: collision with root package name */
    private int f14597i;

    /* renamed from: j, reason: collision with root package name */
    private int f14598j;

    /* renamed from: k, reason: collision with root package name */
    private int f14599k;

    /* renamed from: l, reason: collision with root package name */
    private int f14600l;

    /* renamed from: m, reason: collision with root package name */
    private int f14601m;

    /* renamed from: o, reason: collision with root package name */
    private ShapeAppearanceModel f14603o;

    /* renamed from: p, reason: collision with root package name */
    private ColorStateList f14604p;

    /* renamed from: a, reason: collision with root package name */
    private final ShapeAppearancePathProvider f14589a = ShapeAppearancePathProvider.k();

    /* renamed from: c, reason: collision with root package name */
    private final Path f14591c = new Path();

    /* renamed from: d, reason: collision with root package name */
    private final Rect f14592d = new Rect();

    /* renamed from: e, reason: collision with root package name */
    private final RectF f14593e = new RectF();

    /* renamed from: f, reason: collision with root package name */
    private final RectF f14594f = new RectF();

    /* renamed from: g, reason: collision with root package name */
    private final BorderState f14595g = new BorderState();

    /* renamed from: n, reason: collision with root package name */
    private boolean f14602n = true;

    private class BorderState extends Drawable.ConstantState {
        private BorderState() {
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return BorderDrawable.this;
        }
    }

    BorderDrawable(ShapeAppearanceModel shapeAppearanceModel) {
        this.f14603o = shapeAppearanceModel;
        Paint paint = new Paint(1);
        this.f14590b = paint;
        paint.setStyle(Paint.Style.STROKE);
    }

    private Shader a() {
        copyBounds(this.f14592d);
        float height = this.f14596h / r1.height();
        return new LinearGradient(0.0f, r1.top, 0.0f, r1.bottom, new int[]{ColorUtils.g(this.f14597i, this.f14601m), ColorUtils.g(this.f14598j, this.f14601m), ColorUtils.g(ColorUtils.k(this.f14598j, 0), this.f14601m), ColorUtils.g(ColorUtils.k(this.f14600l, 0), this.f14601m), ColorUtils.g(this.f14600l, this.f14601m), ColorUtils.g(this.f14599k, this.f14601m)}, new float[]{0.0f, height, 0.5f, 0.5f, 1.0f - height, 1.0f}, Shader.TileMode.CLAMP);
    }

    protected RectF b() {
        this.f14594f.set(getBounds());
        return this.f14594f;
    }

    void c(ColorStateList colorStateList) {
        if (colorStateList != null) {
            this.f14601m = colorStateList.getColorForState(getState(), this.f14601m);
        }
        this.f14604p = colorStateList;
        this.f14602n = true;
        invalidateSelf();
    }

    public void d(float f2) {
        if (this.f14596h != f2) {
            this.f14596h = f2;
            this.f14590b.setStrokeWidth(f2 * 1.3333f);
            this.f14602n = true;
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.f14602n) {
            this.f14590b.setShader(a());
            this.f14602n = false;
        }
        float strokeWidth = this.f14590b.getStrokeWidth() / 2.0f;
        copyBounds(this.f14592d);
        this.f14593e.set(this.f14592d);
        float min = Math.min(this.f14603o.r().a(b()), this.f14593e.width() / 2.0f);
        if (this.f14603o.u(b())) {
            this.f14593e.inset(strokeWidth, strokeWidth);
            canvas.drawRoundRect(this.f14593e, min, min, this.f14590b);
        }
    }

    void e(int i2, int i3, int i4, int i5) {
        this.f14597i = i2;
        this.f14598j = i3;
        this.f14599k = i4;
        this.f14600l = i5;
    }

    public void f(ShapeAppearanceModel shapeAppearanceModel) {
        this.f14603o = shapeAppearanceModel;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.f14595g;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.f14596h > 0.0f ? -3 : -2;
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        if (this.f14603o.u(b())) {
            outline.setRoundRect(getBounds(), this.f14603o.r().a(b()));
        } else {
            copyBounds(this.f14592d);
            this.f14593e.set(this.f14592d);
            this.f14589a.d(this.f14603o, 1.0f, this.f14593e, this.f14591c);
            DrawableUtils.l(outline, this.f14591c);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        if (!this.f14603o.u(b())) {
            return true;
        }
        int round = Math.round(this.f14596h);
        rect.set(round, round, round, round);
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList colorStateList = this.f14604p;
        return (colorStateList != null && colorStateList.isStateful()) || super.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        this.f14602n = true;
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        int colorForState;
        ColorStateList colorStateList = this.f14604p;
        if (colorStateList != null && (colorForState = colorStateList.getColorForState(iArr, this.f14601m)) != this.f14601m) {
            this.f14602n = true;
            this.f14601m = colorForState;
        }
        if (this.f14602n) {
            invalidateSelf();
        }
        return this.f14602n;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.f14590b.setAlpha(i2);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f14590b.setColorFilter(colorFilter);
        invalidateSelf();
    }
}
