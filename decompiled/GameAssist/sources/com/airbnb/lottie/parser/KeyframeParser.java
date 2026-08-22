package com.airbnb.lottie.parser;

import android.graphics.PointF;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import androidx.collection.SparseArrayCompat;
import androidx.core.view.animation.PathInterpolatorCompat;
import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.Keyframe;
import com.zte.distbus.basetransfer.DistBusKeys;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
class KeyframeParser {

    /* renamed from: b, reason: collision with root package name */
    private static SparseArrayCompat f9835b;

    /* renamed from: a, reason: collision with root package name */
    private static final Interpolator f9834a = new LinearInterpolator();

    /* renamed from: c, reason: collision with root package name */
    static JsonReader.Options f9836c = JsonReader.Options.a("t", "s", DistBusKeys.KEY_PHYSICAL_TYPE, "o", "i", "h", "to", "ti");

    /* renamed from: d, reason: collision with root package name */
    static JsonReader.Options f9837d = JsonReader.Options.a("x", "y");

    private static WeakReference a(int i2) {
        WeakReference weakReference;
        synchronized (KeyframeParser.class) {
            weakReference = (WeakReference) g().e(i2);
        }
        return weakReference;
    }

    private static Interpolator b(PointF pointF, PointF pointF2) {
        Interpolator a2;
        pointF.x = MiscUtils.b(pointF.x, -1.0f, 1.0f);
        pointF.y = MiscUtils.b(pointF.y, -100.0f, 100.0f);
        pointF2.x = MiscUtils.b(pointF2.x, -1.0f, 1.0f);
        float b2 = MiscUtils.b(pointF2.y, -100.0f, 100.0f);
        pointF2.y = b2;
        int i2 = Utils.i(pointF.x, pointF.y, pointF2.x, b2);
        WeakReference a3 = L.e() ? null : a(i2);
        Interpolator interpolator = a3 != null ? (Interpolator) a3.get() : null;
        if (a3 == null || interpolator == null) {
            try {
                a2 = PathInterpolatorCompat.a(pointF.x, pointF.y, pointF2.x, pointF2.y);
            } catch (IllegalArgumentException e2) {
                a2 = "The Path cannot loop back on itself.".equals(e2.getMessage()) ? PathInterpolatorCompat.a(Math.min(pointF.x, 1.0f), pointF.y, Math.max(pointF2.x, 0.0f), pointF2.y) : new LinearInterpolator();
            }
            interpolator = a2;
            if (!L.e()) {
                try {
                    h(i2, new WeakReference(interpolator));
                } catch (ArrayIndexOutOfBoundsException unused) {
                }
            }
        }
        return interpolator;
    }

    static Keyframe c(JsonReader jsonReader, LottieComposition lottieComposition, float f2, ValueParser valueParser, boolean z, boolean z2) {
        return (z && z2) ? e(lottieComposition, jsonReader, f2, valueParser) : z ? d(lottieComposition, jsonReader, f2, valueParser) : f(jsonReader, f2, valueParser);
    }

    private static Keyframe d(LottieComposition lottieComposition, JsonReader jsonReader, float f2, ValueParser valueParser) {
        Interpolator b2;
        Object obj;
        jsonReader.d();
        PointF pointF = null;
        Object obj2 = null;
        Object obj3 = null;
        PointF pointF2 = null;
        PointF pointF3 = null;
        float f3 = 0.0f;
        boolean z = false;
        PointF pointF4 = null;
        while (jsonReader.j()) {
            switch (jsonReader.E(f9836c)) {
                case 0:
                    f3 = (float) jsonReader.p();
                    break;
                case 1:
                    obj3 = valueParser.a(jsonReader, f2);
                    break;
                case 2:
                    obj2 = valueParser.a(jsonReader, f2);
                    break;
                case 3:
                    pointF = JsonUtils.e(jsonReader, 1.0f);
                    break;
                case 4:
                    pointF4 = JsonUtils.e(jsonReader, 1.0f);
                    break;
                case 5:
                    if (jsonReader.s() != 1) {
                        z = false;
                        break;
                    } else {
                        z = true;
                        break;
                    }
                case 6:
                    pointF2 = JsonUtils.e(jsonReader, f2);
                    break;
                case 7:
                    pointF3 = JsonUtils.e(jsonReader, f2);
                    break;
                default:
                    jsonReader.G();
                    break;
            }
        }
        jsonReader.h();
        if (z) {
            b2 = f9834a;
            obj = obj3;
        } else {
            b2 = (pointF == null || pointF4 == null) ? f9834a : b(pointF, pointF4);
            obj = obj2;
        }
        Keyframe keyframe = new Keyframe(lottieComposition, obj3, obj, b2, f3, null);
        keyframe.f9955o = pointF2;
        keyframe.f9956p = pointF3;
        return keyframe;
    }

