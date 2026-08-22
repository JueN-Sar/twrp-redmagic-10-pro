package cn.nubia.gamelauncher.gamecontrolpanel;

import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.gamecontrolpanel.GameControlDialogCtrl;
import cn.nubia.gamelauncher.gamecontrolpanel.GameStrengthenIndicatorView;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.AnimationUtil;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.ControlPanelFeatureHelper;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.LogUtil;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.Utils;
import cn.nubia.gamelauncher.util.Util;

/* loaded from: classes.dex */
public class GameControlDialog extends Dialog implements View.OnClickListener, TgkProcessListener, AnimationUtil.DialogAnimationEndCallBack, GameControlDialogCtrl.UpdateShortcutIconListener {
    private static final String DB_GAME_MODE_KEYS = "nubia_db_game_keys";
    private static final String TAG = "GameControlDialog";
    public static boolean mDialogIsShowing = false;
    private int firstInter;
    private ImageView icon;
    private boolean isInGameLauncher;
    private int mClickCount;
    private Context mContext;
    private String mCurrentActivity;
    private String mCurrentPackageName;
    private ContentObserver mGameKeysModeObserver;
    protected IGameStrengthSelectedListener mGameStrengthSelectedListener;
    private final Handler mHandler;
    private boolean mLastIsPortrait;
    private int oldClickPosition;
    private PackageManager packageManager;
    private ContentObserver pipPkgObserver;
    private View rootView;
    private TextView title;
    private GameAdjustOperationView vGameAdjustOperation;
    private GameFunctionAllocationView vGameFunctionAllocation;
    private GameGyroscopeSensitivityView vGameGyroscopeSensitivity;
    private GamePlugView vGamePlugin;
    private ViewFlipper vGameStrengthCtrl;
    private GameStrengthenIndicatorView vGameStrengthenIndicator;
    private GameStrengthenPerformanceView vGameStrengthenPerformance;
    private GameStrengthenScreenShowView vGameStrengthenScreenShow;
    private GameStrengthenVoiceView vGameStrengthenVoice;
    private GameNetSettingsView vNetSettings;
    private ResourceSettings vResourceSettings;
    private SnapdragonAdrenoGpuView vSnapdragonAdrenoGpu;
    private SpreadtrumGpuView vSpreadtrumGpuView;

    public interface ISetViewAnimation {
        void animationSelf(boolean z);
    }

    public GameControlDialog(Context context, int i) {
        super(context, i);
        this.oldClickPosition = 0;
        this.firstInter = 0;
        this.mClickCount = 0;
        this.mHandler = new Handler();
        initView();
        initListener();
    }

