package com.zte.aivibrate;

import android.graphics.Rect;

/* loaded from: classes.dex */
public interface IDetectStrategy {
    default void a(int i2) {
    }

    default float b() {
        return 0.85f;
    }

    int c();

    int d();

    int e();

    default Rect f() {
        return new Rect();
    }
}
