package com.airbnb.lottie.animation.content;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.LPaint;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.DropShadowKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.FloatKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.IntegerKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.content.ShapeTrimPath;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public abstract class BaseStrokeContent implements BaseKeyframeAnimation.AnimationListener, KeyPathElementContent, DrawingContent {

    /* renamed from: e, reason: collision with root package name */
    private final LottieDrawable f9347e;

    /* renamed from: f, reason: collision with root package name */
    protected final BaseLayer f9348f;

    /* renamed from: h, reason: collision with root package name */
    private final float[] f9350h;

    /* renamed from: i, reason: collision with root package name */
    final Paint f9351i;

    /* renamed from: j, reason: collision with root package name */
    private final BaseKeyframeAnimation f9352j;

    /* renamed from: k, reason: collision with root package name */
    private final BaseKeyframeAnimation f9353k;

    /* renamed from: l, reason: collision with root package name */
    private final List f9354l;

    /* renamed from: m, reason: collision with root package name */
    private final BaseKeyframeAnimation f9355m;

    /* renamed from: n, reason: collision with root package name */
    private BaseKeyframeAnimation f9356n;

    /* renamed from: o, reason: collision with root package name */
    private BaseKeyframeAnimation f9357o;

    /* renamed from: p, reason: collision with root package name */
    float f9358p;

    /* renamed from: q, reason: collision with root package name */
    private DropShadowKeyframeAnimation f9359q;

    /* renamed from: a, reason: collision with root package name */
    private final PathMeasure f9343a = new PathMeasure();

    /* renamed from: b, reason: collision with root package name */
    private final Path f9344b = new Path();

    /* renamed from: c, reason: collision with root package name */
    private final Path f9345c = new Path();

    /* renamed from: d, reason: collision with root package name */
    private final RectF f9346d = new RectF();

    /* renamed from: g, reason: collision with root package name */
    private final List f9349g = new ArrayList();

    private static final class PathGroup {

        /* renamed from: a, reason: collision with root package name */
        private final List f9360a;

        /* renamed from: b, reason: collision with root package name */
        private final TrimPathContent f9361b;

        private PathGroup(TrimPathContent trimPathContent) {
            this.f9360a = new ArrayList();
            this.f9361b = trimPathContent;
        }
    }

    BaseStrokeContent(LottieDrawable lottieDrawable, BaseLayer baseLayer, Paint.Cap cap, Paint.Join join, float f2, AnimatableIntegerValue animatableIntegerValue, AnimatableFloatValue animatableFloatValue, List list, AnimatableFloatValue animatableFloatValue2) {
        LPaint lPaint = new LPaint(1);
        this.f9351i = lPaint;
        this.f9358p = 0.0f;
        this.f9347e = lottieDrawable;
        this.f9348f = baseLayer;
        lPaint.setStyle(Paint.Style.STROKE);
        lPaint.setStrokeCap(cap);
        lPaint.setStrokeJoin(join);
        lPaint.setStrokeMiter(f2);
        this.f9353k = animatableIntegerValue.a();
        this.f9352j = animatableFloatValue.a();
        if (animatableFloatValue2 == null) {
            this.f9355m = null;
        } else {
            this.f9355m = animatableFloatValue2.a();
        }
        this.f9354l = new ArrayList(list.size());
        this.f9350h = new float[list.size()];
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.f9354l.add(((AnimatableFloatValue) list.get(i2)).a());
        }
        baseLayer.j(this.f9353k);
        baseLayer.j(this.f9352j);
        for (int i3 = 0; i3 < this.f9354l.size(); i3++) {
            baseLayer.j((BaseKeyframeAnimation) this.f9354l.get(i3));
        }
        BaseKeyframeAnimation baseKeyframeAnimation = this.f9355m;
        if (baseKeyframeAnimation != null) {
            baseLayer.j(baseKeyframeAnimation);
        }
        this.f9353k.a(this);
        this.f9352j.a(this);
        for (int i4 = 0; i4 < list.size(); i4++) {
            ((BaseKeyframeAnimation) this.f9354l.get(i4)).a(this);
        }
        BaseKeyframeAnimation baseKeyframeAnimation2 = this.f9355m;
        if (baseKeyframeAnimation2 != null) {
            baseKeyframeAnimation2.a(this);
        }
        if (baseLayer.x() != null) {
            BaseKeyframeAnimation a2 = baseLayer.x().a().a();
            this.f9357o = a2;
            a2.a(this);
            baseLayer.j(this.f9357o);
        }
        if (baseLayer.z() != null) {
            this.f9359q = new DropShadowKeyframeAnimation(this, baseLayer, baseLayer.z());
        }
    }

    private void h(Matrix matrix) {
        L.b("StrokeContent#applyDashPattern");
        if (this.f9354l.isEmpty()) {
            L.c("StrokeContent#applyDashPattern");
            return;
        }
        float g2 = Utils.g(matrix);
        for (int i2 = 0; i2 < this.f9354l.size(); i2++) {
            this.f9350h[i2] = ((Float) ((BaseKeyframeAnimation) this.f9354l.get(i2)).h()).floatValue();
            if (i2 % 2 == 0) {
                float[] fArr = this.f9350h;
                if (fArr[i2] < 1.0f) {
                    fArr[i2] = 1.0f;
                }
            } else {
                float[] fArr2 = this.f9350h;
                if (fArr2[i2] < 0.1f) {
                    fArr2[i2] = 0.1f;
                }
            }
            float[] fArr3 = this.f9350h;
            fArr3[i2] = fArr3[i2] * g2;
        }
        BaseKeyframeAnimation baseKeyframeAnimation = this.f9355m;
        this.f9351i.setPathEffect(new DashPathEffect(this.f9350h, baseKeyframeAnimation == null ? 0.0f : g2 * ((Float) baseKeyframeAnimation.h()).floatValue()));
        L.c("StrokeContent#applyDashPattern");
    }

    private void j(Canvas canvas, PathGroup pathGroup, Matrix matrix) {
        L.b("StrokeContent#applyTrimPath");
        if (pathGroup.f9361b == null) {
            L.c("StrokeContent#applyTrimPath");
            return;
        }
        this.f9344b.reset();
        for (int size = pathGroup.f9360a.size() - 1; size >= 0; size--) {
            this.f9344b.addPath(((PathContent) pathGroup.f9360a.get(size)).d(), matrix);
        }
        float floatValue = ((Float) pathGroup.f9361b.j().h()).floatValue() / 100.0f;
        float floatValue2 = ((Float) pathGroup.f9361b.f().h()).floatValue() / 100.0f;
        float floatValue3 = ((Float) pathGroup.f9361b.h().h()).floatValue() / 360.0f;
        if (floatValue < 0.01f && floatValue2 > 0.99f) {
            canvas.drawPath(this.f9344b, this.f9351i);
            L.c("StrokeContent#applyTrimPath");
            return;
        }
        this.f9343a.setPath(this.f9344b, false);
        float length = this.f9343a.getLength();
        while (this.f9343a.nextContour()) {
            length += this.f9343a.getLength();
        }
        float f2 = floatValue3 * length;
        float f3 = (floatValue * length) + f2;
        float min = Math.min((floatValue2 * length) + f2, (f3 + length) - 1.0f);
        float f4 = 0.0f;
        for (int size2 = pathGroup.f9360a.size() - 1; size2 >= 0; size2--) {
            this.f9345c.set(((PathContent) pathGroup.f9360a.get(size2)).d());
            this.f9345c.transform(matrix);
            this.f9343a.setPath(this.f9345c, false);
            float length2 = this.f9343a.getLength();
            if (min > length) {
                float f5 = min - length;
                if (f5 < f4 + length2 && f4 < f5) {
                    Utils.a(this.f9345c, f3 > length ? (f3 - length) / length2 : 0.0f, Math.min(f5 / length2, 1.0f), 0.0f);
                    canvas.drawPath(this.f9345c, this.f9351i);
                    f4 += length2;
                }
            }
            float f6 = f4 + length2;
            if (f6 >= f3 && f4 <= min) {
                if (f6 > min || f3 >= f4) {
                    Utils.a(this.f9345c, f3 < f4 ? 0.0f : (f3 - f4) / length2, min > f6 ? 1.0f : (min - f4) / length2, 0.0f);
                    canvas.drawPath(this.f9345c, this.f9351i);
                } else {
                    canvas.drawPath(this.f9345c, this.f9351i);
                }
            }
            f4 += length2;
        }
        L.c("StrokeContent#applyTrimPath");
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener
    public void a() {
        this.f9347e.invalidateSelf();
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public void b(List list, List list2) {
        TrimPathContent trimPathContent = null;
        for (int size = list.size() - 1; size >= 0; size--) {
            Content content = (Content) list.get(size);
            if (content instanceof TrimPathContent) {
                TrimPathContent trimPathContent2 = (TrimPathContent) content;
                if (trimPathContent2.k() == ShapeTrimPath.Type.INDIVIDUALLY) {
                    trimPathContent = trimPathContent2;
                }
            }
        }
        if (trimPathContent != null) {
            trimPathContent.e(this);
        }
        PathGroup pathGroup = null;
        for (int size2 = list2.size() - 1; size2 >= 0; size2--) {
            Content content2 = (Content) list2.get(size2);
            if (content2 instanceof TrimPathContent) {
                TrimPathContent trimPathContent3 = (TrimPathContent) content2;
                if (trimPathContent3.k() == ShapeTrimPath.Type.INDIVIDUALLY) {
                    if (pathGroup != null) {
                        this.f9349g.add(pathGroup);
                    }
                    pathGroup = new PathGroup(trimPathContent3);
                    trimPathContent3.e(this);
                }
            }
            if (content2 instanceof PathContent) {
                if (pathGroup == null) {
                    pathGroup = new PathGroup(trimPathContent);
                }
                pathGroup.f9360a.add((PathContent) content2);
            }
        }
        if (pathGroup != null) {
            this.f9349g.add(pathGroup);
        }
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    public void e(Object obj, LottieValueCallback lottieValueCallback) {
        DropShadowKeyframeAnimation dropShadowKeyframeAnimation;
        DropShadowKeyframeAnimation dropShadowKeyframeAnimation2;
        DropShadowKeyframeAnimation dropShadowKeyframeAnimation3;
        DropShadowKeyframeAnimation dropShadowKeyframeAnimation4;
        DropShadowKeyframeAnimation dropShadowKeyframeAnimation5;
        if (obj == LottieProperty.f9308d) {
            this.f9353k.o(lottieValueCallback);
            return;
        }
        if (obj == LottieProperty.f9323s) {
            this.f9352j.o(lottieValueCallback);
            return;
        }
        if (obj == LottieProperty.K) {
            BaseKeyframeAnimation baseKeyframeAnimation = this.f9356n;
            if (baseKeyframeAnimation != null) {
                this.f9348f.I(baseKeyframeAnimation);
            }
            if (lottieValueCallback == null) {
                this.f9356n = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            this.f9356n = valueCallbackKeyframeAnimation;
            valueCallbackKeyframeAnimation.a(this);
            this.f9348f.j(this.f9356n);
            return;
        }
        if (obj == LottieProperty.f9314j) {
            BaseKeyframeAnimation baseKeyframeAnimation2 = this.f9357o;
            if (baseKeyframeAnimation2 != null) {
                baseKeyframeAnimation2.o(lottieValueCallback);
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation2 = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            this.f9357o = valueCallbackKeyframeAnimation2;
            valueCallbackKeyframeAnimation2.a(this);
            this.f9348f.j(this.f9357o);
            return;
        }
        if (obj == LottieProperty.f9309e && (dropShadowKeyframeAnimation5 = this.f9359q) != null) {
            dropShadowKeyframeAnimation5.c(lottieValueCallback);
            return;
        }
        if (obj == LottieProperty.G && (dropShadowKeyframeAnimation4 = this.f9359q) != null) {
            dropShadowKeyframeAnimation4.f(lottieValueCallback);
            return;
        }
        if (obj == LottieProperty.H && (dropShadowKeyframeAnimation3 = this.f9359q) != null) {
            dropShadowKeyframeAnimation3.d(lottieValueCallback);
            return;
        }
        if (obj == LottieProperty.I && (dropShadowKeyframeAnimation2 = this.f9359q) != null) {
            dropShadowKeyframeAnimation2.e(lottieValueCallback);
        } else {
            if (obj != LottieProperty.J || (dropShadowKeyframeAnimation = this.f9359q) == null) {
                return;
            }
            dropShadowKeyframeAnimation.g(lottieValueCallback);
        }
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    public void f(KeyPath keyPath, int i2, List list, KeyPath keyPath2) {
        MiscUtils.k(keyPath, i2, list, keyPath2, this);
    }

    @Override // com.airbnb.lottie.animation.content.DrawingContent
    public void g(RectF rectF, Matrix matrix, boolean z) {
        L.b("StrokeContent#getBounds");
        this.f9344b.reset();
        for (int i2 = 0; i2 < this.f9349g.size(); i2++) {
            PathGroup pathGroup = (PathGroup) this.f9349g.get(i2);
            for (int i3 = 0; i3 < pathGroup.f9360a.size(); i3++) {
                this.f9344b.addPath(((PathContent) pathGroup.f9360a.get(i3)).d(), matrix);
            }
        }
        this.f9344b.computeBounds(this.f9346d, false);
        float q2 = ((FloatKeyframeAnimation) this.f9352j).q();
        RectF rectF2 = this.f9346d;
        float f2 = q2 / 2.0f;
        rectF2.set(rectF2.left - f2, rectF2.top - f2, rectF2.right + f2, rectF2.bottom + f2);
        rectF.set(this.f9346d);
        rectF.set(rectF.left - 1.0f, rectF.top - 1.0f, rectF.right + 1.0f, rectF.bottom + 1.0f);
        L.c("StrokeContent#getBounds");
    }

    @Override // com.airbnb.lottie.animation.content.DrawingContent
    public void i(Canvas canvas, Matrix matrix, int i2) {
        L.b("StrokeContent#draw");
        if (Utils.h(matrix)) {
            L.c("StrokeContent#draw");
            return;
        }
        this.f9351i.setAlpha(MiscUtils.c((int) ((((i2 / 255.0f) * ((IntegerKeyframeAnimation) this.f9353k).q()) / 100.0f) * 255.0f), 0, 255));
        this.f9351i.setStrokeWidth(((FloatKeyframeAnimation) this.f9352j).q() * Utils.g(matrix));
        if (this.f9351i.getStrokeWidth() <= 0.0f) {
            L.c("StrokeContent#draw");
            return;
        }
        h(matrix);
        BaseKeyframeAnimation baseKeyframeAnimation = this.f9356n;
        if (baseKeyframeAnimation != null) {
            this.f9351i.setColorFilter((ColorFilter) baseKeyframeAnimation.h());
        }
        BaseKeyframeAnimation baseKeyframeAnimation2 = this.f9357o;
        if (baseKeyframeAnimation2 != null) {
            float floatValue = ((Float) baseKeyframeAnimation2.h()).floatValue();
            if (floatValue == 0.0f) {
                this.f9351i.setMaskFilter(null);
            } else if (floatValue != this.f9358p) {
                this.f9351i.setMaskFilter(this.f9348f.y(floatValue));
            }
            this.f9358p = floatValue;
        }
        DropShadowKeyframeAnimation dropShadowKeyframeAnimation = this.f9359q;
        if (dropShadowKeyframeAnimation != null) {
            dropShadowKeyframeAnimation.b(this.f9351i);
        }
        for (int i3 = 0; i3 < this.f9349g.size(); i3++) {
            PathGroup pathGroup = (PathGroup) this.f9349g.get(i3);
            if (pathGroup.f9361b != null) {
                j(canvas, pathGroup, matrix);
            } else {
                L.b("StrokeContent#buildPath");
                this.f9344b.reset();
                for (int size = pathGroup.f9360a.size() - 1; size >= 0; size--) {
                    this.f9344b.addPath(((PathContent) pathGroup.f9360a.get(size)).d(), matrix);
                }
                L.c("StrokeContent#buildPath");
                L.b("StrokeContent#drawPath");
                canvas.drawPath(this.f9344b, this.f9351i);
                L.c("StrokeContent#drawPath");
            }
        }
        L.c("StrokeContent#draw");
    }
}
