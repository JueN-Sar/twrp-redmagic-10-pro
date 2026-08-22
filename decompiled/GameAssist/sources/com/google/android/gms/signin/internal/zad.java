package com.google.android.gms.signin.internal;

import android.os.Parcel;

/* loaded from: classes.dex */
public abstract class zad extends com.google.android.gms.internal.base.zab implements zae {
    public zad() {
        super("com.google.android.gms.signin.internal.ISignInCallbacks");
    }

    @Override // com.google.android.gms.internal.base.zab
    protected final boolean zaa(int i2, Parcel parcel, Parcel parcel2, int i3) {
        switch (i2) {
            case 3:
                com.google.android.gms.internal.base.zac.b(parcel);
                break;
            case 4:
                com.google.android.gms.internal.base.zac.b(parcel);
                break;
            case 5:
            default:
                return false;
            case 6:
                com.google.android.gms.internal.base.zac.b(parcel);
                break;
            case 7:
                com.google.android.gms.internal.base.zac.b(parcel);
                break;
            case 8:
                zak zakVar = (zak) com.google.android.gms.internal.base.zac.a(parcel, zak.CREATOR);
                com.google.android.gms.internal.base.zac.b(parcel);
                zab(zakVar);
                break;
            case 9:
                com.google.android.gms.internal.base.zac.b(parcel);
                break;
        }
        parcel2.writeNoException();
        return true;
    }
}
