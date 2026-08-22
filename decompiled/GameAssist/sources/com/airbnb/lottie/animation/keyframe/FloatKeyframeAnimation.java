package com.airbnb.lottie.animation.keyframe;

import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.List;

/* loaded from: classes.dex */
public class FloatKeyframeAnimation extends KeyframeAnimation<Float> {
    public FloatKeyframeAnimation(List list) {
        super(list);
    }

    public float q() {
        return r(b(), d());
    }

    float r(Keyframe keyframe, float f2) {
        Float f3;
        if (keyframe.f9942b == null || keyframe.f9943c == null) {
            throw new IllegalStateException("Missing values for keyframe.");
        }
        LottieValueCallback lottieValueCallback = this.f9487e;
        return (lottieValueCallback == null || (f3 = (Float) lottieValueCallback.b(keyframe.f9947g, keyframe.f9948h.floatValue(), (Float) keyframe.f9942b, (Float) keyframe.f9943c, f2, e(), f())) == null) ? MiscUtils.i(keyframe.g(), keyframe.d(), f2) : f3.floatValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    /* renamed from: s, reason: merged with bridge method [inline-methods] */
    public Float i(Keyframe keyframe, float f2) {
        return Float.valueOf(r(keyframe, f2));
    }
}
