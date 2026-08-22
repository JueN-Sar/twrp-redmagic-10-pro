package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.animation.keyframe.PathKeyframe;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.Utils;

/* loaded from: classes.dex */
class PathKeyframeParser {
    static PathKeyframe a(JsonReader jsonReader, LottieComposition lottieComposition) {
        return new PathKeyframe(lottieComposition, KeyframeParser.c(jsonReader, lottieComposition, Utils.e(), PathParser.f9848a, jsonReader.C() == JsonReader.Token.BEGIN_OBJECT, false));
    }
}
