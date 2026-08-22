package com.airbnb.lottie.model.layer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.LPaint;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.value.LottieValueCallback;

/* loaded from: classes.dex */
public class SolidLayer extends BaseLayer {
    private final RectF D;
    private final Paint E;
    private final float[] F;
    private final Path G;
    private final Layer H;
    private BaseKeyframeAnimation I;
    private BaseKeyframeAnimation J;

    SolidLayer(LottieDrawable lottieDrawable, Layer layer) {
        super(lottieDrawable, layer);
        this.D = new RectF();
        LPaint lPaint = new LPaint();
        this.E = lPaint;
        this.F = new float[8];
        this.G = new Path();
        this.H = layer;
        lPaint.setAlpha(0);
        lPaint.setStyle(Paint.Style.FILL);
        lPaint.setColor(layer.p());
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer, com.airbnb.lottie.model.KeyPathElement
    public void e(Object obj, LottieValueCallback lottieValueCallback) {
        super.e(obj, lottieValueCallback);
        if (obj == LottieProperty.K) {
            if (lottieValueCallback == null) {
                this.I = null;
                return;
            } else {
                this.I = new ValueCallbackKeyframeAnimation(lottieValueCallback);
                return;
            }
        }
        if (obj == LottieProperty.f9305a) {
            if (lottieValueCallback != null) {
                this.J = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            } else {
                this.J = null;
                this.E.setColor(this.H.p());
            }
        }
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer, com.airbnb.lottie.animation.content.DrawingContent
    public void g(RectF rectF, Matrix matrix, boolean z) {
        super.g(rectF, matrix, z);
        this.D.set(0.0f, 0.0f, this.H.r(), this.H.q());
        this.f9751o.mapRect(this.D);
        rectF.set(this.D);
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer
    public void u(Canvas canvas, Matrix matrix, int i2) {
        int alpha = Color.alpha(this.H.p());
        if (alpha == 0) {
            return;
        }
        BaseKeyframeAnimation baseKeyframeAnimation = this.J;
        Integer num = baseKeyframeAnimation == null ? null : (Integer) baseKeyframeAnimation.h();
        if (num != null) {
            this.E.setColor(num.intValue());
        } else {
            this.E.setColor(this.H.p());
        }
        int intValue = (int) ((i2 / 255.0f) * (((alpha / 255.0f) * (this.x.h() == null ? 100 : ((Integer) this.x.h().h()).intValue())) / 100.0f) * 255.0f);
        this.E.setAlpha(intValue);
        BaseKeyframeAnimation baseKeyframeAnimation2 = this.I;
        if (baseKeyframeAnimation2 != null) {
            this.E.setColorFilter((ColorFilter) baseKeyframeAnimation2.h());
        }
        if (intValue > 0) {
            float[] fArr = this.F;
            fArr[0] = 0.0f;
            fArr[1] = 0.0f;
            fArr[2] = this.H.r();
            float[] fArr2 = this.F;
            fArr2[3] = 0.0f;
            fArr2[4] = this.H.r();
            this.F[5] = this.H.q();
            float[] fArr3 = this.F;
            fArr3[6] = 0.0f;
            fArr3[7] = this.H.q();
            matrix.mapPoints(this.F);
            this.G.reset();
            Path path = this.G;
            float[] fArr4 = this.F;
            path.moveTo(fArr4[0], fArr4[1]);
            Path path2 = this.G;
            float[] fArr5 = this.F;
            path2.lineTo(fArr5[2], fArr5[3]);
            Path path3 = this.G;
            float[] fArr6 = this.F;
            path3.lineTo(fArr6[4], fArr6[5]);
            Path path4 = this.G;
            float[] fArr7 = this.F;
            path4.lineTo(fArr7[6], fArr7[7]);
            Path path5 = this.G;
            float[] fArr8 = this.F;
            path5.lineTo(fArr8[0], fArr8[1]);
            this.G.close();
            canvas.drawPath(this.G, this.E);
        }
    }
}
