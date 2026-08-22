package com.airbnb.lottie.parser;

import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.value.ScaleXY;

/* loaded from: classes.dex */
public class ScaleXYParser implements ValueParser<ScaleXY> {

    /* renamed from: a, reason: collision with root package name */
    public static final ScaleXYParser f9854a = new ScaleXYParser();

    private ScaleXYParser() {
    }

    @Override // com.airbnb.lottie.parser.ValueParser
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public ScaleXY a(JsonReader jsonReader, float f2) {
        boolean z = jsonReader.C() == JsonReader.Token.BEGIN_ARRAY;
        if (z) {
            jsonReader.c();
        }
        float p2 = (float) jsonReader.p();
        float p3 = (float) jsonReader.p();
        while (jsonReader.j()) {
            jsonReader.G();
        }
        if (z) {
            jsonReader.e();
        }
        return new ScaleXY((p2 / 100.0f) * f2, (p3 / 100.0f) * f2);
    }
}
