package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes.dex */
public final class zzaj implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int F = SafeParcelReader.F(parcel);
        int i2 = 0;
        boolean z = false;
        boolean z2 = false;
        int i3 = 0;
        int i4 = 0;
        while (parcel.dataPosition() < F) {
            int y = SafeParcelReader.y(parcel);
            int u = SafeParcelReader.u(y);
            if (u == 1) {
                i2 = SafeParcelReader.A(parcel, y);
            } else if (u == 2) {
                z = SafeParcelReader.v(parcel, y);
            } else if (u == 3) {
                z2 = SafeParcelReader.v(parcel, y);
            } else if (u == 4) {
                i3 = SafeParcelReader.A(parcel, y);
            } else if (u != 5) {
                SafeParcelReader.E(parcel, y);
            } else {
                i4 = SafeParcelReader.A(parcel, y);
            }
        }
        SafeParcelReader.t(parcel, F);
        return new RootTelemetryConfiguration(i2, z, z2, i3, i4);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i2) {
        return new RootTelemetryConfiguration[i2];
    }
}
