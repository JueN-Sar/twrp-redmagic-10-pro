package com.google.android.material.shape;

import android.graphics.RectF;
import java.util.Arrays;

/* loaded from: classes.dex */
public final class AbsoluteCornerSize implements CornerSize {

    /* renamed from: a, reason: collision with root package name */
    private final float f15082a;

    public AbsoluteCornerSize(float f2) {
        this.f15082a = f2;
    }

    @Override // com.google.android.material.shape.CornerSize
    public float a(RectF rectF) {
        return this.f15082a;
    }

    public float b() {
        return this.f15082a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof AbsoluteCornerSize) && this.f15082a == ((AbsoluteCornerSize) obj).f15082a;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.f15082a)});
    }
}
