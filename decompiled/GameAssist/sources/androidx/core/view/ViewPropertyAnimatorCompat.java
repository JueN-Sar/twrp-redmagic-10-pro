package androidx.core.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Interpolator;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public final class ViewPropertyAnimatorCompat {

    /* renamed from: a, reason: collision with root package name */
    private final WeakReference f3400a;

    @RequiresApi
    static class Api21Impl {
        @DoNotInline
        static ViewPropertyAnimator a(ViewPropertyAnimator viewPropertyAnimator, float f2) {
            return viewPropertyAnimator.translationZ(f2);
        }

        @DoNotInline
        static ViewPropertyAnimator b(ViewPropertyAnimator viewPropertyAnimator, float f2) {
            return viewPropertyAnimator.translationZBy(f2);
        }

        @DoNotInline
        static ViewPropertyAnimator c(ViewPropertyAnimator viewPropertyAnimator, float f2) {
            return viewPropertyAnimator.z(f2);
        }

        @DoNotInline
        static ViewPropertyAnimator d(ViewPropertyAnimator viewPropertyAnimator, float f2) {
            return viewPropertyAnimator.zBy(f2);
        }
    }

    ViewPropertyAnimatorCompat(View view) {
        this.f3400a = new WeakReference(view);
    }

    private void i(final View view, final ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        if (viewPropertyAnimatorListener != null) {
            view.animate().setListener(new AnimatorListenerAdapter() { // from class: androidx.core.view.ViewPropertyAnimatorCompat.1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animator) {
                    viewPropertyAnimatorListener.a(view);
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    viewPropertyAnimatorListener.b(view);
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                    viewPropertyAnimatorListener.c(view);
                }
            });
        } else {
            view.animate().setListener(null);
        }
    }

    public ViewPropertyAnimatorCompat b(float f2) {
        View view = (View) this.f3400a.get();
        if (view != null) {
            view.animate().alpha(f2);
        }
        return this;
    }

    public void c() {
        View view = (View) this.f3400a.get();
        if (view != null) {
            view.animate().cancel();
        }
    }

    public long d() {
        View view = (View) this.f3400a.get();
        if (view != null) {
            return view.animate().getDuration();
        }
        return 0L;
    }

    public ViewPropertyAnimatorCompat f(long j2) {
        View view = (View) this.f3400a.get();
        if (view != null) {
            view.animate().setDuration(j2);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat g(Interpolator interpolator) {
        View view = (View) this.f3400a.get();
        if (view != null) {
            view.animate().setInterpolator(interpolator);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat h(ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        View view = (View) this.f3400a.get();
        if (view != null) {
            i(view, viewPropertyAnimatorListener);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat j(long j2) {
        View view = (View) this.f3400a.get();
        if (view != null) {
            view.animate().setStartDelay(j2);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat k(final ViewPropertyAnimatorUpdateListener viewPropertyAnimatorUpdateListener) {
        final View view = (View) this.f3400a.get();
        if (view != null) {
            view.animate().setUpdateListener(viewPropertyAnimatorUpdateListener != null ? new ValueAnimator.AnimatorUpdateListener() { // from class: androidx.core.view.j
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    ViewPropertyAnimatorUpdateListener.this.a(view);
                }
            } : null);
        }
        return this;
    }

    public void l() {
        View view = (View) this.f3400a.get();
        if (view != null) {
            view.animate().start();
        }
    }

    public ViewPropertyAnimatorCompat m(float f2) {
        View view = (View) this.f3400a.get();
        if (view != null) {
            view.animate().translationY(f2);
        }
        return this;
    }
}
