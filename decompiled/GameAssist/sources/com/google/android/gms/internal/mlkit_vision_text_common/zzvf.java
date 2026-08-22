package com.google.android.gms.internal.mlkit_vision_text_common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

@SafeParcelable.Class
/* loaded from: classes.dex */
public final class zzvf extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzvf> CREATOR = new zzvg();

    /* renamed from: c, reason: collision with root package name */
    private final String f13617c;

    /* renamed from: h, reason: collision with root package name */
    private final List f13618h;

    public zzvf(String str, List list) {
        this.f13617c = str;
        this.f13618h = list;
    }

    public final String G() {
        return this.f13617c;
    }

    public final List P() {
        return this.f13618h;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        String str = this.f13617c;
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.m(parcel, 1, str, false);
        SafeParcelWriter.q(parcel, 2, this.f13618h, false);
        SafeParcelWriter.b(parcel, a2);
    }
}
