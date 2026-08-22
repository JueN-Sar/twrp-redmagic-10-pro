package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

@SafeParcelable.Class
/* loaded from: classes.dex */
public final class zboe extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zboe> CREATOR = new zbof();

    /* renamed from: c, reason: collision with root package name */
    private final String f12882c;

    /* renamed from: h, reason: collision with root package name */
    private final Rect f12883h;

    /* renamed from: i, reason: collision with root package name */
    private final List f12884i;

    /* renamed from: j, reason: collision with root package name */
    private final String f12885j;

    /* renamed from: k, reason: collision with root package name */
    private final List f12886k;

    public zboe(String str, Rect rect, List list, String str2, List list2) {
        this.f12882c = str;
        this.f12883h = rect;
        this.f12884i = list;
        this.f12885j = str2;
        this.f12886k = list2;
    }

    public final String G() {
        return this.f12882c;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        String str = this.f12882c;
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.m(parcel, 1, str, false);
        SafeParcelWriter.l(parcel, 2, this.f12883h, i2, false);
        SafeParcelWriter.q(parcel, 3, this.f12884i, false);
        SafeParcelWriter.m(parcel, 4, this.f12885j, false);
        SafeParcelWriter.q(parcel, 5, this.f12886k, false);
        SafeParcelWriter.b(parcel, a2);
    }
}
