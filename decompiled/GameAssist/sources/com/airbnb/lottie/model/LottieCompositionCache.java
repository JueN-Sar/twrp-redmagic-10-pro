package com.airbnb.lottie.model;

import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.collection.LruCache;
import com.airbnb.lottie.LottieComposition;

@RestrictTo
/* loaded from: classes.dex */
public class LottieCompositionCache {

    /* renamed from: b, reason: collision with root package name */
    private static final LottieCompositionCache f9616b = new LottieCompositionCache();

    /* renamed from: a, reason: collision with root package name */
    private final LruCache f9617a = new LruCache(20);

    @VisibleForTesting
    LottieCompositionCache() {
    }

    public static LottieCompositionCache b() {
        return f9616b;
    }

    public LottieComposition a(String str) {
        if (str == null) {
            return null;
        }
        return (LottieComposition) this.f9617a.d(str);
    }

    public void c(String str, LottieComposition lottieComposition) {
        if (str == null) {
            return;
        }
        this.f9617a.e(str, lottieComposition);
    }
}
