package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class
/* loaded from: classes.dex */
public final class zax extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zax> CREATOR = new zay();

    /* renamed from: c, reason: collision with root package name */
    final int f11084c;

    /* renamed from: h, reason: collision with root package name */
    private final int f11085h;

    /* renamed from: i, reason: collision with root package name */
    private final int f11086i;

    /* renamed from: j, reason: collision with root package name */
    private final Scope[] f11087j;

    zax(int i2, int i3, int i4, Scope[] scopeArr) {
        this.f11084c = i2;
        this.f11085h = i3;
        this.f11086i = i4;
        this.f11087j = scopeArr;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int i3 = this.f11084c;
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.g(parcel, 1, i3);
        SafeParcelWriter.g(parcel, 2, this.f11085h);
        SafeParcelWriter.g(parcel, 3, this.f11086i);
        SafeParcelWriter.p(parcel, 4, this.f11087j, i2, false);
        SafeParcelWriter.b(parcel, a2);
    }
}
