package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class
/* loaded from: classes.dex */
public final class zzk extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzk> CREATOR = new zzl();

    /* renamed from: c, reason: collision with root package name */
    Bundle f11105c;

    /* renamed from: h, reason: collision with root package name */
    Feature[] f11106h;

    /* renamed from: i, reason: collision with root package name */
    int f11107i;

    /* renamed from: j, reason: collision with root package name */
    ConnectionTelemetryConfiguration f11108j;

    zzk(Bundle bundle, Feature[] featureArr, int i2, ConnectionTelemetryConfiguration connectionTelemetryConfiguration) {
        this.f11105c = bundle;
        this.f11106h = featureArr;
        this.f11107i = i2;
        this.f11108j = connectionTelemetryConfiguration;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.d(parcel, 1, this.f11105c, false);
        SafeParcelWriter.p(parcel, 2, this.f11106h, i2, false);
        SafeParcelWriter.g(parcel, 3, this.f11107i);
        SafeParcelWriter.l(parcel, 4, this.f11108j, i2, false);
        SafeParcelWriter.b(parcel, a2);
    }
}
