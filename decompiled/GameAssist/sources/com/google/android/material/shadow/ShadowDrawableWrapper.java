package com.google.android.material.shadow;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import androidx.appcompat.graphics.drawable.DrawableWrapperCompat;

@Deprecated
/* loaded from: classes.dex */
public class ShadowDrawableWrapper extends DrawableWrapperCompat {
    static final double v = Math.cos(Math.toRadians(45.0d));

    /* renamed from: h, reason: collision with root package name */
    final Paint f15058h;

    /* renamed from: i, reason: collision with root package name */
    final Paint f15059i;

    /* renamed from: j, reason: collision with root package name */
    final RectF f15060j;

    /* renamed from: k, reason: collision with root package name */
    float f15061k;

    /* renamed from: l, reason: collision with root package name */
    Path f15062l;

    /* renamed from: m, reason: collision with root package name */
    float f15063m;

    /* renamed from: n, reason: collision with root package name */
    float f15064n;

    /* renamed from: o, reason: collision with root package name */
    float f15065o;

    /* renamed from: p, reason: collision with root package name */
    private boolean f15066p;

    /* renamed from: q, reason: collision with root package name */
    private final int f15067q;

    /* renamed from: r, reason: collision with root package name */
    private final int f15068r;

    /* renamed from: s, reason: collision with root package name */
    private final int f15069s;
    private boolean t;
    private float u;

