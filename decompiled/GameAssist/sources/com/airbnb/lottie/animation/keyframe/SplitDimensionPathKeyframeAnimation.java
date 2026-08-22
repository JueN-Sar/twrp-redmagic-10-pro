package com.airbnb.lottie.animation.keyframe;

import android.graphics.PointF;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.Collections;

/* loaded from: classes.dex */
public class SplitDimensionPathKeyframeAnimation extends BaseKeyframeAnimation<PointF, PointF> {

    /* renamed from: i, reason: collision with root package name */
    private final PointF f9524i;

    /* renamed from: j, reason: collision with root package name */
    private final PointF f9525j;

    /* renamed from: k, reason: collision with root package name */
    private final BaseKeyframeAnimation f9526k;

    /* renamed from: l, reason: collision with root package name */
    private final BaseKeyframeAnimation f9527l;

    /* renamed from: m, reason: collision with root package name */
    protected LottieValueCallback f9528m;

    /* renamed from: n, reason: collision with root package name */
    protected LottieValueCallback f9529n;

    public SplitDimensionPathKeyframeAnimation(BaseKeyframeAnimation baseKeyframeAnimation, BaseKeyframeAnimation baseKeyframeAnimation2) {
        super(Collections.emptyList());
        this.f9524i = new PointF();
        this.f9525j = new PointF();
        this.f9526k = baseKeyframeAnimation;
        this.f9527l = baseKeyframeAnimation2;
        n(f());
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    public void n(float f2) {
        this.f9526k.n(f2);
        this.f9527l.n(f2);
        this.f9524i.set(((Float) this.f9526k.h()).floatValue(), ((Float) this.f9527l.h()).floatValue());
        for (int i2 = 0; i2 < this.f9483a.size(); i2++) {
            ((BaseKeyframeAnimation.AnimationListener) this.f9483a.get(i2)).a();
        }
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    /* renamed from: q, reason: merged with bridge method [inline-methods] */
    public PointF h() {
        return i(null, 0.0f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    /* renamed from: r, reason: merged with bridge method [inline-methods] */
    public PointF i(Keyframe keyframe, float f2) {
        Float f3;
        Keyframe b2;
        Keyframe b3;
        Float f4 = null;
        if (this.f9528m == null || (b3 = this.f9526k.b()) == null) {
            f3 = null;
        } else {
            float d2 = this.f9526k.d();
            Float f5 = b3.f9948h;
            LottieValueCallback lottieValueCallback = this.f9528m;
            float f6 = b3.f9947g;
            f3 = (Float) lottieValueCallback.b(f6, f5 == null ? f6 : f5.floatValue(), (Float) b3.f9942b, (Float) b3.f9943c, f2, f2, d2);
        }
        if (this.f9529n != null && (b2 = this.f9527l.b()) != null) {
            float d3 = this.f9527l.d();
            Float f7 = b2.f9948h;
            LottieValueCallback lottieValueCallback2 = this.f9529n;
            float f8 = b2.f9947g;
            f4 = (Float) lottieValueCallback2.b(f8, f7 == null ? f8 : f7.floatValue(), (Float) b2.f9942b, (Float) b2.f9943c, f2, f2, d3);
        }
        if (f3 == null) {
            this.f9525j.set(this.f9524i.x, 0.0f);
        } else {
            this.f9525j.set(f3.floatValue(), 0.0f);
        }
        if (f4 == null) {
            PointF pointF = this.f9525j;
            pointF.set(pointF.x, this.f9524i.y);
        } else {
            PointF pointF2 = this.f9525j;
            pointF2.set(pointF2.x, f4.floatValue());
        }
        return this.f9525j;
    }

    public void s(LottieValueCallback lottieValueCallback) {
        LottieValueCallback lottieValueCallback2 = this.f9528m;
        if (lottieValueCallback2 != null) {
            lottieValueCallback2.c(null);
        }
        this.f9528m = lottieValueCallback;
        if (lottieValueCallback != null) {
            lottieValueCallback.c(this);
        }
    }

    public void t(LottieValueCallback lottieValueCallback) {
        LottieValueCallback lottieValueCallback2 = this.f9529n;
        if (lottieValueCallback2 != null) {
            lottieValueCallback2.c(null);
        }
        this.f9529n = lottieValueCallback;
        if (lottieValueCallback != null) {
            lottieValueCallback.c(this);
        }
    }
}
