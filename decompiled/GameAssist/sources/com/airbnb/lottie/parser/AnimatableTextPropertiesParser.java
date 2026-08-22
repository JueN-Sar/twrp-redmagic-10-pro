package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableColorValue;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableTextProperties;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.zte.distbus.basetransfer.DistBusKeys;

/* loaded from: classes.dex */
public class AnimatableTextPropertiesParser {

    /* renamed from: a, reason: collision with root package name */
    private static final JsonReader.Options f9798a = JsonReader.Options.a(DistBusKeys.KEY_WIFI_ENABLE);

    /* renamed from: b, reason: collision with root package name */
    private static final JsonReader.Options f9799b = JsonReader.Options.a("fc", "sc", "sw", "t");

    public static AnimatableTextProperties a(JsonReader jsonReader, LottieComposition lottieComposition) {
        jsonReader.d();
        AnimatableTextProperties animatableTextProperties = null;
        while (jsonReader.j()) {
            if (jsonReader.E(f9798a) != 0) {
                jsonReader.F();
                jsonReader.G();
            } else {
                animatableTextProperties = b(jsonReader, lottieComposition);
            }
        }
        jsonReader.h();
        return animatableTextProperties == null ? new AnimatableTextProperties(null, null, null, null) : animatableTextProperties;
    }

    private static AnimatableTextProperties b(JsonReader jsonReader, LottieComposition lottieComposition) {
        jsonReader.d();
        AnimatableColorValue animatableColorValue = null;
        AnimatableColorValue animatableColorValue2 = null;
        AnimatableFloatValue animatableFloatValue = null;
        AnimatableFloatValue animatableFloatValue2 = null;
        while (jsonReader.j()) {
            int E = jsonReader.E(f9799b);
            if (E == 0) {
                animatableColorValue = AnimatableValueParser.c(jsonReader, lottieComposition);
            } else if (E == 1) {
                animatableColorValue2 = AnimatableValueParser.c(jsonReader, lottieComposition);
            } else if (E == 2) {
                animatableFloatValue = AnimatableValueParser.e(jsonReader, lottieComposition);
            } else if (E != 3) {
                jsonReader.F();
                jsonReader.G();
            } else {
                animatableFloatValue2 = AnimatableValueParser.e(jsonReader, lottieComposition);
            }
        }
        jsonReader.h();
        return new AnimatableTextProperties(animatableColorValue, animatableColorValue2, animatableFloatValue, animatableFloatValue2);
    }
}
