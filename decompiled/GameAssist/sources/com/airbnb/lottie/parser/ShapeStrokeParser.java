package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableColorValue;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.content.ShapeStroke;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.value.Keyframe;
import com.zte.distbus.basetransfer.DistBusKeys;
import java.util.ArrayList;
import java.util.Collections;

/* loaded from: classes.dex */
class ShapeStrokeParser {

    /* renamed from: a, reason: collision with root package name */
    private static final JsonReader.Options f9860a = JsonReader.Options.a("nm", DistBusKeys.KEY_WIFI_FREQUENCY, "w", "o", "lc", "lj", "ml", "hd", DistBusKeys.KEY_WIFI_DBDC);

    /* renamed from: b, reason: collision with root package name */
    private static final JsonReader.Options f9861b = JsonReader.Options.a("n", "v");

    static ShapeStroke a(JsonReader jsonReader, LottieComposition lottieComposition) {
        AnimatableFloatValue animatableFloatValue;
        ArrayList arrayList = new ArrayList();
        float f2 = 0.0f;
        boolean z = false;
        String str = null;
        AnimatableFloatValue animatableFloatValue2 = null;
        AnimatableColorValue animatableColorValue = null;
        AnimatableFloatValue animatableFloatValue3 = null;
        AnimatableIntegerValue animatableIntegerValue = null;
        ShapeStroke.LineCapType lineCapType = null;
        ShapeStroke.LineJoinType lineJoinType = null;
        while (jsonReader.j()) {
            switch (jsonReader.E(f9860a)) {
                case 0:
                    str = jsonReader.A();
                    break;
                case 1:
                    animatableColorValue = AnimatableValueParser.c(jsonReader, lottieComposition);
                    break;
                case 2:
                    animatableFloatValue3 = AnimatableValueParser.e(jsonReader, lottieComposition);
                    break;
                case 3:
                    animatableIntegerValue = AnimatableValueParser.h(jsonReader, lottieComposition);
                    break;
                case 4:
                    lineCapType = ShapeStroke.LineCapType.values()[jsonReader.s() - 1];
                    break;
                case 5:
                    lineJoinType = ShapeStroke.LineJoinType.values()[jsonReader.s() - 1];
                    break;
                case 6:
                    f2 = (float) jsonReader.p();
                    break;
                case 7:
                    z = jsonReader.k();
                    break;
                case 8:
                    jsonReader.c();
                    while (jsonReader.j()) {
                        jsonReader.d();
                        String str2 = null;
                        animatableFloatValue = null;
                        while (jsonReader.j()) {
                            int E = jsonReader.E(f9861b);
                            if (E == 0) {
                                str2 = jsonReader.A();
                            } else if (E != 1) {
                                jsonReader.F();
                                jsonReader.G();
                            } else {
                                animatableFloatValue = AnimatableValueParser.e(jsonReader, lottieComposition);
                            }
                        }
                        jsonReader.h();
                        str2.hashCode();
                        switch (str2) {
                            case "d":
                            case "g":
                                lottieComposition.u(true);
                                arrayList.add(animatableFloatValue);
                                break;
                            case "o":
                                animatableFloatValue2 = animatableFloatValue;
                                break;
                        }
                    }
                    jsonReader.e();
                    if (arrayList.size() != 1) {
                        break;
                    } else {
                        arrayList.add((AnimatableFloatValue) arrayList.get(0));
                        break;
                    }
                    break;
                default:
                    jsonReader.G();
                    break;
            }
        }
        if (animatableIntegerValue == null) {
            animatableIntegerValue = new AnimatableIntegerValue(Collections.singletonList(new Keyframe(100)));
        }
        if (lineCapType == null) {
            lineCapType = ShapeStroke.LineCapType.BUTT;
        }
        if (lineJoinType == null) {
            lineJoinType = ShapeStroke.LineJoinType.MITER;
        }
        return new ShapeStroke(str, animatableFloatValue2, arrayList, animatableColorValue, animatableIntegerValue, animatableFloatValue3, lineCapType, lineJoinType, f2, z);
    }
}
