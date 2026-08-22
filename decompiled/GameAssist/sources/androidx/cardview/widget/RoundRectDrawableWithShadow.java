package androidx.cardview.widget;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import androidx.cardview.R;

/* loaded from: classes.dex */
class RoundRectDrawableWithShadow extends Drawable {

    /* renamed from: q, reason: collision with root package name */
    private static final double f1152q = Math.cos(Math.toRadians(45.0d));

    /* renamed from: r, reason: collision with root package name */
    static RoundRectHelper f1153r;

    /* renamed from: a, reason: collision with root package name */
    private final int f1154a;

    /* renamed from: c, reason: collision with root package name */
    private Paint f1156c;

    /* renamed from: d, reason: collision with root package name */
    private Paint f1157d;

    /* renamed from: e, reason: collision with root package name */
    private final RectF f1158e;

    /* renamed from: f, reason: collision with root package name */
    private float f1159f;

    /* renamed from: g, reason: collision with root package name */
    private Path f1160g;

    /* renamed from: h, reason: collision with root package name */
    private float f1161h;

    /* renamed from: i, reason: collision with root package name */
    private float f1162i;

    /* renamed from: j, reason: collision with root package name */
    private float f1163j;

    /* renamed from: k, reason: collision with root package name */
    private ColorStateList f1164k;

    /* renamed from: m, reason: collision with root package name */
    private final int f1166m;

    /* renamed from: n, reason: collision with root package name */
    private final int f1167n;

    /* renamed from: l, reason: collision with root package name */
    private boolean f1165l = true;

    /* renamed from: o, reason: collision with root package name */
    private boolean f1168o = true;

    /* renamed from: p, reason: collision with root package name */
    private boolean f1169p = false;

    /* renamed from: b, reason: collision with root package name */
    private Paint f1155b = new Paint(5);

    interface RoundRectHelper {
        void a(Canvas canvas, RectF rectF, float f2, Paint paint);
    }

    RoundRectDrawableWithShadow(Resources resources, ColorStateList colorStateList, float f2, float f3, float f4) {
        this.f1166m = resources.getColor(R.color.cardview_shadow_start_color);
        this.f1167n = resources.getColor(R.color.cardview_shadow_end_color);
        this.f1154a = resources.getDimensionPixelSize(R.dimen.cardview_compat_inset_shadow);
        n(colorStateList);
        Paint paint = new Paint(5);
        this.f1156c = paint;
        paint.setStyle(Paint.Style.FILL);
        this.f1159f = (int) (f2 + 0.5f);
        this.f1158e = new RectF();
        Paint paint2 = new Paint(this.f1156c);
        this.f1157d = paint2;
        paint2.setAntiAlias(false);
        s(f3, f4);
    }

    private void a(Rect rect) {
        float f2 = this.f1161h;
        float f3 = 1.5f * f2;
        this.f1158e.set(rect.left + f2, rect.top + f3, rect.right - f2, rect.bottom - f3);
        b();
    }

    private void b() {
        float f2 = this.f1159f;
        RectF rectF = new RectF(-f2, -f2, f2, f2);
        RectF rectF2 = new RectF(rectF);
        float f3 = this.f1162i;
        rectF2.inset(-f3, -f3);
        Path path = this.f1160g;
        if (path == null) {
            this.f1160g = new Path();
        } else {
            path.reset();
        }
        this.f1160g.setFillType(Path.FillType.EVEN_ODD);
        this.f1160g.moveTo(-this.f1159f, 0.0f);
        this.f1160g.rLineTo(-this.f1162i, 0.0f);
        this.f1160g.arcTo(rectF2, 180.0f, 90.0f, false);
        this.f1160g.arcTo(rectF, 270.0f, -90.0f, false);
        this.f1160g.close();
        float f4 = this.f1159f;
        float f5 = f4 / (this.f1162i + f4);
        Paint paint = this.f1156c;
        float f6 = this.f1159f + this.f1162i;
        int i2 = this.f1166m;
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        paint.setShader(new RadialGradient(0.0f, 0.0f, f6, new int[]{i2, i2, this.f1167n}, new float[]{0.0f, f5, 1.0f}, tileMode));
        Paint paint2 = this.f1157d;
        float f7 = this.f1159f;
        float f8 = this.f1162i;
        float f9 = (-f7) + f8;
        float f10 = (-f7) - f8;
        int i3 = this.f1166m;
        paint2.setShader(new LinearGradient(0.0f, f9, 0.0f, f10, new int[]{i3, i3, this.f1167n}, new float[]{0.0f, 0.5f, 1.0f}, tileMode));
        this.f1157d.setAntiAlias(false);
    }

    static float c(float f2, float f3, boolean z) {
        return z ? (float) (f2 + ((1.0d - f1152q) * f3)) : f2;
    }

    static float d(float f2, float f3, boolean z) {
        return z ? (float) ((f2 * 1.5f) + ((1.0d - f1152q) * f3)) : f2 * 1.5f;
    }

