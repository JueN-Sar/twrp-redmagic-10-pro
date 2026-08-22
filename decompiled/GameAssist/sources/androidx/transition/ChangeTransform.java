package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.core.content.res.TypedArrayUtils;
import androidx.core.view.ViewCompat;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes.dex */
public class ChangeTransform extends Transition {
    private static final String[] Z = {"android:changeTransform:matrix", "android:changeTransform:transforms", "android:changeTransform:parentMatrix"};
    private static final Property a0 = new Property<PathAnimatorMatrix, float[]>(float[].class, "nonTranslations") { // from class: androidx.transition.ChangeTransform.1
        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public float[] get(PathAnimatorMatrix pathAnimatorMatrix) {
            return null;
        }

        @Override // android.util.Property
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(PathAnimatorMatrix pathAnimatorMatrix, float[] fArr) {
            pathAnimatorMatrix.d(fArr);
        }
    };
    private static final Property b0 = new Property<PathAnimatorMatrix, PointF>(PointF.class, "translations") { // from class: androidx.transition.ChangeTransform.2
        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public PointF get(PathAnimatorMatrix pathAnimatorMatrix) {
            return null;
        }

        @Override // android.util.Property
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(PathAnimatorMatrix pathAnimatorMatrix, PointF pointF) {
            pathAnimatorMatrix.c(pointF);
        }
    };
    private static final boolean c0 = true;
    boolean W;
    private boolean X;
    private Matrix Y;

    private static class GhostListener extends TransitionListenerAdapter {

        /* renamed from: c, reason: collision with root package name */
        private View f5448c;

        /* renamed from: h, reason: collision with root package name */
        private GhostView f5449h;

        GhostListener(View view, GhostView ghostView) {
            this.f5448c = view;
            this.f5449h = ghostView;
        }

        @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
        public void b(Transition transition) {
            this.f5449h.setVisibility(0);
        }

        @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
        public void d(Transition transition) {
            this.f5449h.setVisibility(4);
        }

        @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
        public void f(Transition transition) {
            transition.b0(this);
            GhostViewUtils.b(this.f5448c);
            this.f5448c.setTag(R.id.transition_transform, null);
            this.f5448c.setTag(R.id.parent_matrix, null);
        }
    }

    private static class Listener extends AnimatorListenerAdapter {

        /* renamed from: c, reason: collision with root package name */
        private boolean f5450c;

        /* renamed from: h, reason: collision with root package name */
        private final Matrix f5451h = new Matrix();

        /* renamed from: i, reason: collision with root package name */
        private final boolean f5452i;

        /* renamed from: j, reason: collision with root package name */
        private final boolean f5453j;

        /* renamed from: k, reason: collision with root package name */
        private final View f5454k;

        /* renamed from: l, reason: collision with root package name */
        private final Transforms f5455l;

        /* renamed from: m, reason: collision with root package name */
        private final PathAnimatorMatrix f5456m;

        /* renamed from: n, reason: collision with root package name */
        private final Matrix f5457n;

        Listener(View view, Transforms transforms, PathAnimatorMatrix pathAnimatorMatrix, Matrix matrix, boolean z, boolean z2) {
            this.f5452i = z;
            this.f5453j = z2;
            this.f5454k = view;
            this.f5455l = transforms;
            this.f5456m = pathAnimatorMatrix;
            this.f5457n = matrix;
        }

