package com.google.android.material.progressindicator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.util.Property;
import androidx.annotation.VisibleForTesting;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import com.google.android.material.progressindicator.DrawingDelegate;

/* loaded from: classes.dex */
final class LinearIndeterminateContiguousAnimatorDelegate extends IndeterminateAnimatorDelegate<ObjectAnimator> {

    /* renamed from: i, reason: collision with root package name */
    private static final Property f14925i = new Property<LinearIndeterminateContiguousAnimatorDelegate, Float>(Float.class, "animationFraction") { // from class: com.google.android.material.progressindicator.LinearIndeterminateContiguousAnimatorDelegate.2
        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float get(LinearIndeterminateContiguousAnimatorDelegate linearIndeterminateContiguousAnimatorDelegate) {
            return Float.valueOf(linearIndeterminateContiguousAnimatorDelegate.n());
        }

        @Override // android.util.Property
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(LinearIndeterminateContiguousAnimatorDelegate linearIndeterminateContiguousAnimatorDelegate, Float f2) {
            linearIndeterminateContiguousAnimatorDelegate.setAnimationFraction(f2.floatValue());
        }
    };

    /* renamed from: c, reason: collision with root package name */
    private ObjectAnimator f14926c;

    /* renamed from: d, reason: collision with root package name */
    private FastOutSlowInInterpolator f14927d;

    /* renamed from: e, reason: collision with root package name */
    private final BaseProgressIndicatorSpec f14928e;

    /* renamed from: f, reason: collision with root package name */
    private int f14929f;

    /* renamed from: g, reason: collision with root package name */
    private boolean f14930g;

    /* renamed from: h, reason: collision with root package name */
    private float f14931h;

    public LinearIndeterminateContiguousAnimatorDelegate(LinearProgressIndicatorSpec linearProgressIndicatorSpec) {
        super(3);
        this.f14929f = 1;
        this.f14928e = linearProgressIndicatorSpec;
        this.f14927d = new FastOutSlowInInterpolator();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float n() {
        return this.f14931h;
    }

    private void o() {
        if (this.f14926c == null) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, (Property<LinearIndeterminateContiguousAnimatorDelegate, Float>) f14925i, 0.0f, 1.0f);
            this.f14926c = ofFloat;
            ofFloat.setDuration(333L);
            this.f14926c.setInterpolator(null);
            this.f14926c.setRepeatCount(-1);
            this.f14926c.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.progressindicator.LinearIndeterminateContiguousAnimatorDelegate.1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationRepeat(Animator animator) {
                    super.onAnimationRepeat(animator);
                    LinearIndeterminateContiguousAnimatorDelegate linearIndeterminateContiguousAnimatorDelegate = LinearIndeterminateContiguousAnimatorDelegate.this;
                    linearIndeterminateContiguousAnimatorDelegate.f14929f = (linearIndeterminateContiguousAnimatorDelegate.f14929f + 1) % LinearIndeterminateContiguousAnimatorDelegate.this.f14928e.f14870c.length;
                    LinearIndeterminateContiguousAnimatorDelegate.this.f14930g = true;
                }
            });
        }
    }

    private void p() {
        if (!this.f14930g || ((DrawingDelegate.ActiveIndicator) this.f14919b.get(1)).f14915b >= 1.0f) {
            return;
        }
        ((DrawingDelegate.ActiveIndicator) this.f14919b.get(2)).f14916c = ((DrawingDelegate.ActiveIndicator) this.f14919b.get(1)).f14916c;
        ((DrawingDelegate.ActiveIndicator) this.f14919b.get(1)).f14916c = ((DrawingDelegate.ActiveIndicator) this.f14919b.get(0)).f14916c;
        ((DrawingDelegate.ActiveIndicator) this.f14919b.get(0)).f14916c = this.f14928e.f14870c[this.f14929f];
        this.f14930g = false;
    }

    private void q(int i2) {
        ((DrawingDelegate.ActiveIndicator) this.f14919b.get(0)).f14914a = 0.0f;
        float b2 = b(i2, 0, 667);
        DrawingDelegate.ActiveIndicator activeIndicator = (DrawingDelegate.ActiveIndicator) this.f14919b.get(0);
        DrawingDelegate.ActiveIndicator activeIndicator2 = (DrawingDelegate.ActiveIndicator) this.f14919b.get(1);
        float interpolation = this.f14927d.getInterpolation(b2);
        activeIndicator2.f14914a = interpolation;
        activeIndicator.f14915b = interpolation;
        DrawingDelegate.ActiveIndicator activeIndicator3 = (DrawingDelegate.ActiveIndicator) this.f14919b.get(1);
        DrawingDelegate.ActiveIndicator activeIndicator4 = (DrawingDelegate.ActiveIndicator) this.f14919b.get(2);
        float interpolation2 = this.f14927d.getInterpolation(b2 + 0.49925038f);
        activeIndicator4.f14914a = interpolation2;
        activeIndicator3.f14915b = interpolation2;
        ((DrawingDelegate.ActiveIndicator) this.f14919b.get(2)).f14915b = 1.0f;
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void a() {
        ObjectAnimator objectAnimator = this.f14926c;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void c() {
        resetPropertiesForNewStart();
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void d(Animatable2Compat.AnimationCallback animationCallback) {
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void f() {
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void g() {
        o();
        resetPropertiesForNewStart();
        this.f14926c.start();
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void h() {
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    @VisibleForTesting
    void resetPropertiesForNewStart() {
        this.f14930g = true;
        this.f14929f = 1;
        for (DrawingDelegate.ActiveIndicator activeIndicator : this.f14919b) {
            BaseProgressIndicatorSpec baseProgressIndicatorSpec = this.f14928e;
            activeIndicator.f14916c = baseProgressIndicatorSpec.f14870c[0];
            activeIndicator.f14917d = baseProgressIndicatorSpec.f14874g / 2;
        }
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    @VisibleForTesting
    void setAnimationFraction(float f2) {
        this.f14931h = f2;
        q((int) (f2 * 333.0f));
        p();
        this.f14918a.invalidateSelf();
    }
}
