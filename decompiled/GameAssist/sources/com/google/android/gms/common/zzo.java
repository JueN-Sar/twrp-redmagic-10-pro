package com.google.android.gms.common;

import android.content.Context;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

@SafeParcelable.Class
/* loaded from: classes.dex */
public final class zzo extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzo> CREATOR = new zzp();

    /* renamed from: c, reason: collision with root package name */
    private final String f11312c;

    /* renamed from: h, reason: collision with root package name */
    private final boolean f11313h;

    /* renamed from: i, reason: collision with root package name */
    private final boolean f11314i;

    /* renamed from: j, reason: collision with root package name */
    private final Context f11315j;

    /* renamed from: k, reason: collision with root package name */
    private final boolean f11316k;

    /* renamed from: l, reason: collision with root package name */
    private final boolean f11317l;

    zzo(String str, boolean z, boolean z2, IBinder iBinder, boolean z3, boolean z4) {
        this.f11312c = str;
        this.f11313h = z;
        this.f11314i = z2;
        this.f11315j = (Context) ObjectWrapper.unwrap(IObjectWrapper.Stub.asInterface(iBinder));
        this.f11316k = z3;
        this.f11317l = z4;
    }

    /* JADX WARN: Type inference failed for: r5v5, types: [android.os.IBinder, com.google.android.gms.dynamic.IObjectWrapper] */
    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        String str = this.f11312c;
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.m(parcel, 1, str, false);
        SafeParcelWriter.c(parcel, 2, this.f11313h);
        SafeParcelWriter.c(parcel, 3, this.f11314i);
        SafeParcelWriter.f(parcel, 4, ObjectWrapper.wrap(this.f11315j), false);
        SafeParcelWriter.c(parcel, 5, this.f11316k);
        SafeParcelWriter.c(parcel, 6, this.f11317l);
        SafeParcelWriter.b(parcel, a2);
    }
}
