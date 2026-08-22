package com.airbnb.lottie.animation.keyframe;

import com.airbnb.lottie.model.content.GradientColor;
import com.airbnb.lottie.value.Keyframe;
import java.util.List;

/* loaded from: classes.dex */
public class GradientColorKeyframeAnimation extends KeyframeAnimation<GradientColor> {

    /* renamed from: i, reason: collision with root package name */
    private final GradientColor f9506i;

    public GradientColorKeyframeAnimation(List list) {
        super(list);
        int i2 = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            GradientColor gradientColor = (GradientColor) ((Keyframe) list.get(i3)).f9942b;
            if (gradientColor != null) {
                i2 = Math.max(i2, gradientColor.f());
            }
        }
        this.f9506i = new GradientColor(new float[i2], new int[i2]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    /* renamed from: q, reason: merged with bridge method [inline-methods] */
    public GradientColor i(Keyframe keyframe, float f2) {
        this.f9506i.g((GradientColor) keyframe.f9942b, (GradientColor) keyframe.f9943c, f2);
        return this.f9506i;
    }
}
