package cn.nubia.gamelauncher.activity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.ContentObserver;
import android.hardware.display.DisplayManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.MessageQueue;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.media3.extractor.text.ttml.TtmlNode;
import cn.nubia.common.GameKeyObserver;
import cn.nubia.common.app.AlertDialog;
import cn.nubia.common.config.GameSpaceConfig;
import cn.nubia.common.helper.AppUsageStatsHelper;
import cn.nubia.common.helper.ImageCache;
import cn.nubia.common.util.SharedPreferencesUtil;
import cn.nubia.common.wallpaper.WallpaperManager;
import cn.nubia.gamecenter.settings.applearning.AppDbSchema;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.atmosphere.Atmosphere;
import cn.nubia.gamelauncher.atmosphere.LiveAtmosphereManager;
import cn.nubia.gamelauncher.commoninterface.ConstantVariable;
import cn.nubia.gamelauncher.controller.GameStateController;
import cn.nubia.gamelauncher.fragment.GameLobbyFragment;
import cn.nubia.gamelauncher.fragment.HandheldFragment;
import cn.nubia.gamelauncher.fragment.HostModeGameLobbyFragment;
import cn.nubia.gamelauncher.fragment.RedMagicPlanetFragment;
import cn.nubia.gamelauncher.fragment.SplashFragment;
import cn.nubia.gamelauncher.gamehandle.NubiaCTAPermissionUtils;
import cn.nubia.gamelauncher.helper.BgmHelper;
import cn.nubia.gamelauncher.helper.Controller;
import cn.nubia.gamelauncher.helper.LobbySoundPoolHelper;
import cn.nubia.gamelauncher.helper.NetWhitelistHelper;
import cn.nubia.gamelauncher.helper.SoundPoolHelper;
import cn.nubia.gamelauncher.helper.VibratorHelper;
import cn.nubia.gamelauncher.model.AppAddModel;
import cn.nubia.gamelauncher.neostore.NeoHelper;
import cn.nubia.gamelauncher.redmagicplanet.util.RedMagicVideoPlayerManager;
import cn.nubia.gamelauncher.service.GameFeatureService;
import cn.nubia.gamelauncher.upgrade.UpgradeManager;
import cn.nubia.gamelauncher.util.CommonUtil;
import cn.nubia.gamelauncher.util.GameCountTrack;
import cn.nubia.gamelauncher.util.GameKeysConstant;
import cn.nubia.gamelauncher.util.LogUtil;
import cn.nubia.gamelauncher.util.NubiaTrackManager;
import cn.nubia.gamelauncher.util.ToastUtil;
import cn.nubia.gamelauncher.util.Util;
import cn.nubia.gamelauncher.wallpaper.Wallpaper;
import cn.nubia.neostore.api.callback.ICallback;
import cn.nubia.neostore.api.model.ErrorMsg;
import cn.nubia.neostore.api.model.NubiaGameNotice;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;

/* loaded from: classes.dex */
public class GameSpaceActivity extends BaseFragmentActivity implements GameKeyObserver.Callback, ICallback<NubiaGameNotice> {
    public static final String HAS_PERMISSION = "has_permission";
    private static final String SETTING_LAUNCHER_RESUME = "nubia_gamelauncher_resume";
    public static final String SHARED_PREFERENCES_NAME = "data";
    private static final String TAG = "GameSpaceAct";
    public static final String TAG_GAME_LOBBY = "GameLobby";
    public static final String TAG_HAND_HELD = "Handheld";
    public static final String TAG_RED_MAGIC_PLANET = "RedMagicPlanet";
    public static final String TAG_SPLASH = "Splash";
    private AlertDialog mAlertDialog;
    private GameStateController mController;
    Fragment mCurrentFragment;
    Fragment mGameLobby;
    HandheldFragment mHandheldFragment;
    private PureObserver mPureObserver;
    RedMagicPlanetFragment mRedMagicPlanet;
    private Bundle mSavedState;
    SplashFragment mSplash;
    SoundPoolHelper mSwitchSound;
    private Wallpaper mWallpaper;
    private int mSplashFlag = 0;
    private String mCurrentTag = TAG_SPLASH;
    private Handler mHandler = new Handler();

    private class OnceIdleHandle implements MessageQueue.IdleHandler {
        private OnceIdleHandle() {
        }

        @Override // android.os.MessageQueue.IdleHandler
        public boolean queueIdle() {
            GameSpaceActivity.this.initNubiaTrackManager();
            return false;
        }
    }

    private class PureObserver extends ContentObserver {
        public PureObserver(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            Log.d(GameSpaceActivity.TAG, "PureObserver ---- onChange()");
            GameSpaceActivity.this.recreate();
        }

        public void register() {
            GameSpaceActivity.this.getContentResolver().registerContentObserver(Settings.Global.getUriFor(Util.PURE_MODE), false, this);
        }

