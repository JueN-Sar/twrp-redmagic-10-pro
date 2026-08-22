package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableShapeValue;
import com.airbnb.lottie.model.content.ShapePath;
import com.airbnb.lottie.parser.moshi.JsonReader;

/* loaded from: classes.dex */
class ShapePathParser {

    /* renamed from: a, reason: collision with root package name */
    static JsonReader.Options f9859a = JsonReader.Options.a("nm", "ind", "ks", "hd");

    static ShapePath a(JsonReader jsonReader, LottieComposition lottieComposition) {
        String str = null;
        int i2 = 0;
        boolean z = false;
        AnimatableShapeValue animatableShapeValue = null;
        while (jsonReader.j()) {
            int E = jsonReader.E(f9859a);
            if (E == 0) {
                str = jsonReader.A();
            } else if (E == 1) {
                i2 = jsonReader.s();
            } else if (E == 2) {
                animatableShapeValue = AnimatableValueParser.k(jsonReader, lottieComposition);
            } else if (E != 3) {
                jsonReader.G();
            } else {
                z = jsonReader.k();
            }
        }
        return new ShapePath(str, i2, animatableShapeValue, z);
    }
}
