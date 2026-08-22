package com.airbnb.lottie.model.content;

import android.graphics.Path;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.FillContent;
import com.airbnb.lottie.model.animatable.AnimatableColorValue;
import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.layer.BaseLayer;

/* loaded from: classes.dex */
public class ShapeFill implements ContentModel {

    /* renamed from: a, reason: collision with root package name */
    private final boolean f9706a;

    /* renamed from: b, reason: collision with root package name */
    private final Path.FillType f9707b;

    /* renamed from: c, reason: collision with root package name */
    private final String f9708c;

    /* renamed from: d, reason: collision with root package name */
    private final AnimatableColorValue f9709d;

    /* renamed from: e, reason: collision with root package name */
    private final AnimatableIntegerValue f9710e;

    /* renamed from: f, reason: collision with root package name */
    private final boolean f9711f;

    public ShapeFill(String str, boolean z, Path.FillType fillType, AnimatableColorValue animatableColorValue, AnimatableIntegerValue animatableIntegerValue, boolean z2) {
        this.f9708c = str;
        this.f9706a = z;
        this.f9707b = fillType;
        this.f9709d = animatableColorValue;
        this.f9710e = animatableIntegerValue;
        this.f9711f = z2;
    }

    @Override // com.airbnb.lottie.model.content.ContentModel
    public Content a(LottieDrawable lottieDrawable, LottieComposition lottieComposition, BaseLayer baseLayer) {
        return new FillContent(lottieDrawable, baseLayer, this);
    }

    public AnimatableColorValue b() {
        return this.f9709d;
    }

    public Path.FillType c() {
        return this.f9707b;
    }

    public String d() {
        return this.f9708c;
    }

    public AnimatableIntegerValue e() {
        return this.f9710e;
    }

    public boolean f() {
        return this.f9711f;
    }

    public String toString() {
        return "ShapeFill{color=, fillEnabled=" + this.f9706a + '}';
    }
}
