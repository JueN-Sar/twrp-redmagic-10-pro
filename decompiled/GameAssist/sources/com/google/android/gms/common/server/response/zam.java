package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.server.response.FastJsonResponse;

@ShowFirstParty
@SafeParcelable.Class
/* loaded from: classes.dex */
public final class zam extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zam> CREATOR = new zak();

    /* renamed from: c, reason: collision with root package name */
    final int f11230c;

    /* renamed from: h, reason: collision with root package name */
    final String f11231h;

    /* renamed from: i, reason: collision with root package name */
    final FastJsonResponse.Field f11232i;

    zam(int i2, String str, FastJsonResponse.Field field) {
        this.f11230c = i2;
        this.f11231h = str;
        this.f11232i = field;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int i3 = this.f11230c;
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.g(parcel, 1, i3);
        SafeParcelWriter.m(parcel, 2, this.f11231h, false);
        SafeParcelWriter.l(parcel, 3, this.f11232i, i2, false);
        SafeParcelWriter.b(parcel, a2);
    }

    zam(String str, FastJsonResponse.Field field) {
        this.f11230c = 1;
        this.f11231h = str;
        this.f11232i = field;
    }
}
