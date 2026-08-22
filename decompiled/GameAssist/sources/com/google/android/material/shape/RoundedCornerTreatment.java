package com.google.android.material.shape;

/* loaded from: classes.dex */
public class RoundedCornerTreatment extends CornerTreatment {

    /* renamed from: a, reason: collision with root package name */
    float f15132a = -1.0f;

    @Override // com.google.android.material.shape.CornerTreatment
    public void b(ShapePath shapePath, float f2, float f3, float f4) {
        shapePath.p(0.0f, f4 * f3, 180.0f, 180.0f - f2);
        float f5 = f4 * 2.0f * f3;
        shapePath.a(0.0f, 0.0f, f5, f5, 180.0f, f2);
    }
}
