package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class
/* loaded from: classes.dex */
public final class zbnx extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zbnx> CREATOR = new zbny();

    /* renamed from: c, reason: collision with root package name */
    private final int f12876c;

    /* renamed from: h, reason: collision with root package name */
    private final int f12877h;

    /* renamed from: i, reason: collision with root package name */
    private final int f12878i;

    /* renamed from: j, reason: collision with root package name */
    private final int f12879j;

    /* renamed from: k, reason: collision with root package name */
    private final long f12880k;

    public zbnx(int i2, int i3, int i4, int i5, long j2) {
        this.f12876c = i2;
        this.f12877h = i3;
        this.f12878i = i4;
        this.f12879j = i5;
        this.f12880k = j2;
    }

    public final int G() {
        return this.f12878i;
    }

    public final int P() {
        return this.f12876c;
    }

    public final int R() {
        return this.f12879j;
    }

    public final int T() {
        return this.f12877h;
    }

    public final long W() {
        return this.f12880k;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.g(parcel, 1, this.f12876c);
        SafeParcelWriter.g(parcel, 2, this.f12877h);
        SafeParcelWriter.g(parcel, 3, this.f12878i);
        SafeParcelWriter.g(parcel, 4, this.f12879j);
        SafeParcelWriter.i(parcel, 5, this.f12880k);
        SafeParcelWriter.b(parcel, a2);
    }
}
