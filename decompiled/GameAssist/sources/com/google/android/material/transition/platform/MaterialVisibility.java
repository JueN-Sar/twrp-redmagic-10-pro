package com.google.android.material.transition.platform;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.transition.TransitionValues;
import android.transition.Visibility;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.RequiresApi;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.animation.AnimatorSetCompat;
import com.google.android.material.transition.platform.VisibilityAnimatorProvider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RequiresApi
/* loaded from: classes.dex */
abstract class MaterialVisibility<P extends VisibilityAnimatorProvider> extends Visibility {

    /* renamed from: c, reason: collision with root package name */
    private final VisibilityAnimatorProvider f15750c;

    /* renamed from: h, reason: collision with root package name */
    private VisibilityAnimatorProvider f15751h;

    /* renamed from: i, reason: collision with root package name */
    private final List f15752i;

    private static void a(List list, VisibilityAnimatorProvider visibilityAnimatorProvider, ViewGroup viewGroup, View view, boolean z) {
        if (visibilityAnimatorProvider == null) {
            return;
        }
        Animator b2 = z ? visibilityAnimatorProvider.b(viewGroup, view) : visibilityAnimatorProvider.a(viewGroup, view);
        if (b2 != null) {
            list.add(b2);
        }
    }

    private Animator b(ViewGroup viewGroup, View view, boolean z) {
        AnimatorSet animatorSet = new AnimatorSet();
        ArrayList arrayList = new ArrayList();
        a(arrayList, this.f15750c, viewGroup, view, z);
        a(arrayList, this.f15751h, viewGroup, view, z);
        Iterator it = this.f15752i.iterator();
        while (it.hasNext()) {
            a(arrayList, (VisibilityAnimatorProvider) it.next(), viewGroup, view, z);
        }
        g(viewGroup.getContext(), z);
        AnimatorSetCompat.a(animatorSet, arrayList);
        return animatorSet;
    }

    private void g(Context context, boolean z) {
        TransitionUtils.r(this, context, e(z));
        TransitionUtils.s(this, context, f(z), c(z));
    }

    TimeInterpolator c(boolean z) {
        return AnimationUtils.f13815b;
    }

    int e(boolean z) {
        return 0;
    }

    int f(boolean z) {
        return 0;
    }

    @Override // android.transition.Visibility
    public Animator onAppear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        return b(viewGroup, view, true);
    }

    @Override // android.transition.Visibility
    public Animator onDisappear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        return b(viewGroup, view, false);
    }
}
