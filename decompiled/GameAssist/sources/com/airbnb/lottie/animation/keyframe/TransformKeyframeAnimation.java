package com.airbnb.lottie.animation.keyframe;

import android.graphics.Matrix;
import android.graphics.PointF;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.model.animatable.AnimatableTransform;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieValueCallback;
import com.airbnb.lottie.value.ScaleXY;
import java.util.Collections;

/* loaded from: classes.dex */
public class TransformKeyframeAnimation {

    /* renamed from: a, reason: collision with root package name */
    private final Matrix f9534a = new Matrix();

    /* renamed from: b, reason: collision with root package name */
    private final Matrix f9535b;

    /* renamed from: c, reason: collision with root package name */
    private final Matrix f9536c;

    /* renamed from: d, reason: collision with root package name */
    private final Matrix f9537d;

    /* renamed from: e, reason: collision with root package name */
    private final float[] f9538e;

    /* renamed from: f, reason: collision with root package name */
    private BaseKeyframeAnimation f9539f;

    /* renamed from: g, reason: collision with root package name */
    private BaseKeyframeAnimation f9540g;

    /* renamed from: h, reason: collision with root package name */
    private BaseKeyframeAnimation f9541h;

    /* renamed from: i, reason: collision with root package name */
    private BaseKeyframeAnimation f9542i;

    /* renamed from: j, reason: collision with root package name */
    private BaseKeyframeAnimation f9543j;

    /* renamed from: k, reason: collision with root package name */
    private FloatKeyframeAnimation f9544k;

    /* renamed from: l, reason: collision with root package name */
    private FloatKeyframeAnimation f9545l;

    /* renamed from: m, reason: collision with root package name */
    private BaseKeyframeAnimation f9546m;

    /* renamed from: n, reason: collision with root package name */
    private BaseKeyframeAnimation f9547n;

    /* renamed from: o, reason: collision with root package name */
    private final boolean f9548o;

    public TransformKeyframeAnimation(AnimatableTransform animatableTransform) {
        this.f9539f = animatableTransform.c() == null ? null : animatableTransform.c().a();
        this.f9540g = animatableTransform.f() == null ? null : animatableTransform.f().a();
        this.f9541h = animatableTransform.h() == null ? null : animatableTransform.h().a();
        this.f9542i = animatableTransform.g() == null ? null : animatableTransform.g().a();
        this.f9544k = animatableTransform.i() == null ? null : (FloatKeyframeAnimation) animatableTransform.i().a();
        this.f9548o = animatableTransform.l();
        if (this.f9544k != null) {
            this.f9535b = new Matrix();
            this.f9536c = new Matrix();
            this.f9537d = new Matrix();
            this.f9538e = new float[9];
        } else {
            this.f9535b = null;
            this.f9536c = null;
            this.f9537d = null;
            this.f9538e = null;
        }
        this.f9545l = animatableTransform.j() == null ? null : (FloatKeyframeAnimation) animatableTransform.j().a();
        if (animatableTransform.e() != null) {
            this.f9543j = animatableTransform.e().a();
        }
        if (animatableTransform.k() != null) {
            this.f9546m = animatableTransform.k().a();
        } else {
            this.f9546m = null;
        }
        if (animatableTransform.d() != null) {
            this.f9547n = animatableTransform.d().a();
        } else {
            this.f9547n = null;
        }
    }

    private void d() {
        for (int i2 = 0; i2 < 9; i2++) {
            this.f9538e[i2] = 0.0f;
        }
    }

    public void a(BaseLayer baseLayer) {
        baseLayer.j(this.f9543j);
        baseLayer.j(this.f9546m);
        baseLayer.j(this.f9547n);
        baseLayer.j(this.f9539f);
        baseLayer.j(this.f9540g);
        baseLayer.j(this.f9541h);
        baseLayer.j(this.f9542i);
        baseLayer.j(this.f9544k);
        baseLayer.j(this.f9545l);
    }

