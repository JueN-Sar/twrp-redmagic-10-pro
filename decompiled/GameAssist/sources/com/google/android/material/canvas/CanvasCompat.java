package com.google.android.material.canvas;

import android.graphics.Canvas;
import androidx.annotation.RestrictTo;

@RestrictTo
/* loaded from: classes.dex */
public class CanvasCompat {

    public interface CanvasOperation {
        void a(Canvas canvas);
    }

    public static int a(Canvas canvas, float f2, float f3, float f4, float f5, int i2) {
        return canvas.saveLayerAlpha(f2, f3, f4, f5, i2);
    }
}
