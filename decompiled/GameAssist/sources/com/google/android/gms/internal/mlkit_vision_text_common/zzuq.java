package com.google.android.gms.internal.mlkit_vision_text_common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class
/* loaded from: classes.dex */
public final class zzuq extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzuq> CREATOR = new zzur();

    /* renamed from: c, reason: collision with root package name */
    private final int f13592c;

    /* renamed from: h, reason: collision with root package name */
    private final int f13593h;

    /* renamed from: i, reason: collision with root package name */
    private final int f13594i;

    /* renamed from: j, reason: collision with root package name */
    private final int f13595j;

    /* renamed from: k, reason: collision with root package name */
    private final long f13596k;

    public zzuq(int i2, int i3, int i4, int i5, long j2) {
        this.f13592c = i2;
        this.f13593h = i3;
        this.f13594i = i4;
        this.f13595j = i5;
        this.f13596k = j2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int i3 = this.f13592c;
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.g(parcel, 1, i3);
        SafeParcelWriter.g(parcel, 2, this.f13593h);
        SafeParcelWriter.g(parcel, 3, this.f13594i);
        SafeParcelWriter.g(parcel, 4, this.f13595j);
        SafeParcelWriter.i(parcel, 5, this.f13596k);
        SafeParcelWriter.b(parcel, a2);
    }
}
