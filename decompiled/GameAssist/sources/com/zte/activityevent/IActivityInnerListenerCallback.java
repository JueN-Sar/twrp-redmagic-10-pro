package com.zte.activityevent;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes.dex */
public interface IActivityInnerListenerCallback extends IInterface {
    public static final String DESCRIPTOR = "com.zte.activityevent.IActivityInnerListenerCallback";

    public static class Default implements IActivityInnerListenerCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.zte.activityevent.IActivityInnerListenerCallback
        public void onNotifyActivityEvent(int i2, String str, String str2, String str3, int i3, int i4, int i5) {
        }
    }

    public static abstract class Stub extends Binder implements IActivityInnerListenerCallback {
        static final int TRANSACTION_onNotifyActivityEvent = 1;

        private static class Proxy implements IActivityInnerListenerCallback {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IActivityInnerListenerCallback.DESCRIPTOR;
            }

            @Override // com.zte.activityevent.IActivityInnerListenerCallback
            public void onNotifyActivityEvent(int i2, String str, String str2, String str3, int i3, int i4, int i5) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IActivityInnerListenerCallback.DESCRIPTOR);
                    obtain.writeInt(i2);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeInt(i3);
                    obtain.writeInt(i4);
                    obtain.writeInt(i5);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, IActivityInnerListenerCallback.DESCRIPTOR);
        }

        public static IActivityInnerListenerCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IActivityInnerListenerCallback.DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IActivityInnerListenerCallback)) ? new Proxy(iBinder) : (IActivityInnerListenerCallback) queryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(IActivityInnerListenerCallback.DESCRIPTOR);
            }
            if (i2 == 1598968902) {
                parcel2.writeString(IActivityInnerListenerCallback.DESCRIPTOR);
                return true;
            }
            if (i2 != 1) {
                return super.onTransact(i2, parcel, parcel2, i3);
            }
            onNotifyActivityEvent(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
            parcel2.writeNoException();
            return true;
        }
    }

    void onNotifyActivityEvent(int i2, String str, String str2, String str3, int i3, int i4, int i5);
}
