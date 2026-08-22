package com.google.android.material.shape;

import android.graphics.RectF;
import androidx.annotation.RestrictTo;
import java.util.Arrays;

@RestrictTo
/* loaded from: classes.dex */
public final class AdjustedCornerSize implements CornerSize {

    /* renamed from: a, reason: collision with root package name */
    private final CornerSize f15083a;

    /* renamed from: b, reason: collision with root package name */
    private final float f15084b;

    public AdjustedCornerSize(float f2, CornerSize cornerSize) {
        while (cornerSize instanceof AdjustedCornerSize) {
            cornerSize = ((AdjustedCornerSize) cornerSize).f15083a;
            f2 += ((AdjustedCornerSize) cornerSize).f15084b;
        }
        this.f15083a = cornerSize;
        this.f15084b = f2;
    }

    @Override // com.google.android.material.shape.CornerSize
    public float a(RectF rectF) {
        return Math.max(0.0f, this.f15083a.a(rectF) + this.f15084b);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AdjustedCornerSize)) {
            return false;
        }
        AdjustedCornerSize adjustedCornerSize = (AdjustedCornerSize) obj;
        return this.f15083a.equals(adjustedCornerSize.f15083a) && this.f15084b == adjustedCornerSize.f15084b;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.f15083a, Float.valueOf(this.f15084b)});
    }
}
