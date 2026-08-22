package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes.dex */
public final class zzl implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int F = SafeParcelReader.F(parcel);
        Bundle bundle = null;
        ConnectionTelemetryConfiguration connectionTelemetryConfiguration = null;
        int i2 = 0;
        Feature[] featureArr = null;
        while (parcel.dataPosition() < F) {
            int y = SafeParcelReader.y(parcel);
            int u = SafeParcelReader.u(y);
            if (u == 1) {
                bundle = SafeParcelReader.f(parcel, y);
            } else if (u == 2) {
                featureArr = (Feature[]) SafeParcelReader.r(parcel, y, Feature.CREATOR);
            } else if (u == 3) {
                i2 = SafeParcelReader.A(parcel, y);
            } else if (u != 4) {
                SafeParcelReader.E(parcel, y);
            } else {
                connectionTelemetryConfiguration = (ConnectionTelemetryConfiguration) SafeParcelReader.n(parcel, y, ConnectionTelemetryConfiguration.CREATOR);
            }
        }
        SafeParcelReader.t(parcel, F);
        return new zzk(bundle, featureArr, i2, connectionTelemetryConfiguration);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i2) {
        return new zzk[i2];
    }
}
