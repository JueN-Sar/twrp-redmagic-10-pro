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
public final class zzvb extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzvb> CREATOR = new zzvc();

    /* renamed from: c, reason: collision with root package name */
    private final String f13603c;

    /* renamed from: h, reason: collision with root package name */
    private final Rect f13604h;

    /* renamed from: i, reason: collision with root package name */
    private final List f13605i;

    /* renamed from: j, reason: collision with root package name */
    private final String f13606j;

    /* renamed from: k, reason: collision with root package name */
    private final float f13607k;

    /* renamed from: l, reason: collision with root package name */
    private final float f13608l;

    /* renamed from: m, reason: collision with root package name */
    private final List f13609m;

    public zzvb(String str, Rect rect, List list, String str2, float f2, float f3, List list2) {
        this.f13603c = str;
        this.f13604h = rect;
        this.f13605i = list;
        this.f13606j = str2;
        this.f13607k = f2;
        this.f13608l = f3;
        this.f13609m = list2;
    }

    public final float G() {
        return this.f13608l;
    }

    public final float P() {
        return this.f13607k;
    }

    public final Rect R() {
        return this.f13604h;
    }

    public final String T() {
        return this.f13606j;
    }

    public final String W() {
        return this.f13603c;
    }

    public final List Y() {
        return this.f13605i;
    }

    public final List a0() {
        return this.f13609m;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        String str = this.f13603c;
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.m(parcel, 1, str, false);
        SafeParcelWriter.l(parcel, 2, this.f13604h, i2, false);
        SafeParcelWriter.q(parcel, 3, this.f13605i, false);
        SafeParcelWriter.m(parcel, 4, this.f13606j, false);
        SafeParcelWriter.e(parcel, 5, this.f13607k);
        SafeParcelWriter.e(parcel, 6, this.f13608l);
        SafeParcelWriter.q(parcel, 7, this.f13609m, false);
        SafeParcelWriter.b(parcel, a2);
    }
}