    public void b(BaseKeyframeAnimation.AnimationListener animationListener) {
        BaseKeyframeAnimation baseKeyframeAnimation = this.f9543j;
        if (baseKeyframeAnimation != null) {
            baseKeyframeAnimation.a(animationListener);
        }
        BaseKeyframeAnimation baseKeyframeAnimation2 = this.f9546m;
        if (baseKeyframeAnimation2 != null) {
            baseKeyframeAnimation2.a(animationListener);
        }
        BaseKeyframeAnimation baseKeyframeAnimation3 = this.f9547n;
        if (baseKeyframeAnimation3 != null) {
            baseKeyframeAnimation3.a(animationListener);
        }
        BaseKeyframeAnimation baseKeyframeAnimation4 = this.f9539f;
        if (baseKeyframeAnimation4 != null) {
            baseKeyframeAnimation4.a(animationListener);
        }
        BaseKeyframeAnimation baseKeyframeAnimation5 = this.f9540g;
        if (baseKeyframeAnimation5 != null) {
            baseKeyframeAnimation5.a(animationListener);
        }
        BaseKeyframeAnimation baseKeyframeAnimation6 = this.f9541h;
        if (baseKeyframeAnimation6 != null) {
            baseKeyframeAnimation6.a(animationListener);
        }
        BaseKeyframeAnimation baseKeyframeAnimation7 = this.f9542i;
        if (baseKeyframeAnimation7 != null) {
            baseKeyframeAnimation7.a(animationListener);
        }
        FloatKeyframeAnimation floatKeyframeAnimation = this.f9544k;
        if (floatKeyframeAnimation != null) {
            floatKeyframeAnimation.a(animationListener);
        }
        FloatKeyframeAnimation floatKeyframeAnimation2 = this.f9545l;
        if (floatKeyframeAnimation2 != null) {
            floatKeyframeAnimation2.a(animationListener);
        }
    }

    public boolean c(Object obj, LottieValueCallback lottieValueCallback) {
        if (obj == LottieProperty.f9310f) {
            BaseKeyframeAnimation baseKeyframeAnimation = this.f9539f;
            if (baseKeyframeAnimation == null) {
                this.f9539f = new ValueCallbackKeyframeAnimation(lottieValueCallback, new PointF());
                return true;
            }
            baseKeyframeAnimation.o(lottieValueCallback);
            return true;
        }
        if (obj == LottieProperty.f9311g) {
            BaseKeyframeAnimation baseKeyframeAnimation2 = this.f9540g;
            if (baseKeyframeAnimation2 == null) {
                this.f9540g = new ValueCallbackKeyframeAnimation(lottieValueCallback, new PointF());
                return true;
            }
            baseKeyframeAnimation2.o(lottieValueCallback);
            return true;
        }
        if (obj == LottieProperty.f9312h) {
            BaseKeyframeAnimation baseKeyframeAnimation3 = this.f9540g;
            if (baseKeyframeAnimation3 instanceof SplitDimensionPathKeyframeAnimation) {
                ((SplitDimensionPathKeyframeAnimation) baseKeyframeAnimation3).s(lottieValueCallback);
                return true;
            }
        }
        if (obj == LottieProperty.f9313i) {
            BaseKeyframeAnimation baseKeyframeAnimation4 = this.f9540g;
            if (baseKeyframeAnimation4 instanceof SplitDimensionPathKeyframeAnimation) {
                ((SplitDimensionPathKeyframeAnimation) baseKeyframeAnimation4).t(lottieValueCallback);
                return true;
            }
        }
        if (obj == LottieProperty.f9319o) {
            BaseKeyframeAnimation baseKeyframeAnimation5 = this.f9541h;
            if (baseKeyframeAnimation5 == null) {
                this.f9541h = new ValueCallbackKeyframeAnimation(lottieValueCallback, new ScaleXY());
                return true;
            }
            baseKeyframeAnimation5.o(lottieValueCallback);
            return true;
        }
        if (obj == LottieProperty.f9320p) {
            BaseKeyframeAnimation baseKeyframeAnimation6 = this.f9542i;
            if (baseKeyframeAnimation6 == null) {
                this.f9542i = new ValueCallbackKeyframeAnimation(lottieValueCallback, Float.valueOf(0.0f));
                return true;
            }
            baseKeyframeAnimation6.o(lottieValueCallback);
            return true;
        }
        if (obj == LottieProperty.f9307c) {
            BaseKeyframeAnimation baseKeyframeAnimation7 = this.f9543j;
            if (baseKeyframeAnimation7 == null) {
                this.f9543j = new ValueCallbackKeyframeAnimation(lottieValueCallback, 100);
                return true;
            }
            baseKeyframeAnimation7.o(lottieValueCallback);
            return true;
        }
        if (obj == LottieProperty.C) {
            BaseKeyframeAnimation baseKeyframeAnimation8 = this.f9546m;
            if (baseKeyframeAnimation8 == null) {
                this.f9546m = new ValueCallbackKeyframeAnimation(lottieValueCallback, Float.valueOf(100.0f));
                return true;
            }
            baseKeyframeAnimation8.o(lottieValueCallback);
            return true;
        }
        if (obj == LottieProperty.D) {
            BaseKeyframeAnimation baseKeyframeAnimation9 = this.f9547n;
            if (baseKeyframeAnimation9 == null) {
                this.f9547n = new ValueCallbackKeyframeAnimation(lottieValueCallback, Float.valueOf(100.0f));
                return true;
            }
            baseKeyframeAnimation9.o(lottieValueCallback);
            return true;
        }
        if (obj == LottieProperty.f9321q) {
            if (this.f9544k == null) {
                this.f9544k = new FloatKeyframeAnimation(Collections.singletonList(new Keyframe(Float.valueOf(0.0f))));
            }
            this.f9544k.o(lottieValueCallback);
            return true;
        }
        if (obj != LottieProperty.f9322r) {
            return false;
        }
        if (this.f9545l == null) {
            this.f9545l = new FloatKeyframeAnimation(Collections.singletonList(new Keyframe(Float.valueOf(0.0f))));
        }
        this.f9545l.o(lottieValueCallback);
        return true;
    }

