package com.google.android.gms.common.moduleinstall;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes.dex */
public final class zab implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int F = SafeParcelReader.F(parcel);
        PendingIntent pendingIntent = null;
        while (parcel.dataPosition() < F) {
            int y = SafeParcelReader.y(parcel);
            if (SafeParcelReader.u(y) != 1) {
                SafeParcelReader.E(parcel, y);
            } else {
                pendingIntent = (PendingIntent) SafeParcelReader.n(parcel, y, PendingIntent.CREATOR);
            }
        }
        SafeParcelReader.t(parcel, F);
        return new ModuleInstallIntentResponse(pendingIntent);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i2) {
        return new ModuleInstallIntentResponse[i2];
    }
}
