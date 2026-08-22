package com.google.android.material.transition.platform;

import android.animation.Animator;
import android.transition.TransitionValues;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.RequiresApi;
import com.google.android.material.R;

@RequiresApi
/* loaded from: classes.dex */
public final class MaterialFadeThrough extends MaterialVisibility<FadeThroughProvider> {

    /* renamed from: j, reason: collision with root package name */
    private static final int f15746j = R.attr.motionDurationLong1;

    /* renamed from: k, reason: collision with root package name */
    private static final int f15747k = R.attr.motionEasingEmphasizedInterpolator;

    @Override // com.google.android.material.transition.platform.MaterialVisibility
    int e(boolean z) {
        return f15746j;
    }

    @Override // com.google.android.material.transition.platform.MaterialVisibility
    int f(boolean z) {
        return f15747k;
    }

    @Override // com.google.android.material.transition.platform.MaterialVisibility, android.transition.Visibility
    public /* bridge */ /* synthetic */ Animator onAppear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        return super.onAppear(viewGroup, view, transitionValues, transitionValues2);
    }

    @Override // com.google.android.material.transition.platform.MaterialVisibility, android.transition.Visibility
    public /* bridge */ /* synthetic */ Animator onDisappear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        return super.onDisappear(viewGroup, view, transitionValues, transitionValues2);
    }
}
