package com.google.android.material.progressindicator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import com.google.android.material.R;
import com.google.android.material.progressindicator.BaseProgressIndicatorSpec;
import com.google.android.material.progressindicator.DrawingDelegate;

/* loaded from: classes.dex */
public final class IndeterminateDrawable<S extends BaseProgressIndicatorSpec> extends DrawableWithAnimatedVisibilityChange {
    private DrawingDelegate v;
    private IndeterminateAnimatorDelegate w;
    private Drawable x;

    IndeterminateDrawable(Context context, BaseProgressIndicatorSpec baseProgressIndicatorSpec, DrawingDelegate drawingDelegate, IndeterminateAnimatorDelegate indeterminateAnimatorDelegate) {
        super(context, baseProgressIndicatorSpec);
        z(drawingDelegate);
        y(indeterminateAnimatorDelegate);
    }

    static IndeterminateDrawable t(Context context, CircularProgressIndicatorSpec circularProgressIndicatorSpec, CircularDrawingDelegate circularDrawingDelegate) {
        IndeterminateDrawable indeterminateDrawable = new IndeterminateDrawable(context, circularProgressIndicatorSpec, circularDrawingDelegate, new CircularIndeterminateAnimatorDelegate(circularProgressIndicatorSpec));
        indeterminateDrawable.setStaticDummyDrawable(VectorDrawableCompat.b(context.getResources(), R.drawable.indeterminate_static, null));
        return indeterminateDrawable;
    }

    static IndeterminateDrawable u(Context context, LinearProgressIndicatorSpec linearProgressIndicatorSpec, LinearDrawingDelegate linearDrawingDelegate) {
        return new IndeterminateDrawable(context, linearProgressIndicatorSpec, linearDrawingDelegate, linearProgressIndicatorSpec.f14946h == 0 ? new LinearIndeterminateContiguousAnimatorDelegate(linearProgressIndicatorSpec) : new LinearIndeterminateDisjointAnimatorDelegate(context, linearProgressIndicatorSpec));
    }

    private boolean x() {
        AnimatorDurationScaleProvider animatorDurationScaleProvider = this.f14900i;
        return animatorDurationScaleProvider != null && animatorDurationScaleProvider.a(this.f14898c.getContentResolver()) == 0.0f;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Drawable drawable;
        Rect rect = new Rect();
        if (!getBounds().isEmpty() && isVisible() && canvas.getClipBounds(rect)) {
            if (x() && (drawable = this.x) != null) {
                drawable.setBounds(getBounds());
                DrawableCompat.n(this.x, this.f14899h.f14870c[0]);
                this.x.draw(canvas);
                return;
            }
            canvas.save();
            this.v.g(canvas, getBounds(), h(), k(), j());
            int i2 = this.f14899h.f14874g;
            int alpha = getAlpha();
            if (i2 == 0) {
                this.v.d(canvas, this.f14910s, 0.0f, 1.0f, this.f14899h.f14871d, alpha, 0);
            } else {
                DrawingDelegate.ActiveIndicator activeIndicator = (DrawingDelegate.ActiveIndicator) this.w.f14919b.get(0);
                DrawingDelegate.ActiveIndicator activeIndicator2 = (DrawingDelegate.ActiveIndicator) this.w.f14919b.get(r3.size() - 1);
                DrawingDelegate drawingDelegate = this.v;
                if (drawingDelegate instanceof LinearDrawingDelegate) {
                    drawingDelegate.d(canvas, this.f14910s, 0.0f, activeIndicator.f14914a, this.f14899h.f14871d, alpha, i2);
                    this.v.d(canvas, this.f14910s, activeIndicator2.f14915b, 1.0f, this.f14899h.f14871d, alpha, i2);
                } else {
                    alpha = 0;
                    drawingDelegate.d(canvas, this.f14910s, activeIndicator2.f14915b, 1.0f + activeIndicator.f14914a, this.f14899h.f14871d, 0, i2);
                }
            }
            for (int i3 = 0; i3 < this.w.f14919b.size(); i3++) {
                DrawingDelegate.ActiveIndicator activeIndicator3 = (DrawingDelegate.ActiveIndicator) this.w.f14919b.get(i3);
                this.v.c(canvas, this.f14910s, activeIndicator3, getAlpha());
                if (i3 > 0 && i2 > 0) {
                    this.v.d(canvas, this.f14910s, ((DrawingDelegate.ActiveIndicator) this.w.f14919b.get(i3 - 1)).f14915b, activeIndicator3.f14914a, this.f14899h.f14871d, alpha, i2);
                }
            }
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

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public /* bridge */ /* synthetic */ boolean k() {
        return super.k();
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public /* bridge */ /* synthetic */ void m(Animatable2Compat.AnimationCallback animationCallback) {
        super.m(animationCallback);
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public /* bridge */ /* synthetic */ boolean q(boolean z, boolean z2, boolean z3) {
        return super.q(z, z2, z3);
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    boolean r(boolean z, boolean z2, boolean z3) {
        Drawable drawable;
        boolean r2 = super.r(z, z2, z3);
        if (x() && (drawable = this.x) != null) {
            return drawable.setVisible(z, z2);
        }
        if (!isRunning()) {
            this.w.a();
        }
        if (z && z3) {
            this.w.g();
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

    @RestrictTo
    @VisibleForTesting
    public void setStaticDummyDrawable(@Nullable Drawable drawable) {
        this.x = drawable;
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

    IndeterminateAnimatorDelegate v() {
        return this.w;
    }

    DrawingDelegate w() {
        return this.v;
    }

    void y(IndeterminateAnimatorDelegate indeterminateAnimatorDelegate) {
        this.w = indeterminateAnimatorDelegate;
        indeterminateAnimatorDelegate.e(this);
    }

    void z(DrawingDelegate drawingDelegate) {
        this.v = drawingDelegate;
    }
}
