package com.airbnb.lottie.animation.keyframe;

import android.graphics.PointF;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.List;

/* loaded from: classes.dex */
public class PointKeyframeAnimation extends KeyframeAnimation<PointF> {

    /* renamed from: i, reason: collision with root package name */
    private final PointF f9517i;

    public PointKeyframeAnimation(List list) {
        super(list);
        this.f9517i = new PointF();
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    /* renamed from: q, reason: merged with bridge method [inline-methods] */
    public PointF i(Keyframe keyframe, float f2) {
        return j(keyframe, f2, f2, f2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    /* renamed from: r, reason: merged with bridge method [inline-methods] */
    public PointF j(Keyframe keyframe, float f2, float f3, float f4) {
        Object obj;
        PointF pointF;
        Object obj2 = keyframe.f9942b;
        if (obj2 == null || (obj = keyframe.f9943c) == null) {
            throw new IllegalStateException("Missing values for keyframe.");
        }
        PointF pointF2 = (PointF) obj2;
        PointF pointF3 = (PointF) obj;
        LottieValueCallback lottieValueCallback = this.f9487e;
        if (lottieValueCallback != null && (pointF = (PointF) lottieValueCallback.b(keyframe.f9947g, keyframe.f9948h.floatValue(), pointF2, pointF3, f2, e(), f())) != null) {
            return pointF;
        }
        PointF pointF4 = this.f9517i;
        float f5 = pointF2.x;
        float f6 = f5 + (f3 * (pointF3.x - f5));
        float f7 = pointF2.y;
        pointF4.set(f6, f7 + (f4 * (pointF3.y - f7)));
        return this.f9517i;
    }
}
