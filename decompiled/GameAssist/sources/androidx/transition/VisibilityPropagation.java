package androidx.transition;

import android.view.View;

/* loaded from: classes.dex */
public abstract class VisibilityPropagation extends TransitionPropagation {

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f5616a = {"android:visibilityPropagation:visibility", "android:visibilityPropagation:center"};

    private static int d(TransitionValues transitionValues, int i2) {
        int[] iArr;
        if (transitionValues == null || (iArr = (int[]) transitionValues.f5570a.get("android:visibilityPropagation:center")) == null) {
            return -1;
        }
        return iArr[i2];
    }

    @Override // androidx.transition.TransitionPropagation
    public void a(TransitionValues transitionValues) {
        View view = transitionValues.f5571b;
        Integer num = (Integer) transitionValues.f5570a.get("android:visibility:visibility");
        if (num == null) {
            num = Integer.valueOf(view.getVisibility());
        }
        transitionValues.f5570a.put("android:visibilityPropagation:visibility", num);
        int[] iArr = {r3, 0};
        view.getLocationOnScreen(iArr);
        int round = iArr[0] + Math.round(view.getTranslationX());
        iArr[0] = round + (view.getWidth() / 2);
        int round2 = iArr[1] + Math.round(view.getTranslationY());
        iArr[1] = round2;
        iArr[1] = round2 + (view.getHeight() / 2);
        transitionValues.f5570a.put("android:visibilityPropagation:center", iArr);
    }

    @Override // androidx.transition.TransitionPropagation
    public String[] b() {
        return f5616a;
    }

    public int e(TransitionValues transitionValues) {
        Integer num;
        if (transitionValues == null || (num = (Integer) transitionValues.f5570a.get("android:visibilityPropagation:visibility")) == null) {
            return 8;
        }
        return num.intValue();
    }

    public int f(TransitionValues transitionValues) {
        return d(transitionValues, 0);
    }

    public int g(TransitionValues transitionValues) {
        return d(transitionValues, 1);
    }
}
