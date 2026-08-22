package com.airbnb.lottie.utils;

/* loaded from: classes.dex */
public class MeanCalculator {

    /* renamed from: a, reason: collision with root package name */
    private float f9932a;

    /* renamed from: b, reason: collision with root package name */
    private int f9933b;

    public void a(float f2) {
        float f3 = this.f9932a + f2;
        this.f9932a = f3;
        int i2 = this.f9933b + 1;
        this.f9933b = i2;
        if (i2 == Integer.MAX_VALUE) {
            this.f9932a = f3 / 2.0f;
            this.f9933b = i2 / 2;
        }
    }
}
