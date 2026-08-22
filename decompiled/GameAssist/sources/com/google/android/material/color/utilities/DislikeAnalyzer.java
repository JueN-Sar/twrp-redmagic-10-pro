package com.google.android.material.color.utilities;

import androidx.annotation.RestrictTo;

@RestrictTo
/* loaded from: classes.dex */
public final class DislikeAnalyzer {
    public static Hct a(Hct hct) {
        return b(hct) ? Hct.a(hct.d(), hct.c(), 70.0d) : hct;
    }

    public static boolean b(Hct hct) {
        return ((((double) Math.round(hct.d())) > 90.0d ? 1 : (((double) Math.round(hct.d())) == 90.0d ? 0 : -1)) >= 0 && (((double) Math.round(hct.d())) > 111.0d ? 1 : (((double) Math.round(hct.d())) == 111.0d ? 0 : -1)) <= 0) && ((((double) Math.round(hct.c())) > 16.0d ? 1 : (((double) Math.round(hct.c())) == 16.0d ? 0 : -1)) > 0) && ((((double) Math.round(hct.e())) > 65.0d ? 1 : (((double) Math.round(hct.e())) == 65.0d ? 0 : -1)) < 0);
    }
}
