package com.airbnb.lottie.parser;

import android.graphics.PointF;
import com.airbnb.lottie.parser.moshi.JsonReader;

/* loaded from: classes.dex */
public class PointFParser implements ValueParser<PointF> {

    /* renamed from: a, reason: collision with root package name */
    public static final PointFParser f9849a = new PointFParser();

    private PointFParser() {
    }

    @Override // com.airbnb.lottie.parser.ValueParser
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public PointF a(JsonReader jsonReader, float f2) {
        JsonReader.Token C = jsonReader.C();
        if (C != JsonReader.Token.BEGIN_ARRAY && C != JsonReader.Token.BEGIN_OBJECT) {
            if (C == JsonReader.Token.NUMBER) {
                PointF pointF = new PointF(((float) jsonReader.p()) * f2, ((float) jsonReader.p()) * f2);
                while (jsonReader.j()) {
                    jsonReader.G();
                }
                return pointF;
            }
            throw new IllegalArgumentException("Cannot convert json to point. Next token is " + C);
        }
        return JsonUtils.e(jsonReader, f2);
    }
}
