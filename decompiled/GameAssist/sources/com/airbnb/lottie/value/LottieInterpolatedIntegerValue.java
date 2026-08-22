package com.airbnb.lottie.value;

import com.airbnb.lottie.utils.MiscUtils;

/* loaded from: classes.dex */
public class LottieInterpolatedIntegerValue extends LottieInterpolatedValue<Integer> {
    @Override // com.airbnb.lottie.value.LottieInterpolatedValue, com.airbnb.lottie.value.LottieValueCallback
    public /* bridge */ /* synthetic */ Object a(LottieFrameInfo lottieFrameInfo) {
        return super.a(lottieFrameInfo);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.airbnb.lottie.value.LottieInterpolatedValue
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public Integer d(Integer num, Integer num2, float f2) {
        return Integer.valueOf(MiscUtils.j(num.intValue(), num2.intValue(), f2));
    }
}
