package com.google.android.material.transition.platform;

import android.graphics.RectF;
import androidx.annotation.RequiresApi;

@RequiresApi
/* loaded from: classes.dex */
interface FitModeEvaluator {
    FitModeResult a(float f2, float f3, float f4, float f5, float f6, float f7, float f8);

    boolean b(FitModeResult fitModeResult);

    void c(RectF rectF, float f2, FitModeResult fitModeResult);
}
