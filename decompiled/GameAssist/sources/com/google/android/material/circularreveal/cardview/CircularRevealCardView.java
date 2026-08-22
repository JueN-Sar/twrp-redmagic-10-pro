package com.google.android.material.circularreveal.cardview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.circularreveal.CircularRevealHelper;
import com.google.android.material.circularreveal.CircularRevealWidget;

/* loaded from: classes.dex */
public class CircularRevealCardView extends MaterialCardView implements CircularRevealWidget {

    @NonNull
    private final CircularRevealHelper helper;

    public CircularRevealCardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.helper = new CircularRevealHelper(this);
    }

    @Override // com.google.android.material.circularreveal.CircularRevealWidget
    public void a() {
        this.helper.a();
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        CircularRevealHelper circularRevealHelper = this.helper;
        if (circularRevealHelper != null) {
            circularRevealHelper.c(canvas);
        } else {
            super.draw(canvas);
        }
    }

    @Override // com.google.android.material.circularreveal.CircularRevealWidget
    public void e() {
        this.helper.b();
    }

    @Override // com.google.android.material.circularreveal.CircularRevealHelper.Delegate
    public void f(Canvas canvas) {
        super.draw(canvas);
    }

    @Nullable
    public Drawable getCircularRevealOverlayDrawable() {
        return this.helper.e();
    }

    @Override // com.google.android.material.circularreveal.CircularRevealWidget
    public int getCircularRevealScrimColor() {
        return this.helper.f();
    }

    @Override // com.google.android.material.circularreveal.CircularRevealWidget
    @Nullable
    public CircularRevealWidget.RevealInfo getRevealInfo() {
        return this.helper.h();
    }

    @Override // android.view.View
    public boolean isOpaque() {
        CircularRevealHelper circularRevealHelper = this.helper;
        return circularRevealHelper != null ? circularRevealHelper.j() : super.isOpaque();
    }

    @Override // com.google.android.material.circularreveal.CircularRevealHelper.Delegate
    public boolean j() {
        return super.isOpaque();
    }

    @Override // com.google.android.material.circularreveal.CircularRevealWidget
    public void setCircularRevealOverlayDrawable(@Nullable Drawable drawable) {
        this.helper.k(drawable);
    }

    @Override // com.google.android.material.circularreveal.CircularRevealWidget
    public void setCircularRevealScrimColor(@ColorInt int i2) {
        this.helper.l(i2);
    }

    @Override // com.google.android.material.circularreveal.CircularRevealWidget
    public void setRevealInfo(@Nullable CircularRevealWidget.RevealInfo revealInfo) {
        this.helper.m(revealInfo);
    }
}
