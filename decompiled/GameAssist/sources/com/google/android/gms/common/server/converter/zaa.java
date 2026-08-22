package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.server.response.FastJsonResponse;

@SafeParcelable.Class
/* loaded from: classes.dex */
public final class zaa extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zaa> CREATOR = new zab();

    /* renamed from: c, reason: collision with root package name */
    final int f11190c;

    /* renamed from: h, reason: collision with root package name */
    private final StringToIntConverter f11191h;

    zaa(int i2, StringToIntConverter stringToIntConverter) {
        this.f11190c = i2;
        this.f11191h = stringToIntConverter;
    }

    public static zaa G(FastJsonResponse.FieldConverter fieldConverter) {
        if (fieldConverter instanceof StringToIntConverter) {
            return new zaa((StringToIntConverter) fieldConverter);
        }
        throw new IllegalArgumentException("Unsupported safe parcelable field converter class.");
    }

    public final FastJsonResponse.FieldConverter P() {
        StringToIntConverter stringToIntConverter = this.f11191h;
        if (stringToIntConverter != null) {
            return stringToIntConverter;
        }
        throw new IllegalStateException("There was no converter wrapped in this ConverterWrapper.");
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int i3 = this.f11190c;
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.g(parcel, 1, i3);
        SafeParcelWriter.l(parcel, 2, this.f11191h, i2, false);
        SafeParcelWriter.b(parcel, a2);
    }

    private zaa(StringToIntConverter stringToIntConverter) {
        this.f11190c = 1;
        this.f11191h = stringToIntConverter;
    }
}
