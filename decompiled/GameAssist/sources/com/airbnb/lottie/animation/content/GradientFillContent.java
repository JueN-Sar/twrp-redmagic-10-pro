package com.airbnb.lottie.animation.content;

import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import androidx.collection.LongSparseArray;
import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.LPaint;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.DropShadowKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.content.GradientColor;
import com.airbnb.lottie.model.content.GradientFill;
import com.airbnb.lottie.model.content.GradientType;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class GradientFillContent implements DrawingContent, BaseKeyframeAnimation.AnimationListener, KeyPathElementContent {

    /* renamed from: a, reason: collision with root package name */
    private final String f9395a;

    /* renamed from: b, reason: collision with root package name */
    private final boolean f9396b;

    /* renamed from: c, reason: collision with root package name */
    private final BaseLayer f9397c;

    /* renamed from: d, reason: collision with root package name */
    private final LongSparseArray f9398d = new LongSparseArray();

    /* renamed from: e, reason: collision with root package name */
    private final LongSparseArray f9399e = new LongSparseArray();

    /* renamed from: f, reason: collision with root package name */
    private final Path f9400f;

    /* renamed from: g, reason: collision with root package name */
    private final Paint f9401g;

    /* renamed from: h, reason: collision with root package name */
    private final RectF f9402h;

    /* renamed from: i, reason: collision with root package name */
    private final List f9403i;

    /* renamed from: j, reason: collision with root package name */
    private final GradientType f9404j;

    /* renamed from: k, reason: collision with root package name */
    private final BaseKeyframeAnimation f9405k;

    /* renamed from: l, reason: collision with root package name */
    private final BaseKeyframeAnimation f9406l;

    /* renamed from: m, reason: collision with root package name */
    private final BaseKeyframeAnimation f9407m;

    /* renamed from: n, reason: collision with root package name */
    private final BaseKeyframeAnimation f9408n;

    /* renamed from: o, reason: collision with root package name */
    private BaseKeyframeAnimation f9409o;

    /* renamed from: p, reason: collision with root package name */
    private ValueCallbackKeyframeAnimation f9410p;

    /* renamed from: q, reason: collision with root package name */
    private final LottieDrawable f9411q;

    /* renamed from: r, reason: collision with root package name */
    private final int f9412r;

    /* renamed from: s, reason: collision with root package name */
    private BaseKeyframeAnimation f9413s;
    float t;
    private DropShadowKeyframeAnimation u;

    public GradientFillContent(LottieDrawable lottieDrawable, LottieComposition lottieComposition, BaseLayer baseLayer, GradientFill gradientFill) {
        Path path = new Path();
        this.f9400f = path;
        this.f9401g = new LPaint(1);
        this.f9402h = new RectF();
        this.f9403i = new ArrayList();
        this.t = 0.0f;
        this.f9397c = baseLayer;
        this.f9395a = gradientFill.f();
        this.f9396b = gradientFill.i();
        this.f9411q = lottieDrawable;
        this.f9404j = gradientFill.e();
        path.setFillType(gradientFill.c());
        this.f9412r = (int) (lottieComposition.d() / 32.0f);
        BaseKeyframeAnimation a2 = gradientFill.d().a();
        this.f9405k = a2;
        a2.a(this);
        baseLayer.j(a2);
        BaseKeyframeAnimation a3 = gradientFill.g().a();
        this.f9406l = a3;
        a3.a(this);
        baseLayer.j(a3);
        BaseKeyframeAnimation a4 = gradientFill.h().a();
        this.f9407m = a4;
        a4.a(this);
        baseLayer.j(a4);
        BaseKeyframeAnimation a5 = gradientFill.b().a();
        this.f9408n = a5;
        a5.a(this);
        baseLayer.j(a5);
        if (baseLayer.x() != null) {
            BaseKeyframeAnimation a6 = baseLayer.x().a().a();
            this.f9413s = a6;
            a6.a(this);
            baseLayer.j(this.f9413s);
        }
        if (baseLayer.z() != null) {
            this.u = new DropShadowKeyframeAnimation(this, baseLayer, baseLayer.z());
        }
    }

    private int[] h(int[] iArr) {
        ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation = this.f9410p;
        if (valueCallbackKeyframeAnimation != null) {
            Integer[] numArr = (Integer[]) valueCallbackKeyframeAnimation.h();
            int i2 = 0;
            if (iArr.length == numArr.length) {
                while (i2 < iArr.length) {
                    iArr[i2] = numArr[i2].intValue();
                    i2++;
                }
            } else {
                iArr = new int[numArr.length];
                while (i2 < numArr.length) {
                    iArr[i2] = numArr[i2].intValue();
                    i2++;
                }
            }
        }
        return iArr;
    }

    private int j() {
        int round = Math.round(this.f9407m.f() * this.f9412r);
        int round2 = Math.round(this.f9408n.f() * this.f9412r);
        int round3 = Math.round(this.f9405k.f() * this.f9412r);
        int i2 = round != 0 ? 527 * round : 17;
        if (round2 != 0) {
            i2 = i2 * 31 * round2;
        }
        return round3 != 0 ? i2 * 31 * round3 : i2;
    }

    private LinearGradient k() {
        long j2 = j();
        LinearGradient linearGradient = (LinearGradient) this.f9398d.f(j2);
        if (linearGradient != null) {
            return linearGradient;
        }
        PointF pointF = (PointF) this.f9407m.h();
        PointF pointF2 = (PointF) this.f9408n.h();
        GradientColor gradientColor = (GradientColor) this.f9405k.h();
        LinearGradient linearGradient2 = new LinearGradient(pointF.x, pointF.y, pointF2.x, pointF2.y, h(gradientColor.d()), gradientColor.e(), Shader.TileMode.CLAMP);
        this.f9398d.k(j2, linearGradient2);
        return linearGradient2;
    }

    private RadialGradient l() {
        long j2 = j();
        RadialGradient radialGradient = (RadialGradient) this.f9399e.f(j2);
        if (radialGradient != null) {
            return radialGradient;
        }
        PointF pointF = (PointF) this.f9407m.h();
        PointF pointF2 = (PointF) this.f9408n.h();
        GradientColor gradientColor = (GradientColor) this.f9405k.h();
        int[] h2 = h(gradientColor.d());
        float[] e2 = gradientColor.e();
        float f2 = pointF.x;
        float f3 = pointF.y;
        float hypot = (float) Math.hypot(pointF2.x - f2, pointF2.y - f3);
        if (hypot <= 0.0f) {
            hypot = 0.001f;
        }
        RadialGradient radialGradient2 = new RadialGradient(f2, f3, hypot, h2, e2, Shader.TileMode.CLAMP);
        this.f9399e.k(j2, radialGradient2);
        return radialGradient2;
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener
    public void a() {
        this.f9411q.invalidateSelf();
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public void b(List list, List list2) {
        for (int i2 = 0; i2 < list2.size(); i2++) {
            Content content = (Content) list2.get(i2);
            if (content instanceof PathContent) {
                this.f9403i.add((PathContent) content);
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
        if (obj == LottieProperty.f9308d) {
            this.f9406l.o(lottieValueCallback);
            return;
        }
        if (obj == LottieProperty.K) {
            BaseKeyframeAnimation baseKeyframeAnimation = this.f9409o;
            if (baseKeyframeAnimation != null) {
                this.f9397c.I(baseKeyframeAnimation);
            }
            if (lottieValueCallback == null) {
                this.f9409o = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            this.f9409o = valueCallbackKeyframeAnimation;
            valueCallbackKeyframeAnimation.a(this);
            this.f9397c.j(this.f9409o);
            return;
        }
        if (obj == LottieProperty.L) {
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation2 = this.f9410p;
            if (valueCallbackKeyframeAnimation2 != null) {
                this.f9397c.I(valueCallbackKeyframeAnimation2);
            }
            if (lottieValueCallback == null) {
                this.f9410p = null;
                return;
            }
            this.f9398d.b();
            this.f9399e.b();
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation3 = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            this.f9410p = valueCallbackKeyframeAnimation3;
            valueCallbackKeyframeAnimation3.a(this);
            this.f9397c.j(this.f9410p);
            return;
        }
        if (obj == LottieProperty.f9314j) {
            BaseKeyframeAnimation baseKeyframeAnimation2 = this.f9413s;
            if (baseKeyframeAnimation2 != null) {
                baseKeyframeAnimation2.o(lottieValueCallback);
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation4 = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            this.f9413s = valueCallbackKeyframeAnimation4;
            valueCallbackKeyframeAnimation4.a(this);
            this.f9397c.j(this.f9413s);
            return;
        }
        if (obj == LottieProperty.f9309e && (dropShadowKeyframeAnimation5 = this.u) != null) {
            dropShadowKeyframeAnimation5.c(lottieValueCallback);
            return;
        }
        if (obj == LottieProperty.G && (dropShadowKeyframeAnimation4 = this.u) != null) {
            dropShadowKeyframeAnimation4.f(lottieValueCallback);
            return;
        }
        if (obj == LottieProperty.H && (dropShadowKeyframeAnimation3 = this.u) != null) {
            dropShadowKeyframeAnimation3.d(lottieValueCallback);
            return;
        }
        if (obj == LottieProperty.I && (dropShadowKeyframeAnimation2 = this.u) != null) {
            dropShadowKeyframeAnimation2.e(lottieValueCallback);
        } else {
            if (obj != LottieProperty.J || (dropShadowKeyframeAnimation = this.u) == null) {
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
        this.f9400f.reset();
        for (int i2 = 0; i2 < this.f9403i.size(); i2++) {
            this.f9400f.addPath(((PathContent) this.f9403i.get(i2)).d(), matrix);
        }
        this.f9400f.computeBounds(rectF, false);
        rectF.set(rectF.left - 1.0f, rectF.top - 1.0f, rectF.right + 1.0f, rectF.bottom + 1.0f);
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public String getName() {
        return this.f9395a;
    }

    @Override // com.airbnb.lottie.animation.content.DrawingContent
    public void i(Canvas canvas, Matrix matrix, int i2) {
        if (this.f9396b) {
            return;
        }
        L.b("GradientFillContent#draw");
        this.f9400f.reset();
        for (int i3 = 0; i3 < this.f9403i.size(); i3++) {
            this.f9400f.addPath(((PathContent) this.f9403i.get(i3)).d(), matrix);
        }
        this.f9400f.computeBounds(this.f9402h, false);
        Shader k2 = this.f9404j == GradientType.LINEAR ? k() : l();
        k2.setLocalMatrix(matrix);
        this.f9401g.setShader(k2);
        BaseKeyframeAnimation baseKeyframeAnimation = this.f9409o;
        if (baseKeyframeAnimation != null) {
            this.f9401g.setColorFilter((ColorFilter) baseKeyframeAnimation.h());
        }
        BaseKeyframeAnimation baseKeyframeAnimation2 = this.f9413s;
        if (baseKeyframeAnimation2 != null) {
            float floatValue = ((Float) baseKeyframeAnimation2.h()).floatValue();
            if (floatValue == 0.0f) {
                this.f9401g.setMaskFilter(null);
            } else if (floatValue != this.t) {
                this.f9401g.setMaskFilter(new BlurMaskFilter(floatValue, BlurMaskFilter.Blur.NORMAL));
            }
            this.t = floatValue;
        }
        DropShadowKeyframeAnimation dropShadowKeyframeAnimation = this.u;
        if (dropShadowKeyframeAnimation != null) {
            dropShadowKeyframeAnimation.b(this.f9401g);
        }
        this.f9401g.setAlpha(MiscUtils.c((int) ((((i2 / 255.0f) * ((Integer) this.f9406l.h()).intValue()) / 100.0f) * 255.0f), 0, 255));
        canvas.drawPath(this.f9400f, this.f9401g);
        L.c("GradientFillContent#draw");
    }
}
