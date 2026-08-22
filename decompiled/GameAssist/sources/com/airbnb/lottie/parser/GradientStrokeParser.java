package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableGradientColorValue;
import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.animatable.AnimatablePointValue;
import com.airbnb.lottie.model.content.GradientStroke;
import com.airbnb.lottie.model.content.GradientType;
import com.airbnb.lottie.model.content.ShapeStroke;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.value.Keyframe;
import com.zte.distbus.basetransfer.DistBusKeys;
import java.util.ArrayList;
import java.util.Collections;

/* loaded from: classes.dex */
class GradientStrokeParser {

    /* renamed from: a, reason: collision with root package name */
    private static final JsonReader.Options f9828a = JsonReader.Options.a("nm", "g", "o", "t", "s", DistBusKeys.KEY_PHYSICAL_TYPE, "w", "lc", "lj", "ml", "hd", DistBusKeys.KEY_WIFI_DBDC);

    /* renamed from: b, reason: collision with root package name */
    private static final JsonReader.Options f9829b = JsonReader.Options.a("p", "k");

    /* renamed from: c, reason: collision with root package name */
    private static final JsonReader.Options f9830c = JsonReader.Options.a("n", "v");

    static GradientStroke a(JsonReader jsonReader, LottieComposition lottieComposition) {
        String str;
        AnimatableGradientColorValue animatableGradientColorValue;
        ArrayList arrayList = new ArrayList();
        float f2 = 0.0f;
        String str2 = null;
        GradientType gradientType = null;
        AnimatableGradientColorValue animatableGradientColorValue2 = null;
        AnimatablePointValue animatablePointValue = null;
        AnimatablePointValue animatablePointValue2 = null;
        AnimatableFloatValue animatableFloatValue = null;
        ShapeStroke.LineCapType lineCapType = null;
        ShapeStroke.LineJoinType lineJoinType = null;
        AnimatableFloatValue animatableFloatValue2 = null;
        boolean z = false;
        AnimatableIntegerValue animatableIntegerValue = null;
        while (jsonReader.j()) {
            switch (jsonReader.E(f9828a)) {
                case 0:
                    str2 = jsonReader.A();
                    continue;
                case 1:
                    str = str2;
                    jsonReader.d();
                    int i2 = -1;
                    while (jsonReader.j()) {
                        int E = jsonReader.E(f9829b);
                        if (E != 0) {
                            animatableGradientColorValue = animatableGradientColorValue2;
                            if (E != 1) {
                                jsonReader.F();
                                jsonReader.G();
                            } else {
                                animatableGradientColorValue2 = AnimatableValueParser.g(jsonReader, lottieComposition, i2);
                            }
                        } else {
                            animatableGradientColorValue = animatableGradientColorValue2;
                            i2 = jsonReader.s();
                        }
                        animatableGradientColorValue2 = animatableGradientColorValue;
                    }
                    jsonReader.h();
                    break;
                case 2:
                    animatableIntegerValue = AnimatableValueParser.h(jsonReader, lottieComposition);
                    continue;
                case 3:
                    str = str2;
                    gradientType = jsonReader.s() == 1 ? GradientType.LINEAR : GradientType.RADIAL;
                    break;
                case 4:
                    animatablePointValue = AnimatableValueParser.i(jsonReader, lottieComposition);
                    continue;
                case 5:
                    animatablePointValue2 = AnimatableValueParser.i(jsonReader, lottieComposition);
                    continue;
                case 6:
                    animatableFloatValue = AnimatableValueParser.e(jsonReader, lottieComposition);
                    continue;
                case 7:
                    str = str2;
                    lineCapType = ShapeStroke.LineCapType.values()[jsonReader.s() - 1];
                    break;
                case 8:
                    str = str2;
                    lineJoinType = ShapeStroke.LineJoinType.values()[jsonReader.s() - 1];
                    break;
                case 9:
                    str = str2;
                    f2 = (float) jsonReader.p();
                    break;
                case 10:
                    z = jsonReader.k();
                    continue;
                case 11:
                    jsonReader.c();
                    while (jsonReader.j()) {
                        jsonReader.d();
                        String str3 = null;
                        AnimatableFloatValue animatableFloatValue3 = null;
                        while (jsonReader.j()) {
                            int E2 = jsonReader.E(f9830c);
                            if (E2 != 0) {
                                AnimatableFloatValue animatableFloatValue4 = animatableFloatValue2;
                                if (E2 != 1) {
                                    jsonReader.F();
                                    jsonReader.G();
                                } else {
                                    animatableFloatValue3 = AnimatableValueParser.e(jsonReader, lottieComposition);
                                }
                                animatableFloatValue2 = animatableFloatValue4;
                            } else {
                                str3 = jsonReader.A();
                            }
                        }
                        AnimatableFloatValue animatableFloatValue5 = animatableFloatValue2;
                        jsonReader.h();
                        if (str3.equals("o")) {
                            animatableFloatValue2 = animatableFloatValue3;
                        } else {
                            if (str3.equals(DistBusKeys.KEY_WIFI_DBDC) || str3.equals("g")) {
                                lottieComposition.u(true);
                                arrayList.add(animatableFloatValue3);
                            }
                            animatableFloatValue2 = animatableFloatValue5;
                        }
                    }
                    AnimatableFloatValue animatableFloatValue6 = animatableFloatValue2;
                    jsonReader.e();
                    if (arrayList.size() == 1) {
                        arrayList.add((AnimatableFloatValue) arrayList.get(0));
                    }
                    animatableFloatValue2 = animatableFloatValue6;
                    continue;
                default:
                    jsonReader.F();
                    jsonReader.G();
                    continue;
            }
            str2 = str;
        }
        String str4 = str2;
        if (animatableIntegerValue == null) {
            animatableIntegerValue = new AnimatableIntegerValue(Collections.singletonList(new Keyframe(100)));
        }
        return new GradientStroke(str4, gradientType, animatableGradientColorValue2, animatableIntegerValue, animatablePointValue, animatablePointValue2, animatableFloatValue, lineCapType, lineJoinType, f2, arrayList, animatableFloatValue2, z);
    }
}
