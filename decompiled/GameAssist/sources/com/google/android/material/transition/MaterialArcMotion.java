package com.google.android.material.transition;

import android.graphics.Path;
import android.graphics.PointF;
import androidx.transition.PathMotion;

/* loaded from: classes.dex */
public final class MaterialArcMotion extends PathMotion {
    private static PointF b(float f2, float f3, float f4, float f5) {
        return f3 > f5 ? new PointF(f4, f3) : new PointF(f2, f5);
    }

    @Override // androidx.transition.PathMotion
    public Path a(float f2, float f3, float f4, float f5) {
        Path path = new Path();
        path.moveTo(f2, f3);
        PointF b2 = b(f2, f3, f4, f5);
        path.quadTo(b2.x, b2.y, f4, f5);
        return path;
    }
}