    private void c(Rect rect) {
        float f2 = this.f15063m;
        float f3 = 1.5f * f2;
        this.f15060j.set(rect.left + f2, rect.top + f3, rect.right - f2, rect.bottom - f3);
        Drawable a2 = a();
        RectF rectF = this.f15060j;
        a2.setBounds((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);
        d();
    }

    private void d() {
        float f2 = this.f15061k;
        RectF rectF = new RectF(-f2, -f2, f2, f2);
        RectF rectF2 = new RectF(rectF);
        float f3 = this.f15064n;
        rectF2.inset(-f3, -f3);
        Path path = this.f15062l;
        if (path == null) {
            this.f15062l = new Path();
        } else {
            path.reset();
        }
        this.f15062l.setFillType(Path.FillType.EVEN_ODD);
        this.f15062l.moveTo(-this.f15061k, 0.0f);
        this.f15062l.rLineTo(-this.f15064n, 0.0f);
        this.f15062l.arcTo(rectF2, 180.0f, 90.0f, false);
        this.f15062l.arcTo(rectF, 270.0f, -90.0f, false);
        this.f15062l.close();
        float f4 = -rectF2.top;
        if (f4 > 0.0f) {
            float f5 = this.f15061k / f4;
            this.f15058h.setShader(new RadialGradient(0.0f, 0.0f, f4, new int[]{0, this.f15067q, this.f15068r, this.f15069s}, new float[]{0.0f, f5, ((1.0f - f5) / 2.0f) + f5, 1.0f}, Shader.TileMode.CLAMP));
        }
        this.f15059i.setShader(new LinearGradient(0.0f, rectF.top, 0.0f, rectF2.top, new int[]{this.f15067q, this.f15068r, this.f15069s}, new float[]{0.0f, 0.5f, 1.0f}, Shader.TileMode.CLAMP));
        this.f15059i.setAntiAlias(false);
    }

    public static float e(float f2, float f3, boolean z) {
        return z ? (float) (f2 + ((1.0d - v) * f3)) : f2;
    }

    public static float f(float f2, float f3, boolean z) {
        return z ? (float) ((f2 * 1.5f) + ((1.0d - v) * f3)) : f2 * 1.5f;
    }

    private void g(Canvas canvas) {
        int i2;
        float f2;
        int i3;
        float f3;
        float f4;
        float f5;
        int save = canvas.save();
        canvas.rotate(this.u, this.f15060j.centerX(), this.f15060j.centerY());
        float f6 = this.f15061k;
        float f7 = (-f6) - this.f15064n;
        float f8 = f6 * 2.0f;
        boolean z = this.f15060j.width() - f8 > 0.0f;
        boolean z2 = this.f15060j.height() - f8 > 0.0f;
        float f9 = this.f15065o;
        float f10 = f6 / ((f9 - (0.5f * f9)) + f6);
        float f11 = f6 / ((f9 - (0.25f * f9)) + f6);
        float f12 = f6 / ((f9 - (f9 * 1.0f)) + f6);
        int save2 = canvas.save();
        RectF rectF = this.f15060j;
        canvas.translate(rectF.left + f6, rectF.top + f6);
        canvas.scale(f10, f11);
        canvas.drawPath(this.f15062l, this.f15058h);
        if (z) {
            canvas.scale(1.0f / f10, 1.0f);
            i2 = save2;
            f2 = f12;
            i3 = save;
            f3 = f11;
            canvas.drawRect(0.0f, f7, this.f15060j.width() - f8, -this.f15061k, this.f15059i);
        } else {
            i2 = save2;
            f2 = f12;
            i3 = save;
            f3 = f11;
        }
        canvas.restoreToCount(i2);
        int save3 = canvas.save();
        RectF rectF2 = this.f15060j;
        canvas.translate(rectF2.right - f6, rectF2.bottom - f6);
        float f13 = f2;
        canvas.scale(f10, f13);
        canvas.rotate(180.0f);
        canvas.drawPath(this.f15062l, this.f15058h);
        if (z) {
            canvas.scale(1.0f / f10, 1.0f);
            f4 = f3;
            f5 = f13;
            canvas.drawRect(0.0f, f7, this.f15060j.width() - f8, (-this.f15061k) + this.f15064n, this.f15059i);
        } else {
            f4 = f3;
            f5 = f13;
        }
        canvas.restoreToCount(save3);
        int save4 = canvas.save();
        RectF rectF3 = this.f15060j;
        canvas.translate(rectF3.left + f6, rectF3.bottom - f6);
        canvas.scale(f10, f5);
        canvas.rotate(270.0f);
        canvas.drawPath(this.f15062l, this.f15058h);
        if (z2) {
            canvas.scale(1.0f / f5, 1.0f);
            canvas.drawRect(0.0f, f7, this.f15060j.height() - f8, -this.f15061k, this.f15059i);
        }
        canvas.restoreToCount(save4);
        int save5 = canvas.save();
        RectF rectF4 = this.f15060j;
        canvas.translate(rectF4.right - f6, rectF4.top + f6);
        float f14 = f4;
        canvas.scale(f10, f14);
        canvas.rotate(90.0f);
        canvas.drawPath(this.f15062l, this.f15058h);
        if (z2) {
            canvas.scale(1.0f / f14, 1.0f);
            canvas.drawRect(0.0f, f7, this.f15060j.height() - f8, -this.f15061k, this.f15059i);
        }
        canvas.restoreToCount(save5);
        canvas.restoreToCount(i3);
    }

    @Override // androidx.appcompat.graphics.drawable.DrawableWrapperCompat, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.f15066p) {
            c(getBounds());
            this.f15066p = false;
        }
        g(canvas);
        super.draw(canvas);
    }

    @Override // androidx.appcompat.graphics.drawable.DrawableWrapperCompat, android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // androidx.appcompat.graphics.drawable.DrawableWrapperCompat, android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        int ceil = (int) Math.ceil(f(this.f15063m, this.f15061k, this.t));
        int ceil2 = (int) Math.ceil(e(this.f15063m, this.f15061k, this.t));
        rect.set(ceil2, ceil, ceil2, ceil);
        return true;
    }

    @Override // androidx.appcompat.graphics.drawable.DrawableWrapperCompat, android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        this.f15066p = true;
    }

    @Override // androidx.appcompat.graphics.drawable.DrawableWrapperCompat, android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        super.setAlpha(i2);
        this.f15058h.setAlpha(i2);
        this.f15059i.setAlpha(i2);
    }
}
