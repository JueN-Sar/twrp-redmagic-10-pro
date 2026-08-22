package com.airbnb.lottie.model.layer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieImageAsset;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.LPaint;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.LottieValueCallback;

/* loaded from: classes.dex */
public class ImageLayer extends BaseLayer {
    private final Paint D;
    private final Rect E;
    private final Rect F;
    private final LottieImageAsset G;
    private BaseKeyframeAnimation H;
    private BaseKeyframeAnimation I;

    ImageLayer(LottieDrawable lottieDrawable, Layer layer) {
        super(lottieDrawable, layer);
        this.D = new LPaint(3);
        this.E = new Rect();
        this.F = new Rect();
        this.G = lottieDrawable.O(layer.n());
    }

    private Bitmap Q() {
        Bitmap bitmap;
        BaseKeyframeAnimation baseKeyframeAnimation = this.I;
        if (baseKeyframeAnimation != null && (bitmap = (Bitmap) baseKeyframeAnimation.h()) != null) {
            return bitmap;
        }
        Bitmap F = this.f9752p.F(this.f9753q.n());
        if (F != null) {
            return F;
        }
        LottieImageAsset lottieImageAsset = this.G;
        if (lottieImageAsset != null) {
            return lottieImageAsset.b();
        }
        return null;
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer, com.airbnb.lottie.model.KeyPathElement
    public void e(Object obj, LottieValueCallback lottieValueCallback) {
        super.e(obj, lottieValueCallback);
        if (obj == LottieProperty.K) {
            if (lottieValueCallback == null) {
                this.H = null;
                return;
            } else {
                this.H = new ValueCallbackKeyframeAnimation(lottieValueCallback);
                return;
            }
        }
        if (obj == LottieProperty.N) {
            if (lottieValueCallback == null) {
                this.I = null;
            } else {
                this.I = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            }
        }
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer, com.airbnb.lottie.animation.content.DrawingContent
    public void g(RectF rectF, Matrix matrix, boolean z) {
        super.g(rectF, matrix, z);
        if (this.G != null) {
            float e2 = Utils.e();
            rectF.set(0.0f, 0.0f, this.G.f() * e2, this.G.d() * e2);
            this.f9751o.mapRect(rectF);
        }
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer
    public void u(Canvas canvas, Matrix matrix, int i2) {
        Bitmap Q = Q();
        if (Q == null || Q.isRecycled() || this.G == null) {
            return;
        }
        float e2 = Utils.e();
        this.D.setAlpha(i2);
        BaseKeyframeAnimation baseKeyframeAnimation = this.H;
        if (baseKeyframeAnimation != null) {
            this.D.setColorFilter((ColorFilter) baseKeyframeAnimation.h());
        }
        canvas.save();
        canvas.concat(matrix);
        this.E.set(0, 0, Q.getWidth(), Q.getHeight());
        if (this.f9752p.P()) {
            this.F.set(0, 0, (int) (this.G.f() * e2), (int) (this.G.d() * e2));
        } else {
            this.F.set(0, 0, (int) (Q.getWidth() * e2), (int) (Q.getHeight() * e2));
        }
        canvas.drawBitmap(Q, this.E, this.F, this.D);
        canvas.restore();
    }
}
