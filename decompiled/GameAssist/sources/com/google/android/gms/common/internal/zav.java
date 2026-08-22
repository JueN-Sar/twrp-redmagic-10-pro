package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class
/* loaded from: classes.dex */
public final class zav extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zav> CREATOR = new zaw();

    /* renamed from: c, reason: collision with root package name */
    final int f11079c;

    /* renamed from: h, reason: collision with root package name */
    final IBinder f11080h;

    /* renamed from: i, reason: collision with root package name */
    private final ConnectionResult f11081i;

    /* renamed from: j, reason: collision with root package name */
    private final boolean f11082j;

    /* renamed from: k, reason: collision with root package name */
    private final boolean f11083k;

    zav(int i2, IBinder iBinder, ConnectionResult connectionResult, boolean z, boolean z2) {
        this.f11079c = i2;
        this.f11080h = iBinder;
        this.f11081i = connectionResult;
        this.f11082j = z;
        this.f11083k = z2;
    }

    public final ConnectionResult G() {
        return this.f11081i;
    }

    public final IAccountAccessor P() {
        IBinder iBinder = this.f11080h;
        if (iBinder == null) {
            return null;
        }
        return IAccountAccessor.Stub.asInterface(iBinder);
    }

    public final boolean R() {
        return this.f11082j;
    }

    public final boolean T() {
        return this.f11083k;
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zav)) {
            return false;
        }
        zav zavVar = (zav) obj;
        return this.f11081i.equals(zavVar.f11081i) && Objects.a(P(), zavVar.P());
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.g(parcel, 1, this.f11079c);
        SafeParcelWriter.f(parcel, 2, this.f11080h, false);
        SafeParcelWriter.l(parcel, 3, this.f11081i, i2, false);
        SafeParcelWriter.c(parcel, 4, this.f11082j);
        SafeParcelWriter.c(parcel, 5, this.f11083k);
        SafeParcelWriter.b(parcel, a2);
    }
}
