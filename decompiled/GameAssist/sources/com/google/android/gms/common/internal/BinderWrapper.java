package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.annotation.KeepName;

@KeepForSdk
@KeepName
/* loaded from: classes.dex */
public final class BinderWrapper implements Parcelable {

    @NonNull
    public static final Parcelable.Creator<BinderWrapper> CREATOR = new zzh();

    /* renamed from: c, reason: collision with root package name */
    private final IBinder f10964c;

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        parcel.writeStrongBinder(this.f10964c);
    }
}
