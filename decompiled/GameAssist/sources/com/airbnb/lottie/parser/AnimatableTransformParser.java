package com.airbnb.lottie.parser;

import android.graphics.PointF;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.animatable.AnimatablePathValue;
import com.airbnb.lottie.model.animatable.AnimatableScaleValue;
import com.airbnb.lottie.model.animatable.AnimatableSplitDimensionPathValue;
import com.airbnb.lottie.model.animatable.AnimatableTransform;
import com.airbnb.lottie.model.animatable.AnimatableValue;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.ScaleXY;
import com.zte.distbus.basetransfer.DistBusKeys;

/* loaded from: classes.dex */
public class AnimatableTransformParser {

    /* renamed from: a, reason: collision with root package name */
    private static final JsonReader.Options f9800a = JsonReader.Options.a(DistBusKeys.KEY_WIFI_ENABLE, "p", "s", "rz", "r", "o", "so", "eo", "sk", "sa");

    /* renamed from: b, reason: collision with root package name */
    private static final JsonReader.Options f9801b = JsonReader.Options.a("k");

    private static boolean a(AnimatablePathValue animatablePathValue) {
        return animatablePathValue == null || (animatablePathValue.b() && ((PointF) ((Keyframe) animatablePathValue.getKeyframes().get(0)).f9942b).equals(0.0f, 0.0f));
    }

    private static boolean b(AnimatableValue animatableValue) {
        return animatableValue == null || (!(animatableValue instanceof AnimatableSplitDimensionPathValue) && animatableValue.b() && ((PointF) ((Keyframe) animatableValue.getKeyframes().get(0)).f9942b).equals(0.0f, 0.0f));
    }

    private static boolean c(AnimatableFloatValue animatableFloatValue) {
        return animatableFloatValue == null || (animatableFloatValue.b() && ((Float) ((Keyframe) animatableFloatValue.getKeyframes().get(0)).f9942b).floatValue() == 0.0f);
    }

    private static boolean d(AnimatableScaleValue animatableScaleValue) {
        return animatableScaleValue == null || (animatableScaleValue.b() && ((ScaleXY) ((Keyframe) animatableScaleValue.getKeyframes().get(0)).f9942b).a(1.0f, 1.0f));
    }

    private static boolean e(AnimatableFloatValue animatableFloatValue) {
        return animatableFloatValue == null || (animatableFloatValue.b() && ((Float) ((Keyframe) animatableFloatValue.getKeyframes().get(0)).f9942b).floatValue() == 0.0f);
    }

    private static boolean f(AnimatableFloatValue animatableFloatValue) {
        return animatableFloatValue == null || (animatableFloatValue.b() && ((Float) ((Keyframe) animatableFloatValue.getKeyframes().get(0)).f9942b).floatValue() == 0.0f);
    }

    public static AnimatableTransform g(JsonReader jsonReader, LottieComposition lottieComposition) {
        boolean z;
        boolean z2 = false;
        boolean z3 = jsonReader.C() == JsonReader.Token.BEGIN_OBJECT;
        if (z3) {
            jsonReader.d();
        }
        AnimatableFloatValue animatableFloatValue = null;
        AnimatablePathValue animatablePathValue = null;
        AnimatableValue animatableValue = null;
        AnimatableScaleValue animatableScaleValue = null;
        AnimatableFloatValue animatableFloatValue2 = null;
        AnimatableFloatValue animatableFloatValue3 = null;
        AnimatableIntegerValue animatableIntegerValue = null;
        AnimatableFloatValue animatableFloatValue4 = null;
        AnimatableFloatValue animatableFloatValue5 = null;
        while (jsonReader.j()) {
            switch (jsonReader.E(f9800a)) {
                case 0:
                    boolean z4 = z2;
                    jsonReader.d();
                    while (jsonReader.j()) {
                        if (jsonReader.E(f9801b) != 0) {
                            jsonReader.F();
                            jsonReader.G();
                        } else {
                            animatablePathValue = AnimatablePathValueParser.a(jsonReader, lottieComposition);
                        }
                    }
                    jsonReader.h();
                    z2 = z4;
                    continue;
                case 1:
                    animatableValue = AnimatablePathValueParser.b(jsonReader, lottieComposition);
                    continue;
                case 2:
                    animatableScaleValue = AnimatableValueParser.j(jsonReader, lottieComposition);
                    continue;
                case 3:
                    lottieComposition.a("Lottie doesn't support 3D layers.");
                    break;
                case 4:
                    break;
                case 5:
                    animatableIntegerValue = AnimatableValueParser.h(jsonReader, lottieComposition);
                    continue;
                case 6:
                    animatableFloatValue4 = AnimatableValueParser.f(jsonReader, lottieComposition, z2);
                    continue;
                case 7:
                    animatableFloatValue5 = AnimatableValueParser.f(jsonReader, lottieComposition, z2);
                    continue;
                case 8:
                    animatableFloatValue2 = AnimatableValueParser.f(jsonReader, lottieComposition, z2);
                    continue;
                case 9:
                    animatableFloatValue3 = AnimatableValueParser.f(jsonReader, lottieComposition, z2);
                    continue;
                default:
                    jsonReader.F();
                    jsonReader.G();
                    continue;
            }
            AnimatableFloatValue f2 = AnimatableValueParser.f(jsonReader, lottieComposition, z2);
            if (f2.getKeyframes().isEmpty()) {
                f2.getKeyframes().add(new Keyframe(lottieComposition, Float.valueOf(0.0f), Float.valueOf(0.0f), null, 0.0f, Float.valueOf(lottieComposition.f())));
            } else if (((Keyframe) f2.getKeyframes().get(0)).f9942b == null) {
                z = false;
                f2.getKeyframes().set(0, new Keyframe(lottieComposition, Float.valueOf(0.0f), Float.valueOf(0.0f), null, 0.0f, Float.valueOf(lottieComposition.f())));
                z2 = z;
                animatableFloatValue = f2;
            }
            z = false;
            z2 = z;
            animatableFloatValue = f2;
        }
        if (z3) {
            jsonReader.h();
        }
        AnimatablePathValue animatablePathValue2 = a(animatablePathValue) ? null : animatablePathValue;
        AnimatableValue animatableValue2 = b(animatableValue) ? null : animatableValue;
        AnimatableFloatValue animatableFloatValue6 = c(animatableFloatValue) ? null : animatableFloatValue;
        if (d(animatableScaleValue)) {
            animatableScaleValue = null;
        }
        return new AnimatableTransform(animatablePathValue2, animatableValue2, animatableScaleValue, animatableFloatValue6, animatableIntegerValue, animatableFloatValue4, animatableFloatValue5, f(animatableFloatValue2) ? null : animatableFloatValue2, e(animatableFloatValue3) ? null : animatableFloatValue3);
    }
}
