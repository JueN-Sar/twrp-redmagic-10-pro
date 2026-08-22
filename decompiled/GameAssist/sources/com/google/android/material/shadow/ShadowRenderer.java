package com.google.android.material.shadow;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Shader;
import androidx.annotation.RestrictTo;
import androidx.core.graphics.ColorUtils;

@RestrictTo
/* loaded from: classes.dex */
public class ShadowRenderer {

    /* renamed from: i, reason: collision with root package name */
    private static final int[] f15070i = new int[3];

    /* renamed from: j, reason: collision with root package name */
    private static final float[] f15071j = {0.0f, 0.5f, 1.0f};

    /* renamed from: k, reason: collision with root package name */
    private static final int[] f15072k = new int[4];

    /* renamed from: l, reason: collision with root package name */
    private static final float[] f15073l = {0.0f, 0.0f, 0.5f, 1.0f};

    /* renamed from: a, reason: collision with root package name */
    private final Paint f15074a;

    /* renamed from: b, reason: collision with root package name */
    private final Paint f15075b;

    /* renamed from: c, reason: collision with root package name */
    private final Paint f15076c;

    /* renamed from: d, reason: collision with root package name */
    private int f15077d;

    /* renamed from: e, reason: collision with root package name */
    private int f15078e;

    /* renamed from: f, reason: collision with root package name */
    private int f15079f;

    /* renamed from: g, reason: collision with root package name */
    private final Path f15080g;

    /* renamed from: h, reason: collision with root package name */
    private final Paint f15081h;

    public ShadowRenderer() {
        this(-16777216);
    }

    public void a(Canvas canvas, Matrix matrix, RectF rectF, int i2, float f2, float f3) {
        boolean z = f3 < 0.0f;
        Path path = this.f15080g;
        if (z) {
            int[] iArr = f15072k;
            iArr[0] = 0;
            iArr[1] = this.f15079f;
            iArr[2] = this.f15078e;
            iArr[3] = this.f15077d;
        } else {
            path.rewind();
            path.moveTo(rectF.centerX(), rectF.centerY());
            path.arcTo(rectF, f2, f3);
            path.close();
            float f4 = -i2;
            rectF.inset(f4, f4);
            int[] iArr2 = f15072k;
            iArr2[0] = 0;
            iArr2[1] = this.f15077d;
            iArr2[2] = this.f15078e;
            iArr2[3] = this.f15079f;
        }
        float width = rectF.width() / 2.0f;
        if (width <= 0.0f) {
            return;
        }
        float f5 = 1.0f - (i2 / width);
        float[] fArr = f15073l;
        fArr[1] = f5;
        fArr[2] = ((1.0f - f5) / 2.0f) + f5;
        this.f15075b.setShader(new RadialGradient(rectF.centerX(), rectF.centerY(), width, f15072k, fArr, Shader.TileMode.CLAMP));
        canvas.save();
        canvas.concat(matrix);
        canvas.scale(1.0f, rectF.height() / rectF.width());
        if (!z) {
            canvas.clipPath(path, Region.Op.DIFFERENCE);
            canvas.drawPath(path, this.f15081h);
        }
        canvas.drawArc(rectF, f2, f3, true, this.f15075b);
        canvas.restore();
    }

    public void b(Canvas canvas, Matrix matrix, RectF rectF, int i2) {
        rectF.bottom += i2;
        rectF.offset(0.0f, -i2);
        int[] iArr = f15070i;
        iArr[0] = this.f15079f;
        iArr[1] = this.f15078e;
        iArr[2] = this.f15077d;
        Paint paint = this.f15076c;
        float f2 = rectF.left;
        paint.setShader(new LinearGradient(f2, rectF.top, f2, rectF.bottom, iArr, f15071j, Shader.TileMode.CLAMP));
        canvas.save();
        canvas.concat(matrix);
        canvas.drawRect(rectF, this.f15076c);
        canvas.restore();
    }

    public void c(Canvas canvas, Matrix matrix, RectF rectF, int i2, float f2, float f3, float[] fArr) {
        if (f3 > 0.0f) {
            f2 += f3;
            f3 = -f3;
        }
        a(canvas, matrix, rectF, i2, f2, f3);
        Path path = this.f15080g;
        path.rewind();
        path.moveTo(fArr[0], fArr[1]);
        path.arcTo(rectF, f2, f3);
        path.close();
        canvas.save();
        canvas.concat(matrix);
        canvas.scale(1.0f, rectF.height() / rectF.width());
        canvas.drawPath(path, this.f15081h);
        canvas.drawPath(path, this.f15074a);
        canvas.restore();
    }

    public Paint d() {
        return this.f15074a;
    }

    public void e(int i2) {
        this.f15077d = ColorUtils.k(i2, 68);
        this.f15078e = ColorUtils.k(i2, 20);
        this.f15079f = ColorUtils.k(i2, 0);
        this.f15074a.setColor(this.f15077d);
    }

    public ShadowRenderer(int i2) {
        this.f15080g = new Path();
        Paint paint = new Paint();
        this.f15081h = paint;
        this.f15074a = new Paint();
        e(i2);
        paint.setColor(0);
        Paint paint2 = new Paint(4);
        this.f15075b = paint2;
        paint2.setStyle(Paint.Style.FILL);
        this.f15076c = new Paint(paint2);
    }
}
