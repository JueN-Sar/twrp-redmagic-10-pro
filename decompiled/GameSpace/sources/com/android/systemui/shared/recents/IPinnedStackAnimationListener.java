package com.android.systemui.shared.recents;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes2.dex */
public interface IPinnedStackAnimationListener extends IInterface {

    public static class Default implements IPinnedStackAnimationListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.systemui.shared.recents.IPinnedStackAnimationListener
        public void onPinnedStackAnimationStarted() throws RemoteException {
        }
    }

    public static abstract class Stub extends Binder implements IPinnedStackAnimationListener {
        private static final String DESCRIPTOR = "com.android.systemui.shared.recents.IPinnedStackAnimationListener";
        static final int TRANSACTION_onPinnedStackAnimationStarted = 1;

        private static class Proxy implements IPinnedStackAnimationListener {
            public static IPinnedStackAnimationListener sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.android.systemui.shared.recents.IPinnedStackAnimationListener
            public void onPinnedStackAnimationStarted() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onPinnedStackAnimationStarted();
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IPinnedStackAnimationListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IPinnedStackAnimationListener)) ? new Proxy(iBinder) : (IPinnedStackAnimationListener) queryLocalInterface;
        }

        public static IPinnedStackAnimationListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IPinnedStackAnimationListener iPinnedStackAnimationListener) {
            if (Proxy.sDefaultImpl != null || iPinnedStackAnimationListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iPinnedStackAnimationListener;
            return true;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onPinnedStackAnimationStarted();
                return true;
            }
            if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel2.writeString(DESCRIPTOR);
            return true;
        }
    }

    void onPinnedStackAnimationStarted() throws RemoteException;
}
