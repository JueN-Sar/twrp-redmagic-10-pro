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
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import cn.nubia.common.app.AlertDialog;
import cn.nubia.common.config.GameSpaceConfig;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.gamecontrolpanel.GameControlDialog;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.AnimationUtil;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.LogUtil;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.Utils;
import cn.nubia.gamelauncher.util.NubiaTrackManager;
import cn.nubia.gpu.drivers.gpuprofile.IGpuProfileControl;
import com.android.systemui.shared.recents.model.Task;
import com.zte.gameassist.ai.AIFlickerTips;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class SpreadtrumGpuView extends FrameLayout implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, GameControlDialog.ISetViewAnimation {
    private static final int ADRENO_GPU_C = 3;
    private static final int ADRENO_GPU_H = 2;
    private static final int ADRENO_GPU_P = 1;
    private static final int ADRENO_GPU_S = 0;
    private static final int COLOR_MENU_NORMAL = 2131099884;
    private static final int COLOR_MENU_SELECTED = 2131099886;
    private static final String TAG = "S-GPU";
    private View adrGpuDialogImage;
    private ImageView adrHeterosexual;
    private ImageView adrTexturefilter;
    private TextView adrenoGpuCustomer;
    private TextView adrenoGpuHighqual;
    private TextView adrenoGpuPowersav;
    private TextView adrenoGpuStandard;
    private ColorStateList colorStateListNormal;
    private ColorStateList colorStateListSelected;
    private int getGpuSettings;
    private SeekBar heterosexualSeekBar;
    private IGpuProfileControl iGpuProfileControl;
    private Context mContext;
    private String mCurrentPkg;
    private AlertDialog mDialog;
    private ExecutorService mExecutor;
    private GameControlDialog mGameControlDialog;
    private int mHeterosexualValue;
    private View mLayoutView;
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

    public SpreadtrumGpuView(Context context) {
        this(context, null);
    }

    public SpreadtrumGpuView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SpreadtrumGpuView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mDialog = null;
        this.serviceConnection = new ServiceConnection() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.SpreadtrumGpuView.1
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                SpreadtrumGpuView.this.iGpuProfileControl = IGpuProfileControl.Stub.asInterface(iBinder);
                LogUtil.i(SpreadtrumGpuView.TAG, "onServiceConnected");
                SpreadtrumGpuView.this.isChooseOptionsAdrenoGpu();
                SpreadtrumGpuView.this.initViewOptions();
                SpreadtrumGpuView.this.initHeterosexualLayout();
                SpreadtrumGpuView.this.initTexturefilteLayout();
                SpreadtrumGpuView.this.initMipmapLodLayout();
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
                LogUtil.i(SpreadtrumGpuView.TAG, "onServiceDisconnected");
                SpreadtrumGpuView.this.iGpuProfileControl = null;
            }
        };
        this.mContext = context;
    }

    private boolean checkGpuSettingsEntryNotNull() {
        try {
            if (this.iGpuProfileControl.getGpuSettingsEntryInSprd(this.mCurrentPkg) != null) {
                return true;
            }
            LogUtil.i(TAG, " checkGpuSettingsEntryNotNull GpuSettingsEntry is null ");
            return false;
        } catch (RemoteException e) {
            LogUtil.e(TAG, " checkGpuSettingsEntryNotNull error ", e);
            return false;
        }
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
        if (this.getGpuSettings == 3) {
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
        String constructTextureFilter = constructTextureFilter();
        String constructHeterosexual = constructHeterosexual();
        String constructMipMapLOD = constructMipMapLOD();
        bundle.putString("anisotropic", constructHeterosexual);
        bundle.putString("texture_quality", constructTextureFilter);
        bundle.putString("mipmap_LOD", constructMipMapLOD);
        if (LogUtil.DEBUG) {
            StringBuilder sb = new StringBuilder("{app_name = ");
            sb.append(currentAppName).append(", package_name = ");
            sb.append(currentPkgName).append(", model = ");
            sb.append(gpuSettingsModel).append(", anisotropic = ");
            sb.append(constructHeterosexual).append(", texture_quality = ");
            sb.append(constructTextureFilter).append(", mipmap_LOD = ");
            sb.append(constructMipMapLOD);
            sb.append("}");
            LogUtil.d(TAG, "report data: " + ((Object) sb));
        }
        return bundle;
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
        return null;
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
                    this.mHeterosexualValue = this.iGpuProfileControl.getGpuSettingsEntryInSprd(this.mCurrentPkg).getTextureMaxAniso();
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
                    this.mMipmapLodValue = this.iGpuProfileControl.getGpuSettingsEntryInSprd(this.mCurrentPkg).getMipLodBias();
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
                    this.mTexturefilteValue = this.iGpuProfileControl.getGpuSettingsEntryInSprd(this.mCurrentPkg).getTextureFilteringQuality();
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

    private void reportGpuSettings(Bundle bundle) {
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "gpu_setting", bundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDetailsInfo(int i, int i2) {
        try {
            AlertDialog alertDialog = this.mDialog;
            if (alertDialog == null) {
                AlertDialog create = new AlertDialog.Builder(this.mContext, 2131952382).setTitle(i).setMessage(i2, 1).setNegativeButton(R.string.gpu_settings_dialog_know_title, new DialogInterface.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.SpreadtrumGpuView$$ExternalSyntheticLambda3
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i3) {
                        SpreadtrumGpuView.this.m281x2e9aad72(dialogInterface, i3);
                    }
                }).create();
                this.mDialog = create;
                create.getWindow().setType(2038);
                this.mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.SpreadtrumGpuView$$ExternalSyntheticLambda4
                    @Override // android.content.DialogInterface.OnDismissListener
                    public final void onDismiss(DialogInterface dialogInterface) {
                        SpreadtrumGpuView.this.m282xbb87c491(dialogInterface);
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

    private void switchPromptText(int i) {
        this.mPromptText.setText(i != 0 ? i != 1 ? i != 2 ? R.string.gpu_settings_option_summary : R.string.gpu_settings_option_highqual_summary : R.string.gpu_settings_option_powersav_summary : R.string.gpu_settings_option_standard_summary);
    }

    @Override // cn.nubia.gamelauncher.gamecontrolpanel.GameControlDialog.ISetViewAnimation
    public void animationSelf(final boolean z) {
        new Handler().post(new Runnable() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.SpreadtrumGpuView.5
            @Override // java.lang.Runnable
            public void run() {
                if (!z) {
                    SpreadtrumGpuView.this.mLayoutView.setAlpha(0.0f);
                } else {
                    AnimationUtil.setGpuTranslationY(SpreadtrumGpuView.this.mLayoutView);
                    AnimationUtil.setGcsRedItemAlpha(SpreadtrumGpuView.this.mLayoutView);
                }
            }
        });
    }

    public void bindService() {
        Intent intent = new Intent("cn.nubia.gpu.gpusetting.action");
        intent.setPackage("cn.nubia.gpu.drivers");
        this.mContext.bindService(intent, this.serviceConnection, 1);
    }

    public void initStartType(String str) {
        this.mCurrentPkg = str;
        initView();
    }

    public void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.spreadtrum_adreno_gpu_layout, this);
        this.mLayoutView = findViewById(R.id.game_gpu_all_layout);
        new Handler().post(new Runnable() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.SpreadtrumGpuView.2
            @Override // java.lang.Runnable
            public void run() {
                AnimationUtil.setGpuTranslationY(SpreadtrumGpuView.this.mLayoutView);
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
        this.adrHeterosexual = (ImageView) findViewById(R.id.adr_gpu_bg_h);
        this.adrTexturefilter = (ImageView) findViewById(R.id.adr_gpu_bg_t);
        this.mMipmapLodHelper = (ImageView) findViewById(R.id.mipMap_lod);
        this.mMipmapLayout = findViewById(R.id.mipmap_lod_layout);
        this.adrHeterosexual.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.SpreadtrumGpuView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SpreadtrumGpuView.this.showDetailsInfo(R.string.gpu_settings_custom_heterosexual_title, R.string.gpu_settings_custom_heterosexual_summa);
            }
        });
        this.adrTexturefilter.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.SpreadtrumGpuView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SpreadtrumGpuView.this.showDetailsInfo(R.string.gpu_settings_custom_texturefilte_title, R.string.gpu_settings_custom_texturefilte_summa);
            }
        });
        this.mMipmapLodHelper.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.SpreadtrumGpuView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SpreadtrumGpuView.this.m279x94387279(view);
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
                    this.getGpuSettings = this.iGpuProfileControl.getGpuSettingsEntryInSprd(this.mCurrentPkg).getGpuSetting();
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: lambda$initView$0$cn-nubia-gamelauncher-gamecontrolpanel-SpreadtrumGpuView, reason: not valid java name */
    /* synthetic */ void m279x94387279(View view) {
        showDetailsInfo(R.string.gpu_settings_custom_mipmap_lod_title, R.string.gpu_settings_custom_mipmap_lod_warning_text);
    }

    /* renamed from: lambda$onDetachedFromWindow$2$cn-nubia-gamelauncher-gamecontrolpanel-SpreadtrumGpuView, reason: not valid java name */
    /* synthetic */ void m280x624adcc1() {
        IGpuProfileControl iGpuProfileControl = this.iGpuProfileControl;
        if (iGpuProfileControl != null) {
            try {
                int i = this.getGpuSettings;
                if (i == 3) {
                    LogUtil.i(TAG, " mHeterosexualValue=: " + this.mHeterosexualValue + " mTexturefilteValue=: " + this.mTexturefilteValue + " ; mMipmapLodValue = " + this.mMipmapLodValue);
                    this.iGpuProfileControl.storeInSprd(this.mCurrentPkg, this.getGpuSettings, this.mHeterosexualValue, this.mTexturefilteValue, this.mMipmapLodValue);
                } else {
                    iGpuProfileControl.storeInSprd(this.mCurrentPkg, i, -1, -1, -1);
                }
                reportGpuSettings(contructReportBundle());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: lambda$showDetailsInfo$3$cn-nubia-gamelauncher-gamecontrolpanel-SpreadtrumGpuView, reason: not valid java name */
    /* synthetic */ void m281x2e9aad72(DialogInterface dialogInterface, int i) {
        this.mDialog.dismiss();
    }

    /* renamed from: lambda$showDetailsInfo$4$cn-nubia-gamelauncher-gamecontrolpanel-SpreadtrumGpuView, reason: not valid java name */
    /* synthetic */ void m282xbb87c491(DialogInterface dialogInterface) {
        this.mDialog = null;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        this.mExecutor = new ThreadPoolExecutor(2, 2, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), Executors.defaultThreadFactory(), new RejectedExecutionHandler() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.SpreadtrumGpuView$$ExternalSyntheticLambda1
            @Override // java.util.concurrent.RejectedExecutionHandler
            public final void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
                LogUtil.d(SpreadtrumGpuView.TAG, Task.TAG + runnable.toString() + "rejected from " + threadPoolExecutor.toString());
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
        LogUtil.i(TAG, "onClick: " + (this.iGpuProfileControl != null));
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
                    this.iGpuProfileControl.storeInSprd(this.mCurrentPkg, 0, -1, -1, -1);
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
                    this.iGpuProfileControl.storeInSprd(this.mCurrentPkg, 1, -1, -1, -1);
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
                    this.iGpuProfileControl.storeInSprd(this.mCurrentPkg, 2, -1, -1, -1);
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
                    this.iGpuProfileControl.storeInSprd(this.mCurrentPkg, 3, this.mHeterosexualValue, this.mTexturefilteValue, this.mMipmapLodValue);
                    initViewOptions();
                }
                switchPromptText(this.getGpuSettings);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        LogUtil.i(TAG, "onDetachedFromWindow: " + (this.iGpuProfileControl != null));
        ExecutorService executorService = this.mExecutor;
        if (executorService != null) {
            executorService.submit(new Runnable() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.SpreadtrumGpuView$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    SpreadtrumGpuView.this.m280x624adcc1();
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
        if (seekBar.getId() == R.id.seek_bar_adreno_h) {
            if (i == 1) {
                this.mHeterosexualValue = 0;
                return;
            } else {
                this.mHeterosexualValue = i;
                return;
            }
        }
        if (seekBar.getId() == R.id.seek_bar_adreno_t) {
            this.mTexturefilteValue = i;
        } else if (seekBar.getId() == R.id.mipmap_lod_seekBar) {
            if (i == 1) {
                this.mMipmapLodValue = 0;
            } else {
                this.mMipmapLodValue = i;
            }
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
            Context context = this.mContext;
            if (context != null) {
                context.unbindService(this.serviceConnection);
            }
        } catch (IllegalArgumentException e) {
            LogUtil.e(TAG, " unbindService", e);
        }
    }
}
