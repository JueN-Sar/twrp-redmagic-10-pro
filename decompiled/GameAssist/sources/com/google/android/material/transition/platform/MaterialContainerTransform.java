package com.google.android.material.transition.platform;

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
import android.transition.ArcMotion;
import android.transition.PathMotion;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.util.Preconditions;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.canvas.CanvasCompat;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@RequiresApi
/* loaded from: classes.dex */
public final class MaterialContainerTransform extends Transition {
    private static final String F = "MaterialContainerTransform";
    private static final ProgressThresholdsGroup I;
    private static final ProgressThresholdsGroup K;
    private ProgressThresholds A;
    private ProgressThresholds B;
    private boolean C;
    private float D;
    private float E;

    /* renamed from: c, reason: collision with root package name */
    private boolean f15683c;

    /* renamed from: h, reason: collision with root package name */
    private boolean f15684h;

    /* renamed from: i, reason: collision with root package name */
    private boolean f15685i;

    /* renamed from: j, reason: collision with root package name */
    private boolean f15686j;

    /* renamed from: k, reason: collision with root package name */
    private int f15687k;

    /* renamed from: l, reason: collision with root package name */
    private int f15688l;

    /* renamed from: m, reason: collision with root package name */
    private int f15689m;

    /* renamed from: n, reason: collision with root package name */
    private int f15690n;

    /* renamed from: o, reason: collision with root package name */
    private int f15691o;

    /* renamed from: p, reason: collision with root package name */
    private int f15692p;

    /* renamed from: q, reason: collision with root package name */
    private int f15693q;

    /* renamed from: r, reason: collision with root package name */
    private int f15694r;

    /* renamed from: s, reason: collision with root package name */
    private int f15695s;
    private int t;
    private View u;
    private View v;
    private ShapeAppearanceModel w;
    private ShapeAppearanceModel x;
    private ProgressThresholds y;
    private ProgressThresholds z;
    private static final String[] G = {"materialContainerTransition:bounds", "materialContainerTransition:shapeAppearance"};
    private static final ProgressThresholdsGroup H = new ProgressThresholdsGroup(new ProgressThresholds(0.0f, 0.25f), new ProgressThresholds(0.0f, 1.0f), new ProgressThresholds(0.0f, 1.0f), new ProgressThresholds(0.0f, 0.75f));
    private static final ProgressThresholdsGroup J = new ProgressThresholdsGroup(new ProgressThresholds(0.1f, 0.4f), new ProgressThresholds(0.1f, 1.0f), new ProgressThresholds(0.1f, 1.0f), new ProgressThresholds(0.1f, 0.9f));

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
        private final float f15703a;

        /* renamed from: b, reason: collision with root package name */
        private final float f15704b;

        public ProgressThresholds(float f2, float f3) {
            this.f15703a = f2;
            this.f15704b = f3;
        }

        public float c() {
            return this.f15704b;
        }

