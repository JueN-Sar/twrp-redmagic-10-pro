package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class
/* loaded from: classes.dex */
public final class zbom extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zbom> CREATOR = new zbon();

    /* renamed from: c, reason: collision with root package name */
    private final String f12903c;

    /* renamed from: h, reason: collision with root package name */
    private final String f12904h;

    /* renamed from: i, reason: collision with root package name */
    private final String f12905i;

    /* renamed from: j, reason: collision with root package name */
    private final boolean f12906j;

    /* renamed from: k, reason: collision with root package name */
    private final int f12907k;

    /* renamed from: l, reason: collision with root package name */
    private final String f12908l;

    /* renamed from: m, reason: collision with root package name */
    private final boolean f12909m;

    public zbom(String str, String str2, String str3, boolean z, int i2, String str4, boolean z2) {
        this.f12903c = str;
        this.f12904h = str2;
        this.f12905i = str3;
        this.f12908l = str4;
        this.f12907k = i2;
        this.f12906j = z;
        this.f12909m = z2;
    }

    public final String G() {
        return this.f12903c;
    }

    public final String P() {
        return this.f12908l;
    }

    public final String R() {
        return this.f12905i;
    }

    public final boolean T() {
        return this.f12909m;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        String str = this.f12903c;
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.m(parcel, 1, str, false);
        SafeParcelWriter.m(parcel, 2, this.f12904h, false);
        SafeParcelWriter.m(parcel, 3, this.f12905i, false);
        SafeParcelWriter.c(parcel, 4, this.f12906j);
        SafeParcelWriter.g(parcel, 5, this.f12907k);
        SafeParcelWriter.m(parcel, 6, this.f12908l, false);
        SafeParcelWriter.c(parcel, 7, this.f12909m);
        SafeParcelWriter.b(parcel, a2);
    }
}
