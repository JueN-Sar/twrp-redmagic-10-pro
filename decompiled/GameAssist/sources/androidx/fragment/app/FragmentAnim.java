package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import androidx.core.view.OneShotPreDrawListener;
import androidx.fragment.R;

/* loaded from: classes.dex */
class FragmentAnim {
    private static int a(Fragment fragment, boolean z, boolean z2) {
        return z2 ? z ? fragment.Q() : fragment.R() : z ? fragment.A() : fragment.D();
    }

    static AnimationOrAnimator b(Context context, Fragment fragment, boolean z, boolean z2) {
        int M = fragment.M();
        int a2 = a(fragment, z, z2);
        fragment.I1(0, 0, 0, 0);
        ViewGroup viewGroup = fragment.N;
        if (viewGroup != null && viewGroup.getTag(R.id.visible_removing_fragment_view_tag) != null) {
            fragment.N.setTag(R.id.visible_removing_fragment_view_tag, null);
        }
        ViewGroup viewGroup2 = fragment.N;
        if (viewGroup2 != null && viewGroup2.getLayoutTransition() != null) {
            return null;
        }
        Animation E0 = fragment.E0(M, z, a2);
        if (E0 != null) {
            return new AnimationOrAnimator(E0);
        }
        Animator F0 = fragment.F0(M, z, a2);
        if (F0 != null) {
            return new AnimationOrAnimator(F0);
        }
        if (a2 == 0 && M != 0) {
            a2 = d(context, M, z);
        }
        if (a2 != 0) {
            boolean equals = "anim".equals(context.getResources().getResourceTypeName(a2));
            if (equals) {
                try {
                    Animation loadAnimation = AnimationUtils.loadAnimation(context, a2);
                    if (loadAnimation != null) {
                        return new AnimationOrAnimator(loadAnimation);
                    }
                } catch (Resources.NotFoundException e2) {
                    throw e2;
                } catch (RuntimeException unused) {
                }
            }
            try {
                Animator loadAnimator = AnimatorInflater.loadAnimator(context, a2);
                if (loadAnimator != null) {
                    return new AnimationOrAnimator(loadAnimator);
                }
            } catch (RuntimeException e3) {
                if (equals) {
                    throw e3;
                }
                Animation loadAnimation2 = AnimationUtils.loadAnimation(context, a2);
                if (loadAnimation2 != null) {
                    return new AnimationOrAnimator(loadAnimation2);
                }
            }
        }
        return null;
    }

    private static int c(Context context, int i2) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(android.R.style.Animation.Activity, new int[]{i2});
        int resourceId = obtainStyledAttributes.getResourceId(0, -1);
        obtainStyledAttributes.recycle();
        return resourceId;
    }

    private static int d(Context context, int i2, boolean z) {
        if (i2 == 4097) {
            return z ? R.animator.fragment_open_enter : R.animator.fragment_open_exit;
        }
        if (i2 == 8194) {
            return z ? R.animator.fragment_close_enter : R.animator.fragment_close_exit;
        }
        if (i2 == 8197) {
            return z ? c(context, android.R.attr.activityCloseEnterAnimation) : c(context, android.R.attr.activityCloseExitAnimation);
        }
        if (i2 == 4099) {
            return z ? R.animator.fragment_fade_enter : R.animator.fragment_fade_exit;
        }
        if (i2 != 4100) {
            return -1;
        }
        return z ? c(context, android.R.attr.activityOpenEnterAnimation) : c(context, android.R.attr.activityOpenExitAnimation);
    }

    static class AnimationOrAnimator {

        /* renamed from: a, reason: collision with root package name */
        public final Animation f4023a;

        /* renamed from: b, reason: collision with root package name */
        public final Animator f4024b;

        AnimationOrAnimator(Animation animation) {
            this.f4023a = animation;
            this.f4024b = null;
            if (animation == null) {
                throw new IllegalStateException("Animation cannot be null");
            }
        }

        AnimationOrAnimator(Animator animator) {
            this.f4023a = null;
            this.f4024b = animator;
            if (animator == null) {
                throw new IllegalStateException("Animator cannot be null");
            }
        }
    }

    static class EndViewTransitionAnimation extends AnimationSet implements Runnable {

        /* renamed from: c, reason: collision with root package name */
        private final ViewGroup f4025c;

        /* renamed from: h, reason: collision with root package name */
        private final View f4026h;

        /* renamed from: i, reason: collision with root package name */
        private boolean f4027i;

        /* renamed from: j, reason: collision with root package name */
        private boolean f4028j;

        /* renamed from: k, reason: collision with root package name */
        private boolean f4029k;

        EndViewTransitionAnimation(Animation animation, ViewGroup viewGroup, View view) {
            super(false);
            this.f4029k = true;
            this.f4025c = viewGroup;
            this.f4026h = view;
            addAnimation(animation);
            viewGroup.post(this);
        }

        @Override // android.view.animation.AnimationSet, android.view.animation.Animation
        public boolean getTransformation(long j2, Transformation transformation) {
            this.f4029k = true;
            if (this.f4027i) {
                return !this.f4028j;
            }
            if (!super.getTransformation(j2, transformation)) {
                this.f4027i = true;
                OneShotPreDrawListener.a(this.f4025c, this);
            }
            return true;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f4027i || !this.f4029k) {
                this.f4025c.endViewTransition(this.f4026h);
                this.f4028j = true;
            } else {
                this.f4029k = false;
                this.f4025c.post(this);
            }
        }

        @Override // android.view.animation.Animation
        public boolean getTransformation(long j2, Transformation transformation, float f2) {
            this.f4029k = true;
            if (this.f4027i) {
                return !this.f4028j;
            }
            if (!super.getTransformation(j2, transformation, f2)) {
                this.f4027i = true;
                OneShotPreDrawListener.a(this.f4025c, this);
            }
            return true;
        }
    }
}
