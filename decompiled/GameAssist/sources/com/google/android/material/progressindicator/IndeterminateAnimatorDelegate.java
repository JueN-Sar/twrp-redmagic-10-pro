package com.google.android.material.progressindicator;

import android.animation.Animator;
import androidx.annotation.VisibleForTesting;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import com.google.android.material.progressindicator.DrawingDelegate;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
abstract class IndeterminateAnimatorDelegate<T extends Animator> {

    /* renamed from: a, reason: collision with root package name */
    protected IndeterminateDrawable f14918a;

    /* renamed from: b, reason: collision with root package name */
    protected final List f14919b = new ArrayList();

    protected IndeterminateAnimatorDelegate(int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.f14919b.add(new DrawingDelegate.ActiveIndicator());
        }
    }

    abstract void a();

    protected float b(int i2, int i3, int i4) {
        return (i2 - i3) / i4;
    }

    public abstract void c();

    public abstract void d(Animatable2Compat.AnimationCallback animationCallback);

    protected void e(IndeterminateDrawable indeterminateDrawable) {
        this.f14918a = indeterminateDrawable;
    }

    abstract void f();

    abstract void g();

    public abstract void h();

    @VisibleForTesting
    abstract void resetPropertiesForNewStart();

    @VisibleForTesting
    abstract void setAnimationFraction(float f2);
}
