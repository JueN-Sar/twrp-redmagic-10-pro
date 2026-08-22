package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.zav;

/* loaded from: classes.dex */
public final class zal implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int F = SafeParcelReader.F(parcel);
        ConnectionResult connectionResult = null;
        int i2 = 0;
        zav zavVar = null;
        while (parcel.dataPosition() < F) {
            int y = SafeParcelReader.y(parcel);
            int u = SafeParcelReader.u(y);
            if (u == 1) {
                i2 = SafeParcelReader.A(parcel, y);
            } else if (u == 2) {
                connectionResult = (ConnectionResult) SafeParcelReader.n(parcel, y, ConnectionResult.CREATOR);
            } else if (u != 3) {
                SafeParcelReader.E(parcel, y);
            } else {
                zavVar = (zav) SafeParcelReader.n(parcel, y, zav.CREATOR);
            }
        }
        SafeParcelReader.t(parcel, F);
        return new zak(i2, connectionResult, zavVar);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i2) {
        return new zak[i2];
    }
}
