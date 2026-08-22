package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes.dex */
public final class zbg implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int F = SafeParcelReader.F(parcel);
        int i2 = 0;
        boolean z = false;
        int i3 = 0;
        int i4 = 0;
        zbj[] zbjVarArr = null;
        zbd zbdVar = null;
        zbd zbdVar2 = null;
        zbd zbdVar3 = null;
        String str = null;
        String str2 = null;
        float f2 = 0.0f;
        while (parcel.dataPosition() < F) {
            int y = SafeParcelReader.y(parcel);
            switch (SafeParcelReader.u(y)) {
                case 2:
                    zbjVarArr = (zbj[]) SafeParcelReader.r(parcel, y, zbj.CREATOR);
                    break;
                case 3:
                    zbdVar = (zbd) SafeParcelReader.n(parcel, y, zbd.CREATOR);
                    break;
                case 4:
                    zbdVar2 = (zbd) SafeParcelReader.n(parcel, y, zbd.CREATOR);
                    break;
                case 5:
                    zbdVar3 = (zbd) SafeParcelReader.n(parcel, y, zbd.CREATOR);
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
        return new zbf(zbjVarArr, zbdVar, zbdVar2, zbdVar3, str, f2, str2, i2, z, i3, i4);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i2) {
        return new zbf[i2];
    }
}
