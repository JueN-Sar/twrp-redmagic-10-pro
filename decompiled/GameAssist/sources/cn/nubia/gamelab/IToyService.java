package cn.nubia.gamelab;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import cn.nubia.gamelab.IToyCallback;

/* loaded from: classes.dex */
public interface IToyService extends IInterface {
    public static final String DESCRIPTOR = "cn.nubia.gamelab.IToyService";

    public static class Default implements IToyService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // cn.nubia.gamelab.IToyService
        public void registerCallback(String str, IToyCallback iToyCallback, long j2) {
        }

        @Override // cn.nubia.gamelab.IToyService
        public void unregisterCallback(String str, IToyCallback iToyCallback) {
        }
    }

    public static abstract class Stub extends Binder implements IToyService {
        static final int TRANSACTION_registerCallback = 1;
        static final int TRANSACTION_unregisterCallback = 2;

        private static class Proxy implements IToyService {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IToyService.DESCRIPTOR;
            }

            @Override // cn.nubia.gamelab.IToyService
            public void registerCallback(String str, IToyCallback iToyCallback, long j2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IToyService.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongInterface(iToyCallback);
                    obtain.writeLong(j2);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // cn.nubia.gamelab.IToyService
            public void unregisterCallback(String str, IToyCallback iToyCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IToyService.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongInterface(iToyCallback);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, IToyService.DESCRIPTOR);
        }

        public static IToyService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IToyService.DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IToyService)) ? new Proxy(iBinder) : (IToyService) queryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(IToyService.DESCRIPTOR);
            }
            if (i2 == 1598968902) {
                parcel2.writeString(IToyService.DESCRIPTOR);
                return true;
            }
            if (i2 == 1) {
                registerCallback(parcel.readString(), IToyCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readLong());
                parcel2.writeNoException();
            } else {
                if (i2 != 2) {
                    return super.onTransact(i2, parcel, parcel2, i3);
                }
                unregisterCallback(parcel.readString(), IToyCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
            }
            return true;
        }
    }

    void registerCallback(String str, IToyCallback iToyCallback, long j2);

    void unregisterCallback(String str, IToyCallback iToyCallback);
}
