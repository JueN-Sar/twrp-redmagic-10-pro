package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes.dex */
public final class zbny implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int F = SafeParcelReader.F(parcel);
        long j2 = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (parcel.dataPosition() < F) {
            int y = SafeParcelReader.y(parcel);
            int u = SafeParcelReader.u(y);
            if (u == 1) {
                i2 = SafeParcelReader.A(parcel, y);
            } else if (u == 2) {
                i3 = SafeParcelReader.A(parcel, y);
            } else if (u == 3) {
                i4 = SafeParcelReader.A(parcel, y);
            } else if (u == 4) {
                i5 = SafeParcelReader.A(parcel, y);
            } else if (u != 5) {
                SafeParcelReader.E(parcel, y);
            } else {
                j2 = SafeParcelReader.B(parcel, y);
            }
        }
        SafeParcelReader.t(parcel, F);
        return new zbnx(i2, i3, i4, i5, j2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i2) {
        return new zbnx[i2];
    }
}
