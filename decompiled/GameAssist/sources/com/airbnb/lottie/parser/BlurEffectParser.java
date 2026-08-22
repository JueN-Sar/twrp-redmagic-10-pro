package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.content.BlurEffect;
import com.airbnb.lottie.parser.moshi.JsonReader;

/* loaded from: classes.dex */
class BlurEffectParser {

    /* renamed from: a, reason: collision with root package name */
    private static final JsonReader.Options f9802a = JsonReader.Options.a("ef");

    /* renamed from: b, reason: collision with root package name */
    private static final JsonReader.Options f9803b = JsonReader.Options.a("ty", "v");

    private static BlurEffect a(JsonReader jsonReader, LottieComposition lottieComposition) {
        jsonReader.d();
        BlurEffect blurEffect = null;
        while (true) {
            boolean z = false;
            while (jsonReader.j()) {
                int E = jsonReader.E(f9803b);
                if (E != 0) {
                    if (E != 1) {
                        jsonReader.F();
                        jsonReader.G();
                    } else if (z) {
                        blurEffect = new BlurEffect(AnimatableValueParser.e(jsonReader, lottieComposition));
                    } else {
                        jsonReader.G();
                    }
                } else if (jsonReader.s() == 0) {
                    z = true;
                }
            }
            jsonReader.h();
            return blurEffect;
        }
    }

    static BlurEffect b(JsonReader jsonReader, LottieComposition lottieComposition) {
        BlurEffect blurEffect = null;
        while (jsonReader.j()) {
            if (jsonReader.E(f9802a) != 0) {
                jsonReader.F();
                jsonReader.G();
            } else {
                jsonReader.c();
                while (jsonReader.j()) {
                    BlurEffect a2 = a(jsonReader, lottieComposition);
                    if (a2 != null) {
                        blurEffect = a2;
                    }
                }
                jsonReader.e();
            }
        }
        return blurEffect;
    }
}
