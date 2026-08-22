package com.airbnb.lottie.animation.content;

import android.graphics.PointF;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.model.CubicCurveData;
import com.airbnb.lottie.model.content.RoundedCorners;
import com.airbnb.lottie.model.content.ShapeData;
import com.airbnb.lottie.model.layer.BaseLayer;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class RoundedCornersContent implements ShapeModifierContent, BaseKeyframeAnimation.AnimationListener {

    /* renamed from: a, reason: collision with root package name */
    private final LottieDrawable f9463a;

    /* renamed from: b, reason: collision with root package name */
    private final String f9464b;

    /* renamed from: c, reason: collision with root package name */
    private final BaseKeyframeAnimation f9465c;

    /* renamed from: d, reason: collision with root package name */
    private ShapeData f9466d;

    public RoundedCornersContent(LottieDrawable lottieDrawable, BaseLayer baseLayer, RoundedCorners roundedCorners) {
        this.f9463a = lottieDrawable;
        this.f9464b = roundedCorners.c();
        BaseKeyframeAnimation a2 = roundedCorners.b().a();
        this.f9465c = a2;
        baseLayer.j(a2);
        a2.a(this);
    }

    private static int e(int i2, int i3) {
        int i4 = i2 / i3;
        return ((i2 ^ i3) >= 0 || i3 * i4 == i2) ? i4 : i4 - 1;
    }

    private static int f(int i2, int i3) {
        return i2 - (e(i2, i3) * i3);
    }

    private ShapeData j(ShapeData shapeData) {
        List a2 = shapeData.a();
        boolean d2 = shapeData.d();
        int size = a2.size() - 1;
        int i2 = 0;
        while (size >= 0) {
            CubicCurveData cubicCurveData = (CubicCurveData) a2.get(size);
            CubicCurveData cubicCurveData2 = (CubicCurveData) a2.get(f(size - 1, a2.size()));
            PointF c2 = (size != 0 || d2) ? cubicCurveData2.c() : shapeData.b();
            i2 = (((size != 0 || d2) ? cubicCurveData2.b() : c2).equals(c2) && cubicCurveData.a().equals(c2) && !(!shapeData.d() && (size == 0 || size == a2.size() - 1))) ? i2 + 2 : i2 + 1;
            size--;
        }
        ShapeData shapeData2 = this.f9466d;
        if (shapeData2 == null || shapeData2.a().size() != i2) {
            ArrayList arrayList = new ArrayList(i2);
            for (int i3 = 0; i3 < i2; i3++) {
                arrayList.add(new CubicCurveData());
            }
            this.f9466d = new ShapeData(new PointF(0.0f, 0.0f), false, arrayList);
        }
        this.f9466d.e(d2);
        return this.f9466d;
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener
    public void a() {
        this.f9463a.invalidateSelf();
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public void b(List list, List list2) {
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x009b, code lost:
    
        if (r6 != (r0.size() - 1)) goto L27;
     */
    @Override // com.airbnb.lottie.animation.content.ShapeModifierContent
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.airbnb.lottie.model.content.ShapeData c(com.airbnb.lottie.model.content.ShapeData r18) {
        /*
            Method dump skipped, instructions count: 406
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.animation.content.RoundedCornersContent.c(com.airbnb.lottie.model.content.ShapeData):com.airbnb.lottie.model.content.ShapeData");
    }

    public BaseKeyframeAnimation h() {
        return this.f9465c;
    }
}
