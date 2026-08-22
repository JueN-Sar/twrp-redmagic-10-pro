package com.google.android.gms.internal.common;

import com.zte.shared.wrapper.VirtualHandleWrapper;
import java.util.List;

/* loaded from: classes.dex */
final class zzaf extends zzag {
    final transient int zza;
    final transient int zzb;
    final /* synthetic */ zzag zzc;

    zzaf(zzag zzagVar, int i2, int i3) {
        this.zzc = zzagVar;
        this.zza = i2;
        this.zzb = i3;
    }

    @Override // com.google.android.gms.internal.common.zzac
    final int d() {
        return this.zzc.f() + this.zza + this.zzb;
    }

    @Override // com.google.android.gms.internal.common.zzac
    final int f() {
        return this.zzc.f() + this.zza;
    }

    @Override // java.util.List
    public final Object get(int i2) {
        zzs.a(i2, this.zzb, VirtualHandleWrapper.KEY_INDEX);
        return this.zzc.get(i2 + this.zza);
    }

    @Override // com.google.android.gms.internal.common.zzac
    final boolean i() {
        return true;
    }

    @Override // com.google.android.gms.internal.common.zzac
    final Object[] j() {
        return this.zzc.j();
    }

    @Override // com.google.android.gms.internal.common.zzag
    /* renamed from: k */
    public final zzag subList(int i2, int i3) {
        zzs.c(i2, i3, this.zzb);
        int i4 = this.zza;
        return this.zzc.subList(i2 + i4, i3 + i4);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.common.zzag, java.util.List
    public final /* bridge */ /* synthetic */ List subList(int i2, int i3) {
        return subList(i2, i3);
    }
}
