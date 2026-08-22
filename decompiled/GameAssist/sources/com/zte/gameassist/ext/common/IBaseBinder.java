package com.zte.gameassist.ext.common;

import android.os.Binder;
import android.os.Bundle;
import android.os.Parcel;

/* loaded from: classes2.dex */
public interface IBaseBinder {

    public static abstract class AbsBaseBinderProxy implements IBaseBinder {
    }

    public static abstract class BaseBinder extends Binder implements IBaseBinder {
        @Override // android.os.Binder
        protected boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            if (i2 != 1024) {
                return super.onTransact(i2, parcel, parcel2, i3);
            }
            parcel.enforceInterface("gameassist.basebinder");
            Bundle a2 = a(parcel.readString(), parcel.readInt() == 1 ? parcel.readBundle() : new Bundle(), (i3 & 1) != 0);
            if (i3 != 1) {
                parcel2.writeNoException();
                if (a2 == null) {
                    parcel2.writeInt(0);
                } else {
                    parcel2.writeInt(1);
                    parcel2.writeBundle(a2);
                }
            }
            return true;
        }
    }

    Bundle a(String str, Bundle bundle, boolean z);
}
