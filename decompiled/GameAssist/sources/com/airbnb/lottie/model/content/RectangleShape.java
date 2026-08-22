package com.airbnb.lottie.model.content;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.RectangleContent;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableValue;
import com.airbnb.lottie.model.layer.BaseLayer;

/* loaded from: classes.dex */
public class RectangleShape implements ContentModel {

    /* renamed from: a, reason: collision with root package name */
    private final String f9691a;

    /* renamed from: b, reason: collision with root package name */
    private final AnimatableValue f9692b;

    /* renamed from: c, reason: collision with root package name */
    private final AnimatableValue f9693c;

    /* renamed from: d, reason: collision with root package name */
    private final AnimatableFloatValue f9694d;

    /* renamed from: e, reason: collision with root package name */
    private final boolean f9695e;

    public RectangleShape(String str, AnimatableValue animatableValue, AnimatableValue animatableValue2, AnimatableFloatValue animatableFloatValue, boolean z) {
        this.f9691a = str;
        this.f9692b = animatableValue;
        this.f9693c = animatableValue2;
        this.f9694d = animatableFloatValue;
        this.f9695e = z;
    }

    @Override // com.airbnb.lottie.model.content.ContentModel
    public Content a(LottieDrawable lottieDrawable, LottieComposition lottieComposition, BaseLayer baseLayer) {
        return new RectangleContent(lottieDrawable, baseLayer, this);
    }

    public AnimatableFloatValue b() {
        return this.f9694d;
    }

    public String c() {
        return this.f9691a;
    }

    public AnimatableValue d() {
        return this.f9692b;
    }

    public AnimatableValue e() {
        return this.f9693c;
    }

    public boolean f() {
        return this.f9695e;
    }

    public String toString() {
        return "RectangleShape{position=" + this.f9692b + ", size=" + this.f9693c + '}';
    }
}
