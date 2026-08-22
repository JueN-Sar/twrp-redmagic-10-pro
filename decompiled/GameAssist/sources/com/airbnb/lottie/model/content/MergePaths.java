package com.airbnb.lottie.model.content;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.MergePathsContent;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.utils.Logger;

/* loaded from: classes.dex */
public class MergePaths implements ContentModel {

    /* renamed from: a, reason: collision with root package name */
    private final String f9677a;

    /* renamed from: b, reason: collision with root package name */
    private final MergePathsMode f9678b;

    /* renamed from: c, reason: collision with root package name */
    private final boolean f9679c;

    public enum MergePathsMode {
        MERGE,
        ADD,
        SUBTRACT,
        INTERSECT,
        EXCLUDE_INTERSECTIONS;

        public static MergePathsMode d(int i2) {
            return i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? i2 != 5 ? MERGE : EXCLUDE_INTERSECTIONS : INTERSECT : SUBTRACT : ADD : MERGE;
        }
    }

    public MergePaths(String str, MergePathsMode mergePathsMode, boolean z) {
        this.f9677a = str;
        this.f9678b = mergePathsMode;
        this.f9679c = z;
    }

    @Override // com.airbnb.lottie.model.content.ContentModel
    public Content a(LottieDrawable lottieDrawable, LottieComposition lottieComposition, BaseLayer baseLayer) {
        if (lottieDrawable.z()) {
            return new MergePathsContent(this);
        }
        Logger.c("Animation contains merge paths but they are disabled.");
        return null;
    }

    public MergePathsMode b() {
        return this.f9678b;
    }

    public String c() {
        return this.f9677a;
    }

    public boolean d() {
        return this.f9679c;
    }

    public String toString() {
        return "MergePaths{mode=" + this.f9678b + '}';
    }
}
