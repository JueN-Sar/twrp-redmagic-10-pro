package com.google.android.gms.internal.mlkit_vision_text_common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class
@SafeParcelable.Reserved
/* loaded from: classes.dex */
public final class zzl extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzl> CREATOR = new zzm();

    /* renamed from: c, reason: collision with root package name */
    public final zzr[] f13388c;

    /* renamed from: h, reason: collision with root package name */
    public final zzf f13389h;

    /* renamed from: i, reason: collision with root package name */
    public final zzf f13390i;

    /* renamed from: j, reason: collision with root package name */
    public final zzf f13391j;

    /* renamed from: k, reason: collision with root package name */
    public final String f13392k;

    /* renamed from: l, reason: collision with root package name */
    public final float f13393l;

    /* renamed from: m, reason: collision with root package name */
    public final String f13394m;

    /* renamed from: n, reason: collision with root package name */
    public final int f13395n;

    /* renamed from: o, reason: collision with root package name */
    public final boolean f13396o;

    /* renamed from: p, reason: collision with root package name */
    public final int f13397p;

    /* renamed from: q, reason: collision with root package name */
    public final int f13398q;

    public zzl(zzr[] zzrVarArr, zzf zzfVar, zzf zzfVar2, zzf zzfVar3, String str, float f2, String str2, int i2, boolean z, int i3, int i4) {
        this.f13388c = zzrVarArr;
        this.f13389h = zzfVar;
        this.f13390i = zzfVar2;
        this.f13391j = zzfVar3;
        this.f13392k = str;
        this.f13393l = f2;
        this.f13394m = str2;
        this.f13395n = i2;
        this.f13396o = z;
        this.f13397p = i3;
        this.f13398q = i4;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        zzr[] zzrVarArr = this.f13388c;
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.p(parcel, 2, zzrVarArr, i2, false);
        SafeParcelWriter.l(parcel, 3, this.f13389h, i2, false);
        SafeParcelWriter.l(parcel, 4, this.f13390i, i2, false);
        SafeParcelWriter.l(parcel, 5, this.f13391j, i2, false);
        SafeParcelWriter.m(parcel, 6, this.f13392k, false);
        SafeParcelWriter.e(parcel, 7, this.f13393l);
        SafeParcelWriter.m(parcel, 8, this.f13394m, false);
        SafeParcelWriter.g(parcel, 9, this.f13395n);
        SafeParcelWriter.c(parcel, 10, this.f13396o);
        SafeParcelWriter.g(parcel, 11, this.f13397p);
        SafeParcelWriter.g(parcel, 12, this.f13398q);
        SafeParcelWriter.b(parcel, a2);
    }
}
