package com.airbnb.lottie.animation.keyframe;

import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieValueCallback;
import com.airbnb.lottie.value.ScaleXY;
import java.util.List;

/* loaded from: classes.dex */
public class ScaleKeyframeAnimation extends KeyframeAnimation<ScaleXY> {

    /* renamed from: i, reason: collision with root package name */
    private final ScaleXY f9518i;

    public ScaleKeyframeAnimation(List list) {
        super(list);
        this.f9518i = new ScaleXY();
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    /* renamed from: q, reason: merged with bridge method [inline-methods] */
    public ScaleXY i(Keyframe keyframe, float f2) {
        Object obj;
        ScaleXY scaleXY;
        Object obj2 = keyframe.f9942b;
        if (obj2 == null || (obj = keyframe.f9943c) == null) {
            throw new IllegalStateException("Missing values for keyframe.");
        }
        ScaleXY scaleXY2 = (ScaleXY) obj2;
        ScaleXY scaleXY3 = (ScaleXY) obj;
        LottieValueCallback lottieValueCallback = this.f9487e;
        if (lottieValueCallback != null && (scaleXY = (ScaleXY) lottieValueCallback.b(keyframe.f9947g, keyframe.f9948h.floatValue(), scaleXY2, scaleXY3, f2, e(), f())) != null) {
            return scaleXY;
        }
        this.f9518i.d(MiscUtils.i(scaleXY2.b(), scaleXY3.b(), f2), MiscUtils.i(scaleXY2.c(), scaleXY3.c(), f2));
        return this.f9518i;
    }
}
