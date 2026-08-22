package com.airbnb.lottie.value;

/* loaded from: classes.dex */
public class ScaleXY {

    /* renamed from: a, reason: collision with root package name */
    private float f9972a;

    /* renamed from: b, reason: collision with root package name */
    private float f9973b;

    public ScaleXY(float f2, float f3) {
        this.f9972a = f2;
        this.f9973b = f3;
    }

    public boolean a(float f2, float f3) {
        return this.f9972a == f2 && this.f9973b == f3;
    }

    public float b() {
        return this.f9972a;
    }

    public float c() {
        return this.f9973b;
    }

    public void d(float f2, float f3) {
        this.f9972a = f2;
        this.f9973b = f3;
    }

    public String toString() {
        return b() + "x" + c();
    }

    public ScaleXY() {
        this(1.0f, 1.0f);
    }
}
