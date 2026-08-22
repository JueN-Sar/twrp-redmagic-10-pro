package com.zte.gameassist.aiagent;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes2.dex */
public interface IGameAssistClientCallback extends IInterface {
    public static final String DESCRIPTOR = "com.zte.gameassist.aiagent.IGameAssistClientCallback";

    public static class Default implements IGameAssistClientCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.zte.gameassist.aiagent.IGameAssistClientCallback
        public void onReceivedCallback(int i2, String str) {
        }
    }

    public static abstract class Stub extends Binder implements IGameAssistClientCallback {
        static final int TRANSACTION_onReceivedCallback = 1;

        private static class Proxy implements IGameAssistClientCallback {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IGameAssistClientCallback.DESCRIPTOR;
            }

            @Override // com.zte.gameassist.aiagent.IGameAssistClientCallback
            public void onReceivedCallback(int i2, String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IGameAssistClientCallback.DESCRIPTOR);
                    obtain.writeInt(i2);
                    obtain.writeString(str);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, IGameAssistClientCallback.DESCRIPTOR);
        }

        public static IGameAssistClientCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IGameAssistClientCallback.DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IGameAssistClientCallback)) ? new Proxy(iBinder) : (IGameAssistClientCallback) queryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(IGameAssistClientCallback.DESCRIPTOR);
            }
            if (i2 == 1598968902) {
                parcel2.writeString(IGameAssistClientCallback.DESCRIPTOR);
                return true;
            }
            if (i2 != 1) {
                return super.onTransact(i2, parcel, parcel2, i3);
            }
            onReceivedCallback(parcel.readInt(), parcel.readString());
            parcel2.writeNoException();
            return true;
        }
    }

    void onReceivedCallback(int i2, String str);
}