    public GameControlDialog(Context context, String str, String str2) {
        super(context, R.style.DualScreenMapDialog);
        this.oldClickPosition = 0;
        this.firstInter = 0;
        this.mClickCount = 0;
        this.mHandler = new Handler();
        this.mCurrentPackageName = str;
        this.mCurrentActivity = str2;
        this.packageManager = context.getApplicationContext().getPackageManager();
        Utils.updateDensity(context);
        GameControlOrientationManager.getInstance().init(context);
        this.mLastIsPortrait = GameControlOrientationManager.getInstance().isPortrait();
        initView();
        initListener();
        this.pipPkgObserver = new ContentObserver(new Handler()) { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameControlDialog.1
            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                super.onChange(z);
                String string = Settings.Global.getString(GameControlDialog.this.mContext.getContentResolver(), "pip_pkg");
                if (string == null || !string.contains(GameControlDialog.this.mCurrentPackageName)) {
                    return;
                }
                GameControlDialog.this.dismiss();
            }
        };
        this.mGameKeysModeObserver = new ContentObserver(new Handler()) { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameControlDialog.2
            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                int i = Settings.Global.getInt(GameControlDialog.this.getContext().getContentResolver(), "nubia_db_game_keys", 0);
                LogUtil.i(GameControlDialog.TAG, "***selfChange***: " + i);
                if (i == 0) {
                    GameControlDialog.this.dismiss();
                }
            }
        };
        this.mContext = context;
        context.getContentResolver().registerContentObserver(Settings.Global.getUriFor("pip_pkg"), false, this.pipPkgObserver);
        this.mContext.getContentResolver().registerContentObserver(Settings.Global.getUriFor("nubia_db_game_keys"), true, this.mGameKeysModeObserver);
    }

    private void checkSupportItem() {
        this.vGameStrengthCtrl.removeAllViews();
        String gameControlpanelMenu = ControlPanelFeatureHelper.getGameControlpanelMenu();
        if (TextUtils.isEmpty(gameControlpanelMenu)) {
            if (!Utils.isRedMagicPad(getContext().getApplicationContext())) {
                this.vGameStrengthCtrl.addView(this.vGameAdjustOperation);
            }
            this.vGameStrengthCtrl.addView(this.vGameStrengthenPerformance);
            if (Utils.isSupportSnapdragonAdrenoGpu(getContext().getApplicationContext()) || gameControlpanelMenu.contains(ControlPanelFeatureHelper.MenuHelper.GpuSettings.toString())) {
                if (!Utils.isSprdPlatform()) {
                    this.vGameStrengthCtrl.addView(this.vSnapdragonAdrenoGpu);
                } else if (Utils.isSupportGpu(this.mCurrentPackageName)) {
                    this.vGameStrengthCtrl.addView(this.vSpreadtrumGpuView);
                }
            }
            this.vGameStrengthCtrl.addView(this.vGameStrengthenScreenShow);
            if (!Utils.isInternalVersion()) {
                this.vGameStrengthCtrl.addView(this.vGameStrengthenVoice);
            }
            if (!Util.isZte()) {
                this.vGameStrengthCtrl.addView(this.vNetSettings);
            }
            this.vGameStrengthCtrl.addView(this.vGameFunctionAllocation);
            if (!Util.isZte()) {
                this.vGameStrengthCtrl.addView(this.vResourceSettings);
            }
            if (!Util.isNubiaAppStore() && !Utils.isInternalVersion()) {
                this.vGameStrengthCtrl.addView(this.vGamePlugin);
            }
        } else {
            if (gameControlpanelMenu.contains(ControlPanelFeatureHelper.MenuHelper.AdjustOperation.toString()) || ControlPanelFeatureHelper.isLddTpInterfaceSupported()) {
                this.vGameStrengthCtrl.addView(this.vGameAdjustOperation);
            }
            if (gameControlpanelMenu.contains(ControlPanelFeatureHelper.MenuHelper.PerformanceStrengthen.toString())) {
                this.vGameStrengthCtrl.addView(this.vGameStrengthenPerformance);
            }
            if (Utils.isSupportSnapdragonAdrenoGpu(getContext().getApplicationContext()) || gameControlpanelMenu.contains(ControlPanelFeatureHelper.MenuHelper.GpuSettings.toString())) {
                if (!Utils.isSprdPlatform()) {
                    this.vGameStrengthCtrl.addView(this.vSnapdragonAdrenoGpu);
                } else if (Utils.isSupportGpu(this.mCurrentPackageName)) {
                    this.vGameStrengthCtrl.addView(this.vSpreadtrumGpuView);
                }
            }
            if (gameControlpanelMenu.contains(ControlPanelFeatureHelper.MenuHelper.ScreenShowStrengthen.toString())) {
                this.vGameStrengthCtrl.addView(this.vGameStrengthenScreenShow);
            }
            if (gameControlpanelMenu.contains(ControlPanelFeatureHelper.MenuHelper.VoiceStrengthen.toString())) {
                this.vGameStrengthCtrl.addView(this.vGameStrengthenVoice);
            }
            if (gameControlpanelMenu.contains(ControlPanelFeatureHelper.MenuHelper.NetSettings.toString())) {
                this.vGameStrengthCtrl.addView(this.vNetSettings);
            }
            if (gameControlpanelMenu.contains(ControlPanelFeatureHelper.MenuHelper.FunctionConfiguration.toString())) {
                this.vGameStrengthCtrl.addView(this.vGameFunctionAllocation);
            }
            if (Utils.supportResourceSettings(gameControlpanelMenu)) {
                this.vGameStrengthCtrl.addView(this.vResourceSettings);
            }
            if (gameControlpanelMenu.contains(ControlPanelFeatureHelper.MenuHelper.PluginSettings.toString())) {
                this.vGameStrengthCtrl.addView(this.vGamePlugin);
            }
        }
        LogUtil.d(TAG, " checkSupportItem childCount = " + this.vGameStrengthCtrl.getChildCount());
    }

    private int dp2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    private void initListener() {
        this.vGameStrengthenIndicator.setOnGameStrengthenTabClickListener(new GameStrengthenIndicatorView.OnGameStrengthenTabClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameControlDialog.3
            @Override // cn.nubia.gamelauncher.gamecontrolpanel.GameStrengthenIndicatorView.OnGameStrengthenTabClickListener
            public void onGameStrengthenTabClick(int i) {
                LogUtil.i(GameControlDialog.TAG, "onGameStrengthenTabClick: index = " + i + " firstInter = " + GameControlDialog.this.firstInter);
                GameControlDialog.this.vGameStrengthCtrl.setDisplayedChild(i);
                if (GameControlDialog.this.firstInter != 0) {
                    for (int i2 = 0; i2 < GameControlDialog.this.vGameStrengthCtrl.getChildCount(); i2++) {
                        View childAt = GameControlDialog.this.vGameStrengthCtrl.getChildAt(i2);
                        if (i2 == i) {
                            if ((childAt instanceof ISetViewAnimation) && i != GameControlDialog.this.oldClickPosition) {
                                ((ISetViewAnimation) GameControlDialog.this.vGameStrengthCtrl.getChildAt(i)).animationSelf(true);
                            }
                        } else if (childAt instanceof ISetViewAnimation) {
                            ((ISetViewAnimation) GameControlDialog.this.vGameStrengthCtrl.getChildAt(i2)).animationSelf(false);
                        }
                    }
                } else {
                    GameControlDialog.this.firstInter = 1;
                }
                GameControlDialog.this.oldClickPosition = i;
                if (GameControlDialog.this.mGameStrengthSelectedListener != null) {
                    GameControlDialog.this.mGameStrengthSelectedListener.onGameStrengthIndicatorSelected(i);
                }
            }
        });
    }

    private void initView() {
        View inflate = LayoutInflater.from(getContext()).inflate(GameControlOrientationManager.getInstance().isPortrait() ? R.layout.nubia_game_strengthen_view_home_port : R.layout.nubia_game_strengthen_view_home, (ViewGroup) null);
        this.rootView = inflate;
        this.icon = (ImageView) inflate.findViewById(R.id.nubia_game_strengthen_icon);
        this.title = (TextView) this.rootView.findViewById(R.id.nubia_game_strengthen_title);
        if (!Utils.isShortcut()) {
            this.icon.setImageDrawable(getAppIcon(this.mCurrentPackageName));
        }
        this.icon.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameControlDialog$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GameControlDialog.this.m250xc6d5504(view);
            }
        });
        this.title.setText((Utils.isShortcut() ? Utils.getShortCutLabel() : getAppLabel(this.mCurrentPackageName)) + "·" + getContext().getString(R.string.game_launcher_title_panel));
        this.isInGameLauncher = "gameLauncher".equals(this.mCurrentActivity);
        this.vGameStrengthCtrl = (ViewFlipper) this.rootView.findViewById(R.id.nubia_game_strengthen_controller);
        GameStrengthenIndicatorView gameStrengthenIndicatorView = (GameStrengthenIndicatorView) this.rootView.findViewById(R.id.nubia_game_strengthen_indicator);
        this.vGameStrengthenIndicator = gameStrengthenIndicatorView;
        gameStrengthenIndicatorView.initStartType(this.mCurrentPackageName);
        this.vGameAdjustOperation = (GameAdjustOperationView) this.rootView.findViewById(R.id.nubia_game_adjust_operation);
        this.vGameStrengthenPerformance = (GameStrengthenPerformanceView) this.rootView.findViewById(R.id.nubia_game_performance_strengthen);
        this.vGameStrengthenScreenShow = (GameStrengthenScreenShowView) this.rootView.findViewById(R.id.nubia_game_screen_show_strengthen);
        GameStrengthenVoiceView gameStrengthenVoiceView = (GameStrengthenVoiceView) this.rootView.findViewById(R.id.nubia_game_voice_strengthen);
        this.vGameStrengthenVoice = gameStrengthenVoiceView;
        gameStrengthenVoiceView.setCurrentPkgName(this.mCurrentPackageName);
        this.vGameStrengthenVoice.setIsInGameLauncher(this.isInGameLauncher);
        GameFunctionAllocationView gameFunctionAllocationView = (GameFunctionAllocationView) this.rootView.findViewById(R.id.nubia_game_function_strengthen);
        this.vGameFunctionAllocation = gameFunctionAllocationView;
        gameFunctionAllocationView.initStartType(this.mCurrentPackageName);
        this.vGameStrengthenPerformance.setPackageName(this.mCurrentPackageName);
        GamePlugView gamePlugView = (GamePlugView) this.rootView.findViewById(R.id.nubia_game_plug_strengthen);
        this.vGamePlugin = gamePlugView;
        gamePlugView.setPackageName(this.mCurrentPackageName);
        ResourceSettings resourceSettings = (ResourceSettings) this.rootView.findViewById(R.id.nubia_game_resource_strengthen);
        this.vResourceSettings = resourceSettings;
        resourceSettings.setTgkProcessListener(this);
        this.vResourceSettings.setPackageName(this.mCurrentPackageName);
        SnapdragonAdrenoGpuView snapdragonAdrenoGpuView = (SnapdragonAdrenoGpuView) this.rootView.findViewById(R.id.nubia_game_gpu_settings);
        this.vSnapdragonAdrenoGpu = snapdragonAdrenoGpuView;
        snapdragonAdrenoGpuView.initStartType(this.mCurrentPackageName);
        this.vSnapdragonAdrenoGpu.setParentDialog(this);
        if (Utils.isSupportGpu(this.mCurrentPackageName) && Utils.isSprdPlatform()) {
            SpreadtrumGpuView spreadtrumGpuView = (SpreadtrumGpuView) this.rootView.findViewById(R.id.nubia_game_sprd_gpu_settings);
            this.vSpreadtrumGpuView = spreadtrumGpuView;
            spreadtrumGpuView.initStartType(this.mCurrentPackageName);
            this.vSpreadtrumGpuView.setParentDialog(this);
        }
        GameNetSettingsView gameNetSettingsView = (GameNetSettingsView) this.rootView.findViewById(R.id.nubia_game_net_settings);
        this.vNetSettings = gameNetSettingsView;
        gameNetSettingsView.initStartType(this.mCurrentPackageName);
        checkSupportItem();
        this.rootView.findViewById(R.id.nubia_game_strengthen_close).setOnClickListener(this);
        this.rootView.findViewById(R.id.nubia_game_strengthen_close_dialog).setOnClickListener(this);
        setContentView(this.rootView);
        setAttributes();
    }

    private boolean isOnlySupportPerformance() {
        return Build.DEVICE.contains("NX627");
    }

    private void setAttributes() {
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.layoutInDisplayCutoutMode = 1;
        attributes.width = -1;
        attributes.height = -1;
        attributes.type = 2038;
        attributes.flags = 8;
        if (!GameControlOrientationManager.getInstance().isPortraitEnabled()) {
            attributes.screenOrientation = 6;
        }
        attributes.setTitle("GameSettingPanel");
        window.setAttributes(attributes);
        window.getDecorView().setSystemUiVisibility(5638);
        window.setBackgroundDrawable(new ColorDrawable(0));
    }

    public void applyState(int i, int i2, int i3, int i4, boolean z, TouchOperationBean touchOperationBean, boolean z2) {
        GameAdjustOperationView gameAdjustOperationView = this.vGameAdjustOperation;
        if (gameAdjustOperationView == null || this.vGameStrengthenIndicator == null || this.vGameStrengthenScreenShow == null || this.vGameStrengthenVoice == null || this.vGameStrengthenPerformance == null || this.vGameStrengthCtrl == null) {
            return;
        }
        gameAdjustOperationView.initData(touchOperationBean);
        this.vGameStrengthenIndicator.setGameStrengthenIndicator(i4);
        this.vGameStrengthenScreenShow.setGameStrengthenScreenShowMode(i2, z, z2);
        this.vGameStrengthenVoice.setGameStrengthenVoiceMode(i3);
        this.vGameStrengthenPerformance.recordRealPerformanceMode(i);
        this.vGameStrengthenPerformance.setGameStrengthenPerformanceMode(i);
        this.vGameStrengthCtrl.setDisplayedChild(i4);
        this.vSnapdragonAdrenoGpu.bindService();
        if (Utils.isSupportGpu(this.mCurrentPackageName) && Utils.isSprdPlatform()) {
            this.vSpreadtrumGpuView.bindService();
        }
    }

    public void closeDialog() {
        if (this.rootView == null || this.mContext == null || this.vGameStrengthenPerformance == null) {
            return;
        }
        LogUtil.i(TAG, " dismiss dialog start");
        Settings.Global.putInt(getContext().getContentResolver(), "game_mode_floating_window_show", 0);
        this.rootView.setBackground(this.mContext.getResources().getDrawable(android.R.color.transparent, null));
        this.vGameStrengthenPerformance.onDestroy();
        AnimationUtil.setMenuTranslationYTTB(this.rootView, this);
    }

    @Override // cn.nubia.gamelauncher.gamecontrolpanel.utils.AnimationUtil.DialogAnimationEndCallBack
    public void dismissDialogAnimationEnd() {
        LogUtil.i(TAG, "dismissDialogAnimationEnd ");
        dismiss();
    }

    public int getAdjustIndex() {
        GameStrengthenIndicatorView gameStrengthenIndicatorView = this.vGameStrengthenIndicator;
        if (gameStrengthenIndicatorView != null) {
            return gameStrengthenIndicatorView.getAdjustIndex();
        }
        return 0;
    }

    public Drawable getAppIcon(String str) {
        try {
            return this.packageManager.getApplicationInfo(str, 0).loadIcon(this.packageManager);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getAppLabel(String str) {
        try {
            return (String) this.packageManager.getApplicationInfo(str, 0).loadLabel(this.packageManager);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getFunctionIndex() {
        GameStrengthenIndicatorView gameStrengthenIndicatorView = this.vGameStrengthenIndicator;
        if (gameStrengthenIndicatorView != null) {
            return gameStrengthenIndicatorView.getFunctionIndex();
        }
        return 0;
    }

    public int getGpuSettingsIndex() {
        GameStrengthenIndicatorView gameStrengthenIndicatorView = this.vGameStrengthenIndicator;
        if (gameStrengthenIndicatorView != null) {
            return gameStrengthenIndicatorView.getGpuSettingsIndex();
        }
        return 0;
    }

    public int getNetSettingsIndex() {
        GameStrengthenIndicatorView gameStrengthenIndicatorView = this.vGameStrengthenIndicator;
        if (gameStrengthenIndicatorView != null) {
            return gameStrengthenIndicatorView.getNetSettingsIndex();
        }
        return 0;
    }

    public int getPerfModeIndex() {
        GameStrengthenIndicatorView gameStrengthenIndicatorView = this.vGameStrengthenIndicator;
        if (gameStrengthenIndicatorView != null) {
            return gameStrengthenIndicatorView.getPerfModeIndex();
        }
        return 0;
    }

    public int getResourceSettingsIndex() {
        GameStrengthenIndicatorView gameStrengthenIndicatorView = this.vGameStrengthenIndicator;
        if (gameStrengthenIndicatorView != null) {
            return gameStrengthenIndicatorView.getResourceSettingsIndex();
        }
        return 0;
    }

    public int getShowScreenIndex() {
        GameStrengthenIndicatorView gameStrengthenIndicatorView = this.vGameStrengthenIndicator;
        if (gameStrengthenIndicatorView != null) {
            return gameStrengthenIndicatorView.getShowScreenIndex();
        }
        return 0;
    }

    public int getStrengthVoiceIndex() {
        GameStrengthenIndicatorView gameStrengthenIndicatorView = this.vGameStrengthenIndicator;
        if (gameStrengthenIndicatorView != null) {
            return gameStrengthenIndicatorView.getStrengthVoiceIndex();
        }
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0147  */
    /* renamed from: lambda$initView$0$cn-nubia-gamelauncher-gamecontrolpanel-GameControlDialog, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    /* synthetic */ void m250xc6d5504(android.view.View r15) {
        /*
            Method dump skipped, instructions count: 408
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gamelauncher.gamecontrolpanel.GameControlDialog.m250xc6d5504(android.view.View):void");
    }

    /* renamed from: lambda$updateShortcutIcon$1$cn-nubia-gamelauncher-gamecontrolpanel-GameControlDialog, reason: not valid java name */
    /* synthetic */ void m251x66dfa310(Drawable drawable) {
        ImageView imageView = this.icon;
        if (imageView != null) {
            imageView.setImageDrawable(drawable);
            LogUtil.d(TAG, "updateShortcutIcon: drawable == " + drawable);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.nubia_game_strengthen_close || id == R.id.nubia_game_strengthen_close_dialog) {
            closeDialog();
        }
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // android.app.Dialog
    protected void onStop() {
        this.mContext.getContentResolver().unregisterContentObserver(this.pipPkgObserver);
        this.mContext.getContentResolver().unregisterContentObserver(this.mGameKeysModeObserver);
        super.onStop();
    }

    public void reloadLayoutForConfigurationChange() {
        if (isShowing()) {
            GameControlOrientationManager.getInstance().init(getContext());
            boolean isPortrait = GameControlOrientationManager.getInstance().isPortrait();
            if (isPortrait == this.mLastIsPortrait) {
                return;
            }
            this.mLastIsPortrait = isPortrait;
            Utils.updateDensity(getContext());
            initView();
            initListener();
            IGameStrengthSelectedListener iGameStrengthSelectedListener = this.mGameStrengthSelectedListener;
            if (iGameStrengthSelectedListener != null) {
                setGameStrengthSelectedListener(iGameStrengthSelectedListener);
            }
        }
    }

    public void setGameStrengthSelectedListener(IGameStrengthSelectedListener iGameStrengthSelectedListener) {
        this.mGameStrengthSelectedListener = iGameStrengthSelectedListener;
        this.vGameStrengthenPerformance.setGameStrengthSelectedListener(iGameStrengthSelectedListener);
        this.vGameStrengthenScreenShow.setGameStrengthSelectedListener(iGameStrengthSelectedListener);
        this.vGameStrengthenVoice.setGameStrengthSelectedListener(iGameStrengthSelectedListener);
        this.vGameAdjustOperation.setGameStrengthSelectedListener(iGameStrengthSelectedListener);
    }

    public void setScreenShowStrengthenEnable(boolean z) {
        GameStrengthenScreenShowView gameStrengthenScreenShowView = this.vGameStrengthenScreenShow;
        if (gameStrengthenScreenShowView != null) {
            gameStrengthenScreenShowView.setScreenShowStrengthenEnable(z);
        }
    }

    public void show(int i, int i2, int i3, int i4, boolean z, TouchOperationBean touchOperationBean, boolean z2) {
        Settings.Global.putInt(getContext().getContentResolver(), "game_mode_floating_window_show", 1);
        show();
        mDialogIsShowing = isShowing();
        applyState(i, i2, i3, i4, z, touchOperationBean, z2);
    }

    @Override // cn.nubia.gamelauncher.gamecontrolpanel.TgkProcessListener
    public void starTgkPickFile() {
        LogUtil.i(TAG, "starTgkPickFile");
        Settings.Global.putInt(getContext().getContentResolver(), "game_mode_floating_window_show", 0);
        dismiss();
    }

    @Override // cn.nubia.gamelauncher.gamecontrolpanel.TgkProcessListener
    public void starTgkShareFile() {
        LogUtil.i(TAG, "starTgkShareFile");
        Settings.Global.putInt(getContext().getContentResolver(), "game_mode_floating_window_show", 0);
        dismiss();
    }

    public void updateNetSettingsSwitch() {
        this.vNetSettings.setWifiSwitchEnable();
        this.vNetSettings.setDataSwitchEnable();
    }

    @Override // cn.nubia.gamelauncher.gamecontrolpanel.GameControlDialogCtrl.UpdateShortcutIconListener
    public void updateShortcutIcon(final Drawable drawable) {
        this.mHandler.post(new Runnable() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameControlDialog$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                GameControlDialog.this.m251x66dfa310(drawable);
            }
        });
    }
}
