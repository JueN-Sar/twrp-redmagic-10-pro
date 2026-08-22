package com.google.android.material.transition;

/* loaded from: classes.dex */
class FadeModeEvaluators {

    /* renamed from: a, reason: collision with root package name */
    private static final FadeModeEvaluator f15555a = new FadeModeEvaluator() { // from class: com.google.android.material.transition.FadeModeEvaluators.1
        @Override // com.google.android.material.transition.FadeModeEvaluator
        public FadeModeResult a(float f2, float f3, float f4, float f5) {
            return FadeModeResult.a(255, TransitionUtils.o(0, 255, f3, f4, f2));
        }
    };

    /* renamed from: b, reason: collision with root package name */
    private static final FadeModeEvaluator f15556b = new FadeModeEvaluator() { // from class: com.google.android.material.transition.FadeModeEvaluators.2
        @Override // com.google.android.material.transition.FadeModeEvaluator
        public FadeModeResult a(float f2, float f3, float f4, float f5) {
            return FadeModeResult.b(TransitionUtils.o(255, 0, f3, f4, f2), 255);
        }
    };

    /* renamed from: c, reason: collision with root package name */
    private static final FadeModeEvaluator f15557c = new FadeModeEvaluator() { // from class: com.google.android.material.transition.FadeModeEvaluators.3
        @Override // com.google.android.material.transition.FadeModeEvaluator
        public FadeModeResult a(float f2, float f3, float f4, float f5) {
            return FadeModeResult.b(TransitionUtils.o(255, 0, f3, f4, f2), TransitionUtils.o(0, 255, f3, f4, f2));
        }
    };

    /* renamed from: d, reason: collision with root package name */
    private static final FadeModeEvaluator f15558d = new FadeModeEvaluator() { // from class: com.google.android.material.transition.FadeModeEvaluators.4
        @Override // com.google.android.material.transition.FadeModeEvaluator
        public FadeModeResult a(float f2, float f3, float f4, float f5) {
            float f6 = ((f4 - f3) * f5) + f3;
            return FadeModeResult.b(TransitionUtils.o(255, 0, f3, f6, f2), TransitionUtils.o(0, 255, f6, f4, f2));
        }
    };

    static FadeModeEvaluator a(int i2, boolean z) {
        if (i2 == 0) {
            return z ? f15555a : f15556b;
        }
        if (i2 == 1) {
            return z ? f15556b : f15555a;
        }
        if (i2 == 2) {
            return f15557c;
        }
        if (i2 == 3) {
            return f15558d;
        }
        throw new IllegalArgumentException("Invalid fade mode: " + i2);
    }
}
