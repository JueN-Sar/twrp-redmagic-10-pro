package com.google.android.gms.internal.mlkit_vision_text_common;

import com.zte.shared.wrapper.VirtualHandleWrapper;
import java.util.List;

/* loaded from: classes.dex */
final class zzbj extends zzbk {
    final transient int zza;
    final transient int zzb;
    final /* synthetic */ zzbk zzc;

    zzbj(zzbk zzbkVar, int i2, int i3) {
        this.zzc = zzbkVar;
        this.zza = i2;
        this.zzb = i3;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzbf
    final int d() {
        return this.zzc.f() + this.zza + this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzbf
    final int f() {
        return this.zzc.f() + this.zza;
    }

    @Override // java.util.List
    public final Object get(int i2) {
        zzx.a(i2, this.zzb, VirtualHandleWrapper.KEY_INDEX);
        return this.zzc.get(i2 + this.zza);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzbf
    final Object[] h() {
        return this.zzc.h();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzbk
    /* renamed from: i */
    public final zzbk subList(int i2, int i3) {
        zzx.c(i2, i3, this.zzb);
        int i4 = this.zza;
        return this.zzc.subList(i2 + i4, i3 + i4);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzbk, java.util.List
    public final /* bridge */ /* synthetic */ List subList(int i2, int i3) {
        return subList(i2, i3);
    }
}
