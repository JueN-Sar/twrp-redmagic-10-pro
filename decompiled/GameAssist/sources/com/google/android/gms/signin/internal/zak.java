package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zav;

@SafeParcelable.Class
/* loaded from: classes.dex */
public final class zak extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zak> CREATOR = new zal();

    /* renamed from: c, reason: collision with root package name */
    final int f13657c;

    /* renamed from: h, reason: collision with root package name */
    private final ConnectionResult f13658h;

    /* renamed from: i, reason: collision with root package name */
    private final zav f13659i;

    zak(int i2, ConnectionResult connectionResult, zav zavVar) {
        this.f13657c = i2;
        this.f13658h = connectionResult;
        this.f13659i = zavVar;
    }

    public final ConnectionResult G() {
        return this.f13658h;
    }

    public final zav P() {
        return this.f13659i;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.g(parcel, 1, this.f13657c);
        SafeParcelWriter.l(parcel, 2, this.f13658h, i2, false);
        SafeParcelWriter.l(parcel, 3, this.f13659i, i2, false);
        SafeParcelWriter.b(parcel, a2);
    }
}
