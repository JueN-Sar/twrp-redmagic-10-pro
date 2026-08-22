package com.airbnb.lottie.value;

import android.view.animation.Interpolator;

/* loaded from: classes.dex */
abstract class LottieInterpolatedValue<T> extends LottieValueCallback<T> {

    /* renamed from: d, reason: collision with root package name */
    private final Object f9965d;

    /* renamed from: e, reason: collision with root package name */
    private final Object f9966e;

    /* renamed from: f, reason: collision with root package name */
    private final Interpolator f9967f;

    @Override // com.airbnb.lottie.value.LottieValueCallback
    public Object a(LottieFrameInfo lottieFrameInfo) {
        return d(this.f9965d, this.f9966e, this.f9967f.getInterpolation(lottieFrameInfo.e()));
    }

    abstract Object d(Object obj, Object obj2, float f2);
}
