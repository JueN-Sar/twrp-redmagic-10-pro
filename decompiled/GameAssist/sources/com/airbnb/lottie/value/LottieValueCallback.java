package com.airbnb.lottie.value;

import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;

/* loaded from: classes.dex */
public class LottieValueCallback<T> {

    /* renamed from: a, reason: collision with root package name */
    private final LottieFrameInfo f9969a;

    /* renamed from: b, reason: collision with root package name */
    private BaseKeyframeAnimation f9970b;

    /* renamed from: c, reason: collision with root package name */
    protected Object f9971c;

    public LottieValueCallback() {
        this.f9969a = new LottieFrameInfo();
        this.f9971c = null;
    }

    public Object a(LottieFrameInfo lottieFrameInfo) {
        return this.f9971c;
    }

    public final Object b(float f2, float f3, Object obj, Object obj2, float f4, float f5, float f6) {
        return a(this.f9969a.h(f2, f3, obj, obj2, f4, f5, f6));
    }

    public final void c(BaseKeyframeAnimation baseKeyframeAnimation) {
        this.f9970b = baseKeyframeAnimation;
    }

    public LottieValueCallback(Object obj) {
        this.f9969a = new LottieFrameInfo();
        this.f9971c = obj;
    }
}
