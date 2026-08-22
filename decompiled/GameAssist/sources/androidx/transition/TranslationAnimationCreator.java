package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.util.Property;
import android.view.View;
import androidx.transition.Transition;

/* loaded from: classes.dex */
class TranslationAnimationCreator {
    static Animator a(View view, TransitionValues transitionValues, int i2, int i3, float f2, float f3, float f4, float f5, TimeInterpolator timeInterpolator, Transition transition) {
        float f6;
        float f7;
        float translationX = view.getTranslationX();
        float translationY = view.getTranslationY();
        if (((int[]) transitionValues.f5571b.getTag(R.id.transition_position)) != null) {
            f6 = (r7[0] - i2) + translationX;
            f7 = (r7[1] - i3) + translationY;
        } else {
            f6 = f2;
            f7 = f3;
        }
        view.setTranslationX(f6);
        view.setTranslationY(f7);
        if (f6 == f4 && f7 == f5) {
            return null;
        }
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, PropertyValuesHolder.ofFloat((Property<?, Float>) View.TRANSLATION_X, f6, f4), PropertyValuesHolder.ofFloat((Property<?, Float>) View.TRANSLATION_Y, f7, f5));
        TransitionPositionListener transitionPositionListener = new TransitionPositionListener(view, transitionValues.f5571b, translationX, translationY);
        transition.a(transitionPositionListener);
        ofPropertyValuesHolder.addListener(transitionPositionListener);
        ofPropertyValuesHolder.setInterpolator(timeInterpolator);
        return ofPropertyValuesHolder;
    }

    private static class TransitionPositionListener extends AnimatorListenerAdapter implements Transition.TransitionListener {

        /* renamed from: c, reason: collision with root package name */
        private final View f5577c;

        /* renamed from: h, reason: collision with root package name */
        private final View f5578h;

        /* renamed from: i, reason: collision with root package name */
        private int[] f5579i;

        /* renamed from: j, reason: collision with root package name */
        private float f5580j;

        /* renamed from: k, reason: collision with root package name */
        private float f5581k;

        /* renamed from: l, reason: collision with root package name */
        private final float f5582l;

        /* renamed from: m, reason: collision with root package name */
        private final float f5583m;

        /* renamed from: n, reason: collision with root package name */
        private boolean f5584n;

        TransitionPositionListener(View view, View view2, float f2, float f3) {
            this.f5578h = view;
            this.f5577c = view2;
            this.f5582l = f2;
            this.f5583m = f3;
            int[] iArr = (int[]) view2.getTag(R.id.transition_position);
            this.f5579i = iArr;
            if (iArr != null) {
                view2.setTag(R.id.transition_position, null);
            }
        }

        private void a() {
            if (this.f5579i == null) {
                this.f5579i = new int[2];
            }
            this.f5578h.getLocationOnScreen(this.f5579i);
            this.f5577c.setTag(R.id.transition_position, this.f5579i);
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void b(Transition transition) {
            this.f5578h.setTranslationX(this.f5580j);
            this.f5578h.setTranslationY(this.f5581k);
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void c(Transition transition) {
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void d(Transition transition) {
            a();
            this.f5580j = this.f5578h.getTranslationX();
            this.f5581k = this.f5578h.getTranslationY();
            this.f5578h.setTranslationX(this.f5582l);
            this.f5578h.setTranslationY(this.f5583m);
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void e(Transition transition, boolean z) {
            if (this.f5584n) {
                return;
            }
            this.f5577c.setTag(R.id.transition_position, null);
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void f(Transition transition) {
            e(transition, false);
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void g(Transition transition) {
            this.f5584n = true;
            this.f5578h.setTranslationX(this.f5582l);
            this.f5578h.setTranslationY(this.f5583m);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f5584n = true;
            this.f5578h.setTranslationX(this.f5582l);
            this.f5578h.setTranslationY(this.f5583m);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator, boolean z) {
            if (z) {
                return;
            }
            this.f5578h.setTranslationX(this.f5582l);
            this.f5578h.setTranslationY(this.f5583m);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            onAnimationEnd(animator, false);
        }
    }
}
