package com.google.mlkit.vision.text.pipeline;

import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbaaj;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbpb;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
final class zbf {
    static Rect a(List list, Matrix matrix) {
        Iterator it = list.iterator();
        int i2 = Integer.MIN_VALUE;
        int i3 = Integer.MAX_VALUE;
        int i4 = Integer.MAX_VALUE;
        int i5 = Integer.MIN_VALUE;
        while (it.hasNext()) {
            Point point = (Point) it.next();
            i3 = Math.min(i3, point.x);
            i2 = Math.max(i2, point.x);
            i4 = Math.min(i4, point.y);
            i5 = Math.max(i5, point.y);
        }
        RectF rectF = new RectF(i3, i4, i2, i5);
        if (matrix != null) {
            matrix.mapRect(rectF);
        }
        Rect rect = new Rect();
        rectF.round(rect);
        return rect;
    }

    static zbpb b(zbaaj zbaajVar) {
        return zbaajVar.L() ? zbaajVar.F().H() : zbaajVar.E() ? zbaajVar.I().E() : zbaajVar.H();
    }

    static List c(zbpb zbpbVar) {
        double sin = Math.sin(Math.toRadians(zbpbVar.E()));
        double cos = Math.cos(Math.toRadians(zbpbVar.E()));
        Point point = new Point((int) (zbpbVar.H() + (zbpbVar.J() * cos)), (int) (zbpbVar.I() + (zbpbVar.J() * sin)));
        double d2 = point.x;
        double F = zbpbVar.F() * sin;
        double F2 = r0[1].y + (zbpbVar.F() * cos);
        Point point2 = r0[0];
        int i2 = point2.x;
        Point point3 = r0[2];
        int i3 = point3.x;
        Point point4 = r0[1];
        Point[] pointArr = {new Point(zbpbVar.H(), zbpbVar.I()), point, new Point((int) (d2 - F), (int) F2), new Point(i2 + (i3 - point4.x), point2.y + (point3.y - point4.y))};
        return Arrays.asList(pointArr);
    }
}
