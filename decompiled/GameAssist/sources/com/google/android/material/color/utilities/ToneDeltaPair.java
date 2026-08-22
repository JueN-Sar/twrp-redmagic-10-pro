package com.google.android.material.color.utilities;

import androidx.annotation.RestrictTo;

@RestrictTo
/* loaded from: classes.dex */
public final class ToneDeltaPair {

    /* renamed from: a, reason: collision with root package name */
    private final DynamicColor f14384a;

    /* renamed from: b, reason: collision with root package name */
    private final DynamicColor f14385b;

    /* renamed from: c, reason: collision with root package name */
    private final double f14386c;

    /* renamed from: d, reason: collision with root package name */
    private final TonePolarity f14387d;

    /* renamed from: e, reason: collision with root package name */
    private final boolean f14388e;

    public ToneDeltaPair(DynamicColor dynamicColor, DynamicColor dynamicColor2, double d2, TonePolarity tonePolarity, boolean z) {
        this.f14384a = dynamicColor;
        this.f14385b = dynamicColor2;
        this.f14386c = d2;
        this.f14387d = tonePolarity;
        this.f14388e = z;
    }

    public double a() {
        return this.f14386c;
    }

    public TonePolarity b() {
        return this.f14387d;
    }

    public DynamicColor c() {
        return this.f14384a;
    }

    public DynamicColor d() {
        return this.f14385b;
    }

    public boolean e() {
        return this.f14388e;
    }
}
