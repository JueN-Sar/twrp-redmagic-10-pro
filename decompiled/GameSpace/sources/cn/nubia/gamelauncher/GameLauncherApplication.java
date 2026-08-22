package cn.nubia.gamelauncher;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Process;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import cn.nubia.common.CommonApplication;
import cn.nubia.common.GameKeyObserver;
import cn.nubia.common.config.GameSpaceConfig;
import cn.nubia.common.helper.HideAppsHelper;
import cn.nubia.common.util.FileOperator;
import cn.nubia.common.util.SharedPreferencesUtil;
import cn.nubia.gamecenter.settings.net.WifiStateChangedReceiver;
import cn.nubia.gamelauncher.aimhelper.NubiaGameTrackManager;
import cn.nubia.gamelauncher.commoninterface.ConstantVariable;
import cn.nubia.gamelauncher.gamecontrolpanel.performancetuning.PerfModeObserver;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.ControlPanelFeatureHelper;
import cn.nubia.gamelauncher.helper.BgmHelper;
import cn.nubia.gamelauncher.model.AppAddModel;
import cn.nubia.gamelauncher.neostore.NeoHelper;
import cn.nubia.gamelauncher.service.GameFeatureService;
import cn.nubia.gamelauncher.test.TestDataReceiver;
import cn.nubia.gamelauncher.upgrade.UpgradeManager;
import cn.nubia.gamelauncher.util.CommonUtil;
import cn.nubia.gamelauncher.util.LogUtil;
import cn.nubia.gamelauncher.util.NubiaTrackManager;
import cn.nubia.gamelauncher.util.Util;
import cn.nubia.globalsearch.GlobalSearchUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes.dex */
public class GameLauncherApplication extends Application implements GameKeyObserver.Callback {
    public static Context CONTEXT = null;
    private static final String SUPPORT_SUPER_BASE = "gamespace_support_super_base_config";
    private static final String TAG = "GameLauncherApplication";
    public static Context mContext;
    static TimerReceiver receiver;
    private CommonApplication mCommonApplication;
    private Locale mCurrentLocale;
    private boolean mNeedCheck = false;
    private WifiStateChangedReceiver mWifiStateChangedReceiver;
    TestDataReceiver testDataReceiver;
    private ArrayList<String> tiles;

    private void checkLanguageChanged(Configuration configuration) {
        Locale locale;
        if (configuration == null || configuration.getLocales().isEmpty() || (locale = configuration.getLocales().get(0)) == null) {
            return;
        }
        Locale locale2 = this.mCurrentLocale;
        if (locale2 == null) {
            this.mCurrentLocale = locale;
        } else {
            if (locale2.equals(locale)) {
                return;
            }
            this.mCurrentLocale = locale;
            onLanguageChanged();
        }
    }

    private void checkUpgrade() {
        if (CommonUtil.isInternalVersion()) {
            return;
        }
        LogUtil.d(this, "checkUpgrade()");
        UpgradeManager.getInstance().start(getAppContext());
    }

    private void checkUserData() {
        if (!hasSharedPrefs() && CONTEXT.isDeviceProtectedStorage() && isFoundOldData()) {
            LogUtil.d(this, "--->checkUserData() need copy old data to user_de!");
            FileOperator.copyDir(getDataDirPath(false), getDataDirPath(true), "glide_cache");
        }
    }

    private void doConfig() {
        Settings.Global.putString(getAppContext().getContentResolver(), SUPPORT_SUPER_BASE, GameSpaceConfig.supportBase() ? "1" : "0");
        GlobalSearchUtil.updateSearchConfigVersion(getAppContext());
    }

    private void enterGameSpaceTrackManager() {
        if (Util.isTencentAppStore()) {
            String valueOf = String.valueOf(System.currentTimeMillis());
            Bundle bundle = new Bundle();
            bundle.putString(NotificationCompat.CATEGORY_EVENT, "GameSpace");
            bundle.putString("eid", "enter_game_space");
            bundle.putString("curTime", valueOf);
            NubiaTrackManager.getInstance().sendEvent(CommonUtil.TX_TRACE_PACKAGENAME, bundle);
        }
    }

    public static Context getAppContext() {
        Context context = CONTEXT;
        return context == null ? mContext : context;
    }

    private String getDataDirPath(boolean z) {
        String path = getDataDir().getPath();
        return !z ? path.replaceFirst("user_de", "user") : path;
    }

