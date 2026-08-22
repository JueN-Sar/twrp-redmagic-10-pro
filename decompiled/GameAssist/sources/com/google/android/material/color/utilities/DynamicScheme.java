package com.google.android.material.color.utilities;

import androidx.annotation.RestrictTo;

@RestrictTo
/* loaded from: classes.dex */
public class DynamicScheme {

    /* renamed from: a, reason: collision with root package name */
    public final int f14324a;

    /* renamed from: b, reason: collision with root package name */
    public final Hct f14325b;

    /* renamed from: c, reason: collision with root package name */
    public final Variant f14326c;

    /* renamed from: d, reason: collision with root package name */
    public final boolean f14327d;

    /* renamed from: e, reason: collision with root package name */
    public final double f14328e;

    /* renamed from: f, reason: collision with root package name */
    public final TonalPalette f14329f;

    /* renamed from: g, reason: collision with root package name */
    public final TonalPalette f14330g;

    /* renamed from: h, reason: collision with root package name */
    public final TonalPalette f14331h;

    /* renamed from: i, reason: collision with root package name */
    public final TonalPalette f14332i;

    /* renamed from: j, reason: collision with root package name */
    public final TonalPalette f14333j;

    /* renamed from: k, reason: collision with root package name */
    public final TonalPalette f14334k = TonalPalette.c(25.0d, 84.0d);

    public DynamicScheme(Hct hct, Variant variant, boolean z, double d2, TonalPalette tonalPalette, TonalPalette tonalPalette2, TonalPalette tonalPalette3, TonalPalette tonalPalette4, TonalPalette tonalPalette5) {
        this.f14324a = hct.h();
        this.f14325b = hct;
        this.f14326c = variant;
        this.f14327d = z;
        this.f14328e = d2;
        this.f14329f = tonalPalette;
        this.f14330g = tonalPalette2;
        this.f14331h = tonalPalette3;
        this.f14332i = tonalPalette4;
        this.f14333j = tonalPalette5;
    }
}
