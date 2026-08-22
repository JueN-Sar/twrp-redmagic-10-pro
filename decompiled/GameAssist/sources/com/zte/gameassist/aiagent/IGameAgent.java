package com.zte.gameassist.aiagent;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;

/* loaded from: classes2.dex */
public interface IGameAgent extends IInterface {
    public static final String DESCRIPTOR = "com.zte.gameassist.aiagent.IGameAgent";

    public static class Default implements IGameAgent {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.zte.gameassist.aiagent.IGameAgent
        public void onWindowStateChanged(int i2) {
        }

        @Override // com.zte.gameassist.aiagent.IGameAgent
        public void registerCallback(String str, IGameAssistClientCallback iGameAssistClientCallback) {
        }

        @Override // com.zte.gameassist.aiagent.IGameAgent
        public void sendMessage(String str, String str2) {
        }

        @Override // com.zte.gameassist.aiagent.IGameAgent
        public void sendTextMessage(String str, String str2) {
        }

        @Override // com.zte.gameassist.aiagent.IGameAgent
        public void unregisterCallback(String str, IGameAssistClientCallback iGameAssistClientCallback) {
        }
    }

    public static abstract class Stub extends Binder implements IGameAgent {
        static final int TRANSACTION_onWindowStateChanged = 3;
        static final int TRANSACTION_registerCallback = 4;
        static final int TRANSACTION_sendMessage = 1;
        static final int TRANSACTION_sendTextMessage = 2;
        static final int TRANSACTION_unregisterCallback = 5;

        private static class Proxy implements IGameAgent {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IGameAgent.DESCRIPTOR;
            }

            @Override // com.zte.gameassist.aiagent.IGameAgent
            public void onWindowStateChanged(int i2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IGameAgent.DESCRIPTOR);
                    obtain.writeInt(i2);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.zte.gameassist.aiagent.IGameAgent
            public void registerCallback(String str, IGameAssistClientCallback iGameAssistClientCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IGameAgent.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongInterface(iGameAssistClientCallback);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.zte.gameassist.aiagent.IGameAgent
            public void sendMessage(String str, String str2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IGameAgent.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.zte.gameassist.aiagent.IGameAgent
            public void sendTextMessage(String str, String str2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IGameAgent.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.zte.gameassist.aiagent.IGameAgent
            public void unregisterCallback(String str, IGameAssistClientCallback iGameAssistClientCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IGameAgent.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongInterface(iGameAssistClientCallback);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, IGameAgent.DESCRIPTOR);
        }

        public static IGameAgent asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IGameAgent.DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IGameAgent)) ? new Proxy(iBinder) : (IGameAgent) queryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(IGameAgent.DESCRIPTOR);
            }
            if (i2 == 1598968902) {
                parcel2.writeString(IGameAgent.DESCRIPTOR);
                return true;
            }
            if (i2 == 1) {
                sendMessage(parcel.readString(), parcel.readString());
                parcel2.writeNoException();
            } else if (i2 == 2) {
                sendTextMessage(parcel.readString(), parcel.readString());
                parcel2.writeNoException();
            } else if (i2 == 3) {
                onWindowStateChanged(parcel.readInt());
                parcel2.writeNoException();
            } else if (i2 == 4) {
                registerCallback(parcel.readString(), IGameAssistClientCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
            } else {
                if (i2 != 5) {
                    return super.onTransact(i2, parcel, parcel2, i3);
                }
                unregisterCallback(parcel.readString(), IGameAssistClientCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
            }
            return true;
        }
    }

    void onWindowStateChanged(int i2);

    void registerCallback(String str, IGameAssistClientCallback iGameAssistClientCallback);

    void sendMessage(String str, String str2);

    void sendTextMessage(String str, String str2);

    void unregisterCallback(String str, IGameAssistClientCallback iGameAssistClientCallback);
}
