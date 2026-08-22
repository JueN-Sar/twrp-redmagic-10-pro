package com.airbnb.lottie.value;

import com.airbnb.lottie.utils.MiscUtils;

/* loaded from: classes.dex */
public class LottieInterpolatedFloatValue extends LottieInterpolatedValue<Float> {
    @Override // com.airbnb.lottie.value.LottieInterpolatedValue, com.airbnb.lottie.value.LottieValueCallback
    public /* bridge */ /* synthetic */ Object a(LottieFrameInfo lottieFrameInfo) {
        return super.a(lottieFrameInfo);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.airbnb.lottie.value.LottieInterpolatedValue
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public Float d(Float f2, Float f3, float f4) {
        return Float.valueOf(MiscUtils.i(f2.floatValue(), f3.floatValue(), f4));
    }
}