    private static String getProcessName(Context context, int i) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService(GameFeatureService.ACTION_CONTROL_PANEL_EXTRA_ACTIVITY)).getRunningAppProcesses();
        if (runningAppProcesses != null && runningAppProcesses.size() != 0) {
            Iterator<ActivityManager.RunningAppProcessInfo> it = runningAppProcesses.iterator();
            while (it.hasNext()) {
                ActivityManager.RunningAppProcessInfo next = it.next();
                try {
                } catch (Exception e) {
                    e.printStackTrace();
                    LogUtil.w(TAG, "getProcessName err : " + e);
                }
                if (next.pid == i) {
                    LogUtil.d(TAG, "processName: " + next.processName);
                    return next.processName;
                }
                continue;
            }
        }
        return "";
    }

    private boolean hasSharedPrefs() {
        LogUtil.v(this, "--->hasSharedPrefs() data path : " + getDataDir().getPath());
        return FileOperator.isFileExists(getDataDir(), "shared_prefs");
    }

    private void init() {
        String versionName = Util.getVersionName(getApplicationContext());
        LogUtil.i(TAG, "GameLauncherApplication init(), channel : " + Util.getChannel() + ", versionName : " + versionName);
        CONTEXT = getApplicationContext();
        initCommonApplication();
        checkUserData();
        initHasPermission();
        AppAddModel.getInstance().init(CONTEXT);
        registerReceive();
        LogUtil.d(this, "init nubia ngc api !");
        NubiaTrackManager.getInstance().init(this);
        NubiaTrackManager.getInstance().sendEventEveryDay("cn.nubia.gamelauncher", "game_center_version", "version", versionName);
        NubiaGameTrackManager.getInstance();
        NubiaGameTrackManager.init(this);
        LogUtil.d(this, "init --> initNeoAndUpgrade()");
        initNeoAndUpgrade();
        GameKeyObserver.getInstance(this).addCallback(this);
        if (ControlPanelFeatureHelper.getZteFeatureZperfCubeGpsettingEnabled().booleanValue()) {
            PerfModeObserver.getInstance().register(this);
        }
        doConfig();
        LogUtil.i(TAG, "GameLauncherApplication onCreate end");
    }

    private void initCommonApplication() {
        if (this.mCommonApplication != null) {
            return;
        }
        CommonApplication commonApplication = CommonApplication.getInstance();
        this.mCommonApplication = commonApplication;
        commonApplication.onCreate(CONTEXT);
    }

    private void initNeoAndUpgrade() {
        LogUtil.d(this, "initNeoAndUpgrade() and isFirstStart : " + isFirstStartGameKey());
        if (isFirstStartGameKey() && Util.isRealGameKeyClose()) {
            this.mNeedCheck = true;
        } else {
            NeoHelper.init(this);
            checkUpgrade();
        }
    }

    private boolean isFirstStartGameKey() {
        return SharedPreferencesUtil.getInstance(getAppContext()).isFirstStartGameKey();
    }

    private boolean isFoundOldData() {
        String str = getDataDirPath(false) + "/shared_prefs";
        LogUtil.v(this, "--->isFoundOldData() data path : " + str);
        return FileOperator.isFoundSubFileInDir(str);
    }

    private boolean needInit() {
        String processName = getProcessName(this, Process.myPid());
        return !TextUtils.isEmpty(processName) && processName.equals(getPackageName());
    }

    private void onLanguageChanged() {
        LogUtil.d("config", " onLanguageChanged()");
        AppAddModel.getInstance().onLanguageChanged();
    }

    void initHasPermission() {
        SharedPreferences sharedPreferences = getSharedPreferences("data", 0);
        LogUtil.d(TAG, " boolean = " + sharedPreferences.getBoolean("has_permission", false));
        if (!sharedPreferences.getBoolean("has_permission", false) || CommonUtil.isInternalVersion()) {
            return;
        }
        ConstantVariable.HAS_PERMISSION = true;
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Util.updateDensity(getAppContext());
        checkLanguageChanged(configuration);
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        Context applicationContext = getApplicationContext();
        mContext = applicationContext;
        Util.updateDensity(applicationContext);
        startInit();
    }

    @Override // cn.nubia.common.GameKeyObserver.Callback
    public void onGameKeyChanged(boolean z) {
        Log.d("kkkkk", " onGameKeyChanged isOff = " + z);
        if (z) {
            Settings.Global.putInt(mContext.getContentResolver(), "game_mode_floating_window_show", 0);
            return;
        }
        enterGameSpaceTrackManager();
        if (this.mNeedCheck) {
            NeoHelper.init(this);
            checkUpgrade();
            SharedPreferencesUtil.getInstance(this).setFirstStartGameKey(false);
            this.mNeedCheck = false;
        }
    }

    @Override // android.app.Application
    public void onTerminate() {
        super.onTerminate();
        if (!ActivityManager.isUserAMonkey()) {
            try {
                TimerReceiver timerReceiver = receiver;
                if (timerReceiver != null) {
                    unregisterReceiver(timerReceiver);
                    receiver = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        TestDataReceiver testDataReceiver = this.testDataReceiver;
        if (testDataReceiver != null) {
            unregisterReceiver(testDataReceiver);
            this.testDataReceiver = null;
        }
        WifiStateChangedReceiver wifiStateChangedReceiver = this.mWifiStateChangedReceiver;
        if (wifiStateChangedReceiver != null) {
            unregisterReceiver(wifiStateChangedReceiver);
            this.mWifiStateChangedReceiver = null;
        }
        AppAddModel.getInstance().end();
        UpgradeManager.getInstance().end();
        HideAppsHelper.getInstance().exit();
        GameKeyObserver.getInstance(this).unregister();
        if (ControlPanelFeatureHelper.getZteFeatureZperfCubeGpsettingEnabled().booleanValue()) {
            PerfModeObserver.getInstance().unregister();
        }
        BgmHelper.getInstance().release();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [cn.nubia.gamelauncher.GameLauncherApplication$1] */
    void registerReceive() {
        new AsyncTask<Void, Void, Void>() { // from class: cn.nubia.gamelauncher.GameLauncherApplication.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... voidArr) {
                if (!ActivityManager.isUserAMonkey()) {
                    GameLauncherApplication.receiver = new TimerReceiver();
                    IntentFilter intentFilter = new IntentFilter();
                    intentFilter.addAction("android.intent.action.TIME_TICK");
                    GameLauncherApplication.this.registerReceiver(GameLauncherApplication.receiver, intentFilter, 2);
                }
                GameLauncherApplication.this.testDataReceiver = new TestDataReceiver();
                IntentFilter intentFilter2 = new IntentFilter();
                intentFilter2.addAction(TestDataReceiver.TEST_ACTION);
                GameLauncherApplication gameLauncherApplication = GameLauncherApplication.this;
                gameLauncherApplication.registerReceiver(gameLauncherApplication.testDataReceiver, intentFilter2, 2);
                return null;
            }
        }.execute(new Void[0]);
    }

    public void startInit() {
        if (needInit()) {
            init();
        }
    }
}
