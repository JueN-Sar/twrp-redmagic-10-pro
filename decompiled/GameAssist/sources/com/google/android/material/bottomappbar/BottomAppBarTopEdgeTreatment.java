package com.google.android.material.bottomappbar;

import com.google.android.material.shape.EdgeTreatment;
import com.google.android.material.shape.ShapePath;

/* loaded from: classes.dex */
public class BottomAppBarTopEdgeTreatment extends EdgeTreatment implements Cloneable {

    /* renamed from: c, reason: collision with root package name */
    private float f14008c;

    /* renamed from: h, reason: collision with root package name */
    private float f14009h;

    /* renamed from: i, reason: collision with root package name */
    private float f14010i;

    /* renamed from: j, reason: collision with root package name */
    private float f14011j;

    /* renamed from: k, reason: collision with root package name */
    private float f14012k;

    /* renamed from: l, reason: collision with root package name */
    private float f14013l = -1.0f;

    public BottomAppBarTopEdgeTreatment(float f2, float f3, float f4) {
        this.f14009h = f2;
        this.f14008c = f3;
        j(f4);
        this.f14012k = 0.0f;
    }

    @Override // com.google.android.material.shape.EdgeTreatment
    public void b(float f2, float f3, float f4, ShapePath shapePath) {
        float f5;
        float f6;
        float f7 = this.f14010i;
        if (f7 == 0.0f) {
            shapePath.m(f2, 0.0f);
            return;
        }
        float f8 = ((this.f14009h * 2.0f) + f7) / 2.0f;
        float f9 = f4 * this.f14008c;
        float f10 = f3 + this.f14012k;
        float f11 = (this.f14011j * f4) + ((1.0f - f4) * f8);
        if (f11 / f8 >= 1.0f) {
            shapePath.m(f2, 0.0f);
            return;
        }
        float f12 = this.f14013l;
        float f13 = f12 * f4;
        boolean z = f12 == -1.0f || Math.abs((f12 * 2.0f) - f7) < 0.1f;
        if (z) {
            f5 = f11;
            f6 = 0.0f;
        } else {
            f6 = 1.75f;
            f5 = 0.0f;
        }
        float f14 = f8 + f9;
        float f15 = f5 + f9;
        float sqrt = (float) Math.sqrt((f14 * f14) - (f15 * f15));
        float f16 = f10 - sqrt;
        float f17 = f10 + sqrt;
        float degrees = (float) Math.toDegrees(Math.atan(sqrt / f15));
        float f18 = (90.0f - degrees) + f6;
        shapePath.m(f16, 0.0f);
        float f19 = f9 * 2.0f;
        shapePath.a(f16 - f9, 0.0f, f16 + f9, f19, 270.0f, degrees);
        if (z) {
            shapePath.a(f10 - f8, (-f8) - f5, f10 + f8, f8 - f5, 180.0f - f18, (f18 * 2.0f) - 180.0f);
        } else {
            float f20 = this.f14009h;
            float f21 = f13 * 2.0f;
            float f22 = f10 - f8;
            shapePath.a(f22, -(f13 + f20), f22 + f20 + f21, f20 + f13, 180.0f - f18, ((f18 * 2.0f) - 180.0f) / 2.0f);
            float f23 = f10 + f8;
            float f24 = this.f14009h;
            shapePath.m(f23 - ((f24 / 2.0f) + f13), f24 + f13);
            float f25 = this.f14009h;
            shapePath.a(f23 - (f21 + f25), -(f13 + f25), f23, f25 + f13, 90.0f, f18 - 90.0f);
        }
        shapePath.a(f17 - f9, 0.0f, f17 + f9, f19, 270.0f - degrees, degrees);
        shapePath.m(f2, 0.0f);
    }

    float c() {
        return this.f14011j;
    }

    public float e() {
        return this.f14013l;
    }

    float f() {
        return this.f14009h;
    }

    float g() {
        return this.f14008c;
    }

    public float h() {
        return this.f14010i;
    }

    public float i() {
        return this.f14012k;
    }

    void j(float f2) {
        if (f2 < 0.0f) {
            throw new IllegalArgumentException("cradleVerticalOffset must be positive.");
        }
        this.f14011j = f2;
    }

    public void k(float f2) {
        this.f14013l = f2;
    }

    void l(float f2) {
        this.f14009h = f2;
    }

    void m(float f2) {
        this.f14008c = f2;
    }

    public void n(float f2) {
        this.f14010i = f2;
    }

    void o(float f2) {
        this.f14012k = f2;
    }
}
