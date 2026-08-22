package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes.dex */
public final class zbk implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int F = SafeParcelReader.F(parcel);
        boolean z = false;
        zbh[] zbhVarArr = null;
        zbd zbdVar = null;
        zbd zbdVar2 = null;
        String str = null;
        String str2 = null;
        float f2 = 0.0f;
        while (parcel.dataPosition() < F) {
            int y = SafeParcelReader.y(parcel);
            switch (SafeParcelReader.u(y)) {
                case 2:
                    zbhVarArr = (zbh[]) SafeParcelReader.r(parcel, y, zbh.CREATOR);
                    break;
                case 3:
                    zbdVar = (zbd) SafeParcelReader.n(parcel, y, zbd.CREATOR);
                    break;
                case 4:
                    zbdVar2 = (zbd) SafeParcelReader.n(parcel, y, zbd.CREATOR);
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
        return new zbj(zbhVarArr, zbdVar, zbdVar2, str, f2, str2, z);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i2) {
        return new zbj[i2];
    }
}
