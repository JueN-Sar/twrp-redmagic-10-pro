package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatablePathValue;
import com.airbnb.lottie.model.animatable.AnimatableSplitDimensionPathValue;
import com.airbnb.lottie.model.animatable.AnimatableValue;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.Keyframe;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class AnimatablePathValueParser {

    /* renamed from: a, reason: collision with root package name */
    private static final JsonReader.Options f9797a = JsonReader.Options.a("k", "x", "y");

    public static AnimatablePathValue a(JsonReader jsonReader, LottieComposition lottieComposition) {
        ArrayList arrayList = new ArrayList();
        if (jsonReader.C() == JsonReader.Token.BEGIN_ARRAY) {
            jsonReader.c();
            while (jsonReader.j()) {
                arrayList.add(PathKeyframeParser.a(jsonReader, lottieComposition));
            }
            jsonReader.e();
            KeyframesParser.b(arrayList);
        } else {
            arrayList.add(new Keyframe(JsonUtils.e(jsonReader, Utils.e())));
        }
        return new AnimatablePathValue(arrayList);
    }

    static AnimatableValue b(JsonReader jsonReader, LottieComposition lottieComposition) {
        jsonReader.d();
        AnimatablePathValue animatablePathValue = null;
        AnimatableFloatValue animatableFloatValue = null;
        boolean z = false;
        AnimatableFloatValue animatableFloatValue2 = null;
        while (jsonReader.C() != JsonReader.Token.END_OBJECT) {
            int E = jsonReader.E(f9797a);
            if (E == 0) {
                animatablePathValue = a(jsonReader, lottieComposition);
            } else if (E != 1) {
                if (E != 2) {
                    jsonReader.F();
                    jsonReader.G();
                } else if (jsonReader.C() == JsonReader.Token.STRING) {
                    jsonReader.G();
                    z = true;
                } else {
                    animatableFloatValue = AnimatableValueParser.e(jsonReader, lottieComposition);
                }
            } else if (jsonReader.C() == JsonReader.Token.STRING) {
                jsonReader.G();
                z = true;
            } else {
                animatableFloatValue2 = AnimatableValueParser.e(jsonReader, lottieComposition);
            }
        }
        jsonReader.h();
        if (z) {
            lottieComposition.a("Lottie doesn't support expressions.");
        }
        return animatablePathValue != null ? animatablePathValue : new AnimatableSplitDimensionPathValue(animatableFloatValue2, animatableFloatValue);
    }
}
