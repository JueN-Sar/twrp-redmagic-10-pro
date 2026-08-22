package com.google.android.material.shape;

import android.graphics.RectF;
import androidx.annotation.RestrictTo;
import java.util.Arrays;

@RestrictTo
/* loaded from: classes.dex */
public final class ClampedCornerSize implements CornerSize {

    /* renamed from: a, reason: collision with root package name */
    private final float f15085a;

    public ClampedCornerSize(float f2) {
        this.f15085a = f2;
    }

    public static ClampedCornerSize b(AbsoluteCornerSize absoluteCornerSize) {
        return new ClampedCornerSize(absoluteCornerSize.b());
    }

    private static float c(RectF rectF) {
        return Math.min(rectF.width() / 2.0f, rectF.height() / 2.0f);
    }

    @Override // com.google.android.material.shape.CornerSize
    public float a(RectF rectF) {
        return Math.min(this.f15085a, c(rectF));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ClampedCornerSize) && this.f15085a == ((ClampedCornerSize) obj).f15085a;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.f15085a)});
    }
}
