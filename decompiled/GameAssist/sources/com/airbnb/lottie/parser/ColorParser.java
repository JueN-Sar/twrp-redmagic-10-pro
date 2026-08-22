package com.airbnb.lottie.parser;

import android.graphics.Color;
import com.airbnb.lottie.parser.moshi.JsonReader;

/* loaded from: classes.dex */
public class ColorParser implements ValueParser<Integer> {

    /* renamed from: a, reason: collision with root package name */
    public static final ColorParser f9805a = new ColorParser();

    private ColorParser() {
    }

    @Override // com.airbnb.lottie.parser.ValueParser
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public Integer a(JsonReader jsonReader, float f2) {
        boolean z = jsonReader.C() == JsonReader.Token.BEGIN_ARRAY;
        if (z) {
            jsonReader.c();
        }
        double p2 = jsonReader.p();
        double p3 = jsonReader.p();
        double p4 = jsonReader.p();
        double p5 = jsonReader.C() == JsonReader.Token.NUMBER ? jsonReader.p() : 1.0d;
        if (z) {
            jsonReader.e();
        }
        if (p2 <= 1.0d && p3 <= 1.0d && p4 <= 1.0d) {
            p2 *= 255.0d;
            p3 *= 255.0d;
            p4 *= 255.0d;
            if (p5 <= 1.0d) {
                p5 *= 255.0d;
            }
        }
        return Integer.valueOf(Color.argb((int) p5, (int) p2, (int) p3, (int) p4));
    }
}
