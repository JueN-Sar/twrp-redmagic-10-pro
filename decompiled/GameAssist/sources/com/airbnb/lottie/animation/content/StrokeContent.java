package com.airbnb.lottie.animation.content;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ColorKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.model.content.ShapeStroke;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.value.LottieValueCallback;

/* loaded from: classes.dex */
public class StrokeContent extends BaseStrokeContent {

    /* renamed from: r, reason: collision with root package name */
    private final BaseLayer f9474r;

    /* renamed from: s, reason: collision with root package name */
    private final String f9475s;
    private final boolean t;
    private final BaseKeyframeAnimation u;
    private BaseKeyframeAnimation v;

    public StrokeContent(LottieDrawable lottieDrawable, BaseLayer baseLayer, ShapeStroke shapeStroke) {
        super(lottieDrawable, baseLayer, shapeStroke.b().d(), shapeStroke.e().d(), shapeStroke.g(), shapeStroke.i(), shapeStroke.j(), shapeStroke.f(), shapeStroke.d());
        this.f9474r = baseLayer;
        this.f9475s = shapeStroke.h();
        this.t = shapeStroke.k();
        BaseKeyframeAnimation a2 = shapeStroke.c().a();
        this.u = a2;
        a2.a(this);
        baseLayer.j(a2);
    }

    @Override // com.airbnb.lottie.animation.content.BaseStrokeContent, com.airbnb.lottie.model.KeyPathElement
    public void e(Object obj, LottieValueCallback lottieValueCallback) {
        super.e(obj, lottieValueCallback);
        if (obj == LottieProperty.f9306b) {
            this.u.o(lottieValueCallback);
            return;
        }
        if (obj == LottieProperty.K) {
            BaseKeyframeAnimation baseKeyframeAnimation = this.v;
            if (baseKeyframeAnimation != null) {
                this.f9474r.I(baseKeyframeAnimation);
            }
            if (lottieValueCallback == null) {
                this.v = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            this.v = valueCallbackKeyframeAnimation;
            valueCallbackKeyframeAnimation.a(this);
            this.f9474r.j(this.u);
        }
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public String getName() {
        return this.f9475s;
    }

    @Override // com.airbnb.lottie.animation.content.BaseStrokeContent, com.airbnb.lottie.animation.content.DrawingContent
    public void i(Canvas canvas, Matrix matrix, int i2) {
        if (this.t) {
            return;
        }
        this.f9351i.setColor(((ColorKeyframeAnimation) this.u).q());
        BaseKeyframeAnimation baseKeyframeAnimation = this.v;
        if (baseKeyframeAnimation != null) {
            this.f9351i.setColorFilter((ColorFilter) baseKeyframeAnimation.h());
        }
        super.i(canvas, matrix, i2);
    }
}
