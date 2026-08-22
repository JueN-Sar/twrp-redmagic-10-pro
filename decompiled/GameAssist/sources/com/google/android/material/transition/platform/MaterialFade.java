package com.google.android.material.transition.platform;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.transition.TransitionValues;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.RequiresApi;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;

@RequiresApi
/* loaded from: classes.dex */
public final class MaterialFade extends MaterialVisibility<FadeProvider> {

    /* renamed from: j, reason: collision with root package name */
    private static final int f15742j = R.attr.motionDurationMedium4;

    /* renamed from: k, reason: collision with root package name */
    private static final int f15743k = R.attr.motionDurationShort3;

    /* renamed from: l, reason: collision with root package name */
    private static final int f15744l = R.attr.motionEasingEmphasizedDecelerateInterpolator;

    /* renamed from: m, reason: collision with root package name */
    private static final int f15745m = R.attr.motionEasingEmphasizedAccelerateInterpolator;

    @Override // com.google.android.material.transition.platform.MaterialVisibility
    TimeInterpolator c(boolean z) {
        return AnimationUtils.f13814a;
    }

    @Override // com.google.android.material.transition.platform.MaterialVisibility
    int e(boolean z) {
        return z ? f15742j : f15743k;
    }

    @Override // com.google.android.material.transition.platform.MaterialVisibility
    int f(boolean z) {
        return z ? f15744l : f15745m;
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
