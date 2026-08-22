package com.google.android.gms.common.internal;

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
public class MethodInvocation extends AbstractSafeParcelable {

    @NonNull
    public static final Parcelable.Creator<MethodInvocation> CREATOR = new zan();

    /* renamed from: c, reason: collision with root package name */
    private final int f11011c;

    /* renamed from: h, reason: collision with root package name */
    private final int f11012h;

    /* renamed from: i, reason: collision with root package name */
    private final int f11013i;

    /* renamed from: j, reason: collision with root package name */
    private final long f11014j;

    /* renamed from: k, reason: collision with root package name */
    private final long f11015k;

    /* renamed from: l, reason: collision with root package name */
    private final String f11016l;

    /* renamed from: m, reason: collision with root package name */
    private final String f11017m;

    /* renamed from: n, reason: collision with root package name */
    private final int f11018n;

    /* renamed from: o, reason: collision with root package name */
    private final int f11019o;

    public MethodInvocation(int i2, int i3, int i4, long j2, long j3, String str, String str2, int i5, int i6) {
        this.f11011c = i2;
        this.f11012h = i3;
        this.f11013i = i4;
        this.f11014j = j2;
        this.f11015k = j3;
        this.f11016l = str;
        this.f11017m = str2;
        this.f11018n = i5;
        this.f11019o = i6;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int i3 = this.f11011c;
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.g(parcel, 1, i3);
        SafeParcelWriter.g(parcel, 2, this.f11012h);
        SafeParcelWriter.g(parcel, 3, this.f11013i);
        SafeParcelWriter.i(parcel, 4, this.f11014j);
        SafeParcelWriter.i(parcel, 5, this.f11015k);
        SafeParcelWriter.m(parcel, 6, this.f11016l, false);
        SafeParcelWriter.m(parcel, 7, this.f11017m, false);
        SafeParcelWriter.g(parcel, 8, this.f11018n);
        SafeParcelWriter.g(parcel, 9, this.f11019o);
        SafeParcelWriter.b(parcel, a2);
    }
}
