package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatablePointValue;
import com.airbnb.lottie.model.animatable.AnimatableValue;
import com.airbnb.lottie.model.content.CircleShape;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.zte.distbus.basetransfer.DistBusKeys;

/* loaded from: classes.dex */
class CircleShapeParser {

    /* renamed from: a, reason: collision with root package name */
    private static final JsonReader.Options f9804a = JsonReader.Options.a("nm", "p", "s", "hd", DistBusKeys.KEY_WIFI_DBDC);

    static CircleShape a(JsonReader jsonReader, LottieComposition lottieComposition, int i2) {
        boolean z = i2 == 3;
        boolean z2 = false;
        String str = null;
        AnimatableValue animatableValue = null;
        AnimatablePointValue animatablePointValue = null;
        while (jsonReader.j()) {
            int E = jsonReader.E(f9804a);
            if (E == 0) {
                str = jsonReader.A();
            } else if (E == 1) {
                animatableValue = AnimatablePathValueParser.b(jsonReader, lottieComposition);
            } else if (E == 2) {
                animatablePointValue = AnimatableValueParser.i(jsonReader, lottieComposition);
            } else if (E == 3) {
                z2 = jsonReader.k();
            } else if (E != 4) {
                jsonReader.F();
                jsonReader.G();
            } else {
                z = jsonReader.s() == 3;
            }
        }
        return new CircleShape(str, animatableValue, animatablePointValue, z, z2);
    }
}
