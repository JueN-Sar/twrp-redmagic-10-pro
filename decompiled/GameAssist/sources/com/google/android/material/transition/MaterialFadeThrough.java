package com.google.android.material.transition;

import android.animation.Animator;
import android.view.View;
import android.view.ViewGroup;
import androidx.transition.TransitionValues;
import com.google.android.material.R;

/* loaded from: classes.dex */
public final class MaterialFadeThrough extends MaterialVisibility<FadeThroughProvider> {
    private static final int b0 = R.attr.motionDurationLong1;
    private static final int c0 = R.attr.motionEasingEmphasizedInterpolator;

    @Override // com.google.android.material.transition.MaterialVisibility
    int B0(boolean z) {
        return b0;
    }

    @Override // com.google.android.material.transition.MaterialVisibility
    int C0(boolean z) {
        return c0;
    }

    @Override // com.google.android.material.transition.MaterialVisibility, androidx.transition.Visibility
    public /* bridge */ /* synthetic */ Animator t0(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        return super.t0(viewGroup, view, transitionValues, transitionValues2);
    }

    @Override // com.google.android.material.transition.MaterialVisibility, androidx.transition.Visibility
    public /* bridge */ /* synthetic */ Animator v0(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        return super.v0(viewGroup, view, transitionValues, transitionValues2);
    }
}
