package com.airbnb.lottie.model.content;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.ContentGroup;
import com.airbnb.lottie.model.layer.BaseLayer;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public class ShapeGroup implements ContentModel {

    /* renamed from: a, reason: collision with root package name */
    private final String f9712a;

    /* renamed from: b, reason: collision with root package name */
    private final List f9713b;

    /* renamed from: c, reason: collision with root package name */
    private final boolean f9714c;

    public ShapeGroup(String str, List list, boolean z) {
        this.f9712a = str;
        this.f9713b = list;
        this.f9714c = z;
    }

    @Override // com.airbnb.lottie.model.content.ContentModel
    public Content a(LottieDrawable lottieDrawable, LottieComposition lottieComposition, BaseLayer baseLayer) {
        return new ContentGroup(lottieDrawable, baseLayer, this, lottieComposition);
    }

    public List b() {
        return this.f9713b;
    }

    public String c() {
        return this.f9712a;
    }

    public boolean d() {
        return this.f9714c;
    }

    public String toString() {
        return "ShapeGroup{name='" + this.f9712a + "' Shapes: " + Arrays.toString(this.f9713b.toArray()) + '}';
    }
}
