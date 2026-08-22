package com.google.android.material.transition.platform;

import androidx.annotation.RequiresApi;

@RequiresApi
/* loaded from: classes.dex */
class FadeModeResult {

    /* renamed from: a, reason: collision with root package name */
    final int f15651a;

    /* renamed from: b, reason: collision with root package name */
    final int f15652b;

    /* renamed from: c, reason: collision with root package name */
    final boolean f15653c;

    private FadeModeResult(int i2, int i3, boolean z) {
        this.f15651a = i2;
        this.f15652b = i3;
        this.f15653c = z;
    }

    static FadeModeResult a(int i2, int i3) {
        return new FadeModeResult(i2, i3, true);
    }

    static FadeModeResult b(int i2, int i3) {
        return new FadeModeResult(i2, i3, false);
    }
}
