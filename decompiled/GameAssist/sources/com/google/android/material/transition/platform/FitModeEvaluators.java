package com.google.android.material.transition.platform;

import android.graphics.RectF;
import androidx.annotation.RequiresApi;

@RequiresApi
/* loaded from: classes.dex */
class FitModeEvaluators {

    /* renamed from: a, reason: collision with root package name */
    private static final FitModeEvaluator f15670a = new FitModeEvaluator() { // from class: com.google.android.material.transition.platform.FitModeEvaluators.1
        @Override // com.google.android.material.transition.platform.FitModeEvaluator
        public FitModeResult a(float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
            float o2 = TransitionUtils.o(f5, f7, f3, f4, f2, true);
            float f9 = o2 / f5;
            float f10 = o2 / f7;
            return new FitModeResult(f9, f10, o2, f6 * f9, o2, f8 * f10);
        }

        @Override // com.google.android.material.transition.platform.FitModeEvaluator
        public boolean b(FitModeResult fitModeResult) {
            return fitModeResult.f15675d > fitModeResult.f15677f;
        }

        @Override // com.google.android.material.transition.platform.FitModeEvaluator
        public void c(RectF rectF, float f2, FitModeResult fitModeResult) {
            rectF.bottom -= Math.abs(fitModeResult.f15677f - fitModeResult.f15675d) * f2;
        }
    };

    /* renamed from: b, reason: collision with root package name */
    private static final FitModeEvaluator f15671b = new FitModeEvaluator() { // from class: com.google.android.material.transition.platform.FitModeEvaluators.2
        @Override // com.google.android.material.transition.platform.FitModeEvaluator
        public FitModeResult a(float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
            float o2 = TransitionUtils.o(f6, f8, f3, f4, f2, true);
            float f9 = o2 / f6;
            float f10 = o2 / f8;
            return new FitModeResult(f9, f10, f5 * f9, o2, f7 * f10, o2);
        }

        @Override // com.google.android.material.transition.platform.FitModeEvaluator
        public boolean b(FitModeResult fitModeResult) {
            return fitModeResult.f15674c > fitModeResult.f15676e;
        }

        @Override // com.google.android.material.transition.platform.FitModeEvaluator
        public void c(RectF rectF, float f2, FitModeResult fitModeResult) {
            float abs = (Math.abs(fitModeResult.f15676e - fitModeResult.f15674c) / 2.0f) * f2;
            rectF.left += abs;
            rectF.right -= abs;
        }
    };

    static FitModeEvaluator a(int i2, boolean z, RectF rectF, RectF rectF2) {
        if (i2 == 0) {
            return b(z, rectF, rectF2) ? f15670a : f15671b;
        }
        if (i2 == 1) {
            return f15670a;
        }
        if (i2 == 2) {
            return f15671b;
        }
        throw new IllegalArgumentException("Invalid fit mode: " + i2);
    }

    private static boolean b(boolean z, RectF rectF, RectF rectF2) {
        float width = rectF.width();
        float height = rectF.height();
        float width2 = rectF2.width();
        float height2 = rectF2.height();
        float f2 = (height2 * width) / width2;
        float f3 = (width2 * height) / width;
        if (z) {
            if (f2 < height) {
                return false;
            }
        } else if (f3 < height2) {
            return false;
        }
        return true;
    }
}
