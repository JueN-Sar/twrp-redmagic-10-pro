package com.airbnb.lottie.animation.keyframe;

import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.Collections;

/* loaded from: classes.dex */
public class ValueCallbackKeyframeAnimation<K, A> extends BaseKeyframeAnimation<K, A> {

    /* renamed from: i, reason: collision with root package name */
    private final Object f9549i;

    public ValueCallbackKeyframeAnimation(LottieValueCallback lottieValueCallback) {
        this(lottieValueCallback, null);
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    float c() {
        return 1.0f;
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    public Object h() {
        LottieValueCallback lottieValueCallback = this.f9487e;
        Object obj = this.f9549i;
        return lottieValueCallback.b(0.0f, 0.0f, obj, obj, f(), f(), f());
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    Object i(Keyframe keyframe, float f2) {
        return h();
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    public void l() {
        if (this.f9487e != null) {
            super.l();
        }
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    public void n(float f2) {
        this.f9486d = f2;
    }

    public ValueCallbackKeyframeAnimation(LottieValueCallback lottieValueCallback, Object obj) {
        super(Collections.emptyList());
        o(lottieValueCallback);
        this.f9549i = obj;
    }
}
