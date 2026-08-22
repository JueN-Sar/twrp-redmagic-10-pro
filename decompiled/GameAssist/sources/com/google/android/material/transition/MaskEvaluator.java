package com.google.android.material.transition;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.ShapeAppearancePathProvider;
import com.google.android.material.transition.MaterialContainerTransform;

/* loaded from: classes.dex */
class MaskEvaluator {

    /* renamed from: a, reason: collision with root package name */
    private final Path f15586a = new Path();

    /* renamed from: b, reason: collision with root package name */
    private final Path f15587b = new Path();

    /* renamed from: c, reason: collision with root package name */
    private final Path f15588c = new Path();

    /* renamed from: d, reason: collision with root package name */
    private final ShapeAppearancePathProvider f15589d = ShapeAppearancePathProvider.k();

    /* renamed from: e, reason: collision with root package name */
    private ShapeAppearanceModel f15590e;

    MaskEvaluator() {
    }

    void a(Canvas canvas) {
        canvas.clipPath(this.f15586a);
    }

    void b(float f2, ShapeAppearanceModel shapeAppearanceModel, ShapeAppearanceModel shapeAppearanceModel2, RectF rectF, RectF rectF2, RectF rectF3, MaterialContainerTransform.ProgressThresholds progressThresholds) {
        ShapeAppearanceModel p2 = TransitionUtils.p(shapeAppearanceModel, shapeAppearanceModel2, rectF, rectF3, progressThresholds.d(), progressThresholds.c(), f2);
        this.f15590e = p2;
        this.f15589d.d(p2, 1.0f, rectF2, this.f15587b);
        this.f15589d.d(this.f15590e, 1.0f, rectF3, this.f15588c);
        this.f15586a.op(this.f15587b, this.f15588c, Path.Op.UNION);
    }

    ShapeAppearanceModel c() {
        return this.f15590e;
    }

    Path d() {
        return this.f15586a;
    }
}
