package cn.nubia.gamelauncher.gamecontrolpanel.performancetuning;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.HandlerThread;
import android.provider.Settings;
import android.text.TextUtils;
import cn.nubia.gamelauncher.aimhelper.ActivityUtils;
import cn.nubia.gamelauncher.aimhelper.LogUtil;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.ControlPanelFeatureHelper;
import cn.nubia.gamelauncher.service.GameFeatureService;

/* loaded from: classes.dex */
public class PerfModeObserver {
    private static final int CLOSE_GAME_ASSIST_VALUE = 4326;
    private static final String KEY_CLOSE_GAME_ASSIST = "controlcenter_ban";
    private static final String KEY_PERF_MODE = "NubiaperformanceMode";
    private static final String TAG = "PerfModeObserver";
    private static PerfModeObserver sPerfModeObserver;
    private Context mAppContext;
    private ContentResolver mContentResolver;
    private SettingObserver mSettingObserver;
    private volatile boolean mShowCustomPerfWindow;
    private HandlerThread mThread;

    private class SettingObserver extends ContentObserver {
        public SettingObserver(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            PerfModeObserver.this.perModeChange();
        }
    }

    private PerfModeObserver() {
        HandlerThread handlerThread = new HandlerThread("perfModeChange");
        this.mThread = handlerThread;
        handlerThread.start();
    }

    public static PerfModeObserver getInstance() {
        if (sPerfModeObserver == null) {
            sPerfModeObserver = new PerfModeObserver();
        }
        return sPerfModeObserver;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void perModeChange() {
        if (!ControlPanelFeatureHelper.getZteFeatureZperfCubeGpsettingEnabled().booleanValue()) {
            LogUtil.i(TAG, "not support ZperfCube");
            return;
        }
        if (this.mShowCustomPerfWindow) {
            LogUtil.i(TAG, "mShowCustomPerfWindow");
            return;
        }
        String string = Settings.Global.getString(this.mContentResolver, KEY_PERF_MODE);
        if (TextUtils.isEmpty(string)) {
            return;
        }
        String currentTopPkg = ActivityUtils.getCurrentTopPkg(this.mAppContext);
        int applyProfile = CustomPerfProfileManager.getInstance().getApplyProfile(currentTopPkg);
        LogUtil.i(TAG, "topPackageName = " + currentTopPkg + " serial = " + applyProfile);
        if (string.contains(currentTopPkg) && applyProfile == -1) {
            int i = 0;
            for (String str : string.split(",")) {
                if (!TextUtils.isEmpty(str) && str.contains(currentTopPkg)) {
                    try {
                        i = Integer.parseInt(String.valueOf(str.charAt(str.indexOf("+") + 1)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    LogUtil.i(TAG, "perMode change = " + i);
                    if (i == 4) {
                        startCustomPerfDialog(currentTopPkg);
                        return;
                    }
                    return;
                }
            }
        }
    }

    private void startCustomPerfDialog(String str) {
        LogUtil.i(TAG, "startCustomPerfDialog");
        Intent intent = new Intent();
        intent.setClass(this.mAppContext, GameFeatureService.class);
        intent.setAction(GameFeatureService.ACTION_CONTROL_PANEL);
        intent.putExtra("packageName", str);
        intent.putExtra("type", GameFeatureService.ACTION_TYPE_PERF_MODE);
        this.mAppContext.startService(intent);
        int i = Settings.Global.getInt(this.mContentResolver, KEY_CLOSE_GAME_ASSIST, 0);
        Settings.Global.putInt(this.mContentResolver, KEY_CLOSE_GAME_ASSIST, CLOSE_GAME_ASSIST_VALUE);
        Settings.Global.putInt(this.mContentResolver, KEY_CLOSE_GAME_ASSIST, i);
    }

    public void register(Context context) {
        if (this.mSettingObserver != null) {
            return;
        }
        LogUtil.i(TAG, "register");
        Context applicationContext = context.getApplicationContext();
        this.mAppContext = applicationContext;
        this.mContentResolver = applicationContext.getContentResolver();
        this.mSettingObserver = new SettingObserver(new Handler(this.mThread.getLooper()));
        this.mContentResolver.registerContentObserver(Settings.Global.getUriFor(KEY_PERF_MODE), true, this.mSettingObserver);
    }

    public void setShowCustomPerfWindow(boolean z) {
        this.mShowCustomPerfWindow = z;
    }

    public void unregister() {
        if (this.mSettingObserver != null) {
            LogUtil.i(TAG, "unregister");
            this.mContentResolver.unregisterContentObserver(this.mSettingObserver);
            this.mSettingObserver = null;
        }
    }
}
