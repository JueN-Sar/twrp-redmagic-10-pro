package com.google.android.material.carousel;

import android.content.Context;
import android.view.View;

/* loaded from: classes.dex */
public abstract class CarouselStrategy {

    /* renamed from: a, reason: collision with root package name */
    private float f14151a;

    /* renamed from: b, reason: collision with root package name */
    private float f14152b;

    static int[] a(int[] iArr) {
        int length = iArr.length;
        int[] iArr2 = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr2[i2] = iArr[i2] * 2;
        }
        return iArr2;
    }

    static float b(float f2, float f3, float f4) {
        return 1.0f - ((f2 - f4) / (f3 - f4));
    }

    public float c() {
        return this.f14152b;
    }

    public float d() {
        return this.f14151a;
    }

    void e(Context context) {
        float f2 = this.f14151a;
        if (f2 <= 0.0f) {
            f2 = CarouselStrategyHelper.h(context);
        }
        this.f14151a = f2;
        float f3 = this.f14152b;
        if (f3 <= 0.0f) {
            f3 = CarouselStrategyHelper.g(context);
        }
        this.f14152b = f3;
    }

    boolean f() {
        return true;
    }

    abstract KeylineState g(Carousel carousel, View view);

    boolean h(Carousel carousel, int i2) {
        return false;
    }
}