    public BaseKeyframeAnimation e() {
        return this.f9547n;
    }

    public Matrix f() {
        PointF pointF;
        ScaleXY scaleXY;
        PointF pointF2;
        this.f9534a.reset();
        BaseKeyframeAnimation baseKeyframeAnimation = this.f9540g;
        if (baseKeyframeAnimation != null && (pointF2 = (PointF) baseKeyframeAnimation.h()) != null) {
            float f2 = pointF2.x;
            if (f2 != 0.0f || pointF2.y != 0.0f) {
                this.f9534a.preTranslate(f2, pointF2.y);
            }
        }
        if (!this.f9548o) {
            BaseKeyframeAnimation baseKeyframeAnimation2 = this.f9542i;
            if (baseKeyframeAnimation2 != null) {
                float floatValue = baseKeyframeAnimation2 instanceof ValueCallbackKeyframeAnimation ? ((Float) baseKeyframeAnimation2.h()).floatValue() : ((FloatKeyframeAnimation) baseKeyframeAnimation2).q();
                if (floatValue != 0.0f) {
                    this.f9534a.preRotate(floatValue);
                }
            }
        } else if (baseKeyframeAnimation != null) {
            float f3 = baseKeyframeAnimation.f();
            PointF pointF3 = (PointF) baseKeyframeAnimation.h();
            float f4 = pointF3.x;
            float f5 = pointF3.y;
            baseKeyframeAnimation.n(1.0E-4f + f3);
            PointF pointF4 = (PointF) baseKeyframeAnimation.h();
            baseKeyframeAnimation.n(f3);
            this.f9534a.preRotate((float) Math.toDegrees(Math.atan2(pointF4.y - f5, pointF4.x - f4)));
        }
        if (this.f9544k != null) {
            float cos = this.f9545l == null ? 0.0f : (float) Math.cos(Math.toRadians((-r3.q()) + 90.0f));
            float sin = this.f9545l == null ? 1.0f : (float) Math.sin(Math.toRadians((-r5.q()) + 90.0f));
            float tan = (float) Math.tan(Math.toRadians(r0.q()));
            d();
            float[] fArr = this.f9538e;
            fArr[0] = cos;
            fArr[1] = sin;
            float f6 = -sin;
            fArr[3] = f6;
            fArr[4] = cos;
            fArr[8] = 1.0f;
            this.f9535b.setValues(fArr);
            d();
            float[] fArr2 = this.f9538e;
            fArr2[0] = 1.0f;
            fArr2[3] = tan;
            fArr2[4] = 1.0f;
            fArr2[8] = 1.0f;
            this.f9536c.setValues(fArr2);
            d();
            float[] fArr3 = this.f9538e;
            fArr3[0] = cos;
            fArr3[1] = f6;
            fArr3[3] = sin;
            fArr3[4] = cos;
            fArr3[8] = 1.0f;
            this.f9537d.setValues(fArr3);
            this.f9536c.preConcat(this.f9535b);
            this.f9537d.preConcat(this.f9536c);
            this.f9534a.preConcat(this.f9537d);
        }
        BaseKeyframeAnimation baseKeyframeAnimation3 = this.f9541h;
        if (baseKeyframeAnimation3 != null && (scaleXY = (ScaleXY) baseKeyframeAnimation3.h()) != null && (scaleXY.b() != 1.0f || scaleXY.c() != 1.0f)) {
            this.f9534a.preScale(scaleXY.b(), scaleXY.c());
        }
        BaseKeyframeAnimation baseKeyframeAnimation4 = this.f9539f;
        if (baseKeyframeAnimation4 != null && (pointF = (PointF) baseKeyframeAnimation4.h()) != null) {
            float f7 = pointF.x;
            if (f7 != 0.0f || pointF.y != 0.0f) {
                this.f9534a.preTranslate(-f7, -pointF.y);
            }
        }
        return this.f9534a;
    }

