package com.airbnb.lottie.model.content;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.GradientStrokeContent;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableGradientColorValue;
import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.animatable.AnimatablePointValue;
import com.airbnb.lottie.model.content.ShapeStroke;
import com.airbnb.lottie.model.layer.BaseLayer;
import java.util.List;

/* loaded from: classes.dex */
public class GradientStroke implements ContentModel {

    /* renamed from: a, reason: collision with root package name */
    private final String f9659a;

    /* renamed from: b, reason: collision with root package name */
    private final GradientType f9660b;

    /* renamed from: c, reason: collision with root package name */
    private final AnimatableGradientColorValue f9661c;

    /* renamed from: d, reason: collision with root package name */
    private final AnimatableIntegerValue f9662d;

    /* renamed from: e, reason: collision with root package name */
    private final AnimatablePointValue f9663e;

    /* renamed from: f, reason: collision with root package name */
    private final AnimatablePointValue f9664f;

    /* renamed from: g, reason: collision with root package name */
    private final AnimatableFloatValue f9665g;

    /* renamed from: h, reason: collision with root package name */
    private final ShapeStroke.LineCapType f9666h;

    /* renamed from: i, reason: collision with root package name */
    private final ShapeStroke.LineJoinType f9667i;

    /* renamed from: j, reason: collision with root package name */
    private final float f9668j;

    /* renamed from: k, reason: collision with root package name */
    private final List f9669k;

    /* renamed from: l, reason: collision with root package name */
    private final AnimatableFloatValue f9670l;

    /* renamed from: m, reason: collision with root package name */
    private final boolean f9671m;

    public GradientStroke(String str, GradientType gradientType, AnimatableGradientColorValue animatableGradientColorValue, AnimatableIntegerValue animatableIntegerValue, AnimatablePointValue animatablePointValue, AnimatablePointValue animatablePointValue2, AnimatableFloatValue animatableFloatValue, ShapeStroke.LineCapType lineCapType, ShapeStroke.LineJoinType lineJoinType, float f2, List list, AnimatableFloatValue animatableFloatValue2, boolean z) {
        this.f9659a = str;
        this.f9660b = gradientType;
        this.f9661c = animatableGradientColorValue;
        this.f9662d = animatableIntegerValue;
        this.f9663e = animatablePointValue;
        this.f9664f = animatablePointValue2;
        this.f9665g = animatableFloatValue;
        this.f9666h = lineCapType;
        this.f9667i = lineJoinType;
        this.f9668j = f2;
        this.f9669k = list;
        this.f9670l = animatableFloatValue2;
        this.f9671m = z;
    }

    @Override // com.airbnb.lottie.model.content.ContentModel
    public Content a(LottieDrawable lottieDrawable, LottieComposition lottieComposition, BaseLayer baseLayer) {
        return new GradientStrokeContent(lottieDrawable, baseLayer, this);
    }

    public ShapeStroke.LineCapType b() {
        return this.f9666h;
    }

    public AnimatableFloatValue c() {
        return this.f9670l;
    }

    public AnimatablePointValue d() {
        return this.f9664f;
    }

    public AnimatableGradientColorValue e() {
        return this.f9661c;
    }

    public GradientType f() {
        return this.f9660b;
    }

    public ShapeStroke.LineJoinType g() {
        return this.f9667i;
    }

    public List h() {
        return this.f9669k;
    }

    public float i() {
        return this.f9668j;
    }

    public String j() {
        return this.f9659a;
    }

    public AnimatableIntegerValue k() {
        return this.f9662d;
    }

    public AnimatablePointValue l() {
        return this.f9663e;
    }

    public AnimatableFloatValue m() {
        return this.f9665g;
    }

    public boolean n() {
        return this.f9671m;
    }
}
