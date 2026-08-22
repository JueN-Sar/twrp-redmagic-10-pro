package com.airbnb.lottie.parser;

import android.graphics.PointF;
import com.airbnb.lottie.parser.moshi.JsonReader;

/* loaded from: classes.dex */
public class PathParser implements ValueParser<PointF> {

    /* renamed from: a, reason: collision with root package name */
    public static final PathParser f9848a = new PathParser();

    private PathParser() {
    }

    @Override // com.airbnb.lottie.parser.ValueParser
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public PointF a(JsonReader jsonReader, float f2) {
        return JsonUtils.e(jsonReader, f2);
    }
}
