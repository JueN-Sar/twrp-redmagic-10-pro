package androidx.cardview.widget;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import androidx.annotation.RequiresApi;

@RequiresApi
/* loaded from: classes.dex */
class RoundRectDrawable extends Drawable {

    /* renamed from: a, reason: collision with root package name */
    private float f1141a;

    /* renamed from: c, reason: collision with root package name */
    private final RectF f1143c;

    /* renamed from: d, reason: collision with root package name */
    private final Rect f1144d;

    /* renamed from: e, reason: collision with root package name */
    private float f1145e;

    /* renamed from: h, reason: collision with root package name */
    private ColorStateList f1148h;

    /* renamed from: i, reason: collision with root package name */
    private PorterDuffColorFilter f1149i;

    /* renamed from: j, reason: collision with root package name */
    private ColorStateList f1150j;

    /* renamed from: f, reason: collision with root package name */
    private boolean f1146f = false;

    /* renamed from: g, reason: collision with root package name */
    private boolean f1147g = true;

    /* renamed from: k, reason: collision with root package name */
    private PorterDuff.Mode f1151k = PorterDuff.Mode.SRC_IN;

    /* renamed from: b, reason: collision with root package name */
    private final Paint f1142b = new Paint(5);

    RoundRectDrawable(ColorStateList colorStateList, float f2) {
        this.f1141a = f2;
        e(colorStateList);
        this.f1143c = new RectF();
        this.f1144d = new Rect();
    }

    private PorterDuffColorFilter a(ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
    }

    private void e(ColorStateList colorStateList) {
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(0);
        }
        this.f1148h = colorStateList;
        this.f1142b.setColor(colorStateList.getColorForState(getState(), this.f1148h.getDefaultColor()));
    }

    private void i(Rect rect) {
        if (rect == null) {
            rect = getBounds();
        }
        this.f1143c.set(rect.left, rect.top, rect.right, rect.bottom);
        this.f1144d.set(rect);
        if (this.f1146f) {
            this.f1144d.inset((int) Math.ceil(RoundRectDrawableWithShadow.c(this.f1145e, this.f1141a, this.f1147g)), (int) Math.ceil(RoundRectDrawableWithShadow.d(this.f1145e, this.f1141a, this.f1147g)));
            this.f1143c.set(this.f1144d);
        }
    }

    public ColorStateList b() {
        return this.f1148h;
    }

    float c() {
        return this.f1145e;
    }

    public float d() {
        return this.f1141a;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        boolean z;
        Paint paint = this.f1142b;
        if (this.f1149i == null || paint.getColorFilter() != null) {
            z = false;
        } else {
            paint.setColorFilter(this.f1149i);
            z = true;
        }
        RectF rectF = this.f1143c;
        float f2 = this.f1141a;
        canvas.drawRoundRect(rectF, f2, f2, paint);
        if (z) {
            paint.setColorFilter(null);
        }
    }

    public void f(ColorStateList colorStateList) {
        e(colorStateList);
        invalidateSelf();
    }

    void g(float f2, boolean z, boolean z2) {
        if (f2 == this.f1145e && this.f1146f == z && this.f1147g == z2) {
            return;
        }
        this.f1145e = f2;
        this.f1146f = z;
        this.f1147g = z2;
        i(null);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        outline.setRoundRect(this.f1144d, this.f1141a);
    }

    void h(float f2) {
        if (f2 == this.f1141a) {
            return;
        }
        this.f1141a = f2;
        i(null);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2 = this.f1150j;
        return (colorStateList2 != null && colorStateList2.isStateful()) || ((colorStateList = this.f1148h) != null && colorStateList.isStateful()) || super.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        i(rect);
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        PorterDuff.Mode mode;
        ColorStateList colorStateList = this.f1148h;
        int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
        boolean z = colorForState != this.f1142b.getColor();
        if (z) {
            this.f1142b.setColor(colorForState);
        }
        ColorStateList colorStateList2 = this.f1150j;
        if (colorStateList2 == null || (mode = this.f1151k) == null) {
            return z;
        }
        this.f1149i = a(colorStateList2, mode);
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.f1142b.setAlpha(i2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f1142b.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        this.f1150j = colorStateList;
        this.f1149i = a(colorStateList, this.f1151k);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        this.f1151k = mode;
        this.f1149i = a(this.f1150j, mode);
        invalidateSelf();
    }
}
