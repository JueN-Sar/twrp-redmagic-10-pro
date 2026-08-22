package com.google.android.material.floatingactionbutton;

import android.animation.Animator;

/* loaded from: classes.dex */
class AnimatorTracker {

    /* renamed from: a, reason: collision with root package name */
    private Animator f14581a;

    AnimatorTracker() {
    }

    public void a() {
        Animator animator = this.f14581a;
        if (animator != null) {
            animator.cancel();
        }
    }

    public void b() {
        this.f14581a = null;
    }

    public void c(Animator animator) {
        a();
        this.f14581a = animator;
    }
}
