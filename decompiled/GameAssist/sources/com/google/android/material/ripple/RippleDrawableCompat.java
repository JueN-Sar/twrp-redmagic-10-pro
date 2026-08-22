package com.google.android.material.ripple;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.annotation.RestrictTo;
import androidx.core.graphics.drawable.TintAwareDrawable;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;

@RestrictTo
/* loaded from: classes.dex */
public class RippleDrawableCompat extends Drawable implements Shapeable, TintAwareDrawable {

    /* renamed from: c, reason: collision with root package name */
    private RippleDrawableCompatState f14977c;

    @Override // android.graphics.drawable.Drawable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public RippleDrawableCompat mutate() {
        this.f14977c = new RippleDrawableCompatState(this.f14977c);
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        RippleDrawableCompatState rippleDrawableCompatState = this.f14977c;
        if (rippleDrawableCompatState.f14979b) {
            rippleDrawableCompatState.f14978a.draw(canvas);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.f14977c;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.f14977c.f14978a.getOpacity();
    }

    @Override // com.google.android.material.shape.Shapeable
    public ShapeAppearanceModel getShapeAppearanceModel() {
        return this.f14977c.f14978a.getShapeAppearanceModel();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.f14977c.f14978a.setBounds(rect);
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        boolean onStateChange = super.onStateChange(iArr);
        if (this.f14977c.f14978a.setState(iArr)) {
            onStateChange = true;
        }
        boolean e2 = RippleUtils.e(iArr);
        RippleDrawableCompatState rippleDrawableCompatState = this.f14977c;
        if (rippleDrawableCompatState.f14979b == e2) {
            return onStateChange;
        }
        rippleDrawableCompatState.f14979b = e2;
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.f14977c.f14978a.setAlpha(i2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f14977c.f14978a.setColorFilter(colorFilter);
    }

    @Override // com.google.android.material.shape.Shapeable
    public void setShapeAppearanceModel(ShapeAppearanceModel shapeAppearanceModel) {
        this.f14977c.f14978a.setShapeAppearanceModel(shapeAppearanceModel);
    }

    @Override // android.graphics.drawable.Drawable
    public void setTint(int i2) {
        this.f14977c.f14978a.setTint(i2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        this.f14977c.f14978a.setTintList(colorStateList);
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        this.f14977c.f14978a.setTintMode(mode);
    }

    public RippleDrawableCompat(ShapeAppearanceModel shapeAppearanceModel) {
        this(new RippleDrawableCompatState(new MaterialShapeDrawable(shapeAppearanceModel)));
    }

    static final class RippleDrawableCompatState extends Drawable.ConstantState {

        /* renamed from: a, reason: collision with root package name */
        MaterialShapeDrawable f14978a;

        /* renamed from: b, reason: collision with root package name */
        boolean f14979b;

        public RippleDrawableCompatState(MaterialShapeDrawable materialShapeDrawable) {
            this.f14978a = materialShapeDrawable;
            this.f14979b = false;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public RippleDrawableCompat newDrawable() {
            return new RippleDrawableCompat(new RippleDrawableCompatState(this));
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return 0;
        }

        public RippleDrawableCompatState(RippleDrawableCompatState rippleDrawableCompatState) {
            this.f14978a = (MaterialShapeDrawable) rippleDrawableCompatState.f14978a.getConstantState().newDrawable();
            this.f14979b = rippleDrawableCompatState.f14979b;
        }
    }

    private RippleDrawableCompat(RippleDrawableCompatState rippleDrawableCompatState) {
        this.f14977c = rippleDrawableCompatState;
    }
}
