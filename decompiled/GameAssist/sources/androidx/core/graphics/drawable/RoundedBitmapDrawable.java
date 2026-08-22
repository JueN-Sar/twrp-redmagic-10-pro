package androidx.core.graphics.drawable;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

/* loaded from: classes.dex */
public abstract class RoundedBitmapDrawable extends Drawable {

    /* renamed from: a, reason: collision with root package name */
    final Bitmap f2983a;

    /* renamed from: b, reason: collision with root package name */
    private int f2984b;

    /* renamed from: c, reason: collision with root package name */
    private final Paint f2985c;

    /* renamed from: d, reason: collision with root package name */
    private final BitmapShader f2986d;

    /* renamed from: e, reason: collision with root package name */
    private final Matrix f2987e;

    /* renamed from: f, reason: collision with root package name */
    private float f2988f;

    /* renamed from: g, reason: collision with root package name */
    final Rect f2989g;

    /* renamed from: h, reason: collision with root package name */
    private final RectF f2990h;

    /* renamed from: i, reason: collision with root package name */
    private boolean f2991i;

    /* renamed from: j, reason: collision with root package name */
    private boolean f2992j;

    /* renamed from: k, reason: collision with root package name */
    private int f2993k;

    /* renamed from: l, reason: collision with root package name */
    private int f2994l;

    private static boolean c(float f2) {
        return f2 > 0.05f;
    }

    private void d() {
        this.f2988f = Math.min(this.f2994l, this.f2993k) / 2;
    }

    public float a() {
        return this.f2988f;
    }

    void b(int i2, int i3, int i4, Rect rect, Rect rect2) {
        throw new UnsupportedOperationException();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Bitmap bitmap = this.f2983a;
        if (bitmap == null) {
            return;
        }
        e();
        if (this.f2985c.getShader() == null) {
            canvas.drawBitmap(bitmap, (Rect) null, this.f2989g, this.f2985c);
            return;
        }
        RectF rectF = this.f2990h;
        float f2 = this.f2988f;
        canvas.drawRoundRect(rectF, f2, f2, this.f2985c);
    }

    void e() {
        if (this.f2991i) {
            if (this.f2992j) {
                int min = Math.min(this.f2993k, this.f2994l);
                b(this.f2984b, min, min, getBounds(), this.f2989g);
                int min2 = Math.min(this.f2989g.width(), this.f2989g.height());
                this.f2989g.inset(Math.max(0, (this.f2989g.width() - min2) / 2), Math.max(0, (this.f2989g.height() - min2) / 2));
                this.f2988f = min2 * 0.5f;
            } else {
                b(this.f2984b, this.f2993k, this.f2994l, getBounds(), this.f2989g);
            }
            this.f2990h.set(this.f2989g);
            if (this.f2986d != null) {
                Matrix matrix = this.f2987e;
                RectF rectF = this.f2990h;
                matrix.setTranslate(rectF.left, rectF.top);
                this.f2987e.preScale(this.f2990h.width() / this.f2983a.getWidth(), this.f2990h.height() / this.f2983a.getHeight());
                this.f2986d.setLocalMatrix(this.f2987e);
                this.f2985c.setShader(this.f2986d);
            }
            this.f2991i = false;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.f2985c.getAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        return this.f2985c.getColorFilter();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.f2994l;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.f2993k;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        Bitmap bitmap;
        return (this.f2984b != 119 || this.f2992j || (bitmap = this.f2983a) == null || bitmap.hasAlpha() || this.f2985c.getAlpha() < 255 || c(this.f2988f)) ? -3 : -1;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        if (this.f2992j) {
            d();
        }
        this.f2991i = true;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        if (i2 != this.f2985c.getAlpha()) {
            this.f2985c.setAlpha(i2);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f2985c.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setDither(boolean z) {
        this.f2985c.setDither(z);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setFilterBitmap(boolean z) {
        this.f2985c.setFilterBitmap(z);
        invalidateSelf();
    }
}
