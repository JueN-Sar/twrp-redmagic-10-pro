package com.google.mlkit.vision.text.internal;

import android.graphics.Point;
import android.graphics.Rect;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
final class zza {
    static Rect a(List list) {
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
        return new Rect(i3, i4, i2, i5);
    }

    static List b(com.google.android.gms.internal.mlkit_vision_text_common.zzf zzfVar) {
        double sin = Math.sin(Math.toRadians(zzfVar.f13185k));
        double cos = Math.cos(Math.toRadians(zzfVar.f13185k));
        double d2 = zzfVar.f13181c;
        double d3 = zzfVar.f13183i;
        Point point = new Point((int) (d2 + (d3 * cos)), (int) (zzfVar.f13182h + (d3 * sin)));
        double d4 = point.x;
        int i2 = zzfVar.f13184j;
        double d5 = i2 * sin;
        double d6 = r0[1].y + (i2 * cos);
        Point point2 = r0[0];
        int i3 = point2.x;
        Point point3 = r0[2];
        int i4 = point3.x;
        Point point4 = r0[1];
        Point[] pointArr = {new Point(zzfVar.f13181c, zzfVar.f13182h), point, new Point((int) (d4 - d5), (int) d6), new Point(i3 + (i4 - point4.x), point2.y + (point3.y - point4.y))};
        return Arrays.asList(pointArr);
    }
}
