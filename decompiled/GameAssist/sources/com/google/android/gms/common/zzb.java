package com.google.android.gms.common;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes.dex */
public final class zzb implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int F = SafeParcelReader.F(parcel);
        PendingIntent pendingIntent = null;
        int i2 = 0;
        int i3 = 0;
        String str = null;
        while (parcel.dataPosition() < F) {
            int y = SafeParcelReader.y(parcel);
            int u = SafeParcelReader.u(y);
            if (u == 1) {
                i2 = SafeParcelReader.A(parcel, y);
            } else if (u == 2) {
                i3 = SafeParcelReader.A(parcel, y);
            } else if (u == 3) {
                pendingIntent = (PendingIntent) SafeParcelReader.n(parcel, y, PendingIntent.CREATOR);
            } else if (u != 4) {
                SafeParcelReader.E(parcel, y);
            } else {
                str = SafeParcelReader.o(parcel, y);
            }
        }
        SafeParcelReader.t(parcel, F);
        return new ConnectionResult(i2, i3, pendingIntent, str);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i2) {
        return new ConnectionResult[i2];
    }
}
