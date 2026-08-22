package com.google.android.gms.common.data;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes.dex */
public final class zaa implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int F = SafeParcelReader.F(parcel);
        int i2 = 0;
        ParcelFileDescriptor parcelFileDescriptor = null;
        int i3 = 0;
        while (parcel.dataPosition() < F) {
            int y = SafeParcelReader.y(parcel);
            int u = SafeParcelReader.u(y);
            if (u == 1) {
                i2 = SafeParcelReader.A(parcel, y);
            } else if (u == 2) {
                parcelFileDescriptor = (ParcelFileDescriptor) SafeParcelReader.n(parcel, y, ParcelFileDescriptor.CREATOR);
            } else if (u != 3) {
                SafeParcelReader.E(parcel, y);
            } else {
                i3 = SafeParcelReader.A(parcel, y);
            }
        }
        SafeParcelReader.t(parcel, F);
        return new BitmapTeleporter(i2, parcelFileDescriptor, i3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i2) {
        return new BitmapTeleporter[i2];
    }
}
