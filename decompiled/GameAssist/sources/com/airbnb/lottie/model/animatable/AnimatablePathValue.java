package com.airbnb.lottie.model.animatable;

import android.graphics.PointF;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.PathKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.PointKeyframeAnimation;
import com.airbnb.lottie.value.Keyframe;
import java.util.List;

/* loaded from: classes.dex */
public class AnimatablePathValue implements AnimatableValue<PointF, PointF> {

    /* renamed from: a, reason: collision with root package name */
    private final List f9623a;

    public AnimatablePathValue(List list) {
        this.f9623a = list;
    }

    @Override // com.airbnb.lottie.model.animatable.AnimatableValue
    public BaseKeyframeAnimation a() {
        return ((Keyframe) this.f9623a.get(0)).i() ? new PointKeyframeAnimation(this.f9623a) : new PathKeyframeAnimation(this.f9623a);
    }

    @Override // com.airbnb.lottie.model.animatable.AnimatableValue
    public boolean b() {
        return this.f9623a.size() == 1 && ((Keyframe) this.f9623a.get(0)).i();
    }

    @Override // com.airbnb.lottie.model.animatable.AnimatableValue
    public List getKeyframes() {
        return this.f9623a;
    }
}
