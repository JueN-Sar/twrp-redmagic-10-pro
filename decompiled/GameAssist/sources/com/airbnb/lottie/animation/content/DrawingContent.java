package com.airbnb.lottie.animation.content;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;

/* loaded from: classes.dex */
public interface DrawingContent extends Content {
    void g(RectF rectF, Matrix matrix, boolean z);

    void i(Canvas canvas, Matrix matrix, int i2);
}
