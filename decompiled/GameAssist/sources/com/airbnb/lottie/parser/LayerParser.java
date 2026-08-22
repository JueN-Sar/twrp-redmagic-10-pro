package com.airbnb.lottie.parser;

import android.graphics.Color;
import android.graphics.Rect;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableTextFrame;
import com.airbnb.lottie.model.animatable.AnimatableTextProperties;
import com.airbnb.lottie.model.animatable.AnimatableTransform;
import com.airbnb.lottie.model.content.BlurEffect;
import com.airbnb.lottie.model.content.ContentModel;
import com.airbnb.lottie.model.content.LBlendMode;
import com.airbnb.lottie.model.layer.Layer;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.Keyframe;
import com.google.mlkit.common.MlKitException;
import com.zte.distbus.basetransfer.DistBusKeys;
import com.zte.distbus.basetransfer.Status;
import java.util.ArrayList;
import java.util.Collections;

/* loaded from: classes.dex */
public class LayerParser {

    /* renamed from: a, reason: collision with root package name */
    private static final JsonReader.Options f9839a = JsonReader.Options.a("nm", "ind", "refId", "ty", "parent", "sw", "sh", "sc", "ks", "tt", "masksProperties", "shapes", "t", "ef", "sr", "st", "w", "h", "ip", "op", "tm", "cl", "hd", "ao", "bm");

    /* renamed from: b, reason: collision with root package name */
    private static final JsonReader.Options f9840b = JsonReader.Options.a(DistBusKeys.KEY_WIFI_DBDC, DistBusKeys.KEY_WIFI_ENABLE);

    /* renamed from: c, reason: collision with root package name */
    private static final JsonReader.Options f9841c = JsonReader.Options.a("ty", "nm");

