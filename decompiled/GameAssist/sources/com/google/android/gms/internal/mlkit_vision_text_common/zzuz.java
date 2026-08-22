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
public final class zzuz extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzuz> CREATOR = new zzva();

    /* renamed from: c, reason: collision with root package name */
    private final String f13597c;

    /* renamed from: h, reason: collision with root package name */
    private final Rect f13598h;

    /* renamed from: i, reason: collision with root package name */
    private final List f13599i;

    /* renamed from: j, reason: collision with root package name */
    private final String f13600j;

    /* renamed from: k, reason: collision with root package name */
    private final List f13601k;

    public zzuz(String str, Rect rect, List list, String str2, List list2) {
        this.f13597c = str;
        this.f13598h = rect;
        this.f13599i = list;
        this.f13600j = str2;
        this.f13601k = list2;
    }

    public final Rect G() {
        return this.f13598h;
    }

    public final String P() {
        return this.f13600j;
    }

    public final String R() {
        return this.f13597c;
    }

    public final List T() {
        return this.f13599i;
    }

    public final List W() {
        return this.f13601k;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        String str = this.f13597c;
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.m(parcel, 1, str, false);
        SafeParcelWriter.l(parcel, 2, this.f13598h, i2, false);
        SafeParcelWriter.q(parcel, 3, this.f13599i, false);
        SafeParcelWriter.m(parcel, 4, this.f13600j, false);
        SafeParcelWriter.q(parcel, 5, this.f13601k, false);
        SafeParcelWriter.b(parcel, a2);
    }
}
