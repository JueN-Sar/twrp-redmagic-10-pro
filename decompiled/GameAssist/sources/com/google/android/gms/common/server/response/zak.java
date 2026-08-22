package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.server.response.FastJsonResponse;

/* loaded from: classes.dex */
public final class zak implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int F = SafeParcelReader.F(parcel);
        String str = null;
        int i2 = 0;
        FastJsonResponse.Field field = null;
        while (parcel.dataPosition() < F) {
            int y = SafeParcelReader.y(parcel);
            int u = SafeParcelReader.u(y);
            if (u == 1) {
                i2 = SafeParcelReader.A(parcel, y);
            } else if (u == 2) {
                str = SafeParcelReader.o(parcel, y);
            } else if (u != 3) {
                SafeParcelReader.E(parcel, y);
            } else {
                field = (FastJsonResponse.Field) SafeParcelReader.n(parcel, y, FastJsonResponse.Field.CREATOR);
            }
        }
        SafeParcelReader.t(parcel, F);
        return new zam(i2, str, field);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i2) {
        return new zam[i2];
    }
}
