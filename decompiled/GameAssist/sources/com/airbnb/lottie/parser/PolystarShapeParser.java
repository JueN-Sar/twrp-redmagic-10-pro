package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableValue;
import com.airbnb.lottie.model.content.PolystarShape;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.zte.distbus.basetransfer.DistBusKeys;

/* loaded from: classes.dex */
class PolystarShapeParser {

    /* renamed from: a, reason: collision with root package name */
    private static final JsonReader.Options f9850a = JsonReader.Options.a("nm", "sy", "pt", "p", "r", "or", "os", "ir", "is", "hd", DistBusKeys.KEY_WIFI_DBDC);

    static PolystarShape a(JsonReader jsonReader, LottieComposition lottieComposition, int i2) {
        boolean z = false;
        boolean z2 = i2 == 3;
        String str = null;
        PolystarShape.Type type = null;
        AnimatableFloatValue animatableFloatValue = null;
        AnimatableValue animatableValue = null;
        AnimatableFloatValue animatableFloatValue2 = null;
        AnimatableFloatValue animatableFloatValue3 = null;
        AnimatableFloatValue animatableFloatValue4 = null;
        AnimatableFloatValue animatableFloatValue5 = null;
        AnimatableFloatValue animatableFloatValue6 = null;
        while (jsonReader.j()) {
            switch (jsonReader.E(f9850a)) {
                case 0:
                    str = jsonReader.A();
                    break;
                case 1:
                    type = PolystarShape.Type.d(jsonReader.s());
                    break;
                case 2:
                    animatableFloatValue = AnimatableValueParser.f(jsonReader, lottieComposition, false);
                    break;
                case 3:
                    animatableValue = AnimatablePathValueParser.b(jsonReader, lottieComposition);
                    break;
                case 4:
                    animatableFloatValue2 = AnimatableValueParser.f(jsonReader, lottieComposition, false);
                    break;
                case 5:
                    animatableFloatValue4 = AnimatableValueParser.e(jsonReader, lottieComposition);
                    break;
                case 6:
                    animatableFloatValue6 = AnimatableValueParser.f(jsonReader, lottieComposition, false);
                    break;
                case 7:
                    animatableFloatValue3 = AnimatableValueParser.e(jsonReader, lottieComposition);
                    break;
                case 8:
                    animatableFloatValue5 = AnimatableValueParser.f(jsonReader, lottieComposition, false);
                    break;
                case 9:
                    z = jsonReader.k();
                    break;
                case 10:
                    if (jsonReader.s() != 3) {
                        z2 = false;
                        break;
                    } else {
                        z2 = true;
                        break;
                    }
                default:
                    jsonReader.F();
                    jsonReader.G();
                    break;
            }
        }
        return new PolystarShape(str, type, animatableFloatValue, animatableValue, animatableFloatValue2, animatableFloatValue3, animatableFloatValue4, animatableFloatValue5, animatableFloatValue6, z, z2);
    }
}
