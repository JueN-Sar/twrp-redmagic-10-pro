package com.google.android.material.internal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import androidx.annotation.RestrictTo;

@RestrictTo
/* loaded from: classes.dex */
public class ExpandCollapseAnimationHelper {

    /* renamed from: a, reason: collision with root package name */
    private final View f14715a;

    /* renamed from: com.google.android.material.internal.ExpandCollapseAnimationHelper$1, reason: invalid class name */
    class AnonymousClass1 extends AnimatorListenerAdapter {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ ExpandCollapseAnimationHelper f14716c;

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            this.f14716c.f14715a.setVisibility(0);
        }
    }

    /* renamed from: com.google.android.material.internal.ExpandCollapseAnimationHelper$2, reason: invalid class name */
    class AnonymousClass2 extends AnimatorListenerAdapter {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ ExpandCollapseAnimationHelper f14717c;

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f14717c.f14715a.setVisibility(8);
        }
    }
}
