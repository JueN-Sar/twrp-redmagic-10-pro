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
public final class zzvd extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzvd> CREATOR = new zzve();

    /* renamed from: c, reason: collision with root package name */
    private final String f13610c;

    /* renamed from: h, reason: collision with root package name */
    private final Rect f13611h;

    /* renamed from: i, reason: collision with root package name */
    private final List f13612i;

    /* renamed from: j, reason: collision with root package name */
    private final String f13613j;

    /* renamed from: k, reason: collision with root package name */
    private final List f13614k;

    /* renamed from: l, reason: collision with root package name */
    private final float f13615l;

    /* renamed from: m, reason: collision with root package name */
    private final float f13616m;

    public zzvd(String str, Rect rect, List list, String str2, List list2, float f2, float f3) {
        this.f13610c = str;
        this.f13611h = rect;
        this.f13612i = list;
        this.f13613j = str2;
        this.f13614k = list2;
        this.f13615l = f2;
        this.f13616m = f3;
    }

    public final float G() {
        return this.f13616m;
    }

    public final float P() {
        return this.f13615l;
    }

    public final Rect R() {
        return this.f13611h;
    }

    public final String T() {
        return this.f13613j;
    }

    public final String W() {
        return this.f13610c;
    }

    public final List Y() {
        return this.f13612i;
    }

    public final List a0() {
        return this.f13614k;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        String str = this.f13610c;
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.m(parcel, 1, str, false);
        SafeParcelWriter.l(parcel, 2, this.f13611h, i2, false);
        SafeParcelWriter.q(parcel, 3, this.f13612i, false);
        SafeParcelWriter.m(parcel, 4, this.f13613j, false);
        SafeParcelWriter.q(parcel, 5, this.f13614k, false);
        SafeParcelWriter.e(parcel, 6, this.f13615l);
        SafeParcelWriter.e(parcel, 7, this.f13616m);
        SafeParcelWriter.b(parcel, a2);
    }
}
