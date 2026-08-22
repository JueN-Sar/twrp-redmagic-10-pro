package com.zte.streamgame.aidl;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

/* loaded from: classes2.dex */
public interface IStreamGameCallback extends IInterface {
    public static final String DESCRIPTOR = "com.zte.streamgame.aidl.IStreamGameCallback";

    public static class Default implements IStreamGameCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.zte.streamgame.aidl.IStreamGameCallback
        public void bundleCallback(Bundle bundle) throws RemoteException {
        }

        @Override // com.zte.streamgame.aidl.IStreamGameCallback
        public void responseCallback(String str) throws RemoteException {
        }
    }

    public static abstract class Stub extends Binder implements IStreamGameCallback {
        static final int TRANSACTION_bundleCallback = 2;
        static final int TRANSACTION_responseCallback = 1;

        private static class Proxy implements IStreamGameCallback {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.zte.streamgame.aidl.IStreamGameCallback
            public void bundleCallback(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IStreamGameCallback.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return IStreamGameCallback.DESCRIPTOR;
            }

            @Override // com.zte.streamgame.aidl.IStreamGameCallback
            public void responseCallback(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IStreamGameCallback.DESCRIPTOR);
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
            attachInterface(this, IStreamGameCallback.DESCRIPTOR);
        }

        public static IStreamGameCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IStreamGameCallback.DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IStreamGameCallback)) ? new Proxy(iBinder) : (IStreamGameCallback) queryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IStreamGameCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IStreamGameCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                responseCallback(parcel.readString());
                parcel2.writeNoException();
            } else {
                if (i != 2) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                bundleCallback((Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
            }
            return true;
        }
    }

    public static class _Parcel {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T readTypedObject(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void writeTypedObject(Parcel parcel, T t, int i) {
            if (t == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            }
        }
    }

    void bundleCallback(Bundle bundle) throws RemoteException;

    void responseCallback(String str) throws RemoteException;
}
