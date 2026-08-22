package com.google.android.material.drawable;

import androidx.annotation.RestrictTo;
import androidx.appcompat.graphics.drawable.DrawableWrapperCompat;

@RestrictTo
/* loaded from: classes.dex */
public class ScaledDrawableWrapper extends DrawableWrapperCompat {

    /* renamed from: h, reason: collision with root package name */
    private final int f14570h;

    /* renamed from: i, reason: collision with root package name */
    private final int f14571i;

    @Override // androidx.appcompat.graphics.drawable.DrawableWrapperCompat, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.f14571i;
    }

    @Override // androidx.appcompat.graphics.drawable.DrawableWrapperCompat, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.f14570h;
    }
}
