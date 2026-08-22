package com.google.android.gms.internal.mlkit_vision_text_common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes.dex */
public final class zzs implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int F = SafeParcelReader.F(parcel);
        boolean z = false;
        zzn[] zznVarArr = null;
        zzf zzfVar = null;
        zzf zzfVar2 = null;
        String str = null;
        String str2 = null;
        float f2 = 0.0f;
        while (parcel.dataPosition() < F) {
            int y = SafeParcelReader.y(parcel);
            switch (SafeParcelReader.u(y)) {
                case 2:
                    zznVarArr = (zzn[]) SafeParcelReader.r(parcel, y, zzn.CREATOR);
                    break;
                case 3:
                    zzfVar = (zzf) SafeParcelReader.n(parcel, y, zzf.CREATOR);
                    break;
                case 4:
                    zzfVar2 = (zzf) SafeParcelReader.n(parcel, y, zzf.CREATOR);
                    break;
                case 5:
                    str = SafeParcelReader.o(parcel, y);
                    break;
                case 6:
                    f2 = SafeParcelReader.x(parcel, y);
                    break;
                case 7:
                    str2 = SafeParcelReader.o(parcel, y);
                    break;
                case 8:
                    z = SafeParcelReader.v(parcel, y);
                    break;
                default:
                    SafeParcelReader.E(parcel, y);
                    break;
            }
        }
        SafeParcelReader.t(parcel, F);
        return new zzr(zznVarArr, zzfVar, zzfVar2, str, f2, str2, z);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i2) {
        return new zzr[i2];
    }
}
