package cn.nubia.gamelab;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes.dex */
public interface IToyCallback extends IInterface {
    public static final String DESCRIPTOR = "cn.nubia.gamelab.IToyCallback";

    public static class Default implements IToyCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // cn.nubia.gamelab.IToyCallback
        public void notifyEvent(List<Bundle> list) {
        }
    }

    public static abstract class Stub extends Binder implements IToyCallback {
        static final int TRANSACTION_notifyEvent = 1;

        private static class Proxy implements IToyCallback {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IToyCallback.DESCRIPTOR;
            }

            @Override // cn.nubia.gamelab.IToyCallback
            public void notifyEvent(List<Bundle> list) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IToyCallback.DESCRIPTOR);
                    _Parcel.b(obtain, list, 0);
                    this.mRemote.transact(1, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, IToyCallback.DESCRIPTOR);
        }

        public static IToyCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IToyCallback.DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IToyCallback)) ? new Proxy(iBinder) : (IToyCallback) queryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(IToyCallback.DESCRIPTOR);
            }
            if (i2 == 1598968902) {
                parcel2.writeString(IToyCallback.DESCRIPTOR);
                return true;
            }
            if (i2 != 1) {
                return super.onTransact(i2, parcel, parcel2, i3);
            }
            notifyEvent(parcel.createTypedArrayList(Bundle.CREATOR));
            return true;
        }
    }

    public static class _Parcel {
        /* JADX INFO: Access modifiers changed from: private */
        public static void b(Parcel parcel, List list, int i2) {
            if (list == null) {
                parcel.writeInt(-1);
                return;
            }
            int size = list.size();
            parcel.writeInt(size);
            for (int i3 = 0; i3 < size; i3++) {
                c(parcel, (Parcelable) list.get(i3), i2);
            }
        }

        private static void c(Parcel parcel, Parcelable parcelable, int i2) {
            if (parcelable == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                parcelable.writeToParcel(parcel, i2);
            }
        }
    }

    void notifyEvent(List<Bundle> list);
}
