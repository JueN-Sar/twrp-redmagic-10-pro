package com.google.android.material.progressindicator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.util.Property;
import androidx.annotation.VisibleForTesting;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import com.google.android.material.animation.ArgbEvaluatorCompat;
import com.google.android.material.progressindicator.DrawingDelegate;

/* loaded from: classes.dex */
final class CircularIndeterminateAnimatorDelegate extends IndeterminateAnimatorDelegate<ObjectAnimator> {

    /* renamed from: k, reason: collision with root package name */
    private static final int[] f14880k = {0, 1350, 2700, 4050};

    /* renamed from: l, reason: collision with root package name */
    private static final int[] f14881l = {667, 2017, 3367, 4717};

    /* renamed from: m, reason: collision with root package name */
    private static final int[] f14882m = {1000, 2350, 3700, 5050};

    /* renamed from: n, reason: collision with root package name */
    private static final Property f14883n;

    /* renamed from: o, reason: collision with root package name */
    private static final Property f14884o;

    /* renamed from: c, reason: collision with root package name */
    private ObjectAnimator f14885c;

    /* renamed from: d, reason: collision with root package name */
    private ObjectAnimator f14886d;

    /* renamed from: e, reason: collision with root package name */
    private final FastOutSlowInInterpolator f14887e;

    /* renamed from: f, reason: collision with root package name */
    private final BaseProgressIndicatorSpec f14888f;

    /* renamed from: g, reason: collision with root package name */
    private int f14889g;

    /* renamed from: h, reason: collision with root package name */
    private float f14890h;

    /* renamed from: i, reason: collision with root package name */
    private float f14891i;

    /* renamed from: j, reason: collision with root package name */
    Animatable2Compat.AnimationCallback f14892j;

    static {
        Class<Float> cls = Float.class;
        f14883n = new Property<CircularIndeterminateAnimatorDelegate, Float>(cls, "animationFraction") { // from class: com.google.android.material.progressindicator.CircularIndeterminateAnimatorDelegate.3
            @Override // android.util.Property
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Float get(CircularIndeterminateAnimatorDelegate circularIndeterminateAnimatorDelegate) {
                return Float.valueOf(circularIndeterminateAnimatorDelegate.o());
            }

            @Override // android.util.Property
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void set(CircularIndeterminateAnimatorDelegate circularIndeterminateAnimatorDelegate, Float f2) {
                circularIndeterminateAnimatorDelegate.setAnimationFraction(f2.floatValue());
            }
        };
        f14884o = new Property<CircularIndeterminateAnimatorDelegate, Float>(cls, "completeEndFraction") { // from class: com.google.android.material.progressindicator.CircularIndeterminateAnimatorDelegate.4
            @Override // android.util.Property
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Float get(CircularIndeterminateAnimatorDelegate circularIndeterminateAnimatorDelegate) {
                return Float.valueOf(circularIndeterminateAnimatorDelegate.p());
            }

            @Override // android.util.Property
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void set(CircularIndeterminateAnimatorDelegate circularIndeterminateAnimatorDelegate, Float f2) {
                circularIndeterminateAnimatorDelegate.s(f2.floatValue());
            }
        };
    }

