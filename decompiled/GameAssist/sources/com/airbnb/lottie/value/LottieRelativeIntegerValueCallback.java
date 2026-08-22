package com.airbnb.lottie.value;

import com.airbnb.lottie.utils.MiscUtils;

/* loaded from: classes.dex */
public class LottieRelativeIntegerValueCallback extends LottieValueCallback<Integer> {
    public Integer d(LottieFrameInfo lottieFrameInfo) {
        Object obj = this.f9971c;
        if (obj != null) {
            return (Integer) obj;
        }
        throw new IllegalArgumentException("You must provide a static value in the constructor , call setValue, or override getValue.");
    }

    @Override // com.airbnb.lottie.value.LottieValueCallback
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public Integer a(LottieFrameInfo lottieFrameInfo) {
        return Integer.valueOf(MiscUtils.j(((Integer) lottieFrameInfo.g()).intValue(), ((Integer) lottieFrameInfo.b()).intValue(), lottieFrameInfo.c()) + d(lottieFrameInfo).intValue());
    }
}
