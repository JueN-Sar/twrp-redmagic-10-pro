package com.airbnb.lottie;

import android.graphics.Bitmap;

/* loaded from: classes.dex */
public class LottieImageAsset {

    /* renamed from: a, reason: collision with root package name */
    private final int f9299a;

    /* renamed from: b, reason: collision with root package name */
    private final int f9300b;

    /* renamed from: c, reason: collision with root package name */
    private final String f9301c;

    /* renamed from: d, reason: collision with root package name */
    private final String f9302d;

    /* renamed from: e, reason: collision with root package name */
    private final String f9303e;

    /* renamed from: f, reason: collision with root package name */
    private Bitmap f9304f;

    public LottieImageAsset(int i2, int i3, String str, String str2, String str3) {
        this.f9299a = i2;
        this.f9300b = i3;
        this.f9301c = str;
        this.f9302d = str2;
        this.f9303e = str3;
    }

    public LottieImageAsset a(float f2) {
        LottieImageAsset lottieImageAsset = new LottieImageAsset((int) (this.f9299a * f2), (int) (this.f9300b * f2), this.f9301c, this.f9302d, this.f9303e);
        Bitmap bitmap = this.f9304f;
        if (bitmap != null) {
            lottieImageAsset.g(Bitmap.createScaledBitmap(bitmap, lottieImageAsset.f9299a, lottieImageAsset.f9300b, true));
        }
        return lottieImageAsset;
    }

    public Bitmap b() {
        return this.f9304f;
    }

    public String c() {
        return this.f9302d;
    }

    public int d() {
        return this.f9300b;
    }

    public String e() {
        return this.f9301c;
    }

    public int f() {
        return this.f9299a;
    }

    public void g(Bitmap bitmap) {
        this.f9304f = bitmap;
    }
}
