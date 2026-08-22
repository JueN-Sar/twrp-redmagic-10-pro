package com.google.android.gms.common.api.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import androidx.annotation.NonNull;
import com.google.android.gms.common.api.Status;

/* loaded from: classes.dex */
public interface IStatusCallback extends IInterface {

    public static abstract class Stub extends com.google.android.gms.internal.base.zab implements IStatusCallback {
        public Stub() {
            super("com.google.android.gms.common.api.internal.IStatusCallback");
        }

        @NonNull
        public static IStatusCallback asInterface(@NonNull IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.api.internal.IStatusCallback");
            return queryLocalInterface instanceof IStatusCallback ? (IStatusCallback) queryLocalInterface : new zaby(iBinder);
        }

        @Override // com.google.android.gms.internal.base.zab
        protected final boolean zaa(int i2, @NonNull Parcel parcel, @NonNull Parcel parcel2, int i3) {
            if (i2 != 1) {
                return false;
            }
            Status status = (Status) com.google.android.gms.internal.base.zac.a(parcel, Status.CREATOR);
            com.google.android.gms.internal.base.zac.b(parcel);
            onResult(status);
            return true;
        }
    }

    void onResult(@NonNull Status status);
}
