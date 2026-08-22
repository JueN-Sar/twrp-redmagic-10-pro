package com.airbnb.lottie.animation.content;

import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.model.content.ShapeTrimPath;
import com.airbnb.lottie.model.layer.BaseLayer;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class TrimPathContent implements Content, BaseKeyframeAnimation.AnimationListener {

    /* renamed from: a, reason: collision with root package name */
    private final String f9476a;

    /* renamed from: b, reason: collision with root package name */
    private final boolean f9477b;

    /* renamed from: c, reason: collision with root package name */
    private final List f9478c = new ArrayList();

    /* renamed from: d, reason: collision with root package name */
    private final ShapeTrimPath.Type f9479d;

    /* renamed from: e, reason: collision with root package name */
    private final BaseKeyframeAnimation f9480e;

    /* renamed from: f, reason: collision with root package name */
    private final BaseKeyframeAnimation f9481f;

    /* renamed from: g, reason: collision with root package name */
    private final BaseKeyframeAnimation f9482g;

    public TrimPathContent(BaseLayer baseLayer, ShapeTrimPath shapeTrimPath) {
        this.f9476a = shapeTrimPath.c();
        this.f9477b = shapeTrimPath.g();
        this.f9479d = shapeTrimPath.f();
        BaseKeyframeAnimation a2 = shapeTrimPath.e().a();
        this.f9480e = a2;
        BaseKeyframeAnimation a3 = shapeTrimPath.b().a();
        this.f9481f = a3;
        BaseKeyframeAnimation a4 = shapeTrimPath.d().a();
        this.f9482g = a4;
        baseLayer.j(a2);
        baseLayer.j(a3);
        baseLayer.j(a4);
        a2.a(this);
        a3.a(this);
        a4.a(this);
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener
    public void a() {
        for (int i2 = 0; i2 < this.f9478c.size(); i2++) {
            ((BaseKeyframeAnimation.AnimationListener) this.f9478c.get(i2)).a();
        }
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public void b(List list, List list2) {
    }

    void e(BaseKeyframeAnimation.AnimationListener animationListener) {
        this.f9478c.add(animationListener);
    }

    public BaseKeyframeAnimation f() {
        return this.f9481f;
    }

    public BaseKeyframeAnimation h() {
        return this.f9482g;
    }

    public BaseKeyframeAnimation j() {
        return this.f9480e;
    }

    ShapeTrimPath.Type k() {
        return this.f9479d;
    }

    public boolean l() {
        return this.f9477b;
    }
}
