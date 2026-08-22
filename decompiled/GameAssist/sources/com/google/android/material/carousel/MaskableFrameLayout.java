package com.google.android.material.carousel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.core.math.MathUtils;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.canvas.CanvasCompat;
import com.google.android.material.shape.AbsoluteCornerSize;
import com.google.android.material.shape.ClampedCornerSize;
import com.google.android.material.shape.CornerSize;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import com.google.android.material.shape.ShapeableDelegate;

/* loaded from: classes.dex */
public class MaskableFrameLayout extends FrameLayout implements Maskable, Shapeable {
    private static final int NOT_SET = -1;
    private final RectF maskRect;
    private float maskXPercentage;

    @Nullable
    private OnMaskChangedListener onMaskChangedListener;

    @Nullable
    private Boolean savedForceCompatClippingEnabled;

    @NonNull
    private ShapeAppearanceModel shapeAppearanceModel;
    private final ShapeableDelegate shapeableDelegate;

    public MaskableFrameLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(Canvas canvas) {
        super.dispatchDraw(canvas);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ CornerSize d(CornerSize cornerSize) {
        return cornerSize instanceof AbsoluteCornerSize ? ClampedCornerSize.b((AbsoluteCornerSize) cornerSize) : cornerSize;
    }

    private void e() {
        this.shapeableDelegate.f(this, this.maskRect);
        OnMaskChangedListener onMaskChangedListener = this.onMaskChangedListener;
        if (onMaskChangedListener != null) {
            onMaskChangedListener.a(this.maskRect);
        }
    }

    private void f() {
        if (this.maskXPercentage != -1.0f) {
            float b2 = AnimationUtils.b(0.0f, getWidth() / 2.0f, 0.0f, 1.0f, this.maskXPercentage);
            setMaskRectF(new RectF(b2, 0.0f, getWidth() - b2, getHeight()));
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        this.shapeableDelegate.e(canvas, new CanvasCompat.CanvasOperation() { // from class: com.google.android.material.carousel.d
            @Override // com.google.android.material.canvas.CanvasCompat.CanvasOperation
            public final void a(Canvas canvas2) {
                MaskableFrameLayout.this.c(canvas2);
            }
        });
    }

    @Override // android.view.View
    public void getFocusedRect(Rect rect) {
        RectF rectF = this.maskRect;
        rect.set((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);
    }

    @NonNull
    public RectF getMaskRectF() {
        return this.maskRect;
    }

    @Deprecated
    public float getMaskXPercentage() {
        return this.maskXPercentage;
    }

    @Override // com.google.android.material.shape.Shapeable
    @NonNull
    public ShapeAppearanceModel getShapeAppearanceModel() {
        return this.shapeAppearanceModel;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Boolean bool = this.savedForceCompatClippingEnabled;
        if (bool != null) {
            this.shapeableDelegate.h(this, bool.booleanValue());
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        this.savedForceCompatClippingEnabled = Boolean.valueOf(this.shapeableDelegate.c());
        this.shapeableDelegate.h(this, true);
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (this.maskXPercentage != -1.0f) {
            f();
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.maskRect.isEmpty() && motionEvent.getAction() == 0) {
            if (!this.maskRect.contains(motionEvent.getX(), motionEvent.getY())) {
                return false;
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    @RestrictTo
    @VisibleForTesting
    public void setForceCompatClipping(boolean z) {
        this.shapeableDelegate.h(this, z);
    }

    @Override // com.google.android.material.carousel.Maskable
    public void setMaskRectF(@NonNull RectF rectF) {
        this.maskRect.set(rectF);
        e();
    }

    @Deprecated
    public void setMaskXPercentage(float f2) {
        float a2 = MathUtils.a(f2, 0.0f, 1.0f);
        if (this.maskXPercentage != a2) {
            this.maskXPercentage = a2;
            f();
        }
    }

    public void setOnMaskChangedListener(@Nullable OnMaskChangedListener onMaskChangedListener) {
        this.onMaskChangedListener = onMaskChangedListener;
    }

    @Override // com.google.android.material.shape.Shapeable
    public void setShapeAppearanceModel(@NonNull ShapeAppearanceModel shapeAppearanceModel) {
        ShapeAppearanceModel y = shapeAppearanceModel.y(new ShapeAppearanceModel.CornerSizeUnaryOperator() { // from class: com.google.android.material.carousel.c
            @Override // com.google.android.material.shape.ShapeAppearanceModel.CornerSizeUnaryOperator
            public final CornerSize a(CornerSize cornerSize) {
                CornerSize d2;
                d2 = MaskableFrameLayout.d(cornerSize);
                return d2;
            }
        });
        this.shapeAppearanceModel = y;
        this.shapeableDelegate.g(this, y);
    }

    public MaskableFrameLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.maskXPercentage = -1.0f;
        this.maskRect = new RectF();
        this.shapeableDelegate = ShapeableDelegate.a(this);
        this.savedForceCompatClippingEnabled = null;
        setShapeAppearanceModel(ShapeAppearanceModel.f(context, attributeSet, i2, 0, 0).m());
    }
}
