package com.zte.activityevent;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes.dex */
public interface IActivityEventsCallback extends IInterface {
    public static final String DESCRIPTOR = "com.zte.activityevent.IActivityEventsCallback";

    public static class Default implements IActivityEventsCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.zte.activityevent.IActivityEventsCallback
        public void notifiyActivityEvent(int i2, String str, String str2, String str3) {
        }
    }

    public static abstract class Stub extends Binder implements IActivityEventsCallback {
        static final int TRANSACTION_notifiyActivityEvent = 1;

        private static class Proxy implements IActivityEventsCallback {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IActivityEventsCallback.DESCRIPTOR;
            }

            @Override // com.zte.activityevent.IActivityEventsCallback
            public void notifiyActivityEvent(int i2, String str, String str2, String str3) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IActivityEventsCallback.DESCRIPTOR);
                    obtain.writeInt(i2);
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
            attachInterface(this, IActivityEventsCallback.DESCRIPTOR);
        }

        public static IActivityEventsCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IActivityEventsCallback.DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IActivityEventsCallback)) ? new Proxy(iBinder) : (IActivityEventsCallback) queryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(IActivityEventsCallback.DESCRIPTOR);
            }
            if (i2 == 1598968902) {
                parcel2.writeString(IActivityEventsCallback.DESCRIPTOR);
                return true;
            }
            if (i2 != 1) {
                return super.onTransact(i2, parcel, parcel2, i3);
            }
            notifiyActivityEvent(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString());
            return true;
        }
    }

    void notifiyActivityEvent(int i2, String str, String str2, String str3);
}
