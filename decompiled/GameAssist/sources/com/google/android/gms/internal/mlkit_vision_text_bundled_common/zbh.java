package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class
@SafeParcelable.Reserved
/* loaded from: classes.dex */
public final class zbh extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zbh> CREATOR = new zbi();

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        SafeParcelWriter.b(parcel, SafeParcelWriter.a(parcel));
    }
}
