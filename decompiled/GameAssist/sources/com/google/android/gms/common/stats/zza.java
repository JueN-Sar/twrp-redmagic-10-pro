package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.mlkit.common.MlKitException;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class zza implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int F = SafeParcelReader.F(parcel);
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        boolean z = false;
        String str = null;
        ArrayList arrayList = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        float f2 = 0.0f;
        while (parcel.dataPosition() < F) {
            int y = SafeParcelReader.y(parcel);
            switch (SafeParcelReader.u(y)) {
                case 1:
                    i2 = SafeParcelReader.A(parcel, y);
                    break;
                case 2:
                    j2 = SafeParcelReader.B(parcel, y);
                    break;
                case 3:
                case 7:
                case 9:
                default:
                    SafeParcelReader.E(parcel, y);
                    break;
                case 4:
                    str = SafeParcelReader.o(parcel, y);
                    break;
                case 5:
                    i4 = SafeParcelReader.A(parcel, y);
                    break;
                case 6:
                    arrayList = SafeParcelReader.q(parcel, y);
                    break;
                case 8:
                    j3 = SafeParcelReader.B(parcel, y);
                    break;
                case 10:
                    str3 = SafeParcelReader.o(parcel, y);
                    break;
                case 11:
                    i3 = SafeParcelReader.A(parcel, y);
                    break;
                case 12:
                    str2 = SafeParcelReader.o(parcel, y);
                    break;
                case 13:
                    str4 = SafeParcelReader.o(parcel, y);
                    break;
                case 14:
                    i5 = SafeParcelReader.A(parcel, y);
                    break;
                case 15:
                    f2 = SafeParcelReader.x(parcel, y);
                    break;
                case 16:
                    j4 = SafeParcelReader.B(parcel, y);
                    break;
                case MlKitException.NETWORK_ISSUE /* 17 */:
                    str5 = SafeParcelReader.o(parcel, y);
                    break;
                case MlKitException.UNSUPPORTED /* 18 */:
                    z = SafeParcelReader.v(parcel, y);
                    break;
            }
        }
        SafeParcelReader.t(parcel, F);
        return new WakeLockEvent(i2, j2, i3, str, i4, arrayList, str2, j3, i5, str3, str4, f2, j4, str5, z);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i2) {
        return new WakeLockEvent[i2];
    }
}
