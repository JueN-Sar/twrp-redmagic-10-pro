package com.airbnb.lottie.model.animatable;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.ModifierContent;
import com.airbnb.lottie.animation.keyframe.TransformKeyframeAnimation;
import com.airbnb.lottie.model.content.ContentModel;
import com.airbnb.lottie.model.layer.BaseLayer;

/* loaded from: classes.dex */
public class AnimatableTransform implements ModifierContent, ContentModel {

    /* renamed from: a, reason: collision with root package name */
    private final AnimatablePathValue f9630a;

    /* renamed from: b, reason: collision with root package name */
    private final AnimatableValue f9631b;

    /* renamed from: c, reason: collision with root package name */
    private final AnimatableScaleValue f9632c;

    /* renamed from: d, reason: collision with root package name */
    private final AnimatableFloatValue f9633d;

    /* renamed from: e, reason: collision with root package name */
    private final AnimatableIntegerValue f9634e;

    /* renamed from: f, reason: collision with root package name */
    private final AnimatableFloatValue f9635f;

    /* renamed from: g, reason: collision with root package name */
    private final AnimatableFloatValue f9636g;

    /* renamed from: h, reason: collision with root package name */
    private final AnimatableFloatValue f9637h;

    /* renamed from: i, reason: collision with root package name */
    private final AnimatableFloatValue f9638i;

    /* renamed from: j, reason: collision with root package name */
    private boolean f9639j;

    public AnimatableTransform() {
        this(null, null, null, null, null, null, null, null, null);
    }

    @Override // com.airbnb.lottie.model.content.ContentModel
    public Content a(LottieDrawable lottieDrawable, LottieComposition lottieComposition, BaseLayer baseLayer) {
        return null;
    }

    public TransformKeyframeAnimation b() {
        return new TransformKeyframeAnimation(this);
    }

    public AnimatablePathValue c() {
        return this.f9630a;
    }

    public AnimatableFloatValue d() {
        return this.f9638i;
    }

    public AnimatableIntegerValue e() {
        return this.f9634e;
    }

    public AnimatableValue f() {
        return this.f9631b;
    }

    public AnimatableFloatValue g() {
        return this.f9633d;
    }

    public AnimatableScaleValue h() {
        return this.f9632c;
    }

    public AnimatableFloatValue i() {
        return this.f9635f;
    }

    public AnimatableFloatValue j() {
        return this.f9636g;
    }

    public AnimatableFloatValue k() {
        return this.f9637h;
    }

    public boolean l() {
        return this.f9639j;
    }

    public void m(boolean z) {
        this.f9639j = z;
    }

    public AnimatableTransform(AnimatablePathValue animatablePathValue, AnimatableValue animatableValue, AnimatableScaleValue animatableScaleValue, AnimatableFloatValue animatableFloatValue, AnimatableIntegerValue animatableIntegerValue, AnimatableFloatValue animatableFloatValue2, AnimatableFloatValue animatableFloatValue3, AnimatableFloatValue animatableFloatValue4, AnimatableFloatValue animatableFloatValue5) {
        this.f9639j = false;
        this.f9630a = animatablePathValue;
        this.f9631b = animatableValue;
        this.f9632c = animatableScaleValue;
        this.f9633d = animatableFloatValue;
        this.f9634e = animatableIntegerValue;
        this.f9637h = animatableFloatValue2;
        this.f9638i = animatableFloatValue3;
        this.f9635f = animatableFloatValue4;
        this.f9636g = animatableFloatValue5;
    }
}
