package com.google.mlkit.vision.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@KeepForSdk
@SafeParcelable.Class
/* loaded from: classes.dex */
public class VisionImageMetadataParcel extends AbstractSafeParcelable {

    @NonNull
    public static final Parcelable.Creator<VisionImageMetadataParcel> CREATOR = new zzg();

    /* renamed from: c, reason: collision with root package name */
    public final int f16073c;

    /* renamed from: h, reason: collision with root package name */
    public final int f16074h;

    /* renamed from: i, reason: collision with root package name */
    public final long f16075i;

    /* renamed from: j, reason: collision with root package name */
    public final int f16076j;

    /* renamed from: k, reason: collision with root package name */
    public final int f16077k;

    public VisionImageMetadataParcel(int i2, int i3, int i4, long j2, int i5) {
        this.f16073c = i2;
        this.f16074h = i3;
        this.f16077k = i4;
        this.f16075i = j2;
        this.f16076j = i5;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.g(parcel, 1, this.f16073c);
        SafeParcelWriter.g(parcel, 2, this.f16074h);
        SafeParcelWriter.g(parcel, 3, this.f16077k);
        SafeParcelWriter.i(parcel, 4, this.f16075i);
        SafeParcelWriter.g(parcel, 5, this.f16076j);
        SafeParcelWriter.b(parcel, a2);
    }
}
