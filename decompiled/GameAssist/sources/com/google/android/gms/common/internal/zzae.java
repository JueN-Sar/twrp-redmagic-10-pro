package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes.dex */
public final class zzae extends com.google.android.gms.internal.common.zza implements zzag {
    zzae(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.IGoogleCertificatesApi");
    }

    @Override // com.google.android.gms.common.internal.zzag
    public final com.google.android.gms.common.zzq zze(com.google.android.gms.common.zzo zzoVar) {
        Parcel zza = zza();
        com.google.android.gms.internal.common.zzc.c(zza, zzoVar);
        Parcel zzB = zzB(6, zza);
        com.google.android.gms.common.zzq zzqVar = (com.google.android.gms.common.zzq) com.google.android.gms.internal.common.zzc.a(zzB, com.google.android.gms.common.zzq.CREATOR);
        zzB.recycle();
        return zzqVar;
    }

    @Override // com.google.android.gms.common.internal.zzag
    public final com.google.android.gms.common.zzq zzf(com.google.android.gms.common.zzo zzoVar) {
        Parcel zza = zza();
        com.google.android.gms.internal.common.zzc.c(zza, zzoVar);
        Parcel zzB = zzB(8, zza);
        com.google.android.gms.common.zzq zzqVar = (com.google.android.gms.common.zzq) com.google.android.gms.internal.common.zzc.a(zzB, com.google.android.gms.common.zzq.CREATOR);
        zzB.recycle();
        return zzqVar;
    }

    @Override // com.google.android.gms.common.internal.zzag
    public final boolean zzg() {
        Parcel zzB = zzB(9, zza());
        boolean f2 = com.google.android.gms.internal.common.zzc.f(zzB);
        zzB.recycle();
        return f2;
    }

    @Override // com.google.android.gms.common.internal.zzag
    public final boolean zzh(com.google.android.gms.common.zzs zzsVar, IObjectWrapper iObjectWrapper) {
        Parcel zza = zza();
        com.google.android.gms.internal.common.zzc.c(zza, zzsVar);
        com.google.android.gms.internal.common.zzc.e(zza, iObjectWrapper);
        Parcel zzB = zzB(5, zza);
        boolean f2 = com.google.android.gms.internal.common.zzc.f(zzB);
        zzB.recycle();
        return f2;
    }

    @Override // com.google.android.gms.common.internal.zzag
    public final boolean zzi() {
        Parcel zzB = zzB(7, zza());
        boolean f2 = com.google.android.gms.internal.common.zzc.f(zzB);
        zzB.recycle();
        return f2;
    }
}
