package com.google.android.gms.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes.dex */
public final class zzc implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int F = SafeParcelReader.F(parcel);
        long j2 = -1;
        int i2 = 0;
        String str = null;
        while (parcel.dataPosition() < F) {
            int y = SafeParcelReader.y(parcel);
            int u = SafeParcelReader.u(y);
            if (u == 1) {
                str = SafeParcelReader.o(parcel, y);
            } else if (u == 2) {
                i2 = SafeParcelReader.A(parcel, y);
            } else if (u != 3) {
                SafeParcelReader.E(parcel, y);
            } else {
                j2 = SafeParcelReader.B(parcel, y);
            }
        }
        SafeParcelReader.t(parcel, F);
        return new Feature(str, i2, j2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i2) {
        return new Feature[i2];
    }
}
