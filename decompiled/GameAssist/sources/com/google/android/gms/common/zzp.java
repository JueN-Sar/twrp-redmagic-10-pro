package com.google.android.gms.common;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes.dex */
public final class zzp implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int F = SafeParcelReader.F(parcel);
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        String str = null;
        IBinder iBinder = null;
        while (parcel.dataPosition() < F) {
            int y = SafeParcelReader.y(parcel);
            switch (SafeParcelReader.u(y)) {
                case 1:
                    str = SafeParcelReader.o(parcel, y);
                    break;
                case 2:
                    z = SafeParcelReader.v(parcel, y);
                    break;
                case 3:
                    z2 = SafeParcelReader.v(parcel, y);
                    break;
                case 4:
                    iBinder = SafeParcelReader.z(parcel, y);
                    break;
                case 5:
                    z3 = SafeParcelReader.v(parcel, y);
                    break;
                case 6:
                    z4 = SafeParcelReader.v(parcel, y);
                    break;
                default:
                    SafeParcelReader.E(parcel, y);
                    break;
            }
        }
        SafeParcelReader.t(parcel, F);
        return new zzo(str, z, z2, iBinder, z3, z4);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i2) {
        return new zzo[i2];
    }
}
