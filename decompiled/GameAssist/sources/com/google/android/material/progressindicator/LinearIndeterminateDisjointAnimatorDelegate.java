package com.google.android.material.progressindicator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.Property;
import android.view.animation.Interpolator;
import androidx.annotation.VisibleForTesting;
import androidx.core.math.MathUtils;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import androidx.vectordrawable.graphics.drawable.AnimationUtilsCompat;
import com.google.android.material.R;
import com.google.android.material.progressindicator.DrawingDelegate;
import java.util.Iterator;

/* loaded from: classes.dex */
final class LinearIndeterminateDisjointAnimatorDelegate extends IndeterminateAnimatorDelegate<ObjectAnimator> {

    /* renamed from: k, reason: collision with root package name */
    private static final int[] f14933k = {533, 567, 850, 750};

    /* renamed from: l, reason: collision with root package name */
    private static final int[] f14934l = {1267, 1000, 333, 0};

    /* renamed from: m, reason: collision with root package name */
    private static final Property f14935m = new Property<LinearIndeterminateDisjointAnimatorDelegate, Float>(Float.class, "animationFraction") { // from class: com.google.android.material.progressindicator.LinearIndeterminateDisjointAnimatorDelegate.3
        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float get(LinearIndeterminateDisjointAnimatorDelegate linearIndeterminateDisjointAnimatorDelegate) {
            return Float.valueOf(linearIndeterminateDisjointAnimatorDelegate.n());
        }

        @Override // android.util.Property
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(LinearIndeterminateDisjointAnimatorDelegate linearIndeterminateDisjointAnimatorDelegate, Float f2) {
            linearIndeterminateDisjointAnimatorDelegate.setAnimationFraction(f2.floatValue());
        }
    };

    /* renamed from: c, reason: collision with root package name */
    private ObjectAnimator f14936c;

    /* renamed from: d, reason: collision with root package name */
    private ObjectAnimator f14937d;

    /* renamed from: e, reason: collision with root package name */
    private final Interpolator[] f14938e;

    /* renamed from: f, reason: collision with root package name */
    private final BaseProgressIndicatorSpec f14939f;

    /* renamed from: g, reason: collision with root package name */
    private int f14940g;

    /* renamed from: h, reason: collision with root package name */
    private boolean f14941h;

    /* renamed from: i, reason: collision with root package name */
    private float f14942i;

    /* renamed from: j, reason: collision with root package name */
    Animatable2Compat.AnimationCallback f14943j;

    public LinearIndeterminateDisjointAnimatorDelegate(Context context, LinearProgressIndicatorSpec linearProgressIndicatorSpec) {
        super(2);
        this.f14940g = 0;
        this.f14943j = null;
        this.f14939f = linearProgressIndicatorSpec;
        this.f14938e = new Interpolator[]{AnimationUtilsCompat.a(context, R.anim.linear_indeterminate_line1_head_interpolator), AnimationUtilsCompat.a(context, R.anim.linear_indeterminate_line1_tail_interpolator), AnimationUtilsCompat.a(context, R.anim.linear_indeterminate_line2_head_interpolator), AnimationUtilsCompat.a(context, R.anim.linear_indeterminate_line2_tail_interpolator)};
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float n() {
        return this.f14942i;
    }

    private void o() {
        if (this.f14936c == null) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, (Property<LinearIndeterminateDisjointAnimatorDelegate, Float>) f14935m, 0.0f, 1.0f);
            this.f14936c = ofFloat;
            ofFloat.setDuration(1800L);
            this.f14936c.setInterpolator(null);
            this.f14936c.setRepeatCount(-1);
            this.f14936c.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.progressindicator.LinearIndeterminateDisjointAnimatorDelegate.1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationRepeat(Animator animator) {
                    super.onAnimationRepeat(animator);
                    LinearIndeterminateDisjointAnimatorDelegate linearIndeterminateDisjointAnimatorDelegate = LinearIndeterminateDisjointAnimatorDelegate.this;
                    linearIndeterminateDisjointAnimatorDelegate.f14940g = (linearIndeterminateDisjointAnimatorDelegate.f14940g + 1) % LinearIndeterminateDisjointAnimatorDelegate.this.f14939f.f14870c.length;
                    LinearIndeterminateDisjointAnimatorDelegate.this.f14941h = true;
                }
            });
        }
        if (this.f14937d == null) {
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, (Property<LinearIndeterminateDisjointAnimatorDelegate, Float>) f14935m, 1.0f);
            this.f14937d = ofFloat2;
            ofFloat2.setDuration(1800L);
            this.f14937d.setInterpolator(null);
            this.f14937d.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.progressindicator.LinearIndeterminateDisjointAnimatorDelegate.2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    LinearIndeterminateDisjointAnimatorDelegate.this.a();
                    LinearIndeterminateDisjointAnimatorDelegate linearIndeterminateDisjointAnimatorDelegate = LinearIndeterminateDisjointAnimatorDelegate.this;
                    Animatable2Compat.AnimationCallback animationCallback = linearIndeterminateDisjointAnimatorDelegate.f14943j;
                    if (animationCallback != null) {
                        animationCallback.b(linearIndeterminateDisjointAnimatorDelegate.f14918a);
                    }
                }
            });
        }
    }

    private void p() {
        if (this.f14941h) {
            Iterator it = this.f14919b.iterator();
            while (it.hasNext()) {
                ((DrawingDelegate.ActiveIndicator) it.next()).f14916c = this.f14939f.f14870c[this.f14940g];
            }
            this.f14941h = false;
        }
    }

    private void q(int i2) {
        for (int i3 = 0; i3 < this.f14919b.size(); i3++) {
            DrawingDelegate.ActiveIndicator activeIndicator = (DrawingDelegate.ActiveIndicator) this.f14919b.get(i3);
            int[] iArr = f14934l;
            int i4 = i3 * 2;
            int i5 = iArr[i4];
            int[] iArr2 = f14933k;
            activeIndicator.f14914a = MathUtils.a(this.f14938e[i4].getInterpolation(b(i2, i5, iArr2[i4])), 0.0f, 1.0f);
            int i6 = i4 + 1;
            activeIndicator.f14915b = MathUtils.a(this.f14938e[i6].getInterpolation(b(i2, iArr[i6], iArr2[i6])), 0.0f, 1.0f);
        }
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void a() {
        ObjectAnimator objectAnimator = this.f14936c;
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
        this.f14943j = animationCallback;
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void f() {
        ObjectAnimator objectAnimator = this.f14937d;
        if (objectAnimator == null || objectAnimator.isRunning()) {
            return;
        }
        a();
        if (this.f14918a.isVisible()) {
            this.f14937d.setFloatValues(this.f14942i, 1.0f);
            this.f14937d.setDuration((long) ((1.0f - this.f14942i) * 1800.0f));
            this.f14937d.start();
        }
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void g() {
        o();
        resetPropertiesForNewStart();
        this.f14936c.start();
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void h() {
        this.f14943j = null;
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    @VisibleForTesting
    void resetPropertiesForNewStart() {
        this.f14940g = 0;
        Iterator it = this.f14919b.iterator();
        while (it.hasNext()) {
            ((DrawingDelegate.ActiveIndicator) it.next()).f14916c = this.f14939f.f14870c[0];
        }
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    @VisibleForTesting
    void setAnimationFraction(float f2) {
        this.f14942i = f2;
        q((int) (f2 * 1800.0f));
        p();
        this.f14918a.invalidateSelf();
    }
}
