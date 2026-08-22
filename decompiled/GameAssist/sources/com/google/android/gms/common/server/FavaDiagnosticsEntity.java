package com.google.android.gms.common.server;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@KeepForSdk
@SafeParcelable.Class
/* loaded from: classes.dex */
public class FavaDiagnosticsEntity extends AbstractSafeParcelable implements ReflectedParcelable {

    @NonNull
    @KeepForSdk
    public static final Parcelable.Creator<FavaDiagnosticsEntity> CREATOR = new zaa();

    /* renamed from: c, reason: collision with root package name */
    final int f11184c;

    /* renamed from: h, reason: collision with root package name */
    public final String f11185h;

    /* renamed from: i, reason: collision with root package name */
    public final int f11186i;

    public FavaDiagnosticsEntity(int i2, String str, int i3) {
        this.f11184c = i2;
        this.f11185h = str;
        this.f11186i = i3;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int i3 = this.f11184c;
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.g(parcel, 1, i3);
        SafeParcelWriter.m(parcel, 2, this.f11185h, false);
        SafeParcelWriter.g(parcel, 3, this.f11186i);
        SafeParcelWriter.b(parcel, a2);
    }
}
