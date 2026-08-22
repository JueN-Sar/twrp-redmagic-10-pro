package com.google.android.material.transition;

import android.animation.Animator;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes.dex */
public interface VisibilityAnimatorProvider {
    Animator a(ViewGroup viewGroup, View view);

    Animator b(ViewGroup viewGroup, View view);
}
