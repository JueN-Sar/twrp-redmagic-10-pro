package com.google.android.gms.common.api;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes.dex */
public final class zzb implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int F = SafeParcelReader.F(parcel);
        String str = null;
        ConnectionResult connectionResult = null;
        int i2 = 0;
        PendingIntent pendingIntent = null;
        while (parcel.dataPosition() < F) {
            int y = SafeParcelReader.y(parcel);
            int u = SafeParcelReader.u(y);
            if (u == 1) {
                i2 = SafeParcelReader.A(parcel, y);
            } else if (u == 2) {
                str = SafeParcelReader.o(parcel, y);
            } else if (u == 3) {
                pendingIntent = (PendingIntent) SafeParcelReader.n(parcel, y, PendingIntent.CREATOR);
            } else if (u != 4) {
                SafeParcelReader.E(parcel, y);
            } else {
                connectionResult = (ConnectionResult) SafeParcelReader.n(parcel, y, ConnectionResult.CREATOR);
            }
        }
        SafeParcelReader.t(parcel, F);
        return new Status(i2, str, pendingIntent, connectionResult);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i2) {
        return new Status[i2];
    }
}
