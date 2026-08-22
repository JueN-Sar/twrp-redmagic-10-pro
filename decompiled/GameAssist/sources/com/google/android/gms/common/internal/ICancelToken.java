package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import androidx.annotation.NonNull;

/* loaded from: classes.dex */
public interface ICancelToken extends IInterface {

    public static abstract class Stub extends com.google.android.gms.internal.common.zzb implements ICancelToken {
        public Stub() {
            super("com.google.android.gms.common.internal.ICancelToken");
        }

        @NonNull
        public static ICancelToken asInterface(@NonNull IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.ICancelToken");
            return queryLocalInterface instanceof ICancelToken ? (ICancelToken) queryLocalInterface : new zzx(iBinder);
        }

        @Override // com.google.android.gms.internal.common.zzb
        protected final boolean zza(int i2, @NonNull Parcel parcel, @NonNull Parcel parcel2, int i3) {
            if (i2 != 2) {
                return false;
            }
            cancel();
            return true;
        }
    }

    void cancel();
}
