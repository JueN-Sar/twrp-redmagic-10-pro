package cn.nubia.gamelauncher.upgrade;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import androidx.core.content.FileProvider;
import cn.nubia.common.util.SharedPreferencesUtil;
import cn.nubia.gamelauncher.GameLauncherApplication;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.upgrade.UpgradeNotification;
import cn.nubia.gamelauncher.util.LogUtil;
import cn.nubia.gamelauncher.util.Util;
import cn.nubia.tgk.TgkHelper;
import cn.nubia.upgrade.api.NubiaUpdateConfiguration;
import cn.nubia.upgrade.api.NubiaUpgradeManager;
import cn.nubia.upgrade.api.RunMode;
import cn.nubia.upgrade.http.IDownLoadListener;
import cn.nubia.upgrade.http.IGetVersionListener;
import cn.nubia.upgrade.model.VersionData;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Objects;

/* loaded from: classes.dex */
public class UpgradeManager implements IGetVersionListener, IDownLoadListener, UpgradeNotification.OnClickListener {
    private static final int MANUAL_CHECK_TIMEOUT = 5000;
    private static final int MB = 1048576;
    private static final int MIN_TIME_INTERVAL = 300;
    private static final int MSG_IDLE_EXIT = 1;
    private static final int MSG_MANUAL_CHECK_TIMEOUT = 0;
    private static final int STATE_CHECKING = 1;
    private static final int STATE_DOWNLOADING = 2;
    private static final int STATE_IDLE = 0;
    private static final int STATE_INSTALLING = 4;
    private static final int STATE_PAUSED = 3;
    public static final String TAG = "Upgrade";
    private final String ROM_APPKEY;
    private final String ROM_SECRETKEY;
    private final String TEST_APPKEY;
    private final String TEST_SECRETKEY;
    private final String TEST_UNIQUE_KEY;
    private String mApkPath;
    Callback mCallback;
    ConnectivityManager mCmgr;
    private Context mContext;
    private final Handler mHandler;
    Runnable mHideRedPointRunnable;
    private boolean mIsManualCheckUpgrade;
    private boolean mIsPausedByClick;
    private boolean mIsWaitNetConnected;
    private boolean mIsWifiOnly;
    long mLastUpdateNotifTime;
    private int mLastUpgradeState;
    NetworkCallbackImpl mNetworkCallback;
    UpgradeNotification mNotification;
    private NubiaUpgradeManager mNubiaUpgradeManager;
    private int mProgress;
    Runnable mShowRedPointRunnable;
    private NubiaUpdateConfiguration mUpgradeConfiguration;
    private int mUpgradeState;
    private VersionData mVersionData;
    private int mWaitShowState;
    Runnable mWakeExternDeviceRunnable;
    private Handler mWorkHandler;
    private HandlerThread mWorkThread;

    public interface Callback {
        void dismissDialog(boolean z);

        void updateDialog(int i);
    }

