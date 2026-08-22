package com.airbnb.lottie.animation.content;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import androidx.collection.LongSparseArray;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.model.content.GradientColor;
import com.airbnb.lottie.model.content.GradientStroke;
import com.airbnb.lottie.model.content.GradientType;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.value.LottieValueCallback;

/* loaded from: classes.dex */
public class GradientStrokeContent extends BaseStrokeContent {
    private final BaseKeyframeAnimation A;
    private ValueCallbackKeyframeAnimation B;

    /* renamed from: r, reason: collision with root package name */
    private final String f9414r;

    /* renamed from: s, reason: collision with root package name */
    private final boolean f9415s;
    private final LongSparseArray t;
    private final LongSparseArray u;
    private final RectF v;
    private final GradientType w;
    private final int x;
    private final BaseKeyframeAnimation y;
    private final BaseKeyframeAnimation z;

    public GradientStrokeContent(LottieDrawable lottieDrawable, BaseLayer baseLayer, GradientStroke gradientStroke) {
        super(lottieDrawable, baseLayer, gradientStroke.b().d(), gradientStroke.g().d(), gradientStroke.i(), gradientStroke.k(), gradientStroke.m(), gradientStroke.h(), gradientStroke.c());
        this.t = new LongSparseArray();
        this.u = new LongSparseArray();
        this.v = new RectF();
        this.f9414r = gradientStroke.j();
        this.w = gradientStroke.f();
        this.f9415s = gradientStroke.n();
        this.x = (int) (lottieDrawable.I().d() / 32.0f);
        BaseKeyframeAnimation a2 = gradientStroke.e().a();
        this.y = a2;
        a2.a(this);
        baseLayer.j(a2);
        BaseKeyframeAnimation a3 = gradientStroke.l().a();
        this.z = a3;
        a3.a(this);
        baseLayer.j(a3);
        BaseKeyframeAnimation a4 = gradientStroke.d().a();
        this.A = a4;
        a4.a(this);
        baseLayer.j(a4);
    }

    private int[] k(int[] iArr) {
        ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation = this.B;
        if (valueCallbackKeyframeAnimation != null) {
            Integer[] numArr = (Integer[]) valueCallbackKeyframeAnimation.h();
            int i2 = 0;
            if (iArr.length == numArr.length) {
                while (i2 < iArr.length) {
                    iArr[i2] = numArr[i2].intValue();
                    i2++;
                }
            } else {
                iArr = new int[numArr.length];
                while (i2 < numArr.length) {
                    iArr[i2] = numArr[i2].intValue();
                    i2++;
                }
            }
        }
        return iArr;
    }

    private int l() {
        int round = Math.round(this.z.f() * this.x);
        int round2 = Math.round(this.A.f() * this.x);
        int round3 = Math.round(this.y.f() * this.x);
        int i2 = round != 0 ? 527 * round : 17;
        if (round2 != 0) {
            i2 = i2 * 31 * round2;
        }
        return round3 != 0 ? i2 * 31 * round3 : i2;
    }

    private LinearGradient m() {
        long l2 = l();
        LinearGradient linearGradient = (LinearGradient) this.t.f(l2);
        if (linearGradient != null) {
            return linearGradient;
        }
        PointF pointF = (PointF) this.z.h();
        PointF pointF2 = (PointF) this.A.h();
        GradientColor gradientColor = (GradientColor) this.y.h();
        LinearGradient linearGradient2 = new LinearGradient(pointF.x, pointF.y, pointF2.x, pointF2.y, k(gradientColor.d()), gradientColor.e(), Shader.TileMode.CLAMP);
        this.t.k(l2, linearGradient2);
        return linearGradient2;
    }

    private RadialGradient n() {
        long l2 = l();
        RadialGradient radialGradient = (RadialGradient) this.u.f(l2);
        if (radialGradient != null) {
            return radialGradient;
        }
        PointF pointF = (PointF) this.z.h();
        PointF pointF2 = (PointF) this.A.h();
        GradientColor gradientColor = (GradientColor) this.y.h();
        int[] k2 = k(gradientColor.d());
        float[] e2 = gradientColor.e();
        RadialGradient radialGradient2 = new RadialGradient(pointF.x, pointF.y, (float) Math.hypot(pointF2.x - r7, pointF2.y - r8), k2, e2, Shader.TileMode.CLAMP);
        this.u.k(l2, radialGradient2);
        return radialGradient2;
    }

    @Override // com.airbnb.lottie.animation.content.BaseStrokeContent, com.airbnb.lottie.model.KeyPathElement
    public void e(Object obj, LottieValueCallback lottieValueCallback) {
        super.e(obj, lottieValueCallback);
        if (obj == LottieProperty.L) {
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation = this.B;
            if (valueCallbackKeyframeAnimation != null) {
                this.f9348f.I(valueCallbackKeyframeAnimation);
            }
            if (lottieValueCallback == null) {
                this.B = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation2 = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            this.B = valueCallbackKeyframeAnimation2;
            valueCallbackKeyframeAnimation2.a(this);
            this.f9348f.j(this.B);
        }
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public String getName() {
        return this.f9414r;
    }

    @Override // com.airbnb.lottie.animation.content.BaseStrokeContent, com.airbnb.lottie.animation.content.DrawingContent
    public void i(Canvas canvas, Matrix matrix, int i2) {
        if (this.f9415s) {
            return;
        }
        g(this.v, matrix, false);
        Shader m2 = this.w == GradientType.LINEAR ? m() : n();
        m2.setLocalMatrix(matrix);
        this.f9351i.setShader(m2);
        super.i(canvas, matrix, i2);
    }
}
