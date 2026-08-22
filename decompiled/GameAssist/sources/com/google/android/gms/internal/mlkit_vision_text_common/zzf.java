package com.google.android.gms.internal.mlkit_vision_text_common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class
@SafeParcelable.Reserved
/* loaded from: classes.dex */
public final class zzf extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzf> CREATOR = new zzg();

    /* renamed from: c, reason: collision with root package name */
    public final int f13181c;

    /* renamed from: h, reason: collision with root package name */
    public final int f13182h;

    /* renamed from: i, reason: collision with root package name */
    public final int f13183i;

    /* renamed from: j, reason: collision with root package name */
    public final int f13184j;

    /* renamed from: k, reason: collision with root package name */
    public final float f13185k;

    public zzf(int i2, int i3, int i4, int i5, float f2) {
        this.f13181c = i2;
        this.f13182h = i3;
        this.f13183i = i4;
        this.f13184j = i5;
        this.f13185k = f2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int i3 = this.f13181c;
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.g(parcel, 2, i3);
        SafeParcelWriter.g(parcel, 3, this.f13182h);
        SafeParcelWriter.g(parcel, 4, this.f13183i);
        SafeParcelWriter.g(parcel, 5, this.f13184j);
        SafeParcelWriter.e(parcel, 6, this.f13185k);
        SafeParcelWriter.b(parcel, a2);
    }
}
