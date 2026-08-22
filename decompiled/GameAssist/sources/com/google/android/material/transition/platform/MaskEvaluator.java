package com.google.android.material.transition.platform;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import androidx.annotation.RequiresApi;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.ShapeAppearancePathProvider;
import com.google.android.material.transition.platform.MaterialContainerTransform;

@RequiresApi
/* loaded from: classes.dex */
class MaskEvaluator {

    /* renamed from: a, reason: collision with root package name */
    private final Path f15678a = new Path();

    /* renamed from: b, reason: collision with root package name */
    private final Path f15679b = new Path();

    /* renamed from: c, reason: collision with root package name */
    private final Path f15680c = new Path();

    /* renamed from: d, reason: collision with root package name */
    private final ShapeAppearancePathProvider f15681d = ShapeAppearancePathProvider.k();

    /* renamed from: e, reason: collision with root package name */
    private ShapeAppearanceModel f15682e;

    MaskEvaluator() {
    }

    void a(Canvas canvas) {
        canvas.clipPath(this.f15678a);
    }

    void b(float f2, ShapeAppearanceModel shapeAppearanceModel, ShapeAppearanceModel shapeAppearanceModel2, RectF rectF, RectF rectF2, RectF rectF3, MaterialContainerTransform.ProgressThresholds progressThresholds) {
        ShapeAppearanceModel q2 = TransitionUtils.q(shapeAppearanceModel, shapeAppearanceModel2, rectF, rectF3, progressThresholds.d(), progressThresholds.c(), f2);
        this.f15682e = q2;
        this.f15681d.d(q2, 1.0f, rectF2, this.f15679b);
        this.f15681d.d(this.f15682e, 1.0f, rectF3, this.f15680c);
        this.f15678a.op(this.f15679b, this.f15680c, Path.Op.UNION);
    }

    ShapeAppearanceModel c() {
        return this.f15682e;
    }

    Path d() {
        return this.f15678a;
    }
}
