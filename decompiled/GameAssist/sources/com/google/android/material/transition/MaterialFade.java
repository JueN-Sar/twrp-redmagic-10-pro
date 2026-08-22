package com.google.android.material.transition;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.view.View;
import android.view.ViewGroup;
import androidx.transition.TransitionValues;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;

/* loaded from: classes.dex */
public final class MaterialFade extends MaterialVisibility<FadeProvider> {
    private static final int b0 = R.attr.motionDurationMedium4;
    private static final int c0 = R.attr.motionDurationShort3;
    private static final int d0 = R.attr.motionEasingEmphasizedDecelerateInterpolator;
    private static final int e0 = R.attr.motionEasingEmphasizedAccelerateInterpolator;

    @Override // com.google.android.material.transition.MaterialVisibility
    TimeInterpolator A0(boolean z) {
        return AnimationUtils.f13814a;
    }

    @Override // com.google.android.material.transition.MaterialVisibility
    int B0(boolean z) {
        return z ? b0 : c0;
    }

    @Override // com.google.android.material.transition.MaterialVisibility
    int C0(boolean z) {
        return z ? d0 : e0;
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
