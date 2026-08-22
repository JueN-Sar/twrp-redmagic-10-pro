package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.server.response.FastJsonResponse;
import java.util.ArrayList;
import java.util.Map;

@ShowFirstParty
@SafeParcelable.Class
/* loaded from: classes.dex */
public final class zal extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zal> CREATOR = new zap();

    /* renamed from: c, reason: collision with root package name */
    final int f11227c;

    /* renamed from: h, reason: collision with root package name */
    final String f11228h;

    /* renamed from: i, reason: collision with root package name */
    final ArrayList f11229i;

    zal(int i2, String str, ArrayList arrayList) {
        this.f11227c = i2;
        this.f11228h = str;
        this.f11229i = arrayList;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int i3 = this.f11227c;
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.g(parcel, 1, i3);
        SafeParcelWriter.m(parcel, 2, this.f11228h, false);
        SafeParcelWriter.q(parcel, 3, this.f11229i, false);
        SafeParcelWriter.b(parcel, a2);
    }

    zal(String str, Map map) {
        ArrayList arrayList;
        this.f11227c = 1;
        this.f11228h = str;
        if (map == null) {
            arrayList = null;
        } else {
            arrayList = new ArrayList();
            for (String str2 : map.keySet()) {
                arrayList.add(new zam(str2, (FastJsonResponse.Field) map.get(str2)));
            }
        }
        this.f11229i = arrayList;
    }
}
