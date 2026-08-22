package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class zad implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int F = SafeParcelReader.F(parcel);
        ArrayList arrayList = null;
        int i2 = 0;
        while (parcel.dataPosition() < F) {
            int y = SafeParcelReader.y(parcel);
            int u = SafeParcelReader.u(y);
            if (u == 1) {
                i2 = SafeParcelReader.A(parcel, y);
            } else if (u != 2) {
                SafeParcelReader.E(parcel, y);
            } else {
                arrayList = SafeParcelReader.s(parcel, y, zac.CREATOR);
            }
        }
        SafeParcelReader.t(parcel, F);
        return new StringToIntConverter(i2, arrayList);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i2) {
        return new StringToIntConverter[i2];
    }
}