        private void a(Matrix matrix) {
            this.f5451h.set(matrix);
            this.f5454k.setTag(R.id.transition_transform, this.f5451h);
            this.f5455l.a(this.f5454k);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f5450c = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (!this.f5450c) {
                if (this.f5452i && this.f5453j) {
                    a(this.f5457n);
                } else {
                    this.f5454k.setTag(R.id.transition_transform, null);
                    this.f5454k.setTag(R.id.parent_matrix, null);
                }
            }
            ViewUtils.d(this.f5454k, null);
            this.f5455l.a(this.f5454k);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
        public void onAnimationPause(Animator animator) {
            a(this.f5456m.a());
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
        public void onAnimationResume(Animator animator) {
            ChangeTransform.u0(this.f5454k);
        }
    }

    private static class PathAnimatorMatrix {

        /* renamed from: a, reason: collision with root package name */
        private final Matrix f5458a = new Matrix();

        /* renamed from: b, reason: collision with root package name */
        private final View f5459b;

        /* renamed from: c, reason: collision with root package name */
        private final float[] f5460c;

        /* renamed from: d, reason: collision with root package name */
        private float f5461d;

        /* renamed from: e, reason: collision with root package name */
        private float f5462e;

        PathAnimatorMatrix(View view, float[] fArr) {
            this.f5459b = view;
            float[] fArr2 = (float[]) fArr.clone();
            this.f5460c = fArr2;
            this.f5461d = fArr2[2];
            this.f5462e = fArr2[5];
            b();
        }

        private void b() {
            float[] fArr = this.f5460c;
            fArr[2] = this.f5461d;
            fArr[5] = this.f5462e;
            this.f5458a.setValues(fArr);
            ViewUtils.d(this.f5459b, this.f5458a);
        }

        Matrix a() {
            return this.f5458a;
        }

        void c(PointF pointF) {
            this.f5461d = pointF.x;
            this.f5462e = pointF.y;
            b();
        }

        void d(float[] fArr) {
            System.arraycopy(fArr, 0, this.f5460c, 0, fArr.length);
            b();
        }
    }

    private static class Transforms {

        /* renamed from: a, reason: collision with root package name */
        final float f5463a;

        /* renamed from: b, reason: collision with root package name */
        final float f5464b;

        /* renamed from: c, reason: collision with root package name */
        final float f5465c;

        /* renamed from: d, reason: collision with root package name */
        final float f5466d;

        /* renamed from: e, reason: collision with root package name */
        final float f5467e;

        /* renamed from: f, reason: collision with root package name */
        final float f5468f;

        /* renamed from: g, reason: collision with root package name */
        final float f5469g;

        /* renamed from: h, reason: collision with root package name */
        final float f5470h;

        Transforms(View view) {
            this.f5463a = view.getTranslationX();
            this.f5464b = view.getTranslationY();
            this.f5465c = ViewCompat.E(view);
            this.f5466d = view.getScaleX();
            this.f5467e = view.getScaleY();
            this.f5468f = view.getRotationX();
            this.f5469g = view.getRotationY();
            this.f5470h = view.getRotation();
        }

        public void a(View view) {
            ChangeTransform.w0(view, this.f5463a, this.f5464b, this.f5465c, this.f5466d, this.f5467e, this.f5468f, this.f5469g, this.f5470h);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Transforms)) {
                return false;
            }
            Transforms transforms = (Transforms) obj;
            return transforms.f5463a == this.f5463a && transforms.f5464b == this.f5464b && transforms.f5465c == this.f5465c && transforms.f5466d == this.f5466d && transforms.f5467e == this.f5467e && transforms.f5468f == this.f5468f && transforms.f5469g == this.f5469g && transforms.f5470h == this.f5470h;
        }

        public int hashCode() {
            float f2 = this.f5463a;
            int floatToIntBits = (f2 != 0.0f ? Float.floatToIntBits(f2) : 0) * 31;
            float f3 = this.f5464b;
            int floatToIntBits2 = (floatToIntBits + (f3 != 0.0f ? Float.floatToIntBits(f3) : 0)) * 31;
            float f4 = this.f5465c;
            int floatToIntBits3 = (floatToIntBits2 + (f4 != 0.0f ? Float.floatToIntBits(f4) : 0)) * 31;
            float f5 = this.f5466d;
            int floatToIntBits4 = (floatToIntBits3 + (f5 != 0.0f ? Float.floatToIntBits(f5) : 0)) * 31;
            float f6 = this.f5467e;
            int floatToIntBits5 = (floatToIntBits4 + (f6 != 0.0f ? Float.floatToIntBits(f6) : 0)) * 31;
            float f7 = this.f5468f;
            int floatToIntBits6 = (floatToIntBits5 + (f7 != 0.0f ? Float.floatToIntBits(f7) : 0)) * 31;
            float f8 = this.f5469g;
            int floatToIntBits7 = (floatToIntBits6 + (f8 != 0.0f ? Float.floatToIntBits(f8) : 0)) * 31;
            float f9 = this.f5470h;
            return floatToIntBits7 + (f9 != 0.0f ? Float.floatToIntBits(f9) : 0);
        }
    }

