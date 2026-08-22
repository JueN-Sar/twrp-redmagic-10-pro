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
public final class zbog extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zbog> CREATOR = new zboh();

    /* renamed from: c, reason: collision with root package name */
    private final String f12887c;

    /* renamed from: h, reason: collision with root package name */
    private final Rect f12888h;

    /* renamed from: i, reason: collision with root package name */
    private final List f12889i;

    /* renamed from: j, reason: collision with root package name */
    private final String f12890j;

    /* renamed from: k, reason: collision with root package name */
    private final float f12891k;

    /* renamed from: l, reason: collision with root package name */
    private final float f12892l;

    /* renamed from: m, reason: collision with root package name */
    private final List f12893m;

    public zbog(String str, Rect rect, List list, String str2, float f2, float f3, List list2) {
        this.f12887c = str;
        this.f12888h = rect;
        this.f12889i = list;
        this.f12890j = str2;
        this.f12891k = f2;
        this.f12892l = f3;
        this.f12893m = list2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        String str = this.f12887c;
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.m(parcel, 1, str, false);
        SafeParcelWriter.l(parcel, 2, this.f12888h, i2, false);
        SafeParcelWriter.q(parcel, 3, this.f12889i, false);
        SafeParcelWriter.m(parcel, 4, this.f12890j, false);
        SafeParcelWriter.e(parcel, 5, this.f12891k);
        SafeParcelWriter.e(parcel, 6, this.f12892l);
        SafeParcelWriter.q(parcel, 7, this.f12893m, false);
        SafeParcelWriter.b(parcel, a2);
    }
}