    class NetworkCallbackImpl extends ConnectivityManager.NetworkCallback {
        NetworkCallbackImpl() {
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onAvailable(Network network) {
            super.onAvailable(network);
            StringBuilder append = new StringBuilder("onAvailable() network = ").append(network).append(", state : ");
            UpgradeManager upgradeManager = UpgradeManager.this;
            Log.d("Upgrade", append.append(upgradeManager.stateToString(upgradeManager.mUpgradeState)).toString());
            if (UpgradeManager.this.mIsWaitNetConnected) {
                UpgradeManager upgradeManager2 = UpgradeManager.this;
                upgradeManager2.resumeUnfinishedIfNeed(upgradeManager2.mUpgradeState);
            }
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            super.onCapabilitiesChanged(network, networkCapabilities);
            if (UpgradeManager.this.mIsWaitNetConnected && UpgradeManager.this.isDownloadStarted() && networkCapabilities.hasCapability(16)) {
                Log.d("Upgrade", "onCapabilitiesChanged()");
                if (networkCapabilities.hasCapability(1) || networkCapabilities.hasCapability(3)) {
                    Log.d("Upgrade", "onCapabilitiesChanged() WIFI ");
                    UpgradeManager.this.resumeDownloadIfNeed();
                    return;
                }
                Log.d("Upgrade", "onCapabilitiesChanged() Mobile ");
                if (UpgradeManager.this.mIsWifiOnly && UpgradeManager.this.isDownloading()) {
                    UpgradeManager.this.pauseDownload(false);
                }
            }
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLost(Network network) {
            super.onLost(network);
            Log.d("Upgrade", "onLost() network = " + UpgradeManager.this.isNetworkConnected());
        }
    }

    private static class UpgradeManagerHolder {
        public static final UpgradeManager INSTANCE = new UpgradeManager();

        private UpgradeManagerHolder() {
        }
    }

    private UpgradeManager() {
        this.TEST_APPKEY = "yCVSUcJea4a08fd2";
        this.TEST_SECRETKEY = "ddbd78783355b4ad";
        this.TEST_UNIQUE_KEY = "900692";
        this.ROM_APPKEY = "HVHjDeG3c469dc8b";
        this.ROM_SECRETKEY = "d5e992309413cd40";
        this.mIsPausedByClick = false;
        this.mIsManualCheckUpgrade = false;
        this.mIsWaitNetConnected = false;
        this.mIsWifiOnly = true;
        this.mProgress = 0;
        this.mWaitShowState = -1;
        this.mUpgradeState = 0;
        this.mLastUpgradeState = 0;
        this.mLastUpdateNotifTime = 0L;
        this.mHandler = new Handler(Looper.getMainLooper()) { // from class: cn.nubia.gamelauncher.upgrade.UpgradeManager.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                int i = message.what;
                if (i == 0) {
                    UpgradeManager.this.manualCheckTimeout();
                } else {
                    if (i != 1) {
                        return;
                    }
                    UpgradeManager.this.exitIfProcessIdle();
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void autoCheck() {
        Log.d("Upgrade", "autoCheck()");
        if (isIntervalLessThanOneDay()) {
            return;
        }
        checkUpgrade();
    }

    private void checkUpgrade() {
        Log.d("Upgrade", "checkUpgrade()");
        setUpgradeState(1);
        if (isNetworkConnected()) {
            checkVersion();
            return;
        }
        boolean z = this.mIsManualCheckUpgrade;
        this.mIsWaitNetConnected = !z;
        if (z) {
            manualCheckError(true);
        }
    }

    private void checkVersion() {
        Log.d("Upgrade", "checkVersion()");
        this.mNubiaUpgradeManager.getVersion(getAppContext(), this);
    }

    private void cleanLastDownloadExists() {
        Log.d("Upgrade", "cleanLastDownloadExists()");
        UpgradeUtil.deleteDir(getLastDownloadPath());
    }

    private void clickContinue() {
        Log.d("Upgrade", "clickContinue()");
        if (!isNetworkConnected()) {
            showToast(R.string.upgrade_no_network);
        } else if (isWifiConnected() || !this.mIsWifiOnly) {
            startDownload();
        } else {
            collapseStatusBar();
            showUpgradeDialog(1);
        }
    }

    private void collapseStatusBar() {
        Log.d("Upgrade", "collapseStatusBar()");
        try {
            Object systemService = getAppContext().getSystemService("statusbar");
            systemService.getClass().getMethod("collapsePanels", new Class[0]).invoke(systemService, new Object[0]);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
        }
    }

    private void deleteDownloadApk(String str) {
        if (str == null) {
            str = getAppContext().getExternalFilesDir(null).getPath() + "/Upgrade";
        }
        Log.d("Upgrade", "deleteDownloadApk() path : " + str);
        UpgradeUtil.deleteDir(str);
    }

    private void dismissDialog(boolean z) {
        if (this.mCallback != null) {
            Log.d("Upgrade", "manager -> dismissDialog() isLatest = " + z);
            this.mCallback.dismissDialog(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void exitIfProcessIdle() {
        if (Util.isProcessIdle()) {
            LogUtil.d(this, "exitIfProcessIdle() idle to exit !");
            if (!Util.isGameScene()) {
                TgkHelper.disableTgkMap(getAppContext(), null);
            }
            System.exit(0);
        }
    }

    private Context getAppContext() {
        if (this.mContext == null) {
            init(GameLauncherApplication.getAppContext());
        }
        return this.mContext;
    }

    private String getDefaultPath(Context context) {
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        StrictMode.allowThreadDiskReads();
        String str = (!UpgradeUtil.checkSDCard() || context.getExternalFilesDir("") == null) ? context.getCacheDir().getPath() + File.separator + "Upgrade" + File.separator : (String) Objects.requireNonNull(context.getExternalFilesDir("").getPath() + File.separator + "Upgrade" + File.separator);
        if (TextUtils.isEmpty(str)) {
            LogUtil.d("Upgrade", "getDegaultPath() get default path Error");
        }
        StrictMode.setThreadPolicy(threadPolicy);
        return str;
    }

    public static UpgradeManager getInstance() {
        return UpgradeManagerHolder.INSTANCE;
    }

    private String getLastDownloadPath() {
        String apkPath = getSp().getApkPath();
        Log.d("Upgrade", "getLastDownloadPath() path = " + apkPath);
        return apkPath;
    }

    private VersionData getLastVersionData() {
        return (VersionData) UpgradeUtil.unmarshall(getSp().getVersionData(), VersionData.CREATOR);
    }

    private SharedPreferencesUtil getSp() {
        return SharedPreferencesUtil.getInstance(getAppContext());
    }

    private void init(Context context) {
        if (this.mContext != null || context == null) {
            return;
        }
        Log.d("Upgrade", "init()");
        this.mContext = context.getApplicationContext();
        registerNetworkCallback();
        initWorkHandler();
        initNubiaUpgradeManager();
    }

    private void initDownLoadListener() {
        this.mNubiaUpgradeManager.addDownLoadListener(this);
    }

    private void initNotification() {
        Log.d("Upgrade", "initNotification()");
        UpgradeNotification upgradeNotification = new UpgradeNotification(getAppContext());
        this.mNotification = upgradeNotification;
        upgradeNotification.setOnClickListener(this);
    }

    private void initNubiaUpgradeManager() {
        Log.d("Upgrade", "initNubiaUpgradeManager()");
        if (isTestServer()) {
            NubiaUpgradeManager nubiaUpgradeManager = NubiaUpgradeManager.getInstance(getAppContext(), "yCVSUcJea4a08fd2", "ddbd78783355b4ad");
            this.mNubiaUpgradeManager = nubiaUpgradeManager;
            nubiaUpgradeManager.debug(true);
        } else {
            NubiaUpgradeManager nubiaUpgradeManager2 = NubiaUpgradeManager.getInstance(getAppContext(), "HVHjDeG3c469dc8b", "d5e992309413cd40");
            this.mNubiaUpgradeManager = nubiaUpgradeManager2;
            nubiaUpgradeManager2.debug(false);
        }
        setConfigurationToUpgradeManager(false);
        initDownLoadListener();
    }

    private void initWorkHandler() {
        Log.d("Upgrade", "initWorkHandler()");
        HandlerThread handlerThread = new HandlerThread("upgrade_work");
        this.mWorkThread = handlerThread;
        handlerThread.start();
        this.mWorkHandler = new Handler(this.mWorkThread.getLooper());
    }

    private boolean install(String str) {
        Log.d("Upgrade", "install() path : " + str);
        Context appContext = getAppContext();
        File file = new File(str);
        if (!file.exists() || file.isDirectory()) {
            return false;
        }
        String str2 = appContext.getPackageName() + ".FileProvider";
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(1);
        Uri uriForFile = FileProvider.getUriForFile(appContext, str2, file);
        intent.setDataAndType(uriForFile, "application/vnd.android.package-archive");
        Iterator<ResolveInfo> it = appContext.getPackageManager().queryIntentActivities(intent, 65536).iterator();
        while (it.hasNext()) {
            appContext.grantUriPermission(it.next().activityInfo.packageName, uriForFile, 3);
        }
        intent.addFlags(268435456);
        appContext.startActivity(intent);
        return true;
    }

    private boolean isForeground() {
        String currentTopPkgQ = UpgradeUtil.getCurrentTopPkgQ();
        return currentTopPkgQ != null && currentTopPkgQ.equals(getAppContext().getPackageName());
    }

    private boolean isIgnoreVersion(String str) {
        String ignoreVersionCode = getSp().getIgnoreVersionCode();
        Log.d("Upgrade", "isIgnoreVersion() newCode : " + str + ", lastCode : " + ignoreVersionCode);
        if (str != null) {
            return str.equals(ignoreVersionCode);
        }
        return false;
    }

    private boolean isInstallSuccess() {
        String installVersionCode = getSp().getInstallVersionCode();
        String stringCurrentVersionCode = UpgradeUtil.getStringCurrentVersionCode(getAppContext());
        Log.d("Upgrade", "isInstallSuccess() installVersionCode : " + installVersionCode + ", currentVersionCode : " + stringCurrentVersionCode);
        return stringCurrentVersionCode != null && stringCurrentVersionCode.equals(installVersionCode);
    }

    private boolean isIntervalLessThanOneDay() {
        int day = UpgradeUtil.getDay();
        boolean z = day == getSp().getLastCheckUpgradeDay();
        Log.d("Upgrade", "isIntervalLessThanOneDay() day = " + day + ", isIntervalLessThanOneDay = " + z);
        return z;
    }

    private boolean isLastDownloadExists() {
        return UpgradeUtil.isFileExists(getLastDownloadPath());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isNetworkConnected() {
        Log.d("Upgrade", "isNetworkConnected() : " + NetworkHelper.isNetworkConnected(getAppContext()));
        return NetworkHelper.isNetworkConnected(getAppContext());
    }

    private boolean isTestServer() {
        try {
            String obj = getAppContext().getPackageManager().getApplicationInfo(getAppContext().getPackageName(), 128).metaData.get("unique_key").toString();
            Log.d("Upgrade", "isTestServer() uniqueKey = " + obj);
            if (obj != null) {
                return obj.equals("900692");
            }
            return false;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean isWifiConnected() {
        Log.d("Upgrade", "isWifiConnected() : " + NetworkHelper.isWifiConnected(getAppContext()));
        return NetworkHelper.isWifiConnected(getAppContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void manualCheck() {
        Log.d("Upgrade", "manualCheck(s)");
        if (isInstalling()) {
            dismissDialog(false);
            installApp();
        } else if (isDownloadStarted()) {
            dismissDialog(false);
            showToast(R.string.upgrade_notification_downloading);
        } else {
            cleanIgnoreVersion();
            checkUpgrade();
            this.mHandler.sendEmptyMessageDelayed(0, 5000L);
            Log.d("Upgrade", "manualCheck(e)");
        }
    }

    private void manualCheckError(boolean z) {
        Log.d("Upgrade", "manualCheckError() isChecking : " + isChecking() + ", isManualCheck : " + this.mIsManualCheckUpgrade + ", isNetError : " + z);
        if (isChecking() && this.mIsManualCheckUpgrade) {
            if (z) {
                showToast(R.string.upgrade_no_network);
            } else {
                showToast(R.string.upgrade_check_error);
            }
            dismissDialog(false);
            setUpgradeState(0);
            this.mHandler.removeMessages(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void manualCheckTimeout() {
        Log.d("Upgrade", "manualCheckTimeout() isChecking : " + isChecking() + ", isManualCheck : " + this.mIsManualCheckUpgrade);
        if (isChecking() && this.mIsManualCheckUpgrade) {
            manualCheckError(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pauseDownload(boolean z) {
        if (isDownloadStarted()) {
            Log.d("Upgrade", "pauseDownload() isClick : " + z);
            this.mIsPausedByClick = z;
            this.mIsWaitNetConnected = !z;
            if (isDownloadStarted()) {
                setUpgradeState(3);
            }
            updateNotification();
            this.mWorkHandler.postDelayed(new Runnable() { // from class: cn.nubia.gamelauncher.upgrade.UpgradeManager$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    UpgradeManager.this.updateNotification();
                }
            }, 500L);
            this.mNubiaUpgradeManager.pauseDownload();
        }
    }

    private void readLastPausedType() {
        this.mIsPausedByClick = getSp().getPausedType();
    }

    private int readLastRecord() {
        int upgradeState = getSp().getUpgradeState();
        this.mIsWifiOnly = getSp().getIsWifiOnly();
        Log.d("Upgrade", "readLastRecord() lastUpgradeState : " + stateToString(upgradeState));
        return upgradeState;
    }

    private void readLastVersionData() {
        this.mVersionData = getLastVersionData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void realStart() {
        Log.d("Upgrade", "realStart()");
        int readLastRecord = readLastRecord();
        if (resumeUnfinishedIfNeed(readLastRecord)) {
            return;
        }
        resetRecordState();
        Log.d("Upgrade", "realStart() lastState : " + readLastRecord + ", mUpgradeState : " + this.mUpgradeState);
        startCheck(false);
    }

    private void registerNetworkCallback() {
        Log.d("Upgrade", "registerNetworkCallback()");
        this.mNetworkCallback = new NetworkCallbackImpl();
        NetworkRequest build = new NetworkRequest.Builder().build();
        ConnectivityManager connectivityManager = (ConnectivityManager) getAppContext().getSystemService("connectivity");
        this.mCmgr = connectivityManager;
        if (connectivityManager != null) {
            try {
                connectivityManager.registerNetworkCallback(build, this.mNetworkCallback);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void resetRecordState() {
        Log.d("Upgrade", "resetRecordState()");
        setUpgradeState(0);
    }

    private boolean resumeInstalling() {
        if (!isLastDownloadExists()) {
            return false;
        }
        readLastVersionData();
        Log.d("Upgrade", "resumeInstalling() mVersionData : " + this.mVersionData);
        if (isInstallSuccess()) {
            setNewVersionFlag(false);
            cleanLastDownloadExists();
            return false;
        }
        this.mApkPath = getLastDownloadPath();
        installApp();
        return true;
    }

    private boolean resumeLastDownload() {
        if (!isLastDownloadExists()) {
            return false;
        }
        readLastVersionData();
        Log.d("Upgrade", "resumeLastDownload() mVersionData : " + this.mVersionData);
        if (this.mVersionData == null) {
            cleanLastDownloadExists();
            return false;
        }
        resumeDownloadIfNeed();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean resumeUnfinishedIfNeed(int i) {
        Log.d("Upgrade", "------->resumeUnfinishedIfNeed() lastState = " + stateToString(i) + ", cur state : " + stateToString(this.mUpgradeState));
        if (i == 1) {
            startCheck(false);
            return true;
        }
        if (i != 2) {
            if (i != 3) {
                if (i != 4) {
                    return false;
                }
                setUpgradeState(i);
                return resumeInstalling();
            }
            readLastPausedType();
            if (isPausedByClick()) {
                this.mProgress = getSp().getProgress();
                setUpgradeState(i);
                return true;
            }
        }
        setUpgradeState(i);
        return resumeLastDownload();
    }

    private void saveVersionData(VersionData versionData) {
        getSp().setVersionData(UpgradeUtil.parcelableToString(versionData));
    }

    private void setConfigurationToUpgradeManager(boolean z) {
        String str = this.mApkPath;
        if (str == null) {
            str = getDefaultPath(getAppContext());
        }
        NubiaUpdateConfiguration build = new NubiaUpdateConfiguration.Builder().setAllowMobileNetwork(!z).setDownloadPath(str).setSilentDownload(false).setSilentInstall(false).setDownloadRunMode(new RunMode.ForegroundRunMode(R.mipmap.ic_launcher, getAppContext().getString(R.string.downloading))).setInstallRunMode(new RunMode.ForegroundRunMode(R.mipmap.ic_launcher, getAppContext().getString(R.string.installing))).build();
        this.mUpgradeConfiguration = build;
        this.mNubiaUpgradeManager.setConfiguration(build);
        this.mApkPath = this.mUpgradeConfiguration.getDownloadPath();
        Log.d("Upgrade", "setConfigurationToUpgradeManager() path : " + this.mApkPath);
    }

    private void setUpgradeState(int i) {
        if (i == this.mUpgradeState) {
            return;
        }
        Log.d("Upgrade", "--------->setUpgradeState() " + stateToString(i));
        this.mUpgradeState = i;
        getSp().setUpgradeState(i);
        if (isPaused()) {
            getSp().setPausedType(this.mIsPausedByClick);
        }
        if (isDownloadStarted()) {
            updateNotification();
        }
    }

    private void startDownload() {
        if (this.mVersionData == null) {
            readLastVersionData();
        }
        if (this.mVersionData != null) {
            Log.d("Upgrade", "startDownload()");
            setUpgradeState(2);
            this.mNubiaUpgradeManager.startDownload(getAppContext(), this.mVersionData);
            updateNotification();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String stateToString(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? "" + i : "installing" : "paused" : "downloading" : "checking" : "idle";
    }

    private void unRegisterNetworkCallback() {
        NetworkCallbackImpl networkCallbackImpl;
        ConnectivityManager connectivityManager = this.mCmgr;
        if (connectivityManager == null || (networkCallbackImpl = this.mNetworkCallback) == null) {
            return;
        }
        connectivityManager.unregisterNetworkCallback(networkCallbackImpl);
    }

    private void updateDialog(int i) {
        Callback callback = this.mCallback;
        if (callback != null) {
            callback.updateDialog(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateNotification() {
        if (!isDownloadStarted()) {
            this.mNotification.cancelNotification(1);
            return;
        }
        if (!isInstalling() || this.mProgress > 0) {
            long currentTimeMillis = System.currentTimeMillis();
            long j = currentTimeMillis - this.mLastUpdateNotifTime;
            Log.d("Upgrade", "updateNotification(err) last : " + this.mLastUpdateNotifTime + ", curr : " + currentTimeMillis + ", interval : " + j);
            int i = this.mLastUpgradeState;
            int i2 = this.mUpgradeState;
            if (i == i2 && j < 300) {
                Log.d("Upgrade", "updateNotification() interval : " + j);
                return;
            }
            this.mLastUpdateNotifTime = currentTimeMillis;
            this.mLastUpgradeState = i2;
            Log.d("Upgrade", "updateNotification()");
            if (this.mNotification == null) {
                initNotification();
            }
            this.mNotification.updateNotification();
        }
    }

    public void addExternDeviceCallback(Runnable runnable, Runnable runnable2) {
        this.mShowRedPointRunnable = runnable;
        this.mHideRedPointRunnable = runnable2;
    }

    public void addWakeExternDeviceRunnable(Runnable runnable) {
        this.mWakeExternDeviceRunnable = runnable;
    }

    public void cancelExit() {
        if (this.mHandler == null) {
            return;
        }
        LogUtil.d(this, "cancelExit() has exit Messages : " + this.mHandler.hasMessages(1));
        this.mHandler.removeMessages(1);
    }

    public void cancelManualCheck() {
        Log.d("Upgrade", "cancelManualCheck() isChecking : " + isChecking() + ", isManualCheck : " + this.mIsManualCheckUpgrade);
        if (isChecking() && this.mIsManualCheckUpgrade) {
            setUpgradeState(0);
            this.mHandler.removeMessages(0);
        }
    }

    public void checkUpgradeDone() {
        Log.d("Upgrade", "checkUpgradeDone()");
        setUpgradeState(0);
        getSp().setLastCheckUpgradeDay(UpgradeUtil.getDay());
    }

    public void cleanIgnoreVersion() {
        Log.d("Upgrade", "cleanIgnoreVersion()");
        getSp().setIgnoreVersionCode(null);
    }

    public void continueDownloadEvenOnMobile() {
        Log.d("Upgrade", "continueDownloadEvenOnMobile()");
        this.mIsWifiOnly = false;
        getSp().setIsWifiOnly(false);
        setConfigurationToUpgradeManager(false);
        startDownload();
    }

    public void doUpgradeNow() {
        Log.d("Upgrade", "doUpgrade()");
        if (!isWifiConnected()) {
            showUpgradeDialog(1);
        } else {
            dismissDialog(false);
            startDownload();
        }
    }

    public void end() {
        Log.d("Upgrade", "EDMU - end()");
        unRegisterNetworkCallback();
        UpgradeNotification upgradeNotification = this.mNotification;
        if (upgradeNotification != null) {
            upgradeNotification.unregisterNotificationReceiver();
        }
    }

    public void exitIfIdle() {
        this.mWakeExternDeviceRunnable = null;
        this.mHideRedPointRunnable = null;
        this.mShowRedPointRunnable = null;
    }

    public String getButtonText() {
        return isPaused() ? getAppContext().getString(R.string.upgrade_notification_continue) : getAppContext().getString(R.string.upgrade_notification_pause);
    }

    public String getContent() {
        if (isInstalling()) {
            return getAppContext().getString(R.string.upgrade_download_complete);
        }
        VersionData versionData = this.mVersionData;
        if (versionData == null) {
            return "";
        }
        long fileSize = versionData.getFileSize() / 1048576;
        return ((this.mProgress * fileSize) / 100) + "M/" + fileSize + "M";
    }

    public String getContentTitle() {
        return isInstalling() ? getAppContext().getString(R.string.game_space_app_name) : isPaused() ? getAppContext().getString(R.string.upgrade_notification_download_paused) : getAppContext().getString(R.string.upgrade_notification_downloading);
    }

    public int getProgress() {
        return this.mProgress;
    }

    public String getStringState() {
        Log.d("Upgrade", "getStringState() " + stateToString(this.mUpgradeState));
        return stateToString(this.mUpgradeState);
    }

    public String getUpgradeContent() {
        if (this.mVersionData == null) {
            return null;
        }
        Log.d("Upgrade", "getUpgradeContent() content = " + this.mVersionData.getUpgradeContent());
        return this.mVersionData.getUpgradeContent();
    }

    public boolean hasEnoughStorageSpace() {
        VersionData versionData = this.mVersionData;
        if (versionData == null) {
            return true;
        }
        long fileSize = versionData.getFileSize();
        Log.d("Upgrade", "hasEnoughStorageSpace() fileSize = " + fileSize);
        return UpgradeUtil.hasEnoughStorageSpace(getAppContext(), fileSize);
    }

    public boolean hasNewVersion() {
        boolean newVersionFlag = getSp().getNewVersionFlag();
        Log.d("Upgrade", "hasNewVersion() flag = " + newVersionFlag);
        return newVersionFlag;
    }

    public void hideRedPoint() {
        Runnable runnable = this.mHideRedPointRunnable;
        if (runnable == null) {
            return;
        }
        runnable.run();
    }

    public void ignoreThisVersion() {
        if (this.mVersionData != null) {
            Log.d("Upgrade", "ignoreThisVersion() code = " + this.mVersionData.getToVersionCode());
            getSp().setIgnoreVersionCode(this.mVersionData.getToVersionCode());
            setNewVersionFlag(false);
        }
    }

    public void installApp() {
        Log.d("Upgrade", "installApp() code : " + this.mVersionData.getToVersionCode() + ", url : " + this.mVersionData.getApkUrl());
        getSp().setInstallVersionCode(this.mVersionData.getToVersionCode());
        collapseStatusBar();
        install(this.mApkPath);
        Log.d("Upgrade", "installApp() state : " + stateToString(getSp().getUpgradeState()));
    }

    public boolean isChecking() {
        return this.mUpgradeState == 1;
    }

    public boolean isDownloadStarted() {
        return isDownloading() || isPaused() || isInstalling();
    }

    public boolean isDownloading() {
        return this.mUpgradeState == 2;
    }

    public boolean isIdle() {
        return this.mUpgradeState == 0;
    }

    public boolean isInstalling() {
        return this.mUpgradeState == 4;
    }

    public boolean isPaused() {
        return this.mUpgradeState == 3;
    }

    public boolean isPausedByClick() {
        return this.mIsPausedByClick;
    }

    @Override // cn.nubia.gamelauncher.upgrade.UpgradeNotification.OnClickListener
    public void onCancelClick() {
        Log.d("Upgrade", "onCancelClick()");
        collapseStatusBar();
        pauseDownload(true);
        setUpgradeState(0);
        updateNotification();
        deleteDownloadApk(this.mApkPath);
    }

    @Override // cn.nubia.upgrade.http.IDownLoadListener
    public void onDownloadComplete(String str) {
        Log.d("Upgrade", "onDownloadComplete() downLoadPath = " + str);
        this.mApkPath = str;
        getSp().setApkPath(str);
        setUpgradeState(4);
        updateNotification();
        installApp();
    }

    @Override // cn.nubia.upgrade.http.IDownLoadListener
    public void onDownloadError(int i) {
        Log.d("Upgrade", "onDownloadError() errorCode = " + i + " -> mApkPath = " + this.mApkPath);
        if (i == 1000) {
            pauseDownload(false);
        } else if (i != 1007) {
            pauseDownload(true);
        } else {
            pauseDownload(true);
        }
    }

    @Override // cn.nubia.upgrade.http.IDownLoadListener
    public void onDownloadPause() {
        Log.d("Upgrade", "onDownloadPause()");
        if (!this.mIsPausedByClick) {
            this.mIsWaitNetConnected = true;
        }
        if (isDownloadStarted()) {
            setUpgradeState(3);
        }
        updateNotification();
    }

    @Override // cn.nubia.upgrade.http.IDownLoadListener
    public void onDownloadProgress(int i) {
        if (isDownloadStarted()) {
            Log.d("Upgrade", "onDownloadProgress() i = " + i);
            setUpgradeState(2);
            this.mProgress = i;
            updateNotification();
        }
    }

    @Override // cn.nubia.upgrade.http.IGetVersionListener
    public void onError(int i) {
        Log.d("Upgrade", "onError() errCode = " + i);
        boolean z = i == 1000;
        if (this.mIsManualCheckUpgrade) {
            manualCheckError(z);
            return;
        }
        dismissDialog(false);
        if (z) {
            this.mIsWaitNetConnected = true;
        } else {
            checkUpgradeDone();
        }
    }

    @Override // cn.nubia.upgrade.http.IGetVersionListener
    public void onGetNewVersion(VersionData versionData) {
        this.mVersionData = versionData;
        Log.d("Upgrade", "onGetNewVersion() mVersionData = " + this.mVersionData);
        Log.d("Upgrade", "onGetNewVersion() apk exist = " + this.mNubiaUpgradeManager.isApkExist(this.mVersionData));
        if (versionData == null || isIgnoreVersion(versionData.getToVersionCode()) || isDownloadStarted()) {
            dismissDialog(true);
            checkUpgradeDone();
            return;
        }
        setNewVersionFlag(true);
        if (!Util.isRealGameKeyClose() || !isWifiConnected()) {
            showUpgradeDialog(2);
        } else {
            checkUpgradeDone();
            startDownload();
        }
    }

    @Override // cn.nubia.upgrade.http.IGetVersionListener
    public void onGetNoVersion() {
        Log.d("Upgrade", "onGetNoVersion()");
        dismissDialog(true);
        setNewVersionFlag(false);
        checkUpgradeDone();
    }

    @Override // cn.nubia.gamelauncher.upgrade.UpgradeNotification.OnClickListener
    public void onInstallClick() {
        Log.d("Upgrade", "onInstallClick()");
        collapseStatusBar();
        installApp();
    }

    @Override // cn.nubia.gamelauncher.upgrade.UpgradeNotification.OnClickListener
    public void onPauseClick() {
        Log.d("Upgrade", "onPauseClick()");
        collapseStatusBar();
        this.mIsPausedByClick = true;
        pauseDownload(true);
        getSp().setProgress(this.mProgress);
    }

    @Override // cn.nubia.gamelauncher.upgrade.UpgradeNotification.OnClickListener
    public void onResumeClick() {
        Log.d("Upgrade", "onResumeClick()");
        collapseStatusBar();
        clickContinue();
    }

    @Override // cn.nubia.upgrade.http.IDownLoadListener
    public void onResumeDownload() {
        Log.d("Upgrade", "onResumeDownload()");
        setUpgradeState(2);
    }

    @Override // cn.nubia.upgrade.http.IDownLoadListener
    public void onStartDownload() {
        Log.d("Upgrade", "onStartDownload() mApkPath : " + this.mApkPath);
        setUpgradeState(2);
        getSp().setApkPath(this.mApkPath);
        saveVersionData(this.mVersionData);
    }

    public void registerCallback(Callback callback) {
        this.mCallback = callback;
    }

    public void resumeDownloadIfNeed() {
        Log.d("Upgrade", "resumeDownloadIfNeed()");
        if (this.mIsPausedByClick || !isDownloadStarted()) {
            return;
        }
        this.mIsWaitNetConnected = true;
        if (!isNetworkConnected()) {
            Log.d("Upgrade", "resumeDownloadIfNeed() - isNetworkConnected() false , return");
        } else if (isWifiConnected() || !this.mIsWifiOnly) {
            startDownload();
        } else {
            Log.d("Upgrade", "resumeDownloadIfNeed() - isWifiConnected() false and isWifiOnly true, return");
        }
    }

    public void runOnWorkThread(Runnable runnable) {
        if (this.mWorkThread == null) {
            initWorkHandler();
        }
        if (Process.myTid() == this.mWorkThread.getThreadId()) {
            runnable.run();
        } else {
            this.mWorkHandler.post(runnable);
        }
    }

    public void setNewVersionFlag(boolean z) {
        Log.d("Upgrade", "setNewVersionFlag() flag = " + z);
        getSp().setNewVersionFlag(z);
    }

    public void showRedPoint() {
        Runnable runnable = this.mShowRedPointRunnable;
        if (runnable == null) {
            return;
        }
        runnable.run();
    }

    public void showToast(int i) {
        Log.d("Upgrade", "Upgrade - showToast() : " + getAppContext().getString(i));
        Toast.makeText(getAppContext(), i, 0).show();
    }

    public void showUpgradeDialog(int i) {
        Log.d("Upgrade", "showUpgradeDialog(" + i + ") mCallback = " + this.mCallback);
        if (!isForeground() && i != 1) {
            this.mWaitShowState = i;
            return;
        }
        if (i == 2) {
            checkUpgradeDone();
        }
        this.mWaitShowState = -1;
        if (this.mCallback != null) {
            updateDialog(i);
            return;
        }
        Intent intent = new Intent();
        intent.setAction("cn.nubia.gamelauncher.upgrade");
        intent.setFlags(268435456);
        intent.putExtra("state", i);
        Log.d("Upgrade", "showUpgradeDialog(" + i + ") -> DOWNLOADING");
        getAppContext().startActivity(intent);
    }

    public void showWaitUpgradeDialog() {
        int i = this.mWaitShowState;
        if (i >= 0) {
            showUpgradeDialog(i);
        }
    }

    public void start(Context context) {
        if (this.mContext != null) {
            return;
        }
        Log.d("Upgrade", "start()");
        init(context);
        runOnWorkThread(new Runnable() { // from class: cn.nubia.gamelauncher.upgrade.UpgradeManager$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                UpgradeManager.this.realStart();
            }
        });
    }

    public void startCheck(boolean z) {
        if (!isChecking() || z) {
            this.mIsManualCheckUpgrade = z;
            Log.d("Upgrade", "startCheck() isManual : " + z + ", state : " + getStringState());
            if (z) {
                runOnWorkThread(new Runnable() { // from class: cn.nubia.gamelauncher.upgrade.UpgradeManager$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        UpgradeManager.this.manualCheck();
                    }
                });
            } else {
                runOnWorkThread(new Runnable() { // from class: cn.nubia.gamelauncher.upgrade.UpgradeManager$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        UpgradeManager.this.autoCheck();
                    }
                });
            }
        }
    }

    public void unRegisterCallback() {
        this.mCallback = null;
    }

    public void updateExternDeviceRedPoint(boolean z) {
        if (z) {
            showRedPoint();
        } else {
            hideRedPoint();
        }
    }

    public void wakeExternDevice() {
        Runnable runnable = this.mWakeExternDeviceRunnable;
        if (runnable == null) {
            return;
        }
        runnable.run();
    }
}
