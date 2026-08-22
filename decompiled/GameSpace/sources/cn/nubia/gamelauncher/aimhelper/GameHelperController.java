package cn.nubia.gamelauncher.aimhelper;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.os.AsyncTask;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import cn.nubia.gamecenter.settings.records.VideoListUtil;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.util.ToastUtil;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

/* loaded from: classes.dex */
public class GameHelperController extends OrientationListener {
    public static final String ACTION_CLOSE_AIM_HELPER = "cn.nubia.gamelauncher.action.delay_close_aim_helper_for_package";
    private static final int ALARM_REQUEST_CODE_DELAY_CLOSE_AIM_HELPER = 1;
    private static final long CLOSE_AIM_HELPER_DELAY_MS = 20000;
    private static final String KEY_APPMIRROR = "app_mirror_status";
    private static final String KEY_EXPAND = "ExpandingVisionSwitch";
    private static final String SETTINGS_KEY_ENABLE_PKGS = "aim_helper_open_pkgs";
    private static final String SETTING_KEY_FLOATING_WINDOW = "game_mode_floating_window_show";
    private static final String SETTING_KEY_GMS_PCF = "gms_permission_controller_fg";
    private static final String SETTING_KEY_PIP_PKG = "hasWindowReply";
    private static final String SETTING_KEY_QQ_ICON = "nubia_pip_icon_com.tencent.mobileqq";
    private static final String SETTING_KEY_WX_ICON = "nubia_pip_icon_com.tencent.mm";
    private static final String TAG = "GameHelperController";
    private static final int VIEW_EIGHT = 801;
    private static final int VIEW_FIVE = 501;
    private static final int VIEW_FORE = 401;
    private static final int VIEW_NINE = 900;
    private static final int VIEW_SEVEN = 701;
    private static final int VIEW_THREE = 301;
    private static final int VIEW_TWO = 201;
    private static int[] aimResArray = {R.mipmap.center1, R.mipmap.center2, R.mipmap.center3, R.mipmap.center4, R.mipmap.center5};
    private int appMirrorStatus;
    private boolean isExpandOpen;
    private boolean isOtherAppFloatingWindowShow;
    private boolean isShowingQuasiCenter;
    private ContentObserver mAppMirrorObserver;
    private Context mContext;
    private boolean mEnable;
    private ContentObserver mExpandObserver;
    private FloatingWindowValueChangeObserver mFloatingWindowValueChangeObserver;
    private String mForegroundPackage;
    private GmsPcfObserver mGmsObserver;
    private Handler mHandler;
    private Offset mOffset;
    private String mPipPkg;
    private PipPkgObserver mPipPkgObserver;
    private QuickHideFloatView mQuickHideFloatView;
    private AimSettingFloatingWindow mSettingFloatingWindow;
    private ImageView mView;
    private WindowManager windowManager;

    class FloatingWindowValueChangeObserver extends ContentObserver {
        public FloatingWindowValueChangeObserver(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            super.onChange(z);
            int i = Settings.Global.getInt(GameHelperController.this.mContext.getContentResolver(), GameHelperController.SETTING_KEY_FLOATING_WINDOW, 0);
            LogUtil.i(GameHelperController.TAG, "SettingValueChangeObserver onChange value = " + i);
            GameHelperController.this.isOtherAppFloatingWindowShow = i != 0;
            if (GameHelperController.this.isOtherAppFloatingWindowShow) {
                GameHelperController.this.refreshAimCenter();
            } else {
                LogUtil.d(GameHelperController.TAG, "delay refresh aim center");
                GameHelperController.this.mHandler.postDelayed(new Runnable() { // from class: cn.nubia.gamelauncher.aimhelper.GameHelperController.FloatingWindowValueChangeObserver.1
                    @Override // java.lang.Runnable
                    public void run() {
                        GameHelperController.this.refreshAimCenter();
                    }
                }, 100L);
            }
        }
    }

    class GmsPcfObserver extends ContentObserver {
        public GmsPcfObserver(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            super.onChange(z);
            LogUtil.i(GameHelperController.TAG, "onChange pcf=" + Settings.Global.getInt(GameHelperController.this.mContext.getContentResolver(), GameHelperController.SETTING_KEY_GMS_PCF, 0));
            GameHelperController gameHelperController = GameHelperController.this;
            gameHelperController.onActivityChange(ActivityUtils.getCurrentTopPkg(gameHelperController.mContext));
            GameHelperController.this.refreshAimCenter();
            if (GameHelperController.this.isHasGmsPermissionController()) {
                GameHelperController.this.hideChoice();
            }
        }
    }

