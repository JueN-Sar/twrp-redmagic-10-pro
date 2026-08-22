package com.zte.gameassist.aidl;

import android.content.ComponentName;
import android.graphics.Rect;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.zte.gameassist.aidl.ICallback;
import com.zte.gameassist.aidl.IGameAssistController;

/* loaded from: classes2.dex */
public interface IGameAssist extends IInterface {
    public static final String DESCRIPTOR = "com.zte.gameassist.aidl.IGameAssist";

    public static class Default implements IGameAssist {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.zte.gameassist.aidl.IGameAssist
        public void invake(String str, Bundle bundle, ICallback iCallback) {
        }

        @Override // com.zte.gameassist.aidl.IGameAssist
        public void notifyActivityResumed(ComponentName componentName, boolean z, int i2, int i3, int i4, int i5, int i6) {
        }

        @Override // com.zte.gameassist.aidl.IGameAssist
        public void notifyFocuesWindowChanged(String str, int i2, Rect rect) {
        }

        @Override // com.zte.gameassist.aidl.IGameAssist
        public void notifyFullActivityFirstCreate(ComponentName componentName, boolean z, int i2, int i3, int i4, int i5, int i6) {
        }

        @Override // com.zte.gameassist.aidl.IGameAssist
        public void notifySystemWindowChanged(boolean z, String str, String str2, int i2) {
        }

        @Override // com.zte.gameassist.aidl.IGameAssist
        public void onControllerConnected(IGameAssistController iGameAssistController) {
        }
    }

    public static abstract class Stub extends Binder implements IGameAssist {
        static final int TRANSACTION_invake = 2;
        static final int TRANSACTION_notifyActivityResumed = 4;
        static final int TRANSACTION_notifyFocuesWindowChanged = 5;
        static final int TRANSACTION_notifyFullActivityFirstCreate = 3;
        static final int TRANSACTION_notifySystemWindowChanged = 6;
        static final int TRANSACTION_onControllerConnected = 1;

        private static class Proxy implements IGameAssist {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IGameAssist.DESCRIPTOR;
            }

            @Override // com.zte.gameassist.aidl.IGameAssist
            public void invake(String str, Bundle bundle, ICallback iCallback) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IGameAssist.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeTypedObject(bundle, 0);
                    obtain.writeStrongInterface(iCallback);
                    this.mRemote.transact(2, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.zte.gameassist.aidl.IGameAssist
            public void notifyActivityResumed(ComponentName componentName, boolean z, int i2, int i3, int i4, int i5, int i6) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IGameAssist.DESCRIPTOR);
                    obtain.writeTypedObject(componentName, 0);
                    obtain.writeBoolean(z);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    obtain.writeInt(i4);
                    obtain.writeInt(i5);
                    obtain.writeInt(i6);
                    this.mRemote.transact(4, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.zte.gameassist.aidl.IGameAssist
            public void notifyFocuesWindowChanged(String str, int i2, Rect rect) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IGameAssist.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    obtain.writeTypedObject(rect, 0);
                    this.mRemote.transact(5, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.zte.gameassist.aidl.IGameAssist
            public void notifyFullActivityFirstCreate(ComponentName componentName, boolean z, int i2, int i3, int i4, int i5, int i6) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IGameAssist.DESCRIPTOR);
                    obtain.writeTypedObject(componentName, 0);
                    obtain.writeBoolean(z);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    obtain.writeInt(i4);
                    obtain.writeInt(i5);
                    obtain.writeInt(i6);
                    this.mRemote.transact(3, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.zte.gameassist.aidl.IGameAssist
            public void notifySystemWindowChanged(boolean z, String str, String str2, int i2) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IGameAssist.DESCRIPTOR);
                    obtain.writeBoolean(z);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(i2);
                    this.mRemote.transact(6, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.zte.gameassist.aidl.IGameAssist
            public void onControllerConnected(IGameAssistController iGameAssistController) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IGameAssist.DESCRIPTOR);
                    obtain.writeStrongInterface(iGameAssistController);
                    this.mRemote.transact(1, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, IGameAssist.DESCRIPTOR);
        }

        public static IGameAssist asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IGameAssist.DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IGameAssist)) ? new Proxy(iBinder) : (IGameAssist) queryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(IGameAssist.DESCRIPTOR);
            }
            if (i2 == 1598968902) {
                parcel2.writeString(IGameAssist.DESCRIPTOR);
                return true;
            }
            switch (i2) {
                case 1:
                    onControllerConnected(IGameAssistController.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 2:
                    invake(parcel.readString(), (Bundle) parcel.readTypedObject(Bundle.CREATOR), ICallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 3:
                    notifyFullActivityFirstCreate((ComponentName) parcel.readTypedObject(ComponentName.CREATOR), parcel.readBoolean(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    return true;
                case 4:
                    notifyActivityResumed((ComponentName) parcel.readTypedObject(ComponentName.CREATOR), parcel.readBoolean(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    return true;
                case 5:
                    notifyFocuesWindowChanged(parcel.readString(), parcel.readInt(), (Rect) parcel.readTypedObject(Rect.CREATOR));
                    return true;
                case 6:
                    notifySystemWindowChanged(parcel.readBoolean(), parcel.readString(), parcel.readString(), parcel.readInt());
                    return true;
                default:
                    return super.onTransact(i2, parcel, parcel2, i3);
            }
        }
    }

    void invake(String str, Bundle bundle, ICallback iCallback);

    void notifyActivityResumed(ComponentName componentName, boolean z, int i2, int i3, int i4, int i5, int i6);

    void notifyFocuesWindowChanged(String str, int i2, Rect rect);

    void notifyFullActivityFirstCreate(ComponentName componentName, boolean z, int i2, int i3, int i4, int i5, int i6);

    void notifySystemWindowChanged(boolean z, String str, String str2, int i2);

    void onControllerConnected(IGameAssistController iGameAssistController);
}
