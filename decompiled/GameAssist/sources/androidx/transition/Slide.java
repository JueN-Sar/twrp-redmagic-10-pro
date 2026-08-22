package androidx.transition;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.core.content.res.TypedArrayUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes.dex */
public class Slide extends Visibility {
    private static final TimeInterpolator a0 = new DecelerateInterpolator();
    private static final TimeInterpolator b0 = new AccelerateInterpolator();
    private static final CalculateSlide c0 = new CalculateSlideHorizontal() { // from class: androidx.transition.Slide.1
        @Override // androidx.transition.Slide.CalculateSlide
        public float b(ViewGroup viewGroup, View view) {
            return view.getTranslationX() - viewGroup.getWidth();
        }
    };
    private static final CalculateSlide d0 = new CalculateSlideHorizontal() { // from class: androidx.transition.Slide.2
        @Override // androidx.transition.Slide.CalculateSlide
        public float b(ViewGroup viewGroup, View view) {
            return viewGroup.getLayoutDirection() == 1 ? view.getTranslationX() + viewGroup.getWidth() : view.getTranslationX() - viewGroup.getWidth();
        }
    };
    private static final CalculateSlide e0 = new CalculateSlideVertical() { // from class: androidx.transition.Slide.3
        @Override // androidx.transition.Slide.CalculateSlide
        public float a(ViewGroup viewGroup, View view) {
            return view.getTranslationY() - viewGroup.getHeight();
        }
    };
    private static final CalculateSlide f0 = new CalculateSlideHorizontal() { // from class: androidx.transition.Slide.4
        @Override // androidx.transition.Slide.CalculateSlide
        public float b(ViewGroup viewGroup, View view) {
            return view.getTranslationX() + viewGroup.getWidth();
        }
    };
    private static final CalculateSlide g0 = new CalculateSlideHorizontal() { // from class: androidx.transition.Slide.5
        @Override // androidx.transition.Slide.CalculateSlide
        public float b(ViewGroup viewGroup, View view) {
            return viewGroup.getLayoutDirection() == 1 ? view.getTranslationX() - viewGroup.getWidth() : view.getTranslationX() + viewGroup.getWidth();
        }
    };
    private static final CalculateSlide h0 = new CalculateSlideVertical() { // from class: androidx.transition.Slide.6
        @Override // androidx.transition.Slide.CalculateSlide
        public float a(ViewGroup viewGroup, View view) {
            return view.getTranslationY() + viewGroup.getHeight();
        }
    };
    private CalculateSlide Y;
    private int Z;

    private interface CalculateSlide {
        float a(ViewGroup viewGroup, View view);

        float b(ViewGroup viewGroup, View view);
    }

    private static abstract class CalculateSlideHorizontal implements CalculateSlide {
        private CalculateSlideHorizontal() {
        }

        @Override // androidx.transition.Slide.CalculateSlide
        public float a(ViewGroup viewGroup, View view) {
            return view.getTranslationY();
        }
    }

    private static abstract class CalculateSlideVertical implements CalculateSlide {
        private CalculateSlideVertical() {
        }

        @Override // androidx.transition.Slide.CalculateSlide
        public float b(ViewGroup viewGroup, View view) {
            return view.getTranslationX();
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface GravityFlag {
    }

    public Slide(@NonNull Context context, @NonNull AttributeSet attributeSet) {
        super(context, attributeSet);
        this.Y = h0;
        this.Z = 80;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, Styleable.f5515h);
        int k2 = TypedArrayUtils.k(obtainStyledAttributes, (XmlPullParser) attributeSet, "slideEdge", 0, 80);
        obtainStyledAttributes.recycle();
        y0(k2);
    }

    private void q0(TransitionValues transitionValues) {
        int[] iArr = new int[2];
        transitionValues.f5571b.getLocationOnScreen(iArr);
        transitionValues.f5570a.put("android:slide:screenPosition", iArr);
    }

    @Override // androidx.transition.Visibility, androidx.transition.Transition
    public void i(TransitionValues transitionValues) {
        super.i(transitionValues);
        q0(transitionValues);
    }

    @Override // androidx.transition.Visibility, androidx.transition.Transition
    public void l(TransitionValues transitionValues) {
        super.l(transitionValues);
        q0(transitionValues);
    }

    @Override // androidx.transition.Visibility
    public Animator t0(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (transitionValues2 == null) {
            return null;
        }
        int[] iArr = (int[]) transitionValues2.f5570a.get("android:slide:screenPosition");
        float translationX = view.getTranslationX();
        float translationY = view.getTranslationY();
        return TranslationAnimationCreator.a(view, transitionValues2, iArr[0], iArr[1], this.Y.b(viewGroup, view), this.Y.a(viewGroup, view), translationX, translationY, a0, this);
    }

    @Override // androidx.transition.Visibility
    public Animator v0(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (transitionValues == null) {
            return null;
        }
        int[] iArr = (int[]) transitionValues.f5570a.get("android:slide:screenPosition");
        return TranslationAnimationCreator.a(view, transitionValues, iArr[0], iArr[1], view.getTranslationX(), view.getTranslationY(), this.Y.b(viewGroup, view), this.Y.a(viewGroup, view), b0, this);
    }

    public void y0(int i2) {
        if (i2 == 3) {
            this.Y = c0;
        } else if (i2 == 5) {
            this.Y = f0;
        } else if (i2 == 48) {
            this.Y = e0;
        } else if (i2 == 80) {
            this.Y = h0;
        } else if (i2 == 8388611) {
            this.Y = d0;
        } else {
            if (i2 != 8388613) {
                throw new IllegalArgumentException("Invalid slide direction");
            }
            this.Y = g0;
        }
        this.Z = i2;
        SidePropagation sidePropagation = new SidePropagation();
        sidePropagation.j(i2);
        m0(sidePropagation);
    }
}
