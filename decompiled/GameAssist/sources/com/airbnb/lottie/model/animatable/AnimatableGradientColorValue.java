package com.airbnb.lottie.model.animatable;

import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.GradientColorKeyframeAnimation;
import com.airbnb.lottie.model.content.GradientColor;
import com.airbnb.lottie.value.Keyframe;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public class AnimatableGradientColorValue extends BaseAnimatableValue<GradientColor, GradientColor> {
    public AnimatableGradientColorValue(List list) {
        super(d(list));
    }

    private static Keyframe c(Keyframe keyframe) {
        GradientColor gradientColor = (GradientColor) keyframe.f9942b;
        GradientColor gradientColor2 = (GradientColor) keyframe.f9943c;
        if (gradientColor == null || gradientColor2 == null || gradientColor.e().length == gradientColor2.e().length) {
            return keyframe;
        }
        float[] e2 = e(gradientColor.e(), gradientColor2.e());
        return keyframe.b(gradientColor.b(e2), gradientColor2.b(e2));
    }

    private static List d(List list) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            list.set(i2, c((Keyframe) list.get(i2)));
        }
        return list;
    }

    static float[] e(float[] fArr, float[] fArr2) {
        int length = fArr.length + fArr2.length;
        float[] fArr3 = new float[length];
        System.arraycopy(fArr, 0, fArr3, 0, fArr.length);
        System.arraycopy(fArr2, 0, fArr3, fArr.length, fArr2.length);
        Arrays.sort(fArr3);
        float f2 = Float.NaN;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            float f3 = fArr3[i3];
            if (f3 != f2) {
                fArr3[i2] = f3;
                i2++;
                f2 = fArr3[i3];
            }
        }
        return Arrays.copyOfRange(fArr3, 0, i2);
    }

    @Override // com.airbnb.lottie.model.animatable.AnimatableValue
    public BaseKeyframeAnimation a() {
        return new GradientColorKeyframeAnimation(this.f9640a);
    }

    @Override // com.airbnb.lottie.model.animatable.BaseAnimatableValue, com.airbnb.lottie.model.animatable.AnimatableValue
    public /* bridge */ /* synthetic */ boolean b() {
        return super.b();
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