        public void unregister() {
            GameSpaceActivity.this.getContentResolver().unregisterContentObserver(this);
        }
    }

    private void addFragment(String str, FragmentTransaction fragmentTransaction) {
        if (fragmentTransaction == null) {
            return;
        }
        Fragment fragment = this.mCurrentFragment;
        if (fragment != null) {
            fragmentTransaction.show(fragment);
            updateFragmentObject();
        }
        Fragment fragmentByTag = getFragmentByTag(str);
        this.mCurrentFragment = fragmentByTag;
        if (fragmentByTag != null) {
            if (fragmentByTag.isAdded()) {
                fragmentTransaction.show(this.mCurrentFragment);
                return;
            }
            try {
                fragmentTransaction.add(R.id.fragment_container, this.mCurrentFragment, str);
            } catch (Exception e) {
                LogUtil.d(TAG, " switchFragment() Exception e : " + e.getMessage());
                fragmentTransaction.show(this.mCurrentFragment);
            }
        }
    }

    private int checkSplashFlag() {
        this.mSplashFlag = 0;
        if (!NubiaCTAPermissionUtils.isCTAOK(this) && !isHostMode() && !CommonUtil.isAbroad()) {
            this.mSplashFlag |= 1;
        }
        if (isNeedStartAnim(getIntent(), this.mSavedState)) {
            this.mSplashFlag |= 2;
        }
        if (isFirstStartApp() && !isHostMode()) {
            this.mSplashFlag |= 4;
        }
        LogUtil.i(TAG, "--------- GameSpace - checkSplashScreen() flag : " + this.mSplashFlag);
        return this.mSplashFlag;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doResume() {
        LogUtil.v(getClass().getSimpleName(), "doResume()");
        if (this.mController == null || isHostMode()) {
            return;
        }
        this.mController.doResume();
    }

    private static void doTrack(String str) {
        if (CommonUtil.isAbroad()) {
            return;
        }
        NubiaTrackManager.getInstance().sendEventEveryDay("cn.nubia.gamelauncher", "game_card_size_setting", "option", str);
        Bundle bundle = new Bundle();
        bundle.putString("event_name", "gamespace_view_switching_status");
        bundle.putString("option", str);
        bundle.putInt("game_number", AppAddModel.getInstance().getCurrentGameListSize());
        bundle.putInt(AppDbSchema.AppTable.OneDayCols.REPORT_INTERVAL, 1);
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", bundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void exitHostMode(Context context) {
        if (context == null) {
            return;
        }
        try {
            DisplayManager displayManager = (DisplayManager) context.getSystemService("display");
            Method method = displayManager.getClass().getMethod("setCmdToDisplay", Integer.TYPE, Integer.TYPE, Integer.TYPE, Bundle.class);
            method.setAccessible(true);
            method.invoke(displayManager, 5, -1, 0, null);
            LogUtil.d(TAG, "exitHostMode() end");
        } catch (IllegalAccessException e) {
            LogUtil.d(TAG, "exitHostMode() IllegalAccessException : " + e.getMessage());
        } catch (NoSuchMethodException e2) {
            LogUtil.d(TAG, "exitHostMode() NoSuchMethodException : " + e2.getMessage());
        } catch (InvocationTargetException e3) {
            LogUtil.d(TAG, "exitHostMode() InvocationTargetException : " + e3.getMessage());
        }
    }

    private void fragmentChanged() {
        Controller.getInstance().switchTag(this.mCurrentTag);
        String str = this.mCurrentTag;
        str.hashCode();
        switch (str) {
            case "Splash":
            case "RedMagicPlanet":
                setOneMoreThingVisible(8);
                break;
            case "GameLobby":
            case "Handheld":
                setOneMoreThingVisible(0);
                break;
        }
    }

    private Fragment getFragmentByTag(String str) {
        LogUtil.d(TAG, " getFragmentByTag() " + str);
        str.hashCode();
        switch (str) {
            case "Splash":
                if (this.mSplash == null) {
                    this.mSplash = new SplashFragment();
                }
                Bundle bundle = new Bundle();
                bundle.putInt(SplashFragment.SPLASH_FLAG, this.mSplashFlag);
                this.mSplash.setArguments(bundle);
                this.mSplash.setDismissCallback(new GameSpaceActivity$$ExternalSyntheticLambda4(this));
                LogUtil.d(TAG, " getFragmentByTag(" + this.mSplashFlag + ") and get() is : " + bundle.getInt(SplashFragment.SPLASH_FLAG));
                return this.mSplash;
            case "GameLobby":
                if (this.mGameLobby == null) {
                    this.mGameLobby = getLobbyFragment();
                }
                return this.mGameLobby;
            case "Handheld":
                if (this.mHandheldFragment == null) {
                    this.mHandheldFragment = new HandheldFragment();
                }
                return this.mHandheldFragment;
            case "RedMagicPlanet":
                if (this.mRedMagicPlanet == null) {
                    this.mRedMagicPlanet = new RedMagicPlanetFragment();
                }
                return this.mRedMagicPlanet;
            default:
                return null;
        }
    }

    private String getFragmentTag() {
        LogUtil.i(TAG, "--------- GameSpace - getFragmentTag() mCurrentTag : " + this.mCurrentTag);
        if (isReCreate(this.mSavedState)) {
            readSavedTag(this.mSavedState);
        }
        String str = this.mCurrentTag;
        String str2 = TAG_SPLASH;
        if (!TAG_SPLASH.equals(str)) {
            return this.mCurrentTag;
        }
        int checkSplashFlag = checkSplashFlag();
        this.mSplashFlag = checkSplashFlag;
        if (checkSplashFlag == 0) {
            str2 = isHandleConnected() ? TAG_HAND_HELD : TAG_GAME_LOBBY;
        }
        this.mCurrentTag = str2;
        return str2;
    }

    private Fragment getLobbyFragment() {
        return isHostMode() ? new HostModeGameLobbyFragment() : new GameLobbyFragment();
    }

    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (fragmentTransaction == null) {
            return;
        }
        Fragment fragment = this.mGameLobby;
        if (fragment != null) {
            fragmentTransaction.hide(fragment);
        }
        HandheldFragment handheldFragment = this.mHandheldFragment;
        if (handheldFragment != null) {
            fragmentTransaction.hide(handheldFragment);
        }
        SplashFragment splashFragment = this.mSplash;
        if (splashFragment != null) {
            fragmentTransaction.remove(splashFragment);
        }
        RedMagicPlanetFragment redMagicPlanetFragment = this.mRedMagicPlanet;
        if (redMagicPlanetFragment != null) {
            fragmentTransaction.hide(redMagicPlanetFragment);
        }
    }

