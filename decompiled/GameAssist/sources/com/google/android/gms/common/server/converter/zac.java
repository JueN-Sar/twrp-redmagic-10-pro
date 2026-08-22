package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class
/* loaded from: classes.dex */
public final class zac extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zac> CREATOR = new zae();

    /* renamed from: c, reason: collision with root package name */
    final int f11192c;

    /* renamed from: h, reason: collision with root package name */
    final String f11193h;

    /* renamed from: i, reason: collision with root package name */
    final int f11194i;

    zac(int i2, String str, int i3) {
        this.f11192c = i2;
        this.f11193h = str;
        this.f11194i = i3;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int i3 = this.f11192c;
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.g(parcel, 1, i3);
        SafeParcelWriter.m(parcel, 2, this.f11193h, false);
        SafeParcelWriter.g(parcel, 3, this.f11194i);
        SafeParcelWriter.b(parcel, a2);
    }

    zac(String str, int i2) {
        this.f11192c = 1;
        this.f11193h = str;
        this.f11194i = i2;
    }
}
