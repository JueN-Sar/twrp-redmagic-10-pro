package com.airbnb.lottie.animation.keyframe;

import android.graphics.Path;
import android.graphics.PointF;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.Keyframe;

/* loaded from: classes.dex */
public class PathKeyframe extends Keyframe<PointF> {

    /* renamed from: q, reason: collision with root package name */
    private Path f9510q;

    /* renamed from: r, reason: collision with root package name */
    private final Keyframe f9511r;

    public PathKeyframe(LottieComposition lottieComposition, Keyframe keyframe) {
        super(lottieComposition, (PointF) keyframe.f9942b, (PointF) keyframe.f9943c, keyframe.f9944d, keyframe.f9945e, keyframe.f9946f, keyframe.f9947g, keyframe.f9948h);
        this.f9511r = keyframe;
        j();
    }

    public void j() {
        Object obj;
        Object obj2;
        Object obj3 = this.f9943c;
        boolean z = (obj3 == null || (obj2 = this.f9942b) == null || !((PointF) obj2).equals(((PointF) obj3).x, ((PointF) obj3).y)) ? false : true;
        Object obj4 = this.f9942b;
        if (obj4 == null || (obj = this.f9943c) == null || z) {
            return;
        }
        Keyframe keyframe = this.f9511r;
        this.f9510q = Utils.d((PointF) obj4, (PointF) obj, keyframe.f9955o, keyframe.f9956p);
    }

    Path k() {
        return this.f9510q;
    }
}
