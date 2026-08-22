package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import android.graphics.Point;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class zbop implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int F = SafeParcelReader.F(parcel);
        float f2 = 0.0f;
        float f3 = 0.0f;
        String str = null;
        Rect rect = null;
        ArrayList arrayList = null;
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
                f2 = SafeParcelReader.x(parcel, y);
            } else if (u != 5) {
                SafeParcelReader.E(parcel, y);
            } else {
                f3 = SafeParcelReader.x(parcel, y);
            }
        }
        SafeParcelReader.t(parcel, F);
        return new zboo(str, rect, arrayList, f2, f3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i2) {
        return new zboo[i2];
    }
}
