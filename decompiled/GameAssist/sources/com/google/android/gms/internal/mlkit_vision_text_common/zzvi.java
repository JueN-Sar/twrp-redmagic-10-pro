package com.google.android.gms.internal.mlkit_vision_text_common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes.dex */
public final class zzvi implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int F = SafeParcelReader.F(parcel);
        boolean z = false;
        int i2 = 0;
        boolean z2 = false;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        while (parcel.dataPosition() < F) {
            int y = SafeParcelReader.y(parcel);
            switch (SafeParcelReader.u(y)) {
                case 1:
                    str = SafeParcelReader.o(parcel, y);
                    break;
                case 2:
                    str2 = SafeParcelReader.o(parcel, y);
                    break;
                case 3:
                    str3 = SafeParcelReader.o(parcel, y);
                    break;
                case 4:
                    z = SafeParcelReader.v(parcel, y);
                    break;
                case 5:
                    i2 = SafeParcelReader.A(parcel, y);
                    break;
                case 6:
                    str4 = SafeParcelReader.o(parcel, y);
                    break;
                case 7:
                    z2 = SafeParcelReader.v(parcel, y);
                    break;
                default:
                    SafeParcelReader.E(parcel, y);
                    break;
            }
        }
        SafeParcelReader.t(parcel, F);
        return new zzvh(str, str2, str3, z, i2, str4, z2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i2) {
        return new zzvh[i2];
    }
}
