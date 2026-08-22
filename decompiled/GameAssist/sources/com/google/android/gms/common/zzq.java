package com.google.android.gms.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class
/* loaded from: classes.dex */
public final class zzq extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzq> CREATOR = new zzr();

    /* renamed from: c, reason: collision with root package name */
    private final boolean f11318c;

    /* renamed from: h, reason: collision with root package name */
    private final String f11319h;

    /* renamed from: i, reason: collision with root package name */
    private final int f11320i;

    /* renamed from: j, reason: collision with root package name */
    private final int f11321j;

    zzq(boolean z, String str, int i2, int i3) {
        this.f11318c = z;
        this.f11319h = str;
        this.f11320i = zzy.a(i2) - 1;
        this.f11321j = zzd.a(i3) - 1;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.c(parcel, 1, this.f11318c);
        SafeParcelWriter.m(parcel, 2, this.f11319h, false);
        SafeParcelWriter.g(parcel, 3, this.f11320i);
        SafeParcelWriter.g(parcel, 4, this.f11321j);
        SafeParcelWriter.b(parcel, a2);
    }
}
