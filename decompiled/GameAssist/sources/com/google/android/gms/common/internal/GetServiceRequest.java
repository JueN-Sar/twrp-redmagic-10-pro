package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@KeepForSdk
@SafeParcelable.Class
@SafeParcelable.Reserved
/* loaded from: classes.dex */
public class GetServiceRequest extends AbstractSafeParcelable {

    @NonNull
    public static final Parcelable.Creator<GetServiceRequest> CREATOR = new zzn();
    static final Scope[] u = new Scope[0];
    static final Feature[] v = new Feature[0];

    /* renamed from: c, reason: collision with root package name */
    final int f10989c;

    /* renamed from: h, reason: collision with root package name */
    final int f10990h;

    /* renamed from: i, reason: collision with root package name */
    final int f10991i;

    /* renamed from: j, reason: collision with root package name */
    String f10992j;

    /* renamed from: k, reason: collision with root package name */
    IBinder f10993k;

    /* renamed from: l, reason: collision with root package name */
    Scope[] f10994l;

    /* renamed from: m, reason: collision with root package name */
    Bundle f10995m;

    /* renamed from: n, reason: collision with root package name */
    Account f10996n;

    /* renamed from: o, reason: collision with root package name */
    Feature[] f10997o;

    /* renamed from: p, reason: collision with root package name */
    Feature[] f10998p;

    /* renamed from: q, reason: collision with root package name */
    final boolean f10999q;

    /* renamed from: r, reason: collision with root package name */
    final int f11000r;

    /* renamed from: s, reason: collision with root package name */
    boolean f11001s;
    private final String t;

    GetServiceRequest(int i2, int i3, int i4, String str, IBinder iBinder, Scope[] scopeArr, Bundle bundle, Account account, Feature[] featureArr, Feature[] featureArr2, boolean z, int i5, boolean z2, String str2) {
        scopeArr = scopeArr == null ? u : scopeArr;
        bundle = bundle == null ? new Bundle() : bundle;
        featureArr = featureArr == null ? v : featureArr;
        featureArr2 = featureArr2 == null ? v : featureArr2;
        this.f10989c = i2;
        this.f10990h = i3;
        this.f10991i = i4;
        if ("com.google.android.gms".equals(str)) {
            this.f10992j = "com.google.android.gms";
        } else {
            this.f10992j = str;
        }
        if (i2 < 2) {
            this.f10996n = iBinder != null ? AccountAccessor.getAccountBinderSafe(IAccountAccessor.Stub.asInterface(iBinder)) : null;
        } else {
            this.f10993k = iBinder;
            this.f10996n = account;
        }
        this.f10994l = scopeArr;
        this.f10995m = bundle;
        this.f10997o = featureArr;
        this.f10998p = featureArr2;
        this.f10999q = z;
        this.f11000r = i5;
        this.f11001s = z2;
        this.t = str2;
    }

    public final String G() {
        return this.t;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        zzn.a(this, parcel, i2);
    }
}
