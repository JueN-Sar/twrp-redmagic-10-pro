package com.google.android.material.textfield;

import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.mlkit.common.MlKitException;

/* loaded from: classes.dex */
class CutoutDrawable extends MaterialShapeDrawable {
    CutoutDrawableState F;

    private static class ImplApi14 extends CutoutDrawable {
        private Paint G;
        private int H;

        private void A0(Canvas canvas) {
            this.H = canvas.saveLayer(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight(), null);
        }

        private boolean B0(Drawable.Callback callback) {
            return callback instanceof View;
        }

        private Paint x0() {
            if (this.G == null) {
                Paint paint = new Paint(1);
                this.G = paint;
                paint.setStyle(Paint.Style.FILL_AND_STROKE);
                this.G.setColor(-1);
                this.G.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
            }
            return this.G;
        }

        private void y0(Canvas canvas) {
            if (B0(getCallback())) {
                return;
            }
            canvas.restoreToCount(this.H);
        }

        private void z0(Canvas canvas) {
            Drawable.Callback callback = getCallback();
            if (!B0(callback)) {
                A0(canvas);
                return;
            }
            View view = (View) callback;
            if (view.getLayerType() != 2) {
                view.setLayerType(2, null);
            }
        }

        @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            z0(canvas);
            super.draw(canvas);
            y0(canvas);
        }

        @Override // com.google.android.material.shape.MaterialShapeDrawable
        protected void r(Canvas canvas) {
            super.r(canvas);
            canvas.drawRect(this.F.w, x0());
        }
    }

    @TargetApi(MlKitException.UNSUPPORTED)
    private static class ImplApi18 extends CutoutDrawable {
        ImplApi18(CutoutDrawableState cutoutDrawableState) {
            super(cutoutDrawableState);
        }

        @Override // com.google.android.material.shape.MaterialShapeDrawable
        protected void r(Canvas canvas) {
            if (this.F.w.isEmpty()) {
                super.r(canvas);
                return;
            }
            canvas.save();
            canvas.clipOutRect(this.F.w);
            super.r(canvas);
            canvas.restore();
        }
    }

    static CutoutDrawable r0(ShapeAppearanceModel shapeAppearanceModel) {
        if (shapeAppearanceModel == null) {
            shapeAppearanceModel = new ShapeAppearanceModel();
        }
        return s0(new CutoutDrawableState(shapeAppearanceModel, new RectF()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static CutoutDrawable s0(CutoutDrawableState cutoutDrawableState) {
        return new ImplApi18(cutoutDrawableState);
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public Drawable mutate() {
        this.F = new CutoutDrawableState(this.F);
        return this;
    }

    boolean t0() {
        return !this.F.w.isEmpty();
    }

    void u0() {
        v0(0.0f, 0.0f, 0.0f, 0.0f);
    }

    void v0(float f2, float f3, float f4, float f5) {
        if (f2 == this.F.w.left && f3 == this.F.w.top && f4 == this.F.w.right && f5 == this.F.w.bottom) {
            return;
        }
        this.F.w.set(f2, f3, f4, f5);
        invalidateSelf();
    }

    void w0(RectF rectF) {
        v0(rectF.left, rectF.top, rectF.right, rectF.bottom);
    }

    private static final class CutoutDrawableState extends MaterialShapeDrawable.MaterialShapeDrawableState {
        private final RectF w;

        @Override // com.google.android.material.shape.MaterialShapeDrawable.MaterialShapeDrawableState, android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            CutoutDrawable s0 = CutoutDrawable.s0(this);
            s0.invalidateSelf();
            return s0;
        }

        private CutoutDrawableState(ShapeAppearanceModel shapeAppearanceModel, RectF rectF) {
            super(shapeAppearanceModel, null);
            this.w = rectF;
        }

        private CutoutDrawableState(CutoutDrawableState cutoutDrawableState) {
            super(cutoutDrawableState);
            this.w = cutoutDrawableState.w;
        }
    }

    private CutoutDrawable(CutoutDrawableState cutoutDrawableState) {
        super(cutoutDrawableState);
        this.F = cutoutDrawableState;
    }
}
