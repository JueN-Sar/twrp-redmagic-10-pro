package com.airbnb.lottie.animation.keyframe;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.List;

/* loaded from: classes.dex */
public class PathKeyframeAnimation extends KeyframeAnimation<PointF> {

    /* renamed from: i, reason: collision with root package name */
    private final PointF f9512i;

    /* renamed from: j, reason: collision with root package name */
    private final float[] f9513j;

    /* renamed from: k, reason: collision with root package name */
    private final float[] f9514k;

    /* renamed from: l, reason: collision with root package name */
    private final PathMeasure f9515l;

    /* renamed from: m, reason: collision with root package name */
    private PathKeyframe f9516m;

    public PathKeyframeAnimation(List list) {
        super(list);
        this.f9512i = new PointF();
        this.f9513j = new float[2];
        this.f9514k = new float[2];
        this.f9515l = new PathMeasure();
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    /* renamed from: q, reason: merged with bridge method [inline-methods] */
    public PointF i(Keyframe keyframe, float f2) {
        PointF pointF;
        PathKeyframe pathKeyframe = (PathKeyframe) keyframe;
        Path k2 = pathKeyframe.k();
        if (k2 == null) {
            return (PointF) keyframe.f9942b;
        }
        LottieValueCallback lottieValueCallback = this.f9487e;
        if (lottieValueCallback != null && (pointF = (PointF) lottieValueCallback.b(pathKeyframe.f9947g, pathKeyframe.f9948h.floatValue(), (PointF) pathKeyframe.f9942b, (PointF) pathKeyframe.f9943c, e(), f2, f())) != null) {
            return pointF;
        }
        if (this.f9516m != pathKeyframe) {
            this.f9515l.setPath(k2, false);
            this.f9516m = pathKeyframe;
        }
        float length = this.f9515l.getLength();
        float f3 = f2 * length;
        this.f9515l.getPosTan(f3, this.f9513j, this.f9514k);
        PointF pointF2 = this.f9512i;
        float[] fArr = this.f9513j;
        pointF2.set(fArr[0], fArr[1]);
        if (f3 < 0.0f) {
            PointF pointF3 = this.f9512i;
            float[] fArr2 = this.f9514k;
            pointF3.offset(fArr2[0] * f3, fArr2[1] * f3);
        } else if (f3 > length) {
            PointF pointF4 = this.f9512i;
            float[] fArr3 = this.f9514k;
            float f4 = f3 - length;
            pointF4.offset(fArr3[0] * f4, fArr3[1] * f4);
        }
        return this.f9512i;
    }
}
