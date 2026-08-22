package com.google.android.material.transition.platform;

import android.animation.Animator;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.RequiresApi;

@RequiresApi
/* loaded from: classes.dex */
public interface VisibilityAnimatorProvider {
    Animator a(ViewGroup viewGroup, View view);

    Animator b(ViewGroup viewGroup, View view);
}
