package com.google.android.material.color.utilities;

import androidx.annotation.RestrictTo;

@RestrictTo
/* loaded from: classes.dex */
public class SchemeContent extends DynamicScheme {
    public SchemeContent(Hct hct, boolean z, double d2) {
        super(hct, Variant.CONTENT, z, d2, TonalPalette.c(hct.d(), hct.c()), TonalPalette.c(hct.d(), Math.max(hct.c() - 32.0d, hct.c() * 0.5d)), TonalPalette.b(DislikeAnalyzer.a((Hct) new TemperatureCache(hct).b(3, 6).get(2))), TonalPalette.c(hct.d(), hct.c() / 8.0d), TonalPalette.c(hct.d(), (hct.c() / 8.0d) + 4.0d));
    }
}
