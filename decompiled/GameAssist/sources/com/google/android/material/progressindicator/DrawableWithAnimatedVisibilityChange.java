package com.google.android.material.progressindicator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.Property;
import androidx.annotation.FloatRange;
import androidx.annotation.VisibleForTesting;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import com.google.android.material.animation.AnimationUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
abstract class DrawableWithAnimatedVisibilityChange extends Drawable implements Animatable2Compat {
    private static final Property u = new Property<DrawableWithAnimatedVisibilityChange, Float>(Float.class, "growFraction") { // from class: com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange.3
        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float get(DrawableWithAnimatedVisibilityChange drawableWithAnimatedVisibilityChange) {
            return Float.valueOf(drawableWithAnimatedVisibilityChange.h());
        }

        @Override // android.util.Property
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(DrawableWithAnimatedVisibilityChange drawableWithAnimatedVisibilityChange, Float f2) {
            drawableWithAnimatedVisibilityChange.n(f2.floatValue());
        }
    };

    /* renamed from: c, reason: collision with root package name */
    final Context f14898c;

    /* renamed from: h, reason: collision with root package name */
    final BaseProgressIndicatorSpec f14899h;

    /* renamed from: j, reason: collision with root package name */
    private ValueAnimator f14901j;

    /* renamed from: k, reason: collision with root package name */
    private ValueAnimator f14902k;

    /* renamed from: l, reason: collision with root package name */
    private boolean f14903l;

    /* renamed from: m, reason: collision with root package name */
    private boolean f14904m;

    /* renamed from: n, reason: collision with root package name */
    private float f14905n;

    /* renamed from: o, reason: collision with root package name */
    private List f14906o;

    /* renamed from: p, reason: collision with root package name */
    private Animatable2Compat.AnimationCallback f14907p;

    /* renamed from: q, reason: collision with root package name */
    private boolean f14908q;

    /* renamed from: r, reason: collision with root package name */
    private float f14909r;
    private int t;

    /* renamed from: s, reason: collision with root package name */
    final Paint f14910s = new Paint();

    /* renamed from: i, reason: collision with root package name */
    AnimatorDurationScaleProvider f14900i = new AnimatorDurationScaleProvider();

    DrawableWithAnimatedVisibilityChange(Context context, BaseProgressIndicatorSpec baseProgressIndicatorSpec) {
        this.f14898c = context;
        this.f14899h = baseProgressIndicatorSpec;
        setAlpha(255);
    }

