package com.google.android.gms.internal.mlkit_vision_common;

import com.zte.shared.wrapper.VirtualHandleWrapper;
import java.util.List;

/* loaded from: classes.dex */
final class zzo extends zzp {
    final transient int zza;
    final transient int zzb;
    final /* synthetic */ zzp zzc;

    zzo(zzp zzpVar, int i2, int i3) {
        this.zzc = zzpVar;
        this.zza = i2;
        this.zzb = i3;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzl
    final int d() {
        return this.zzc.f() + this.zza + this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzl
    final int f() {
        return this.zzc.f() + this.zza;
    }

    @Override // java.util.List
    public final Object get(int i2) {
        zzf.a(i2, this.zzb, VirtualHandleWrapper.KEY_INDEX);
        return this.zzc.get(i2 + this.zza);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzl
    final Object[] h() {
        return this.zzc.h();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzp
    /* renamed from: i */
    public final zzp subList(int i2, int i3) {
        zzf.c(i2, i3, this.zzb);
        zzp zzpVar = this.zzc;
        int i4 = this.zza;
        return zzpVar.subList(i2 + i4, i3 + i4);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzp, java.util.List
    public final /* bridge */ /* synthetic */ List subList(int i2, int i3) {
        return subList(i2, i3);
    }
}
