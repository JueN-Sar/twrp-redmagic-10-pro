package cn.nubia.gameassist.onemorething;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.os.RemoteException;
import cn.nubia.gameassist.GameAssistApplication;
import cn.nubia.gameassist.search.GlobalSearchDatabaseHelper;
import cn.nubia.gameassist.search.GlobalSearchUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zte.gameassist.AbsGameAssistToken;
import com.zte.gameassist.common.GameCheck;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.IGameAssistCommander;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.VirtualHandleWrapper;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/* loaded from: classes.dex */
public class OneMoreThingManager implements GameMonitor.Callback, IGameAssistCommander {

    /* renamed from: n, reason: collision with root package name */
    private static final Uri f6724n = Uri.parse("content://com.zte.onemorething.contentProvider");

    /* renamed from: o, reason: collision with root package name */
    private static final Uri f6725o = Uri.parse("content://com.zte.onemorething.contentProvider/omt_info");

    /* renamed from: c, reason: collision with root package name */
    private final Context f6726c;

    /* renamed from: h, reason: collision with root package name */
    private final Handler f6727h;

    /* renamed from: i, reason: collision with root package name */
    private ContentObserver f6728i;

    /* renamed from: j, reason: collision with root package name */
    private List f6729j;

    /* renamed from: k, reason: collision with root package name */
    private boolean f6730k;

    /* renamed from: l, reason: collision with root package name */
    private IBinder f6731l;

    /* renamed from: m, reason: collision with root package name */
    private BroadcastReceiver f6732m;

    private static class Holder {

        /* renamed from: a, reason: collision with root package name */
        private static final OneMoreThingManager f6735a = new OneMoreThingManager();
    }

    private static class WorkHandler extends Handler {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference f6736a;

        public WorkHandler(OneMoreThingManager oneMoreThingManager, Looper looper) {
            super(looper);
            this.f6736a = new WeakReference(oneMoreThingManager);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            OneMoreThingManager oneMoreThingManager = (OneMoreThingManager) this.f6736a.get();
            if (oneMoreThingManager == null) {
                return;
            }
            int i2 = message.what;
            if (i2 == 1) {
                removeMessages(1);
                oneMoreThingManager.n();
            } else if (i2 == 2) {
                removeMessages(2);
                GlobalSearchUtil.p(oneMoreThingManager.f6726c);
            } else {
                if (i2 != 3) {
                    return;
                }
                removeMessages(3);
                GlobalSearchUtil.q(oneMoreThingManager.f6726c);
            }
        }
    }

    public static boolean f() {
        ComponentName componentName;
        AbsGameAssistToken.ActivityEntity activityEntity = SystemMgr.f16556q;
        if (activityEntity == null || (componentName = activityEntity.mActivity) == null) {
            return false;
        }
        String packageName = componentName.getPackageName();
        int i2 = activityEntity.mUserId;
        return ((i2 == 0 || i2 == 999) && GameCheck.h(packageName)) || "cn.nubia.gamelauncher".equals(packageName);
    }

