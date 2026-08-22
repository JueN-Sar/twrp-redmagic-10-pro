package com.airbnb.lottie.model.content;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.EllipseContent;
import com.airbnb.lottie.model.animatable.AnimatablePointValue;
import com.airbnb.lottie.model.animatable.AnimatableValue;
import com.airbnb.lottie.model.layer.BaseLayer;

/* loaded from: classes.dex */
public class CircleShape implements ContentModel {

    /* renamed from: a, reason: collision with root package name */
    private final String f9642a;

    /* renamed from: b, reason: collision with root package name */
    private final AnimatableValue f9643b;

    /* renamed from: c, reason: collision with root package name */
    private final AnimatablePointValue f9644c;

    /* renamed from: d, reason: collision with root package name */
    private final boolean f9645d;

    /* renamed from: e, reason: collision with root package name */
    private final boolean f9646e;

    public CircleShape(String str, AnimatableValue animatableValue, AnimatablePointValue animatablePointValue, boolean z, boolean z2) {
        this.f9642a = str;
        this.f9643b = animatableValue;
        this.f9644c = animatablePointValue;
        this.f9645d = z;
        this.f9646e = z2;
    }

    @Override // com.airbnb.lottie.model.content.ContentModel
    public Content a(LottieDrawable lottieDrawable, LottieComposition lottieComposition, BaseLayer baseLayer) {
        return new EllipseContent(lottieDrawable, baseLayer, this);
    }

    public String b() {
        return this.f9642a;
    }

    public AnimatableValue c() {
        return this.f9643b;
    }

    public AnimatablePointValue d() {
        return this.f9644c;
    }

    public boolean e() {
        return this.f9646e;
    }

    public boolean f() {
        return this.f9645d;
    }
}
