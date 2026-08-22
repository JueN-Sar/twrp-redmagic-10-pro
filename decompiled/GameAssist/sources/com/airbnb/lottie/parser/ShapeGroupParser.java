package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.content.ContentModel;
import com.airbnb.lottie.model.content.ShapeGroup;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.util.ArrayList;

/* loaded from: classes.dex */
class ShapeGroupParser {

    /* renamed from: a, reason: collision with root package name */
    private static final JsonReader.Options f9858a = JsonReader.Options.a("nm", "hd", "it");

    static ShapeGroup a(JsonReader jsonReader, LottieComposition lottieComposition) {
        ArrayList arrayList = new ArrayList();
        String str = null;
        boolean z = false;
        while (jsonReader.j()) {
            int E = jsonReader.E(f9858a);
            if (E == 0) {
                str = jsonReader.A();
            } else if (E == 1) {
                z = jsonReader.k();
            } else if (E != 2) {
                jsonReader.G();
            } else {
                jsonReader.c();
                while (jsonReader.j()) {
                    ContentModel a2 = ContentModelParser.a(jsonReader, lottieComposition);
                    if (a2 != null) {
                        arrayList.add(a2);
                    }
                }
                jsonReader.e();
            }
        }
        return new ShapeGroup(str, arrayList, z);
    }
}
