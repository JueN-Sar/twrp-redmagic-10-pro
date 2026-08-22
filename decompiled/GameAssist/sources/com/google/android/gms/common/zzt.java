package com.google.android.gms.common;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes.dex */
public final class zzt implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int F = SafeParcelReader.F(parcel);
        boolean z = false;
        String str = null;
        IBinder iBinder = null;
        boolean z2 = false;
        while (parcel.dataPosition() < F) {
            int y = SafeParcelReader.y(parcel);
            int u = SafeParcelReader.u(y);
            if (u == 1) {
                str = SafeParcelReader.o(parcel, y);
            } else if (u == 2) {
                iBinder = SafeParcelReader.z(parcel, y);
            } else if (u == 3) {
                z = SafeParcelReader.v(parcel, y);
            } else if (u != 4) {
                SafeParcelReader.E(parcel, y);
            } else {
                z2 = SafeParcelReader.v(parcel, y);
            }
        }
        SafeParcelReader.t(parcel, F);
        return new zzs(str, iBinder, z, z2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i2) {
        return new zzs[i2];
    }
}
