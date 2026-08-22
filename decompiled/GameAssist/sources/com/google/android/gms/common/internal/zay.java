package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes.dex */
public final class zay implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int F = SafeParcelReader.F(parcel);
        Scope[] scopeArr = null;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (parcel.dataPosition() < F) {
            int y = SafeParcelReader.y(parcel);
            int u = SafeParcelReader.u(y);
            if (u == 1) {
                i2 = SafeParcelReader.A(parcel, y);
            } else if (u == 2) {
                i3 = SafeParcelReader.A(parcel, y);
            } else if (u == 3) {
                i4 = SafeParcelReader.A(parcel, y);
            } else if (u != 4) {
                SafeParcelReader.E(parcel, y);
            } else {
                scopeArr = (Scope[]) SafeParcelReader.r(parcel, y, Scope.CREATOR);
            }
        }
        SafeParcelReader.t(parcel, F);
        return new zax(i2, i3, i4, scopeArr);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i2) {
        return new zax[i2];
    }
}
