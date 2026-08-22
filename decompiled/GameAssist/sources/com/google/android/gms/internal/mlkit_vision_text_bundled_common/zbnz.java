package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes.dex */
public abstract class zbnz extends zbb implements zboa {
    public zbnz() {
        super("com.google.mlkit.vision.text.aidls.ITextRecognizer");
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbb
    protected final boolean zba(int i2, Parcel parcel, Parcel parcel2, int i3) {
        if (i2 == 1) {
            zbc();
            parcel2.writeNoException();
        } else if (i2 == 2) {
            zbd();
            parcel2.writeNoException();
        } else if (i2 == 3) {
            IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
            zbnx zbnxVar = (zbnx) zbc.a(parcel, zbnx.CREATOR);
            zbc.b(parcel);
            zbok zbb = zbb(asInterface, zbnxVar);
            parcel2.writeNoException();
            parcel2.writeInt(1);
            zbb.writeToParcel(parcel2, 1);
        } else {
            if (i2 != 4) {
                return false;
            }
            IObjectWrapper asInterface2 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
            zbnx zbnxVar2 = (zbnx) zbc.a(parcel, zbnx.CREATOR);
            zbc.b(parcel);
            zbf[] zbe = zbe(asInterface2, zbnxVar2);
            parcel2.writeNoException();
            parcel2.writeTypedArray(zbe, 1);
        }
        return true;
    }
}
