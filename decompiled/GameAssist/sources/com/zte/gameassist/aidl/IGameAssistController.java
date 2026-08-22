package com.zte.gameassist.aidl;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.view.InputChannel;
import com.zte.gameassist.aidl.ICallback;
import java.util.List;

/* loaded from: classes2.dex */
public interface IGameAssistController extends IInterface {
    public static final String DESCRIPTOR = "com.zte.gameassist.aidl.IGameAssistController";

    public static class Default implements IGameAssistController {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.zte.gameassist.aidl.IGameAssistController
        public List<String> getGameLauncherAppNameList() {
            return null;
        }

        @Override // com.zte.gameassist.aidl.IGameAssistController
        public InputChannel getInputChannel(String str, int i2) {
            return null;
        }

        @Override // com.zte.gameassist.aidl.IGameAssistController
        public List<String> getMutexTags(String str) {
            return null;
        }

        @Override // com.zte.gameassist.aidl.IGameAssistController
        public void invake(String str, Bundle bundle, ICallback iCallback) {
        }

        @Override // com.zte.gameassist.aidl.IGameAssistController
        public boolean isInGameListForResumedActivity() {
            return false;
        }

        @Override // com.zte.gameassist.aidl.IGameAssistController
        public void monitorMutexTag(boolean z, String str, ICallback iCallback) {
        }

        @Override // com.zte.gameassist.aidl.IGameAssistController
        public void mutexTag(boolean z, String str, String str2, IBinder iBinder) {
        }

        @Override // com.zte.gameassist.aidl.IGameAssistController
        public void setOverrideScreenBrightness(float f2) {
        }
    }

    public static abstract class Stub extends Binder implements IGameAssistController {
        static final int TRANSACTION_getGameLauncherAppNameList = 3;
        static final int TRANSACTION_getInputChannel = 4;
        static final int TRANSACTION_getMutexTags = 8;
        static final int TRANSACTION_invake = 1;
        static final int TRANSACTION_isInGameListForResumedActivity = 2;
        static final int TRANSACTION_monitorMutexTag = 6;
        static final int TRANSACTION_mutexTag = 7;
        static final int TRANSACTION_setOverrideScreenBrightness = 5;

        private static class Proxy implements IGameAssistController {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.zte.gameassist.aidl.IGameAssistController
            public List<String> getGameLauncherAppNameList() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IGameAssistController.DESCRIPTOR);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createStringArrayList();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.zte.gameassist.aidl.IGameAssistController
            public InputChannel getInputChannel(String str, int i2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IGameAssistController.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return (InputChannel) obtain2.readTypedObject(InputChannel.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return IGameAssistController.DESCRIPTOR;
            }

            @Override // com.zte.gameassist.aidl.IGameAssistController
            public List<String> getMutexTags(String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IGameAssistController.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createStringArrayList();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.zte.gameassist.aidl.IGameAssistController
            public void invake(String str, Bundle bundle, ICallback iCallback) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IGameAssistController.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeTypedObject(bundle, 0);
                    obtain.writeStrongInterface(iCallback);
                    this.mRemote.transact(1, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.zte.gameassist.aidl.IGameAssistController
            public boolean isInGameListForResumedActivity() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IGameAssistController.DESCRIPTOR);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readBoolean();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.zte.gameassist.aidl.IGameAssistController
            public void monitorMutexTag(boolean z, String str, ICallback iCallback) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IGameAssistController.DESCRIPTOR);
                    obtain.writeBoolean(z);
                    obtain.writeString(str);
                    obtain.writeStrongInterface(iCallback);
                    this.mRemote.transact(6, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.zte.gameassist.aidl.IGameAssistController
            public void mutexTag(boolean z, String str, String str2, IBinder iBinder) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IGameAssistController.DESCRIPTOR);
                    obtain.writeBoolean(z);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongBinder(iBinder);
                    this.mRemote.transact(7, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.zte.gameassist.aidl.IGameAssistController
            public void setOverrideScreenBrightness(float f2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IGameAssistController.DESCRIPTOR);
                    obtain.writeFloat(f2);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, IGameAssistController.DESCRIPTOR);
        }

        public static IGameAssistController asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IGameAssistController.DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IGameAssistController)) ? new Proxy(iBinder) : (IGameAssistController) queryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(IGameAssistController.DESCRIPTOR);
            }
            if (i2 == 1598968902) {
                parcel2.writeString(IGameAssistController.DESCRIPTOR);
                return true;
            }
            switch (i2) {
                case 1:
                    invake(parcel.readString(), (Bundle) parcel.readTypedObject(Bundle.CREATOR), ICallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 2:
                    boolean isInGameListForResumedActivity = isInGameListForResumedActivity();
                    parcel2.writeNoException();
                    parcel2.writeBoolean(isInGameListForResumedActivity);
                    return true;
                case 3:
                    List<String> gameLauncherAppNameList = getGameLauncherAppNameList();
                    parcel2.writeNoException();
                    parcel2.writeStringList(gameLauncherAppNameList);
                    return true;
                case 4:
                    InputChannel inputChannel = getInputChannel(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeTypedObject(inputChannel, 1);
                    return true;
                case 5:
                    setOverrideScreenBrightness(parcel.readFloat());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    monitorMutexTag(parcel.readBoolean(), parcel.readString(), ICallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 7:
                    mutexTag(parcel.readBoolean(), parcel.readString(), parcel.readString(), parcel.readStrongBinder());
                    return true;
                case 8:
                    List<String> mutexTags = getMutexTags(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeStringList(mutexTags);
                    return true;
                default:
                    return super.onTransact(i2, parcel, parcel2, i3);
            }
        }
    }

    List<String> getGameLauncherAppNameList();

    InputChannel getInputChannel(String str, int i2);

    List<String> getMutexTags(String str);

    void invake(String str, Bundle bundle, ICallback iCallback);

    boolean isInGameListForResumedActivity();

    void monitorMutexTag(boolean z, String str, ICallback iCallback);

    void mutexTag(boolean z, String str, String str2, IBinder iBinder);

    void setOverrideScreenBrightness(float f2);
}
