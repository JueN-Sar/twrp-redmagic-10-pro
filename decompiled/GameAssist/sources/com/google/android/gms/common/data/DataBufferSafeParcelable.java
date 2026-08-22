package com.google.android.gms.common.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@KeepForSdk
/* loaded from: classes.dex */
public class DataBufferSafeParcelable<T extends SafeParcelable> extends AbstractDataBuffer<T> {

    /* renamed from: i, reason: collision with root package name */
    private static final String[] f10898i = {"data"};

    /* renamed from: h, reason: collision with root package name */
    private final Parcelable.Creator f10899h;

    @Override // com.google.android.gms.common.data.AbstractDataBuffer, com.google.android.gms.common.data.DataBuffer
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public SafeParcelable get(int i2) {
        DataHolder dataHolder = (DataHolder) Preconditions.i(this.f10886c);
        byte[] G = dataHolder.G("data", i2, dataHolder.W(i2));
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(G, 0, G.length);
        obtain.setDataPosition(0);
        SafeParcelable safeParcelable = (SafeParcelable) this.f10899h.createFromParcel(obtain);
        obtain.recycle();
        return safeParcelable;
    }
}
