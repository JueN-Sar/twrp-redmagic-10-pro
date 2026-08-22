package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.core.content.res.TypedArrayUtils;
import androidx.transition.Transition;

/* loaded from: classes.dex */
public class Fade extends Visibility {

    private static class FadeAnimatorListener extends AnimatorListenerAdapter implements Transition.TransitionListener {

        /* renamed from: c, reason: collision with root package name */
        private final View f5472c;

        /* renamed from: h, reason: collision with root package name */
        private boolean f5473h = false;

        FadeAnimatorListener(View view) {
            this.f5472c = view;
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void b(Transition transition) {
            this.f5472c.setTag(R.id.transition_pause_alpha, null);
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void c(Transition transition) {
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void d(Transition transition) {
            this.f5472c.setTag(R.id.transition_pause_alpha, Float.valueOf(this.f5472c.getVisibility() == 0 ? ViewUtils.b(this.f5472c) : 0.0f));
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void f(Transition transition) {
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void g(Transition transition) {
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void h(Transition transition, boolean z) {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            ViewUtils.f(this.f5472c, 1.0f);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            onAnimationEnd(animator, false);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            if (this.f5472c.hasOverlappingRendering() && this.f5472c.getLayerType() == 0) {
                this.f5473h = true;
                this.f5472c.setLayerType(2, null);
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator, boolean z) {
            if (this.f5473h) {
                this.f5472c.setLayerType(0, null);
            }
            if (z) {
                return;
            }
            ViewUtils.f(this.f5472c, 1.0f);
            ViewUtils.a(this.f5472c);
        }
    }

    public Fade(int i2) {
        x0(i2);
    }

    private Animator y0(View view, float f2, float f3) {
        if (f2 == f3) {
            return null;
        }
        ViewUtils.f(view, f2);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, (Property<View, Float>) ViewUtils.f5586b, f3);
        FadeAnimatorListener fadeAnimatorListener = new FadeAnimatorListener(view);
        ofFloat.addListener(fadeAnimatorListener);
        A().a(fadeAnimatorListener);
        return ofFloat;
    }

    private static float z0(TransitionValues transitionValues, float f2) {
        Float f3;
        return (transitionValues == null || (f3 = (Float) transitionValues.f5570a.get("android:fade:transitionAlpha")) == null) ? f2 : f3.floatValue();
    }

    @Override // androidx.transition.Visibility, androidx.transition.Transition
    public void l(TransitionValues transitionValues) {
        super.l(transitionValues);
        Float f2 = (Float) transitionValues.f5571b.getTag(R.id.transition_pause_alpha);
        if (f2 == null) {
            f2 = transitionValues.f5571b.getVisibility() == 0 ? Float.valueOf(ViewUtils.b(transitionValues.f5571b)) : Float.valueOf(0.0f);
        }
        transitionValues.f5570a.put("android:fade:transitionAlpha", f2);
    }

    @Override // androidx.transition.Visibility
    public Animator t0(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        ViewUtils.c(view);
        return y0(view, z0(transitionValues, 0.0f), 1.0f);
    }

    @Override // androidx.transition.Visibility
    public Animator v0(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        ViewUtils.c(view);
        Animator y0 = y0(view, z0(transitionValues, 1.0f), 0.0f);
        if (y0 == null) {
            ViewUtils.f(view, z0(transitionValues2, 1.0f));
        }
        return y0;
    }

    public Fade() {
    }

    public Fade(@NonNull Context context, @NonNull AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, Styleable.f5513f);
        x0(TypedArrayUtils.k(obtainStyledAttributes, (XmlResourceParser) attributeSet, "fadingMode", 0, r0()));
        obtainStyledAttributes.recycle();
    }
}
