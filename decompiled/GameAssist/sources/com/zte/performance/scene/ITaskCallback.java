package com.zte.performance.scene;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Message;
import android.os.Parcel;

/* loaded from: classes2.dex */
public interface ITaskCallback extends IInterface {
    public static final String DESCRIPTOR = "com.zte.performance.scene.ITaskCallback";

    public static class Default implements ITaskCallback {
        @Override // com.zte.performance.scene.ITaskCallback
        public void actionPerformed(Message message) {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements ITaskCallback {
        static final int TRANSACTION_actionPerformed = 1;

        private static class Proxy implements ITaskCallback {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // com.zte.performance.scene.ITaskCallback
            public void actionPerformed(Message message) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITaskCallback.DESCRIPTOR);
                    obtain.writeTypedObject(message, 0);
                    this.mRemote.transact(1, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ITaskCallback.DESCRIPTOR;
            }
        }

        public Stub() {
            attachInterface(this, ITaskCallback.DESCRIPTOR);
        }

        public static ITaskCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(ITaskCallback.DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof ITaskCallback)) ? new Proxy(iBinder) : (ITaskCallback) queryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(ITaskCallback.DESCRIPTOR);
            }
            if (i2 == 1598968902) {
                parcel2.writeString(ITaskCallback.DESCRIPTOR);
                return true;
            }
            if (i2 != 1) {
                return super.onTransact(i2, parcel, parcel2, i3);
            }
            actionPerformed((Message) parcel.readTypedObject(Message.CREATOR));
            return true;
        }
    }

    void actionPerformed(Message message);
}
