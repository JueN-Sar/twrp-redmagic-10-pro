package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import android.graphics.Point;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class zbof implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int F = SafeParcelReader.F(parcel);
        String str = null;
        Rect rect = null;
        ArrayList arrayList = null;
        String str2 = null;
        ArrayList arrayList2 = null;
        while (parcel.dataPosition() < F) {
            int y = SafeParcelReader.y(parcel);
            int u = SafeParcelReader.u(y);
            if (u == 1) {
                str = SafeParcelReader.o(parcel, y);
            } else if (u == 2) {
                rect = (Rect) SafeParcelReader.n(parcel, y, Rect.CREATOR);
            } else if (u == 3) {
                arrayList = SafeParcelReader.s(parcel, y, Point.CREATOR);
            } else if (u == 4) {
                str2 = SafeParcelReader.o(parcel, y);
            } else if (u != 5) {
                SafeParcelReader.E(parcel, y);
            } else {
                arrayList2 = SafeParcelReader.s(parcel, y, zboi.CREATOR);
            }
        }
        SafeParcelReader.t(parcel, F);
        return new zboe(str, rect, arrayList, str2, arrayList2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i2) {
        return new zboe[i2];
    }
}
