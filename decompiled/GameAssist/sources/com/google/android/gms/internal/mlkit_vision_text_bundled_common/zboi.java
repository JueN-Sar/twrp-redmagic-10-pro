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
public final class zboi extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zboi> CREATOR = new zboj();

    /* renamed from: c, reason: collision with root package name */
    private final String f12894c;

    /* renamed from: h, reason: collision with root package name */
    private final Rect f12895h;

    /* renamed from: i, reason: collision with root package name */
    private final List f12896i;

    /* renamed from: j, reason: collision with root package name */
    private final String f12897j;

    /* renamed from: k, reason: collision with root package name */
    private final List f12898k;

    /* renamed from: l, reason: collision with root package name */
    private final float f12899l;

    /* renamed from: m, reason: collision with root package name */
    private final float f12900m;

    public zboi(String str, Rect rect, List list, String str2, List list2, float f2, float f3) {
        this.f12894c = str;
        this.f12895h = rect;
        this.f12896i = list;
        this.f12897j = str2;
        this.f12898k = list2;
        this.f12899l = f2;
        this.f12900m = f3;
    }

    public final Rect G() {
        return this.f12895h;
    }

    public final String P() {
        return this.f12897j;
    }

    public final String R() {
        return this.f12894c;
    }

    public final List T() {
        return this.f12896i;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        String str = this.f12894c;
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.m(parcel, 1, str, false);
        SafeParcelWriter.l(parcel, 2, this.f12895h, i2, false);
        SafeParcelWriter.q(parcel, 3, this.f12896i, false);
        SafeParcelWriter.m(parcel, 4, this.f12897j, false);
        SafeParcelWriter.q(parcel, 5, this.f12898k, false);
        SafeParcelWriter.e(parcel, 6, this.f12899l);
        SafeParcelWriter.e(parcel, 7, this.f12900m);
        SafeParcelWriter.b(parcel, a2);
    }
}
