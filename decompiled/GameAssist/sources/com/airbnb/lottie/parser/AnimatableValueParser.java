package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableColorValue;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableGradientColorValue;
import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.animatable.AnimatablePointValue;
import com.airbnb.lottie.model.animatable.AnimatableScaleValue;
import com.airbnb.lottie.model.animatable.AnimatableShapeValue;
import com.airbnb.lottie.model.animatable.AnimatableTextFrame;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.Utils;
import java.util.List;

/* loaded from: classes.dex */
public class AnimatableValueParser {
    private static List a(JsonReader jsonReader, float f2, LottieComposition lottieComposition, ValueParser valueParser) {
        return KeyframesParser.a(jsonReader, lottieComposition, f2, valueParser, false);
    }

    private static List b(JsonReader jsonReader, LottieComposition lottieComposition, ValueParser valueParser) {
        return KeyframesParser.a(jsonReader, lottieComposition, 1.0f, valueParser, false);
    }

    static AnimatableColorValue c(JsonReader jsonReader, LottieComposition lottieComposition) {
        return new AnimatableColorValue(b(jsonReader, lottieComposition, ColorParser.f9805a));
    }

    static AnimatableTextFrame d(JsonReader jsonReader, LottieComposition lottieComposition) {
        return new AnimatableTextFrame(a(jsonReader, Utils.e(), lottieComposition, DocumentDataParser.f9807a));
    }

    public static AnimatableFloatValue e(JsonReader jsonReader, LottieComposition lottieComposition) {
        return f(jsonReader, lottieComposition, true);
    }

    public static AnimatableFloatValue f(JsonReader jsonReader, LottieComposition lottieComposition, boolean z) {
        return new AnimatableFloatValue(a(jsonReader, z ? Utils.e() : 1.0f, lottieComposition, FloatParser.f9821a));
    }

    static AnimatableGradientColorValue g(JsonReader jsonReader, LottieComposition lottieComposition, int i2) {
        return new AnimatableGradientColorValue(b(jsonReader, lottieComposition, new GradientColorParser(i2)));
    }

    static AnimatableIntegerValue h(JsonReader jsonReader, LottieComposition lottieComposition) {
        return new AnimatableIntegerValue(b(jsonReader, lottieComposition, IntegerParser.f9831a));
    }

    static AnimatablePointValue i(JsonReader jsonReader, LottieComposition lottieComposition) {
        return new AnimatablePointValue(KeyframesParser.a(jsonReader, lottieComposition, Utils.e(), PointFParser.f9849a, true));
    }

    static AnimatableScaleValue j(JsonReader jsonReader, LottieComposition lottieComposition) {
        return new AnimatableScaleValue(b(jsonReader, lottieComposition, ScaleXYParser.f9854a));
    }

    static AnimatableShapeValue k(JsonReader jsonReader, LottieComposition lottieComposition) {
        return new AnimatableShapeValue(a(jsonReader, Utils.e(), lottieComposition, ShapeDataParser.f9855a));
    }
}
