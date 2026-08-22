package com.google.android.material.transformation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Pair;
import android.util.Property;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.animation.AnimatorSetCompat;
import com.google.android.material.animation.ArgbEvaluatorCompat;
import com.google.android.material.animation.ChildrenAlphaProperty;
import com.google.android.material.animation.DrawableAlphaProperty;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.animation.MotionTiming;
import com.google.android.material.animation.Positioning;
import com.google.android.material.circularreveal.CircularRevealCompat;
import com.google.android.material.circularreveal.CircularRevealHelper;
import com.google.android.material.circularreveal.CircularRevealWidget;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.math.MathUtils;
import java.util.ArrayList;
import java.util.List;

@Deprecated
/* loaded from: classes.dex */
public abstract class FabTransformationBehavior extends ExpandableTransformationBehavior {

    /* renamed from: i, reason: collision with root package name */
    private final Rect f15530i;

    /* renamed from: j, reason: collision with root package name */
    private final RectF f15531j;

    /* renamed from: k, reason: collision with root package name */
    private final RectF f15532k;

    /* renamed from: l, reason: collision with root package name */
    private final int[] f15533l;

    /* renamed from: m, reason: collision with root package name */
    private float f15534m;

    /* renamed from: n, reason: collision with root package name */
    private float f15535n;

    protected static class FabTransformationSpec {

        /* renamed from: a, reason: collision with root package name */
        public MotionSpec f15547a;

        /* renamed from: b, reason: collision with root package name */
        public Positioning f15548b;

        protected FabTransformationSpec() {
        }
    }

    public FabTransformationBehavior() {
        this.f15530i = new Rect();
        this.f15531j = new RectF();
        this.f15532k = new RectF();
        this.f15533l = new int[2];
    }

    private ViewGroup P(View view) {
        View findViewById = view.findViewById(R.id.mtrl_child_content_container);
        return findViewById != null ? k0(findViewById) : ((view instanceof TransformationChildLayout) || (view instanceof TransformationChildCard)) ? k0(((ViewGroup) view).getChildAt(0)) : k0(view);
    }

    private void Q(View view, FabTransformationSpec fabTransformationSpec, MotionTiming motionTiming, MotionTiming motionTiming2, float f2, float f3, float f4, float f5, RectF rectF) {
        float X = X(fabTransformationSpec, motionTiming, f2, f4);
        float X2 = X(fabTransformationSpec, motionTiming2, f3, f5);
        Rect rect = this.f15530i;
        view.getWindowVisibleDisplayFrame(rect);
        RectF rectF2 = this.f15531j;
        rectF2.set(rect);
        RectF rectF3 = this.f15532k;
        Y(view, rectF3);
        rectF3.offset(X, X2);
        rectF3.intersect(rectF2);
        rectF.set(rectF3);
    }

    private void R(View view, RectF rectF) {
        Y(view, rectF);
        rectF.offset(this.f15534m, this.f15535n);
    }

    private Pair S(float f2, float f3, boolean z, FabTransformationSpec fabTransformationSpec) {
        MotionTiming h2;
        MotionTiming h3;
        if (f2 == 0.0f || f3 == 0.0f) {
            h2 = fabTransformationSpec.f15547a.h("translationXLinear");
            h3 = fabTransformationSpec.f15547a.h("translationYLinear");
        } else if ((!z || f3 >= 0.0f) && (z || f3 <= 0.0f)) {
            h2 = fabTransformationSpec.f15547a.h("translationXCurveDownwards");
            h3 = fabTransformationSpec.f15547a.h("translationYCurveDownwards");
        } else {
            h2 = fabTransformationSpec.f15547a.h("translationXCurveUpwards");
            h3 = fabTransformationSpec.f15547a.h("translationYCurveUpwards");
        }
        return new Pair(h2, h3);
    }

    private float T(View view, View view2, Positioning positioning) {
        RectF rectF = this.f15531j;
        RectF rectF2 = this.f15532k;
        R(view, rectF);
        Y(view2, rectF2);
        rectF2.offset(-V(view, view2, positioning), 0.0f);
        return rectF.centerX() - rectF2.left;
    }

    private float U(View view, View view2, Positioning positioning) {
        RectF rectF = this.f15531j;
        RectF rectF2 = this.f15532k;
        R(view, rectF);
        Y(view2, rectF2);
        rectF2.offset(0.0f, -W(view, view2, positioning));
        return rectF.centerY() - rectF2.top;
    }

