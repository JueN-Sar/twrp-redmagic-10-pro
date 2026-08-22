package com.google.android.material.transition;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.transition.TransitionValues;
import androidx.transition.Visibility;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.animation.AnimatorSetCompat;
import com.google.android.material.transition.VisibilityAnimatorProvider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
abstract class MaterialVisibility<P extends VisibilityAnimatorProvider> extends Visibility {
    private final VisibilityAnimatorProvider Y;
    private VisibilityAnimatorProvider Z;
    private final List a0;

    private void D0(Context context, boolean z) {
        TransitionUtils.q(this, context, B0(z));
        TransitionUtils.r(this, context, C0(z), A0(z));
    }

    private static void y0(List list, VisibilityAnimatorProvider visibilityAnimatorProvider, ViewGroup viewGroup, View view, boolean z) {
        if (visibilityAnimatorProvider == null) {
            return;
        }
        Animator b2 = z ? visibilityAnimatorProvider.b(viewGroup, view) : visibilityAnimatorProvider.a(viewGroup, view);
        if (b2 != null) {
            list.add(b2);
        }
    }

    private Animator z0(ViewGroup viewGroup, View view, boolean z) {
        AnimatorSet animatorSet = new AnimatorSet();
        ArrayList arrayList = new ArrayList();
        y0(arrayList, this.Y, viewGroup, view, z);
        y0(arrayList, this.Z, viewGroup, view, z);
        Iterator it = this.a0.iterator();
        while (it.hasNext()) {
            y0(arrayList, (VisibilityAnimatorProvider) it.next(), viewGroup, view, z);
        }
        D0(viewGroup.getContext(), z);
        AnimatorSetCompat.a(animatorSet, arrayList);
        return animatorSet;
    }

    TimeInterpolator A0(boolean z) {
        return AnimationUtils.f13815b;
    }

    int B0(boolean z) {
        return 0;
    }

    int C0(boolean z) {
        return 0;
    }

    @Override // androidx.transition.Visibility
    public Animator t0(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        return z0(viewGroup, view, true);
    }

    @Override // androidx.transition.Visibility
    public Animator v0(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        return z0(viewGroup, view, false);
    }
}
