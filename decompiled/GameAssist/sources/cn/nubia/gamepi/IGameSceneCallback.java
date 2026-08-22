package cn.nubia.gamepi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes.dex */
public interface IGameSceneCallback extends IInterface {
    public static final String DESCRIPTOR = "cn.nubia.gamepi.IGameSceneCallback";

    public static class Default implements IGameSceneCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // cn.nubia.gamepi.IGameSceneCallback
        public void onSceneChange(String str, String str2, String str3) {
        }
    }

    public static abstract class Stub extends Binder implements IGameSceneCallback {
        static final int TRANSACTION_onSceneChange = 1;

        private static class Proxy implements IGameSceneCallback {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IGameSceneCallback.DESCRIPTOR;
            }

            @Override // cn.nubia.gamepi.IGameSceneCallback
            public void onSceneChange(String str, String str2, String str3) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IGameSceneCallback.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    this.mRemote.transact(1, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, IGameSceneCallback.DESCRIPTOR);
        }

        public static IGameSceneCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IGameSceneCallback.DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IGameSceneCallback)) ? new Proxy(iBinder) : (IGameSceneCallback) queryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(IGameSceneCallback.DESCRIPTOR);
            }
            if (i2 == 1598968902) {
                parcel2.writeString(IGameSceneCallback.DESCRIPTOR);
                return true;
            }
            if (i2 != 1) {
                return super.onTransact(i2, parcel, parcel2, i3);
            }
            onSceneChange(parcel.readString(), parcel.readString(), parcel.readString());
            return true;
        }
    }

    void onSceneChange(String str, String str2, String str3);
}
