package com.google.android.material.shape;

/* loaded from: classes.dex */
public class CutCornerTreatment extends CornerTreatment {

    /* renamed from: a, reason: collision with root package name */
    float f15086a = -1.0f;

    @Override // com.google.android.material.shape.CornerTreatment
    public void b(ShapePath shapePath, float f2, float f3, float f4) {
        shapePath.p(0.0f, f4 * f3, 180.0f, 180.0f - f2);
        double d2 = f4;
        double d3 = f3;
        shapePath.m((float) (Math.sin(Math.toRadians(f2)) * d2 * d3), (float) (Math.sin(Math.toRadians(90.0f - f2)) * d2 * d3));
    }
}
