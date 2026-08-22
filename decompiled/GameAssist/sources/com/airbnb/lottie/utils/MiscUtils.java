package com.airbnb.lottie.utils;

import android.graphics.Path;
import android.graphics.PointF;
import com.airbnb.lottie.animation.content.KeyPathElementContent;
import com.airbnb.lottie.model.CubicCurveData;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.content.ShapeData;
import java.util.List;

/* loaded from: classes.dex */
public class MiscUtils {

    /* renamed from: a, reason: collision with root package name */
    private static final PointF f9934a = new PointF();

    public static PointF a(PointF pointF, PointF pointF2) {
        return new PointF(pointF.x + pointF2.x, pointF.y + pointF2.y);
    }

    public static float b(float f2, float f3, float f4) {
        return Math.max(f3, Math.min(f4, f2));
    }

    public static int c(int i2, int i3, int i4) {
        return Math.max(i3, Math.min(i4, i2));
    }

    public static boolean d(float f2, float f3, float f4) {
        return f2 >= f3 && f2 <= f4;
    }

    private static int e(int i2, int i3) {
        int i4 = i2 / i3;
        return (((i2 ^ i3) >= 0) || i2 % i3 == 0) ? i4 : i4 - 1;
    }

    static int f(float f2, float f3) {
        return g((int) f2, (int) f3);
    }

    private static int g(int i2, int i3) {
        return i2 - (i3 * e(i2, i3));
    }

    public static void h(ShapeData shapeData, Path path) {
        path.reset();
        PointF b2 = shapeData.b();
        path.moveTo(b2.x, b2.y);
        f9934a.set(b2.x, b2.y);
        for (int i2 = 0; i2 < shapeData.a().size(); i2++) {
            CubicCurveData cubicCurveData = (CubicCurveData) shapeData.a().get(i2);
            PointF a2 = cubicCurveData.a();
            PointF b3 = cubicCurveData.b();
            PointF c2 = cubicCurveData.c();
            PointF pointF = f9934a;
            if (a2.equals(pointF) && b3.equals(c2)) {
                path.lineTo(c2.x, c2.y);
            } else {
                path.cubicTo(a2.x, a2.y, b3.x, b3.y, c2.x, c2.y);
            }
            pointF.set(c2.x, c2.y);
        }
        if (shapeData.d()) {
            path.close();
        }
    }

    public static float i(float f2, float f3, float f4) {
        return f2 + (f4 * (f3 - f2));
    }

    public static int j(int i2, int i3, float f2) {
        return (int) (i2 + (f2 * (i3 - i2)));
    }

    public static void k(KeyPath keyPath, int i2, List list, KeyPath keyPath2, KeyPathElementContent keyPathElementContent) {
        if (keyPath.c(keyPathElementContent.getName(), i2)) {
            list.add(keyPath2.a(keyPathElementContent.getName()).i(keyPathElementContent));
        }
    }
}
