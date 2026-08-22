package com.airbnb.lottie.model.animatable;

import android.graphics.Path;
import com.airbnb.lottie.animation.keyframe.ShapeKeyframeAnimation;
import com.airbnb.lottie.model.content.ShapeData;
import java.util.List;

/* loaded from: classes.dex */
public class AnimatableShapeValue extends BaseAnimatableValue<ShapeData, Path> {
    public AnimatableShapeValue(List list) {
        super(list);
    }

    @Override // com.airbnb.lottie.model.animatable.BaseAnimatableValue, com.airbnb.lottie.model.animatable.AnimatableValue
    public /* bridge */ /* synthetic */ boolean b() {
        return super.b();
    }

    @Override // com.airbnb.lottie.model.animatable.AnimatableValue
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public ShapeKeyframeAnimation a() {
        return new ShapeKeyframeAnimation(this.f9640a);
    }

    @Override // com.airbnb.lottie.model.animatable.BaseAnimatableValue, com.airbnb.lottie.model.animatable.AnimatableValue
    public /* bridge */ /* synthetic */ List getKeyframes() {
        return super.getKeyframes();
    }

    @Override // com.airbnb.lottie.model.animatable.BaseAnimatableValue
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }
}
