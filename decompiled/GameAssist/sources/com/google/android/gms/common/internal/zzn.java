package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes.dex */
public final class zzn implements Parcelable.Creator {
    static void a(GetServiceRequest getServiceRequest, Parcel parcel, int i2) {
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.g(parcel, 1, getServiceRequest.f10989c);
        SafeParcelWriter.g(parcel, 2, getServiceRequest.f10990h);
        SafeParcelWriter.g(parcel, 3, getServiceRequest.f10991i);
        SafeParcelWriter.m(parcel, 4, getServiceRequest.f10992j, false);
        SafeParcelWriter.f(parcel, 5, getServiceRequest.f10993k, false);
        SafeParcelWriter.p(parcel, 6, getServiceRequest.f10994l, i2, false);
        SafeParcelWriter.d(parcel, 7, getServiceRequest.f10995m, false);
        SafeParcelWriter.l(parcel, 8, getServiceRequest.f10996n, i2, false);
        SafeParcelWriter.p(parcel, 10, getServiceRequest.f10997o, i2, false);
        SafeParcelWriter.p(parcel, 11, getServiceRequest.f10998p, i2, false);
        SafeParcelWriter.c(parcel, 12, getServiceRequest.f10999q);
        SafeParcelWriter.g(parcel, 13, getServiceRequest.f11000r);
        SafeParcelWriter.c(parcel, 14, getServiceRequest.f11001s);
        SafeParcelWriter.m(parcel, 15, getServiceRequest.G(), false);
        SafeParcelWriter.b(parcel, a2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int F = SafeParcelReader.F(parcel);
        Scope[] scopeArr = GetServiceRequest.u;
        Bundle bundle = new Bundle();
        Feature[] featureArr = GetServiceRequest.v;
        Feature[] featureArr2 = featureArr;
        String str = null;
        IBinder iBinder = null;
        Account account = null;
        String str2 = null;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        boolean z = false;
        int i5 = 0;
        boolean z2 = false;
        while (parcel.dataPosition() < F) {
            int y = SafeParcelReader.y(parcel);
            switch (SafeParcelReader.u(y)) {
                case 1:
                    i2 = SafeParcelReader.A(parcel, y);
                    break;
                case 2:
                    i3 = SafeParcelReader.A(parcel, y);
                    break;
                case 3:
                    i4 = SafeParcelReader.A(parcel, y);
                    break;
                case 4:
                    str = SafeParcelReader.o(parcel, y);
                    break;
                case 5:
                    iBinder = SafeParcelReader.z(parcel, y);
                    break;
                case 6:
                    scopeArr = (Scope[]) SafeParcelReader.r(parcel, y, Scope.CREATOR);
                    break;
                case 7:
                    bundle = SafeParcelReader.f(parcel, y);
                    break;
                case 8:
                    account = (Account) SafeParcelReader.n(parcel, y, Account.CREATOR);
                    break;
                case 9:
                default:
                    SafeParcelReader.E(parcel, y);
                    break;
                case 10:
                    featureArr = (Feature[]) SafeParcelReader.r(parcel, y, Feature.CREATOR);
                    break;
                case 11:
                    featureArr2 = (Feature[]) SafeParcelReader.r(parcel, y, Feature.CREATOR);
                    break;
                case 12:
                    z = SafeParcelReader.v(parcel, y);
                    break;
                case 13:
                    i5 = SafeParcelReader.A(parcel, y);
                    break;
                case 14:
                    z2 = SafeParcelReader.v(parcel, y);
                    break;
                case 15:
                    str2 = SafeParcelReader.o(parcel, y);
                    break;
            }
        }
        SafeParcelReader.t(parcel, F);
        return new GetServiceRequest(i2, i3, i4, str, iBinder, scopeArr, bundle, account, featureArr, featureArr2, z, i5, z2, str2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i2) {
        return new GetServiceRequest[i2];
    }
}
