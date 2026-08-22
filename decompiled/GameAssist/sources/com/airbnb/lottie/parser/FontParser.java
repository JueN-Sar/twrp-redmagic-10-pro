package com.airbnb.lottie.parser;

import com.airbnb.lottie.model.Font;
import com.airbnb.lottie.parser.moshi.JsonReader;

/* loaded from: classes.dex */
class FontParser {

    /* renamed from: a, reason: collision with root package name */
    private static final JsonReader.Options f9824a = JsonReader.Options.a("fFamily", "fName", "fStyle", "ascent");

    static Font a(JsonReader jsonReader) {
        jsonReader.d();
        String str = null;
        String str2 = null;
        float f2 = 0.0f;
        String str3 = null;
        while (jsonReader.j()) {
            int E = jsonReader.E(f9824a);
            if (E == 0) {
                str = jsonReader.A();
            } else if (E == 1) {
                str3 = jsonReader.A();
            } else if (E == 2) {
                str2 = jsonReader.A();
            } else if (E != 3) {
                jsonReader.F();
                jsonReader.G();
            } else {
                f2 = (float) jsonReader.p();
            }
        }
        jsonReader.h();
        return new Font(str, str3, str2, f2);
    }
}
