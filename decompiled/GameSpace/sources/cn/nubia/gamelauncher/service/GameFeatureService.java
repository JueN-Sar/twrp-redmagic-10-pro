package cn.nubia.gamelauncher.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;
import androidx.media3.extractor.text.ttml.TtmlNode;
import cn.nubia.common.util.CommonUtil;
import cn.nubia.gamelauncher.gamecontrolpanel.GameControlDialogCtrl;
import cn.nubia.gamelauncher.gamecontrolpanel.PanelDismissListener;
import cn.nubia.gamelauncher.gamecontrolpanel.superresolution.SuperResolutionController;
import cn.nubia.gamelauncher.gamecontrolpanel.superresolution.SuperResolutionHelper;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.LogUtil;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.Utils;

/* loaded from: classes.dex */
public class GameFeatureService extends Service implements PanelDismissListener {
    public static final String ACTION_CONTROL_PANEL = "cn.nubia.intent.action.PERFORMANCE_MODE_OPTION";
    public static final String ACTION_CONTROL_PANEL_EXTRA_ACTIVITY = "activity";
    public static final String ACTION_CONTROL_PANEL_EXTRA_CALLING_PKGNAME = "calling_packageName";
    public static final String ACTION_CONTROL_PANEL_EXTRA_PKGNAME = "packageName";
    public static final String ACTION_TYPE = "type";
    public static final String ACTION_TYPE_CONTROL_PANEL = "control_panel_type";
    public static final String ACTION_TYPE_FAN = "action_fan";
    public static final String ACTION_TYPE_IS_SHORTCUT = "isShortCut";
    public static final String ACTION_TYPE_PERF_MODE = "action_perf_mode";
    public static final String ACTION_TYPE_SHORTCUT_LABEL = "shortcutLabel";
    public static final String NUBIA_FAN_PKGNAME = "cn.nubia.fan";
    public static final String OPERATE_TYPE = "operate_type";
    private static final String TAG = "GameFeatureService";
    private static final String VIEW_ID = "view_id";
    private GameControlDialogCtrl mGameControlDialogCtrl;
    private SuperResolutionController mSuperResolutionController;

    private void closeSuperResolution(Context context, String str, Integer num, boolean z) {
        SuperResolutionHelper.saveLastSuperResolutionSwitchStats(context, str, num);
        SuperResolutionHelper.closeSuperResolution(context, str);
        showRestartAppDialog(str, num, z);
    }

