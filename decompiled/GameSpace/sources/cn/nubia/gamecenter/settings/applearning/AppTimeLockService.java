package cn.nubia.gamecenter.settings.applearning;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import cn.nubia.gamecenter.settings.utils.LogUtil;
import com.zte.activityevent.IActivityEventsServer;
import com.zte.activityevent.IActivityInnerListenerCallback;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public class AppTimeLockService extends Service {
    public static final int AM_FREE_TO_FULL = 2048;
    public static final int AM_RESUME_ACTIVITY = 1;
    public static final int AM_STOP_ACTIVITY = 16;
    public static final int AM_TOP_RESUMED_GAINED = 4096;
    private static final String INNER_CALLBACK_TAG = "gamespaceHealthReminderActivityListernerInnerCallback";
    public static final String TAG_LOG = "AppTimeLockService";
    private IActivityEventsServer mActivityServer;
    private AppTimeLockHelper mAppTimeLockHelper;
    private Context mContext;
    private String mPrePkgName;
    private Handler mWatcherHandler;
    private volatile Looper mWatcherLooper;
    private Object mObject = new Object();
    private AppActivityEventCallback mActivityListenerCallback = new AppActivityEventCallback();
    final BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: cn.nubia.gamecenter.settings.applearning.AppTimeLockService.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
                AppTimeLockService.this.handleScreenOffAct();
            }
        }
    };

    class AppActivityEventCallback extends IActivityInnerListenerCallback.Stub {
        public AppActivityEventCallback() {
            LogUtil.d(AppTimeLockService.TAG_LOG, "AppActivityEventCallback init");
        }

        @Override // com.zte.activityevent.IActivityInnerListenerCallback
        public void onNotifyActivityEvent(final int i, final String str, String str2, String str3, int i2, int i3, int i4) throws RemoteException {
            LogUtil.i(AppTimeLockService.TAG_LOG, "inner callback pkg:" + str + ", windowMode:" + i2 + ", eventid:" + i);
            if (AppTimeLockService.this.mWatcherHandler != null) {
                AppTimeLockService.this.mWatcherHandler.post(new Runnable() { // from class: cn.nubia.gamecenter.settings.applearning.AppTimeLockService.AppActivityEventCallback.1
                    @Override // java.lang.Runnable
                    public void run() {
                        int i5 = i;
                        if (i5 != 1) {
                            if (i5 == 16) {
                                AppTimeLockService.this.handleStopMessage(str);
                                return;
                            } else if (i5 != 2048) {
                                return;
                            }
                        }
                        AppTimeLockService.this.handleResumeMessage(str);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleResumeMessage(String str) {
        synchronized (this.mObject) {
            LogUtil.i(TAG_LOG, str + ", " + this.mPrePkgName);
            if (!str.equals(this.mPrePkgName)) {
                this.mPrePkgName = str;
                ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();
                concurrentHashMap.put(str, 0);
                this.mAppTimeLockHelper.delayStartUnlockAppActivity(concurrentHashMap);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleStopMessage(String str) {
        if (str == null || !str.equals(this.mPrePkgName)) {
            LogUtil.d(TAG_LOG, "handleOtherStopMessage,pkgname = " + str);
        } else {
            this.mAppTimeLockHelper.removeDelayCallbacks(str);
        }
    }

    private void registerActivityWatcher() {
        LogUtil.i(TAG_LOG, "registerActivityWatcher");
        try {
            Class<?> cls = Class.forName("android.os.ServiceManager");
            if (cls == null) {
                LogUtil.w(TAG_LOG, "servicemanager null");
                return;
            }
            Method declaredMethod = cls.getDeclaredMethod("getService", String.class);
            if (declaredMethod == null) {
                LogUtil.w(TAG_LOG, "servicemanager getService null");
                return;
            }
            IActivityEventsServer asInterface = IActivityEventsServer.Stub.asInterface((IBinder) declaredMethod.invoke(null, "activityevent"));
            this.mActivityServer = asInterface;
            if (asInterface == null) {
                LogUtil.w(TAG_LOG, "servicemanager getService null");
            } else {
                this.mWatcherHandler.post(new Runnable() { // from class: cn.nubia.gamecenter.settings.applearning.AppTimeLockService.2
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            AppTimeLockService.this.mActivityServer.addInnerCallBack(AppTimeLockService.INNER_CALLBACK_TAG, AppTimeLockService.this.mActivityListenerCallback, 2065);
                        } catch (Exception e) {
                            LogUtil.w(AppTimeLockService.TAG_LOG, e);
                        }
                    }
                });
            }
        } catch (Exception e) {
            LogUtil.w(TAG_LOG, e);
        }
    }

    private void registerDynamicListener() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.setPriority(1000);
        this.mContext.registerReceiver(this.mReceiver, intentFilter, 2);
        this.mAppTimeLockHelper.register();
    }

    private void unregisterActivityWatcher() {
        LogUtil.i(TAG_LOG, "unRegisterWatcher");
        IActivityEventsServer iActivityEventsServer = this.mActivityServer;
        if (iActivityEventsServer != null) {
            try {
                iActivityEventsServer.delInnerCallBack(INNER_CALLBACK_TAG);
            } catch (RemoteException unused) {
                LogUtil.e(TAG_LOG, "remove callback error");
            } catch (Exception unused2) {
                LogUtil.e(TAG_LOG, "remove callback sth error, skip");
            }
        }
    }

    private void unregisterDynamicListener() {
        this.mContext.unregisterReceiver(this.mReceiver);
        this.mAppTimeLockHelper.unregister();
    }

    public void handleScreenOffAct() {
        Handler handler = this.mWatcherHandler;
        if (handler != null) {
            handler.post(new Runnable() { // from class: cn.nubia.gamecenter.settings.applearning.AppTimeLockService.3
                @Override // java.lang.Runnable
                public void run() {
                    AppTimeLockService.this.handleResumeMessage("cn.nubia.screenoff");
                }
            });
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        LogUtil.i(TAG_LOG, "onCreate");
        this.mContext = getApplicationContext();
        this.mAppTimeLockHelper = new AppTimeLockHelper(this.mContext);
        HandlerThread handlerThread = new HandlerThread(TAG_LOG);
        handlerThread.start();
        this.mWatcherLooper = handlerThread.getLooper();
        this.mWatcherHandler = new Handler(this.mWatcherLooper);
        registerActivityWatcher();
        registerDynamicListener();
        this.mAppTimeLockHelper.updateOneKeyAsStartService();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        unregisterDynamicListener();
        unregisterActivityWatcher();
        Handler handler = this.mWatcherHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        return 1;
    }
}
