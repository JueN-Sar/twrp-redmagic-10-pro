package com.google.android.material.progressindicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import androidx.core.math.MathUtils;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.progressindicator.DrawingDelegate;

/* loaded from: classes.dex */
final class LinearDrawingDelegate extends DrawingDelegate<LinearProgressIndicatorSpec> {

    /* renamed from: b, reason: collision with root package name */
    private float f14920b;

    /* renamed from: c, reason: collision with root package name */
    private float f14921c;

    /* renamed from: d, reason: collision with root package name */
    private float f14922d;

    /* renamed from: e, reason: collision with root package name */
    private boolean f14923e;

    /* renamed from: f, reason: collision with root package name */
    private float f14924f;

    LinearDrawingDelegate(LinearProgressIndicatorSpec linearProgressIndicatorSpec) {
        super(linearProgressIndicatorSpec);
        this.f14920b = 300.0f;
    }

    private void h(Canvas canvas, Paint paint, float f2, float f3, int i2, int i3, int i4) {
        float a2 = MathUtils.a(f2, 0.0f, 1.0f);
        float a3 = MathUtils.a(f3, 0.0f, 1.0f);
        float d2 = com.google.android.material.math.MathUtils.d(1.0f - this.f14924f, 1.0f, a2);
        float d3 = com.google.android.material.math.MathUtils.d(1.0f - this.f14924f, 1.0f, a3);
        int a4 = (int) ((i3 * MathUtils.a(d2, 0.0f, 0.01f)) / 0.01f);
        int a5 = (int) ((i4 * (1.0f - MathUtils.a(d3, 0.99f, 1.0f))) / 0.01f);
        float f4 = this.f14920b;
        int i5 = (int) ((d2 * f4) + a4);
        int i6 = (int) ((d3 * f4) - a5);
        float f5 = (-f4) / 2.0f;
        if (i5 <= i6) {
            float f6 = this.f14922d;
            float f7 = i5 + f6;
            float f8 = i6 - f6;
            float f9 = f6 * 2.0f;
            paint.setColor(i2);
            paint.setAntiAlias(true);
            paint.setStrokeWidth(this.f14921c);
            if (f7 >= f8) {
                j(canvas, paint, new PointF(f7 + f5, 0.0f), new PointF(f8 + f5, 0.0f), f9, this.f14921c);
                return;
            }
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeCap(this.f14923e ? Paint.Cap.ROUND : Paint.Cap.BUTT);
            float f10 = f7 + f5;
            float f11 = f8 + f5;
            canvas.drawLine(f10, 0.0f, f11, 0.0f, paint);
            if (this.f14923e || this.f14922d <= 0.0f) {
                return;
            }
            paint.setStyle(Paint.Style.FILL);
            if (f7 > 0.0f) {
                i(canvas, paint, new PointF(f10, 0.0f), f9, this.f14921c);
            }
            if (f8 < this.f14920b) {
                i(canvas, paint, new PointF(f11, 0.0f), f9, this.f14921c);
            }
        }
    }

    private void i(Canvas canvas, Paint paint, PointF pointF, float f2, float f3) {
        j(canvas, paint, pointF, null, f2, f3);
    }

    private void j(Canvas canvas, Paint paint, PointF pointF, PointF pointF2, float f2, float f3) {
        float min = Math.min(f3, this.f14921c);
        float f4 = f2 / 2.0f;
        float min2 = Math.min(f4, (this.f14922d * min) / this.f14921c);
        RectF rectF = new RectF((-f2) / 2.0f, (-min) / 2.0f, f4, min / 2.0f);
        paint.setStyle(Paint.Style.FILL);
        canvas.save();
        if (pointF2 != null) {
            canvas.translate(pointF2.x, pointF2.y);
            Path path = new Path();
            path.addRoundRect(rectF, min2, min2, Path.Direction.CCW);
            canvas.clipPath(path);
            canvas.translate(-pointF2.x, -pointF2.y);
        }
        canvas.translate(pointF.x, pointF.y);
        canvas.drawRoundRect(rectF, min2, min2, paint);
        canvas.restore();
    }

    @Override // com.google.android.material.progressindicator.DrawingDelegate
    void a(Canvas canvas, Rect rect, float f2, boolean z, boolean z2) {
        this.f14920b = rect.width();
        float f3 = ((LinearProgressIndicatorSpec) this.f14913a).f14868a;
        canvas.translate(rect.left + (rect.width() / 2.0f), rect.top + (rect.height() / 2.0f) + Math.max(0.0f, (rect.height() - f3) / 2.0f));
        if (((LinearProgressIndicatorSpec) this.f14913a).f14948j) {
            canvas.scale(-1.0f, 1.0f);
        }
        float f4 = this.f14920b / 2.0f;
        float f5 = f3 / 2.0f;
        canvas.clipRect(-f4, -f5, f4, f5);
        BaseProgressIndicatorSpec baseProgressIndicatorSpec = this.f14913a;
        this.f14923e = ((LinearProgressIndicatorSpec) baseProgressIndicatorSpec).f14868a / 2 == ((LinearProgressIndicatorSpec) baseProgressIndicatorSpec).f14869b;
        this.f14921c = ((LinearProgressIndicatorSpec) baseProgressIndicatorSpec).f14868a * f2;
        this.f14922d = Math.min(((LinearProgressIndicatorSpec) baseProgressIndicatorSpec).f14868a / 2, ((LinearProgressIndicatorSpec) baseProgressIndicatorSpec).f14869b) * f2;
        if (z || z2) {
            if ((z && ((LinearProgressIndicatorSpec) this.f14913a).f14872e == 2) || (z2 && ((LinearProgressIndicatorSpec) this.f14913a).f14873f == 1)) {
                canvas.scale(1.0f, -1.0f);
            }
            if (z || (z2 && ((LinearProgressIndicatorSpec) this.f14913a).f14873f != 3)) {
                canvas.translate(0.0f, (((LinearProgressIndicatorSpec) this.f14913a).f14868a * (1.0f - f2)) / 2.0f);
            }
        }
        if (z2 && ((LinearProgressIndicatorSpec) this.f14913a).f14873f == 3) {
            this.f14924f = f2;
        } else {
            this.f14924f = 1.0f;
        }
    }

    @Override // com.google.android.material.progressindicator.DrawingDelegate
    void b(Canvas canvas, Paint paint, int i2, int i3) {
        int a2 = MaterialColors.a(i2, i3);
        if (((LinearProgressIndicatorSpec) this.f14913a).f14949k <= 0 || a2 == 0) {
            return;
        }
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(a2);
        PointF pointF = new PointF((this.f14920b / 2.0f) - (this.f14921c / 2.0f), 0.0f);
        BaseProgressIndicatorSpec baseProgressIndicatorSpec = this.f14913a;
        i(canvas, paint, pointF, ((LinearProgressIndicatorSpec) baseProgressIndicatorSpec).f14949k, ((LinearProgressIndicatorSpec) baseProgressIndicatorSpec).f14949k);
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
        return ((LinearProgressIndicatorSpec) this.f14913a).f14868a;
    }

    @Override // com.google.android.material.progressindicator.DrawingDelegate
    int f() {
        return -1;
    }
}
