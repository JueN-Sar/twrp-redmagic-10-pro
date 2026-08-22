package com.airbnb.lottie.parser;

import com.airbnb.lottie.parser.moshi.JsonReader;

/* loaded from: classes.dex */
public class IntegerParser implements ValueParser<Integer> {

    /* renamed from: a, reason: collision with root package name */
    public static final IntegerParser f9831a = new IntegerParser();

    private IntegerParser() {
    }

    @Override // com.airbnb.lottie.parser.ValueParser
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public Integer a(JsonReader jsonReader, float f2) {
        return Integer.valueOf(Math.round(JsonUtils.g(jsonReader) * f2));
    }
}
