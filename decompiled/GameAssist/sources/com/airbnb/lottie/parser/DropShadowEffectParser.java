package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableColorValue;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.parser.moshi.JsonReader;

/* loaded from: classes.dex */
public class DropShadowEffectParser {

    /* renamed from: f, reason: collision with root package name */
    private static final JsonReader.Options f9814f = JsonReader.Options.a("ef");

    /* renamed from: g, reason: collision with root package name */
    private static final JsonReader.Options f9815g = JsonReader.Options.a("nm", "v");

    /* renamed from: a, reason: collision with root package name */
    private AnimatableColorValue f9816a;

    /* renamed from: b, reason: collision with root package name */
    private AnimatableFloatValue f9817b;

    /* renamed from: c, reason: collision with root package name */
    private AnimatableFloatValue f9818c;

    /* renamed from: d, reason: collision with root package name */
    private AnimatableFloatValue f9819d;

    /* renamed from: e, reason: collision with root package name */
    private AnimatableFloatValue f9820e;

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0052, code lost:
    
        if (r0.equals("Opacity") == false) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(com.airbnb.lottie.parser.moshi.JsonReader r6, com.airbnb.lottie.LottieComposition r7) {
        /*
            r5 = this;
            r6.d()
            java.lang.String r0 = ""
        L5:
            boolean r1 = r6.j()
            if (r1 == 0) goto L90
            com.airbnb.lottie.parser.moshi.JsonReader$Options r1 = com.airbnb.lottie.parser.DropShadowEffectParser.f9815g
            int r1 = r6.E(r1)
            if (r1 == 0) goto L8a
            r2 = 1
            if (r1 == r2) goto L1d
            r6.F()
            r6.G()
            goto L5
        L1d:
            r0.hashCode()
            int r1 = r0.hashCode()
            r3 = 0
            r4 = -1
            switch(r1) {
                case 353103893: goto L55;
                case 397447147: goto L4c;
                case 1041377119: goto L41;
                case 1379387491: goto L36;
                case 1383710113: goto L2b;
                default: goto L29;
            }
        L29:
            r2 = r4
            goto L5f
        L2b:
            java.lang.String r1 = "Softness"
            boolean r1 = r0.equals(r1)
            if (r1 != 0) goto L34
            goto L29
        L34:
            r2 = 4
            goto L5f
        L36:
            java.lang.String r1 = "Shadow Color"
            boolean r1 = r0.equals(r1)
            if (r1 != 0) goto L3f
            goto L29
        L3f:
            r2 = 3
            goto L5f
        L41:
            java.lang.String r1 = "Direction"
            boolean r1 = r0.equals(r1)
            if (r1 != 0) goto L4a
            goto L29
        L4a:
            r2 = 2
            goto L5f
        L4c:
            java.lang.String r1 = "Opacity"
            boolean r1 = r0.equals(r1)
            if (r1 != 0) goto L5f
            goto L29
        L55:
            java.lang.String r1 = "Distance"
            boolean r1 = r0.equals(r1)
            if (r1 != 0) goto L5e
            goto L29
        L5e:
            r2 = r3
        L5f:
            switch(r2) {
                case 0: goto L82;
                case 1: goto L7b;
                case 2: goto L74;
                case 3: goto L6d;
                case 4: goto L66;
                default: goto L62;
            }
        L62:
            r6.G()
            goto L5
        L66:
            com.airbnb.lottie.model.animatable.AnimatableFloatValue r1 = com.airbnb.lottie.parser.AnimatableValueParser.e(r6, r7)
            r5.f9820e = r1
            goto L5
        L6d:
            com.airbnb.lottie.model.animatable.AnimatableColorValue r1 = com.airbnb.lottie.parser.AnimatableValueParser.c(r6, r7)
            r5.f9816a = r1
            goto L5
        L74:
            com.airbnb.lottie.model.animatable.AnimatableFloatValue r1 = com.airbnb.lottie.parser.AnimatableValueParser.f(r6, r7, r3)
            r5.f9818c = r1
            goto L5
        L7b:
            com.airbnb.lottie.model.animatable.AnimatableFloatValue r1 = com.airbnb.lottie.parser.AnimatableValueParser.f(r6, r7, r3)
            r5.f9817b = r1
            goto L5
        L82:
            com.airbnb.lottie.model.animatable.AnimatableFloatValue r1 = com.airbnb.lottie.parser.AnimatableValueParser.e(r6, r7)
            r5.f9819d = r1
            goto L5
        L8a:
            java.lang.String r0 = r6.A()
            goto L5
        L90:
            r6.h()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.parser.DropShadowEffectParser.a(com.airbnb.lottie.parser.moshi.JsonReader, com.airbnb.lottie.LottieComposition):void");
    }

    DropShadowEffect b(JsonReader jsonReader, LottieComposition lottieComposition) {
        AnimatableFloatValue animatableFloatValue;
        AnimatableFloatValue animatableFloatValue2;
        AnimatableFloatValue animatableFloatValue3;
        AnimatableFloatValue animatableFloatValue4;
        while (jsonReader.j()) {
            if (jsonReader.E(f9814f) != 0) {
                jsonReader.F();
                jsonReader.G();
            } else {
                jsonReader.c();
                while (jsonReader.j()) {
                    a(jsonReader, lottieComposition);
                }
                jsonReader.e();
            }
        }
        AnimatableColorValue animatableColorValue = this.f9816a;
        if (animatableColorValue == null || (animatableFloatValue = this.f9817b) == null || (animatableFloatValue2 = this.f9818c) == null || (animatableFloatValue3 = this.f9819d) == null || (animatableFloatValue4 = this.f9820e) == null) {
            return null;
        }
        return new DropShadowEffect(animatableColorValue, animatableFloatValue, animatableFloatValue2, animatableFloatValue3, animatableFloatValue4);
    }
}
