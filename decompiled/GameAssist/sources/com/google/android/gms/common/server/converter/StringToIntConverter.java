package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.server.response.FastJsonResponse;
import java.util.ArrayList;
import java.util.HashMap;

@KeepForSdk
@SafeParcelable.Class
/* loaded from: classes.dex */
public final class StringToIntConverter extends AbstractSafeParcelable implements FastJsonResponse.FieldConverter<String, Integer> {

    @NonNull
    public static final Parcelable.Creator<StringToIntConverter> CREATOR = new zad();

    /* renamed from: c, reason: collision with root package name */
    final int f11187c;

    /* renamed from: h, reason: collision with root package name */
    private final HashMap f11188h = new HashMap();

    /* renamed from: i, reason: collision with root package name */
    private final SparseArray f11189i = new SparseArray();

    StringToIntConverter(int i2, ArrayList arrayList) {
        this.f11187c = i2;
        int size = arrayList.size();
        for (int i3 = 0; i3 < size; i3++) {
            zac zacVar = (zac) arrayList.get(i3);
            G(zacVar.f11193h, zacVar.f11194i);
        }
    }

    public StringToIntConverter G(String str, int i2) {
        this.f11188h.put(str, Integer.valueOf(i2));
        this.f11189i.put(i2, str);
        return this;
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse.FieldConverter
    public final /* bridge */ /* synthetic */ Object d(Object obj) {
        String str = (String) this.f11189i.get(((Integer) obj).intValue());
        return (str == null && this.f11188h.containsKey("gms_unknown")) ? "gms_unknown" : str;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int i3 = this.f11187c;
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.g(parcel, 1, i3);
        ArrayList arrayList = new ArrayList();
        for (String str : this.f11188h.keySet()) {
            arrayList.add(new zac(str, ((Integer) this.f11188h.get(str)).intValue()));
        }
        SafeParcelWriter.q(parcel, 2, arrayList, false);
        SafeParcelWriter.b(parcel, a2);
    }
}
