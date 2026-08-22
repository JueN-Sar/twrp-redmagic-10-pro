package com.airbnb.lottie.animation.keyframe;

import android.graphics.Color;
import android.graphics.Paint;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.parser.DropShadowEffect;
import com.airbnb.lottie.value.LottieFrameInfo;
import com.airbnb.lottie.value.LottieValueCallback;

/* loaded from: classes.dex */
public class DropShadowKeyframeAnimation implements BaseKeyframeAnimation.AnimationListener {

    /* renamed from: a, reason: collision with root package name */
    private final BaseKeyframeAnimation.AnimationListener f9497a;

    /* renamed from: b, reason: collision with root package name */
    private final BaseKeyframeAnimation f9498b;

    /* renamed from: c, reason: collision with root package name */
    private final BaseKeyframeAnimation f9499c;

    /* renamed from: d, reason: collision with root package name */
    private final BaseKeyframeAnimation f9500d;

    /* renamed from: e, reason: collision with root package name */
    private final BaseKeyframeAnimation f9501e;

    /* renamed from: f, reason: collision with root package name */
    private final BaseKeyframeAnimation f9502f;

    /* renamed from: g, reason: collision with root package name */
    private boolean f9503g = true;

    public DropShadowKeyframeAnimation(BaseKeyframeAnimation.AnimationListener animationListener, BaseLayer baseLayer, DropShadowEffect dropShadowEffect) {
        this.f9497a = animationListener;
        BaseKeyframeAnimation a2 = dropShadowEffect.a().a();
        this.f9498b = a2;
        a2.a(this);
        baseLayer.j(a2);
        BaseKeyframeAnimation a3 = dropShadowEffect.d().a();
        this.f9499c = a3;
        a3.a(this);
        baseLayer.j(a3);
        BaseKeyframeAnimation a4 = dropShadowEffect.b().a();
        this.f9500d = a4;
        a4.a(this);
        baseLayer.j(a4);
        BaseKeyframeAnimation a5 = dropShadowEffect.c().a();
        this.f9501e = a5;
        a5.a(this);
        baseLayer.j(a5);
        BaseKeyframeAnimation a6 = dropShadowEffect.e().a();
        this.f9502f = a6;
        a6.a(this);
        baseLayer.j(a6);
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener
    public void a() {
        this.f9503g = true;
        this.f9497a.a();
    }

    public void b(Paint paint) {
        if (this.f9503g) {
            this.f9503g = false;
            double floatValue = ((Float) this.f9500d.h()).floatValue() * 0.017453292519943295d;
            float floatValue2 = ((Float) this.f9501e.h()).floatValue();
            float sin = ((float) Math.sin(floatValue)) * floatValue2;
            float cos = ((float) Math.cos(floatValue + 3.141592653589793d)) * floatValue2;
            int intValue = ((Integer) this.f9498b.h()).intValue();
            paint.setShadowLayer(((Float) this.f9502f.h()).floatValue(), sin, cos, Color.argb(Math.round(((Float) this.f9499c.h()).floatValue()), Color.red(intValue), Color.green(intValue), Color.blue(intValue)));
        }
    }

    public void c(LottieValueCallback lottieValueCallback) {
        this.f9498b.o(lottieValueCallback);
    }

    public void d(LottieValueCallback lottieValueCallback) {
        this.f9500d.o(lottieValueCallback);
    }

    public void e(LottieValueCallback lottieValueCallback) {
        this.f9501e.o(lottieValueCallback);
    }

    public void f(final LottieValueCallback lottieValueCallback) {
        if (lottieValueCallback == null) {
            this.f9499c.o(null);
        } else {
            this.f9499c.o(new LottieValueCallback<Float>() { // from class: com.airbnb.lottie.animation.keyframe.DropShadowKeyframeAnimation.1
                @Override // com.airbnb.lottie.value.LottieValueCallback
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public Float a(LottieFrameInfo lottieFrameInfo) {
                    Float f2 = (Float) lottieValueCallback.a(lottieFrameInfo);
                    if (f2 == null) {
                        return null;
                    }
                    return Float.valueOf(f2.floatValue() * 2.55f);
                }
            });
        }
    }

    public void g(LottieValueCallback lottieValueCallback) {
        this.f9502f.o(lottieValueCallback);
    }
}
