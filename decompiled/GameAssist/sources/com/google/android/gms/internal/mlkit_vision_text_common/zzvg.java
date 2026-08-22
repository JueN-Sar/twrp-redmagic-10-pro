package com.google.android.gms.internal.mlkit_vision_text_common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class zzvg implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int F = SafeParcelReader.F(parcel);
        String str = null;
        ArrayList arrayList = null;
        while (parcel.dataPosition() < F) {
            int y = SafeParcelReader.y(parcel);
            int u = SafeParcelReader.u(y);
            if (u == 1) {
                str = SafeParcelReader.o(parcel, y);
            } else if (u != 2) {
                SafeParcelReader.E(parcel, y);
            } else {
                arrayList = SafeParcelReader.s(parcel, y, zzuz.CREATOR);
            }
        }
        SafeParcelReader.t(parcel, F);
        return new zzvf(str, arrayList);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i2) {
        return new zzvf[i2];
    }
}
