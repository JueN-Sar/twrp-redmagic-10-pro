package com.zte.activityevent;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes.dex */
public interface IActivityEventsServer extends IInterface {
    public static final String DESCRIPTOR = "com.zte.activityevent.IActivityEventsServer";

    public static class Default implements IActivityEventsServer {
        @Override // com.zte.activityevent.IActivityEventsServer
        public boolean addCallBack(String str, IBinder iBinder, int i2) {
            return false;
        }

        @Override // com.zte.activityevent.IActivityEventsServer
        public boolean addCallBackNubia(String str, IBinder iBinder, int i2) {
            return false;
        }

        @Override // com.zte.activityevent.IActivityEventsServer
        public boolean addInnerCallBack(String str, IBinder iBinder, int i2) {
            return false;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.zte.activityevent.IActivityEventsServer
        public void collectActivityEventDetail(int i2, String str, String str2, String str3, int i3, int i4, int i5, String str4) {
        }

        @Override // com.zte.activityevent.IActivityEventsServer
        public boolean delCallBack(String str) {
            return false;
        }

        @Override // com.zte.activityevent.IActivityEventsServer
        public boolean delCallBackNubia(String str) {
            return false;
        }

        @Override // com.zte.activityevent.IActivityEventsServer
        public boolean delInnerCallBack(String str) {
            return false;
        }

        @Override // com.zte.activityevent.IActivityEventsServer
        public long getFirstInstallTime(String str) {
            return 0L;
        }

        @Override // com.zte.activityevent.IActivityEventsServer
        public List<Bundle> getVisiblePackageDates() {
            return null;
        }

        @Override // com.zte.activityevent.IActivityEventsServer
        public boolean hasGameAppForeground() {
            return false;
        }
    }

    public static abstract class Stub extends Binder implements IActivityEventsServer {
        static final int TRANSACTION_addCallBack = 2;
        static final int TRANSACTION_addCallBackNubia = 3;
        static final int TRANSACTION_addInnerCallBack = 1;
        static final int TRANSACTION_collectActivityEventDetail = 7;
        static final int TRANSACTION_delCallBack = 4;
        static final int TRANSACTION_delCallBackNubia = 6;
        static final int TRANSACTION_delInnerCallBack = 5;
        static final int TRANSACTION_getFirstInstallTime = 9;
        static final int TRANSACTION_getVisiblePackageDates = 8;
        static final int TRANSACTION_hasGameAppForeground = 10;

        private static class Proxy implements IActivityEventsServer {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // com.zte.activityevent.IActivityEventsServer
            public boolean addCallBack(String str, IBinder iBinder, int i2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IActivityEventsServer.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iBinder);
                    obtain.writeInt(i2);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readBoolean();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.zte.activityevent.IActivityEventsServer
            public boolean addCallBackNubia(String str, IBinder iBinder, int i2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IActivityEventsServer.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iBinder);
                    obtain.writeInt(i2);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readBoolean();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.zte.activityevent.IActivityEventsServer
            public boolean addInnerCallBack(String str, IBinder iBinder, int i2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IActivityEventsServer.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iBinder);
                    obtain.writeInt(i2);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readBoolean();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.zte.activityevent.IActivityEventsServer
            public void collectActivityEventDetail(int i2, String str, String str2, String str3, int i3, int i4, int i5, String str4) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IActivityEventsServer.DESCRIPTOR);
                    obtain.writeInt(i2);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeInt(i3);
                    obtain.writeInt(i4);
                    obtain.writeInt(i5);
                    obtain.writeString(str4);
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.zte.activityevent.IActivityEventsServer
            public boolean delCallBack(String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IActivityEventsServer.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readBoolean();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.zte.activityevent.IActivityEventsServer
            public boolean delCallBackNubia(String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IActivityEventsServer.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readBoolean();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.zte.activityevent.IActivityEventsServer
            public boolean delInnerCallBack(String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IActivityEventsServer.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readBoolean();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.zte.activityevent.IActivityEventsServer
            public long getFirstInstallTime(String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IActivityEventsServer.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readLong();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return IActivityEventsServer.DESCRIPTOR;
            }

            @Override // com.zte.activityevent.IActivityEventsServer
            public List<Bundle> getVisiblePackageDates() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IActivityEventsServer.DESCRIPTOR);
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(Bundle.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.zte.activityevent.IActivityEventsServer
            public boolean hasGameAppForeground() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IActivityEventsServer.DESCRIPTOR);
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readBoolean();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, IActivityEventsServer.DESCRIPTOR);
        }

        public static IActivityEventsServer asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IActivityEventsServer.DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IActivityEventsServer)) ? new Proxy(iBinder) : (IActivityEventsServer) queryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(IActivityEventsServer.DESCRIPTOR);
            }
            if (i2 == 1598968902) {
                parcel2.writeString(IActivityEventsServer.DESCRIPTOR);
                return true;
            }
            switch (i2) {
                case 1:
                    boolean addInnerCallBack = addInnerCallBack(parcel.readString(), parcel.readStrongBinder(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeBoolean(addInnerCallBack);
                    return true;
                case 2:
                    boolean addCallBack = addCallBack(parcel.readString(), parcel.readStrongBinder(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeBoolean(addCallBack);
                    return true;
                case 3:
                    boolean addCallBackNubia = addCallBackNubia(parcel.readString(), parcel.readStrongBinder(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeBoolean(addCallBackNubia);
                    return true;
                case 4:
                    boolean delCallBack = delCallBack(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeBoolean(delCallBack);
                    return true;
                case 5:
                    boolean delInnerCallBack = delInnerCallBack(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeBoolean(delInnerCallBack);
                    return true;
                case 6:
                    boolean delCallBackNubia = delCallBackNubia(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeBoolean(delCallBackNubia);
                    return true;
                case 7:
                    collectActivityEventDetail(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 8:
                    List<Bundle> visiblePackageDates = getVisiblePackageDates();
                    parcel2.writeNoException();
                    _Parcel.b(parcel2, visiblePackageDates, 1);
                    return true;
                case 9:
                    long firstInstallTime = getFirstInstallTime(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeLong(firstInstallTime);
                    return true;
                case 10:
                    boolean hasGameAppForeground = hasGameAppForeground();
                    parcel2.writeNoException();
                    parcel2.writeBoolean(hasGameAppForeground);
                    return true;
                default:
                    return super.onTransact(i2, parcel, parcel2, i3);
            }
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
                parcel.writeTypedObject((Parcelable) list.get(i3), i2);
            }
        }
    }

    boolean addCallBack(String str, IBinder iBinder, int i2);

    boolean addCallBackNubia(String str, IBinder iBinder, int i2);

    boolean addInnerCallBack(String str, IBinder iBinder, int i2);

    void collectActivityEventDetail(int i2, String str, String str2, String str3, int i3, int i4, int i5, String str4);

    boolean delCallBack(String str);

    boolean delCallBackNubia(String str);

    boolean delInnerCallBack(String str);

    long getFirstInstallTime(String str);

    List<Bundle> getVisiblePackageDates();

    boolean hasGameAppForeground();
}
