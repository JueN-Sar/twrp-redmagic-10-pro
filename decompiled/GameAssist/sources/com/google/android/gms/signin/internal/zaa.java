package com.google.android.gms.signin.internal;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class
/* loaded from: classes.dex */
public final class zaa extends AbstractSafeParcelable implements Result {
    public static final Parcelable.Creator<zaa> CREATOR = new zab();

    /* renamed from: c, reason: collision with root package name */
    final int f13650c;

    /* renamed from: h, reason: collision with root package name */
    private int f13651h;

    /* renamed from: i, reason: collision with root package name */
    private Intent f13652i;

    zaa(int i2, int i3, Intent intent) {
        this.f13650c = i2;
        this.f13651h = i3;
        this.f13652i = intent;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status a() {
        return this.f13651h == 0 ? Status.f10543l : Status.f10547p;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int i3 = this.f13650c;
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.g(parcel, 1, i3);
        SafeParcelWriter.g(parcel, 2, this.f13651h);
        SafeParcelWriter.l(parcel, 3, this.f13652i, i2, false);
        SafeParcelWriter.b(parcel, a2);
    }
}
