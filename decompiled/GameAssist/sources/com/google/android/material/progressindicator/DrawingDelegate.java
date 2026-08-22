package com.google.android.material.progressindicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import com.google.android.material.progressindicator.BaseProgressIndicatorSpec;

/* loaded from: classes.dex */
abstract class DrawingDelegate<S extends BaseProgressIndicatorSpec> {

    /* renamed from: a, reason: collision with root package name */
    BaseProgressIndicatorSpec f14913a;

    protected static class ActiveIndicator {

        /* renamed from: a, reason: collision with root package name */
        float f14914a;

        /* renamed from: b, reason: collision with root package name */
        float f14915b;

        /* renamed from: c, reason: collision with root package name */
        int f14916c;

        /* renamed from: d, reason: collision with root package name */
        int f14917d;

        protected ActiveIndicator() {
        }
    }

    public DrawingDelegate(BaseProgressIndicatorSpec baseProgressIndicatorSpec) {
        this.f14913a = baseProgressIndicatorSpec;
    }

    abstract void a(Canvas canvas, Rect rect, float f2, boolean z, boolean z2);

    abstract void b(Canvas canvas, Paint paint, int i2, int i3);

    abstract void c(Canvas canvas, Paint paint, ActiveIndicator activeIndicator, int i2);

    abstract void d(Canvas canvas, Paint paint, float f2, float f3, int i2, int i3, int i4);

    abstract int e();

    abstract int f();

    void g(Canvas canvas, Rect rect, float f2, boolean z, boolean z2) {
        this.f14913a.e();
        a(canvas, rect, f2, z, z2);
    }
}
