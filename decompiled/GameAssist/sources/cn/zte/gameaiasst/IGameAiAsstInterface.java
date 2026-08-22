package cn.zte.gameaiasst;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import cn.zte.gameaiasst.IGameAiAsstCallback;

/* loaded from: classes.dex */
public interface IGameAiAsstInterface extends IInterface {
    public static final String DESCRIPTOR = "cn.zte.gameaiasst.IGameAiAsstInterface";

    public static class Default implements IGameAiAsstInterface {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // cn.zte.gameaiasst.IGameAiAsstInterface
        public void close() {
        }

        @Override // cn.zte.gameaiasst.IGameAiAsstInterface
        public void registerCallback(IGameAiAsstCallback iGameAiAsstCallback) {
        }

        @Override // cn.zte.gameaiasst.IGameAiAsstInterface
        public void requestGameAIAsst(String str, int i2) {
        }

        @Override // cn.zte.gameaiasst.IGameAiAsstInterface
        public void unregisterCallback(IGameAiAsstCallback iGameAiAsstCallback) {
        }
    }

    public static abstract class Stub extends Binder implements IGameAiAsstInterface {
        static final int TRANSACTION_close = 4;
        static final int TRANSACTION_registerCallback = 1;
        static final int TRANSACTION_requestGameAIAsst = 3;
        static final int TRANSACTION_unregisterCallback = 2;

        private static class Proxy implements IGameAiAsstInterface {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // cn.zte.gameaiasst.IGameAiAsstInterface
            public void close() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IGameAiAsstInterface.DESCRIPTOR);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return IGameAiAsstInterface.DESCRIPTOR;
            }

            @Override // cn.zte.gameaiasst.IGameAiAsstInterface
            public void registerCallback(IGameAiAsstCallback iGameAiAsstCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IGameAiAsstInterface.DESCRIPTOR);
                    obtain.writeStrongInterface(iGameAiAsstCallback);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // cn.zte.gameaiasst.IGameAiAsstInterface
            public void requestGameAIAsst(String str, int i2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IGameAiAsstInterface.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // cn.zte.gameaiasst.IGameAiAsstInterface
            public void unregisterCallback(IGameAiAsstCallback iGameAiAsstCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IGameAiAsstInterface.DESCRIPTOR);
                    obtain.writeStrongInterface(iGameAiAsstCallback);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, IGameAiAsstInterface.DESCRIPTOR);
        }

        public static IGameAiAsstInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IGameAiAsstInterface.DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IGameAiAsstInterface)) ? new Proxy(iBinder) : (IGameAiAsstInterface) queryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(IGameAiAsstInterface.DESCRIPTOR);
            }
            if (i2 == 1598968902) {
                parcel2.writeString(IGameAiAsstInterface.DESCRIPTOR);
                return true;
            }
            if (i2 == 1) {
                registerCallback(IGameAiAsstCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
            } else if (i2 == 2) {
                unregisterCallback(IGameAiAsstCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
            } else if (i2 == 3) {
                requestGameAIAsst(parcel.readString(), parcel.readInt());
                parcel2.writeNoException();
            } else {
                if (i2 != 4) {
                    return super.onTransact(i2, parcel, parcel2, i3);
                }
                close();
                parcel2.writeNoException();
            }
            return true;
        }
    }

    void close();

    void registerCallback(IGameAiAsstCallback iGameAiAsstCallback);

    void requestGameAIAsst(String str, int i2);

    void unregisterCallback(IGameAiAsstCallback iGameAiAsstCallback);
}
