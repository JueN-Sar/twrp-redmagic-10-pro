package com.google.android.gms.internal.mlkit_vision_text_common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class
@SafeParcelable.Reserved
/* loaded from: classes.dex */
public final class zzd extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzd> CREATOR = new zze();

    /* renamed from: c, reason: collision with root package name */
    public int f13148c;

    /* renamed from: h, reason: collision with root package name */
    public int f13149h;

    /* renamed from: i, reason: collision with root package name */
    public int f13150i;

    /* renamed from: j, reason: collision with root package name */
    public long f13151j;

    /* renamed from: k, reason: collision with root package name */
    public int f13152k;

    public zzd(int i2, int i3, int i4, long j2, int i5) {
        this.f13148c = i2;
        this.f13149h = i3;
        this.f13150i = i4;
        this.f13151j = j2;
        this.f13152k = i5;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.g(parcel, 2, this.f13148c);
        SafeParcelWriter.g(parcel, 3, this.f13149h);
        SafeParcelWriter.g(parcel, 4, this.f13150i);
        SafeParcelWriter.i(parcel, 5, this.f13151j);
        SafeParcelWriter.g(parcel, 6, this.f13152k);
        SafeParcelWriter.b(parcel, a2);
    }
}
