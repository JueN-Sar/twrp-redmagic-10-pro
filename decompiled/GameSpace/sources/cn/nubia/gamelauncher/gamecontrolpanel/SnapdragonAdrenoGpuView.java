package cn.nubia.gamelauncher.gamecontrolpanel;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import cn.nubia.common.app.AlertDialog;
import cn.nubia.common.config.GameSpaceConfig;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.gamecontrolpanel.GameControlDialog;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.AnimationUtil;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.ControlPanelFeatureHelper;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.LogUtil;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.Utils;
import cn.nubia.gamelauncher.util.NubiaTrackManager;
import cn.nubia.gpu.drivers.gpuprofile.IGpuCallback;
import cn.nubia.gpu.drivers.gpuprofile.IGpuProfileControl;
import cn.nubia.gpuupdate.common.GpuUpdateUtils;
import com.android.systemui.shared.recents.model.Task;
import com.zte.gameassist.ai.AIFlickerTips;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class SnapdragonAdrenoGpuView extends FrameLayout implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, GameControlDialog.ISetViewAnimation {
    private static final int ADRENO_GPU_C = 3;
    private static final int ADRENO_GPU_H = 2;
    private static final int ADRENO_GPU_P = 1;
    private static final int ADRENO_GPU_S = 0;
    private static final int COLOR_MENU_NORMAL = 2131099884;
    private static final int COLOR_MENU_SELECTED = 2131099886;
    private static final String TAG = "S-GPU";
    private ImageView adrAntialiasing;
    private ImageView adrAutoVrsSwitch;
    private RelativeLayout adrGpuAutoVrsSwitchRelativeLayout;
    private View adrGpuDialogImage;
    private ConstraintLayout adrGpuSettingImage;
    private ImageView adrHeterosexual;
    private ImageView adrTexturefilter;
    private TextView adrenoGpuCustomer;
    private TextView adrenoGpuHighqual;
    private TextView adrenoGpuPowersav;
    private TextView adrenoGpuStandard;
    private SeekBar antialiasingSeekBar;
    private ColorStateList colorStateListNormal;
    private ColorStateList colorStateListSelected;
    private int getGpuSettings;
    private SeekBar heterosexualSeekBar;
    private IGpuProfileControl iGpuProfileControl;
    private boolean isAdrAutoVrsSwitch;
    private int mAntialiasingValue;
    private volatile Context mContext;
    private String mCurrentPkg;
    private AlertDialog mDialog;
    private ExecutorService mExecutor;
    private GameControlDialog mGameControlDialog;
    private IGpuCallback mGpuCallback;
    private volatile LinearLayout mGpuUpdateLayout;
    private GpuUpdateUtils mGpuUpdateUtils;
    private volatile TextView mGpuUpdateVersion;
    private int mHeterosexualValue;
    private View mLayoutView;
    private final Object mLock;
    private View mMipmapLayout;
    private ImageView mMipmapLodHelper;
    private SeekBar mMipmapLodSeekBar;
    private int mMipmapLodValue;
    private View mModeSwitchLayout;
    private TextView mPromptText;
    private int mTexturefilteValue;
    private ServiceConnection serviceConnection;
    private LinearLayout snapdragonAdrenoGpuCustomLayout;
    private SeekBar texturefilteSeekBar;
    private static boolean mSupportMipmapLod = ControlPanelFeatureHelper.getZteFeatureSupportMipmapLod().booleanValue();
    private static boolean mSupportGPUUpdate = ControlPanelFeatureHelper.getZteFeatureSupportGPUUpdate().booleanValue();

    public SnapdragonAdrenoGpuView(Context context) {
        this(context, null);
    }

    public SnapdragonAdrenoGpuView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SnapdragonAdrenoGpuView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mDialog = null;
        this.serviceConnection = new ServiceConnection() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.SnapdragonAdrenoGpuView.1
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                SnapdragonAdrenoGpuView.this.iGpuProfileControl = IGpuProfileControl.Stub.asInterface(iBinder);
                LogUtil.i(SnapdragonAdrenoGpuView.TAG, "onServiceConnected");
                SnapdragonAdrenoGpuView.this.isChooseOptionsAdrenoGpu();
                SnapdragonAdrenoGpuView.this.initViewOptions();
                SnapdragonAdrenoGpuView.this.initAntialiasingLayout();
                SnapdragonAdrenoGpuView.this.initHeterosexualLayout();
                SnapdragonAdrenoGpuView.this.initTexturefilteLayout();
                SnapdragonAdrenoGpuView.this.initMipmapLodLayout();
                SnapdragonAdrenoGpuView.this.initAutoVrsLayout();
                SnapdragonAdrenoGpuView.this.initGPUVersion();
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
                LogUtil.i(SnapdragonAdrenoGpuView.TAG, "onServiceDisconnected");
                SnapdragonAdrenoGpuView.this.iGpuProfileControl = null;
            }
        };
        this.mLock = new Object();
        this.mGpuCallback = new IGpuCallback.Stub() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.SnapdragonAdrenoGpuView.7
            @Override // cn.nubia.gpu.drivers.gpuprofile.IGpuCallback
            public void onError(int i2, String str) throws RemoteException {
                LogUtil.i(SnapdragonAdrenoGpuView.TAG, "mGpuCallback onResult: " + str);
            }

            @Override // cn.nubia.gpu.drivers.gpuprofile.IGpuCallback
            public void onResult(final String str) throws RemoteException {
                LinearLayout linearLayout;
                LogUtil.i(SnapdragonAdrenoGpuView.TAG, "mGpuCallback onResult: " + str);
                synchronized (SnapdragonAdrenoGpuView.this.mLock) {
                    if (SnapdragonAdrenoGpuView.this.mContext != null) {
                        str = SnapdragonAdrenoGpuView.this.mContext.getString(R.string.gpu_upgrade_version_title) + str;
                    }
                    linearLayout = SnapdragonAdrenoGpuView.this.mGpuUpdateLayout;
                }
                if (linearLayout != null) {
                    synchronized (SnapdragonAdrenoGpuView.this.mLock) {
                        linearLayout.post(new Runnable() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.SnapdragonAdrenoGpuView.7.1
                            @Override // java.lang.Runnable
                            public void run() {
                                synchronized (SnapdragonAdrenoGpuView.this.mLock) {
                                    if (SnapdragonAdrenoGpuView.this.mGpuUpdateVersion != null) {
                                        SnapdragonAdrenoGpuView.this.mGpuUpdateVersion.setText(str);
                                    }
                                }
                            }
                        });
                    }
                }
            }
        };
        this.mContext = context;
    }

    private boolean checkGpuSettingsEntryNotNull() {
        try {
            if (this.iGpuProfileControl.getGpuSettingsEntry(this.mCurrentPkg) != null) {
                return true;
            }
            LogUtil.i(TAG, " checkGpuSettingsEntryNotNull GpuSettingsEntry is null ");
            return false;
        } catch (RemoteException e) {
            LogUtil.e(TAG, " checkGpuSettingsEntryNotNull error ", e);
            return false;
        }
    }

    private String constructAntialias() {
        if (this.getGpuSettings == 3) {
            int i = this.mAntialiasingValue;
            if (i == 1) {
                return "off";
            }
            if (i == 2) {
                return "2";
            }
            if (i == 3) {
                return "4";
            }
        }
        return null;
    }

    private String constructAutoVRS() {
        if (this.getGpuSettings == 3) {
            return this.isAdrAutoVrsSwitch ? "on" : "off";
        }
        return null;
    }

    private String constructHeterosexual() {
        if (this.getGpuSettings == 3) {
            int i = this.mHeterosexualValue;
            if (i == 1) {
                return "off";
            }
            if (i == 2) {
                return "2";
            }
            if (i == 3) {
                return "4";
            }
            if (i == 4) {
                return GameSpaceConfig.SUPPORT_PLAY_MOUSE;
            }
            if (i == 5) {
                return "16";
            }
        }
        return null;
    }

    private String constructMipMapLOD() {
        if (this.getGpuSettings == 3 && supportMipmapLod()) {
            return String.valueOf(this.mMipmapLodValue);
        }
        return null;
    }

    private String constructTextureFilter() {
        if (this.getGpuSettings == 3) {
            int i = this.mTexturefilteValue;
            if (i == 1) {
                return "speed";
            }
            if (i == 2) {
                return "norm";
            }
            if (i == 3) {
                return "quality";
            }
        }
        return null;
    }

    private Bundle contructReportBundle() {
        Bundle bundle = new Bundle();
        String currentPkgName = Utils.getCurrentPkgName();
        CharSequence currentAppName = Utils.getCurrentAppName();
        String gpuSettingsModel = getGpuSettingsModel();
        bundle.putCharSequence("app_name", currentAppName);
        bundle.putString("package_name", currentPkgName);
        bundle.putString("model", gpuSettingsModel);
        String constructAntialias = constructAntialias();
        String constructTextureFilter = constructTextureFilter();
        String constructHeterosexual = constructHeterosexual();
        String constructMipMapLOD = constructMipMapLOD();
        String constructAutoVRS = constructAutoVRS();
        bundle.putString("multi_sample", constructAntialias);
        bundle.putString("anisotropic", constructHeterosexual);
        bundle.putString("texture_quality", constructTextureFilter);
        bundle.putString("mipmap_LOD", constructMipMapLOD);
        bundle.putString("AutoVRS", constructAutoVRS);
        if (LogUtil.DEBUG) {
            StringBuilder sb = new StringBuilder("{app_name = ");
            sb.append(currentAppName).append(", package_name = ");
            sb.append(currentPkgName).append(", model = ");
            sb.append(gpuSettingsModel).append(", multi_sample = ");
            sb.append(constructAntialias).append(", anisotropic = ");
            sb.append(constructHeterosexual).append(", texture_quality = ");
            sb.append(constructTextureFilter).append(", mipmap_LOD = ");
            sb.append(constructMipMapLOD).append(", AutoVRS = ");
            sb.append(constructAutoVRS);
            sb.append("}");
            LogUtil.d(TAG, "report data: " + ((Object) sb));
        }
        return bundle;
    }

    private String getGameStrengthenValue(String str, String str2) {
        String string;
        if (!TextUtils.isEmpty(str) && (string = Settings.Global.getString(this.mContext.getContentResolver(), str2)) != null && string.indexOf(str) != -1) {
            for (String str3 : string.split(",")) {
                String trim = str3.trim();
                if (!trim.isEmpty() && trim.indexOf(str) != -1) {
                    return trim;
                }
            }
        }
        return null;
    }

    private String getGpuSettingsModel() {
        int i = this.getGpuSettings;
        if (i == 0) {
            return "norm";
        }
        if (i == 1) {
            return "save_power";
        }
        if (i == 2) {
            return "high_quality";
        }
        if (i != 3) {
            return null;
        }
        return "customize";
    }

    private View getShowFlickerView(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        str.hashCode();
        if (str.equals("gpu_settings_mode_switch_layout")) {
            return this.mModeSwitchLayout;
        }
        if (str.equals("gpu_update_layout")) {
            return this.mGpuUpdateLayout;
        }
        return null;
    }

    private int getSpecificGameStrengthenParam(String str, int i, int i2) {
        try {
            return Integer.parseInt(String.valueOf(str.charAt(i + i2)));
        } catch (StringIndexOutOfBoundsException | Exception unused) {
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initAntialiasingLayout() {
        SeekBar seekBar = (SeekBar) findViewById(R.id.seek_bar_adreno_a);
        this.antialiasingSeekBar = seekBar;
        seekBar.setOnSeekBarChangeListener(this);
        LogUtil.i(TAG, "initAntialiasingLayout: " + (this.iGpuProfileControl != null));
        if (this.iGpuProfileControl != null) {
            try {
                if (checkGpuSettingsEntryNotNull()) {
                    this.mAntialiasingValue = this.iGpuProfileControl.getGpuSettingsEntry(this.mCurrentPkg).getMaxSamples();
                }
                this.antialiasingSeekBar.setProgress(this.mAntialiasingValue);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initAutoVrsLayout() {
        this.adrAutoVrsSwitch = (ImageView) findViewById(R.id.auto_vrs_switch);
        boolean isAdrAutoVrsOpen = isAdrAutoVrsOpen();
        this.isAdrAutoVrsSwitch = isAdrAutoVrsOpen;
        setSwitched(this.adrAutoVrsSwitch, isAdrAutoVrsOpen);
        this.adrAutoVrsSwitch.setOnClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initGPUVersion() {
        try {
            if (this.iGpuProfileControl != null) {
                LogUtil.d(TAG, "performGetGPUInfoTask");
                this.iGpuProfileControl.performGetGPUInfoTask(this.mGpuCallback);
            } else {
                LogUtil.i(TAG, "initGPUVersion iGpuProfileControl is null");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
            LogUtil.e(TAG, "initGPUVersion error: " + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initHeterosexualLayout() {
        SeekBar seekBar = (SeekBar) findViewById(R.id.seek_bar_adreno_h);
        this.heterosexualSeekBar = seekBar;
        seekBar.setOnSeekBarChangeListener(this);
        LogUtil.i(TAG, "initHeterosexualLayout: " + (this.iGpuProfileControl != null));
        if (this.iGpuProfileControl != null) {
            try {
                if (checkGpuSettingsEntryNotNull()) {
                    this.mHeterosexualValue = this.iGpuProfileControl.getGpuSettingsEntry(this.mCurrentPkg).getTextureMaxAniso();
                }
                this.heterosexualSeekBar.setProgress(this.mHeterosexualValue);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initMipmapLodLayout() {
        SeekBar seekBar = (SeekBar) findViewById(R.id.mipmap_lod_seekBar);
        this.mMipmapLodSeekBar = seekBar;
        seekBar.setOnSeekBarChangeListener(this);
        LogUtil.i(TAG, "initMipmapLodLayout: " + (this.iGpuProfileControl != null));
        if (this.iGpuProfileControl != null) {
            try {
                if (checkGpuSettingsEntryNotNull()) {
                    this.mMipmapLodValue = this.iGpuProfileControl.getGpuSettingsEntry(this.mCurrentPkg).getMipLodBias();
                }
                LogUtil.i(TAG, "mMipmapLodValue: " + this.mMipmapLodValue);
                this.mMipmapLodSeekBar.setProgress(this.mMipmapLodValue);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initTexturefilteLayout() {
        SeekBar seekBar = (SeekBar) findViewById(R.id.seek_bar_adreno_t);
        this.texturefilteSeekBar = seekBar;
        seekBar.setOnSeekBarChangeListener(this);
        LogUtil.i(TAG, "initTexturefilteLayout: " + (this.iGpuProfileControl != null));
        if (this.iGpuProfileControl != null) {
            try {
                if (checkGpuSettingsEntryNotNull()) {
                    this.mTexturefilteValue = this.iGpuProfileControl.getGpuSettingsEntry(this.mCurrentPkg).getTextureFilteringQuality();
                }
                this.texturefilteSeekBar.setProgress(this.mTexturefilteValue);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initViewOptions() {
        if (this.iGpuProfileControl != null) {
            if (this.getGpuSettings == 3) {
                this.snapdragonAdrenoGpuCustomLayout.setVisibility(0);
                this.adrGpuDialogImage.setVisibility(8);
            } else {
                this.snapdragonAdrenoGpuCustomLayout.setVisibility(8);
                this.adrGpuDialogImage.setVisibility(0);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean isAdrAutoVrsOpen() {
        /*
            r5 = this;
            java.lang.String r0 = "autoVRS: "
            cn.nubia.gpu.drivers.gpuprofile.IGpuProfileControl r1 = r5.iGpuProfileControl
            r2 = 0
            if (r1 == 0) goto L3d
            boolean r1 = r5.checkGpuSettingsEntryNotNull()     // Catch: android.os.RemoteException -> L33
            if (r1 == 0) goto L1a
            cn.nubia.gpu.drivers.gpuprofile.IGpuProfileControl r1 = r5.iGpuProfileControl     // Catch: android.os.RemoteException -> L33
            java.lang.String r5 = r5.mCurrentPkg     // Catch: android.os.RemoteException -> L33
            cn.nubia.gpu.drivers.gpuprofile.GpuSettingsEntry r5 = r1.getGpuSettingsEntry(r5)     // Catch: android.os.RemoteException -> L33
            int r5 = r5.getAutoVRS()     // Catch: android.os.RemoteException -> L33
            goto L1b
        L1a:
            r5 = r2
        L1b:
            java.lang.String r1 = "S-GPU"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: android.os.RemoteException -> L2e
            r3.<init>(r0)     // Catch: android.os.RemoteException -> L2e
            java.lang.StringBuilder r0 = r3.append(r5)     // Catch: android.os.RemoteException -> L2e
            java.lang.String r0 = r0.toString()     // Catch: android.os.RemoteException -> L2e
            cn.nubia.gamelauncher.gamecontrolpanel.utils.LogUtil.i(r1, r0)     // Catch: android.os.RemoteException -> L2e
            goto L39
        L2e:
            r0 = move-exception
            r4 = r0
            r0 = r5
            r5 = r4
            goto L35
        L33:
            r5 = move-exception
            r0 = r2
        L35:
            r5.printStackTrace()
            r5 = r0
        L39:
            r0 = 1
            if (r5 != r0) goto L3d
            r2 = r0
        L3d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gamelauncher.gamecontrolpanel.SnapdragonAdrenoGpuView.isAdrAutoVrsOpen():boolean");
    }

    private void reportGpuSettings(Bundle bundle) {
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "gpu_setting", bundle);
    }

    private void setAdrAutoVrsStatus(boolean z) {
        if (this.iGpuProfileControl != null) {
            try {
                if (supportMipmapLod()) {
                    this.iGpuProfileControl.store(this.mCurrentPkg, 3, this.mAntialiasingValue, this.mHeterosexualValue, this.mTexturefilteValue, z ? 1 : 0, this.mMipmapLodValue);
                } else {
                    this.iGpuProfileControl.save(this.mCurrentPkg, 3, this.mAntialiasingValue, this.mHeterosexualValue, this.mTexturefilteValue, z ? 1 : 0);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDetailsInfo(int i, int i2) {
        try {
            AlertDialog alertDialog = this.mDialog;
            if (alertDialog == null) {
                AlertDialog create = new AlertDialog.Builder(this.mContext, 2131952382).setTitle(i).setMessage(i2, 1).setNegativeButton(R.string.gpu_settings_dialog_know_title, new DialogInterface.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.SnapdragonAdrenoGpuView$$ExternalSyntheticLambda0
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i3) {
                        SnapdragonAdrenoGpuView.this.m277x37273287(dialogInterface, i3);
                    }
                }).create();
                this.mDialog = create;
                create.getWindow().setType(2038);
                this.mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.SnapdragonAdrenoGpuView$$ExternalSyntheticLambda1
                    @Override // android.content.DialogInterface.OnDismissListener
                    public final void onDismiss(DialogInterface dialogInterface) {
                        SnapdragonAdrenoGpuView.this.m278xb5883666(dialogInterface);
                    }
                });
            } else {
                alertDialog.setTitle(i);
                this.mDialog.setMessage(this.mContext.getString(i2), 1);
            }
            this.mDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showFlicker(String str) {
        View showFlickerView;
        if (TextUtils.isEmpty(str) || (showFlickerView = getShowFlickerView(str)) == null) {
            return;
        }
        AIFlickerTips.setFlickerName(showFlickerView, str);
        AIFlickerTips.setFlickerPadding(showFlickerView, 3, 3, 3, 3);
        AIFlickerTips.showFlicker(str);
    }

    private boolean supportGPUUpdate() {
        LogUtil.d(TAG, "supportGPUUpdate mSupportGPUUpdate: " + mSupportGPUUpdate);
        return mSupportGPUUpdate;
    }

    private boolean supportMipmapLod() {
        return mSupportMipmapLod;
    }

    private void switchPromptText(int i) {
        this.mPromptText.setText(i != 0 ? i != 1 ? i != 2 ? R.string.gpu_settings_option_summary : R.string.gpu_settings_option_highqual_summary : R.string.gpu_settings_option_powersav_summary : R.string.gpu_settings_option_standard_summary);
    }

    @Override // cn.nubia.gamelauncher.gamecontrolpanel.GameControlDialog.ISetViewAnimation
    public void animationSelf(final boolean z) {
        new Handler().post(new Runnable() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.SnapdragonAdrenoGpuView.8
            @Override // java.lang.Runnable
            public void run() {
                if (!z) {
                    SnapdragonAdrenoGpuView.this.adrGpuSettingImage.setAlpha(0.0f);
                    SnapdragonAdrenoGpuView.this.mLayoutView.setAlpha(0.0f);
                } else {
                    AnimationUtil.setGpuTranslationY(SnapdragonAdrenoGpuView.this.adrGpuSettingImage);
                    AnimationUtil.setGcsRedItemAlpha(SnapdragonAdrenoGpuView.this.adrGpuSettingImage);
                    AnimationUtil.setGpuTranslationY(SnapdragonAdrenoGpuView.this.mLayoutView);
                    AnimationUtil.setGcsRedItemAlpha(SnapdragonAdrenoGpuView.this.mLayoutView);
                }
            }
        });
    }

    public void bindService() {
        Intent intent = new Intent("cn.nubia.gpu.gpusetting.action");
        intent.setPackage("cn.nubia.gpu.drivers");
        this.mContext.bindService(intent, this.serviceConnection, 1);
    }

    public void closeParentDialog() {
        GameControlDialog gameControlDialog = this.mGameControlDialog;
        if (gameControlDialog != null) {
            gameControlDialog.closeDialog();
        }
    }

    public void initStartType(String str) {
        this.mCurrentPkg = str;
        initView();
    }

    public void initView() {
        LayoutInflater.from(getContext()).inflate(GameControlOrientationManager.getInstance().isPortrait() ? R.layout.snapdragon_adreno_gpu_layout_port : R.layout.snapdragon_adreno_gpu_layout, this);
        this.mLayoutView = findViewById(R.id.game_gpu_all_layout);
        this.adrGpuSettingImage = (ConstraintLayout) findViewById(R.id.adr_gpu_bg);
        new Handler().post(new Runnable() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.SnapdragonAdrenoGpuView.2
            @Override // java.lang.Runnable
            public void run() {
                AnimationUtil.setGpuTranslationY(SnapdragonAdrenoGpuView.this.adrGpuSettingImage);
                AnimationUtil.setGpuTranslationY(SnapdragonAdrenoGpuView.this.mLayoutView);
            }
        });
        this.adrGpuDialogImage = findViewById(R.id.snapdragon_adreno_gpu_dialog_layout);
        this.mPromptText = (TextView) findViewById(R.id.nubia_game_voice_strengthen_close_prompt);
        this.adrenoGpuStandard = (TextView) findViewById(R.id.nubia_game_gpu_adreno_standard);
        this.adrenoGpuPowersav = (TextView) findViewById(R.id.nubia_game_gpu_adreno_powersav);
        this.adrenoGpuHighqual = (TextView) findViewById(R.id.nubia_game_gpu_adreno_highqual);
        this.adrenoGpuCustomer = (TextView) findViewById(R.id.nubia_game_gpu_adreno_customer);
        this.adrenoGpuStandard.setOnClickListener(this);
        this.adrenoGpuPowersav.setOnClickListener(this);
        this.adrenoGpuHighqual.setOnClickListener(this);
        this.adrenoGpuCustomer.setOnClickListener(this);
        this.snapdragonAdrenoGpuCustomLayout = (LinearLayout) findViewById(R.id.snapdragon_adreno_gpu_custom_layout);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.auto_vrs_switch_layout);
        this.adrGpuAutoVrsSwitchRelativeLayout = relativeLayout;
        relativeLayout.setOnClickListener(this);
        this.adrAntialiasing = (ImageView) findViewById(R.id.adr_gpu_bg_a);
        this.adrHeterosexual = (ImageView) findViewById(R.id.adr_gpu_bg_h);
        this.adrTexturefilter = (ImageView) findViewById(R.id.adr_gpu_bg_t);
        this.mMipmapLodHelper = (ImageView) findViewById(R.id.mipMap_lod);
        this.mMipmapLayout = findViewById(R.id.mipmap_lod_layout);
        if (!supportMipmapLod()) {
            this.mMipmapLayout.setVisibility(8);
        }
        this.mGpuUpdateLayout = (LinearLayout) findViewById(R.id.gpu_update_layout);
        this.mGpuUpdateVersion = (TextView) findViewById(R.id.gpu_update_version);
        if (!supportGPUUpdate()) {
            LogUtil.i(TAG, "The game not support gpu update");
            this.mGpuUpdateLayout.setVisibility(8);
        }
        this.mGpuUpdateLayout.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.SnapdragonAdrenoGpuView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (SnapdragonAdrenoGpuView.this.mGpuUpdateUtils == null) {
                    SnapdragonAdrenoGpuView.this.mGpuUpdateUtils = new GpuUpdateUtils(SnapdragonAdrenoGpuView.this.getContext(), SnapdragonAdrenoGpuView.this);
                }
                SnapdragonAdrenoGpuView.this.mGpuUpdateUtils.getGpuUpdate().checkUpgrade();
            }
        });
        this.adrHeterosexual.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.SnapdragonAdrenoGpuView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SnapdragonAdrenoGpuView.this.showDetailsInfo(R.string.gpu_settings_custom_heterosexual_title, R.string.gpu_settings_custom_heterosexual_summa);
            }
        });
        this.adrTexturefilter.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.SnapdragonAdrenoGpuView.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SnapdragonAdrenoGpuView.this.showDetailsInfo(R.string.gpu_settings_custom_texturefilte_title, R.string.gpu_settings_custom_texturefilte_summa);
            }
        });
        this.adrAntialiasing.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.SnapdragonAdrenoGpuView.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SnapdragonAdrenoGpuView.this.showDetailsInfo(R.string.gpu_settings_custom_antialiasing_title, R.string.gpu_settings_custom_antialiasing_summa);
            }
        });
        this.mMipmapLodHelper.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.SnapdragonAdrenoGpuView$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SnapdragonAdrenoGpuView.this.m275xd0e8d64e(view);
            }
        });
        this.mModeSwitchLayout = findViewById(R.id.gpu_settings_mode_switch_layout);
        showFlicker(Utils.getHighLightViewId());
    }

    public void isChooseOptionsAdrenoGpu() {
        this.colorStateListSelected = getContext().getResources().getColorStateList(R.color.gcs_gamecenter_menu_text_checked);
        this.colorStateListNormal = getContext().getResources().getColorStateList(R.color.gcs_gamecenter_menu_text);
        if (this.iGpuProfileControl != null) {
            try {
                if (checkGpuSettingsEntryNotNull()) {
                    this.getGpuSettings = this.iGpuProfileControl.getGpuSettingsEntry(this.mCurrentPkg).getGpuSetting();
                }
                int i = this.getGpuSettings;
                if (i == 0) {
                    this.adrenoGpuStandard.setTextColor(this.colorStateListSelected);
                    this.adrenoGpuPowersav.setTextColor(this.colorStateListNormal);
                    this.adrenoGpuHighqual.setTextColor(this.colorStateListNormal);
                    this.adrenoGpuCustomer.setTextColor(this.colorStateListNormal);
                    this.adrenoGpuStandard.setBackgroundResource(R.drawable.shape_sample_rate);
                    this.adrenoGpuPowersav.setBackgroundResource(R.drawable.shape_sample_rate2);
                    this.adrenoGpuHighqual.setBackgroundResource(R.drawable.shape_sample_rate2);
                    this.adrenoGpuCustomer.setBackgroundResource(R.drawable.shape_sample_rate2);
                } else if (i == 1) {
                    this.adrenoGpuStandard.setTextColor(this.colorStateListNormal);
                    this.adrenoGpuPowersav.setTextColor(this.colorStateListSelected);
                    this.adrenoGpuHighqual.setTextColor(this.colorStateListNormal);
                    this.adrenoGpuCustomer.setTextColor(this.colorStateListNormal);
                    this.adrenoGpuStandard.setBackgroundResource(R.drawable.shape_sample_rate2);
                    this.adrenoGpuPowersav.setBackgroundResource(R.drawable.shape_sample_rate);
                    this.adrenoGpuHighqual.setBackgroundResource(R.drawable.shape_sample_rate2);
                    this.adrenoGpuCustomer.setBackgroundResource(R.drawable.shape_sample_rate2);
                } else if (i == 2) {
                    this.adrenoGpuStandard.setTextColor(this.colorStateListNormal);
                    this.adrenoGpuPowersav.setTextColor(this.colorStateListNormal);
                    this.adrenoGpuHighqual.setTextColor(this.colorStateListSelected);
                    this.adrenoGpuCustomer.setTextColor(this.colorStateListNormal);
                    this.adrenoGpuStandard.setBackgroundResource(R.drawable.shape_sample_rate2);
                    this.adrenoGpuPowersav.setBackgroundResource(R.drawable.shape_sample_rate2);
                    this.adrenoGpuHighqual.setBackgroundResource(R.drawable.shape_sample_rate);
                    this.adrenoGpuCustomer.setBackgroundResource(R.drawable.shape_sample_rate2);
                } else if (i == 3) {
                    this.adrenoGpuStandard.setTextColor(this.colorStateListNormal);
                    this.adrenoGpuPowersav.setTextColor(this.colorStateListNormal);
                    this.adrenoGpuHighqual.setTextColor(this.colorStateListNormal);
                    this.adrenoGpuCustomer.setTextColor(this.colorStateListSelected);
                    this.adrenoGpuStandard.setBackgroundResource(R.drawable.shape_sample_rate2);
                    this.adrenoGpuPowersav.setBackgroundResource(R.drawable.shape_sample_rate2);
                    this.adrenoGpuHighqual.setBackgroundResource(R.drawable.shape_sample_rate2);
                    this.adrenoGpuCustomer.setBackgroundResource(R.drawable.shape_sample_rate);
                }
                switchPromptText(this.getGpuSettings);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: lambda$initView$0$cn-nubia-gamelauncher-gamecontrolpanel-SnapdragonAdrenoGpuView, reason: not valid java name */
    /* synthetic */ void m275xd0e8d64e(View view) {
        showDetailsInfo(R.string.gpu_settings_custom_mipmap_lod_title, R.string.gpu_settings_custom_mipmap_lod_warning_text);
    }

    /* renamed from: lambda$onDetachedFromWindow$2$cn-nubia-gamelauncher-gamecontrolpanel-SnapdragonAdrenoGpuView, reason: not valid java name */
    /* synthetic */ void m276x7ec46a96() {
        IGpuProfileControl iGpuProfileControl = this.iGpuProfileControl;
        if (iGpuProfileControl != null) {
            try {
                int i = this.getGpuSettings;
                if (i == 3) {
                    LogUtil.i(TAG, "mAntialiasingValue=: " + this.mAntialiasingValue + " mHeterosexualValue=: " + this.mHeterosexualValue + " mTexturefilteValue=: " + this.mTexturefilteValue + " ;; mMipmapLodValue = " + this.mMipmapLodValue);
                    if (supportMipmapLod()) {
                        this.iGpuProfileControl.store(this.mCurrentPkg, this.getGpuSettings, this.mAntialiasingValue, this.mHeterosexualValue, this.mTexturefilteValue, this.isAdrAutoVrsSwitch ? 1 : 0, this.mMipmapLodValue);
                    } else {
                        this.iGpuProfileControl.save(this.mCurrentPkg, this.getGpuSettings, this.mAntialiasingValue, this.mHeterosexualValue, this.mTexturefilteValue, this.isAdrAutoVrsSwitch ? 1 : 0);
                    }
                } else {
                    iGpuProfileControl.save(this.mCurrentPkg, i, -1, -1, -1, -1);
                }
                reportGpuSettings(contructReportBundle());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: lambda$showDetailsInfo$3$cn-nubia-gamelauncher-gamecontrolpanel-SnapdragonAdrenoGpuView, reason: not valid java name */
    /* synthetic */ void m277x37273287(DialogInterface dialogInterface, int i) {
        this.mDialog.dismiss();
    }

    /* renamed from: lambda$showDetailsInfo$4$cn-nubia-gamelauncher-gamecontrolpanel-SnapdragonAdrenoGpuView, reason: not valid java name */
    /* synthetic */ void m278xb5883666(DialogInterface dialogInterface) {
        this.mDialog = null;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        this.mExecutor = new ThreadPoolExecutor(2, 2, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), Executors.defaultThreadFactory(), new RejectedExecutionHandler() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.SnapdragonAdrenoGpuView$$ExternalSyntheticLambda3
            @Override // java.util.concurrent.RejectedExecutionHandler
            public final void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
                LogUtil.d(SnapdragonAdrenoGpuView.TAG, Task.TAG + runnable.toString() + "rejected from " + threadPoolExecutor.toString());
            }
        });
        isChooseOptionsAdrenoGpu();
        initViewOptions();
        super.onAttachedToWindow();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.colorStateListSelected = getContext().getResources().getColorStateList(R.color.gcs_gamecenter_menu_text_checked);
        this.colorStateListNormal = getContext().getResources().getColorStateList(R.color.gcs_gamecenter_menu_text);
        int id = view.getId();
        if (this.iGpuProfileControl != null) {
            try {
                if (id == R.id.nubia_game_gpu_adreno_standard) {
                    this.getGpuSettings = 0;
                    this.adrenoGpuStandard.setTextColor(this.colorStateListSelected);
                    this.adrenoGpuPowersav.setTextColor(this.colorStateListNormal);
                    this.adrenoGpuHighqual.setTextColor(this.colorStateListNormal);
                    this.adrenoGpuCustomer.setTextColor(this.colorStateListNormal);
                    this.adrenoGpuStandard.setBackgroundResource(R.drawable.shape_sample_rate);
                    this.adrenoGpuPowersav.setBackgroundResource(R.drawable.shape_sample_rate2);
                    this.adrenoGpuHighqual.setBackgroundResource(R.drawable.shape_sample_rate2);
                    this.adrenoGpuCustomer.setBackgroundResource(R.drawable.shape_sample_rate2);
                    this.iGpuProfileControl.save(this.mCurrentPkg, 0, -1, -1, -1, -1);
                    this.snapdragonAdrenoGpuCustomLayout.setVisibility(8);
                    this.adrGpuDialogImage.setVisibility(0);
                } else if (id == R.id.nubia_game_gpu_adreno_powersav) {
                    this.getGpuSettings = 1;
                    this.adrenoGpuStandard.setTextColor(this.colorStateListNormal);
                    this.adrenoGpuPowersav.setTextColor(this.colorStateListSelected);
                    this.adrenoGpuHighqual.setTextColor(this.colorStateListNormal);
                    this.adrenoGpuCustomer.setTextColor(this.colorStateListNormal);
                    this.adrenoGpuStandard.setBackgroundResource(R.drawable.shape_sample_rate2);
                    this.adrenoGpuPowersav.setBackgroundResource(R.drawable.shape_sample_rate);
                    this.adrenoGpuHighqual.setBackgroundResource(R.drawable.shape_sample_rate2);
                    this.adrenoGpuCustomer.setBackgroundResource(R.drawable.shape_sample_rate2);
                    this.iGpuProfileControl.save(this.mCurrentPkg, 1, -1, -1, -1, -1);
                    this.snapdragonAdrenoGpuCustomLayout.setVisibility(8);
                    this.adrGpuDialogImage.setVisibility(0);
                } else if (id == R.id.nubia_game_gpu_adreno_highqual) {
                    this.getGpuSettings = 2;
                    this.adrenoGpuStandard.setTextColor(this.colorStateListNormal);
                    this.adrenoGpuPowersav.setTextColor(this.colorStateListNormal);
                    this.adrenoGpuHighqual.setTextColor(this.colorStateListSelected);
                    this.adrenoGpuCustomer.setTextColor(this.colorStateListNormal);
                    this.adrenoGpuStandard.setBackgroundResource(R.drawable.shape_sample_rate2);
                    this.adrenoGpuPowersav.setBackgroundResource(R.drawable.shape_sample_rate2);
                    this.adrenoGpuHighqual.setBackgroundResource(R.drawable.shape_sample_rate);
                    this.adrenoGpuCustomer.setBackgroundResource(R.drawable.shape_sample_rate2);
                    this.iGpuProfileControl.save(this.mCurrentPkg, 2, -1, -1, -1, -1);
                    this.snapdragonAdrenoGpuCustomLayout.setVisibility(8);
                    this.adrGpuDialogImage.setVisibility(0);
                } else if (id == R.id.nubia_game_gpu_adreno_customer) {
                    this.getGpuSettings = 3;
                    this.adrenoGpuStandard.setTextColor(this.colorStateListNormal);
                    this.adrenoGpuPowersav.setTextColor(this.colorStateListNormal);
                    this.adrenoGpuHighqual.setTextColor(this.colorStateListNormal);
                    this.adrenoGpuCustomer.setTextColor(this.colorStateListSelected);
                    this.adrenoGpuStandard.setBackgroundResource(R.drawable.shape_sample_rate2);
                    this.adrenoGpuPowersav.setBackgroundResource(R.drawable.shape_sample_rate2);
                    this.adrenoGpuHighqual.setBackgroundResource(R.drawable.shape_sample_rate2);
                    this.adrenoGpuCustomer.setBackgroundResource(R.drawable.shape_sample_rate);
                    if (supportMipmapLod()) {
                        this.iGpuProfileControl.store(this.mCurrentPkg, 3, this.mAntialiasingValue, this.mHeterosexualValue, this.mTexturefilteValue, this.isAdrAutoVrsSwitch ? 1 : 0, this.mMipmapLodValue);
                    } else {
                        this.iGpuProfileControl.save(this.mCurrentPkg, 3, this.mAntialiasingValue, this.mHeterosexualValue, this.mTexturefilteValue, this.isAdrAutoVrsSwitch ? 1 : 0);
                    }
                    initViewOptions();
                } else if (id == R.id.auto_vrs_switch) {
                    boolean z = !this.isAdrAutoVrsSwitch;
                    this.isAdrAutoVrsSwitch = z;
                    setSwitched(this.adrAutoVrsSwitch, z);
                    setAdrAutoVrsStatus(this.isAdrAutoVrsSwitch);
                }
                switchPromptText(this.getGpuSettings);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        LogUtil.i(TAG, "onDetachedFromWindow: " + (this.iGpuProfileControl != null));
        ExecutorService executorService = this.mExecutor;
        if (executorService != null) {
            executorService.submit(new Runnable() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.SnapdragonAdrenoGpuView$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    SnapdragonAdrenoGpuView.this.m276x7ec46a96();
                }
            });
            this.mExecutor.shutdownNow();
        }
        unbindService();
        AlertDialog alertDialog = this.mDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
            this.mDialog = null;
        }
        super.onDetachedFromWindow();
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (seekBar.getId() == R.id.seek_bar_adreno_a) {
            this.mAntialiasingValue = i;
            return;
        }
        if (seekBar.getId() == R.id.seek_bar_adreno_h) {
            this.mHeterosexualValue = i;
        } else if (seekBar.getId() == R.id.seek_bar_adreno_t) {
            this.mTexturefilteValue = i;
        } else if (seekBar.getId() == R.id.mipmap_lod_seekBar) {
            this.mMipmapLodValue = i;
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    public void setParentDialog(GameControlDialog gameControlDialog) {
        this.mGameControlDialog = gameControlDialog;
    }

    public void setSwitched(ImageView imageView, boolean z) {
        imageView.setImageResource(z ? R.drawable.function_toggle_on : R.drawable.function_toggle_off);
    }

    public void showFlicker(View view) {
        String highLightViewId = Utils.getHighLightViewId();
        if (TextUtils.isEmpty(highLightViewId)) {
            return;
        }
        AIFlickerTips.setFlickerName(view, highLightViewId);
        AIFlickerTips.setFlickerPadding(view, 3, 3, 3, 3);
        AIFlickerTips.showFlicker(highLightViewId);
    }

    public void unbindService() {
        LogUtil.d(TAG, "unbindService");
        try {
            if (this.mContext != null) {
                this.mContext.unbindService(this.serviceConnection);
            }
        } catch (IllegalArgumentException e) {
            LogUtil.e(TAG, " unbindService", e);
        }
    }
}
