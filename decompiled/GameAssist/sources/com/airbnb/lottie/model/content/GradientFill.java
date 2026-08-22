package com.airbnb.lottie.model.content;

import android.graphics.Path;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.GradientFillContent;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableGradientColorValue;
import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.animatable.AnimatablePointValue;
import com.airbnb.lottie.model.layer.BaseLayer;

/* loaded from: classes.dex */
public class GradientFill implements ContentModel {

    /* renamed from: a, reason: collision with root package name */
    private final GradientType f9649a;

    /* renamed from: b, reason: collision with root package name */
    private final Path.FillType f9650b;

    /* renamed from: c, reason: collision with root package name */
    private final AnimatableGradientColorValue f9651c;

    /* renamed from: d, reason: collision with root package name */
    private final AnimatableIntegerValue f9652d;

    /* renamed from: e, reason: collision with root package name */
    private final AnimatablePointValue f9653e;

    /* renamed from: f, reason: collision with root package name */
    private final AnimatablePointValue f9654f;

    /* renamed from: g, reason: collision with root package name */
    private final String f9655g;

    /* renamed from: h, reason: collision with root package name */
    private final AnimatableFloatValue f9656h;

    /* renamed from: i, reason: collision with root package name */
    private final AnimatableFloatValue f9657i;

    /* renamed from: j, reason: collision with root package name */
    private final boolean f9658j;

    public GradientFill(String str, GradientType gradientType, Path.FillType fillType, AnimatableGradientColorValue animatableGradientColorValue, AnimatableIntegerValue animatableIntegerValue, AnimatablePointValue animatablePointValue, AnimatablePointValue animatablePointValue2, AnimatableFloatValue animatableFloatValue, AnimatableFloatValue animatableFloatValue2, boolean z) {
        this.f9649a = gradientType;
        this.f9650b = fillType;
        this.f9651c = animatableGradientColorValue;
        this.f9652d = animatableIntegerValue;
        this.f9653e = animatablePointValue;
        this.f9654f = animatablePointValue2;
        this.f9655g = str;
        this.f9656h = animatableFloatValue;
        this.f9657i = animatableFloatValue2;
        this.f9658j = z;
    }

    @Override // com.airbnb.lottie.model.content.ContentModel
    public Content a(LottieDrawable lottieDrawable, LottieComposition lottieComposition, BaseLayer baseLayer) {
        return new GradientFillContent(lottieDrawable, lottieComposition, baseLayer, this);
    }

    public AnimatablePointValue b() {
        return this.f9654f;
    }

    public Path.FillType c() {
        return this.f9650b;
    }

    public AnimatableGradientColorValue d() {
        return this.f9651c;
    }

    public GradientType e() {
        return this.f9649a;
    }

    public String f() {
        return this.f9655g;
    }

    public AnimatableIntegerValue g() {
        return this.f9652d;
    }

    public AnimatablePointValue h() {
        return this.f9653e;
    }

    public boolean i() {
        return this.f9658j;
    }
}
