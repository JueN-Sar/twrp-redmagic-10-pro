package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes.dex */
public final class zaw implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int F = SafeParcelReader.F(parcel);
        int i2 = 0;
        boolean z = false;
        boolean z2 = false;
        IBinder iBinder = null;
        ConnectionResult connectionResult = null;
        while (parcel.dataPosition() < F) {
            int y = SafeParcelReader.y(parcel);
            int u = SafeParcelReader.u(y);
            if (u == 1) {
                i2 = SafeParcelReader.A(parcel, y);
            } else if (u == 2) {
                iBinder = SafeParcelReader.z(parcel, y);
            } else if (u == 3) {
                connectionResult = (ConnectionResult) SafeParcelReader.n(parcel, y, ConnectionResult.CREATOR);
            } else if (u == 4) {
                z = SafeParcelReader.v(parcel, y);
            } else if (u != 5) {
                SafeParcelReader.E(parcel, y);
            } else {
                z2 = SafeParcelReader.v(parcel, y);
            }
        }
        SafeParcelReader.t(parcel, F);
        return new zav(i2, iBinder, connectionResult, z, z2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i2) {
        return new zav[i2];
    }
}
