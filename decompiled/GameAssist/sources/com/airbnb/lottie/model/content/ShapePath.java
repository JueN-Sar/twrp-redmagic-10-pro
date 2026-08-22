package com.airbnb.lottie.model.content;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.ShapeContent;
import com.airbnb.lottie.model.animatable.AnimatableShapeValue;
import com.airbnb.lottie.model.layer.BaseLayer;

/* loaded from: classes.dex */
public class ShapePath implements ContentModel {

    /* renamed from: a, reason: collision with root package name */
    private final String f9715a;

    /* renamed from: b, reason: collision with root package name */
    private final int f9716b;

    /* renamed from: c, reason: collision with root package name */
    private final AnimatableShapeValue f9717c;

    /* renamed from: d, reason: collision with root package name */
    private final boolean f9718d;

    public ShapePath(String str, int i2, AnimatableShapeValue animatableShapeValue, boolean z) {
        this.f9715a = str;
        this.f9716b = i2;
        this.f9717c = animatableShapeValue;
        this.f9718d = z;
    }

    @Override // com.airbnb.lottie.model.content.ContentModel
    public Content a(LottieDrawable lottieDrawable, LottieComposition lottieComposition, BaseLayer baseLayer) {
        return new ShapeContent(lottieDrawable, baseLayer, this);
    }

    public String b() {
        return this.f9715a;
    }

    public AnimatableShapeValue c() {
        return this.f9717c;
    }

    public boolean d() {
        return this.f9718d;
    }

    public String toString() {
        return "ShapePath{name=" + this.f9715a + ", index=" + this.f9716b + '}';
    }
}
