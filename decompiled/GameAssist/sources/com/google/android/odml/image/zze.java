package com.google.android.odml.image;

import android.graphics.Bitmap;

/* loaded from: classes.dex */
final class zze implements zzg {

    /* renamed from: a, reason: collision with root package name */
    private final Bitmap f15783a;

    /* renamed from: b, reason: collision with root package name */
    private final ImageProperties f15784b;

    public final Bitmap a() {
        return this.f15783a;
    }

    @Override // com.google.android.odml.image.zzg
    public final ImageProperties zzb() {
        return this.f15784b;
    }

    @Override // com.google.android.odml.image.zzg
    public final void zzc() {
        this.f15783a.recycle();
    }
}