    public static OneMoreThingManager g() {
        return Holder.f6735a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void i(String str, Bundle bundle) {
        try {
            j(str, bundle);
        } catch (Exception e2) {
            GaLog.c("OneMoreThingManager", "linkOMTProviderAsync error: ", e2);
        }
    }

    private void k(final String str, final Bundle bundle) {
        this.f6727h.post(new Runnable() { // from class: cn.nubia.gameassist.onemorething.a
            @Override // java.lang.Runnable
            public final void run() {
                OneMoreThingManager.this.i(str, bundle);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        GaLog.a("OneMoreThingManager", "request OMT info data");
        try {
            Bundle bundle = new Bundle();
            bundle.putString("packageName", this.f6726c.getPackageName());
            Bundle j2 = j("getOMTInfoData", bundle);
            if (j2 != null) {
                this.f6731l = j2.getBinder("dump");
                String string = j2.getString("OMTInfo");
                GaLog.j("OneMoreThingManager", "get omt info json = " + string);
                List<OMTInfo> list = (List) new Gson().fromJson(string, new TypeToken<List<OMTInfo>>(this) { // from class: cn.nubia.gameassist.onemorething.OneMoreThingManager.3
                }.getType());
                if (list != null) {
                    if (ZteFeature.IS_INTER_VERSION) {
                        this.f6729j = list;
                        return;
                    }
                    this.f6729j.clear();
                    for (OMTInfo oMTInfo : list) {
                        List asList = Arrays.asList(oMTInfo.games);
                        if (asList.contains(SystemMgr.t()) || asList.size() == 0) {
                            this.f6729j.add(oMTInfo);
                        }
                    }
                }
            }
        } catch (Exception e2) {
            GaLog.c("OneMoreThingManager", "request exception e = ", e2);
        }
    }

    public void e(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.flush();
        IBinder iBinder = this.f6731l;
        if (iBinder == null || !iBinder.isBinderAlive()) {
            return;
        }
        try {
            this.f6731l.dump(fileDescriptor, new String[0]);
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                this.f6731l.transact(1024, obtain, obtain2, 0);
                printWriter.println(obtain2.readString());
                obtain2.readException();
                obtain.recycle();
                obtain2.recycle();
            } catch (Throwable th) {
                obtain.recycle();
                obtain2.recycle();
                throw th;
            }
        } catch (RemoteException e2) {
            printWriter.println("dump err: " + e2.getMessage());
            e2.printStackTrace(printWriter);
        } catch (Exception e3) {
            printWriter.println("dump err: " + e3.getMessage());
            e3.printStackTrace(printWriter);
        }
    }

    @Override // com.zte.gameassist.common.IGameAssistCommander, com.zte.gameassist.AbsGameAssistToken.ICommander
    public void executive(String str, Bundle bundle, AbsGameAssistToken.Callback callback) {
        if ("getTipString".equals(str)) {
            bundle.getString("packageName");
            bundle.getInt("tipType");
            callback.callback("getTipString", bundle);
        }
    }

    public OMTInfo h() {
        List list = this.f6729j;
        if (list == null || list.size() == 0) {
            return null;
        }
        return (OMTInfo) this.f6729j.get(new Random().nextInt(this.f6729j.size()));
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0035  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.os.Bundle j(java.lang.String r3, android.os.Bundle r4) {
        /*
            r2 = this;
            r0 = 0
            android.content.Context r2 = r2.f6726c     // Catch: java.lang.Throwable -> L22 java.lang.Exception -> L24
            android.content.ContentResolver r2 = r2.getContentResolver()     // Catch: java.lang.Throwable -> L22 java.lang.Exception -> L24
            android.net.Uri r1 = cn.nubia.gameassist.onemorething.OneMoreThingManager.f6724n     // Catch: java.lang.Throwable -> L22 java.lang.Exception -> L24
            android.content.ContentProviderClient r2 = r2.acquireUnstableContentProviderClient(r1)     // Catch: java.lang.Throwable -> L22 java.lang.Exception -> L24
            if (r2 != 0) goto L15
            if (r2 == 0) goto L14
            r2.close()
        L14:
            return r0
        L15:
            android.os.Bundle r3 = r2.call(r3, r0, r4)     // Catch: java.lang.Throwable -> L1d java.lang.Exception -> L20
            r2.close()
            return r3
        L1d:
            r3 = move-exception
            r0 = r2
            goto L33
        L20:
            r3 = move-exception
            goto L26
        L22:
            r3 = move-exception
            goto L33
        L24:
            r3 = move-exception
            r2 = r0
        L26:
            java.lang.String r4 = "OneMoreThingManager"
            java.lang.String r1 = "linkOMTProvider: e = "
            com.zte.gameassist.utils.GaLog.c(r4, r1, r3)     // Catch: java.lang.Throwable -> L1d
            if (r2 == 0) goto L32
            r2.close()
        L32:
            return r0
        L33:
            if (r0 == 0) goto L38
            r0.close()
        L38:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gameassist.onemorething.OneMoreThingManager.j(java.lang.String, android.os.Bundle):android.os.Bundle");
    }

    public void l(int i2, int i3) {
        Bundle bundle = new Bundle();
        bundle.putInt(VirtualHandleWrapper.KEY_ID, i2);
        bundle.putInt("hasVote", i3);
        k("postVoteData", bundle);
    }

    public void m(boolean z) {
        if (ZteFeature.isSupportGlobalSearch()) {
            if (z) {
                GaLog.e("OneMoreThingManager", "onConfigurationChanged");
                GlobalSearchDatabaseHelper.d(this.f6726c).e();
                Handler handler = this.f6727h;
                handler.sendMessage(handler.obtainMessage(2));
                Handler handler2 = this.f6727h;
                handler2.sendMessage(handler2.obtainMessage(3));
                return;
            }
            if (GlobalSearchUtil.a(this.f6726c, "cn.nubia.gameassist")) {
                GlobalSearchDatabaseHelper.d(this.f6726c).c(true);
                Handler handler3 = this.f6727h;
                handler3.sendMessage(handler3.obtainMessage(2));
            }
            if (GlobalSearchUtil.a(this.f6726c, "cn.nubia.gamelauncher")) {
                GlobalSearchDatabaseHelper.d(this.f6726c).c(false);
                Handler handler4 = this.f6727h;
                handler4.sendMessage(handler4.obtainMessage(3));
            }
        }
    }

    public void o() {
        this.f6727h.sendEmptyMessage(1);
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    public void onFullscreenActivityChange(ComponentName componentName) {
        GaLog.a("OneMoreThingManager", "onFullscreenActivityChange enableOMTService = " + f());
        if (f() != this.f6730k) {
            this.f6730k = f();
            Bundle bundle = new Bundle();
            bundle.putBoolean("kill", !this.f6730k);
            GaLog.a("OneMoreThingManager", "onFullscreenActivityChange enableOMTService() = " + f() + ", mEnableOMTS = " + this.f6730k);
            k("kill", bundle);
        }
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStart */
    public void y() {
        GaLog.a("OneMoreThingManager", "onGameStart");
        try {
            this.f6728i = new ContentObserver(this.f6727h) { // from class: cn.nubia.gameassist.onemorething.OneMoreThingManager.1
                @Override // android.database.ContentObserver
                public void onChange(boolean z, Uri uri) {
                    if (OneMoreThingManager.f6725o.equals(uri)) {
                        OneMoreThingManager.this.n();
                        GaLog.a("OneMoreThingManager", "omt info update");
                    }
                }
            };
            this.f6726c.getContentResolver().registerContentObserver(f6725o, true, this.f6728i);
            o();
            m(false);
        } catch (Exception e2) {
            GaLog.c("OneMoreThingManager", "register observer is error:", e2);
        }
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStop */
    public void z() {
        GaLog.a("OneMoreThingManager", "onGameStop");
        try {
            if (this.f6728i != null) {
                this.f6726c.getContentResolver().unregisterContentObserver(this.f6728i);
                this.f6728i.releaseContentObserver();
                this.f6728i = null;
            }
        } catch (Exception e2) {
            GaLog.c("OneMoreThingManager", "unregister observer is error: ", e2);
        }
    }

    private OneMoreThingManager() {
        this.f6729j = new ArrayList();
        this.f6730k = true;
        this.f6732m = new BroadcastReceiver() { // from class: cn.nubia.gameassist.onemorething.OneMoreThingManager.2
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals("android.intent.action.LOCALE_CHANGED")) {
                    OneMoreThingManager.this.o();
                }
            }
        };
        Context applicationContext = GameAssistApplication.j().getApplicationContext();
        this.f6726c = applicationContext;
        SystemMgr.y(applicationContext).h(this);
        HandlerThread handlerThread = new HandlerThread("OMTThread");
        handlerThread.start();
        this.f6727h = new WorkHandler(this, handlerThread.getLooper());
        SystemMgr.y(applicationContext).o(this);
        applicationContext.registerReceiver(this.f6732m, new IntentFilter("android.intent.action.LOCALE_CHANGED"), 2);
    }
}
