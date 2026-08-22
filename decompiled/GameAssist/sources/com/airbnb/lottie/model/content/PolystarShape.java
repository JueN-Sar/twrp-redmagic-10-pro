package com.airbnb.lottie.model.content;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.PolystarContent;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableValue;
import com.airbnb.lottie.model.layer.BaseLayer;

/* loaded from: classes.dex */
public class PolystarShape implements ContentModel {

    /* renamed from: a, reason: collision with root package name */
    private final String f9680a;

    /* renamed from: b, reason: collision with root package name */
    private final Type f9681b;

    /* renamed from: c, reason: collision with root package name */
    private final AnimatableFloatValue f9682c;

    /* renamed from: d, reason: collision with root package name */
    private final AnimatableValue f9683d;

    /* renamed from: e, reason: collision with root package name */
    private final AnimatableFloatValue f9684e;

    /* renamed from: f, reason: collision with root package name */
    private final AnimatableFloatValue f9685f;

    /* renamed from: g, reason: collision with root package name */
    private final AnimatableFloatValue f9686g;

    /* renamed from: h, reason: collision with root package name */
    private final AnimatableFloatValue f9687h;

    /* renamed from: i, reason: collision with root package name */
    private final AnimatableFloatValue f9688i;

    /* renamed from: j, reason: collision with root package name */
    private final boolean f9689j;

    /* renamed from: k, reason: collision with root package name */
    private final boolean f9690k;

    public enum Type {
        STAR(1),
        POLYGON(2);

        private final int value;

        Type(int i2) {
            this.value = i2;
        }

        public static Type d(int i2) {
            for (Type type : values()) {
                if (type.value == i2) {
                    return type;
                }
            }
            return null;
        }
    }

    public PolystarShape(String str, Type type, AnimatableFloatValue animatableFloatValue, AnimatableValue animatableValue, AnimatableFloatValue animatableFloatValue2, AnimatableFloatValue animatableFloatValue3, AnimatableFloatValue animatableFloatValue4, AnimatableFloatValue animatableFloatValue5, AnimatableFloatValue animatableFloatValue6, boolean z, boolean z2) {
        this.f9680a = str;
        this.f9681b = type;
        this.f9682c = animatableFloatValue;
        this.f9683d = animatableValue;
        this.f9684e = animatableFloatValue2;
        this.f9685f = animatableFloatValue3;
        this.f9686g = animatableFloatValue4;
        this.f9687h = animatableFloatValue5;
        this.f9688i = animatableFloatValue6;
        this.f9689j = z;
        this.f9690k = z2;
    }

    @Override // com.airbnb.lottie.model.content.ContentModel
    public Content a(LottieDrawable lottieDrawable, LottieComposition lottieComposition, BaseLayer baseLayer) {
        return new PolystarContent(lottieDrawable, baseLayer, this);
    }

    public AnimatableFloatValue b() {
        return this.f9685f;
    }

    public AnimatableFloatValue c() {
        return this.f9687h;
    }

    public String d() {
        return this.f9680a;
    }

    public AnimatableFloatValue e() {
        return this.f9686g;
    }

    public AnimatableFloatValue f() {
        return this.f9688i;
    }

    public AnimatableFloatValue g() {
        return this.f9682c;
    }

    public AnimatableValue h() {
        return this.f9683d;
    }

    public AnimatableFloatValue i() {
        return this.f9684e;
    }

    public Type j() {
        return this.f9681b;
    }

    public boolean k() {
        return this.f9689j;
    }

    public boolean l() {
        return this.f9690k;
    }
}
