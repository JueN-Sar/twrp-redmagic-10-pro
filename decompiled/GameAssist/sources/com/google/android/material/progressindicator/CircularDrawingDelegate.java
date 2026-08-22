package com.google.android.material.progressindicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.math.MathUtils;
import com.google.android.material.progressindicator.DrawingDelegate;

/* loaded from: classes.dex */
final class CircularDrawingDelegate extends DrawingDelegate<CircularProgressIndicatorSpec> {

    /* renamed from: b, reason: collision with root package name */
    private float f14875b;

    /* renamed from: c, reason: collision with root package name */
    private float f14876c;

    /* renamed from: d, reason: collision with root package name */
    private float f14877d;

    /* renamed from: e, reason: collision with root package name */
    private boolean f14878e;

    /* renamed from: f, reason: collision with root package name */
    private float f14879f;

    CircularDrawingDelegate(CircularProgressIndicatorSpec circularProgressIndicatorSpec) {
        super(circularProgressIndicatorSpec);
    }

    private void h(Canvas canvas, Paint paint, float f2, float f3, int i2, int i3, int i4) {
        float f4 = f3 >= f2 ? f3 - f2 : (f3 + 1.0f) - f2;
        float f5 = f2 % 1.0f;
        if (this.f14879f < 1.0f) {
            float f6 = f5 + f4;
            if (f6 > 1.0f) {
                h(canvas, paint, f5, 1.0f, i2, i3, 0);
                h(canvas, paint, 1.0f, f6, i2, 0, i4);
                return;
            }
        }
        float degrees = (float) Math.toDegrees(this.f14876c / this.f14877d);
        if (f5 == 0.0f && f4 >= 0.99f) {
            f4 += ((f4 - 0.99f) * ((degrees * 2.0f) / 360.0f)) / 0.01f;
        }
        float d2 = MathUtils.d(1.0f - this.f14879f, 1.0f, f5);
        float d3 = MathUtils.d(0.0f, this.f14879f, f4);
        float degrees2 = (float) Math.toDegrees(i3 / this.f14877d);
        float degrees3 = ((d3 * 360.0f) - degrees2) - ((float) Math.toDegrees(i4 / this.f14877d));
        float f7 = (d2 * 360.0f) + degrees2;
        if (degrees3 <= 0.0f) {
            return;
        }
        paint.setAntiAlias(true);
        paint.setColor(i2);
        paint.setStrokeWidth(this.f14875b);
        float f8 = degrees * 2.0f;
        if (degrees3 < f8) {
            float f9 = degrees3 / f8;
            paint.setStyle(Paint.Style.FILL);
            j(canvas, paint, f7 + (degrees * f9), this.f14876c * 2.0f, this.f14875b, f9);
            return;
        }
        float f10 = this.f14877d;
        RectF rectF = new RectF(-f10, -f10, f10, f10);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(this.f14878e ? Paint.Cap.ROUND : Paint.Cap.BUTT);
        float f11 = f7 + degrees;
        canvas.drawArc(rectF, f11, degrees3 - f8, false, paint);
        if (this.f14878e || this.f14876c <= 0.0f) {
            return;
        }
        paint.setStyle(Paint.Style.FILL);
        i(canvas, paint, f11, this.f14876c * 2.0f, this.f14875b);
        i(canvas, paint, (f7 + degrees3) - degrees, this.f14876c * 2.0f, this.f14875b);
    }

    private void i(Canvas canvas, Paint paint, float f2, float f3, float f4) {
        j(canvas, paint, f2, f3, f4, 1.0f);
    }

    private void j(Canvas canvas, Paint paint, float f2, float f3, float f4, float f5) {
        float min = (int) Math.min(f4, this.f14875b);
        float f6 = f3 / 2.0f;
        float min2 = Math.min(f6, (this.f14876c * min) / this.f14875b);
        RectF rectF = new RectF((-min) / 2.0f, (-f3) / 2.0f, min / 2.0f, f6);
        canvas.save();
        double d2 = f2;
        canvas.translate((float) (this.f14877d * Math.cos(Math.toRadians(d2))), (float) (this.f14877d * Math.sin(Math.toRadians(d2))));
        canvas.rotate(f2);
        canvas.scale(f5, f5);
        canvas.drawRoundRect(rectF, min2, min2, paint);
        canvas.restore();
    }

