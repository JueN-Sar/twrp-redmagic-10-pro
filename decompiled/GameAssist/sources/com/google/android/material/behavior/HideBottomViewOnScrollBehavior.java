package com.google.android.material.behavior;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import androidx.annotation.RestrictTo;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.motion.MotionUtils;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* loaded from: classes.dex */
public class HideBottomViewOnScrollBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {

    /* renamed from: p, reason: collision with root package name */
    private static final int f13955p = R.attr.motionDurationLong2;

    /* renamed from: q, reason: collision with root package name */
    private static final int f13956q = R.attr.motionDurationMedium4;

    /* renamed from: r, reason: collision with root package name */
    private static final int f13957r = R.attr.motionEasingEmphasizedInterpolator;

    /* renamed from: c, reason: collision with root package name */
    private final LinkedHashSet f13958c;

    /* renamed from: h, reason: collision with root package name */
    private int f13959h;

    /* renamed from: i, reason: collision with root package name */
    private int f13960i;

    /* renamed from: j, reason: collision with root package name */
    private TimeInterpolator f13961j;

    /* renamed from: k, reason: collision with root package name */
    private TimeInterpolator f13962k;

    /* renamed from: l, reason: collision with root package name */
    private int f13963l;

    /* renamed from: m, reason: collision with root package name */
    private int f13964m;

    /* renamed from: n, reason: collision with root package name */
    private int f13965n;

    /* renamed from: o, reason: collision with root package name */
    private ViewPropertyAnimator f13966o;

    public interface OnScrollStateChangedListener {
        void a(View view, int i2);
    }

    @RestrictTo
    public @interface ScrollState {
    }

    public HideBottomViewOnScrollBehavior() {
        this.f13958c = new LinkedHashSet();
        this.f13963l = 0;
        this.f13964m = 2;
        this.f13965n = 0;
    }

    private void K(View view, int i2, long j2, TimeInterpolator timeInterpolator) {
        this.f13966o = view.animate().translationY(i2).setInterpolator(timeInterpolator).setDuration(j2).setListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.behavior.HideBottomViewOnScrollBehavior.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                HideBottomViewOnScrollBehavior.this.f13966o = null;
            }
        });
    }

    private void S(View view, int i2) {
        this.f13964m = i2;
        Iterator it = this.f13958c.iterator();
        while (it.hasNext()) {
            ((OnScrollStateChangedListener) it.next()).a(view, this.f13964m);
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean F(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i2, int i3) {
        return i2 == 2;
    }

    public boolean L() {
        return this.f13964m == 1;
    }

    public boolean M() {
        return this.f13964m == 2;
    }

    public void N(View view, int i2) {
        this.f13965n = i2;
        if (this.f13964m == 1) {
            view.setTranslationY(this.f13963l + i2);
        }
    }

    public void O(View view) {
        P(view, true);
    }

    public void P(View view, boolean z) {
        if (L()) {
            return;
        }
        ViewPropertyAnimator viewPropertyAnimator = this.f13966o;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
            view.clearAnimation();
        }
        S(view, 1);
        int i2 = this.f13963l + this.f13965n;
        if (z) {
            K(view, i2, this.f13960i, this.f13962k);
        } else {
            view.setTranslationY(i2);
        }
    }

    public void Q(View view) {
        R(view, true);
    }

    public void R(View view, boolean z) {
        if (M()) {
            return;
        }
        ViewPropertyAnimator viewPropertyAnimator = this.f13966o;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
            view.clearAnimation();
        }
        S(view, 2);
        if (z) {
            K(view, 0, this.f13959h, this.f13961j);
        } else {
            view.setTranslationY(0);
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean q(CoordinatorLayout coordinatorLayout, View view, int i2) {
        this.f13963l = view.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).bottomMargin;
        this.f13959h = MotionUtils.f(view.getContext(), f13955p, 225);
        this.f13960i = MotionUtils.f(view.getContext(), f13956q, 175);
        Context context = view.getContext();
        int i3 = f13957r;
        this.f13961j = MotionUtils.g(context, i3, AnimationUtils.f13817d);
        this.f13962k = MotionUtils.g(view.getContext(), i3, AnimationUtils.f13816c);
        return super.q(coordinatorLayout, view, i2);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void y(CoordinatorLayout coordinatorLayout, View view, View view2, int i2, int i3, int i4, int i5, int i6, int[] iArr) {
        if (i3 > 0) {
            O(view);
        } else if (i3 < 0) {
            Q(view);
        }
    }

    public HideBottomViewOnScrollBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f13958c = new LinkedHashSet();
        this.f13963l = 0;
        this.f13964m = 2;
        this.f13965n = 0;
    }
}
