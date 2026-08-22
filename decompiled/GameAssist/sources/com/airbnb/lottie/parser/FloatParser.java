package com.airbnb.lottie.parser;

import com.airbnb.lottie.parser.moshi.JsonReader;

/* loaded from: classes.dex */
public class FloatParser implements ValueParser<Float> {

    /* renamed from: a, reason: collision with root package name */
    public static final FloatParser f9821a = new FloatParser();

    private FloatParser() {
    }

    @Override // com.airbnb.lottie.parser.ValueParser
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public Float a(JsonReader jsonReader, float f2) {
        return Float.valueOf(JsonUtils.g(jsonReader) * f2);
    }
}
