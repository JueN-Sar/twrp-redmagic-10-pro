package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes.dex */
public final class zau implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int F = SafeParcelReader.F(parcel);
        Account account = null;
        int i2 = 0;
        int i3 = 0;
        GoogleSignInAccount googleSignInAccount = null;
        while (parcel.dataPosition() < F) {
            int y = SafeParcelReader.y(parcel);
            int u = SafeParcelReader.u(y);
            if (u == 1) {
                i2 = SafeParcelReader.A(parcel, y);
            } else if (u == 2) {
                account = (Account) SafeParcelReader.n(parcel, y, Account.CREATOR);
            } else if (u == 3) {
                i3 = SafeParcelReader.A(parcel, y);
            } else if (u != 4) {
                SafeParcelReader.E(parcel, y);
            } else {
                googleSignInAccount = (GoogleSignInAccount) SafeParcelReader.n(parcel, y, GoogleSignInAccount.CREATOR);
            }
        }
        SafeParcelReader.t(parcel, F);
        return new zat(i2, account, i3, googleSignInAccount);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i2) {
        return new zat[i2];
    }
}
