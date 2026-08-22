package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import android.graphics.Point;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class zboh implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int F = SafeParcelReader.F(parcel);
        String str = null;
        Rect rect = null;
        ArrayList arrayList = null;
        String str2 = null;
        ArrayList arrayList2 = null;
        float f2 = 0.0f;
        float f3 = 0.0f;
        while (parcel.dataPosition() < F) {
            int y = SafeParcelReader.y(parcel);
            switch (SafeParcelReader.u(y)) {
                case 1:
                    str = SafeParcelReader.o(parcel, y);
                    break;
                case 2:
                    rect = (Rect) SafeParcelReader.n(parcel, y, Rect.CREATOR);
                    break;
                case 3:
                    arrayList = SafeParcelReader.s(parcel, y, Point.CREATOR);
                    break;
                case 4:
                    str2 = SafeParcelReader.o(parcel, y);
                    break;
                case 5:
                    f2 = SafeParcelReader.x(parcel, y);
                    break;
                case 6:
                    f3 = SafeParcelReader.x(parcel, y);
                    break;
                case 7:
                    arrayList2 = SafeParcelReader.s(parcel, y, zboo.CREATOR);
                    break;
                default:
                    SafeParcelReader.E(parcel, y);
                    break;
            }
        }
        SafeParcelReader.t(parcel, F);
        return new zbog(str, rect, arrayList, str2, f2, f3, arrayList2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i2) {
        return new zbog[i2];
    }
}
