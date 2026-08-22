package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class
@SafeParcelable.Reserved
/* loaded from: classes.dex */
public final class zbd extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zbd> CREATOR = new zbe();

    /* renamed from: c, reason: collision with root package name */
    public final int f12755c;

    /* renamed from: h, reason: collision with root package name */
    public final int f12756h;

    /* renamed from: i, reason: collision with root package name */
    public final int f12757i;

    /* renamed from: j, reason: collision with root package name */
    public final int f12758j;

    /* renamed from: k, reason: collision with root package name */
    public final float f12759k;

    public zbd(int i2, int i3, int i4, int i5, float f2) {
        this.f12755c = i2;
        this.f12756h = i3;
        this.f12757i = i4;
        this.f12758j = i5;
        this.f12759k = f2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int i3 = this.f12755c;
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.g(parcel, 2, i3);
        SafeParcelWriter.g(parcel, 3, this.f12756h);
        SafeParcelWriter.g(parcel, 4, this.f12757i);
        SafeParcelWriter.g(parcel, 5, this.f12758j);
        SafeParcelWriter.e(parcel, 6, this.f12759k);
        SafeParcelWriter.b(parcel, a2);
    }
}
