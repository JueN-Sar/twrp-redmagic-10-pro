package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;

/* loaded from: classes.dex */
public final class zzab extends com.google.android.gms.internal.common.zza implements IGmsCallbacks {
    zzab(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.IGmsCallbacks");
    }

    @Override // com.google.android.gms.common.internal.IGmsCallbacks
    public final void onPostInitComplete(int i2, IBinder iBinder, Bundle bundle) {
        Parcel zza = zza();
        zza.writeInt(i2);
        zza.writeStrongBinder(iBinder);
        com.google.android.gms.internal.common.zzc.c(zza, bundle);
        zzC(1, zza);
    }

    @Override // com.google.android.gms.common.internal.IGmsCallbacks
    public final void zzb(int i2, Bundle bundle) {
        throw null;
    }

    @Override // com.google.android.gms.common.internal.IGmsCallbacks
    public final void zzc(int i2, IBinder iBinder, zzk zzkVar) {
        throw null;
    }
}
