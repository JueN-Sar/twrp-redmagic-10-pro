package com.google.android.odml.image;

import android.media.Image;
import androidx.annotation.RequiresApi;

@RequiresApi
/* loaded from: classes.dex */
final class zzi implements zzg {

    /* renamed from: a, reason: collision with root package name */
    private final Image f15787a;

    /* renamed from: b, reason: collision with root package name */
    private final ImageProperties f15788b;

    public final Image a() {
        return this.f15787a;
    }

    @Override // com.google.android.odml.image.zzg
    public final ImageProperties zzb() {
        return this.f15788b;
    }

    @Override // com.google.android.odml.image.zzg
    public final void zzc() {
        this.f15787a.close();
    }
}
