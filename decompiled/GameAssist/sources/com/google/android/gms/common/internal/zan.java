package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes.dex */
public final class zan implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int F = SafeParcelReader.F(parcel);
        int i2 = -1;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        String str = null;
        String str2 = null;
        long j2 = 0;
        long j3 = 0;
        while (parcel.dataPosition() < F) {
            int y = SafeParcelReader.y(parcel);
            switch (SafeParcelReader.u(y)) {
                case 1:
                    i3 = SafeParcelReader.A(parcel, y);
                    break;
                case 2:
                    i4 = SafeParcelReader.A(parcel, y);
                    break;
                case 3:
                    i5 = SafeParcelReader.A(parcel, y);
                    break;
                case 4:
                    j2 = SafeParcelReader.B(parcel, y);
                    break;
                case 5:
                    j3 = SafeParcelReader.B(parcel, y);
                    break;
                case 6:
                    str = SafeParcelReader.o(parcel, y);
                    break;
                case 7:
                    str2 = SafeParcelReader.o(parcel, y);
                    break;
                case 8:
                    i6 = SafeParcelReader.A(parcel, y);
                    break;
                case 9:
                    i2 = SafeParcelReader.A(parcel, y);
                    break;
                default:
                    SafeParcelReader.E(parcel, y);
                    break;
            }
        }
        SafeParcelReader.t(parcel, F);
        return new MethodInvocation(i3, i4, i5, j2, j3, str, str2, i6, i2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i2) {
        return new MethodInvocation[i2];
    }
}
