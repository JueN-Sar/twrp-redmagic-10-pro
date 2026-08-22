package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.server.response.FastJsonResponse;

/* loaded from: classes.dex */
public final class zaj implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int F = SafeParcelReader.F(parcel);
        String str = null;
        String str2 = null;
        com.google.android.gms.common.server.converter.zaa zaaVar = null;
        int i2 = 0;
        int i3 = 0;
        boolean z = false;
        int i4 = 0;
        boolean z2 = false;
        int i5 = 0;
        while (parcel.dataPosition() < F) {
            int y = SafeParcelReader.y(parcel);
            switch (SafeParcelReader.u(y)) {
                case 1:
                    i2 = SafeParcelReader.A(parcel, y);
                    break;
                case 2:
                    i3 = SafeParcelReader.A(parcel, y);
                    break;
                case 3:
                    z = SafeParcelReader.v(parcel, y);
                    break;
                case 4:
                    i4 = SafeParcelReader.A(parcel, y);
                    break;
                case 5:
                    z2 = SafeParcelReader.v(parcel, y);
                    break;
                case 6:
                    str = SafeParcelReader.o(parcel, y);
                    break;
                case 7:
                    i5 = SafeParcelReader.A(parcel, y);
                    break;
                case 8:
                    str2 = SafeParcelReader.o(parcel, y);
                    break;
                case 9:
                    zaaVar = (com.google.android.gms.common.server.converter.zaa) SafeParcelReader.n(parcel, y, com.google.android.gms.common.server.converter.zaa.CREATOR);
                    break;
                default:
                    SafeParcelReader.E(parcel, y);
                    break;
            }
        }
        SafeParcelReader.t(parcel, F);
        return new FastJsonResponse.Field(i2, i3, z, i4, z2, str, i5, str2, zaaVar);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i2) {
        return new FastJsonResponse.Field[i2];
    }
}
