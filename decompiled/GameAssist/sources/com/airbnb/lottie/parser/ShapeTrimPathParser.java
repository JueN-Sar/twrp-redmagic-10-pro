package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.content.ShapeTrimPath;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.zte.distbus.basetransfer.DistBusKeys;

/* loaded from: classes.dex */
class ShapeTrimPathParser {

    /* renamed from: a, reason: collision with root package name */
    private static final JsonReader.Options f9862a = JsonReader.Options.a("s", DistBusKeys.KEY_PHYSICAL_TYPE, "o", "nm", "m", "hd");

    static ShapeTrimPath a(JsonReader jsonReader, LottieComposition lottieComposition) {
        String str = null;
        ShapeTrimPath.Type type = null;
        AnimatableFloatValue animatableFloatValue = null;
        AnimatableFloatValue animatableFloatValue2 = null;
        AnimatableFloatValue animatableFloatValue3 = null;
        boolean z = false;
        while (jsonReader.j()) {
            int E = jsonReader.E(f9862a);
            if (E == 0) {
                animatableFloatValue = AnimatableValueParser.f(jsonReader, lottieComposition, false);
            } else if (E == 1) {
                animatableFloatValue2 = AnimatableValueParser.f(jsonReader, lottieComposition, false);
            } else if (E == 2) {
                animatableFloatValue3 = AnimatableValueParser.f(jsonReader, lottieComposition, false);
            } else if (E == 3) {
                str = jsonReader.A();
            } else if (E == 4) {
                type = ShapeTrimPath.Type.d(jsonReader.s());
            } else if (E != 5) {
                jsonReader.G();
            } else {
                z = jsonReader.k();
            }
        }
        return new ShapeTrimPath(str, type, animatableFloatValue, animatableFloatValue2, animatableFloatValue3, z);
    }
}
