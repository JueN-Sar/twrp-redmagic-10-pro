package com.zte.mifavor.utils;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

/* loaded from: classes2.dex */
public class RoundedCornersDrawable extends Drawable {

    /* renamed from: a, reason: collision with root package name */
    private Paint f17425a;

    /* renamed from: b, reason: collision with root package name */
    private float f17426b;

    /* renamed from: c, reason: collision with root package name */
    private int f17427c;

    /* renamed from: d, reason: collision with root package name */
    private int f17428d;

    public void a(float f2) {
        this.f17426b = f2;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        int i2 = bounds.left;
        this.f17425a.setShader(new LinearGradient(i2, bounds.top, i2, bounds.bottom, this.f17427c, this.f17428d, Shader.TileMode.CLAMP));
        RectF rectF = new RectF(bounds);
        float f2 = this.f17426b;
        canvas.drawRoundRect(rectF, f2, f2, this.f17425a);
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return super.getAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }
}
