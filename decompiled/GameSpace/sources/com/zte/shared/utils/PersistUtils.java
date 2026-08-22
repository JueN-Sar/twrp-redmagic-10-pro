package com.zte.shared.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Parcel;
import android.util.Log;
import android.util.Slog;
import com.zte.shared.utils.PersistUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* loaded from: classes2.dex */
public final class PersistUtils implements ServiceConnection, Runnable {
    public static final String TAG = "PersistUtils";
    private static final PersistUtils U = new PersistUtils();
    private Context mContext;
    private IBinder mIPersistOperate;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final List<Consumer<IBinder>> mActions = new ArrayList();

    public interface ReadCallback {
        void onRead(String str);
    }

    private PersistUtils() {
    }

    private boolean bindPersist(Context context, Consumer<IBinder> consumer) {
        if (context == null || consumer == null) {
            return false;
        }
        this.mContext = context;
        this.mHandler.removeCallbacks(this);
        synchronized (this) {
            this.mActions.add(consumer);
        }
        IBinder iBinder = this.mIPersistOperate;
        if (iBinder != null && iBinder.isBinderAlive()) {
            doActions();
            return true;
        }
        Intent intent = new Intent("cn.nubia.intent.action.operatepersist");
        intent.setComponent(new ComponentName("cn.nubia.persist", "cn.nubia.persist.PersistService"));
        return context.bindService(intent, this, 1);
    }

    private void doActions() {
        AsyncTask.execute(new Runnable() { // from class: com.zte.shared.utils.PersistUtils$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                PersistUtils.this.m462lambda$doActions$2$comztesharedutilsPersistUtils();
            }
        });
    }

    private static String getValue(IBinder iBinder, String str) {
        String str2;
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("cn.nubia.persist.aidl.IPersistOperate");
            obtain.writeString(str);
            try {
                iBinder.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                str2 = obtain2.readString();
            } catch (Exception e) {
                Slog.e(TAG, "getValue err=" + e.getMessage());
                str2 = null;
            }
            obtain.recycle();
            obtain2.recycle();
            return str2;
        } catch (Throwable unused) {
            obtain.recycle();
            obtain2.recycle();
            return "null";
        }
    }

    public static boolean readPersist(Context context, final String str, final ReadCallback readCallback) {
        if (str != null && !str.startsWith("/persist/")) {
            return U.bindPersist(context, new Consumer() { // from class: com.zte.shared.utils.PersistUtils$$ExternalSyntheticLambda1
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    PersistUtils.ReadCallback.this.onRead(PersistUtils.getValue((IBinder) obj, str));
                }
            });
        }
        Log.w(TAG, "is not persist path, " + str);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void setValue(IBinder iBinder, String str, String str2) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("cn.nubia.persist.aidl.IPersistOperate");
            obtain.writeString(str);
            obtain.writeString(str2);
            try {
                iBinder.transact(2, obtain, obtain2, 0);
                obtain2.readException();
            } catch (Exception e) {
                Slog.e(TAG, "setValue err=" + e.getMessage());
            }
        } finally {
            obtain.recycle();
            obtain2.recycle();
        }
    }

    public static boolean writPersist(Context context, final String str, final String str2) {
        if (str != null && str.startsWith("/persist/")) {
            return U.bindPersist(context, new Consumer() { // from class: com.zte.shared.utils.PersistUtils$$ExternalSyntheticLambda0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    PersistUtils.setValue((IBinder) obj, str, str2);
                }
            });
        }
        Log.w(TAG, "is not persist path, " + str);
        return false;
    }

    /* renamed from: lambda$doActions$2$com-zte-shared-utils-PersistUtils, reason: not valid java name */
    /* synthetic */ void m462lambda$doActions$2$comztesharedutilsPersistUtils() {
        this.mHandler.removeCallbacks(this);
        ArrayList arrayList = new ArrayList();
        synchronized (this) {
            arrayList.addAll(this.mActions);
            this.mActions.clear();
        }
        for (int i = 0; i < arrayList.size(); i++) {
            ((Consumer) arrayList.get(i)).accept(this.mIPersistOperate);
        }
        if (this.mActions.size() > 0) {
            doActions();
        } else {
            this.mHandler.postDelayed(this, 5000L);
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.mIPersistOperate = iBinder;
        doActions();
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        this.mIPersistOperate = null;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.mContext.unbindService(this);
        this.mIPersistOperate = null;
    }
}
