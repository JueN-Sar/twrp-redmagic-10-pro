package com.zte.zscreenshot.aidl;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public interface IZScreenshotCallback extends IInterface {
    public static final String DESCRIPTOR = "com.zte.zscreenshot.aidl.IZScreenshotCallback";

    public static class Default implements IZScreenshotCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.zte.zscreenshot.aidl.IZScreenshotCallback
        public void callback(Bundle bundle) {
        }
    }

    public static abstract class Stub extends Binder implements IZScreenshotCallback {
        static final int TRANSACTION_callback = 1;

        private static class Proxy implements IZScreenshotCallback {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.zte.zscreenshot.aidl.IZScreenshotCallback
            public void callback(Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IZScreenshotCallback.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return IZScreenshotCallback.DESCRIPTOR;
            }
        }

        public Stub() {
            attachInterface(this, IZScreenshotCallback.DESCRIPTOR);
        }

        public static IZScreenshotCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IZScreenshotCallback.DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IZScreenshotCallback)) ? new Proxy(iBinder) : (IZScreenshotCallback) queryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(IZScreenshotCallback.DESCRIPTOR);
            }
            if (i2 == 1598968902) {
                parcel2.writeString(IZScreenshotCallback.DESCRIPTOR);
                return true;
            }
            if (i2 != 1) {
                return super.onTransact(i2, parcel, parcel2, i3);
            }
            callback((Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
            parcel2.writeNoException();
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
        public static <T extends Parcelable> void writeTypedObject(Parcel parcel, T t, int i2) {
            if (t == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i2);
            }
        }
    }

    void callback(Bundle bundle);
}