    private void initBGM() {
        BgmHelper.getInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initNubiaTrackManager() {
        int i = Settings.System.getInt(getContentResolver(), "fan_state_of_manual", 1);
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "gamespace_redmagic_time_status", "switch_on", Settings.System.getInt(getContentResolver(), GameKeysConstant.SETTING_REDMAGIC_TIME_SWITCH_KEY, -1) == 1 ? "开" : "关");
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "gamespace_cooling_fan_switch", "switch_on", i != 1 ? "关" : "开");
    }

    private void initOther() {
        LogUtil.i(TAG, "--------- GameSpace - initOther()");
        initPermission();
        if (isHostMode()) {
            return;
        }
        GameStateController gameStateController = this.mController;
        if (gameStateController != null) {
            gameStateController.doResume();
        }
        if (isHostMode()) {
            return;
        }
        BgmHelper.getInstance().playBgm(TtmlNode.START);
    }

    private void initPermission() {
        if (ConstantVariable.HAS_PERMISSION || !NubiaCTAPermissionUtils.isCTAOK(this)) {
            return;
        }
        ConstantVariable.HAS_PERMISSION = true;
        SharedPreferences.Editor edit = getSharedPreferences("data", 0).edit();
        edit.putBoolean("has_permission", true);
        edit.apply();
    }

    private void initSound() {
        this.mSwitchSound = new SoundPoolHelper(R.raw.switch_game);
    }

    private boolean isFirstStartApp() {
        return SharedPreferencesUtil.getInstance(this).isFirstStartApp();
    }

    private boolean isHandleConnected() {
        if (isHostMode()) {
            return false;
        }
        return cn.nubia.common.util.CommonUtil.isHandleConnected();
    }

    private boolean isLaunchFromGameKey(Intent intent) {
        if (intent == null) {
            return false;
        }
        return intent.getBooleanExtra("nubia.intent.extra.FROM_GAME_KEY", false);
    }

    private boolean isNeedStartAnim(Intent intent, Bundle bundle) {
        if (isHostMode() || ActivityManager.isUserAMonkey()) {
            return false;
        }
        if ((!isLaunchFromGameKey(intent) && GameSpaceConfig.supportGameKey()) || isReCreate(bundle)) {
            return false;
        }
        boolean z = 1 == Settings.Global.getInt(getContentResolver(), GameKeysConstant.DB_GAME_SPACE_START_ANIM, 1);
        LogUtil.d(getClass().getSimpleName(), " isNeedStartAnim() showStartAnim = " + z);
        return z;
    }

    private boolean isReCreate(Bundle bundle) {
        if (bundle == null) {
            return false;
        }
        LogUtil.d(TAG, "--------- isReCreate()");
        return bundle.getBoolean("reCreate");
    }

    private void loadCommonViewForLobbyAndBase(final String str) {
        if (isHostMode() || TAG_SPLASH.equals(str)) {
            return;
        }
        if (isHostMode()) {
            onWallpaperLoadEnd();
            return;
        }
        loadWallpaper();
        loadGameCtrl();
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.postDelayed(new Runnable() { // from class: cn.nubia.gamelauncher.activity.GameSpaceActivity.1
                @Override // java.lang.Runnable
                public void run() {
                    if (GameSpaceActivity.this.mController == null || TextUtils.isEmpty(str)) {
                        return;
                    }
                    GameSpaceActivity.this.mController.updateTabView(str);
                }
            }, 100L);
        }
    }

    private void loadGameCtrl() {
        if (this.mController != null || isHostMode()) {
            return;
        }
        this.mController = new GameStateController(this);
        LogUtil.i(TAG, "--------- GameSpace - loadGameCtrl()");
        this.mController.initGameSpaceSwitchView(this, ((ViewStub) findViewById(Controller.getInstance().isPureMode() ? R.id.ctrl_pure_stub : R.id.ctrl_stub)).inflate());
        this.mController.registerObserver();
        this.mController.setSwitchCallback(new Runnable() { // from class: cn.nubia.gamelauncher.activity.GameSpaceActivity$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                GameSpaceActivity.this.switchToNormal();
            }
        }, new Runnable() { // from class: cn.nubia.gamelauncher.activity.GameSpaceActivity$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                GameSpaceActivity.this.switchToHandheld();
            }
        });
    }

    private void loadWallpaper() {
        LogUtil.i(TAG, " GameSpace ----- loadWallpaper()");
        if (isDestroyed()) {
            return;
        }
        if (this.mWallpaper == null) {
            Wallpaper wallpaper = (Wallpaper) findViewById(R.id.wallpaper);
            this.mWallpaper = wallpaper;
            wallpaper.load();
        }
        onWallpaperLoadEnd();
    }

    private void notifyHandheldModeChanged(boolean z) {
        cn.nubia.common.util.CommonUtil.notifyHandheldModeChanged(z);
    }

    private void onBackPressedGameSpace() {
        if (this.mAlertDialog == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this, 2131952382);
            View inflate = LayoutInflater.from(this).inflate(R.layout.dialog_text_exit, (ViewGroup) null);
            if (isHostMode()) {
                ((TextView) inflate.findViewById(R.id.exit_desc)).setText(R.string.host_mode_exit_message);
            }
            inflate.findViewById(R.id.is_add_shortcut).setVisibility(4);
            builder.setView(inflate);
            builder.setTitle(R.string.identify_dialog_title);
            builder.setPositiveButton(isHostMode() ? R.string.host_mode_exit : R.string.exit_ok, new DialogInterface.OnClickListener() { // from class: cn.nubia.gamelauncher.activity.GameSpaceActivity.4
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    LogUtil.i(GameSpaceActivity.TAG, "GameSpace - onBackPressedGameSpace() - click to exit !");
                    dialogInterface.dismiss();
                    if (GameSpaceActivity.this.mAlertDialog != null) {
                        GameSpaceActivity.this.mAlertDialog = null;
                    }
                    GameSpaceActivity.this.finish();
                    Iterator<ActivityManager.AppTask> it = ((ActivityManager) GameSpaceActivity.this.getSystemService(GameFeatureService.ACTION_CONTROL_PANEL_EXTRA_ACTIVITY)).getAppTasks().iterator();
                    while (it.hasNext()) {
                        it.next().finishAndRemoveTask();
                    }
                    GameSpaceActivity gameSpaceActivity = GameSpaceActivity.this;
                    gameSpaceActivity.exitHostMode(gameSpaceActivity);
                }
            }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: cn.nubia.gamelauncher.activity.GameSpaceActivity.3
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    if (GameSpaceActivity.this.mAlertDialog != null) {
                        GameSpaceActivity.this.mAlertDialog = null;
                    }
                }
            });
            this.mAlertDialog = builder.create();
        }
        if (this.mAlertDialog.isShowing()) {
            return;
        }
        this.mAlertDialog.show();
        this.mAlertDialog.getWindow().setWindowAnimations(2131952381);
    }

    private void onWallpaperLoadEnd() {
        LogUtil.i(TAG, " GameSpace ----- onWallpaperLoadEnd()");
        Looper.myQueue().addIdleHandler(new OnceIdleHandle());
        this.mHandler.postDelayed(new Runnable() { // from class: cn.nubia.gamelauncher.activity.GameSpaceActivity$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                GameSpaceActivity.this.setCleanUp();
            }
        }, 300L);
        startGetDialogNotice();
        initOther();
    }

    private void readSavedTag(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        this.mCurrentTag = bundle.getString("tag");
    }

    private void resetUpgrade() {
        UpgradeManager.getInstance().addWakeExternDeviceRunnable(new Runnable() { // from class: cn.nubia.gamelauncher.activity.GameSpaceActivity$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                GameSpaceActivity.this.wakeExternDevice();
            }
        });
        UpgradeManager.getInstance().cancelExit();
    }

    private void restoreFragment(FragmentManager fragmentManager, String str) {
        if (fragmentManager == null || str == null) {
            return;
        }
        Fragment findFragmentByTag = fragmentManager.findFragmentByTag(str);
        this.mCurrentFragment = findFragmentByTag;
        this.mCurrentTag = str;
        if (findFragmentByTag == null) {
        }
        str.hashCode();
        switch (str) {
            case "Splash":
                this.mSplash = (SplashFragment) this.mCurrentFragment;
                break;
            case "GameLobby":
                this.mGameLobby = this.mCurrentFragment;
                break;
            case "Handheld":
                this.mHandheldFragment = (HandheldFragment) this.mCurrentFragment;
                break;
            case "RedMagicPlanet":
                this.mRedMagicPlanet = (RedMagicPlanetFragment) this.mCurrentFragment;
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCleanUp() {
        if (!isLaunchFromGameKey(getIntent()) || isReCreate(this.mSavedState)) {
            return;
        }
        LogUtil.d(getClass().getSimpleName(), "setCleanUp()");
        cleanup();
    }

    private void setFlashEndCallback(Fragment fragment) {
        if (fragment == null || !(fragment instanceof SplashFragment)) {
            return;
        }
        ((SplashFragment) fragment).setDismissCallback(new GameSpaceActivity$$ExternalSyntheticLambda4(this));
    }

    private void setLobbyCallback(Fragment fragment) {
        if (fragment == null || !(fragment instanceof GameLobbyFragment)) {
            return;
        }
    }

    private void setLottieCallback() {
        HandheldFragment handheldFragment = this.mHandheldFragment;
        if (handheldFragment == null || this.mController == null) {
            return;
        }
        handheldFragment.setLottieCallback(new Runnable() { // from class: cn.nubia.gamelauncher.activity.GameSpaceActivity$$ExternalSyntheticLambda11
            @Override // java.lang.Runnable
            public final void run() {
                GameSpaceActivity.this.m225x6d9da991();
            }
        }, new Runnable() { // from class: cn.nubia.gamelauncher.activity.GameSpaceActivity$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                GameSpaceActivity.this.m226x5f474fb0();
            }
        });
    }

    private void setOneMoreThingVisible(int i) {
        GameStateController gameStateController = this.mController;
        if (gameStateController == null) {
            return;
        }
        gameStateController.setOneMoreThingVisible(i);
    }

    private void setupBackPressedDispatcher() {
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) { // from class: cn.nubia.gamelauncher.activity.GameSpaceActivity.5
            @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
                GameSpaceActivity.this.onBackPressed();
            }
        });
    }

    private void showToastIfNeed() {
        if (cn.nubia.common.util.CommonUtil.isHandleDeviceConnected()) {
            return;
        }
        ToastUtil.showNormalToast(getResources().getString(R.string.handheld_toast));
    }

    private void startLobbyDetailGuide() {
        detailGuideEnd();
    }

    private void stopRedMagicHighLightPreview(String str) {
        if (RedMagicVideoPlayerManager.instance().getCurrentRedMagicVideoPlayer() != null) {
            RedMagicVideoPlayerManager.instance().releaseRedMagicVideoPlayer();
        }
        if (!TextUtils.equals(TAG_RED_MAGIC_PLANET, str) || this.mRedMagicPlanet == null) {
            return;
        }
        cn.nubia.gamelauncher.redmagicplanet.util.LogUtil.d(TAG, "stopRedMagicHighLightPreview: loadVideoView");
        this.mRedMagicPlanet.checkVideoAndResourceLibUpdate();
    }

    private void switchFragment(String str) {
        try {
            LogUtil.d(TAG, " switchFragment() tag : " + str);
            loadCommonViewForLobbyAndBase(str);
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
            restoreFragment(supportFragmentManager, str);
            hideAllFragment(beginTransaction);
            addFragment(str, beginTransaction);
            switchWallpaperIfNeed(this.mCurrentFragment);
            setFlashEndCallback(this.mCurrentFragment);
            setLobbyCallback(this.mCurrentFragment);
            stopRedMagicHighLightPreview(str);
            beginTransaction.commitAllowingStateLoss();
            showHandheldView(str);
            fragmentChanged();
            if (TAG_HAND_HELD.equals(str)) {
                setLottieCallback();
            }
        } catch (Exception e) {
            LogUtil.e(TAG, "switchFragment() An error occurred while switching fragments : " + e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void switchToHandheld() {
        switchFragment(TAG_HAND_HELD);
        showToastIfNeed();
        notifyHandheldModeChanged(true);
        WallpaperManager.getInstance().switchToWallpaper();
        AppAddModel.getInstance().setCurrentMode(2);
    }

    private void switchWallpaperIfNeed(Fragment fragment) {
        if (fragment == null || (fragment instanceof SplashFragment) || (fragment instanceof GameLobbyFragment)) {
            return;
        }
        if ((fragment instanceof HandheldFragment) || (fragment instanceof RedMagicPlanetFragment)) {
            WallpaperManager.getInstance().switchToWallpaper();
        } else {
            Log.d("wallpaper", "Lobby -- switchWallpaperIfNeed()");
            WallpaperManager.getInstance().switchToWallpaper();
        }
    }

    private void updateFragmentObject() {
        if (this.mCurrentFragment != null && this.mGameLobby == null && TAG_GAME_LOBBY.equals(this.mCurrentTag)) {
            Fragment fragment = this.mCurrentFragment;
            if (fragment instanceof GameLobbyFragment) {
                this.mGameLobby = fragment;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void wakeExternDevice() {
        LogUtil.i("RPoint", "wakeExternDevice()");
        UpgradeManager.getInstance().addExternDeviceCallback(new Runnable() { // from class: cn.nubia.gamelauncher.activity.GameSpaceActivity$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                GameSpaceActivity.this.showRedPoint();
            }
        }, new Runnable() { // from class: cn.nubia.gamelauncher.activity.GameSpaceActivity$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() {
                GameSpaceActivity.this.hideRedPoint();
            }
        });
        try {
            Bundle call = getApplicationContext().getContentResolver().call(Uri.parse("content://cn.nubia.externdevice.UpgradeProvider"), "isNeedPrompt", "", new Bundle());
            LogUtil.i("RPoint", "call() -> isNeedPrompt() result : " + (call != null ? Boolean.valueOf(call.getBoolean("is_need")) : "false"));
            if (call == null) {
                return;
            }
            updateRedPointVisble(call.getBoolean("is_need"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void backClick(View view) {
        if (this.mHandheldFragment == null || !isHandheld()) {
            return;
        }
        this.mHandheldFragment.back();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [cn.nubia.gamelauncher.activity.GameSpaceActivity$2] */
    public void cleanup() {
        new AsyncTask<Void, Void, Void>() { // from class: cn.nubia.gamelauncher.activity.GameSpaceActivity.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... voidArr) {
                GameSpaceActivity.this.getContentResolver().notifyChange(Settings.Global.getUriFor(GameSpaceActivity.SETTING_LAUNCHER_RESUME), null);
                return null;
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null);
    }

    public void detailGuideEnd() {
        switchFragment(TAG_GAME_LOBBY);
        cleanup();
    }

    public void dismissSplash() {
        LogUtil.i(TAG, "--------- GameSpace - dismissSplash()");
        if (isDestroyed()) {
            return;
        }
        if ((this.mSplashFlag & 4) != 0) {
            notifyGuideViewGone();
        } else {
            switchFragment(isHandleConnected() ? TAG_HAND_HELD : TAG_GAME_LOBBY);
            cleanup();
        }
    }

    @Override // androidx.core.app.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        HandheldFragment handheldFragment;
        if (isHandheld() && (handheldFragment = this.mHandheldFragment) != null && handheldFragment.onDispatchKeyEvent(keyEvent)) {
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            LogUtil.i("ky", "action_down touch_X = " + motionEvent.getX() + "  touch_Y = " + motionEvent.getY());
            GameStateController gameStateController = this.mController;
            if (gameStateController != null && gameStateController.isConsumptionTouch(motionEvent)) {
                return true;
            }
        } else if (action == 1) {
            LogUtil.i("ky", "action_up touch_X = " + motionEvent.getX() + "  touch_Y = " + motionEvent.getY());
        } else if (action == 3) {
            LogUtil.i("ky", "action_cancel touch_X = " + motionEvent.getX() + "  touch_Y = " + motionEvent.getY());
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        LogUtil.d(TAG, "finish() -> " + Log.getStackTraceString(new Throwable()));
    }

    public void hideRedPoint() {
        LogUtil.i("RPoint", "GS showRedPoint()");
        updateRedPointVisble(false);
    }

    public boolean isHandheld() {
        String str = this.mCurrentTag;
        return str != null && str.equals(TAG_HAND_HELD);
    }

    public boolean isSplash() {
        String str = this.mCurrentTag;
        return str != null && str.equals(TAG_SPLASH);
    }

    /* renamed from: lambda$setLottieCallback$2$cn-nubia-gamelauncher-activity-GameSpaceActivity, reason: not valid java name */
    /* synthetic */ void m225x6d9da991() {
        this.mController.setLottieVisible();
    }

    /* renamed from: lambda$setLottieCallback$3$cn-nubia-gamelauncher-activity-GameSpaceActivity, reason: not valid java name */
    /* synthetic */ void m226x5f474fb0() {
        this.mController.setLottieInvisible();
    }

    public void notifyGuideViewGone() {
        startLobbyDetailGuide();
        SharedPreferencesUtil.getInstance(this).setFirstStartValue();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (GameSpaceConfig.supportGameKey()) {
            return;
        }
        onBackPressedGameSpace();
    }

    @Override // cn.nubia.gamelauncher.activity.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.i(TAG, " GameSpace ----- onCreate() sw : " + getResources().getConfiguration().smallestScreenWidthDp);
        this.mSavedState = bundle;
        setupBackPressedDispatcher();
        preUpdateGameList();
        GameKeyObserver.getInstance(this).addCallback(this);
        NetWhitelistHelper.getNetWhitelist();
        initBGM();
        initSound();
        resetUpgrade();
        setContentView(R.layout.game_space);
        LobbySoundPoolHelper.getInstance();
        switchFragment(getFragmentTag());
        LiveAtmosphereManager.getInstance().doTraversalDirectoryIfNeed();
        AppAddModel.getInstance().sendGameCount();
        PureObserver pureObserver = new PureObserver(this.mHandler);
        this.mPureObserver = pureObserver;
        pureObserver.register();
    }

    @Override // cn.nubia.gamelauncher.activity.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.i(TAG, " onDestroy()");
        this.mSwitchSound.release();
        notifyHandheldModeChanged(false);
        this.mHandler.removeCallbacksAndMessages(null);
        GameKeyObserver.getInstance(this).removeCallback(this);
        LobbySoundPoolHelper.getInstance().release();
        NubiaTrackManager.getInstance().unbindServiceInvoked();
        GameCountTrack.getInstance().unbindArkService();
        Controller.getInstance().clearSelectedChangedListener();
        this.mPureObserver.unregister();
        this.mPureObserver = null;
        Wallpaper wallpaper = this.mWallpaper;
        if (wallpaper != null) {
            wallpaper.exit();
        }
        GameStateController gameStateController = this.mController;
        if (gameStateController != null) {
            gameStateController.exit();
            this.mController = null;
        }
        if (Util.isProcessIdle()) {
            UpgradeManager.getInstance().exitIfIdle();
        }
    }

    @Override // cn.nubia.neostore.api.callback.ICallback
    public void onError(ErrorMsg errorMsg) {
        LogUtil.d(getClass().getSimpleName(), "-- onError() errCode = " + errorMsg.errCode + ", errMsg = " + errorMsg.errMsg);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        HandheldFragment handheldFragment;
        HandheldFragment handheldFragment2;
        if (i != 4) {
            if (isHandheld() && (handheldFragment = this.mHandheldFragment) != null && handheldFragment.doKeyDown(i)) {
                return true;
            }
            return super.onKeyDown(i, keyEvent);
        }
        if (isHandheld() && (handheldFragment2 = this.mHandheldFragment) != null && handheldFragment2.back()) {
            return true;
        }
        if (isSplash() && this.mSplash != null && Util.isSwitchGameKeyToOtherFunctions()) {
            return true;
        }
        if (RedMagicVideoPlayerManager.instance().onBackPressed()) {
            LogUtil.d(TAG, "onKeyDown: Back to exit RedMagic video full-screen playback");
            return true;
        }
        if (!GameSpaceConfig.supportGameKey()) {
            onBackPressedGameSpace();
            return false;
        }
        GameStateController gameStateController = this.mController;
        if (gameStateController == null || !gameStateController.supportExitButton()) {
            Toast.makeText(this, R.string.turn_off_game_button_redmagic, 0).show();
        } else {
            Toast.makeText(this, R.string.turn_off_game_button, 0).show();
        }
        return true;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        LogUtil.d(getClass().getSimpleName(), " onNewIntent() isLaunchFromGameKey = " + isLaunchFromGameKey(intent));
        super.onNewIntent(intent);
        if (intent.getStringExtra("mode") == null || this.mCurrentFragment == null) {
            return;
        }
        switchToNormal();
        if (this.mCurrentFragment instanceof RedMagicPlanetFragment) {
            switchFragment(TAG_GAME_LOBBY);
        }
        Fragment fragment = this.mCurrentFragment;
        if (fragment instanceof GameLobbyFragment) {
            ((GameLobbyFragment) fragment).onNewIntent(intent);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        LogUtil.i(TAG, " onPause()");
        Controller.getInstance().setSpaceResumed(false);
        BgmHelper.getInstance().pauseBgm();
        WallpaperManager.getInstance().clearAtmosphereUrl();
        LiveAtmosphereManager.getInstance().resetTraversalFlag();
        this.mHandler.postDelayed(new Runnable() { // from class: cn.nubia.gamelauncher.activity.GameSpaceActivity$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                AppUsageStatsHelper.getInstance().updateAppUsageStat();
            }
        }, 300L);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    @Override // cn.nubia.gamelauncher.activity.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        LogUtil.i(TAG, " GameSpace ----- onResume()");
        Util.showCurrentVersion();
        Controller.getInstance().setSpaceResumed(true);
        sendReceiver();
        this.mHandler.post(new Runnable() { // from class: cn.nubia.gamelauncher.activity.GameSpaceActivity$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                GameSpaceActivity.this.doResume();
            }
        });
        if (isHostMode() || this.mCurrentTag == TAG_SPLASH) {
            return;
        }
        BgmHelper.getInstance().resumeBgm();
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("reCreate", true);
        bundle.putString("tag", this.mCurrentTag);
        LogUtil.d(TAG, "--------- onSaveInstanceState()");
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        LogUtil.i(TAG, " onStop()");
        super.onStop();
    }

    @Override // cn.nubia.neostore.api.callback.ICallback
    public void onSuccess(NubiaGameNotice nubiaGameNotice) {
        LogUtil.d(getClass().getSimpleName(), "-- onSuccess() notice = " + nubiaGameNotice);
        if (nubiaGameNotice == null) {
            LogUtil.d(getClass().getSimpleName(), "onSuccess() but notice is null!");
            return;
        }
        if (nubiaGameNotice.id == SharedPreferencesUtil.getInstance(this).getLastGameNoticeId()) {
            LogUtil.d(getClass().getSimpleName(), "onSuccess() but has been read");
            return;
        }
        long j = nubiaGameNotice.startTime;
        long j2 = nubiaGameNotice.endTime;
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis < j || currentTimeMillis > j2) {
            LogUtil.d(getClass().getSimpleName(), "onSuccess()  but not in valid time start = " + j + ", end = " + j2 + ", current = " + currentTimeMillis);
            return;
        }
        LogUtil.d(getClass().getSimpleName(), "onSuccess() -> startDialogNotice()");
        if (!Util.isTencentAppStore()) {
            NeoHelper.initIfNeed();
            NeoHelper.startDialogNotice(this);
        }
        SharedPreferencesUtil.getInstance(this).setGameNoticeId(nubiaGameNotice.id);
    }

    public void playTabSound() {
        SoundPoolHelper soundPoolHelper = this.mSwitchSound;
        if (soundPoolHelper != null) {
            soundPoolHelper.playSync();
        }
    }

    public void preUpdateGameList() {
        LogUtil.i("Full", " preUpdateGameList()");
        AppUsageStatsHelper.getInstance().preUpdateData(new Runnable() { // from class: cn.nubia.gamelauncher.activity.GameSpaceActivity$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                AppAddModel.getInstance().updateGameList();
            }
        });
    }

    public void realSwitchToRedMagicPlanet() {
        LogUtil.d(TAG, " realSwitchToRedMagicPlanet() to red magic planet");
        switchFragment(TAG_RED_MAGIC_PLANET);
        if (CommonUtil.isInternalVersion()) {
            return;
        }
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "redmagic_menu_click");
    }

    public void sendReceiver() {
        Intent intent = new Intent();
        intent.setAction("cn.nubia.action.GO_GAME");
        sendBroadcast(intent);
    }

    public void showHandheldView(String str) {
        boolean equals = TAG_HAND_HELD.equals(str);
        notifyHandheldModeChanged(equals);
        View findViewById = findViewById(R.id.handheld_tips);
        if (findViewById != null) {
            findViewById.setVisibility(equals ? 0 : 8);
        }
        GameStateController gameStateController = this.mController;
        if (gameStateController != null) {
            gameStateController.setTabVisibility(equals);
        }
        if (equals) {
            AppAddModel.getInstance().setSelected(null);
            ImageCache.getInstance().remove(Atmosphere.TYPE_CURRENT);
        }
    }

    public void showRedPoint() {
        LogUtil.i("RPoint", "GS showRedPoint()");
        updateRedPointVisble(true);
    }

    public void startGetDialogNotice() {
        if (Util.isTencentAppStore() || isHostMode()) {
            return;
        }
        NeoHelper.initIfNeed();
        NeoHelper.getDialogNotice(this, this);
    }

    public void switchGuideEnd() {
        try {
            realSwitchToRedMagicPlanet();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToGameLobby(View view) {
        LogUtil.d(TAG, " switchToGameLobby() to game lobby");
        switchFragment(TAG_GAME_LOBBY);
        playTabSound();
        VibratorHelper.getInstance().vibrateSync();
        if (CommonUtil.isInternalVersion()) {
            return;
        }
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "game_lobby_menu_click");
    }

    public void switchToNormal() {
        switchFragment(TAG_GAME_LOBBY);
        notifyHandheldModeChanged(false);
        AppAddModel.getInstance().setCurrentMode(!Controller.getInstance().isFullMode() ? 1 : 0);
    }

    public void switchToRegMagicPlanet(View view) {
        LogUtil.d(TAG, " switchToRegMagicPlanet() to red magic planet");
        playTabSound();
        VibratorHelper.getInstance().vibrateSync();
        realSwitchToRedMagicPlanet();
    }

    public void updateRedPointVisble(boolean z) {
        LogUtil.i("RPoint", "GS updateRedPointVisble(" + z + ")");
        if (this.mController == null || isHostMode()) {
            return;
        }
        this.mController.updateRedPointVisble(z);
    }
}
