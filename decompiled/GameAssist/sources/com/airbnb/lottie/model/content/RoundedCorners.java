package com.airbnb.lottie.model.content;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.RoundedCornersContent;
import com.airbnb.lottie.model.animatable.AnimatableValue;
import com.airbnb.lottie.model.layer.BaseLayer;

/* loaded from: classes.dex */
public class RoundedCorners implements ContentModel {

    /* renamed from: a, reason: collision with root package name */
    private final String f9701a;

    /* renamed from: b, reason: collision with root package name */
    private final AnimatableValue f9702b;

    public RoundedCorners(String str, AnimatableValue animatableValue) {
        this.f9701a = str;
        this.f9702b = animatableValue;
    }

    @Override // com.airbnb.lottie.model.content.ContentModel
    public Content a(LottieDrawable lottieDrawable, LottieComposition lottieComposition, BaseLayer baseLayer) {
        return new RoundedCornersContent(lottieDrawable, baseLayer, this);
    }

    public AnimatableValue b() {
        return this.f9702b;
    }

    public String c() {
        return this.f9701a;
    }
}
