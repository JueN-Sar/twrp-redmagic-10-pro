package com.airbnb.lottie.parser;

import android.graphics.Path;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableColorValue;
import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.content.ShapeFill;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.value.Keyframe;
import com.zte.distbus.basetransfer.DistBusKeys;
import java.util.Collections;

/* loaded from: classes.dex */
class ShapeFillParser {

    /* renamed from: a, reason: collision with root package name */
    private static final JsonReader.Options f9857a = JsonReader.Options.a("nm", DistBusKeys.KEY_WIFI_FREQUENCY, "o", "fillEnabled", "r", "hd");

    static ShapeFill a(JsonReader jsonReader, LottieComposition lottieComposition) {
        AnimatableIntegerValue animatableIntegerValue = null;
        String str = null;
        AnimatableColorValue animatableColorValue = null;
        boolean z = false;
        boolean z2 = false;
        int i2 = 1;
        while (jsonReader.j()) {
            int E = jsonReader.E(f9857a);
            if (E == 0) {
                str = jsonReader.A();
            } else if (E == 1) {
                animatableColorValue = AnimatableValueParser.c(jsonReader, lottieComposition);
            } else if (E == 2) {
                animatableIntegerValue = AnimatableValueParser.h(jsonReader, lottieComposition);
            } else if (E == 3) {
                z = jsonReader.k();
            } else if (E == 4) {
                i2 = jsonReader.s();
            } else if (E != 5) {
                jsonReader.F();
                jsonReader.G();
            } else {
                z2 = jsonReader.k();
            }
        }
        if (animatableIntegerValue == null) {
            animatableIntegerValue = new AnimatableIntegerValue(Collections.singletonList(new Keyframe(100)));
        }
        return new ShapeFill(str, z, i2 == 1 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD, animatableColorValue, animatableIntegerValue, z2);
    }
}
