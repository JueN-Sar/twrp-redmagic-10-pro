package com.airbnb.lottie.animation.content;

import android.graphics.Path;
import android.graphics.PointF;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.content.CircleShape;
import com.airbnb.lottie.model.content.ShapeTrimPath;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.List;

/* loaded from: classes.dex */
public class EllipseContent implements PathContent, BaseKeyframeAnimation.AnimationListener, KeyPathElementContent {

    /* renamed from: b, reason: collision with root package name */
    private final String f9375b;

    /* renamed from: c, reason: collision with root package name */
    private final LottieDrawable f9376c;

    /* renamed from: d, reason: collision with root package name */
    private final BaseKeyframeAnimation f9377d;

    /* renamed from: e, reason: collision with root package name */
    private final BaseKeyframeAnimation f9378e;

    /* renamed from: f, reason: collision with root package name */
    private final CircleShape f9379f;

    /* renamed from: h, reason: collision with root package name */
    private boolean f9381h;

    /* renamed from: a, reason: collision with root package name */
    private final Path f9374a = new Path();

    /* renamed from: g, reason: collision with root package name */
    private final CompoundTrimPathContent f9380g = new CompoundTrimPathContent();

    public EllipseContent(LottieDrawable lottieDrawable, BaseLayer baseLayer, CircleShape circleShape) {
        this.f9375b = circleShape.b();
        this.f9376c = lottieDrawable;
        BaseKeyframeAnimation a2 = circleShape.d().a();
        this.f9377d = a2;
        BaseKeyframeAnimation a3 = circleShape.c().a();
        this.f9378e = a3;
        this.f9379f = circleShape;
        baseLayer.j(a2);
        baseLayer.j(a3);
        a2.a(this);
        a3.a(this);
    }

    private void h() {
        this.f9381h = false;
        this.f9376c.invalidateSelf();
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener
    public void a() {
        h();
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public void b(List list, List list2) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            Content content = (Content) list.get(i2);
            if (content instanceof TrimPathContent) {
                TrimPathContent trimPathContent = (TrimPathContent) content;
                if (trimPathContent.k() == ShapeTrimPath.Type.SIMULTANEOUSLY) {
                    this.f9380g.a(trimPathContent);
                    trimPathContent.e(this);
                }
            }
        }
    }

    @Override // com.airbnb.lottie.animation.content.PathContent
    public Path d() {
        if (this.f9381h) {
            return this.f9374a;
        }
        this.f9374a.reset();
        if (this.f9379f.e()) {
            this.f9381h = true;
            return this.f9374a;
        }
        PointF pointF = (PointF) this.f9377d.h();
        float f2 = pointF.x / 2.0f;
        float f3 = pointF.y / 2.0f;
        float f4 = f2 * 0.55228f;
        float f5 = 0.55228f * f3;
        this.f9374a.reset();
        if (this.f9379f.f()) {
            float f6 = -f3;
            this.f9374a.moveTo(0.0f, f6);
            float f7 = 0.0f - f4;
            float f8 = -f2;
            float f9 = 0.0f - f5;
            this.f9374a.cubicTo(f7, f6, f8, f9, f8, 0.0f);
            float f10 = f5 + 0.0f;
            this.f9374a.cubicTo(f8, f10, f7, f3, 0.0f, f3);
            float f11 = f4 + 0.0f;
            this.f9374a.cubicTo(f11, f3, f2, f10, f2, 0.0f);
            this.f9374a.cubicTo(f2, f9, f11, f6, 0.0f, f6);
        } else {
            float f12 = -f3;
            this.f9374a.moveTo(0.0f, f12);
            float f13 = f4 + 0.0f;
            float f14 = 0.0f - f5;
            this.f9374a.cubicTo(f13, f12, f2, f14, f2, 0.0f);
            float f15 = f5 + 0.0f;
            this.f9374a.cubicTo(f2, f15, f13, f3, 0.0f, f3);
            float f16 = 0.0f - f4;
            float f17 = -f2;
            this.f9374a.cubicTo(f16, f3, f17, f15, f17, 0.0f);
            this.f9374a.cubicTo(f17, f14, f16, f12, 0.0f, f12);
        }
        PointF pointF2 = (PointF) this.f9378e.h();
        this.f9374a.offset(pointF2.x, pointF2.y);
        this.f9374a.close();
        this.f9380g.b(this.f9374a);
        this.f9381h = true;
        return this.f9374a;
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    public void e(Object obj, LottieValueCallback lottieValueCallback) {
        if (obj == LottieProperty.f9315k) {
            this.f9377d.o(lottieValueCallback);
        } else if (obj == LottieProperty.f9318n) {
            this.f9378e.o(lottieValueCallback);
        }
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    public void f(KeyPath keyPath, int i2, List list, KeyPath keyPath2) {
        MiscUtils.k(keyPath, i2, list, keyPath2, this);
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public String getName() {
        return this.f9375b;
    }
}
