package com.google.android.material.progressindicator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import androidx.core.math.MathUtils;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import com.google.android.material.progressindicator.BaseProgressIndicatorSpec;
import com.google.android.material.progressindicator.DrawingDelegate;

/* loaded from: classes.dex */
public final class DeterminateDrawable<S extends BaseProgressIndicatorSpec> extends DrawableWithAnimatedVisibilityChange {
    private static final FloatPropertyCompat A = new FloatPropertyCompat<DeterminateDrawable<?>>("indicatorLevel") { // from class: com.google.android.material.progressindicator.DeterminateDrawable.1
        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public float a(DeterminateDrawable determinateDrawable) {
            return determinateDrawable.y() * 10000.0f;
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(DeterminateDrawable determinateDrawable, float f2) {
            determinateDrawable.A(f2 / 10000.0f);
        }
    };
    private DrawingDelegate v;
    private final SpringForce w;
    private final SpringAnimation x;
    private final DrawingDelegate.ActiveIndicator y;
    private boolean z;

    DeterminateDrawable(Context context, BaseProgressIndicatorSpec baseProgressIndicatorSpec, DrawingDelegate drawingDelegate) {
        super(context, baseProgressIndicatorSpec);
        this.z = false;
        z(drawingDelegate);
        this.y = new DrawingDelegate.ActiveIndicator();
        SpringForce springForce = new SpringForce();
        this.w = springForce;
        springForce.d(1.0f);
        springForce.f(50.0f);
        SpringAnimation springAnimation = new SpringAnimation(this, A);
        this.x = springAnimation;
        springAnimation.s(springForce);
        n(1.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void A(float f2) {
        this.y.f14915b = f2;
        invalidateSelf();
    }

    static DeterminateDrawable v(Context context, CircularProgressIndicatorSpec circularProgressIndicatorSpec, CircularDrawingDelegate circularDrawingDelegate) {
        return new DeterminateDrawable(context, circularProgressIndicatorSpec, circularDrawingDelegate);
    }

    static DeterminateDrawable w(Context context, LinearProgressIndicatorSpec linearProgressIndicatorSpec, LinearDrawingDelegate linearDrawingDelegate) {
        return new DeterminateDrawable(context, linearProgressIndicatorSpec, linearDrawingDelegate);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float y() {
        return this.y.f14915b;
    }

    void B(float f2) {
        setLevel((int) (f2 * 10000.0f));
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect rect = new Rect();
        if (!getBounds().isEmpty() && isVisible() && canvas.getClipBounds(rect)) {
            canvas.save();
            this.v.g(canvas, getBounds(), h(), k(), j());
            this.f14910s.setStyle(Paint.Style.FILL);
            this.f14910s.setAntiAlias(true);
            DrawingDelegate.ActiveIndicator activeIndicator = this.y;
            BaseProgressIndicatorSpec baseProgressIndicatorSpec = this.f14899h;
            activeIndicator.f14916c = baseProgressIndicatorSpec.f14870c[0];
            int i2 = baseProgressIndicatorSpec.f14874g;
            if (i2 > 0) {
                if (!(this.v instanceof LinearDrawingDelegate)) {
                    i2 = (int) ((i2 * MathUtils.a(y(), 0.0f, 0.01f)) / 0.01f);
                }
                this.v.d(canvas, this.f14910s, y(), 1.0f, this.f14899h.f14871d, getAlpha(), i2);
            } else {
                this.v.d(canvas, this.f14910s, 0.0f, 1.0f, baseProgressIndicatorSpec.f14871d, getAlpha(), 0);
            }
            this.v.c(canvas, this.f14910s, this.y, getAlpha());
            this.v.b(canvas, this.f14910s, this.f14899h.f14870c[0], getAlpha());
            canvas.restore();
        }
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getAlpha() {
        return super.getAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.v.e();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.v.f();
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getOpacity() {
        return super.getOpacity();
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public /* bridge */ /* synthetic */ boolean i() {
        return super.i();
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange, android.graphics.drawable.Animatable
    public /* bridge */ /* synthetic */ boolean isRunning() {
        return super.isRunning();
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public /* bridge */ /* synthetic */ boolean j() {
        return super.j();
    }

    @Override // android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        this.x.t();
        A(getLevel() / 10000.0f);
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public /* bridge */ /* synthetic */ boolean k() {
        return super.k();
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public /* bridge */ /* synthetic */ void m(Animatable2Compat.AnimationCallback animationCallback) {
        super.m(animationCallback);
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onLevelChange(int i2) {
        if (this.z) {
            this.x.t();
            A(i2 / 10000.0f);
            return true;
        }
        this.x.i(y() * 10000.0f);
        this.x.n(i2);
        return true;
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public /* bridge */ /* synthetic */ boolean q(boolean z, boolean z2, boolean z3) {
        return super.q(z, z2, z3);
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    boolean r(boolean z, boolean z2, boolean z3) {
        boolean r2 = super.r(z, z2, z3);
        float a2 = this.f14900i.a(this.f14898c.getContentResolver());
        if (a2 == 0.0f) {
            this.z = true;
        } else {
            this.z = false;
            this.w.f(50.0f / a2);
        }
        return r2;
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public /* bridge */ /* synthetic */ boolean s(Animatable2Compat.AnimationCallback animationCallback) {
        return super.s(animationCallback);
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setAlpha(int i2) {
        super.setAlpha(i2);
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setColorFilter(ColorFilter colorFilter) {
        super.setColorFilter(colorFilter);
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean setVisible(boolean z, boolean z2) {
        return super.setVisible(z, z2);
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange, android.graphics.drawable.Animatable
    public /* bridge */ /* synthetic */ void start() {
        super.start();
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange, android.graphics.drawable.Animatable
    public /* bridge */ /* synthetic */ void stop() {
        super.stop();
    }

    DrawingDelegate x() {
        return this.v;
    }

    void z(DrawingDelegate drawingDelegate) {
        this.v = drawingDelegate;
    }
}
