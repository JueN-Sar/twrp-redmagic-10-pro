package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorSet;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import java.util.List;

/* loaded from: classes.dex */
interface MotionStrategy {
    void a();

    void b();

    MotionSpec c();

    boolean d();

    void e();

    int f();

    void g(MotionSpec motionSpec);

    AnimatorSet h();

    List i();

    void j(ExtendedFloatingActionButton.OnChangedCallback onChangedCallback);

    void onAnimationStart(Animator animator);
}
