package com.airbnb.lottie.model;

import android.graphics.PointF;
import androidx.annotation.RestrictTo;

@RestrictTo
/* loaded from: classes.dex */
public class CubicCurveData {

    /* renamed from: a, reason: collision with root package name */
    private final PointF f9586a;

    /* renamed from: b, reason: collision with root package name */
    private final PointF f9587b;

    /* renamed from: c, reason: collision with root package name */
    private final PointF f9588c;

    public CubicCurveData() {
        this.f9586a = new PointF();
        this.f9587b = new PointF();
        this.f9588c = new PointF();
    }

    public PointF a() {
        return this.f9586a;
    }

    public PointF b() {
        return this.f9587b;
    }

    public PointF c() {
        return this.f9588c;
    }

    public void d(float f2, float f3) {
        this.f9586a.set(f2, f3);
    }

    public void e(float f2, float f3) {
        this.f9587b.set(f2, f3);
    }

    public void f(float f2, float f3) {
        this.f9588c.set(f2, f3);
    }

    public String toString() {
        return String.format("v=%.2f,%.2f cp1=%.2f,%.2f cp2=%.2f,%.2f", Float.valueOf(this.f9588c.x), Float.valueOf(this.f9588c.y), Float.valueOf(this.f9586a.x), Float.valueOf(this.f9586a.y), Float.valueOf(this.f9587b.x), Float.valueOf(this.f9587b.y));
    }

    public CubicCurveData(PointF pointF, PointF pointF2, PointF pointF3) {
        this.f9586a = pointF;
        this.f9587b = pointF2;
        this.f9588c = pointF3;
    }
}
