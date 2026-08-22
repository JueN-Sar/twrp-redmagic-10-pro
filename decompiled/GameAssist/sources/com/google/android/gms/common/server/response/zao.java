package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class zao implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int F = SafeParcelReader.F(parcel);
        ArrayList arrayList = null;
        int i2 = 0;
        String str = null;
        while (parcel.dataPosition() < F) {
            int y = SafeParcelReader.y(parcel);
            int u = SafeParcelReader.u(y);
            if (u == 1) {
                i2 = SafeParcelReader.A(parcel, y);
            } else if (u == 2) {
                arrayList = SafeParcelReader.s(parcel, y, zal.CREATOR);
            } else if (u != 3) {
                SafeParcelReader.E(parcel, y);
            } else {
                str = SafeParcelReader.o(parcel, y);
            }
        }
        SafeParcelReader.t(parcel, F);
        return new zan(i2, arrayList, str);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i2) {
        return new zan[i2];
    }
}
