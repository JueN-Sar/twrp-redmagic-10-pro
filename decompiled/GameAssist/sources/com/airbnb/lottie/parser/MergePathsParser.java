package com.airbnb.lottie.parser;

import com.airbnb.lottie.model.content.MergePaths;
import com.airbnb.lottie.parser.moshi.JsonReader;

/* loaded from: classes.dex */
class MergePathsParser {

    /* renamed from: a, reason: collision with root package name */
    private static final JsonReader.Options f9847a = JsonReader.Options.a("nm", "mm", "hd");

    static MergePaths a(JsonReader jsonReader) {
        String str = null;
        boolean z = false;
        MergePaths.MergePathsMode mergePathsMode = null;
        while (jsonReader.j()) {
            int E = jsonReader.E(f9847a);
            if (E == 0) {
                str = jsonReader.A();
            } else if (E == 1) {
                mergePathsMode = MergePaths.MergePathsMode.d(jsonReader.s());
            } else if (E != 2) {
                jsonReader.F();
                jsonReader.G();
            } else {
                z = jsonReader.k();
            }
        }
        return new MergePaths(str, mergePathsMode, z);
    }
}
