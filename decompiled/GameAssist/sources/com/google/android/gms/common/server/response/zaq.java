package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes.dex */
public final class zaq implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int F = SafeParcelReader.F(parcel);
        Parcel parcel2 = null;
        int i2 = 0;
        zan zanVar = null;
        while (parcel.dataPosition() < F) {
            int y = SafeParcelReader.y(parcel);
            int u = SafeParcelReader.u(y);
            if (u == 1) {
                i2 = SafeParcelReader.A(parcel, y);
            } else if (u == 2) {
                parcel2 = SafeParcelReader.l(parcel, y);
            } else if (u != 3) {
                SafeParcelReader.E(parcel, y);
            } else {
                zanVar = (zan) SafeParcelReader.n(parcel, y, zan.CREATOR);
            }
        }
        SafeParcelReader.t(parcel, F);
        return new SafeParcelResponse(i2, parcel2, zanVar);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i2) {
        return new SafeParcelResponse[i2];
    }
}
