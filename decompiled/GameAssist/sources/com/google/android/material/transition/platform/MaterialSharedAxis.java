package com.google.android.material.transition.platform;

import android.animation.Animator;
import android.transition.TransitionValues;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import com.google.android.material.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@RequiresApi
/* loaded from: classes.dex */
public final class MaterialSharedAxis extends MaterialVisibility<VisibilityAnimatorProvider> {

    /* renamed from: j, reason: collision with root package name */
    private static final int f15748j = R.attr.motionDurationLong1;

    /* renamed from: k, reason: collision with root package name */
    private static final int f15749k = R.attr.motionEasingEmphasizedInterpolator;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface Axis {
    }

    @Override // com.google.android.material.transition.platform.MaterialVisibility
    int e(boolean z) {
        return f15748j;
    }

    @Override // com.google.android.material.transition.platform.MaterialVisibility
    int f(boolean z) {
        return f15749k;
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
