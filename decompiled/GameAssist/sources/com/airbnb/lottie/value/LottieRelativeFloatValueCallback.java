package com.airbnb.lottie.value;

import com.airbnb.lottie.utils.MiscUtils;

/* loaded from: classes.dex */
public class LottieRelativeFloatValueCallback extends LottieValueCallback<Float> {
    public Float d(LottieFrameInfo lottieFrameInfo) {
        Object obj = this.f9971c;
        if (obj != null) {
            return (Float) obj;
        }
        throw new IllegalArgumentException("You must provide a static value in the constructor , call setValue, or override getValue.");
    }

    @Override // com.airbnb.lottie.value.LottieValueCallback
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public Float a(LottieFrameInfo lottieFrameInfo) {
        return Float.valueOf(MiscUtils.i(((Float) lottieFrameInfo.g()).floatValue(), ((Float) lottieFrameInfo.b()).floatValue(), lottieFrameInfo.c()) + d(lottieFrameInfo).floatValue());
    }
}
