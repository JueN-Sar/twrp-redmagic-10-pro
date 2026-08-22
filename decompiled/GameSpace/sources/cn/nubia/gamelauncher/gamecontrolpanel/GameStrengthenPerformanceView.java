package cn.nubia.gamelauncher.gamecontrolpanel;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.gamecontrolpanel.GameControlDialog;
import cn.nubia.gamelauncher.gamecontrolpanel.GameStrengthenPerformanceView;
import cn.nubia.gamelauncher.gamecontrolpanel.performancetuning.CustomPerfProfileManager;
import cn.nubia.gamelauncher.gamecontrolpanel.performancetuning.adapter.AdapterItem;
import cn.nubia.gamelauncher.gamecontrolpanel.performancetuning.adapter.CustomPerfAdapter;
import cn.nubia.gamelauncher.gamecontrolpanel.performancetuning.ui.CustomPerfDialog;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.AnimationUtil;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.ControlPanelFeatureHelper;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.LogUtil;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.Utils;
import cn.nubia.gamelauncher.util.CommonUtil;
import cn.nubia.gamelauncher.util.NubiaTrackManager;
import cn.nubia.gamelauncher.util.ToastUtil;
import java.lang.ref.WeakReference;
import java.util.List;

/* loaded from: classes.dex */
public class GameStrengthenPerformanceView extends GameStrengthenBaseSelectedView implements GameControlDialog.ISetViewAnimation {
    private static final String DB_GAME_CHICKEN_MODE = "game_chicken_mode_switch";
    private static final String DB_GAME_PERFORMANCE_MODE_ALL_LIST = "NubiaperformanceMode";
    private static final int PERFORMANCE_MODE_HIGH = 2;
    private static final int PERFORMANCE_MODE_LOW = 0;
    private static final int PERFORMANCE_MODE_MIDDLE = 1;
    private static final int PERFORMANCE_MODE_SUPER = 3;
    private static final int SWITCH_CLOSED_STATUS = 0;
    private static final int SWITCH_OPENED_STATUS = 2;
    private static final int SWITCH_OPENED_STATUS_OLD = 1;
    private static final String TAG = "GameStrengthenPerformanceView";
    private final String DB_GAME_CHICKEN_VALUE;
    private boolean mBiaBlo;
    private View mBottomLayoutView;
    private int mCheckedId;
    private View mCustomMode;
    private CustomPerfAdapter mCustomPerfAdapter;
    private RecyclerView mCustomPerfRecyclerView;
    private LinearLayout mCustomPerformanceLayout;
    private ImageView mGameChickenModeSwitch;
    private final Handler mHandler;
    private boolean mIsGameChicken;
    private final LowPowerModeObserver mLowPowerModeObserver;
    private String mPackageName;
    private ImageView mPerformanceBg;
    private ConstraintLayout mPerformanceCircleLayout;
    private PerformanceCircleView mPerformanceCpuView;
    private PerformanceCircleView mPerformanceGpuView;
    private int mPerformanceMode;
    private RadioButton mRadioButton;
    private Dialog mSuperPerformanceDialog;
    private View mTopLayoutView;
    private View vGameChickenMode;
    private static final Boolean SUPPORT_CUSTOM_PERFORMANCE_MODE = ControlPanelFeatureHelper.getZteFeatureZperfCubeGpsettingEnabled();
    private static int mOriginalPerformanceMode = 0;

    /* JADX INFO: Access modifiers changed from: private */
    static class LowPowerModeObserver extends ContentObserver {
        private final Handler mHandler;
        private final WeakReference<GameStrengthenPerformanceView> performanceViewWeakReference;
        private final WeakReference<Context> reference;

        public LowPowerModeObserver(GameStrengthenPerformanceView gameStrengthenPerformanceView, Context context, Handler handler) {
            super(handler);
            this.mHandler = handler;
            this.reference = new WeakReference<>(context);
            this.performanceViewWeakReference = new WeakReference<>(gameStrengthenPerformanceView);
        }

