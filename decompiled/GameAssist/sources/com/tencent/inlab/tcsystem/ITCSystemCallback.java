package com.tencent.inlab.tcsystem;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes.dex */
public interface ITCSystemCallback extends IInterface {
    public static final String DESCRIPTOR = "com.tencent.inlab.tcsystem.ITCSystemCallback";

    public static class Default implements ITCSystemCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.tencent.inlab.tcsystem.ITCSystemCallback
        public String onEventNotify(byte[] bArr) {
            return null;
        }

        @Override // com.tencent.inlab.tcsystem.ITCSystemCallback
        public String onStreamNotify(byte[] bArr, String str) {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements ITCSystemCallback {
        static final int TRANSACTION_onEventNotify = 1;
        static final int TRANSACTION_onStreamNotify = 2;

        private static class Proxy implements ITCSystemCallback {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ITCSystemCallback.DESCRIPTOR;
            }

            @Override // com.tencent.inlab.tcsystem.ITCSystemCallback
            public String onEventNotify(byte[] bArr) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITCSystemCallback.DESCRIPTOR);
                    obtain.writeByteArray(bArr);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.tencent.inlab.tcsystem.ITCSystemCallback
            public String onStreamNotify(byte[] bArr, String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITCSystemCallback.DESCRIPTOR);
                    obtain.writeByteArray(bArr);
                    obtain.writeString(str);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, ITCSystemCallback.DESCRIPTOR);
        }

        public static ITCSystemCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(ITCSystemCallback.DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof ITCSystemCallback)) ? new Proxy(iBinder) : (ITCSystemCallback) queryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(ITCSystemCallback.DESCRIPTOR);
            }
            if (i2 == 1598968902) {
                parcel2.writeString(ITCSystemCallback.DESCRIPTOR);
                return true;
            }
            if (i2 == 1) {
                String onEventNotify = onEventNotify(parcel.createByteArray());
                parcel2.writeNoException();
                parcel2.writeString(onEventNotify);
            } else {
                if (i2 != 2) {
                    return super.onTransact(i2, parcel, parcel2, i3);
                }
                String onStreamNotify = onStreamNotify(parcel.createByteArray(), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeString(onStreamNotify);
            }
            return true;
        }
    }

    String onEventNotify(byte[] bArr);

    String onStreamNotify(byte[] bArr, String str);
}
