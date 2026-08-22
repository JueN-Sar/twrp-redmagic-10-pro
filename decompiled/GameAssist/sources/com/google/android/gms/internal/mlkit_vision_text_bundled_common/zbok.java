package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

@SafeParcelable.Class
/* loaded from: classes.dex */
public final class zbok extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zbok> CREATOR = new zbol();

    /* renamed from: c, reason: collision with root package name */
    private final String f12901c;

    /* renamed from: h, reason: collision with root package name */
    private final List f12902h;

    public zbok(String str, List list) {
        this.f12901c = str;
        this.f12902h = list;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        String str = this.f12901c;
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.m(parcel, 1, str, false);
        SafeParcelWriter.q(parcel, 2, this.f12902h, false);
        SafeParcelWriter.b(parcel, a2);
    }
}
