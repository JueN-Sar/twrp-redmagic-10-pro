package com.tencent.inlab.tcsystem;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.tencent.inlab.tcsystem.ITCSystemCallback;

/* loaded from: classes.dex */
public interface ITCSystemService extends IInterface {
    public static final String DESCRIPTOR = "com.tencent.inlab.tcsystem.ITCSystemService";

    public static class Default implements ITCSystemService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.tencent.inlab.tcsystem.ITCSystemService
        public byte[] carry(byte[] bArr, int i2) {
            return null;
        }

        @Override // com.tencent.inlab.tcsystem.ITCSystemService
        public int registerTCSystemCallback(String str, ITCSystemCallback iTCSystemCallback) {
            return 0;
        }

        @Override // com.tencent.inlab.tcsystem.ITCSystemService
        public int unregisterTCSystemCallback(String str, ITCSystemCallback iTCSystemCallback) {
            return 0;
        }
    }

    public static abstract class Stub extends Binder implements ITCSystemService {
        static final int TRANSACTION_carry = 3;
        static final int TRANSACTION_registerTCSystemCallback = 1;
        static final int TRANSACTION_unregisterTCSystemCallback = 2;

        private static class Proxy implements ITCSystemService {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.tencent.inlab.tcsystem.ITCSystemService
            public byte[] carry(byte[] bArr, int i2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITCSystemService.DESCRIPTOR);
                    obtain.writeByteArray(bArr);
                    obtain.writeInt(i2);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createByteArray();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return ITCSystemService.DESCRIPTOR;
            }

            @Override // com.tencent.inlab.tcsystem.ITCSystemService
            public int registerTCSystemCallback(String str, ITCSystemCallback iTCSystemCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITCSystemService.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongInterface(iTCSystemCallback);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.tencent.inlab.tcsystem.ITCSystemService
            public int unregisterTCSystemCallback(String str, ITCSystemCallback iTCSystemCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITCSystemService.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongInterface(iTCSystemCallback);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, ITCSystemService.DESCRIPTOR);
        }

        public static ITCSystemService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(ITCSystemService.DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof ITCSystemService)) ? new Proxy(iBinder) : (ITCSystemService) queryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(ITCSystemService.DESCRIPTOR);
            }
            if (i2 == 1598968902) {
                parcel2.writeString(ITCSystemService.DESCRIPTOR);
                return true;
            }
            if (i2 == 1) {
                int registerTCSystemCallback = registerTCSystemCallback(parcel.readString(), ITCSystemCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeInt(registerTCSystemCallback);
            } else if (i2 == 2) {
                int unregisterTCSystemCallback = unregisterTCSystemCallback(parcel.readString(), ITCSystemCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeInt(unregisterTCSystemCallback);
            } else {
                if (i2 != 3) {
                    return super.onTransact(i2, parcel, parcel2, i3);
                }
                byte[] carry = carry(parcel.createByteArray(), parcel.readInt());
                parcel2.writeNoException();
                parcel2.writeByteArray(carry);
            }
            return true;
        }
    }

    byte[] carry(byte[] bArr, int i2);

    int registerTCSystemCallback(String str, ITCSystemCallback iTCSystemCallback);

    int unregisterTCSystemCallback(String str, ITCSystemCallback iTCSystemCallback);
}
