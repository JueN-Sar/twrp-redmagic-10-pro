package com.airbnb.lottie.parser;

import android.graphics.Rect;
import androidx.collection.LongSparseArray;
import androidx.collection.SparseArrayCompat;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieImageAsset;
import com.airbnb.lottie.model.Font;
import com.airbnb.lottie.model.FontCharacter;
import com.airbnb.lottie.model.Marker;
import com.airbnb.lottie.model.layer.Layer;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.Utils;
import com.zte.distbus.basetransfer.Constants;
import com.zte.shared.wrapper.VirtualHandleWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class LottieCompositionMoshiParser {

    /* renamed from: a, reason: collision with root package name */
    private static final JsonReader.Options f9843a = JsonReader.Options.a("w", "h", "ip", "op", "fr", "v", "layers", "assets", "fonts", "chars", "markers");

    /* renamed from: b, reason: collision with root package name */
    static JsonReader.Options f9844b = JsonReader.Options.a(VirtualHandleWrapper.KEY_ID, "layers", "w", "h", "p", "u");

    /* renamed from: c, reason: collision with root package name */
    private static final JsonReader.Options f9845c = JsonReader.Options.a(Constants.EXTRA_LIST);

    /* renamed from: d, reason: collision with root package name */
    private static final JsonReader.Options f9846d = JsonReader.Options.a("cm", "tm", "dr");

    public static LottieComposition a(JsonReader jsonReader) {
        HashMap hashMap;
        ArrayList arrayList;
        JsonReader jsonReader2 = jsonReader;
        float e2 = Utils.e();
        LongSparseArray longSparseArray = new LongSparseArray();
        ArrayList arrayList2 = new ArrayList();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        HashMap hashMap4 = new HashMap();
        ArrayList arrayList3 = new ArrayList();
        SparseArrayCompat sparseArrayCompat = new SparseArrayCompat();
        LottieComposition lottieComposition = new LottieComposition();
        jsonReader.d();
        float f2 = 0.0f;
        float f3 = 0.0f;
        float f4 = 0.0f;
        int i2 = 0;
        int i3 = 0;
        while (jsonReader.j()) {
            switch (jsonReader2.E(f9843a)) {
                case 0:
                    i2 = jsonReader.s();
                    continue;
                case 1:
                    i3 = jsonReader.s();
                    continue;
                case 2:
                    f2 = (float) jsonReader.p();
                    continue;
                case 3:
                    hashMap = hashMap4;
                    arrayList = arrayList3;
                    f3 = ((float) jsonReader.p()) - 0.01f;
                    break;
                case 4:
                    hashMap = hashMap4;
                    arrayList = arrayList3;
                    f4 = (float) jsonReader.p();
                    break;
                case 5:
                    String[] split = jsonReader.A().split("\\.");
                    if (Utils.j(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]), 4, 4, 0)) {
                        break;
                    } else {
                        lottieComposition.a("Lottie only supports bodymovin >= 4.4.0");
                        continue;
                    }
                case 6:
                    e(jsonReader2, lottieComposition, arrayList2, longSparseArray);
                    continue;
                case 7:
                    b(jsonReader2, lottieComposition, hashMap2, hashMap3);
                    continue;
                case 8:
                    d(jsonReader2, hashMap4);
                    continue;
                case 9:
                    c(jsonReader2, lottieComposition, sparseArrayCompat);
                    continue;
                case 10:
                    f(jsonReader2, arrayList3);
                    continue;
                default:
                    jsonReader.F();
                    jsonReader.G();
                    continue;
            }
            hashMap4 = hashMap;
            arrayList3 = arrayList;
            jsonReader2 = jsonReader;
        }
        lottieComposition.s(new Rect(0, 0, (int) (i2 * e2), (int) (i3 * e2)), f2, f3, f4, arrayList2, longSparseArray, hashMap2, hashMap3, Utils.e(), sparseArrayCompat, hashMap4, arrayList3);
        return lottieComposition;
    }

    private static void b(JsonReader jsonReader, LottieComposition lottieComposition, Map map, Map map2) {
        jsonReader.c();
        while (jsonReader.j()) {
            ArrayList arrayList = new ArrayList();
            LongSparseArray longSparseArray = new LongSparseArray();
            jsonReader.d();
            int i2 = 0;
            int i3 = 0;
            String str = null;
            String str2 = null;
            String str3 = null;
            while (jsonReader.j()) {
                int E = jsonReader.E(f9844b);
                if (E == 0) {
                    str = jsonReader.A();
                } else if (E == 1) {
                    jsonReader.c();
                    while (jsonReader.j()) {
                        Layer b2 = LayerParser.b(jsonReader, lottieComposition);
                        longSparseArray.k(b2.e(), b2);
                        arrayList.add(b2);
                    }
                    jsonReader.e();
                } else if (E == 2) {
                    i2 = jsonReader.s();
                } else if (E == 3) {
                    i3 = jsonReader.s();
                } else if (E == 4) {
                    str2 = jsonReader.A();
                } else if (E != 5) {
                    jsonReader.F();
                    jsonReader.G();
                } else {
                    str3 = jsonReader.A();
                }
            }
            jsonReader.h();
            if (str2 != null) {
                LottieImageAsset lottieImageAsset = new LottieImageAsset(i2, i3, str, str2, str3);
                map2.put(lottieImageAsset.e(), lottieImageAsset);
            } else {
                map.put(str, arrayList);
            }
        }
        jsonReader.e();
    }

    private static void c(JsonReader jsonReader, LottieComposition lottieComposition, SparseArrayCompat sparseArrayCompat) {
        jsonReader.c();
        while (jsonReader.j()) {
            FontCharacter a2 = FontCharacterParser.a(jsonReader, lottieComposition);
            sparseArrayCompat.i(a2.hashCode(), a2);
        }
        jsonReader.e();
    }

    private static void d(JsonReader jsonReader, Map map) {
        jsonReader.d();
        while (jsonReader.j()) {
            if (jsonReader.E(f9845c) != 0) {
                jsonReader.F();
                jsonReader.G();
            } else {
                jsonReader.c();
                while (jsonReader.j()) {
                    Font a2 = FontParser.a(jsonReader);
                    map.put(a2.b(), a2);
                }
                jsonReader.e();
            }
        }
        jsonReader.h();
    }

    private static void e(JsonReader jsonReader, LottieComposition lottieComposition, List list, LongSparseArray longSparseArray) {
        jsonReader.c();
        int i2 = 0;
        while (jsonReader.j()) {
            Layer b2 = LayerParser.b(jsonReader, lottieComposition);
            if (b2.g() == Layer.LayerType.IMAGE) {
                i2++;
            }
            list.add(b2);
            longSparseArray.k(b2.e(), b2);
            if (i2 > 4) {
                Logger.c("You have " + i2 + " images. Lottie should primarily be used with shapes. If you are using Adobe Illustrator, convert the Illustrator layers to shape layers.");
            }
        }
        jsonReader.e();
    }

    private static void f(JsonReader jsonReader, List list) {
        jsonReader.c();
        while (jsonReader.j()) {
            jsonReader.d();
            float f2 = 0.0f;
            String str = null;
            float f3 = 0.0f;
            while (jsonReader.j()) {
                int E = jsonReader.E(f9846d);
                if (E == 0) {
                    str = jsonReader.A();
                } else if (E == 1) {
                    f2 = (float) jsonReader.p();
                } else if (E != 2) {
                    jsonReader.F();
                    jsonReader.G();
                } else {
                    f3 = (float) jsonReader.p();
                }
            }
            jsonReader.h();
            list.add(new Marker(str, f2, f3));
        }
        jsonReader.e();
    }
}
