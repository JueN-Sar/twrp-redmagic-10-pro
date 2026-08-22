package com.google.android.material.shape;

/* loaded from: classes.dex */
public final class OffsetEdgeTreatment extends EdgeTreatment {

    /* renamed from: c, reason: collision with root package name */
    private final EdgeTreatment f15129c;

    /* renamed from: h, reason: collision with root package name */
    private final float f15130h;

    public OffsetEdgeTreatment(EdgeTreatment edgeTreatment, float f2) {
        this.f15129c = edgeTreatment;
        this.f15130h = f2;
    }

    @Override // com.google.android.material.shape.EdgeTreatment
    boolean a() {
        return this.f15129c.a();
    }

    @Override // com.google.android.material.shape.EdgeTreatment
    public void b(float f2, float f3, float f4, ShapePath shapePath) {
        this.f15129c.b(f2, f3 - this.f15130h, f4, shapePath);
    }
}