    private void showRestartAppDialog(String str, Integer num, boolean z) {
        LogUtil.i(TAG, "showRestartAppDialog: status = " + num);
        this.mSuperResolutionController.showRestartAppWarningDialog(str, getString(SuperResolutionHelper.getDescriptionFromSuperGear(SuperResolutionHelper.valueToSuperGear(num))), z);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        Utils.setCurrentAppName(null);
        Utils.setCurrentPkgName(null);
        Utils.saveHighLightViewId(null);
        LogUtil.i(TAG, "onDestroy");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r4v3, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r4v4, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r4v5, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r7v10, types: [java.lang.StringBuilder] */
    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent == null || !ACTION_CONTROL_PANEL.equals(intent.getAction())) {
            stopSelf();
        } else {
            String stringExtra = intent.getStringExtra("packageName");
            String stringExtra2 = intent.getStringExtra(ACTION_CONTROL_PANEL_EXTRA_ACTIVITY);
            String stringExtra3 = intent.getStringExtra(ACTION_CONTROL_PANEL_EXTRA_CALLING_PKGNAME);
            String stringExtra4 = intent.getStringExtra("type");
            String stringExtra5 = intent.getStringExtra(OPERATE_TYPE);
            ?? stringExtra6 = intent.getStringExtra(ACTION_TYPE_SHORTCUT_LABEL);
            boolean booleanExtra = intent.getBooleanExtra(ACTION_TYPE_IS_SHORTCUT, false);
            LogUtil.i(TAG, "onStartCommand, pkgname = " + stringExtra + ", curActivity = " + stringExtra2 + "  ;; callingPkgName = " + stringExtra3 + " ;; action_type = " + stringExtra4 + " ;; version = " + CommonUtil.getGameSpaceVersionName(this, getPackageName()) + " ;; shortCutLabel = " + stringExtra6 + " ;; isShortCut = " + booleanExtra);
            Utils.setCurrentPkgName(stringExtra);
            Utils.setShortcut(booleanExtra);
            Utils.setShortCutLabel(stringExtra6);
            if ("super_resolution".equals(stringExtra4)) {
                SuperResolutionHelper.resetCurrentAppSupportGearArray();
                if (this.mSuperResolutionController == null) {
                    SuperResolutionController superResolutionController = new SuperResolutionController(getApplicationContext());
                    this.mSuperResolutionController = superResolutionController;
                    superResolutionController.setPanelDismissListener(new PanelDismissListener() { // from class: cn.nubia.gamelauncher.service.GameFeatureService$$ExternalSyntheticLambda0
                        @Override // cn.nubia.gamelauncher.gamecontrolpanel.PanelDismissListener
                        public final void panelDismiss() {
                            GameFeatureService.this.panelDismiss();
                        }
                    });
                }
                String stringExtra7 = intent.getStringExtra("current_state");
                Integer superResolutionSwitchStatus = SuperResolutionHelper.getSuperResolutionSwitchStatus(getApplicationContext(), stringExtra);
                LogUtil.i(TAG, "onStartCommand status = " + superResolutionSwitchStatus + " ;; operate_type = " + stringExtra5 + " ;; resolution_status = " + stringExtra7);
                String[] supportResolutionGear = SuperResolutionHelper.getSupportResolutionGear(stringExtra);
                if (supportResolutionGear == null) {
                    LogUtil.i(TAG, " get support Gear array error ");
                    return 2;
                }
                int i3 = 1;
                boolean z = supportResolutionGear.length > 1;
                if ("close".equals(stringExtra5)) {
                    Context applicationContext = getApplicationContext();
                    if (superResolutionSwitchStatus != null && superResolutionSwitchStatus.intValue() != 0) {
                        i3 = superResolutionSwitchStatus.intValue();
                    }
                    closeSuperResolution(applicationContext, stringExtra, Integer.valueOf(i3), z);
                } else if (TtmlNode.TEXT_EMPHASIS_MARK_OPEN.equals(stringExtra5)) {
                    if (superResolutionSwitchStatus != null) {
                        Integer lastSuperResolutionSwitchStats = SuperResolutionHelper.getLastSuperResolutionSwitchStats(getApplicationContext(), stringExtra);
                        LogUtil.i(TAG, "onStartCommand lastSwitchStatus = " + lastSuperResolutionSwitchStats);
                        if (lastSuperResolutionSwitchStats != null && lastSuperResolutionSwitchStats.intValue() != 0) {
                            i3 = lastSuperResolutionSwitchStats.intValue();
                        }
                        Integer valueOf = Integer.valueOf(i3);
                        SuperResolutionHelper.openSuperResolution(getApplicationContext(), stringExtra, valueOf.intValue());
                        showRestartAppDialog(stringExtra, valueOf, z);
                    } else if (z) {
                        this.mSuperResolutionController.showSuperResolutionSettingsDialog(stringExtra, stringExtra2, superResolutionSwitchStatus);
                    } else {
                        SuperResolutionHelper.openSuperResolution(getApplicationContext(), stringExtra, 1);
                        showRestartAppDialog(stringExtra, Integer.valueOf(SuperResolutionHelper.superGearToValue(supportResolutionGear[0])), false);
                    }
                } else if ("switch".equals(stringExtra5)) {
                    this.mSuperResolutionController.showSuperResolutionSettingsDialog(stringExtra, stringExtra2, superResolutionSwitchStatus);
                }
            } else if ("global_search".equals(stringExtra4)) {
                String stringExtra8 = intent.getStringExtra("gcp_start_type");
                String stringExtra9 = intent.getStringExtra("view_id");
                LogUtil.i(TAG, " viewId = " + stringExtra9);
                if (this.mGameControlDialogCtrl == null) {
                    GameControlDialogCtrl gameControlDialogCtrl = new GameControlDialogCtrl(this);
                    this.mGameControlDialogCtrl = gameControlDialogCtrl;
                    gameControlDialogCtrl.setPanelDismissListener(this);
                }
                if (!booleanExtra) {
                    stringExtra6 = Utils.getAppNameByPkgName(getApplicationContext(), stringExtra);
                }
                Utils.setCurrentAppName(stringExtra6);
                Utils.saveHighLightViewId(stringExtra9);
                this.mGameControlDialogCtrl.showGameStrengthenModeView(stringExtra, stringExtra2, stringExtra8);
            } else {
                if (this.mGameControlDialogCtrl == null) {
                    GameControlDialogCtrl gameControlDialogCtrl2 = new GameControlDialogCtrl(this);
                    this.mGameControlDialogCtrl = gameControlDialogCtrl2;
                    gameControlDialogCtrl2.setPanelDismissListener(this);
                }
                if (!booleanExtra) {
                    stringExtra6 = Utils.getAppNameByPkgName(getApplicationContext(), stringExtra);
                }
                Utils.setCurrentAppName(stringExtra6);
                if (TextUtils.equals(NUBIA_FAN_PKGNAME, stringExtra3)) {
                    stringExtra4 = ACTION_TYPE_FAN;
                }
                this.mGameControlDialogCtrl.showGameStrengthenModeView(stringExtra, stringExtra2, stringExtra4);
            }
        }
        return 2;
    }

    @Override // cn.nubia.gamelauncher.gamecontrolpanel.PanelDismissListener
    public void panelDismiss() {
        LogUtil.i(TAG, "panelDismiss");
        SuperResolutionHelper.resetCurrentAppSupportGearArray();
        stopSelf();
    }
}