    private int k() {
        BaseProgressIndicatorSpec baseProgressIndicatorSpec = this.f14913a;
        return ((CircularProgressIndicatorSpec) baseProgressIndicatorSpec).f14895h + (((CircularProgressIndicatorSpec) baseProgressIndicatorSpec).f14896i * 2);
    }

    @Override // com.google.android.material.progressindicator.DrawingDelegate
    void a(Canvas canvas, Rect rect, float f2, boolean z, boolean z2) {
        float width = rect.width() / f();
        float height = rect.height() / e();
        BaseProgressIndicatorSpec baseProgressIndicatorSpec = this.f14913a;
        float f3 = (((CircularProgressIndicatorSpec) baseProgressIndicatorSpec).f14895h / 2.0f) + ((CircularProgressIndicatorSpec) baseProgressIndicatorSpec).f14896i;
        canvas.translate((f3 * width) + rect.left, (f3 * height) + rect.top);
        canvas.rotate(-90.0f);
        canvas.scale(width, height);
        if (((CircularProgressIndicatorSpec) this.f14913a).f14897j != 0) {
            canvas.scale(1.0f, -1.0f);
        }
        float f4 = -f3;
        canvas.clipRect(f4, f4, f3, f3);
        BaseProgressIndicatorSpec baseProgressIndicatorSpec2 = this.f14913a;
        this.f14878e = ((CircularProgressIndicatorSpec) baseProgressIndicatorSpec2).f14868a / 2 <= ((CircularProgressIndicatorSpec) baseProgressIndicatorSpec2).f14869b;
        this.f14875b = ((CircularProgressIndicatorSpec) baseProgressIndicatorSpec2).f14868a * f2;
        this.f14876c = Math.min(((CircularProgressIndicatorSpec) baseProgressIndicatorSpec2).f14868a / 2, ((CircularProgressIndicatorSpec) baseProgressIndicatorSpec2).f14869b) * f2;
        BaseProgressIndicatorSpec baseProgressIndicatorSpec3 = this.f14913a;
        float f5 = (((CircularProgressIndicatorSpec) baseProgressIndicatorSpec3).f14895h - ((CircularProgressIndicatorSpec) baseProgressIndicatorSpec3).f14868a) / 2.0f;
        this.f14877d = f5;
        if (z || z2) {
            if ((z && ((CircularProgressIndicatorSpec) baseProgressIndicatorSpec3).f14872e == 2) || (z2 && ((CircularProgressIndicatorSpec) baseProgressIndicatorSpec3).f14873f == 1)) {
                this.f14877d = f5 + (((1.0f - f2) * ((CircularProgressIndicatorSpec) baseProgressIndicatorSpec3).f14868a) / 2.0f);
            } else if ((z && ((CircularProgressIndicatorSpec) baseProgressIndicatorSpec3).f14872e == 1) || (z2 && ((CircularProgressIndicatorSpec) baseProgressIndicatorSpec3).f14873f == 2)) {
                this.f14877d = f5 - (((1.0f - f2) * ((CircularProgressIndicatorSpec) baseProgressIndicatorSpec3).f14868a) / 2.0f);
            }
        }
        if (z2 && ((CircularProgressIndicatorSpec) baseProgressIndicatorSpec3).f14873f == 3) {
            this.f14879f = f2;
        } else {
            this.f14879f = 1.0f;
        }
    }

    @Override // com.google.android.material.progressindicator.DrawingDelegate
    void b(Canvas canvas, Paint paint, int i2, int i3) {
    }

    @Override // com.google.android.material.progressindicator.DrawingDelegate
    void c(Canvas canvas, Paint paint, DrawingDelegate.ActiveIndicator activeIndicator, int i2) {
        int a2 = MaterialColors.a(activeIndicator.f14916c, i2);
        float f2 = activeIndicator.f14914a;
        float f3 = activeIndicator.f14915b;
        int i3 = activeIndicator.f14917d;
        h(canvas, paint, f2, f3, a2, i3, i3);
    }

    @Override // com.google.android.material.progressindicator.DrawingDelegate
    void d(Canvas canvas, Paint paint, float f2, float f3, int i2, int i3, int i4) {
        h(canvas, paint, f2, f3, MaterialColors.a(i2, i3), i4, i4);
    }

    @Override // com.google.android.material.progressindicator.DrawingDelegate
    int e() {
        return k();
    }

    @Override // com.google.android.material.progressindicator.DrawingDelegate
    int f() {
        return k();
    }
}
