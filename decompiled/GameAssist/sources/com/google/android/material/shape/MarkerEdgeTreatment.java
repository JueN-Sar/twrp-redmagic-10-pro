package com.google.android.material.shape;

/* loaded from: classes.dex */
public final class MarkerEdgeTreatment extends EdgeTreatment {

    /* renamed from: c, reason: collision with root package name */
    private final float f15093c;

    public MarkerEdgeTreatment(float f2) {
        this.f15093c = f2 - 0.001f;
    }

    @Override // com.google.android.material.shape.EdgeTreatment
    boolean a() {
        return true;
    }

    @Override // com.google.android.material.shape.EdgeTreatment
    public void b(float f2, float f3, float f4, ShapePath shapePath) {
        float sqrt = (float) ((this.f15093c * Math.sqrt(2.0d)) / 2.0d);
        float sqrt2 = (float) Math.sqrt(Math.pow(this.f15093c, 2.0d) - Math.pow(sqrt, 2.0d));
        shapePath.o(f3 - sqrt, ((float) (-((this.f15093c * Math.sqrt(2.0d)) - this.f15093c))) + sqrt2);
        shapePath.m(f3, (float) (-((this.f15093c * Math.sqrt(2.0d)) - this.f15093c)));
        shapePath.m(f3 + sqrt, ((float) (-((this.f15093c * Math.sqrt(2.0d)) - this.f15093c))) + sqrt2);
    }
}
