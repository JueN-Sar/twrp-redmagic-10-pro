package com.google.android.gms.internal.mlkit_vision_text_common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes.dex */
public final class zzm implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int F = SafeParcelReader.F(parcel);
        int i2 = 0;
        boolean z = false;
        int i3 = 0;
        int i4 = 0;
        zzr[] zzrVarArr = null;
        zzf zzfVar = null;
        zzf zzfVar2 = null;
        zzf zzfVar3 = null;
        String str = null;
        String str2 = null;
        float f2 = 0.0f;
        while (parcel.dataPosition() < F) {
            int y = SafeParcelReader.y(parcel);
            switch (SafeParcelReader.u(y)) {
                case 2:
                    zzrVarArr = (zzr[]) SafeParcelReader.r(parcel, y, zzr.CREATOR);
                    break;
                case 3:
                    zzfVar = (zzf) SafeParcelReader.n(parcel, y, zzf.CREATOR);
                    break;
                case 4:
                    zzfVar2 = (zzf) SafeParcelReader.n(parcel, y, zzf.CREATOR);
                    break;
                case 5:
                    zzfVar3 = (zzf) SafeParcelReader.n(parcel, y, zzf.CREATOR);
                    break;
                case 6:
                    str = SafeParcelReader.o(parcel, y);
                    break;
                case 7:
                    f2 = SafeParcelReader.x(parcel, y);
                    break;
                case 8:
                    str2 = SafeParcelReader.o(parcel, y);
                    break;
                case 9:
                    i2 = SafeParcelReader.A(parcel, y);
                    break;
                case 10:
                    z = SafeParcelReader.v(parcel, y);
                    break;
                case 11:
                    i3 = SafeParcelReader.A(parcel, y);
                    break;
                case 12:
                    i4 = SafeParcelReader.A(parcel, y);
                    break;
                default:
                    SafeParcelReader.E(parcel, y);
                    break;
            }
        }
        SafeParcelReader.t(parcel, F);
        return new zzl(zzrVarArr, zzfVar, zzfVar2, zzfVar3, str, f2, str2, i2, z, i3, i4);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i2) {
        return new zzl[i2];
    }
}
