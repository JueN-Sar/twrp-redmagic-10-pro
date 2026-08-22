package cn.nubia.gameassist.panel.drawable.diplogen;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;

/* loaded from: classes.dex */
public final class DiplogenUtils {
    public static Path a(Path path, RatioPoint[][] ratioPointArr) {
        return b(path, ratioPointArr, true);
    }

    public static Path b(Path path, RatioPoint[][] ratioPointArr, boolean z) {
        RatioPoint[] ratioPointArr2;
        Path path2 = path == null ? new Path() : path;
        RatioPoint ratioPoint = null;
        for (int i2 = 0; i2 < ratioPointArr.length; i2++) {
            RatioPoint[] ratioPointArr3 = ratioPointArr[z ? i2 : (ratioPointArr.length - 1) - i2];
            if (z) {
                ratioPointArr2 = ratioPointArr3;
            } else {
                int length = ratioPointArr3.length;
                RatioPoint[] ratioPointArr4 = new RatioPoint[length];
                for (int i3 = 0; i3 < length; i3++) {
                    ratioPointArr4[i3] = ratioPointArr3[(ratioPointArr3.length - 1) - i2];
                }
                ratioPointArr2 = ratioPointArr4;
            }
            int length2 = ratioPointArr2.length;
            if (length2 != 1) {
                if (length2 == 2 || length2 == 3) {
                    if (ratioPoint != null) {
                        float f2 = ratioPoint.f6972a;
                        float f3 = ratioPoint.f6973b;
                        RatioPoint ratioPoint2 = ratioPointArr2[0];
                        float f4 = ratioPoint2.f6972a;
                        float f5 = ratioPoint2.f6973b;
                        RatioPoint ratioPoint3 = ratioPointArr2[1];
                        path2.cubicTo(f2, f3, f4, f5, ratioPoint3.f6972a, ratioPoint3.f6973b);
                    } else {
                        RatioPoint ratioPoint4 = ratioPointArr2[0];
                        float f6 = ratioPoint4.f6972a;
                        float f7 = ratioPoint4.f6973b;
                        RatioPoint ratioPoint5 = ratioPointArr2[1];
                        path2.quadTo(f6, f7, ratioPoint5.f6972a, ratioPoint5.f6973b);
                    }
                    if (ratioPointArr2.length == 3) {
                        RatioPoint ratioPoint6 = ratioPointArr2[2];
                        ratioPoint = new RatioPoint(ratioPoint6.f6972a, ratioPoint6.f6973b);
                    }
                }
            } else if (i2 == 0) {
                RatioPoint ratioPoint7 = ratioPointArr2[0];
                path2.moveTo(ratioPoint7.f6972a, ratioPoint7.f6973b);
            } else if (ratioPoint != null) {
                float f8 = ratioPoint.f6972a;
                float f9 = ratioPoint.f6973b;
                RatioPoint ratioPoint8 = ratioPointArr2[0];
                path2.quadTo(f8, f9, ratioPoint8.f6972a, ratioPoint8.f6973b);
                ratioPoint = null;
            } else {
                RatioPoint ratioPoint9 = ratioPointArr2[0];
                path2.lineTo(ratioPoint9.f6972a, ratioPoint9.f6973b);
            }
        }
        return path2;
    }

    public static Path c(Path path, RatioPoint[][] ratioPointArr, RatioPoint[][] ratioPointArr2) {
        if (path == null) {
            path = new Path();
        }
        RatioPoint[][] ratioPointArr3 = new RatioPoint[ratioPointArr.length + ratioPointArr2.length][];
        int i2 = 0;
        int i3 = 0;
        while (i2 < ratioPointArr.length) {
            ratioPointArr3[i3] = ratioPointArr[i2];
            i2++;
            i3++;
        }
        int length = ratioPointArr2.length - 1;
        while (length >= 0) {
            RatioPoint[] ratioPointArr4 = ratioPointArr2[length];
            RatioPoint[] ratioPointArr5 = new RatioPoint[ratioPointArr4.length];
            int i4 = i3 + 1;
            ratioPointArr3[i3] = ratioPointArr5;
            int length2 = ratioPointArr4.length - 1;
            int i5 = 0;
            while (length2 >= 0) {
                ratioPointArr5[i5] = ratioPointArr4[length2];
                length2--;
                i5++;
            }
            length--;
            i3 = i4;
        }
        return a(path, ratioPointArr3);
    }

    public static Matrix d(int i2, Matrix matrix, Rect rect) {
        if (i2 == 1) {
            matrix.setRotate(90.0f);
            matrix.postTranslate(rect.width(), 0.0f);
        } else if (i2 == 2) {
            matrix.setScale(-1.0f, 1.0f);
            matrix.postTranslate(rect.width(), 0.0f);
        } else if (i2 == 3) {
            matrix.setRotate(90.0f, rect.height() / 2, rect.height() / 2);
            matrix.preScale(-1.0f, 1.0f, rect.height() / 2, rect.height() / 2);
            matrix.postTranslate(rect.width() - rect.height(), 0.0f);
        }
        return matrix;
    }

