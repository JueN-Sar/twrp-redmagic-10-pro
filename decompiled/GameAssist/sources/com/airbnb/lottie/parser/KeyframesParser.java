package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.animation.keyframe.PathKeyframe;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.value.Keyframe;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
class KeyframesParser {

    /* renamed from: a, reason: collision with root package name */
    static JsonReader.Options f9838a = JsonReader.Options.a("k");

    static List a(JsonReader jsonReader, LottieComposition lottieComposition, float f2, ValueParser valueParser, boolean z) {
        ArrayList arrayList = new ArrayList();
        if (jsonReader.C() == JsonReader.Token.STRING) {
            lottieComposition.a("Lottie doesn't support expressions.");
            return arrayList;
        }
        jsonReader.d();
        while (jsonReader.j()) {
            if (jsonReader.E(f9838a) != 0) {
                jsonReader.G();
            } else if (jsonReader.C() == JsonReader.Token.BEGIN_ARRAY) {
                jsonReader.c();
                if (jsonReader.C() == JsonReader.Token.NUMBER) {
                    arrayList.add(KeyframeParser.c(jsonReader, lottieComposition, f2, valueParser, false, z));
                } else {
                    while (jsonReader.j()) {
                        arrayList.add(KeyframeParser.c(jsonReader, lottieComposition, f2, valueParser, true, z));
                    }
                }
                jsonReader.e();
            } else {
                arrayList.add(KeyframeParser.c(jsonReader, lottieComposition, f2, valueParser, false, z));
            }
        }
        jsonReader.h();
        b(arrayList);
        return arrayList;
    }

    public static void b(List list) {
        int i2;
        Object obj;
        int size = list.size();
        int i3 = 0;
        while (true) {
            i2 = size - 1;
            if (i3 >= i2) {
                break;
            }
            Keyframe keyframe = (Keyframe) list.get(i3);
            i3++;
            Keyframe keyframe2 = (Keyframe) list.get(i3);
            keyframe.f9948h = Float.valueOf(keyframe2.f9947g);
            if (keyframe.f9943c == null && (obj = keyframe2.f9942b) != null) {
                keyframe.f9943c = obj;
                if (keyframe instanceof PathKeyframe) {
                    ((PathKeyframe) keyframe).j();
                }
            }
        }
        Keyframe keyframe3 = (Keyframe) list.get(i2);
        if ((keyframe3.f9942b == null || keyframe3.f9943c == null) && list.size() > 1) {
            list.remove(keyframe3);
        }
    }
}
