package com.zte.mifavor.widget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

/* loaded from: classes2.dex */
public class MetaBall {
    private float b(float[] fArr, float[] fArr2) {
        float f2 = fArr[0] - fArr2[0];
        float f3 = fArr[1] - fArr2[1];
        return (float) Math.sqrt((f2 * f2) + (f3 * f3));
    }

    private float c(float[] fArr) {
        float f2 = fArr[0];
        float f3 = fArr[1];
        return (float) Math.sqrt((f2 * f2) + (f3 * f3));
    }

    private float[] d(float f2, float f3) {
        double d2 = f2;
        double d3 = f3;
        return new float[]{(float) (Math.cos(d2) * d3), (float) (Math.sin(d2) * d3)};
    }

    public void a(Canvas canvas, int i2, int i3, int i4, int i5, int i6, int i7, Paint paint, float f2, float f3, float f4) {
        float f5;
        RectF rectF = new RectF();
        float f6 = i2 - i4;
        rectF.left = f6;
        float f7 = i3 - i4;
        rectF.top = f7;
        float f8 = i4 * 2;
        rectF.right = f6 + f8;
        rectF.bottom = f7 + f8;
        RectF rectF2 = new RectF();
        float f9 = i5 - i7;
        rectF2.left = f9;
        float f10 = i6 - i7;
        rectF2.top = f10;
        float f11 = i7 * 2;
        rectF2.right = f9 + f11;
        rectF2.bottom = f10 + f11;
        float[] fArr = {rectF.centerX(), rectF.centerY()};
        float[] fArr2 = {rectF2.centerX(), rectF2.centerY()};
        float b2 = b(fArr, fArr2);
        float width = rectF.width() / 2.0f;
        float width2 = rectF2.width() / 2.0f;
        float f12 = 0.0f;
        if (width == 0.0f || width2 == 0.0f || b2 > f4) {
            return;
        }
        if (b2 <= Math.abs(width - width2)) {
            return;
        }
        float f13 = width + width2;
        if (b2 < f13) {
            float f14 = width * width;
            float f15 = b2 * b2;
            float f16 = width2 * width2;
            float acos = (float) Math.acos(((f14 + f15) - f16) / ((width * 2.0f) * b2));
            float acos2 = (float) Math.acos(((f16 + f15) - f14) / ((width2 * 2.0f) * b2));
            f12 = acos;
            f5 = acos2;
        } else {
            f5 = 0.0f;
        }
        float[] fArr3 = {fArr2[0] - fArr[0], fArr2[1] - fArr[1]};
        float atan2 = (float) Math.atan2(fArr3[1], fArr3[0]);
        float acos3 = (float) Math.acos(r15 / b2);
        float f17 = (acos3 - f12) * f2;
        float f18 = atan2 + f12 + f17;
        float f19 = (atan2 - f12) - f17;
        double d2 = atan2;
        double d3 = f5;
        double d4 = ((3.141592653589793d - d3) - acos3) * f2;
        float f20 = (float) (((d2 + 3.141592653589793d) - d3) - d4);
        float f21 = (float) ((d2 - 3.141592653589793d) + d3 + d4);
        float[] d5 = d(f18, width);
        float[] d6 = d(f19, width);
        float[] d7 = d(f20, width2);
        float[] d8 = d(f21, width2);
        float f22 = d5[0];
        float f23 = fArr[0];
        float f24 = d5[1];
        float f25 = fArr[1];
        float[] fArr4 = {f22 + f23, f24 + f25};
        float[] fArr5 = {d6[0] + f23, d6[1] + f25};
        float f26 = d7[0];
        float f27 = fArr2[0];
        float f28 = d7[1];
        float f29 = fArr2[1];
        float[] fArr6 = {f26 + f27, f28 + f29};
        float[] fArr7 = {d8[0] + f27, d8[1] + f29};
        float min = Math.min(f2 * f3, c(new float[]{fArr4[0] - fArr6[0], fArr4[1] - fArr6[1]}) / f13) * Math.min(1.0f, (b2 * 2.0f) / f13);
        float f30 = width * min;
        float f31 = width2 * min;
        float[] d9 = d(f18 - 1.5707964f, f30);
        float[] d10 = d(f20 + 1.5707964f, f31);
        float[] d11 = d(f21 - 1.5707964f, f31);
        float[] d12 = d(f19 + 1.5707964f, f30);
        float f32 = i3;
        float f33 = i2;
        float degrees = (float) Math.toDegrees(Math.atan(((fArr5[1] - f32) / fArr5[0]) - f33));
        float degrees2 = (float) Math.toDegrees(Math.atan(((fArr7[1] - f32) / fArr7[0]) - f33));
        if (degrees > 180.0f) {
            degrees -= 90.0f;
        }
        if (degrees2 > 180.0f) {
            degrees = degrees2 - 90.0f;
        }
        Path path = new Path();
        path.moveTo(fArr4[0], fArr4[1]);
        float f34 = fArr4[0] + d9[0];
        float f35 = fArr4[1] + d9[1];
        float f36 = fArr6[0];
        float f37 = f36 + d10[0];
        float f38 = fArr6[1];
        path.cubicTo(f34, f35, f37, f38 + d10[1], f36, f38);
        path.lineTo(fArr7[0], fArr7[1]);
        float f39 = fArr7[0] + d11[0];
        float f40 = fArr7[1] + d11[1];
        float f41 = fArr5[0];
        float f42 = f41 + d12[0];
        float f43 = fArr5[1];
        path.cubicTo(f39, f40, f42, f43 + d12[1], f41, f43);
        path.addArc(rectF, degrees - 90.0f, degrees2 - 90.0f);
        path.close();
        canvas.drawPath(path, paint);
    }
}