    /* renamed from: com.airbnb.lottie.parser.LayerParser$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f9842a;

        static {
            int[] iArr = new int[Layer.MatteType.values().length];
            f9842a = iArr;
            try {
                iArr[Layer.MatteType.LUMA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9842a[Layer.MatteType.LUMA_INVERTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public static Layer a(LottieComposition lottieComposition) {
        Rect b2 = lottieComposition.b();
        return new Layer(Collections.emptyList(), lottieComposition, "__container", -1L, Layer.LayerType.PRE_COMP, -1L, null, Collections.emptyList(), new AnimatableTransform(), 0, 0, 0, 0.0f, 0.0f, b2.width(), b2.height(), null, null, Collections.emptyList(), Layer.MatteType.NONE, null, false, null, null, LBlendMode.NORMAL);
    }

    public static Layer b(JsonReader jsonReader, LottieComposition lottieComposition) {
        ArrayList arrayList;
        boolean z;
        float f2;
        Layer.MatteType matteType = Layer.MatteType.NONE;
        LBlendMode lBlendMode = LBlendMode.NORMAL;
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        jsonReader.d();
        Float valueOf = Float.valueOf(0.0f);
        Float valueOf2 = Float.valueOf(1.0f);
        Layer.MatteType matteType2 = matteType;
        LBlendMode lBlendMode2 = lBlendMode;
        Layer.LayerType layerType = null;
        String str = null;
        AnimatableTextFrame animatableTextFrame = null;
        AnimatableTextProperties animatableTextProperties = null;
        AnimatableFloatValue animatableFloatValue = null;
        BlurEffect blurEffect = null;
        DropShadowEffect dropShadowEffect = null;
        long j2 = 0;
        boolean z2 = false;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        boolean z3 = false;
        float f3 = 0.0f;
        float f4 = 0.0f;
        float f5 = 0.0f;
        float f6 = 0.0f;
        float f7 = 0.0f;
        long j3 = -1;
        float f8 = 1.0f;
        String str2 = "UNSET";
        String str3 = null;
        AnimatableTransform animatableTransform = null;
        while (jsonReader.j()) {
            switch (jsonReader.E(f9839a)) {
                case 0:
                    str2 = jsonReader.A();
                    break;
                case 1:
                    j2 = jsonReader.s();
                    break;
                case 2:
                    str = jsonReader.A();
                    break;
                case 3:
                    int s2 = jsonReader.s();
                    layerType = Layer.LayerType.UNKNOWN;
                    if (s2 >= layerType.ordinal()) {
                        break;
                    } else {
                        layerType = Layer.LayerType.values()[s2];
                        break;
                    }
                case 4:
                    j3 = jsonReader.s();
                    break;
                case 5:
                    i2 = (int) (jsonReader.s() * Utils.e());
                    break;
                case 6:
                    i3 = (int) (jsonReader.s() * Utils.e());
                    break;
                case 7:
                    i4 = Color.parseColor(jsonReader.A());
                    break;
                case 8:
                    animatableTransform = AnimatableTransformParser.g(jsonReader, lottieComposition);
                    break;
                case 9:
                    int s3 = jsonReader.s();
                    if (s3 < Layer.MatteType.values().length) {
                        matteType2 = Layer.MatteType.values()[s3];
                        int i5 = AnonymousClass1.f9842a[matteType2.ordinal()];
                        if (i5 == 1) {
                            lottieComposition.a("Unsupported matte type: Luma");
                        } else if (i5 == 2) {
                            lottieComposition.a("Unsupported matte type: Luma Inverted");
                        }
                        lottieComposition.r(1);
                        break;
                    } else {
                        lottieComposition.a("Unsupported matte type: " + s3);
                        break;
                    }
                case 10:
                    jsonReader.c();
                    while (jsonReader.j()) {
                        arrayList2.add(MaskParser.a(jsonReader, lottieComposition));
                    }
                    lottieComposition.r(arrayList2.size());
                    jsonReader.e();
                    break;
                case 11:
                    jsonReader.c();
                    while (jsonReader.j()) {
                        ContentModel a2 = ContentModelParser.a(jsonReader, lottieComposition);
                        if (a2 != null) {
                            arrayList3.add(a2);
                        }
                    }
                    jsonReader.e();
                    break;
                case 12:
                    jsonReader.d();
                    while (jsonReader.j()) {
                        int E = jsonReader.E(f9840b);
                        if (E == 0) {
                            animatableTextFrame = AnimatableValueParser.d(jsonReader, lottieComposition);
                        } else if (E != 1) {
                            jsonReader.F();
                            jsonReader.G();
                        } else {
                            jsonReader.c();
                            if (jsonReader.j()) {
                                animatableTextProperties = AnimatableTextPropertiesParser.a(jsonReader, lottieComposition);
                            }
                            while (jsonReader.j()) {
                                jsonReader.G();
                            }
                            jsonReader.e();
                        }
                    }
                    jsonReader.h();
                    break;
                case 13:
                    jsonReader.c();
                    ArrayList arrayList4 = new ArrayList();
                    while (jsonReader.j()) {
                        jsonReader.d();
                        while (jsonReader.j()) {
                            int E2 = jsonReader.E(f9841c);
                            if (E2 == 0) {
                                int s4 = jsonReader.s();
                                if (s4 == 29) {
                                    blurEffect = BlurEffectParser.b(jsonReader, lottieComposition);
                                } else if (s4 == 25) {
                                    dropShadowEffect = new DropShadowEffectParser().b(jsonReader, lottieComposition);
                                }
                            } else if (E2 != 1) {
                                jsonReader.F();
                                jsonReader.G();
                            } else {
                                arrayList4.add(jsonReader.A());
                            }
                        }
                        jsonReader.h();
                    }
                    jsonReader.e();
                    lottieComposition.a("Lottie doesn't support layer effects. If you are using them for  fills, strokes, trim paths etc. then try adding them directly as contents  in your shape. Found: " + arrayList4);
                    break;
                case 14:
                    f8 = (float) jsonReader.p();
                    break;
                case 15:
                    f4 = (float) jsonReader.p();
                    break;
                case 16:
                    f5 = (float) (jsonReader.p() * Utils.e());
                    break;
                case MlKitException.NETWORK_ISSUE /* 17 */:
                    f6 = (float) (jsonReader.p() * Utils.e());
                    break;
                case MlKitException.UNSUPPORTED /* 18 */:
                    f3 = (float) jsonReader.p();
                    break;
                case 19:
                    f7 = (float) jsonReader.p();
                    break;
                case 20:
                    animatableFloatValue = AnimatableValueParser.f(jsonReader, lottieComposition, false);
                    break;
                case Status.ERROR_STREAM_REMOTE_FAILED /* 21 */:
                    str3 = jsonReader.A();
                    break;
                case 22:
                    z3 = jsonReader.k();
                    break;
                case 23:
                    if (jsonReader.s() != 1) {
                        z2 = false;
                        break;
                    } else {
                        z2 = true;
                        break;
                    }
                case 24:
                    int s5 = jsonReader.s();
                    if (s5 < LBlendMode.values().length) {
                        lBlendMode2 = LBlendMode.values()[s5];
                        break;
                    } else {
                        lottieComposition.a("Unsupported Blend Mode: " + s5);
                        lBlendMode2 = LBlendMode.NORMAL;
                        break;
                    }
                default:
                    jsonReader.F();
                    jsonReader.G();
                    break;
            }
        }
        jsonReader.h();
        ArrayList arrayList5 = new ArrayList();
        if (f3 > 0.0f) {
            arrayList = arrayList2;
            z = z2;
            arrayList5.add(new Keyframe(lottieComposition, valueOf, valueOf, null, 0.0f, Float.valueOf(f3)));
            f2 = 0.0f;
        } else {
            arrayList = arrayList2;
            z = z2;
            f2 = 0.0f;
        }
        if (f7 <= f2) {
            f7 = lottieComposition.f();
        }
        arrayList5.add(new Keyframe(lottieComposition, valueOf2, valueOf2, null, f3, Float.valueOf(f7)));
        arrayList5.add(new Keyframe(lottieComposition, valueOf, valueOf, null, f7, Float.valueOf(Float.MAX_VALUE)));
        if (str2.endsWith(".ai") || "ai".equals(str3)) {
            lottieComposition.a("Convert your Illustrator layers to shape layers.");
        }
        if (z) {
            if (animatableTransform == null) {
                animatableTransform = new AnimatableTransform();
            }
            animatableTransform.m(z);
        }
        return new Layer(arrayList3, lottieComposition, str2, j2, layerType, j3, str, arrayList, animatableTransform, i2, i3, i4, f8, f4, f5, f6, animatableTextFrame, animatableTextProperties, arrayList5, matteType2, animatableFloatValue, z3, blurEffect, dropShadowEffect, lBlendMode2);
    }
}
