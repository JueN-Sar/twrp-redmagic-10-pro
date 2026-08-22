package com.google.android.material.shape;

/* loaded from: classes.dex */
public class TriangleEdgeTreatment extends EdgeTreatment {

    /* renamed from: c, reason: collision with root package name */
    private final float f15227c;

    /* renamed from: h, reason: collision with root package name */
    private final boolean f15228h;

    @Override // com.google.android.material.shape.EdgeTreatment
    public void b(float f2, float f3, float f4, ShapePath shapePath) {
        if (!this.f15228h) {
            float f5 = this.f15227c;
            shapePath.n(f3 - (f5 * f4), 0.0f, f3, (-f5) * f4);
            shapePath.n(f3 + (this.f15227c * f4), 0.0f, f2, 0.0f);
        } else {
            shapePath.m(f3 - (this.f15227c * f4), 0.0f);
            float f6 = this.f15227c;
            shapePath.n(f3, f6 * f4, (f6 * f4) + f3, 0.0f);
            shapePath.m(f2, 0.0f);
        }
    }
}
