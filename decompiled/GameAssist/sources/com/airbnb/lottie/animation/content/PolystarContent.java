package com.airbnb.lottie.animation.content;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.content.PolystarShape;
import com.airbnb.lottie.model.content.ShapeTrimPath;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.List;

/* loaded from: classes.dex */
public class PolystarContent implements PathContent, BaseKeyframeAnimation.AnimationListener, KeyPathElementContent {

    /* renamed from: e, reason: collision with root package name */
    private final String f9427e;

    /* renamed from: f, reason: collision with root package name */
    private final LottieDrawable f9428f;

    /* renamed from: g, reason: collision with root package name */
    private final PolystarShape.Type f9429g;

    /* renamed from: h, reason: collision with root package name */
    private final boolean f9430h;

    /* renamed from: i, reason: collision with root package name */
    private final boolean f9431i;

    /* renamed from: j, reason: collision with root package name */
    private final BaseKeyframeAnimation f9432j;

    /* renamed from: k, reason: collision with root package name */
    private final BaseKeyframeAnimation f9433k;

    /* renamed from: l, reason: collision with root package name */
    private final BaseKeyframeAnimation f9434l;

    /* renamed from: m, reason: collision with root package name */
    private final BaseKeyframeAnimation f9435m;

    /* renamed from: n, reason: collision with root package name */
    private final BaseKeyframeAnimation f9436n;

    /* renamed from: o, reason: collision with root package name */
    private final BaseKeyframeAnimation f9437o;

    /* renamed from: p, reason: collision with root package name */
    private final BaseKeyframeAnimation f9438p;

    /* renamed from: r, reason: collision with root package name */
    private boolean f9440r;

    /* renamed from: a, reason: collision with root package name */
    private final Path f9423a = new Path();

    /* renamed from: b, reason: collision with root package name */
    private final Path f9424b = new Path();

    /* renamed from: c, reason: collision with root package name */
    private final PathMeasure f9425c = new PathMeasure();

    /* renamed from: d, reason: collision with root package name */
    private final float[] f9426d = new float[2];

    /* renamed from: q, reason: collision with root package name */
    private final CompoundTrimPathContent f9439q = new CompoundTrimPathContent();

