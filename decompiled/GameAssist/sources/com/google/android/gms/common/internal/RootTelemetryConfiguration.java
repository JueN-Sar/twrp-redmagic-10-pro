package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@KeepForSdk
@SafeParcelable.Class
/* loaded from: classes.dex */
public class RootTelemetryConfiguration extends AbstractSafeParcelable {

    @NonNull
    @KeepForSdk
    public static final Parcelable.Creator<RootTelemetryConfiguration> CREATOR = new zzaj();

    /* renamed from: c, reason: collision with root package name */
    private final int f11027c;

    /* renamed from: h, reason: collision with root package name */
    private final boolean f11028h;

    /* renamed from: i, reason: collision with root package name */
    private final boolean f11029i;

    /* renamed from: j, reason: collision with root package name */
    private final int f11030j;

    /* renamed from: k, reason: collision with root package name */
    private final int f11031k;

    public RootTelemetryConfiguration(int i2, boolean z, boolean z2, int i3, int i4) {
        this.f11027c = i2;
        this.f11028h = z;
        this.f11029i = z2;
        this.f11030j = i3;
        this.f11031k = i4;
    }

    public int G() {
        return this.f11030j;
    }

    public int P() {
        return this.f11031k;
    }

    public boolean R() {
        return this.f11028h;
    }

    public boolean T() {
        return this.f11029i;
    }

    public int W() {
        return this.f11027c;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.g(parcel, 1, W());
        SafeParcelWriter.c(parcel, 2, R());
        SafeParcelWriter.c(parcel, 3, T());
        SafeParcelWriter.g(parcel, 4, G());
        SafeParcelWriter.g(parcel, 5, P());
        SafeParcelWriter.b(parcel, a2);
    }
}