    private float V(View view, View view2, Positioning positioning) {
        float centerX;
        float centerX2;
        float f2;
        RectF rectF = this.f15531j;
        RectF rectF2 = this.f15532k;
        R(view, rectF);
        Y(view2, rectF2);
        int i2 = positioning.f13834a & 7;
        if (i2 == 1) {
            centerX = rectF2.centerX();
            centerX2 = rectF.centerX();
        } else if (i2 == 3) {
            centerX = rectF2.left;
            centerX2 = rectF.left;
        } else {
            if (i2 != 5) {
                f2 = 0.0f;
                return f2 + positioning.f13835b;
            }
            centerX = rectF2.right;
            centerX2 = rectF.right;
        }
        f2 = centerX - centerX2;
        return f2 + positioning.f13835b;
    }

    private float W(View view, View view2, Positioning positioning) {
        float centerY;
        float centerY2;
        float f2;
        RectF rectF = this.f15531j;
        RectF rectF2 = this.f15532k;
        R(view, rectF);
        Y(view2, rectF2);
        int i2 = positioning.f13834a & 112;
        if (i2 == 16) {
            centerY = rectF2.centerY();
            centerY2 = rectF.centerY();
        } else if (i2 == 48) {
            centerY = rectF2.top;
            centerY2 = rectF.top;
        } else {
            if (i2 != 80) {
                f2 = 0.0f;
                return f2 + positioning.f13836c;
            }
            centerY = rectF2.bottom;
            centerY2 = rectF.bottom;
        }
        f2 = centerY - centerY2;
        return f2 + positioning.f13836c;
    }

    private float X(FabTransformationSpec fabTransformationSpec, MotionTiming motionTiming, float f2, float f3) {
        long c2 = motionTiming.c();
        long d2 = motionTiming.d();
        MotionTiming h2 = fabTransformationSpec.f15547a.h("expansion");
        return AnimationUtils.a(f2, f3, motionTiming.e().getInterpolation((((h2.c() + h2.d()) + 17) - c2) / d2));
    }

    private void Y(View view, RectF rectF) {
        rectF.set(0.0f, 0.0f, view.getWidth(), view.getHeight());
        view.getLocationInWindow(this.f15533l);
        rectF.offsetTo(r3[0], r3[1]);
        rectF.offset((int) (-view.getTranslationX()), (int) (-view.getTranslationY()));
    }

