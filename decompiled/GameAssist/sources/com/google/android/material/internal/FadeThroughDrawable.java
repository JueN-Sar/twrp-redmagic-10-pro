package com.google.android.material.internal;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import androidx.annotation.RestrictTo;

@RestrictTo
/* loaded from: classes.dex */
public class FadeThroughDrawable extends Drawable {

    /* renamed from: a, reason: collision with root package name */
    private final Drawable f14718a;

    /* renamed from: b, reason: collision with root package name */
    private final Drawable f14719b;

    /* renamed from: c, reason: collision with root package name */
    private final float[] f14720c;

    /* renamed from: d, reason: collision with root package name */
    private float f14721d;

    public FadeThroughDrawable(Drawable drawable, Drawable drawable2) {
        this.f14718a = drawable.getConstantState().newDrawable().mutate();
        Drawable mutate = drawable2.getConstantState().newDrawable().mutate();
        this.f14719b = mutate;
        mutate.setAlpha(0);
        this.f14720c = new float[2];
    }

    public void a(float f2) {
        if (this.f14721d != f2) {
            this.f14721d = f2;
            FadeThroughUtils.a(f2, this.f14720c);
            this.f14718a.setAlpha((int) (this.f14720c[0] * 255.0f));
            this.f14719b.setAlpha((int) (this.f14720c[1] * 255.0f));
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        this.f14718a.draw(canvas);
        this.f14719b.draw(canvas);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return Math.max(this.f14718a.getIntrinsicHeight(), this.f14719b.getIntrinsicHeight());
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return Math.max(this.f14718a.getIntrinsicWidth(), this.f14719b.getIntrinsicWidth());
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumHeight() {
        return Math.max(this.f14718a.getMinimumHeight(), this.f14719b.getMinimumHeight());
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumWidth() {
        return Math.max(this.f14718a.getMinimumWidth(), this.f14719b.getMinimumWidth());
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return this.f14718a.isStateful() || this.f14719b.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        if (this.f14721d <= 0.5f) {
            this.f14718a.setAlpha(i2);
            this.f14719b.setAlpha(0);
        } else {
            this.f14718a.setAlpha(0);
            this.f14719b.setAlpha(i2);
        }
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setBounds(int i2, int i3, int i4, int i5) {
        super.setBounds(i2, i3, i4, i5);
        this.f14718a.setBounds(i2, i3, i4, i5);
        this.f14719b.setBounds(i2, i3, i4, i5);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f14718a.setColorFilter(colorFilter);
        this.f14719b.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setState(int[] iArr) {
        return this.f14718a.setState(iArr) || this.f14719b.setState(iArr);
    }
}
