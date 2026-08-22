package com.zte.zscreenshot.aidl;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.zte.zscreenshot.aidl.IZScreenshotCallback;

/* loaded from: classes2.dex */
public interface IZScreenshotController extends IInterface {
    public static final String DESCRIPTOR = "com.zte.zscreenshot.aidl.IZScreenshotController";

    public static class Default implements IZScreenshotController {
        @Override // com.zte.zscreenshot.aidl.IZScreenshotController
        public void ZScreenshot(Bundle bundle, IZScreenshotCallback iZScreenshotCallback) {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.zte.zscreenshot.aidl.IZScreenshotController
        public void invoke(String str, Bundle bundle, IZScreenshotCallback iZScreenshotCallback) {
        }

        @Override // com.zte.zscreenshot.aidl.IZScreenshotController
        public void start(Bundle bundle, IZScreenshotCallback iZScreenshotCallback) {
        }

        @Override // com.zte.zscreenshot.aidl.IZScreenshotController
        public void stop(IZScreenshotCallback iZScreenshotCallback) {
        }
    }

    public static abstract class Stub extends Binder implements IZScreenshotController {
        static final int TRANSACTION_ZScreenshot = 2;
        static final int TRANSACTION_invoke = 1;
        static final int TRANSACTION_start = 3;
        static final int TRANSACTION_stop = 4;

        private static class Proxy implements IZScreenshotController {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // com.zte.zscreenshot.aidl.IZScreenshotController
            public void ZScreenshot(Bundle bundle, IZScreenshotCallback iZScreenshotCallback) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IZScreenshotController.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    obtain.writeStrongInterface(iZScreenshotCallback);
                    this.mRemote.transact(2, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IZScreenshotController.DESCRIPTOR;
            }

            @Override // com.zte.zscreenshot.aidl.IZScreenshotController
            public void invoke(String str, Bundle bundle, IZScreenshotCallback iZScreenshotCallback) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IZScreenshotController.DESCRIPTOR);
                    obtain.writeString(str);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    obtain.writeStrongInterface(iZScreenshotCallback);
                    this.mRemote.transact(1, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.zte.zscreenshot.aidl.IZScreenshotController
            public void start(Bundle bundle, IZScreenshotCallback iZScreenshotCallback) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IZScreenshotController.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    obtain.writeStrongInterface(iZScreenshotCallback);
                    this.mRemote.transact(3, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.zte.zscreenshot.aidl.IZScreenshotController
            public void stop(IZScreenshotCallback iZScreenshotCallback) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IZScreenshotController.DESCRIPTOR);
                    obtain.writeStrongInterface(iZScreenshotCallback);
                    this.mRemote.transact(4, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, IZScreenshotController.DESCRIPTOR);
        }

        public static IZScreenshotController asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IZScreenshotController.DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IZScreenshotController)) ? new Proxy(iBinder) : (IZScreenshotController) queryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(IZScreenshotController.DESCRIPTOR);
            }
            if (i2 == 1598968902) {
                parcel2.writeString(IZScreenshotController.DESCRIPTOR);
                return true;
            }
            if (i2 == 1) {
                invoke(parcel.readString(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR), IZScreenshotCallback.Stub.asInterface(parcel.readStrongBinder()));
            } else if (i2 == 2) {
                ZScreenshot((Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR), IZScreenshotCallback.Stub.asInterface(parcel.readStrongBinder()));
            } else if (i2 == 3) {
                start((Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR), IZScreenshotCallback.Stub.asInterface(parcel.readStrongBinder()));
            } else {
                if (i2 != 4) {
                    return super.onTransact(i2, parcel, parcel2, i3);
                }
                stop(IZScreenshotCallback.Stub.asInterface(parcel.readStrongBinder()));
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
        public static <T extends Parcelable> void writeTypedObject(Parcel parcel, T t, int i2) {
            if (t == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i2);
            }
        }
    }

    void ZScreenshot(Bundle bundle, IZScreenshotCallback iZScreenshotCallback);

    void invoke(String str, Bundle bundle, IZScreenshotCallback iZScreenshotCallback);

    void start(Bundle bundle, IZScreenshotCallback iZScreenshotCallback);

    void stop(IZScreenshotCallback iZScreenshotCallback);
}
