package com.zte.activityevent;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes.dex */
public interface IActivityEventsCallbackNubia extends IInterface {
    public static final String DESCRIPTOR = "com.zte.activityevent.IActivityEventsCallbackNubia";

    public static class Default implements IActivityEventsCallbackNubia {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.zte.activityevent.IActivityEventsCallbackNubia
        public void notifiyActivityEventNubia(String str) {
        }
    }

    public static abstract class Stub extends Binder implements IActivityEventsCallbackNubia {
        static final int TRANSACTION_notifiyActivityEventNubia = 1;

        private static class Proxy implements IActivityEventsCallbackNubia {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IActivityEventsCallbackNubia.DESCRIPTOR;
            }

            @Override // com.zte.activityevent.IActivityEventsCallbackNubia
            public void notifiyActivityEventNubia(String str) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IActivityEventsCallbackNubia.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(1, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, IActivityEventsCallbackNubia.DESCRIPTOR);
        }

        public static IActivityEventsCallbackNubia asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IActivityEventsCallbackNubia.DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IActivityEventsCallbackNubia)) ? new Proxy(iBinder) : (IActivityEventsCallbackNubia) queryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(IActivityEventsCallbackNubia.DESCRIPTOR);
            }
            if (i2 == 1598968902) {
                parcel2.writeString(IActivityEventsCallbackNubia.DESCRIPTOR);
                return true;
            }
            if (i2 != 1) {
                return super.onTransact(i2, parcel, parcel2, i3);
            }
            notifiyActivityEventNubia(parcel.readString());
            return true;
        }
    }

    void notifiyActivityEventNubia(String str);
}
