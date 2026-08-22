package com.zte.aispeaker.sentryMode;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes.dex */
public interface ISentryModeInterface extends IInterface {
    public static final String DESCRIPTOR = "com.zte.aispeaker.sentryMode.ISentryModeInterface";

    public static class Default implements ISentryModeInterface {
        @Override // com.zte.aispeaker.sentryMode.ISentryModeInterface
        public int add(int i2, int i3) {
            return 0;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements ISentryModeInterface {
        static final int TRANSACTION_add = 1;

        private static class Proxy implements ISentryModeInterface {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // com.zte.aispeaker.sentryMode.ISentryModeInterface
            public int add(int i2, int i3) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISentryModeInterface.DESCRIPTOR);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ISentryModeInterface.DESCRIPTOR;
            }
        }

        public Stub() {
            attachInterface(this, ISentryModeInterface.DESCRIPTOR);
        }

        public static ISentryModeInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(ISentryModeInterface.DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof ISentryModeInterface)) ? new Proxy(iBinder) : (ISentryModeInterface) queryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(ISentryModeInterface.DESCRIPTOR);
            }
            if (i2 == 1598968902) {
                parcel2.writeString(ISentryModeInterface.DESCRIPTOR);
                return true;
            }
            if (i2 != 1) {
                return super.onTransact(i2, parcel, parcel2, i3);
            }
            int add = add(parcel.readInt(), parcel.readInt());
            parcel2.writeNoException();
            parcel2.writeInt(add);
            return true;
        }
    }

    int add(int i2, int i3);
}
