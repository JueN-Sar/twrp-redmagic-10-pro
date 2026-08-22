package com.airbnb.lottie.parser;

import android.graphics.Color;
import android.graphics.PointF;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
class JsonUtils {

    /* renamed from: a, reason: collision with root package name */
    private static final JsonReader.Options f9832a = JsonReader.Options.a("x", "y");

    /* renamed from: com.airbnb.lottie.parser.JsonUtils$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f9833a;

        static {
            int[] iArr = new int[JsonReader.Token.values().length];
            f9833a = iArr;
            try {
                iArr[JsonReader.Token.NUMBER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9833a[JsonReader.Token.BEGIN_ARRAY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9833a[JsonReader.Token.BEGIN_OBJECT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private static PointF a(JsonReader jsonReader, float f2) {
        jsonReader.c();
        float p2 = (float) jsonReader.p();
        float p3 = (float) jsonReader.p();
        while (jsonReader.C() != JsonReader.Token.END_ARRAY) {
            jsonReader.G();
        }
        jsonReader.e();
        return new PointF(p2 * f2, p3 * f2);
    }

    private static PointF b(JsonReader jsonReader, float f2) {
        float p2 = (float) jsonReader.p();
        float p3 = (float) jsonReader.p();
        while (jsonReader.j()) {
            jsonReader.G();
        }
        return new PointF(p2 * f2, p3 * f2);
    }

    private static PointF c(JsonReader jsonReader, float f2) {
        jsonReader.d();
        float f3 = 0.0f;
        float f4 = 0.0f;
        while (jsonReader.j()) {
            int E = jsonReader.E(f9832a);
            if (E == 0) {
                f3 = g(jsonReader);
            } else if (E != 1) {
                jsonReader.F();
                jsonReader.G();
            } else {
                f4 = g(jsonReader);
            }
        }
        jsonReader.h();
        return new PointF(f3 * f2, f4 * f2);
    }

    static int d(JsonReader jsonReader) {
        jsonReader.c();
        int p2 = (int) (jsonReader.p() * 255.0d);
        int p3 = (int) (jsonReader.p() * 255.0d);
        int p4 = (int) (jsonReader.p() * 255.0d);
        while (jsonReader.j()) {
            jsonReader.G();
        }
        jsonReader.e();
        return Color.argb(255, p2, p3, p4);
    }

    static PointF e(JsonReader jsonReader, float f2) {
        int i2 = AnonymousClass1.f9833a[jsonReader.C().ordinal()];
        if (i2 == 1) {
            return b(jsonReader, f2);
        }
        if (i2 == 2) {
            return a(jsonReader, f2);
        }
        if (i2 == 3) {
            return c(jsonReader, f2);
        }
        throw new IllegalArgumentException("Unknown point starts with " + jsonReader.C());
    }

    static List f(JsonReader jsonReader, float f2) {
        ArrayList arrayList = new ArrayList();
        jsonReader.c();
        while (jsonReader.C() == JsonReader.Token.BEGIN_ARRAY) {
            jsonReader.c();
            arrayList.add(e(jsonReader, f2));
            jsonReader.e();
        }
        jsonReader.e();
        return arrayList;
    }

    static float g(JsonReader jsonReader) {
        JsonReader.Token C = jsonReader.C();
        int i2 = AnonymousClass1.f9833a[C.ordinal()];
        if (i2 == 1) {
            return (float) jsonReader.p();
        }
        if (i2 != 2) {
            throw new IllegalArgumentException("Unknown value for token of type " + C);
        }
        jsonReader.c();
        float p2 = (float) jsonReader.p();
        while (jsonReader.j()) {
            jsonReader.G();
        }
        jsonReader.e();
        return p2;
    }
}
