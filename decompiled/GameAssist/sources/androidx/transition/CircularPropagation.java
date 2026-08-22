package androidx.transition;

import android.graphics.Rect;
import android.view.ViewGroup;

/* loaded from: classes.dex */
public class CircularPropagation extends VisibilityPropagation {

    /* renamed from: b, reason: collision with root package name */
    private float f5471b = 3.0f;

    private static float h(float f2, float f3, float f4, float f5) {
        float f6 = f4 - f2;
        float f7 = f5 - f3;
        return (float) Math.sqrt((f6 * f6) + (f7 * f7));
    }

    @Override // androidx.transition.TransitionPropagation
    public long c(ViewGroup viewGroup, Transition transition, TransitionValues transitionValues, TransitionValues transitionValues2) {
        int i2;
        int round;
        int i3;
        if (transitionValues == null && transitionValues2 == null) {
            return 0L;
        }
        if (transitionValues2 == null || e(transitionValues) == 0) {
            i2 = -1;
        } else {
            transitionValues = transitionValues2;
            i2 = 1;
        }
        int f2 = f(transitionValues);
        int g2 = g(transitionValues);
        Rect t = transition.t();
        if (t != null) {
            i3 = t.centerX();
            round = t.centerY();
        } else {
            viewGroup.getLocationOnScreen(new int[2]);
            int round2 = Math.round(r5[0] + (viewGroup.getWidth() / 2) + viewGroup.getTranslationX());
            round = Math.round(r5[1] + (viewGroup.getHeight() / 2) + viewGroup.getTranslationY());
            i3 = round2;
        }
        float h2 = h(f2, g2, i3, round) / h(0.0f, 0.0f, viewGroup.getWidth(), viewGroup.getHeight());
        long s2 = transition.s();
        if (s2 < 0) {
            s2 = 300;
        }
        return Math.round(((s2 * i2) / this.f5471b) * h2);
    }
}
