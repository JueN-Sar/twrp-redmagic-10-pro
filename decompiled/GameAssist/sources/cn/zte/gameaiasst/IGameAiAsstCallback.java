package cn.zte.gameaiasst;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes.dex */
public interface IGameAiAsstCallback extends IInterface {
    public static final String DESCRIPTOR = "cn.zte.gameaiasst.IGameAiAsstCallback";

    public static class Default implements IGameAiAsstCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // cn.zte.gameaiasst.IGameAiAsstCallback
        public void responseResult(String str, int i2) {
        }
    }

    public static abstract class Stub extends Binder implements IGameAiAsstCallback {
        static final int TRANSACTION_responseResult = 1;

        private static class Proxy implements IGameAiAsstCallback {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IGameAiAsstCallback.DESCRIPTOR;
            }

            @Override // cn.zte.gameaiasst.IGameAiAsstCallback
            public void responseResult(String str, int i2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IGameAiAsstCallback.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, IGameAiAsstCallback.DESCRIPTOR);
        }

        public static IGameAiAsstCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IGameAiAsstCallback.DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IGameAiAsstCallback)) ? new Proxy(iBinder) : (IGameAiAsstCallback) queryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(IGameAiAsstCallback.DESCRIPTOR);
            }
            if (i2 == 1598968902) {
                parcel2.writeString(IGameAiAsstCallback.DESCRIPTOR);
                return true;
            }
            if (i2 != 1) {
                return super.onTransact(i2, parcel, parcel2, i3);
            }
            responseResult(parcel.readString(), parcel.readInt());
            parcel2.writeNoException();
            return true;
        }
    }

    void responseResult(String str, int i2);
}