    /* renamed from: com.airbnb.lottie.animation.content.PolystarContent$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f9441a;

        static {
            int[] iArr = new int[PolystarShape.Type.values().length];
            f9441a = iArr;
            try {
                iArr[PolystarShape.Type.STAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9441a[PolystarShape.Type.POLYGON.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public PolystarContent(LottieDrawable lottieDrawable, BaseLayer baseLayer, PolystarShape polystarShape) {
        this.f9428f = lottieDrawable;
        this.f9427e = polystarShape.d();
        PolystarShape.Type j2 = polystarShape.j();
        this.f9429g = j2;
        this.f9430h = polystarShape.k();
        this.f9431i = polystarShape.l();
        BaseKeyframeAnimation a2 = polystarShape.g().a();
        this.f9432j = a2;
        BaseKeyframeAnimation a3 = polystarShape.h().a();
        this.f9433k = a3;
        BaseKeyframeAnimation a4 = polystarShape.i().a();
        this.f9434l = a4;
        BaseKeyframeAnimation a5 = polystarShape.e().a();
        this.f9436n = a5;
        BaseKeyframeAnimation a6 = polystarShape.f().a();
        this.f9438p = a6;
        PolystarShape.Type type = PolystarShape.Type.STAR;
        if (j2 == type) {
            this.f9435m = polystarShape.b().a();
            this.f9437o = polystarShape.c().a();
        } else {
            this.f9435m = null;
            this.f9437o = null;
        }
        baseLayer.j(a2);
        baseLayer.j(a3);
        baseLayer.j(a4);
        baseLayer.j(a5);
        baseLayer.j(a6);
        if (j2 == type) {
            baseLayer.j(this.f9435m);
            baseLayer.j(this.f9437o);
        }
        a2.a(this);
        a3.a(this);
        a4.a(this);
        a5.a(this);
        a6.a(this);
        if (j2 == type) {
            this.f9435m.a(this);
            this.f9437o.a(this);
        }
    }

    private void h() {
        double d2;
        float f2;
        PolystarContent polystarContent;
        PolystarContent polystarContent2 = this;
        int floor = (int) Math.floor(((Float) polystarContent2.f9432j.h()).floatValue());
        double radians = Math.toRadians((polystarContent2.f9434l == null ? 0.0d : ((Float) r2.h()).floatValue()) - 90.0d);
        double d3 = floor;
        float floatValue = ((Float) polystarContent2.f9438p.h()).floatValue() / 100.0f;
        float floatValue2 = ((Float) polystarContent2.f9436n.h()).floatValue();
        double d4 = floatValue2;
        float cos = (float) (Math.cos(radians) * d4);
        float sin = (float) (Math.sin(radians) * d4);
        polystarContent2.f9423a.moveTo(cos, sin);
        double d5 = (float) (6.283185307179586d / d3);
        double ceil = Math.ceil(d3);
        double d6 = radians + d5;
        int i2 = 0;
        while (true) {
            double d7 = i2;
            if (d7 >= ceil) {
                PolystarContent polystarContent3 = polystarContent2;
                PointF pointF = (PointF) polystarContent3.f9433k.h();
                polystarContent3.f9423a.offset(pointF.x, pointF.y);
                polystarContent3.f9423a.close();
                return;
            }
            int i3 = i2;
            float cos2 = (float) (d4 * Math.cos(d6));
            double d8 = d5;
            float sin2 = (float) (d4 * Math.sin(d6));
            if (floatValue != 0.0f) {
                d2 = d4;
                double atan2 = (float) (Math.atan2(sin, cos) - 1.5707963267948966d);
                float cos3 = (float) Math.cos(atan2);
                float sin3 = (float) Math.sin(atan2);
                f2 = sin2;
                double atan22 = (float) (Math.atan2(sin2, cos2) - 1.5707963267948966d);
                float f3 = floatValue2 * floatValue * 0.25f;
                float f4 = cos3 * f3;
                float f5 = sin3 * f3;
                float cos4 = ((float) Math.cos(atan22)) * f3;
                float sin4 = f3 * ((float) Math.sin(atan22));
                if (d7 == ceil - 1.0d) {
                    polystarContent = this;
                    polystarContent.f9424b.reset();
                    polystarContent.f9424b.moveTo(cos, sin);
                    float f6 = cos - f4;
                    float f7 = sin - f5;
                    float f8 = cos2 + cos4;
                    float f9 = sin4 + f2;
                    polystarContent.f9424b.cubicTo(f6, f7, f8, f9, cos2, f2);
                    polystarContent.f9425c.setPath(polystarContent.f9424b, false);
                    PathMeasure pathMeasure = polystarContent.f9425c;
                    pathMeasure.getPosTan(pathMeasure.getLength() * 0.9999f, polystarContent.f9426d, null);
                    Path path = polystarContent.f9423a;
                    float[] fArr = polystarContent.f9426d;
                    path.cubicTo(f6, f7, f8, f9, fArr[0], fArr[1]);
                } else {
                    polystarContent = this;
                    polystarContent.f9423a.cubicTo(cos - f4, sin - f5, cos2 + cos4, f2 + sin4, cos2, f2);
                }
            } else {
                d2 = d4;
                f2 = sin2;
                polystarContent = polystarContent2;
                if (d7 == ceil - 1.0d) {
                    sin = f2;
                    cos = cos2;
                    d5 = d8;
                    i2 = i3 + 1;
                    polystarContent2 = polystarContent;
                    d4 = d2;
                } else {
                    polystarContent.f9423a.lineTo(cos2, f2);
                }
            }
            d6 += d8;
            sin = f2;
            cos = cos2;
            d5 = d8;
            i2 = i3 + 1;
            polystarContent2 = polystarContent;
            d4 = d2;
        }
    }

    private void j() {
        int i2;
        float f2;
        float f3;
        double d2;
        float f4;
        float f5;
        float f6;
        float f7;
        double d3;
        float f8;
        float f9;
        float f10;
        double d4;
        float floatValue = ((Float) this.f9432j.h()).floatValue();
        double radians = Math.toRadians((this.f9434l == null ? 0.0d : ((Float) r2.h()).floatValue()) - 90.0d);
        double d5 = floatValue;
        float f11 = (float) (6.283185307179586d / d5);
        if (this.f9431i) {
            f11 *= -1.0f;
        }
        float f12 = f11 / 2.0f;
        float f13 = floatValue - ((int) floatValue);
        int i3 = (f13 > 0.0f ? 1 : (f13 == 0.0f ? 0 : -1));
        if (i3 != 0) {
            radians += (1.0f - f13) * f12;
        }
        float floatValue2 = ((Float) this.f9436n.h()).floatValue();
        float floatValue3 = ((Float) this.f9435m.h()).floatValue();
        BaseKeyframeAnimation baseKeyframeAnimation = this.f9437o;
        float floatValue4 = baseKeyframeAnimation != null ? ((Float) baseKeyframeAnimation.h()).floatValue() / 100.0f : 0.0f;
        BaseKeyframeAnimation baseKeyframeAnimation2 = this.f9438p;
        float floatValue5 = baseKeyframeAnimation2 != null ? ((Float) baseKeyframeAnimation2.h()).floatValue() / 100.0f : 0.0f;
        if (i3 != 0) {
            f5 = ((floatValue2 - floatValue3) * f13) + floatValue3;
            i2 = i3;
            double d6 = f5;
            float cos = (float) (d6 * Math.cos(radians));
            f4 = (float) (d6 * Math.sin(radians));
            this.f9423a.moveTo(cos, f4);
            d2 = radians + ((f11 * f13) / 2.0f);
            f2 = cos;
            f3 = f12;
        } else {
            i2 = i3;
            double d7 = floatValue2;
            float cos2 = (float) (Math.cos(radians) * d7);
            float sin = (float) (d7 * Math.sin(radians));
            this.f9423a.moveTo(cos2, sin);
            f2 = cos2;
            f3 = f12;
            d2 = radians + f3;
            f4 = sin;
            f5 = 0.0f;
        }
        double ceil = Math.ceil(d5) * 2.0d;
        int i4 = 0;
        float f14 = f3;
        float f15 = f2;
        boolean z = false;
        while (true) {
            double d8 = i4;
            if (d8 >= ceil) {
                PointF pointF = (PointF) this.f9433k.h();
                this.f9423a.offset(pointF.x, pointF.y);
                this.f9423a.close();
                return;
            }
            float f16 = z ? floatValue2 : floatValue3;
            if (f5 == 0.0f || d8 != ceil - 2.0d) {
                f6 = f11;
                f7 = f14;
            } else {
                f6 = f11;
                f7 = (f11 * f13) / 2.0f;
            }
            if (f5 == 0.0f || d8 != ceil - 1.0d) {
                d3 = d8;
                f8 = f5;
                f5 = f16;
            } else {
                d3 = d8;
                f8 = f5;
            }
            double d9 = f5;
            double d10 = ceil;
            float cos3 = (float) (d9 * Math.cos(d2));
            float sin2 = (float) (d9 * Math.sin(d2));
            if (floatValue4 == 0.0f && floatValue5 == 0.0f) {
                this.f9423a.lineTo(cos3, sin2);
                d4 = d2;
                f9 = floatValue4;
                f10 = floatValue5;
            } else {
                f9 = floatValue4;
                double atan2 = (float) (Math.atan2(f4, f15) - 1.5707963267948966d);
                float cos4 = (float) Math.cos(atan2);
                float sin3 = (float) Math.sin(atan2);
                f10 = floatValue5;
                d4 = d2;
                double atan22 = (float) (Math.atan2(sin2, cos3) - 1.5707963267948966d);
                float cos5 = (float) Math.cos(atan22);
                float sin4 = (float) Math.sin(atan22);
                float f17 = z ? f9 : f10;
                float f18 = z ? f10 : f9;
                float f19 = (z ? floatValue3 : floatValue2) * f17 * 0.47829f;
                float f20 = cos4 * f19;
                float f21 = f19 * sin3;
                float f22 = (z ? floatValue2 : floatValue3) * f18 * 0.47829f;
                float f23 = cos5 * f22;
                float f24 = f22 * sin4;
                if (i2 != 0) {
                    if (i4 == 0) {
                        f20 *= f13;
                        f21 *= f13;
                    } else if (d3 == d10 - 1.0d) {
                        f23 *= f13;
                        f24 *= f13;
                    }
                }
                this.f9423a.cubicTo(f15 - f20, f4 - f21, cos3 + f23, sin2 + f24, cos3, sin2);
            }
            d2 = d4 + f7;
            z = !z;
            i4++;
            f15 = cos3;
            f4 = sin2;
            floatValue5 = f10;
            floatValue4 = f9;
            f5 = f8;
            f11 = f6;
            ceil = d10;
        }
    }

    private void k() {
        this.f9440r = false;
        this.f9428f.invalidateSelf();
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener
    public void a() {
        k();
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public void b(List list, List list2) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            Content content = (Content) list.get(i2);
            if (content instanceof TrimPathContent) {
                TrimPathContent trimPathContent = (TrimPathContent) content;
                if (trimPathContent.k() == ShapeTrimPath.Type.SIMULTANEOUSLY) {
                    this.f9439q.a(trimPathContent);
                    trimPathContent.e(this);
                }
            }
        }
    }

    @Override // com.airbnb.lottie.animation.content.PathContent
    public Path d() {
        if (this.f9440r) {
            return this.f9423a;
        }
        this.f9423a.reset();
        if (this.f9430h) {
            this.f9440r = true;
            return this.f9423a;
        }
        int i2 = AnonymousClass1.f9441a[this.f9429g.ordinal()];
        if (i2 == 1) {
            j();
        } else if (i2 == 2) {
            h();
        }
        this.f9423a.close();
        this.f9439q.b(this.f9423a);
        this.f9440r = true;
        return this.f9423a;
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    public void e(Object obj, LottieValueCallback lottieValueCallback) {
        BaseKeyframeAnimation baseKeyframeAnimation;
        BaseKeyframeAnimation baseKeyframeAnimation2;
        if (obj == LottieProperty.w) {
            this.f9432j.o(lottieValueCallback);
            return;
        }
        if (obj == LottieProperty.x) {
            this.f9434l.o(lottieValueCallback);
            return;
        }
        if (obj == LottieProperty.f9318n) {
            this.f9433k.o(lottieValueCallback);
            return;
        }
        if (obj == LottieProperty.y && (baseKeyframeAnimation2 = this.f9435m) != null) {
            baseKeyframeAnimation2.o(lottieValueCallback);
            return;
        }
        if (obj == LottieProperty.z) {
            this.f9436n.o(lottieValueCallback);
            return;
        }
        if (obj == LottieProperty.A && (baseKeyframeAnimation = this.f9437o) != null) {
            baseKeyframeAnimation.o(lottieValueCallback);
        } else if (obj == LottieProperty.B) {
            this.f9438p.o(lottieValueCallback);
        }
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    public void f(KeyPath keyPath, int i2, List list, KeyPath keyPath2) {
        MiscUtils.k(keyPath, i2, list, keyPath2, this);
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public String getName() {
        return this.f9427e;
    }
}
