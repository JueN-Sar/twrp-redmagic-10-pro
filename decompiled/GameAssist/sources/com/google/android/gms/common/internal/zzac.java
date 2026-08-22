package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;

/* loaded from: classes.dex */
public abstract class zzac extends com.google.android.gms.internal.common.zzb implements IGmsCallbacks {
    public zzac() {
        super("com.google.android.gms.common.internal.IGmsCallbacks");
    }

    @Override // com.google.android.gms.internal.common.zzb
    protected final boolean zza(int i2, Parcel parcel, Parcel parcel2, int i3) {
        if (i2 == 1) {
            int readInt = parcel.readInt();
            IBinder readStrongBinder = parcel.readStrongBinder();
            Bundle bundle = (Bundle) com.google.android.gms.internal.common.zzc.a(parcel, Bundle.CREATOR);
            com.google.android.gms.internal.common.zzc.b(parcel);
            onPostInitComplete(readInt, readStrongBinder, bundle);
        } else if (i2 == 2) {
            int readInt2 = parcel.readInt();
            Bundle bundle2 = (Bundle) com.google.android.gms.internal.common.zzc.a(parcel, Bundle.CREATOR);
            com.google.android.gms.internal.common.zzc.b(parcel);
            zzb(readInt2, bundle2);
        } else {
            if (i2 != 3) {
                return false;
            }
            int readInt3 = parcel.readInt();
            IBinder readStrongBinder2 = parcel.readStrongBinder();
            zzk zzkVar = (zzk) com.google.android.gms.internal.common.zzc.a(parcel, zzk.CREATOR);
            com.google.android.gms.internal.common.zzc.b(parcel);
            zzc(readInt3, readStrongBinder2, zzkVar);
        }
        parcel2.writeNoException();
        return true;
    }
}
