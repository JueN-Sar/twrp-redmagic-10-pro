package com.airbnb.lottie.animation.keyframe;

import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.List;

/* loaded from: classes.dex */
public class IntegerKeyframeAnimation extends KeyframeAnimation<Integer> {
    public IntegerKeyframeAnimation(List list) {
        super(list);
    }

    public int q() {
        return r(b(), d());
    }

    int r(Keyframe keyframe, float f2) {
        Integer num;
        if (keyframe.f9942b == null || keyframe.f9943c == null) {
            throw new IllegalStateException("Missing values for keyframe.");
        }
        LottieValueCallback lottieValueCallback = this.f9487e;
        return (lottieValueCallback == null || (num = (Integer) lottieValueCallback.b(keyframe.f9947g, keyframe.f9948h.floatValue(), (Integer) keyframe.f9942b, (Integer) keyframe.f9943c, f2, e(), f())) == null) ? MiscUtils.j(keyframe.h(), keyframe.e(), f2) : num.intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    /* renamed from: s, reason: merged with bridge method [inline-methods] */
    public Integer i(Keyframe keyframe, float f2) {
        return Integer.valueOf(r(keyframe, f2));
    }
}
