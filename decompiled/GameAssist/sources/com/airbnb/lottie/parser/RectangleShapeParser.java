package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatablePointValue;
import com.airbnb.lottie.model.animatable.AnimatableValue;
import com.airbnb.lottie.model.content.RectangleShape;
import com.airbnb.lottie.parser.moshi.JsonReader;

/* loaded from: classes.dex */
class RectangleShapeParser {

    /* renamed from: a, reason: collision with root package name */
    private static final JsonReader.Options f9851a = JsonReader.Options.a("nm", "p", "s", "r", "hd");

    static RectangleShape a(JsonReader jsonReader, LottieComposition lottieComposition) {
        String str = null;
        AnimatableValue animatableValue = null;
        AnimatablePointValue animatablePointValue = null;
        AnimatableFloatValue animatableFloatValue = null;
        boolean z = false;
        while (jsonReader.j()) {
            int E = jsonReader.E(f9851a);
            if (E == 0) {
                str = jsonReader.A();
            } else if (E == 1) {
                animatableValue = AnimatablePathValueParser.b(jsonReader, lottieComposition);
            } else if (E == 2) {
                animatablePointValue = AnimatableValueParser.i(jsonReader, lottieComposition);
            } else if (E == 3) {
                animatableFloatValue = AnimatableValueParser.e(jsonReader, lottieComposition);
            } else if (E != 4) {
                jsonReader.G();
            } else {
                z = jsonReader.k();
            }
        }
        return new RectangleShape(str, animatableValue, animatablePointValue, animatableFloatValue, z);
    }
}
