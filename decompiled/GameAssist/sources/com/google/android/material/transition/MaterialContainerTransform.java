package com.google.android.material.transition;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.annotation.RestrictTo;
import androidx.core.util.Preconditions;
import androidx.core.view.ViewCompat;
import androidx.transition.ArcMotion;
import androidx.transition.PathMotion;
import androidx.transition.Transition;
import androidx.transition.TransitionValues;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.canvas.CanvasCompat;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public final class MaterialContainerTransform extends Transition {
    private static final ProgressThresholdsGroup A0;
    private static final String v0 = "MaterialContainerTransform";
    private static final ProgressThresholdsGroup y0;
    private boolean W;
    private boolean X;
    private boolean Y;
    private boolean Z;
    private int a0;
    private int b0;
    private int c0;
    private int d0;
    private int e0;
    private int f0;
    private int g0;
    private int h0;
    private int i0;
    private int j0;
    private View k0;
    private View l0;
    private ShapeAppearanceModel m0;
    private ShapeAppearanceModel n0;
    private ProgressThresholds o0;
    private ProgressThresholds p0;
    private ProgressThresholds q0;
    private ProgressThresholds r0;
    private boolean s0;
    private float t0;
    private float u0;
    private static final String[] w0 = {"materialContainerTransition:bounds", "materialContainerTransition:shapeAppearance"};
    private static final ProgressThresholdsGroup x0 = new ProgressThresholdsGroup(new ProgressThresholds(0.0f, 0.25f), new ProgressThresholds(0.0f, 1.0f), new ProgressThresholds(0.0f, 1.0f), new ProgressThresholds(0.0f, 0.75f));
    private static final ProgressThresholdsGroup z0 = new ProgressThresholdsGroup(new ProgressThresholds(0.1f, 0.4f), new ProgressThresholds(0.1f, 1.0f), new ProgressThresholds(0.1f, 1.0f), new ProgressThresholds(0.1f, 0.9f));

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface FadeMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface FitMode {
    }

    public static class ProgressThresholds {

        /* renamed from: a, reason: collision with root package name */
        private final float f15598a;

        /* renamed from: b, reason: collision with root package name */
        private final float f15599b;

        public ProgressThresholds(float f2, float f3) {
            this.f15598a = f2;
            this.f15599b = f3;
        }

        public float c() {
            return this.f15599b;
        }

        public float d() {
            return this.f15598a;
        }
    }

    private static class ProgressThresholdsGroup {

        /* renamed from: a, reason: collision with root package name */
        private final ProgressThresholds f15600a;

        /* renamed from: b, reason: collision with root package name */
        private final ProgressThresholds f15601b;

        /* renamed from: c, reason: collision with root package name */
        private final ProgressThresholds f15602c;

        /* renamed from: d, reason: collision with root package name */
        private final ProgressThresholds f15603d;

        private ProgressThresholdsGroup(ProgressThresholds progressThresholds, ProgressThresholds progressThresholds2, ProgressThresholds progressThresholds3, ProgressThresholds progressThresholds4) {
            this.f15600a = progressThresholds;
            this.f15601b = progressThresholds2;
            this.f15602c = progressThresholds3;
            this.f15603d = progressThresholds4;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface TransitionDirection {
    }

    private static final class TransitionDrawable extends Drawable {
        private final ProgressThresholdsGroup A;
        private final FadeModeEvaluator B;
        private final FitModeEvaluator C;
        private final boolean D;
        private final Paint E;
        private final Path F;
        private FadeModeResult G;
        private FitModeResult H;
        private RectF I;
        private float J;
        private float K;
        private float L;

        /* renamed from: a, reason: collision with root package name */
        private final View f15604a;

        /* renamed from: b, reason: collision with root package name */
        private final RectF f15605b;

        /* renamed from: c, reason: collision with root package name */
        private final ShapeAppearanceModel f15606c;

        /* renamed from: d, reason: collision with root package name */
        private final float f15607d;

        /* renamed from: e, reason: collision with root package name */
        private final View f15608e;

        /* renamed from: f, reason: collision with root package name */
        private final RectF f15609f;

        /* renamed from: g, reason: collision with root package name */
        private final ShapeAppearanceModel f15610g;

        /* renamed from: h, reason: collision with root package name */
        private final float f15611h;

        /* renamed from: i, reason: collision with root package name */
        private final Paint f15612i;

        /* renamed from: j, reason: collision with root package name */
        private final Paint f15613j;

        /* renamed from: k, reason: collision with root package name */
        private final Paint f15614k;

        /* renamed from: l, reason: collision with root package name */
        private final Paint f15615l;

        /* renamed from: m, reason: collision with root package name */
        private final Paint f15616m;

        /* renamed from: n, reason: collision with root package name */
        private final MaskEvaluator f15617n;

        /* renamed from: o, reason: collision with root package name */
        private final PathMeasure f15618o;

        /* renamed from: p, reason: collision with root package name */
        private final float f15619p;

        /* renamed from: q, reason: collision with root package name */
        private final float[] f15620q;

        /* renamed from: r, reason: collision with root package name */
        private final boolean f15621r;

        /* renamed from: s, reason: collision with root package name */
        private final float f15622s;
        private final float t;
        private final boolean u;
        private final MaterialShapeDrawable v;
        private final RectF w;
        private final RectF x;
        private final RectF y;
        private final RectF z;

        private static float d(RectF rectF, float f2) {
            return ((rectF.centerX() / (f2 / 2.0f)) - 1.0f) * 0.3f;
        }

        private static float e(RectF rectF, float f2) {
            return (rectF.centerY() / f2) * 1.5f;
        }

        private void f(Canvas canvas, RectF rectF, Path path, int i2) {
            PointF l2 = l(rectF);
            if (this.L == 0.0f) {
                path.reset();
                path.moveTo(l2.x, l2.y);
            } else {
                path.lineTo(l2.x, l2.y);
                this.E.setColor(i2);
                canvas.drawPath(path, this.E);
            }
        }

        private void g(Canvas canvas, RectF rectF, int i2) {
            this.E.setColor(i2);
            canvas.drawRect(rectF, this.E);
        }

        private void h(Canvas canvas) {
            canvas.save();
            canvas.clipPath(this.f15617n.d(), Region.Op.DIFFERENCE);
            i(canvas);
            canvas.restore();
        }

        private void i(Canvas canvas) {
            ShapeAppearanceModel c2 = this.f15617n.c();
            if (!c2.u(this.I)) {
                canvas.drawPath(this.f15617n.d(), this.f15615l);
            } else {
                float a2 = c2.r().a(this.I);
                canvas.drawRoundRect(this.I, a2, a2, this.f15615l);
            }
        }

        private void j(Canvas canvas) {
            m(canvas, this.f15614k);
            Rect bounds = getBounds();
            RectF rectF = this.y;
            TransitionUtils.v(canvas, bounds, rectF.left, rectF.top, this.H.f15581b, this.G.f15560b, new CanvasCompat.CanvasOperation() { // from class: com.google.android.material.transition.MaterialContainerTransform.TransitionDrawable.2
                @Override // com.google.android.material.canvas.CanvasCompat.CanvasOperation
                public void a(Canvas canvas2) {
                    TransitionDrawable.this.f15608e.draw(canvas2);
                }
            });
        }

        private void k(Canvas canvas) {
            m(canvas, this.f15613j);
            Rect bounds = getBounds();
            RectF rectF = this.w;
            TransitionUtils.v(canvas, bounds, rectF.left, rectF.top, this.H.f15580a, this.G.f15559a, new CanvasCompat.CanvasOperation() { // from class: com.google.android.material.transition.MaterialContainerTransform.TransitionDrawable.1
                @Override // com.google.android.material.canvas.CanvasCompat.CanvasOperation
                public void a(Canvas canvas2) {
                    TransitionDrawable.this.f15604a.draw(canvas2);
                }
            });
        }

        private static PointF l(RectF rectF) {
            return new PointF(rectF.centerX(), rectF.top);
        }

        private void m(Canvas canvas, Paint paint) {
            if (paint.getColor() == 0 || paint.getAlpha() <= 0) {
                return;
            }
            canvas.drawRect(getBounds(), paint);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void n(float f2) {
            if (this.L != f2) {
                o(f2);
            }
        }

        private void o(float f2) {
            float f3;
            float f4;
            this.L = f2;
            this.f15616m.setAlpha((int) (this.f15621r ? TransitionUtils.l(0.0f, 255.0f, f2) : TransitionUtils.l(255.0f, 0.0f, f2)));
            this.f15618o.getPosTan(this.f15619p * f2, this.f15620q, null);
            float[] fArr = this.f15620q;
            float f5 = fArr[0];
            float f6 = fArr[1];
            if (f2 > 1.0f || f2 < 0.0f) {
                if (f2 > 1.0f) {
                    f4 = (f2 - 1.0f) / 0.00999999f;
                    f3 = 0.99f;
                } else {
                    f3 = 0.01f;
                    f4 = (f2 / 0.01f) * (-1.0f);
                }
                this.f15618o.getPosTan(this.f15619p * f3, fArr, null);
                float[] fArr2 = this.f15620q;
                f5 += (f5 - fArr2[0]) * f4;
                f6 += (f6 - fArr2[1]) * f4;
            }
            float f7 = f5;
            float f8 = f6;
            FitModeResult a2 = this.C.a(f2, ((Float) Preconditions.h(Float.valueOf(this.A.f15601b.f15598a))).floatValue(), ((Float) Preconditions.h(Float.valueOf(this.A.f15601b.f15599b))).floatValue(), this.f15605b.width(), this.f15605b.height(), this.f15609f.width(), this.f15609f.height());
            this.H = a2;
            RectF rectF = this.w;
            float f9 = a2.f15582c;
            rectF.set(f7 - (f9 / 2.0f), f8, (f9 / 2.0f) + f7, a2.f15583d + f8);
            RectF rectF2 = this.y;
            FitModeResult fitModeResult = this.H;
            float f10 = fitModeResult.f15584e;
            rectF2.set(f7 - (f10 / 2.0f), f8, f7 + (f10 / 2.0f), fitModeResult.f15585f + f8);
            this.x.set(this.w);
            this.z.set(this.y);
            float floatValue = ((Float) Preconditions.h(Float.valueOf(this.A.f15602c.f15598a))).floatValue();
            float floatValue2 = ((Float) Preconditions.h(Float.valueOf(this.A.f15602c.f15599b))).floatValue();
            boolean b2 = this.C.b(this.H);
            RectF rectF3 = b2 ? this.x : this.z;
            float m2 = TransitionUtils.m(0.0f, 1.0f, floatValue, floatValue2, f2);
            if (!b2) {
                m2 = 1.0f - m2;
            }
            this.C.c(rectF3, m2, this.H);
            this.I = new RectF(Math.min(this.x.left, this.z.left), Math.min(this.x.top, this.z.top), Math.max(this.x.right, this.z.right), Math.max(this.x.bottom, this.z.bottom));
            this.f15617n.b(f2, this.f15606c, this.f15610g, this.w, this.x, this.z, this.A.f15603d);
            this.J = TransitionUtils.l(this.f15607d, this.f15611h, f2);
            float d2 = d(this.I, this.f15622s);
            float e2 = e(this.I, this.t);
            float f11 = this.J;
            float f12 = (int) (e2 * f11);
            this.K = f12;
            this.f15615l.setShadowLayer(f11, (int) (d2 * f11), f12, 754974720);
            this.G = this.B.a(f2, ((Float) Preconditions.h(Float.valueOf(this.A.f15600a.f15598a))).floatValue(), ((Float) Preconditions.h(Float.valueOf(this.A.f15600a.f15599b))).floatValue(), 0.35f);
            if (this.f15613j.getColor() != 0) {
                this.f15613j.setAlpha(this.G.f15559a);
            }
            if (this.f15614k.getColor() != 0) {
                this.f15614k.setAlpha(this.G.f15560b);
            }
            invalidateSelf();
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            if (this.f15616m.getAlpha() > 0) {
                canvas.drawRect(getBounds(), this.f15616m);
            }
            int save = this.D ? canvas.save() : -1;
            if (this.u && this.J > 0.0f) {
                h(canvas);
            }
            this.f15617n.a(canvas);
            m(canvas, this.f15612i);
            if (this.G.f15561c) {
                k(canvas);
                j(canvas);
            } else {
                j(canvas);
                k(canvas);
            }
            if (this.D) {
                canvas.restoreToCount(save);
                f(canvas, this.w, this.F, -65281);
                g(canvas, this.x, -256);
                g(canvas, this.w, -16711936);
                g(canvas, this.z, -16711681);
                g(canvas, this.y, -16776961);
            }
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -3;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int i2) {
            throw new UnsupportedOperationException("Setting alpha on is not supported");
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter colorFilter) {
            throw new UnsupportedOperationException("Setting a color filter is not supported");
        }

        private TransitionDrawable(PathMotion pathMotion, View view, RectF rectF, ShapeAppearanceModel shapeAppearanceModel, float f2, View view2, RectF rectF2, ShapeAppearanceModel shapeAppearanceModel2, float f3, int i2, int i3, int i4, int i5, boolean z, boolean z2, FadeModeEvaluator fadeModeEvaluator, FitModeEvaluator fitModeEvaluator, ProgressThresholdsGroup progressThresholdsGroup, boolean z3) {
            Paint paint = new Paint();
            this.f15612i = paint;
            Paint paint2 = new Paint();
            this.f15613j = paint2;
            Paint paint3 = new Paint();
            this.f15614k = paint3;
            this.f15615l = new Paint();
            Paint paint4 = new Paint();
            this.f15616m = paint4;
            this.f15617n = new MaskEvaluator();
            this.f15620q = new float[]{rectF.centerX(), rectF.top};
            MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable();
            this.v = materialShapeDrawable;
            Paint paint5 = new Paint();
            this.E = paint5;
            this.F = new Path();
            this.f15604a = view;
            this.f15605b = rectF;
            this.f15606c = shapeAppearanceModel;
            this.f15607d = f2;
            this.f15608e = view2;
            this.f15609f = rectF2;
            this.f15610g = shapeAppearanceModel2;
            this.f15611h = f3;
            this.f15621r = z;
            this.u = z2;
            this.B = fadeModeEvaluator;
            this.C = fitModeEvaluator;
            this.A = progressThresholdsGroup;
            this.D = z3;
            WindowManager windowManager = (WindowManager) view.getContext().getSystemService("window");
            windowManager.getDefaultDisplay().getMetrics(new DisplayMetrics());
            this.f15622s = r12.widthPixels;
            this.t = r12.heightPixels;
            paint.setColor(i2);
            paint2.setColor(i3);
            paint3.setColor(i4);
            materialShapeDrawable.a0(ColorStateList.valueOf(0));
            materialShapeDrawable.i0(2);
            materialShapeDrawable.f0(false);
            materialShapeDrawable.g0(-7829368);
            RectF rectF3 = new RectF(rectF);
            this.w = rectF3;
            this.x = new RectF(rectF3);
            RectF rectF4 = new RectF(rectF3);
            this.y = rectF4;
            this.z = new RectF(rectF4);
            PointF l2 = l(rectF);
            PointF l3 = l(rectF2);
            PathMeasure pathMeasure = new PathMeasure(pathMotion.a(l2.x, l2.y, l3.x, l3.y), false);
            this.f15618o = pathMeasure;
            this.f15619p = pathMeasure.getLength();
            paint4.setStyle(Paint.Style.FILL);
            paint4.setShader(TransitionUtils.d(i5));
            paint5.setStyle(Paint.Style.STROKE);
            paint5.setStrokeWidth(10.0f);
            o(0.0f);
        }
    }

    static {
        y0 = new ProgressThresholdsGroup(new ProgressThresholds(0.6f, 0.9f), new ProgressThresholds(0.0f, 1.0f), new ProgressThresholds(0.0f, 0.9f), new ProgressThresholds(0.3f, 0.9f));
        A0 = new ProgressThresholdsGroup(new ProgressThresholds(0.6f, 0.9f), new ProgressThresholds(0.0f, 0.9f), new ProgressThresholds(0.0f, 0.9f), new ProgressThresholds(0.2f, 0.9f));
    }

    private void A0(Context context, boolean z) {
        TransitionUtils.r(this, context, R.attr.motionEasingEmphasizedInterpolator, AnimationUtils.f13815b);
        TransitionUtils.q(this, context, z ? R.attr.motionDurationLong2 : R.attr.motionDurationMedium4);
        if (this.Y) {
            return;
        }
        TransitionUtils.s(this, context, R.attr.motionPath);
    }

    private ProgressThresholdsGroup r0(boolean z) {
        PathMotion y = y();
        return ((y instanceof ArcMotion) || (y instanceof MaterialArcMotion)) ? x0(z, z0, A0) : x0(z, x0, y0);
    }

    private static RectF s0(View view, View view2, float f2, float f3) {
        if (view2 == null) {
            return new RectF(0.0f, 0.0f, view.getWidth(), view.getHeight());
        }
        RectF h2 = TransitionUtils.h(view2);
        h2.offset(f2, f3);
        return h2;
    }

    private static ShapeAppearanceModel t0(View view, RectF rectF, ShapeAppearanceModel shapeAppearanceModel) {
        return TransitionUtils.c(w0(view, shapeAppearanceModel), rectF);
    }

    private static void u0(TransitionValues transitionValues, View view, int i2, ShapeAppearanceModel shapeAppearanceModel) {
        if (i2 != -1) {
            transitionValues.f5571b = TransitionUtils.g(transitionValues.f5571b, i2);
        } else if (view != null) {
            transitionValues.f5571b = view;
        } else if (transitionValues.f5571b.getTag(R.id.mtrl_motion_snapshot_view) instanceof View) {
            View view2 = (View) transitionValues.f5571b.getTag(R.id.mtrl_motion_snapshot_view);
            transitionValues.f5571b.setTag(R.id.mtrl_motion_snapshot_view, null);
            transitionValues.f5571b = view2;
        }
        View view3 = transitionValues.f5571b;
        if (!ViewCompat.N(view3) && view3.getWidth() == 0 && view3.getHeight() == 0) {
            return;
        }
        RectF i3 = view3.getParent() == null ? TransitionUtils.i(view3) : TransitionUtils.h(view3);
        transitionValues.f5570a.put("materialContainerTransition:bounds", i3);
        transitionValues.f5570a.put("materialContainerTransition:shapeAppearance", t0(view3, i3, shapeAppearanceModel));
    }

    private static float v0(float f2, View view) {
        return f2 != -1.0f ? f2 : ViewCompat.r(view);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static ShapeAppearanceModel w0(View view, ShapeAppearanceModel shapeAppearanceModel) {
        if (shapeAppearanceModel != null) {
            return shapeAppearanceModel;
        }
        if (view.getTag(R.id.mtrl_motion_snapshot_view) instanceof ShapeAppearanceModel) {
            return (ShapeAppearanceModel) view.getTag(R.id.mtrl_motion_snapshot_view);
        }
        Context context = view.getContext();
        int y02 = y0(context);
        return y02 != -1 ? ShapeAppearanceModel.b(context, y02, 0).m() : view instanceof Shapeable ? ((Shapeable) view).getShapeAppearanceModel() : ShapeAppearanceModel.a().m();
    }

    private ProgressThresholdsGroup x0(boolean z, ProgressThresholdsGroup progressThresholdsGroup, ProgressThresholdsGroup progressThresholdsGroup2) {
        if (!z) {
            progressThresholdsGroup = progressThresholdsGroup2;
        }
        return new ProgressThresholdsGroup((ProgressThresholds) TransitionUtils.e(this.o0, progressThresholdsGroup.f15600a), (ProgressThresholds) TransitionUtils.e(this.p0, progressThresholdsGroup.f15601b), (ProgressThresholds) TransitionUtils.e(this.q0, progressThresholdsGroup.f15602c), (ProgressThresholds) TransitionUtils.e(this.r0, progressThresholdsGroup.f15603d));
    }

    private static int y0(Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new int[]{R.attr.transitionShapeAppearance});
        int resourceId = obtainStyledAttributes.getResourceId(0, -1);
        obtainStyledAttributes.recycle();
        return resourceId;
    }

    private boolean z0(RectF rectF, RectF rectF2) {
        int i2 = this.h0;
        if (i2 == 0) {
            return TransitionUtils.b(rectF2) > TransitionUtils.b(rectF);
        }
        if (i2 == 1) {
            return true;
        }
        if (i2 == 2) {
            return false;
        }
        throw new IllegalArgumentException("Invalid transition direction: " + this.h0);
    }

    @Override // androidx.transition.Transition
    public String[] J() {
        return w0;
    }

    @Override // androidx.transition.Transition
    public void i(TransitionValues transitionValues) {
        u0(transitionValues, this.l0, this.c0, this.n0);
    }

    @Override // androidx.transition.Transition
    public void l(TransitionValues transitionValues) {
        u0(transitionValues, this.k0, this.b0, this.m0);
    }

    @Override // androidx.transition.Transition
    public void l0(PathMotion pathMotion) {
        super.l0(pathMotion);
        this.Y = true;
    }

    @Override // androidx.transition.Transition
    public Animator p(ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        final View f2;
        View view;
        if (transitionValues != null && transitionValues2 != null) {
            RectF rectF = (RectF) transitionValues.f5570a.get("materialContainerTransition:bounds");
            ShapeAppearanceModel shapeAppearanceModel = (ShapeAppearanceModel) transitionValues.f5570a.get("materialContainerTransition:shapeAppearance");
            if (rectF != null && shapeAppearanceModel != null) {
                RectF rectF2 = (RectF) transitionValues2.f5570a.get("materialContainerTransition:bounds");
                ShapeAppearanceModel shapeAppearanceModel2 = (ShapeAppearanceModel) transitionValues2.f5570a.get("materialContainerTransition:shapeAppearance");
                if (rectF2 == null || shapeAppearanceModel2 == null) {
                    Log.w(v0, "Skipping due to null end bounds. Ensure end view is laid out and measured.");
                    return null;
                }
                final View view2 = transitionValues.f5571b;
                final View view3 = transitionValues2.f5571b;
                View view4 = view3.getParent() != null ? view3 : view2;
                if (this.a0 == view4.getId()) {
                    f2 = (View) view4.getParent();
                    view = view4;
                } else {
                    f2 = TransitionUtils.f(view4, this.a0);
                    view = null;
                }
                RectF h2 = TransitionUtils.h(f2);
                float f3 = -h2.left;
                float f4 = -h2.top;
                RectF s0 = s0(f2, view, f3, f4);
                rectF.offset(f3, f4);
                rectF2.offset(f3, f4);
                boolean z02 = z0(rectF, rectF2);
                if (!this.Z) {
                    A0(view4.getContext(), z02);
                }
                final TransitionDrawable transitionDrawable = new TransitionDrawable(y(), view2, rectF, shapeAppearanceModel, v0(this.t0, view2), view3, rectF2, shapeAppearanceModel2, v0(this.u0, view3), this.d0, this.e0, this.f0, this.g0, z02, this.s0, FadeModeEvaluators.a(this.i0, z02), FitModeEvaluators.a(this.j0, z02, rectF, rectF2), r0(z02), this.W);
                transitionDrawable.setBounds(Math.round(s0.left), Math.round(s0.top), Math.round(s0.right), Math.round(s0.bottom));
                ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
                ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.transition.MaterialContainerTransform.1
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        transitionDrawable.n(valueAnimator.getAnimatedFraction());
                    }
                });
                a(new TransitionListenerAdapter() { // from class: com.google.android.material.transition.MaterialContainerTransform.2
                    @Override // com.google.android.material.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
                    public void c(Transition transition) {
                        ViewUtils.m(f2).a(transitionDrawable);
                        view2.setAlpha(0.0f);
                        view3.setAlpha(0.0f);
                    }

                    @Override // com.google.android.material.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
                    public void f(Transition transition) {
                        MaterialContainerTransform.this.b0(this);
                        if (MaterialContainerTransform.this.X) {
                            return;
                        }
                        view2.setAlpha(1.0f);
                        view3.setAlpha(1.0f);
                        ViewUtils.m(f2).b(transitionDrawable);
                    }
                });
                return ofFloat;
            }
            Log.w(v0, "Skipping due to null start bounds. Ensure start view is laid out and measured.");
        }
        return null;
    }
}
