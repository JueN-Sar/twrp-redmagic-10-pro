package com.airbnb.lottie.animation.content;

import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.FloatKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.content.RectangleShape;
import com.airbnb.lottie.model.content.ShapeTrimPath;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.List;

/* loaded from: classes.dex */
public class RectangleContent implements BaseKeyframeAnimation.AnimationListener, KeyPathElementContent, PathContent {

    /* renamed from: c, reason: collision with root package name */
    private final String f9444c;

    /* renamed from: d, reason: collision with root package name */
    private final boolean f9445d;

    /* renamed from: e, reason: collision with root package name */
    private final LottieDrawable f9446e;

    /* renamed from: f, reason: collision with root package name */
    private final BaseKeyframeAnimation f9447f;

    /* renamed from: g, reason: collision with root package name */
    private final BaseKeyframeAnimation f9448g;

    /* renamed from: h, reason: collision with root package name */
    private final BaseKeyframeAnimation f9449h;

    /* renamed from: k, reason: collision with root package name */
    private boolean f9452k;

    /* renamed from: a, reason: collision with root package name */
    private final Path f9442a = new Path();

    /* renamed from: b, reason: collision with root package name */
    private final RectF f9443b = new RectF();

    /* renamed from: i, reason: collision with root package name */
    private final CompoundTrimPathContent f9450i = new CompoundTrimPathContent();

    /* renamed from: j, reason: collision with root package name */
    private BaseKeyframeAnimation f9451j = null;

    public RectangleContent(LottieDrawable lottieDrawable, BaseLayer baseLayer, RectangleShape rectangleShape) {
        this.f9444c = rectangleShape.c();
        this.f9445d = rectangleShape.f();
        this.f9446e = lottieDrawable;
        BaseKeyframeAnimation a2 = rectangleShape.d().a();
        this.f9447f = a2;
        BaseKeyframeAnimation a3 = rectangleShape.e().a();
        this.f9448g = a3;
        BaseKeyframeAnimation a4 = rectangleShape.b().a();
        this.f9449h = a4;
        baseLayer.j(a2);
        baseLayer.j(a3);
        baseLayer.j(a4);
        a2.a(this);
        a3.a(this);
        a4.a(this);
    }

    private void h() {
        this.f9452k = false;
        this.f9446e.invalidateSelf();
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
                    this.f9450i.a(trimPathContent);
                    trimPathContent.e(this);
                }
            }
            if (content instanceof RoundedCornersContent) {
                this.f9451j = ((RoundedCornersContent) content).h();
            }
        }
    }

    @Override // com.airbnb.lottie.animation.content.PathContent
    public Path d() {
        BaseKeyframeAnimation baseKeyframeAnimation;
        if (this.f9452k) {
            return this.f9442a;
        }
        this.f9442a.reset();
        if (this.f9445d) {
            this.f9452k = true;
            return this.f9442a;
        }
        PointF pointF = (PointF) this.f9448g.h();
        float f2 = pointF.x / 2.0f;
        float f3 = pointF.y / 2.0f;
        BaseKeyframeAnimation baseKeyframeAnimation2 = this.f9449h;
        float q2 = baseKeyframeAnimation2 == null ? 0.0f : ((FloatKeyframeAnimation) baseKeyframeAnimation2).q();
        if (q2 == 0.0f && (baseKeyframeAnimation = this.f9451j) != null) {
            q2 = Math.min(((Float) baseKeyframeAnimation.h()).floatValue(), Math.min(f2, f3));
        }
        float min = Math.min(f2, f3);
        if (q2 > min) {
            q2 = min;
        }
        PointF pointF2 = (PointF) this.f9447f.h();
        this.f9442a.moveTo(pointF2.x + f2, (pointF2.y - f3) + q2);
        this.f9442a.lineTo(pointF2.x + f2, (pointF2.y + f3) - q2);
        if (q2 > 0.0f) {
            RectF rectF = this.f9443b;
            float f4 = pointF2.x;
            float f5 = q2 * 2.0f;
            float f6 = pointF2.y;
            rectF.set((f4 + f2) - f5, (f6 + f3) - f5, f4 + f2, f6 + f3);
            this.f9442a.arcTo(this.f9443b, 0.0f, 90.0f, false);
        }
        this.f9442a.lineTo((pointF2.x - f2) + q2, pointF2.y + f3);
        if (q2 > 0.0f) {
            RectF rectF2 = this.f9443b;
            float f7 = pointF2.x;
            float f8 = pointF2.y;
            float f9 = q2 * 2.0f;
            rectF2.set(f7 - f2, (f8 + f3) - f9, (f7 - f2) + f9, f8 + f3);
            this.f9442a.arcTo(this.f9443b, 90.0f, 90.0f, false);
        }
        this.f9442a.lineTo(pointF2.x - f2, (pointF2.y - f3) + q2);
        if (q2 > 0.0f) {
            RectF rectF3 = this.f9443b;
            float f10 = pointF2.x;
            float f11 = pointF2.y;
            float f12 = q2 * 2.0f;
            rectF3.set(f10 - f2, f11 - f3, (f10 - f2) + f12, (f11 - f3) + f12);
            this.f9442a.arcTo(this.f9443b, 180.0f, 90.0f, false);
        }
        this.f9442a.lineTo((pointF2.x + f2) - q2, pointF2.y - f3);
        if (q2 > 0.0f) {
            RectF rectF4 = this.f9443b;
            float f13 = pointF2.x;
            float f14 = q2 * 2.0f;
            float f15 = pointF2.y;
            rectF4.set((f13 + f2) - f14, f15 - f3, f13 + f2, (f15 - f3) + f14);
            this.f9442a.arcTo(this.f9443b, 270.0f, 90.0f, false);
        }
        this.f9442a.close();
        this.f9450i.b(this.f9442a);
        this.f9452k = true;
        return this.f9442a;
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    public void e(Object obj, LottieValueCallback lottieValueCallback) {
        if (obj == LottieProperty.f9316l) {
            this.f9448g.o(lottieValueCallback);
        } else if (obj == LottieProperty.f9318n) {
            this.f9447f.o(lottieValueCallback);
        } else if (obj == LottieProperty.f9317m) {
            this.f9449h.o(lottieValueCallback);
        }
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    public void f(KeyPath keyPath, int i2, List list, KeyPath keyPath2) {
        MiscUtils.k(keyPath, i2, list, keyPath2, this);
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public String getName() {
        return this.f9444c;
    }
}
