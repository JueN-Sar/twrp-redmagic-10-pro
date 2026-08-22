package com.google.android.material.shape;

import android.graphics.RectF;

/* loaded from: classes.dex */
public class CornerTreatment {
    public void a(float f2, float f3, ShapePath shapePath) {
    }

    public void b(ShapePath shapePath, float f2, float f3, float f4) {
        a(f2, f3, shapePath);
    }

    public void c(ShapePath shapePath, float f2, float f3, RectF rectF, CornerSize cornerSize) {
        b(shapePath, f2, f3, cornerSize.a(rectF));
    }
}
