package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableTransform;
import com.airbnb.lottie.model.content.Repeater;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.zte.distbus.basetransfer.DistBusKeys;

/* loaded from: classes.dex */
class RepeaterParser {

    /* renamed from: a, reason: collision with root package name */
    private static final JsonReader.Options f9852a = JsonReader.Options.a("nm", DistBusKeys.KEY_WIFI_FREQUENCY, "o", "tr", "hd");

    static Repeater a(JsonReader jsonReader, LottieComposition lottieComposition) {
        String str = null;
        AnimatableFloatValue animatableFloatValue = null;
        AnimatableFloatValue animatableFloatValue2 = null;
        AnimatableTransform animatableTransform = null;
        boolean z = false;
        while (jsonReader.j()) {
            int E = jsonReader.E(f9852a);
            if (E == 0) {
                str = jsonReader.A();
            } else if (E == 1) {
                animatableFloatValue = AnimatableValueParser.f(jsonReader, lottieComposition, false);
            } else if (E == 2) {
                animatableFloatValue2 = AnimatableValueParser.f(jsonReader, lottieComposition, false);
            } else if (E == 3) {
                animatableTransform = AnimatableTransformParser.g(jsonReader, lottieComposition);
            } else if (E != 4) {
                jsonReader.G();
            } else {
                z = jsonReader.k();
            }
        }
        return new Repeater(str, animatableFloatValue, animatableFloatValue2, animatableTransform, z);
    }
}
