package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.FontCharacter;
import com.airbnb.lottie.model.content.ShapeGroup;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.util.ArrayList;

/* loaded from: classes.dex */
class FontCharacterParser {

    /* renamed from: a, reason: collision with root package name */
    private static final JsonReader.Options f9822a = JsonReader.Options.a("ch", "size", "w", "style", "fFamily", "data");

    /* renamed from: b, reason: collision with root package name */
    private static final JsonReader.Options f9823b = JsonReader.Options.a("shapes");

    static FontCharacter a(JsonReader jsonReader, LottieComposition lottieComposition) {
        ArrayList arrayList = new ArrayList();
        jsonReader.d();
        double d2 = 0.0d;
        String str = null;
        String str2 = null;
        char c2 = 0;
        double d3 = 0.0d;
        while (jsonReader.j()) {
            int E = jsonReader.E(f9822a);
            if (E == 0) {
                c2 = jsonReader.A().charAt(0);
            } else if (E == 1) {
                d3 = jsonReader.p();
            } else if (E == 2) {
                d2 = jsonReader.p();
            } else if (E == 3) {
                str = jsonReader.A();
            } else if (E == 4) {
                str2 = jsonReader.A();
            } else if (E != 5) {
                jsonReader.F();
                jsonReader.G();
            } else {
                jsonReader.d();
                while (jsonReader.j()) {
                    if (jsonReader.E(f9823b) != 0) {
                        jsonReader.F();
                        jsonReader.G();
                    } else {
                        jsonReader.c();
                        while (jsonReader.j()) {
                            arrayList.add((ShapeGroup) ContentModelParser.a(jsonReader, lottieComposition));
                        }
                        jsonReader.e();
                    }
                }
                jsonReader.h();
            }
        }
        jsonReader.h();
        return new FontCharacter(arrayList, c2, d3, d2, str, str2);
    }
}
