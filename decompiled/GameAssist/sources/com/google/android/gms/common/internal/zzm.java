package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes.dex */
public final class zzm implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int F = SafeParcelReader.F(parcel);
        RootTelemetryConfiguration rootTelemetryConfiguration = null;
        int[] iArr = null;
        int[] iArr2 = null;
        boolean z = false;
        boolean z2 = false;
        int i2 = 0;
        while (parcel.dataPosition() < F) {
            int y = SafeParcelReader.y(parcel);
            switch (SafeParcelReader.u(y)) {
                case 1:
                    rootTelemetryConfiguration = (RootTelemetryConfiguration) SafeParcelReader.n(parcel, y, RootTelemetryConfiguration.CREATOR);
                    break;
                case 2:
                    z = SafeParcelReader.v(parcel, y);
                    break;
                case 3:
                    z2 = SafeParcelReader.v(parcel, y);
                    break;
                case 4:
                    iArr = SafeParcelReader.j(parcel, y);
                    break;
                case 5:
                    i2 = SafeParcelReader.A(parcel, y);
                    break;
                case 6:
                    iArr2 = SafeParcelReader.j(parcel, y);
                    break;
                default:
                    SafeParcelReader.E(parcel, y);
                    break;
            }
        }
        SafeParcelReader.t(parcel, F);
        return new ConnectionTelemetryConfiguration(rootTelemetryConfiguration, z, z2, iArr, i2, iArr2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i2) {
        return new ConnectionTelemetryConfiguration[i2];
    }
}
