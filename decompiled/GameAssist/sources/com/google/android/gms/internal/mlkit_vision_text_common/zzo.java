package com.google.android.gms.internal.mlkit_vision_text_common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes.dex */
public final class zzo implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int F = SafeParcelReader.F(parcel);
        while (parcel.dataPosition() < F) {
            int y = SafeParcelReader.y(parcel);
            SafeParcelReader.u(y);
            SafeParcelReader.E(parcel, y);
        }
        SafeParcelReader.t(parcel, F);
        return new zzn();
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i2) {
        return new zzn[i2];
    }
}
