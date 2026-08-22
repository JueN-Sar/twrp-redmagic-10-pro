package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class
@Deprecated
/* loaded from: classes.dex */
public final class zzak extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzak> CREATOR = new zzal();

    /* renamed from: c, reason: collision with root package name */
    final int f11096c;

    zzak(int i2) {
        this.f11096c = i2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int i3 = this.f11096c;
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.g(parcel, 1, i3);
        SafeParcelWriter.b(parcel, a2);
    }
}
