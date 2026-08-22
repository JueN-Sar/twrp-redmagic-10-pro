package com.airbnb.lottie.parser;

import android.graphics.Path;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableGradientColorValue;
import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.animatable.AnimatablePointValue;
import com.airbnb.lottie.model.content.GradientFill;
import com.airbnb.lottie.model.content.GradientType;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.value.Keyframe;
import com.zte.distbus.basetransfer.DistBusKeys;
import java.util.Collections;

/* loaded from: classes.dex */
class GradientFillParser {

    /* renamed from: a, reason: collision with root package name */
    private static final JsonReader.Options f9826a = JsonReader.Options.a("nm", "g", "o", "t", "s", DistBusKeys.KEY_PHYSICAL_TYPE, "r", "hd");

    /* renamed from: b, reason: collision with root package name */
    private static final JsonReader.Options f9827b = JsonReader.Options.a("p", "k");

    static GradientFill a(JsonReader jsonReader, LottieComposition lottieComposition) {
        AnimatableIntegerValue animatableIntegerValue = null;
        Path.FillType fillType = Path.FillType.WINDING;
        String str = null;
        GradientType gradientType = null;
        AnimatableGradientColorValue animatableGradientColorValue = null;
        AnimatablePointValue animatablePointValue = null;
        AnimatablePointValue animatablePointValue2 = null;
        boolean z = false;
        while (jsonReader.j()) {
            switch (jsonReader.E(f9826a)) {
                case 0:
                    str = jsonReader.A();
                    break;
                case 1:
                    jsonReader.d();
                    int i2 = -1;
                    while (jsonReader.j()) {
                        int E = jsonReader.E(f9827b);
                        if (E == 0) {
                            i2 = jsonReader.s();
                        } else if (E != 1) {
                            jsonReader.F();
                            jsonReader.G();
                        } else {
                            animatableGradientColorValue = AnimatableValueParser.g(jsonReader, lottieComposition, i2);
                        }
                    }
                    jsonReader.h();
                    break;
                case 2:
                    animatableIntegerValue = AnimatableValueParser.h(jsonReader, lottieComposition);
                    break;
                case 3:
                    gradientType = jsonReader.s() == 1 ? GradientType.LINEAR : GradientType.RADIAL;
                    break;
                case 4:
                    animatablePointValue = AnimatableValueParser.i(jsonReader, lottieComposition);
                    break;
                case 5:
                    animatablePointValue2 = AnimatableValueParser.i(jsonReader, lottieComposition);
                    break;
                case 6:
                    fillType = jsonReader.s() == 1 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD;
                    break;
                case 7:
                    z = jsonReader.k();
                    break;
                default:
                    jsonReader.F();
                    jsonReader.G();
                    break;
            }
        }
        return new GradientFill(str, gradientType, fillType, animatableGradientColorValue, animatableIntegerValue == null ? new AnimatableIntegerValue(Collections.singletonList(new Keyframe(100))) : animatableIntegerValue, animatablePointValue, animatablePointValue2, null, null, z);
    }
}
