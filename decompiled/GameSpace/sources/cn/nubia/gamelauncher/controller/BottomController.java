package cn.nubia.gamelauncher.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import cn.nubia.common.config.GameSpaceConfig;
import cn.nubia.common.util.FeatureUtil;
import cn.nubia.common.view.NubiaFanView;
import cn.nubia.common.view.ZoomLottieAnimationView;
import cn.nubia.common.wallpaper.WallpaperManager;
import cn.nubia.gamecenter.settings.applearning.AppDbSchema;
import cn.nubia.gamelauncher.GameLauncherApplication;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.activity.ExperienceHostMode;
import cn.nubia.gamelauncher.helper.BgmHelper;
import cn.nubia.gamelauncher.helper.Controller;
import cn.nubia.gamelauncher.helper.LobbySoundPoolHelper;
import cn.nubia.gamelauncher.helper.ShortCutHelper;
import cn.nubia.gamelauncher.helper.VibratorHelper;
import cn.nubia.gamelauncher.model.AppAddModel;
import cn.nubia.gamelauncher.util.CommonUtil;
import cn.nubia.gamelauncher.util.GameKeysConstant;
import cn.nubia.gamelauncher.util.LogUtil;
import cn.nubia.gamelauncher.util.NubiaTrackManager;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class BottomController implements View.OnClickListener, View.OnFocusChangeListener {
    public static final String DISPLAY_MODE = "displayMode";
    public static final int MODE_HANDHELD = 1;
    public static final int MODE_NORMAL = 0;
    public static final String SHARED_PREFERENCES_NAME = "data";
    private static final String TAG = "Controller";
    private View mBgmBg;
    private ZoomLottieAnimationView mBgmSwitch;
    private final Map<Integer, Runnable> mClickMaps;
    private int mCurrentMode = -1;
    private NubiaFanView mFan;
    private View mFanBg;
    private Runnable mHandheldCallback;
    private ImageView mModeAR;
    private View mModeBg;
    private ImageView mModeHandheld;
    private ImageView mModeHost;
    private ImageView mModeNormal;
    private ZoomLottieAnimationView mModeView;
    private PopupWindow mModeWindow;
    private Runnable mNormalCallback;
    private final Runnable mSelectedChangedRunnable;
    private SharedPreferences mSharedPref;
    private View mSwitchBg;
    private ZoomLottieAnimationView mSwitchView;

    public BottomController(View view) {
        Runnable runnable = new Runnable() { // from class: cn.nubia.gamelauncher.controller.BottomController$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                BottomController.this.onSelectedChanged();
            }
        };
        this.mSelectedChangedRunnable = runnable;
        this.mClickMaps = new HashMap();
        initMode();
        initView(view);
        Controller.getInstance().addSelectedChangedListener(runnable);
        Controller.getInstance().addBgmCallback(new Runnable() { // from class: cn.nubia.gamelauncher.controller.BottomController$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                BottomController.this.openBgm();
            }
        }, new Runnable() { // from class: cn.nubia.gamelauncher.controller.BottomController$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                BottomController.this.closeBgm();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clickAR() {
        Log.d("Controller", "bottom - clickAR() ");
        dismissPop();
        startAR();
        LobbySoundPoolHelper.getInstance().play();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clickBgm() {
        Log.d("Controller", "bottom - clickBgm() ");
        BgmHelper.getInstance().switchBgmState();
        VibratorHelper.getInstance().vibrateSync();
        doBgmAnim();
        LobbySoundPoolHelper.getInstance().play();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clickFan() {
        Log.d("Controller", "bottom - clickFan() ");
        boolean isFanOpenFromSystem = this.mFan.isFanOpenFromSystem();
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "gamespace_cooling_fan", "switch_on", isFanOpenFromSystem);
        Bundle bundle = new Bundle();
        bundle.putString("package_name", "cn.nubia.gamelauncher");
        bundle.putString("event_name", "gamespace_cooling_fan_switch");
        bundle.putString("action_type", "switch_status");
        bundle.putBoolean(AppDbSchema.AppTable.OneDayCols.ACTION_VALUE, isFanOpenFromSystem);
        bundle.putInt(AppDbSchema.AppTable.OneDayCols.REPORT_INTERVAL, 1);
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", bundle);
        if (this.mSharedPref == null) {
            this.mSharedPref = getContext().getSharedPreferences(GameKeysConstant.IS_FIRST_DIALOG_NAME, 0);
        }
        this.mSharedPref.edit().putString(GameKeysConstant.FAN_STATUS, isFanOpenFromSystem ? "开" : "关");
        if (isFanOpenFromSystem) {
            NubiaTrackManager.getInstance().sendEvent("com.android.settings", "manual_fan_used", "close_position", "game_space_home");
        } else {
            NubiaTrackManager.getInstance().sendEvent("com.android.settings", "manual_fan_used", "open_position", "game_space_home");
        }
        this.mFan.switchFanState();
        this.mFan.doAnim(!isFanOpenFromSystem);
        VibratorHelper.getInstance().vibrateSync();
        LobbySoundPoolHelper.getInstance().play();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clickHandheld() {
        Log.d("Controller", "bottom - clickHandheld() ");
        dismissPop();
        Runnable runnable = this.mHandheldCallback;
        if (runnable != null) {
            runnable.run();
        }
        switchToHandheld();
        LobbySoundPoolHelper.getInstance().play();
        updateSwitchVisible();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clickHostMode() {
        Log.d("Controller", "bottom - clickHostMode() ");
        dismissPop();
        Intent intent = new Intent(getContext(), (Class<?>) ExperienceHostMode.class);
        intent.setFlags(268435456);
        getContext().startActivity(intent);
        LobbySoundPoolHelper.getInstance().play();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clickMode() {
        Log.d("Controller", "bottom - clickMode() ");
        if (supportPopWindow()) {
            showModeSwitchPopupwindow();
        } else if (FeatureUtil.handHeldEnable()) {
            doClickWithOutPop();
            return;
        } else if (supportExperienceHostMode()) {
            clickHostMode();
        } else {
            startAR();
        }
        LobbySoundPoolHelper.getInstance().play();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clickNormal() {
        Log.d("Controller", "bottom - clickNormal() ");
        dismissPop();
        Runnable runnable = this.mNormalCallback;
        if (runnable != null) {
            runnable.run();
        }
        switchToNormal();
        LobbySoundPoolHelper.getInstance().play();
        updateSwitchVisible();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clickSwitch() {
        Log.d("Controller", "bottom - clickSwitch() ");
        Controller.getInstance().switchDisplayMode();
        doLottieAnim(this.mSwitchView, getSwitchAnimJson());
        LobbySoundPoolHelper.getInstance().play();
        VibratorHelper.getInstance().vibrateSync();
        updateModeVisible();
        recordDisplayMode();
        doTrack(Controller.getInstance().isFullMode() ? "card" : "list");
    }

    private void dismissPop() {
        PopupWindow popupWindow = this.mModeWindow;
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }

    private void doBgmAnim() {
        this.mBgmSwitch.cancelAnimation();
        this.mBgmSwitch.setVisibility(0);
        this.mBgmSwitch.setAnimation(!BgmHelper.getInstance().isBgmSwitchOn() ? "lottie/bgm_on.json" : "lottie/bgm_off.json");
        this.mBgmSwitch.playAnimation();
    }

    private void doLottieAnim(ZoomLottieAnimationView zoomLottieAnimationView, String str) {
        if (zoomLottieAnimationView == null || zoomLottieAnimationView.getVisibility() != 0) {
            return;
        }
        zoomLottieAnimationView.cancelAnimation();
        zoomLottieAnimationView.setAnimation(str);
        zoomLottieAnimationView.playAnimation();
    }

    private static void doTrack(String str) {
        if (CommonUtil.isAbroad()) {
            return;
        }
        NubiaTrackManager.getInstance().sendEventEveryDay("cn.nubia.gamelauncher", "game_card_size_setting", "option", str);
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(" ");
        sb.append(AppAddModel.getInstance().getCurrentGameListSize());
        sb.append(" ");
        sb.append(ShortCutHelper.getInstance().getShortcutAddList().size());
        sb.append(" ");
        sb.append(Controller.getInstance().isPureMode() ? "on" : "off");
        NubiaTrackManager.getInstance().sendEventEveryDay("cn.nubia.gamelauncher", "gamespace_view_switching_status", "list_option game_number applet_num pure_mode", sb.toString());
    }

    private void fillClickMaps() {
        this.mClickMaps.put(Integer.valueOf(R.id.bgm_switch), new Runnable() { // from class: cn.nubia.gamelauncher.controller.BottomController$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                BottomController.this.clickBgm();
            }
        });
        this.mClickMaps.put(Integer.valueOf(R.id.fan_anim_view), new Runnable() { // from class: cn.nubia.gamelauncher.controller.BottomController$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                BottomController.this.clickFan();
            }
        });
        this.mClickMaps.put(Integer.valueOf(R.id.switch_view), new Runnable() { // from class: cn.nubia.gamelauncher.controller.BottomController$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                BottomController.this.clickSwitch();
            }
        });
        this.mClickMaps.put(Integer.valueOf(R.id.mode_view), new Runnable() { // from class: cn.nubia.gamelauncher.controller.BottomController$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                BottomController.this.clickMode();
            }
        });
        this.mClickMaps.put(Integer.valueOf(R.id.mode_handheld), new Runnable() { // from class: cn.nubia.gamelauncher.controller.BottomController$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                BottomController.this.clickHandheld();
            }
        });
        this.mClickMaps.put(Integer.valueOf(R.id.mode_normal), new Runnable() { // from class: cn.nubia.gamelauncher.controller.BottomController$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() {
                BottomController.this.clickNormal();
            }
        });
        this.mClickMaps.put(Integer.valueOf(R.id.mode_host), new Runnable() { // from class: cn.nubia.gamelauncher.controller.BottomController$$ExternalSyntheticLambda11
            @Override // java.lang.Runnable
            public final void run() {
                BottomController.this.clickHostMode();
            }
        });
        this.mClickMaps.put(Integer.valueOf(R.id.mode_ar), new Runnable() { // from class: cn.nubia.gamelauncher.controller.BottomController$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                BottomController.this.clickAR();
            }
        });
    }

    private Context getContext() {
        return GameLauncherApplication.getAppContext();
    }

    private String getModeAnimJson() {
        return isNormalMode() ? "lottie/mode_normal.json" : "lottie/mode_handheld.json";
    }

    private String getSwitchAnimJson() {
        return Controller.getInstance().isFullMode() ? "lottie/switch_full.json" : "lottie/switch_grid.json";
    }

    private void handleModeViewFocus(boolean z) {
        if (!z) {
            this.mModeBg.postDelayed(new Runnable() { // from class: cn.nubia.gamelauncher.controller.BottomController$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    BottomController.this.hideFocus();
                }
            }, 300L);
        } else {
            this.mModeBg.removeCallbacks(new Runnable() { // from class: cn.nubia.gamelauncher.controller.BottomController$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    BottomController.this.hideFocus();
                }
            });
            this.mModeBg.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideFocus() {
        this.mModeBg.setVisibility(0);
        this.mModeView.requestFocus();
    }

    private void initBgm(View view) {
        this.mBgmBg = view.findViewById(R.id.bgm_switch_bg);
        ZoomLottieAnimationView zoomLottieAnimationView = (ZoomLottieAnimationView) view.findViewById(R.id.bgm_switch);
        this.mBgmSwitch = zoomLottieAnimationView;
        zoomLottieAnimationView.setOnClickListener(this);
        this.mBgmSwitch.setOnFocusChangeListener(this);
        updateBgm();
    }

    private void initFan(View view) {
        this.mFan = (NubiaFanView) view.findViewById(R.id.fan_anim_view);
        this.mFanBg = view.findViewById(R.id.fan_anim_view_bg);
        this.mFan.setOnClickListener(this);
        this.mFan.setOnFocusChangeListener(this);
        this.mFan.doInit();
        if (GameSpaceConfig.supportFan()) {
            return;
        }
        this.mFan.setVisibility(8);
    }

    private void initMode() {
        if (isHandleConnected()) {
            switchToHandheld();
        } else {
            switchToNormal();
        }
        Controller.getInstance().switchDisplayMode(getContext().getSharedPreferences("data", 0).getInt(DISPLAY_MODE, 0) == 0);
    }

    private void initModeView(View view) {
        this.mModeBg = view.findViewById(R.id.mode_focus_bg);
        ZoomLottieAnimationView zoomLottieAnimationView = (ZoomLottieAnimationView) view.findViewById(R.id.mode_view);
        this.mModeView = zoomLottieAnimationView;
        zoomLottieAnimationView.setOnClickListener(this);
        this.mModeView.setOnFocusChangeListener(this);
        this.mModeView.setAnimation(getModeAnimJson());
        this.mModeView.playAnimation();
        updateModeVisible();
    }

    private void initSwitchView(View view) {
        this.mSwitchBg = view.findViewById(R.id.switch_focus_bg);
        ZoomLottieAnimationView zoomLottieAnimationView = (ZoomLottieAnimationView) view.findViewById(R.id.switch_view);
        this.mSwitchView = zoomLottieAnimationView;
        zoomLottieAnimationView.setOnClickListener(this);
        this.mSwitchView.setOnFocusChangeListener(this);
        this.mSwitchView.setAnimation(getSwitchAnimJson());
        this.mSwitchView.playAnimation();
    }

    private void initView(View view) {
        initFan(view);
        initSwitchView(view);
        initModeView(view);
        initBgm(view);
        fillClickMaps();
    }

    private boolean isHandleConnected() {
        return cn.nubia.common.util.CommonUtil.isHandleConnected();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSelectedChanged() {
        Log.d("Controller", "onSelectedChanged()");
        this.mModeView.setAlpha(Controller.getInstance().hasGameCard() ? 1.0f : 0.0f);
        this.mSwitchView.setVisibility((!Controller.getInstance().hasGameCard() || Controller.getInstance().isHandheld()) ? 8 : 0);
    }

    private void showModeSwitchPopupwindow() {
        if (this.mModeWindow == null) {
            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.popupwindow_switch_mode, (ViewGroup) null);
            this.mModeNormal = (ImageView) inflate.findViewById(R.id.mode_normal);
            this.mModeHandheld = (ImageView) inflate.findViewById(R.id.mode_handheld);
            this.mModeHost = (ImageView) inflate.findViewById(R.id.mode_host);
            this.mModeAR = (ImageView) inflate.findViewById(R.id.mode_ar);
            this.mModeNormal.setOnClickListener(this);
            this.mModeAR.setOnClickListener(this);
            this.mModeHost.setOnClickListener(this);
            this.mModeHandheld.setOnClickListener(this);
            PopupWindow popupWindow = new PopupWindow(inflate, -2, -2);
            this.mModeWindow = popupWindow;
            popupWindow.setContentView(inflate);
            this.mModeWindow.setOutsideTouchable(true);
            this.mModeWindow.setFocusable(true);
            this.mModeNormal.setOnFocusChangeListener(this);
            this.mModeHost.setOnFocusChangeListener(this);
            this.mModeAR.setOnFocusChangeListener(this);
            this.mModeHandheld.setOnFocusChangeListener(this);
        }
        int i = 8;
        this.mModeAR.setVisibility(supportAr() ? 0 : 8);
        this.mModeHost.setVisibility(supportExperienceHostMode() ? 0 : 8);
        this.mModeNormal.setVisibility(isNormalMode() ? 8 : 0);
        ImageView imageView = this.mModeHandheld;
        if (!isHandheldMode() && supportHandheld()) {
            i = 0;
        }
        imageView.setVisibility(i);
        this.mModeWindow.getContentView().measure(0, 0);
        this.mModeWindow.showAsDropDown(this.mModeView, (this.mModeView.getWidth() - this.mModeWindow.getContentView().getMeasuredWidth()) / 2, ((-this.mModeView.getHeight()) - this.mModeWindow.getContentView().getMeasuredHeight()) - 12);
    }

    private void updateVisibility(View view, View view2, boolean z) {
        view2.setVisibility((view.getVisibility() == 0 && z) ? 0 : 8);
    }

    public void closeBgm() {
        Log.d("assist", "BottomController --- closeBgm()");
        if (BgmHelper.getInstance().isBgmSwitchOn()) {
            switchBgm();
        }
    }

    public void doClickWithOutPop() {
        Log.d("Controller", "doClickWithOutPop()");
        if (isNormalMode()) {
            clickHandheld();
        } else {
            clickNormal();
        }
    }

    public void exit() {
        Log.d("Controller", "bottom - exit()");
        Controller.getInstance().clearBgmCallback();
        Controller.getInstance().removeSelectedChangedListener(this.mSelectedChangedRunnable);
    }

    public void finishActivityWhenStartAr() {
        Log.d("Controller", "finishActivityWhenStartAr()");
        ZoomLottieAnimationView zoomLottieAnimationView = this.mModeView;
        if (zoomLottieAnimationView == null) {
            return;
        }
        try {
            ((Activity) zoomLottieAnimationView.getContext()).finish();
        } catch (Exception e) {
            Log.w("Controller", "finishActivityWhenStartAr() Exception : " + e.getMessage());
        }
    }

    public boolean isHandheldMode() {
        return this.mCurrentMode == 1;
    }

    public boolean isNormalMode() {
        return this.mCurrentMode == 0;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Runnable runnable = this.mClickMaps.get(Integer.valueOf(view.getId()));
        if (runnable != null) {
            runnable.run();
        }
    }

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(View view, boolean z) {
        View view2;
        switch (view.getId()) {
            case R.id.bgm_switch /* 2131361957 */:
                view2 = this.mBgmBg;
                break;
            case R.id.fan_anim_view /* 2131362236 */:
                view2 = this.mFanBg;
                break;
            case R.id.mode_ar /* 2131362782 */:
            case R.id.mode_handheld /* 2131362786 */:
            case R.id.mode_normal /* 2131362790 */:
                handleModeViewFocus(z);
                return;
            case R.id.mode_view /* 2131362791 */:
                view2 = this.mModeBg;
                break;
            case R.id.switch_view /* 2131363386 */:
                view2 = this.mSwitchBg;
                break;
            default:
                return;
        }
        if (view2 != null) {
            updateVisibility(view, view2, z);
        }
    }

    public void openBgm() {
        Log.d("assist", "BottomController --- openBgm()");
        if (BgmHelper.getInstance().isBgmSwitchOn()) {
            return;
        }
        switchBgm();
    }

    public void recordDisplayMode() {
        SharedPreferences.Editor edit = getContext().getSharedPreferences("data", 0).edit();
        edit.putInt(DISPLAY_MODE, !Controller.getInstance().isFullMode() ? 1 : 0);
        edit.apply();
    }

    public void setLottieVisibility(int i) {
        ZoomLottieAnimationView zoomLottieAnimationView = this.mBgmSwitch;
        if (zoomLottieAnimationView == null || this.mFan == null || this.mModeView == null) {
            return;
        }
        zoomLottieAnimationView.setVisibility(i);
        this.mFan.setVisibility(i);
        ZoomLottieAnimationView zoomLottieAnimationView2 = this.mModeView;
        if (!supportMode()) {
            i = 8;
        }
        zoomLottieAnimationView2.setVisibility(i);
    }

    public void setSwitchCallback(Runnable runnable, Runnable runnable2) {
        this.mNormalCallback = runnable;
        this.mHandheldCallback = runnable2;
    }

    public void startAR() {
        try {
            DisplayManager.class.getMethod("setCmdToDisplay", Integer.TYPE, Integer.TYPE, Integer.TYPE, Bundle.class).invoke((DisplayManager) getContext().getSystemService("display"), 4, -1, 8, null);
            LogUtil.d("Controller", "startAR() end");
            finishActivityWhenStartAr();
        } catch (Exception e) {
            LogUtil.d("Controller", "startAR() Exception : " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public boolean supportAr() {
        int i = Settings.Global.getInt(getContext().getContentResolver(), "db_mirror_host_mode", 1);
        if (i != 0) {
            LogUtil.d("Controller", "supportAr() status : " + i);
            return false;
        }
        int i2 = Settings.Global.getInt(getContext().getContentResolver(), "db_xr_mirror_host_mode", 1);
        if (i2 != 0) {
            LogUtil.d("Controller", "supportAr() mode : " + i2);
            return false;
        }
        int i3 = Settings.Global.getInt(getContext().getContentResolver(), "mirror_is_xr_device", 0);
        if (i3 == 1) {
            return true;
        }
        LogUtil.d("Controller", "supportAr() device : " + i3);
        return false;
    }

    public boolean supportExperienceHostMode() {
        return GameSpaceConfig.supportExperienceHostMode();
    }

    public boolean supportHandheld() {
        return FeatureUtil.handHeldEnable();
    }

    public boolean supportMode() {
        return supportAr() || supportHandheld() || supportExperienceHostMode();
    }

    public boolean supportPopWindow() {
        int i = supportAr() ? 2 : 1;
        if (supportHandheld()) {
            i++;
        }
        if (supportExperienceHostMode()) {
            i++;
        }
        return i > 2;
    }

    public void switchBgm() {
        Log.d("assist", "BottomController --- switchBgm()");
        BgmHelper.getInstance().switchBgmState();
        this.mBgmSwitch.setAnimation(BgmHelper.getInstance().isBgmSwitchOn() ? "lottie/bgm_on.json" : "lottie/bgm_off.json");
        this.mBgmSwitch.setProgress(0.0f);
    }

    public void switchMode(int i) {
        Log.d("Controller", "switchMode( " + this.mCurrentMode + ") to mode : " + i);
        if (i == this.mCurrentMode) {
            return;
        }
        this.mCurrentMode = i;
    }

    public void switchToHandheld() {
        switchMode(1);
        WallpaperManager.getInstance().switchToWallpaper();
        doLottieAnim(this.mModeView, "lottie/mode_handheld.json");
        doTrack("handheld");
    }

    public void switchToNormal() {
        switchMode(0);
        doLottieAnim(this.mModeView, "lottie/mode_normal.json");
        doTrack(Controller.getInstance().isFullMode() ? "card" : "list");
    }

    public void updateBgm() {
        this.mBgmSwitch.setAnimation(BgmHelper.getInstance().isBgmSwitchOn() ? "lottie/bgm_on.json" : "lottie/bgm_off.json");
        this.mBgmSwitch.setProgress(0.0f);
    }

    public void updateModeVisible() {
        Log.d("Controller", "bottom - updateModeVisible()");
        this.mModeView.setVisibility((Controller.getInstance().isFullMode() && supportMode()) ? 0 : 8);
    }

    public void updateSwitchVisible() {
        Log.d("Controller", "bottom - updateSwitchVisible()");
        this.mSwitchView.setVisibility(isNormalMode() ? 0 : 8);
    }
}
