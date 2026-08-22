package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.content.RoundedCorners;
import com.airbnb.lottie.parser.moshi.JsonReader;

/* loaded from: classes.dex */
public class RoundedCornersParser {

    /* renamed from: a, reason: collision with root package name */
    private static final JsonReader.Options f9853a = JsonReader.Options.a("nm", "r", "hd");

    static RoundedCorners a(JsonReader jsonReader, LottieComposition lottieComposition) {
        boolean z = false;
        String str = null;
        AnimatableFloatValue animatableFloatValue = null;
        while (jsonReader.j()) {
            int E = jsonReader.E(f9853a);
            if (E == 0) {
                str = jsonReader.A();
            } else if (E == 1) {
                animatableFloatValue = AnimatableValueParser.f(jsonReader, lottieComposition, true);
            } else if (E != 2) {
                jsonReader.G();
            } else {
                z = jsonReader.k();
            }
        }
        if (z) {
            return null;
        }
        return new RoundedCorners(str, animatableFloatValue);
    }
}
