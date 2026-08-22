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
public class ConnectionTelemetryConfiguration extends AbstractSafeParcelable {

    @NonNull
    @KeepForSdk
    public static final Parcelable.Creator<ConnectionTelemetryConfiguration> CREATOR = new zzm();

    /* renamed from: c, reason: collision with root package name */
    private final RootTelemetryConfiguration f10982c;

    /* renamed from: h, reason: collision with root package name */
    private final boolean f10983h;

    /* renamed from: i, reason: collision with root package name */
    private final boolean f10984i;

    /* renamed from: j, reason: collision with root package name */
    private final int[] f10985j;

    /* renamed from: k, reason: collision with root package name */
    private final int f10986k;

    /* renamed from: l, reason: collision with root package name */
    private final int[] f10987l;

    public ConnectionTelemetryConfiguration(RootTelemetryConfiguration rootTelemetryConfiguration, boolean z, boolean z2, int[] iArr, int i2, int[] iArr2) {
        this.f10982c = rootTelemetryConfiguration;
        this.f10983h = z;
        this.f10984i = z2;
        this.f10985j = iArr;
        this.f10986k = i2;
        this.f10987l = iArr2;
    }

    public int G() {
        return this.f10986k;
    }

    public int[] P() {
        return this.f10985j;
    }

    public int[] R() {
        return this.f10987l;
    }

    public boolean T() {
        return this.f10983h;
    }

    public boolean W() {
        return this.f10984i;
    }

    public final RootTelemetryConfiguration Y() {
        return this.f10982c;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.l(parcel, 1, this.f10982c, i2, false);
        SafeParcelWriter.c(parcel, 2, T());
        SafeParcelWriter.c(parcel, 3, W());
        SafeParcelWriter.h(parcel, 4, P(), false);
        SafeParcelWriter.g(parcel, 5, G());
        SafeParcelWriter.h(parcel, 6, R(), false);
        SafeParcelWriter.b(parcel, a2);
    }
}
