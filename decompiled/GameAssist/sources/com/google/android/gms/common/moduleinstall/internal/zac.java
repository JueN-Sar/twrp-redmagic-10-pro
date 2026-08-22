package com.google.android.gms.common.moduleinstall.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class zac implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int F = SafeParcelReader.F(parcel);
        ArrayList arrayList = null;
        String str = null;
        boolean z = false;
        String str2 = null;
        while (parcel.dataPosition() < F) {
            int y = SafeParcelReader.y(parcel);
            int u = SafeParcelReader.u(y);
            if (u == 1) {
                arrayList = SafeParcelReader.s(parcel, y, Feature.CREATOR);
            } else if (u == 2) {
                z = SafeParcelReader.v(parcel, y);
            } else if (u == 3) {
                str2 = SafeParcelReader.o(parcel, y);
            } else if (u != 4) {
                SafeParcelReader.E(parcel, y);
            } else {
                str = SafeParcelReader.o(parcel, y);
            }
        }
        SafeParcelReader.t(parcel, F);
        return new ApiFeatureRequest(arrayList, z, str2, str);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i2) {
        return new ApiFeatureRequest[i2];
    }
}
