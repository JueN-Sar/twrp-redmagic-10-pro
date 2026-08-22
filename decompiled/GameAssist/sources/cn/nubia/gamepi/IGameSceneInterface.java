package cn.nubia.gamepi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import cn.nubia.gamepi.IGameSceneCallback;

/* loaded from: classes.dex */
public interface IGameSceneInterface extends IInterface {
    public static final String DESCRIPTOR = "cn.nubia.gamepi.IGameSceneInterface";

    public static class Default implements IGameSceneInterface {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // cn.nubia.gamepi.IGameSceneInterface
        public void removeCallback() {
        }

        @Override // cn.nubia.gamepi.IGameSceneInterface
        public void setCallback(String str, String str2, IGameSceneCallback iGameSceneCallback) {
        }
    }

    public static abstract class Stub extends Binder implements IGameSceneInterface {
        static final int TRANSACTION_removeCallback = 2;
        static final int TRANSACTION_setCallback = 1;

        private static class Proxy implements IGameSceneInterface {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IGameSceneInterface.DESCRIPTOR;
            }

            @Override // cn.nubia.gamepi.IGameSceneInterface
            public void removeCallback() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IGameSceneInterface.DESCRIPTOR);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // cn.nubia.gamepi.IGameSceneInterface
            public void setCallback(String str, String str2, IGameSceneCallback iGameSceneCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IGameSceneInterface.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongInterface(iGameSceneCallback);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, IGameSceneInterface.DESCRIPTOR);
        }

        public static IGameSceneInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IGameSceneInterface.DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IGameSceneInterface)) ? new Proxy(iBinder) : (IGameSceneInterface) queryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(IGameSceneInterface.DESCRIPTOR);
            }
            if (i2 == 1598968902) {
                parcel2.writeString(IGameSceneInterface.DESCRIPTOR);
                return true;
            }
            if (i2 == 1) {
                setCallback(parcel.readString(), parcel.readString(), IGameSceneCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
            } else {
                if (i2 != 2) {
                    return super.onTransact(i2, parcel, parcel2, i3);
                }
                removeCallback();
                parcel2.writeNoException();
            }
            return true;
        }
    }

    void removeCallback();

    void setCallback(String str, String str2, IGameSceneCallback iGameSceneCallback);
}