    public CircularIndeterminateAnimatorDelegate(CircularProgressIndicatorSpec circularProgressIndicatorSpec) {
        super(1);
        this.f14889g = 0;
        this.f14892j = null;
        this.f14888f = circularProgressIndicatorSpec;
        this.f14887e = new FastOutSlowInInterpolator();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float o() {
        return this.f14890h;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float p() {
        return this.f14891i;
    }

    private void q() {
        if (this.f14885c == null) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, (Property<CircularIndeterminateAnimatorDelegate, Float>) f14883n, 0.0f, 1.0f);
            this.f14885c = ofFloat;
            ofFloat.setDuration(5400L);
            this.f14885c.setInterpolator(null);
            this.f14885c.setRepeatCount(-1);
            this.f14885c.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.progressindicator.CircularIndeterminateAnimatorDelegate.1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationRepeat(Animator animator) {
                    super.onAnimationRepeat(animator);
                    CircularIndeterminateAnimatorDelegate circularIndeterminateAnimatorDelegate = CircularIndeterminateAnimatorDelegate.this;
                    circularIndeterminateAnimatorDelegate.f14889g = (circularIndeterminateAnimatorDelegate.f14889g + 4) % CircularIndeterminateAnimatorDelegate.this.f14888f.f14870c.length;
                }
            });
        }
        if (this.f14886d == null) {
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, (Property<CircularIndeterminateAnimatorDelegate, Float>) f14884o, 0.0f, 1.0f);
            this.f14886d = ofFloat2;
            ofFloat2.setDuration(333L);
            this.f14886d.setInterpolator(this.f14887e);
            this.f14886d.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.progressindicator.CircularIndeterminateAnimatorDelegate.2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    CircularIndeterminateAnimatorDelegate.this.a();
                    CircularIndeterminateAnimatorDelegate circularIndeterminateAnimatorDelegate = CircularIndeterminateAnimatorDelegate.this;
                    Animatable2Compat.AnimationCallback animationCallback = circularIndeterminateAnimatorDelegate.f14892j;
                    if (animationCallback != null) {
                        animationCallback.b(circularIndeterminateAnimatorDelegate.f14918a);
                    }
                }
            });
        }
    }

    private void r(int i2) {
        for (int i3 = 0; i3 < 4; i3++) {
            float b2 = b(i2, f14882m[i3], 333);
            if (b2 >= 0.0f && b2 <= 1.0f) {
                int i4 = i3 + this.f14889g;
                int[] iArr = this.f14888f.f14870c;
                int length = i4 % iArr.length;
                int length2 = (length + 1) % iArr.length;
                int i5 = iArr[length];
                int i6 = iArr[length2];
                ((DrawingDelegate.ActiveIndicator) this.f14919b.get(0)).f14916c = ArgbEvaluatorCompat.b().evaluate(this.f14887e.getInterpolation(b2), Integer.valueOf(i5), Integer.valueOf(i6)).intValue();
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s(float f2) {
        this.f14891i = f2;
    }

    private void t(int i2) {
        DrawingDelegate.ActiveIndicator activeIndicator = (DrawingDelegate.ActiveIndicator) this.f14919b.get(0);
        float f2 = this.f14890h;
        activeIndicator.f14914a = (f2 * 1520.0f) - 20.0f;
        activeIndicator.f14915b = f2 * 1520.0f;
        for (int i3 = 0; i3 < 4; i3++) {
            activeIndicator.f14915b += this.f14887e.getInterpolation(b(i2, f14880k[i3], 667)) * 250.0f;
            activeIndicator.f14914a += this.f14887e.getInterpolation(b(i2, f14881l[i3], 667)) * 250.0f;
        }
        float f3 = activeIndicator.f14914a;
        float f4 = activeIndicator.f14915b;
        activeIndicator.f14914a = (f3 + ((f4 - f3) * this.f14891i)) / 360.0f;
        activeIndicator.f14915b = f4 / 360.0f;
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    void a() {
        ObjectAnimator objectAnimator = this.f14885c;
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
        this.f14892j = animationCallback;
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    void f() {
        ObjectAnimator objectAnimator = this.f14886d;
        if (objectAnimator == null || objectAnimator.isRunning()) {
            return;
        }
        if (this.f14918a.isVisible()) {
            this.f14886d.start();
        } else {
            a();
        }
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    void g() {
        q();
        resetPropertiesForNewStart();
        this.f14885c.start();
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void h() {
        this.f14892j = null;
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    @VisibleForTesting
    void resetPropertiesForNewStart() {
        this.f14889g = 0;
        ((DrawingDelegate.ActiveIndicator) this.f14919b.get(0)).f14916c = this.f14888f.f14870c[0];
        this.f14891i = 0.0f;
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    @VisibleForTesting
    void setAnimationFraction(float f2) {
        this.f14890h = f2;
        int i2 = (int) (f2 * 5400.0f);
        t(i2);
        r(i2);
        this.f14918a.invalidateSelf();
    }
}
