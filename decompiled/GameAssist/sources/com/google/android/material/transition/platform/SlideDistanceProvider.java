package com.google.android.material.transition.platform;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@RequiresApi
/* loaded from: classes.dex */
public final class SlideDistanceProvider implements VisibilityAnimatorProvider {

    /* renamed from: a, reason: collision with root package name */
    private int f15762a;

    /* renamed from: b, reason: collision with root package name */
    private int f15763b;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface GravityFlag {
    }

    private static Animator c(View view, View view2, int i2, int i3) {
        float translationX = view2.getTranslationX();
        float translationY = view2.getTranslationY();
        if (i2 == 3) {
            return e(view2, i3 + translationX, translationX, translationX);
        }
        if (i2 == 5) {
            return e(view2, translationX - i3, translationX, translationX);
        }
        if (i2 == 48) {
            return f(view2, translationY - i3, translationY, translationY);
        }
        if (i2 == 80) {
            return f(view2, i3 + translationY, translationY, translationY);
        }
        if (i2 == 8388611) {
            return e(view2, h(view) ? i3 + translationX : translationX - i3, translationX, translationX);
        }
        if (i2 == 8388613) {
            return e(view2, h(view) ? translationX - i3 : i3 + translationX, translationX, translationX);
        }
        throw new IllegalArgumentException("Invalid slide direction: " + i2);
    }

    private static Animator d(View view, View view2, int i2, int i3) {
        float translationX = view2.getTranslationX();
        float translationY = view2.getTranslationY();
        if (i2 == 3) {
            return e(view2, translationX, translationX - i3, translationX);
        }
        if (i2 == 5) {
            return e(view2, translationX, i3 + translationX, translationX);
        }
        if (i2 == 48) {
            return f(view2, translationY, i3 + translationY, translationY);
        }
        if (i2 == 80) {
            return f(view2, translationY, translationY - i3, translationY);
        }
        if (i2 == 8388611) {
            return e(view2, translationX, h(view) ? translationX - i3 : i3 + translationX, translationX);
        }
        if (i2 == 8388613) {
            return e(view2, translationX, h(view) ? i3 + translationX : translationX - i3, translationX);
        }
        throw new IllegalArgumentException("Invalid slide direction: " + i2);
    }

    private static Animator e(final View view, float f2, float f3, final float f4) {
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, PropertyValuesHolder.ofFloat((Property<?, Float>) View.TRANSLATION_X, f2, f3));
        ofPropertyValuesHolder.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.transition.platform.SlideDistanceProvider.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                view.setTranslationX(f4);
            }
        });
        return ofPropertyValuesHolder;
    }

    private static Animator f(final View view, float f2, float f3, final float f4) {
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, PropertyValuesHolder.ofFloat((Property<?, Float>) View.TRANSLATION_Y, f2, f3));
        ofPropertyValuesHolder.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.transition.platform.SlideDistanceProvider.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                view.setTranslationY(f4);
            }
        });
        return ofPropertyValuesHolder;
    }

    private int g(Context context) {
        int i2 = this.f15763b;
        return i2 != -1 ? i2 : context.getResources().getDimensionPixelSize(R.dimen.mtrl_transition_shared_axis_slide_distance);
    }

    private static boolean h(View view) {
        return ViewCompat.v(view) == 1;
    }

    @Override // com.google.android.material.transition.platform.VisibilityAnimatorProvider
    public Animator a(ViewGroup viewGroup, View view) {
        return d(viewGroup, view, this.f15762a, g(view.getContext()));
    }

    @Override // com.google.android.material.transition.platform.VisibilityAnimatorProvider
    public Animator b(ViewGroup viewGroup, View view) {
        return c(viewGroup, view, this.f15762a, g(view.getContext()));
    }
}
