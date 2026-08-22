package com.airbnb.lottie.value;

import android.graphics.PointF;
import com.airbnb.lottie.utils.MiscUtils;

/* loaded from: classes.dex */
public class LottieInterpolatedPointValue extends LottieInterpolatedValue<PointF> {

    /* renamed from: g, reason: collision with root package name */
    private final PointF f9964g;

    @Override // com.airbnb.lottie.value.LottieInterpolatedValue, com.airbnb.lottie.value.LottieValueCallback
    public /* bridge */ /* synthetic */ Object a(LottieFrameInfo lottieFrameInfo) {
        return super.a(lottieFrameInfo);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.airbnb.lottie.value.LottieInterpolatedValue
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public PointF d(PointF pointF, PointF pointF2, float f2) {
        this.f9964g.set(MiscUtils.i(pointF.x, pointF2.x, f2), MiscUtils.i(pointF.y, pointF2.y, f2));
        return this.f9964g;
    }
}
