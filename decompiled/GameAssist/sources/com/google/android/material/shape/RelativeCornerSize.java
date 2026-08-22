package com.google.android.material.shape;

import android.graphics.RectF;
import java.util.Arrays;

/* loaded from: classes.dex */
public final class RelativeCornerSize implements CornerSize {

    /* renamed from: a, reason: collision with root package name */
    private final float f15131a;

    public RelativeCornerSize(float f2) {
        this.f15131a = f2;
    }

    public static RelativeCornerSize b(RectF rectF, CornerSize cornerSize) {
        return cornerSize instanceof RelativeCornerSize ? (RelativeCornerSize) cornerSize : new RelativeCornerSize(cornerSize.a(rectF) / c(rectF));
    }

    private static float c(RectF rectF) {
        return Math.min(rectF.width(), rectF.height());
    }

    @Override // com.google.android.material.shape.CornerSize
    public float a(RectF rectF) {
        return this.f15131a * c(rectF);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof RelativeCornerSize) && this.f15131a == ((RelativeCornerSize) obj).f15131a;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.f15131a)});
    }
}
