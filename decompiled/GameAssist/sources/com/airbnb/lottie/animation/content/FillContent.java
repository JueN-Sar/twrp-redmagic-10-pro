package com.airbnb.lottie.animation.content;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import androidx.core.graphics.PaintCompat;
import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.LPaint;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ColorKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.DropShadowKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.content.ShapeFill;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class FillContent implements DrawingContent, BaseKeyframeAnimation.AnimationListener, KeyPathElementContent {

    /* renamed from: a, reason: collision with root package name */
    private final Path f9382a;

    /* renamed from: b, reason: collision with root package name */
    private final Paint f9383b;

    /* renamed from: c, reason: collision with root package name */
    private final BaseLayer f9384c;

    /* renamed from: d, reason: collision with root package name */
    private final String f9385d;

    /* renamed from: e, reason: collision with root package name */
    private final boolean f9386e;

    /* renamed from: f, reason: collision with root package name */
    private final List f9387f;

    /* renamed from: g, reason: collision with root package name */
    private final BaseKeyframeAnimation f9388g;

    /* renamed from: h, reason: collision with root package name */
    private final BaseKeyframeAnimation f9389h;

    /* renamed from: i, reason: collision with root package name */
    private BaseKeyframeAnimation f9390i;

    /* renamed from: j, reason: collision with root package name */
    private final LottieDrawable f9391j;

    /* renamed from: k, reason: collision with root package name */
    private BaseKeyframeAnimation f9392k;

    /* renamed from: l, reason: collision with root package name */
    float f9393l;

    /* renamed from: m, reason: collision with root package name */
    private DropShadowKeyframeAnimation f9394m;

    public FillContent(LottieDrawable lottieDrawable, BaseLayer baseLayer, ShapeFill shapeFill) {
        Path path = new Path();
        this.f9382a = path;
        LPaint lPaint = new LPaint(1);
        this.f9383b = lPaint;
        this.f9387f = new ArrayList();
        this.f9384c = baseLayer;
        this.f9385d = shapeFill.d();
        this.f9386e = shapeFill.f();
        this.f9391j = lottieDrawable;
        if (baseLayer.x() != null) {
            BaseKeyframeAnimation a2 = baseLayer.x().a().a();
            this.f9392k = a2;
            a2.a(this);
            baseLayer.j(this.f9392k);
        }
        if (baseLayer.z() != null) {
            this.f9394m = new DropShadowKeyframeAnimation(this, baseLayer, baseLayer.z());
        }
        if (shapeFill.b() == null || shapeFill.e() == null) {
            this.f9388g = null;
            this.f9389h = null;
            return;
        }
        PaintCompat.b(lPaint, baseLayer.w().d());
        path.setFillType(shapeFill.c());
        BaseKeyframeAnimation a3 = shapeFill.b().a();
        this.f9388g = a3;
        a3.a(this);
        baseLayer.j(a3);
        BaseKeyframeAnimation a4 = shapeFill.e().a();
        this.f9389h = a4;
        a4.a(this);
        baseLayer.j(a4);
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener
    public void a() {
        this.f9391j.invalidateSelf();
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public void b(List list, List list2) {
        for (int i2 = 0; i2 < list2.size(); i2++) {
            Content content = (Content) list2.get(i2);
            if (content instanceof PathContent) {
                this.f9387f.add((PathContent) content);
            }
        }
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    public void e(Object obj, LottieValueCallback lottieValueCallback) {
        DropShadowKeyframeAnimation dropShadowKeyframeAnimation;
        DropShadowKeyframeAnimation dropShadowKeyframeAnimation2;
        DropShadowKeyframeAnimation dropShadowKeyframeAnimation3;
        DropShadowKeyframeAnimation dropShadowKeyframeAnimation4;
        DropShadowKeyframeAnimation dropShadowKeyframeAnimation5;
        if (obj == LottieProperty.f9305a) {
            this.f9388g.o(lottieValueCallback);
            return;
        }
        if (obj == LottieProperty.f9308d) {
            this.f9389h.o(lottieValueCallback);
            return;
        }
        if (obj == LottieProperty.K) {
            BaseKeyframeAnimation baseKeyframeAnimation = this.f9390i;
            if (baseKeyframeAnimation != null) {
                this.f9384c.I(baseKeyframeAnimation);
            }
            if (lottieValueCallback == null) {
                this.f9390i = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            this.f9390i = valueCallbackKeyframeAnimation;
            valueCallbackKeyframeAnimation.a(this);
            this.f9384c.j(this.f9390i);
            return;
        }
        if (obj == LottieProperty.f9314j) {
            BaseKeyframeAnimation baseKeyframeAnimation2 = this.f9392k;
            if (baseKeyframeAnimation2 != null) {
                baseKeyframeAnimation2.o(lottieValueCallback);
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation2 = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            this.f9392k = valueCallbackKeyframeAnimation2;
            valueCallbackKeyframeAnimation2.a(this);
            this.f9384c.j(this.f9392k);
            return;
        }
        if (obj == LottieProperty.f9309e && (dropShadowKeyframeAnimation5 = this.f9394m) != null) {
            dropShadowKeyframeAnimation5.c(lottieValueCallback);
            return;
        }
        if (obj == LottieProperty.G && (dropShadowKeyframeAnimation4 = this.f9394m) != null) {
            dropShadowKeyframeAnimation4.f(lottieValueCallback);
            return;
        }
        if (obj == LottieProperty.H && (dropShadowKeyframeAnimation3 = this.f9394m) != null) {
            dropShadowKeyframeAnimation3.d(lottieValueCallback);
            return;
        }
        if (obj == LottieProperty.I && (dropShadowKeyframeAnimation2 = this.f9394m) != null) {
            dropShadowKeyframeAnimation2.e(lottieValueCallback);
        } else {
            if (obj != LottieProperty.J || (dropShadowKeyframeAnimation = this.f9394m) == null) {
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
        this.f9382a.reset();
        for (int i2 = 0; i2 < this.f9387f.size(); i2++) {
            this.f9382a.addPath(((PathContent) this.f9387f.get(i2)).d(), matrix);
        }
        this.f9382a.computeBounds(rectF, false);
        rectF.set(rectF.left - 1.0f, rectF.top - 1.0f, rectF.right + 1.0f, rectF.bottom + 1.0f);
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public String getName() {
        return this.f9385d;
    }

    @Override // com.airbnb.lottie.animation.content.DrawingContent
    public void i(Canvas canvas, Matrix matrix, int i2) {
        if (this.f9386e) {
            return;
        }
        L.b("FillContent#draw");
        this.f9383b.setColor((MiscUtils.c((int) ((((i2 / 255.0f) * ((Integer) this.f9389h.h()).intValue()) / 100.0f) * 255.0f), 0, 255) << 24) | (((ColorKeyframeAnimation) this.f9388g).q() & 16777215));
        BaseKeyframeAnimation baseKeyframeAnimation = this.f9390i;
        if (baseKeyframeAnimation != null) {
            this.f9383b.setColorFilter((ColorFilter) baseKeyframeAnimation.h());
        }
        BaseKeyframeAnimation baseKeyframeAnimation2 = this.f9392k;
        if (baseKeyframeAnimation2 != null) {
            float floatValue = ((Float) baseKeyframeAnimation2.h()).floatValue();
            if (floatValue == 0.0f) {
                this.f9383b.setMaskFilter(null);
            } else if (floatValue != this.f9393l) {
                this.f9383b.setMaskFilter(this.f9384c.y(floatValue));
            }
            this.f9393l = floatValue;
        }
        DropShadowKeyframeAnimation dropShadowKeyframeAnimation = this.f9394m;
        if (dropShadowKeyframeAnimation != null) {
            dropShadowKeyframeAnimation.b(this.f9383b);
        }
        this.f9382a.reset();
        for (int i3 = 0; i3 < this.f9387f.size(); i3++) {
            this.f9382a.addPath(((PathContent) this.f9387f.get(i3)).d(), matrix);
        }
        canvas.drawPath(this.f9382a, this.f9383b);
        L.c("FillContent#draw");
    }
}
