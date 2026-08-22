package com.google.android.gms.auth.api.signin;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class zab implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int F = SafeParcelReader.F(parcel);
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        Uri uri = null;
        String str5 = null;
        String str6 = null;
        ArrayList arrayList = null;
        String str7 = null;
        String str8 = null;
        long j2 = 0;
        int i2 = 0;
        while (parcel.dataPosition() < F) {
            int y = SafeParcelReader.y(parcel);
            switch (SafeParcelReader.u(y)) {
                case 1:
                    i2 = SafeParcelReader.A(parcel, y);
                    break;
                case 2:
                    str = SafeParcelReader.o(parcel, y);
                    break;
                case 3:
                    str2 = SafeParcelReader.o(parcel, y);
                    break;
                case 4:
                    str3 = SafeParcelReader.o(parcel, y);
                    break;
                case 5:
                    str4 = SafeParcelReader.o(parcel, y);
                    break;
                case 6:
                    uri = (Uri) SafeParcelReader.n(parcel, y, Uri.CREATOR);
                    break;
                case 7:
                    str5 = SafeParcelReader.o(parcel, y);
                    break;
                case 8:
                    j2 = SafeParcelReader.B(parcel, y);
                    break;
                case 9:
                    str6 = SafeParcelReader.o(parcel, y);
                    break;
                case 10:
                    arrayList = SafeParcelReader.s(parcel, y, Scope.CREATOR);
                    break;
                case 11:
                    str7 = SafeParcelReader.o(parcel, y);
                    break;
                case 12:
                    str8 = SafeParcelReader.o(parcel, y);
                    break;
                default:
                    SafeParcelReader.E(parcel, y);
                    break;
            }
        }
        SafeParcelReader.t(parcel, F);
        return new GoogleSignInAccount(i2, str, str2, str3, str4, uri, str5, j2, str6, arrayList, str7, str8);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i2) {
        return new GoogleSignInAccount[i2];
    }
}
