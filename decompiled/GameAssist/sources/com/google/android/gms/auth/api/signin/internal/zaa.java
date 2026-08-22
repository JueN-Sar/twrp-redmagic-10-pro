package com.google.android.gms.auth.api.signin.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes.dex */
public final class zaa implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int F = SafeParcelReader.F(parcel);
        Bundle bundle = null;
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
                bundle = SafeParcelReader.f(parcel, y);
            }
        }
        SafeParcelReader.t(parcel, F);
        return new GoogleSignInOptionsExtensionParcelable(i2, i3, bundle);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i2) {
        return new GoogleSignInOptionsExtensionParcelable[i2];
    }
}
