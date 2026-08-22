package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes.dex */
public final class zbe implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int F = SafeParcelReader.F(parcel);
        float f2 = 0.0f;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (parcel.dataPosition() < F) {
            int y = SafeParcelReader.y(parcel);
            int u = SafeParcelReader.u(y);
            if (u == 2) {
                i2 = SafeParcelReader.A(parcel, y);
            } else if (u == 3) {
                i3 = SafeParcelReader.A(parcel, y);
            } else if (u == 4) {
                i4 = SafeParcelReader.A(parcel, y);
            } else if (u == 5) {
                i5 = SafeParcelReader.A(parcel, y);
            } else if (u != 6) {
                SafeParcelReader.E(parcel, y);
            } else {
                f2 = SafeParcelReader.x(parcel, y);
            }
        }
        SafeParcelReader.t(parcel, F);
        return new zbd(i2, i3, i4, i5, f2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i2) {
        return new zbd[i2];
    }
}
