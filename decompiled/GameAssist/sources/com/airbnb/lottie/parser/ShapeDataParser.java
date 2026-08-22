package com.airbnb.lottie.parser;

import android.graphics.PointF;
import com.airbnb.lottie.model.CubicCurveData;
import com.airbnb.lottie.model.content.ShapeData;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.MiscUtils;
import com.zte.distbus.basetransfer.DistBusKeys;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class ShapeDataParser implements ValueParser<ShapeData> {

    /* renamed from: a, reason: collision with root package name */
    public static final ShapeDataParser f9855a = new ShapeDataParser();

    /* renamed from: b, reason: collision with root package name */
    private static final JsonReader.Options f9856b = JsonReader.Options.a(DistBusKeys.KEY_WIFI_FREQUENCY, "v", "i", "o");

    private ShapeDataParser() {
    }

    @Override // com.airbnb.lottie.parser.ValueParser
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public ShapeData a(JsonReader jsonReader, float f2) {
        if (jsonReader.C() == JsonReader.Token.BEGIN_ARRAY) {
            jsonReader.c();
        }
        jsonReader.d();
        List list = null;
        List list2 = null;
        List list3 = null;
        boolean z = false;
        while (jsonReader.j()) {
            int E = jsonReader.E(f9856b);
            if (E == 0) {
                z = jsonReader.k();
            } else if (E == 1) {
                list = JsonUtils.f(jsonReader, f2);
            } else if (E == 2) {
                list2 = JsonUtils.f(jsonReader, f2);
            } else if (E != 3) {
                jsonReader.F();
                jsonReader.G();
            } else {
                list3 = JsonUtils.f(jsonReader, f2);
            }
        }
        jsonReader.h();
        if (jsonReader.C() == JsonReader.Token.END_ARRAY) {
            jsonReader.e();
        }
        if (list == null || list2 == null || list3 == null) {
            throw new IllegalArgumentException("Shape data was missing information.");
        }
        if (list.isEmpty()) {
            return new ShapeData(new PointF(), false, Collections.emptyList());
        }
        int size = list.size();
        PointF pointF = (PointF) list.get(0);
        ArrayList arrayList = new ArrayList(size);
        for (int i2 = 1; i2 < size; i2++) {
            PointF pointF2 = (PointF) list.get(i2);
            int i3 = i2 - 1;
            arrayList.add(new CubicCurveData(MiscUtils.a((PointF) list.get(i3), (PointF) list3.get(i3)), MiscUtils.a(pointF2, (PointF) list2.get(i2)), pointF2));
        }
        if (z) {
            PointF pointF3 = (PointF) list.get(0);
            int i4 = size - 1;
            arrayList.add(new CubicCurveData(MiscUtils.a((PointF) list.get(i4), (PointF) list3.get(i4)), MiscUtils.a(pointF3, (PointF) list2.get(0)), pointF3));
        }
        return new ShapeData(pointF, z, arrayList);
    }
}