    public ChangeTransform(@NonNull Context context, @NonNull AttributeSet attributeSet) {
        super(context, attributeSet);
        this.W = true;
        this.X = true;
        this.Y = new Matrix();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, Styleable.f5514g);
        XmlPullParser xmlPullParser = (XmlPullParser) attributeSet;
        this.W = TypedArrayUtils.e(obtainStyledAttributes, xmlPullParser, "reparentWithOverlay", 1, true);
        this.X = TypedArrayUtils.e(obtainStyledAttributes, xmlPullParser, "reparent", 0, true);
        obtainStyledAttributes.recycle();
    }

    private void q0(TransitionValues transitionValues) {
        View view = transitionValues.f5571b;
        if (view.getVisibility() == 8) {
            return;
        }
        transitionValues.f5570a.put("android:changeTransform:parent", view.getParent());
        transitionValues.f5570a.put("android:changeTransform:transforms", new Transforms(view));
        Matrix matrix = view.getMatrix();
        transitionValues.f5570a.put("android:changeTransform:matrix", (matrix == null || matrix.isIdentity()) ? null : new Matrix(matrix));
        if (this.X) {
            Matrix matrix2 = new Matrix();
            ViewUtils.h((ViewGroup) view.getParent(), matrix2);
            matrix2.preTranslate(-r1.getScrollX(), -r1.getScrollY());
            transitionValues.f5570a.put("android:changeTransform:parentMatrix", matrix2);
            transitionValues.f5570a.put("android:changeTransform:intermediateMatrix", view.getTag(R.id.transition_transform));
            transitionValues.f5570a.put("android:changeTransform:intermediateParentMatrix", view.getTag(R.id.parent_matrix));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v6, types: [androidx.transition.TransitionSet] */
    private void r0(ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        View view = transitionValues2.f5571b;
        Matrix matrix = new Matrix((Matrix) transitionValues2.f5570a.get("android:changeTransform:parentMatrix"));
        ViewUtils.i(viewGroup, matrix);
        GhostView a2 = GhostViewUtils.a(view, viewGroup, matrix);
        if (a2 == null) {
            return;
        }
        a2.a((ViewGroup) transitionValues.f5570a.get("android:changeTransform:parent"), transitionValues.f5571b);
        while (true) {
            ?? r1 = this.x;
            if (r1 == 0) {
                break;
            } else {
                this = r1;
            }
        }
        this.a(new GhostListener(view, a2));
        if (c0) {
            View view2 = transitionValues.f5571b;
            if (view2 != transitionValues2.f5571b) {
                ViewUtils.f(view2, 0.0f);
            }
            ViewUtils.f(view, 1.0f);
        }
    }

    private ObjectAnimator s0(TransitionValues transitionValues, TransitionValues transitionValues2, boolean z) {
        Matrix matrix = (Matrix) transitionValues.f5570a.get("android:changeTransform:matrix");
        Matrix matrix2 = (Matrix) transitionValues2.f5570a.get("android:changeTransform:matrix");
        if (matrix == null) {
            matrix = MatrixUtils.f5493a;
        }
        if (matrix2 == null) {
            matrix2 = MatrixUtils.f5493a;
        }
        Matrix matrix3 = matrix2;
        if (matrix.equals(matrix3)) {
            return null;
        }
        Transforms transforms = (Transforms) transitionValues2.f5570a.get("android:changeTransform:transforms");
        View view = transitionValues2.f5571b;
        u0(view);
        float[] fArr = new float[9];
        matrix.getValues(fArr);
        float[] fArr2 = new float[9];
        matrix3.getValues(fArr2);
        PathAnimatorMatrix pathAnimatorMatrix = new PathAnimatorMatrix(view, fArr);
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(pathAnimatorMatrix, PropertyValuesHolder.ofObject(a0, new FloatArrayEvaluator(new float[9]), fArr, fArr2), PropertyValuesHolderUtils.a(b0, y().a(fArr[2], fArr[5], fArr2[2], fArr2[5])));
        Listener listener = new Listener(view, transforms, pathAnimatorMatrix, matrix3, z, this.W);
        ofPropertyValuesHolder.addListener(listener);
        ofPropertyValuesHolder.addPauseListener(listener);
        return ofPropertyValuesHolder;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:?, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x001d, code lost:
    
        if (r4 == r5) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0017, code lost:
    
        if (r5 == r3.f5571b) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x001a, code lost:
    
        r1 = false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean t0(android.view.ViewGroup r4, android.view.ViewGroup r5) {
        /*
            r3 = this;
            boolean r0 = r3.O(r4)
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L1d
            boolean r0 = r3.O(r5)
            if (r0 != 0) goto Lf
            goto L1d
        Lf:
            androidx.transition.TransitionValues r3 = r3.w(r4, r1)
            if (r3 == 0) goto L20
            android.view.View r3 = r3.f5571b
            if (r5 != r3) goto L1a
            goto L1b
        L1a:
            r1 = r2
        L1b:
            r2 = r1
            goto L20
        L1d:
            if (r4 != r5) goto L1a
            goto L1b
        L20:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.transition.ChangeTransform.t0(android.view.ViewGroup, android.view.ViewGroup):boolean");
    }

    static void u0(View view) {
        w0(view, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f);
    }

    private void v0(TransitionValues transitionValues, TransitionValues transitionValues2) {
        Matrix matrix = (Matrix) transitionValues2.f5570a.get("android:changeTransform:parentMatrix");
        transitionValues2.f5571b.setTag(R.id.parent_matrix, matrix);
        Matrix matrix2 = this.Y;
        matrix2.reset();
        matrix.invert(matrix2);
        Matrix matrix3 = (Matrix) transitionValues.f5570a.get("android:changeTransform:matrix");
        if (matrix3 == null) {
            matrix3 = new Matrix();
            transitionValues.f5570a.put("android:changeTransform:matrix", matrix3);
        }
        matrix3.postConcat((Matrix) transitionValues.f5570a.get("android:changeTransform:parentMatrix"));
        matrix3.postConcat(matrix2);
    }

    static void w0(View view, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        view.setTranslationX(f2);
        view.setTranslationY(f3);
        ViewCompat.D0(view, f4);
        view.setScaleX(f5);
        view.setScaleY(f6);
        view.setRotationX(f7);
        view.setRotationY(f8);
        view.setRotation(f9);
    }

    @Override // androidx.transition.Transition
    public String[] J() {
        return Z;
    }

    @Override // androidx.transition.Transition
    public void i(TransitionValues transitionValues) {
        q0(transitionValues);
    }

    @Override // androidx.transition.Transition
    public void l(TransitionValues transitionValues) {
        q0(transitionValues);
        if (c0) {
            return;
        }
        ((ViewGroup) transitionValues.f5571b.getParent()).startViewTransition(transitionValues.f5571b);
    }

    @Override // androidx.transition.Transition
    public Animator p(ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (transitionValues == null || transitionValues2 == null || !transitionValues.f5570a.containsKey("android:changeTransform:parent") || !transitionValues2.f5570a.containsKey("android:changeTransform:parent")) {
            return null;
        }
        ViewGroup viewGroup2 = (ViewGroup) transitionValues.f5570a.get("android:changeTransform:parent");
        boolean z = this.X && !t0(viewGroup2, (ViewGroup) transitionValues2.f5570a.get("android:changeTransform:parent"));
        Matrix matrix = (Matrix) transitionValues.f5570a.get("android:changeTransform:intermediateMatrix");
        if (matrix != null) {
            transitionValues.f5570a.put("android:changeTransform:matrix", matrix);
        }
        Matrix matrix2 = (Matrix) transitionValues.f5570a.get("android:changeTransform:intermediateParentMatrix");
        if (matrix2 != null) {
            transitionValues.f5570a.put("android:changeTransform:parentMatrix", matrix2);
        }
        if (z) {
            v0(transitionValues, transitionValues2);
        }
        ObjectAnimator s0 = s0(transitionValues, transitionValues2, z);
        if (z && s0 != null && this.W) {
            r0(viewGroup, transitionValues, transitionValues2);
        } else if (!c0) {
            viewGroup2.endViewTransition(transitionValues.f5571b);
        }
        return s0;
    }
}
