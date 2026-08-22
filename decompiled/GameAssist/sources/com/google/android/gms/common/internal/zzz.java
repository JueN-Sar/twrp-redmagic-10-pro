package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes.dex */
public abstract class zzz extends com.google.android.gms.internal.common.zzb implements zzaa {
    public zzz() {
        super("com.google.android.gms.common.internal.ICertData");
    }

    public static zzaa zzg(IBinder iBinder) {
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.ICertData");
        return queryLocalInterface instanceof zzaa ? (zzaa) queryLocalInterface : new zzy(iBinder);
    }

    @Override // com.google.android.gms.internal.common.zzb
    protected final boolean zza(int i2, Parcel parcel, Parcel parcel2, int i3) {
        if (i2 == 1) {
            IObjectWrapper zzd = zzd();
            parcel2.writeNoException();
            com.google.android.gms.internal.common.zzc.e(parcel2, zzd);
        } else {
            if (i2 != 2) {
                return false;
            }
            int zzc = zzc();
            parcel2.writeNoException();
            parcel2.writeInt(zzc);
        }
        return true;
    }
}
