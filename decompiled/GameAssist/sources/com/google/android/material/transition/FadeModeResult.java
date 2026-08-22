package com.google.android.material.transition;

/* loaded from: classes.dex */
class FadeModeResult {

    /* renamed from: a, reason: collision with root package name */
    final int f15559a;

    /* renamed from: b, reason: collision with root package name */
    final int f15560b;

    /* renamed from: c, reason: collision with root package name */
    final boolean f15561c;

    private FadeModeResult(int i2, int i3, boolean z) {
        this.f15559a = i2;
        this.f15560b = i3;
        this.f15561c = z;
    }

    static FadeModeResult a(int i2, int i3) {
        return new FadeModeResult(i2, i3, true);
    }

    static FadeModeResult b(int i2, int i3) {
        return new FadeModeResult(i2, i3, false);
    }
}