    private static Keyframe e(LottieComposition lottieComposition, JsonReader jsonReader, float f2, ValueParser valueParser) {
        Interpolator interpolator;
        Interpolator b2;
        Interpolator b3;
        Object obj;
        PointF pointF;
        Keyframe keyframe;
        PointF pointF2;
        float f3;
        PointF pointF3;
        jsonReader.d();
        PointF pointF4 = null;
        boolean z = false;
        PointF pointF5 = null;
        PointF pointF6 = null;
        PointF pointF7 = null;
        Object obj2 = null;
        PointF pointF8 = null;
        PointF pointF9 = null;
        PointF pointF10 = null;
        float f4 = 0.0f;
        PointF pointF11 = null;
        Object obj3 = null;
        while (jsonReader.j()) {
            switch (jsonReader.E(f9836c)) {
                case 0:
                    pointF2 = pointF4;
                    f4 = (float) jsonReader.p();
                    pointF4 = pointF2;
                    break;
                case 1:
                    pointF2 = pointF4;
                    obj2 = valueParser.a(jsonReader, f2);
                    pointF4 = pointF2;
                    break;
                case 2:
                    pointF2 = pointF4;
                    obj3 = valueParser.a(jsonReader, f2);
                    pointF4 = pointF2;
                    break;
                case 3:
                    pointF2 = pointF4;
                    f3 = f4;
                    PointF pointF12 = pointF11;
                    if (jsonReader.C() == JsonReader.Token.BEGIN_OBJECT) {
                        jsonReader.d();
                        float f5 = 0.0f;
                        float f6 = 0.0f;
                        float f7 = 0.0f;
                        float f8 = 0.0f;
                        while (jsonReader.j()) {
                            int E = jsonReader.E(f9837d);
                            if (E == 0) {
                                JsonReader.Token C = jsonReader.C();
                                JsonReader.Token token = JsonReader.Token.NUMBER;
                                if (C == token) {
                                    f7 = (float) jsonReader.p();
                                    f5 = f7;
                                } else {
                                    jsonReader.c();
                                    f5 = (float) jsonReader.p();
                                    f7 = jsonReader.C() == token ? (float) jsonReader.p() : f5;
                                    jsonReader.e();
                                }
                            } else if (E != 1) {
                                jsonReader.G();
                            } else {
                                JsonReader.Token C2 = jsonReader.C();
                                JsonReader.Token token2 = JsonReader.Token.NUMBER;
                                if (C2 == token2) {
                                    f8 = (float) jsonReader.p();
                                    f6 = f8;
                                } else {
                                    jsonReader.c();
                                    f6 = (float) jsonReader.p();
                                    f8 = jsonReader.C() == token2 ? (float) jsonReader.p() : f6;
                                    jsonReader.e();
                                }
                            }
                        }
                        PointF pointF13 = new PointF(f5, f6);
                        PointF pointF14 = new PointF(f7, f8);
                        jsonReader.h();
                        pointF8 = pointF14;
                        pointF7 = pointF13;
                        pointF11 = pointF12;
                        f4 = f3;
                        pointF4 = pointF2;
                        break;
                    } else {
                        pointF5 = JsonUtils.e(jsonReader, f2);
                        f4 = f3;
                        pointF11 = pointF12;
                        pointF4 = pointF2;
                    }
                case 4:
                    if (jsonReader.C() == JsonReader.Token.BEGIN_OBJECT) {
                        jsonReader.d();
                        float f9 = 0.0f;
                        float f10 = 0.0f;
                        float f11 = 0.0f;
                        float f12 = 0.0f;
                        while (jsonReader.j()) {
                            PointF pointF15 = pointF11;
                            int E2 = jsonReader.E(f9837d);
                            if (E2 != 0) {
                                pointF3 = pointF4;
                                if (E2 != 1) {
                                    jsonReader.G();
                                } else {
                                    JsonReader.Token C3 = jsonReader.C();
                                    JsonReader.Token token3 = JsonReader.Token.NUMBER;
                                    if (C3 == token3) {
                                        f12 = (float) jsonReader.p();
                                        f4 = f4;
                                        f10 = f12;
                                    } else {
                                        float f13 = f4;
                                        jsonReader.c();
                                        float p2 = (float) jsonReader.p();
                                        float p3 = jsonReader.C() == token3 ? (float) jsonReader.p() : p2;
                                        jsonReader.e();
                                        f4 = f13;
                                        pointF11 = pointF15;
                                        pointF4 = pointF3;
                                        f12 = p3;
                                        f10 = p2;
                                    }
                                }
                            } else {
                                pointF3 = pointF4;
                                float f14 = f4;
                                JsonReader.Token C4 = jsonReader.C();
                                JsonReader.Token token4 = JsonReader.Token.NUMBER;
                                if (C4 == token4) {
                                    f11 = (float) jsonReader.p();
                                    f4 = f14;
                                    f9 = f11;
                                } else {
                                    jsonReader.c();
                                    f9 = (float) jsonReader.p();
                                    f11 = jsonReader.C() == token4 ? (float) jsonReader.p() : f9;
                                    jsonReader.e();
                                    f4 = f14;
                                }
                            }
                            pointF11 = pointF15;
                            pointF4 = pointF3;
                        }
                        pointF2 = pointF4;
                        f3 = f4;
                        PointF pointF16 = new PointF(f9, f10);
                        PointF pointF17 = new PointF(f11, f12);
                        jsonReader.h();
                        pointF10 = pointF17;
                        pointF9 = pointF16;
                        f4 = f3;
                        pointF4 = pointF2;
                        break;
                    } else {
                        pointF2 = pointF4;
                        pointF6 = JsonUtils.e(jsonReader, f2);
                        pointF4 = pointF2;
                    }
                case 5:
                    if (jsonReader.s() != 1) {
                        z = false;
                        break;
                    } else {
                        z = true;
                        break;
                    }
                case 6:
                    pointF11 = JsonUtils.e(jsonReader, f2);
                    break;
                case 7:
                    pointF4 = JsonUtils.e(jsonReader, f2);
                    break;
                default:
                    jsonReader.G();
                    break;
            }
        }
        PointF pointF18 = pointF4;
        float f15 = f4;
        PointF pointF19 = pointF11;
        jsonReader.h();
        if (z) {
            interpolator = f9834a;
            obj = obj2;
        } else {
            if (pointF5 != null && pointF6 != null) {
                interpolator = b(pointF5, pointF6);
            } else {
                if (pointF7 != null && pointF8 != null && pointF9 != null && pointF10 != null) {
                    b2 = b(pointF7, pointF9);
                    b3 = b(pointF8, pointF10);
                    obj = obj3;
                    interpolator = null;
                    if (b2 != null || b3 == null) {
                        pointF = pointF19;
                        keyframe = new Keyframe(lottieComposition, obj2, obj, interpolator, f15, null);
                    } else {
                        pointF = pointF19;
                        keyframe = new Keyframe(lottieComposition, obj2, obj, b2, b3, f15, null);
                    }
                    keyframe.f9955o = pointF;
                    keyframe.f9956p = pointF18;
                    return keyframe;
                }
                interpolator = f9834a;
            }
            obj = obj3;
        }
        b2 = null;
        b3 = null;
        if (b2 != null) {
        }
        pointF = pointF19;
        keyframe = new Keyframe(lottieComposition, obj2, obj, interpolator, f15, null);
        keyframe.f9955o = pointF;
        keyframe.f9956p = pointF18;
        return keyframe;
    }

    private static Keyframe f(JsonReader jsonReader, float f2, ValueParser valueParser) {
        return new Keyframe(valueParser.a(jsonReader, f2));
    }

    private static SparseArrayCompat g() {
        if (f9835b == null) {
            f9835b = new SparseArrayCompat();
        }
        return f9835b;
    }

    private static void h(int i2, WeakReference weakReference) {
        synchronized (KeyframeParser.class) {
            f9835b.i(i2, weakReference);
        }
    }
}
