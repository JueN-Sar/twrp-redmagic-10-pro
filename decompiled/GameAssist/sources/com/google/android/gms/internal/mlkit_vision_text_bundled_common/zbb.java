package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes.dex */
public class zbb extends Binder implements IInterface {
    protected zbb(String str) {
        attachInterface(this, str);
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
        if (i2 <= 16777215) {
            parcel.enforceInterface(getInterfaceDescriptor());
        } else if (super.onTransact(i2, parcel, parcel2, i3)) {
            return true;
        }
        return zba(i2, parcel, parcel2, i3);
    }

    protected boolean zba(int i2, Parcel parcel, Parcel parcel2, int i3) {
        throw null;
    }
}
