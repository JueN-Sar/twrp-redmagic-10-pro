package com.zte.gameassist.ext.common;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

/* loaded from: classes2.dex */
public interface IBaseBinder {
    public static final int TRANSACTION_CODE = 1024;
    public static final String TRANSACTION_TOKEN = "gameassist.basebinder";

    public static abstract class AbsBaseBinderProxy implements IBaseBinder {
        private IBinder mIBinder;

        @Override // com.zte.gameassist.ext.common.IBaseBinder
        public Bundle invoke(String str, Bundle bundle, boolean z) {
            Bundle bundle2 = null;
            try {
                try {
                    if (isAlive()) {
                        Parcel obtain = Parcel.obtain();
                        Parcel obtain2 = Parcel.obtain();
                        try {
                            try {
                                obtain.writeInterfaceToken(IBaseBinder.TRANSACTION_TOKEN);
                                if (str == null) {
                                    str = "";
                                }
                                obtain.writeString(str);
                                if (bundle != null) {
                                    obtain.writeInt(1);
                                    obtain.writeBundle(bundle);
                                } else {
                                    obtain.writeInt(0);
                                }
                                this.mIBinder.transact(1024, obtain, obtain2, z ? 1 : 0);
                                if (!z) {
                                    obtain2.readException();
                                    if (obtain2.readInt() == 1) {
                                        bundle2 = obtain2.readBundle();
                                    }
                                }
                                obtain.recycle();
                            } catch (Exception e) {
                                e.printStackTrace();
                                obtain.recycle();
                            }
                            obtain2.recycle();
                        } catch (Throwable th) {
                            obtain.recycle();
                            obtain2.recycle();
                            throw th;
                        }
                    } else {
                        Log.w("IBaseBinder", "gameassist is died, send action=" + str);
                    }
                    return bundle2;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return null;
                }
            } catch (Throwable unused) {
                return null;
            }
        }

        public boolean isAlive() {
            IBinder iBinder = this.mIBinder;
            return iBinder != null && iBinder.isBinderAlive();
        }

        public void setBinder(IBinder iBinder) {
            this.mIBinder = Binder.allowBlocking(iBinder);
        }
    }

    public static abstract class BaseBinder extends Binder implements IBaseBinder {
        @Override // android.os.Binder
        protected boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1024) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(IBaseBinder.TRANSACTION_TOKEN);
            Bundle invoke = invoke(parcel.readString(), parcel.readInt() == 1 ? parcel.readBundle() : new Bundle(), (i2 & 1) != 0);
            if (i2 != 1) {
                parcel2.writeNoException();
                if (invoke == null) {
                    parcel2.writeInt(0);
                } else {
                    parcel2.writeInt(1);
                    parcel2.writeBundle(invoke);
                }
            }
            return true;
        }
    }

    Bundle invoke(String str, Bundle bundle, boolean z);
}
