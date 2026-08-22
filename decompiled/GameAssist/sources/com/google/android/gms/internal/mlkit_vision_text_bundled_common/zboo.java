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
public final class zboo extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zboo> CREATOR = new zbop();

    /* renamed from: c, reason: collision with root package name */
    private final String f12910c;

    /* renamed from: h, reason: collision with root package name */
    private final Rect f12911h;

    /* renamed from: i, reason: collision with root package name */
    private final List f12912i;

    /* renamed from: j, reason: collision with root package name */
    private final float f12913j;

    /* renamed from: k, reason: collision with root package name */
    private final float f12914k;

    public zboo(String str, Rect rect, List list, float f2, float f3) {
        this.f12910c = str;
        this.f12911h = rect;
        this.f12912i = list;
        this.f12913j = f2;
        this.f12914k = f3;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        String str = this.f12910c;
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.m(parcel, 1, str, false);
        SafeParcelWriter.l(parcel, 2, this.f12911h, i2, false);
        SafeParcelWriter.q(parcel, 3, this.f12912i, false);
        SafeParcelWriter.e(parcel, 4, this.f12913j);
        SafeParcelWriter.e(parcel, 5, this.f12914k);
        SafeParcelWriter.b(parcel, a2);
    }
}
