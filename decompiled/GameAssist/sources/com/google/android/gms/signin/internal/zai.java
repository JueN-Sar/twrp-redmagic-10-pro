package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zat;

@SafeParcelable.Class
/* loaded from: classes.dex */
public final class zai extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zai> CREATOR = new zaj();

    /* renamed from: c, reason: collision with root package name */
    final int f13655c;

    /* renamed from: h, reason: collision with root package name */
    final zat f13656h;

    zai(int i2, zat zatVar) {
        this.f13655c = i2;
        this.f13656h = zatVar;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.g(parcel, 1, this.f13655c);
        SafeParcelWriter.l(parcel, 2, this.f13656h, i2, false);
        SafeParcelWriter.b(parcel, a2);
    }
}
