package com.airbnb.lottie.model.content;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.RepeaterContent;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableTransform;
import com.airbnb.lottie.model.layer.BaseLayer;

/* loaded from: classes.dex */
public class Repeater implements ContentModel {

    /* renamed from: a, reason: collision with root package name */
    private final String f9696a;

    /* renamed from: b, reason: collision with root package name */
    private final AnimatableFloatValue f9697b;

    /* renamed from: c, reason: collision with root package name */
    private final AnimatableFloatValue f9698c;

    /* renamed from: d, reason: collision with root package name */
    private final AnimatableTransform f9699d;

    /* renamed from: e, reason: collision with root package name */
    private final boolean f9700e;

    public Repeater(String str, AnimatableFloatValue animatableFloatValue, AnimatableFloatValue animatableFloatValue2, AnimatableTransform animatableTransform, boolean z) {
        this.f9696a = str;
        this.f9697b = animatableFloatValue;
        this.f9698c = animatableFloatValue2;
        this.f9699d = animatableTransform;
        this.f9700e = z;
    }

    @Override // com.airbnb.lottie.model.content.ContentModel
    public Content a(LottieDrawable lottieDrawable, LottieComposition lottieComposition, BaseLayer baseLayer) {
        return new RepeaterContent(lottieDrawable, baseLayer, this);
    }

    public AnimatableFloatValue b() {
        return this.f9697b;
    }

    public String c() {
        return this.f9696a;
    }

    public AnimatableFloatValue d() {
        return this.f9698c;
    }

    public AnimatableTransform e() {
        return this.f9699d;
    }

    public boolean f() {
        return this.f9700e;
    }
}