        public float d() {
            return this.f15703a;
        }
    }

    private static class ProgressThresholdsGroup {

        /* renamed from: a, reason: collision with root package name */
        private final ProgressThresholds f15705a;

        /* renamed from: b, reason: collision with root package name */
        private final ProgressThresholds f15706b;

        /* renamed from: c, reason: collision with root package name */
        private final ProgressThresholds f15707c;

        /* renamed from: d, reason: collision with root package name */
        private final ProgressThresholds f15708d;

        private ProgressThresholdsGroup(ProgressThresholds progressThresholds, ProgressThresholds progressThresholds2, ProgressThresholds progressThresholds3, ProgressThresholds progressThresholds4) {
            this.f15705a = progressThresholds;
            this.f15706b = progressThresholds2;
            this.f15707c = progressThresholds3;
            this.f15708d = progressThresholds4;
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
        private final View f15709a;

        /* renamed from: b, reason: collision with root package name */
        private final RectF f15710b;

        /* renamed from: c, reason: collision with root package name */
        private final ShapeAppearanceModel f15711c;

        /* renamed from: d, reason: collision with root package name */
        private final float f15712d;

        /* renamed from: e, reason: collision with root package name */
        private final View f15713e;

        /* renamed from: f, reason: collision with root package name */
        private final RectF f15714f;

        /* renamed from: g, reason: collision with root package name */
        private final ShapeAppearanceModel f15715g;

        /* renamed from: h, reason: collision with root package name */
        private final float f15716h;

        /* renamed from: i, reason: collision with root package name */
        private final Paint f15717i;

        /* renamed from: j, reason: collision with root package name */
        private final Paint f15718j;

        /* renamed from: k, reason: collision with root package name */
        private final Paint f15719k;

        /* renamed from: l, reason: collision with root package name */
        private final Paint f15720l;

        /* renamed from: m, reason: collision with root package name */
        private final Paint f15721m;

        /* renamed from: n, reason: collision with root package name */
        private final MaskEvaluator f15722n;

        /* renamed from: o, reason: collision with root package name */
        private final PathMeasure f15723o;

        /* renamed from: p, reason: collision with root package name */
        private final float f15724p;

        /* renamed from: q, reason: collision with root package name */
        private final float[] f15725q;

        /* renamed from: r, reason: collision with root package name */
        private final boolean f15726r;

        /* renamed from: s, reason: collision with root package name */
        private final float f15727s;
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
            canvas.clipPath(this.f15722n.d(), Region.Op.DIFFERENCE);
            i(canvas);
            canvas.restore();
        }

        private void i(Canvas canvas) {
            ShapeAppearanceModel c2 = this.f15722n.c();
            if (!c2.u(this.I)) {
                canvas.drawPath(this.f15722n.d(), this.f15720l);
            } else {
                float a2 = c2.r().a(this.I);
                canvas.drawRoundRect(this.I, a2, a2, this.f15720l);
            }
        }

        private void j(Canvas canvas) {
            m(canvas, this.f15719k);
            Rect bounds = getBounds();
            RectF rectF = this.y;
            TransitionUtils.w(canvas, bounds, rectF.left, rectF.top, this.H.f15673b, this.G.f15652b, new CanvasCompat.CanvasOperation() { // from class: com.google.android.material.transition.platform.MaterialContainerTransform.TransitionDrawable.2
                @Override // com.google.android.material.canvas.CanvasCompat.CanvasOperation
                public void a(Canvas canvas2) {
                    TransitionDrawable.this.f15713e.draw(canvas2);
                }
            });
        }

        private void k(Canvas canvas) {
            m(canvas, this.f15718j);
            Rect bounds = getBounds();
            RectF rectF = this.w;
            TransitionUtils.w(canvas, bounds, rectF.left, rectF.top, this.H.f15672a, this.G.f15651a, new CanvasCompat.CanvasOperation() { // from class: com.google.android.material.transition.platform.MaterialContainerTransform.TransitionDrawable.1
                @Override // com.google.android.material.canvas.CanvasCompat.CanvasOperation
                public void a(Canvas canvas2) {
                    TransitionDrawable.this.f15709a.draw(canvas2);
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
            this.f15721m.setAlpha((int) (this.f15726r ? TransitionUtils.m(0.0f, 255.0f, f2) : TransitionUtils.m(255.0f, 0.0f, f2)));
            this.f15723o.getPosTan(this.f15724p * f2, this.f15725q, null);
            float[] fArr = this.f15725q;
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
                this.f15723o.getPosTan(this.f15724p * f3, fArr, null);
                float[] fArr2 = this.f15725q;
                f5 += (f5 - fArr2[0]) * f4;
                f6 += (f6 - fArr2[1]) * f4;
            }
            float f7 = f5;
            float f8 = f6;
            FitModeResult a2 = this.C.a(f2, ((Float) Preconditions.h(Float.valueOf(this.A.f15706b.f15703a))).floatValue(), ((Float) Preconditions.h(Float.valueOf(this.A.f15706b.f15704b))).floatValue(), this.f15710b.width(), this.f15710b.height(), this.f15714f.width(), this.f15714f.height());
            this.H = a2;
            RectF rectF = this.w;
            float f9 = a2.f15674c;
            rectF.set(f7 - (f9 / 2.0f), f8, (f9 / 2.0f) + f7, a2.f15675d + f8);
            RectF rectF2 = this.y;
            FitModeResult fitModeResult = this.H;
            float f10 = fitModeResult.f15676e;
            rectF2.set(f7 - (f10 / 2.0f), f8, f7 + (f10 / 2.0f), fitModeResult.f15677f + f8);
            this.x.set(this.w);
            this.z.set(this.y);
            float floatValue = ((Float) Preconditions.h(Float.valueOf(this.A.f15707c.f15703a))).floatValue();
            float floatValue2 = ((Float) Preconditions.h(Float.valueOf(this.A.f15707c.f15704b))).floatValue();
            boolean b2 = this.C.b(this.H);
            RectF rectF3 = b2 ? this.x : this.z;
            float n2 = TransitionUtils.n(0.0f, 1.0f, floatValue, floatValue2, f2);
            if (!b2) {
                n2 = 1.0f - n2;
            }
            this.C.c(rectF3, n2, this.H);
            this.I = new RectF(Math.min(this.x.left, this.z.left), Math.min(this.x.top, this.z.top), Math.max(this.x.right, this.z.right), Math.max(this.x.bottom, this.z.bottom));
            this.f15722n.b(f2, this.f15711c, this.f15715g, this.w, this.x, this.z, this.A.f15708d);
            this.J = TransitionUtils.m(this.f15712d, this.f15716h, f2);
            float d2 = d(this.I, this.f15727s);
            float e2 = e(this.I, this.t);
            float f11 = this.J;
            float f12 = (int) (e2 * f11);
            this.K = f12;
            this.f15720l.setShadowLayer(f11, (int) (d2 * f11), f12, 754974720);
            this.G = this.B.a(f2, ((Float) Preconditions.h(Float.valueOf(this.A.f15705a.f15703a))).floatValue(), ((Float) Preconditions.h(Float.valueOf(this.A.f15705a.f15704b))).floatValue(), 0.35f);
            if (this.f15718j.getColor() != 0) {
                this.f15718j.setAlpha(this.G.f15651a);
            }
            if (this.f15719k.getColor() != 0) {
                this.f15719k.setAlpha(this.G.f15652b);
            }
            invalidateSelf();
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            if (this.f15721m.getAlpha() > 0) {
                canvas.drawRect(getBounds(), this.f15721m);
            }
            int save = this.D ? canvas.save() : -1;
            if (this.u && this.J > 0.0f) {
                h(canvas);
            }
            this.f15722n.a(canvas);
            m(canvas, this.f15717i);
            if (this.G.f15653c) {
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
            this.f15717i = paint;
            Paint paint2 = new Paint();
            this.f15718j = paint2;
            Paint paint3 = new Paint();
            this.f15719k = paint3;
            this.f15720l = new Paint();
            Paint paint4 = new Paint();
            this.f15721m = paint4;
            this.f15722n = new MaskEvaluator();
            this.f15725q = new float[]{rectF.centerX(), rectF.top};
            MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable();
            this.v = materialShapeDrawable;
            Paint paint5 = new Paint();
            this.E = paint5;
            this.F = new Path();
            this.f15709a = view;
            this.f15710b = rectF;
            this.f15711c = shapeAppearanceModel;
            this.f15712d = f2;
            this.f15713e = view2;
            this.f15714f = rectF2;
            this.f15715g = shapeAppearanceModel2;
            this.f15716h = f3;
            this.f15726r = z;
            this.u = z2;
            this.B = fadeModeEvaluator;
            this.C = fitModeEvaluator;
            this.A = progressThresholdsGroup;
            this.D = z3;
            WindowManager windowManager = (WindowManager) view.getContext().getSystemService("window");
            windowManager.getDefaultDisplay().getMetrics(new DisplayMetrics());
            this.f15727s = r12.widthPixels;
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
            PathMeasure pathMeasure = new PathMeasure(pathMotion.getPath(l2.x, l2.y, l3.x, l3.y), false);
            this.f15723o = pathMeasure;
            this.f15724p = pathMeasure.getLength();
            paint4.setStyle(Paint.Style.FILL);
            paint4.setShader(TransitionUtils.d(i5));
            paint5.setStyle(Paint.Style.STROKE);
            paint5.setStrokeWidth(10.0f);
            o(0.0f);
        }
    }

    static {
        I = new ProgressThresholdsGroup(new ProgressThresholds(0.6f, 0.9f), new ProgressThresholds(0.0f, 1.0f), new ProgressThresholds(0.0f, 0.9f), new ProgressThresholds(0.3f, 0.9f));
        K = new ProgressThresholdsGroup(new ProgressThresholds(0.6f, 0.9f), new ProgressThresholds(0.0f, 0.9f), new ProgressThresholds(0.0f, 0.9f), new ProgressThresholds(0.2f, 0.9f));
    }

    private ProgressThresholdsGroup b(boolean z) {
        PathMotion pathMotion = getPathMotion();
        return ((pathMotion instanceof ArcMotion) || (pathMotion instanceof MaterialArcMotion)) ? i(z, J, K) : i(z, H, I);
    }

    private static RectF c(View view, View view2, float f2, float f3) {
        if (view2 == null) {
            return new RectF(0.0f, 0.0f, view.getWidth(), view.getHeight());
        }
        RectF h2 = TransitionUtils.h(view2);
        h2.offset(f2, f3);
        return h2;
    }

    private static ShapeAppearanceModel e(View view, RectF rectF, ShapeAppearanceModel shapeAppearanceModel) {
        return TransitionUtils.c(h(view, shapeAppearanceModel), rectF);
    }

    private static void f(TransitionValues transitionValues, View view, int i2, ShapeAppearanceModel shapeAppearanceModel) {
        if (i2 != -1) {
            transitionValues.view = TransitionUtils.g(transitionValues.view, i2);
        } else if (view != null) {
            transitionValues.view = view;
        } else if (transitionValues.view.getTag(R.id.mtrl_motion_snapshot_view) instanceof View) {
            View view2 = (View) transitionValues.view.getTag(R.id.mtrl_motion_snapshot_view);
            transitionValues.view.setTag(R.id.mtrl_motion_snapshot_view, null);
            transitionValues.view = view2;
        }
        View view3 = transitionValues.view;
        if (!ViewCompat.N(view3) && view3.getWidth() == 0 && view3.getHeight() == 0) {
            return;
        }
        RectF i3 = view3.getParent() == null ? TransitionUtils.i(view3) : TransitionUtils.h(view3);
        transitionValues.values.put("materialContainerTransition:bounds", i3);
        transitionValues.values.put("materialContainerTransition:shapeAppearance", e(view3, i3, shapeAppearanceModel));
    }

    private static float g(float f2, View view) {
        return f2 != -1.0f ? f2 : ViewCompat.r(view);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static ShapeAppearanceModel h(View view, ShapeAppearanceModel shapeAppearanceModel) {
        if (shapeAppearanceModel != null) {
            return shapeAppearanceModel;
        }
        if (view.getTag(R.id.mtrl_motion_snapshot_view) instanceof ShapeAppearanceModel) {
            return (ShapeAppearanceModel) view.getTag(R.id.mtrl_motion_snapshot_view);
        }
        Context context = view.getContext();
        int j2 = j(context);
        return j2 != -1 ? ShapeAppearanceModel.b(context, j2, 0).m() : view instanceof Shapeable ? ((Shapeable) view).getShapeAppearanceModel() : ShapeAppearanceModel.a().m();
    }

    private ProgressThresholdsGroup i(boolean z, ProgressThresholdsGroup progressThresholdsGroup, ProgressThresholdsGroup progressThresholdsGroup2) {
        if (!z) {
            progressThresholdsGroup = progressThresholdsGroup2;
        }
        return new ProgressThresholdsGroup((ProgressThresholds) TransitionUtils.e(this.y, progressThresholdsGroup.f15705a), (ProgressThresholds) TransitionUtils.e(this.z, progressThresholdsGroup.f15706b), (ProgressThresholds) TransitionUtils.e(this.A, progressThresholdsGroup.f15707c), (ProgressThresholds) TransitionUtils.e(this.B, progressThresholdsGroup.f15708d));
    }

    private static int j(Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new int[]{R.attr.transitionShapeAppearance});
        int resourceId = obtainStyledAttributes.getResourceId(0, -1);
        obtainStyledAttributes.recycle();
        return resourceId;
    }

    private boolean k(RectF rectF, RectF rectF2) {
        int i2 = this.f15694r;
        if (i2 == 0) {
            return TransitionUtils.b(rectF2) > TransitionUtils.b(rectF);
        }
        if (i2 == 1) {
            return true;
        }
        if (i2 == 2) {
            return false;
        }
        throw new IllegalArgumentException("Invalid transition direction: " + this.f15694r);
    }

    private void l(Context context, boolean z) {
        TransitionUtils.s(this, context, R.attr.motionEasingEmphasizedInterpolator, AnimationUtils.f13815b);
        TransitionUtils.r(this, context, z ? R.attr.motionDurationLong2 : R.attr.motionDurationMedium4);
        if (this.f15685i) {
            return;
        }
        TransitionUtils.t(this, context, R.attr.motionPath);
    }

    @Override // android.transition.Transition
    public void captureEndValues(TransitionValues transitionValues) {
        f(transitionValues, this.v, this.f15689m, this.x);
    }

    @Override // android.transition.Transition
    public void captureStartValues(TransitionValues transitionValues) {
        f(transitionValues, this.u, this.f15688l, this.w);
    }

    @Override // android.transition.Transition
    public Animator createAnimator(ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        final View f2;
        View view;
        if (transitionValues != null && transitionValues2 != null) {
            RectF rectF = (RectF) transitionValues.values.get("materialContainerTransition:bounds");
            ShapeAppearanceModel shapeAppearanceModel = (ShapeAppearanceModel) transitionValues.values.get("materialContainerTransition:shapeAppearance");
            if (rectF != null && shapeAppearanceModel != null) {
                RectF rectF2 = (RectF) transitionValues2.values.get("materialContainerTransition:bounds");
                ShapeAppearanceModel shapeAppearanceModel2 = (ShapeAppearanceModel) transitionValues2.values.get("materialContainerTransition:shapeAppearance");
                if (rectF2 == null || shapeAppearanceModel2 == null) {
                    Log.w(F, "Skipping due to null end bounds. Ensure end view is laid out and measured.");
                    return null;
                }
                final View view2 = transitionValues.view;
                final View view3 = transitionValues2.view;
                View view4 = view3.getParent() != null ? view3 : view2;
                if (this.f15687k == view4.getId()) {
                    f2 = (View) view4.getParent();
                    view = view4;
                } else {
                    f2 = TransitionUtils.f(view4, this.f15687k);
                    view = null;
                }
                RectF h2 = TransitionUtils.h(f2);
                float f3 = -h2.left;
                float f4 = -h2.top;
                RectF c2 = c(f2, view, f3, f4);
                rectF.offset(f3, f4);
                rectF2.offset(f3, f4);
                boolean k2 = k(rectF, rectF2);
                if (!this.f15686j) {
                    l(view4.getContext(), k2);
                }
                final TransitionDrawable transitionDrawable = new TransitionDrawable(getPathMotion(), view2, rectF, shapeAppearanceModel, g(this.D, view2), view3, rectF2, shapeAppearanceModel2, g(this.E, view3), this.f15690n, this.f15691o, this.f15692p, this.f15693q, k2, this.C, FadeModeEvaluators.a(this.f15695s, k2), FitModeEvaluators.a(this.t, k2, rectF, rectF2), b(k2), this.f15683c);
                transitionDrawable.setBounds(Math.round(c2.left), Math.round(c2.top), Math.round(c2.right), Math.round(c2.bottom));
                ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
                ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.transition.platform.MaterialContainerTransform.1
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        transitionDrawable.n(valueAnimator.getAnimatedFraction());
                    }
                });
                addListener(new TransitionListenerAdapter() { // from class: com.google.android.material.transition.platform.MaterialContainerTransform.2
                    @Override // com.google.android.material.transition.platform.TransitionListenerAdapter, android.transition.Transition.TransitionListener
                    public void onTransitionEnd(Transition transition) {
                        MaterialContainerTransform.this.removeListener(this);
                        if (MaterialContainerTransform.this.f15684h) {
                            return;
                        }
                        view2.setAlpha(1.0f);
                        view3.setAlpha(1.0f);
                        ViewUtils.m(f2).b(transitionDrawable);
                    }

                    @Override // com.google.android.material.transition.platform.TransitionListenerAdapter, android.transition.Transition.TransitionListener
                    public void onTransitionStart(Transition transition) {
                        ViewUtils.m(f2).a(transitionDrawable);
                        view2.setAlpha(0.0f);
                        view3.setAlpha(0.0f);
                    }
                });
                return ofFloat;
            }
            Log.w(F, "Skipping due to null start bounds. Ensure start view is laid out and measured.");
        }
        return null;
    }

    @Override // android.transition.Transition
    public String[] getTransitionProperties() {
        return G;
    }

    public void m(boolean z) {
        this.f15684h = z;
    }

    @Override // android.transition.Transition
    public void setPathMotion(PathMotion pathMotion) {
        super.setPathMotion(pathMotion);
        this.f15685i = true;
    }
}
