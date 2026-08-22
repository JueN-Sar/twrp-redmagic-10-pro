package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

@SafeParcelable.Class
/* loaded from: classes.dex */
public final class zag extends AbstractSafeParcelable implements Result {
    public static final Parcelable.Creator<zag> CREATOR = new zah();

    /* renamed from: c, reason: collision with root package name */
    private final List f13653c;

    /* renamed from: h, reason: collision with root package name */
    private final String f13654h;

    public zag(List list, String str) {
        this.f13653c = list;
        this.f13654h = str;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status a() {
        return this.f13654h != null ? Status.f10543l : Status.f10547p;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        List list = this.f13653c;
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.o(parcel, 1, list, false);
        SafeParcelWriter.m(parcel, 2, this.f13654h, false);
        SafeParcelWriter.b(parcel, a2);
    }
}
