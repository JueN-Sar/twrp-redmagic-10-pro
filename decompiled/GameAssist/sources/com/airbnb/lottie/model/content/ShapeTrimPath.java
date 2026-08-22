package com.airbnb.lottie.model.content;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.TrimPathContent;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.layer.BaseLayer;

/* loaded from: classes.dex */
public class ShapeTrimPath implements ContentModel {

    /* renamed from: a, reason: collision with root package name */
    private final String f9731a;

    /* renamed from: b, reason: collision with root package name */
    private final Type f9732b;

    /* renamed from: c, reason: collision with root package name */
    private final AnimatableFloatValue f9733c;

    /* renamed from: d, reason: collision with root package name */
    private final AnimatableFloatValue f9734d;

    /* renamed from: e, reason: collision with root package name */
    private final AnimatableFloatValue f9735e;

    /* renamed from: f, reason: collision with root package name */
    private final boolean f9736f;

    public enum Type {
        SIMULTANEOUSLY,
        INDIVIDUALLY;

        public static Type d(int i2) {
            if (i2 == 1) {
                return SIMULTANEOUSLY;
            }
            if (i2 == 2) {
                return INDIVIDUALLY;
            }
            throw new IllegalArgumentException("Unknown trim path type " + i2);
        }
    }

    public ShapeTrimPath(String str, Type type, AnimatableFloatValue animatableFloatValue, AnimatableFloatValue animatableFloatValue2, AnimatableFloatValue animatableFloatValue3, boolean z) {
        this.f9731a = str;
        this.f9732b = type;
        this.f9733c = animatableFloatValue;
        this.f9734d = animatableFloatValue2;
        this.f9735e = animatableFloatValue3;
        this.f9736f = z;
    }

    @Override // com.airbnb.lottie.model.content.ContentModel
    public Content a(LottieDrawable lottieDrawable, LottieComposition lottieComposition, BaseLayer baseLayer) {
        return new TrimPathContent(baseLayer, this);
    }

    public AnimatableFloatValue b() {
        return this.f9734d;
    }

    public String c() {
        return this.f9731a;
    }

    public AnimatableFloatValue d() {
        return this.f9735e;
    }

    public AnimatableFloatValue e() {
        return this.f9733c;
    }

    public Type f() {
        return this.f9732b;
    }

    public boolean g() {
        return this.f9736f;
    }

    public String toString() {
        return "Trim Path: {start: " + this.f9733c + ", end: " + this.f9734d + ", offset: " + this.f9735e + "}";
    }
}