    class Offset {
        int x;
        int y;

        Offset(int i, int i2) {
            this.x = i;
            this.y = i2;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Offset offset = (Offset) obj;
            return this.x == offset.x && this.y == offset.y;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(this.x), Integer.valueOf(this.y));
        }
    }

    class PipPkgObserver extends ContentObserver {
        public PipPkgObserver(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            super.onChange(z);
            String string = Settings.Secure.getString(GameHelperController.this.mContext.getContentResolver(), GameHelperController.SETTING_KEY_PIP_PKG);
            LogUtil.i(GameHelperController.TAG, "onChange pip_pkg=" + string);
            GameHelperController.this.mPipPkg = string;
            GameHelperController gameHelperController = GameHelperController.this;
            gameHelperController.onActivityChange(ActivityUtils.getCurrentTopPkg(gameHelperController.mContext));
            GameHelperController.this.refreshAimCenter();
            if (GameHelperController.this.isHasWindowReply()) {
                GameHelperController.this.hideChoice();
            }
        }
    }

    private class WriteSettingTask extends AsyncTask<Integer, Void, Void> {
        private WriteSettingTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Integer... numArr) {
            try {
                Settings.Global.putInt(GameHelperController.this.mContext.getContentResolver(), "gamelauncher_helper", numArr[0].intValue());
                LogUtil.d(GameHelperController.TAG, "write setting gamelauncher_helper " + numArr[0]);
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public GameHelperController(Context context) {
        super(context);
        this.isShowingQuasiCenter = false;
        this.mPipPkg = null;
        this.isOtherAppFloatingWindowShow = false;
        this.mHandler = new Handler();
        this.isExpandOpen = false;
        this.mExpandObserver = new ContentObserver(this.mHandler) { // from class: cn.nubia.gamelauncher.aimhelper.GameHelperController.1
            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                int i = Settings.Global.getInt(GameHelperController.this.mContext.getContentResolver(), "ExpandingVisionSwitch", 0);
                LogUtil.d(GameHelperController.TAG, "ExpandingVisionSwitch change value=" + i);
                GameHelperController.this.isExpandOpen = i == 1;
                GameHelperController.this.refreshAimCenter();
            }
        };
        this.appMirrorStatus = 0;
        this.mAppMirrorObserver = new ContentObserver(this.mHandler) { // from class: cn.nubia.gamelauncher.aimhelper.GameHelperController.2
            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                super.onChange(z);
                GameHelperController gameHelperController = GameHelperController.this;
                gameHelperController.appMirrorStatus = Settings.Global.getInt(gameHelperController.mContext.getContentResolver(), GameHelperController.KEY_APPMIRROR, 0);
                GameHelperController.this.refreshAimCenter();
                GameHelperController.this.refreshQuickHideFloatView();
            }
        };
        this.mOffset = new Offset(0, 0);
    }

    private boolean canDrawOverlays() {
        return Settings.canDrawOverlays(this.mContext);
    }

    private void changeSwitchState(boolean z) {
        new WriteSettingTask().execute(Integer.valueOf(z ? 1 : 0));
    }

    private void createQuasiCenter() {
        if (this.mView == null) {
            this.mView = new ImageView(this.mContext);
        }
    }

    private boolean enableOpenSetting() {
        boolean z = Settings.Global.getInt(this.mContext.getContentResolver(), SETTING_KEY_FLOATING_WINDOW, 0) != 0;
        if (z) {
            ToastUtil.showGamemodeToast(this.mContext.getString(R.string.please_close_current_settings_window));
            LogUtil.i(TAG, "enableOpenSetting false isOtherAppShowFloatingWindow");
            return false;
        }
        setTopApplication(ActivityUtils.getCurrentTopPkg(this.mContext));
        int rotation = this.windowManager.getDefaultDisplay().getRotation();
        if (rotation != 1 && rotation != 3) {
            ToastUtil.showGamemodeToast(this.mContext.getString(R.string.only_support_portrait_screen));
            LogUtil.i(TAG, "enableOpenSetting false is not portrait screen rotation=" + rotation);
            return false;
        }
        if (isHasWindowReply()) {
            ToastUtil.showGamemodeToast(this.mContext.getString(R.string.game_close_pip));
            LogUtil.i(TAG, "enableOpenSetting false mPipPkg=" + this.mPipPkg);
            return false;
        }
        if (isHasGmsPermissionController()) {
            LogUtil.i(TAG, "enableOpenSetting false isHasGmsPermissionController=");
            return false;
        }
        LogUtil.i(TAG, "enableOpenSetting isOtherAppShowFloatingWindow:" + z + " mForegroundPackage=" + this.mForegroundPackage + " mPipPkg=" + this.mPipPkg);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideChoice() {
        this.mSettingFloatingWindow.hide();
    }

    private void hideQuickHideDialog() {
        AimSettingFloatingWindow aimSettingFloatingWindow = this.mSettingFloatingWindow;
        if (aimSettingFloatingWindow != null) {
            aimSettingFloatingWindow.hideQuickHideDialog();
        }
    }

    private boolean isEnable(String str) {
        String string;
        return (TextUtils.isEmpty(str) || (string = Settings.Global.getString(this.mContext.getContentResolver(), SETTINGS_KEY_ENABLE_PKGS)) == null || !string.contains(str)) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isHasGmsPermissionController() {
        return Settings.Global.getInt(this.mContext.getContentResolver(), SETTING_KEY_GMS_PCF, 0) == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isHasWindowReply() {
        return "1".equals(Settings.Secure.getString(this.mContext.getContentResolver(), SETTING_KEY_PIP_PKG));
    }

    private WindowManager.LayoutParams makeQuasiCenterLayoutParams() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.type = 2010;
        layoutParams.format = 1;
        layoutParams.flags = 67108888;
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.gravity = 17;
        layoutParams.layoutInDisplayCutoutMode = 1;
        layoutParams.setTitle("ZteScreenshot_Window");
        return layoutParams;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onHideAimChange() {
        boolean isHideAim = AimConfigs.getInstance(this.mContext).isHideAim(getTopApplication());
        LogUtil.i(this, "click quickhidefloatview, isHideAim=" + isHideAim);
        AimConfigs.getInstance(this.mContext).setHideAim(getTopApplication(), !isHideAim);
        QuickHideFloatView quickHideFloatView = this.mQuickHideFloatView;
        if (quickHideFloatView != null) {
            quickHideFloatView.changeIcon(!isHideAim);
        }
        refreshAimCenter();
    }

    private void setControlCenterButtonState(boolean z) {
    }

    private void setTopApplication(String str) {
        this.mForegroundPackage = str;
        LogUtil.d(this, "setTopApplication " + str);
    }

    private void showChoice() {
        WindowManager windowManager;
        ImageView imageView;
        LogUtil.i(TAG, "showChoice " + canDrawOverlays());
        if (canDrawOverlays()) {
            if (this.isShowingQuasiCenter && (windowManager = this.windowManager) != null && (imageView = this.mView) != null) {
                this.isShowingQuasiCenter = false;
                windowManager.removeViewImmediate(imageView);
            }
            this.mSettingFloatingWindow.show();
            refreshAimCenter();
        }
    }

    private void updateAlpha(float f) {
        ImageView imageView = this.mView;
        if (imageView != null) {
            imageView.setAlpha(f);
        }
    }

    private void updateGlobalSettingValue(final boolean z, final String str) {
        AsyncTask.execute(new Runnable() { // from class: cn.nubia.gamelauncher.aimhelper.GameHelperController.5
            @Override // java.lang.Runnable
            public void run() {
                ContentResolver contentResolver = GameHelperController.this.mContext.getContentResolver();
                String string = Settings.Global.getString(contentResolver, GameHelperController.SETTINGS_KEY_ENABLE_PKGS);
                HashSet hashSet = new HashSet(TextUtils.isEmpty(string) ? Collections.emptyList() : Arrays.asList(string.split(",")));
                if (z) {
                    hashSet.add(str);
                } else {
                    hashSet.remove(str);
                }
                StringBuffer stringBuffer = new StringBuffer();
                Iterator it = hashSet.iterator();
                while (it.hasNext()) {
                    stringBuffer.append((String) it.next()).append(",");
                }
                String stringBuffer2 = stringBuffer.toString();
                if (stringBuffer2.endsWith(",")) {
                    stringBuffer2 = stringBuffer2.substring(0, stringBuffer2.length() - 1);
                }
                LogUtil.i(this, "updateGlobalSettingValue value=" + stringBuffer2);
                Settings.Global.putString(contentResolver, GameHelperController.SETTINGS_KEY_ENABLE_PKGS, stringBuffer2);
            }
        });
    }

    private void updateOffset(Offset offset) {
        if (offset != null) {
            this.mOffset = offset;
            if (!this.isShowingQuasiCenter || this.mView == null) {
                return;
            }
            WindowManager.LayoutParams makeQuasiCenterLayoutParams = makeQuasiCenterLayoutParams();
            makeQuasiCenterLayoutParams.x = offset.x;
            makeQuasiCenterLayoutParams.y = offset.y;
            this.windowManager.updateViewLayout(this.mView, makeQuasiCenterLayoutParams);
        }
    }

    AimSettingFloatingWindow getAimSettingFloatingWindow() {
        return this.mSettingFloatingWindow;
    }

    public String getTopApplication() {
        return this.mForegroundPackage;
    }

    public void handleDelayCloseAimHelperAlarm(String str) {
        LogUtil.d(TAG, "handleDelayCloseAimHelperAlarm close aim helper for package:" + str);
    }

    public void handleGameAssistSwitchChange(String str, boolean z) {
        int rotation;
        if (z && (rotation = this.windowManager.getDefaultDisplay().getRotation()) != 1 && rotation != 3) {
            ToastUtil.showGamemodeToast(this.mContext.getString(R.string.only_support_portrait_screen));
            LogUtil.i(TAG, "enableOpenSetting false is not portrait screen rotation=" + rotation);
            return;
        }
        this.mForegroundPackage = str;
        this.mEnable = z;
        refreshAimCenter();
        refreshQuickHideFloatView();
        updateGlobalSettingValue(z, str);
    }

    public void handleGameModeChange(boolean z) {
        if (z) {
            this.mHandler.postDelayed(new Runnable() { // from class: cn.nubia.gamelauncher.aimhelper.GameHelperController.6
                @Override // java.lang.Runnable
                public void run() {
                    String currentTopPkg = ActivityUtils.getCurrentTopPkg(GameHelperController.this.mContext);
                    if (currentTopPkg == null || currentTopPkg.equals("")) {
                        return;
                    }
                    GameHelperController.this.onActivityChange(currentTopPkg);
                }
            }, 100L);
        }
    }

    public void handleScreenOnOff(boolean z) {
        LogUtil.i(TAG, "handleScreenOnOff " + z);
        if (z) {
            this.mHandler.postDelayed(new Runnable() { // from class: cn.nubia.gamelauncher.aimhelper.GameHelperController.4
                @Override // java.lang.Runnable
                public void run() {
                }
            }, 100L);
        } else {
            AimService.kill(this.mContext);
        }
    }

    public void handleStart() {
        if (enableOpenSetting()) {
            if (!Settings.canDrawOverlays(this.mContext)) {
                LogUtil.w(TAG, "canDrawOverlays = false");
            }
            showChoice();
        }
    }

    public void handleStop(String str) {
        LogUtil.i(this, "handleStop disable plugin, packageName=" + str + " mForegroundPackage =" + this.mForegroundPackage);
        this.mEnable = false;
        hideChoice();
        hideQuickHideFloatView();
        hideQuickHideDialog();
        if (TextUtils.isEmpty(str)) {
            str = this.mForegroundPackage;
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        updateGlobalSettingValue(false, str);
    }

    public void handlerSceneValueChange(int i) {
        if (i == 900) {
            return;
        }
        if (i == VIEW_TWO || i == VIEW_THREE || i == VIEW_SEVEN || i == VIEW_FORE || i == VIEW_EIGHT || i == VIEW_FIVE) {
            updateAlpha(0.0f);
        } else {
            updateAlpha(1.0f);
        }
    }

    public void hideQuickHideFloatView() {
        QuickHideFloatView quickHideFloatView = this.mQuickHideFloatView;
        if (quickHideFloatView == null || !quickHideFloatView.isFloatViewVisible()) {
            return;
        }
        this.mQuickHideFloatView.hideFloatView();
    }

    public void onActivityChange(String str) {
        LogUtil.i(this, "onActivityChange " + this.mForegroundPackage + " --------> " + str);
        boolean equals = TextUtils.equals(this.mForegroundPackage, str);
        setTopApplication(str);
        this.mEnable = isEnable(str);
        refreshAimCenter();
        refreshQuickHideFloatView();
        if (equals) {
            hideQuickHideDialog();
            if (this.mSettingFloatingWindow.isShowing()) {
                this.mSettingFloatingWindow.hide();
            }
        }
        if (!VideoListUtil.isZteInternal()) {
            if (this.mEnable || this.mSettingFloatingWindow.isShowing()) {
                return;
            }
            AimService.kill(this.mContext);
            return;
        }
        if (this.mEnable || this.mSettingFloatingWindow.isShowing() || isHasGmsPermissionController()) {
            return;
        }
        AimService.kill(this.mContext);
    }

    public void onAppListloaded() {
    }

    public void onCreate(Context context) {
        this.mContext = context;
        this.windowManager = (WindowManager) context.getSystemService("window");
        ContentResolver contentResolver = this.mContext.getContentResolver();
        this.isOtherAppFloatingWindowShow = Settings.Global.getInt(contentResolver, SETTING_KEY_FLOATING_WINDOW, 0) != 0;
        this.mFloatingWindowValueChangeObserver = new FloatingWindowValueChangeObserver(this.mHandler);
        contentResolver.registerContentObserver(Settings.Global.getUriFor(SETTING_KEY_FLOATING_WINDOW), true, this.mFloatingWindowValueChangeObserver);
        String string = Settings.Secure.getString(this.mContext.getContentResolver(), SETTING_KEY_PIP_PKG);
        this.mPipPkg = string;
        this.mPipPkgObserver = new PipPkgObserver(this.mHandler);
        contentResolver.registerContentObserver(Settings.Secure.getUriFor(SETTING_KEY_PIP_PKG), true, this.mPipPkgObserver);
        this.mGmsObserver = new GmsPcfObserver(this.mHandler);
        contentResolver.registerContentObserver(Settings.Global.getUriFor(SETTING_KEY_GMS_PCF), true, this.mGmsObserver);
        contentResolver.registerContentObserver(Settings.Global.getUriFor("ExpandingVisionSwitch"), true, this.mExpandObserver);
        int i = Settings.Global.getInt(this.mContext.getContentResolver(), "ExpandingVisionSwitch", 0);
        this.isExpandOpen = i == 1;
        contentResolver.registerContentObserver(Settings.Global.getUriFor(KEY_APPMIRROR), true, this.mAppMirrorObserver);
        this.appMirrorStatus = Settings.Global.getInt(this.mContext.getContentResolver(), KEY_APPMIRROR, 0);
        setTopApplication(ActivityUtils.getCurrentTopPkg(this.mContext));
        this.mSettingFloatingWindow = new AimSettingFloatingWindow(context, this);
        QuickHideFloatView quickHideFloatView = new QuickHideFloatView(context, this);
        this.mQuickHideFloatView = quickHideFloatView;
        quickHideFloatView.createFloatView();
        this.mQuickHideFloatView.onFloatViewClick(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.aimhelper.GameHelperController.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GameHelperController.this.onHideAimChange();
            }
        });
        listenOrientation();
        LogUtil.i(TAG, "onCreate  pip_pkg=" + string + " ExpandingVisionSwitch=" + i + " appMirrorStatus=" + this.appMirrorStatus);
    }

    public void onDestroy() {
        this.mContext.getContentResolver().unregisterContentObserver(this.mFloatingWindowValueChangeObserver);
        this.mContext.getContentResolver().unregisterContentObserver(this.mPipPkgObserver);
        this.mContext.getContentResolver().unregisterContentObserver(this.mGmsObserver);
        this.mContext.getContentResolver().unregisterContentObserver(this.mExpandObserver);
        this.mContext.getContentResolver().unregisterContentObserver(this.mAppMirrorObserver);
        this.mHandler.removeCallbacksAndMessages(null);
        unListenOrientation();
        setTopApplication("");
        this.mEnable = false;
        hideChoice();
        refreshAimCenter();
        QuickHideFloatView quickHideFloatView = this.mQuickHideFloatView;
        if (quickHideFloatView != null) {
            quickHideFloatView.destroy();
        }
        LogUtil.i(TAG, "onDestroy");
    }

    @Override // cn.nubia.gamelauncher.aimhelper.OrientationListener
    void onOrientationChange() {
        refreshQuickHideFloatView();
        refreshAimCenter();
    }

    public void refreshAimCenter() {
        StringBuffer stringBuffer = new StringBuffer();
        AimConfigs aimConfigs = AimConfigs.getInstance(this.mContext);
        String topApplication = getTopApplication();
        boolean isQuickHide = aimConfigs.isQuickHide(topApplication);
        boolean isHideAim = aimConfigs.isHideAim(topApplication);
        boolean isHasWindowReply = isHasWindowReply();
        boolean z = (this.appMirrorStatus == 1 || this.isExpandOpen || isHasWindowReply || (!this.mEnable && !this.mSettingFloatingWindow.isShowing()) || ((this.isOtherAppFloatingWindowShow && !this.mSettingFloatingWindow.isShowing()) || ((isQuickHide && isHideAim) || !isLandscape()))) ? false : true;
        stringBuffer.append(String.format("refreshAimCenter needShow:%b hasPipWindow:%b isOn:%b isOtherAppFloatingWindowShow:%b foregroundPkg=%s mPipPkg=%s isExpandOpen=%b isLandscape=%b", Boolean.valueOf(z), Boolean.valueOf(isHasWindowReply), Boolean.valueOf(this.mEnable), Boolean.valueOf(this.isOtherAppFloatingWindowShow), this.mForegroundPackage, this.mPipPkg, Boolean.valueOf(this.isExpandOpen), Boolean.valueOf(isLandscape())));
        stringBuffer.append(" ").append("isQuickHide = " + isQuickHide + " ishideAim=" + isHideAim);
        LogUtil.i(TAG, stringBuffer.toString());
        if (!z) {
            LogUtil.d(TAG, "mView=" + this.mView + (this.mView != null ? " mView.isAttachedToWindow()=" + this.mView.isAttachedToWindow() : ""));
            ImageView imageView = this.mView;
            if (imageView != null && this.isShowingQuasiCenter) {
                try {
                    this.windowManager.removeView(imageView);
                    this.isShowingQuasiCenter = false;
                    LogUtil.i(TAG, "remove aim view from window " + this);
                } catch (Exception e) {
                    this.isShowingQuasiCenter = true;
                    LogUtil.i(TAG, "remove aim view from window error");
                    e.printStackTrace();
                }
            }
            setControlCenterButtonState(false);
            return;
        }
        int style = aimConfigs.getStyle(topApplication);
        int color = aimConfigs.getColor(topApplication);
        int size = aimConfigs.getSize(topApplication);
        int transparent = (aimConfigs.getTransparent(topApplication) * 2) + 55;
        if (size > 100 || size < 40) {
            size = 100;
        }
        float f = size / 100.0f;
        LogUtil.d(TAG, String.format("style=%d, color=%d, scale=%d", Integer.valueOf(style), Integer.valueOf(color), Integer.valueOf(size)));
        createQuasiCenter();
        this.mView.setImageResource(aimResArray[((style < 1 || style > 5) ? 1 : style) - 1]);
        this.mView.setColorFilter(color);
        this.mView.setAlpha(transparent);
        this.mView.setScaleX(f);
        this.mView.setScaleY(f);
        this.mView.setScaleType(ImageView.ScaleType.CENTER);
        if (this.isShowingQuasiCenter || !canDrawOverlays()) {
            return;
        }
        try {
            this.windowManager.addView(this.mView, makeQuasiCenterLayoutParams());
            this.isShowingQuasiCenter = true;
            setControlCenterButtonState(true);
            LogUtil.i(TAG, "add aim view to window " + this);
        } catch (Exception e2) {
            this.isShowingQuasiCenter = false;
            LogUtil.w(TAG, "add aim view to window error");
            e2.printStackTrace();
        }
    }

    public void refreshQuickHideFloatView() {
        boolean isQuickHide = AimConfigs.getInstance(this.mContext).isQuickHide(getTopApplication());
        LogUtil.i(TAG, "refreshQuickHideFloatView isQuickHide = " + isQuickHide + " mEnable=" + this.mEnable + " isLandscape=" + isLandscape());
        if (isQuickHide && this.mEnable && this.appMirrorStatus != 1 && isLandscape()) {
            showQuickHideFloatView();
        } else {
            hideQuickHideFloatView();
        }
    }

    public void showQuickHideFloatView() {
        if (this.mQuickHideFloatView != null) {
            LogUtil.d(TAG, "mQuickHideFloatView.isFloatViewVisible=" + this.mQuickHideFloatView.isFloatViewVisible());
            if (!this.mQuickHideFloatView.isFloatViewVisible()) {
                this.mQuickHideFloatView.showFloatView();
            } else {
                this.mQuickHideFloatView.changeIcon(AimConfigs.getInstance(this.mContext).isHideAim(getTopApplication()));
            }
        }
    }
}
