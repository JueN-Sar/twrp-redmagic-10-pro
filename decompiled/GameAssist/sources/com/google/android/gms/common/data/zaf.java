package com.google.android.gms.common.data;

import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes.dex */
public final class zaf implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int F = SafeParcelReader.F(parcel);
        String[] strArr = null;
        CursorWindow[] cursorWindowArr = null;
        Bundle bundle = null;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < F) {
            int y = SafeParcelReader.y(parcel);
            int u = SafeParcelReader.u(y);
            if (u == 1) {
                strArr = SafeParcelReader.p(parcel, y);
            } else if (u == 2) {
                cursorWindowArr = (CursorWindow[]) SafeParcelReader.r(parcel, y, CursorWindow.CREATOR);
            } else if (u == 3) {
                i3 = SafeParcelReader.A(parcel, y);
            } else if (u == 4) {
                bundle = SafeParcelReader.f(parcel, y);
            } else if (u != 1000) {
                SafeParcelReader.E(parcel, y);
            } else {
                i2 = SafeParcelReader.A(parcel, y);
            }
        }
        SafeParcelReader.t(parcel, F);
        DataHolder dataHolder = new DataHolder(i2, strArr, cursorWindowArr, i3, bundle);
        dataHolder.Y();
        return dataHolder;
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i2) {
        return new DataHolder[i2];
    }
}
