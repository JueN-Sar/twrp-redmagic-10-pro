package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.util.Property;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.util.Preconditions;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.animation.AnimatorSetCompat;
import com.google.android.material.animation.ImageMatrixProperty;
import com.google.android.material.animation.MatrixEvaluator;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.internal.StateListAnimator;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.ripple.RippleDrawableCompat;
import com.google.android.material.ripple.RippleUtils;
import com.google.android.material.shadow.ShadowViewDelegate;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
class FloatingActionButtonImpl {
    static final TimeInterpolator D = AnimationUtils.f13816c;
    private static final int E = R.attr.motionDurationLong2;
    private static final int F = R.attr.motionEasingEmphasizedInterpolator;
    private static final int G = R.attr.motionDurationMedium1;
    private static final int H = R.attr.motionEasingEmphasizedAccelerateInterpolator;
    static final int[] I = {android.R.attr.state_pressed, android.R.attr.state_enabled};
    static final int[] J = {android.R.attr.state_hovered, android.R.attr.state_focused, android.R.attr.state_enabled};
    static final int[] K = {android.R.attr.state_focused, android.R.attr.state_enabled};
    static final int[] L = {android.R.attr.state_hovered, android.R.attr.state_enabled};
    static final int[] M = {android.R.attr.state_enabled};
    static final int[] N = new int[0];
    private ViewTreeObserver.OnPreDrawListener C;

    /* renamed from: a, reason: collision with root package name */
    ShapeAppearanceModel f14636a;

    /* renamed from: b, reason: collision with root package name */
    MaterialShapeDrawable f14637b;

    /* renamed from: c, reason: collision with root package name */
    Drawable f14638c;

    /* renamed from: d, reason: collision with root package name */
    BorderDrawable f14639d;

    /* renamed from: e, reason: collision with root package name */
    Drawable f14640e;

    /* renamed from: f, reason: collision with root package name */
    boolean f14641f;

    /* renamed from: h, reason: collision with root package name */
    float f14643h;

    /* renamed from: i, reason: collision with root package name */
    float f14644i;

    /* renamed from: j, reason: collision with root package name */
    float f14645j;

    /* renamed from: k, reason: collision with root package name */
    int f14646k;

    /* renamed from: l, reason: collision with root package name */
    private final StateListAnimator f14647l;

    /* renamed from: m, reason: collision with root package name */
    private Animator f14648m;

    /* renamed from: n, reason: collision with root package name */
    private MotionSpec f14649n;

    /* renamed from: o, reason: collision with root package name */
    private MotionSpec f14650o;

    /* renamed from: p, reason: collision with root package name */
    private float f14651p;

    /* renamed from: r, reason: collision with root package name */
    private int f14653r;
    private ArrayList t;
    private ArrayList u;
    private ArrayList v;
    final FloatingActionButton w;
    final ShadowViewDelegate x;

    /* renamed from: g, reason: collision with root package name */
    boolean f14642g = true;

    /* renamed from: q, reason: collision with root package name */
    private float f14652q = 1.0f;

    /* renamed from: s, reason: collision with root package name */
    private int f14654s = 0;
    private final Rect y = new Rect();
    private final RectF z = new RectF();
    private final RectF A = new RectF();
    private final Matrix B = new Matrix();

    /* renamed from: com.google.android.material.floatingactionbutton.FloatingActionButtonImpl$5, reason: invalid class name */
    class AnonymousClass5 implements TypeEvaluator<Float> {

        /* renamed from: a, reason: collision with root package name */
        FloatEvaluator f14672a;

