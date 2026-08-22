package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class
/* loaded from: classes.dex */
public final class zat extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zat> CREATOR = new zau();

    /* renamed from: c, reason: collision with root package name */
    final int f11075c;

    /* renamed from: h, reason: collision with root package name */
    private final Account f11076h;

    /* renamed from: i, reason: collision with root package name */
    private final int f11077i;

    /* renamed from: j, reason: collision with root package name */
    private final GoogleSignInAccount f11078j;

    zat(int i2, Account account, int i3, GoogleSignInAccount googleSignInAccount) {
        this.f11075c = i2;
        this.f11076h = account;
        this.f11077i = i3;
        this.f11078j = googleSignInAccount;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int i3 = this.f11075c;
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.g(parcel, 1, i3);
        SafeParcelWriter.l(parcel, 2, this.f11076h, i2, false);
        SafeParcelWriter.g(parcel, 3, this.f11077i);
        SafeParcelWriter.l(parcel, 4, this.f11078j, i2, false);
        SafeParcelWriter.b(parcel, a2);
    }

    public zat(Account account, int i2, GoogleSignInAccount googleSignInAccount) {
        this(2, account, i2, googleSignInAccount);
    }
}
