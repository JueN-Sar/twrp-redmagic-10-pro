package com.google.android.gms.internal.mlkit_vision_text_common;

import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

@SafeParcelable.Class
/* loaded from: classes.dex */
public final class zzvj extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzvj> CREATOR = new zzvk();

    /* renamed from: c, reason: collision with root package name */
    private final String f13626c;

    /* renamed from: h, reason: collision with root package name */
    private final Rect f13627h;

    /* renamed from: i, reason: collision with root package name */
    private final List f13628i;

    /* renamed from: j, reason: collision with root package name */
    private final float f13629j;

    /* renamed from: k, reason: collision with root package name */
    private final float f13630k;

    public zzvj(String str, Rect rect, List list, float f2, float f3) {
        this.f13626c = str;
        this.f13627h = rect;
        this.f13628i = list;
        this.f13629j = f2;
        this.f13630k = f3;
    }

    public final float G() {
        return this.f13630k;
    }

    public final float P() {
        return this.f13629j;
    }

    public final Rect R() {
        return this.f13627h;
    }

    public final String T() {
        return this.f13626c;
    }

    public final List W() {
        return this.f13628i;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        String str = this.f13626c;
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.m(parcel, 1, str, false);
        SafeParcelWriter.l(parcel, 2, this.f13627h, i2, false);
        SafeParcelWriter.q(parcel, 3, this.f13628i, false);
        SafeParcelWriter.e(parcel, 4, this.f13629j);
        SafeParcelWriter.e(parcel, 5, this.f13630k);
        SafeParcelWriter.b(parcel, a2);
    }
}
