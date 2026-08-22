package com.airbnb.lottie.model.animatable;

import android.graphics.PointF;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.SplitDimensionPathKeyframeAnimation;
import java.util.List;

/* loaded from: classes.dex */
public class AnimatableSplitDimensionPathValue implements AnimatableValue<PointF, PointF> {

    /* renamed from: a, reason: collision with root package name */
    private final AnimatableFloatValue f9624a;

    /* renamed from: b, reason: collision with root package name */
    private final AnimatableFloatValue f9625b;

    public AnimatableSplitDimensionPathValue(AnimatableFloatValue animatableFloatValue, AnimatableFloatValue animatableFloatValue2) {
        this.f9624a = animatableFloatValue;
        this.f9625b = animatableFloatValue2;
    }

    @Override // com.airbnb.lottie.model.animatable.AnimatableValue
    public BaseKeyframeAnimation a() {
        return new SplitDimensionPathKeyframeAnimation(this.f9624a.a(), this.f9625b.a());
    }

    @Override // com.airbnb.lottie.model.animatable.AnimatableValue
    public boolean b() {
        return this.f9624a.b() && this.f9625b.b();
    }

    @Override // com.airbnb.lottie.model.animatable.AnimatableValue
    public List getKeyframes() {
        throw new UnsupportedOperationException("Cannot call getKeyframes on AnimatableSplitDimensionPathValue.");
    }
}
