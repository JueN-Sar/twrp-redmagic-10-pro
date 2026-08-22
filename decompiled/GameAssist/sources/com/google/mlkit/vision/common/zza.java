package com.google.mlkit.vision.common;

/* loaded from: classes.dex */
final class zza extends PointF3D {

    /* renamed from: a, reason: collision with root package name */
    private final float f16086a;

    /* renamed from: b, reason: collision with root package name */
    private final float f16087b;

    /* renamed from: c, reason: collision with root package name */
    private final float f16088c;

    @Override // com.google.mlkit.vision.common.PointF3D
    public final float a() {
        return this.f16086a;
    }

    @Override // com.google.mlkit.vision.common.PointF3D
    public final float b() {
        return this.f16087b;
    }

    @Override // com.google.mlkit.vision.common.PointF3D
    public final float c() {
        return this.f16088c;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof PointF3D) {
            PointF3D pointF3D = (PointF3D) obj;
            if (Float.floatToIntBits(this.f16086a) == Float.floatToIntBits(pointF3D.a()) && Float.floatToIntBits(this.f16087b) == Float.floatToIntBits(pointF3D.b()) && Float.floatToIntBits(this.f16088c) == Float.floatToIntBits(pointF3D.c())) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Float.floatToIntBits(this.f16088c) ^ ((((Float.floatToIntBits(this.f16086a) ^ 1000003) * 1000003) ^ Float.floatToIntBits(this.f16087b)) * 1000003);
    }

    public final String toString() {
        return "PointF3D{x=" + this.f16086a + ", y=" + this.f16087b + ", z=" + this.f16088c + "}";
    }
}
