package com.airbnb.lottie.animation.keyframe;

import com.airbnb.lottie.utils.GammaEvaluator;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.List;

/* loaded from: classes.dex */
public class ColorKeyframeAnimation extends KeyframeAnimation<Integer> {
    public ColorKeyframeAnimation(List list) {
        super(list);
    }

    public int q() {
        return r(b(), d());
    }

    public int r(Keyframe keyframe, float f2) {
        Float f3;
        Integer num;
        if (keyframe.f9942b == null || keyframe.f9943c == null) {
            throw new IllegalStateException("Missing values for keyframe.");
        }
        LottieValueCallback lottieValueCallback = this.f9487e;
        return (lottieValueCallback == null || (f3 = keyframe.f9948h) == null || (num = (Integer) lottieValueCallback.b(keyframe.f9947g, f3.floatValue(), (Integer) keyframe.f9942b, (Integer) keyframe.f9943c, f2, e(), f())) == null) ? GammaEvaluator.c(MiscUtils.b(f2, 0.0f, 1.0f), ((Integer) keyframe.f9942b).intValue(), ((Integer) keyframe.f9943c).intValue()) : num.intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    /* renamed from: s, reason: merged with bridge method [inline-methods] */
    public Integer i(Keyframe keyframe, float f2) {
        return Integer.valueOf(r(keyframe, f2));
    }
}
