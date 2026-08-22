package com.google.android.gms.internal.mlkit_vision_text_common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class
/* loaded from: classes.dex */
public final class zzvh extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzvh> CREATOR = new zzvi();

    /* renamed from: c, reason: collision with root package name */
    private final String f13619c;

    /* renamed from: h, reason: collision with root package name */
    private final String f13620h;

    /* renamed from: i, reason: collision with root package name */
    private final String f13621i;

    /* renamed from: j, reason: collision with root package name */
    private final boolean f13622j;

    /* renamed from: k, reason: collision with root package name */
    private final int f13623k;

    /* renamed from: l, reason: collision with root package name */
    private final String f13624l;

    /* renamed from: m, reason: collision with root package name */
    private final boolean f13625m;

    public zzvh(String str, String str2, String str3, boolean z, int i2, String str4, boolean z2) {
        this.f13619c = str;
        this.f13620h = str2;
        this.f13621i = str3;
        this.f13624l = str4;
        this.f13623k = i2;
        this.f13622j = z;
        this.f13625m = z2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        String str = this.f13619c;
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.m(parcel, 1, str, false);
        SafeParcelWriter.m(parcel, 2, this.f13620h, false);
        SafeParcelWriter.m(parcel, 3, this.f13621i, false);
        SafeParcelWriter.c(parcel, 4, this.f13622j);
        SafeParcelWriter.g(parcel, 5, this.f13623k);
        SafeParcelWriter.m(parcel, 6, this.f13624l, false);
        SafeParcelWriter.c(parcel, 7, this.f13625m);
        SafeParcelWriter.b(parcel, a2);
    }
}
