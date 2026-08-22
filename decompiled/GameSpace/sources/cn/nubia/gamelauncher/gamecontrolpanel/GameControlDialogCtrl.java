package cn.nubia.gamelauncher.gamecontrolpanel;

import android.content.BroadcastReceiver;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ShortcutInfo;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import cn.nubia.common.GameKeyObserver;
import cn.nubia.common.util.CommonUtil;
import cn.nubia.gamelauncher.gamecontrolpanel.GameControlDialogCtrl;
import cn.nubia.gamelauncher.gamecontrolpanel.TouchOperationBean;
import cn.nubia.gamelauncher.gamecontrolpanel.performancetuning.PerfModeObserver;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.ControlPanelFeatureHelper;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.LogUtil;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.Utils;
import cn.nubia.gamelauncher.helper.ShortCutHelper;
import cn.nubia.gamelauncher.service.GameFeatureService;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public class GameControlDialogCtrl implements GameKeyObserver.Callback {
    private static final String ACTION_CLOSE_CONTROLPANEL = "cn.nubia.gamelauncher.action.close_controlpanel";
    private static String ACTION_CLOSE_SYSTEM_DIALOGS = "android.intent.action.CLOSE_SYSTEM_DIALOGS";
    private static final String ACTION_GAME_COLOR_INVERT = "cn.nubia.intent.action.game_color_invert";
    private static final String ACTION_PERFORMANCE = "click_performance_notification";
    private static String AUDIO_FOR_GAMES_MODE = "cn.nubia.action.AUDIO_FOR_GAMES_MODE";
    private static String AUDIO_GAME_MODE_PACKAGE = "com.dts.dtsxultra";
    private static String AUDIO_GAME_MODE_RC = "com.dts.dtsxultra.activities.SRSConfigReceiver";
    private static final String DB_GAME_COLOR_INVERT = "db_game_color_invert";
    private static final String DB_GAME_PERFORMANCE_MODE_ALL_LIST = "NubiaperformanceMode";
    private static final String DB_GAME_STRENGTHEN_MODE_ALL_LIST = "db_game_strengthen_mode_list";
    private static final String DB_GAME_STRENGTHEN_MODE_PACKAGENAME = "db_game_strengthen_packagename";
    private static final String DB_NIGHT_DISPLAY_VALUE = "nubia_night_display_color_value";
    private static final int GAME_NOTIFICATION = 1024;
    private static final int MODE_GAME_SCREEN_SHOW_CAR = 1;
    private static final int MODE_GAME_SCREEN_SHOW_COLOR_INVERT = 5;
    private static final int MODE_GAME_SCREEN_SHOW_DEFAULT = 0;
    private static final int MODE_GAME_SCREEN_SHOW_GCP = 4;
    private static final int MODE_GAME_SCREEN_SHOW_MOBA = 3;
    private static final int MODE_GAME_SCREEN_SHOW_SHOOT = 2;
    private static final String NUBIA_GAME_SCENE = "nubia_game_scene";
    private static final String NUBIA_SMALL_WINDOWN_DB = "nubia_small_window_open";
    private static final String PERFORMANCE_MODE_VALUE = "performance_mode_value";
    private static final int RENDER_INTENT_ARGB = 256;
    private static final int RENDER_INTENT_COLOR_INVERT = 263;
    private static final int RENDER_INTENT_DCI_P3 = 257;
    private static final int RENDER_INTENT_GCP = 262;
    private static final int RENDER_INTENT_MOBA = 261;
    private static final int RENDER_INTENT_P3 = 264;
    private static final int RENDER_INTENT_RACING = 259;
    private static int RENDER_INTENT_SHARPEN_DEFAULT = 266;
    private static int RENDER_INTENT_SHARPEN_DEFAULT_NX659J = 264;
    private static int RENDER_INTENT_SHARPEN_SHOOT = 267;
    private static int RENDER_INTENT_SHARPEN_SHOOT_NX659J = 265;
    private static final int RENDER_INTENT_SHOOT = 260;
    private static final int RENDER_INTENT_SRGB = 258;
    private static final int RENDER_INTENT_SRGB_NEW = 265;
    private static final String TAG = "GameControlDialogCtrl";
    private boolean isSprdPlatform;
    private Context mContext;
    private String mCurrentActivity;
    private String mCurrentPackageName;
    private GameControlDialog mGameControlDialog;
    private Method mGcpCheckMethod;
    private UpdateShortcutIconListener mListener;
    private ContentObserver mNubiaGameSceneObserver;
    private PanelDismissListener mPanelDismissListener;
    private Handler mWorkHandler;
    private int mNightDisplayValue = -1;
    private int mSetFailedGameVoiceMode = -1;
    private String GAME_STRENGTHEN_MODE_VALUE = "game_strengthen_mode_value";
    private boolean isSupportGcp = true;
    private BroadcastReceiver mFinishDialogReceiver = new BroadcastReceiver() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameControlDialogCtrl.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                LogUtil.i(GameControlDialogCtrl.TAG, "onReceive: mFinishDialogReceiver action = " + intent.getAction());
                if (!GameControlDialogCtrl.ACTION_CLOSE_SYSTEM_DIALOGS.equals(intent.getAction())) {
                    if (GameControlDialogCtrl.ACTION_CLOSE_CONTROLPANEL.equals(intent.getAction()) && GameControlDialogCtrl.this.mGameControlDialog != null && GameControlDialogCtrl.this.mGameControlDialog.isShowing()) {
                        GameControlDialogCtrl.this.mGameControlDialog.dismiss();
                        return;
                    }
                    return;
                }
                String stringExtra = intent.getStringExtra("reason");
                LogUtil.i(GameControlDialogCtrl.TAG, "onReceive: mFinishDialogReceiver reason = " + stringExtra);
                if (stringExtra != null) {
                    if ((ActivityManagerWrapper.CLOSE_SYSTEM_WINDOWS_REASON_HOME_KEY.equals(stringExtra) || ActivityManagerWrapper.CLOSE_SYSTEM_WINDOWS_REASON_RECENTS.equals(stringExtra)) && GameControlDialogCtrl.this.mGameControlDialog != null && GameControlDialogCtrl.this.mGameControlDialog.isShowing()) {
                        GameControlDialogCtrl.this.mGameControlDialog.dismiss();
                    }
                }
            }
        }
    };
    private final ComponentCallbacks mComponentCallbacks = new AnonymousClass2();
    private final BroadcastReceiver mConfigurationChangedReceiver = new BroadcastReceiver() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameControlDialogCtrl.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent != null && "android.intent.action.CONFIGURATION_CHANGED".equals(intent.getAction()) && GameControlDialogCtrl.this.mGameControlDialog != null && GameControlDialogCtrl.this.mGameControlDialog.isShowing()) {
                GameControlDialogCtrl.this.reloadDialogLayoutAndState();
            }
        }
    };

    /* renamed from: cn.nubia.gamelauncher.gamecontrolpanel.GameControlDialogCtrl$2, reason: invalid class name */
    class AnonymousClass2 implements ComponentCallbacks {
        AnonymousClass2() {
        }

        /* renamed from: lambda$onConfigurationChanged$0$cn-nubia-gamelauncher-gamecontrolpanel-GameControlDialogCtrl$2, reason: not valid java name */
        /* synthetic */ void m254x9e535895() {
            if (GameControlDialogCtrl.this.mGameControlDialog == null || !GameControlDialogCtrl.this.mGameControlDialog.isShowing()) {
                return;
            }
            GameControlDialogCtrl.this.reloadDialogLayoutAndState();
        }

        @Override // android.content.ComponentCallbacks
        public void onConfigurationChanged(Configuration configuration) {
            if (GameControlDialogCtrl.this.mGameControlDialog == null || !GameControlDialogCtrl.this.mGameControlDialog.isShowing()) {
                return;
            }
            Runnable runnable = new Runnable() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameControlDialogCtrl$2$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    GameControlDialogCtrl.AnonymousClass2.this.m254x9e535895();
                }
            };
            if (Looper.myLooper() == Looper.getMainLooper()) {
                runnable.run();
            } else {
                new Handler(Looper.getMainLooper()).post(runnable);
            }
        }

        @Override // android.content.ComponentCallbacks
        public void onLowMemory() {
        }
    }

    public interface UpdateShortcutIconListener {
        void updateShortcutIcon(Drawable drawable);
    }

    public GameControlDialogCtrl(Context context) {
        this.mWorkHandler = null;
        this.mNubiaGameSceneObserver = new ContentObserver(this.mWorkHandler) { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameControlDialogCtrl.4
            @Override // android.database.ContentObserver
            public void onChange(boolean z, Uri uri) {
                GameControlDialogCtrl.this.handleDialogStatus();
            }
        };
        this.mContext = context;
        HandlerThread handlerThread = new HandlerThread(TAG);
        handlerThread.start();
        this.mWorkHandler = new Handler(handlerThread.getLooper());
        initGcpCheckMethod();
        initIsSprdPlatform();
    }

    private TouchOperationBean assembleOperationData() {
        TouchOperationBean touchOperationBean = new TouchOperationBean(PerformanceUtils.hasGyroSenFun(this.mCurrentPackageName), true);
        PerformanceUtils.getOperationParamFromDB(this.mContext, this.mCurrentPackageName, TouchOperationBean.OperationTypeParams.TOUCH_SAMPLE);
        PerformanceUtils.getOperationParamFromDB(this.mContext, this.mCurrentPackageName, TouchOperationBean.OperationTypeParams.TOUCH_SEN);
        PerformanceUtils.getOperationParamFromDB(this.mContext, this.mCurrentPackageName, TouchOperationBean.OperationTypeParams.TOUCH_FOLLOW);
        PerformanceUtils.getOperationParamFromDB(this.mContext, this.mCurrentPackageName, TouchOperationBean.OperationTypeParams.TOUCH_MICRO_SENSITIVE);
        PerformanceUtils.getOperationParamFromDB(this.mContext, this.mCurrentPackageName, TouchOperationBean.OperationTypeParams.GYROSEN);
        PerformanceUtils.getOperationParamFromDB(this.mContext, this.mCurrentPackageName, TouchOperationBean.OperationTypeParams.TOUCH_PROTECTION);
        return touchOperationBean;
    }

    private boolean checkGcpSupport() {
        if (TextUtils.isEmpty(this.mCurrentPackageName)) {
            return false;
        }
        Method method = this.mGcpCheckMethod;
        if (method != null) {
            try {
                return ((Boolean) method.invoke(null, this.mCurrentPackageName, null)).booleanValue();
            } catch (IllegalAccessException e) {
                LogUtil.e(TAG, "checkGcpSupport IllegalAccessException = " + e.toString());
            } catch (InvocationTargetException e2) {
                LogUtil.e(TAG, "checkGcpSupport InvocationTargetException = " + e2.toString());
            }
        } else {
            LogUtil.d(TAG, "checkGcpSupport mGcpCheckMethod is null");
        }
        return false;
    }

    private int getGameStrengthenIndicatotIndexFromDB() {
        String[] split;
        if (TextUtils.isEmpty(this.mCurrentPackageName)) {
            return 0;
        }
        String string = Settings.Global.getString(this.mContext.getContentResolver(), DB_GAME_STRENGTHEN_MODE_ALL_LIST);
        if (!TextUtils.isEmpty(string) && string.contains(this.mCurrentPackageName) && (split = string.split(",")) != null && split.length != 0) {
            for (String str : split) {
                if (!TextUtils.isEmpty(str) && str.contains(this.mCurrentPackageName)) {
                    return Integer.parseInt(str.substring(str.indexOf("+") + 1));
                }
            }
        }
        return 0;
    }

    private String getGameStrengthenValue(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String str2 = str + "+";
        String string = Settings.Global.getString(this.mContext.getContentResolver(), DB_GAME_PERFORMANCE_MODE_ALL_LIST);
        LogUtil.i(TAG, "getGameStrengthenValue strengthenValue " + string);
        if (string != null && string.indexOf(str2) != -1) {
            for (String str3 : string.split(",")) {
                String trim = str3.trim();
                if (!trim.isEmpty() && trim.indexOf(str2) != -1) {
                    return trim;
                }
            }
        }
        return null;
    }

    private int getIndicatorIndex(String str) {
        int gameStrengthenIndicatotIndexFromDB = getGameStrengthenIndicatotIndexFromDB();
        LogUtil.d(TAG, "getIndicatorIndex actionType = " + str);
        if (TextUtils.isEmpty(str)) {
            LogUtil.d(TAG, "actionType is null or empty, returning default indicatorIndex = " + gameStrengthenIndicatotIndexFromDB);
            return gameStrengthenIndicatotIndexFromDB;
        }
        str.hashCode();
        switch (str) {
            case "ScreenShowStrengthen":
                gameStrengthenIndicatotIndexFromDB = this.mGameControlDialog.getShowScreenIndex();
                break;
            case "ResourceSettings":
                gameStrengthenIndicatotIndexFromDB = this.mGameControlDialog.getResourceSettingsIndex();
                break;
            case "AdjustOperation":
                gameStrengthenIndicatotIndexFromDB = this.mGameControlDialog.getAdjustIndex();
                break;
            case "VoiceStrengthen":
                gameStrengthenIndicatotIndexFromDB = this.mGameControlDialog.getStrengthVoiceIndex();
                break;
            case "PerformanceStrengthen":
                gameStrengthenIndicatotIndexFromDB = this.mGameControlDialog.getPerfModeIndex();
                break;
            case "NetSettings":
                gameStrengthenIndicatotIndexFromDB = this.mGameControlDialog.getNetSettingsIndex();
                break;
            case "GpuSettings":
                gameStrengthenIndicatotIndexFromDB = this.mGameControlDialog.getGpuSettingsIndex();
                break;
            case "FunctionConfiguration":
                gameStrengthenIndicatotIndexFromDB = this.mGameControlDialog.getFunctionIndex();
                break;
            default:
                LogUtil.d(TAG, "Unknown actionType: " + str + ", using default indicatorIndex = " + gameStrengthenIndicatotIndexFromDB);
                break;
        }
        LogUtil.d(TAG, "getIndicatorIndex indicatorIndex = " + gameStrengthenIndicatotIndexFromDB);
        return gameStrengthenIndicatotIndexFromDB;
    }

    private int getSpecificGameStrengthenParam(String str, int i, int i2) {
        try {
            return Integer.parseInt(String.valueOf(str.charAt(i + i2)));
        } catch (StringIndexOutOfBoundsException | Exception unused) {
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleDialogStatus() {
        GameControlDialog gameControlDialog;
        int i = Settings.Global.getInt(this.mContext.getContentResolver(), "nubia_game_scene", 0);
        LogUtil.i(TAG, "handleDialogStatus: gameScene = " + i);
        if (i == 0 && (gameControlDialog = this.mGameControlDialog) != null && gameControlDialog.isShowing()) {
            this.mGameControlDialog.dismiss();
        }
    }

    private void initDefaultStrengthenMode(String str) {
        int i = 0;
        boolean z = Settings.Global.getInt(this.mContext.getContentResolver(), "nubia_game_scene", 0) != 0;
        Log.d(TAG, "initDefaultStrengthenMode packageName:" + str + ", isGameScene:" + z);
        if (this.isSprdPlatform || z) {
            return;
        }
        if (CommonUtil.isNubia() && PerformanceUtils.supportSharpenDisplay(str)) {
            i = 4;
        }
        boolean supportDTSXULTR = PerformanceUtils.supportDTSXULTR(str);
        if (i != 0 || supportDTSXULTR) {
            String format = String.format("%d%d%d", 2, Integer.valueOf(i), Integer.valueOf(supportDTSXULTR ? 1 : 0));
            String string = Settings.Global.getString(this.mContext.getContentResolver(), DB_GAME_PERFORMANCE_MODE_ALL_LIST);
            Log.d(TAG, "initDefaultStrengthenMode strengthenValue:" + string + "  targetMode:" + format);
            if (TextUtils.isEmpty(string)) {
                Settings.Global.putString(this.mContext.getContentResolver(), DB_GAME_PERFORMANCE_MODE_ALL_LIST, str + "+" + format + ",");
                return;
            }
            List asList = Arrays.asList(string.split("\\+\\d+,"));
            if (asList == null || asList.contains(str)) {
                return;
            }
            Settings.Global.putString(this.mContext.getContentResolver(), DB_GAME_PERFORMANCE_MODE_ALL_LIST, string + str + "+" + format + ",");
        }
    }

    private void initIsSprdPlatform() {
        try {
            this.isSprdPlatform = Utils.isSprdPlatform();
        } catch (Exception e) {
            LogUtil.e(TAG, "initIsSprdPlatform Exception", e);
        }
    }

    private int[] parserGyro(String str) {
        int[] iArr = {100, 100};
        if (!TextUtils.isEmpty(str)) {
            try {
                int indexOf = str.indexOf("&");
                String substring = str.substring(0, indexOf);
                String substring2 = str.substring(indexOf + 1, str.length());
                iArr[0] = Integer.parseInt(substring);
                iArr[1] = Integer.parseInt(substring2);
            } catch (Exception e) {
                LogUtil.e(TAG, "parserGyro error " + e.toString());
            }
        }
        return iArr;
    }

    private void registerComponentCallbacks() {
        try {
            this.mContext.registerComponentCallbacks(this.mComponentCallbacks);
        } catch (Exception e) {
            LogUtil.e(TAG, "registerComponentCallbacks error", e);
        }
    }

    private void registerConfigurationReceiver() {
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.CONFIGURATION_CHANGED");
            this.mContext.registerReceiver(this.mConfigurationChangedReceiver, intentFilter, 2);
        } catch (Exception e) {
            LogUtil.e(TAG, "registerConfigurationReceiver error", e);
        }
    }

    private void registerReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_CLOSE_SYSTEM_DIALOGS);
        intentFilter.addAction(ACTION_CLOSE_CONTROLPANEL);
        intentFilter.setPriority(1000);
        this.mContext.registerReceiver(this.mFinishDialogReceiver, intentFilter, 2);
    }

    private void registerSettingsObserver() {
        try {
            LogUtil.d(TAG, "registerSettingsObserver: ");
            this.mContext.getContentResolver().registerContentObserver(Settings.Global.getUriFor("nubia_game_scene"), false, this.mNubiaGameSceneObserver);
        } catch (Exception e) {
            LogUtil.d(TAG, "registerSettingsObserver: " + e.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reloadDialogLayoutAndState() {
        int i;
        int i2;
        int i3;
        GameControlDialog gameControlDialog = this.mGameControlDialog;
        if (gameControlDialog == null || !gameControlDialog.isShowing()) {
            return;
        }
        this.isSupportGcp = checkGcpSupport();
        String gameStrengthenValue = getGameStrengthenValue(this.mCurrentPackageName);
        boolean z = true;
        if (TextUtils.isEmpty(gameStrengthenValue)) {
            i = 2;
            i2 = 0;
            i3 = 0;
        } else {
            int indexOf = gameStrengthenValue.indexOf("+");
            int specificGameStrengthenParam = getSpecificGameStrengthenParam(gameStrengthenValue, indexOf, 1);
            if (specificGameStrengthenParam < 1) {
                specificGameStrengthenParam = 2;
            }
            int specificGameStrengthenParam2 = getSpecificGameStrengthenParam(gameStrengthenValue, indexOf, 2);
            i3 = getSpecificGameStrengthenParam(gameStrengthenValue, indexOf, 3);
            i2 = specificGameStrengthenParam2;
            i = specificGameStrengthenParam;
        }
        int gameStrengthenIndicatotIndexFromDB = getGameStrengthenIndicatotIndexFromDB();
        String string = Settings.Global.getString(this.mContext.getContentResolver(), DB_GAME_COLOR_INVERT);
        int i4 = Settings.Global.getInt(this.mContext.getContentResolver(), this.GAME_STRENGTHEN_MODE_VALUE, 0);
        if (CommonUtil.isRedMagicRunOnMyOs() || CommonUtil.isRedMagicLegacyProject() ? i4 != 5 : TextUtils.isEmpty(string) || !string.contains(this.mCurrentPackageName)) {
            z = false;
        }
        this.mGameControlDialog.reloadLayoutForConfigurationChange();
        this.mGameControlDialog.applyState(i, i2, i3, gameStrengthenIndicatotIndexFromDB, this.isSupportGcp, assembleOperationData(), z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveGameStrengthenNewValueToDB(int i, int i2) {
        if (TextUtils.isEmpty(this.mCurrentPackageName)) {
            return;
        }
        String string = Settings.Global.getString(this.mContext.getContentResolver(), DB_GAME_PERFORMANCE_MODE_ALL_LIST);
        if (!TextUtils.isEmpty(string) && string.contains(this.mCurrentPackageName + "+")) {
            String[] split = string.split(",");
            int length = split.length;
            int i3 = 0;
            while (true) {
                if (i3 >= length) {
                    break;
                }
                String str = split[i3];
                if (TextUtils.isEmpty(str) || !str.contains(this.mCurrentPackageName + "+")) {
                    i3++;
                } else {
                    int indexOf = str.indexOf("+");
                    int specificGameStrengthenParam = getSpecificGameStrengthenParam(str, indexOf, 1);
                    int specificGameStrengthenParam2 = getSpecificGameStrengthenParam(str, indexOf, 2);
                    int specificGameStrengthenParam3 = getSpecificGameStrengthenParam(str, indexOf, 3);
                    if (i != 0) {
                        if (i == 1) {
                            specificGameStrengthenParam2 = i2;
                        } else if (i == 2) {
                            specificGameStrengthenParam3 = i2;
                        }
                        i2 = specificGameStrengthenParam;
                    }
                    string = string.replace(str, this.mCurrentPackageName + "+" + i2 + specificGameStrengthenParam2 + specificGameStrengthenParam3);
                }
            }
        } else {
            if (TextUtils.isEmpty(string)) {
                string = "";
            }
            if (i == 0) {
                string = string + this.mCurrentPackageName + "+" + i2 + "00,";
            } else if (i == 1) {
                string = string + this.mCurrentPackageName + "+2" + i2 + "0,";
            } else if (i == 2) {
                string = string + this.mCurrentPackageName + "+20" + i2 + ",";
            }
        }
        Settings.Global.putString(this.mContext.getContentResolver(), DB_GAME_PERFORMANCE_MODE_ALL_LIST, string);
        this.mGameControlDialog.updateNetSettingsSwitch();
    }

    private void showGameStrengthen(String str) {
        int i;
        GameKeyObserver.getInstance(this.mContext).addCallback(this);
        registerReceiver();
        registerConfigurationReceiver();
        registerComponentCallbacks();
        registerSettingsObserver();
        if (Utils.isShortcut()) {
            this.mWorkHandler.post(new Runnable() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameControlDialogCtrl$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    GameControlDialogCtrl.this.m252x1ff2428b();
                }
            });
        }
        String gameStrengthenValue = getGameStrengthenValue(this.mCurrentPackageName);
        int functionIndex = TextUtils.equals(str, GameFeatureService.ACTION_TYPE_FAN) ? this.mGameControlDialog.getFunctionIndex() : TextUtils.equals(str, GameFeatureService.ACTION_TYPE_PERF_MODE) ? this.mGameControlDialog.getPerfModeIndex() : getIndicatorIndex(str);
        String string = Settings.Global.getString(this.mContext.getContentResolver(), DB_GAME_COLOR_INVERT);
        boolean z = false;
        int i2 = Settings.Global.getInt(this.mContext.getContentResolver(), this.GAME_STRENGTHEN_MODE_VALUE, 0);
        LogUtil.i(TAG, "showGameStrengthen: colorInvertValue = " + string + " ;; zteInvertValue = " + i2 + " ;; gameStrengthernValue = " + gameStrengthenValue);
        if (CommonUtil.isRedMagicRunOnMyOs() || CommonUtil.isRedMagicLegacyProject() ? i2 == 5 : !(TextUtils.isEmpty(string) || !string.contains(this.mCurrentPackageName))) {
            z = true;
        }
        boolean z2 = z;
        if (TextUtils.isEmpty(gameStrengthenValue)) {
            this.mGameControlDialog.show(2, 0, 0, functionIndex, this.isSupportGcp, assembleOperationData(), z2);
            return;
        }
        int indexOf = gameStrengthenValue.indexOf("+");
        int specificGameStrengthenParam = getSpecificGameStrengthenParam(gameStrengthenValue, indexOf, 1);
        if (specificGameStrengthenParam < 1) {
            this.mWorkHandler.post(new Runnable() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameControlDialogCtrl$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    GameControlDialogCtrl.this.m253xb430b22a();
                }
            });
            i = 2;
        } else {
            i = specificGameStrengthenParam;
        }
        this.mGameControlDialog.show(i, getSpecificGameStrengthenParam(gameStrengthenValue, indexOf, 2), getSpecificGameStrengthenParam(gameStrengthenValue, indexOf, 3), functionIndex, this.isSupportGcp, assembleOperationData(), z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unRegisterSettingsObserver() {
        try {
            LogUtil.d(TAG, "unRegisterSettingsObserver: ");
            this.mContext.getContentResolver().unregisterContentObserver(this.mNubiaGameSceneObserver);
        } catch (Exception e) {
            LogUtil.d(TAG, "unRegisterSettingsObserver: " + e.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unregisterComponentCallbacks() {
        try {
            this.mContext.unregisterComponentCallbacks(this.mComponentCallbacks);
        } catch (Exception e) {
            LogUtil.e(TAG, "unregisterComponentCallbacks error", e);
        }
    }

    public void initGcpCheckMethod() {
        try {
            this.mGcpCheckMethod = Class.forName("android.view.SurfaceControl").getDeclaredMethod("checkGcpSupport", String.class, String.class);
        } catch (Exception e) {
            LogUtil.e(TAG, "initGcpCheckMethod Exception = " + e.toString());
        }
    }

    /* renamed from: lambda$showGameStrengthen$0$cn-nubia-gamelauncher-gamecontrolpanel-GameControlDialogCtrl, reason: not valid java name */
    /* synthetic */ void m252x1ff2428b() {
        ShortcutInfo shortcutInfoByShortName = ShortCutHelper.getInstance().getShortcutInfoByShortName(Utils.getShortCutLabel());
        if (this.mListener != null) {
            LogUtil.i(TAG, "updateShortcutIcon: ");
            this.mListener.updateShortcutIcon(ShortCutHelper.getInstance().getShortcutIcon(shortcutInfoByShortName));
        }
    }

    /* renamed from: lambda$showGameStrengthen$1$cn-nubia-gamelauncher-gamecontrolpanel-GameControlDialogCtrl, reason: not valid java name */
    /* synthetic */ void m253xb430b22a() {
        LogUtil.d(TAG, "error value,reset performance mode value");
        saveGameStrengthenNewValueToDB(0, 2);
    }

    @Override // cn.nubia.common.GameKeyObserver.Callback
    public void onGameKeyChanged(boolean z) {
        GameControlDialog gameControlDialog;
        LogUtil.i(TAG, " onGameKeyChanged  ----- isOff = " + z);
        if (Utils.isZte() && (gameControlDialog = this.mGameControlDialog) != null && gameControlDialog.isShowing()) {
            this.mGameControlDialog.dismiss();
        }
    }

    public void saveGameStrengthenIndicatotIndexToDB(int i) {
        if (TextUtils.isEmpty(this.mCurrentPackageName)) {
            return;
        }
        String string = Settings.Global.getString(this.mContext.getContentResolver(), DB_GAME_STRENGTHEN_MODE_ALL_LIST);
        if (TextUtils.isEmpty(string) || !string.contains(this.mCurrentPackageName)) {
            string = string + "," + this.mCurrentPackageName + "+" + i;
        } else {
            String[] split = string.split(",");
            if (split != null && split.length != 0) {
                int length = split.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    }
                    String str = split[i2];
                    if (!TextUtils.isEmpty(str) && str.contains(this.mCurrentPackageName)) {
                        string = string.replace(str, this.mCurrentPackageName + "+" + i);
                        break;
                    }
                    i2++;
                }
            }
        }
        Settings.Global.putString(this.mContext.getContentResolver(), DB_GAME_STRENGTHEN_MODE_ALL_LIST, string);
    }

    public void setPanelDismissListener(PanelDismissListener panelDismissListener) {
        this.mPanelDismissListener = panelDismissListener;
    }

    public void showGameStrengthenModeView(String str, String str2, String str3) {
        try {
            GameControlDialog gameControlDialog = this.mGameControlDialog;
            if (gameControlDialog == null || !gameControlDialog.isShowing()) {
                this.mCurrentPackageName = str;
                this.mCurrentActivity = str2;
                initDefaultStrengthenMode(str);
                GameControlDialog gameControlDialog2 = new GameControlDialog(this.mContext, this.mCurrentPackageName, this.mCurrentActivity);
                this.mGameControlDialog = gameControlDialog2;
                this.mListener = gameControlDialog2;
                gameControlDialog2.setGameStrengthSelectedListener(new IGameStrengthSelectedListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameControlDialogCtrl.5
                    @Override // cn.nubia.gamelauncher.gamecontrolpanel.IGameStrengthSelectedListener
                    public void onAdjustOperationDataChanged(TouchOperationBean.OperationTypeParams operationTypeParams) {
                        PerformanceUtils.saveOperationParamToDB(GameControlDialogCtrl.this.mContext, GameControlDialogCtrl.this.mCurrentPackageName, operationTypeParams, GameControlDialogCtrl.this.mWorkHandler);
                    }

                    @Override // cn.nubia.gamelauncher.gamecontrolpanel.IGameStrengthSelectedListener
                    public void onGameStrengthIndicatorSelected(int i) {
                        GameControlDialogCtrl.this.saveGameStrengthenIndicatotIndexToDB(i);
                    }

                    @Override // cn.nubia.gamelauncher.gamecontrolpanel.IGameStrengthSelectedListener
                    public void onGameStrengthSelected(int i, int i2, int[] iArr) {
                        GameControlDialogCtrl.this.saveGameStrengthenNewValueToDB(i, i2);
                    }
                });
                showGameStrengthen(str3);
                if (ControlPanelFeatureHelper.getZteFeatureZperfCubeGpsettingEnabled().booleanValue()) {
                    PerfModeObserver.getInstance().setShowCustomPerfWindow(true);
                }
                this.mGameControlDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameControlDialogCtrl.6
                    @Override // android.content.DialogInterface.OnDismissListener
                    public void onDismiss(DialogInterface dialogInterface) {
                        LogUtil.i(GameControlDialogCtrl.TAG, "onDismiss");
                        if (ControlPanelFeatureHelper.getZteFeatureZperfCubeGpsettingEnabled().booleanValue()) {
                            PerfModeObserver.getInstance().setShowCustomPerfWindow(false);
                        }
                        if (GameControlDialogCtrl.this.mPanelDismissListener != null) {
                            GameControlDialogCtrl.this.mPanelDismissListener.panelDismiss();
                            GameKeyObserver.getInstance(GameControlDialogCtrl.this.mContext).removeCallback(GameControlDialogCtrl.this);
                            try {
                                GameControlDialogCtrl.this.mContext.unregisterReceiver(GameControlDialogCtrl.this.mFinishDialogReceiver);
                            } catch (Exception e) {
                                LogUtil.e(GameControlDialogCtrl.TAG, " unregisterReceiver -- error --  ", e);
                            }
                            try {
                                GameControlDialogCtrl.this.mContext.unregisterReceiver(GameControlDialogCtrl.this.mConfigurationChangedReceiver);
                            } catch (Exception e2) {
                                LogUtil.e(GameControlDialogCtrl.TAG, " unregisterReceiver mConfigurationChangedReceiver -- error --  ", e2);
                            }
                            GameControlDialogCtrl.this.unregisterComponentCallbacks();
                            GameControlDialogCtrl.this.unRegisterSettingsObserver();
                        }
                    }
                });
                Settings.Global.putString(this.mContext.getContentResolver(), DB_GAME_STRENGTHEN_MODE_PACKAGENAME, this.mCurrentPackageName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
