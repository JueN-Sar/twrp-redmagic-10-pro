package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class
@SafeParcelable.Reserved
/* loaded from: classes.dex */
public final class zbf extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zbf> CREATOR = new zbg();

    /* renamed from: c, reason: collision with root package name */
    public final zbj[] f12779c;

    /* renamed from: h, reason: collision with root package name */
    public final zbd f12780h;

    /* renamed from: i, reason: collision with root package name */
    public final zbd f12781i;

    /* renamed from: j, reason: collision with root package name */
    public final zbd f12782j;

    /* renamed from: k, reason: collision with root package name */
    public final String f12783k;

    /* renamed from: l, reason: collision with root package name */
    public final float f12784l;

    /* renamed from: m, reason: collision with root package name */
    public final String f12785m;

    /* renamed from: n, reason: collision with root package name */
    public final int f12786n;

    /* renamed from: o, reason: collision with root package name */
    public final boolean f12787o;

    /* renamed from: p, reason: collision with root package name */
    public final int f12788p;

    /* renamed from: q, reason: collision with root package name */
    public final int f12789q;

    public zbf(zbj[] zbjVarArr, zbd zbdVar, zbd zbdVar2, zbd zbdVar3, String str, float f2, String str2, int i2, boolean z, int i3, int i4) {
        this.f12779c = zbjVarArr;
        this.f12780h = zbdVar;
        this.f12781i = zbdVar2;
        this.f12782j = zbdVar3;
        this.f12783k = str;
        this.f12784l = f2;
        this.f12785m = str2;
        this.f12786n = i2;
        this.f12787o = z;
        this.f12788p = i3;
        this.f12789q = i4;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        zbj[] zbjVarArr = this.f12779c;
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.p(parcel, 2, zbjVarArr, i2, false);
        SafeParcelWriter.l(parcel, 3, this.f12780h, i2, false);
        SafeParcelWriter.l(parcel, 4, this.f12781i, i2, false);
        SafeParcelWriter.l(parcel, 5, this.f12782j, i2, false);
        SafeParcelWriter.m(parcel, 6, this.f12783k, false);
        SafeParcelWriter.e(parcel, 7, this.f12784l);
        SafeParcelWriter.m(parcel, 8, this.f12785m, false);
        SafeParcelWriter.g(parcel, 9, this.f12786n);
        SafeParcelWriter.c(parcel, 10, this.f12787o);
        SafeParcelWriter.g(parcel, 11, this.f12788p);
        SafeParcelWriter.g(parcel, 12, this.f12789q);
        SafeParcelWriter.b(parcel, a2);
    }
}
