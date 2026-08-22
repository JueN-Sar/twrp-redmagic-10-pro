package com.google.android.gms.signin.internal;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes.dex */
public final class zab implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int F = SafeParcelReader.F(parcel);
        Intent intent = null;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < F) {
            int y = SafeParcelReader.y(parcel);
            int u = SafeParcelReader.u(y);
            if (u == 1) {
                i2 = SafeParcelReader.A(parcel, y);
            } else if (u == 2) {
                i3 = SafeParcelReader.A(parcel, y);
            } else if (u != 3) {
                SafeParcelReader.E(parcel, y);
            } else {
                intent = (Intent) SafeParcelReader.n(parcel, y, Intent.CREATOR);
            }
        }
        SafeParcelReader.t(parcel, F);
        return new zaa(i2, i3, intent);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i2) {
        return new zaa[i2];
    }
}
