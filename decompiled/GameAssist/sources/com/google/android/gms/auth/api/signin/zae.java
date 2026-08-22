package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.internal.GoogleSignInOptionsExtensionParcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class zae implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int F = SafeParcelReader.F(parcel);
        ArrayList arrayList = null;
        Account account = null;
        String str = null;
        String str2 = null;
        ArrayList arrayList2 = null;
        String str3 = null;
        int i2 = 0;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        while (parcel.dataPosition() < F) {
            int y = SafeParcelReader.y(parcel);
            switch (SafeParcelReader.u(y)) {
                case 1:
                    i2 = SafeParcelReader.A(parcel, y);
                    break;
                case 2:
                    arrayList = SafeParcelReader.s(parcel, y, Scope.CREATOR);
                    break;
                case 3:
                    account = (Account) SafeParcelReader.n(parcel, y, Account.CREATOR);
                    break;
                case 4:
                    z = SafeParcelReader.v(parcel, y);
                    break;
                case 5:
                    z2 = SafeParcelReader.v(parcel, y);
                    break;
                case 6:
                    z3 = SafeParcelReader.v(parcel, y);
                    break;
                case 7:
                    str = SafeParcelReader.o(parcel, y);
                    break;
                case 8:
                    str2 = SafeParcelReader.o(parcel, y);
                    break;
                case 9:
                    arrayList2 = SafeParcelReader.s(parcel, y, GoogleSignInOptionsExtensionParcelable.CREATOR);
                    break;
                case 10:
                    str3 = SafeParcelReader.o(parcel, y);
                    break;
                default:
                    SafeParcelReader.E(parcel, y);
                    break;
            }
        }
        SafeParcelReader.t(parcel, F);
        return new GoogleSignInOptions(i2, arrayList, account, z, z2, z3, str, str2, arrayList2, str3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i2) {
        return new GoogleSignInOptions[i2];
    }
}