        /* renamed from: lambda$onChange$0$cn-nubia-gamelauncher-gamecontrolpanel-GameStrengthenPerformanceView$LowPowerModeObserver, reason: not valid java name */
        /* synthetic */ void m273x6f3e6ffe() {
            this.performanceViewWeakReference.get().setGameStrengthenPerformanceMode(GameStrengthenPerformanceView.mOriginalPerformanceMode);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            long j;
            super.onChange(z, uri);
            if (this.reference.get() == null || this.performanceViewWeakReference.get() == null) {
                return;
            }
            LogUtil.i(GameStrengthenPerformanceView.TAG, " isPowerMode = " + Utils.isLowPowerMode(this.reference.get()));
            if (!Utils.isLowPowerMode(this.reference.get())) {
                LogUtil.i(GameStrengthenPerformanceView.TAG, " mIsGameChicken = " + this.performanceViewWeakReference.get().mIsGameChicken);
                if (this.performanceViewWeakReference.get().mIsGameChicken) {
                    this.performanceViewWeakReference.get().mIsGameChicken = false;
                    j = 250;
                    this.mHandler.postDelayed(new Runnable() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameStrengthenPerformanceView$LowPowerModeObserver$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            GameStrengthenPerformanceView.LowPowerModeObserver.this.m273x6f3e6ffe();
                        }
                    }, j);
                }
            }
            j = 0;
            this.mHandler.postDelayed(new Runnable() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameStrengthenPerformanceView$LowPowerModeObserver$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    GameStrengthenPerformanceView.LowPowerModeObserver.this.m273x6f3e6ffe();
                }
            }, j);
        }
    }

    public GameStrengthenPerformanceView(Context context) {
        this(context, null);
    }

    public GameStrengthenPerformanceView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GameStrengthenPerformanceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mCheckedId = -1;
        this.mBiaBlo = false;
        this.mPerformanceMode = 0;
        this.DB_GAME_CHICKEN_VALUE = "db_game_chicken_value";
        this.mIsGameChicken = false;
        this.mTopLayoutView = findViewById(R.id.strengthen_top_layout);
        this.mBottomLayoutView = findViewById(R.id.strengthen_bottom_layout);
        this.mPerformanceCpuView = (PerformanceCircleView) findViewById(R.id.performance_view_cpu);
        this.mPerformanceGpuView = (PerformanceCircleView) findViewById(R.id.performance_view_gpu);
        this.mPerformanceBg = (ImageView) findViewById(R.id.performance_bg);
        this.mCustomPerformanceLayout = (LinearLayout) findViewById(R.id.custom_performance_mode_layout);
        this.mPerformanceCircleLayout = (ConstraintLayout) findViewById(R.id.strengthen_top_layout);
        this.mCustomPerfRecyclerView = (RecyclerView) findViewById(R.id.custom_performance_mode_settings_recyclerView);
        this.mCustomMode = findViewById(R.id.nubia_game_strength_performance_custom);
        Handler handler = new Handler(context.getMainLooper());
        this.mHandler = handler;
        this.mLowPowerModeObserver = new LowPowerModeObserver(this, context, handler);
        initGameChickenModeView();
        showFlicker(this.vGameStrengthenGroup);
    }

    private void dismissSuperPerformanceConfirmDialog() {
        Dialog dialog = this.mSuperPerformanceDialog;
        if (dialog == null || !dialog.isShowing()) {
            return;
        }
        this.mSuperPerformanceDialog.dismiss();
    }

    private int dp2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    private boolean enableGameChicken(int i) {
        boolean z = false;
        if (Utils.isLowPowerMode(getContext())) {
            return false;
        }
        ContentResolver contentResolver = getContext().getContentResolver();
        if (!CommonUtil.isAndroidVersionAtLeastVanillaIceCream()) {
            int i2 = Settings.Global.getInt(contentResolver, DB_GAME_CHICKEN_MODE, 0);
            this.mBiaBlo = (i & 4) > 0;
            LogUtil.i(TAG, "enableGameChicken: mBiaBlo = " + this.mBiaBlo + " ;;chickenModeStatus = " + i2);
            return i2 != 0 || this.mBiaBlo;
        }
        String string = Settings.Global.getString(contentResolver, "db_game_chicken_value");
        if (!TextUtils.isEmpty(string) && string.contains(this.mPackageName)) {
            z = true;
        }
        this.mBiaBlo = z;
        LogUtil.i(TAG, "enableGameChicken: mBiaBlo = " + this.mBiaBlo + " ;;chicken_mode = " + string);
        return this.mBiaBlo;
    }

    private void exitCustomPerf() {
        if (!SUPPORT_CUSTOM_PERFORMANCE_MODE.booleanValue() || this.mCustomPerfAdapter == null) {
            return;
        }
        CustomPerfProfileManager.getInstance().existEditProfile(this.mCustomPerfAdapter.getItemList());
        CustomPerfDialog.getInstance().dismissDialog();
    }

    private void initCustomPerf() {
        if (SUPPORT_CUSTOM_PERFORMANCE_MODE.booleanValue()) {
            this.mCustomMode.setVisibility(0);
            CustomPerfProfileManager customPerfProfileManager = CustomPerfProfileManager.getInstance();
            int applyProfile = customPerfProfileManager.getApplyProfile(this.mPackageName);
            LogUtil.i(TAG, "initCustomPerf serial = " + applyProfile);
            List<AdapterItem> convert2AdapterItem = customPerfProfileManager.convert2AdapterItem(applyProfile);
            if (convert2AdapterItem.size() == 2) {
                customPerfProfileManager.applyProfile(1);
                convert2AdapterItem = customPerfProfileManager.convert2AdapterItem(1);
            }
            this.mCustomPerfAdapter = new CustomPerfAdapter(getContext(), convert2AdapterItem);
            this.mCustomPerfRecyclerView.setNestedScrollingEnabled(false);
            this.mCustomPerfRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
            this.mCustomPerfRecyclerView.setAdapter(this.mCustomPerfAdapter);
        }
    }

    private void initGameChickenModeView() {
        this.vGameChickenMode = findViewById(R.id.nubia_game_chicken_mode);
        this.mGameChickenModeSwitch = (ImageView) findViewById(R.id.nubia_game_chicken_mode_checkbox);
    }

    private boolean isSupportChickenMode() {
        return !Build.DEVICE.contains("NX651");
    }

    private void reportPerformanceSwitchUsed(int i) {
        String str = i != 0 ? i != 1 ? i != 2 ? i != 3 ? "" : "super" : "high" : "middle" : "low";
        Bundle bundle = new Bundle();
        bundle.putString("level", str);
        LogUtil.d(TAG, "  reportPerformanceSwitchUsed level = " + str + "  ;; event = game_enhance_performance_switch_used");
        bundle.putCharSequence("app_name ", Utils.getCurrentAppName());
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "game_enhance_performance_switch_used", bundle);
    }

    private void setSuperPerformanceDialogAttributes() {
        Window window = this.mSuperPerformanceDialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.height = -1;
        attributes.type = 2038;
        attributes.flags = 268435456;
        attributes.screenOrientation = 3;
        window.setAttributes(attributes);
        window.setGravity(80);
        window.getDecorView().setSystemUiVisibility(5638);
        window.setFlags(1024, 1024);
        window.setLayout(-1, -1);
        window.setBackgroundDrawable(new ColorDrawable(0));
    }

    private void setSuperPerformanceDialogContentView() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.nubia_game_strengthen_performance_super_confirm_dialog_view, (ViewGroup) null);
        if (!isSupportChickenMode()) {
            ((TextView) inflate.findViewById(R.id.nubia_game_strengthen_performance_super_title)).setText(getContext().getString(R.string.nubia_game_performance_super_dialog_title_no_chicken_mode));
        }
        inflate.findViewById(R.id.nubia_game_strengthen_performance_super_cancel).setOnClickListener(this);
        inflate.findViewById(R.id.nubia_game_strengthen_performance_super_ok).setOnClickListener(this);
        this.mSuperPerformanceDialog.setContentView(inflate);
    }

    private void showSuperPerformanceConfirmDialog() {
        if (this.mSuperPerformanceDialog == null) {
            this.mSuperPerformanceDialog = new Dialog(getContext(), R.style.DualScreenMapDialog);
            setSuperPerformanceDialogAttributes();
            setSuperPerformanceDialogContentView();
        }
        if (this.mSuperPerformanceDialog.isShowing()) {
            return;
        }
        this.mSuperPerformanceDialog.show();
    }

    @Override // cn.nubia.gamelauncher.gamecontrolpanel.GameControlDialog.ISetViewAnimation
    public void animationSelf(final boolean z) {
        new Handler().post(new Runnable() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameStrengthenPerformanceView.2
            @Override // java.lang.Runnable
            public void run() {
                if (GameStrengthenPerformanceView.this.mTopLayoutView == null || GameStrengthenPerformanceView.this.mBottomLayoutView == null) {
                    LogUtil.i(GameStrengthenPerformanceView.TAG, " mBottomLayoutView or mTopLayoutView is null ");
                    return;
                }
                if (!z) {
                    GameStrengthenPerformanceView.this.mTopLayoutView.setAlpha(0.0f);
                    GameStrengthenPerformanceView.this.mBottomLayoutView.setAlpha(0.0f);
                    GameStrengthenPerformanceView.this.mCustomPerformanceLayout.setAlpha(0.0f);
                } else {
                    AnimationUtil.setGpuTranslationY(GameStrengthenPerformanceView.this.mTopLayoutView);
                    AnimationUtil.setGcsRedItemAlpha(GameStrengthenPerformanceView.this.mTopLayoutView);
                    AnimationUtil.setDoublePxTranslationY(GameStrengthenPerformanceView.this.mBottomLayoutView);
                    AnimationUtil.setGcsRedItemAlpha(GameStrengthenPerformanceView.this.mBottomLayoutView);
                    AnimationUtil.setDoublePxTranslationY(GameStrengthenPerformanceView.this.mCustomPerformanceLayout);
                    AnimationUtil.setGcsRedItemAlpha(GameStrengthenPerformanceView.this.mCustomPerformanceLayout);
                }
            }
        });
    }

    @Override // cn.nubia.gamelauncher.gamecontrolpanel.GameStrengthenBaseSelectedView
    protected int getGameStrengthenDescId() {
        return R.id.nubia_game_performance_strengthen_desc;
    }

    @Override // cn.nubia.gamelauncher.gamecontrolpanel.GameStrengthenBaseSelectedView
    protected int getGameStrengthenGroupId() {
        return R.id.nubia_game_performance_group;
    }

    @Override // cn.nubia.gamelauncher.gamecontrolpanel.GameStrengthenBaseSelectedView
    protected int getGameStrengthenLayout() {
        return GameControlOrientationManager.getInstance().isPortrait() ? R.layout.gamecontrol_strengthen_view_performance_port : R.layout.gamecontrol_strengthen_view_performance;
    }

    @Override // cn.nubia.gamelauncher.gamecontrolpanel.GameStrengthenBaseSelectedView
    protected int getGameStrengthenType() {
        return 0;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getContext().getContentResolver().registerContentObserver(Settings.Global.getUriFor(Utils.LOW_POWER_MODE), false, this.mLowPowerModeObserver);
    }

    @Override // cn.nubia.gamelauncher.gamecontrolpanel.GameStrengthenBaseSelectedView, android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (Utils.isLowPowerMode(getContext())) {
            if (id != R.id.nubia_game_strength_performance_GPU) {
                ToastUtil.showGamemodeToast(getContext().getString(R.string.performancemode_is_lowpowermode_tip));
            }
        } else if (id == this.mCheckedId) {
            return;
        } else {
            super.onClick(view);
        }
        if (this.mCheckedId != id) {
            this.mCheckedId = id;
        }
    }

    public void onDestroy() {
        this.mPerformanceCpuView.stop();
        this.mPerformanceGpuView.stop();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getContext().getContentResolver().unregisterContentObserver(this.mLowPowerModeObserver);
        this.mCheckedId = -1;
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        dismissSuperPerformanceConfirmDialog();
        exitCustomPerf();
    }

    public void recordRealPerformanceMode(int i) {
        LogUtil.i(TAG, "recordRealPerformanceMode: mode = " + i);
        mOriginalPerformanceMode = i;
    }

    @Override // cn.nubia.gamelauncher.gamecontrolpanel.GameStrengthenBaseSelectedView
    protected void saveCurrentMode(int i) {
        mOriginalPerformanceMode = i;
    }

    public void setGameStrengthenPerformanceMode(int i) {
        this.mPerformanceMode = CommonUtil.isAndroidVersionAtLeastVanillaIceCream() ? i : i & 3;
        if (Utils.isLowPowerMode(getContext())) {
            this.mPerformanceMode = 1;
        }
        LogUtil.i(TAG, "setGameStrengthenPerformanceMode:" + i + " ;; mPerformanceMode == " + this.mPerformanceMode);
        this.mCheckedId = this.vGameStrengthenGroup.getChildAt(this.mPerformanceMode).getId();
        this.mPerformanceCpuView.start();
        this.mPerformanceGpuView.start();
        boolean enableGameChicken = enableGameChicken(i);
        if (!enableGameChicken) {
            updateGameStrength(this.mCheckedId);
        }
        this.vGameChickenMode.setVisibility(enableGameChicken ? 0 : 8);
        if (i == 4) {
            this.vGameStrengthenDesc.setVisibility(8);
        } else {
            this.vGameStrengthenDesc.setVisibility(enableGameChicken ? 8 : 0);
        }
        this.vGameStrengthenGroup.setVisibility(enableGameChicken ? 8 : 0);
        this.mCustomPerformanceLayout.setVisibility(enableGameChicken ? 8 : 0);
        if (enableGameChicken) {
            setSwitched(this.mGameChickenModeSwitch, enableGameChicken);
            this.mGameChickenModeSwitch.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameStrengthenPerformanceView.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    GameStrengthenPerformanceView.this.vGameChickenMode.setVisibility(8);
                    GameStrengthenPerformanceView.this.vGameStrengthenGroup.setVisibility(0);
                    GameStrengthenPerformanceView.this.vGameStrengthenDesc.setVisibility(0);
                    String string = Settings.Global.getString(GameStrengthenPerformanceView.this.getContext().getContentResolver(), "db_game_chicken_value");
                    String replace = string.replace(GameStrengthenPerformanceView.this.mPackageName + ",", "");
                    Log.i(GameStrengthenPerformanceView.TAG, "onClick: chicken_mode = " + string + " ;; result = " + replace);
                    Settings.Global.putString(GameStrengthenPerformanceView.this.getContext().getContentResolver(), "db_game_chicken_value", replace);
                    GameStrengthenPerformanceView gameStrengthenPerformanceView = GameStrengthenPerformanceView.this;
                    gameStrengthenPerformanceView.updateGameStrength(gameStrengthenPerformanceView.mCheckedId);
                }
            });
        }
        if (!enableGameChicken) {
            this.mPerformanceCpuView.setMode(this.mPerformanceMode);
            this.mPerformanceGpuView.setMode(this.mPerformanceMode);
        } else {
            this.mIsGameChicken = true;
            this.mPerformanceCpuView.setMode(4);
            this.mPerformanceGpuView.setMode(4);
            this.mPerformanceBg.setImageResource(R.drawable.infinite_bg);
        }
    }

    public void setPackageName(String str) {
        this.mPackageName = str;
        CustomPerfProfileManager.getInstance().setPackageName(this.mPackageName);
        initCustomPerf();
    }

    public void setSwitched(ImageView imageView, boolean z) {
        imageView.setImageResource(z ? R.drawable.function_toggle_on : R.drawable.function_toggle_off);
    }

    @Override // cn.nubia.gamelauncher.gamecontrolpanel.GameStrengthenBaseSelectedView
    protected void updateGameStrength(int i) {
        int i2;
        boolean z;
        super.updateGameStrength(i);
        int i3 = 4;
        if (i == R.id.nubia_game_strength_performance_auto) {
            LogUtil.i(TAG, " nubia_game_strength_performance_auto ");
            this.vGameStrengthenDesc.setText(getContext().getString(R.string.nubia_game_performance_mode_title0));
            this.mPerformanceBg.setImageResource(R.drawable.balance_bg);
            z = false;
            i2 = 0;
        } else {
            i2 = 1;
            if (i == R.id.nubia_game_strength_performance_GPU) {
                LogUtil.i(TAG, " nubia_game_strength_performance_GPU ");
                this.vGameStrengthenDesc.setText(getContext().getString(R.string.nubia_game_performance_mode_title1));
                this.mPerformanceCpuView.setMode(1);
                this.mPerformanceGpuView.setMode(1);
                this.mPerformanceBg.setImageResource(R.drawable.balance_bg);
            } else if (i == R.id.nubia_game_strength_performance_CPU) {
                LogUtil.i(TAG, " nubia_game_strength_performance_CPU ");
                this.vGameStrengthenDesc.setText(getContext().getString(R.string.nubia_game_performance_mode_title2));
                i2 = 2;
                this.mPerformanceCpuView.setMode(2);
                this.mPerformanceGpuView.setMode(2);
                this.mPerformanceBg.setImageResource(R.drawable.rise_bg);
            } else {
                int i4 = R.string.nubia_game_performance_mode_title3;
                if (i == R.id.nubia_game_strength_performance_super) {
                    LogUtil.i(TAG, " nubia_game_strength_performance_super ");
                    TextView textView = this.vGameStrengthenDesc;
                    Context context = getContext();
                    if (!isSupportChickenMode()) {
                        i4 = R.string.nubia_game_performance_mode_title3_no_chicken_mode;
                    }
                    textView.setText(context.getString(i4));
                    i2 = 3;
                    this.mPerformanceCpuView.setMode(3);
                    this.mPerformanceGpuView.setMode(3);
                    this.mPerformanceBg.setImageResource(R.drawable.beyond_bg);
                } else if (i == R.id.nubia_game_strength_performance_custom) {
                    LogUtil.i(TAG, " nubia_game_strength_performance_custom ");
                    TextView textView2 = this.vGameStrengthenDesc;
                    Context context2 = getContext();
                    if (!isSupportChickenMode()) {
                        i4 = R.string.nubia_game_performance_mode_title3_no_chicken_mode;
                    }
                    textView2.setText(context2.getString(i4));
                    this.mPerformanceCpuView.setMode(4);
                    this.mPerformanceGpuView.setMode(4);
                    z = true;
                    i2 = 4;
                } else {
                    i2 = -1;
                }
            }
            z = false;
        }
        reportPerformanceSwitchUsed(i2);
        if (z) {
            this.mPerformanceCircleLayout.setVisibility(8);
            this.mCustomPerformanceLayout.setVisibility(0);
            this.mCustomPerfRecyclerView.setVisibility(0);
            this.vGameChickenMode.setVisibility(8);
            this.vGameStrengthenDesc.setVisibility(8);
        } else {
            this.mPerformanceCircleLayout.setVisibility(0);
            this.mCustomPerformanceLayout.setVisibility(8);
            this.vGameChickenMode.setVisibility(8);
            this.vGameStrengthenDesc.setVisibility(0);
            this.mCustomPerfRecyclerView.setVisibility(8);
            if (enableGameChicken(i2)) {
                this.mPerformanceCpuView.setMode(4);
                this.mPerformanceGpuView.setMode(4);
                this.mPerformanceBg.setImageResource(R.drawable.infinite_bg);
                LogUtil.i(TAG, "updateGameStrength mode:" + i3);
            }
        }
        i3 = i2;
        LogUtil.i(TAG, "updateGameStrength mode:" + i3);
    }
}