    private void d(ValueAnimator... valueAnimatorArr) {
        boolean z = this.f14908q;
        this.f14908q = true;
        for (ValueAnimator valueAnimator : valueAnimatorArr) {
            valueAnimator.cancel();
        }
        this.f14908q = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        Animatable2Compat.AnimationCallback animationCallback = this.f14907p;
        if (animationCallback != null) {
            animationCallback.b(this);
        }
        List list = this.f14906o;
        if (list == null || this.f14908q) {
            return;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((Animatable2Compat.AnimationCallback) it.next()).b(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        Animatable2Compat.AnimationCallback animationCallback = this.f14907p;
        if (animationCallback != null) {
            animationCallback.c(this);
        }
        List list = this.f14906o;
        if (list == null || this.f14908q) {
            return;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((Animatable2Compat.AnimationCallback) it.next()).c(this);
        }
    }

    private void g(ValueAnimator... valueAnimatorArr) {
        boolean z = this.f14908q;
        this.f14908q = true;
        for (ValueAnimator valueAnimator : valueAnimatorArr) {
            valueAnimator.end();
        }
        this.f14908q = z;
    }

    private void l() {
        if (this.f14901j == null) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, (Property<DrawableWithAnimatedVisibilityChange, Float>) u, 0.0f, 1.0f);
            this.f14901j = ofFloat;
            ofFloat.setDuration(500L);
            this.f14901j.setInterpolator(AnimationUtils.f13815b);
            p(this.f14901j);
        }
        if (this.f14902k == null) {
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, (Property<DrawableWithAnimatedVisibilityChange, Float>) u, 1.0f, 0.0f);
            this.f14902k = ofFloat2;
            ofFloat2.setDuration(500L);
            this.f14902k.setInterpolator(AnimationUtils.f13815b);
            o(this.f14902k);
        }
    }

    private void o(ValueAnimator valueAnimator) {
        ValueAnimator valueAnimator2 = this.f14902k;
        if (valueAnimator2 != null && valueAnimator2.isRunning()) {
            throw new IllegalArgumentException("Cannot set hideAnimator while the current hideAnimator is running.");
        }
        this.f14902k = valueAnimator;
        valueAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                DrawableWithAnimatedVisibilityChange.super.setVisible(false, false);
                DrawableWithAnimatedVisibilityChange.this.e();
            }
        });
    }

    private void p(ValueAnimator valueAnimator) {
        ValueAnimator valueAnimator2 = this.f14901j;
        if (valueAnimator2 != null && valueAnimator2.isRunning()) {
            throw new IllegalArgumentException("Cannot set showAnimator while the current showAnimator is running.");
        }
        this.f14901j = valueAnimator;
        valueAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                super.onAnimationStart(animator);
                DrawableWithAnimatedVisibilityChange.this.f();
            }
        });
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.t;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    float h() {
        if (this.f14899h.b() || this.f14899h.a()) {
            return (this.f14904m || this.f14903l) ? this.f14905n : this.f14909r;
        }
        return 1.0f;
    }

    public boolean i() {
        return q(false, false, false);
    }

    public boolean isRunning() {
        return k() || j();
    }

    public boolean j() {
        ValueAnimator valueAnimator = this.f14902k;
        return (valueAnimator != null && valueAnimator.isRunning()) || this.f14904m;
    }

    public boolean k() {
        ValueAnimator valueAnimator = this.f14901j;
        return (valueAnimator != null && valueAnimator.isRunning()) || this.f14903l;
    }

    public void m(Animatable2Compat.AnimationCallback animationCallback) {
        if (this.f14906o == null) {
            this.f14906o = new ArrayList();
        }
        if (this.f14906o.contains(animationCallback)) {
            return;
        }
        this.f14906o.add(animationCallback);
    }

    void n(float f2) {
        if (this.f14909r != f2) {
            this.f14909r = f2;
            invalidateSelf();
        }
    }

    public boolean q(boolean z, boolean z2, boolean z3) {
        return r(z, z2, z3 && this.f14900i.a(this.f14898c.getContentResolver()) > 0.0f);
    }

    boolean r(boolean z, boolean z2, boolean z3) {
        l();
        if (!isVisible() && !z) {
            return false;
        }
        ValueAnimator valueAnimator = z ? this.f14901j : this.f14902k;
        ValueAnimator valueAnimator2 = z ? this.f14902k : this.f14901j;
        if (!z3) {
            if (valueAnimator2.isRunning()) {
                d(valueAnimator2);
            }
            if (valueAnimator.isRunning()) {
                valueAnimator.end();
            } else {
                g(valueAnimator);
            }
            return super.setVisible(z, false);
        }
        if (valueAnimator.isRunning()) {
            return false;
        }
        boolean z4 = !z || super.setVisible(z, false);
        if (!(z ? this.f14899h.b() : this.f14899h.a())) {
            g(valueAnimator);
            return z4;
        }
        if (z2 || !valueAnimator.isPaused()) {
            valueAnimator.start();
        } else {
            valueAnimator.resume();
        }
        return z4;
    }

    public boolean s(Animatable2Compat.AnimationCallback animationCallback) {
        List list = this.f14906o;
        if (list == null || !list.contains(animationCallback)) {
            return false;
        }
        this.f14906o.remove(animationCallback);
        if (!this.f14906o.isEmpty()) {
            return true;
        }
        this.f14906o = null;
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.t = i2;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f14910s.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @VisibleForTesting
    void setMockHideAnimationRunning(boolean z, @FloatRange float f2) {
        this.f14904m = z;
        this.f14905n = f2;
    }

    @VisibleForTesting
    void setMockShowAnimationRunning(boolean z, @FloatRange float f2) {
        this.f14903l = z;
        this.f14905n = f2;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        return q(z, z2, true);
    }

    public void start() {
        r(true, true, false);
    }

    public void stop() {
        r(false, true, false);
    }
}