    private void Z(View view, View view2, boolean z, boolean z2, FabTransformationSpec fabTransformationSpec, List list, List list2) {
        ViewGroup P;
        ObjectAnimator ofFloat;
        if (view2 instanceof ViewGroup) {
            if (((view2 instanceof CircularRevealWidget) && CircularRevealHelper.f14202j == 0) || (P = P(view2)) == null) {
                return;
            }
            if (z) {
                if (!z2) {
                    ChildrenAlphaProperty.f13820a.set(P, Float.valueOf(0.0f));
                }
                ofFloat = ObjectAnimator.ofFloat(P, (Property<ViewGroup, Float>) ChildrenAlphaProperty.f13820a, 1.0f);
            } else {
                ofFloat = ObjectAnimator.ofFloat(P, (Property<ViewGroup, Float>) ChildrenAlphaProperty.f13820a, 0.0f);
            }
            fabTransformationSpec.f15547a.h("contentFade").a(ofFloat);
            list.add(ofFloat);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void a0(View view, View view2, boolean z, boolean z2, FabTransformationSpec fabTransformationSpec, List list, List list2) {
        ObjectAnimator ofInt;
        if (view2 instanceof CircularRevealWidget) {
            CircularRevealWidget circularRevealWidget = (CircularRevealWidget) view2;
            int i0 = i0(view);
            int i2 = 16777215 & i0;
            if (z) {
                if (!z2) {
                    circularRevealWidget.setCircularRevealScrimColor(i0);
                }
                ofInt = ObjectAnimator.ofInt(circularRevealWidget, (Property<CircularRevealWidget, Integer>) CircularRevealWidget.CircularRevealScrimColorProperty.f14215a, i2);
            } else {
                ofInt = ObjectAnimator.ofInt(circularRevealWidget, (Property<CircularRevealWidget, Integer>) CircularRevealWidget.CircularRevealScrimColorProperty.f14215a, i0);
            }
            ofInt.setEvaluator(ArgbEvaluatorCompat.b());
            fabTransformationSpec.f15547a.h("color").a(ofInt);
            list.add(ofInt);
        }
    }

    private void b0(View view, View view2, boolean z, FabTransformationSpec fabTransformationSpec, List list) {
        float V = V(view, view2, fabTransformationSpec.f15548b);
        float W = W(view, view2, fabTransformationSpec.f15548b);
        Pair S = S(V, W, z, fabTransformationSpec);
        MotionTiming motionTiming = (MotionTiming) S.first;
        MotionTiming motionTiming2 = (MotionTiming) S.second;
        Property property = View.TRANSLATION_X;
        if (!z) {
            V = this.f15534m;
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, (Property<View, Float>) property, V);
        Property property2 = View.TRANSLATION_Y;
        if (!z) {
            W = this.f15535n;
        }
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, (Property<View, Float>) property2, W);
        motionTiming.a(ofFloat);
        motionTiming2.a(ofFloat2);
        list.add(ofFloat);
        list.add(ofFloat2);
    }

    private void c0(View view, View view2, boolean z, boolean z2, FabTransformationSpec fabTransformationSpec, List list, List list2) {
        ObjectAnimator ofFloat;
        float r2 = ViewCompat.r(view2) - ViewCompat.r(view);
        if (z) {
            if (!z2) {
                view2.setTranslationZ(-r2);
            }
            ofFloat = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.TRANSLATION_Z, 0.0f);
        } else {
            ofFloat = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.TRANSLATION_Z, -r2);
        }
        fabTransformationSpec.f15547a.h("elevation").a(ofFloat);
        list.add(ofFloat);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void d0(View view, View view2, boolean z, boolean z2, FabTransformationSpec fabTransformationSpec, float f2, float f3, List list, List list2) {
        Animator animator;
        if (view2 instanceof CircularRevealWidget) {
            final CircularRevealWidget circularRevealWidget = (CircularRevealWidget) view2;
            float T = T(view, view2, fabTransformationSpec.f15548b);
            float U = U(view, view2, fabTransformationSpec.f15548b);
            ((FloatingActionButton) view).i(this.f15530i);
            float width = this.f15530i.width() / 2.0f;
            MotionTiming h2 = fabTransformationSpec.f15547a.h("expansion");
            if (z) {
                if (!z2) {
                    circularRevealWidget.setRevealInfo(new CircularRevealWidget.RevealInfo(T, U, width));
                }
                if (z2) {
                    width = circularRevealWidget.getRevealInfo().f14218c;
                }
                animator = CircularRevealCompat.a(circularRevealWidget, T, U, MathUtils.b(T, U, 0.0f, 0.0f, f2, f3));
                animator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.transformation.FabTransformationBehavior.4
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationEnd(Animator animator2) {
                        CircularRevealWidget.RevealInfo revealInfo = circularRevealWidget.getRevealInfo();
                        revealInfo.f14218c = Float.MAX_VALUE;
                        circularRevealWidget.setRevealInfo(revealInfo);
                    }
                });
                g0(view2, h2.c(), (int) T, (int) U, width, list);
            } else {
                float f4 = circularRevealWidget.getRevealInfo().f14218c;
                Animator a2 = CircularRevealCompat.a(circularRevealWidget, T, U, width);
                int i2 = (int) T;
                int i3 = (int) U;
                g0(view2, h2.c(), i2, i3, f4, list);
                f0(view2, h2.c(), h2.d(), fabTransformationSpec.f15547a.i(), i2, i3, width, list);
                animator = a2;
            }
            h2.a(animator);
            list.add(animator);
            list2.add(CircularRevealCompat.b(circularRevealWidget));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void e0(View view, final View view2, boolean z, boolean z2, FabTransformationSpec fabTransformationSpec, List list, List list2) {
        ObjectAnimator ofInt;
        if ((view2 instanceof CircularRevealWidget) && (view instanceof ImageView)) {
            final CircularRevealWidget circularRevealWidget = (CircularRevealWidget) view2;
            final Drawable drawable = ((ImageView) view).getDrawable();
            if (drawable == null) {
                return;
            }
            drawable.mutate();
            if (z) {
                if (!z2) {
                    drawable.setAlpha(255);
                }
                ofInt = ObjectAnimator.ofInt(drawable, (Property<Drawable, Integer>) DrawableAlphaProperty.f13821b, 0);
            } else {
                ofInt = ObjectAnimator.ofInt(drawable, (Property<Drawable, Integer>) DrawableAlphaProperty.f13821b, 255);
            }
            ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.transformation.FabTransformationBehavior.2
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    view2.invalidate();
                }
            });
            fabTransformationSpec.f15547a.h("iconFade").a(ofInt);
            list.add(ofInt);
            list2.add(new AnimatorListenerAdapter() { // from class: com.google.android.material.transformation.FabTransformationBehavior.3
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    circularRevealWidget.setCircularRevealOverlayDrawable(null);
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                    circularRevealWidget.setCircularRevealOverlayDrawable(drawable);
                }
            });
        }
    }

    private void f0(View view, long j2, long j3, long j4, int i2, int i3, float f2, List list) {
        long j5 = j2 + j3;
        if (j5 < j4) {
            Animator createCircularReveal = ViewAnimationUtils.createCircularReveal(view, i2, i3, f2, f2);
            createCircularReveal.setStartDelay(j5);
            createCircularReveal.setDuration(j4 - j5);
            list.add(createCircularReveal);
        }
    }

    private void g0(View view, long j2, int i2, int i3, float f2, List list) {
        if (j2 > 0) {
            Animator createCircularReveal = ViewAnimationUtils.createCircularReveal(view, i2, i3, f2, f2);
            createCircularReveal.setStartDelay(0L);
            createCircularReveal.setDuration(j2);
            list.add(createCircularReveal);
        }
    }

    private void h0(View view, View view2, boolean z, boolean z2, FabTransformationSpec fabTransformationSpec, List list, List list2, RectF rectF) {
        ObjectAnimator ofFloat;
        ObjectAnimator ofFloat2;
        float V = V(view, view2, fabTransformationSpec.f15548b);
        float W = W(view, view2, fabTransformationSpec.f15548b);
        Pair S = S(V, W, z, fabTransformationSpec);
        MotionTiming motionTiming = (MotionTiming) S.first;
        MotionTiming motionTiming2 = (MotionTiming) S.second;
        if (z) {
            if (!z2) {
                view2.setTranslationX(-V);
                view2.setTranslationY(-W);
            }
            ofFloat = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.TRANSLATION_X, 0.0f);
            ofFloat2 = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.TRANSLATION_Y, 0.0f);
            Q(view2, fabTransformationSpec, motionTiming, motionTiming2, -V, -W, 0.0f, 0.0f, rectF);
        } else {
            ofFloat = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.TRANSLATION_X, -V);
            ofFloat2 = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.TRANSLATION_Y, -W);
        }
        motionTiming.a(ofFloat);
        motionTiming2.a(ofFloat2);
        list.add(ofFloat);
        list.add(ofFloat2);
    }

    private int i0(View view) {
        ColorStateList o2 = ViewCompat.o(view);
        if (o2 != null) {
            return o2.getColorForState(view.getDrawableState(), o2.getDefaultColor());
        }
        return 0;
    }

    private ViewGroup k0(View view) {
        if (view instanceof ViewGroup) {
            return (ViewGroup) view;
        }
        return null;
    }

    @Override // com.google.android.material.transformation.ExpandableTransformationBehavior
    protected AnimatorSet O(final View view, final View view2, final boolean z, boolean z2) {
        FabTransformationSpec j0 = j0(view2.getContext(), z);
        if (z) {
            this.f15534m = view.getTranslationX();
            this.f15535n = view.getTranslationY();
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        c0(view, view2, z, z2, j0, arrayList, arrayList2);
        RectF rectF = this.f15531j;
        h0(view, view2, z, z2, j0, arrayList, arrayList2, rectF);
        float width = rectF.width();
        float height = rectF.height();
        b0(view, view2, z, j0, arrayList);
        e0(view, view2, z, z2, j0, arrayList, arrayList2);
        d0(view, view2, z, z2, j0, width, height, arrayList, arrayList2);
        a0(view, view2, z, z2, j0, arrayList, arrayList2);
        Z(view, view2, z, z2, j0, arrayList, arrayList2);
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSetCompat.a(animatorSet, arrayList);
        animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.transformation.FabTransformationBehavior.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (z) {
                    return;
                }
                view2.setVisibility(4);
                view.setAlpha(1.0f);
                view.setVisibility(0);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                if (z) {
                    view2.setVisibility(0);
                    view.setAlpha(0.0f);
                    view.setVisibility(4);
                }
            }
        });
        int size = arrayList2.size();
        for (int i2 = 0; i2 < size; i2++) {
            animatorSet.addListener((Animator.AnimatorListener) arrayList2.get(i2));
        }
        return animatorSet;
    }

    @Override // com.google.android.material.transformation.ExpandableBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean j(CoordinatorLayout coordinatorLayout, View view, View view2) {
        if (view.getVisibility() == 8) {
            throw new IllegalStateException("This behavior cannot be attached to a GONE view. Set the view to INVISIBLE instead.");
        }
        if (!(view2 instanceof FloatingActionButton)) {
            return false;
        }
        int expandedComponentIdHint = ((FloatingActionButton) view2).getExpandedComponentIdHint();
        return expandedComponentIdHint == 0 || expandedComponentIdHint == view.getId();
    }

    protected abstract FabTransformationSpec j0(Context context, boolean z);

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void l(CoordinatorLayout.LayoutParams layoutParams) {
        if (layoutParams.f2587h == 0) {
            layoutParams.f2587h = 80;
        }
    }

    public FabTransformationBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f15530i = new Rect();
        this.f15531j = new RectF();
        this.f15532k = new RectF();
        this.f15533l = new int[2];
    }
}