        @Override // android.animation.TypeEvaluator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float evaluate(float f2, Float f3, Float f4) {
            float floatValue = this.f14672a.evaluate(f2, (Number) f3, (Number) f4).floatValue();
            if (floatValue < 0.1f) {
                floatValue = 0.0f;
            }
            return Float.valueOf(floatValue);
        }
    }

    private class DisabledElevationAnimation extends ShadowAnimatorImpl {
        DisabledElevationAnimation() {
            super();
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.ShadowAnimatorImpl
        protected float a() {
            return 0.0f;
        }
    }

    private class ElevateToHoveredFocusedTranslationZAnimation extends ShadowAnimatorImpl {
        ElevateToHoveredFocusedTranslationZAnimation() {
            super();
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.ShadowAnimatorImpl
        protected float a() {
            FloatingActionButtonImpl floatingActionButtonImpl = FloatingActionButtonImpl.this;
            return floatingActionButtonImpl.f14643h + floatingActionButtonImpl.f14644i;
        }
    }

    private class ElevateToPressedTranslationZAnimation extends ShadowAnimatorImpl {
        ElevateToPressedTranslationZAnimation() {
            super();
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.ShadowAnimatorImpl
        protected float a() {
            FloatingActionButtonImpl floatingActionButtonImpl = FloatingActionButtonImpl.this;
            return floatingActionButtonImpl.f14643h + floatingActionButtonImpl.f14645j;
        }
    }

    interface InternalTransformationCallback {
        void a();

        void b();
    }

    interface InternalVisibilityChangedListener {
        void a();

        void b();
    }

    private class ResetElevationAnimation extends ShadowAnimatorImpl {
        ResetElevationAnimation() {
            super();
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.ShadowAnimatorImpl
        protected float a() {
            return FloatingActionButtonImpl.this.f14643h;
        }
    }

    private abstract class ShadowAnimatorImpl extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: c, reason: collision with root package name */
        private boolean f14678c;

        /* renamed from: h, reason: collision with root package name */
        private float f14679h;

        /* renamed from: i, reason: collision with root package name */
        private float f14680i;

        private ShadowAnimatorImpl() {
        }

        protected abstract float a();

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            FloatingActionButtonImpl.this.h0((int) this.f14680i);
            this.f14678c = false;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (!this.f14678c) {
                MaterialShapeDrawable materialShapeDrawable = FloatingActionButtonImpl.this.f14637b;
                this.f14679h = materialShapeDrawable == null ? 0.0f : materialShapeDrawable.w();
                this.f14680i = a();
                this.f14678c = true;
            }
            FloatingActionButtonImpl floatingActionButtonImpl = FloatingActionButtonImpl.this;
            float f2 = this.f14679h;
            floatingActionButtonImpl.h0((int) (f2 + ((this.f14680i - f2) * valueAnimator.getAnimatedFraction())));
        }
    }

    FloatingActionButtonImpl(FloatingActionButton floatingActionButton, ShadowViewDelegate shadowViewDelegate) {
        this.w = floatingActionButton;
        this.x = shadowViewDelegate;
        StateListAnimator stateListAnimator = new StateListAnimator();
        this.f14647l = stateListAnimator;
        stateListAnimator.a(I, k(new ElevateToPressedTranslationZAnimation()));
        stateListAnimator.a(J, k(new ElevateToHoveredFocusedTranslationZAnimation()));
        stateListAnimator.a(K, k(new ElevateToHoveredFocusedTranslationZAnimation()));
        stateListAnimator.a(L, k(new ElevateToHoveredFocusedTranslationZAnimation()));
        stateListAnimator.a(M, k(new ResetElevationAnimation()));
        stateListAnimator.a(N, k(new DisabledElevationAnimation()));
        this.f14651p = floatingActionButton.getRotation();
    }

    private boolean b0() {
        return ViewCompat.N(this.w) && !this.w.isInEditMode();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h(float f2, Matrix matrix) {
        matrix.reset();
        if (this.w.getDrawable() == null || this.f14653r == 0) {
            return;
        }
        RectF rectF = this.z;
        RectF rectF2 = this.A;
        rectF.set(0.0f, 0.0f, r0.getIntrinsicWidth(), r0.getIntrinsicHeight());
        int i2 = this.f14653r;
        rectF2.set(0.0f, 0.0f, i2, i2);
        matrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.CENTER);
        int i3 = this.f14653r;
        matrix.postScale(f2, f2, i3 / 2.0f, i3 / 2.0f);
    }

    private AnimatorSet i(MotionSpec motionSpec, float f2, float f3, float f4) {
        ArrayList arrayList = new ArrayList();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.w, (Property<FloatingActionButton, Float>) View.ALPHA, f2);
        motionSpec.h("opacity").a(ofFloat);
        arrayList.add(ofFloat);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.w, (Property<FloatingActionButton, Float>) View.SCALE_X, f3);
        motionSpec.h("scale").a(ofFloat2);
        i0(ofFloat2);
        arrayList.add(ofFloat2);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.w, (Property<FloatingActionButton, Float>) View.SCALE_Y, f3);
        motionSpec.h("scale").a(ofFloat3);
        i0(ofFloat3);
        arrayList.add(ofFloat3);
        h(f4, this.B);
        ObjectAnimator ofObject = ObjectAnimator.ofObject(this.w, new ImageMatrixProperty(), new MatrixEvaluator() { // from class: com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.3
            @Override // com.google.android.material.animation.MatrixEvaluator, android.animation.TypeEvaluator
            /* renamed from: a */
            public Matrix evaluate(float f5, Matrix matrix, Matrix matrix2) {
                FloatingActionButtonImpl.this.f14652q = f5;
                return super.evaluate(f5, matrix, matrix2);
            }
        }, new Matrix(this.B));
        motionSpec.h("iconScale").a(ofObject);
        arrayList.add(ofObject);
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSetCompat.a(animatorSet, arrayList);
        return animatorSet;
    }

    private void i0(ObjectAnimator objectAnimator) {
    }

    private AnimatorSet j(final float f2, final float f3, final float f4, int i2, int i3) {
        AnimatorSet animatorSet = new AnimatorSet();
        ArrayList arrayList = new ArrayList();
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        final float alpha = this.w.getAlpha();
        final float scaleX = this.w.getScaleX();
        final float scaleY = this.w.getScaleY();
        final float f5 = this.f14652q;
        final Matrix matrix = new Matrix(this.B);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.4
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                FloatingActionButtonImpl.this.w.setAlpha(AnimationUtils.b(alpha, f2, 0.0f, 0.2f, floatValue));
                FloatingActionButtonImpl.this.w.setScaleX(AnimationUtils.a(scaleX, f3, floatValue));
                FloatingActionButtonImpl.this.w.setScaleY(AnimationUtils.a(scaleY, f3, floatValue));
                FloatingActionButtonImpl.this.f14652q = AnimationUtils.a(f5, f4, floatValue);
                FloatingActionButtonImpl.this.h(AnimationUtils.a(f5, f4, floatValue), matrix);
                FloatingActionButtonImpl.this.w.setImageMatrix(matrix);
            }
        });
        arrayList.add(ofFloat);
        AnimatorSetCompat.a(animatorSet, arrayList);
        animatorSet.setDuration(MotionUtils.f(this.w.getContext(), i2, this.w.getContext().getResources().getInteger(R.integer.material_motion_duration_long_1)));
        animatorSet.setInterpolator(MotionUtils.g(this.w.getContext(), i3, AnimationUtils.f13815b));
        return animatorSet;
    }

    private ValueAnimator k(ShadowAnimatorImpl shadowAnimatorImpl) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setInterpolator(D);
        valueAnimator.setDuration(100L);
        valueAnimator.addListener(shadowAnimatorImpl);
        valueAnimator.addUpdateListener(shadowAnimatorImpl);
        valueAnimator.setFloatValues(0.0f, 1.0f);
        return valueAnimator;
    }

    private ViewTreeObserver.OnPreDrawListener r() {
        if (this.C == null) {
            this.C = new ViewTreeObserver.OnPreDrawListener() { // from class: com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.6
                @Override // android.view.ViewTreeObserver.OnPreDrawListener
                public boolean onPreDraw() {
                    FloatingActionButtonImpl.this.I();
                    return true;
                }
            };
        }
        return this.C;
    }

    boolean A() {
        return this.w.getVisibility() != 0 ? this.f14654s == 2 : this.f14654s != 1;
    }

    void B() {
        this.f14647l.c();
    }

    void C() {
        MaterialShapeDrawable materialShapeDrawable = this.f14637b;
        if (materialShapeDrawable != null) {
            MaterialShapeUtils.f(this.w, materialShapeDrawable);
        }
        if (L()) {
            this.w.getViewTreeObserver().addOnPreDrawListener(r());
        }
    }

    void D() {
    }

    void E() {
        ViewTreeObserver viewTreeObserver = this.w.getViewTreeObserver();
        ViewTreeObserver.OnPreDrawListener onPreDrawListener = this.C;
        if (onPreDrawListener != null) {
            viewTreeObserver.removeOnPreDrawListener(onPreDrawListener);
            this.C = null;
        }
    }

    void F(int[] iArr) {
        this.f14647l.d(iArr);
    }

    void G(float f2, float f3, float f4) {
        B();
        g0();
        h0(f2);
    }

    void H(Rect rect) {
        Preconditions.i(this.f14640e, "Didn't initialize content background");
        if (!a0()) {
            this.x.setBackgroundDrawable(this.f14640e);
        } else {
            this.x.setBackgroundDrawable(new InsetDrawable(this.f14640e, rect.left, rect.top, rect.right, rect.bottom));
        }
    }

    void I() {
        float rotation = this.w.getRotation();
        if (this.f14651p != rotation) {
            this.f14651p = rotation;
            e0();
        }
    }

    void J() {
        ArrayList arrayList = this.v;
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((InternalTransformationCallback) it.next()).b();
            }
        }
    }

    void K() {
        ArrayList arrayList = this.v;
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((InternalTransformationCallback) it.next()).a();
            }
        }
    }

    boolean L() {
        return true;
    }

    void M(ColorStateList colorStateList) {
        MaterialShapeDrawable materialShapeDrawable = this.f14637b;
        if (materialShapeDrawable != null) {
            materialShapeDrawable.setTintList(colorStateList);
        }
        BorderDrawable borderDrawable = this.f14639d;
        if (borderDrawable != null) {
            borderDrawable.c(colorStateList);
        }
    }

    void N(PorterDuff.Mode mode) {
        MaterialShapeDrawable materialShapeDrawable = this.f14637b;
        if (materialShapeDrawable != null) {
            materialShapeDrawable.setTintMode(mode);
        }
    }

    final void O(float f2) {
        if (this.f14643h != f2) {
            this.f14643h = f2;
            G(f2, this.f14644i, this.f14645j);
        }
    }

    void P(boolean z) {
        this.f14641f = z;
    }

    final void Q(MotionSpec motionSpec) {
        this.f14650o = motionSpec;
    }

    final void R(float f2) {
        if (this.f14644i != f2) {
            this.f14644i = f2;
            G(this.f14643h, f2, this.f14645j);
        }
    }

    final void S(float f2) {
        this.f14652q = f2;
        Matrix matrix = this.B;
        h(f2, matrix);
        this.w.setImageMatrix(matrix);
    }

    final void T(int i2) {
        if (this.f14653r != i2) {
            this.f14653r = i2;
            f0();
        }
    }

    void U(int i2) {
        this.f14646k = i2;
    }

    final void V(float f2) {
        if (this.f14645j != f2) {
            this.f14645j = f2;
            G(this.f14643h, this.f14644i, f2);
        }
    }

    void W(ColorStateList colorStateList) {
        Drawable drawable = this.f14638c;
        if (drawable != null) {
            DrawableCompat.o(drawable, RippleUtils.d(colorStateList));
        }
    }

    void X(boolean z) {
        this.f14642g = z;
        g0();
    }

    final void Y(ShapeAppearanceModel shapeAppearanceModel) {
        this.f14636a = shapeAppearanceModel;
        MaterialShapeDrawable materialShapeDrawable = this.f14637b;
        if (materialShapeDrawable != null) {
            materialShapeDrawable.setShapeAppearanceModel(shapeAppearanceModel);
        }
        Object obj = this.f14638c;
        if (obj instanceof Shapeable) {
            ((Shapeable) obj).setShapeAppearanceModel(shapeAppearanceModel);
        }
        BorderDrawable borderDrawable = this.f14639d;
        if (borderDrawable != null) {
            borderDrawable.f(shapeAppearanceModel);
        }
    }

    final void Z(MotionSpec motionSpec) {
        this.f14649n = motionSpec;
    }

    boolean a0() {
        return true;
    }

    final boolean c0() {
        return !this.f14641f || this.w.getSizeDimension() >= this.f14646k;
    }

    void d0(final InternalVisibilityChangedListener internalVisibilityChangedListener, final boolean z) {
        if (A()) {
            return;
        }
        Animator animator = this.f14648m;
        if (animator != null) {
            animator.cancel();
        }
        boolean z2 = this.f14649n == null;
        if (!b0()) {
            this.w.b(0, z);
            this.w.setAlpha(1.0f);
            this.w.setScaleY(1.0f);
            this.w.setScaleX(1.0f);
            S(1.0f);
            if (internalVisibilityChangedListener != null) {
                internalVisibilityChangedListener.a();
                return;
            }
            return;
        }
        if (this.w.getVisibility() != 0) {
            this.w.setAlpha(0.0f);
            this.w.setScaleY(z2 ? 0.4f : 0.0f);
            this.w.setScaleX(z2 ? 0.4f : 0.0f);
            S(z2 ? 0.4f : 0.0f);
        }
        MotionSpec motionSpec = this.f14649n;
        AnimatorSet i2 = motionSpec != null ? i(motionSpec, 1.0f, 1.0f, 1.0f) : j(1.0f, 1.0f, 1.0f, E, F);
        i2.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator2) {
                FloatingActionButtonImpl.this.f14654s = 0;
                FloatingActionButtonImpl.this.f14648m = null;
                InternalVisibilityChangedListener internalVisibilityChangedListener2 = internalVisibilityChangedListener;
                if (internalVisibilityChangedListener2 != null) {
                    internalVisibilityChangedListener2.a();
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator2) {
                FloatingActionButtonImpl.this.w.b(0, z);
                FloatingActionButtonImpl.this.f14654s = 2;
                FloatingActionButtonImpl.this.f14648m = animator2;
            }
        });
        ArrayList arrayList = this.t;
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                i2.addListener((Animator.AnimatorListener) it.next());
            }
        }
        i2.start();
    }

    public void e(Animator.AnimatorListener animatorListener) {
        if (this.u == null) {
            this.u = new ArrayList();
        }
        this.u.add(animatorListener);
    }

    void e0() {
        MaterialShapeDrawable materialShapeDrawable = this.f14637b;
        if (materialShapeDrawable != null) {
            materialShapeDrawable.h0((int) this.f14651p);
        }
    }

    void f(Animator.AnimatorListener animatorListener) {
        if (this.t == null) {
            this.t = new ArrayList();
        }
        this.t.add(animatorListener);
    }

    final void f0() {
        S(this.f14652q);
    }

    void g(InternalTransformationCallback internalTransformationCallback) {
        if (this.v == null) {
            this.v = new ArrayList();
        }
        this.v.add(internalTransformationCallback);
    }

    final void g0() {
        Rect rect = this.y;
        s(rect);
        H(rect);
        this.x.a(rect.left, rect.top, rect.right, rect.bottom);
    }

    void h0(float f2) {
        MaterialShapeDrawable materialShapeDrawable = this.f14637b;
        if (materialShapeDrawable != null) {
            materialShapeDrawable.Z(f2);
        }
    }

    MaterialShapeDrawable l() {
        return new MaterialShapeDrawable((ShapeAppearanceModel) Preconditions.h(this.f14636a));
    }

    final Drawable m() {
        return this.f14640e;
    }

    float n() {
        return this.f14643h;
    }

    boolean o() {
        return this.f14641f;
    }

    final MotionSpec p() {
        return this.f14650o;
    }

    float q() {
        return this.f14644i;
    }

    void s(Rect rect) {
        int w = w();
        int max = Math.max(w, (int) Math.ceil(this.f14642g ? n() + this.f14645j : 0.0f));
        int max2 = Math.max(w, (int) Math.ceil(r1 * 1.5f));
        rect.set(max, max2, max, max2);
    }

    float t() {
        return this.f14645j;
    }

    final ShapeAppearanceModel u() {
        return this.f14636a;
    }

    final MotionSpec v() {
        return this.f14649n;
    }

    int w() {
        if (this.f14641f) {
            return Math.max((this.f14646k - this.w.getSizeDimension()) / 2, 0);
        }
        return 0;
    }

    void x(final InternalVisibilityChangedListener internalVisibilityChangedListener, final boolean z) {
        if (z()) {
            return;
        }
        Animator animator = this.f14648m;
        if (animator != null) {
            animator.cancel();
        }
        if (!b0()) {
            this.w.b(z ? 8 : 4, z);
            if (internalVisibilityChangedListener != null) {
                internalVisibilityChangedListener.b();
                return;
            }
            return;
        }
        MotionSpec motionSpec = this.f14650o;
        AnimatorSet i2 = motionSpec != null ? i(motionSpec, 0.0f, 0.0f, 0.0f) : j(0.0f, 0.4f, 0.4f, G, H);
        i2.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.1

            /* renamed from: c, reason: collision with root package name */
            private boolean f14655c;

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator2) {
                this.f14655c = true;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator2) {
                FloatingActionButtonImpl.this.f14654s = 0;
                FloatingActionButtonImpl.this.f14648m = null;
                if (this.f14655c) {
                    return;
                }
                FloatingActionButton floatingActionButton = FloatingActionButtonImpl.this.w;
                boolean z2 = z;
                floatingActionButton.b(z2 ? 8 : 4, z2);
                InternalVisibilityChangedListener internalVisibilityChangedListener2 = internalVisibilityChangedListener;
                if (internalVisibilityChangedListener2 != null) {
                    internalVisibilityChangedListener2.b();
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator2) {
                FloatingActionButtonImpl.this.w.b(0, z);
                FloatingActionButtonImpl.this.f14654s = 1;
                FloatingActionButtonImpl.this.f14648m = animator2;
                this.f14655c = false;
            }
        });
        ArrayList arrayList = this.u;
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                i2.addListener((Animator.AnimatorListener) it.next());
            }
        }
        i2.start();
    }

    void y(ColorStateList colorStateList, PorterDuff.Mode mode, ColorStateList colorStateList2, int i2) {
        MaterialShapeDrawable l2 = l();
        this.f14637b = l2;
        l2.setTintList(colorStateList);
        if (mode != null) {
            this.f14637b.setTintMode(mode);
        }
        this.f14637b.g0(-12303292);
        this.f14637b.P(this.w.getContext());
        RippleDrawableCompat rippleDrawableCompat = new RippleDrawableCompat(this.f14637b.getShapeAppearanceModel());
        rippleDrawableCompat.setTintList(RippleUtils.d(colorStateList2));
        this.f14638c = rippleDrawableCompat;
        this.f14640e = new LayerDrawable(new Drawable[]{(Drawable) Preconditions.h(this.f14637b), rippleDrawableCompat});
    }

    boolean z() {
        return this.w.getVisibility() == 0 ? this.f14654s == 1 : this.f14654s != 2;
    }
}