    public Matrix g(float f2) {
        BaseKeyframeAnimation baseKeyframeAnimation = this.f9540g;
        PointF pointF = baseKeyframeAnimation == null ? null : (PointF) baseKeyframeAnimation.h();
        BaseKeyframeAnimation baseKeyframeAnimation2 = this.f9541h;
        ScaleXY scaleXY = baseKeyframeAnimation2 == null ? null : (ScaleXY) baseKeyframeAnimation2.h();
        this.f9534a.reset();
        if (pointF != null) {
            this.f9534a.preTranslate(pointF.x * f2, pointF.y * f2);
        }
        if (scaleXY != null) {
            double d2 = f2;
            this.f9534a.preScale((float) Math.pow(scaleXY.b(), d2), (float) Math.pow(scaleXY.c(), d2));
        }
        BaseKeyframeAnimation baseKeyframeAnimation3 = this.f9542i;
        if (baseKeyframeAnimation3 != null) {
            float floatValue = ((Float) baseKeyframeAnimation3.h()).floatValue();
            BaseKeyframeAnimation baseKeyframeAnimation4 = this.f9539f;
            PointF pointF2 = baseKeyframeAnimation4 != null ? (PointF) baseKeyframeAnimation4.h() : null;
            this.f9534a.preRotate(floatValue * f2, pointF2 == null ? 0.0f : pointF2.x, pointF2 != null ? pointF2.y : 0.0f);
        }
        return this.f9534a;
    }

    public BaseKeyframeAnimation h() {
        return this.f9543j;
    }

    public BaseKeyframeAnimation i() {
        return this.f9546m;
    }

    public void j(float f2) {
        BaseKeyframeAnimation baseKeyframeAnimation = this.f9543j;
        if (baseKeyframeAnimation != null) {
            baseKeyframeAnimation.n(f2);
        }
        BaseKeyframeAnimation baseKeyframeAnimation2 = this.f9546m;
        if (baseKeyframeAnimation2 != null) {
            baseKeyframeAnimation2.n(f2);
        }
        BaseKeyframeAnimation baseKeyframeAnimation3 = this.f9547n;
        if (baseKeyframeAnimation3 != null) {
            baseKeyframeAnimation3.n(f2);
        }
        BaseKeyframeAnimation baseKeyframeAnimation4 = this.f9539f;
        if (baseKeyframeAnimation4 != null) {
            baseKeyframeAnimation4.n(f2);
        }
        BaseKeyframeAnimation baseKeyframeAnimation5 = this.f9540g;
        if (baseKeyframeAnimation5 != null) {
            baseKeyframeAnimation5.n(f2);
        }
        BaseKeyframeAnimation baseKeyframeAnimation6 = this.f9541h;
        if (baseKeyframeAnimation6 != null) {
            baseKeyframeAnimation6.n(f2);
        }
        BaseKeyframeAnimation baseKeyframeAnimation7 = this.f9542i;
        if (baseKeyframeAnimation7 != null) {
            baseKeyframeAnimation7.n(f2);
        }
        FloatKeyframeAnimation floatKeyframeAnimation = this.f9544k;
        if (floatKeyframeAnimation != null) {
            floatKeyframeAnimation.n(f2);
        }
        FloatKeyframeAnimation floatKeyframeAnimation2 = this.f9545l;
        if (floatKeyframeAnimation2 != null) {
            floatKeyframeAnimation2.n(f2);
        }
    }
}