    public static float[] e(Path path, int i2, boolean z) {
        PathMeasure pathMeasure = new PathMeasure(path, false);
        int i3 = z ? i2 : i2 + 1;
        float[] fArr = new float[i3 * 2];
        float length = pathMeasure.getLength() / i2;
        float[] fArr2 = new float[2];
        for (int i4 = 0; i4 < i3; i4++) {
            pathMeasure.getPosTan(i4 * length, fArr2, null);
            int i5 = i4 * 2;
            fArr[i5] = fArr2[0];
            fArr[i5 + 1] = fArr2[1];
        }
        return fArr;
    }

    public static RatioPoint[][] f(RatioPoint[][] ratioPointArr, RatioPoint ratioPoint, double d2) {
        int i2;
        RatioPoint[][] ratioPointArr2;
        RatioPoint[] ratioPointArr3;
        int i3;
        int length = ratioPointArr.length;
        RatioPoint[][] ratioPointArr4 = new RatioPoint[length][];
        int i4 = 0;
        while (i4 < length) {
            RatioPoint[] ratioPointArr5 = ratioPointArr[i4];
            if (ratioPointArr5 != null) {
                int length2 = ratioPointArr5.length;
                RatioPoint[] ratioPointArr6 = new RatioPoint[length2];
                ratioPointArr4[i4] = ratioPointArr6;
                int i5 = 0;
                while (i5 < length2) {
                    RatioPoint ratioPoint2 = ratioPointArr5[i5];
                    if (ratioPoint2 != null) {
                        float f2 = ratioPoint2.f6972a - ratioPoint.f6972a;
                        float f3 = ratioPoint2.f6973b - ratioPoint.f6973b;
                        double sin = Math.sin(d2);
                        double cos = Math.cos(d2);
                        ratioPointArr3 = ratioPointArr5;
                        i3 = length2;
                        double d3 = f2;
                        double d4 = f3;
                        i2 = length;
                        ratioPointArr2 = ratioPointArr4;
                        ratioPointArr6[i5] = new RatioPoint(((float) ((d3 * cos) - (d4 * sin))) + ratioPoint.f6972a, ((float) ((d3 * sin) + (d4 * cos))) + ratioPoint.f6973b);
                    } else {
                        i2 = length;
                        ratioPointArr2 = ratioPointArr4;
                        ratioPointArr3 = ratioPointArr5;
                        i3 = length2;
                    }
                    i5++;
                    ratioPointArr5 = ratioPointArr3;
                    length2 = i3;
                    length = i2;
                    ratioPointArr4 = ratioPointArr2;
                }
            }
            i4++;
            length = length;
            ratioPointArr4 = ratioPointArr4;
        }
        return ratioPointArr4;
    }

    public static RatioPoint[][] g(RatioPoint[][] ratioPointArr, RatioPoint ratioPoint, float f2) {
        return f(ratioPointArr, ratioPoint, (f2 * 3.141592653589793d) / 180.0d);
    }

    public static RatioPoint[][] h(RatioPoint[][] ratioPointArr, float f2, float f3) {
        int length = ratioPointArr.length;
        RatioPoint[][] ratioPointArr2 = new RatioPoint[length][];
        for (int i2 = 0; i2 < length; i2++) {
            RatioPoint[] ratioPointArr3 = ratioPointArr[i2];
            if (ratioPointArr3 != null) {
                int length2 = ratioPointArr3.length;
                RatioPoint[] ratioPointArr4 = new RatioPoint[length2];
                ratioPointArr2[i2] = ratioPointArr4;
                for (int i3 = 0; i3 < length2; i3++) {
                    RatioPoint ratioPoint = ratioPointArr3[i3];
                    if (ratioPoint != null) {
                        ratioPointArr4[i3] = new RatioPoint(ratioPoint.f6972a * f2, ratioPoint.f6973b * f3);
                    }
                }
            }
        }
        return ratioPointArr2;
    }

    public static RatioPoint[][] i(RatioPoint[][] ratioPointArr, float f2, float f3) {
        int length = ratioPointArr.length;
        RatioPoint[][] ratioPointArr2 = new RatioPoint[length][];
        for (int i2 = 0; i2 < length; i2++) {
            RatioPoint[] ratioPointArr3 = ratioPointArr[i2];
            if (ratioPointArr3 != null) {
                int length2 = ratioPointArr3.length;
                RatioPoint[] ratioPointArr4 = new RatioPoint[length2];
                ratioPointArr2[i2] = ratioPointArr4;
                for (int i3 = 0; i3 < length2; i3++) {
                    RatioPoint ratioPoint = ratioPointArr3[i3];
                    if (ratioPoint != null) {
                        ratioPointArr4[i3] = new RatioPoint(ratioPoint.f6972a + f2, ratioPoint.f6973b + f3);
                    }
                }
            }
        }
        return ratioPointArr2;
    }
}
