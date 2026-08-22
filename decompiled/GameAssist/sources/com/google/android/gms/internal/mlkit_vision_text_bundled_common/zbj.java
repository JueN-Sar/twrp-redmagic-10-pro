package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class
@SafeParcelable.Reserved
/* loaded from: classes.dex */
public final class zbj extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zbj> CREATOR = new zbk();

    /* renamed from: c, reason: collision with root package name */
    public final zbh[] f12822c;

    /* renamed from: h, reason: collision with root package name */
    public final zbd f12823h;

    /* renamed from: i, reason: collision with root package name */
    public final zbd f12824i;

    /* renamed from: j, reason: collision with root package name */
    public final String f12825j;

    /* renamed from: k, reason: collision with root package name */
    public final float f12826k;

    /* renamed from: l, reason: collision with root package name */
    public final String f12827l;

    /* renamed from: m, reason: collision with root package name */
    public final boolean f12828m;

    public zbj(zbh[] zbhVarArr, zbd zbdVar, zbd zbdVar2, String str, float f2, String str2, boolean z) {
        this.f12822c = zbhVarArr;
        this.f12823h = zbdVar;
        this.f12824i = zbdVar2;
        this.f12825j = str;
        this.f12826k = f2;
        this.f12827l = str2;
        this.f12828m = z;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        zbh[] zbhVarArr = this.f12822c;
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.p(parcel, 2, zbhVarArr, i2, false);
        SafeParcelWriter.l(parcel, 3, this.f12823h, i2, false);
        SafeParcelWriter.l(parcel, 4, this.f12824i, i2, false);
        SafeParcelWriter.m(parcel, 5, this.f12825j, false);
        SafeParcelWriter.e(parcel, 6, this.f12826k);
        SafeParcelWriter.m(parcel, 7, this.f12827l, false);
        SafeParcelWriter.c(parcel, 8, this.f12828m);
        SafeParcelWriter.b(parcel, a2);
    }
}