    private void e(Canvas canvas) {
        float f2 = this.f1159f;
        float f3 = (-f2) - this.f1162i;
        float f4 = f2 + this.f1154a + (this.f1163j / 2.0f);
        float f5 = f4 * 2.0f;
        boolean z = this.f1158e.width() - f5 > 0.0f;
        boolean z2 = this.f1158e.height() - f5 > 0.0f;
        int save = canvas.save();
        RectF rectF = this.f1158e;
        canvas.translate(rectF.left + f4, rectF.top + f4);
        canvas.drawPath(this.f1160g, this.f1156c);
        if (z) {
            canvas.drawRect(0.0f, f3, this.f1158e.width() - f5, -this.f1159f, this.f1157d);
        }
        canvas.restoreToCount(save);
        int save2 = canvas.save();
        RectF rectF2 = this.f1158e;
        canvas.translate(rectF2.right - f4, rectF2.bottom - f4);
        canvas.rotate(180.0f);
        canvas.drawPath(this.f1160g, this.f1156c);
        if (z) {
            canvas.drawRect(0.0f, f3, this.f1158e.width() - f5, (-this.f1159f) + this.f1162i, this.f1157d);
        }
        canvas.restoreToCount(save2);
        int save3 = canvas.save();
        RectF rectF3 = this.f1158e;
        canvas.translate(rectF3.left + f4, rectF3.bottom - f4);
        canvas.rotate(270.0f);
        canvas.drawPath(this.f1160g, this.f1156c);
        if (z2) {
            canvas.drawRect(0.0f, f3, this.f1158e.height() - f5, -this.f1159f, this.f1157d);
        }
        canvas.restoreToCount(save3);
        int save4 = canvas.save();
        RectF rectF4 = this.f1158e;
        canvas.translate(rectF4.right - f4, rectF4.top + f4);
        canvas.rotate(90.0f);
        canvas.drawPath(this.f1160g, this.f1156c);
        if (z2) {
            canvas.drawRect(0.0f, f3, this.f1158e.height() - f5, -this.f1159f, this.f1157d);
        }
        canvas.restoreToCount(save4);
    }

    private void n(ColorStateList colorStateList) {
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(0);
        }
        this.f1164k = colorStateList;
        this.f1155b.setColor(colorStateList.getColorForState(getState(), this.f1164k.getDefaultColor()));
    }

    private void s(float f2, float f3) {
        if (f2 < 0.0f) {
            throw new IllegalArgumentException("Invalid shadow size " + f2 + ". Must be >= 0");
        }
        if (f3 < 0.0f) {
            throw new IllegalArgumentException("Invalid max shadow size " + f3 + ". Must be >= 0");
        }
        float t = t(f2);
        float t2 = t(f3);
        if (t > t2) {
            if (!this.f1169p) {
                this.f1169p = true;
            }
            t = t2;
        }
        if (this.f1163j == t && this.f1161h == t2) {
            return;
        }
        this.f1163j = t;
        this.f1161h = t2;
        this.f1162i = (int) ((t * 1.5f) + this.f1154a + 0.5f);
        this.f1165l = true;
        invalidateSelf();
    }

    private int t(float f2) {
        int i2 = (int) (f2 + 0.5f);
        return i2 % 2 == 1 ? i2 - 1 : i2;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.f1165l) {
            a(getBounds());
            this.f1165l = false;
        }
        canvas.translate(0.0f, this.f1163j / 2.0f);
        e(canvas);
        canvas.translate(0.0f, (-this.f1163j) / 2.0f);
        f1153r.a(canvas, this.f1158e, this.f1159f, this.f1155b);
    }

    ColorStateList f() {
        return this.f1164k;
    }

    float g() {
        return this.f1159f;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        int ceil = (int) Math.ceil(d(this.f1161h, this.f1159f, this.f1168o));
        int ceil2 = (int) Math.ceil(c(this.f1161h, this.f1159f, this.f1168o));
        rect.set(ceil2, ceil, ceil2, ceil);
        return true;
    }

    void h(Rect rect) {
        getPadding(rect);
    }

    float i() {
        return this.f1161h;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList colorStateList = this.f1164k;
        return (colorStateList != null && colorStateList.isStateful()) || super.isStateful();
    }

    float j() {
        float f2 = this.f1161h;
        return (Math.max(f2, this.f1159f + this.f1154a + ((f2 * 1.5f) / 2.0f)) * 2.0f) + (((this.f1161h * 1.5f) + this.f1154a) * 2.0f);
    }

    float k() {
        float f2 = this.f1161h;
        return (Math.max(f2, this.f1159f + this.f1154a + (f2 / 2.0f)) * 2.0f) + ((this.f1161h + this.f1154a) * 2.0f);
    }

    float l() {
        return this.f1163j;
    }

    void m(boolean z) {
        this.f1168o = z;
        invalidateSelf();
    }

    void o(ColorStateList colorStateList) {
        n(colorStateList);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.f1165l = true;
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        ColorStateList colorStateList = this.f1164k;
        int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
        if (this.f1155b.getColor() == colorForState) {
            return false;
        }
        this.f1155b.setColor(colorForState);
        this.f1165l = true;
        invalidateSelf();
        return true;
    }

    void p(float f2) {
        if (f2 < 0.0f) {
            throw new IllegalArgumentException("Invalid radius " + f2 + ". Must be >= 0");
        }
        float f3 = (int) (f2 + 0.5f);
        if (this.f1159f == f3) {
            return;
        }
        this.f1159f = f3;
        this.f1165l = true;
        invalidateSelf();
    }

    void q(float f2) {
        s(this.f1163j, f2);
    }

    void r(float f2) {
        s(f2, this.f1161h);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.f1155b.setAlpha(i2);
        this.f1156c.setAlpha(i2);
        this.f1157d.setAlpha(i2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f1155b.setColorFilter(colorFilter);
    }
}
