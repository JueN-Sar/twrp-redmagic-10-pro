package com.google.android.gms.internal.mlkit_vision_text_common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class
@SafeParcelable.Reserved
/* loaded from: classes.dex */
public final class zzr extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzr> CREATOR = new zzs();

    /* renamed from: c, reason: collision with root package name */
    public final zzn[] f13504c;

    /* renamed from: h, reason: collision with root package name */
    public final zzf f13505h;

    /* renamed from: i, reason: collision with root package name */
    public final zzf f13506i;

    /* renamed from: j, reason: collision with root package name */
    public final String f13507j;

    /* renamed from: k, reason: collision with root package name */
    public final float f13508k;

    /* renamed from: l, reason: collision with root package name */
    public final String f13509l;

    /* renamed from: m, reason: collision with root package name */
    public final boolean f13510m;

    public zzr(zzn[] zznVarArr, zzf zzfVar, zzf zzfVar2, String str, float f2, String str2, boolean z) {
        this.f13504c = zznVarArr;
        this.f13505h = zzfVar;
        this.f13506i = zzfVar2;
        this.f13507j = str;
        this.f13508k = f2;
        this.f13509l = str2;
        this.f13510m = z;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        zzn[] zznVarArr = this.f13504c;
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.p(parcel, 2, zznVarArr, i2, false);
        SafeParcelWriter.l(parcel, 3, this.f13505h, i2, false);
        SafeParcelWriter.l(parcel, 4, this.f13506i, i2, false);
        SafeParcelWriter.m(parcel, 5, this.f13507j, false);
        SafeParcelWriter.e(parcel, 6, this.f13508k);
        SafeParcelWriter.m(parcel, 7, this.f13509l, false);
        SafeParcelWriter.c(parcel, 8, this.f13510m);
        SafeParcelWriter.b(parcel, a2);
    }
}
